package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.math.BigDecimal;

import nextapp.echo2.app.SelectField;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class _HD_Station_FUHREN_MASKE extends HD_Station {

	private FU_MASK_ComponentMAP  	oMAP_FU = null;
	
	private boolean           	   	bIsLadeStation = true;    
	private boolean         		bSperreMaskenAenderung = false;

	/**
	 * 
	 * @param FU_MaskCompMAP
	 * @param IsLadestation
	 * @param bVerbieteSetzen  (wird nur true, wenn die klasse aus dem fuhrenort benutzt wird, dann darf die korrellierende Hauptfuhre nicht gesetzt werden)
	 * @throws myException
	 */
	public _HD_Station_FUHREN_MASKE(FU_MASK_ComponentMAP  FU_MaskCompMAP, boolean IsLadestation, boolean SperreMaskenAenderung) throws myException {
		super();
		this.oMAP_FU = 					FU_MaskCompMAP;
		this.bIsLadeStation = 			IsLadestation;
		this.bSperreMaskenAenderung = 	SperreMaskenAenderung;
		
		
		String 					cID_VPOS_TPA_FUHRE=	oMAP_FU.get_oInternalSQLResultMAP()==null?"":oMAP_FU.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		RECORD_VPOS_TPA_FUHRE 	recFuhre =			        S.isFull(cID_VPOS_TPA_FUHRE)?new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE):null;
		
		String   				cTP_Verantwortung = 		oMAP_FU.get_cActualDBValueFormated(_DB.VPOS_TPA_FUHRE$TP_VERANTWORTUNG);
		
		Long 					lID_ADRESSE_START= 			oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, true, true, null);
		String 					cID_ADRESSE_START = 		lID_ADRESSE_START==null?"":""+lID_ADRESSE_START.longValue();
		
		Long 					lID_ADRESSE_LAGER_START= 	oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START, true, true, null);
		String 					cID_ADRESSE_LAGER_START = 	lID_ADRESSE_LAGER_START==null?"":""+lID_ADRESSE_LAGER_START.longValue();

		Long 					lID_ARTIKEL_BEZ_START= 		oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_EK, true, true, null);
		String 					cID_ARTIKEL_BEZ_START = 	lID_ARTIKEL_BEZ_START==null?"":""+lID_ARTIKEL_BEZ_START.longValue();

		Long 					lID_ADRESSE_ZIEL= 			oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, true, true, null);
		String 					cID_ADRESSE_ZIEL = 			lID_ADRESSE_ZIEL==null?"":""+lID_ADRESSE_ZIEL.longValue();
		
		Long 					lID_ADRESSE_LAGER_ZIEL= 	oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, true, true, null);
		String 					cID_ADRESSE_LAGER_ZIEL = 	lID_ADRESSE_LAGER_ZIEL==null?"":""+lID_ADRESSE_LAGER_ZIEL.longValue();

		Long 					lID_ARTIKEL_BEZ_ZIEL= 		oMAP_FU.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_VK, true, true, null);
		String 					cID_ARTIKEL_BEZ_ZIEL = 		lID_ARTIKEL_BEZ_ZIEL==null?"":""+lID_ARTIKEL_BEZ_ZIEL.longValue();

		
		String 					cInfoTextName12LadeStation 	= "";
		String 					cInfoTextOrtLadeStation 	= "";
		String 					cArtikelBezEK 				= "";

		String 					cInfoTextName12AbladeStation= "";
		String 					cInfoTextOrtAbladeStation 	= "";
		String 					cArtikelBezVK 				= "";
	
		if (S.isFull(cID_ADRESSE_START)) {
			RECORD_ADRESSE  	recLadeStat = new RECORD_ADRESSE(cID_ADRESSE_START);
			cInfoTextName12LadeStation = 	recLadeStat.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2));
			cInfoTextOrtLadeStation =		recLadeStat.get_ORT_cUF_NN("");
		}
		
		if (S.isFull(cID_ARTIKEL_BEZ_START)) {
			RECORD_ARTIKEL_BEZ  recArtikelBez = new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ_START);
			cArtikelBezEK =		recArtikelBez.get_ARTBEZ1_cUF_NN("");
		}
	
		
		if (S.isFull(cID_ADRESSE_ZIEL)) {
			RECORD_ADRESSE  	recAbladeStat = new RECORD_ADRESSE(cID_ADRESSE_ZIEL);
			cInfoTextName12AbladeStation = 	recAbladeStat.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2));
			cInfoTextOrtAbladeStation =		recAbladeStat.get_ORT_cUF_NN("");
		}
		
		if (S.isFull(cID_ARTIKEL_BEZ_ZIEL)) {
			RECORD_ARTIKEL_BEZ  recArtikelBez = new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ_ZIEL);
			cArtikelBezVK =		recArtikelBez.get_ARTBEZ1_cUF_NN("");
		}
	
		if (this.bIsLadeStation) {
			this.init(	true, 
						true, 
						cID_ADRESSE_START, 
						cID_ADRESSE_LAGER_START, 
						cID_ARTIKEL_BEZ_START, 
						oMAP_FU.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$ANTEIL_LADEMENGE_LIEF,BigDecimal.ZERO,BigDecimal.ZERO),
						cTP_Verantwortung,
						recFuhre, 
						null, 
						cInfoTextName12LadeStation, 
						cInfoTextOrtLadeStation, 
						cArtikelBezEK,
						oMAP_FU.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_EK,BigDecimal.ZERO,BigDecimal.ZERO),
						new MyDate(oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_aufladen.fn()))
						);
		} else {
			this.init(	true, 
						false, 
						cID_ADRESSE_ZIEL, 
						cID_ADRESSE_LAGER_ZIEL, 
						cID_ARTIKEL_BEZ_ZIEL, 
						oMAP_FU.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$ANTEIL_ABLADEMENGE_ABN,BigDecimal.ZERO,BigDecimal.ZERO),
						cTP_Verantwortung,
						recFuhre, 
						null, 
						cInfoTextName12AbladeStation, 
						cInfoTextOrtAbladeStation, 
						cArtikelBezVK,
						oMAP_FU.get_bdActualDBValue(_DB.VPOS_TPA_FUHRE$EINZELPREIS_VK,BigDecimal.ZERO,BigDecimal.ZERO),
						new MyDate(oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_abladen.fn()))
						);
		}
		
		
	}

	@Override
	public MyE2_MessageVector applyResults(	HD_WarenBewegungEinstufung 	oHD_Fuhreneinstufung,
												String 					cID_TAX_UF,
												String 					cIntrastat_YN, 
												String 					cTransit_YN,
												boolean 				bEK_true__VK_false) throws myException {

		MyE2_MessageVector oMV = new MyE2_MessageVector();

		if (this.bSperreMaskenAenderung) {
			if (this.bIsLadeStation) {
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Steuereinträge Hauptfuhre/Ladeseite wurde nicht gesetzt. Die Anzeige ist nur ein Vorschlag !!!")));
			} else {
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Steuereinträge Hauptfuhre/Abladeseite wurde nicht gesetzt. Die Anzeige ist nur ein Vorschlag !!!")));
			}
			return oMV;
		}
		
		
		//hier die unterscheidung, ob die steuerermittlung auf mandantenadressen explizit oder implizit erfolgt
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES() && this.get_bStationIstMandant()) {
			// dann wird die tax-ermittlung durchgefuehrt, als waere es ein normaler ort, es wird aber der proforma-steuersatz fuer 
			// lager gesetzt 
			
			RECORD_TAX  recTax =  new HD__FinderProformaSteuersatzFuerMandantenorte().get_RECORD_TAX_proforma();
			 
			if (recTax==null) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Sie müssen einen Proforma-Steuersatz mit dem Merkmal: LEERVERMERK erzeugen")));
			} else {
				//20171114: wechseldatum der steuer beruecksichtigen
				RECORD_TAX_EXT recTax2=new RECORD_TAX_EXT(recTax);
				
				if (bEK_true__VK_false) {
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_EK).set_cActualMaskValue(recTax.get_ID_TAX_cF());
					
					String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_aufladen.fn())));
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_EK).set_cActualMaskValue(cTax);
					
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_EK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_IN).set_cActualMaskValue("N");
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$TRANSIT_EK).set_cActualMaskValue("N");
					oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Hauptfuhre/Ladestation: Proforma-Steuer für Lagerseite wurde gesetzt")));
					
					//20181127: die alten steuerselektions-dropdowns ausschalten
					neutralize(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK);
					
				} else {
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue(recTax.get_ID_TAX_cF());
					
					String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_abladen.fn())));
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_VK).set_cActualMaskValue(cTax);
					
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_VK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_OUT).set_cActualMaskValue("N");
					this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$TRANSIT_VK).set_cActualMaskValue("N");
					oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Hauptfuhre/Abladestation: Proforma-Steuer für Lagerseite wurde gesetzt")));
					
					//20181127: die alten steuerselektions-dropdowns ausschalten
					neutralize(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK);

				}
			}
		} else {
		
			RECORD_TAX  recTax = new RECORD_TAX(cID_TAX_UF);
			
			//20171114: wechseldatum der steuer beruecksichtigen
			RECORD_TAX_EXT recTax2=new RECORD_TAX_EXT(recTax);

			
			if (bEK_true__VK_false) {
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_EK).set_cActualMaskValue(recTax.get_ID_TAX_cF());
				
				String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_aufladen.fn())));
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_EK).set_cActualMaskValue(cTax);
				
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_EK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_IN).set_cActualMaskValue(cIntrastat_YN);
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$TRANSIT_EK).set_cActualMaskValue(cTransit_YN);
				oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Hauptfuhre/Ladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt")));
				
				//20181127: die alten steuerselektions-dropdowns ausschalten
				neutralize(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK);
				
			} else {
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue(recTax.get_ID_TAX_cF());

				String cTax = recTax2.getSteuerSatzKorrigiert(new MyDate(this.oMAP_FU.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_abladen.fn())));
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_VK).set_cActualMaskValue(cTax);
				
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_VK).set_cActualMaskValue(recTax.get_STEUERVERMERK_cUF_NN(""));
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_OUT).set_cActualMaskValue(cIntrastat_YN);
				this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$TRANSIT_VK).set_cActualMaskValue(cTransit_YN);
				oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Hauptfuhre/Abladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt")));
				
				//20181127: die alten steuerselektions-dropdowns ausschalten
				neutralize(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK);
				
			}
		
		}
		
		
		//2013-09-30: hier wird die id_handelsdef in das hilfsfeld gesetzt
		if (oHD_Fuhreneinstufung != null && oHD_Fuhreneinstufung.get_recHANDELSDEF()!=null) {
			this.oMAP_FU.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF).set_cActualMaskValue(oHD_Fuhreneinstufung.get_recHANDELSDEF().get_ID_HANDELSDEF_cUF_NN(""));
		}
		
		
		return oMV;
	}

	//20181127: beim finden einer steuerdefinition das alte steuer-selectField zu machen (sicherheitshalber innerhalb einer exception)
	private void neutralize(String key) {
		try {
			this.oMAP_FU.get__Comp(key).set_bEnabled_For_Edit(false);
			((SelectField) this.oMAP_FU.get__Comp(key)).setSelectedIndex(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean isUpdatingAllowd() throws myException {
		//2013-08-13: pruefung auf abschluss hier nicht mehr sinnvoll,
		//weil dies im aufruf schon geprueft wird (ueber den parameter bSperreMaskenAenderung)
//		
//		
//		//in der maskenbearbeitung kann, wenn eine von beiden preisabschluessen gesetzt ist, keine steuersetzung erfolgen
//		if (this.get_bEK()) {
//			return  !this.oMAP_FU.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS);
//		} else {
//			return  !this.oMAP_FU.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS);	
//		}
		
		return true;
		
	}
	
}


