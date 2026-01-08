package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

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
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
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
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector_Multiple_Targets;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN_AUFGABE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_CONST;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_BT_NEW_EXTERNAL;



public class MS___AUFGABEN_LIST_BasicModule_Inlay extends E2_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	
	public static final	String HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS =   "HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS";
	public static final	String HASHKEY_LIST_INLAY_BUTTON_WORKFLOW =   "HASHKEY_LIST_INLAY_BUTTON_WORKFLOW";
	public static final String HASHKEY_LIST_INLAY_BUTTON_CONNECTORS = "HASHKEY_LIST_INLAY_BUTTON_CONNECTORS";
	
	public static final String MODULKENNER_ADDON_LIST =			"_ADDON_AUGABE";
	public static final String MODULKENNER_ADDON_MASK =			"_ADDON_AUGABE";


	//hier werden die sql-fieldmaps fuer liste und mask erzeugt, um sie spaeter mit dem restrict-wert fuellen zu koennen
	private MS___LIST_SqlFieldMAP oSQLFieldMapList = new MS___LIST_SqlFieldMAP();
	private MS___MASK_SQLFieldMAP oSQLFieldMapMask = new MS___MASK_SQLFieldMAP();
 
	
	private E2_BasicModuleContainer    oCallingContainer = null;


    //hier den namen des ID-Feldes aus der rufenden Einheit (1-zu-N - Beziehung)
	public static final String CONNECTION_FIELD   =  "ID_MASCHINEN";
	
	public static final String KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON = "_AUFGABE"; 


	//festlegen, wie die hintergrundfarbe von listenkopf und bedienpanel sein soll (integration in maske oder ausklappliste)
	private Color  				oColorBackgroundListHeader = new E2_ColorBase();

	//navigationlist muss klassenobjekt sein
	private E2_NavigationList oNaviList = null;


	
	//die listenbuttons muessen exportiert werden koennen
	private MS___LIST_BT_NEW  oButtonNewInList = null; 
	private MS___LIST_BT_VIEW oButtonViewInList = null; 
	private MS___LIST_BT_EDIT oButtonEditInList = null; 
	private MS___LIST_BT_DELETE oButtonDeleteInList  = null;
//	private MS___LIST_BT_NEW_WORKFLOW oButtonNewWorkflow = null;
	
	private E2_ButtonToSelectVisibleListColumns_and_other_settings  oButtonSelVisibleCols = null;



	public MS___AUFGABEN_LIST_BasicModule_Inlay(E2_BasicModuleContainer  CallingContainer) throws myException
	{
		super();
		
		this.oCallingContainer = CallingContainer;
		
		this.set_MODUL_IDENTIFIER(CallingContainer.get_MODUL_IDENTIFIER()+MS___AUFGABEN_LIST_BasicModule_Inlay.MODULKENNER_ADDON_LIST);
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oNaviList = new E2_NavigationList();
		
		oNaviList.INIT_WITH_ComponentMAP(new MS___LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		MS___LIST_BedienPanel oPanel = new MS___LIST_BedienPanel(oNaviList,new MS___MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		//sonst
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
	

	/**
	 * falls die Kadenz einer Aufgabe geändert wird, muss auch die Kadenz in der aktuellen
	 * noch aktiven Workflow-Aufgabe geändert werden.
	 * 
	 * @param IdMaschine
	 * @param IDMaschineAufgabe
	 * @param nKadenz
	 * @throws myException 
	 */
	public void updateWorkflowEntry(String IdMaschine, String IDMaschineAufgabe, String nKadenz) throws myException{
		// suchen aller connections die von der Aufgabenliste kommen. Damit bekommt man die ID des/der Workflows der dieser Aufgabe zugewiesen wurde
		RECLIST_MODUL_CONNECT listModConnect = new RECLIST_MODUL_CONNECT("QUELLE ='"  + E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE + "' " +  " AND ID_QUELLE = " + IDMaschineAufgabe , "");
		
		for (int i=0; i<listModConnect.size(); i++){
			// jetzt für jeden Eintrag die Workflow-Einträge finden die zum gefundenen Workflow gehören
			RECORD_MODUL_CONNECT recModConnect = listModConnect.get(i);
			String idLaufzettel = recModConnect.get_ID_ZIEL_cUF();
			RECLIST_LAUFZETTEL_EINTRAG listEintrag = new RECLIST_LAUFZETTEL_EINTRAG(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL + "=" + idLaufzettel
																			 + " AND " + RECORD_LAUFZETTEL_EINTRAG.FIELD__ABGESCHLOSSEN_AM + " IS NULL"	,"");
			// das ist die liste der aktuellen aktiven Workflow-Entries, die einen Bezug zur Maschine haben könnten
			Vector<String> vIDEintrag = listEintrag.get_vKeyValues();
			String sIDs = "(" + bibALL.Concatenate(vIDEintrag, ",", "0") + ")";
			
			// jetzt die Einträge finden, die auf die ausgewählte Maschine zeigen, die ID_QUELLE beinhaltet die IDs der Workflow-Einträge, die angepasst werden müssen.
			RECLIST_MODUL_CONNECT listModConnectMaschinen = new RECLIST_MODUL_CONNECT(RECORD_MODUL_CONNECT.FIELD__ZIEL + " = '" + E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE + "' "
																					  + " AND " + RECORD_MODUL_CONNECT.FIELD__ID_ZIEL + " = " + IdMaschine  
																					  + " AND " + RECORD_MODUL_CONNECT.FIELD__ID_QUELLE + " in " + sIDs ,"");
			Vector<String> vSQL = new Vector<String>();
			for (int j=0; j < listModConnectMaschinen.size(); j++){
				try {
					RECORD_LAUFZETTEL_EINTRAG recEintrag = new RECORD_LAUFZETTEL_EINTRAG(listModConnectMaschinen.get(j).get_ID_QUELLE_cUF());
					recEintrag.set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(MyNumberFormater.formatDez(nKadenz, true) );
					vSQL.add(recEintrag.get_SQL_UPDATE_STATEMENT(null, true));
				} catch (Exception e) {
					// irgendwas gieng schief, hier einfach ignorieren
				}
				
			}
			
			bibDB.ExecMultiSQLVector(vSQL, true);
		}
	}
	
	
	/**
	 * ActionAgent der die Kadenz in den Workflow-Einträgen aktualisiert.
	 * @author manfred
	 *
	 */
	private class MS__actionAgentUpdateWorkflows extends XX_ActionAgent{

		private E2_BasicModuleContainer_MASK m_mask = null;
		
		public MS__actionAgentUpdateWorkflows(E2_BasicModuleContainer_MASK mask ) {
			m_mask = mask;
			
		}
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			

			
			String idMaschine = m_mask.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_UnFormatedValue(RECORD_MASCHINEN_AUFGABE.FIELD__ID_MASCHINEN);
			String idAufgabe = m_mask.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_UnFormatedValue(RECORD_MASCHINEN_AUFGABE.FIELD__ID_MASCHINEN_AUFGABE);
			String kadenz_alt = m_mask.get_vCombinedComponentMAPs().get(0).get_oInternalSQLResultMAP().get_UnFormatedValue(RECORD_MASCHINEN_AUFGABE.FIELD__INTERVALL_TAGE);
			
			MyE2_DB_TextField tf = (MyE2_DB_TextField)m_mask.get_vCombinedComponentMAPs().get(0).get__Comp(RECORD_MASCHINEN_AUFGABE.FIELD__INTERVALL_TAGE);
			String kadenz_neu = tf.get_cActualMaskValue().replace(".", "");
			
			MS___AUFGABEN_LIST_BasicModule_Inlay.this.updateWorkflowEntry(idMaschine, idAufgabe, kadenz_neu);
		}
		
	}

	


	private class MS___LIST_BedienPanel extends MyE2_Column 
	{
		
		public MS___LIST_BedienPanel(E2_NavigationList oNaviList,E2_BasicModuleContainer_MASK oMaskContainer) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
			
			MS___AUFGABEN_LIST_BasicModule_Inlay oThis = MS___AUFGABEN_LIST_BasicModule_Inlay.this;
			
			MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
			
		
			ColumnLayoutData  oColLayout = new ColumnLayoutData();
			oColLayout.setBackground(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oColLayout.setInsets(new Insets(0,0,0,2));
			
			this.add(oRowForComponents, oColLayout);

			RowLayoutData  oRowLayout = new RowLayoutData();
			oRowLayout.setBackground(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayout.setInsets(new Insets(1,1,3,1));
			
			RowLayoutData  oRowLayoutWide = new RowLayoutData();
			oRowLayoutWide.setBackground(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oRowLayoutWide.setInsets(new Insets(1,1,10,1));
			
			oRowForComponents.add(oThis.oButtonSelVisibleCols = new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  		oRowLayoutWide);
			oRowForComponents.add(oThis.oButtonNewInList = 		new MS___LIST_BT_NEW(oNaviList,oMaskContainer), 			oRowLayout);
			oRowForComponents.add(oThis.oButtonViewInList =		new MS___LIST_BT_VIEW(oNaviList,oMaskContainer), 			oRowLayout);
			oRowForComponents.add(oThis.oButtonEditInList =		new MS___LIST_BT_EDIT(oNaviList,oMaskContainer), 			oRowLayout);
			oRowForComponents.add(oThis.oButtonDeleteInList =	new MS___LIST_BT_DELETE(oNaviList), 						oRowLayoutWide);
			oRowForComponents.add(new E2_ButtonUpDown_NavigationList_to_Archiv(		oNaviList,
																					E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE,
																					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()), oRowLayoutWide);
//			oRowForComponents.add(oThis.oButtonNewWorkflow =    new MS___LIST_BT_NEW_WORKFLOW(oNaviList,oMaskContainer),	oRowLayoutWide);
		
		}
	
	}
	
	
	public MS___LIST_BT_NEW get_oButtonNewInList() 
	{
		return oButtonNewInList;
	}
	
	public MS___LIST_BT_VIEW get_oButtonViewInList() 
	{
		return oButtonViewInList;
	}
	
	public MS___LIST_BT_EDIT get_oButtonEditInList() 
	{
		return oButtonEditInList;
	}
	
	public MS___LIST_BT_DELETE get_oButtonDeleteInList() 
	{
		return oButtonDeleteInList;
	}



	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonSelVisibleCols() 
	{
		return oButtonSelVisibleCols;
	}

	
	private class MS___LIST_BT_DELETE extends MyE2_Button
	{
	
		public MS___LIST_BT_DELETE(E2_NavigationList onavigationList)
		{
			super(E2_ResourceIcon.get_RI("delete_mini.png") , E2_ResourceIcon.get_RI("delete_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"LOESCHE_"+MS___AUFGABEN_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
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
	
	
	
	
	
	
	
	private class MS___LIST_BT_EDIT extends MyE2_Button
	{
	
		public MS___LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png") , E2_ResourceIcon.get_RI("edit_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));

			//die freigabe der buttons erfolgt im ubergeordneten modul 
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
																"BEARBEITE_"+MS___AUFGABEN_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));

		}
		
		private class ownActionAgent extends ButtonActionAgentEDIT
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Bearbeiten eines -Eintrages"), onavigationList, omaskContainer, oownButton, null, null);
				
				this.get_oButtonMaskSave().add_oActionAgent(new MS__actionAgentUpdateWorkflows(omaskContainer));
				
			}
		}
		
	
		
		
		
	}
	
	
	
	
	private class MS___LIST_BT_NEW extends MyE2_Button
	{
	
		public MS___LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("new_mini.png") , E2_ResourceIcon.get_RI("new_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"NEUEINGABE_"+MS___AUFGABEN_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
			
			
		}
		
		private class ownActionAgent extends ButtonActionAgentNEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Neueingabe... "), onavigationList, omaskContainer, oownButton, null,null);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	private class MS___LIST_BT_VIEW extends MyE2_Button
	{
	
		public MS___LIST_BT_VIEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
		{
			super(E2_ResourceIcon.get_RI("view_mini.png") , E2_ResourceIcon.get_RI("view_mini__.png"));
			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
									"ANZEIGE_"+MS___AUFGABEN_LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
	
		}
		
		private class ownActionAgent extends ButtonActionAgentVIEW
		{
			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
			{
				super(new MyE2_String("Anzeige von von TODO-Eintraegen"), onavigationList, omaskContainer, oownButton,null);
			}
		}
		
	}
	
//	/**
//	 * Button-Klasse zum erzeugen eines neuen Workflows innerhalb der Row der Aufgaben
//	 * @author manfred
//	 *
//	 */
//	private class MS___LIST_BT_NEW_WORKFLOW_INROW  extends MyE2_ButtonInLIST{
//
//		
//		String m_idMaschine = null;
//		String m_idMaschinenAufgabe = null;
//		String m_sKadenz = null;
//		
//		public MS___LIST_BT_NEW_WORKFLOW_INROW(E2_NavigationList onavigationList /*, E2_BasicModuleContainer_MASK omaskContainer*/)
//		{
//			super(E2_ResourceIcon.get_RI("workflow.png") , E2_ResourceIcon.get_RI("leer.png"));
//			
//			this.add_oActionAgent(new ownActionAgent(onavigationList /*,omaskContainer*/,this));
//			
//			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
//					"WORKFLOW_ANLEGEN_" + MS___LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
//		}
//		
//		
//		/**
//		 * Setzen der zusätzlichen Parameter in der Button-Klasse
//		 * @param IDMaschine
//		 * @param IDMaschinenAufgabe
//		 * @param Kadenz
//		 */
//		public void setParameters(String IDMaschine, String IDMaschinenAufgabe, String Kadenz){
//			m_idMaschine = IDMaschine;
//			m_idMaschinenAufgabe = IDMaschinenAufgabe;
//			m_sKadenz = Kadenz;
//		}
//		
//		
//		
//		private class ownActionAgent extends XX_ActionAgent
//		{
//			E2_NavigationList m_onavigationList = null;
//			E2_BasicModuleContainer_MASK m_omaskContainer = null;;
//			MyE2_Button m_oownButton = null; 
//			
//			public ownActionAgent(E2_NavigationList onavigationList /*, E2_BasicModuleContainer_MASK omaskContainer*/, MyE2_Button oownButton)
//			{
//				super();
////				m_omaskContainer = omaskContainer;
//				m_onavigationList = onavigationList;
//				m_oownButton = oownButton;
//			}
//
//			
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				
//				if (m_idMaschine == null || m_idMaschinenAufgabe == null){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens eine Aufgabe ausgewählt sein."));
//					return;
//				}
//				
//					
//				RECORD_MASCHINEN recMaschine = new RECORD_MASCHINEN(m_idMaschine);
//				RECORD_MASCHINEN_AUFGABE recAufgabe = new RECORD_MASCHINEN_AUFGABE(m_idMaschinenAufgabe);
//				RECORD_MASCHINEN_AUFGABE_TYP recTyp = recAufgabe.get_UP_RECORD_MASCHINEN_AUFGABE_TYP_id_maschinen_aufgabe_typ();
//					
//					
//				String sWorkflowTitle = recMaschine.get_KFZKENNZEICHEN_cUF() + "\n" + recMaschine.get_BESCHREIBUNG_cUF_NN("") + "\n" + recMaschine.get_BEMERKUNG_cUF_NN("");
//				String sWorkflowEntry = recTyp.get_AUFGABE_TYP_cUF_NN("") + ": " + recAufgabe.get_BEMERKUNG_cUF_NN("");
//				
//				//
//				// Workflow anlegen
//				WF_Handler oWFHandler = new WF_Handler();
//				String idWorkflowEntry = oWFHandler.createNew_Workflow_And_Entry(sWorkflowTitle, sWorkflowEntry, m_sKadenz);
//				String idWorkflow = oWFHandler.getIDWorkflowOfCreatedEntry();
//				
//				//
//				// Vernküpfung anlegen
//				if (idWorkflowEntry != null){
//					MODUL_LINK_SQL_Factory mlFactory = new MODUL_LINK_SQL_Factory();
//					
//					Vector<String> vSql = mlFactory.get_SQL_Statement_For_MaschinenAufgabe_to_Workflow(m_idMaschinenAufgabe, idWorkflow, "Springe zum Workflow");
//					vSql.addAll( mlFactory.get_SQL_Statement_For_Workflow_to_Maschine(idWorkflowEntry, m_idMaschine, "Springe zur Wartungsaufgabe"));
//					
//					// starten der Transaktion
//					MyE2_MessageVector vRet = bibDB.ExecMultiSQLVector(vSql, true);
//				}
//								
//				// neue Workflows anlegen
//				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neuer Workflow angelegt"));
//				
//			}
//			
//			
//		}
//
//	}

	
//	private class MS___LIST_BT_NEW_WORKFLOW  extends MyE2_Button{
//
//		public MS___LIST_BT_NEW_WORKFLOW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
//		{
//			super("Laufzettel anlegen",E2_ResourceIcon.get_RI("workflow.png") , E2_ResourceIcon.get_RI("leer.png"));
//			
//			this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
//			
//			this.add_GlobalValidator(new E2_ButtonAUTHValidator(MS___LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER(),
//					"WORKFLOW_ANLEGEN_" + MS___LIST_BasicModule_Inlay.KENN_NAME_FUER_VALIDIERUNG_DER_LISTENBUTTON));
//		}
//		
//		
//		private class ownActionAgent extends XX_ActionAgent
//		{
//			E2_NavigationList m_onavigationList = null;
//			E2_BasicModuleContainer_MASK m_omaskContainer = null;;
//			MyE2_Button m_oownButton = null; 
//			
//			public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
//			{
//				super();
//				m_omaskContainer = omaskContainer;
//				m_onavigationList = onavigationList;
//				m_oownButton = oownButton;
//			}
//
//			
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				
//				String idMaschine = null;
//				String idMaschinenAufgabe = null;
//				String sKadenz  = null;
//				String sIDWorkflow = null;
//				
//				Vector<String> vIDs = oNaviList.get_vSelectedIDs_Unformated();
//				
//				if (vIDs.size() == 0){
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens eine Aufgabe ausgewählt sein."));
//					return;
//				}
//				
//				for (int i=0; i< vIDs.size(); i++){
//					idMaschine = m_onavigationList.get_ComponentMAP(vIDs.get(i)).get_oInternalSQLResultMAP().get_UnFormatedValue("ID_MASCHINEN");
//					idMaschinenAufgabe = m_onavigationList.get_ComponentMAP(vIDs.get(i)).get_oInternalSQLResultMAP().get_UnFormatedValue("ID_MASCHINEN_AUFGABE");
//					sKadenz = m_onavigationList.get_ComponentMAP(vIDs.get(i)).get_oInternalSQLResultMAP().get_UnFormatedValue("INTERVALL_TAGE");
//					
//					RECORD_MASCHINEN recMaschine = new RECORD_MASCHINEN(idMaschine);
//					RECORD_MASCHINEN_AUFGABE recAufgabe = new RECORD_MASCHINEN_AUFGABE(idMaschinenAufgabe);
//					RECORD_MASCHINEN_AUFGABE_TYP recTyp = recAufgabe.get_UP_RECORD_MASCHINEN_AUFGABE_TYP_id_maschinen_aufgabe_typ();
//					
//					
//					String sWorkflowTitle = recMaschine.get_KFZKENNZEICHEN_cUF() + "\n" + recMaschine.get_BESCHREIBUNG_cUF_NN("") + "\n" + recMaschine.get_BEMERKUNG_cUF_NN("");
//					String sWorkflowEntry = recTyp.get_AUFGABE_TYP_cUF_NN("") + ": " + recAufgabe.get_BEMERKUNG_cUF_NN("");
//				
//					//
//					// Workflow anlegen
//					WF_Handler oWFHandler = new WF_Handler();
//					String idWorkflowEntry = oWFHandler.createNew_Workflow_And_Entry(sWorkflowTitle, sWorkflowEntry, sKadenz);
//					String idWorkflow = oWFHandler.getIDWorkflowOfCreatedEntry();
//					
//					//
//					// Vernküpfung anlegen
//					if (idWorkflowEntry != null){
//						MODUL_LINK_SQL_Factory mlFactory = new MODUL_LINK_SQL_Factory();
//						
//						Vector<String> vSql = mlFactory.get_SQL_Statement_For_MaschinenAufgabe_to_Workflow(idMaschinenAufgabe, idWorkflow, "Springe zum Workflow");
//						vSql.addAll( mlFactory.get_SQL_Statement_For_Workflow_to_Maschine(idWorkflowEntry, idMaschine, "Springe zur Wartungsaufgabe"));
//						
//						// starten der Transaktion
//						MyE2_MessageVector vRet = bibDB.ExecMultiSQLVector(vSql, true);
//					}
//				
//				}
//				
//				// neue Workflows anlegen
//				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neuer Workflow angelegt"));
//				
//			}
//			
//			
//		}
//
//	}
	
	
	
	
	private class MS___LIST_ComponentMap extends E2_ComponentMAP 
	{
	
		public MS___LIST_ComponentMap() throws myException
		{
			super(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oSQLFieldMapList);
			
			SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
			
			this.add_Component(MS___AUFGABEN_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component(MS___AUFGABEN_LIST_BasicModule_Inlay.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
	
			//hier kommen die Felder	

			// Button zum anlegen eines Workflows für die Aufgabe
			MyE2_Row_EveryTimeEnabled oRowWorkflowButtons = new MyE2_Row_EveryTimeEnabled();
			this.add_Component(MS___AUFGABEN_LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS, oRowWorkflowButtons,new MyE2_String("Laufzettel"));
			
			this.add_Component(new MS__DB_SelectField_AufgabenTyp(oFM.get_("ID_MASCHINEN_AUFGABE_TYP"), 300), 	new MyE2_String("Aufgabentyp"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG"),true), 							new MyE2_String("Bemerkung"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("INTERVALL_TAGE")), 							new MyE2_String("Intervall"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_MASCHINEN")), 								new MyE2_String("ID-Maschine"));
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_MASCHINEN_AUFGABE")), 						new MyE2_String("ID-Aufgabe"));
						
			ownGridLayoutDataLeft  oGLLeftTitel = new ownGridLayoutDataLeft(
					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, new Insets(2,1,4,1));
			
			ownGridLayoutDataRight  oGLRightTitel = new ownGridLayoutDataRight(
					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			ownGridLayoutDataLeft  oGLLeftInhalt = new ownGridLayoutDataLeft(
					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, new Insets(2,1,4,1));
			
			ownGridLayoutDataRight  oGLRightInhalt = new ownGridLayoutDataRight(
					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader, E2_INSETS.I_1_1_1_1);

			this.get__Comp("BEMERKUNG").EXT().set_oColExtent(new Extent(300));

			
			this.get__Comp(MS___AUFGABEN_LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS).EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_MASCHINEN").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_MASCHINEN_AUFGABE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("ID_MASCHINEN_AUFGABE_TYP").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			this.get__Comp("INTERVALL_TAGE").EXT().set_oLayout_ListElement(oGLLeftInhalt);
			
			this.get__Comp(MS___AUFGABEN_LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS).EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("BEMERKUNG").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_MASCHINEN").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_MASCHINEN_AUFGABE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("ID_MASCHINEN_AUFGABE_TYP").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);
			this.get__Comp("INTERVALL_TAGE").EXT().set_oLayout_ListTitelElement(oGLLeftTitel);

			this.get__Comp(MS___AUFGABEN_LIST_BasicModule_Inlay.NAME_OF_CHECKBOX_IN_LIST).EXT().
									set_oCompTitleInList(new ownSelectPopupAllNoneInvert());
	
			MutableStyle  oStyleSmallTextLightColor = new MutableStyle();
			oStyleSmallTextLightColor.setProperty(PROPERTY_BACKGROUND, MS___AUFGABEN_LIST_BasicModule_Inlay.this.oColorBackgroundListHeader);
			oStyleSmallTextLightColor.setProperty(PROPERTY_FONT, new E2_FontItalic(-2));

            //den buttons/komponenten des listenheaders einen eigenen style verpassen
			
			this.get__Comp(MS___AUFGABEN_LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS).EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("BEMERKUNG").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_MASCHINEN").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_MASCHINEN_AUFGABE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("ID_MASCHINEN_AUFGABE_TYP").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			this.get__Comp("INTERVALL_TAGE").EXT().set_oStyle_4_ListHeaderComponent(oStyleSmallTextLightColor);
			
			this.set_oSubQueryAgent(new MS___LIST_FORMATING_Agent());
		}
	
	
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				MS___LIST_ComponentMap oCopy = new MS___LIST_ComponentMap();
				return oCopy;
			}
			catch (myException ex)
			{
				throw new myExceptionCopy(ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}
	
	}
	
	
	
	
	
	
	private class MS___LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}
	
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{

			MyE2_Row_EveryTimeEnabled oRowWorkflowButtons = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp(MS___AUFGABEN_LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_ROW_WORKFLOW_BUTTONS);
			
			oRowWorkflowButtons.removeAll();
			
//			WF_LIST_BT_NEW_EXTERNAL  oBtn = (WF_LIST_BT_NEW_EXTERNAL) oMAP.get__Comp(MS___LIST_BasicModule_Inlay.HASHKEY_LIST_INLAY_BUTTON_WORKFLOW);
			
			String idMaschine = oUsedResultMAP.get_UnFormatedValue("ID_MASCHINEN");
			String idAufgabe = oUsedResultMAP.get_UnFormatedValue("ID_MASCHINEN_AUFGABE");
			String intervall_tage =  oUsedResultMAP.get_UnFormatedValue("INTERVALL_TAGE");
			
			String sAufgabe = oUsedResultMAP.get_UnFormatedValue("BEMERKUNG");
			String sTyp = ((MS__DB_SelectField_AufgabenTyp)oMAP.get__Comp("ID_MASCHINEN_AUFGABE_TYP")).get_cActualMaskValue();
			String sWorkflowEntry = sTyp + ": " + sAufgabe;

			RECORD_MASCHINEN recMaschine = new RECORD_MASCHINEN(idMaschine);
			String sWorkflowTitle = recMaschine.get_KFZKENNZEICHEN_cUF() + "\n" + recMaschine.get_BESCHREIBUNG_cUF_NN("") + "\n" + recMaschine.get_BEMERKUNG_cUF_NN("");
			// 2011-09-30: Herr Leber will die Aufgabe noch in den Workflow-Titel haben
			sWorkflowTitle += "\n" + sWorkflowEntry;
			
			WF_LIST_BT_NEW_EXTERNAL  oBtnNew = new WF_LIST_BT_NEW_EXTERNAL(oMAP, null,null,null,null	);
			oBtnNew.setParameterForInitialisation("KADENZ_NACH_ABSCHLUSS", intervall_tage);
			oBtnNew.setParameterForInitialisation(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG, sWorkflowTitle);
			oBtnNew.setParameterForInitialisation("AUFGABE", sWorkflowEntry);
			// nach dem speichern ein refresh der Maske durchführen
			oBtnNew.add_XXActionAgentsToButtonSaveMask(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					MS___AUFGABEN_LIST_BasicModule_Inlay.this.oNaviList.RefreshList();
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Laufzettel gespeichert!"));
				}
			});
			
			oBtnNew.setModulLinkInformations(
						E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE, idMaschine, "Springe zur Maschine",
						E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE, idAufgabe, "Springe zum Laufzettel"
						);
			oRowWorkflowButtons.add(oBtnNew);
			
			MODUL_LINK_Connector_Multiple_Targets oLink = new MODUL_LINK_Connector_Multiple_Targets(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE);
			oLink.initConnector(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE, idAufgabe, MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer);
			oRowWorkflowButtons.add(oLink);
		}
	
	}
	
	
	
	
	private class MS___LIST_SqlFieldMAP extends Project_SQLFieldMAP 
	{
	
		public MS___LIST_SqlFieldMAP() throws myException 
		{
			super("JT_MASCHINEN_AUFGABE", MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD , false);
			
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_MASCHINEN_AUFGABE",MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD,MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);

			this.initFields();
		}
		
	}
	
		
	
	private class MS___MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
	{
	
		public MS___MASK_BasicModuleContainer() throws myException
		{
			super(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer.get_MODUL_IDENTIFIER()+MS___AUFGABEN_LIST_BasicModule_Inlay.MODULKENNER_ADDON_MASK);
						
			this.set_bVisible_Row_For_Messages(true);
			
			MS___MASK_ComponentMAP oMS___MASK_ComponentMAP = new MS___MASK_ComponentMAP();
			
			this.INIT(oMS___MASK_ComponentMAP, new MS__MASK(oMS___MASK_ComponentMAP), new Extent(700), new Extent(400));
		}
		
		
	}
	
	
	
	
	private class MS___MASK_ComponentMAP extends E2_ComponentMAP 
	{
	
		public MS___MASK_ComponentMAP() throws myException
		{
			super(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oSQLFieldMapMask);
			
			SQLFieldMAP oFM = this.get_oSQLFieldMAP();
			
	
			//hier kommen die Felder	
			this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG"),500,8),		 						new MyE2_String("Bemerkung"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MASCHINEN")), 									new MyE2_String("ID-Maschine"));
			this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MASCHINEN_AUFGABE")), 							new MyE2_String("ID-Aufgabe"));
			this.add_Component(new MS__DB_SelectField_AufgabenTyp( oFM.get_("ID_MASCHINEN_AUFGABE_TYP"),300), 	new MyE2_String("Typ"));
			this.add_Component(new MyE2_DB_TextField(oFM.get_("INTERVALL_TAGE"),true,100,10,false), 			new MyE2_String("Intervall"));
			
			// das Modul für die Verlinkung einbauen
			this.add_Component(MS__CONST.HASHKEY_MASK_INLAY_CONNECTOR, new MODUL_LINK_Connector() , new MyE2_String("Connector"));

			/*
	 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
			 */
			this.set_oMAPSettingAgent(new MS___MASK_MapSettingAgent());
			
			/*
			 * ermoeglicht formatierungen von zusatzinfos in der maske
			 */
			this.set_oSubQueryAgent(new MS___MASK_FORMATING_Agent());
		}
		
	}
	
	
	
	private class MS___MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
	{
	
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
			MODUL_LINK_Connector o = (MODUL_LINK_Connector)oMAP.get__Comp(MS__CONST.HASHKEY_MASK_INLAY_CONNECTOR);
			o.removeAll();
		}
	
		
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{
			/***
			 * Setzen des Connectors 
			 */
			String idMaschinenAufgabe = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_MASCHINEN_AUFGABE");
			
			MODUL_LINK_Connector o = (MODUL_LINK_Connector)oMAP.get__Comp(MS__CONST.HASHKEY_MASK_INLAY_CONNECTOR);
			o.removeAll();

			// Die Maske liegt innerhalb der aufrufenden Maske: Es müssen beim Sprung 2 Fenster geschlossen werden 
			Vector<E2_BasicModuleContainer> m_container_to_close = new Vector<E2_BasicModuleContainer>();
			m_container_to_close.add( oMAP.get_oModulContainerMASK_This_BelongsTo() );
			m_container_to_close.add( MS___AUFGABEN_LIST_BasicModule_Inlay.this.oCallingContainer );

			o.initConnector(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE, idMaschinenAufgabe, m_container_to_close);
//			o.initConnector(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE, idMaschinenAufgabe, oMAP.get_oModulContainerMASK_This_BelongsTo());
		}

	}
	
	
	
	
	
	private class MS___MASK_MapSettingAgent extends XX_MAP_SettingAgent {
	
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
		
	
		}
	
		public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
		{
			
		}
		
		private void do_Disable(E2_ComponentMAP oMap,String[] cFieldList,boolean bDisalbed) throws myException
		{
			for (int i=0;i<cFieldList.length;i++)
			{
				oMap.get__Comp(cFieldList[i]).EXT().set_bDisabledFromInteractive(bDisalbed);
			}
		}
	
	}
	
	
	
	
	
	private class MS___MASK_SQLFieldMAP extends Project_SQLFieldMAP 
	{
	
		public MS___MASK_SQLFieldMAP() throws myException 
		{
			super("JT_MASCHINEN_AUFGABE", MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD, false);
		
			/*
			 * restrict: id_adresse_basis - je nach situation
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_MASCHINEN_AUFGABE",MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD,MS___AUFGABEN_LIST_BasicModule_Inlay.CONNECTION_FIELD,new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);

			/*
			 * beispiel fuer die definition von standard-werten
			 */
			//this.get_("BEMERKUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("ID_MASCHINEN_AUFGABE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("ID_MASCHINEN_AUFGABE_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("INTERVALL_TAGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			
			this.initFields();
		}
	
	}
	
	
	
	
	
	/*
	* maskenvariante mit TabbedPane 
	*/
	private class MS__MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
	{
	
		private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
		
		public MS__MASK(MS___MASK_ComponentMAP oMap) throws myException
		{
			super(MyE2_Column.STYLE_NO_BORDER());
		
			E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
	
			
			// 5 Grids
			MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
			
			
			this.add(oGrid0, E2_INSETS.I_2_2_2_2);
			
			//hier kommen die Felder	
			oFiller.add_Line(oGrid0, new MyE2_String("ID-Maschine:"), 1, 				"ID_MASCHINEN|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("ID-Aufgabe:"), 1, 				"ID_MASCHINEN_AUFGABE|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Aufgaben-Typ:"), 1, 				"ID_MASCHINEN_AUFGABE_TYP|#  |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Interval:"), 1, 					"INTERVALL_TAGE|# (Tage) |",3);
			oFiller.add_Line(oGrid0, new MyE2_String("Bemerkung:"), 1, 					"BEMERKUNG|#  |",3);
	
			// objekt für die Connection zu anderen Moduln
			oFiller.add_Line(oGrid0, new MyE2_String(""),1, MS__CONST.HASHKEY_MASK_INLAY_CONNECTOR,3 );
			
			
			this.vMaskGrids.add(oGrid0);
			
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

	private class ownGridLayoutDataRight extends GridLayoutData
	{
		public ownGridLayoutDataRight(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}

	
	private class ownGridLayoutDataCenter extends GridLayoutData
	{
		public ownGridLayoutDataCenter(Color oColBack, Insets inSet) 
		{
			super();
			this.setInsets(inSet);
			this.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
			this.setBackground(oColBack);
		}
	}

	
	private class ownSelectPopupAllNoneInvert extends E2_PopUpSelectAllNoneInvert
	{

		public ownSelectPopupAllNoneInvert() 
		{
			super(MS___AUFGABEN_LIST_BasicModule_Inlay.this.oNaviList.get_vComponentMAPS());
			
			this.set_oIconAktiv(E2_ResourceIcon.get_RI("popup_mini.png"));
			this.set_oIconInactiv(E2_ResourceIcon.get_RI("popup_mini__.png"));
		}
		
	}
	

	
	
	
}
