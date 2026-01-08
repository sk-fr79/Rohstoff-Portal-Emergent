/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.BasicInterfaces.Service.PdServiceCheckAndAddRoutingKostenTyp;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoRoutingEntfernungUndKostenBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoRoutingEntfernungUndKosten.ENUM_ROUTING_TYP;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec21SaveOnlyChanged;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.StammDaten.FuhrenKostenTypen.EN_KOSTENTYP_VORSCHLAEGE;

/**
 * @author martin
 *
 */
public class FU_LIST_btRoutingEntfernungUndKosten extends E2_Button {
	public FU_LIST_btRoutingEntfernungUndKosten() {
		super();
		this._ttt(new MyE2_String("Routen planen, Entfernung und Zeitprognose"));
		this.__setImages(E2_ResourceIcon.get_RI("routing.png"),E2_ResourceIcon.get_RI("routing__.png"));

		this._aaa(new ownActionAgent());

		this.setBreak4PopupController(new OwnPopupController());
	}




	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP map = FU_LIST_btRoutingEntfernungUndKosten.this.EXT().get_oComponentMAP();

			//zuerst pruefen, ob es einen kostentyp routing
			Rec20 recKostenTyp = new PdServiceCheckAndAddRoutingKostenTyp().getRecTypRouting();

			if (map!=null && recKostenTyp!=null) {
				Rec21SaveOnlyChanged fuhre = new Rec21SaveOnlyChanged(_TAB.vpos_tpa_fuhre)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());

				_TAB fuk = _TAB.vpos_tpa_fuhre_kosten;
				_TAB fukt = _TAB.fuhren_kosten_typ;

				SEL sel = new SEL(fuk,fukt).FROM(fuk).INNERJOIN(fukt, VPOS_TPA_FUHRE_KOSTEN.id_fuhren_kosten_typ,FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ)
						.WHERE(	new vgl(FUHREN_KOSTEN_TYP.kurztext_uebersicht,EN_KOSTENTYP_VORSCHLAEGE.ROUTE.dbVal()))
						.AND(	new vgl(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre,fuhre.getUfs(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)))
						.AND(	new vgl_YN(VPOS_TPA_FUHRE_KOSTEN.deleted,false))
						;

//				DEBUG._print("sel: "+sel.s());

				RecList20 rlKostenRoutingVorhanden = new RecList20(_TAB.vpos_tpa_fuhre_kosten)._fill(sel.s());


				Rec20 adresseStart = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse, true);
				Rec20 adresseZiel = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_ziel, ADRESSE.id_adresse, true);

				if (O.isNoOneNull(fuhre,adresseStart,adresseZiel)) {
					BigDecimal bdLatitudeStart = adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeStart = adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
					BigDecimal bdLatitudeZiel = adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeZiel = adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude);

					if (O.isNoOneNull(bdLatitudeStart ,bdLongitudeStart ,bdLatitudeZiel ,bdLongitudeZiel )) {
						PdServiceGeoRoutingEntfernungUndKostenBean service = new PdServiceGeoRoutingEntfernungUndKostenBean();
						MyE2_MessageVector mv = new MyE2_MessageVector();
						HashMap<ENUM_ROUTING_TYP,BigDecimal> response_map = service.getDistanceTimeAmdCosts(bdLatitudeStart, bdLongitudeStart, bdLatitudeZiel, bdLongitudeZiel, 
								fuhre.getUfs(VPOS_TPA_FUHRE.transportkennzeichen), fuhre.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen), mv);

						if (mv.get_bIsOK()) {
							BigDecimal bdEntferungKm = 	response_map.get(ENUM_ROUTING_TYP.ENTFERNUNG);
							BigDecimal bdZeitMinuten = 	response_map.get(ENUM_ROUTING_TYP.ZEIT_MIN);
							BigDecimal bdKosten = 		response_map.get(ENUM_ROUTING_TYP.KOSTEN_KM);

							if (O.isNoOneNull(bdEntferungKm,bdZeitMinuten)) {
								fuhre._setNewVal(VPOS_TPA_FUHRE.routing_distance_km, bdEntferungKm, bibMSG.MV());
								fuhre._setNewVal(VPOS_TPA_FUHRE.routing_time_minutes, bdZeitMinuten.longValue(), bibMSG.MV());
								fuhre._SAVE(true, bibMSG.MV());
								bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Entferung und Zeit wurde ermittelt: ")
										.ut(" Entfernung(km):  "+new MyBigDecimal(bdEntferungKm).get_FormatedRoundedNumber(2))
										.ut(" / Zeit (min):  "+new MyBigDecimal(bdZeitMinuten).get_FormatedRoundedNumber(2))
										));
							}
							if (rlKostenRoutingVorhanden.size()>0) {
								bibMSG.MV()._addWarn("Es ist bereits ein Kostenbetrag vom Typ Routing erfasst!");
							} else {
								if (bdKosten!=null && bdKosten.compareTo(BigDecimal.ZERO)>0) {
									MyE2_MessageVector mv1 = bibMSG.getNewMV();
									Rec20 rKosten = new Rec20(_TAB.vpos_tpa_fuhre_kosten);
									rKosten	._setNewVal(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre, 	fuhre.getRawVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre), mv1)
									._setNewVal(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten, 		bdKosten, mv1)
									._setNewVal(VPOS_TPA_FUHRE_KOSTEN.id_fuhren_kosten_typ, recKostenTyp.getRawVal(FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ), mv1)
									._setNewVal(VPOS_TPA_FUHRE_KOSTEN.info_kosten, 			"Automatische Eintrag bei Routenplanung!", mv1)
									._setNewValueInDatabaseTerminus(VPOS_TPA_FUHRE_KOSTEN.datum_beleg, 				"SYSDATE")
									;
									if (mv1.get_bIsOK()) {
										rKosten._SAVE(true, mv1);
										if (mv1.get_bIsOK()) {
											bibMSG.MV()._addInfo(S.ms("Es wurde ein Kostensatz geschrieben ...").ut(new MyBigDecimal(bdKosten).get_FormatedRoundedNumber(2)));
											map._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, false);
										}
									}
									bibMSG.add_MESSAGE(mv1);

								} else {
									bibMSG.MV()._addWarn("Für die Fuhre liegt keine Transportkosten-Informationen vor !");
								}
							}

						} else {
							bibMSG.MV()._add(mv);
						}
					} else {
						bibMSG.MV()._addAlarm("Die Geokoordinaten sind nicht vollständig!");
					}
				} else {
					bibMSG.MV()._addAlarm("Ein Routing ist nicht möglich, die Adressen wurde nicht gefunden !");
				}

			} else {
				bibMSG.MV()._addAlarm("Unbekannter Fehler, Listenwerte sind unvollständig oder Kostentyp <ROUTING> nicht gefunden !");
			}


		}

	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		FU_LIST_btRoutingEntfernungUndKosten oButton = new FU_LIST_btRoutingEntfernungUndKosten();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}


	@Override
	//listenbutton immer enabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}

	private class OwnPopupController extends E2_Break4PopupController {
		public OwnPopupController() {
			super();
			this._registerBreak(new OwnPopupShowErgebnis());
			this._registerBreak(new OwnPopupFehlenderKostenTyp());
		}
	}

	private class OwnPopupFehlenderKostenTyp extends E2_Break4Popup {

		private ownButtonSave btSave = new ownButtonSave();

		public OwnPopupFehlenderKostenTyp() {
			super();
			this.setTitle(S.ms("Keine oder uneindeutiger Kostentyp <ROUTE>"));
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return new OwnPopupContainer();
		}

		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			return !(new PdServiceCheckAndAddRoutingKostenTyp().isKostenTypRoutingSingularAndPresent());
		}

		private class OwnPopupContainer extends E2_BasicModuleContainer {
			public OwnPopupContainer() throws myException {
				super();
				OwnPopupFehlenderKostenTyp.this._setWidth(400)._setHeight(300)._setLeftPos(200)._setTopPos(200);
				E2_Grid g = new E2_Grid()._setSize(380);

				if (new PdServiceCheckAndAddRoutingKostenTyp().getCountKostenTypRouting()==0) {
					OwnPopupFehlenderKostenTyp.this.btSave.set_bEnabled_For_Edit(true);

					g
					._a(new RB_lab(S.ms("Es exitiert kein passender Kostentyp")),new RB_gld()._left_mid()._ins(5,5,5,2))
					._a(new RB_lab(S.ms("... aber ich kann einen passenden anlegen:")),new RB_gld()._left_mid()._ins(5,2,5,2))
					._a(OwnPopupFehlenderKostenTyp.this.btSave,new RB_gld()._left_mid()._ins(5,10,5,2))
					._a(new E2_Grid()._setSize(200,150)
							._a(OwnPopupFehlenderKostenTyp.this.getOwnSaveButton()._tr("Weiter zur Kostenermittlung")._standard_text_button(), new RB_gld()._ins(5,20,5,2))
							._a(OwnPopupFehlenderKostenTyp.this.getOwnCloseButton()._tr("Fenster schliessen")._standard_text_button(), new RB_gld()._ins(5,20,5,2))
							);

				} else {
					OwnPopupFehlenderKostenTyp.this.btSave.set_bEnabled_For_Edit(false);
					g
					._a(new RB_lab(S.ms("Es exitieren mehrer Kostentyp <ROUTE>")),new RB_gld()._left_mid()._ins(5,5,5,2))
					._a(new RB_lab(S.ms("Bitte korrigieren Sie das im Fuhren-Kostentypen-Stamm")),new RB_gld()._left_mid()._ins(5,2,5,2))
					._a(new E2_Grid()._setSize(200)
							._a(OwnPopupFehlenderKostenTyp.this.getOwnCloseButton()._tr("Fenster schliessen")._standard_text_button(), new RB_gld()._ins(5,20,5,2))
							);
				}

				this.add(g, E2_INSETS.I(2,2,2,2));
			}
		}

		private class ownButtonSave extends E2_Button {
			public ownButtonSave() {
				super();
				this._tr("Kostentyp <ROUTE> anlegen")._b()._bord_black()._aaa(new ownAgentCreateKostentyp());
				this._style(E2_Button.StyleTextButtonCentered());
			}
			private class ownAgentCreateKostentyp extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					MyE2_MessageVector mv = bibMSG.getNewMV();
					new PdServiceCheckAndAddRoutingKostenTyp().writeKostenTypRouting(mv);
					if (mv.get_bHasAlarms()) {
						bibMSG.add_MESSAGE(mv); 
					} else {
						bibMSG.MV()._addInfo("Kostensatz <ROUTE> angelegt ...");
					}
					ownButtonSave.this.set_bEnabled_For_Edit(false);
				}	
			}	
		}	
	}	

	private class ownSaveEntfZeit extends E2_Button {
		public ownSaveEntfZeit() {
			super();
			this._tr("Entfernung/Zeit speichern")._bord_black()._aaa(new ownActionAgent());
			this._style(E2_Button.StyleTextButtonCentered());
		}
	}	


	private class OwnPopupShowErgebnis extends E2_Break4Popup {

		public OwnPopupShowErgebnis() {
			super();
			this.setTitle(S.ms("Route Ergebnis"));
		}

		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return new OwnErgebnisPopUpContainer();
		}

		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			E2_ComponentMAP map = FU_LIST_btRoutingEntfernungUndKosten.this.EXT().get_oComponentMAP();
			
			Rec20 fuhre = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());

			Rec20 adresseStart = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse, true);
			Rec20 adresseZiel = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_ziel, ADRESSE.id_adresse, true);
			
			if(O.isNoOneNull(fuhre,adresseStart,adresseZiel)) {
				if(O.isNoOneNull(
						adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude),
						adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude),
						adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude),
						adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude)
						)) {
					return true;
				}else {
					bibMSG.MV()._addAlarm("Die Geokoordinaten sind nicht vollständig!");
					return false;
				}
			}else {
				bibMSG.MV()._addAlarm("Ein Routing ist nicht möglich, die Adressen wurde nicht gefunden !");
				return false;
			}
			
		}

		private class OwnErgebnisPopUpContainer extends E2_BasicModuleContainer {
			public OwnErgebnisPopUpContainer() throws myException {
				super();

				E2_ComponentMAP map = FU_LIST_btRoutingEntfernungUndKosten.this.EXT().get_oComponentMAP();
				E2_Grid g = new E2_Grid()._setSize(120,120,120);

				OwnPopupShowErgebnis.this._setWidth(400)._setHeight(300)._setLeftPos(200)._setTopPos(200);

				RB_gld title_gld = new RB_gld()._col_back_d()._ins(2);
				RB_gld data_gld = new RB_gld()._col_back(new E2_ColorBase())._ins(2);

				MyE2_MessageVector mv = OwnPopupShowErgebnis.this.getBreak4PopupController().getMv();
				
				if (map!=null) {
					Rec20 fuhre = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());

					Rec20 adresseStart = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse, true);
					Rec20 adresseZiel = fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_ziel, ADRESSE.id_adresse, true);

					if (O.isNoOneNull(fuhre,adresseStart,adresseZiel)) {

//						MyE2_MessageVector mv = new MyE2_MessageVector();

						BigDecimal bdLatitudeStart = 	adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal bdLongitudeStart = 	adresseStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						BigDecimal bdLatitudeZiel = 	adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal bdLongitudeZiel = 	adresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude);

						ownSaveEntfZeit save_bt= new ownSaveEntfZeit();
						save_bt._aaa(()-> this.CLOSE_AND_DESTROY_POPUPWINDOW(true));

						if (O.isNoOneNull(bdLatitudeStart ,bdLongitudeStart ,bdLatitudeZiel ,bdLongitudeZiel )) {
							PdServiceGeoRoutingEntfernungUndKostenBean service = new PdServiceGeoRoutingEntfernungUndKostenBean();

							HashMap<ENUM_ROUTING_TYP,BigDecimal> response_map = service.getDistanceTimeAmdCosts(bdLatitudeStart, bdLongitudeStart, bdLatitudeZiel, bdLongitudeZiel, 
									fuhre.getUfs(VPOS_TPA_FUHRE.transportkennzeichen), fuhre.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen), mv);

							if (mv.get_bIsOK()) {
								BigDecimal bdEntferungKm = 	response_map.get(ENUM_ROUTING_TYP.ENTFERNUNG);
								BigDecimal bdZeitMinuten = 	response_map.get(ENUM_ROUTING_TYP.ZEIT_MIN);
								BigDecimal bdKosten = 		response_map.get(ENUM_ROUTING_TYP.KOSTEN_KM);

								String sZeit = "0:00 h";
								if (bdZeitMinuten != null ){
									long hours = bdZeitMinuten.longValue() / 60; 
									long minutes = bdZeitMinuten.longValue() % 60;
									if (hours == 0){
										sZeit = String.format("%02d min", minutes);
									} else {
										sZeit = String.format("%d h %02d min", hours, minutes);
									}
								}
								
								g._bo_ddd()
								._a("Entfernung", 	title_gld)
								._a("Zeit", 		title_gld)
								._a("Kosten", 		title_gld)
								._a(new MyBigDecimal(bdEntferungKm).get_FormatedRoundedNumber(2)+ " km", 	data_gld)
//								._a(new MyBigDecimal(bdZeitMinuten).get_FormatedRoundedNumber(0)+ " min", 	data_gld)
								._a(sZeit, 	data_gld)
								._a(new MyBigDecimal(bdKosten).get_FormatedRoundedNumber(2), data_gld);
								
								this.add(g, E2_INSETS.I(2,2,2,2));
								this.add(new E2_Grid()._setSize(140,80,140)
										._a(save_bt, new RB_gld()._ins(2,20,5,2)._left_bottom())
										._a()
										._a(OwnPopupShowErgebnis.this.getOwnCloseButton()._tr("Fenster schliessen")._standard_text_button()._center(), new RB_gld()._ins(5,20,5,2)._right_bottom())
										, E2_INSETS.I(0));
							}
						}else {
							mv._addAlarm("Die Geokoordinaten sind nicht vollständig!");
						}
						//OwnPopupShowErgebnis.this.getBreak4PopupController().getMv()._add(mv);
					}
				}
			}
		}
	}
}
