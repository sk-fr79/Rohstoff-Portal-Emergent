package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_PROFORMA_RECHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_TPA_FUHRE_ORT_SPEZ;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_TPA_FUHRE_SPEZ;

public class FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS_NG {
	
	private Vector<String>  						vIDs = 						new Vector<String>();
	private Vector<FU_PRO_PassendeFuhrenInfos>   	vFU_PRO_CheckResults = 		new Vector<FU_PRO_PassendeFuhrenInfos>();
	private MyE2_MessageVector  					oMV = 						new MyE2_MessageVector();
	
	private int 									iAnzahlUebersprungeneFuhren = 0;
	

	
	/**
	 * prueft anhand von ids aus der fuhrenzentrale, ob es proforma-rechnungen zu drucken gib
	 * @param IDsFuhren
	 * @throws myException
	 */
	public FU_PRO_SuchePassende_Fuhren_And_CreatePROFORMA_RECHNUNG_RECORDS_NG(Vector<String> IDsFuhren) throws myException {
		super();
		
		this.vIDs.addAll(IDsFuhren);
		
		int iAnzahlProformaRechnungen = 0;
		
		for (String cID: vIDs) {
			RECORD_VPOS_TPA_FUHRE_SPEZ  recFuhre = new RECORD_VPOS_TPA_FUHRE_SPEZ(new RECORD_VPOS_TPA_FUHRE(cID));
			
			if (recFuhre.is_OHNE_ABRECHNUNG_YES()) {
				this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fuhre ",true,recFuhre.get_cInfoString(),false, " ist ohne Abrechnung, Proforma-Rechnung ist nicht möglich !",true)));
				this.iAnzahlUebersprungeneFuhren++;
			} else {
				
				
				
				//pruefen, ob die fuhre ins nicht-EU-Ausland geht
				if (recFuhre.get_bZielOrtIstAusland()!=null && recFuhre.get_bZielOrtIstDrittland()!=null && recFuhre.get_bQuelleOrtIstAusland()!=null && recFuhre.get_bQuelleOrtIstDrittland()!=null) {
					if ( 
							(recFuhre.get_bZielOrtIstAusland() && recFuhre.get_bZielOrtIstDrittland()) ||  
							(recFuhre.get_bQuelleOrtIstAusland() && recFuhre.get_bQuelleOrtIstDrittland()) 
						) {
						if (S.isFull(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF_NN("")) && 
							recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0) {
							iAnzahlProformaRechnungen++;
							
							this.vFU_PRO_CheckResults.add(
									new FU_PRO_PassendeFuhrenInfos(
											recFuhre.get_ID_VPOS_TPA_FUHRE_lValue(0l).intValue(),
											-1, 
											recFuhre.get_ID_ADRESSE_ZIEL_lValue(-1l).intValue(),
											recFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(-1l).intValue(),
											this.get_or_make_recPro(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), null)));
						} else {
							this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fuhre ",true,recFuhre.get_cInfoString(),false, 
									" ist undefiniert in Sorte, Menge oder Preis!",true)));								
						}
					} else {
						this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Haupt-Fuhre ",true,recFuhre.get_cInfoString(),false, 
								" führt nicht in ein Drittland !",true)));								
					}
				} else {
					this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Fuhre ",true,recFuhre.get_cInfoString(),false, 
							" ist undefiniert in Ziel-Ort oder Land !",true)));								
				}
				
				
				
				//jetzt potentielle fuhrenorte scannen
				RECLIST_VPOS_TPA_FUHRE_ORT  recListOrt = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
				
				for (RECORD_VPOS_TPA_FUHRE_ORT rec_Ort: recListOrt.values() ) {
					if (rec_Ort.is_DELETED_NO() && rec_Ort.get_DEF_QUELLE_ZIEL_cUF_NN("-").equals("VK"))
					{
						RECORD_VPOS_TPA_FUHRE_ORT_SPEZ  recOrt = new RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(rec_Ort);
						
						if (recOrt.get_bZielOrtIstAusland()!=null && recOrt.get_bZielOrtIstDrittland()!=null) {
							if (recOrt.get_bZielOrtIstAusland() && recOrt.get_bZielOrtIstDrittland()) {
								if (S.isFull(recOrt.get_ID_ARTIKEL_BEZ_cUF_NN("")) && 
									recOrt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0) {
//									recOrt.get_EINZELPREIS_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)!=0) {
									iAnzahlProformaRechnungen++;
									
									this.vFU_PRO_CheckResults.add(new FU_PRO_PassendeFuhrenInfos(	recOrt.get_ID_VPOS_TPA_FUHRE_lValue(0l).intValue(),
																								recOrt.get_ID_VPOS_TPA_FUHRE_ORT_lValue(0l).intValue(),
																								recOrt.get_ID_ADRESSE_lValue(-1l).intValue(),
																								recOrt.get_ID_ADRESSE_LAGER_lValue(-1l).intValue(),
																								this.get_or_make_recPro(recOrt.get_ID_VPOS_TPA_FUHRE_cUF(), recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF())));
								} else {
									this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("ZUSATZ_ABLADEORT der Fuhre ",true,recFuhre.get_cInfoString(),false, 
											" ist undefiniert in Sorte, Menge oder Preis!",true)));								
								}
							} else {
								this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("ZUSATZ_ABLADEORT der Fuhre ",true,recFuhre.get_cInfoString(),false, 
										" führt nicht in ein Drittland !",true)));								
							}
						} else {
							this.oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("ZUSATZ_ABLADEORT der Fuhre ",true,recFuhre.get_cInfoString(),false, 
									" ist undefiniert in Ziel-Ort oder Land !",true)));								
						}
			
					}
				}
			}
		}
		
		if (this.vIDs.size()==0) {
			this.oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens  eine Fuhre aus !",true)));		
		}
		
		if (iAnzahlProformaRechnungen==0) {
			this.oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Innerhalb der ausgewählten Fuhre wurde keine komplette Drittland-Fuhre gefunden !",true)));		
		}
	}

	private RECORD_PROFORMA_RECHNUNG get_or_make_recPro(String cID_VPOS_TPA_FUHRE, String cID_VPOS_TPA_FUHRE_ORT) throws myException {
		//jetzt pruefen, ob es schon einen RECORD_PROFORMA_RECHNUNG gibt
		RECLIST_PROFORMA_RECHNUNG  rlPro = new RECLIST_PROFORMA_RECHNUNG("SELECT * FROM "+bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+" WHERE "+
				_DB.PROFORMA_RECHNUNG+"."+_DB.PROFORMA_RECHNUNG$ID_VPOS_TPA_FUHRE+"="+cID_VPOS_TPA_FUHRE+" AND "+
			    (S.isEmpty(cID_VPOS_TPA_FUHRE_ORT)
			    ?
				_DB.PROFORMA_RECHNUNG+"."+_DB.PROFORMA_RECHNUNG$ID_VPOS_TPA_FUHRE_ORT+" IS NULL "
				:
				_DB.PROFORMA_RECHNUNG+"."+_DB.PROFORMA_RECHNUNG$ID_VPOS_TPA_FUHRE_ORT+"="+cID_VPOS_TPA_FUHRE_ORT));

		
		RECORD_PROFORMA_RECHNUNG 	recPFR = null;
		MyE2_MessageVector  		oMV    = new MyE2_MessageVector();
		
		if (rlPro.get_vKeyValues().size()==0) {		    //wenns keinen satz gib, dann einen machen
			RECORDNEW_PROFORMA_RECHNUNG  recNew = new RECORDNEW_PROFORMA_RECHNUNG();
			recNew.set_NEW_VALUE_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			if (S.isFull(cID_VPOS_TPA_FUHRE_ORT)) {
				recNew.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT);
			}
			
			//hier die daten des aktuellen benutzers eintragen
			recNew.set_NEW_VALUE_VORNAME_SACHBEARBEITER(bibALL.get_RECORD_USER().get_VORNAME_cUF_NN(""));
			recNew.set_NEW_VALUE_NAME_SACHBEARBEITER(bibALL.get_RECORD_USER().get_NAME1_cUF_NN(""));
			recNew.set_NEW_VALUE_TELEFON_SACHBEARBEITER(bibALL.get_RECORD_USER().get_TELEFON_cUF_NN(""));
			recNew.set_NEW_VALUE_EMAIL_SACHBEARBEITER(bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""));
			
			recPFR=recNew.do_WRITE_NEW_PROFORMA_RECHNUNG(oMV);
			
			if (oMV.get_bHasAlarms()) {
				bibMSG.add_MESSAGE(oMV);
				return null;
			}
			
			
		} else if (rlPro.get_vKeyValues().size()==1) {
			recPFR=rlPro.get(0);
		} else {
			throw new myException(this,"Error: More than one RECORDSETS <PROFORMA_RECHNUNG> are not possible !!");
		}
		
		return recPFR;
	}
	
	
	public Vector<FU_PRO_PassendeFuhrenInfos> get_vFuhrenFuhrenOrt_kombi() {
		return vFU_PRO_CheckResults;
	}

	public MyE2_MessageVector get_oMV() {
		return oMV;
	}

	public int get_iAnzahlUebersprungeneFuhren() {
		return iAnzahlUebersprungeneFuhren;
	}
	
}
