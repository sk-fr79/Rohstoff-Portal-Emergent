package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.math.BigDecimal;

import nextapp.echo2.app.SelectField;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class _HD_Station_FUHREN_ORT_MASKE extends HD_Station {

	private FUO_MASK_ComponentMAP  oMAP_FUO = null;
	
	private boolean           	   bIsLadeStation = true;              
	
	
	public _HD_Station_FUHREN_ORT_MASKE(FUO_MASK_ComponentMAP  FUO_MaskCompMAP, FU_MASK_ComponentMAP  oFU_MASK_Map) throws myException {
		super();
		this.oMAP_FUO = 			FUO_MaskCompMAP;

		//der einzige wert aus der fuhren-MAP
		String   					cTP_Verantwortung = 		oFU_MASK_Map.get_cActualDBValueFormated(_DB.VPOS_TPA_FUHRE$TP_VERANTWORTUNG);
		
		//alles andere aus dem fuhrenort
		this.bIsLadeStation = 		this.oMAP_FUO.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_TPA_FUHRE_ORT$DEF_QUELLE_ZIEL).equals("'EK'");
		String 						cID_VPOS_TPA_FUHRE_ORT=		oMAP_FUO.get_oInternalSQLResultMAP()==null?"":oMAP_FUO.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrt =			    S.isFull(cID_VPOS_TPA_FUHRE_ORT)?new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT):null;
		
		
		Long 					lID_ADRESSE= 				oMAP_FUO.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, true, true, null);
		Long 					lID_ADRESSE_LAGER= 			oMAP_FUO.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE_LAGER, true, true, null);
		Long 					lID_ARTIKEL_BEZ= 			oMAP_FUO.get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ARTIKEL_BEZ, true, true, null);

		String 					cID_ADRESSE = 				lID_ADRESSE==null?"":""+lID_ADRESSE.longValue();
		String 					cID_ADRESSE_LAGER = 		lID_ADRESSE_LAGER==null?"":""+lID_ADRESSE_LAGER.longValue();
		String 					cID_ARTIKEL_BEZ = 			lID_ARTIKEL_BEZ==null?"":""+lID_ARTIKEL_BEZ.longValue();

		String 					cInfoTextName12 			= "";
		String 					cInfoTextOrt 				= "";
		String 					cArtikelBez 				= "";

	
		if (S.isFull(cID_ADRESSE)) {
			RECORD_ADRESSE  	recAdresse = new RECORD_ADRESSE(cID_ADRESSE);
			cInfoTextName12 = 	recAdresse.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2));
			cInfoTextOrt =		recAdresse.get_ORT_cUF_NN("");
		}
		
		if (S.isFull(cID_ARTIKEL_BEZ)) {
			RECORD_ARTIKEL_BEZ  recArtikelBez = new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ);
			cArtikelBez =		recArtikelBez.get_ARTBEZ1_cUF_NN("");
		}
	
	
		this.init(	false, 
					this.bIsLadeStation, 
					cID_ADRESSE, 
					cID_ADRESSE_LAGER, 
					cID_ARTIKEL_BEZ, 
					oMAP_FUO.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ANTEIL_LADEMENGE,BigDecimal.ZERO,BigDecimal.ZERO),
					cTP_Verantwortung,
					null, 
					recFuhreOrt, 
					cInfoTextName12, 
					cInfoTextOrt, 
					cArtikelBez,
					oMAP_FUO.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$EINZELPREIS,BigDecimal.ZERO,BigDecimal.ZERO),
					new MyDate(oMAP_FUO.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn()))
					);
		
		
	}

	@Override
	public MyE2_MessageVector applyResults(	HD_WarenBewegungEinstufung 	oHD_Fuhreneinstufung,
												String 					cID_TAX_UF,
												String 					cIntrastat_YN, 
												String 					cTransit_YN,
												boolean 				bEK_true__VK_false) throws myException {

		MyE2_MessageVector oMV = new MyE2_MessageVector();

		//hier die unterscheidung, ob die steuerermittlung auf mandantenadressen explizit oder implizit erfolgt
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES() && this.get_bStationIstMandant()) {
			// dann wird die tax-ermittlung durchgefuehrt, als waere es ein normaler ort, es wird aber der proforma-steuersatz fuer 
			// lager gesetzt 
			RECORD_TAX  recTax = new HD__FinderProformaSteuersatzFuerMandantenorte().get_RECORD_TAX_proforma();
			 
			if (recTax==null) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Sie müssen einen Proforma-Steuersatz mit dem Merkmal: LEERVERMERK erzeugen")));
			} else {
				
				//20171114: wechseldatum der steuer beruecksichtigen
				RECORD_TAX_EXT recTax2=new RECORD_TAX_EXT(recTax);

				
				String cMeldung = bEK_true__VK_false?"Zusatzort/Ladestation: Proforma-Steuer für Lagerseite wurde gesetzt":"Zusatzort/Abladestation: Proforma-Steuer für Lagerseite wurde gesetzt";
			
				this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_TAX).set_cActualMaskValue(recTax.get_ID_TAX_cF());
				
				String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FUO.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn())));
				this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$STEUERSATZ).set_cActualMaskValue(cTax);

				this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$EU_STEUER_VERMERK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
				this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$INTRASTAT_MELD).set_cActualMaskValue("N");
				this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$TRANSIT).set_cActualMaskValue("N");
				oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(cMeldung)));
				
				//20181127: die alten steuerselektions-dropdowns ausschalten
				neutralize(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT);

			}
		} else {
		
			RECORD_TAX  recTax = new RECORD_TAX(cID_TAX_UF);
		
			//20171114: wechseldatum der steuer beruecksichtigen
			RECORD_TAX_EXT recTax2=new RECORD_TAX_EXT(recTax);

			
			String cMeldung = bEK_true__VK_false?"Zusatzort/Ladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt":"Zusatzort/Abladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt";

			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_TAX).set_cActualMaskValue(recTax.get_ID_TAX_cF());
			
			String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FUO.get_cActualDBValueFormated(VPOS_TPA_FUHRE_ORT.datum_lade_ablade.fn())));
			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$STEUERSATZ).set_cActualMaskValue(cTax);
			
			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$EU_STEUER_VERMERK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$INTRASTAT_MELD).set_cActualMaskValue(cIntrastat_YN);
			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$TRANSIT).set_cActualMaskValue(cTransit_YN);
			oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(cMeldung)));
			
			//20181127: die alten steuerselektions-dropdowns ausschalten
			neutralize(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT);

		}
		
		
		//2013-09-30: hier wird die id_handelsdef in das hilfsfeld gesetzt
		if (oHD_Fuhreneinstufung != null && oHD_Fuhreneinstufung.get_recHANDELSDEF()!=null) {
			this.oMAP_FUO.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_HANDELSDEF).set_cActualMaskValue(oHD_Fuhreneinstufung.get_recHANDELSDEF().get_ID_HANDELSDEF_cUF_NN(""));
		}
		

		
		return oMV;
	}

	
	//20181127: beim finden einer steuerdefinition das alte steuer-selectField zu machen (sicherheitshalber innerhalb einer exception)
	private void neutralize(String key) {
		try {
			this.oMAP_FUO.get__Comp(key).set_bEnabled_For_Edit(false);
			((SelectField) this.oMAP_FUO.get__Comp(key)).setSelectedIndex(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public boolean isUpdatingAllowd() throws myException {
		//in der maskenbearbeitung kann, wenn eine von beiden preisabschluessen gesetzt ist, keine steuersetzung erfolgen
		return  !this.oMAP_FUO.get_bActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$PRUEFUNG_PREIS);
	}
	
}


