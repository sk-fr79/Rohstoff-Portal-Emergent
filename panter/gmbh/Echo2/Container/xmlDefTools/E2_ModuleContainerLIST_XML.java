package panter.gmbh.Echo2.Container.xmlDefTools;

import java.lang.reflect.Constructor;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_TableNamingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;



/*
 * klasse nimmt eine listendef entgegen und baut eine listenansicht mit
 * editiermoeglichkeiten aus dieser definition
 */
public class E2_ModuleContainerLIST_XML extends Project_BasicModuleContainer
{
	public static final String 		MODULKENNER_BASE_NAME = "TABLEEDITOR_";    // basisname fuer den modulkenner
	
	public static final String 		HASHKEY_CHECKBOX = 		"HASHKEY_CHECKBOX"; 
	public static final String 		HASHKEY_LISTMARKER = 	"HASHKEY_LISTMARKER";
	
	
	private XX_TableNamingAgent 	oNameingAgent = null;
	private String 					cTableName = null;
	private SQLFieldMAP				oSQLFieldMAP = null;
	private E2_ComponentMAP 		oComponentMAP = null;
	private boolean 				bAddAllowed = false;
	private boolean 				bDeleteAllowed = false;

	private E2_NavigationList 		oNaviList = null;
	
	
	private MyDBToolBox				oToolBoxWithoutSpecialFields = null;
	
	private boolean 				bListIsEditable = true;
	
	
	protected void finalize()
	{
		if (oToolBoxWithoutSpecialFields != null)
		{
			bibALL.destroy_myDBToolBox(oToolBoxWithoutSpecialFields);
		}
	}
	
	
	
	

	/**
	 * Konstuctor mit listendef - object.
	 * @param onameingAgent
	 * @param oListDef
	 * @param OwnContentPane
	 * @throws myException
	 */
	public E2_ModuleContainerLIST_XML(	XX_TableNamingAgent onameingAgent,
										ListDef_NG 			oListDef) throws myException
	{
		super(E2_ModuleContainerLIST_XML.MODULKENNER_BASE_NAME+oListDef.MODULKENNER);
		
		this.bAddAllowed = 		oListDef.NEWALLOWED;
		this.bDeleteAllowed = 	oListDef.DELETEALLOWED;
		this.cTableName = 		oListDef.TABLENAME;
		
		this.oNameingAgent = onameingAgent;
		
		/*
		 * Fieldmap und CoponentMAP werden geliefert von ListDef
		 */
		this.oSQLFieldMAP = oListDef.build_SQLFieldMAP();
		this.oComponentMAP = oListDef.build_ComponentMAP(this.oSQLFieldMAP);

		this.oNaviList = new E2_NavigationList();
		//2014-04-06: titelbuttons mit zeilenumbuch darstellen
		this.oNaviList.set_bStandardSortButtonsLineWrap(true);
		oNaviList.INIT_WITH_ComponentMAP(this.oComponentMAP, null, this.get_MODUL_IDENTIFIER());
		/*
		 * anzahl zeilen festlegen
		 */
//		int iCountRows = oListDef.NUMBERROWSINLIST;
		
//		if (iCountRows > 0)
//			oNaviList.get_vectorSegmentation().set_iSegmentGroesse(iCountRows);

		E2_ListSelectorContainer 	oSelectExpander = new E2_ListSelectorContainer(new MyE2_String("Selektionsbereich ausgeblendet ..."),null,null);
		E2_Grid 					gridWithSelektors = oListDef.build_GridWithSelectors(oNaviList, this.get_MODUL_IDENTIFIER());
		E2_DataSearch				oSearch	= oListDef.build_SearchDef(this.oSQLFieldMAP,oNaviList,this.get_MODUL_IDENTIFIER());

		
		/*
		 * bedienpanel bauen, immer drin: der Selektor fuer die Spalten,
		 * bei eingabe auch die Bedienbuttons, und, falls vorhanden, die suche
		 */
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(null);
		oCompGroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), new Insets(2,2,1,2));

		
		/*
		 * jetzt nachsehen, ob zusatzmodule definiert sind
		 */
		MyE2_PopUpMenue oPopUp = null; 
		if (oListDef.VECTORPOPUPWINDOWCLASSES != null && oListDef.VECTORPOPUPWINDOWCLASSES.size()>0)
		{
			oPopUp = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup.png"),E2_ResourceIcon.get_RI("leer.png"), false);
			
			/*
			 * jetzt die buttons definieren
			 */
			for (int i=0;i<oListDef.VECTORPOPUPWINDOWCLASSES.size();i++)
			{
				String cBoth = (String)oListDef.VECTORPOPUPWINDOWCLASSES.get(i);
				String cClass = null;
				String cMenue = null;
				String cAUTH_KENNER_BUTTON = null;
				
				StringSeparator oSep = new StringSeparator(cBoth,":");
				
				if (oSep.size() == 3)
				{
					cClass = (String)oSep.get(0);
					cMenue = (String)oSep.get(1);
					cAUTH_KENNER_BUTTON = (String)oSep.get(2);
				}
				else
					throw new myException("E2_ModuleContainerListEditing:Constructor:VECTORPOPUPWINDOWCLASSES containes uncorrect value: "+cBoth+" MUST be like class:button:authword");
				
				MyE2_Button oButton = new MyE2_Button(new MyE2_String(cMenue));
				oPopUp.addButton(oButton,true);
				oButton.add_oActionAgent(new ActionAgentLoadClass(cClass));
				oButton.add_GlobalValidator(new E2_ButtonAUTHValidator(this.get_MODUL_IDENTIFIER(),cAUTH_KENNER_BUTTON));
			}
		}
		
		
		
		
		E2_BASIC_EditListButtonPanel oPanel = new E2_BASIC_EditListButtonPanel(	oNaviList,
																				oListDef.EDITALLOWED,
																				oListDef.NEWALLOWED,
																				oListDef.DELETEALLOWED,
																				oListDef.get_ButtonHelp(),
																				oPopUp,
																				this.get_MODUL_IDENTIFIER(),"", null, null, null);
		
		//2012-08-06: fuer geschaeftsfuehrer-priv. einen SQL-export-button einsetzen
		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
		{
			E2_ButtonToCreate_SQL_ExportStatements oExportButton = new E2_ButtonToCreate_SQL_ExportStatements(this.oNaviList);
			oPanel.add(oExportButton, E2_INSETS.I_10_2_2_2);
		}

		
		oCompGroup.add(oPanel,new Insets(20,2,1,2));


		
		
		//hier jetzt ein report-button und einen button zum Excel-Export anfuegen und einen upload-button
		oCompGroup.add(new REP__POPUP_Button(this.get_MODUL_IDENTIFIER(),this.oNaviList),new Insets(10,2,1,2));
		MyE2_String oDownloadName = new MyE2_String(this.get_MODUL_IDENTIFIER(),false,".xls",false);
//		E2_ButtonWriteListToExcel oButtExcel = new E2_ButtonWriteListToExcel(this.oNaviList,oDownloadName);
//		oButtExcel.add_GlobalAUTHValidator(this.get_MODUL_IDENTIFIER(), "EXCEL_EXPORT");
		
		EXP_popup_menue_exporter oButtexport = new EXP_popup_menue_exporter(this.oNaviList);
		
		oCompGroup.add(oButtexport,new Insets(10,2,1,2));
		oCompGroup.add(new E2_ButtonUpDown_NavigationList_to_Archiv(this.oNaviList,this.get_MODUL_IDENTIFIER()),new Insets(10,2,1,2));
		
		
		
		if (oListDef.NEWINSINGLELINE)
			oPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);        // bei umfangreichen listen kann die eingabe so besser erkannt werden

		
		
		/*
		 * jetzt pruefen, ob die XML-definition den schalter fuer INSERTSPECIALFIELDS gesetzt hat.
		 * Falls nein, muss dem E2_BASIC_EditListButtonPanel ein speziell angepasstes MyDBToolBox-objekt uebergeben werden
		 */
		if (oListDef.NONSPECIALFIELDS)
		{
			this.oToolBoxWithoutSpecialFields =  bibALL.get_myDBToolBox(false, true);
			oPanel.set_oDBToolBox(this.oToolBoxWithoutSpecialFields);
		}
		
		if (oSearch != null)
			oCompGroup.add(oSearch,new Insets(20,2,1,2));
		
		Insets  oIN = new Insets(7,5,7,10);

		this.add(new MyE2_Label(new MyE2_String(oListDef.HEADLINE)),oIN);
		
		/*
		 * selektoren einblenden in expandable section
		 */
		if (gridWithSelektors != null)
		{
			oSelectExpander.add(gridWithSelektors);
			this.add(oSelectExpander,oIN);
		}
		
		this.add(oCompGroup,oIN);
		this.add(oNaviList,oIN);
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		oListDef.INIT_LIST();
	}
	
	
	
	
	
	/**
	 * Konstuctor mit standard-definition ohne listendef - object.
	 * Zeigt eine einzeilige liste ohne selektor oder suche
	 * 
	 * @param onameingAgent
	 * @param ctableName
	 * @param baddAllowed
	 * @param bdeleteAllowed
	 * @param ListIsEditable
	 * @param Vector_MyStrings_ForFieldTitles (null = keine zusatzkomponenten, sonst MyString-Auflistung fuer alle Spalten)
	 * @param cNameOfExcelDownloadFile       (null oder leer = keine Downloads erlaubt)
	 * @throws myException
	 */
	public E2_ModuleContainerLIST_XML(		XX_TableNamingAgent 	onameingAgent, 
											String 					ctableName,
											boolean 				baddAllowed,
											boolean 				bdeleteAllowed, 
											boolean 				ListIsEditable, 
											Vector<MyString> 		Vector_MyStrings_ForFieldTitles, 
											String 					cNameOfExcelDownloadFile) throws myException
	{
		super(E2_ModuleContainerLIST_XML.MODULKENNER_BASE_NAME+ctableName);
		this.bAddAllowed = 		baddAllowed;
		this.bDeleteAllowed = 	bdeleteAllowed;
		this.cTableName = ctableName;
	
		this.bListIsEditable = ListIsEditable;
		
		this.oNameingAgent = onameingAgent;
		
		this.oSQLFieldMAP = new SQLFieldMAP(this.cTableName,bibE2.get_CurrSession());
		
		String cNotUsedList = ":"+this.oNameingAgent.get_cNameOfPrimaryKey(this.cTableName)+":";
		if (this.oNameingAgent.get_vStringOfNotUsedFields().size()>0)
		{
			for (int i=0;i<this.oNameingAgent.get_vStringOfNotUsedFields().size();i++)
			{
				cNotUsedList+=":"+(String)this.oNameingAgent.get_vStringOfNotUsedFields().get(i)+":";
			}
		}
		
		
		this.oSQLFieldMAP.addCompleteTable_FIELDLIST(	this.cTableName,
														cNotUsedList,
														false,
														this.bListIsEditable,
														"");
		
		this.oSQLFieldMAP.add_SQLField(new SQLFieldForPrimaryKey(
				this.cTableName,
				this.oNameingAgent.get_cNameOfPrimaryKey(this.cTableName),
				this.oNameingAgent.get_cNameOfPrimaryKey(this.cTableName),
				new MyE2_String(this.oNameingAgent.get_cNameOfPrimaryKey(this.cTableName)),
				bibE2.get_CurrSession(),
				this.oNameingAgent.get_SQLSequenceQuery(this.cTableName),
				true), false);
		
		this.oSQLFieldMAP.initFields();
		
		this.oComponentMAP = new E2_ComponentMAP(this.oSQLFieldMAP);
		this.oComponentMAP.add_Component(E2_ModuleContainerLIST_XML.HASHKEY_CHECKBOX,new E2_CheckBoxForList(),new MyE2_String("?"));
		this.oComponentMAP.add_Component(E2_ModuleContainerLIST_XML.HASHKEY_LISTMARKER,new E2_ButtonListMarker(),new MyE2_String("?"));
		
		
		for (int i=0;i<this.oSQLFieldMAP.get_vFieldLabels().size();i++)
		{
			String cLabel = (String)this.oSQLFieldMAP.get_vFieldLabels().get(i);
			if (!cLabel.equals(this.oNameingAgent.get_cNameOfPrimaryKey(this.cTableName)))
				this.oComponentMAP.add_Component(MaskComponentsFAB.createStandardComponent(this.oSQLFieldMAP.get_SQLField(cLabel),!this.bListIsEditable, false, 0, false),new MyE2_String(cLabel));
		}
		
		/*
		 * am schluss den label fuer den primaryKey anhaengen
		 */
		this.oComponentMAP.add_Component(new MyE2_DB_Label(this.oSQLFieldMAP.get_oSQLFieldPKMainTable()),new MyE2_String("ID"));
		
		this.oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(this.oComponentMAP, null, null);
		
		MyE2_String cTitel = new MyE2_String("Listenbearbeitung der Tabelle: ");
		cTitel.addUnTranslated(this.cTableName);
		MyE2_Label labelTitle = new MyE2_Label(cTitel);;
		
		if (this.bListIsEditable)
		{
			/*
			 * bedienpanel
			 */
			E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(null);
			oCompGroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), new Insets(2,2,2,2));
			
			if (!bibALL.isEmpty(cNameOfExcelDownloadFile))
			{
				//oCompGroup.add(new E2_ButtonWriteListToExcel(this.oNaviList,new MyString(cNameOfExcelDownloadFile,false)));
				oCompGroup.add(new EXP_popup_menue_exporter(this.oNaviList));
			}
			
			
			oCompGroup.add(new E2_BASIC_EditListButtonPanel(	
											oNaviList,
											(this.bAddAllowed && this.bListIsEditable),
											this.bListIsEditable,
											(this.bDeleteAllowed && this.bListIsEditable),
											null,
											null,
											this.get_MODUL_IDENTIFIER(),
											"", null, null, null));
			
			this.add(labelTitle,new Insets(2,5,2,10));
			this.add(oCompGroup,new Insets(2,5,2,10));
		}
		else
		{
			// wenn keine toolbar zum bearbeiten angezeigt wird, dann wird der spaltenselektor in der navilist eingeblendet
			oNaviList.get_RowForNavigationElements().add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), new Insets(30,2,2,2));
			if (!bibALL.isEmpty(cNameOfExcelDownloadFile))
			{
				oNaviList.get_RowForNavigationElements().add(new EXP_popup_menue_exporter(this.oNaviList));
			//	EXP_popup_menue_exporter oButtexport = new EXP_popup_menue_exporter(this.oNaviList);

			}

		}
		
		//jetzt noch evtl uebergebene Titel eintragen
		if (	Vector_MyStrings_ForFieldTitles 		!= 	null && 
				Vector_MyStrings_ForFieldTitles.size()	>	0 && 
				Vector_MyStrings_ForFieldTitles.size()	<=	this.oComponentMAP.get_vComponentHashKeys().size())
		{
			for (int i=0;i<Vector_MyStrings_ForFieldTitles.size();i++)
			{
				if (Vector_MyStrings_ForFieldTitles.get(i) instanceof MyString)
					this.oComponentMAP.get__Comp(i).EXT().set_cList_or_Mask_Titel((MyString)Vector_MyStrings_ForFieldTitles.get(i));
			}
		}
		
		
		this.add(oNaviList,new Insets(2,5,2,5));
		
		oNaviList._REBUILD_COMPLETE_LIST("");
		
	}
	
	
	
	private class ActionAgentLoadClass extends XX_ActionAgent
	{
		private String 								cClassName = null;
		private XX_ModuleContainerListEditPopup		oModulPop = null;
		
		
		public ActionAgentLoadClass(String name) throws myException
		{
			super();
			cClassName = name;
			try
			{
				
			}
			catch (Exception ex)
			{
				throw new myException("E2_ModuleContainerListEditing:ActionAgentLoadClass:Error building Object from: "+cClassName);
			}
			
		}



		@SuppressWarnings("unchecked")
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				Class[] Params = new Class[0];
                Class c =  Class.forName(this.cClassName);
                Constructor con = c.getDeclaredConstructor(Params); 
                
                // Setzen des/der Paramter für  das neu zu erzeugende Objekt, mit dem grade ermittelten 
                // Construktor
                Object[] oParams = new Object[0];

                // erzeugen des eigentlichen Objeks mit newInstance(parameterliste)
                this.oModulPop = (XX_ModuleContainerListEditPopup)con.newInstance(oParams);
				
				/*
				 * hier wird der content gebaut
				 */
				this.oModulPop.START(E2_ModuleContainerLIST_XML.this.oNaviList,
									 E2_ModuleContainerLIST_XML.this.get_MODUL_IDENTIFIER());
				
				/*
				 * jetzt pruefen, ob alles ok
				 */
				MyString oErrorString = this.oModulPop.get_Check_CanBeShown();
				if (oErrorString == null)
					this.oModulPop.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
				else
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(oErrorString.CTrans(),false)));
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Start des Moduls"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(this.cClassName,false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			catch (Exception ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("E2_ModuleContainerListEditing:ActionAgentLoadClass:Error building Object from: "+cClassName,false)));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.getMessage(),false)));
			}

		}
		
	}
	
	
	
	
}
