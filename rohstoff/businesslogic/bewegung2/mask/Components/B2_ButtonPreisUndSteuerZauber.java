/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 10.03.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.BasicInterfaces.Service.PdServiceErmittleLagerPreis;
import panter.gmbh.BasicInterfaces.Service.PdServiceErmittleRecListAngebotKontrakt;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_LabNoDatabase;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_GridSelectRec21;
import panter.gmbh.Echo2.components.E2_GridSelectRec21.ButtonWithRec;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 10.03.2020
 *
 */
public class B2_ButtonPreisUndSteuerZauber extends E2_Button implements IF_RbComponentWithOwnKey{

	
	private B2_McForValueSettingsOnMaskAction  controller = null;
	
	/**
	 * @author martin
	 * @date 10.03.2020
	 *
	 */
	public B2_ButtonPreisUndSteuerZauber() {
		
		this.__setImages(E2_ResourceIcon.get_RI("wizard.png"), E2_ResourceIcon.get_RI("wizard__.png"));
		this._width(30);
		this._ttt(S.ms("Preisprüfung / Einsetzen eines Kontraktes / Angebots und Steuerprüfung"));
		this._aaa(new OwnActionKlickOnZauberstab());
	}

	
	@Override
	public RB_KF getFieldKey() {
		return B2_ButtonPreisUndSteuerZauber.key();
	}

	
	public static RB_KF key() {
		return new RB_KF()._setREALNAME("E2_ButtonSteuerZauber")._setHASHKEY("E2_ButtonSteuerZauber@1fc9f682-8132-47bf-a9d2-f4cb0dae1fe6");
	}
	
	
	private class OwnActionKlickOnZauberstab extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				
				RecList21 angeboteToSelectLeft = null;
				RecList21 kontrakteToSelectLeft = null;
				RecList21 angeboteToSelectRight = null;
				RecList21 kontrakteToSelectRight= null;
				
			
				controller = new B2_McForValueSettingsOnMaskAction(B2_ButtonPreisUndSteuerZauber.this);
				
				if (controller.isLeftPreisAbschluss()) {
					bibMSG.MV()._addWarn(S.ms("Ladeseite: Prüfung kann nur vor dem Preisabschluss erfolgen !"));
				} else {
				
					//linke seite
					if (O.isOneNull(controller.getValueJustInTime(RecS1.key, BG_STATION.id_adresse)
									,controller.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez)
									,controller.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung)
									,controller.getValueJustInTime(RecA1.key, BG_ATOM.menge)
									,controller.getValueJustInTime(RecS1.key, BG_STATION.id_adresse_besitz_ldg)
									,controller.getValueJustInTime(RecS2.key, BG_STATION.id_adresse_besitz_ldg)
									)) {
						bibMSG.MV()._addWarn(S.ms("Die Ladeseite kann nicht bewertet werden, da dazu Adresse, Menge, Sorte, Datum sowie die Besitzverhältnisse nötig sind !"));
					} else {
						
						if (! controller.isLeftPreisManuel()) {
						
							//zuerst linke seite
							if (!controller.isLeftSiteToBill()) {   //keine abrechnung links 
								
								BigDecimal preis = BigDecimal.ZERO;
								
								if (controller.isStartBesitzerFullAndOwn()) {
									//lagerpreis ermitteln
									preis = new PdServiceErmittleLagerPreis().getLagerpreis(controller.getStartAdresse(), controller.getArtbezQuelle());
								}
								String c_preis = new MyBigDecimal(preis).get_FormatedRoundedNumber(2);
								
								controller	._setMaskValue(RecA1.key, BG_ATOM.e_preis_basiswaehrung, c_preis)
											._setMaskValue(RecA1.key, BG_ATOM.e_preis_fremdwaehrung, c_preis)
											._setMaskValue(RecA1.key, BG_ATOM.e_preis_res_netto_mge_basis, c_preis)
											._setMaskValue(RecA1.key, BG_ATOM.e_preis_res_netto_mge_fremd, c_preis)
											._setMaskValue(RecA1.key, BG_ATOM.manuell_preis, "Y")
											;
								
								controller._setPreisZielFuerWareneingangeOderNichtAbrechnungsFuhren();
								
							} else {
	
								
								Rec21_VPosKon recKonLeft  = 	controller.getKontraktPosStart();
								Rec21_VPosStd recAngebotLeft  = controller.getAngebotPosStart();
								
								if ( (recKonLeft!=null && recKonLeft.is_ExistingRecord()) || (recAngebotLeft!=null && recAngebotLeft.is_ExistingRecord())) {
									controller._loadPreisAndWaehrungskursFromKontraktOrAngebot(EnPositionStation.LEFT, bibMSG.MV());
									controller._berechneFremdPreis(EnPositionStation.LEFT, bibMSG.MV());
									
									controller._setPreisZielFuerWareneingangeOderNichtAbrechnungsFuhren();
	
									
								} else {
									//hier werden passende angebote und kontrakte gesucht
									
									PdServiceErmittleRecListAngebotKontrakt ermittler = new PdServiceErmittleRecListAngebotKontrakt();
									
									RecList21 rlKontrakte = ermittler.getReclistKontrakte(
																		    (Long) controller.getValueJustInTime(RecS1.key, BG_STATION.id_adresse)
																		  , (Long) controller.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez) 
																		  , (Date) controller.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung)
																		  , true);
									
									RecList21 rlAngebote = ermittler.getReclistAngebote(
																		    (Long) controller.getValueJustInTime(RecS1.key, BG_STATION.id_adresse)
																		  , (Long) controller.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez) 
																		  , (Date) controller.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung)
																		  , true);
									
									
									if (rlKontrakte.size()>0 || rlAngebote.size()>0) {
										if (rlKontrakte.size()>0) {
											kontrakteToSelectLeft=rlKontrakte;
										}
										if (rlAngebote.size()>0) {
											angeboteToSelectLeft=rlAngebote;
										}
									} else {
										bibMSG.MV()._addInfo("Auf der Ladeseite sind keine preisgebenenden Kontrakte oder Angebote zu finden !");
									}
								}
							}
						} else {
							bibMSG.MV()._addInfo("Auf der Ladeseite haben Sie den Preis als manuell markiert !");
						}
					}
				}

				
				if (controller.isRightPreisAbschluss()) {
					bibMSG.MV()._addWarn(S.ms("Abladeseite: Prüfung kann nur vor dem Preisabschluss erfolgen !"));
				} else {
					//rechte seite
					if (O.isOneNull(controller.getValueJustInTime(RecS3.key, BG_STATION.id_adresse)
									,controller.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez)
									,controller.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung)
									,controller.getValueJustInTime(RecA2.key, BG_ATOM.menge)
									,controller.getValueJustInTime(RecS2.key, BG_STATION.id_adresse_besitz_ldg)
									,controller.getValueJustInTime(RecS3.key, BG_STATION.id_adresse_besitz_ldg)
									)) {
						bibMSG.MV()._addWarn(S.ms("Die Abladeseite kann nicht bewertet werden, da dazu Adresse, Sorte, Menge, Datum sowie die Besitzverhältnisse nötig sind !"));
					} else {
						
						if (! controller.isRightPreisManuel()) {
						
							//dann rechte seite
							if (controller.isRightSiteToBill()) {
	
								Rec21_VPosKon recKonRight  = 		controller.getKontraktPosZiel();
								Rec21_VPosStd recAngebotRight  = 	controller.getAngebotPosZiel();
								
								if ( (recKonRight!=null && recKonRight.is_ExistingRecord()) || (recAngebotRight!=null && recAngebotRight.is_ExistingRecord())) {
									controller._loadPreisAndWaehrungskursFromKontraktOrAngebot(EnPositionStation.RIGHT, bibMSG.MV());
									controller._berechneFremdPreis(EnPositionStation.RIGHT, bibMSG.MV());
								} else {
									//hier werden passende angebote und kontrakte gesucht
									
									PdServiceErmittleRecListAngebotKontrakt ermittler = new PdServiceErmittleRecListAngebotKontrakt();
									
									RecList21 rlKontrakte = ermittler.getReclistKontrakte(
																		    (Long) controller.getValueJustInTime(RecS3.key, BG_STATION.id_adresse)
																		  , (Long) controller.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez) 
																		  , (Date) controller.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung)
																		  , false);
									
									RecList21 rlAngebote = ermittler.getReclistAngebote(
																		    (Long) controller.getValueJustInTime(RecS3.key, BG_STATION.id_adresse)
																		  , (Long) controller.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez) 
																		  , (Date) controller.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung)
																		  , false);
									
									
									if (rlKontrakte.size()>0 || rlAngebote.size()>0) {
										if (rlKontrakte.size()>0) {
											kontrakteToSelectRight=rlKontrakte;
										}
										if (rlAngebote.size()>0) {
											angeboteToSelectRight=rlAngebote;
										}
									} else {
										bibMSG.MV()._addInfo("Auf der Abladeseite sind keine preisgebenenden Kontrakte oder Angebote zu finden !");
									}
								}
							}
						} else {
							bibMSG.MV()._addInfo("Auf der Abladeseite haben Sie den Preis als manuell markiert !");
						}
					}
				}
				
				if (!O.isAllNull(kontrakteToSelectLeft,angeboteToSelectLeft,kontrakteToSelectRight,angeboteToSelectRight)) {
					new OwnBasicModuleContainerToSelectKontraktOrAngebot(kontrakteToSelectLeft, angeboteToSelectLeft, kontrakteToSelectRight, angeboteToSelectRight);
				} else {
					controller._executeTaxDetectionIfPossible();
				}
				
				
			} catch (myExceptionForUser e) {
				bibMSG.MV().add((e.get_ErrorMessage()));
			} catch (Exception e) {
				bibMSG.MV()._addAlarm(S.ms("Anwendungsfehler: ").ut(e.getLocalizedMessage()));
			}
			
		}
	}
	
	
	private class OwnBasicModuleContainerToSelectKontraktOrAngebot extends E2_BasicModuleContainer {

		private RB_cb  cbPreisLeftManuelKon = new RB_cb()._t(S.ms("Ladeseite Preis manuell"));
		private RB_cb  cbPreisLeftManuelAng = new RB_cb()._t(S.ms("Ladeseite Preis manuell"));

		private RB_cb  cbPreisRightManuelKon = new RB_cb()._t(S.ms("Abladeseite Preis manuell"));
		private RB_cb  cbPreisRightManuelAng = new RB_cb()._t(S.ms("Abladeseite Preis manuell"));
		
		private Rec21  selectedRecLeft = null;
		private Rec21  selectedRecRight = null;
		
		private E2_GridSelectRec21 selKontraktLeft = null;
		private E2_GridSelectRec21 selAngebotLeft = null;
		private E2_GridSelectRec21 selKontraktRight = null;
		private E2_GridSelectRec21 selAngebotRight = null;

		/**
		 * @author martin
		 * @throws myException 
		 * @date 31.07.2020
		 *
		 */
		public OwnBasicModuleContainerToSelectKontraktOrAngebot(	RecList21 kontrakteToSelectLeft
																	,RecList21 angeboteToSelectLeft
																	,RecList21 kontrakteToSelectRight
																	,RecList21 angeboteToSelectRight) throws myException {
			super();
			//hier ein fenster mit 4 quadranten und auswahlboxen fuer die auswahl von kontrakt oder angebot
		
			cbPreisLeftManuelKon._aaa(()->{
				cbPreisLeftManuelAng._setSelected(cbPreisLeftManuelKon.isSelected());
				selectedRecLeft = null;
				if (selKontraktLeft!=null) { selKontraktLeft._renderGrid();}
				if (selAngebotLeft!=null) { selAngebotLeft._renderGrid(); }
			});
			cbPreisLeftManuelAng._aaa(()->{
				cbPreisLeftManuelKon._setSelected(cbPreisLeftManuelAng.isSelected());
				selectedRecLeft = null;
				if (selKontraktLeft!=null) { selKontraktLeft._renderGrid();}
				if (selAngebotLeft!=null) { selAngebotLeft._renderGrid(); }
			});
			cbPreisRightManuelKon._aaa(()->{
				cbPreisRightManuelAng._setSelected(cbPreisRightManuelKon.isSelected());
				selectedRecRight = null;
				if (selKontraktRight!=null) { selKontraktRight._renderGrid();}
				if (selAngebotRight!=null) { selAngebotRight._renderGrid(); }
			});
			cbPreisRightManuelAng._aaa(()->{
				cbPreisRightManuelKon._setSelected(cbPreisRightManuelAng.isSelected());
				selectedRecRight = null;
				if (selKontraktRight!=null) { selKontraktRight._renderGrid();}
				if (selAngebotRight!=null) { selAngebotRight._renderGrid(); }
			});

			
			MyE2_ContainerEx   paneKontraktLinks = new MyE2_ContainerEx();
			MyE2_ContainerEx   paneAngebotLinks = new MyE2_ContainerEx();
			MyE2_ContainerEx   paneKontraktRechts = new MyE2_ContainerEx();
			MyE2_ContainerEx   paneAngebotRechts = new MyE2_ContainerEx();
			
			paneKontraktLinks.setWidth(new Extent(620));
			paneKontraktLinks.setHeight(new Extent(250));
			paneAngebotLinks.setWidth(new Extent(620));
			paneAngebotLinks.setHeight(new Extent(250));
			paneKontraktRechts.setWidth(new Extent(620));
			paneKontraktRechts.setHeight(new Extent(250));
			paneAngebotRechts.setWidth(new Extent(620));
			paneAngebotRechts.setHeight(new Extent(250));
			
			
			selKontraktLeft = null;
			selAngebotLeft = null;
			selKontraktRight = null;
			selAngebotRight = null;
			
			boolean linksBeteiligt = false;
			boolean rechtsBeteiligt = false;	
			
			if (kontrakteToSelectLeft!=null) {
				linksBeteiligt = true;
				selKontraktLeft = new OwnGridSelectKontrakt(true);
				selKontraktLeft._setTitelComponent(new Ueberschrift("Kontrakte auf der Ladeseite ...",cbPreisLeftManuelKon), new RB_gld()._col_back_dd()._ins(2, 3, 2, 3));
				selKontraktLeft._renderGrid(kontrakteToSelectLeft);
				paneKontraktLinks.add(selKontraktLeft);
	
				selKontraktLeft._addActionAgentForListButtons((s)-> {
					selectedRecLeft = ((E2_GridSelectRec21.ButtonWithRec)s).getRec21();
					if (selAngebotLeft!=null) {selAngebotLeft._renderGrid();}
					if (selKontraktLeft!=null) {selKontraktLeft._renderGrid();}
					cbPreisLeftManuelAng._setSelected(false);
					cbPreisLeftManuelKon._setSelected(false);
				});

				
			}

			if (angeboteToSelectLeft!=null) {
				linksBeteiligt = true;

				selAngebotLeft = new OwnGridSelectAngebot(true);
				selAngebotLeft._setTitelComponent(new Ueberschrift("Angebote auf der Ladeseite ...",cbPreisLeftManuelAng), new RB_gld()._col_back_dd()._ins(2, 3, 2, 3));
				selAngebotLeft._renderGrid(angeboteToSelectLeft);
				paneAngebotLinks.add(selAngebotLeft);
			
				
				selAngebotLeft._addActionAgentForListButtons((s)-> {
					selectedRecLeft = ((E2_GridSelectRec21.ButtonWithRec)s).getRec21();
					if (selAngebotLeft!=null) {selAngebotLeft._renderGrid();}
					if (selKontraktLeft!=null) {selKontraktLeft._renderGrid();}
					cbPreisLeftManuelAng._setSelected(false);
					cbPreisLeftManuelKon._setSelected(false);
				});
				
			}
			if (kontrakteToSelectRight!=null) {
				rechtsBeteiligt = true;
				selKontraktRight = new OwnGridSelectKontrakt(false);
				selKontraktRight._setTitelComponent(new Ueberschrift("Kontrakte auf der Abladeseite ...",cbPreisRightManuelKon), new RB_gld()._col_back_dd()._ins(2, 3, 2, 3));
				selKontraktRight._renderGrid(kontrakteToSelectRight);
				paneKontraktRechts.add(selKontraktRight);

				selKontraktRight._addActionAgentForListButtons((s)-> {
					selectedRecRight = ((E2_GridSelectRec21.ButtonWithRec)s).getRec21();
					if (selAngebotRight!=null) {selAngebotRight._renderGrid();}
					if (selKontraktRight!=null) {selKontraktRight._renderGrid();}
					cbPreisRightManuelAng._setSelected(false);
					cbPreisRightManuelKon._setSelected(false);

				});

				
			}
			if (angeboteToSelectRight!=null) {
				rechtsBeteiligt = true;
				selAngebotRight = new OwnGridSelectAngebot(false);
				selAngebotRight._setTitelComponent(new Ueberschrift("Angebote auf der Abladeseite ...",cbPreisRightManuelAng), new RB_gld()._col_back_dd()._ins(2, 3, 2, 3));
				selAngebotRight._renderGrid(angeboteToSelectRight);
				paneAngebotRechts.add(selAngebotRight);
			
				
				selAngebotRight._addActionAgentForListButtons((s)-> {
					selectedRecRight = ((E2_GridSelectRec21.ButtonWithRec)s).getRec21();
					if (selAngebotRight!=null) {selAngebotRight._renderGrid();}
					if (selKontraktRight!=null) {selKontraktRight._renderGrid();}
					cbPreisRightManuelAng._setSelected(false);
					cbPreisRightManuelKon._setSelected(false);
				});

			}
			
			
			E2_Grid grid = null;
			if (linksBeteiligt && rechtsBeteiligt) {
				grid = new E2_Grid()._bo_b()._setSize(620,550)._a(paneKontraktLinks)._a(paneKontraktRechts)._a(paneAngebotLinks)._a(paneAngebotRechts);
			} else if (linksBeteiligt) {
				grid = new E2_Grid()._bo_b()._setSize(620)._a(paneKontraktLinks)._a(paneAngebotLinks);
			} else if (rechtsBeteiligt) {
				grid = new E2_Grid()._bo_b()._setSize(620)._a(paneKontraktRechts)._a(paneAngebotRechts);
			}
			
			//in der untersten zeile einen ok-button
			grid._a(new E2_Button()._t(S.ms("OK"))._setShapeBigHighLightText()._aaa(new OwnActionCloseWindowAndApplySettings()),  new RB_gld()._ins(5));
			
			this.add(grid, E2_INSETS.I(5));
			this.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Bitte wählen !"),1300,750);
			
		}
	
		
		private class OwnButtonStyler implements E2_GridSelectRec21.ButtonStyler {
			private boolean left = true;
			public OwnButtonStyler(boolean left) {
				super();
				this.left = left;
			}

			@Override
			public void applyStyleAndLayoutToButton(ButtonWithRec button, int colNumber) {
				Rec21 recCompare = this.left?selectedRecLeft:selectedRecRight;
				MutableStyle style = null;
				RB_gld  	 layout = null;
				boolean      bold = false;
				if (recCompare!=null && button.getRec21()==recCompare) {
					if (colNumber>=3) {
						//zahlen rechts ausrichten
						style = E2_Button.baseStyleRightBold(Color.BLACK, new E2_ColorLLight());
					} else {
						style = E2_Button.baseStyleBold(Color.BLACK, new E2_ColorLLight());
					}
					layout = new RB_gld()._ins(1, 2, 1, 2)._col_back(new E2_ColorLLight());
					bold = true;
				} else {
					if (colNumber>=3) {
						//zahlen rechts ausrichten
						style = E2_Button.baseStyleRight(Color.BLACK, new E2_ColorBase());
					} else {
						style = E2_Button.baseStyle(Color.BLACK, new E2_ColorBase());
					}
					layout = new RB_gld()._ins(1, 2, 1, 2)._col_back(new E2_ColorBase());
				}
				button.setStyle(style);
				button.setLayoutData(layout);
				if (bold) {
					button._fo_bold();
				}
			}
		}
		
		
		private class Ueberschrift extends E2_Grid {
			public Ueberschrift(String labelText, RB_cb checkbox) {
				super();
				this._s(2)._a(new RB_lab()._b()._t(S.ms(labelText)), new RB_gld()._ins(0,0,20,0))._a(checkbox, new RB_gld()._ins(0,0,0,0));
			}
		}
		

		
		//agent zu schliessen des fensters und uebernehmen der einstellungen
		private class OwnActionCloseWindowAndApplySettings extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				try {
					if (cbPreisLeftManuelAng.isSelected() ||cbPreisLeftManuelKon.isSelected() ) {
						controller._setMaskValue(RecA1.key, BG_ATOM.manuell_preis, "Y");
						new B2_McForMaskShapeSettings(B2_ButtonPreisUndSteuerZauber.this)._setAllMaskShapeSettings();

					} else 	if (selectedRecLeft!=null) {
						
						if (selectedRecLeft.get_tab()==_TAB.vpos_kon) {
							controller._setMaskValue(RecA1.key, BG_ATOM.id_vpos_kon, selectedRecLeft.getIdLong().toString());
							new B2_McForValueSettingsOnMaskAction(B2_ButtonPreisUndSteuerZauber.this)._doActionsAfterKontraktPosWasChanged(B2_ButtonPreisUndSteuerZauber.this,EnPositionStation.LEFT,false);
							controller._setPreisZielFuerWareneingangeOderNichtAbrechnungsFuhren();

						} else if (selectedRecLeft.get_tab()==_TAB.vpos_std) {
							controller._setMaskValue(RecA1.key, BG_ATOM.id_vpos_std, selectedRecLeft.getIdLong().toString());
							new B2_McForValueSettingsOnMaskAction(B2_ButtonPreisUndSteuerZauber.this)._doActionsAfterAngebotPosWasChanged(B2_ButtonPreisUndSteuerZauber.this,EnPositionStation.LEFT,false);
							controller._setPreisZielFuerWareneingangeOderNichtAbrechnungsFuhren();
							
						}
					}

					if (cbPreisRightManuelAng.isSelected() ||cbPreisRightManuelKon.isSelected() ) {
						controller._setMaskValue(RecA2.key, BG_ATOM.manuell_preis, "Y");
						new B2_McForMaskShapeSettings(B2_ButtonPreisUndSteuerZauber.this)._setAllMaskShapeSettings();

					} else 	if (selectedRecRight!=null) {

						if (selectedRecRight.get_tab()==_TAB.vpos_kon) {
							controller._setMaskValue(RecA2.key, BG_ATOM.id_vpos_kon, selectedRecRight.getIdLong().toString());
							new B2_McForValueSettingsOnMaskAction(B2_ButtonPreisUndSteuerZauber.this)._doActionsAfterKontraktPosWasChanged(B2_ButtonPreisUndSteuerZauber.this,EnPositionStation.RIGHT,false);
						} else if (selectedRecRight.get_tab()==_TAB.vpos_std) {
							controller._setMaskValue(RecA2.key, BG_ATOM.id_vpos_std, selectedRecRight.getIdLong().toString());
							new B2_McForValueSettingsOnMaskAction(B2_ButtonPreisUndSteuerZauber.this)._doActionsAfterAngebotPosWasChanged(B2_ButtonPreisUndSteuerZauber.this,EnPositionStation.RIGHT,false);
						}
					}
					CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					controller._executeTaxDetectionIfPossible();
					
				} catch (myException e) {
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw new myException(e.getLocalizedMessage());
				}
			}
		}
		
		
		
		private class OwnGridSelectKontrakt extends E2_GridSelectRec21 {
			public OwnGridSelectKontrakt(boolean isLeft) {
				super();
				this._setListButtonFont(new E2_FontPlain(-2))
				._setButtonStyler(new OwnButtonStyler(isLeft))
				._addListField(VPOS_KON.anr1)
				._addListField(VPOS_KON.anr2)
				._addListField(VPOS_KON.artbez1)
				._addListField(VPOS_KON.anzahl)
				._addListButtonGenerator((rec)-> {
					ButtonWithRec br= new ButtonWithRec(rec);
					try {
						Rec21_VPosKon kon_rec = new Rec21_VPosKon(rec);
						br._t(new MyBigDecimal(kon_rec.getMengeNochOffen()).get_FormatedRoundedNumber(2));
					} catch (Exception e) {
						e.printStackTrace();
						br._t("@@Error<9c8134560-d7f3-11ea-87d0-0242ac130003>");
					}
					
					return br;
				})
				
				._addListField(VPOS_KON.einzelpreis)
				._addListButtonGenerator((rec)-> {
						ButtonWithRec br= new ButtonWithRec(rec);
						try {
							Rec21_VPosKon kon_rec = new Rec21_VPosKon(rec);
							br._t(kon_rec.getGueltigkeit());
						} catch (Exception e) {
							e.printStackTrace();
							br._t("@@Error<9c817c30-d7f3-11ea-87d0-0242ac130003>");
						}
						
						return br;
					})
				._addListField(VPOS_KON.id_vpos_kon);
				
				//jetzt die sortierbuttons
				//jetzt die sortierbuttons
				Comparator<Rec21> sortUpAnr1 = 	 ((r1,r2)-> {return r1.getUfs("",VPOS_KON.anr1).compareTo( r2.getUfs("",VPOS_KON.anr1));});
				Comparator<Rec21> sortDownAnr1 = ((r3,r4)-> {return r4.getUfs("",VPOS_KON.anr1).compareTo( r3.getUfs("",VPOS_KON.anr1));});
				Comparator<Rec21> sortUpAnr2 = 	 ((r1,r2)-> {return r1.getUfs("",VPOS_KON.anr2).compareTo( r2.getUfs("",VPOS_KON.anr2));});
				Comparator<Rec21> sortDownAnr2 = ((r3,r4)-> {return r4.getUfs("",VPOS_KON.anr2).compareTo( r3.getUfs("",VPOS_STD.anr2));});
				Comparator<Rec21> sortUpArtbez =   ((r1,r2)-> {return r1.getUfs("",VPOS_KON.artbez1).compareTo( r2.getUfs("",VPOS_KON.artbez1));});
				Comparator<Rec21> sortDownArtbez = ((r3,r4)-> {return r4.getUfs("",VPOS_KON.artbez1).compareTo( r3.getUfs("",VPOS_KON.artbez1));});
				Comparator<Rec21> sortUpAnzahl =   ((r1,r2)-> {return ((BigDecimal)r1.getValue(VPOS_KON.anzahl,BigDecimal.ZERO)).compareTo((BigDecimal)r2.getValue(VPOS_KON.anzahl,BigDecimal.ZERO));});
				Comparator<Rec21> sortDownAnzahl = ((r1,r2)-> {return ((BigDecimal)r2.getValue(VPOS_KON.anzahl,BigDecimal.ZERO)).compareTo((BigDecimal)r1.getValue(VPOS_KON.anzahl,BigDecimal.ZERO));});
				Comparator<Rec21> sortUpPreis =   ((r1,r2)-> {return ((BigDecimal)r1.getValue(VPOS_KON.einzelpreis,BigDecimal.ZERO)).compareTo((BigDecimal)r2.getValue(VPOS_KON.einzelpreis,BigDecimal.ZERO));});
				Comparator<Rec21> sortDownPreis = ((r1,r2)-> {return ((BigDecimal)r2.getValue(VPOS_KON.einzelpreis,BigDecimal.ZERO)).compareTo((BigDecimal)r1.getValue(VPOS_KON.einzelpreis,BigDecimal.ZERO));});
				Comparator<Rec21> sortUpGueltig =   ((r1,r2)-> {
									try {
										return new Rec21_VPosKon(r1).getGueltigkeit().compareTo( new Rec21_VPosKon(r2).getGueltigkeit());
									} catch (myException e) {
										e.printStackTrace();
										return "1".compareTo("1");
									}
								});
				Comparator<Rec21> sortDownGueltig =   ((r1,r2)-> {
								try {
									return new Rec21_VPosKon(r2).getGueltigkeit().compareTo( new Rec21_VPosKon(r1).getGueltigkeit());
								} catch (myException e) {
									e.printStackTrace();
									return "1".compareTo("1");
								}
							});
				
				Comparator<Rec21> sortUpID =   ((r1,r2)-> {return ((BigDecimal)r1.getValue(VPOS_KON.id_vpos_kon,BigDecimal.ZERO)).compareTo((BigDecimal)r2.getValue(VPOS_KON.id_vpos_kon,BigDecimal.ZERO));});
				Comparator<Rec21> sortDownID = ((r1,r2)-> {return ((BigDecimal)r2.getValue(VPOS_KON.id_vpos_kon,BigDecimal.ZERO)).compareTo((BigDecimal)r1.getValue(VPOS_KON.id_vpos_kon,BigDecimal.ZERO));});
				
				
				this._addHeadLineComponent(new SortButton(sortUpAnr1,sortDownAnr1)._t("Anr1")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpAnr2,sortDownAnr2)._t("Anr2")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpArtbez,sortDownArtbez)._t("Artbez1")._fsa(-2))	
					._addHeadLineComponent(new SortButton(sortUpAnzahl,sortDownAnzahl)._t("Menge")._fsa(-2))
					._addHeadLineComponent(new RB_LabNoDatabase()._t("Offen")._fsa(-2))
					._addHeadLineComponent(new SortButton(sortUpPreis,sortDownPreis)._t("Preis")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpGueltig,sortDownGueltig)._t("gültig")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpID,sortDownID)._t("ID")._fsa(-2))	
					;
			}
		}

		
		private class OwnGridSelectAngebot extends E2_GridSelectRec21 {
			public OwnGridSelectAngebot(boolean isLeft) {
				super();
				this._setListButtonFont(new E2_FontPlain(-2))
				._setButtonStyler(new OwnButtonStyler(isLeft))
				._addListField(VPOS_STD.anr1)
				._addListField(VPOS_STD.anr2)
				._addListField(VPOS_STD.artbez1)
				._addListField(VPOS_STD.einzelpreis)
				._addListButtonGenerator((rec)-> {
					ButtonWithRec br= new ButtonWithRec(rec);
					try {
						Rec21_VPosStd angebot = new Rec21_VPosStd(rec);
						br._t(angebot.getGueltigkeit());
					} catch (Exception e) {
						e.printStackTrace();
						br._t("@@Error<9c817c30-d7f3-11ea-87d0-0242ac130003>");
					}
					
					return br;
				})
				._addListField(VPOS_STD.id_vpos_std);
				
				//jetzt die sortierbuttons
				Comparator<Rec21> sortUpAnr1 = 	 ((r1,r2)-> {return r1.getUfs("",VPOS_STD.anr1).compareTo( r2.getUfs("",VPOS_STD.anr1));});
				Comparator<Rec21> sortDownAnr1 = ((r3,r4)-> {return r4.getUfs("",VPOS_STD.anr1).compareTo( r3.getUfs("",VPOS_STD.anr1));});
				Comparator<Rec21> sortUpAnr2 = 	 ((r1,r2)-> {return r1.getUfs("",VPOS_STD.anr2).compareTo( r2.getUfs("",VPOS_STD.anr2));});
				Comparator<Rec21> sortDownAnr2 = ((r3,r4)-> {return r4.getUfs("",VPOS_STD.anr2).compareTo( r3.getUfs("",VPOS_STD.anr2));});
				Comparator<Rec21> sortUpArtbez =   ((r1,r2)-> {return r1.getUfs("",VPOS_STD.artbez1).compareTo( r2.getUfs("",VPOS_STD.artbez1));});
				Comparator<Rec21> sortDownArtbez = ((r3,r4)-> {return r4.getUfs("",VPOS_STD.artbez1).compareTo( r3.getUfs("",VPOS_STD.artbez1));});
				Comparator<Rec21> sortUpPreis =   ((r1,r2)-> {return ((BigDecimal)r1.getValue(VPOS_STD.einzelpreis,BigDecimal.ZERO)).compareTo((BigDecimal)r2.getValue(VPOS_STD.einzelpreis,BigDecimal.ZERO));});
				Comparator<Rec21> sortDownPreis = ((r1,r2)-> {return ((BigDecimal)r2.getValue(VPOS_STD.einzelpreis,BigDecimal.ZERO)).compareTo((BigDecimal)r1.getValue(VPOS_STD.einzelpreis,BigDecimal.ZERO));});
				Comparator<Rec21> sortUpGueltig =   ((r1,r2)-> {
									try {
										return new Rec21_VPosStd(r1).getGueltigkeit().compareTo( new Rec21_VPosStd(r2).getGueltigkeit());
									} catch (myException e) {
										e.printStackTrace();
										return "1".compareTo("1");
									}
								});
				Comparator<Rec21> sortDownGueltig =   ((r1,r2)-> {
								try {
									return new Rec21_VPosStd(r2).getGueltigkeit().compareTo( new Rec21_VPosStd(r1).getGueltigkeit());
								} catch (myException e) {
									e.printStackTrace();
									return "1".compareTo("1");
								}
							});
				
				Comparator<Rec21> sortUpID =   ((r1,r2)-> {return ((BigDecimal)r1.getValue(VPOS_STD.id_vpos_std,BigDecimal.ZERO)).compareTo((BigDecimal)r2.getValue(VPOS_STD.id_vpos_std,BigDecimal.ZERO));});
				Comparator<Rec21> sortDownID = ((r1,r2)-> {return ((BigDecimal)r2.getValue(VPOS_STD.id_vpos_std,BigDecimal.ZERO)).compareTo((BigDecimal)r1.getValue(VPOS_STD.id_vpos_std,BigDecimal.ZERO));});
				
				
				this._addHeadLineComponent(new SortButton(sortUpAnr1,sortDownAnr1)._t("Anr1")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpAnr2,sortDownAnr2)._t("Anr2")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpArtbez,sortDownArtbez)._t("Artbez1")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpPreis,sortDownPreis)._t("Preis")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpGueltig,sortDownGueltig)._t("gültig")._fsa(-2))	 
					._addHeadLineComponent(new SortButton(sortUpID,sortDownID)._t("ID")._fsa(-2))	
					;

			}
		}
	}
	

	
}
