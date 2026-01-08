package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.math.BigDecimal;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;



/**
 * validierer basiert auf der neuen Kundeneinstufung:
 * - Zuerst wird geprueft, ob eine Firma eine Korrekte Firma ist (im Sinne von vollstaendig erfasst)
 * - Dann wird unterschieden, ob eine Adresse als PRIVAT oder als FIRMA eingestuft wird 
 * - bei Privatkunden wird geprueft, ob es eine Rechnungsposition ohne oder eine Gutschriftsposition mit MWSt gibt, dies ist beides nicht erlaubt,
 *   andere Steuersachverhalte werden separat geprueft   
 * @author martin
 *
 */
public class BSRG_K_LIST_BT_Mail_and_Print_Validiere_Steuer extends XX_ActionValidator {

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		return null;
	}

	@Override
	protected MyE2_MessageVector isValid(String cID_VKOPF_RG)	throws myException {

		MyE2_MessageVector oMV = new MyE2_MessageVector(); 
		
		RECORD_VKOPF_RG_ext     recVKOPF =    	new RECORD_VKOPF_RG_ext(cID_VKOPF_RG);
		RECORD_ADRESSE 			recAD = 		recVKOPF.get_UP_RECORD_ADRESSE_id_adresse();
//		RECORD_FIRMENINFO  		recFI = 		recAD.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
//		RECLIST_ADRESSE_UST_ID  rlZusatzSteuer = recAD.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();

		//zuerst pruefen, ob adresse im sinne der UST-Definition "korrekt" ist
//		__FS_Adress_Check oAdresseCheck = new __FS_Adress_Check(recFI.is_PRIVAT_YES(), 
//																recFI.is_FIRMA_YES(),
//																recAD.get_AUSWEIS_NUMMER_cUF_NN(""),
//																recAD.get_AUSWEIS_ABLAUF_DATUM_cF_NN(""),
//																recFI.is_FIRMA_OHNE_USTID_YES(), 
//																recFI.is_PRIVAT_MIT_USTID_YES(),
//																recFI.get_UMSATZSTEUERLKZ_cUF_NN(""), 
//																recFI.get_UMSATZSTEUERID_cUF_NN(""), 
//																recFI.get_STEUERNUMMER_cUF_NN(""),
//																rlZusatzSteuer.get_vKeyValues().size()>0, 
//																recAD.get_ID_LAND_cUF_NN(""));
		
		__FS_Adress_Check oAdresseCheck = new __FS_Adress_Check(recAD);
		
		
		/*
		 * es darf keine Rechnung/Gutschrift raus ohne mandanten-ustid
		 */
		if (S.isEmpty(recVKOPF.get_UMSATZSTEUERLKZ_MANDANT_cUF_NN("")) || S.isEmpty(recVKOPF.get_UMSATZSTEUERID_MANDANT_cUF_NN(""))) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler im Rechnungskopf:  ",true,
							"Im Rechnungskopf muss immer eine vollständige Mandanten-UST-ID stehen",true)));
		}
		
		
		if (!oAdresseCheck.get_bADRESSE_IST_OK()) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei Adressprüfung:  ")));
			oMV.add_MESSAGE(oAdresseCheck.get_Messages_4_Bewertung());
		} else {
			
			//wenn eine Firma aus der EU ohne steuerberaterauskunft, dann muss in der rechnung eine UST-ID des adressaten stehen
			if (oAdresseCheck.get_b_ADRESSE_MUSS_USTID_AUF_RECH_GUT_KOPF_HABEN()) {
				if (S.isEmpty(recVKOPF.get_UMSATZSTEUERLKZ_cUF_NN("")) || S.isEmpty(recVKOPF.get_UMSATZSTEUERID_cUF_NN(""))) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler im Rechnungskopf/Gutschriftskopf:   ",true,
									"Bei einer als FIRMA eingestuften Adresse Deutschland (ohne Ausnahmeschalter <Einstufung: FIRMA  (ohne UST-ID)>) oder aus der EU muss im Rechnungskopf eine vollständige Empfänger-UST-ID stehen",true)));
				}
			} 
			
			
			if (oAdresseCheck.get_bAdresse_IST_PRIVAT()) {
				//dann nachschauen, ob die basis-steuerregeln korrekt interpretiert werden
				boolean bRegel_Keine_MWST_Bei_Gutschrift_verletzt = false;
				boolean bRegel_IMMER_MWST_Bei_Rechnung_verletzt = false;
				
				RECLIST_VPOS_RG  rlRG = recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$DELETED+",'N')='N'",null,true);
				
				for (RECORD_VPOS_RG  recRGPos: rlRG.values()) {
					//positionen aus eine stornozyklus bleiben aussen vor, da diese bereits validiert wurden
					if (S.isEmpty(recRGPos.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) && S.isEmpty(recRGPos.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN(""))) {
						
						if (this.get_bIST_GUTSCHRIFTSPOS_Formal(recRGPos) && recRGPos.get_STEUERSATZ_bdValue(new BigDecimal(-1)).compareTo(BigDecimal.ZERO)!=0) {
							bRegel_Keine_MWST_Bei_Gutschrift_verletzt=true;
						}
	
						if (this.get_bIST_RECHPOS_Formal(recRGPos) && recRGPos.get_STEUERSATZ_bdValue(new BigDecimal(-1)).compareTo(BigDecimal.ZERO)==0) {
							bRegel_IMMER_MWST_Bei_Rechnung_verletzt=true;
						}
					}
				}
				
				if (bRegel_Keine_MWST_Bei_Gutschrift_verletzt) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Gutschriftsposition (oder negative Rechnungsposition) an eine als Privat eingestufte Adresse DARF keine MWSt. haben !")));
				}
				if (bRegel_IMMER_MWST_Bei_Rechnung_verletzt) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Rechnungsposition  (oder negative Gutschriftsposition) an eine als Privat eingestufte Adresse MUSS immer MWSt. haben !")));
				}
			}
			
		}

		return oMV;
	}

	
	private boolean get_bIST_RECHPOS_Formal(RECORD_VPOS_RG recRGPos) throws myException {
		boolean bRueck = false;
		
		if (   (recRGPos.get_LAGER_VORZEICHEN_lValue(0l)==-1 && bibNUM.IS_GREATER_EQUAL_0(recRGPos.get_EINZELPREIS_bdValue(BigDecimal.ZERO)))  ||
			   (recRGPos.get_LAGER_VORZEICHEN_lValue(0l)==1 && bibNUM.IS_LESS_0(recRGPos.get_EINZELPREIS_bdValue(BigDecimal.ZERO))) 	
			) {
			bRueck = true;
		}
		
		
		return bRueck;
	}
	
	private boolean get_bIST_GUTSCHRIFTSPOS_Formal(RECORD_VPOS_RG recRGPos) throws myException {
		boolean bRueck = false;
		
		if (    (recRGPos.get_LAGER_VORZEICHEN_lValue(0l)==1 && bibNUM.IS_GREATER_EQUAL_0(recRGPos.get_EINZELPREIS_bdValue(BigDecimal.ZERO)))  ||
				(recRGPos.get_LAGER_VORZEICHEN_lValue(0l)==-1 && bibNUM.IS_LESS_0(recRGPos.get_EINZELPREIS_bdValue(BigDecimal.ZERO))) 
			) {
			bRueck = true;
		}
		
		
		return bRueck;
	}

	
}
