package _Test;



import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.StringEncoderComparator;
import org.apache.commons.codec.language.Soundex;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New_V2;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_ImageCapture_Handler;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_PopUp_For_Display;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.__BASIC_MODULS.SESSIONMANAGEMENT.ModuleContainer_POPUP_SessionManagement;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue.TYPE_OF_INPUT;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_STATION;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_MARKER_COLOR;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeoPointsOnOSMLocal;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeoRouteOnOSMLocal;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Coordinate4Map;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Factory_URLForLocations;
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory_4_FreeText;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory_4_OriginalDownload;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_LIST_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossing_Record;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossing_RecordList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung_Batch;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler_KALKULATORISCH;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal_EXT;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;
import rohstoff.Echo2BusinessLogic.INTRASTAT.XML.INSTAT;
import rohstoff.Echo2BusinessLogic.INTRASTAT.XML.Instat_XML_Handler;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_DataRowStatus;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_Reorganize;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_SearchDialogFuhre;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_SearchDialogWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_LIST_WiegekartenHandler;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.E2_PopUp_For_LookupValue;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.E2_PopUp_For_TextBausteine;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_DialogGetStornoTara;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_InteractiveSettings;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintBase;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintEingangLieferschein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintEtikett;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintHofschein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_ContainerZyklus;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_ContainerZyklus;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.WK_RB_FuhrenInfo;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.WK_RB_RECORD_FuhrenInfo;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_Dialog_Copy_Laufzettel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_User;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList22_Archivmedien;
import rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez.BG_Kostenberechnung;
import rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez.BG_Kostenberechnung_Batch;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.Bewegung_Conversion_handler;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.test_convert;



public class TESTMODUL_MANFRED extends E2_BasicModuleContainer {

	
	
	private E2_Grid  button_grid = new E2_Grid()._setSize(200,200)._s(5);
	private E2_Button btnStartSearch ;
	private MyE2_TextField tfResult  = new MyE2_TextField();
	
	public TESTMODUL_MANFRED() throws myException {
		super();
		
		
		
		this.add(button_grid);
		
		button_grid._a(btnStartSearchFuhre());
		button_grid._a(btnStartSearchWiegekarte());
		button_grid._endLine(null);
		
		button_grid._a(tfResult);
		
		E2_TF_4_Date tfd = new E2_TF_4_Date("20.01.2017", 100, true, true);
		tfd.setSelectionMode(E2_TF_4_Date_Enum.SLIP_SELECTION_MODE);
		this.add(tfd);
		
		this.add(gridCopyLaufzettel());
		
		this.add(new testUmsetzungBewegungssatzRow());
		this.add(new testSetzkastenAtomRow());
		this.add(new testSetzkastenAtomRow_KALKULATORISCH());
		this.add(new testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel());
		
		// test mit den ja/nein/egal-Buttons
//		this.addButtonsTest();
	
		// Test eines NEU-Dialogs für die Container 		
		this.add(new rowContainerNew());
		
		
	
		// neue Kostenberechnung der BG-Atome
		// alte Kostenberechnung (BEWEGUNG)
		this.add(new MyE2_Label("BGAtom - KOSTENRECHNUNG"));
		this.add(new testKostenberechnung_BG());
		this.add(new testKostenberechnung_BG_Batch());
		
		
		
		// alte Kostenberechnung (BEWEGUNG)
		this.add(new MyE2_Label("<-----ALTE BEWEGUNG - KOSTENRECHNUNG"));
		this.add(new testAtomkostenBEWEGUNG());
		this.add(new testAtomkostenERMITTLUNG_BATCH());
		this.add(new testAtomkostenAdresse());
		this.add(new MyE2_Label("ALTE BEWEGUNG - KOSTENRECHNUNG----->"));
		
		
		this.add(new MyE2_Label("------------------------------- Bilder Capture ----------------------------------------------------------"));
		this.add(new testeBilderanzeige());
		this.add(new testeBilderCapture());
		this.add(new testeBilderCaptureAutomatik());
		
		this.add(new testBordercrossing_artikel());
		
		this.add(new testBordercrossing_check());
		
		
		this.add(new testSequencerReorg());
		this.add(new testReorgLager());
		this.add(new testSessionInfo());
		
		this.add(new testGeoCoordinatenOSMServer());
		this.add(new testRouteOSMServer());
		this.add(new testStringCombinationen() );
		this.add(new testStringCompareCommonsCodex());
		
		this.add(new test_RecList21_User());
		
		this.add(new test_fakturierungsfrist());
		
		this.add(new test_Convert_to_BG2_Structure());
		
		// test der neuen ATOM-Struktur-Umsetzung
		this.add(new testUmsetzungBewegungssatzNEW());

		this.add(new test_Rec21Wiegekarte());
		
		
		String sqlLKWs = " SELECT M.ID_MASCHINEN, UPPER( REPLACE(M.KFZKENNZEICHEN,'-',' ') ) "
				+ " FROM  "
				+ bibE2.cTO()
				+ ".JT_MASCHINENTYP  T INNER JOIN "
				+ bibE2.cTO()
				+ ".JT_MASCHINEN M  ON   ( T.ID_MASCHINENTYP = M.ID_MASCHINENTYP    ) "
				+ " WHERE     NVL(T.IST_LKW ,'N') = 'Y' AND NVL(M.AKTIV,'N') = 'Y' ORDER BY 1  "
				+ " ";
		
		this.add(new E2_PopUp_For_LookupValue()
				.setInfoWhenEmpty("NIX DA")
				.setKeyInComponent()
				.setSQL(sqlLKWs)
				.setIconActive(E2_ResourceIcon.get_RI("abgang_ins_universum.png"))
			
				.addElementAtBottomOfList(new MyE2_Button("TESTBUTTON"))._render());
		
		this.add(new E2_PopUp_For_TextBausteine("KENNER_TEXT_HANDWIEGUNG")
				.setInfoWhenEmpty("auch nix da")
				.setValueInComponent()
				.setKeyInList()
				.setTooltipText("tooltip für kenner handwiegung")
				._render() )
				;
		
		this.add( new test_Get_Fuhreninfo() );
		this.add(new test_ES_NR_Erzeugen() );
		this.add(new test_WK_PRINT());
		this.add(new  testINSTAT_XML() );
		this.add(new  testINSTAT_XML_DOWNLOAD());
		this.add(new test_LagerplatzHierarchie());
		this.add(new testInputDialog());
		this.add(new TestAnhangGeneratorTemp());
		this.add(new TestOffenePostenKunde());
		this.add(new TestContainerZyklusListe());
		this.add(new TestKorrekturwiegekarte());
		this.add(new TestDialogGetValuesExtended());
		
		
		
	}

	
	private void addButtonsTest(){
		this.add(new BTN_JaNeinEgal());
		this.add(new BTN_JaNeinEgal_EXT(true,"Hallo")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, "Ja")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Nein")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL, "J/N")
				.setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL)
				.setColLayoutHeading(new RB_gld()._col(Color.RED)._center_bottom())
				.setFontHeading(new E2_FontBold(-2))
				.setCheckboxStyle(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER())
				.setCheckboxStyle(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, MyE2_CheckBox.STYLE_BIG_BOLD())
				.setCheckboxStyle(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS())
				
				);

		this.add(new BTN_JaNeinEgal_EXT(false)
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, "Ja")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Nein")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL, "Egal")
				.setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL)
				.setButtonFont(null,new E2_FontPlain(+2))
				.setCheckboxStyle(null, MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS())
				);
		
		this.add(new BTN_JaNeinEgal_EXT(true)
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, "Ja")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Nein")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL, "Egal")
				.setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL)
				.setCheckboxStyle(null, MyE2_CheckBox.STYLE_BIG_BOLD())
				);
		this.add(new BTN_JaNeinEgal_EXT(false)
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, "Ja")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Nein")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL, "Egal")
				.setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));
		
		this.add(new BTN_JaNeinEgal_EXT(true,"Mit Deckel")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA, "Mit Deckel")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Ohne Deckel")
				.setCaption(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL, "Egal")
				.setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.JA)
				.setColWidth(null,300)
				.setColWidthHeading(800)
				
				.setBorderObject(new Border(new Extent(1), Color.RED, Border.STYLE_DOTTED))
				
				)
		;

		this.add(new BTN_JaNeinEgal_EXT(true).setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));
		this.add(new BTN_JaNeinEgal_EXT(true).setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));
		this.add(new BTN_JaNeinEgal_EXT(true).setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));
		this.add(new BTN_JaNeinEgal_EXT(true).setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));
		this.add(new BTN_JaNeinEgal_EXT(true).setZustand(BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL.EGAL));

		this.add(new BTN_JaNeinEgal()
				.setHeading("Teste mal den Button")
				.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, "Mit Deckel")
				.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Ohne Deckel")
				.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "Egal")
				.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA)
				.setColWidth(null,300)
				.setColWidthHeading(800)
				
				.setBorderObject(new Border(new Extent(4), new E2_ColorBase(-20), Border.STYLE_DASHED))

				)
		;
	}
	
	
	private class actionSearchResult extends XX_ActionAgent{
		/**
		 * @author manfred
		 * @date 14.08.2017
		 *
		 */
		private SearchDialog_Base oDialog = null;
		public actionSearchResult(SearchDialog_Base oDialogCalling) {
			oDialog = oDialogCalling;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				tfResult.setText(oDialog.getResultValue());
		}
	}
	
	
	private E2_Button btnStartSearchFuhre(){
		E2_Button btnStartSearch = new E2_Button()._t("Starte Suche Fuhre")._bord_black();
		btnStartSearch.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WK_SearchDialogFuhre search = new WK_SearchDialogFuhre("5525");
				search.set_iMaxResults(500);
//				search.getSelections_list().setSearchValue(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.WEWA.name(), "WA");
//				search.getSelections_list().setSearchValue(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.NAME.name(), "panter");
				// oder 
//				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.NAME.name()).set_Value("panter").set_Active(true);
//				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.WEWA.name()).set_Value("WE").set_Active(true);
//				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.KENNZEICHEN.name()).set_Active(true).set_Value("OG L  ");
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.DATUM_AB.name()).set_Value("2015-12-30"); //set_Active(true).
				
				search.setAgentAfterFound(new actionSearchResult(search));

				// Anzeigen des Dialogs
				search.show();
				
			}
		});
		
		return btnStartSearch;
	}
	
	
	
	private E2_Button btnStartSearchWiegekarte(){
		
		E2_Button btnStartSearch =new E2_Button()._t("Starte Suche Wiegekarte")._bord_black();
		btnStartSearch.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				WK_SearchDialogWiegekarte search = new WK_SearchDialogWiegekarte();
				search.set_iMaxResults(500);
//				search.getSelections_list().setSearchValue(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.WEWA.name(), "WA");
//				search.getSelections_list().setSearchValue(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.NAME.name(), "panter");
				// oder 
				search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.KUNDE.name()).set_Value("panter").set_Active(true);
				search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.WEWA.name()).set_Value("WE").set_Active(true);
				search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.KENNZEICHEN.name()).set_Active(true).set_Value("OG");
			
				search.setAgentAfterFound(new actionSearchResult(search));

				// Anzeigen des Dialogs
				search.show();
				
			}
		});
		
		return btnStartSearch;
	}
	
	
	private E2_Grid gridCopyLaufzettel(){
		E2_Grid gd = new E2_Grid()._s(3)._bo_dd();
		
		MyE2_TextField tfID = new MyE2_TextField();
		MyE2_TextField tfDatum = new MyE2_TextField();
		E2_Button btnStartCopy = new E2_Button()._t("Kopiere Laufzettel")._bord_black();
		
		gd._a("ID Laufzettel")._a("Neues Fälligkeitsdatum")._a("");
		gd.add(tfID);
		gd.add(tfDatum);
		gd.add(btnStartCopy);
		
		btnStartCopy.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
//				WF_Handler wf =new WF_Handler();
//				wf.copyWorkflow(tfID.getText(), tfDatum.getText());
				
				WF_Dialog_Copy_Laufzettel wfDialog_Copy_Laufzettel = new WF_Dialog_Copy_Laufzettel(tfID.getText(), null);
				
			}
		});
		
		return gd;
	}
	


	private class testUmsetzungBewegungssatzRow extends MyE2_Row{


		MyE2_CheckBox cbZeitraum = new MyE2_CheckBox("Zeitraum");
		MyE2_CheckBox cbFuhre = new MyE2_CheckBox("Fuhre");
		MyE2_TextField tfFuhre = new MyE2_TextField();

		MyE2_TextField tfFuhreVon = new MyE2_TextField();
		MyE2_TextField tfFuhreBis = new MyE2_TextField();

		MyE2_CheckBox cbWE = new MyE2_CheckBox("WE");
		MyE2_CheckBox cbWA = new MyE2_CheckBox("WA");
		MyE2_CheckBox cbS = new MyE2_CheckBox("S");
		MyE2_CheckBox cbLL = new MyE2_CheckBox("LL");

		MyE2_CheckBox cbMIXED = new MyE2_CheckBox("MIXED");
		MyE2_CheckBox cbHAND = new MyE2_CheckBox("HAND");

		ownTF4Datum   dateStart = null;
		ownTF4Datum   dateEnd = null;
		MyE2_Button   btnTest = new MyE2_Button("Konvertierung starten");

		public testUmsetzungBewegungssatzRow() throws myException{
			super();

			dateStart = new ownTF4Datum("01.01.2012", true);
			dateEnd = new ownTF4Datum("10.01.2012", true);

			tfFuhreVon.setText("");
			tfFuhreBis.setText("");

			tfFuhre.setText("");
			tfFuhre.set_bEnabled_For_Edit(false);

			cbZeitraum.setSelected(true);
			cbFuhre.setSelected(false);
			cbWE.setSelected(true);
			cbWA.setSelected(true);
			cbS.setSelected(true);
			cbLL.setSelected(true);
			cbMIXED.setSelected(true);
			cbHAND.setSelected(true);

			this.add(new MyE2_Label("Umsetzung Bewegungssatz aus Fuhren: "));
			this.add(cbZeitraum);
			this.add(dateStart);
			this.add(dateEnd);
			this.add(cbFuhre, E2_INSETS.I_10_0_0_0);
			this.add(tfFuhre);

			this.add(new MyE2_Label("ID von-bis:"), E2_INSETS.I_10_0_0_0);
			this.add(tfFuhreVon, E2_INSETS.I_0_0_0_0);
			this.add(tfFuhreBis, E2_INSETS.I_0_0_0_0);

			this.add(cbWE, E2_INSETS.I_10_0_0_0);
			this.add(cbWA);
			this.add(cbS);
			this.add(cbLL);
			this.add(cbMIXED);
			this.add(cbHAND);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);



			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testUmsetzungBewegungssatzRow oThis = testUmsetzungBewegungssatzRow.this;

					BL_BEWEGUNG_HANDLER oHandler = new BL_BEWEGUNG_HANDLER();
					String dateStart = oThis.dateStart.get_oDBFormatedDateFromTextField();
					String dateEnd = oThis.dateEnd.get_oDBFormatedDateFromTextField();
					oHandler.test( 
							(oThis.cbFuhre.isSelected() ?  oThis.tfFuhre.getText() : ""),
							oThis.tfFuhreVon.getText(),
							oThis.tfFuhreBis.getText(),
							dateStart,
							dateEnd,
							oThis.cbWE.isSelected(),
							oThis.cbWA.isSelected(),
							oThis.cbS.isSelected(),
							oThis.cbMIXED.isSelected(),
							oThis.cbLL.isSelected(),
							oThis.cbHAND.isSelected());
				}
			});


			cbZeitraum.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					testUmsetzungBewegungssatzRow oThis = testUmsetzungBewegungssatzRow.this;
					oThis.dateStart.set_bEnabled_For_Edit(oThis.cbZeitraum.isSelected());
					oThis.dateEnd.set_bEnabled_For_Edit(oThis.cbZeitraum.isSelected());
				}
			});


			cbFuhre.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					testUmsetzungBewegungssatzRow oThis = testUmsetzungBewegungssatzRow.this;

					oThis.cbHAND.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbWA.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbWE.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbS.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbLL.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbMIXED.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());
					oThis.cbZeitraum.set_bEnabled_For_Edit(!oThis.cbFuhre.isSelected());

					oThis.tfFuhre.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());

				}
			});

		}



		private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
		{
			public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
			{
				super(cStartWert, 100);
				this.set_bEnabled_For_Edit(bEnabled4Edit);

			}
		}


	}


	private class testSetzkastenAtomRow extends MyE2_Row{


		ownTF4Datum   dateStart = null;
		MyE2_Button   btnTest = new MyE2_Button("Verbuchung bis zum Zeitpunkt");

		public testSetzkastenAtomRow() throws myException{
			super();

			dateStart = new ownTF4Datum("01.01.2011", true);

			this.add(new MyE2_Label("Test Bewegungsatom-Setzkasten: "));
			this.add(dateStart);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testSetzkastenAtomRow oThis = testSetzkastenAtomRow.this;

					ATOM_SetzkastenHandler oHandler = new ATOM_SetzkastenHandler(null);

					String dateStart = oThis.dateStart.get_oDBFormatedDateFromTextField();

					oHandler.ReorganiseLagerEntries(bibALL.get_ID_MANDANT(), dateStart,true);

				}
			});

		}
	}


	private class testSetzkastenAtomRow_KALKULATORISCH extends MyE2_Row{


		ownTF4Datum   dateStart = null;
		MyE2_Button   btnTest = new MyE2_Button("Verbuchung bis zum Zeitpunkt");

		public testSetzkastenAtomRow_KALKULATORISCH() throws myException{
			super();



			dateStart = new ownTF4Datum("01.01.2011", true);

			this.add(new MyE2_Label("Test Bewegungsatom-Setzkasten KALKULATORISCH: "));
			this.add(dateStart);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{	
					GregorianCalendar calBegin = new GregorianCalendar();
					calBegin = new GregorianCalendar();



					testSetzkastenAtomRow_KALKULATORISCH oThis = testSetzkastenAtomRow_KALKULATORISCH.this;
					ATOM_SetzkastenHandler_KALKULATORISCH oHandler = new ATOM_SetzkastenHandler_KALKULATORISCH(null);
					String dateStart = oThis.dateStart.get_oDBFormatedDateFromTextField();
					oHandler.ReorganiseLagerEntries(bibALL.get_ID_MANDANT(), dateStart,true);


					GregorianCalendar calEnd = new  GregorianCalendar();
					long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
					DEBUG.System_println("Einträge verarbeitet " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);

				}
			});

		}


	}


	private class testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel extends MyE2_Row{


		ownTF4Datum   dateStart = null;
		MyE2_TextField tfIDADresse = null;
		MyE2_TextField tfIDArtikel = null;
		MyE2_CheckBox cbKalkulatorisch = null;
		MyE2_Button   btnTest = new MyE2_Button("Verbuchung bis zum Zeitpunkt");

		public testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel() throws myException{
			super();



			dateStart = new ownTF4Datum("01.01.2011", true);
			tfIDADresse = new MyE2_TextField("5525", 50, 10);
			tfIDArtikel = new MyE2_TextField("148", 50, 10);
			cbKalkulatorisch = new MyE2_CheckBox("Kalkulatorischer Setzkasten");
			cbKalkulatorisch.setSelected(true);


			this.add(new MyE2_Label("Test Bewegungsatom-Setzkasten KALKULATORISCH: ID-Lager:"));
			this.add(tfIDADresse);
			this.add(new MyE2_Label(" ID-Artikel:"));
			this.add(tfIDArtikel);
			this.add(cbKalkulatorisch);
			this.add(dateStart);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{	
					GregorianCalendar calBegin = new GregorianCalendar();
					calBegin = new GregorianCalendar();



					testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel oThis = testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel.this;
					ATOM_SetzkastenHandler oHandler = null;
					if (oThis.cbKalkulatorisch.isSelected()){
						oHandler = new ATOM_SetzkastenHandler_KALKULATORISCH(null);
					} else {
						oHandler = new ATOM_SetzkastenHandler(null);
					}

					String dateBisZu = oThis.dateStart.get_oDBFormatedDateFromTextField();
					String sIDAdresse = oThis.tfIDADresse.getText();
					String sIDArtikel = oThis.tfIDArtikel.getText();

					oHandler.ReorganiseLagerEntries( sIDAdresse, sIDArtikel, dateBisZu ,"2009-01-01");



					GregorianCalendar calEnd = new  GregorianCalendar();
					long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
					DEBUG.System_println("Einträge verarbeitet " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);

				}
			});

		}
	}







	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100);
			this.set_bEnabled_For_Edit(bEnabled4Edit);

		}
	}

	
	
	private class rowContainerNew extends MyE2_Row{


		ownTF4Datum   dateStart = null;
		E2_Button   btnTest = new bt_NewContainer();

		public rowContainerNew() throws myException{
			super();

			this.add(btnTest,E2_INSETS.I_10_0_0_0);

		}
	}
	
	
	private class bt_NewContainer extends RB_bt_New_V2 {
	    
	    public bt_NewContainer() {
	        super();
	        setText("Container-Datensatz mit RBV-Komponente anlegen");
	        this.set_Text4MaskTitle(S.ms("Container neu anlegen..."));
	        this.add_GlobalValidator(ENUM_VALIDATION.CONTAINER_NEW.getValidator());
	    }
	    
	    
	    @Override
	    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
	    	RB_ModuleContainerMASK container = new CON_MASK_MaskModulContainer();
	    	return container;
	    }
	    
	    
	    @Override
	    public RB_DataobjectsCollector generate_DataObjects4New()    throws myException {
	        return new CON_MASK_DataObjectCollector();
	    }
	    
	    
	    @Override
	    public void define_Actions_4_saveButton(RB_bt_New_V2 btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
	        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
	        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	    }
	 
	    
	    @Override
	    public void define_Actions_4_CloseButton(RB_bt_New_V2 btNewInList,    RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup)    throws myException {
	        bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	    }
	    
	}


	
	/**
	 * NEUEN ATOMSATZ Testen
	 * @author manfred
	 * @date 26.01.2018
	 *
	 */
	private class testUmsetzungBewegungssatzNEW extends MyE2_Row{


		MyE2_CheckBox cbZeitraum = new MyE2_CheckBox("Zeitraum");
		ownTF4Datum   dateStart = null;
		ownTF4Datum   dateEnd = null;

		MyE2_CheckBox cbFuhre = new MyE2_CheckBox("Fuhren konvertieren");
		MyE2_TextField tfFuhre = new MyE2_TextField();

		MyE2_TextField tfFuhreVon = new MyE2_TextField();
		MyE2_TextField tfFuhreBis = new MyE2_TextField();

		MyE2_CheckBox cbWE = new MyE2_CheckBox("WE");
		MyE2_CheckBox cbWA = new MyE2_CheckBox("WA");
		MyE2_CheckBox cbS = new MyE2_CheckBox("S");
		MyE2_CheckBox cbLL = new MyE2_CheckBox("LL");
		MyE2_CheckBox cbMIXED = new MyE2_CheckBox("MIXED");


		MyE2_CheckBox cbHAND = new MyE2_CheckBox("Handbuchungen konvertieren");
		MyE2_TextField tfHand  = new MyE2_TextField();
		MyE2_TextField tfHandVon = new MyE2_TextField();
		MyE2_TextField tfHandBis = new MyE2_TextField();
		
		
		MyE2_Button   btnTest = new MyE2_Button("Konvertierung starten");
		
		
		public testUmsetzungBewegungssatzNEW() throws myException{
			super();

			cbZeitraum.setSelected(true);
			dateStart = new ownTF4Datum("01.01.2012", true);
			dateEnd = new ownTF4Datum("10.01.2012", true);

			tfFuhreVon.setText("");
			tfFuhreBis.setText("");

			cbFuhre.setSelected(true);
			tfFuhre.setText("");
			tfFuhre.set_bEnabled_For_Edit(true);
			
			cbHAND.setSelected(true);
			tfHand.setText("");
			tfHand.set_bEnabled_For_Edit(true);
			tfHandVon.setText("");
			tfHandBis.setText("");

			cbWE.setSelected(true);
			cbWA.setSelected(true);
			cbS.setSelected(true);
			cbLL.setSelected(true);
			cbMIXED.setSelected(true);

			this.add(new MyE2_Label("Umsetzung Bewegungssatz aus Fuhren/Hand: "));
			this.add(cbZeitraum);
			this.add(dateStart);
			this.add(dateEnd);
			
			this.add(cbFuhre, E2_INSETS.I_10_0_0_0);
			this.add(tfFuhre);
			this.add(new MyE2_Label("ID von-bis:"), E2_INSETS.I_10_0_0_0);
			this.add(tfFuhreVon, E2_INSETS.I_0_0_0_0);
			this.add(tfFuhreBis, E2_INSETS.I_0_0_0_0);
			this.add(cbWE, E2_INSETS.I_10_0_0_0);
			this.add(cbWA);
			this.add(cbS);
			this.add(cbLL);
			this.add(cbMIXED);
			
			
			this.add(new MyE2_Label("Handbuchung: "), E2_INSETS.I_10_0_0_0);
			this.add(cbHAND);
			
			this.add(new MyE2_Label("ID-Lager:"), E2_INSETS.I_10_0_0_0);
			this.add(tfHand);
			
			this.add(new MyE2_Label("ID-Lager von/bis"));
			this.add(tfHandVon, E2_INSETS.I_0_0_0_0);
			this.add(tfHandBis, E2_INSETS.I_0_0_0_0);

			this.add(btnTest,E2_INSETS.I_10_0_0_0);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testUmsetzungBewegungssatzNEW oThis = testUmsetzungBewegungssatzNEW.this;

					Bewegung_Conversion_handler oHandler = new Bewegung_Conversion_handler();
					
					String dateStart = oThis.dateStart.get_oDBFormatedDateFromTextField();
					String dateEnd = oThis.dateEnd.get_oDBFormatedDateFromTextField();
					oHandler.test(
							oThis.cbZeitraum.isSelected() ? dateStart : "",
							oThis.cbZeitraum.isSelected() ? dateEnd : "",
							oThis.cbFuhre.isSelected(),
							oThis.tfFuhre.getText(),
							oThis.tfFuhreVon.getText(),
							oThis.tfFuhreBis.getText(),
							oThis.cbWE.isSelected(),
							oThis.cbWA.isSelected(),
							oThis.cbS.isSelected(),
							oThis.cbMIXED.isSelected(),
							oThis.cbLL.isSelected(),
							oThis.cbHAND.isSelected(),
							(oThis.cbHAND.isSelected() ?  oThis.tfHand.getText() : ""),
							(oThis.cbHAND.isSelected() ?  oThis.tfHandVon.getText() : ""),
							(oThis.cbHAND.isSelected() ?  oThis.tfHandBis.getText() : "")
							);
					
//					oHandler.test(DateStart, DateEnd, bFuhre, IDFuhre, IDFuhreVon, IDFuhreBis, bWE, bWA, bS, bMixed, bLL, bHand, IDLager, IDLagerVon, IDLagerBis);
							
//					oHandler.test(  
//							(oThis.cbFuhre.isSelected() ?  oThis.tfFuhre.getText() : ""),
//							(oThis.cbFuhre.isSelected() ?  oThis.tfHand.getText() : ""),
//							oThis.tfFuhreVon.getText(),
//							oThis.tfFuhreBis.getText(),
//							!oThis.cbFuhre.isSelected() && oThis.cbZeitraum.isSelected() ? dateStart : "",
//							!oThis.cbFuhre.isSelected() && oThis.cbZeitraum.isSelected() ? dateEnd : "",
//							oThis.cbWE.isSelected(),
//							oThis.cbWA.isSelected(),
//							oThis.cbS.isSelected(),
//							oThis.cbMIXED.isSelected(),
//							oThis.cbLL.isSelected(),
//							oThis.cbHAND.isSelected(),
//							oThis.cbPrepared.isSelected());
				}
			});


			cbZeitraum.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					testUmsetzungBewegungssatzNEW oThis = testUmsetzungBewegungssatzNEW.this;
					oThis.dateStart.set_bEnabled_For_Edit(oThis.cbZeitraum.isSelected());
					oThis.dateEnd.set_bEnabled_For_Edit(oThis.cbZeitraum.isSelected());
				}
			});


			cbFuhre.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					testUmsetzungBewegungssatzNEW oThis = testUmsetzungBewegungssatzNEW.this;

					oThis.cbWA.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.cbWE.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.cbS.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.cbLL.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.cbMIXED.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());

					oThis.tfFuhre.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.tfFuhreVon.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					oThis.tfFuhreBis.set_bEnabled_For_Edit(oThis.cbFuhre.isSelected());
					
				}
			});
			
			
			cbHAND.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					testUmsetzungBewegungssatzNEW oThis = testUmsetzungBewegungssatzNEW.this;

					oThis.tfHand.set_bEnabled_For_Edit(oThis.cbHAND.isSelected());
					oThis.tfHandVon.set_bEnabled_For_Edit(oThis.cbHAND.isSelected());
					oThis.tfHandBis.set_bEnabled_For_Edit(oThis.cbHAND.isSelected());
					
				}
			});
			

		}



		private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
		{
			public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
			{
				super(cStartWert, 100);
				this.set_bEnabled_For_Edit(bEnabled4Edit);

			}
		}


	}



	/**
	 * Kostenberchnung auf basis von BG-Sätzen testen
	 * @author manfred
	 * @date 23.04.2018
	 *
	 */
	private class testKostenberechnung_BG extends MyE2_Row{

		BG_Kostenberechnung kostenberechnung = null;
		
		MyE2_Label 	   lblVektor = new MyE2_Label("ID-Vektor");
		MyE2_TextField tfVektor = new MyE2_TextField();
		MyE2_Label 	   lblAdresse = new MyE2_Label("ID-Adresse");
		MyE2_TextField tfIDAdresse  = new MyE2_TextField();
		MyE2_Button   btnTest = new MyE2_Button("Berechnen");
	
		public testKostenberechnung_BG() throws myException{
			super();
			
			tfVektor.setText("");
			tfIDAdresse.setText("");

			this.add(lblVektor);
			this.add(tfVektor);
			this.add(lblAdresse);
			this.add(tfIDAdresse);

			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					kostenberechnung = new BG_Kostenberechnung();
					String id =  "";
					
					if ( !bibALL.isEmpty(tfVektor.getText()) ){
						kostenberechnung.Erzeuge_Kostensaetze_Fuer_BGVektor(tfVektor.getText());
					}else if (!bibALL.isEmpty(tfIDAdresse.getText())) {
						kostenberechnung.ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(tfIDAdresse.getText());
					} else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message("VektorID oder AdressID muss angegeben werden."));
					}
				}
			});
		}

	}
	
	
	private class testKostenberechnung_BG_Batch extends MyE2_Row{

		BG_Kostenberechnung_Batch kostenberechnung = null;
		
		MyE2_Button   btnTest = new MyE2_Button("BG-Vektor Kosten Berechnung im Batch");
	
		public testKostenberechnung_BG_Batch() throws myException{
			super();
			
			
			this.add(new MyE2_Label("Batchberechnung aller Kosten aller BG-Vektoren"));
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					kostenberechnung = new BG_Kostenberechnung_Batch();
					kostenberechnung.runTask();
				}
			});
		}

	}
	
	
	
	
	// alte Funktionalität
	

	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testAtomkostenBEWEGUNG extends MyE2_Row{

		MyE2_TextField tfIDBewegung = new MyE2_TextField();
		MyE2_Button   btnTest = new MyE2_Button("Kosten berechnen für ID_BEWEGUNG");
		public testAtomkostenBEWEGUNG() throws myException{
			super();

			this.add(new MyE2_Label("Test Bewegung-Kosten (ID-Bewegung): "));
			this.add(tfIDBewegung);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testAtomkostenBEWEGUNG oThis = testAtomkostenBEWEGUNG.this;
					BL_Kostenberechnung  oTest = new BL_Kostenberechnung();
					oTest.ErzeugeSQL_Kostensaetze_Fuer_Bewegung(tfIDBewegung.getText().trim());
					oTest.executeSqlStatements(true);
				}
			});

		}
	}


	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testAtomkostenAdresse extends MyE2_Row{

		MyE2_TextField tfIDAdresse = new MyE2_TextField();
		MyE2_Button   btnTest = new MyE2_Button("Kosten berechnen für ID_ADRESSE");
		public testAtomkostenAdresse() throws myException{
			super();

			this.add(new MyE2_Label("Test Bewegung-Kosten (ID-Adresse): "));
			this.add(tfIDAdresse);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testAtomkostenAdresse oThis = testAtomkostenAdresse.this;
					BL_Kostenberechnung  oTest = new BL_Kostenberechnung();
					oTest.ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(tfIDAdresse.getText().trim());
				}
			});

		}
	}



	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testAtomkostenERMITTLUNG_BATCH extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Alle Kosten aktualisieren");

		public testAtomkostenERMITTLUNG_BATCH() throws myException{
			super();

			this.add(new MyE2_Label("Komplettlauf: "));
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testAtomkostenERMITTLUNG_BATCH oThis = testAtomkostenERMITTLUNG_BATCH.this;

					BL_Kostenberechnung_Batch oTest = new BL_Kostenberechnung_Batch();
					oTest.runTask();


				}
			});

		}
	}

	
	
	

	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testeBilderanzeige extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Bilder anzeigen");

		public testeBilderanzeige() throws myException{
			super();

			this.add(new MyE2_Label("Anzeigen von Bildern im Dialog: "));
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					try
					{
						Vector<String> vBilder = new Vector<String>();
						Archiver oArchiv = new Archiver("");

						vBilder.add(oArchiv.get_cCompleteArchivePath() + "IMG_1.jpg");
						vBilder.add(oArchiv.get_cCompleteArchivePath() + "IMG_2.jpg");


						IMG_PopUp_For_Display oPopUp = new IMG_PopUp_For_Display(vBilder);


						oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Bilder anzeigen ..."));
					}
					catch (myException ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Image-Window: "));
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}


				}
			});
		}


	}

	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testeBilderCapture extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Bilder Aufnehmen zu Wiegekarte #24145 ");

		public testeBilderCapture() throws myException{
			super();
			IMG_ImageCapture_Handler oImgCapture = new  IMG_ImageCapture_Handler(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE);

			this.add(new MyE2_Label("Capture: (wiegekarte 24145) "));
			this.add(oImgCapture.get_ButtonRow("24145"));
			//			this.add(oImgCapture.get_ButtonForSnapshot("24145"),E2_INSETS.I_10_0_0_0);
			//			this.add(oImgCapture.get_ButtonForImageDisplay("24145"),E2_INSETS.I_10_0_0_0);

		}


	}
	

	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testeBilderCaptureAutomatik extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Simulation Automatische Bilderaufnahme");
		MyE2_TextField tfWaageSettings = new MyE2_TextField("1005", 50, 20);
		MyE2_TextField tfIDWiegekarte = new MyE2_TextField("69293", 50, 20);
		
		public testeBilderCaptureAutomatik() throws myException{
			super();
			btnTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					IMG_ImageCapture_Handler oImgCapture = new  IMG_ImageCapture_Handler("CAPTURE_WAAGE_AUTOMATIK",tfWaageSettings.getText());
					oImgCapture.captureSnapshots(tfIDWiegekarte.getText());
				}
			});
			
			
			this.add(new MyE2_Label("ID Waage Settings"));
			this.add(tfWaageSettings);
			this.add(new MyE2_Label("ID Wiegekarte"));
			this.add(tfIDWiegekarte);
			
			this.add(btnTest);
			
		}


	}
	
	
	
	/**
	 * @author manfred
	 * @date 12.06.2018
	 *
	 */
	private class testBordercrossing_artikel extends MyE2_Row{
		MyE2_Button bTest;
		
		public testBordercrossing_artikel() {
			bTest = new MyE2_Button("Bordercrossing-Artikel");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					BOR_ART_LIST_BasicModuleContainer c = new BOR_ART_LIST_BasicModuleContainer(null);
					c.CREATE_AND_SHOW_POPUPWINDOW(new MyE2_String("Bordercrossing-Artikel"));
					
				}
			});
			
			this.add(bTest);
		}
	}
	
	private class testBordercrossing_check extends MyE2_Row{
		MyE2_Button bTest;
		
		public testBordercrossing_check() {
			bTest = new MyE2_Button("Bordercrossing-Check routinen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					BorderCrossing_RecordList rl = new BorderCrossing_RecordList();
					
					Vector<BorderCrossing_Record> recList = rl.get_recList();
					
				}
			});
			
			this.add(bTest);
		}
	}
	
	
	private class testSequencerReorg extends MyE2_Row{
		MyE2_Button bTest;
		
		public testSequencerReorg() {
			bTest = new MyE2_Button("Sequencer REORG test");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					Project_TableSequenceBuilder 
					oSeq = new Project_TableSequenceBuilder(   _TAB.lager_konto.fullTableName(),	"REORG");
					bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
					oSeq = new Project_TableSequenceBuilder(   _TAB.lager_bewegung.fullTableName(),	"REORG");
					bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
					oSeq = new Project_TableSequenceBuilder(   _TAB.status_lager.fullTableName(),	"REORG");
					bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
					
				}
			});
			
			this.add(bTest);
		}
	}
	

	// sequencer der Tabellen neu setzen:
	private class testReorgLager extends MyE2_Row{
	MyE2_Button bTest;
	
		public testReorgLager() {
			bTest = new MyE2_Button("Lager neu aufbauen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					LAG_Reorganize reorg = new LAG_Reorganize();
					reorg.Reorganize_GesamtesLager_AufbauAusFuhren_WithProcessBar();
				}
			});
			this.add(bTest);
		}
	}
	
	
	private class testSessionInfo extends MyE2_Row{
		MyE2_Button bTest;
		
		public testSessionInfo() {
			bTest = new MyE2_Button("Sessions");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					// die sammler-hashmap aus dem servlet-context rausholen
					ServletContext ctx =  bibE2.get_CurrSession().getServletContext();
					Enumeration<String> attrib =  ctx.getAttributeNames();
					
//					for (Enumeration<E> e = v.elements(); e.hasMoreElements();)
//					       System.out.println(e.nextElement());
					
					for (Enumeration<String> attribs =  ctx.getAttributeNames(); attribs.hasMoreElements();){ 
						String s = attribs.nextElement();
						DEBUG.System_println(s, DEBUG_FLAGS.DIVERS1.name());
						
					}
					
					HashMap<String, WeakReference<HttpSession>> hmSessions = (HashMap<String, WeakReference<HttpSession>>) ctx.getAttribute("applications.users");
					
					
					
					
//					HashMap hmSessions = (HashMap)bibE2.get_CurrSession().getServletContext().getAttribute("applications.users");
					
					
					
					E2_BasicModuleContainer  oContainer_Ref = new ModuleContainer_POPUP_SessionManagement();
					
				}
			});
			this.add(bTest);
		}
		
		
	}

	
	private class testGeoCoordinatenOSMServer extends MyE2_Row{
		btGeoServerPoints bTest;
		MyE2_TextField tfIDAdresse = new MyE2_TextField("5525", 50, 20);
		
		public testGeoCoordinatenOSMServer() throws myException {
			bTest = new btGeoServerPoints();
			this.add(tfIDAdresse);
			this.add(bTest);
		}
		
		
		
		private class btGeoServerPoints extends GEO_BtShowGeoPointsOnOSMLocal{

			/**
			 * @author manfred
			 * @date 17.10.2018
			 *
			 * @throws myException
			 */
			public btGeoServerPoints() throws myException {
				super();
				
			}

			/* (non-Javadoc)
			 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnOSMLocal#getURLFactory()
			 */
			@Override
			public GEO_Factory_URLForLocations getURLFactory() throws myException {
				GEO_Factory_URLForLocations factory = new GEO_Factory_URLForLocations();
				
				
				Rec21_adresse r;
				try {
					r = new Rec21_adresse()._fill_id(tfIDAdresse.getText());
					if (r != null){
//						RecList21 rl  = r.getMainEmployeeAndStoreAdresses();
						
						RecList21 rl  = r.getMainStoreAdresses();
						
						for (Rec21 adr : rl){
							BigDecimal lat = adr.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
							BigDecimal lng = adr.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
							String text = adr.get_ufs_dbVal(ADRESSE.name1) + " " + adr.get_fs_dbVal(ADRESSE.name2,"");
							String id = adr.get_ufs_dbVal(ADRESSE.id_adresse);
							
							ENUM_GEO_MARKER_COLOR col = id.equals(tfIDAdresse.getText().trim()) 
									? ENUM_GEO_MARKER_COLOR.RED 
									: ENUM_GEO_MARKER_COLOR.YELLOW;
							
							if (lat == null || lng == null) continue;
							
							try{
								GEO_Coordinate4Map c = new GEO_Coordinate4Map(lat, lng, text, col);
								factory.addGeoCoordiante(c);
							} catch(myException e){
								// fehler beim der Koordinatenumwandlung
							}
						}
					}

				} catch (myException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return factory;
			}
			
		}
	}
	
	
	
	
	private class testRouteOSMServer extends MyE2_Row{
		btGeoServerPoints bTest;
		MyE2_TextField tfIDAdresse = new MyE2_TextField("5525", 50, 20);
		MyE2_TextField tfIDAdresse2 = new MyE2_TextField("3007", 50, 20);
		MyE2_TextField tfIDAdresse3 = new MyE2_TextField("21285", 50, 20);
		
		public testRouteOSMServer() throws myException {
			bTest = new btGeoServerPoints();
			this.add(tfIDAdresse);
			this.add(tfIDAdresse2);
			this.add(tfIDAdresse3);
			this.add(bTest);
		}
		
		
		/**
		 *	Button zur Darstellung der Route 
		 * @author manfred
		 * @date 18.10.2018
		 *
		 */
		private class btGeoServerPoints extends GEO_BtShowGeoRouteOnOSMLocal{

			/**
			 * @author manfred
			 * @date 17.10.2018
			 *
			 * @throws myException
			 */
			public btGeoServerPoints() throws myException {
				super();
				
			}

			/* (non-Javadoc)
			 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnOSMLocal#getURLFactory()
			 */
			@Override
			public GEO_Factory_URLForLocations getURLFactory() throws myException {
				GEO_Factory_URLForLocations factory = new GEO_Factory_URLForLocations();
				BigDecimal lat ;
				BigDecimal lng ;
				String text;
				ENUM_GEO_MARKER_COLOR col;
				Rec21_adresse r;
				GEO_Coordinate4Map c;

				try {
					if (S.isFull(tfIDAdresse.getText())){
						r = new Rec21_adresse()._fill_id(tfIDAdresse.getText());
						lat = r.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						lng = r.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						text = r.get_ufs_dbVal(ADRESSE.name1) + " " + r.get_fs_dbVal(ADRESSE.name2,"");
						col = ENUM_GEO_MARKER_COLOR.GREEN ;
						
						c = new GEO_Coordinate4Map(lat, lng, text.trim(), col);
						factory.addGeoCoordiante(c);
					}
					
					if (S.isFull(tfIDAdresse2.getText())){
						r = new Rec21_adresse()._fill_id(tfIDAdresse2.getText());
						lat = r.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						lng = r.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						text = r.get_ufs_dbVal(ADRESSE.name1) + " " + r.get_fs_dbVal(ADRESSE.name2,"");
						col = ENUM_GEO_MARKER_COLOR.YELLOW;
						
						c = new GEO_Coordinate4Map(lat, lng, text.trim(), col);
						factory.addGeoCoordiante(c);
					}
					
					if (S.isFull(tfIDAdresse3.getText())){
						r = new Rec21_adresse()._fill_id(tfIDAdresse3.getText());
						lat = r.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						lng = r.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						text = r.get_ufs_dbVal(ADRESSE.name1) + " " + r.get_fs_dbVal(ADRESSE.name2,"");
						col = ENUM_GEO_MARKER_COLOR.RED ;
						
						c = new GEO_Coordinate4Map(lat, lng, text.trim(), col);
						factory.addGeoCoordiante(c);
					}
			
				} catch (myException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return factory;
			}
			
		}
		
		
	}
	
	
	
	
	private class testStringCombinationen extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("a1 a2 a3", 50, 20);
		MyE2_TextField tftext2 = new MyE2_TextField("b1 b2", 50, 20);
		MyE2_TextField tftext3 = new MyE2_TextField("c3", 50, 20);
		
		
		MyE2_TextField tfErgebnis = new MyE2_TextField("",200,500);
		
		
		public testStringCombinationen() throws myException {
			bTest = new MyE2_Button("Combiniere");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String a = "";;
					String arr1[] = tftext1.getText().split(" ");  
					String arr2[] = tftext2.getText().split(" ");
					String arr3[] = tftext3.getText().split(" ");

					java.util.List<String> result = Arrays.stream(arr1)
					        .flatMap(s1 -> Arrays.stream(arr2)
					                .flatMap(s2 -> Arrays.stream(arr3)
					                        .map(s3 -> s1 + s2 + s3)))
					        				.collect(Collectors.toList());
					
					tfErgebnis.setText(result.toString());
				}
			});
			
			
			this.add(tftext1);
			this.add(tftext2);
			this.add(tftext3);
			this.add(bTest);
			this.add(tfErgebnis);
		}
		
		
	
	}

	
	
	private class testStringCompareCommonsCodex extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("a1 a2 a3", 100, 100);
		MyE2_TextField tftext2 = new MyE2_TextField("b1 b2", 100, 100);
		MyE2_TextField tftext3 = new MyE2_TextField("c3", 100, 100);
		
		
		MyE2_TextField tfErgebnis = new MyE2_TextField("",200,500);
		
		
		public testStringCompareCommonsCodex() throws myException {
			bTest = new MyE2_Button("Compare testen mit soundex");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					StringEncoderComparator sCompare = new StringEncoderComparator(new Soundex());
					if(sCompare.compare(tftext1.getText(), tftext2.getText()) == 0 ){
						tfErgebnis.setText("GLEICH");
					} else {
						tfErgebnis.setText("Unterschiedlich");
					}
					
					
				}
			});
			
			
			this.add(tftext1);
			this.add(tftext2);
			
			this.add(bTest);
			this.add(tfErgebnis);
		}
		
		
	
	}
	
	private class test_RecList21_User extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("", 500,500);
		
		
		public test_RecList21_User() throws myException {
			bTest = new MyE2_Button("RecListUserV21 testen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sTest = "";
					RecList21_User rl = new RecList21_User(true,false);
					
					sTest += (Integer.toString( rl.size() ) + " aktive Einträge...") ;
					rl = new RecList21_User(false,true);
					sTest += (Integer.toString( rl.size() ) + " inaktive Einträge...") ;
					rl = new RecList21_User(true,true);
					sTest += (Integer.toString( rl.size() ) + " Alle Einträge...") ;
					
					
					
					tftext1.setText(sTest) ;
					
				}
			});
			
			
			
			this.add(bTest);
			this.add(tftext1);
		}
		
		
	
	}
	
	

	
	private class test_fakturierungsfrist extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tfIDAdresse = new MyE2_TextField("", 50,50);
		MyE2_TextField tfTage = new MyE2_TextField("", 50,50);
		
		
		MyDate dt = new MyDate(new Date());
		ownTF4Datum tfdatum = new ownTF4Datum(dt.get_cDateStandardFormat(),true);
		
		
		public test_fakturierungsfrist() throws myException {
			bTest = new MyE2_Button("Fakturierungsfrist-Verlängerung testen: ");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					
					String sTage = "?";
					Rec21_adresse adr = new Rec21_adresse()._fill_id(tfIDAdresse.getText());
					String datum = tfdatum.get_oFormatedDateFromTextField();
					MyDate dt = new MyDate(datum);
					
					int i = adr.getMinimum_Fakturierungsfrist(dt.getDate());
					
					
					tfTage.setText(Integer.toString(i));
					
					// debug
					RecList21 rl = adr.getKreditversicherungen_Aktiv();
					int n = rl.size();
				
					RecList21 rlPos = adr.getKreditVersicherungsPositionen(dt.getDate());
					for (Rec21 r: rlPos){
						DEBUG.System_println(Long.toString(r.getId()));
					}
					
				}
			});
			
			
			
			this.add(new MyE2_Label("Fakturierungsfrist zum Stichtag für Kunde"));
			this.add(tfIDAdresse);
			this.add(tfdatum);
			this.add(bTest);
			this.add(tfTage);
			
		}
		
		
		private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
		{
			public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
			{
				super(cStartWert, 100);
				this.set_bEnabled_For_Edit(bEnabled4Edit);

			}
		}

		
	
	}
	
	
	private class test_Convert_to_BG2_Structure extends MyE2_Row{
		MyE2_Button bTest;
		
		public test_Convert_to_BG2_Structure() throws myException {
			bTest = new MyE2_Button("Konvertierung Fuhre -> Bewegung2 testen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					test_convert test = new test_convert();
					
					test.runTest();
					
				}
			});
			
			
			
			this.add(bTest);
			
		}
		
		
	
	}
	
	
	
	private class test_Rec21Wiegekarte extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tfWKID= new MyE2_TextField("", 100,100);
		MyE2_TextField tftext1 = new MyE2_TextField("", 500,500);
		
		MyE2_Button btNewwK;
		
		public test_Rec21Wiegekarte() throws myException {
			bTest = new MyE2_Button("Rec21Wiegekarte testen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sTest = "";
					RecDOWiegekarte wk = new RecDOWiegekarte(MASK_STATUS.EDIT)._fill_id(tfWKID.getText().trim());
					
//					Rec21_wiegekarte wk = new Rec21_wiegekarte()._fill_id(tfWKID.getText().trim());
					
					
					
					sTest += ( " ID..." + wk.get_key_value()) ;
					sTest += " / Waeg1..." + wk._get_Waegung1().get_key_value();
					sTest += " / Waeg2..." + wk._get_Waegung2().get_key_value();
					
					tftext1.setText(sTest) ;
				}
			});
			
			btNewwK = new MyE2_Button("Neue Wiegekarte");
			btNewwK._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					MyE2_MessageVector mv = new MyE2_MessageVector();
					
					RecDOWiegekarte wk = new RecDOWiegekarte(MASK_STATUS.NEW);
//					Rec21_wiegekarte wk = new Rec21_wiegekarte();
					wk._setNewValue(WIEGEKARTE.id_adresse_lager, 5525L, mv);
					wk._setNewValue(WIEGEKARTE.typ_wiegekarte, "W", mv);
					wk._setNewValueInDatabaseTerminus(WIEGEKARTE.lfd_nr, "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL");
					
					
					RecDOWaegung1 wg1 = new RecDOWaegung1(MASK_STATUS.NEW);
					wg1._setNewValue(WAEGUNG.waegung_pos, 1L, mv);
					wg1._setNewValueInDatabaseTerminus(WAEGUNG.id_wiegekarte, "SEQ_WIEGEKARTE.CURRVAL");
					wk._set_Waegung1(wg1);
					
					
					RecDOWaegung2 wg2 = new RecDOWaegung2(MASK_STATUS.NEW);
					wg2._setNewValue(WAEGUNG.waegung_pos, 2L, mv);
					wg2._setNewValueInDatabaseTerminus(WAEGUNG.id_wiegekarte, "SEQ_WIEGEKARTE.CURRVAL");
					wk._set_Waegung2(wg2);
					
					wk._SAVE(false, mv);
					wg1._SAVE(false, mv);
					wg2._SAVE(true, mv);
					
					
					tftext1.setText( mv.get_MessagesAsText() );
					
				}
			})  ;
			
			
			
			this.add(tfWKID);
			this.add(bTest);
			this.add(tftext1);
			this.add(btNewwK);
		}
		
		
	
	}
	
	

	private class test_Get_Fuhreninfo extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		
		public test_Get_Fuhreninfo() throws myException {
			bTest = new MyE2_Button("Fuhreninfo Testen...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					WK_RB_FuhrenInfo oFI = new WK_RB_FuhrenInfo();
					oFI.set_DatumVon("2019-01-01");
					oFI.set_DatumBis("2020-10-01");
					ArrayList<WK_RB_RECORD_FuhrenInfo> recsFI = oFI.getFuhrenInfos("5525");
					tftext1.setText(Integer.toString(recsFI.size()) );
					
				}
			});
			
			
			
			this.add(bTest);
			this.add(tftext1);
			
		}
		
		
	
	}
	
	
	private class test_ES_NR_Erzeugen extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		
		public test_ES_NR_Erzeugen() throws myException {
			bTest = new MyE2_Button("Eingangsschein von WK erzeugen...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					WK_RB_PrintBase wk_print = new WK_RB_PrintBase(tftext1.getText().trim()) {
						@Override
						public void Print() {
						}
					};;
					
					wk_print.updateWK_SetDruckdatum();
					wk_print.updateWK_SetEingangsscheinNr();
					
				}
			});
			
			this.add(bTest);
			this.add(tftext1);
		}
		
	}	
		
	private class test_WK_PRINT extends MyE2_Row{
			MyE2_Button bTestWK;
			MyE2_Button bTestES;
			MyE2_Button bTestHofschein;
			MyE2_Button bTestEtikett;
			
			MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
			
			public test_WK_PRINT() throws myException {

				bTestWK = new MyE2_Button("WK drucken...");
				bTestES = new MyE2_Button("ES/LS drucken...");
				bTestHofschein = new MyE2_Button("Hofschein drucken...");
				bTestEtikett = new MyE2_Button("Etiketten drucken...");
				
				bTestWK.add_oActionAgent(new XX_ActionAgent() {
					
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						
						WK_RB_PrintWiegekarte wk_print = new WK_RB_PrintWiegekarte(tftext1.getText().trim());
						
						wk_print.Print();
						
					}
				});

				bTestES.add_oActionAgent(new XX_ActionAgent() {
					
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						
						WK_RB_PrintEingangLieferschein wk_print = new WK_RB_PrintEingangLieferschein(tftext1.getText().trim());
						
						wk_print.Print();
						
					}
				});

				bTestHofschein.add_oActionAgent(new XX_ActionAgent() {
					
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						
						WK_RB_PrintHofschein wk_print = new WK_RB_PrintHofschein(tftext1.getText().trim());
						
						wk_print.Print();
						
					}
				});

				bTestEtikett.add_oActionAgent(new XX_ActionAgent() {
					
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						
						WK_RB_PrintEtikett wk_print = new WK_RB_PrintEtikett(tftext1.getText().trim(),-1);
						
						wk_print.Print();
						
					}
				});

				
				
				
				this.add(new MyE2_Label("Drucktest: ID Wiegekarte:"));
				this.add(tftext1);
				this.add(bTestWK);
				this.add(bTestES);
				this.add(bTestHofschein);
				this.add(bTestEtikett);
				
			}
			
	}
	
	
	
	private class testINSTAT_XML extends MyE2_Row{
		MyE2_Button bTest;
		
		public testINSTAT_XML() throws myException {
			bTest = new MyE2_Button("INTRASTAT XML testen...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					new INSTAT().runTest();
				}
			});
			
			
			
			this.add(bTest);
		}
		
		
	
	}
	
	private class testINSTAT_XML_DOWNLOAD extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tfYEAR     = new MyE2_TextField("2013", 100,100);
		MyE2_TextField tfMONTH    = new MyE2_TextField("01", 100,100);
		MyE2_TextField tfZAEHLER  = new MyE2_TextField("1", 100,100);
		MyE2_TextField tfTyp      = new MyE2_TextField("1", 100,100);
		
		
		
		public testINSTAT_XML_DOWNLOAD() throws myException {
			bTest = new MyE2_Button("INTRASTAT XML Download...");
			
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					new Instat_XML_Handler().exportIntrastatRecordsToXML(
																Integer.parseInt(tfYEAR.getText()), 
																Integer.parseInt(tfMONTH.getText()), 
																Integer.parseInt(tfZAEHLER.getText()), 
																tfTyp.getText().equals("1")? ENUM_IntrastatType.ENUM_Import : ENUM_IntrastatType.ENUM_Export);
					;
				}
			});
			
			this.add(new MyE2_Label("Jahr:"));
			this.add(tfYEAR);
			
			this.add(new MyE2_Label("Monat:"));
			this.add(tfMONTH);
			this.add(new MyE2_Label("Zähler:"));
			this.add(tfZAEHLER);
			this.add(new MyE2_Label("Typ (1-Einfuhr/2-Ausfuhr):"));
			this.add(tfTyp);

			this.add(bTest);
		}
		
		
	
	}
	
	private class test_LagerplatzHierarchie extends MyE2_Row{
		
		MyE2_TextField tfID = new MyE2_TextField("",100,100);
		
		MyE2_Button bTest;
		MyE2_Button bTest2;
		MyE2_Button bTest3;		
		MyE2_Button bTest4;
		MyE2_Button bTest5;
		
		WK_RB_SelField_Lagerplatz selFieldLager;
		WK_RB_SelField_Lagerplatz selFieldLager2;
		WK_RB_SelField_Lagerplatz selFieldLager3;
		WK_RB_SelField_Lagerplatz selFieldLager4;
		
		
		MyE2_TextArea tftext1 = new MyE2_TextArea("",500,5000,5);
		
		public test_LagerplatzHierarchie() throws myException {
			bTest = new MyE2_Button("Lagerplatz-Liste testen");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sResult = "";
					RecList_Lagerplatz rlLagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Default());
					for (Rec21 r: rlLagerplatz) {
						sResult += r.getDbValueUnFormated("ID_LAGERPLATZ") + ": " + r.getDbValueUnFormated("path") +" level:" + r.getDbValueUnFormated("tiefe")+" isLeaf:"+ r.getDbValueUnFormated("is_leaf")+" id_root:"+ r.getDbValueUnFormated("id_root") + ";\n";
					}
					tftext1.setText(Integer.toString(rlLagerplatz.size()) + ": " + sResult);
				}
			});
			
			bTest2 = new MyE2_Button("Lagerplätze ");
			bTest2.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sResult = "";
					RecList_Lagerplatz rlLagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz(true,false));
					for (Rec21 r: rlLagerplatz) {
						sResult += r.getDbValueUnFormated("path") + ";\n";
					}
					tftext1.setText(Integer.toString(rlLagerplatz.size()) + ": " + sResult);
					
				}
			});
			
			bTest3 = new MyE2_Button("Schüttgut ");
			bTest3.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sResult = "";
					RecList_Lagerplatz rlLagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut(true,false));
					for (Rec21 r: rlLagerplatz) {
						sResult += r.getDbValueUnFormated("path") + ";\n";
					}
					tftext1.setText(Integer.toString(rlLagerplatz.size()) + ": " + sResult);
					
				}
			});


			bTest4 = new MyE2_Button("Schüttgut ab Knoten ...");
			bTest4.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sResult = "";
					
					RecList_Lagerplatz rlLagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Schuettgut_unterhalb(tfID.getText(), false,true,true));
					for (Rec21 r: rlLagerplatz) {
						sResult += r.getDbValueUnFormated("path") + ";\n";
					}
					tftext1.setText(Integer.toString(rlLagerplatz.size()) + ": " + sResult);
					
				}
			});

			bTest5 = new MyE2_Button("Container ab Knoten ... incl.");
			bTest5.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String sResult = "";
					RecList_Lagerplatz rlLagerplatz = new RecList_Lagerplatz(RecList_Lagerplatz.getSqlExt_Lagerplatz_unterhalb(tfID.getText(), true,true,false) );
					for (Rec21 r: rlLagerplatz) {
						sResult += r.getDbValueUnFormated("path") + ";\n";
					}
					tftext1.setText(Integer.toString(rlLagerplatz.size()) + ": " + sResult);
					
				}
			});

			selFieldLager = new WK_RB_SelField_Lagerplatz(null, ENUM_Gueterkategorie.STUECKGUT,1);
			selFieldLager2 = new WK_RB_SelField_Lagerplatz(null, ENUM_Gueterkategorie.STUECKGUT,-1).setParentField(selFieldLager);
			
			selFieldLager3 = new WK_RB_SelField_Lagerplatz(null, ENUM_Gueterkategorie.SCHUETTGUT,1);
			selFieldLager4 = new WK_RB_SelField_Lagerplatz(null, ENUM_Gueterkategorie.SCHUETTGUT,-1).setParentField(selFieldLager3);
			
			this.add(tfID);
			this.add(bTest);
			this.add(bTest2);
			this.add(bTest3);
			this.add(bTest4);
			this.add(bTest5);
			this.add(tftext1);
			this.add(selFieldLager);
			this.add(selFieldLager2);
			this.add(selFieldLager3);
			this.add(selFieldLager4);

		}
		
	}	
	
	
	private class testInputDialog extends MyE2_Row{
		MyE2_Button bTest;
		
		
		
		public testInputDialog() throws myException {
			bTest = new MyE2_Button("Einfacher Input-Dialog...");
			
			bTest._aaa(()-> {
				new E2_MessageBoxGetValue(
						"Titel Eingabezeile", 
						"Text OK", 
						"TextCancel", 
						true, 
						false, 
						"Neues Gewicht:",
						new VectorE2String().ut("Hello World"), // 	null,
						null, 
						500, 
						200)._addChangeListener((o)->{
							MyE2_MessageVector mv = bibMSG.getNewMV();
							System.out.println(o.getValue()) ;
							mv.add_MESSAGE(new MyE2_Alarm_Message("ALRARM"));
							return mv;
						})
				._setComponentExtra(new E2_Grid()._insets(10)._setSize(100,100,200)._setRowHight(50)
							._a(new MyE2_Label("hallo"), new RB_gld()._left_top())
							._a(new MyE2_Button("button"), new RB_gld()._center_mid())
							._a("simple String", new RB_gld()._right_bottom()))
				._setTypeOfInput(TYPE_OF_INPUT.NUMBER)
				
				._show();


			});
		
			this.add(bTest);
			
			
			
	
		}
		

	}
	
	
	
	
	private class TestAnhangGeneratorTemp extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		
		public TestAnhangGeneratorTemp() throws myException {
			bTest = new MyE2_Button("Download der Anhänge von Wiegekarte-ID ...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					Long id = new MyLong(tftext1.getText()).get_oLong();
					RecList22_Archivmedien rlArch = new RecList22_Archivmedien(_TAB.wiegekarte.baseTableName(), id);
					
					myTempFile  tf1 = rlArch.generate_pdf_tempFile_concatenated();
					
					myTempFile tf = new PDF_Overlay_Factory_4_FreeText().generateCopyFromOriginal(tf1.getFileName(),"STORNO",Color.RED); 
					tf1.set_bDeleteAtFinalize(true);
					tf1 = null;
					
					E2_Download oDown = new E2_Download();
					oDown.starteFileDownload(tf.getFileName(),tf.getFileName() ,"application/pdf");
					
				}
			});
			
			this.add(bTest);
			this.add(tftext1);
		}
		
	}	
	
	
	private class TestOffenePostenKunde extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		MyE2_TextArea tfResult = new MyE2_TextArea("", 500, 1000, 6);
		
		
		public TestOffenePostenKunde() throws myException {
			bTest = new MyE2_Button("Offene Beträge des Kunden ...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					Long id = new MyLong(tftext1.getText()).get_oLong();
					String result = "nix!";
					STATKD_StatusErmittlung status = new STATKD_StatusErmittlung();
					STATKD_DataRowStatus row = status.ErmittleKundenStatus(id.toString());
					if (row != null) {
						result = "Status Kunde:" + id.toString()+ System.lineSeparator();
						result += "Gesamtforderung: " + row.get_Gesamt_FORDERUNG().toPlainString() + System.lineSeparator();
						result += "Fuhre: " + row.get_FUHRE_FORDERUNG().toPlainString() + System.lineSeparator();
						result += "Fuhre geplant: " + row.get_FUHRE_FORDERUNG_GEPLANT().toPlainString() + System.lineSeparator();
						result += "Rechnung: " + row.get_RECH_FORDERUNG().toPlainString() + System.lineSeparator();
						result += "Fibu: " + row.get_FIBU_FORDERUNG().toPlainString() + System.lineSeparator();
					}
					TestOffenePostenKunde.this.tfResult.setText(result);
				}
			});
			
			this.add(bTest);
			this.add(tftext1);
			this.add(tfResult);
		}
		
	}	

	
	
	private class TestContainerZyklusListe extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		MyE2_TextArea tfResult = new MyE2_TextArea("", 500, 1000, 6);
		
		
		public TestContainerZyklusListe() throws myException {
			bTest = new MyE2_Button("Container-Zyklus eines Containers");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RecList_ContainerZyklus rl = new RecList_ContainerZyklus(tftext1.getText());
					String result = "";
					
					if (rl != null) {
						result = "#Zyklus" + rl.size() + "\n";
						
						for (Rec22 rec : rl) {
							Rec_ContainerZyklus rc = new Rec_ContainerZyklus(rec);
							result += "\nZyklusID = " + rec.getActualID().toString() + "\n" ;
							
							RecList22 rlEntries = rc.getReclistContainerStationen();
							for(Rec22 entry : rlEntries) {
								result += " entryID:" + entry.getActualID().toString()  + "\n";
							}
						}
						
					}
					TestContainerZyklusListe.this.tfResult.setText(result);
				}
			});
			
			this.add(new MyE2_Label("ContainerID"));
			this.add(tftext1);
			this.add(bTest);
			this.add(tfResult);
		}
		
	}	

	
	
	private class TestKorrekturwiegekarte extends MyE2_Row{
		MyE2_Button bTest;
		MyE2_TextField tftext1 = new MyE2_TextField("",200,200);
		
		public TestKorrekturwiegekarte() throws myException {
			bTest = new MyE2_Button("Korrekturwiegekarte von Wiegekarte-ID ...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					Long id = new MyLong(tftext1.getText()).get_oLong();
					
					WK_RB_LIST_WiegekartenHandler wkHandler = new WK_RB_LIST_WiegekartenHandler(tftext1.getText(), bibMSG.MV());
					Long idNew = wkHandler._createStornoKorrekturWiegekarte( false);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(String.format("Wiegekarte kopiert %s", idNew)));
					bibDB.Rollback();
				}
			});
			
			this.add(bTest);
			this.add(tftext1);
		}
		
	}	

	
	
	private class TestDialogGetValuesExtended extends MyE2_Row{
		MyE2_Button bTest;
		
		public TestDialogGetValuesExtended() throws myException {
			bTest = new MyE2_Button("DialogGetStornoTara ...");
			bTest.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					// TODO Auto-generated method stub
					new WK_RB_DialogGetStornoTara("Titel","ok","abbrechen",false,true,new VectorE2String(),null,300,100)._show();
				}
			});;
			
			this.add(bTest);
		}
		
	}	
	
	
}
