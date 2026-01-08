/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TestHandelsdefAufHistorie;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings.ActionHandlerWhenFound;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings.EN_STATUSBERICHT;
import panter.gmbh.BasicInterfaces.Service.PdServiceMessagePopup;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TESTTABELLE_HANDELDEF;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Station;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufungen;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 */
public class BtStartVergleichHistorie extends E2_Button {

	private E2_Grid  				anzeigeGrid = new E2_Grid();

	private RB_date_selektor        dateSelectorStart = new RB_date_selektor();
	private RB_TextField  			tfIDFuhre = new RB_TextField(80)._bord_dd();
	
	private boolean   				bStartBreak = true;
	
	private RB_cb  					cbProtokollSchreiben = new RB_cb();
	private RB_cb  					cbFehlendeHandelsdefsErfassen = new RB_cb();
	
	private VEK<String>    			vBereitsGeschrieben = new VEK<String>();
	
	private boolean 				saveError = false;
	
	public BtStartVergleichHistorie() throws myException {
		super();
		
		this._tr("Starte Handelsdef.-Vergleich mit bestehenden Fuhren")._b()._bord_ddd()._backDDark();
		
		this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
		this.setBreak4PopupController(new ownPopup());
		
		this._aaa(new ownAction());
		
	}

	
	
	private class ownAction extends XX_ActionAgent {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		MyE2_MessageVector mvdb = new MyE2_MessageVector();

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			mv.clear();
			mvdb.clear();

			SEL sel = new SEL(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).FROM(_TAB.vpos_tpa_fuhre).WHERE(new vgl(VPOS_TPA_FUHRE.status_buchung,""+myCONST.STATUS_FUHRE__GANZGEBUCHT));
			
			SEL selVorhanden = new SEL(TESTTABELLE_HANDELDEF.id_vpos_tpa_fuhre).FROM(_TAB.testtabelle_handeldef);
			
			MyDate date = dateSelectorStart.get_oDateFromTextField();
			
			MyLong idFuhre = new MyLong(tfIDFuhre.getText());  
			
			if (idFuhre.isOK()) {
				sel.AND(new vgl(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, idFuhre.get_cUF_LongString()));
			} else if (date !=null && date.isOK()) {
				sel.AND(new vgl(VPOS_TPA_FUHRE.datum_abholung,COMP.GE, date.get_cDateStandardFormat()));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte Datum oder spezielle Fuhre füllen !"));
				BtStartVergleichHistorie.this.bStartBreak=true;
				return;
			}
			
			String[][] fuhren = bibDB.EinzelAbfrageInArray(sel.s());
			VEK<String> vFuhren = new VEK<String>()._a(bibVECTOR.get_VectorFromArray(fuhren));
			
			String[][] fuhrenBereitsGecheckt = bibDB.EinzelAbfrageInArray(selVorhanden.s());
			VEK<String> vFuhrenBereitsGecheckt = new VEK<String>()._a(bibVECTOR.get_VectorFromArray(fuhrenBereitsGecheckt));
//			vFuhren.removeAll(vFuhrenBereitsGecheckt);
			
			BtStartVergleichHistorie.this.bStartBreak=true;   //beim naechsten start wieder fragen 

			
			new E2_ServerPushMessageContainer_STD(new Extent(400), new Extent(250), new MyE2_String("Handelsdefinitionen prüfen .."),false,true,2000,anzeigeGrid,E2_INSETS.I(10,10,10,10)) {
				
				@Override
				public void Run_Loop() throws myException {
					this.get_oGridBaseForMessages()._clear();
					this.get_oGridBaseForMessages()._add(anzeigeGrid, new RB_gld()._ins(4));
					
					vBereitsGeschrieben.clear();
					
					int i=0;
					HashMap<EN_STATUSBERICHT, VEK<Long>> statistik = EN_STATUSBERICHT.OK.generateSchubladen();

					RB_gld lg = new RB_gld()._center_top()._ins(2, 2, 2, 2);
					
					for (String f: vFuhren) {
						
						i++;
						new PdServiceFindHandelsdefSettings()._findSettingsFuhre(new RecFuhre4Testlauf(f), new OwnActionHandlerWhenFound(vFuhrenBereitsGecheckt), statistik, cbFehlendeHandelsdefsErfassen.isSelected(), true, mv);

						if (i%20==0) {
							anzeigeGrid._clear();
							anzeigeGrid._setSize(100,30,100,100,100,100);
							anzeigeGrid._a(new RB_lab("Nummer"),lg)
										._a(new RB_lab(" von "),lg)
										._a(new RB_lab(" Gesamt"),lg)
										._a(new RB_lab("...davon gefunden"),lg)
										._a(new RB_lab("Fehler allg."),lg)
										._a(new RB_lab("Fehler Datenbank"),lg);
							
							anzeigeGrid.addSeparator();
							anzeigeGrid._a(new RB_lab()._tr(""+i),lg)	
										._a(new RB_lab(" - "),lg)
										._a(new RB_lab(""+vFuhren.size()),lg)
										._a(new RB_lab(""+EN_STATUSBERICHT.OK.getCount(statistik,EN_STATUSBERICHT.OK)),lg)
										._a(new RB_lab(""+mv.size()),lg)
										._a(new RB_lab(""+mvdb.size()),lg)
																	;
						}
						
						if (mvdb.size()>100) {
							//dann systemischer fehler und abbruch
							this.set_bIsInterupted(true);
						}

						
						if (this.get_bIsInterupted() || saveError) {
							break;
						}
					}
					
					//abschluss
					anzeigeGrid._clear();
					anzeigeGrid._setSize(100,20,100,100,100,100);
					anzeigeGrid._a(new RB_lab("Nummer"),lg)
								._a(new RB_lab(" von "),lg)
								._a(new RB_lab(" Gesamt"),lg)
								._a(new RB_lab("...davon gefunden"),lg)
								._a(new RB_lab("Fehler allg."),lg)
								._a(new RB_lab("Fehler Datenbank"),lg);
					anzeigeGrid.addSeparator();
					anzeigeGrid._a(new RB_lab()._tr(""+i),lg)	._a(new RB_lab(" - "),lg)
								._a(new RB_lab(""+vFuhren.size()),lg)
								._a(new RB_lab(""+EN_STATUSBERICHT.OK.getCount(statistik,EN_STATUSBERICHT.OK)),lg)
								._a(new RB_lab(""+mv.size()),lg)
								._a(new RB_lab(""+mvdb.size()),lg)
															;
					
					//hier die fehlermeldungen anzeigen (falls welche da sind)
					if (mvdb.get_bHasAlarms() || mv.get_bHasAlarms()) {
						new PdServiceMessagePopup().assignMessages(mvdb).assignMessages(mv).showPopup();
					}
					
				}


				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
				}
			};
			
			//bibMSG.add_MESSAGE(mv);
			
		}
		
		private class OwnActionHandlerWhenFound implements ActionHandlerWhenFound {
			
			private Vector<String>  vIDsBereitsInProtokoll = new Vector<String>(); 
			
			
			/**
			 * @param v_IDsBereitsInPruefliste
			 */
			public OwnActionHandlerWhenFound(Vector<String> v_IDsBereitsInPruefliste) {
				super();
				this.vIDsBereitsInProtokoll.addAll(v_IDsBereitsInPruefliste);
			}


			@Override
			public void applyAction(HD_WarenBewegungEinstufungen einstufungen, HD_Station stationEK, HD_Station stationVK, EN_STATUSBERICHT fehlerDef, Long IDRecord)	throws myException {
				
				MyE2_MessageVector mvLocal = new MyE2_MessageVector(); 
				
				if (fehlerDef.isErfolgreich() && einstufungen.size()==1) {
					if (cbProtokollSchreiben.isSelected()) {
						if (!this.vIDsBereitsInProtokoll.contains(IDRecord.toString())) {
							HD_WarenBewegungEinstufung e = einstufungen.elementAt(0);
							
							String id_handelsdefFound = null;
							HD_WarenBewegungEinstufung einstufungSingle  = O.getSingleMember(einstufungen);
							if (einstufungSingle!=null) {
								if (einstufungSingle.get_recHANDELSDEF()!=null) {
									id_handelsdefFound=einstufungSingle.get_recHANDELSDEF().ufs(HANDELSDEF.id_handelsdef);
								}
							}
							
							Rec21 r = new Rec21(_TAB.testtabelle_handeldef);
							Rec21 rFuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(IDRecord);
		
							r._setNewVal(TESTTABELLE_HANDELDEF.id_handelsdef, id_handelsdefFound, mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.suchergebnis, fehlerDef.name(), mvLocal);
							
							r._setNewVal(TESTTABELLE_HANDELDEF.id_vpos_tpa_fuhre,IDRecord , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.id_tax_start_hd,  	e.get_cID_TAX_IN_Korrekt_UF() , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.id_tax_ziel_hd,  	e.get_cID_TAX_OUT_Korrekt_UF() , mvLocal);
							
							if (S.isAllFull(e.get_cID_TAX_IN_Korrekt_UF(),e.get_cID_TAX_OUT_Korrekt_UF()) ) {
								Rec21 rTaxStart = new Rec21(_TAB.tax)._fill_id(e.get_cID_TAX_IN_Korrekt_UF());
								Rec21 rTaxZiel  = new Rec21(_TAB.tax)._fill_id(e.get_cID_TAX_OUT_Korrekt_UF());
								
								r._setNewVal(TESTTABELLE_HANDELDEF.steuersatz_start_hd, 	rTaxStart.getRawVal(TAX.steuersatz) , mvLocal);
								r._setNewVal(TESTTABELLE_HANDELDEF.steuersatz_ziel_hd,  	rTaxZiel.getRawVal(TAX.steuersatz) , mvLocal);
								r._setNewVal(TESTTABELLE_HANDELDEF.steuervermerk_start_hd, 	rTaxStart.getRawVal(TAX.steuervermerk) , mvLocal);
								r._setNewVal(TESTTABELLE_HANDELDEF.steuervermerk_ziel_hd,  	rTaxZiel.getRawVal(TAX.steuervermerk) , mvLocal);
							}
							
							
							r._setNewVal(TESTTABELLE_HANDELDEF.steuersatz_start_alt, 	rFuhre.getRawVal(VPOS_TPA_FUHRE.steuersatz_ek) , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.steuersatz_ziel_alt,  	rFuhre.getRawVal(VPOS_TPA_FUHRE.steuersatz_vk) , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.steuervermerk_start_alt, rFuhre.getRawVal(VPOS_TPA_FUHRE.eu_steuer_vermerk_ek) , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.steuervermerk_ziel_alt,  rFuhre.getRawVal(VPOS_TPA_FUHRE.eu_steuer_vermerk_vk) , mvLocal);
							
							r._setNewVal(TESTTABELLE_HANDELDEF.laendercode_start,  		rFuhre.getRawVal(VPOS_TPA_FUHRE.l_laendercode) , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.laendercode_ziel,  		rFuhre.getRawVal(VPOS_TPA_FUHRE.a_laendercode) , mvLocal);
							
							r._setNewVal(TESTTABELLE_HANDELDEF.epreis_quelle,  			rFuhre.getRawVal(VPOS_TPA_FUHRE.einzelpreis_ek) , mvLocal);
							r._setNewVal(TESTTABELLE_HANDELDEF.epreis_ziel,  			rFuhre.getRawVal(VPOS_TPA_FUHRE.einzelpreis_vk) , mvLocal);
							
							r._SAVE(true, mvLocal);
						}
					}
				} else if (fehlerDef.isErfolgreich() && einstufungen.size()!=1){
					throw new myException(this, "Success can only be with a singular typ");
				} else {
					
					if (fehlerDef==EN_STATUSBERICHT.KEINE_HANDELSDEF_GEFUNDEN && stationEK!=null && stationVK!=null) {
						if (cbProtokollSchreiben.isSelected() || cbFehlendeHandelsdefsErfassen.isSelected()) {
							
							HD_Station  stationStart = stationEK;
							Rec21  rLandJurStart = new Rec21(_TAB.land)._fill_id(stationStart.get_IdLandJuristisch());
							Rec21  rLandGeoStart = new Rec21(_TAB.land)._fill_id(stationStart.get_IdLandGeografisch());

							HD_Station  stationZiel=stationVK;
							Rec21  rLandJurZiel = new Rec21(_TAB.land)._fill_id(stationZiel.get_IdLandJuristisch());
							Rec21  rLandGeoZiel = new Rec21(_TAB.land)._fill_id(stationZiel.get_IdLandGeografisch());
						
							Rec21_adresse  ra1 = new Rec21_adresse()._fill_id(stationStart.get_recAdresse_JUR().get_ID_ADRESSE_cUF());
							Rec21_adresse  ra2 = new Rec21_adresse()._fill_id(stationZiel.get_recAdresse_JUR().get_ID_ADRESSE_cUF());

							if (cbProtokollSchreiben.isSelected()) {
								if (!this.vIDsBereitsInProtokoll.contains(IDRecord.toString())) {
									Rec21 r = new Rec21(_TAB.testtabelle_handeldef);
									r._setNewVal(TESTTABELLE_HANDELDEF.suchergebnis, fehlerDef.name(), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.id_vpos_tpa_fuhre,IDRecord , mvLocal);
									//dann das noetige profil in die tabelle schreiben, um das clearing zu vereinfachen
									
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_land_jur, rLandJurStart.getRawVal(LAND.laendercode), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_land_geo, rLandGeoStart.getRawVal(LAND.laendercode), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_id_land_quelle_geo, rLandJurStart.getRawVal(LAND.id_land), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_id_land_quelle_jur, rLandGeoStart.getRawVal(LAND.id_land), mvLocal);
									
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_produkt,    stationStart.get_bSorteProdukt()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_rc, 	    stationStart.get_bSorteRC()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_dienstlst,  stationStart.get_bSorteDienstleistung()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_eow,        stationStart.get_bSorteEoW()?"Y":"N", mvLocal);
			
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_unternehmen, ra1.getFirmeninfo().getRawVal(FIRMENINFO.firma), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_start_ist_mandant, ra1.isAdressOfMandant()?"Y":"N", mvLocal);
			
									r._setNewVal(TESTTABELLE_HANDELDEF.om_tpa_verantwort,    stationStart.get_cTP_Verantwortung(), mvLocal);
									
								
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_land_jur,   rLandJurZiel.getRawVal(LAND.laendercode), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_land_geo,   rLandGeoZiel.getRawVal(LAND.laendercode), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_id_land_ziel_geo,   rLandJurZiel.getRawVal(LAND.id_land), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_id_land_ziel_jur,   rLandGeoZiel.getRawVal(LAND.id_land), mvLocal);
			
									
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_produkt,    	stationZiel.get_bSorteProdukt()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_rc, 	    	stationZiel.get_bSorteRC()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_dienstlst, 	 stationZiel.get_bSorteDienstleistung()?"Y":"N", mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_eow,       	 stationZiel.get_bSorteEoW()?"Y":"N", mvLocal);
			
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_unternehmen, ra2.getFirmeninfo().getRawVal(FIRMENINFO.firma), mvLocal);
									r._setNewVal(TESTTABELLE_HANDELDEF.om_ziel_ist_mandant, ra2.isAdressOfMandant()?"Y":"N", mvLocal);
									
									r._SAVE(true, mvLocal);
								}
							}
							if (cbFehlendeHandelsdefsErfassen.isSelected()) {
								MyE2_MessageVector mvSave = bibMSG.getNewMV();
								Rec21 r = new Rec21(_TAB.handelsdef);
								r._setNewVal(HANDELSDEF.id_land_quelle_jur, 		rLandJurStart.getRawVal(LAND.id_land), 				mvSave);
								r._setNewVal(HANDELSDEF.id_land_quelle_geo, 		rLandGeoStart.getRawVal(LAND.id_land), 				mvSave);
								r._setNewVal(HANDELSDEF.sorte_produkt_quelle , 		stationStart.get_bSorteProdukt()?					"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_rc_quelle, 	    	stationStart.get_bSorteRC()?						"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_dienstleist_quelle,  	stationStart.get_bSorteDienstleistung()?			"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_eow_quelle,        	stationStart.get_bSorteEoW()?						"1":"-1", mvSave);
		
								r._setNewVal(HANDELSDEF.ust_teilnehmer_quelle, 		ra1.getFirmeninfo().is_yes_db_val(FIRMENINFO.firma)?"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.quelle_ist_mandant, 		ra1.isAdressOfMandant()?"Y":"N", 					mvSave);
		
								
								r._setNewVal(HANDELSDEF.id_land_ziel_jur,   		rLandJurZiel.getRawVal(LAND.id_land), 				mvSave);
								r._setNewVal(HANDELSDEF.id_land_ziel_geo,   		rLandGeoZiel.getRawVal(LAND.id_land), 				mvSave);
								r._setNewVal(HANDELSDEF.sorte_produkt_ziel,    		stationZiel.get_bSorteProdukt()?					"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_rc_ziel, 	    		stationZiel.get_bSorteRC()?							"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_dienstleist_ziel, 	stationZiel.get_bSorteDienstleistung()?				"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.sorte_eow_ziel,       	 	stationZiel.get_bSorteEoW()?						"1":"-1", mvSave);
		
								r._setNewVal(HANDELSDEF.ust_teilnehmer_ziel, 		ra2.getFirmeninfo().is_yes_db_val(FIRMENINFO.firma)?"1":"-1", mvSave);
								r._setNewVal(HANDELSDEF.ziel_ist_mandant, 			ra2.isAdressOfMandant()?"Y":"N", 					mvSave);
								
								r._setNewVal(HANDELSDEF.tp_verantwortung,    		stationStart.get_cTP_Verantwortung(), 				mvSave);
								r._setNewVal(HANDELSDEF.aktiv,    					"N", 												mvSave);
								
								r._setNewVal(HANDELSDEF.versionszaehler, "0", mvSave);
								r._setNewVal(HANDELSDEF.intrastat_meld_in, "N", mvSave);
								r._setNewVal(HANDELSDEF.intrastat_meld_out, "N", mvSave);
								r._setNewVal(HANDELSDEF.transit_ek, "N", mvSave);
								r._setNewVal(HANDELSDEF.transit_vk, "N", mvSave);
								
								
								r._SAVE(true, mvSave);
								
								if (mvSave.get_bHasAlarms()) {
									//saveError=true;   //dann endet die schleife
								}
								mvLocal._add(mvSave);
							}
						}
					}
				}
				
				if (mvLocal.get_bHasAlarms()) {
					mvdb.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Datenfehler Fuhre-ID: "+S.NN(IDRecord.toString()))));
					mvdb.add_MESSAGE(mvLocal);
				}
			}
		}
	}

	
	
	
	private class ownPopup extends E2_Break4PopupController {

		public ownPopup() {
			super();
			
			this._registerBreak(new E2_Break4Popup() {
				
				@Override
				public E2_BasicModuleContainer generatePopUpContainer() throws myException {
					E2_BasicModuleContainer pop = new E2_BasicModuleContainer();
					
					E2_Grid g = new E2_Grid()._setSize(200,300)
								._a(new RB_lab()._tr("Ab welchem Fuhrendatum soll der Vergleich starten ?")._b(), 	new RB_gld()._span(2)._ins(4))
								
								._a(new RB_lab()._tr("Einzelne Fuhre"),										 		new RB_gld()._span(1)._ins(4))
								._a(BtStartVergleichHistorie.this.tfIDFuhre, 						 				new RB_gld()._span(1)._ins(4))
								
								._a(new RB_lab()._tr(".. oder .. Datum Start: "), 									new RB_gld()._span(1)._ins(4))
								._a(BtStartVergleichHistorie.this.dateSelectorStart, 						 		new RB_gld()._span(1)._ins(4))
								
								._a(new RB_lab()._tr("Protokoll schreiben ?"), 									    new RB_gld()._span(1)._ins(4))
								._a(BtStartVergleichHistorie.this.cbProtokollSchreiben, 					 		new RB_gld()._span(1)._ins(4))

								._a(new RB_lab()._tr("Fehlende Handelsdef. ergänzen ?"), 							new RB_gld()._span(1)._ins(4))
								._a(BtStartVergleichHistorie.this.cbFehlendeHandelsdefsErfassen, 					new RB_gld()._span(1)._ins(4))
								
								._a(this.getOwnSaveButton()._bord_dd()._b()._center()._fsa(2), 						new RB_gld()._span(1)._ins(4,10,4,2)._left_bottom())
								._a(this.getOwnCloseButton()._bord_dd()._b()._center()._fsa(2), 					new RB_gld()._span(1)._ins(4,10,4,2)._right_bottom())
								;
					this.setTitle(S.ms("Bitte Start-Datum oder Fuhren festlegen"));
					this.setPopupWidth(400);
					this.setPopupHeight(300);
					pop.add(g,E2_INSETS.I(0));
					
					this.getOwnSaveButton()._aaa(new XX_ActionAgent() {
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							BtStartVergleichHistorie.this.bStartBreak=false;
						}
					});
					
					return pop;
				}
				
				@Override
				protected boolean check4break(MyE2_MessageVector mv) throws myException {
					return BtStartVergleichHistorie.this.bStartBreak;
				}
			});
		}
		
	}
	
	
	
}
