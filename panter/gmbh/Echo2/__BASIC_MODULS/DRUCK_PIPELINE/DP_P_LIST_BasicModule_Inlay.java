package panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_PopUpSelectAllNoneInvert;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentVIEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.Interpret_ARCHID;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.Interpret_ARCHNAME;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.Interpret_VALID;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.CROSS_CONNECTION_USER;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.ComboBoxErsatzMitReportFilenames;
import panter.gmbh.Echo2.components.E2_HelpButton;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorString;




public class DP_P_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_INLAY_PP_LIST";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_INLAY_PP_LIST";


	public static final String NAME_HELP_BEDINGUNG =			"NAME_HELP_BEDINGUNG";
	public static final String NAME_HELP_ARCHNAME =				"NAME_HELP_ARCHNAME";
	public static final String NAME_HELP_ARCHID =				"NAME_HELP_ARCHID";
	
	public static final String NAME_TEST_BEDINGUNG =			"NAME_TEST_BEDINGUNG";
	public static final String NAME_TEST_ARCHNAME =				"NAME_TEST_ARCHNAME";
	public static final String NAME_TEST_ARCHID =				"NAME_TEST_ARCHID";

	//neue kreuztabellenfelder fuer user include-exclude-bedingung
	public static final String NAME_USERS_INCLUDE =				"NAME_USERS_INCLUDE";
	public static final String NAME_USERS_EXCLUDE =				"NAME_USERS_EXCLUDE";
	
	
	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private DP__LIST_SqlFieldMAP oSQLFieldMapList = new DP__LIST_SqlFieldMAP();
	private DP__MASK_SQLFieldMAP oSQLFieldMapMask = new DP__MASK_SQLFieldMAP();


	private DP__MASK_ComponentMAP  oDP_MaskComponentMap = null;
	
	
	private E2_BasicModuleContainer    oCallingContainer = null;


    //hier den namen des ID-Feldes aus der rufenden Einheit (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  "ID_REPORT_PIPELINE";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "PIPELINE_POS"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorLLight();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList 	oNaviList = null;


	
	//die listenbuttons muessen exportiert werden koennen
	private DP__LIST_BT_NEW  oButtonNewInList = null; 
	private DP__LIST_BT_VIEW oButtonViewInList = null; 
	private DP__LIST_BT_EDIT oButtonEditInList = null; 
	private DP__LIST_BT_DELETE oButtonDeleteInList  = null; 
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;

	private VectorString  	vFIELD_STATUS_ALLE = 		new VectorString(	"ID_REPORT_PIPELINE","ID_REPORT_PIPELINE_POS","REPORTFILEBASENAME","TYP_VERARBEITUNG",
																			"ZUSATZSTRING4JASPERHASH1","ZUSATZSTRING4JASPERHASH2","ZUSATZSTRING4JASPERHASH3","RELEVANT_4_PRINT",
																			"RELEVANT_4_MAIL","RELEVANT_4_PREVIEW");

	private VectorString  	vFIELD_STATUS_STANDARD = 	new VectorString();
	private VectorString  	vFIELD_STATUS_DIREKTDRUCK = new VectorString("ID_DRUCKER");
	private VectorString  	vFIELD_STATUS_EMAIL = 		new VectorString("MAIL_TARGETS_KOMMASEPARATED");
	private VectorString  	vFIELD_STATUS_ARCHIV = 		new VectorString("ARCHIV_TABLENAME","SQL_ARCHIVFILENAME","SQL_ARCHIV_ID");


	public DP_P_LIST_BasicModule_Inlay(E2_BasicModuleContainer  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = 	CallingContainer;
		this.oNaviList = 			new E2_NavigationList();
		
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+DP_P_LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		this.set_bVisible_Row_For_Messages(false);
		
		oNaviList.INIT_WITH_ComponentMAP(new DP__LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		DP__LIST_BedienPanel oPanel = new DP__LIST_BedienPanel(oNaviList,new DP__MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oNaviList._REBUILD_COMPLETE_LIST("");    
	}
		

	
	
	

	//beide fieldmaps werden nach einem restrict-Field durchsucht und dann mit dem wert gefuettert
	public void set_ID_From_Calling_Record(String cRestrictValueInDBFormat) throws myException
	{
		Iterator<Entry<String, SQLField>> it = this.oSQLFieldMapList.entrySet().iterator(); 
		
		while (it.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it.next();
		    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
		    {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cRestrictValueInDBFormat);
		    }
		} 			

		Iterator<Entry<String, SQLField>> it2 = this.oSQLFieldMapMask.entrySet().iterator(); 
		
		while (it2.hasNext()) 
		{
		    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it2.next();
		    
		    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
		    {
		    	((SQLFieldForRestrictTableRange)entry.getValue()).set_cRestrictionValue_IN_DB_FORMAT(cRestrictValueInDBFormat);
		    }
		} 	
		
		
	}



	/*
	 * methode, um die listbuttons enabled/diabled zu schalten
	 */
	public void set_ListButtonsEnabled(boolean bEnabled, boolean allowViewWhenDisabled) throws myException
	{
		this.oButtonDeleteInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonEditInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonViewInList.set_bEnabled_For_Edit(bEnabled);
		this.oButtonNewInList.set_bEnabled_For_Edit(bEnabled);
		
		if (allowViewWhenDisabled)
		{
			this.oButtonViewInList.set_bEnabled_For_Edit(true);
			this.oButtonSelVisibleCols.set_bEnabled_For_Edit(true);
		}
		
	}
	



	private class DP__LIST_BedienPanel extends MyE2_Column 
	{
		
		public DP__LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			DP_P_LIST_BasicModule_Inlay oThis = DP_P_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,0));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(0,0,3,0));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,0,10,0));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new DP__LIST_BT_NEW(oNaviList,oMaskContainer), 		oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new DP__LIST_BT_VIEW(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new DP__LIST_BT_EDIT(oNaviList,oMaskContainer), 	oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new DP__LIST_BT_DELETE(oNaviList), 					oRowLayout);
		
			//2014-04-28: exportbutton um definitionen zu exportieren
			//2012-08-06: fuer geschaeftsfuehrer-priv. einen SQL-export-button einsetzen
			if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
			{
				XX_ActionAgent  oAgentWarning = new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Bitte die Einschluss/Ausschluss-Einstellungen der Benutzer müssen gegebenefalls nacherfasst werden !")));
					}
				};
				Vector<XX_ActionAgent> vZusatzAgenten = new Vector<XX_ActionAgent>();
				vZusatzAgenten.add(oAgentWarning);
				E2_ButtonToCreate_SQL_ExportStatements oExportButton = new E2_ButtonToCreate_SQL_ExportStatements(oNaviList,vZusatzAgenten);
				oRowForComponents.add(oExportButton, oRowLayout);
			}

			
			
			oThis.oButtonNewInList.add_oActionAgent(new addonActionPresetValuesAtNew(), true);
			
		}
	
		
		private class addonActionPresetValuesAtNew extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				DP__LIST_BT_NEW ownButton = (DP__LIST_BT_NEW)oExecInfo.get_MyActionEvent().getSource();
				
				SQLFieldMAP oFM_Mask = ownButton.get_oMaskContainer().get_vCombinedComponentMAPs().get(0).get_oSQLFieldMAP();
				
				Iterator<Entry<String, SQLField>> it = oFM_Mask.entrySet().iterator(); 
				
				String cRestrictID = null;
				
				while (it.hasNext()) 
				{
				    Map.Entry<String, SQLField> entry = (Map.Entry<String, SQLField>)it.next();
				    
				    if (entry.getValue() instanceof SQLFieldForRestrictTableRange)
				    {
				    	cRestrictID = ((SQLFieldForRestrictTableRange)entry.getValue()).get_cRestictionValue_IN_DB_FORMAT();
				    }
				} 			

				if (S.isEmpty(cRestrictID))
				{
					throw new myException(this,"Error - cannot find mask-ID !");
				}
				
				//hier wird der gleiche report (als standard) eingetragen, der auch in der pipeline steht
				// und standard-verarbeitung als vorgabe
				RECORD_REPORT_PIPELINE  recPipe = new RECORD_REPORT_PIPELINE(cRestrictID);
				oFM_Mask.get_("REPORTFILEBASENAME").set_cDefaultValueFormated(recPipe.get_REPORTFILEBASENAME_cUF_NN(""));
				oFM_Mask.get_("TYP_VERARBEITUNG").set_cDefaultValueFormated(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD);
				
				//wenn es die erste pipeline-pos ist, dann auch alles relevant-schalter einschalten
				if (recPipe.get_DOWN_RECORD_LIST_REPORT_PIPELINE_POS_id_report_pipeline().size()==0)
				{
					oFM_Mask.get_("RELEVANT_4_PRINT").set_cDefaultValueFormated("Y");
					oFM_Mask.get_("RELEVANT_4_MAIL").set_cDefaultValueFormated("Y");
					oFM_Mask.get_("RELEVANT_4_PREVIEW").set_cDefaultValueFormated("Y");
				}
			}
		}

		
	}
	
	

	
	private class DP__LIST_BT_DELETE extends MyE2_Button
	{
	
		public DP__LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(DP_P_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+DP_P_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
		}
		
	
		private class ownActionAgent extends ButtonActionAgentMULTIDELETE
		{
			public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
			{
				super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
			}
			
			public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  			{return  new Vector<String>();}
			public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
			public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
			public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
			public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
	
		}
	}
	
	
	
	
	private class DP__LIST_BT_EDIT extends MyE2_Button
	{
	
		public DP__LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(DP_P_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+DP_P_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
			}
		}
		
	
	}
	
	
	
	
	private class DP__LIST_BT_NEW extends MyE2_Button
	{
		
		private  E2_BasicModuleContainer_MASK oMaskContainer = null;
		
		
		public E2_BasicModuleContainer_MASK get_oMaskContainer() 
		{
			return oMaskContainer;
		}

		public DP__LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.oMaskContainer = omaskContainer;
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(DP_P_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+DP_P_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class DP__LIST_BT_VIEW extends MyE2_Button
	{
	
		public DP__LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(DP_P_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+DP_P_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class DP__LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public DP__LIST_ComponentMap() throws myException
		{
			super(DP_P_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	
			
			MyE2_DB_SelectField  SelFieldVerarbeitung = new MyE2_DB_SelectField(oFM.get_("TYP_VERARBEITUNG"), DP__CONST.PP_ARRAY_4_DROPDOWN, false);
			SelFieldVerarbeitung.setFont(new E2_FontPlain(-2));
			
			this.add_Component(SelFieldVerarbeitung, 											new MyE2_String("Verarbeitung"));
			//2014-12-16: neues feld (orig)
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT)), 				new MyE2_String("Orig ?"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_PRINT")), 				new MyE2_String("Druck ?"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_MAIL")), 				new MyE2_String("eMail ?"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_PREVIEW")), 			new MyE2_String("Vorschau ?"));
			
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("REPORTFILEBASENAME")), 		new MyE2_String("Report-Datei"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARCHIV_TABLENAME")), 			new MyE2_String("Archivdatei zu Tabelle"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZUSATZSTRING4JASPERHASH1"),new E2_FontPlain(-2)), 	new MyE2_String("Eindruck-Text 1"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZUSATZSTRING4JASPERHASH2"),new E2_FontPlain(-2)), 	new MyE2_String("Eindruck-Text 2"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZUSATZSTRING4JASPERHASH3"),new E2_FontPlain(-2)), 	new MyE2_String("Eindruck-Text 3"));
			
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_DRUCKER")), 				new MyE2_String("Drucker"));
//			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER"),true), 					new MyE2_String("FÜR Benutzer"));
//			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_AUSSCHLUSS"),true), 		new MyE2_String("NICHT FÜR Benutzer"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_REPORT_PIPELINE_POS")), 	new MyE2_String("ID-Pos"));

			
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);
			
			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_5_1_5_1);
			
			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp("TYP_VERARBEITUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("TYP_VERARBEITUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("REPORTFILEBASENAME").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("REPORTFILEBASENAME").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("ARCHIV_TABLENAME").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ARCHIV_TABLENAME").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_DRUCKER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("ID_REPORT_PIPELINE_POS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_REPORT_PIPELINE_POS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
//			this.get__Comp("ID_USER").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("ID_USER").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
//
//			this.get__Comp("ID_USER_AUSSCHLUSS").EXT().set_oLayout_ListElement(oGLLeftInhalt);
//			this.get__Comp("ID_USER_AUSSCHLUSS").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			//2014-12-16: neues feld (orig)
			this.get__Comp(_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp(_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			
			this.get__Comp("RELEVANT_4_PRINT").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("RELEVANT_4_PRINT").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("RELEVANT_4_MAIL").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("RELEVANT_4_MAIL").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			
			this.get__Comp("RELEVANT_4_PREVIEW").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("RELEVANT_4_PREVIEW").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp("ZUSATZSTRING4JASPERHASH1").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ZUSATZSTRING4JASPERHASH1").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp("ZUSATZSTRING4JASPERHASH2").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ZUSATZSTRING4JASPERHASH2").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp("ZUSATZSTRING4JASPERHASH3").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ZUSATZSTRING4JASPERHASH3").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, DP_P_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.get__Comp("TYP_VERARBEITUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("REPORTFILEBASENAME").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ARCHIV_TABLENAME").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_DRUCKER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_REPORT_PIPELINE_POS").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
//			this.get__Comp("ID_USER").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
//			this.get__Comp("ID_USER_AUSSCHLUSS").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			//2014-12-16: neues feld (orig)
			this.get__Comp(_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.get__Comp("RELEVANT_4_PRINT").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("RELEVANT_4_MAIL").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);

			this.get__Comp("RELEVANT_4_PREVIEW").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ZUSATZSTRING4JASPERHASH1").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ZUSATZSTRING4JASPERHASH2").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ZUSATZSTRING4JASPERHASH3").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);

//			((MyE2_DB_SelectField)this.get__Comp("ID_USER")).setWidth(new Extent(130));
//			((MyE2_DB_SelectField)this.get__Comp("ID_USER_AUSSCHLUSS")).setWidth(new Extent(130));
			
			
			this.get__Comp(DP_P_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
			this.set_oSubQueryAgent(new DP__LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				DP__LIST_ComponentMap oCopy = new DP__LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class DP__LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
	
		}
	
	}
	
	
	
	
	
	
	
	private class DP__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public DP__LIST_SqlFieldMAP() throws myException 
		{
			super("JT_REPORT_PIPELINE_POS", DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_REPORT_PIPELINE_POS",DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD,DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
			
			this.initFields();
		}
		
	}
	
	
		
	
	private class DP__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public DP__MASK_BasicModuleContainer() throws myException
		{
			super(DP_P_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+DP_P_LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			
			
			DP_P_LIST_BasicModule_Inlay.this.oDP_MaskComponentMap = new DP__MASK_ComponentMAP();
			
			this.INIT(DP_P_LIST_BasicModule_Inlay.this.oDP_MaskComponentMap, new DP__MASK(DP_P_LIST_BasicModule_Inlay.this.oDP_MaskComponentMap), new Extent(900), new Extent(650));
		}
		
		
	}
	
	
	
	
	private class DP__MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		
				
		public DP__MASK_ComponentMAP() throws myException
		{
			super(DP_P_LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
			
			
			E2_DropDownSettingsNew  ddDrucker = new E2_DropDownSettingsNew("JT_DRUCKER", "NAME", "ID_DRUCKER", null, true, true);
			
			this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_DRUCKER"),ddDrucker.getDD(),false), new MyE2_String("Drucker"));

			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_REPORT_PIPELINE"),true,200,10,false), new MyE2_String("ID Pipe"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_REPORT_PIPELINE_POS"),true,200,10,false), new MyE2_String("ID Pipe-Pos"));
//			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER"),true), new MyE2_String("Nur für Benutzer"));
//			this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_AUSSCHLUSS"),true), new MyE2_String("NICHT für Benutzer"));
			this.add_Component(new ComboBoxErsatzMitReportFilenames(oFM.get_("REPORTFILEBASENAME"),400,300), new MyE2_String("Reportdatei"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ARCHIV_TABLENAME"),true,350,30,false), new MyE2_String("Archiv-Tablename"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("MAIL_TARGETS_KOMMASEPARATED"),500,8), new MyE2_String("Mailziele"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("SQL_ARCHIVFILENAME"),500,8), new MyE2_String("SQL-Statement zum Festlegen des Archivnames"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("SQL_ARCHIV_ID"),500,8), new MyE2_String("SQL-Statement zum Festlegen der Archiv-ID"));
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("SQL_EXEC_TRUE_FALSE"),500,8), new MyE2_String("SQL-Statement, das dafuer sorgt, dass nur ausgeführt wird, wenn das True zurueckkommt"));
			
			MyE2_DB_SelectField  SelFieldVerarbeitung = new MyE2_DB_SelectField(oFM.get_("TYP_VERARBEITUNG"), DP__CONST.PP_ARRAY_4_DROPDOWN, false);
			SelFieldVerarbeitung.add_oActionAgent(new ownActionSetStatus());
			
			this.add_Component(SelFieldVerarbeitung, new MyE2_String("Std./Direktdruck/Mail an feste Adressen/Archivierung"));
			
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ZUSATZSTRING4JASPERHASH1"),true,500,100,false), new MyE2_String("Zusatz-String für die Jasper-Datei"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ZUSATZSTRING4JASPERHASH2"),true,500,100,false), new MyE2_String("Zusatz-String für die Jasper-Datei"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("ZUSATZSTRING4JASPERHASH3"),true,500,100,false), new MyE2_String("Zusatz-String für die Jasper-Datei"));
			
//			_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT
	
			//2014-12-16: neues Feld (orig)
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT)), 		new MyE2_String("Original-Dokument"));
			
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_PRINT")), 		new MyE2_String("Relevant für Druck"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_MAIL")), 		new MyE2_String("Relevant für eMail"));
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_("RELEVANT_4_PREVIEW")), 	new MyE2_String("Relevant für Vorschau"));
			
			
			//jetzt die help-Buttons
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_HELP_BEDINGUNG,new HelpButtonBedingung(),	new MyE2_String("Hilfebutton"));
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_HELP_ARCHNAME,new HelpButtonArchivName(),	new MyE2_String("Hilfebutton"));
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_HELP_ARCHID,new HelpButtonArchivID(),		new MyE2_String("Hilfebutton"));
			
			//jetzt die test-Buttons
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_TEST_BEDINGUNG,	new testButton("SQL_EXEC_TRUE_FALSE",
																		bibALL.get_Vector("<JASPERHASH>","<SQL>","<GROOVY>")),	new MyE2_String("Test"));
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_TEST_ARCHNAME,	new testButton("SQL_ARCHIVFILENAME",
																		bibALL.get_Vector("<JASPERHASH>","<SQL>","<GROOVY>","<ARCHIVNAME>")),	new MyE2_String("Test"));
			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_TEST_ARCHID,	new testButton("SQL_ARCHIV_ID",
																		bibALL.get_Vector("<JASPERHASH>","<SQL>","<GROOVY>","<ARCHIVNAME>")),		new MyE2_String("Test"));

			
			
			
//			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_USERS_INCLUDE,new ownUserCrossConnectionField(oFM,"INCL"),new MyE2_String("Benutzer einschliessen"));
//			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_USERS_EXCLUDE,new ownUserCrossConnectionField(oFM,"EXCL"),new MyE2_String("Benutzer ausschliessen"));

			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_USERS_INCLUDE,new CROSS_CONNECTION_USER((SQLFieldForPrimaryKey)oFM.get_("ID_REPORT_PIPELINE_POS"),
																										"JT_REPORT_PP_POS_USER_INCL",
																										"ID_REPORT_PP_POS_USER_INCL",
																										"ID_REPORT_PIPELINE_POS",
																										"ID_USER",
																										4,
																										new E2_FontPlain(-2),
																										MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL),new MyE2_String("Benutzer einschliessen"));

			this.add_Component(DP_P_LIST_BasicModule_Inlay.NAME_USERS_EXCLUDE,new CROSS_CONNECTION_USER((SQLFieldForPrimaryKey)oFM.get_("ID_REPORT_PIPELINE_POS"),
																										"JT_REPORT_PP_POS_USER_EXCL",
																										"ID_REPORT_PP_POS_USER_EXCL",
																										"ID_REPORT_PIPELINE_POS",
																										"ID_USER",
																										4,
																										new E2_FontPlain(-2),
																										MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL),new MyE2_String("Benutzer ausschliessen"));

			
			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new DP__MASK_MapSettingAgent());
			
		}
		
		
		
		
		private class DP__MASK_MapSettingAgent extends XX_MAP_SettingAgent {
			
			public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
			{
			}
		
			public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
			{
				DP_P_LIST_BasicModule_Inlay oThis = DP_P_LIST_BasicModule_Inlay.this;

				//alle erstmal aktiv
				oThis.set_disabled(oThis.vFIELD_STATUS_ALLE, 		false);
				oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	false);
				oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		false);
				oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, 	false);
				oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		false);

				if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
				{
					oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		true);
					oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, true);
					oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		true);
					oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	false);
				}
				else if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
				{
					
					if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("TYP_VERARBEITUNG").equals(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD))
					{
						oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, true);
						oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	false);
					}
					else if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("TYP_VERARBEITUNG").equals(DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK))
					{
						oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	true);
						oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, false);
					}
					else if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("TYP_VERARBEITUNG").equals(DP__CONST.PP_VERARBEITUNG_TYP_EMAIL))
					{
						oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	true);
						oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, true);
						oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		false);
					}
					else if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("TYP_VERARBEITUNG").equals(DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV))
					{
						oThis.set_disabled(oThis.vFIELD_STATUS_STANDARD, 	true);
						oThis.set_disabled(oThis.vFIELD_STATUS_EMAIL, 		true);
						oThis.set_disabled(oThis.vFIELD_STATUS_DIREKTDRUCK, true);
						oThis.set_disabled(oThis.vFIELD_STATUS_ARCHIV, 		false);
					}
				}
				
			}
		
			
		}
		
		
		private class ownActionSetStatus extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				MyE2_DB_SelectField oDD_TYP = (MyE2_DB_SelectField)oExecInfo.get_MyActionEvent().getSource();
				E2_ComponentMAP  	oMap = oDD_TYP.EXT().get_oComponentMAP();
				
				oMap.set_ActiveADHOC(vFIELD_STATUS_ALLE, 		true, false);
				oMap.set_ActiveADHOC(vFIELD_STATUS_STANDARD, 	true, false);
				oMap.set_ActiveADHOC(vFIELD_STATUS_DIREKTDRUCK, true, false);
				oMap.set_ActiveADHOC(vFIELD_STATUS_EMAIL, 		true, false);
				oMap.set_ActiveADHOC(vFIELD_STATUS_ARCHIV, 		true, false);

				
				if 		(oDD_TYP.get_ActualWert().equals(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD))
				{
					oMap.set_ActiveADHOC(vFIELD_STATUS_STANDARD, true, false);

					oMap.set_ActiveADHOC(vFIELD_STATUS_DIREKTDRUCK, false, true);
					oMap.set_ActiveADHOC(vFIELD_STATUS_EMAIL, false, true);
					oMap.set_ActiveADHOC(vFIELD_STATUS_ARCHIV, false, true);
				}
				else if (oDD_TYP.get_ActualWert().equals(DP__CONST.PP_VERARBEITUNG_TYP_EMAIL))
				{
					oMap.set_ActiveADHOC(vFIELD_STATUS_EMAIL, true, true);
					
					oMap.set_ActiveADHOC(vFIELD_STATUS_STANDARD, false, false);
					oMap.set_ActiveADHOC(vFIELD_STATUS_DIREKTDRUCK, false, true);
					oMap.set_ActiveADHOC(vFIELD_STATUS_ARCHIV, false, true);
				}
				else if (oDD_TYP.get_ActualWert().equals(DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK))
				{
					oMap.set_ActiveADHOC(vFIELD_STATUS_DIREKTDRUCK, true, true);

					oMap.set_ActiveADHOC(vFIELD_STATUS_EMAIL, false, true);
					oMap.set_ActiveADHOC(vFIELD_STATUS_STANDARD, false, false);
					oMap.set_ActiveADHOC(vFIELD_STATUS_ARCHIV, false, true);
				}
				else if (oDD_TYP.get_ActualWert().equals(DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV))
				{
					oMap.set_ActiveADHOC(vFIELD_STATUS_ARCHIV, true, true);
					
					oMap.set_ActiveADHOC(vFIELD_STATUS_EMAIL, false, true);
					oMap.set_ActiveADHOC(vFIELD_STATUS_STANDARD, false, false);
					oMap.set_ActiveADHOC(vFIELD_STATUS_DIREKTDRUCK, false, true);
				}
			}
		}
		
	}

	
	
	
	private class HelpButtonBedingung extends E2_HelpButton
	{

		public HelpButtonBedingung() 
		{
			super(new MyE2_String("Richtlinien zur Definition der Scripte:"),new Vector<String>(),false);
			
			this.get_vHelpInfos().add("Typ1 (beginnt mit <JASPERHASH>):");
			this.get_vHelpInfos().add("      Es wird nur ein Name angegeben, der einen String in der JasperHash repräsentiert");
			this.get_vHelpInfos().add("      Die Bedingung ist erfüllt, wenn 'Y' unter diesem Schlüssel in der JasperHash steht.");
			this.get_vHelpInfos().add("      Damit koennen Bedingungen via Programmcode übermittelt werden.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ2 (beginnt mit <SQL>):");
			this.get_vHelpInfos().add("      Es wird ein SQL-Statement angegeben, in dem via #XXX# (XXX steht für einen beliebigen JasperHash-Key)");
			this.get_vHelpInfos().add("      alle Werte aus der JasperHash übergeben werden können. Die SELECT-Abfrage darf nur einen Wert");
			this.get_vHelpInfos().add("      (eine Zeile und eine Spalte) als Ergebnis haben. Die Bedingung ist erfüllt, wenn 'Y' zurückkommt.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ3 (beginnt mit <GROOVY>):");
			this.get_vHelpInfos().add("      Es wird ein Groovy-Script angegeben, in das via Binding alle Objekte aus der JasperHash");
			this.get_vHelpInfos().add("      übergeben werden. Das Ergebnis wird in das Binding geschrieben unter dem Namen RETURNVALUE");
			this.get_vHelpInfos().add("      Die Bedingung ist erfüllt, wenn im Bindung unter dem Hash-Wert RETURNVALUE  der Wert Y steht.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Alle anderen Fälle sind Y wenn das Feld leer ist, sonst N (werden nicht ausgeführt)");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Fest eingebaute Parameter in der JasperHash:");
			this.get_vHelpInfos().add("      SYS_USERNAME   = 	 Benutzername des Ausführenden");
			this.get_vHelpInfos().add("      SYS_KUERZEL    = 	 Kürzel  des Ausführenden");
			this.get_vHelpInfos().add("      SYS_USERID     = 	 ID_USER des Ausführenden");
			this.get_vHelpInfos().add("      SYS_MANDANT_ID = 	 ID_MANDANT in dem sich alles abspielt");
			this.get_vHelpInfos().add("      SYS_ID_ADRESSE_MANDANT = ID_ADRESSE des Mandanten");
			this.get_vHelpInfos().add("      SYS_REPORTDATE =         Ausführungsdatum im Format 31.12.2010");
			this.get_vHelpInfos().add("      SYS_REPORTTARGET =       PRINT  oder MAIL");
			this.get_vHelpInfos().add("      HASH_ID_DRUCKTABLE =     Falls eine Drucktabelle (Protokoll) benutzt wird, die geschriebene ID");
			this.get_vHelpInfos().add("                               oder mehrere IDs");
		}
	}
	
	
	private class HelpButtonArchivName extends E2_HelpButton
	{

		public HelpButtonArchivName() 
		{
			super(new MyE2_String("Richtlinien zur Definition der Scripte:"),new Vector<String>(),false);
			
			this.get_vHelpInfos().add("Typ1 (beginnt mit <JASPERHASH>):");
			this.get_vHelpInfos().add("      Es wird nur ein Name angegeben, der einen String in der JasperHash repräsentiert");
			this.get_vHelpInfos().add("      Wenn ein String unter diesem Schlüssel in der JasperHash steht, ist dieser Wert ist der Archivname.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ2 (beginnt mit <SQL>):");
			this.get_vHelpInfos().add("      Es wird ein SQL-Statement angegeben, in dem via #XXX# (XXX steht für einen beliebigen JasperHash-Key)");
			this.get_vHelpInfos().add("      alle Werte aus der JasperHash übergeben werden können. Die SELECT-Abfrage darf nur einen Wert");
			this.get_vHelpInfos().add("      (eine Zeile und eine Spalte) als Ergebnis haben. Die Rückgabewert ist der Archivname.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ3 (beginnt mit <GROOVY>):");
			this.get_vHelpInfos().add("      Es wird ein Groovy-Script angegeben, in das via Binding alle Objekte aus der JasperHash");
			this.get_vHelpInfos().add("      übergeben werden. Das Ergebnis wird in das Binding geschrieben unter dem Namen RETURNVALUE");
			this.get_vHelpInfos().add("      Der Archivname ist gültig, wenn im Bindung unter dem Hash-Wert RETURNVALUE ein String steht.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ4 (beginnt mit <ARCHIVNAME>):");
			this.get_vHelpInfos().add("      Hier ist der Archivname das was hinter <ARCHIVNAME> kommt.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Ist das Feld leer oder liefert kein Ergebnis, dann wird ein Fehler erzeugt !!");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Fest eingebaute Parameter in der JasperHash:");
			this.get_vHelpInfos().add("      SYS_USERNAME   = 	 Benutzername des Ausführenden");
			this.get_vHelpInfos().add("      SYS_KUERZEL    = 	 Kürzel  des Ausführenden");
			this.get_vHelpInfos().add("      SYS_USERID     = 	 ID_USER des Ausführenden");
			this.get_vHelpInfos().add("      SYS_MANDANT_ID = 	 ID_MANDANT in dem sich alles abspielt");
			this.get_vHelpInfos().add("      SYS_ID_ADRESSE_MANDANT = ID_ADRESSE des Mandanten");
			this.get_vHelpInfos().add("      SYS_REPORTDATE =         Ausführungsdatum im Format 31.12.2010");
			this.get_vHelpInfos().add("      SYS_REPORTTARGET =       PRINT  oder MAIL");
			this.get_vHelpInfos().add("      HASH_ID_DRUCKTABLE =     Falls eine Drucktabelle (Protokoll) benutzt wird, die geschriebene ID");
			this.get_vHelpInfos().add("                               oder mehrere IDs");
		}
	}
	

	private class HelpButtonArchivID extends E2_HelpButton
	{

		public HelpButtonArchivID() 
		{
			super(new MyE2_String("Richtlinien zur Definition der Scripte:"),new Vector<String>(),false);
			
			this.get_vHelpInfos().add("Typ1 (beginnt mit <JASPERHASH>):");
			this.get_vHelpInfos().add("      Es wird nur ein Name angegeben, der einen String in der JasperHash repräsentiert");
			this.get_vHelpInfos().add("      Die Bedingung ist erfüllt, wenn ein String unter diesem Schlüssel in der JasperHash steht.");
			this.get_vHelpInfos().add("      Dieser Wert ist die Archiv-ID.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ2 (beginnt mit <SQL>):");
			this.get_vHelpInfos().add("      Es wird ein SQL-Statement angegeben, in dem via #XXX# (XXX steht für einen beliebigen JasperHash-Key)");
			this.get_vHelpInfos().add("      alle Werte aus der JasperHash übergeben werden können. Die SELECT-Abfrage darf nur einen Wert");
			this.get_vHelpInfos().add("      (eine Zeile und eine Spalte) als Ergebnis haben. Der Rückgabewert ist die Archiv-ID.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Typ3 (beginnt mit <GROOVY>):");
			this.get_vHelpInfos().add("      Es wird ein Groovy-Script angegeben, in das via Binding alle Objekte aus der JasperHash");
			this.get_vHelpInfos().add("      übergeben werden. Das Ergebnis wird in das Binding geschrieben unter dem Namen RETURNVALUE");
			this.get_vHelpInfos().add("      Die Archiv-ID steht als String im Bindung unter dem Hash-Wert RETURNVALUE.");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Ist das Feld leer oder liefert kein Ergebnis oder das Ergebnis ist kein LONG-Wert, dann wird ein Fehler erzeugt !!");
			this.get_vHelpInfos().add("      -----------------------------");
			this.get_vHelpInfos().add("Fest eingebaute Parameter in der JasperHash:");
			this.get_vHelpInfos().add("      SYS_USERNAME   = 	 Benutzername des Ausführenden");
			this.get_vHelpInfos().add("      SYS_KUERZEL    = 	 Kürzel  des Ausführenden");
			this.get_vHelpInfos().add("      SYS_USERID     = 	 ID_USER des Ausführenden");
			this.get_vHelpInfos().add("      SYS_MANDANT_ID = 	 ID_MANDANT in dem sich alles abspielt");
			this.get_vHelpInfos().add("      SYS_ID_ADRESSE_MANDANT = ID_ADRESSE des Mandanten");
			this.get_vHelpInfos().add("      SYS_REPORTDATE =         Ausführungsdatum im Format 31.12.2010");
			this.get_vHelpInfos().add("      SYS_REPORTTARGET =       PRINT  oder MAIL");
			this.get_vHelpInfos().add("      HASH_ID_DRUCKTABLE =     Falls eine Drucktabelle (Protokoll) benutzt wird, die geschriebene ID");
			this.get_vHelpInfos().add("                               oder mehrere IDs");
		}
	}
	
	
	
	
	private class testButton extends MyE2_Button
	{
		private String 			cFIELDNAME_TO_CHECK = null;
		private Vector<String>	vScriptTypen = null;
		
		public testButton(String FIELDNAME_TO_CHECK, Vector<String> ScriptTypenErlaubt) 
		{
			super(new MyE2_String("TEST"));
			this.cFIELDNAME_TO_CHECK = FIELDNAME_TO_CHECK;
			this.vScriptTypen = ScriptTypenErlaubt;
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				E2_ComponentMAP  oMap = testButton.this.EXT().get_oComponentMAP();
				String cScript = oMap.get_cActualDBValueFormated(testButton.this.cFIELDNAME_TO_CHECK).trim();
				String cJasperFile = oMap.get_cActualDBValueFormated("REPORTFILEBASENAME");
				
				if (S.isEmpty(cScript) || S.isEmpty(cJasperFile))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst eine Report-Datei und das Script eintragen !")));
					return;
				}
				
				
				boolean bFalscherTyp = true;
				for (String cStart: testButton.this.vScriptTypen)
				{
					if (cScript.startsWith(cStart))
					{
						bFalscherTyp = false;
						break;
					}
				}
				
				if (bFalscherTyp)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es sind nur Scripte erlaubt, die mit den folgenden Signaturen beginnen: ",true,bibALL.Concatenate(testButton.this.vScriptTypen, "/", "-"),false)));
					return;
				}
				
				
				
				
				
				if (testButton.this.cFIELDNAME_TO_CHECK.equals("SQL_EXEC_TRUE_FALSE"))
				{
					Interpret_VALID  oValid = new Interpret_VALID(cScript, new E2_JasperHASH_STD(cJasperFile, new JasperFileDef_PDF()));
					if (S.isEmpty(oValid.get_cRueckgabe()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Rückgabe ist leer !!"));
					}
					else
					{
						if (oValid.get_cRueckgabe().equals("Y") || oValid.get_cRueckgabe().equals("N"))
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Rückgabe :"+oValid.get_cRueckgabe()));
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Rückgabe muss Y oder N sein. Hier: "+oValid.get_cRueckgabe()));
						}
					}
				}
				else if (testButton.this.cFIELDNAME_TO_CHECK.equals("SQL_ARCHIVFILENAME"))
				{
					Interpret_ARCHNAME  oArchname = new Interpret_ARCHNAME(cScript, new E2_JasperHASH_STD(cJasperFile, new JasperFileDef_PDF()));

					if (S.isEmpty(oArchname.get_cArchName()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Archivname ist leer !!"));
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Archivname :"+oArchname.get_cArchName()));
					}
				}
				else if (testButton.this.cFIELDNAME_TO_CHECK.equals("SQL_ARCHIV_ID"))
				{
					Interpret_ARCHID  oArchID = new Interpret_ARCHID(cScript, new E2_JasperHASH_STD(cJasperFile, new JasperFileDef_PDF()));

					if (oArchID.get_vID_for_Arch()==null || oArchID.get_vID_for_Arch().size()==0)
					{ 
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Archiv-ID ist leer !!"));
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Archiv-ID :"+oArchID.get_cRueckgabe()));
					}
					
				}
			}
		}
	}
	
	
	
	private class DP__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public DP__MASK_SQLFieldMAP() throws myException 
		{
			super("JT_REPORT_PIPELINE_POS", DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD, false);
		
	
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_REPORT_PIPELINE_POS",DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD,DP_P_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
	
	
			this.initFields();
		}
	
	}
	
	
	
	
	
	/*
	* maskenvariante mit TabbedPane 
	*/
	private class DP__MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
	{
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public DP__MASK(DP__MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
			
			oTabbedPaneMaske.set_bAutoHeight(true);         //damit wird beim vergroessern die tabbedpane automatisch mitgezogen
			
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
			MyE2_Grid oGrid2 = new MyE2_Grid(2,0);
			//neue benutzerzuordnung
			MyE2_Grid oGrid3 = new MyE2_Grid(1,0);
			MyE2_Grid oGrid4 = new MyE2_Grid(1,0);
			
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Basisangaben"), oGrid0);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Angabe für Mail"), oGrid1);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Angabe für Archiv"), oGrid2);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Benutzer einschliessen"), oGrid3);
			oTabbedPaneMaske.add_Tabb(new MyE2_String("Benutzer ausschliessen"), oGrid4);
			
			this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("IDs:"), 1, "|#Pipeline: |ID_REPORT_PIPELINE|#  |#Position: |ID_REPORT_PIPELINE_POS|",3);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(oGrid0, new MyE2_String("Report-Datei:"), 1, "REPORTFILEBASENAME|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Verarbeitung:"), 1, "TYP_VERARBEITUNG|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Relevanz: "), 1, "#Druck |RELEVANT_4_PRINT|#  |#Mail |RELEVANT_4_MAIL|#  |#Vorschau |RELEVANT_4_PREVIEW|",3);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(oGrid0, new MyE2_String("Gehört zum Originaldokument:"), 1, _DB.REPORT_PIPELINE_POS$ORIGINAL_DOKUMENT,3);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(oGrid0, new MyE2_String("Drucker (direkt):"), 1, "ID_DRUCKER<W150W>",3);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
//			oFiller.add_Line(oGrid0, new MyE2_String("Gilt NUR für Benutzer:"), 1, "ID_USER<W150W>",3);
//			oFiller.add_Line(oGrid0, new MyE2_String("Gilt NICHT für Benutzer:"), 1, "ID_USER_AUSSCHLUSS<W150W>",3);
//			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(oGrid0, new MyE2_String("Zusatztext (1) für JasperHash:"), 1, "ZUSATZSTRING4JASPERHASH1|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Zusatztext (2) für JasperHash::"), 1, "ZUSATZSTRING4JASPERHASH2|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Zusatztext (3) für JasperHash::"), 1, "ZUSATZSTRING4JASPERHASH3|#  |",3);
			oFiller.add_Trenner(oGrid0, E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(oGrid0, new MyE2_String("Script (Bedingung zur Ausführung)"), 1, "SQL_EXEC_TRUE_FALSE|#  |"+
									DP_P_LIST_BasicModule_Inlay.NAME_HELP_BEDINGUNG+"|# |"+DP_P_LIST_BasicModule_Inlay.NAME_TEST_BEDINGUNG,3);

			oFiller.add_Line(oGrid1, new MyE2_String("Mailadressen (Komma-Trenner):"), 1, "MAIL_TARGETS_KOMMASEPARATED|#  |",3);

			oFiller.add_Line(oGrid2, new MyE2_String("Tabellename für Archivierung"), 1, "ARCHIV_TABLENAME|#  |",3);
			oFiller.add_Line(oGrid2, new MyE2_String("Script (Ermittlung des Archivnames)"), 1, "SQL_ARCHIVFILENAME|#  |"+
									DP_P_LIST_BasicModule_Inlay.NAME_HELP_ARCHNAME+"|# |"+DP_P_LIST_BasicModule_Inlay.NAME_TEST_ARCHNAME,3);
			oFiller.add_Line(oGrid2, new MyE2_String("Script (Ermittlung Archiv-ID)"), 1, "SQL_ARCHIV_ID|#  |"+
									DP_P_LIST_BasicModule_Inlay.NAME_HELP_ARCHID+"|# |"+DP_P_LIST_BasicModule_Inlay.NAME_TEST_ARCHID,3);

			
			//neue benutzerzuordnung
			oGrid3.add_raw(oMap.get_Comp(DP_P_LIST_BasicModule_Inlay.NAME_USERS_INCLUDE), LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,10,5,5)));
			oGrid4.add_raw(oMap.get_Comp(DP_P_LIST_BasicModule_Inlay.NAME_USERS_EXCLUDE), LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,10,5,5)));
			
			this.vMaskGrids.add(oGrid0);
			this.vMaskGrids.add(oGrid1);
			this.vMaskGrids.add(oGrid2);
			this.vMaskGrids.add(oGrid3);
			this.vMaskGrids.add(oGrid4);
			
		}

		@Override
		public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
		{
			return this.vMaskGrids;
		}
	}
	
	
		
	private class ownGridLayoutDataLeft extends GridLayoutData
	{
		public ownGridLayoutDataLeft(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}


	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(DP_P_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	

	
	private void set_disabled(Vector<String> vFELDER, boolean bDisabled) throws myException
	{
		DP__MASK_ComponentMAP oMaskMap =  DP_P_LIST_BasicModule_Inlay.this.oDP_MaskComponentMap;
		
		for (int i=0;i<vFELDER.size();i++)
		{
			oMaskMap.get__Comp(vFELDER.get(i)).EXT().set_bDisabledFromInteractive(bDisabled);
		}
	}

	
	
	
}
