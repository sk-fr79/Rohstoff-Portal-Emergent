package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.filetransfer.UploadEvent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentSingleDelete_Basic;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ColumnForFileUpload;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_PopUpWindow_for_Upload;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_UploadSelect;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PARAMETER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.reportBundle.ReportBundlePacker;
import panter.gmbh.indep.reportBundle.ReportBundleUnpacker;

public class MOD_REPORTS extends MyE2_Column
{

	public static String[][] cEndungenReports = {	{"jasper","Jasper-Report-Datei"},
													{"jrxml","Jasper-Report-Quelle"},
													{"gif","GIF-Bild"},
													{"jpg","JPG-Bild"}};  

	private String 							cMODULKENNER = null; 
	
	private E2_NavigationList				oNaviList = null;
	private ownPopUpMask					oMaskContainer = null;
	
	
	
	
	public MOD_REPORTS(	String   				  	cModulKenner) throws myException
	{
		super();

		this.cMODULKENNER = cModulKenner;
		
		if (bibALL.isEmpty(this.cMODULKENNER))
			throw new myException("MOD_REPORTS:Constuctor: Modul has no MODUL_KENNER !!!");
		
		this.oNaviList = new E2_NavigationList();
		
		/*
		 * 2013-10-10: auomatische sortierung einschalten
		 */
		this.oNaviList.set_bSaveSortStatus(true);
		
		MOD_REPORTS_ComponentMAPLIST oComponentMAPList = new MOD_REPORTS_ComponentMAPLIST(this.cMODULKENNER);
		
		oNaviList.INIT_WITH_ComponentMAP(oComponentMAPList,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)),
				E2_MODULNAMES.NAME_MODUL_GLOBALE_LISTE_ZUM_EINSTELLEN_DER_MODULREPORTS);
		
		
		
		oMaskContainer = new ownPopUpMask();
		MOD_REPORTS_ComponentMAPMASK oComponentMAP_MASK = new MOD_REPORTS_ComponentMAPMASK(	this.cMODULKENNER);
		oMaskContainer.INIT(	oComponentMAP_MASK, 
								new MOD_REPORTS_MASK(oComponentMAP_MASK),
								new Extent(950),
								new Extent(600));
		
		
		/*
		 *  moegliche fehler wegen gespeicherter sortierung abfangen 
		 */
		try {
			this.oNaviList.Fill_NavigationList("");
		} catch (myException e) {
			this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
			this.oNaviList.Fill_NavigationList("");
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Gespeicherte Sortierung hat Fehler erzeugt, Sortierung wurde entfernt !")));
			e.printStackTrace();
		}
		
		
		
		/*
		 * bedienelemente einbauen
		 */
		MyE2_Button oButtonNew = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		MyE2_Button oButtonCopy = new MyE2_Button(E2_ResourceIcon.get_RI("copy.png"),true);
		MyE2_Button oButtonEdit = new MyE2_Button(E2_ResourceIcon.get_RI("edit.png"),true);
		MyE2_Button oButtonDelete = new MyE2_Button(E2_ResourceIcon.get_RI("delete.png"),true);
		MyE2_Button buttonUploadFile = new MyE2_Button(E2_ResourceIcon.get_RI("up.png"),true);
		
		oButtonNew.add_oActionAgent(new actionNew(oButtonNew));
		oButtonCopy.add_oActionAgent(new actioncopy());
		oButtonEdit.add_oActionAgent(new actionEdit(oButtonEdit));
		oButtonDelete.add_oActionAgent(new actionDelete());
		buttonUploadFile.add_oActionAgent(new actionAgentUploadReportFile());
		
		Vector<MyString> vHelp = new Vector<MyString>();
		vHelp.add(new MyE2_String("Infos für die Reports: "));
		vHelp.add(new MyE2_String("----------------------------------------------------------------------------"));
		vHelp.add(new MyE2_String("Bei einer EINZEL - ID-Übergabe wird für jeden selektierten Datensatz "));
		vHelp.add(new MyE2_String("ein eigener Report gebildet. Die Jasper-Definition muss einen Parameter "));
		vHelp.add(new MyE2_String("Namens IDRECORD übernehmen, dieser muss vom Typ String sein und enthält die ID des Satzes."));
		vHelp.add(new MyE2_String("----------------------------------------------------------------------------"));
		vHelp.add(new MyE2_String("Bei einer MEHRFACH - ID-Übergabe muss ein"));
		vHelp.add(new MyE2_String("Parameter Namens REPORTNUMMER übergeben werden. Auf diese kann in der"));
		vHelp.add(new MyE2_String("Query des Reports mit der Sequence:"));
		vHelp.add(new MyE2_String("WHERE ID_XXX IN"));
		vHelp.add(new MyE2_String("(SELECT ID_TABLE FROM JD_REPORTAKTION WHERE REPORTNUMMER=$P{REPORTNUMMER})"));
		vHelp.add(new MyE2_String("zugegriffen werden."));
		vHelp.add(new MyE2_String("ORDERSTRING: Bei MEHRFACH-ID wird ausserdem noch der Sortierwert der Liste übergeben"));
		vHelp.add(new MyE2_String("----------------------------------------------------------------------------"));
		vHelp.add(new MyE2_String("Bei allen Report-Typen werden auch folgende Parameter uebergeben:"));
		vHelp.add(new MyE2_String("Der Pfad zu den Reportdateien wird als REPORTPATH Übergeben,"));
		vHelp.add(new MyE2_String("Dies ist wichtig für das Einbinden von Subreports !!!"));
		vHelp.add(new MyE2_String("REPORTUSER = Benutzerkürzel des Benutzers, der den Druck auslöst !!!"));
		vHelp.add(new MyE2_String("REPORTDATE = aktuelles Datum des Drucks !!!"));
		vHelp.add(new MyE2_String("Dies ist wichtig für das Einbinden von Subreports !!!"));
		vHelp.add(new MyE2_String("----------------------------------------------------------------------------"));
		vHelp.add(new MyE2_String("Die individuellen Parameter werden als"));
		vHelp.add(new MyE2_String("String übergeben."));
		
		ownHelpButton oHelp = new ownHelpButton(vHelp,new Extent(700), new Extent(400),new E2_FontPlain(-2)); 
		
		
		//2013-10-10: suchfunktion
		MOD_REPORTS_LIST_DATASEARCH  oSearch = new MOD_REPORTS_LIST_DATASEARCH(this.oNaviList, this.cMODULKENNER);
		
		
		E2_ComponentGroupHorizontal oButtonBar = new E2_ComponentGroupHorizontal(1,
															new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNaviList),
															oButtonNew,
															oButtonCopy,
															oButtonEdit,
															oButtonDelete,
															buttonUploadFile,
															new ownButtonShowReportDefinition(),
															new ownButtonExportReportBundle(),
															new ownButtonImportReportBundle(),
															oHelp,
															oSearch,new Insets(0,2,5,2));
		
		
		this.add(new MyE2_Label(new MyE2_String("Reports für Modul ").CTrans()+" "+this.cMODULKENNER,MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),
											E2_INSETS.I_5_5_5_5);
		this.add(oButtonBar);
		this.add(this.oNaviList);
		
		
	}

	
	private class ownHelpButton extends MyE2_PopUpButtonWithHelpWindow
	{
		public ownHelpButton(Vector<MyString> vHelpStrings, Extent ExtWidth, Extent ExtHeight, Font oFontForHelpText)
		{
			super(vHelpStrings, ExtWidth, ExtHeight, oFontForHelpText);
		}

		@Override
		public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException
		{
			return new ownBasicContainer();
		}
		private class ownBasicContainer extends E2_BasicModuleContainer
		{
		}
	}
	
	
	
	private class actionValidatorSomethingMustBeSelected extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			MOD_REPORTS oThis = MOD_REPORTS.this;

			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()!=1)
			{
				mv.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau einen Report zur Selektion exportieren !!"));
			}
			return mv;
		}
		
	}
	
	
	private class ownButtonShowReportDefinition extends MyE2_Button implements DS_IF_components4decision {

		private MyE2_TextField  tf_mandant_value = new MyE2_TextField("$$ID_MANDANT$$",200,20);
		
		
		public ownButtonShowReportDefinition() 
		{
			super(new MyE2_String("Zeige Reportdefinition"));
			
			this.setToolTipText(new MyE2_String("Zeigt die SQL-Statements der Reportdefinition als Datenbankskript").CTrans());
			
			this.add_GlobalValidator(new actionValidatorSomethingMustBeSelected());
			
			this.add_oActionAgent(new ownActionAgentPopupBefore(this));
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				MOD_REPORTS oThis = MOD_REPORTS.this;
				
				Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
				
				if (vIDs.size()!=1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau einen Report zur Selektion exportieren !!"));
					return;
				}
				
				RECORD_REPORT  recREPORT = new RECORD_REPORT(vIDs.get(0));
				RECLIST_REPORT_PARAMETER  recParms = recREPORT.get_DOWN_RECORD_LIST_REPORT_PARAMETER_id_report();

				String mandanten_field = ownButtonShowReportDefinition.this.tf_mandant_value.getText();
				
				Vector<String> vInsertStatementsWithSpecialFields = new Vector<String>();
				
				
				MySqlStatementBuilder oSTB_Report_WithSpecialfields = recREPORT.get_StatementBuilderFilledWithActualValues(false);
				oSTB_Report_WithSpecialfields.addSQL_Paar("ID_REPORT", "SEQ_REPORT.NEXTVAL");
				oSTB_Report_WithSpecialfields.addSQL_Paar("ID_MANDANT", mandanten_field);
				oSTB_Report_WithSpecialfields.ChangeDateFormat(recREPORT);
				
				vInsertStatementsWithSpecialFields.add(oSTB_Report_WithSpecialfields.get_CompleteInsertString("JT_REPORT"));
				
				
				for (int i=0;i<recParms.get_vKeyValues().size();i++)
				{
					MySqlStatementBuilder oSTB_ReportParm_WithSpecialfields = recParms.get(i).get_StatementBuilderFilledWithActualValues(false);
					oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_REPORT_PARAMETER", 	"SEQ_REPORT_PARAMETER.NEXTVAL");
					oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_REPORT", 				"SEQ_REPORT.CURRVAL");
					oSTB_ReportParm_WithSpecialfields.addSQL_Paar("ID_MANDANT", 			mandanten_field);
					oSTB_ReportParm_WithSpecialfields.ChangeDateFormat(recParms.get(i));
					
					vInsertStatementsWithSpecialFields.add(oSTB_ReportParm_WithSpecialfields.get_CompleteInsertString("JT_REPORT_PARAMETER"));
				}
				
				//vor dem popup noch einen download starten
				StringBuffer c_sql = new StringBuffer();
				for (String c: vInsertStatementsWithSpecialFields) {
					c_sql.append(c+";\n");
				}
				
				myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
				oTempFile.WriteTextBlock(c_sql.toString(), myTempFile.CHARSET_ISO_8859_1);
				oTempFile.close();
				
				E2_Download  oDownload = new E2_Download();
				oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");

				new PopupWindowStatus(vInsertStatementsWithSpecialFields);
			}
		}
		
		
		
		private class ownActionAgentPopupBefore extends DS_ActionAgent {

			public ownActionAgentPopupBefore(DS_IF_components4decision p_actionComponent) {
				super(p_actionComponent);
			}

			@Override
			public Boolean make_decision_when_true_then_popup() throws myException {
				return true;
			}

			@Override
			public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) throws myException {
				return new own_ds_container(activeComponent);
			}

			@Override
			public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
				container.RESET_Content();

				ownButtonShowReportDefinition.this.tf_mandant_value.setText("$$ID_MANDANT$$");
				
				E2_Grid  g = new E2_Grid()._a(new RB_lab()._t("Zielmandant für die SQL-Statements: ")._i()._lw(), new RB_gld()._ins(3,10,10,10))
										  ._a(ownButtonShowReportDefinition.this.tf_mandant_value, new RB_gld()._ins(3,10,3,10))
										  ._a(container.get_bt_OK(), new RB_gld()._ins(3))
										  ._a(container.get_bt_NO(), new RB_gld()._ins(3))
										  ._setSize(200,200);
				
				container.add(g,  E2_INSETS.I(2,2,2,2));
			}

			@Override
			public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
				String mandanten_field = ownButtonShowReportDefinition.this.tf_mandant_value.getText();
				
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				if (!(mandanten_field.equals("$$ID_MANDANT$$") || new MyLong(mandanten_field).get_bOK())) {
					mv.add(new MyE2_Alarm_Message("Angabe im Feld Zielmandant ist nicht korrekt!"));
				}
				
				return  mv;
			}
			
		}

		private class own_ds_container extends DS_PopupContainer4Decision {

			public own_ds_container(DS_IF_components4decision p_motherComponent) {
				super(p_motherComponent);
			}

			@Override
			public int get_width_in_pixel() {
				return 400;
			}

			@Override
			public int get_height_in_pixel() {
				return 200;
			}

			@Override
			public MyE2_String get_titleText4PopUp() {
				return new MyE2_String("Bitte entscheiden Sie ...");
			}
			
		}


		private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
		private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
		private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
		private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
		
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
			return this.storage_vector_4_all_agents;
		}
	
		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
			return this.storage_vector_4_standard_agents;
		}
	
		@Override
		public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
			return this.storage_vector_4_decision_agents;
		}
	
		@Override
		public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
			return this.hm_descision_containers;
		}
		
		
		
	}
	
	
	
	
	private class ownButtonExportReportBundle extends MyE2_Button
	{

		public ownButtonExportReportBundle() 
		{
			super(new MyE2_String("Export Reportdefinition"));
			
			this.setToolTipText(new MyE2_String("Baut ein Archiv aus Reportdefinition/Datenbankskript und jrxml/jasper-Datei zu einem Report").CTrans());
			
			this.add_oActionAgent(new ownActionAgent());
		}
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				MOD_REPORTS oThis = MOD_REPORTS.this;
				
				Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
				
				if (vIDs.size()!=1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau einen Report zur Selektion exportieren !!"));
					return;
				}
				
				ReportBundlePacker oPacker = new ReportBundlePacker();
				boolean bRet = oPacker.InitReportPackerFromID(vIDs.get(0));
				Vector<String> vMessages = new Vector<String>();
				
				if (bRet){
					try {
						oPacker.PackBundle();
						E2_Download oDown = new E2_Download();
						oDown.starteFileDownload(oPacker.getFilePath(),oPacker.getFileName() ,"application/zip");
					} catch (IOException e) {
						e.printStackTrace();
						vMessages.add("MOD_REPORTS: Fehler beim erzeugen der Zip-Datei");
					}
				}
				
				vMessages.addAll(bibALL.get_Vector(oPacker.getMessagesAsString()));
				
				new PopupWindowStatus(vMessages);
			}
		}
		
	}

	
	private class ownButtonImportReportBundle extends MyE2_Button
	{

		public ownButtonImportReportBundle() 
		{
			super(new MyE2_String("Import einer Reportdefinition"));
			
			this.setToolTipText(new MyE2_String("Lädt ein Report-Bundle hoch und importiert den Report").CTrans());
			
			this.add_oActionAgent(new ownActionAgent());
		}
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				MOD_REPORTS oThis = MOD_REPORTS.this;
				new PopupWindowReportImportBundle(oThis.cMODULKENNER);
			}
		}
		
	}
	
	
	private class PopupWindowReportImportBundle extends  E2_BasicModuleContainer
	{
		private String   		sCallingModule = null;
		private MyE2_CheckBox 	cbAllowOverride = null;
		private MyE2_CheckBox 	cbAllowModuleChange = null;
		
		public PopupWindowReportImportBundle(String CallingModule) throws myException 
		{
			super();
			this.sCallingModule = CallingModule;

			String filePathMandant 	= bibALL.get_REPORTPATH_LISTEN_MANDANT() + ReportBundleUnpacker.REPORT_BUNDLE_ZIP_PATH;
			String filePathAlle 	= bibALL.get_REPORTPATH_LISTEN_ALLE() + ReportBundleUnpacker.REPORT_BUNDLE_ZIP_PATH;
			
			E2_ColumnForFileUpload ofileUploadALLE = new E2_ColumnForFileUpload(filePathAlle, true, null, bibALL.get_Vector("zip"));
			E2_ColumnForFileUpload ofileUploadMandant = new E2_ColumnForFileUpload(filePathMandant, true, null, bibALL.get_Vector("zip"));
			cbAllowOverride 			= new MyE2_CheckBox(new MyString("Bestehende Reportdateien überschreiben?"));
			cbAllowOverride.set_bEnabled_For_Edit(true);
			cbAllowModuleChange = new MyE2_CheckBox(new MyString("Modulwechsel erlauben?"));
			cbAllowModuleChange.set_bEnabled_For_Edit(true);
			
			
			this.add(cbAllowOverride, E2_INSETS.I_5_5_5_5);
			cbAllowOverride.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					System.out.println("allowOverride: " + (cbAllowOverride.isSelected() ? "Seleted" : "not"));
				}
			});
			this.add(cbAllowModuleChange,E2_INSETS.I_5_5_5_5);
			cbAllowModuleChange.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					System.out.println("allowmodulechange: " + (cbAllowModuleChange.isSelected() ? "Seleted" : "not"));
				}
			});
						
			this.add(new MyE2_Label("Allgemeine Report für ALLE Mandanten hochladen:"), E2_INSETS.I_5_5_5_5);
			this.add(ofileUploadALLE, E2_INSETS.I_5_5_5_5);
			ofileUploadALLE.set_ActionAfterUpload(new actionImportReportBundle(ofileUploadALLE));
			
			
			this.add(new MyE2_Label("Report für den Mandanten hochladen:"), E2_INSETS.I_5_5_5_5);
			this.add(ofileUploadMandant, E2_INSETS.I_5_5_5_5);
			ofileUploadMandant.set_ActionAfterUpload(new actionImportReportBundle(ofileUploadMandant));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(500), new MyE2_String("Report-Bundle hochladen und installieren ..."));
			
		}
		
		private boolean get_AllowChangeModules (){
			return cbAllowModuleChange.isSelected();
		}
		
		private boolean get_AllowOverrideExistingReports(){
			return cbAllowOverride.isSelected();
		}
		
		
		private class actionImportReportBundle extends XX_ActionAgent{
			
			private String sPath = null;
			private E2_ColumnForFileUpload oUploader = null;

			
			public actionImportReportBundle(E2_ColumnForFileUpload UploadObject ) {
				oUploader = UploadObject;
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				PopupWindowReportImportBundle oThis = PopupWindowReportImportBundle.this; 
				
				ReportBundleUnpacker oUnpacker = new ReportBundleUnpacker(oUploader.get_cStoredFileNameWithEndig());
				oUnpacker.setModuleKennerZiel(oThis.sCallingModule);
				oUnpacker.setAllowModuleChange(oThis.get_AllowChangeModules());
				oUnpacker.UnpackBundle(bibALL.get_ID_MANDANT(), oThis.get_AllowOverrideExistingReports());
				
				new PopupWindowStatus(bibALL.get_Vector(oUnpacker.getMessagesAsString()));
			}
			
		}
	}
	
	
	
	/**
	 * zeigt ein Fenster mit den Statusmeldungen an
	 * @author manfred
	 *
	 */
	private class PopupWindowStatus extends  E2_BasicModuleContainer
	{
		private Vector<String>  vSQL_WithAllFields = null;
		
		public PopupWindowStatus(Vector<String> V_SQL_WithAllFields) throws myException 
		{
			super();
			this.vSQL_WithAllFields = V_SQL_WithAllFields;
			
			StringBuffer  stbText = new StringBuffer();
			
			for (int i=0;i<this.vSQL_WithAllFields.size();i++)
			{
				stbText.append(this.vSQL_WithAllFields.get(i)+";\n");
			}
			
			
			MyE2_TextArea  oText = new MyE2_TextArea(stbText.toString(),990,0,30);
			oText.setFont(new E2_FontPlain(-2));
			
			this.add(oText, E2_INSETS.I_5_5_5_5);
			
			oText.set_bEnabled_For_Edit(false);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(500), new MyE2_String("SQL-Script zum Einpflegen des Reports ..."));
			
		}
		
		
	}
	
		
	
	private class actionNew extends ButtonActionAgentNEW
	{

		public actionNew(MyE2_Button oownButton)
		{
			super(	new MyE2_String("Neueingabe einer Report-Definition"), 
					MOD_REPORTS.this.oNaviList,MOD_REPORTS.this.oMaskContainer, oownButton, null, null);

		}
		
	}
	
	
	
	private class actioncopy extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//ist was angekreuzt
			Vector<String> vID_REPORT = MOD_REPORTS.this.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vID_REPORT.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zum Kopieren muss EXAKT ein Report ausgewählt sein !!!")));
				return;
			}
			
			Vector<String> vSQL_Copy = new Vector<String>();
			
			RECORD_REPORT  recReport = new RECORD_REPORT(vID_REPORT.get(0));
			RECLIST_REPORT_PARAMETER  reclistRepParms = recReport.get_DOWN_RECORD_LIST_REPORT_PARAMETER_id_report();
			
			String cID_NewID = bibDB.get_NextSequenceValueOfTable("JT_REPORT");
			
			recReport.set_NEW_VALUE_ID_REPORT(cID_NewID);
			
			vSQL_Copy.add(recReport.get_StatementBuilderFilledWithActualValues().get_CompleteInsertString("JT_REPORT", bibE2.cTO()));
			
			for (int i=0;i<reclistRepParms.get_vKeyValues().size();i++)
			{
				String cID_NewID_Parm = bibDB.get_NextSequenceValueOfTable("JT_REPORT_PARAMETER");
				reclistRepParms.get(i).set_NEW_VALUE_ID_REPORT(cID_NewID);
				reclistRepParms.get(i).set_NEW_VALUE_ID_REPORT_PARAMETER(cID_NewID_Parm);
				
				vSQL_Copy.add(reclistRepParms.get(i).get_StatementBuilderFilledWithActualValues().get_CompleteInsertString("JT_REPORT_PARAMETER", bibE2.cTO()));
			}

			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Copy, true));
			
			if (!bibMSG.get_bHasAlarms())
			{
				MOD_REPORTS.this.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(cID_NewID);
				MOD_REPORTS.this.oNaviList._REBUILD_ACTUAL_SITE("");
			}
			
			
			
			
			
			
			
			
			
			
			
		}
	}
	

	
	private class actionEdit extends ButtonActionAgentEDIT
	{

		public actionEdit(MyE2_Button oownButton)
		{
			super(	new MyE2_String("Bearbeiten einer Report-Definition"), 
					MOD_REPORTS.this.oNaviList,MOD_REPORTS.this.oMaskContainer, oownButton, null, null);

		}
		
	}

	private class actionDelete extends ButtonActionAgentSingleDelete_Basic
	{

		public actionDelete()
		{
			super(	new MyE2_String("Löschen einer Report-Definition"), 
					MOD_REPORTS.this.oNaviList);

		}
		
	}
	
	
	
	private class actionAgentUploadReportFile extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MOD_REPORTS oThis = MOD_REPORTS.this;
			
			Vector<String> vSelectedID = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vSelectedID.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Zum Hochladen einer Reportdatei bitte genau einen Report markieren !!!"));
				return;
			}
			try
			{
				new E2_PopUpWindowUploadReport((String)vSelectedID.get(0)).CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Hochladen ..."));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
			
			
			
		}

		
	}
	
	
	
	private class E2_PopUpWindowUploadReport extends E2_PopUpWindow_for_Upload
	{
		
		private String cID_Report = "";
		private MyE2_CheckBox	oCB_WriteToDB = new MyE2_CheckBox();
		private MyE2_CheckBox	oCB_OverwriteFile = new MyE2_CheckBox();
		
		public E2_PopUpWindowUploadReport(String cID_report) throws myException
		{
			super(	bibALL.get_REPORTPATH_LISTEN_MANDANT(), 
					false, 
					MOD_REPORTS.cEndungenReports, 
					new MyE2_String("Upload einer Reportdatei"),
					new MyE2_String("Hochladen einer jasper-Datei zu dieser Reportdefinition!"));

			this.cID_Report = cID_report;
			
			E2_ComponentGroupHorizontal oCompZusatz = 
				new E2_ComponentGroupHorizontal(1,
												new MyE2_Label(new MyE2_String("Name in Tabelle schreiben ?")),
												this.oCB_WriteToDB,
												new MyE2_Label(new MyE2_String("Zieldatei überschreiben ?")),
												this.oCB_OverwriteFile,new Insets(0,5,10,5));
			
			this.set_oZusatzComponent(oCompZusatz);
			this.oCB_WriteToDB.setSelected(true);
		}
		
		
		// den status des file-overwrites uebergeben
		public boolean actionBeforeStoringFile(E2_UploadSelect oUploadSelect, UploadEvent oUploadEvent) throws myException
		{
			oUploadSelect.set_bOverWrite(this.oCB_OverwriteFile.isSelected());
			return true;
		}

		
		
		// den reportnamen in die datenbank schreiben
		public boolean actionAfterStoringFile(E2_UploadSelect oUploadSelect, UploadEvent oUploadEvent) throws myException
		{
			// nur schreiben, wenn der schreiben-schalter gesetzt ist
			if (!this.oCB_WriteToDB.isSelected())
				return true;
			
			boolean bRueck = false;

			String cSQL = "UPDATE "+bibE2.cTO()+".JT_REPORT SET NAME_OF_REPORTFILE="+bibALL.MakeSql(oUploadEvent.getFileName())+" WHERE ID_REPORT="+this.cID_Report;
			if (bibDB.ExecSQL(cSQL,true))
				bRueck = true;
			else
				throw new myException(this,"Error writing Reportinfo to database !");;
			
			
			return bRueck;
		}
		
	}
	
	
	
	
	private class ownPopUpMask extends E2_BasicModuleContainer_MASK
	{

		public ownPopUpMask()
		{
			super();
		}
		
	}
	
	
}
