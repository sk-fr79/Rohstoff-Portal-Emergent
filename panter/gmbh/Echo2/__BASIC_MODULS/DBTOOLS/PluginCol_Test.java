package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import echopointng.Separator;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.TestButton;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.xmlDefTools.XStreamWriterListDefs;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.EMAIL.RB_Email_test_ui;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RecursiveSearch.E2_Recursive_Search_Generate_Fingerprint;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveOneString;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_ImageCapture_Handler;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_PopUp_For_Display;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.E2_Button_ConnectToOtherObjects;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.SEARCH_Adress_ARCHIV;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.SEARCH_Kontrakt_ARCHIV;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.SEARCH_Sorte_ARCHIV;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.UP_AND_DOWNLOAD_ENUM_CONDITIONS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_MailAdressSearcher;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST.bt_test_enum_persistence;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST.test_bt_4_new_records;
import panter.gmbh.Echo2.__BASIC_MODULS.MAIL_MESSAGES.MAIL_MSG_Automail;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Email_Handler;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_Factory;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_List_Executer;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_USER_Entry_Data;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ButtonPopupPasswordQuery;
import panter.gmbh.Echo2.components.E2_CalendarComponent_WithDayButtons;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.E2_PopUp;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.MultiValueSelector.E2IF_MultiValueSelectorContainer;
import panter.gmbh.Echo2.components.MultiValueSelector.E2IF_MultiValueSelectorItem;
import panter.gmbh.Echo2.components.MultiValueSelector.MultiValueSelectorContainer;
import panter.gmbh.Echo2.components.MultiValueSelector.MultiValueSelector_SaveKeySizeofPopup;
import panter.gmbh.Echo2.decisions.FingerPrint_Generator_Dummy;
import panter.gmbh.Echo2.decisions.FingerPrint_Screen;
import panter.gmbh.Echo2.decisions.MyE2_AlarmMessage_4_Confirm_STD;
import panter.gmbh.Echo2.decisions.bibDECISIONS;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TEMP_IMPORT_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKEL_BEREICH;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARTIKEL_GRUPPE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEREICH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_GRUPPE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_BRANCHE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_GRUPPE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_OECD_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TEMP_IMPORT_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibHASHMAP;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.Replacer.RP_GeneratorReplacelistFromRecord;
import panter.gmbh.indep.ZIP.ZIP_NG_Creator;
import panter.gmbh.indep.ZIP.ZIP_NG_NamePair;
import panter.gmbh.indep.archive.ArchiveReportFiles;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.ENUM_DB_PARAMETER;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.LeftOuterJoin;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.IMAP.IMAP_Mail_Handler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingReminder;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.BL_BEWEGUNG_HANDLER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung_Batch;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN.ATOM_SetzkastenHandler_KALKULATORISCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_DBSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW.BEW_ModelView_Suche_Grid;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer.IMPORT_Zolltarifnummer_Temp_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic.FIBU.SUCHE.VerbundenFibuIdSuche;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung_Kreditversicherung;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_Reorganize;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_Reorganize_Sorte;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_Reorganize_Umbuchungspreise;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_SelectBuchungsgewichte;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_Handler;
import rohstoff.Echo2BusinessLogic.WUT.HANDLER.WUT_Command_counter;
import rohstoff.Echo2BusinessLogic.WUT.HANDLER.WUT_Command_input;
import rohstoff.Echo2BusinessLogic.WUT.HANDLER.WUT_Socket_Handler_Base;
import rohstoff.Echo2BusinessLogic._4_ALL.Print_via_DirectPrinterDefinition;


public class PluginCol_Test extends Basic_PluginColumn
{

	private MyE2_Button  oButtonStartImport = new MyE2_Button("LADE Artikel aus JT_TEMP_IMPORT_DEF");

	private RECORD_TEMP_IMPORT_DEF  recWithActualGroup = null;    //nimmt immer den satz der letzten gruppendefinition auf


	private MyE2_Button  oButtonStartImportArtBez = new MyE2_Button("LADE Artikelbez aus JT_TEMP_IMP_SORTEN");


	private MyE2_Button  oButtonStartImportText = new MyE2_Button("LADE Texte aus JT_TEMP_TEXT");


	public PluginCol_Test(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		MyE2_Button  oButtRPC = new MyE2_Button("rpc");
		TestButton tb = new TestButton();
		ownButtonCheckIndirekt tbi = new ownButtonCheckIndirekt(tb);
		this.add(new E2_ComponentGroupHorizontal(0, 
				this.oButtonStartImport, 
				oButtRPC, 
				new buttonCheckMailAdressSearcher(),
				tb,
				tbi,
				E2_INSETS.I_2_2_2_2));
		this.oButtonStartImport.add_oActionAgent(new importActionAgentSortenBasis());





		this.add(new ownButtonTestArchiv());


		oButtRPC.add_oActionAgent(new action_check_xml());


		this.add(new ownButtonGroovy());


		this.oButtonStartImportArtBez.add_oActionAgent(new __actionAgentImportSortenBez());
		this.oButtonStartImportText.add_oActionAgent(new __actionAgentImportTexte());

		this.add(this.oButtonStartImportArtBez);
		this.add(this.oButtonStartImportText);

		// Lager reorg
		//		ownButtonReorganizeLager oBtnReorg = new ownButtonReorganizeLager(); 
		//		this.add (oBtnReorg);
		//		if (!bibALL.get_bIST_SUPERVISOR()){
		//			oBtnReorg.set_bEnabled_For_Edit(false);
		//		}




		// test lagerreorg_sorte
		this.add(new testButtonReorganizeLagerSorteDatum());
		this.add(new ownButtonReorganizeUmbuchungspreise()) ;

		// Automails von Hand starten
		this.add(new oButtonTestAutoMails());

		// Größenumrechnungen testen
		if (bibALL.get_bDebugMode()){
			this.add(new testGroessenUmrechnungen());
		}

		// Bewegungshandler testen
		if (bibALL.get_bDebugMode()){
			//			this.add(new testUmsetzungBewegungssatz());
			this.add(new testUmsetzungBewegungssatzRow());
			this.add(new testSetzkastenAtomRow());
			this.add(new testSetzkastenAtomRow_KALKULATORISCH());
			this.add(new testSetzkastenAtomRow_KALKULATORISCH_Adresse_Artikel());



			// IMAP-ZUGRIFF
			this.add(new testIMAPZugriff());

			this.add(new testSuche_fuer_ARCHIV());

			this.add(new oButtonTestMANDANT_ZUSATZ() );


			this.add(new testNeueLagerselektion());


			//this.add(new testTransportkostenAdresse());

			this.add(new testHandlingskostenArtbezlief());

			//			this.add(new testAtomkostenATOM());

			this.add(new testAtomkostenBEWEGUNG());

			this.add(new testAtomkostenERMITTLUNG_BATCH());

			this.add(new testAtomkostenAdresse());

			this.add(new testeBilderanzeige());
			this.add(new testeBilderCapture());

			this.add(new testeXMLErzeugung());			

			// 
			this.add(new testeForderungspruefung() );


			this.add(new ownTestZippenBeliebigerDateien(),E2_INSETS.I(4,20,5,5));
			this.add(new ownGridWithTestButtons(),E2_INSETS.I(4,20,5,5));

			this.add(new TestButtonOwnSelect());

			this.add(new test_passivKlick());

			//			this.add(new ownButtonTestSelectObj());

			//			this.add(new TEST_BT_1(new MyE2_String("Die erste maske ...")));
			//			
			this.add(new testArchivePaths());

			this.add(new testMandantenZusatzWerte());
			this.add(new testMandantenZusatzWerte2());	

			this.add(new testBtDynamischerVector());
			this.add(new E2_Grid4MaskSimple().add_(new testMultiselectorComponent()).add_(new ownButtonTestRec()).add_(new testMessageMailer()));



			this.add(new Test_E2_CalendarComponent_Version2());
			
			
			this.add(new fibuSearchTest());

//			this.add(new Rp_Replace_ClassTestCall());
			
			this.add(new testDropdownWithEnum());
			
			this.add(new buttonFindOpenReminder());
			
			this.add(new buttonNewReminder());
			
			this.add(new ownFileReadBt());
			
			this.add((MyE2_Button)new MyE2_Button("Wiegekarten-Reminder testen ...")._bordDDark()._font(new E2_FontBold(4))._aaa(new actionAgentCheckWiegekarteReminder()));
			this.add(new ownGrid4TestingSave());

			this.add(new BEW_ModelView_Suche_Grid());
			
			
			this.add(new buttonTestImportZolltarifnummern());
			this.add(new ownPopup());
			this.add(new buttonTestGenerateReplaceList());

			this.add(new test_new_RECORD_Email_Send_ext_creation(), E2_INSETS.I(5,30,5,10));
			
			this.add(new test_bt_4_new_records());
			
			this.add(new WUT_test());
			this.add(new button_test_user_id());
			
			this.add(new bt_test_enum_persistence());
			
			this.add(new button_test_sendMailSimple());
			
//			this.add(new Test_multiselector().get_oComponentForSelection(), E2_INSETS.I(0));		
		}
	}

	
	private class ownPopup extends E2_PopUp {

		public ownPopup() throws myException {
			super();
		
			this._set_inner_size(100,20)
					._add_line(new E2_Button()._t("test1")._b(),new RB_gld()._ins(4),new MyE2_CheckBox(), new RB_gld()._col(Color.RED))
					._add_line(new E2_Button()._t("test2")._b(),new RB_gld()._ins(4),new MyE2_CheckBox(), new RB_gld()._col(Color.BLUE))
					._add_line(new E2_Button()._t("test3")._b(),new RB_gld()._ins(4),new MyE2_CheckBox(), new RB_gld()._col(Color.GREEN))
					._add_line(new E2_Button()._t("test4")._b(),new RB_gld()._ins(4),new MyE2_CheckBox(), new RB_gld()._col(Color.WHITE));

		}
		
		
	}
	

	private class ownButtonCheckIndirekt extends MyE2_Button {
		private MyE2_Button  refButton = null;

		public ownButtonCheckIndirekt(MyE2_Button ref_Button) {
			super(new MyE2_String("Indirekt..."));
			this.refButton = ref_Button;
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					refButton.doActionPassiv();
				}
			});
		}
	}
	
	

	private class ownButtonGroovy extends MyE2_Button
	{

		public ownButtonGroovy() 
		{
			super(new MyE2_String("Groovy"));
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				Binding binding = new Binding();
				binding.setVariable("foo", new Integer(2));
				binding.setVariable("text", "abc");
				//	binding.setVariable("active", "abc");
				GroovyShell shell = new GroovyShell(binding);

				String cCode =
						"x = 123; " +
								"if (text=='abc') {text=text+ 'hij';} else {text=text+'eee';};"+
								"active='Y';";

				shell.evaluate(cCode);

				//				if (value instanceof Integer)
				//				{
				//					System.out.println("Integer: "+value);
				//					System.out.println("text:"+(String)binding.getVariable("text"));

				Map<String, Object>  groovyMap = binding.getVariables();

				if (groovyMap.containsKey("active"))
				{
					System.out.println("text:"+groovyMap.get("text"));
					System.out.println("AKTIV:"+groovyMap.get("active"));
				}

				//				}

				//				if (value.equals(new Integer(21)))
				//				assert binding.getVariable("x").equals(new Integer(123));



			}
		}


	}



	private class ownButtonTestArchiv extends MyE2_Button
	{

		public ownButtonTestArchiv() 
		{
			super(new MyE2_String("Teste Archiv"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					ArchiveReportFiles oArch = new ArchiveReportFiles("RECHNUNGEN", "RECH", "pdf");

					bibMSG.add_MESSAGE(new MyE2_Info_Message(oArch.get_cCompleteArchivePathWithSeparators(),false));
					bibMSG.add_MESSAGE(new MyE2_Info_Message(oArch.get_cFileName(),false));
					bibMSG.add_MESSAGE(new MyE2_Info_Message(oArch.get_cArchivePathWithEndSeparators(),false));


				}
			});


		}

	}


	private class ownButtonReorganizeLager extends MyE2_Button
	{

		public ownButtonReorganizeLager() 
		{
			super(new MyE2_String("Lager Reorganisieren"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					LAG_Reorganize oReorg = new LAG_Reorganize();
					oReorg.Reorganize_GesamtesLager_AufbauAusFuhren_WithProcessBar();

				}
			});


		}

	}


	private class ownButtonReorganizeUmbuchungspreise extends MyE2_Button
	{

		public ownButtonReorganizeUmbuchungspreise() 
		{
			super(new MyE2_String("0Euro-Preise von Umbuchungen korrigieren"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					LAG_Reorganize_Umbuchungspreise reorg = new LAG_Reorganize_Umbuchungspreise();
					reorg.reorganize();
				}
			});

		}

	}




	private class testButtonReorganizeLagerSorteDatum extends MyE2_Button
	{

		public testButtonReorganizeLagerSorteDatum() 
		{
			super(new MyE2_String("Lager-Setzkasten neu erzeugen ab dem 01.11.2009"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					LAG_Reorganize_Sorte oReorg = new LAG_Reorganize_Sorte("2009-11-01");

					oReorg.Reorganize_Lager_WithProcessBar();

				}
			});


		}

	}

	private class testGroessenUmrechnungen extends MyE2_Button
	{

		GROESSEN_Umrechnung oUmrechnung = null;

		public testGroessenUmrechnungen() throws myException 
		{
			super(new MyE2_String("Größenumrechnungen testen."));




			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					// Größenumrechnung initialisieren
					oUmrechnung = new GROESSEN_Umrechnung();


					System.out.println("Gesamtpreisberechnung ArtID=1, Kg=20501,5, Preis 312,5 Euro/t  (6406,71 Euro) : "  
							+  oUmrechnung.BerechneGesamtpreis("1", new BigDecimal(20501.5), new BigDecimal(312.5)).toPlainString());

					System.out.println("Gesamtpreisberechnung ArtID=1642, Stk=10, Preis 312,5 Euro/stk  (3125 Euro) : "  
							+  oUmrechnung.BerechneGesamtpreis("1642", new BigDecimal(10), new BigDecimal(312.5)).toPlainString());

					System.out.println("Einzelpreis-umrechnung Kg/Kg -> ArtID=1 (Kg/t), Preis 312,5 Euro/Kg -> ? Euro/t (312500,0 Euro) : "  
							+  oUmrechnung.BerechneEinzelpreisNeuerArtikel(new BigDecimal(312.5), new BigDecimal(1),"1" ).toPlainString());




				}
			});


		}

	}

	private class testNeueLagerselektion extends MyE2_Row{
		MyE2_SelectField selLager = null;

		public testNeueLagerselektion() throws myException{
			super();
			UTIL_DBSelectField_Factory_ForLager o = new UTIL_DBSelectField_Factory_ForLager();
			selLager = o.getSelectField(false);
			this.add(selLager);
		}

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





	private class oButtonTestAutoMails extends MyE2_Button
	{

		public oButtonTestAutoMails() 
		{
			super(new MyE2_String("Automails testen"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					MAIL_MSG_Automail oMailer = new MAIL_MSG_Automail();
					oMailer.runAutomailGeneration();

				}
			});
		}

	}

	private class oButtonTestMANDANT_ZUSATZ extends MyE2_Button
	{

		public oButtonTestMANDANT_ZUSATZ() 
		{
			super(new MyE2_String("ZUGRIFF auf JD_MANDANT_ZUSATZ testen"));

			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					// 
					bibMSG.add_MESSAGE(new MyE2_Info_Message(__RECORD_MANDANT_ZUSATZ.get___Value("xxx", "kein Eintrag definiert") ));
					bibMSG.add_MESSAGE(new MyE2_Info_Message(__RECORD_MANDANT_ZUSATZ.get___Value("LAGER_WA_PREIS_KORREKTUR", "fehler,da default-wert angezeigt werden müsste") ) );
					bibMSG.add_MESSAGE(new MyE2_Info_Message(__RECORD_MANDANT_ZUSATZ.get___Value("LAGER_UMBUCHUNGEN_ERLAUBEN", "fehler, der wert ist gesetzt") ) );

				}
			});
		}

	}

	private class testSuche_fuer_ARCHIV extends MyE2_Column{

		SEARCH_Adress_ARCHIV oSearchAdr = null;
		SEARCH_Sorte_ARCHIV oSearchSorte = null;
		SEARCH_Kontrakt_ARCHIV oSearchKontrakt = null;


		public testSuche_fuer_ARCHIV() throws myException{
			super();

			oSearchSorte = new SEARCH_Sorte_ARCHIV();
			oSearchSorte.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, "3005");

			oSearchSorte.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB() {
				@Override
				public void doMaskSettingsAfterValueWrittenInMaskField(
						String cMaskValue, MyE2_MaskSearchField oSearchField,
						boolean bAfterAction) throws myException {

					bibMSG.add_MESSAGE(new MyE2_Info_Message("Tablename: " + oSearchSorte.get_DBTableName() + " ID: " + oSearchSorte.get_FoundObjectID()));
				}
			});
			this.add(new MyE2_Label("Test Suche fürs Archiv: SORTE: "));
			this.add(oSearchSorte,E2_INSETS.I_10_0_0_0);

			oSearchAdr = new SEARCH_Adress_ARCHIV();
			oSearchAdr.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, "2");	

			oSearchAdr.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB() {

				@Override
				public void doMaskSettingsAfterValueWrittenInMaskField(
						String cMaskValue, MyE2_MaskSearchField oSearchField,
						boolean bAfterAction) throws myException {

					bibMSG.add_MESSAGE(new MyE2_Info_Message("Tablename: " + oSearchAdr.get_DBTableName() + " ID: " + oSearchAdr.get_FoundObjectID()));
				}
			});
			this.add(new MyE2_Label("Test Suche fürs Archiv: ADRESSE: "));
			this.add(oSearchAdr,E2_INSETS.I_10_0_0_0);

			oSearchKontrakt = new SEARCH_Kontrakt_ARCHIV();
			//			oSearchKontrakt.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, "3005");	
			oSearchKontrakt.set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, "2");


			oSearchKontrakt.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB() {
				@Override
				public void doMaskSettingsAfterValueWrittenInMaskField(
						String cMaskValue, MyE2_MaskSearchField oSearchField,
						boolean bAfterAction) throws myException {

					bibMSG.add_MESSAGE(new MyE2_Info_Message("Tablename: " + oSearchKontrakt.get_DBTableName() + " ID: " + oSearchKontrakt.get_FoundObjectID()));
				}
			});
			this.add(new MyE2_Label("Test Suche fürs Archiv: KONTRAKTE:  "));
			this.add(oSearchKontrakt,E2_INSETS.I_10_0_0_0);


			// der Knopf zum Upload
			this.add(new MyE2_Label("Beispiel Anhang einer Adresse:  ")); 
			this.add(new E2_Button_ConnectToOtherObjects("TEST","76996"));

			this.add(new MyE2_Label("Beispiel Anhang einer Fuhre:  ")); 
			this.add(new E2_Button_ConnectToOtherObjects("TEST","76995"));

		}

	}




	/**
	 * testet die verschiedenen Archiv-Kombinationen
	 * @author manfred
	 * @date 13.08.2015
	 *
	 */
	private class testArchivePaths extends MyE2_Row
	{

		MyE2_TextArea m_tfResults = new MyE2_TextArea("", 300, 10000, 5);
		MyE2_Button   m_btnTest = new MyE2_Button("Archivpfade testen");



		public testArchivePaths() throws myException 
		{

			this.add(m_btnTest);
			this.add(m_tfResults);


			m_btnTest.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo)
						throws myException {
					// Archiv testen
					Archiver oArchiv = new Archiver("TEST");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					try {
						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR.toString());
						addText(oArchiv);

						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.MONTH);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.MONTH.toString());
						addText(oArchiv);
						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.DAY);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.DAY.toString());
						addText(oArchiv);
						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY.toString());
						addText(oArchiv);
						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH.toString());
						addText(oArchiv); 
						oArchiv = new Archiver("INBOX", df.parse("2012-10-30"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK.toString());
						addText(oArchiv);
						oArchiv = new Archiver("INBOX", df.parse("2012-02-25"),
								Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK);
						addText(Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK.toString());
						addText(oArchiv);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

		}

		/** 
		 * protokolliert die Archiv-Rückgabewerte
		 * 
		 * @author manfred
		 * @date   13.08.2015
		 *
		 * @param oArchiv
		 */
		private void addText(Archiver oArchiv){
			//			addText(oArchiv.get_cArchiveBasePath());
			//			addText(oArchiv.get_cSUB_DIR_IN_Archiv());
			addText(oArchiv.get_cCompleteArchivePath());
		}

		/**
		 * fügt Text in das Ergebnisfeld ein
		 * 
		 * @author manfred
		 * @date   13.08.2015
		 *
		 * @param sText
		 */
		private void addText(String sText){
			if (m_tfResults != null){
				m_tfResults.setText(m_tfResults.getText() + System.lineSeparator() + sText);
			}
		}

	}



	private class testIMAPZugriff extends MyE2_Button
	{

		IMAP_Mail_Handler oIMAP = null;

		public testIMAPZugriff() throws myException 
		{
			super(new MyE2_String("IMAP-Zugriff testen"));


			this.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{

					MAIL_INBOX_Handler oMailInbox = new MAIL_INBOX_Handler();
					oMailInbox.importMailsFromInboxes();

					//					// Archiv testen
					//					Archiver oArchiv = new Archiver("TEST");
					//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					//					
					//					try {
					//						oArchiv = new Archiver("INBOX", df.parse("2012-01-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR);
					//						oArchiv = new Archiver("INBOX", df.parse("2013-02-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.MONTH);
					//						oArchiv = new Archiver("INBOX", df.parse("2013-03-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.DAY);
					//						oArchiv = new Archiver("INBOX", df.parse("2013-04-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY);
					//						oArchiv = new Archiver("INBOX", df.parse("2013-05-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH);
					//						oArchiv = new Archiver("INBOX", df.parse("2013-01-02"), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_CALWEEK);
					//					} catch (ParseException e) {
					//						// TODO Auto-generated catch block
					//						e.printStackTrace();
					//					}
					//					
					//					
					//					System.out.println( oArchiv.get_cCompleteArchivePath() );
					//					System.out.println( oArchiv.get_cArchiveBasePath() );
					//					System.out.println( oArchiv.get_cSUB_DIR_IN_Archiv() );
					//					
					//					String cNameOfArchivCopy = oArchiv.copyFilenameToNextFree("TEST.eml", "filename_base"+"_"+bibALL.get_cDateTimeNOWInverse()+".eml");

					//					oArchiv.WriteFileInfoToArchiveTable(oArchiv.get_cSUB_DIR_IN_Archiv(),
					//															cNameOfArchivCopy,
					//															cFileBaseName+this.oJasperHASH.get_oJasperFileDef().Endung,
					//															"Archivierung aus Druckvorgang: "+cFileBaseName,
					//															 null,
					//															 null,
					//															 cTableName4Archiv,    //tabellenname ohne JT_
					//															 cID_ARCHIV,
					//															 this.oJasperHASH.get_oJasperFileDef().Endung,
					//															 this.oJasperHASH.get_IS_TYP_MAIL()?Archiver.MEDIENKENNER_EMAIL:Archiver.MEDIENKENNER_PRINT,
					//															 null,
					//															 null,
					//															 null,null);
					//
					//					oMVRueck.add(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+this.vTempFilesToProcess.size(),false," Dokumente archiviert !",true)));
					//




					//					oIMAP = new IMAP_Mail_Handler(
					//							"bugtrack@panter-datentechnik.de",
					//							"21bugtrack34",
					//							"imap.1und1.de",
					//							"imaps",
					//							"INBOX",
					//							"993");
					//					
					//					int i = 0;
					//					
					//					try {
					//						oIMAP.connect();
					//						
					//						System.out.println(oIMAP.getFolderNames());
					//
					//						
					//						if (oIMAP.OpenFolder()){
					//							while (oIMAP.fetchNextMessge()){
					//								System.out.println("----  MAIL BEGIN --- ");
					//								System.out.println(oIMAP.getMailUID());
					//								System.out.println(oIMAP.getMailMessageID());
					//								System.out.println(oIMAP.getMailReceiveDate());
					//								System.out.println(oIMAP.getMailFrom());
					//								System.out.println(oIMAP.getMailSubject());
					//								System.out.println(oIMAP.getMailBodyText());
					//
					//								System.out.println(oIMAP.getMailAttachmentCount());
					//								if (oIMAP.getMailAttachmentCount() > 0) {
					//									System.out.println(oIMAP.getMailAttachmentFilename(1));
					//									oIMAP.saveMailAttachment(1, "/daten/" + oIMAP.getMailAttachmentFilename(1) );
					//								}
					//								i++;
					//								oIMAP.saveMail("/daten/testmail" + Integer.toString(i) + ".eml");
					//								System.out.println("----  MAIL END --- ");
					//								
					//							}
					//						}
					//						
					//					} catch (MessagingException e) {
					//						e.printStackTrace();
					//					} catch (IOException e) {
					//						e.printStackTrace();
					//					} finally {
					//						oIMAP.disconnect();
					//					}
					//					
				}
			});


		}

	}


	//	private class testTransportkostenAdresse extends MyE2_Button
	//	{
	//		public testTransportkostenAdresse() 
	//		{
	//			super("Teste die Transportkosten der Kunden");
	//			
	//			this.add_oActionAgent(new XX_ActionAgent() 
	//			{
	//				@Override
	//				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	//				{
	//					new TEST_Container_Transportkosten();
	//				}
	//			});
	//		}
	//		
	//	}


	
		private class testHandlingskostenArtbezlief extends MyE2_Button
		{
			public testHandlingskostenArtbezlief() 
			{
				super("Teste die Handlingskosten der Kundenspezifischen Artikelbezeichnungen");
				
				this.add_oActionAgent(new XX_ActionAgent() 
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{
						new TEST_Container_Handlingskosten();
					}
				});
			}
			
		}


	//	/**
	//	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	//	 * @author manfred
	//	 * @date   28.11.2013
	//	 */
	//	private class testAtomkostenATOM extends MyE2_Row{
	//
	//		MyE2_TextField tfFuhre = new MyE2_TextField();
	//		MyE2_Button   btnTest = new MyE2_Button("Kosten berechnen für ID_ATOM");
	//		public testAtomkostenATOM() throws myException{
	//			super();
	//			
	//			this.add(new MyE2_Label("Test Bewegungsatom-Kosten (ID-Atom): "));
	//			this.add(tfFuhre);
	//			this.add(btnTest,E2_INSETS.I_10_0_0_0);
	//
	//			btnTest.add_oActionAgent(new XX_ActionAgent() 
	//				{
	//					
	//					@Override
	//					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	//					{
	//						testAtomkostenATOM oThis = testAtomkostenATOM.this;
	//						BL_Kostenberechnung  oTest = new BL_Kostenberechnung();
	//						oTest.ErzeugeSQL_Kostensaetze_Fuer_Atom(tfFuhre.getText().trim());
	//						oTest.executeSqlStatements(true);
	//						
	////						oTest.Create_ATOM_Kostensaetze_And_WriteToDB(tfFuhre.getText().trim());
	//						
	//					}
	//				});
	//			
	//		}
	//
	//	}
	//	



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
	private class testMandantenZusatzWerte extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Mandanten-Zusatzfelder testen");
		MyE2_TextField txtFeldname = new MyE2_TextField("", 300, 300);
		MyE2_TextField txtValueWhenNoDefault = new MyE2_TextField("--NO DEFAULT--", 200, 200);
		MyE2_TextField txtValue = new MyE2_TextField("", 200, 200);


		public testMandantenZusatzWerte() throws myException{
			super();

			this.add(new MyE2_Label("Feldname: "));
			this.add(txtFeldname);
			this.add(new MyE2_Label("Wenn kein Defaultwert: "));
			this.add(txtValueWhenNoDefault);
			this.add(btnTest,E2_INSETS.I_10_0_0_0);
			this.add(txtValue);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testMandantenZusatzWerte oThis = testMandantenZusatzWerte.this;

					String sValue = bib_Settigs_Mandant.get__Value(txtFeldname.getText(), txtValueWhenNoDefault.getText());
					txtValue.setText(sValue);


				}
			});

		}
	}


	/**
	 * Ermitteln der kalkulatorischen Kosten für ein Bewegungs-Atom
	 * @author manfred
	 * @date   28.11.2013
	 */
	private class testMandantenZusatzWerte2 extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Mandanten-Zusatzfelder testen");
		MyE2_TextField txtFeldname = new MyE2_TextField("", 300, 300);
		MyE2_TextField txtValueWhenNotSet = new MyE2_TextField("--Not Set--", 200, 200);
		MyE2_TextField txtValueWhenNotDefined = new MyE2_TextField("--Not Defined--", 200, 200);
		MyE2_TextField txtValue = new MyE2_TextField("", 200, 200);


		public testMandantenZusatzWerte2() throws myException{
			super();

			this.add(new MyE2_Label("Feldname: "));
			this.add(txtFeldname);
			this.add(new MyE2_Label("wenn not_defined: "));
			this.add(txtValueWhenNotDefined);
			this.add(new MyE2_Label("wenn value_not_set: "));
			this.add(txtValueWhenNotSet);

			this.add(btnTest,E2_INSETS.I_10_0_0_0);
			this.add(txtValue);


			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					testMandantenZusatzWerte2 oThis = testMandantenZusatzWerte2.this;

					String sValue = bib_Settigs_Mandant.get__Value(txtFeldname.getText(), txtValueWhenNotDefined.getText(), txtValueWhenNotSet.getText());
					txtValue.setText(sValue);


				}
			});

		}
	}




	private class action_check_xml extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			try 
			{
				XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
				config.setServerURL(new URL("http://evatr.bff-online.de/"));
				XmlRpcClient client = new XmlRpcClient();
				client.setConfig(config);

				Object[] Werte = new Object[7];
				Werte[0]="DE142213487";
				Werte[1]="ESB48934038";
				Werte[2]="Befesa Aluminio S.L";
				Werte[3]="Erandio";
				Werte[4]="";
				Werte[5]="";
				Werte[6]="";

				String cErgebnis = (String)client.execute("evatrRPC", Werte);



				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
					DocumentBuilder builder = factory.newDocumentBuilder(); 
					Document document = builder.parse(new InputSource(new StringReader(cErgebnis))); 

					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());
					//					System.out.println("Name :"+ document.getDocumentElement());

					Element El = document.getDocumentElement();

					NodeList oNL= document.getElementsByTagName("string");

					System.out.println("Anzahl: "+oNL.getLength());

					for (int i=0;i<oNL.getLength();i++)
					{
						//						System.out.println(oNL.item(i).get);
					}



					//					System.out.println("Pruefung :"+ document.getElementsByTagName("ErrorCode"));



				} 
				catch (DOMException e) 
				{
					e.printStackTrace();
				} 
				catch (ParserConfigurationException e) 
				{
					e.printStackTrace();
				} 
				catch (SAXException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 


			}	 
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
			catch (XmlRpcException e2) 
			{
				e2.printStackTrace();
			}

		}


	}




	private class importActionAgentSortenBasis extends XX_ActionAgent
	{
		RECLIST_TEMP_IMPORT_DEF  reclistNewSorten = null;;

		RECORD_ARTIKEL_BEREICH recBereich = 	null;
		RECORD_ARTIKEL_GRUPPE  recGruppe  = 	null;
		RECORD_ARTIKEL         recArtikel = 	null;
		RECORD_ARTIKEL_BEZ     recArtikelBez = 	null;

		int iCountBereichNew = 0;
		int iCountBereichUpdate = 0;

		int iCountGruppeNew = 0;
		int iCountGruppeUpdate = 0;

		int iCountArtikelNew = 0;
		int iCountArtikelUpdate = 0;

		int iCountArtikelBezNew = 0;
		int iCountArtikelBezUpdate = 0;

		int iCountKeinBASEL_OECD_TEXT = 0;
		int iCountKeinZOLLTEXT = 0;

		int iCountAVV_IN_ERR =0;
		int iCountAVV_OUT_ERR =0;

		private String cNotFoundAVV_Codes = "";
		private String cDoubleHauptSorten = "";




		private MyE2_Grid oGridServerAnzeige = new MyE2_Grid(3);

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{

			new E2_ServerPushMessageContainer_STD(new Extent(400),new Extent(400),new MyE2_String("Artikelimport"),false,true,6000,this.oGridServerAnzeige,E2_INSETS.I_5_5_5_5) 
			{
				@Override
				public void Run_Loop() throws myException 
				{

					importActionAgentSortenBasis oThis = importActionAgentSortenBasis.this;


					oThis.iCountBereichNew = 0;
					oThis.iCountBereichUpdate = 0;

					oThis.iCountGruppeNew = 0;
					oThis.iCountGruppeUpdate = 0;

					oThis.iCountArtikelNew = 0;
					oThis.iCountArtikelUpdate = 0;

					oThis.iCountArtikelBezNew = 0;
					oThis.iCountArtikelBezUpdate = 0;

					oThis.iCountKeinBASEL_OECD_TEXT = 0;
					oThis.iCountKeinZOLLTEXT = 0;

					oThis.iCountAVV_IN_ERR = 0;
					oThis.iCountAVV_OUT_ERR = 0;

					oThis.cNotFoundAVV_Codes="";
					oThis.cDoubleHauptSorten="";


					oThis.reclistNewSorten = new RECLIST_TEMP_IMPORT_DEF("SELECT * FROM JT_TEMP_IMPORT_DEF ORDER BY SORTFIELD");

					for (int i=0;i<reclistNewSorten.get_vKeyValues().size();i++)
					{

						if (i%10==0)
						{
							oThis.oGridServerAnzeige.removeAll();
							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Tabelle")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Neu")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Geändert")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Bereich")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountBereichNew),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountBereichUpdate),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Gruppe")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountGruppeNew),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountGruppeUpdate),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Artikel")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountArtikelNew),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountArtikelUpdate),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.cDoubleHauptSorten),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2,null,3));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Artikel-Bez")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountArtikelBezNew),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountArtikelBezUpdate),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new Separator(),3,E2_INSETS.I_2_10_2_10);

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Kein Basel/OECD-Text gefunden ...")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2,null,2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountKeinBASEL_OECD_TEXT),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Kein ZOLL-Text gefunden ...")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2,null,2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountKeinZOLLTEXT),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));

							oThis.oGridServerAnzeige.add(new MyE2_Label(new MyE2_String("Fehler bei AVV-Code (IN/OUT) ...")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2,null,2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.iCountAVV_IN_ERR+"/"+oThis.iCountAVV_OUT_ERR),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
							oThis.oGridServerAnzeige.add(new MyE2_Label(""+oThis.cNotFoundAVV_Codes),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2,null,3));

						}

						RECORD_TEMP_IMPORT_DEF  recNewSort = reclistNewSorten.get(i);

						//zuerst pruefen, ob es ein neuer Artikelbereich ist
						if (recNewSort.is_BEREICHDEF_YES())
						{
							//nachsehen, ob es einen Artikelbereich dieses namens gibt
							String[][] cBereichTest = bibDB.EinzelAbfrageInArray("SELECT ID_ARTIKEL_BEREICH FROM JT_ARTIKEL_BEREICH WHERE BEREICH="+recNewSort.get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT());

							if (cBereichTest==null)
							{
								throw new myException("Error: False Query for ARTIKEL_BEREICH");
							}
							else if (cBereichTest.length>1)
							{
								throw new myException("Error: Duplicate Value for ARTIKEL_BEREICH");
							}
							else if (cBereichTest.length==0)
							{
								RECORDNEW_ARTIKEL_BEREICH  recNewBereich = new RECORDNEW_ARTIKEL_BEREICH();
								bibMSG.add_MESSAGE(recNewBereich.set_NEW_VALUE_BEREICH(recNewSort.get_ARTBEZ1_cUF_NN("--")));

								if (bibMSG.get_bHasAlarms())
								{
									return;
								}
								recBereich = recNewBereich.do_WRITE_NEW_ARTIKEL_BEREICH(bibMSG.MV());

								oThis.iCountBereichNew++;
							}
							else
							{
								recBereich = new RECORD_ARTIKEL_BEREICH(cBereichTest[0][0]);
								bibMSG.add_MESSAGE(recBereich.set_NEW_VALUE_BEREICH(recNewSort.get_ARTBEZ1_cUF_NN("--")));
								bibMSG.add_MESSAGE(recBereich.UPDATE(null, true));

								oThis.iCountBereichUpdate++;

							}

							if (bibMSG.get_bHasAlarms())
							{
								return;
							}
						}
						else if (recNewSort.is_GRUPPENDEF_YES())        //wenn nicht koennte es eine gruppe sein
						{
							//nachsehen, ob es einen Artikelbereich dieses namens gibt
							String[][] cGruppeTest = bibDB.EinzelAbfrageInArray("SELECT ID_ARTIKEL_GRUPPE FROM JT_ARTIKEL_GRUPPE WHERE GRUPPE="+recNewSort.get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT());

							if (cGruppeTest==null)
							{
								throw new myException("Error: False Query for ARTIKEL_GRUPPE");
							}
							else if (cGruppeTest.length>1)
							{
								throw new myException("Error: Duplicate Value for ARTIKEL_GRUPPE");
							}
							else if (cGruppeTest.length==0)
							{
								RECORDNEW_ARTIKEL_GRUPPE  recNewGruppe = new RECORDNEW_ARTIKEL_GRUPPE();
								bibMSG.add_MESSAGE(recNewGruppe.set_NEW_VALUE_GRUPPE(recNewSort.get_ARTBEZ1_cUF_NN("--")));
								bibMSG.add_MESSAGE(recNewGruppe.set_NEW_VALUE_ID_ARTIKEL_BEREICH(recBereich.get_ID_ARTIKEL_BEREICH_cUF()));

								if (bibMSG.get_bHasAlarms())
								{
									return;
								}

								recGruppe = recNewGruppe.do_WRITE_NEW_ARTIKEL_GRUPPE(bibMSG.MV());

								oThis.iCountGruppeNew++;


							}
							else
							{
								recGruppe = new RECORD_ARTIKEL_GRUPPE(cGruppeTest[0][0]);
								bibMSG.add_MESSAGE(recGruppe.set_NEW_VALUE_GRUPPE(recNewSort.get_ARTBEZ1_cUF_NN("--")));
								bibMSG.add_MESSAGE(recGruppe.set_NEW_VALUE_ID_ARTIKEL_BEREICH(recBereich.get_ID_ARTIKEL_BEREICH_cUF()));
								bibMSG.add_MESSAGE(recGruppe.UPDATE(null, true));

								oThis.iCountGruppeUpdate++;


							}
							if (bibMSG.get_bHasAlarms())
							{
								return;
							}
						}
						else if (recNewSort.get_ANR2_cUF_NN("--").equals("01"))    //dann ist das eine hauptsorte
						{
							//nachsehen, ob es einen Artikel mit dieser anr1 gibt

							String cANR1=recNewSort.get_ANR1_1_cUF_NN("-")+"-"+recNewSort.get_ANR1_2_cUF_NN("-");

							String[][] cSorteTest = bibDB.EinzelAbfrageInArray("SELECT ID_ARTIKEL FROM JT_ARTIKEL WHERE ANR1="+bibALL.MakeSql(cANR1));

							if (cSorteTest==null)
							{
								throw new myException("Error: False Query for ARTIKEL");
							}
							else if (cSorteTest.length>1)
							{
								throw new myException("Error: Duplicate Value for ARTIKEL");
							}
							else if (cSorteTest.length==0)
							{
								//einheit pruefen
								String cEinheit1 = recNewSort.get_EINHEIT1_cUF_NN("kg");
								String cEinheit2 = cEinheit1;
								String cMengenDivisor = "1";
								if (cEinheit1.equals("kg"))
								{
									cEinheit2 = "to";
									cMengenDivisor="1000";
								}

								RECORD_EINHEIT  eh1 = oThis.pruefeEinheit(cEinheit1);
								RECORD_EINHEIT  eh2 = oThis.pruefeEinheit(cEinheit2);

								if (bibMSG.get_bHasAlarms())
								{
									return;
								}


								RECORDNEW_ARTIKEL  recNewArtikel = new RECORDNEW_ARTIKEL();
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ANR1(cANR1));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_AKTIV("Y"));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ARTBEZ1(recNewSort.get_ARTBEZ1_cF_NN("")));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ARTBEZ2(recNewSort.get_ARTBEZ2_cUF_NN("")));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ID_ARTIKEL_GRUPPE(recGruppe.get_ID_ARTIKEL_GRUPPE_cUF()));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_GEFAHRGUT(S.isFull(recNewSort.get_IST_GEFAEHRLICH_cUF_NN(""))?"Y":"N"));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_IST_PRODUKT(S.isFull(recNewSort.get_IST_PRODUKT_cUF_NN(""))?"Y":"N"));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_DIENSTLEISTUNG(S.isFull(recNewSort.get_IST_DIENSTLEISTUNG_cUF_NN(""))?"Y":"N"));

								if (oThis.pruefe_basel_oecd_code(recNewSort.get_BASEL_cUF_NN(""))==2)
								{
									bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_BASEL_CODE(recNewSort.get_BASEL_cUF_NN("")));
								}
								else
								{
									bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_EUCODE(recNewSort.get_BASEL_cUF_NN("")));
								}


								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ZOLLTARIFNR(recNewSort.get_ZOLLTARIF_cUF_NN("")));

								//bei dienstleistungen keine codes
								if (S.isFull(recNewSort.get_IST_DIENSTLEISTUNG_cUF_NN("")))
								{
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_CODE(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ZOLLTARIFNR(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUCODE(""));
								}


								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_GENAUIGKEIT_MENGEN(3));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ID_EINHEIT(eh1.get_ID_EINHEIT_cUF()));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_ID_EINHEIT_PREIS(eh2.get_ID_EINHEIT_cUF()));
								bibMSG.add_MESSAGE(recNewArtikel.set_NEW_VALUE_MENGENDIVISOR(cMengenDivisor));

								if (bibMSG.get_bHasAlarms())
								{
									return;
								}

								recArtikel = recNewArtikel.do_WRITE_NEW_ARTIKEL(bibMSG.MV());
								oThis.pruefe_codes(recArtikel, recNewSort);

								oThis.iCountArtikelNew++;


								//jetzt die artikel-nummer auch als artbez anlegen
								oThis.add_ArtBez(recNewSort);


								if (bibMSG.get_bHasAlarms())
								{
									return;
								}


							}
							else
							{

								//einheit pruefen
								String cEinheit1 = recNewSort.get_EINHEIT1_cUF_NN("kg");
								String cEinheit2 = cEinheit1;
								String cMengenDivisor = "1";
								if (cEinheit1.equals("kg"))
								{
									cEinheit2 = "to";
									cMengenDivisor="1000";
								}

								RECORD_EINHEIT  eh1 = oThis.pruefeEinheit(cEinheit1);
								RECORD_EINHEIT  eh2 = oThis.pruefeEinheit(cEinheit2);

								if (bibMSG.get_bHasAlarms())
								{
									return;
								}



								recArtikel = new RECORD_ARTIKEL(cSorteTest[0][0]);

								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ANR1(cANR1));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_AKTIV("Y"));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ARTBEZ1(recNewSort.get_ARTBEZ1_cF_NN("")));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ARTBEZ2(recNewSort.get_ARTBEZ2_cUF_NN("")));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ID_ARTIKEL_GRUPPE(recGruppe.get_ID_ARTIKEL_GRUPPE_cUF()));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_GEFAHRGUT(S.isFull(recNewSort.get_IST_GEFAEHRLICH_cUF_NN(""))?"Y":"N"));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_IST_PRODUKT(S.isFull(recNewSort.get_IST_PRODUKT_cUF_NN(""))?"Y":"N"));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_DIENSTLEISTUNG(S.isFull(recNewSort.get_IST_DIENSTLEISTUNG_cUF_NN(""))?"Y":"N"));

								//bei dienstleistungen keine codes
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUCODE(""));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUNOTIZ(""));

								if (oThis.pruefe_basel_oecd_code(recNewSort.get_BASEL_cUF_NN(""))==2)
								{
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_CODE(recNewSort.get_BASEL_cUF_NN("")));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUCODE(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUNOTIZ(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_NOTIZ(""));
								}
								else
								{
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_CODE(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUCODE(recNewSort.get_BASEL_cUF_NN("")));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUNOTIZ(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_NOTIZ(""));
								}


								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ZOLLTARIFNR(recNewSort.get_ZOLLTARIF_cUF_NN("")));

								if (S.isFull(recNewSort.get_IST_DIENSTLEISTUNG_cUF_NN("")))
								{
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_CODE(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ZOLLTARIFNR(""));
									bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUCODE(""));
								}

								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ID_EINHEIT(eh1.get_ID_EINHEIT_cUF()));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ID_EINHEIT_PREIS(eh2.get_ID_EINHEIT_cUF()));
								bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_MENGENDIVISOR(cMengenDivisor));

								bibMSG.add_MESSAGE(recArtikel.UPDATE(null, true));
								oThis.pruefe_codes(recArtikel, recNewSort);


								oThis.iCountArtikelUpdate++;

								if (oThis.iCountArtikelUpdate<10)
								{
									oThis.cDoubleHauptSorten+="/"+recNewSort.get_ANR1_1_cUF_NN("")+"-"+recNewSort.get_ANR1_2_cUF_NN("");
								}


								//jetzt die artikel-nummer auch als artbez anlegen
								oThis.add_ArtBez(recNewSort);
								if (bibMSG.get_bHasAlarms())
								{
									return;
								}



							}
							if (bibMSG.get_bHasAlarms())
							{
								return;
							}
						}
						else            //dann ist es artikelbezeichnung
						{
							oThis.add_ArtBez(recNewSort);
						}
					}

					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Import beendet !!"));
					}

				}

				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
		}


		private void add_ArtBez(RECORD_TEMP_IMPORT_DEF  recNewSort) throws myException
		{

			String cANR2 = recNewSort.get_ANR2_cUF_NN("");

			String[][] cArtBezTest = bibDB.EinzelAbfrageInArray("SELECT ID_ARTIKEL_BEZ FROM JT_ARTIKEL_BEZ WHERE ID_ARTIKEL="+
					recArtikel.get_ID_ARTIKEL_cUF()+" AND ANR2="+bibALL.MakeSql(cANR2));

			if (cArtBezTest==null)
			{
				throw new myException("Error: False Query for ARTIKEL_BEZ "+recNewSort.get_ANR1_1_cUF_NN("<anr1>")+"-"+recNewSort.get_ARTBEZ1_cUF_NN("<artbez1>"));
			}
			else if (cArtBezTest.length>1)
			{
				throw new myException("Error: Duplicate Value for ARTIKEL_BEZ");
			}
			else if (cArtBezTest.length==0)
			{

				RECORDNEW_ARTIKEL_BEZ  recNewArtikelBez = new RECORDNEW_ARTIKEL_BEZ();
				bibMSG.add_MESSAGE(recNewArtikelBez.set_NEW_VALUE_ID_ARTIKEL(recArtikel.get_ID_ARTIKEL_cUF()));
				bibMSG.add_MESSAGE(recNewArtikelBez.set_NEW_VALUE_ANR2(cANR2));
				bibMSG.add_MESSAGE(recNewArtikelBez.set_NEW_VALUE_ARTBEZ1(recNewSort.get_ARTBEZ1_cF_NN("")));
				bibMSG.add_MESSAGE(recNewArtikelBez.set_NEW_VALUE_ARTBEZ2(recNewSort.get_ARTBEZ2_cF_NN("")));
				bibMSG.add_MESSAGE(recNewArtikelBez.set_NEW_VALUE_AKTIV("Y"));

				recArtikelBez = recNewArtikelBez.do_WRITE_NEW_ARTIKEL_BEZ(bibMSG.MV());
				this.iCountArtikelBezNew++;
			}
			else
			{
				recArtikelBez = new RECORD_ARTIKEL_BEZ(cArtBezTest[0][0]);

				bibMSG.add_MESSAGE(recArtikelBez.set_NEW_VALUE_ID_ARTIKEL(recArtikel.get_ID_ARTIKEL_cUF()));
				bibMSG.add_MESSAGE(recArtikelBez.set_NEW_VALUE_ANR2(cANR2));
				bibMSG.add_MESSAGE(recArtikelBez.set_NEW_VALUE_ARTBEZ1(recNewSort.get_ARTBEZ1_cF_NN("")));
				bibMSG.add_MESSAGE(recArtikelBez.set_NEW_VALUE_ARTBEZ2(recNewSort.get_ARTBEZ2_cF_NN("")));
				bibMSG.add_MESSAGE(recArtikelBez.set_NEW_VALUE_AKTIV("Y"));

				bibMSG.add_MESSAGE(recArtikelBez.UPDATE(null, true));

				iCountArtikelBezUpdate++;

				//System.out.println(recArtikel.get_ANR1_cUF()+"-"+recArtikelBez.get_ANR2_cUF());

			}

			if (bibMSG.get_bHasAlarms())
			{
				return;
			}

		}


		/**
		 * Rueckgabe: 0=leer, 1=OECD, 2=BASEL
		 */
		private int pruefe_basel_oecd_code(String cCode)
		{
			//zuerst schaunen, was fuer ein code-typ es ist (BASEL: B1010   / OECD: GA430)
			String cTest = "";
			String cHelp = S.NN(cCode);

			for (int i=0; i<cHelp.length();i++)
			{
				if (S.isFull(cHelp.substring(i,i+1)))
				{
					cTest+=cHelp.substring(i,i+1);
				}
			}

			// cTest ist ohne leerstellen
			if (S.isEmpty(cTest.trim()))
			{
				return 0;
			}
			else if (cTest.length()>=2)
			{
				if (bibALL.isInteger(cTest.substring(1,2)))
				{
					return 2;
				}
				else
				{
					return 1;
				}
			}
			else
			{
				return 2;
			}
		}



		//fuellt die sortendefinierten BASEL/OECD-Code und Zolltarifnummer zugehoerenden Text
		//alle eintraege aus der Tabelle jt_oecd_code wird in den baselcode eingetragen
		private void pruefe_codes(RECORD_ARTIKEL recArtikel,RECORD_TEMP_IMPORT_DEF  recNewSort) throws myException
		{
			recArtikel.REBUILD();

			//basel-Code
			if (S.isFull(recArtikel.get_BASEL_CODE_cF()))
			{
				try 
				{
					RECORD_OECD_CODE recOECD_BASEL = new RECORD_OECD_CODE("OECD_CODE="+recArtikel.get_BASEL_CODE_VALUE_FOR_SQLSTATEMENT());
					bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_BASEL_NOTIZ(recOECD_BASEL.get_OECD_TEXT_cF()));
				} 
				catch (myException e) 
				{
					this.iCountKeinBASEL_OECD_TEXT++;
				}
			}

			//oecd (vormals EU)-Code
			if (S.isFull(recArtikel.get_EUCODE_cF()))
			{
				try 
				{
					RECORD_OECD_CODE recOECD_BASEL = new RECORD_OECD_CODE("OECD_CODE="+recArtikel.get_EUCODE_VALUE_FOR_SQLSTATEMENT());
					bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_EUNOTIZ(recOECD_BASEL.get_OECD_TEXT_cF()));
				} 
				catch (myException e) 
				{
					this.iCountKeinBASEL_OECD_TEXT++;
				}
			}






			//zolltarifcode
			if (S.isFull(recArtikel.get_ZOLLTARIFNR_cUF()))
			{
				try 
				{
					RECORD_ZOLLTARIFNUMMER recZoll = new RECORD_ZOLLTARIFNUMMER("NUMMER="+recArtikel.get_ZOLLTARIFNR_VALUE_FOR_SQLSTATEMENT());

					String cText = recZoll.get_TEXT1_cUF_NN("")+"\n"+
							recZoll.get_TEXT2_cUF_NN("")+"\n"+
							recZoll.get_TEXT3_cUF_NN("")+"\n";

					bibMSG.add_MESSAGE(recArtikel.set_NEW_VALUE_ZOLLTARIFNOTIZ(cText));
				} 
				catch (myException e) 
				{
					this.iCountKeinZOLLTEXT++;
				}
			}

			//avv-Code
			try 
			{
				recArtikel.set_NEW_VALUE_ID_EAK_CODE(this.get_ID_AVV_CODE(recNewSort.get_AVV_IN_cUF()));
			} 
			catch (Exception e) 
			{
				this.iCountAVV_IN_ERR++;

				this.cNotFoundAVV_Codes+="/"+recNewSort.get_AVV_IN_cUF();
			}

			try 
			{
				recArtikel.set_NEW_VALUE_ID_EAK_CODE_EX_MANDANT(this.get_ID_AVV_CODE(recNewSort.get_AVV_OUT_cUF()));
			} 
			catch (Exception e) 
			{
				this.iCountAVV_OUT_ERR++;
				this.cNotFoundAVV_Codes+="/"+recNewSort.get_AVV_OUT_cUF();
			}

			if (recArtikel.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
			{
				bibMSG.add_MESSAGE(recArtikel.UPDATE(null, true));
			}


		}

		// uebernimmt den EAK-Code in der Form: 19 05 13*
		private String get_ID_AVV_CODE(String cAVV_Code) throws myException 
		{
			String cID_EAK_CODE = null;

			if (S.isFull(cAVV_Code))
			{
				//in 3 codebloecke uebernehmen
				Vector<String> vKeys = bibALL.TrenneZeile(cAVV_Code.trim(), " ");

				if (vKeys.size()==3)
				{
					RECORD_EAK_BRANCHE recBranche = new RECORD_EAK_BRANCHE("JT_EAK_BRANCHE.KEY_BRANCHE="+bibALL.MakeSql(vKeys.get(0)));;

					RECORD_EAK_GRUPPE  recGruppe  = new RECORD_EAK_GRUPPE("JT_EAK_GRUPPE.KEY_GRUPPE="+bibALL.MakeSql(vKeys.get(1))+" AND "+
							"JT_EAK_GRUPPE.ID_EAK_BRANCHE="+recBranche.get_ID_EAK_BRANCHE_cUF());

					RECORD_EAK_CODE  recCode  = new RECORD_EAK_CODE("JT_EAK_CODE.KEY_CODE="+bibALL.MakeSql(vKeys.get(2).substring(0,2))+" AND "+
							"JT_EAK_CODE.ID_EAK_GRUPPE="+recGruppe.get_ID_EAK_GRUPPE_cUF());

					cID_EAK_CODE = recCode.get_ID_EAK_CODE_cUF();
				}
			}
			return cID_EAK_CODE;
		}



		private RECORD_EINHEIT pruefeEinheit(String cEinheit) throws myException
		{
			//nachsehen, ob es einen Artikelbereich dieses namens gibt
			String[][] cEinheitTest = bibDB.EinzelAbfrageInArray("SELECT ID_EINHEIT FROM JT_EINHEIT WHERE UPPER(EINHEITKURZ)="+bibALL.MakeSql(cEinheit.trim().toUpperCase()));

			if (cEinheitTest==null)
			{
				throw new myException("Error: False Query for EINHEIT");
			}
			else if (cEinheitTest.length>1)
			{
				throw new myException("Error: Duplicate Value for EINHEIT");
			}
			else if (cEinheitTest.length==0)
			{
				RECORDNEW_EINHEIT  recNewEinheit = new RECORDNEW_EINHEIT();
				recNewEinheit.set_NEW_VALUE_EINHEITKURZ(cEinheit);
				recNewEinheit.set_NEW_VALUE_EINHEITLANG(cEinheit);

				return recNewEinheit.do_WRITE_NEW_EINHEIT(bibMSG.MV());
			}
			else
			{
				return new RECORD_EINHEIT(cEinheitTest[0][0]);
			}
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
	private class testeForderungspruefung extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("Forderungsprüfung für Fuhre");
		MyE2_TextField tfIDFuhre = new MyE2_TextField();


		public testeForderungspruefung() throws myException{
			super();
			IMG_ImageCapture_Handler oImgCapture = new  IMG_ImageCapture_Handler(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE);

			this.add(new MyE2_Label("Fuhre prüfen:"));
			this.add( tfIDFuhre);
			this.add( btnTest);

			btnTest.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					STATKD_StatusErmittlung_Kreditversicherung o = new STATKD_StatusErmittlung_Kreditversicherung();
					boolean bRet =  o.pruefeFuhre(tfIDFuhre.getText());

					// warnungen schreiben
					o.warnung_FuhreBestaetigt();


					if (!bRet){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("ACHTUNG: der Forderungsbetrag ist höher als der KV-Betrag"));
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
	private class testeXMLErzeugung extends MyE2_Row{

		MyE2_Button   btnTest = new MyE2_Button("XML file erzeugen");

		public testeXMLErzeugung() throws myException{
			super();

			this.add(new MyE2_Label("Erzeuge XML: "));
			this.add(btnTest,E2_INSETS.I_10_0_0_0);

			btnTest.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					XStreamWriterListDefs o = new XStreamWriterListDefs("TESTxml");
				}
			});
		}


	}


	private class testePDFImprint extends MyE2_Row{
		MyE2_Button	btnTest = new MyE2_Button("Text in PDF eindrucken");



		MyE2_TextField tfImprint1 = new MyE2_TextField("Text1", 100, 20);
		MyE2_TextField tfImprint2 = new MyE2_TextField("Text2", 100, 20);
		MyE2_TextField tfImprint3 = new MyE2_TextField("Text3", 100, 20);


	}

	//	
	//	private class fibu_test_button extends MyE2_Button {
	//
	//		public fibu_test_button()
	//		{
	//			super("FIBU-TEST");
	//			this.add_oActionAgent(new ownAction());
	//		}
	//		
	//		private class ownAction extends XX_ActionAgent {
	//			@Override
	//			public void executeAgentCode(ExecINFO oExecInfo) throws myException
	//			{
	//				String cSQL = 
	//						"UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='Y' WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND BUCHUNGSBLOCK_NR=40005";
	//				
	//				bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(cSQL), true);
	//			}
	//			
	//		}
	//		
	//	}
	//	


	//	private class ownButtonTestSelect extends MyE2_Button {
	//
	//		public ownButtonTestSelect() {
	//			super(new MyE2_String("Test-Call-Select"));
	//			this.add_oActionAgent(new ownActionAgent());
	//		}
	//		
	//		private class ownActionAgent extends XX_ActionAgent {
	//
	//			@Override
	//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
	//				new TP_KST_SELECT_RelevantSorts_TEST();
	//			}
	//			
	//		}
	//	}



	private class ownTestZippenBeliebigerDateien extends MyE2_Button {

		public ownTestZippenBeliebigerDateien() {
			super(new MyE2_String("Tests Zippen mehrere Dateien"));
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new ownPopup();
			}



			private class ownPopup extends E2_BasicModuleContainer {

				Vector<MyE2_TextField> v_TF_Namen = new Vector<MyE2_TextField>();
				Vector<MyE2_TextField> v_TF_Dateipfad = new Vector<MyE2_TextField>();

				HashMap<String, String> hm1 = null;
				HashMap<String, String> hm2 = null;


				public ownPopup() throws myException {
					super();

					this.v_TF_Namen.add(new MyE2_TextField("datei1.txt", 300, 100));
					this.v_TF_Namen.add(new MyE2_TextField("datei2.txt", 300, 100));
					this.v_TF_Namen.add(new MyE2_TextField("datei3.txt", 300, 100));

					this.v_TF_Dateipfad.add(new MyE2_TextField("/daten/Topologie_Netze_1e.ods", 300, 100));
					this.v_TF_Dateipfad.add(new MyE2_TextField("/daten/Topologie_Netze_1-sicher.ods", 300, 100));
					this.v_TF_Dateipfad.add(new MyE2_TextField("/daten2/wallpapers/sky.png", 300, 100));

					MyE2_Grid oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());

					for (int i=0;i<this.v_TF_Namen.size();i++) {
						oGridHelp.add(this.v_TF_Namen.get(i), 1, E2_INSETS.I(2,2,2,2));
						oGridHelp.add(this.v_TF_Dateipfad.get(i), 1, E2_INSETS.I(2,2,2,2));
					}

					MyE2_Button oBT_Los = new MyE2_Button(new MyE2_String("ZippenUndDownload"));
					oBT_Los.add_oActionAgent(new ownActionZipAndDownload());
					oGridHelp.add(oBT_Los, 2, E2_INSETS.I(2,2,2,2));
					oGridHelp.add(new ownSelectPrinter(), 2, E2_INSETS.I(2,2,2,2));
					oGridHelp.add(new ownButtonGenerateStamp(), 2, E2_INSETS.I(2,2,2,2));
					oGridHelp.add(new ownButtonTestFingerPrintCompare(), 2, E2_INSETS.I(2,2,2,2));


					this.add(oGridHelp,E2_INSETS.I(10,10,10,10));

					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("ZIP-Test"));

				}


				private class ownActionZipAndDownload extends XX_ActionAgent {

					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {

						Vector<ZIP_NG_NamePair>  oPairs = new Vector<ZIP_NG_NamePair>();
						oPairs.add(new ZIP_NG_NamePair(v_TF_Namen.get(0).getText(), v_TF_Dateipfad.get(0).getText()));
						oPairs.add(new ZIP_NG_NamePair(v_TF_Namen.get(1).getText(), v_TF_Dateipfad.get(1).getText()));
						oPairs.add(new ZIP_NG_NamePair(v_TF_Namen.get(2).getText(), v_TF_Dateipfad.get(2).getText()));


						ZIP_NG_Creator oZip = new ZIP_NG_Creator(oPairs, "SoHeisstDerDownload.zip");

						oZip.startDownload();
					}

				}


				private class ownSelectPrinter extends MyE2_SelectField {
					public ownSelectPrinter() throws myException {
						super(new E2_DropDownSettingsNew("SELECT NAME,ID_DRUCKER FROM JT_DRUCKER WHERE NVL(AKTIV,'N')='Y'",true,false).getDD(), "", false, new Extent(300));

						this.add_oActionAgent(new ownActionAgentDrucker());
					}

					private class ownActionAgentDrucker extends XX_ActionAgent {

						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							if (S.isFull(ownSelectPrinter.this.get_ActualWert())) {
								RECORD_DRUCKER recDrucker = new RECORD_DRUCKER(ownSelectPrinter.this.get_ActualWert());

								for (MyE2_TextField tfDatei: v_TF_Dateipfad) {
									new Print_via_DirectPrinterDefinition(tfDatei.getText(), recDrucker);
								}


							}
						}
					}
				}


				private class ownButtonGenerateStamp extends MyE2_Button {

					public ownButtonGenerateStamp() {
						super("Teste Fingerprint-Funktion");

						this.add_oActionAgent(new XX_ActionAgent() {

							@Override
							public void executeAgentCode(ExecINFO oExecInfo) throws myException {
								HashMap<String, String> hmFingerPrint = new E2_Recursive_Search_Generate_Fingerprint().get_hmFingerPrint();

								if (ownPopup.this.hm1==null) {
									ownPopup.this.hm1=new E2_Recursive_Search_Generate_Fingerprint().get_hmFingerPrint();
									bibMSG.add_MESSAGE(new MyE2_Info_Message("1 gefüllt"));
								} else if (ownPopup.this.hm2==null) {
									ownPopup.this.hm2=new E2_Recursive_Search_Generate_Fingerprint().get_hmFingerPrint();
									if (bibHASHMAP.Are_Equal_HASHMAPS_INTERSECT(ownPopup.this.hm1, ownPopup.this.hm2)) {
										bibMSG.add_MESSAGE(new MyE2_Warning_Message("GLEICH"));
									} else {
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message("VERSCHIEDEN"));

										HashMap<String, String> hmDiff = bibHASHMAP.get_Diff_HASHMAP_Entrys_INTERSECT(hm1, hm2);
										DEBUG.System_print(hmDiff);
										DEBUG.System_println("Anzahl hm1:"+hm1.size()+" ...  "+"Anzahl hm2:"+hm2.size());

									}
									ownPopup.this.hm1=null;
									ownPopup.this.hm2=null;
								}
							}
						});


					}

				}



				private class ownButtonTestFingerPrintCompare extends MyE2_Button {

					public ownButtonTestFingerPrintCompare() {
						super("Teste Ueberstimmen-Funktion");

						this.add_oActionAgent(new XX_ActionAgent() {

							@Override
							public void executeAgentCode(ExecINFO oExecInfo) throws myException {

								MyE2_Button oButOrig = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

								if (bibDECISIONS.COMPARE_SESSION_FingerPrints_WithActualFingerPrint(oButOrig.EXT().get_UUID(), this.get_UUID(), new FingerPrint_Screen())) {
									bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("ALLES Paletti (1)!")));
								} else {
									//pruefcode 1
									bibDECISIONS.SAVE_VALID_FingerPrint_TO_SESSION_STEP1(oButOrig.EXT().get_UUID(), this.get_UUID(), new FingerPrint_Screen());


									//hier steht der pruefcode
									bibMSG.add_MESSAGE(new ownMessage(
											new MyE2_String("Achtung! Die Kreditsumme der Firma ist überschritten !"),
											new MyE2_String("Trotzdem ausführen"),oButOrig,this.get_UUID(),false));

									bibMSG.MV().get(bibMSG.MV().size()-1).set_bMustBeExpandedAtStart(true);

								}
							}
						});



						this.add_oActionAgent(new XX_ActionAgent() {

							@Override
							public void executeAgentCode(ExecINFO oExecInfo) throws myException {

								MyE2_Button oButOrig = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

								if (bibDECISIONS.COMPARE_SESSION_FingerPrints_WithActualFingerPrint(oButOrig.EXT().get_UUID(), this.get_UUID(), new FingerPrint_Screen())) {
									bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("ALLES Paletti (2)!")));
								} else {
									//pruefcode 1
									bibDECISIONS.SAVE_VALID_FingerPrint_TO_SESSION_STEP1(oButOrig.EXT().get_UUID(), this.get_UUID(), new FingerPrint_Screen());


									//hier steht der pruefcode
									bibMSG.add_MESSAGE(new ownMessage(
											new MyE2_String("Achtung! Die Kreditsumme der Firma ist immer noch  überschritten !"),
											new MyE2_String("Trotzdem ausführen"),oButOrig,this.get_UUID(),true));


								}
							}
						});



					}
				}

			}
		}


		private class ownMessage extends MyE2_AlarmMessage_4_Confirm_STD {

			public ownMessage(	MyString 					cmessage, 	
					MyString 					Text_4_OK_Button, 
					E2_IF_Handles_ActionAgents 	oComponentCalling, 
					UUID 						uuidActionAgent_OR_Validator,
					boolean isLastAction) throws myException {
				super(		cmessage, 
						Text_4_OK_Button, 
						new MyE2_String("Abbruch"), 
						500, 
						120,
						100,
						oComponentCalling, 
						uuidActionAgent_OR_Validator,
						new FingerPrint_Generator_Dummy());

				this.get_oButtonOK().add_oActionAgent(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownMessage.this.get_WindowPane4ShowingMessages(ownMessage.this).set_bCloseEventIsFromWindowButtonRightCorner(false);
						ownMessage.this.get_WindowPane4ShowingMessages(ownMessage.this).userClose();
					}
				},true);

				this.get_oButtonCancel().add_oActionAgent(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownMessage.this.get_WindowPane4ShowingMessages(ownMessage.this).set_bCloseEventIsFromWindowButtonRightCorner(false);
						ownMessage.this.get_WindowPane4ShowingMessages(ownMessage.this).userClose();
					}
				},true);



				if (isLastAction) {
					this.get_oButtonOK().add_oActionAgent(new XX_ActionAgent() {
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							bibDECISIONS.CLEAN_Component_Fingerprint_in_SESSION(ownMessage.this.get_uuid_ComponentWhichStartedThis());
						}
					});
				}
			}
		}


	}




	private class ownGridWithTestButtons extends MyE2_Grid {
		private MyE2_Button  oBT1 = new MyE2_Button("Steuert fern");
		private MyE2_Button  oBT2 = new MyE2_Button("Wird gesteuert");

		public ownGridWithTestButtons()
		{
			super(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER());

			this.oBT1.add_oActionAgent(new ownActionAgentSteuertFern());
			this.oBT2.add_oActionAgent(new ownActionAgentWirdGesteuert());

			this.add(oBT1);
			this.add(oBT2);
		}

		private class ownActionAgentSteuertFern extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				DEBUG.System_println(oExecInfo.get_MyActionEvent().getSource().toString());
				oBT2.doActionPassiv();
			}

		}

		private class ownActionAgentWirdGesteuert extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				DEBUG.System_println(oExecInfo.get_MyActionEvent().getSource().toString());
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Hallo")));
			}
		}



	}



	private class ownButtonToScan extends MyE2_Button {

		public ownButtonToScan() {
			super(new MyE2_String("Scan starten"));
			this.add_oActionAgent(new ownActionScan());
		}


		private class ownActionScan extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				String cLocalIP = bibServer.get_LocalIpAdress();
				if (S.isFull(cLocalIP)) {

					String cShellCommandScan = "ssh -l root 192.168.0.226 /batch/scan-canon1.sh test /tmp/ 600";

					String cShellCommandCopy = "scp root@192.168.0.226:/tmp/test.pdf /"+bibALL.get_WEBROOTPATH()+"/"+bibALL.get_OUTPUTPATH()+"/";
					String cShellCommandDel  = "ssh -l root 192.168.0.226 rm /tmp/test.pdf";


					try {

						Process p = Runtime.getRuntime().exec(cShellCommandScan);
						p.waitFor();

						Process p2 = Runtime.getRuntime().exec(cShellCommandCopy);
						p2.waitFor();

						Process p3 = Runtime.getRuntime().exec(cShellCommandDel);
						p3.waitFor();


						File oFile = new File("/"+bibALL.get_WEBROOTPATH()+"/"+bibALL.get_OUTPUTPATH()+"/"+"test.pdf");
						oFile.deleteOnExit();

						E2_Download oDownload = new E2_Download();
						oDownload.starteFileDownload("/"+bibALL.get_WEBROOTPATH()+"/"+bibALL.get_OUTPUTPATH()+"/"+"test.pdf","application/pdf");

					}  
					catch (InterruptedException ex) {
						ex.printStackTrace(); 
					}

					catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					throw new myException("IP-Adress cannot be found!");
				}
			}

		}

	}



	private class test_passivKlick extends MyE2_Grid {

		MyE2_Button  but1_direct = new MyE2_Button("Direkter-button");
		MyE2_Button  but2_indirect = new MyE2_Button("Indirekt-button");

		public test_passivKlick() throws myException {
			super();

			MyE2_Grid  grid = new MyE2_Grid(1);
			this.add(grid, E2_INSETS.I(3,3,3,3));

			grid.add(but1_direct);
			grid.add(but2_indirect);


			but1_direct.add_oActionAgent(new ownActionAgentKlickDirect());
			but1_direct.add_GlobalValidator(new XX_ActionValidator() {
				@Override
				protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
					return null;
				}

				@Override
				public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException {
					return new MyE2_MessageVector(new MyE2_Warning_Message(new MyE2_String("Warnung")));
				}
			});

			but2_indirect.add_oActionAgent(new ownActionAgentKlickInDirect());
			but2_indirect.add_oActionAgent(new ownActionAgentKlickdurch());
		}

		private class ownActionAgentKlickdurch extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				but1_direct.doActionPassiv();
			}

		}


		private class ownActionAgentKlickDirect extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				MyE2_Button  butTes = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

				DEBUG.System_println("Ausführender button "+butTes.getText());
			}

		}

		private class ownActionAgentKlickInDirect extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				MyE2_Button  butTes = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

				DEBUG.System_println("Ausführender button "+butTes.getText());
			}

		}

	}


	private class TestButtonOwnSelect extends MyE2_Button {

		public TestButtonOwnSelect() {
			super(new MyE2_String("Teste Select"));
			this.add_oActionAgent(new ownActionTestSel());
		}

		private class ownActionTestSel extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				SEL subsel = new SEL("COUNT(*)").FROM(VKOPF_RG.T()).WHERE(VKOPF_RG.id_adresse,ADRESSE.id_adresse.t("A")).SET_ALIAS("Z_RG");
				SEL  sel1 = new SEL(subsel).
						ADDFIELD(ADRESSE.gen_FieldList("A", "A_")).
						ADDFIELD("L.*").
						FROM(ADRESSE.T("A"))
						.LEFTOUTER(FIRMENINFO.T("F"),ADRESSE.id_adresse.t("A"), FIRMENINFO.id_adresse.t("F"))
						.JOIN(new LeftOuterJoin(LAND.T("L"), new And(ADRESSE.id_land.t("A"), LAND.id_land.t("L"))))
						.WHERE(new vgl("A",ADRESSE.id_adresse,"5000"))
						.OR(ADRESSE.name1.t("A"), "like","'%leber%'")
						.OR(new vgl("A",ADRESSE.name1, "Meier"))
						.OR(new vgl("A",ADRESSE.letzte_aenderung, "01.01.2011"))
						.AND(new vgl("A",ADRESSE.id_adresse,COMP.GE,"1000"))
						;

				sel1.s();

			}

		}


	}



	//	private class ownButtonTestSelectObj extends MyE2_Button {
	//
	//		public ownButtonTestSelectObj() {
	//			super(new MyE2_String("Test-Call-Select"));
	//			this.add_oActionAgent(new ownActionAgent());
	//		}
	//		
	//		private class ownActionAgent extends XX_ActionAgent {
	//
	//			@Override
	//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
	//				new TP_KST_SELECT_RelevantSorts_TEST();
	//			}
	//			
	//		}
	//	}



	private class testBtDynamischerVector extends E2_ButtonPopupPasswordQuery {


		public testBtDynamischerVector() throws myException {
			super(new MyE2_String("passwort-button"));
			this.add_oActionAgent(new ownActionAgent());
			this.add_oActionAgent(new ownActionAgent2());
		}

		private class ownActionAgent extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kein passwort ...")));
			}
		}

		private class ownActionAgent2 extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kein passwort die zweite ...")));
			}
		}


		@Override
		public String get_password_for_this_button() throws myException {
			return "Hello Kitty";
		}

		@Override
		public MyE2_Button generate_password_confirm_button()	throws myException {
			return new MyE2_Button("TEST");
		}

		@Override
		public E2_BasicModuleContainer generate_password_confirm_popup_modulcontainer() throws myException {
			return new E2_BasicModuleContainer();
		}

	}



	/**
	 * Klasse zum testen der Multiselector-Componente
	 * @author manfred
	 *
	 */
	private class testMultiselectorComponent extends MyE2_Row implements E2IF_MultiValueSelectorContainer{

		MyE2_TextField c_feld1 = new MyE2_TextField();
		MyE2_TextField c_feld2 = new MyE2_TextField();
		MyE2_TextField c_feld3 = new MyE2_TextField();
		MyE2_TextField c_feld4 = new MyE2_TextField();
		MyE2_TextField c_feld5 = new MyE2_TextField();

		MyE2_Button c_buttonForPopUp = new MyE2_Button("MultiValueSelectorContainer anrufen");
		MyE2_Button c_resetFields = new MyE2_Button("Reset fields");

		MultiValueSelectorContainer mvsc = null;
		private MyE2_Button m_testButton;

		public testMultiselectorComponent() {
			super();
			this.add(c_feld1);
			this.add(c_feld2);
			this.add(c_feld3);
			this.add(c_feld4);
			this.add(c_feld5);
			this.add(c_buttonForPopUp);
			this.add(c_resetFields);
			c_buttonForPopUp.add_oActionAgent(new ownActionAgent(this));
			c_resetFields.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					c_feld1.setText("");
					c_feld2.setText("abba");
					c_feld3.setText("");
					c_feld4.setText("");
					c_feld5.setText("");
				}
			});

			m_testButton = new MyE2_Button("make some things");
			m_testButton.add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibMSG.add_MESSAGE(new MyE2_Message(new MyString("I've make something"), MyE2_Message.TYP_INFO, false));

				}
			});
			c_feld2.setText("abba");
		}

		@Override
		public void refreshParentComponents() throws myException {
			c_feld1.setText(mvsc.getValueFromComponent("cmp1").toString());
			c_feld2.setText(mvsc.getValueFromComponent("cmp2").toString());
			c_feld3.setText(mvsc.getValueFromComponent("cmp3").toString());
			c_feld4.setText(mvsc.getValueFromComponent("cmp4").toString());
			c_feld5.setText(mvsc.getValueFromComponent("cmp5").toString());		
		}

		@Override
		public void getValueFromParentComponents() throws myException {
			mvsc.setValue("cmp1", c_feld1.getText());
			mvsc.setValue("cmp2", c_feld2.getText());
			mvsc.setValue("cmp3", c_feld3.getText());
			mvsc.setValue("cmp4", c_feld4.getText());
			mvsc.setValue("cmp5", c_feld5.getText());
		}

		private class SpecialSelector_MVSC extends E2_CalendarComponent_WithDayButtons implements E2IF_MultiValueSelectorItem{

			public SpecialSelector_MVSC(String cActualDayFormated ) throws myException {
				super(cActualDayFormated);
			}

			@Override
			public void setValue(Object o) {

			}

			@Override
			public String getValue() {
				return get_cSelectedDayFormated();
			}

			@Override
			public void doDayButtonAction(ExecINFO execInfo) throws myException {

			}	
		}

		private class SpecialTextField extends MyE2_TextField implements E2IF_MultiValueSelectorItem{

			@Override
			public void setValue(Object o) {
				setText((String) o);

			}

			@Override
			public Object getValue() {
				return getText();
			}

		}

		private class ownActionAgent extends XX_ActionAgent {

			private testMultiselectorComponent m_parent;			

			public ownActionAgent(testMultiselectorComponent parent) {
				m_parent = parent;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				mvsc = new MultiValueSelectorContainer(m_parent,MultiValueSelector_SaveKeySizeofPopup.FUHREN_SIXPACK_DATUMSWERTE);
				mvsc.addComponent("cmp1", new MyE2_Label("Component 1"),new SpecialSelector_MVSC("01.01.2015") )
				.addComponent("cmp2", new MyE2_Label("Textfield Demo"),new SpecialTextField() )
				.addComponent("cmp3", null, new SpecialSelector_MVSC("30.01.2015"))
				.addComponent("cmp4", new MyE2_Label("HELLO"), new SpecialSelector_MVSC("01.05.2015"))
				.addComponent("cmp5", new SpecialSelector_MVSC("05.01.2015"));
				mvsc.setGridWidth(3);
				mvsc.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("MultiValueSelectorContainer developement"));
			}
		}


	}



	private class testMessageMailer extends MyE2_Button {

		public testMessageMailer() {

			super(new MyE2_String("Teste Maile unversandte Nachrichten..."));
			this.add_oActionAgent(new actionMaileMessages());
		}

		private class actionMaileMessages extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				MESSAGE_Email_Handler mailer  = new MESSAGE_Email_Handler();

				mailer.sendAllOpenMails();

			}
		}
	}

	private class Test_E2_CalendarComponent_Version2 extends MyE2_Grid{

		public Test_E2_CalendarComponent_Version2() {
			super(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			try {
				E2_TF_4_Date oCompOld = new E2_TF_4_Date(bibALL.get_cDateNOW(),70);
				oCompOld.setPopUpInSmallMode(true);
				oCompOld.setSelectionMode(E2_TF_4_Date_Enum.OLD_SELECTION_MODE);
				
				E2_TF_4_Date oCompDirect = new E2_TF_4_Date(bibALL.get_cDateNOW(),70);
				oCompDirect.setPopUpInSmallMode(true);
				oCompDirect.setSelectionMode(E2_TF_4_Date_Enum.DIRECT_SELECTION_MODE);
				
				E2_TF_4_Date oCompSlip = new E2_TF_4_Date(bibALL.get_cDateNOW(),70);
				oCompSlip.setPopUpInSmallMode(true);
				oCompSlip.setSelectionMode(E2_TF_4_Date_Enum.SLIP_SELECTION_MODE);
				
				this.add(new MyE2_Label("Alt Modus"));
				this.add(oCompOld);
				
				this.add(new MyE2_Label("Direkt selektion modus"));
				this.add(oCompDirect);
				
				this.add(new MyE2_Label("Slip selektion modus"));
				this.add(oCompSlip);
				
			} catch (myException e) {
				e.printStackTrace();
			}


		}
	}

	
	private class ownButtonTestRec extends MyE2_Button {

		public ownButtonTestRec() {
			super(new MyE2_String("TEST-Records"));
			
			this.add_oActionAgent(new ownActionagent());
		}
		
		
		
		private class ownActionagent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				RECORD_ADRESSE ra = new RECORD_ADRESSE("5525"); 
				
				RECORD_LAND rl = ra.get_UP_RECORD_LAND_id_land();
				
				
				RECLIST_VPOS_TPA_FUHRE  rlf = ra.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_lager_start();
				
				
				rlf.get_ID_VPOS_TPA_FUHRE_hmLong(0l);
				
				
				Vector<String> vids = rlf.get_vKeyValues();
				
				DEBUG.System_println("anzahl:"+vids.size());
				
				for (int i=0;i<vids.size();i++) {
					RECORD_VPOS_TPA_FUHRE fu = rlf.get(i);
					
					
					if (fu.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null) {
						DEBUG.System_println("Spedition ...."+fu.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().
							get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().
							   get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cF_NN("-keine name"));						
						
					}
					
				}
				
				
				
			}
			
		}
		
	}
	
	public class fibuSearchTest extends MyE2_Grid{
		MyE2_TextField feld;
		public fibuSearchTest() {
			feld = new MyE2_TextField();
			MyE2_Button button = new MyE2_Button("Test Fibu Search");
			add(feld);
			add(button);
			
			button.add_oActionAgent(new ownActionAgent());
		
		}
		
		private class ownActionAgent extends XX_ActionAgent{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String fibuId = feld.getText();
				VerbundenFibuIdSuche test = new VerbundenFibuIdSuche();
				test.suche(fibuId);
			}
			
		}
	}
	
//	public class Rp_Replace_ClassTestCall extends MyE2_Grid{
//		public Rp_Replace_ClassTestCall() {
//			MyE2_Button button = new MyE2_Button("Rp_Replace");
//			button.add_oActionAgent(new ownActionAgent());
//			add(button);
//			
//			MyE2_Button button2 = new MyE2_Button("Test Filename cleaner");
//			button2.add_oActionAgent(new fileNameCleanerAgent());
//			add(button2);
//			
//		}
//		
//		private class ownActionAgent extends XX_ActionAgent{
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				RP_Replace_Test rprt = new RP_Replace_Test();
//				rprt.test();
//			}
//			
//		}
//		
//		private class fileNameCleanerAgent extends XX_ActionAgent{
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				try {
//					new UP_DOWN_FILENAME_Container();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
////				UP_DOWN_FileName_Checker udfc = new UP_DOWN_FileName_Checker();
////				try {
////					udfc.cleanFileNames();
////				} catch (UnsupportedEncodingException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//////				udfc.extractFileNameFromDB();
//// catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//			}
//			
//		}
//	}
	
	
	
	
	private class buttonCheckMailAdressSearcher extends MyE2_Button {

		public buttonCheckMailAdressSearcher() {
			super("Teste MailAdressSearcher");

			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					DEBUG.System_print(new SE_MailAdressSearcher(new RECORD_ADRESSE("3002"), MAILDEF.EMAIL_ANGEBOT, true, true, true).get_v_MailAdressesFound());
				}
			});
			
		}
		
	}
	
	
	private class testDropdownWithEnum extends MyE2_Grid{
		
		LAG_SelectBuchungsgewichte dd_test ;
		
		public testDropdownWithEnum() throws myException {
			dd_test = new LAG_SelectBuchungsgewichte();
			
			dd_test.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					LAG_SelectBuchungsgewichte.ENUM_SelektorLagerBuchungsgewichte 
					e_test = dd_test.getSelected();
					
					DEBUG.System_println(e_test.name() + "/" + e_test.getDescription()+ "/" + e_test.getType() + "/" + e_test.getMultiplikatorBuchhalterisch()+ "/" + e_test.getMultiplikatorLager() , DEBUG.DEBUG_FLAG_DIVERS1);
					
				}
			});
			
			this.add(dd_test);
			
		}
		
	}

	
	private class buttonFindOpenReminder extends MyE2_Button {

		public buttonFindOpenReminder() {
			super("Reminder verschicken");

			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					REMINDER_List_Executer remider_executer = new REMINDER_List_Executer();
					remider_executer.doRemindAllOpenReminders();
					
				}
			});
			
		}
		
	}
	
	private class buttonNewReminder extends MyE2_Button {

		public buttonNewReminder() {
			super("Reminder anlegen");

			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					REMINDER_Factory remFactory = new REMINDER_Factory();
//					remFactory
//						.get_ReminderData()
//							.set_BaseTable("ADRESSE")
//							.set_ID_Table("14127")
//							.set_modul_ziel(MODUL.NAME_MODUL_FIRMENSTAMM_LIST)
//							.set_Nachricht("Testnachricht Adresse 14127")
//							.set_Titel("Testnachricht 14127")
//							.set_intervall(2L)
//							.set_DtAnzeigeAb("2016-06-04")
//							.set_SendEmail(true)
//							.set_SendMessageOnCreation(true)
//							;
//					
//					remFactory.saveReminderDef();
//					vUser.addElement(new REMINDER_USER_Entry_Data().set_id_user("9").set_allow_close(true).set_allow_edit(true));
//								
					Vector<REMINDER_USER_Entry_Data> vUser = new Vector<>();
					vUser.addElement(new REMINDER_USER_Entry_Data().set_id_user("9").set_allow_close(true).set_allow_edit(false).set_send_mail(true).set_sofortanzeige(false));
					vUser.addElement(new REMINDER_USER_Entry_Data().set_id_user("338").set_allow_close(false).set_allow_edit(false).set_send_mail(false).set_sofortanzeige(true));
					vUser.addElement(new REMINDER_USER_Entry_Data().set_id_user("589").set_allow_close(true).set_allow_edit(true).set_send_mail(true).set_sofortanzeige(false));
					
					
					new REMINDER_Factory(ADRESSE.baseTabName(), 
							"14127", 
							MODUL.NAME_MODUL_FIRMENSTAMM_LIST, 
							"Testreminder 2. Möglichkeit", 
							"Dies ist ein Testreminder mit dem 2. Konstruktor", 
							1L, 
							"2016-04-10", 
							true, 
							false, 
							"test#1234#", 
							null
					).saveReminderDef();
				}
			});
			
		}
		
	}


	private class ownFileReadBt extends MyE2_Button {

		public ownFileReadBt() {
			super(new MyE2_String("pfad-test"));
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new fileNameReader();
				}
			});
		}
	
		
	}
	
	
	
	private class fileNameReader  {

		String path="/daten/entwicklungstools/tomcat/webapps/rohstoff_app/archiv/MANDANT_0000000001/DATEIEN";
		
		public fileNameReader() {
			super();
			
			File pfad = new File(path);
			
			File[] files= pfad.listFiles();
			
			for (File f: files) {
				if (f.getName().contains("17818")) {
					DEBUG.System_println(f.getName());
//					try {
						//FileUtils.copyFile(f, new File(path+"/_test_.pdf"));
						f.renameTo(new File(path+"/_test_.pdf"));
	//				} catch (IOException e) {
						//e.printStackTrace();
					//}
//					f.renameTo(new File(path+"_test_.pdf"));
				}
			}
			
			DEBUG.System_println("Anzahl:"+files.length);
			
		}
		
		
	}
	
	
	
	private class actionAgentCheckWiegekarteReminder extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			bibMSG.add_MESSAGE(new BorderCrossingReminder().check_and_generate_necessary_reminders_from_wiegekarte("22542"));
			
		}
		
	}
	
	
	
	private class ownGrid4TestingSave extends E2_Grid {
		
		private MyE2_TextField  field2save = new MyE2_TextField();
		private MyE2_Button     bt_save = new MyE2_Button("Save");
		private MyE2_Button     bt_rest = new MyE2_Button("Restore");
		
		public ownGrid4TestingSave() {
			super();
			this._a(field2save)._a(bt_save)._a(bt_rest)._setSize(100,100,100)._bo_red();
			
			bt_save.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_XML_MODULES).SAVE(field2save.getText());
				}
			});

			
			bt_rest.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					field2save.setText(new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_XML_MODULES).LOAD());
				}
			});
			
		}
		
		
	}
	
	
	private class buttonTestImportZolltarifnummern extends MyE2_Button {

		public buttonTestImportZolltarifnummern() {
			super("Zolltarifnummern importieren");

			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					ROWS_Zolltarif_Import o = new ROWS_Zolltarif_Import();
//					o.ImportFile("");
					IMPORT_Zolltarifnummer_Temp_BasicModuleContainer imp = new IMPORT_Zolltarifnummer_Temp_BasicModuleContainer();
					imp.show();
					
				}
			});
			
		}
		
	}
	

	
	private class buttonTestGenerateReplaceList extends MyE2_Button {

		public buttonTestGenerateReplaceList() {
			super("Ersetzungsliste aufbauen");

			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RECORD_ADRESSE rec_adresse = new RECORD_ADRESSE("5525");
					
					TextField tf = new TextField();
					
					if (tf.getBorder()==null) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kein Border am Anfang")));
					} else {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("ACHTUNG! Border am Anfang vorhanden !!!")));
					}
					
					
					RP_GeneratorReplacelistFromRecord gen = new RP_GeneratorReplacelistFromRecord().	 _a(rec_adresse)
																				._a(rec_adresse.get_UP_RECORD_ANREDE_id_anrede())
																				._a(rec_adresse.get_UP_RECORD_SPRACHE_id_sprache())
																				._a(rec_adresse.get_UP_RECORD_USER_id_user(),"HAENDLER")
																				._a(rec_adresse.get_UP_RECORD_LAND_id_land())
																				._a(rec_adresse.get_UP_RECORD_USER_id_user_sachbearbeiter())
																				._a(rec_adresse.get_UP_RECORD_WAEHRUNG_id_waehrung())
																				._a(rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()>0?rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0):null)
																				;
					
					gen.write_to_console();
					
				}
			});
			
		}
		
	}

	
//	private class buttonSplitTest extends MyE2_Button {
//
//		public buttonSplitTest() {
//			super("Splitpane-test");
//
//			this.add_oActionAgent(new XX_ActionAgent() {
//				
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					new ownContainer();
//					
//					
//				}
//			});
//			
//		}
//		
//		private class ownContainer extends E2_BasicModuleContainer {
//
//			public ownContainer() throws myException {
//				super();
//				
//				this.add(new ownPane());
//				
//				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(600), new MyE2_String("TEST"));
//			}
//			
//		}
//		
//		
//		private class ownPane extends ContentPaneEx {
//
//			public ownPane() {
//				super();
//				
//				SplitPane  s_pane = new SplitPane();
//				
//				this.add(s_pane);
//
//			}
//			
//			
//			
//		}
//		
//		
//	}
//	
	
	private class test_new_RECORD_Email_Send_ext_creation extends E2_Grid{
		
		private MyE2_TextField 		betreffField = new MyE2_TextField("", 400, 400);
		
		private MyE2_SelectField 	typField;
		
		private MyE2_SelectField	tabField;
		
		private MyE2_TextField		idField = new MyE2_TextField("", 200, 20);
		
		public test_new_RECORD_Email_Send_ext_creation() throws myException {
			super();
			this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			typField = new MyE2_SelectField(new String[]{SEND_TYPE.SINGLE.db_val(), SEND_TYPE.CC.user_text(), SEND_TYPE.BCC.user_text()}, SEND_TYPE.SINGLE.user_text(), true);
			tabField = new MyE2_SelectField(new String[]{"adresse", "fibu"}, "adresse" , false);
			
			this
			._a_lm(new MyE2_Button("Mail herstellen", null, new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new RB_Email_test_ui().CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(600), new MyE2_String("RB_Email() Test"));
				}
			}));
			
			
		}
	}
	
	
	private class WUT_test extends E2_Grid {
		private MyE2_TextField 		tfIP = new MyE2_TextField("", 400, 400);
		private MyE2_TextField 		tfPort = new MyE2_TextField("", 400, 400);
		private MyE2_TextField 		tfPin = new MyE2_TextField("", 400, 400);
		private MyE2_TextField 		tfAnswer = new MyE2_TextField("", 400, 400);
		private MyE2_Button			btSend = new MyE2_Button("Send");
		private WUT_test (){

			super();
			this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			this._w(new Extent(4))
			._a("IP-Adresse")._a("Port")._a("Pin(0..x)")._a("Antwort")
			._a(tfIP)._a(tfPort)._a(tfPin)._a(tfAnswer)
			._a(btSend);
			
			btSend.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					WUT_Socket_Handler_Base wut = new WUT_Socket_Handler_Base();
					try {
						
						wut.setHostIP(tfIP.getText())
						   .setPort(tfPort.getText())
						   .setPassword("");
						wut.addCommand(new WUT_Command_counter().setPin(Integer.parseInt(tfPin.getText())));
						wut.addCommand(new WUT_Command_input().setPin(Integer.parseInt(tfPin.getText())+1));
						
						wut.readWUT();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					ArrayList<String> sList = wut.getCommands().get(0).getResult();
					String s = "";
					for (int i=0; i<wut.getCommands().size(); i++){
						s += "ori:";
						s += wut.getResultOri(i);
						s += " - cmd:";
						s += wut.getResultToken(i).get(0);
						s += " - value:";
						s += wut.getResultToken(i).get(1);
						s += "::";
					}

					tfAnswer.setText(s);
				
					
				}
			});
		}
		
	}
	
	
	private class button_test_user_id extends E2_Button {

		public button_test_user_id() {
			super();
			this._t("Test USER-ID")._aaa(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Deine ID: "+bibDB.EinzelAbfrage("SELECT "+ENUM_DB_PARAMETER.ID_USER.sql_term()+" FROM DUAL")));
				
			}
		}
	}
	
	
	
	private class button_test_sendMailSimple extends E2_Button {

		/**
		 * 
		 */
		public button_test_sendMailSimple() {
			super();
			this._t("Test Mail")._aaa(new ownAction());
		}
		
		
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				if (new SimpleMailer()._set_senderAdress("martinpanter@googlemail.com")._set_receiverAdress("martinpanter@googlemail.com")._set_subject("Test")._set_mailText("Test").sendMail()) {
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Gesendet"));
//				} else {
//					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler"));
//				}
				
//				System.out.println(new PDCal()._addMonths(2).date());
//				System.out.println(new PDCal()._firstOfMonth()._addDays(4).date());
//				System.out.println(new PDCal()._lastOfMonth()._addDays(4).date());
//				System.out.println(new PDCal()._lastOfMonth().date());
//				System.out.println(new PDCal()._lastOfMonth()._addDays(1).date());
//				System.out.println(new PDCal()._lastOfMonth()._addDays(1)._lastOfMonth().date());
//				
//				
//				RecList20 rlTest = new RecList20(_TAB.artikel)._fill("1=1", ARTIKEL.id_artikel.fn());
//				
//				VEK<Rec20> v = rlTest.get_sorted_vector(new Comparator<Rec20>() {
//					
//					@Override
//					public int compare(Rec20 o1, Rec20 o2) {
//						try {
//							return o1.get_ufs_dbVal(ARTIKEL.artbez1,"").compareTo(o2.get_ufs_dbVal(ARTIKEL.artbez1,""));
//						} catch (myException e) {
//							e.printStackTrace();
//						}
//						
//						return 0;
//					}
//				}); 
//				
//				for (Rec20 r: v) {
//					
//					System.out.println(r.get_fs_dbVal(ARTIKEL.artbez1));
//				}
// 				
				

				
			}
		}
	}
	
}