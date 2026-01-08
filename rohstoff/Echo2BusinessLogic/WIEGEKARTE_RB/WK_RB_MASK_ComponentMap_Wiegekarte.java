 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
   
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_HORIZONTAL;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_WaegungPos;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_ZustandWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre.WK_RB_CHILD_MGE_ABZ_MASK_DaughterListForMotherMask;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde.WK_RB_CHILD_ABZUG_GEB_MASK_DaughterListForMotherMask;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Fuhre;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Comp_Waegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_ContainerAbsetzGrund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen_Trailer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Adresse;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_ArtikelBezHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Search_Container;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_ArtikelBezKunde;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_Drucktyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WaageUser;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Waagedisplay;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_WeWa;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_bt_Print;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Spedition_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Fremdcontainer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzAbsetz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzSchuett;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Mehrfachverwiegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_SorteHand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_StrahlungGeprueft;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_lbl_storno;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfEtiketten;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfHinweisAllgemein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfSortenHinweis;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_CopyWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_InteractiveSettings;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_PrintHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ValidateOnSave;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_WaageHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_ZustandWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
  
public class WK_RB_MASK_ComponentMap_Wiegekarte extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
	WK_RB_MC_WaageHandling oMaskControllerWaageHandling = null;

	
	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WK_RB_MASK_ComponentMap_Wiegekarte(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		
		this.m_tpHashMap = p_tpHashMap;        

	    	// Felder nur zum Anzeigen, im Hintergrund
		this.registerComponent(WIEGEKARTE.id_wiegekarte,    		new RB_lab());
		this.registerComponent(WIEGEKARTE.typ_wiegekarte,    		new WK_RB_SelField_WiegekartenTyp()
												._addChangeListener((o)->{
													MyE2_MessageVector mv = bibMSG.getNewMV();
													new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
													._setzeStatusAdresseAbnehmerStrecke(mv)
													._setzeWerteBeiLagerverwiegung(mv)
													._setzeStatusGueterkategorie(mv)
													._setzeWerteBeiFremdverwiegung(mv)
													;
													return mv;
										})
										._width(400) );
		
		
		
		this.registerComponent(WIEGEKARTE.id_waage_standort,    	new RB_lab());
		this.registerComponent(WIEGEKARTE.id_adresse_lager,    		new RB_lab());
		this.registerComponent(WIEGEKARTE.druckzaehler,    			new RB_lab());
		this.registerComponent(WIEGEKARTE.druckzaehler_eingangsschein	, new RB_lab() );
		this.registerComponent(WIEGEKARTE.gedruckt_am,    			new RB_lab());
		this.registerComponent(WIEGEKARTE.id_wiegekarte_parent, 	new RB_lab());
		this.registerComponent(WIEGEKARTE.ist_gesamtverwiegung, 	new RB_cb());
		this.registerComponent(WIEGEKARTE.strahlung_geprueft,   	new WK_RB_cb_StrahlungGeprueft(new MyE2_String("Auf Radioaktivität geprüft"))._width(200));
		//this.registerComponent(WIEGEKARTE.storno,    				new RB_cb());
		this.registerComponent(WIEGEKARTE.storno,    				new WK_RB_lbl_storno());
		
		
		
		this.registerComponent (WK_RB_cb_Mehrfachverwiegung.key	 , new WK_RB_cb_Mehrfachverwiegung() );
		
		this.registerComponent(WIEGEKARTE.lfd_nr,    				new RB_lab()._b()._fo_s_plus(3));
		this.registerComponent(WIEGEKARTE.es_nr,    				new RB_lab());
		
		
		// 
		// Fuhre
		//
		this.registerComponent(WIEGEKARTE.id_vpos_tpa_fuhre,    	new RB_TextFieldReadOnly()._width(80));
		this.registerComponent(WIEGEKARTE.id_vpos_tpa_fuhre_ort,	new RB_TextFieldReadOnly()._width(80));
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key()	, new WK_RB_Comp_Fuhre( m_tpHashMap)
				._addChangeListener((o)-> {
					MyE2_MessageVector mv = bibMSG.getNewMV();
					mv._add(new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)._loadFuhre());
					return mv;
				}));
		
		
		//
		//  WE / WA
		//
		this.registerComponent(WIEGEKARTE.ist_lieferant,    		new WK_RB_WeWa()
				._addChangeListener((o)-> {
									MyE2_MessageVector mv = bibMSG.getNewMV();
									new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
																		._refreshKundenSorten(mv)
																		._setzeStatusGueterkategorie(mv)
																		._setzeStatusWaegung(mv)
																		;
									return mv;
								}));
		
		//
		// Allgemeiner Hinweis...
		//
		this.registerComponent(WK_RB_tfHinweisAllgemein.key,new WK_RB_tfHinweisAllgemein(600,2).setNeutralBorder(WK_RB_CONST.borderNeutral) );
		
		
		//
		//  LKW Kennzeichen
		//
		this.registerComponent(WIEGEKARTE.kennzeichen,   			new RB_TextField()._width(200)._fsa(3)._fo_bold().setNeutralBorder(WK_RB_CONST.borderNeutral));
		this.registerComponent(WIEGEKARTE.trailer,   				new RB_TextField()._width(200)._fsa(3)._fo_bold().setNeutralBorder(WK_RB_CONST.borderNeutral));
		this.registerComponent(WK_RB_Popup_Kennzeichen.key, 		new WK_RB_Popup_Kennzeichen().setComponentToSetValueIn(getRbComponent(WIEGEKARTE.kennzeichen))
																								 ._render()
																								._addChangeListener(((o)->{
																									MyE2_MessageVector mv = bibMSG.getNewMV();
																									new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)._setzeSpeditionWennEigenerLKW(mv);
																									return mv;
																								}
																								)));
		this.registerComponent(WK_RB_Popup_Kennzeichen_Trailer.key, 		new WK_RB_Popup_Kennzeichen_Trailer().setComponentToSetValueIn(getRbComponent(WIEGEKARTE.trailer))
				 ._render()
				._addChangeListener(((o)->{
					MyE2_MessageVector mv = bibMSG.getNewMV();
					new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)._setzeSpeditionWennEigenerLKW(mv);
					return mv;
				}
				)));		
		
		
		//
		//  Adresse Kunde
		//
		this.registerComponent(WIEGEKARTE.id_adresse_lieferant	, 	new WK_RB_Search_Adresse()._setFontSizeAdd(3)
									._addChangeListener((o)->{
										MyE2_MessageVector mv = bibMSG.getNewMV();
										new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
													._refreshKundenSorten(mv)
													._setzeSortenInfo()
													;
										return mv;
									})) ;
		this.registerComponent(WIEGEKARTE.adresse_lieferant		, 	new RB_TextArea()._width(500)._rows(2));
		this.registerComponent(WK_RB_cb_Adresse_Hand.key		, 	new WK_RB_cb_Adresse_Hand(new MyE2_String("Adresse Hand").CTrans())
								._addChangeListener((o)-> {
									MyE2_MessageVector mv = bibMSG.getNewMV();
									new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)	._setzeStatusKundenadresse(false,mv)
																											._refreshKundenSorten(mv)
																											._setzeSortenInfo();
									
									//if (mv.hasAlarms()) {	}
									return mv;
								}));
		
		
		//
		// Adresse Strecke
		//
		this.registerComponent(WIEGEKARTE.id_adresse_abn_strecke, 	new WK_RB_Search_Adresse() );
		

		//
		//  Adresse Spedition
		//
		this.registerComponent(WIEGEKARTE.adresse_spedition		, 	new RB_TextArea()._width(500)._rows(2).setNeutralBorder(WK_RB_CONST.borderNeutral));
		
		this.registerComponent(WIEGEKARTE.id_adresse_spedition	, 	new WK_RB_Search_Adresse());
		this.registerComponent(WK_RB_cb_Adresse_Spedition_Hand.key		, 	
									new WK_RB_cb_Adresse_Spedition_Hand(new MyE2_String("Spedition Zusatz").CTrans())
										._addChangeListener((o)-> {
												MyE2_MessageVector mv = bibMSG.getNewMV();
												new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)._setzeStatusSpeditionsadresse(false,mv);
												return mv;
									}));

		   
		//
		// Sorte
		//
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key(),	new WK_RB_SelField_ArtikelBezKunde()._width(505)
				._aaa(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, bibMSG.MV())._setzeSortenInfo();
					}
				}));
		
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key(),  	new WK_RB_Search_ArtikelBezHand(m_tpHashMap)
//										._addChangeListener((o)->{
//											MyE2_MessageVector mv = bibMSG.getNewMV();
//											new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
//														._setzeSortenInfo()
//														._setzeStatusSorte(false,mv)
//											;
//											return mv;	})
										);
		
		this.registerComponent(WIEGEKARTE.sorte_hand,    			new WK_RB_cb_SorteHand(new MyE2_String("Sorte Hand"))
										._addChangeListener((o)->{
											MyE2_MessageVector mv = bibMSG.getNewMV();
											new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
														._refreshKundenSorten(mv)
														._setzeSortenInfo()
														._setzeStatusSorte(false,mv)
											;
											return mv;	}));
		
		this.registerComponent(WK_RB_tfSortenHinweis.key,new WK_RB_tfSortenHinweis(500,3).setNeutralBorder(WK_RB_CONST.borderNeutral) );
		
		
		
		
		// bemerkung WK
		this.registerComponent(WIEGEKARTE.bemerkung1,    			new RB_TextArea()._width(500)._rows(2)._bord_black());
		this.registerComponent(WIEGEKARTE.bemerkung_intern,    		new RB_TextArea()._width(500)._rows(2)._bord_black());

		this.registerComponent(WIEGEKARTE.siegel_nr,    			new RB_TextField()._width(500)._bord_black());
		
		this.registerComponent(WIEGEKARTE.anz_allg,    				new RB_TextField()._width(100));
		this.registerComponent(WIEGEKARTE.anz_behaelter,    		new RB_TextField()._width(100));
		this.registerComponent(WIEGEKARTE.anz_bigbags,    			new RB_TextField()._width(100));
		this.registerComponent(WIEGEKARTE.anz_gitterboxen,    		new RB_TextField()._width(100));
		this.registerComponent(WIEGEKARTE.anz_paletten,    			new RB_TextField()._width(100));
		
		this.registerComponent(WIEGEKARTE.befund,    				new RB_TextField()._width(400));
		this.registerComponent(WIEGEKARTE.bemerkung2,    			new RB_TextArea()._width(500)._rows(2));
		this.registerComponent(WIEGEKARTE.bez_allg,    				new RB_TextArea()._width(500)._rows(2));
		
		this.registerComponent(WIEGEKARTE.id_container_eigen,   	new WK_RB_Search_Container(m_tpHashMap)
																					._registerAfterValueChangeEvents(()->{
																						bibMSG.MV()._addInfo("Container geändert... Prüfung erforderlich"  );
																						
																						}));
		this.registerComponent(WIEGEKARTE.container_nr,    			new RB_TextField()._width(200));
		this.registerComponent(WIEGEKARTE.container_tara,			new RB_TextField()._width(100));
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.CB_FREMDCONTAINER.key()	, new WK_RB_cb_Fremdcontainer("Fremdcontainer")
																						._addChangeListener((o)->{
																							MyE2_MessageVector mv = bibMSG.getNewMV();
																							new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)
																										._setzeStatusContainerNr(false, mv)
																							;
																							return mv;
																						}));
		
		
		
		this.registerComponent(WIEGEKARTE.nettogewicht,    			new RB_TextFieldReadOnly()._width(130)._col_back_alarm()._fsa(6)._fo_bold());
		this.registerComponent(WIEGEKARTE.gewicht_abzug,    		new RB_TextFieldReadOnly()._width(130)._col_back_base()._fsa(6)._fo_bold());
		this.registerComponent(WIEGEKARTE.gewicht_abzug_fuhre,  	new RB_TextFieldReadOnly()._width(130)._col_back_base()._fsa(6)._fo_bold());
		this.registerComponent(WIEGEKARTE.gewicht_nach_abzug,   	new RB_TextFieldReadOnly()._width(130)._col_back_base()._fsa(6)._fo_bold());
		this.registerComponent(WIEGEKARTE.gewicht_nach_abzug_fuhre, new RB_TextFieldReadOnly()._width(130)._col_back_base()._fsa(6)._fo_bold());
		this.registerComponent(WIEGEKARTE.grund_abzug,    			new RB_TextArea()._width(400)._rows(5));
		
		this.registerComponent (WIEGEKARTE.gueterkategorie, new WK_RB_Comp_Gueterkategorie(m_tpHashMap)._width(300));
	    
		this.registerComponent( WIEGEKARTE.id_lagerplatz_schuett, 	new WK_RB_Comp_Lagerplatz(m_tpHashMap, ENUM_Gueterkategorie.SCHUETTGUT));
		this.registerComponent(WK_RB_cb_LagerplatzSchuett.key		, 	new WK_RB_cb_LagerplatzSchuett(new MyE2_String("Entladen / Gekippt").CTrans())
				._addChangeListener((o)-> {
					MyE2_MessageVector mv = bibMSG.getNewMV();
					new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)	._setzeStatusLagerplaetze(o,false, mv);																						;
					return mv;
				}));

		
		
		this.registerComponent( WIEGEKARTE.id_lagerplatz_absetz, 	new WK_RB_Comp_Lagerplatz(m_tpHashMap, ENUM_Gueterkategorie.STUECKGUT));
		this.registerComponent(WK_RB_cb_LagerplatzAbsetz.key		, 	new WK_RB_cb_LagerplatzAbsetz(new MyE2_String("Einlagerung / Abgesetzt").CTrans())
				._addChangeListener((o)-> {
					MyE2_MessageVector mv = bibMSG.getNewMV();
					new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv)	._setzeStatusLagerplaetze(o,false,mv);
					return mv;
				}));

//		this.registerComponent(WIEGEKARTE.container_absetz_grund,		new RB_TextField()._width(500)._bord_black());
//		this.registerComponent(WK_RB_Popup_ContainerAbsetzGrund.key, 		new WK_RB_Popup_ContainerAbsetzGrund().
//				setComponentToSetValueIn(getRbComponent(WIEGEKARTE.container_absetz_grund))
//				.setKeyInList().setValueInComponent()
//				 ._render()
//				._addChangeListener(((o)->{
//					MyE2_MessageVector mv = bibMSG.getNewMV();
//					;
//					return mv;
//				}
//				)));
//		
		
		
		
		WK_RB_Comp_Waegung c1 =  new WK_RB_Comp_Waegung(1,m_tpHashMap);
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key() 	, c1._addListenerSave(new XX_ActionAgent() {
																							@Override
																							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																								WK_RB_MC_ValidateOnSave oMaskControllerValidateOnSafe = new WK_RB_MC_ValidateOnSave(WK_RB_MASK_ComponentMap_Wiegekarte.this,bibMSG.MV());
																								if (oMaskControllerValidateOnSafe._validateBEFORE_Zustand(ENUM_ZustandWiegekarte.WAEGUNG1).isOK()) {
																									oMaskControllerWaageHandling._createWaegungHandAndSave(ENUM_WaegungPos.WAEGUNG_1);
																								}
																							}})
																									
																						._addListenerTaraUebernehmen(new XX_ActionAgent() {
																							@Override
																							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																								oMaskControllerWaageHandling._readTara(ENUM_WaegungPos.WAEGUNG_1,bibMSG.MV());	
																								
																							}})
																						._addListenerStornoUebernehmen(new XX_ActionAgent() {
																							@Override
																							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																								oMaskControllerWaageHandling._CopyWaegungTara(ENUM_WaegungPos.WAEGUNG_1, bibMSG.MV());
																							}
																						})
																						);
		WK_RB_Comp_Waegung c2 =  new WK_RB_Comp_Waegung(2,m_tpHashMap);
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key()		, c2._addListenerSave(new XX_ActionAgent() {
																							@Override
																							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																								WK_RB_MC_ValidateOnSave oMaskControllerValidateOnSafe = new WK_RB_MC_ValidateOnSave(WK_RB_MASK_ComponentMap_Wiegekarte.this,bibMSG.MV());
																								if (oMaskControllerValidateOnSafe._validateBEFORE_Zustand(ENUM_ZustandWiegekarte.WAEGUNG2).isOK()) {
																									oMaskControllerWaageHandling._createWaegungHandAndSave(ENUM_WaegungPos.WAEGUNG_2);
																								}
																							}})
																						._addListenerTaraUebernehmen(new XX_ActionAgent() {
																							@Override
																							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																								oMaskControllerWaageHandling._readTara(ENUM_WaegungPos.WAEGUNG_2,bibMSG.MV());	
																								
																							}})
																							._addListenerStornoUebernehmen(new XX_ActionAgent() {
																									@Override
																									public void executeAgentCode(ExecINFO oExecInfo) throws myException {
																										oMaskControllerWaageHandling._CopyWaegungTara(ENUM_WaegungPos.WAEGUNG_2, bibMSG.MV());
																									}
																							})
																							);
																													
		
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key()	, new WK_RB_SelField_WaageUser(m_tpHashMap)._width(500)
				._aaa(new XX_ActionAgent() {
					
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						
						oMaskControllerWaageHandling.runTempWaegungen();
					}
				})
				
				);
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.COMP_WAAGELISTE.key(), 	new WK_RB_Waagedisplay(m_tpHashMap));
		
		// Child Abzug Gebinde
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_GEBINDE.key(), new WK_RB_CHILD_ABZUG_GEB_MASK_DaughterListForMotherMask(m_tpHashMap, true));
		
		// Child Abzug Menge
		this.registerComponent (WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_MENGE.key(), 	new WK_RB_CHILD_MGE_ABZ_MASK_DaughterListForMotherMask(m_tpHashMap, true));				
		

		// Button Folgewägung
		WK_RB_MASK_bt_FolgeWaegung btFolgewaegung = (WK_RB_MASK_bt_FolgeWaegung) new WK_RB_MASK_bt_FolgeWaegung(m_tpHashMap);
		btFolgewaegung._aaa(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				// generieren der Folgewiegekarte...
				WK_RB_MC_CopyWiegekarte mc_copy = new WK_RB_MC_CopyWiegekarte(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
				Long idwkFolge = mc_copy._createWKFolgewaegung( true);
				if (!mv.isOK() || idwkFolge == null) {
					bibMSG.MV()._add(mv);
				} else {
					// Neue ID in Klasse übernehmen, damit diese dann dortgeöffnet werden kann.
					btFolgewaegung._setIdFolgewiegekarte(idwkFolge);
				}
				 
			}},true);
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_FOLGEWAEGUNG.key() , btFolgewaegung);

		
		//
		// Buttons zum Drucken
		//
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key() 	, new WK_RB_SelField_Drucktyp());
		
//		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key()			, new E2_Button()._setShapeStandardTextButton()._image("printer.png", true)._txt_trans(new MyE2_String("Drucken").CTrans())
//																								._aaa(()->{
//																									MyE2_MessageVector mv = bibMSG.getNewMV();
//																									WK_RB_MC_PrintHandling mcPrint = new WK_RB_MC_PrintHandling(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
//																									mcPrint.printWiegekarte();
//																									bibMSG.add_MESSAGE(mv);
//																								}))
//																								;
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key()			, new WK_RB_bt_Print(this)
				._aaa(()->{
					MyE2_MessageVector mv = bibMSG.getNewMV();
					WK_RB_MC_PrintHandling mcPrint = new WK_RB_MC_PrintHandling(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
					mcPrint.printWiegekarte();
					bibMSG.add_MESSAGE(mv);
				}))
				;
		
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key()	, new E2_Button()._setShapeStandardTextButton()._image("printer.png", true)._txt_trans(new MyE2_String("Hofschein").CTrans())
																								._aaa((o)->{
																									MyE2_MessageVector mv = bibMSG.getNewMV();
																									WK_RB_MC_PrintHandling mcPrint = new WK_RB_MC_PrintHandling(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
																									mcPrint.printHofschein();
																									bibMSG.add_MESSAGE(mv);
																								}) );
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key()		, new WK_RB_tfEtiketten(50));
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_ETIKETT.key()		, new E2_Button()._setShapeStandardTextButton()._image("printer.png", true)._txt_trans(new MyE2_String("Etiketten").CTrans())
																								._aaa((o)->{
																									MyE2_MessageVector mv = bibMSG.getNewMV();
																									WK_RB_MC_PrintHandling mcPrint = new WK_RB_MC_PrintHandling(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
																									mcPrint.printEtiketten();
																									bibMSG.add_MESSAGE(mv);
																								}));
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BUERO.key()		, new E2_Button()._setShapeStandardTextButton()._image("printer.png", true)._txt_trans(new MyE2_String("Ausgang Büro").CTrans()));

		
		this.registerSetterValidators(WIEGEKARTE.id_wiegekarte,new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = bibMSG.newMV();

				WK_RB_MC_ZustandWiegekarte 		maskController_ZustandWiegekarte 	= new WK_RB_MC_ZustandWiegekarte(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
				WK_RB_MC_InteractiveSettings	maskController_InteractiveSettings 	= new WK_RB_MC_InteractiveSettings(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
				WK_RB_MC_ValidateOnSave 		maskController_ValidationOnSave		= new WK_RB_MC_ValidateOnSave(WK_RB_MASK_ComponentMap_Wiegekarte.this, mv);
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION ) {

					//
					// Falls eine Kopie geladen wird, die Felder anpassen
					//
					WK_RB_MC_CopyWiegekarte mc = new WK_RB_MC_CopyWiegekarte(rbMASK, mv);
					WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) mc.get_ComponentMapCollector().get(RecDOWiegekarte.key);
					if (compMap.getStatus().equals(MASK_STATUS.NEW_COPY)){
										mc._setValuesForCopyOfWK();
					} 
					
					
					//
					//  Gui-Settings setzen...
					//
					maskController_InteractiveSettings	._setzeStatusKundenadresse(true,mv)
										._setzeStatusSpeditionsadresse(true, mv)
										._refreshKundenSorten(mv)
										._setzeStatusSorte(true, mv)
										._setzeSortenInfo()
										._setzeStatusContainerNr(true, mv)
										._setzeStatus_StrahlungGeprueft(mv)
										._setzeStatusGueterkategorie(mv)
										._check_Fuhre_Sorte_Adressen(mv)
										._setzeStatusAdresseAbnehmerStrecke(mv)
										._setzeStatusLagerplaetze(null,true, mv)
										._setzeStatusKennzeichenFolgewaegung(mv)
										;
					
					maskController_ZustandWiegekarte._disable_by_ZustandWiegekarte();
					
					
				} 
				
				if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
					
					maskController_ValidationOnSave._validateBeforeSave() ;
					
					
				}
				
				if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
//					oMaskControllerWaageHandling.checkDataContentsForWaegung(mv);
					
//					maskControllerMain._check_Fuhre_Sorte_Adressen(mv);
					
				} 
				
				return mv;
			};
		});

		
		
    }
  
	
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
    	
    	WK_RB_Waagedisplay waagedisplay = (WK_RB_Waagedisplay) this.get__Comp(WK_RB_CONST.MASK_KEYS_String.COMP_WAAGELISTE.key().get_HASHKEY());
    	waagedisplay.refreshWaageData();
    	
    	WK_RB_SelField_WaageUser waageuser = (WK_RB_SelField_WaageUser) this.get__Comp(WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key().get_HASHKEY());
    	waageuser.refreshData();
    	
    	// Zuordnung des Lager zur Fuhren-Anzeige und der Speicher-Felder die in der Komponente gesetzt werden
    	WK_RB_Comp_Fuhre compFuhre = (WK_RB_Comp_Fuhre) this.get__Comp(WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key().get_HASHKEY());
    	compFuhre.setDataFields((RB_TextFieldReadOnly)	this.get__Comp(WIEGEKARTE.id_vpos_tpa_fuhre), (RB_TextFieldReadOnly)this.get__Comp(WIEGEKARTE.id_vpos_tpa_fuhre_ort));
    	compFuhre.initData();

        return null;
    }
    

    
    
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
    	// Mask-Controller instantiieren
    	oMaskControllerWaageHandling = new WK_RB_MC_WaageHandling(WK_RB_MASK_ComponentMap_Wiegekarte.this);
    	
    	return null;
    }
    
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    
    
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {

		// Anzahl Etiketten rechtsbündig...
		preSettingsContainer.rb_get(WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key()).set_AlignHorizontal(ALIGN_HORIZONTAL.RIGHT);
    
		
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        
        
        // Lager und Standort setzen, wenn Neuerfassung
        if (status.isStatusNew() ) {
			String id_lager = (String) m_tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_LAGER);
			preSettingsContainer.rb_get(WIEGEKARTE.id_adresse_lager).set_rb_Default(id_lager);
			
			String id_Standort = (String) m_tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_STANDORT);
			preSettingsContainer.rb_get(WIEGEKARTE.id_waage_standort).set_rb_Default(id_Standort);
			
			// default-Wert ist der Wiegeschein
//			WK_RB_SelField_WiegekartenTyp typ = (WK_RB_SelField_WiegekartenTyp) this.get__Comp(WIEGEKARTE.typ_wiegekarte);
//			typ._setActiveOrFirstDBVal(WK_RB_ENUM_WKTYP.WIEGESCHEIN.db_val());
			preSettingsContainer.rb_get(WIEGEKARTE.typ_wiegekarte).set_rb_Default(WK_RB_ENUM_WKTYP.WIEGESCHEIN.db_val());

			// default-Wert Radioaktivität geprüft
//			RB_cb rbRadioaktivitaet = (RB_cb) get__Comp(WIEGEKARTE.strahlung_geprueft);
//			rbRadioaktivitaet.setSelected(true);
			preSettingsContainer.rb_get(WIEGEKARTE.strahlung_geprueft).set_rb_Default("Y");

			// 
			//  Child-Listen disablen
			//
			preSettingsContainer.rb_get(WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_GEBINDE.key()).set_Enabled(false);
			preSettingsContainer.rb_get(WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_MENGE.key()).set_Enabled(false);
			
		}
        
        if (status.isStatusEdit()) {
        	WK_RB_WeWa rbwewa = (WK_RB_WeWa)this.get__Comp(WIEGEKARTE.ist_lieferant);
        	rbwewa.setEnabled(false);
        }

        
        return null;
     }
	
	
	


	
	
}
 
 
