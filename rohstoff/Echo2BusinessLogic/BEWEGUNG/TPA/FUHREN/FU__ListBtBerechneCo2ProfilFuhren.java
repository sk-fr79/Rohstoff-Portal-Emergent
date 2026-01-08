/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 09.07.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoRoutingEntfernungUndKostenBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoRoutingEntfernungUndKosten.ENUM_ROUTING_TYP;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_XLS;
import panter.gmbh.Echo2.Messaging.E2_WindowPane_4_PopupMessages;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_CO2_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyDecimalFormat;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec21SaveOnlyChanged;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre.TYPLKW;

/**
 * @author martin
 * @date 09.07.2019
 * neuer popup-button in der fuhrenzentrale mit durchlauf durch selektierte Fuhren und 
 * berechnung der entfernung von geocodierten fuhren inclusive erstellung einer statistik (nur hauptfuhren)
 */
public class FU__ListBtBerechneCo2ProfilFuhren extends MyE2_Button {

	private E2_NavigationList naviList = null; 
	
	private static String paramUUID4Report = "UUID";

	
	public FU__ListBtBerechneCo2ProfilFuhren(E2_NavigationList p_naviList) throws myException {
		super();
		
		this.naviList = p_naviList;
		
		this._t(S.ms("Entfernungsberechung durchführen"));
		this._aaa(new ActionBerechneDifferenzen(p_naviList));
		
		this._v(ENUM_VALIDATION.WARENBEWEGUNGEN_ENTFERNUNGEN_BERECHNEN.getValidator());
	}
	
	
	
	private class ActionBerechneDifferenzen extends E2_NaviListActionOnSelection {

		private RB_cb 				cbKmBerechnungImmerAusfuehren = (RB_cb)new RB_cb()._t(S.ms("Entfernungsberechnungen immer durchführen ?"))
																						._setSelected()._ttt("Falls nicht angewählt, werden vorhandene Entfernungsangaben nicht neu berechnet !");
		
		private RB_cb 				cbUeberschreibeBestehendeKmAngaben = new RB_cb()._t(S.ms("Bestehende Entfernungen überschreiben ?"))._setSelected();
		private RB_TextField 		tfVerbrauchLKWOhneAnhaenger = new RB_TextField()._w(100);
		private RB_TextField 		tfVerbrauchLKWMitAnhaenger = new RB_TextField()._w(100);
		private RB_TextField 		tfKgCO2ProLiterDiesel = new RB_TextField()._w(100);

		//eingelesene Werte aus dem startbildschirm
		private BigDecimal  bdVerbrauchLKWOhneAnhaenger = null;
		private BigDecimal  bdVerbrauchLKWMitAnhaenger = null;
		private BigDecimal  bdCO2ProLiter = null;
		
		private int counterFehler = 0;
		private int counterNoGeoDaten = 0;
		private int counterBerechnet = 0;
		private int counterGeschriebene = 0;
		private int countSkipedWgFuhrenOrt = 0;
		
		private int countProtokollSaves = 0;
		
		private String      uuidString = null;    //wird mitgefuehrt, um den listendruck zu starten auf der basis der aktuellen berechnung
		
		private BigDecimal  bdVerbrauchDieselGesamt = BigDecimal.ZERO;
		private BigDecimal  bdSummeTonnageTransport = BigDecimal.ZERO;
		
		private BigDecimal bdSummeKm = new BigDecimal(0);
		
		private VEK<Long>   vAdressenOhneGeocodierung = new VEK<>();
		private VEK<Long>   vFuhrenInhomogeneOrt = new VEK<>();
		
		private ResultWindow  popupResult = null;
		
		public ActionBerechneDifferenzen(E2_NavigationList p_naviList) throws myException {
			super(p_naviList);
			
			this.getCbSelected()._t(S.ms("Entfernungen der ausgewählten Fuhren berechnen"));
			this.getCbAllInActualPage()._t(S.ms("Entfernungen der Fuhren der aktuellen Seite berechnen"));
			this.getCbAllInListFilter()._t(S.ms("Entfernungen aller gefilterten Fuhren berechnen"));
			
			this.tfVerbrauchLKWOhneAnhaenger.setText(ENUM_MANDANT_CONFIG.BASIS_VERBRAUCH_LKW_OHNE_ANHAENGER.getCheckedValue());
			this.tfVerbrauchLKWMitAnhaenger.setText(ENUM_MANDANT_CONFIG.BASIS_VERBRAUCH_LKW_MIT_ANHAENGER.getCheckedValue());
			this.tfKgCO2ProLiterDiesel.setText(ENUM_MANDANT_CONFIG.KG_CO2_PRO_LITER_DIESEL.getCheckedValue());
			
			
			this._setAutoCloseProgressWindowAfterDone(true)
				._setGrenzeBisFortschrittsanzeige(20)
				._setLoopRefreshTimeInMilliSeconds(7000)
				._setTitelInPopup(S.ms("Berechne Entfernungen der Fuhren von Start bis Zielort (nur Hauptfuhren)"))
				._setTitelOfProgressWindow(S.ms("km-Berechnung"))
				._setWidthOfPopup(600)
				._setHeightOfPopup(470)
				._setWidthOfProgressPopup(1100)
				._setHeightOfProgressPopup(200)
				._setClosePopupAfterDone(false)
				;
			
			this.getBtOK()._aaa(()->{getBtOK().set_bEnabled_For_Edit(false);});
			
			this.getBtOK()._addValidator(()->{
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				BigDecimal bdVerbrauchLKW = new MyDecimalFormat().getParsedBigDecimal(tfVerbrauchLKWOhneAnhaenger.getText());
				BigDecimal bdVerbrauchLKWMit = new MyDecimalFormat().getParsedBigDecimal(tfVerbrauchLKWMitAnhaenger.getText());
				BigDecimal bdCO2ProLiter = new MyDecimalFormat().getParsedBigDecimal(tfKgCO2ProLiterDiesel.getText());
				
				if (O.isOneNull(bdVerbrauchLKW,bdVerbrauchLKWMit,bdCO2ProLiter)) {
					mv._addAlarm(S.ms("Bitte alle Zahlenwerte korrekt ausfüllen !"));
				}
				
				return mv;
			});
			
			
		}

		@Override
		public void setShapeOfSelectPopup(E2_BasicModuleContainer popup) {
			
			try {
				this.getBtOK().set_bEnabled_For_Edit(true);
			} catch (myException e) {
				e.printStackTrace();
			}
			
			E2_Grid grid = new E2_Grid()
					._setSize(450,100)
					._a(new RB_lab()._t(getTitelInPopup())._fsa(2), new RB_gld()._span(2)._ins(5, 15, 5, 15))
					
					._a(new RB_lab()._t("Auswahl"), new RB_gld()._ins(5,5,5,5)._col_back_d())
					._a(new RB_lab()._t("aktuell"), new RB_gld()._ins(5,5,5,5)._col_back_d()._center_mid())
					
					._a(getCbAllInListFilter(), new RB_gld()._ins(5, 2, 5, 2))
					._a(new RB_lab()._t(""+getNaviList().get_vectorSegmentation().size()), new RB_gld()._ins(5, 2, 5, 2)._center_mid())
					
					._a(getCbAllInActualPage(), new RB_gld()._ins(5, 2, 5, 2))
					._a(new RB_lab()._t(""+getNaviList().get_vActualID_Segment().size()), new RB_gld()._ins(5, 2, 5, 2)._center_mid())
					
					._a(getCbSelected(), new RB_gld()._ins(5, 2, 5, 10))
					._a(new RB_lab()._t(""+getNaviList().get_vSelectedIDs_Unformated().size()), new RB_gld()._ins(5, 2, 5, 10)._center_mid())

					._addSeparator()
					
					._a(this.cbKmBerechnungImmerAusfuehren, 			new RB_gld()._span(2)._ins(5, 10, 5, 2))
					._a(this.cbUeberschreibeBestehendeKmAngaben, 		new RB_gld()._span(2)._ins(5, 2, 5, 10))

					._addSeparator()

					._a(new RB_lab()._tr("Angenommener Verbrauch LKW/100km ohne Anhänger"), 	new RB_gld()._ins(5, 10, 5, 5))
					._a(this.tfVerbrauchLKWOhneAnhaenger, 								new RB_gld()._ins(5, 10, 5, 5)._center_mid())
					
					._a(new RB_lab()._tr("Angenommener Verbrauch LKW/100km mit Anhänger"), 	new RB_gld()._ins(5, 2, 5, 5))
					._a(this.tfVerbrauchLKWMitAnhaenger, 								new RB_gld()._ins(5, 2, 5, 5)._center_mid())
					
					._a(new RB_lab()._tr("Erzeugtes CO2 pro Liter Diesel"), 			new RB_gld()._ins(5, 2, 5, 5))
					._a(this.tfKgCO2ProLiterDiesel, 									new RB_gld()._ins(5, 2, 5, 5)._center_mid())
					
					
					._a(new E2_Grid()._setSize(100,100)._a(getBtOK(), new RB_gld()._ins(0, 0, 10, 0))._a(getBtCancel(), new RB_gld()._ins(0, 0, 0, 0))
						, new RB_gld()._span(2)._ins(5,20,5,5))
					
					;
			
			popup.RESET_Content();
			
			popup.add(grid, E2_INSETS.I(10,0,0,0));

			
		}

		@Override
		public void doSomethingBeforeLoop() throws myException {
			counterFehler = 				0;
			counterNoGeoDaten = 			0;
			counterBerechnet = 				0;
			counterGeschriebene = 			0;
			countSkipedWgFuhrenOrt = 		0;
			countProtokollSaves = 			0;
			
			bdSummeKm = 					new BigDecimal(0);
			
			bdVerbrauchLKWOhneAnhaenger = 	null;
			bdVerbrauchLKWMitAnhaenger = 	null;
			bdCO2ProLiter = 				null;
			bdVerbrauchLKWOhneAnhaenger =	new MyDecimalFormat().getParsedBigDecimal(tfVerbrauchLKWOhneAnhaenger.getText());
			bdVerbrauchLKWMitAnhaenger = 	new MyDecimalFormat().getParsedBigDecimal(tfVerbrauchLKWMitAnhaenger.getText());
			bdCO2ProLiter = 				new MyDecimalFormat().getParsedBigDecimal(tfKgCO2ProLiterDiesel.getText());

			bdVerbrauchDieselGesamt = 		new BigDecimal(0);
			bdSummeTonnageTransport = 		new BigDecimal(0);
			
			
			vAdressenOhneGeocodierung.clear();
			vFuhrenInhomogeneOrt.clear();
			popupResult = null;
			
			uuidString = UUID.randomUUID().toString();
		}

		
		@Override
		public void doSomethingWithId(String idUnformatedRowId) throws myException {
			
			try {
				Rec21SaveOnlyChanged fuhre = new Rec21SaveOnlyChanged(_TAB.vpos_tpa_fuhre)._fill_id(idUnformatedRowId);
				
				Rec21_VposTpaFuhre 	rFuhre = new Rec21_VposTpaFuhre(fuhre);
				
				Rec21SaveOnlyChanged recCo2 = 		rFuhre.getRecFuhrenCo2Profil();
				
				MyE2_MessageVector mvSaveCo2 = bibMSG.getNewMV();
				recCo2._setNewVal(FUHREN_CO2_PROFIL.id_vpos_tpa_fuhre, 		rFuhre.getIdLong(), mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.uuid_code_of_call, 		uuidString, mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.co2_pro_liter_diesel, 	bdCO2ProLiter, mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.liter_pro_100_lkw, 		bdVerbrauchLKWOhneAnhaenger, mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.liter_pro_100_lkw_anh, 	bdVerbrauchLKWMitAnhaenger, mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.eigene_fuhre, 		    rFuhre.isOwnTransport()?"Y":"N", mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.lkw_mit_anhaenger, 	    S.isFull(rFuhre.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen))?"Y":"N", mvSaveCo2);
				recCo2._setNewVal(FUHREN_CO2_PROFIL.id_einheit,				rFuhre.getIDEinheit(), mvSaveCo2);
				
				Boolean  transportHomogen = rFuhre.isTransportHomogen();
				
				if (transportHomogen==null) {
					counterFehler++;
				} else {
//					if (!transportHomogen) {
//				}
//					countSkipedWgFuhrenOrt++;
//					recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.INHOMOGEN_FUHRENORT.db_val(), mvSaveCo2);
//					saveProtokollRecord(recCo2);
//					
//					vFuhrenInhomogeneOrt._a(rFuhre.getIdLong());
//					
//				} else {
				
					Rec21 adresseStart = fuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_start,ADRESSE.id_adresse,true);
					Rec21 adresseZiel = fuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_ziel,ADRESSE.id_adresse,true);
					
					BigDecimal bdStartLatitude = (BigDecimal)adresseStart.getRawVal(ADRESSE.latitude); 
					BigDecimal bdStartLongitude = (BigDecimal)adresseStart.getRawVal(ADRESSE.longitude); 
					
					BigDecimal bdZielLatitude = (BigDecimal)adresseZiel.getRawVal(ADRESSE.latitude); 
					BigDecimal bdZielLongitude = (BigDecimal)adresseZiel.getRawVal(ADRESSE.longitude); 
					
					if (O.isNoOneNull(bdStartLatitude,bdStartLongitude,bdZielLatitude,bdZielLongitude)) {
						BigDecimal 								km = (BigDecimal)rFuhre.getRawVal(VPOS_TPA_FUHRE.routing_distance_km);
						BigDecimal								minutes = (BigDecimal)rFuhre.getRawVal(VPOS_TPA_FUHRE.routing_time_minutes);
						
						HashMap<ENUM_ROUTING_TYP, BigDecimal> 	result = null;
						MyE2_MessageVector 						mv = bibMSG.getNewMV();
						
						if (km==null || km.longValue()<5 || minutes ==null || cbKmBerechnungImmerAusfuehren.isSelected()) {
							PdServiceGeoRoutingEntfernungUndKostenBean diffs = new PdServiceGeoRoutingEntfernungUndKostenBean();
							result = diffs.getDistanceTimeAmdCosts(bdStartLatitude,bdStartLongitude,bdZielLatitude,bdZielLongitude, null, null, mv);
							km = result.get(ENUM_ROUTING_TYP.ENTFERNUNG);
							minutes = result.get(ENUM_ROUTING_TYP.ZEIT_MIN);
						}

						if (mv.get_bHasAlarms()) {
							counterFehler++;
							recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.UNKNOWN_ERROR.db_val(), mvSaveCo2);
							saveProtokollRecord(recCo2);
						} else {
							
							bdSummeKm = bdSummeKm.add(km);
							counterBerechnet++;
							BigDecimal bdVerbrauchDerFahrt = new BigDecimal(0);
							if (rFuhre.getTypLKW()==TYPLKW.LKW_MIT_ANHAENGER) {
								bdVerbrauchDerFahrt = km.multiply(bdVerbrauchLKWMitAnhaenger, MathContext.DECIMAL128).divide(new BigDecimal(100),  MathContext.DECIMAL128);
								bdVerbrauchDieselGesamt = bdVerbrauchDieselGesamt.add(bdVerbrauchDerFahrt);
							} else {
								bdVerbrauchDerFahrt = km.multiply(bdVerbrauchLKWOhneAnhaenger, MathContext.DECIMAL128).divide(new BigDecimal(100),  MathContext.DECIMAL128);
								bdVerbrauchDieselGesamt = bdVerbrauchDieselGesamt.add(bdVerbrauchDerFahrt);
							}
							
							recCo2._setNewVal(FUHREN_CO2_PROFIL.km_distanz, km, mvSaveCo2);
							recCo2._setNewVal(FUHREN_CO2_PROFIL.spritverbrauch_fuhre_l, bdVerbrauchDerFahrt, mvSaveCo2);
							recCo2._setNewVal(FUHREN_CO2_PROFIL.ladung_fuhre_in_tonnen, rFuhre.getAbladeMengeInTonnen(), mvSaveCo2);
							recCo2._setNewVal(FUHREN_CO2_PROFIL.co2_erzeugung_fuhre_kg, bdVerbrauchDerFahrt
																						.multiply(bdCO2ProLiter,MathContext.DECIMAL128)
																						.setScale(2, RoundingMode.HALF_UP), mvSaveCo2);
							
							// falls die Fuhre Inhomogen war, hier den Status kennzeichnen
							if (!transportHomogen){
								recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.INHOMOGEN_FUHRENORT.db_val(), mvSaveCo2);
							} else {
								recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.OK.db_val(), mvSaveCo2);
							}
							
							
							
							bdSummeTonnageTransport = bdSummeTonnageTransport.add(O.NN(rFuhre.getAbladeMengeInTonnen(), BigDecimal.ZERO));
							
							if (fuhre.getRawVal(VPOS_TPA_FUHRE.routing_distance_km)==null
										|| fuhre.getRawVal(VPOS_TPA_FUHRE.routing_time_minutes) == null
										|| cbUeberschreibeBestehendeKmAngaben.isSelected()) {
								mv.clear();
								
								fuhre._setNewVal(VPOS_TPA_FUHRE.routing_distance_km,km , mv);
								fuhre._setNewVal(VPOS_TPA_FUHRE.routing_time_minutes, minutes != null? minutes.longValue():null, mv);
								
								if (mv.get_bHasAlarms()) {
									counterBerechnet--;
									counterFehler++;
								} else {
									fuhre._SAVE(true, true, mv);
									if (mv.get_bHasAlarms()) {
										counterBerechnet--;
										counterFehler++;
									} else {
										counterGeschriebene++;
									}
								}
							} 
							saveProtokollRecord(recCo2);
						}
					} else {
						counterNoGeoDaten++;
						recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.NO_COORDINATES.db_val(), mvSaveCo2);
						saveProtokollRecord(recCo2);
						
						VEK<Long> adresseOhneGeoCode = rFuhre.getAdressenOhneGeocodierung();
						if (adresseOhneGeoCode!=null) {
							for (Long id: adresseOhneGeoCode) {
								vAdressenOhneGeocodierung._addIfNotIn(id);
							}
						}
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				counterFehler++;
			}
			
		}

		private void saveProtokollRecord(Rec21SaveOnlyChanged protokollSet) {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			try {
				protokollSet._SAVE(true, mv);
				if (mv.isOK()) {
					countProtokollSaves++;
				}
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}

		
		
		
		
		
		
//		@Override
//		public void doSomethingWithId(String idUnformatedRowId) throws myException {
//			
//			try {
//				Rec22 fuhre = new Rec22(_TAB.vpos_tpa_fuhre)._fill_id(idUnformatedRowId);
//				
//				Rec21_VposTpaFuhre 	rFuhre = new Rec21_VposTpaFuhre(fuhre);
//				
//				Rec22 recCo2 = 		rFuhre.getRecFuhrenCo2Profil();
//				
//				MyE2_MessageVector mvSaveCo2 = bibMSG.getNewMV();
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.id_vpos_tpa_fuhre, 		rFuhre.getIdLong(), mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.uuid_code_of_call, 		uuidString, mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.co2_pro_liter_diesel, 	bdCO2ProLiter, mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.liter_pro_100_lkw, 		bdVerbrauchLKWOhneAnhaenger, mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.liter_pro_100_lkw_anh, 	bdVerbrauchLKWMitAnhaenger, mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.eigene_fuhre, 		    rFuhre.isOwnTransport()?"Y":"N", mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.lkw_mit_anhaenger, 	    S.isFull(rFuhre.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen))?"Y":"N", mvSaveCo2);
//				recCo2._setNewVal(FUHREN_CO2_PROFIL.id_einheit,				rFuhre.getIDEinheit(), mvSaveCo2);
//				
//				Boolean  transportHomogen = rFuhre.isTransportHomogen();
//				
//				if (transportHomogen==null) {
//					counterFehler++;
//				} else if (!transportHomogen) {
//					countSkipedWgFuhrenOrt++;
//					recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.INHOMOGEN_FUHRENORT.db_val(), mvSaveCo2);
//					saveProtokollRecord(recCo2);
//					
//					vFuhrenInhomogeneOrt._a(rFuhre.getIdLong());
//					
//				} else {
//				
//					Rec21 adresseStart = fuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_start,ADRESSE.id_adresse,true);
//					Rec21 adresseZiel = fuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_ziel,ADRESSE.id_adresse,true);
//					
//					BigDecimal bdStartLatitude = (BigDecimal)adresseStart.getRawVal(ADRESSE.latitude); 
//					BigDecimal bdStartLongitude = (BigDecimal)adresseStart.getRawVal(ADRESSE.longitude); 
//					
//					BigDecimal bdZielLatitude = (BigDecimal)adresseZiel.getRawVal(ADRESSE.latitude); 
//					BigDecimal bdZielLongitude = (BigDecimal)adresseZiel.getRawVal(ADRESSE.longitude); 
//					
//					if (O.isNoOneNull(bdStartLatitude,bdStartLongitude,bdZielLatitude,bdZielLongitude)) {
//						BigDecimal 								km = (BigDecimal)rFuhre.getRawVal(VPOS_TPA_FUHRE.routing_distance_km);
//						HashMap<ENUM_ROUTING_TYP, BigDecimal> 	result = null;
//						MyE2_MessageVector mv = bibMSG.getNewMV();
//						if (km==null || km.longValue()<5 || cbKmBerechnungImmerAusfuehren.isSelected()) {
//							PdServiceGeoRoutingEntfernungUndKostenBean diffs = new PdServiceGeoRoutingEntfernungUndKostenBean();
//							result = diffs.getDistanceTimeAmdCosts(bdStartLatitude,bdStartLongitude,bdZielLatitude,bdZielLongitude, null, null, mv);
//							km = result.get(ENUM_ROUTING_TYP.ENTFERNUNG);
//						}
//
//						if (mv.get_bHasAlarms()) {
//							counterFehler++;
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.UNKNOWN_ERROR.db_val(), mvSaveCo2);
//							saveProtokollRecord(recCo2);
//						} else {
//							
//							bdSummeKm = bdSummeKm.add(km);
//							counterBerechnet++;
//							BigDecimal bdVerbrauchDerFahrt = new BigDecimal(0);
//							if (rFuhre.getTypLKW()==TYPLKW.LKW_MIT_ANHAENGER) {
//								bdVerbrauchDerFahrt = km.multiply(bdVerbrauchLKWMitAnhaenger, MathContext.DECIMAL128).divide(new BigDecimal(100),  MathContext.DECIMAL128);
//								bdVerbrauchDieselGesamt = bdVerbrauchDieselGesamt.add(bdVerbrauchDerFahrt);
//							} else {
//								bdVerbrauchDerFahrt = km.multiply(bdVerbrauchLKWOhneAnhaenger, MathContext.DECIMAL128).divide(new BigDecimal(100),  MathContext.DECIMAL128);
//								bdVerbrauchDieselGesamt = bdVerbrauchDieselGesamt.add(bdVerbrauchDerFahrt);
//							}
//							
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.km_distanz, km, mvSaveCo2);
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.spritverbrauch_fuhre_l, bdVerbrauchDerFahrt, mvSaveCo2);
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.ladung_fuhre_in_tonnen, rFuhre.getAbladeMengeInTonnen(), mvSaveCo2);
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.co2_erzeugung_fuhre_kg, bdVerbrauchDerFahrt
//																						.multiply(bdCO2ProLiter,MathContext.DECIMAL128)
//																						.setScale(2, RoundingMode.HALF_UP), mvSaveCo2);
//							
//							recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.OK.db_val(), mvSaveCo2);
//							
//							
//							bdSummeTonnageTransport = bdSummeTonnageTransport.add(O.NN(rFuhre.getAbladeMengeInTonnen(), BigDecimal.ZERO));
//							
//							if (fuhre.getRawVal(VPOS_TPA_FUHRE.routing_distance_km)==null || cbUeberschreibeBestehendeKmAngaben.isSelected()) {
//								mv.clear();
//								fuhre._setNewVal(VPOS_TPA_FUHRE.routing_distance_km,km , mv);
//								if (mv.get_bHasAlarms()) {
//									counterBerechnet--;
//									counterFehler++;
//								} else {
//									fuhre._SAVE(true, true, mv);
//									if (mv.get_bHasAlarms()) {
//										counterBerechnet--;
//										counterFehler++;
//									} else {
//										counterGeschriebene++;
//									}
//								}
//							} 
//							saveProtokollRecord(recCo2);
//						}
//					} else {
//						counterNoGeoDaten++;
//						recCo2._setNewVal(FUHREN_CO2_PROFIL.en_co2_result_status, EN_CO2_RESULT_STATUS.NO_COORDINATES.db_val(), mvSaveCo2);
//						saveProtokollRecord(recCo2);
//						
//						VEK<Long> adresseOhneGeoCode = rFuhre.getAdressenOhneGeocodierung();
//						if (adresseOhneGeoCode!=null) {
//							for (Long id: adresseOhneGeoCode) {
//								vAdressenOhneGeocodierung._addIfNotIn(id);
//							}
//						}
//						
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				counterFehler++;
//			}
//			
//		}
//
//		private void saveProtokollRecord(Rec22 protokollSet) {
//			MyE2_MessageVector mv = bibMSG.getNewMV();
//			try {
//				protokollSet._SAVE(true, mv);
//				if (mv.isOK()) {
//					countProtokollSaves++;
//				}
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//			
//		}
		
		
		
		
		
		@Override
		public void doSomethingAfterLoop() throws myException {
			if (this.getMvSammler().isOK()) {
				
				popupResult = new ResultWindow();
				
			} else {
				new E2_WindowPane_4_PopupMessages()._showMessages(this.getMvSammler())._showPopup();
			}
		}

		@Override
		public void doFinaleTasks() {
			
		}

		@Override
		public void refreshFortschrittsAnzeige(E2_Grid g, int iCount, int iGesamt) {
			
			
			RB_gld titel =  new RB_gld()._col_back_d()._center_mid()._ins(3,3,3,3);
			RB_gld werte =  new RB_gld()._center_mid()._ins(3);
			
			MyDecimalFormat  df = new MyDecimalFormat();
			
			g._clear()	._setSize(80,80,80,80,80,80,80,120,120,120,120)._bo_dd()
			
						._a(new RB_lab(S.ms("Anzahl"))._fsa(-2)._lw(), 					titel)
						._a(new RB_lab(S.ms("Zahl Proto."))._fsa(-2)._lw(), 			titel)
						._a(new RB_lab(S.ms("Ok"))._fsa(-2)._lw(), 						titel)
						._a(new RB_lab(S.ms("keine Geo-Daten"))._fsa(-2)._lw(), 		titel)
						._a(new RB_lab(S.ms("unbewertet wg. Fuhrenort"))._fsa(-2)._lw(),titel)
						._a(new RB_lab(S.ms("neu geschrieben"))._fsa(-2)._lw(), 		titel)
						
						._a(new RB_lab(S.ms("Fehler"))._fsa(-2)._lw(), 					titel)
						._a(new RB_lab(S.ms("Tonnage gesamt"))._fsa(-2)._lw(), 			titel)
						._a(new RB_lab(S.ms("Km-Gesamt"))._fsa(-2)._lw(), 				titel)
						._a(new RB_lab(S.ms("Diesel gesamt (l)"))._fsa(-2)._lw(), 		titel)
						._a(new RB_lab(S.ms("CO2 gesamt (kg)"))._fsa(-2)._lw(), 		titel)
						
						._a(""+(counterBerechnet+counterNoGeoDaten+counterFehler+countSkipedWgFuhrenOrt), 	werte)
						._a(""+(countProtokollSaves), 														werte)
						._a(""+counterBerechnet, 															werte)
						._a(""+counterNoGeoDaten, 															werte)
						._a(""+countSkipedWgFuhrenOrt,	 													werte)
						._a(""+counterGeschriebene, 														werte)
						
						._a(""+counterFehler, 																werte)
						._a(df.format(bdSummeTonnageTransport), 											werte)
						._a(df.format(bdSummeKm), 															werte)
						._a(df.format(bdVerbrauchDieselGesamt.setScale(1, RoundingMode.HALF_UP)),			werte)
						._a(df.format(bdVerbrauchDieselGesamt.multiply(bdCO2ProLiter,MathContext.DECIMAL128).setScale(0, RoundingMode.HALF_UP)),			werte)
						;

		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection#setProgressWindowPaneLookAndFeel(panter.gmbh.Echo2.components.MyE2_WindowPane)
		 */
		@Override
		public void setProgressWindowPaneLookAndFeel(MyE2_WindowPane pane) {
		}

		
		
		
		private class ResultWindow extends E2_BasicModuleContainer {
			
			public ResultWindow() throws myException {
				super();
				
				MyDecimalFormat  df = new MyDecimalFormat().withPattern("#,##0");
				
				E2_Grid  ergebnis = new E2_Grid()._setSize(500,100,100);
				
				ergebnis._a(new RB_lab()._tr("Ergebnisse des Bewertungslaufs: "), 	new RB_gld()._span(3)._col_back_d()._ins(5, 5, 5, 5));
				
				ergebnis._a(new RB_lab()._tr("Anzahl bearbeitete Fuhren"),			new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+df.format(counterBerechnet
												+counterNoGeoDaten
												+counterFehler
												+countSkipedWgFuhrenOrt))._b(), 	new RB_gld()._ins(5,5,5,5)._right_mid())
						._a("");
				ergebnis._a(new RB_lab()._tr("Anzahl Protokolle"),				new RB_gld()._ins(5,5,5,5)._left_mid())
											._a(new RB_lab()._t(""+df.format(countProtokollSaves))._b(), 	new RB_gld()._ins(5,5,5,5)._right_mid())
											._a("");
				
				ergebnis._a(new RB_lab()._tr("Erfolgreich geprüft"),				new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+df.format(counterBerechnet))._b(), 	new RB_gld()._ins(5,5,5,5)._right_mid()._col_back_green())
						._a("");
		
				ergebnis._a(new RB_lab()._tr("Übersprungen (keine Geo-Koordinaten)"),new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+df.format(counterNoGeoDaten))._b(), 	new RB_gld()._ins(5,5,5,5)._right_mid()._col_back_help())
						._a(new BtJumpToAdresseOhneGeoCode(), new RB_gld()._ins(4,2,4,2));

				ergebnis._a(new RB_lab()._tr("Übersprungen (Inhomogene Fuhrenorte, d.h."
											+ " keine eindeutige Fahrt)"),			new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+df.format(countSkipedWgFuhrenOrt))._b(), new RB_gld()._ins(5,5,5,5)._right_mid()._col_back_help())
						._a(new BtShowInhomogeneFuhren(vFuhrenInhomogeneOrt), new RB_gld()._ins(4,2,4,2));

				
				ergebnis._a(new RB_lab()._tr("Aktualisierte KM-Stände in Fuhren"),	new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+df.format(counterGeschriebene))._b(), new RB_gld()._ins(5,5,5,5)._right_mid())
						._a("");

				
				RB_gld ldForErrors = new RB_gld()._ins(5,5,5,5)._right_mid(); 
				if (counterFehler>0) {
					ldForErrors._col_back_alarm();
				}
					
				ergebnis._a(new RB_lab()._tr("Fehler"),								new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(""+(counterFehler))._b(), 				ldForErrors)
						._a("");

				ergebnis._addSeparator();
				
				ergebnis._a(new RB_lab()._tr("Gesamt-Tonnen"),						new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(df.format(bdSummeTonnageTransport))._b()._fsa(2),	new RB_gld()._ins(5,5,5,5)._right_mid())
						._a("");
				
				ergebnis._a(new RB_lab()._tr("Gesamt-KM"),							new RB_gld()._ins(5,5,5,5)._left_mid())
						._a(new RB_lab()._t(df.format(bdSummeKm))._b()._fsa(2),	new RB_gld()._ins(5,5,5,5)._right_mid())
						._a("");

				
				ergebnis._a(new RB_lab()._tr("Gesamter Verbrauch in l Diesel"),							new RB_gld()._ins(5,5,5,5)._left_mid())
					._a(new RB_lab()._t(df.format(bdVerbrauchDieselGesamt.setScale(1, RoundingMode.HALF_UP)))._b()._fsa(2),	new RB_gld()._ins(5,5,5,5)._right_mid())
					._a("");

				
				ergebnis._a(new RB_lab()._tr("Erzeugtes CO2 in kg"),							new RB_gld()._ins(5,5,5,5)._left_mid())
					._a(new RB_lab()._t(df.format(bdVerbrauchDieselGesamt
													.multiply(bdCO2ProLiter,MathContext.DECIMAL128).setScale(0, RoundingMode.HALF_UP)))._b()._fsa(2),	new RB_gld()._ins(5,5,5,5)._right_mid())
					._a("");


				E2_Grid buttonListe = new E2_Grid()._setSize(100,100,100)
													._a(new BtPrintResultsPdf(), 	new RB_gld()._ins(0,0,5,0))
													._a(new BtPrintResultsExcel(), 	new RB_gld()._ins(0,0,5,0))
													._a(new BtCloseAll(), 			new RB_gld()._ins(0,0,5,0));
				ergebnis._a(buttonListe, new RB_gld()._ins(5,5,5,5)._left_mid()._span(3));
				
				
				this.add(ergebnis, E2_INSETS.I(5));

				try {
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(720), new Extent(470),S.ms("Ergebnisse der KM-Ermittlung"));
				} catch (myException e) {
					e.printStackTrace();
					new E2_WindowPane_4_PopupMessages()._showMessages(bibMSG.getNewMV()._addAlarm(e.getOriginalMessage()))._showPopup();
				}
			}
			
		}
		
		//---------------------------------------------------------------------
		private class BtJumpToAdresseOhneGeoCode extends E2_Button  {
			public BtJumpToAdresseOhneGeoCode() throws myException {
				super();
				this	._t(S.ms("Zeige Adressen"))
						._setShapeStandardTextButton()
						._aaa(new ownActionJumpToFreiePos())
						._aaa(()->{
							try {
								popupResult.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							})
						._aaa(()->{
							try {
								getStartPopup().CLOSE_AND_DESTROY_POPUPWINDOW(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							})
						;
				
				if (counterNoGeoDaten==0) {
					this.set_bEnabled_For_Edit(false);
				}
			}

			private class ownActionJumpToFreiePos extends XX_ActionAgentJumpToTargetList	{
				public ownActionJumpToFreiePos() throws myException 	{
					super(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST, "Adressen");
				}

				@Override
				public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException		{
					VEK<String>  vIdAdresse = new VEK<>();
					for (Long id: vAdressenOhneGeocodierung) {
						vIdAdresse._a(id.toString());
					}
					return vIdAdresse;
				}

				@Override
				public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException		{
					MyE2_MessageVector  oMV = new MyE2_MessageVector();

					if (vTargetList.size()==0)	{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
					}
					return oMV;
				}
			}
		}
		//---------------------------------------------------------------------

		
		
		
		
		//-----------------------------------------------------------------------------
		private class BtShowInhomogeneFuhren extends E2_Button {

			private VEK<Long> ids=new VEK<>();

			public BtShowInhomogeneFuhren(VEK<Long> p_ids) throws myException {
				super();
				this.ids._a(p_ids);
				this._setShapeStandardTextButton();
				this._t("Zeige Fuhren")._aaa(new Action())
										._aaa(()->{
													try {
														popupResult.CLOSE_AND_DESTROY_POPUPWINDOW(true);
													} catch (Exception e) {
														e.printStackTrace();
													}
												})
										._aaa(()->{
													try {
														getStartPopup().CLOSE_AND_DESTROY_POPUPWINDOW(true);
													} catch (Exception e) {
														e.printStackTrace();
													}
												})
											;
				
				
				this._ttt(S.ms("Die Fuhren anzeigen, die in wegen multipler Ladeorte nicht als eindeutige Fahrtstrecke erkennbar sind"));
				
				if (ids.size()==0) {
					this.set_bEnabled_For_Edit(false);
				}
				
			}
			
			private class Action extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					if (naviList.getStatusSaver()==null) {
						naviList.saveStatus(bibMSG.MV());
						
						naviList.get_vectorSegmentation().clear();
						
						for (Long l: ids) {
							naviList.get_vectorSegmentation().add(l.toString());
						}
						
						naviList._RebuildListWithActualIds();
					}
				}
			}
		}
		//-----------------------------------------------------------------------------

		
		
		
		// ------------------------------------------------------
		private class BtCloseAll extends E2_Button {

			public BtCloseAll() {
				super();
				this	._t(S.ms("Alle Fenster schliessen"))
				._setShapeStandardTextButton()
				._aaa(()->{
					try {
						popupResult.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					})
				._aaa(()->{
					try {
						getStartPopup().CLOSE_AND_DESTROY_POPUPWINDOW(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					})
				;
			}
		}
		// ------------------------------------------------------
		

		
		
		
		private class BtPrintResultsPdf extends E2_Button {

			public BtPrintResultsPdf() {
				super();
				this	._t(S.ms("Ergebnisse als Pdf"))
				._setShapeStandardTextButton()
				._aaa(()->{
						JasperResults jasper = new JasperResults(new JasperFileDef_PDF());
						jasper.executeAndDownload();
					})
				;
			}
		}
		
		private class BtPrintResultsExcel extends E2_Button {

			public BtPrintResultsExcel() {
				super();
				this	._t(S.ms("Ergebnisse als Excel"))
				._setShapeStandardTextButton()
				._aaa(()->{
					JasperResults jasper = new JasperResults(new JasperFileDef_XLS());
					jasper.executeAndDownload();
					})
				;
			}
		}
	
		
		private class JasperResults extends E2_JasperHASH_STD {
			
			/**
			 * @author martin
			 * @date 12.07.2019
			 *
			 * @param nameOfReport
			 * @param jasperFileDef
			 * @throws myException
			 */
			public JasperResults(JasperFileDef jasperFileDef) throws myException {
				super("co2_report_fuhren.jasper", jasperFileDef);
				this.put(paramUUID4Report, uuidString);
			}
			
			public void executeAndDownload() throws myException {
				this.Build_tempFile(false);
				FileWithSendName oFile = this.get_oTempFileWithSendeName();
				if (oFile != null){
					oFile.Download_File();
				}
			}
		}

		
	}
	
	
	
	
	/**
	 * 

 
 select 
   1 as A
   ,P.ID_VPOS_TPA_FUHRE
   ,P.EIGENE_FUHRE
   ,P.EN_CO2_RESULT_STATUS
   ,P.LKW_MIT_ANHAENGER AS ANHAENGER_VORHANDEN
   ,F.TRANSPORTMITTEL
   ,F.L_NAME1
   ,F.L_NAME2
   ,F.L_NAME3
   ,F.L_STRASSE
   ,F.L_HAUSNUMMER
   ,F.L_LAENDERCODE
   ,F.L_PLZ
   ,F.L_ORT
   ,F.A_NAME1
   ,F.A_NAME2
   ,F.A_NAME3
   ,F.A_STRASSE
   ,F.A_LAENDERCODE
   ,F.A_PLZ
   ,F.A_ORT
   ,P.KM_DISTANZ
   ,P.LADUNG_FUHRE_IN_TONNEN
   ,P.SPRITVERBRAUCH_FUHRE_L
   ,P.CO2_ERZEUGUNG_FUHRE_KG
   , A.ANR1
   , A.ARTBEZ1
   , START_JUR.NAME1 AS START_JUR_NAME1
   , START_JUR.NAME2 AS START_JUR_NAME2
   , START_LAND.LAENDERCODE AS START_LAND
   , START_JUR.PLZ   AS START_JUR_PLZ
   , START_JUR.ORT   AS START_JUR_ORT

   , ZIEL_JUR.NAME1 AS ZIEL_JUR_NAME1
   , ZIEL_JUR.NAME2 AS ZIEL_JUR_NAME2
   , ZIEL_LAND.LAENDERCODE AS ZIEL_LAND
   , ZIEL_JUR.PLZ   AS ZIEL_JUR_PLZ
   , ZIEL_JUR.ORT   AS ZIEL_JUR_ORT
   ,P.UUID_CODE_OF_CALL
   ,P.LITER_PRO_100_LKW
   ,P.LITER_PRO_100_LKW_ANH
   ,P.CO2_PRO_LITER_DIESEL
      
   FROM JT_FUHREN_CO2_PROFIL P
inner join JT_VPOS_TPA_FUHRE F ON (F.ID_VPOS_TPA_FUHRE=P.ID_VPOS_TPA_FUHRE)
left outer join JT_ARTIKEL A on  (F.ID_ARTIKEL=A.ID_ARTIKEL)

left outer join JT_ADRESSE START_JUR ON (F.ID_ADRESSE_START=START_JUR.ID_ADRESSE)
left outer join JD_LAND    START_LAND ON (START_JUR.ID_LAND=START_LAND.ID_LAND)

left outer join JT_ADRESSE ZIEL_JUR ON (F.ID_ADRESSE_ZIEL=ZIEL_JUR.ID_ADRESSE)
left outer join JD_LAND    ZIEL_LAND ON (ZIEL_JUR.ID_LAND=ZIEL_LAND.ID_LAND)

WHERE P.UUID_CODE_OF_CALL = 'afab6f2d-ada8-4750-90dc-b08a70d00934'
 
	 * 
	 */
	
	
	
}
