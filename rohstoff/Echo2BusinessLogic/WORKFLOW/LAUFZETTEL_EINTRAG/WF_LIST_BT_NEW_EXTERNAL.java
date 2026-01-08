package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UB_SelectField_USERS;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Button_Goto_Modul;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_SQL_Factory;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_BasicModuleContainer;
import rohstoff.utils.ECHO2.E2_AuswahlSelectorUsers;
import echopointng.KeyStrokeListener;

public class WF_LIST_BT_NEW_EXTERNAL extends MyE2_Button{
	/***
	 * Button-Klasse :Neuanlegen von Laufzettel-Einträgen
	 *  
	 * @author manfred
	 * @date 
	 */
		private String m_ID_Laufzettel = null;
		private String m_ID_Eintrag_Parent = null;
		private String m_ID_User_Bearbeiter = null;
		private String m_ID_User_Besitzer = null;
		private String m_ID_User_Supervisor_Laufzettel = null;
		
		// Falls ein Workflow-Entry neu generiert wurde, wird hier die ID abgelegt.
		private String m_ID_Laufzettel_neu = null;
		

		// Informationen um einen Linkeintrag zu generieren, falls gewünscht...
		private boolean m_bCreateConnector = false;
		private String m_ID_ConnectorTo = null;
		private String m_ID_ConnectorFrom = null;
		private String m_ModulNameWhereToJumpTo = null;
		private String m_ModulNameWhereToJumpFrom = null;
		private String m_ConnectorTextJumpTo = null;
		private String m_ConnectorTextJumpFrom = null;
		
		
		
		// Speicherbutton
//		ownMaskButtonSaveMask m_BT_saveMask = null;
		Vector<XX_ActionAgent> m_vActionAgentsAfterSave = new Vector<XX_ActionAgent>();
		
		// das Objekt, in dem der Button liegt
		private Object m_Parent = null;

		private HashMap<String,String> m_ParameterForInitialisationOfMask = new HashMap<String, String>();
		
		/***
		 * Neuanlegen eines Laufzettels
		 * @param ID_Laufzettel
		 * @param ID_Eintrag_Parent
		 * @param ID_User_Bearbeiter
		 * @param ID_User_Besitzer TODO
		 */
		public WF_LIST_BT_NEW_EXTERNAL( Object parent, 
										String ID_Laufzettel, 
										String ID_Eintrag_Parent,
										String ID_User_Bearbeiter, 
										String ID_User_Besitzer)
		{
			super();
			
			m_Parent = parent;
			
			m_ID_Eintrag_Parent = ID_Eintrag_Parent;
			m_ID_Laufzettel = ID_Laufzettel;
			m_ID_User_Bearbeiter = ID_User_Bearbeiter;
			m_ID_User_Besitzer = ID_User_Besitzer;
			m_ID_User_Supervisor_Laufzettel = null;
			

			E2_ResourceIcon ico_new  = E2_ResourceIcon.get_RI("new.png");
			
			if (m_ID_Laufzettel == null){
				ico_new = E2_ResourceIcon.get_RI("workflow_new.png");
				this.add_GlobalAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, "NEUER_LAUFZETTEL_EINTRAG_MIT_KOPF");

				this.setToolTipText(new MyE2_String("Einen Laufzettel mit dem ersten Eintrag anlegen.").CTrans());
			} else {
				this.add_GlobalAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, "NEUER_LAUFZETTEL_EINTRAG");

				if (ID_Eintrag_Parent != null){
					this.setToolTipText(new MyE2_String("Eine neuen Folge-Eintrag einfügen.").CTrans());
				} else {
					this.setToolTipText(new MyE2_String("Eine neuen Eintrag zum Laufzettel einfügen.").CTrans());
				}
			}

			this.__setImages(ico_new, ico_new);
			
			this.add_oActionAgent(new NewActionAgent());
			
			//Validierung der Laufzettel-Einträge
			this.add_IDValidator(new ValidateLaufzettelEintrag());
			
		}


		private class NewActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				execInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Laufzettel));
				if (!bibMSG.get_bHasAlarms())
				{
					WF_MASK_BasicModuleContainer oMaskEintrag = new WF_MASK_BasicModuleContainer(
							WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Laufzettel,
							WF_LIST_BT_NEW_EXTERNAL.this.m_ID_User_Bearbeiter,
							WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Eintrag_Parent);

				
					// falls Parameter im Button gesetzt wurden, werden sie hier an die Maske übergeben
					if (m_ParameterForInitialisationOfMask.size() > 0){
						for (String key : m_ParameterForInitialisationOfMask.keySet()){
							String value = m_ParameterForInitialisationOfMask.get(key);
							((WF_MASK_ComponentMAP)oMaskEintrag.get_vCombinedComponentMAPs().get(0)).setParameterForInitialisation(key,value);
						}
					}
			
					
					oMaskEintrag.get_oRowForButtons().removeAll();
					ownMaskButtonSaveMask m_BT_saveMask = new ownMaskButtonSaveMask(oMaskEintrag, new E2_SaveMaskStandard(oMaskEintrag,	null), null, null);

					
					m_BT_saveMask.add_oActionAgent(new XX_ActionAgent()
					{
						@Override
						public void executeAgentCode(ExecINFO execInfo)
								throws myException
						{
							
							// Explizite Behandlung abhängig vom aufrufenden Objekt: der Aufruf innerhalb der Seite
							if (WF_LIST_BT_NEW_EXTERNAL.this.m_Parent != null ) {
								
								if (WF_LIST_BT_NEW_EXTERNAL.this.m_Parent instanceof WF_LIST_EXPANDER_4_Compressed_Entries ) {
									((WF_LIST_EXPANDER_4_Compressed_Entries)WF_LIST_BT_NEW_EXTERNAL.this.m_Parent).refreshDaughterComponent();
								}
								if (WF_LIST_BT_NEW_EXTERNAL.this.m_Parent instanceof WF_HEAD_LIST_BasicModuleContainer ) {
									((WF_HEAD_LIST_BasicModuleContainer)WF_LIST_BT_NEW_EXTERNAL.this.m_Parent).get_oNaviListFirstAdded().RefreshList();
								}
							}
						}

					});
					
					
					// jetzt noch die externen Agents dazufügen
					for (XX_ActionAgent o : m_vActionAgentsAfterSave){
						m_BT_saveMask.add_oActionAgent(o);
					}

					oMaskEintrag.get_oRowForButtons().add( m_BT_saveMask );
					oMaskEintrag.get_oRowForButtons().add( new MaskButtonCancelMaskSTANDARD(oMaskEintrag) );

					// Beispiel wie man einen weiteren Button in die
					// Buttonleiste einbauen kann....
					// oMaskEintrag.get_oRowForButtons().add(new MyE2_Button(new
					// MyE2_String("Hello")));

					//oMaskEintrag.CREATE_AND_PREPARE_MASK_WINDOW(E2_ComponentMAP
					// .STATUS_NEW_EMPTY,null);

					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskEintrag.get_vCombinedComponentMAPs();
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Laufzettel);

					
					oMaskEintrag.CREATE_AND_SHOW_POPUPWINDOW(null, null,new MyE2_String("Neuer Laufzettel-Eintrag"));

				}
			}

		}


		
		
		
		private class ownMaskButtonSaveMask extends MyE2_ButtonWithKey
		{
			
			/*
			 * map enthaelt die komponenten der maske
			 */
			private E2_SaveMASK 					oMaskSaver = null;
			private MyDBToolBox						oDB = null;
			private E2_BasicModuleContainer_MASK	oMaskContainer = null;

			public ownMaskButtonSaveMask(	E2_BasicModuleContainer_MASK 				MaskContainer,
											E2_SaveMASK 								maskSaver, 
											E2_NavigationList 							onavList, 
											E2_BasicModuleContainer_PopUp_BeforeExecute oPOPUP_BeforeExecute)
			{
				super(E2_ResourceIcon.get_RI("save.png"), true,KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_RETURN);
				this.oMaskSaver = maskSaver;
				this.oMaskContainer = MaskContainer;
				this.add_oActionAgent(new ownActionAgent(oPOPUP_BeforeExecute));
				this.oDB = bibALL.get_myDBToolBox();
			}

			
			protected void finalize()
			{
				bibALL.destroy_myDBToolBox(this.oDB);
			}
			
			
			private class ownActionAgent extends XX_ActionAgent
			{
				
				public ownActionAgent(E2_BasicModuleContainer_PopUp_BeforeExecute beforeExecute)
				{
					super();
					this.set_oPopup_ContainerBeforeExecute(beforeExecute);
				}


				public void executeAgentCode(ExecINFO oExecInfo)
				{
					ownMaskButtonSaveMask oThis = ownMaskButtonSaveMask.this;
					
					try
					{
						E2_ComponentMAP oMapMask = ownMaskButtonSaveMask.this.oMaskContainer.get_vCombinedComponentMAPs().get(0);
						
						// auswahlfeld für die Benutzerauswahl
						E2_AuswahlSelectorUsers oSelektorUsers = (E2_AuswahlSelectorUsers)oMapMask.get__Comp_From_RealComponents(WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL);
						MyE2_DB_SelectField oSelectID_USER = (MyE2_DB_SelectField)oMapMask.get__Comp_From_RealComponents("ID_USER_BEARBEITER");
						
						// wenn keine Laufzettel-Id vorhanden ist, einen neuen Eintrag erzeugen und die ID übergeben...
						if (m_ID_Laufzettel == null){
							
							RECORDNEW_LAUFZETTEL recnew = new RECORDNEW_LAUFZETTEL();

							// den Text des Laufzettels ermitteln
							MyE2_TextArea oTextLaufzettel = (MyE2_TextArea) oMapMask.get__Comp(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG);
							String sText = oTextLaufzettel.getText();
							
							// den Supervisor des Laufzettels ermitteln
							UB_SelectField_USERS oSupervisor = (UB_SelectField_USERS) oMapMask.get__Comp(WF_CONST.HASH_LAUFZETTEL_SUPERVISOR);
							String idSupervisor = oSupervisor.get_ActualWert();
						    
					
							RECORD_LAUFZETTEL_EINTRAG o;
							
							
							// Default-Status des Laufzettels ermitteln 
							RECLIST_LAUFZETTEL_STATUS listStatus = new RECLIST_LAUFZETTEL_STATUS("NVL(ISDEFAULT,'N') = 'Y'", "");
							if (listStatus.size() > 0){
								RECORD_LAUFZETTEL_STATUS rec =  listStatus.get(0);
								recnew.set_NEW_VALUE_ID_LAUFZETTEL_STATUS(rec.get_ID_LAUFZETTEL_STATUS_cUF());
							} else {
								bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es ist kein Standard-Status für den Laufzettel angegeben. Es kann kein Eintrag generiert werden!"));
							}
							
							recnew.set_NEW_VALUE_ANGELEGT_AM(bibALL.get_cDateNOW());
							recnew.set_NEW_VALUE_TEXT(sText);
							recnew.set_NEW_VALUE_ID_USER_BESITZER(bibALL.get_ID_USER());
							recnew.set_NEW_VALUE_ID_USER_SUPERVISOR(idSupervisor);
							
							
							
							// RECORD schreiben
							//zuerst die NotNull-felder pruefen (ausser den automatismen)
							Vector<String> vExcludeFields = new Vector<String>();
							vExcludeFields.addAll(DB_STATICS.get_AutomaticWrittenFields_STD());
							vExcludeFields.add("ID_LAUFZETTEL");
							bibMSG.MV().add_MESSAGE(recnew.CheckNotNullables(vExcludeFields));
							
							if (!bibMSG.get_bHasAlarms())
							{
								Vector<String> vSQL = new Vector<String>();
								vSQL.add(recnew.get_InsertSQLStatementWith_Id_Field(false, true));
								
								bibMSG.MV().add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, false));
							}
							
							// merken der neu generierten Laufzettel-ID
							m_ID_Laufzettel_neu = recnew.get_cLastSEQ_NUMBER();
							
							if (bibMSG.get_bIsOK())						
							{
								oMapMask.set_cActualDatabaseValueToMask("ID_LAUFZETTEL", m_ID_Laufzettel_neu);
							}
							
							
						}
						
						//
						// für alle ausgewählten Benutzer die Laufzettel-Einträge generieren
						//
						Vector<String> vSQLStack = new Vector<String>();
					    Vector<String> vIDs = oSelektorUsers.get_DataToView_AUSWAHL().get_vDataValues();
					    
					    if (vIDs.size()==0)
					    {
					    	vIDs.add("");
					    }
					    
					    for (String cID: vIDs)
					    {
					    	if (cID.equals(""))
					    	{
					    		oSelectID_USER.setSelectedIndex(0);
					    	}
					    	else
					    	{
					    		oSelectID_USER.set_ActiveValue(cID);
					    	}
					    	bibMSG.add_MESSAGE(oThis.oMaskSaver.doSaveMask_DUMMY(vSQLStack, false));
					    	
					    	// ***  Verknüpfung Laufzettel-Eintrag mit externem Objekt definieren, falls es definiert wurde
					    	String idLaufzettelEintrag = oThis.oMaskSaver.get_cActualMaskID_Unformated();
					    	vSQLStack.addAll(GenerateModulLinkEntries(m_ID_Laufzettel_neu, idLaufzettelEintrag));
					    	// *** 	
					    	
					    	if (bibMSG.get_bHasAlarms())
					    	{
					    		break;
					    	}
					    }
					    
					    
					    if (bibMSG.get_bIsOK())
						{
					    		// dann die Laufzettel-Einträge anlegen
					    		bibDB.ExecMultiSQLVector(vSQLStack, true);
					    		
					    		if (bibMSG.get_bIsOK())
					    		{
					    			oThis.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					    			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden ",true,""+vIDs.size(),false," Einträge gespeichert.",true)), false);

					    		}
						} else {
							bibDB.Rollback();
							m_ID_Laufzettel_neu = null;
						}

					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
			}
			
		}
		
		
		
		/*
		 * ActionAgent zum internen Speichern-Button einfügen 
		 */
		public void add_XXActionAgentsToButtonSaveMask(XX_ActionAgent oAgent) {
			m_vActionAgentsAfterSave.add(oAgent);
		}
		

		
		/**
		 * Anlegen der Verknüpfung 
		 * @throws myException 
		 */
		private Vector<String> GenerateModulLinkEntries(String idWorkflow, String idWorkflowEintrag) throws myException {
			Vector<String> vSql = new Vector<String>();
			
			// Vernküpfung anlegen, wenn definiert
			if (m_bCreateConnector){
				
				MODUL_LINK_SQL_Factory mlFactory = new MODUL_LINK_SQL_Factory();
				
				// Link to generating Object
				vSql.addAll(mlFactory.get_SQL_Statement_For_Insert(
						 MODUL_LINK_Button_Goto_Modul.class,
						 idWorkflowEintrag,
						 E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE, 
						 m_ID_ConnectorTo ,
						 m_ModulNameWhereToJumpTo,
						 m_ConnectorTextJumpTo,
						 null )
				);
				
				
				// Link To Workflow
				vSql.addAll(mlFactory.get_SQL_Statement_For_Insert(
						 MODUL_LINK_Button_Goto_Modul.class,
						 m_ID_ConnectorFrom,
						 m_ModulNameWhereToJumpFrom,
						 idWorkflow,
						 E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, 
						 m_ConnectorTextJumpFrom,
						 null
						 )
				);				
			}
							
			return vSql;
			
		}
		
		
		/**
		 * 
		 *     ++++++++++++++++                      +++++++++++++++++++++
		 *     +              +                      +                   +
		 *     + Workflow     + <------------------- +  Zielmodul        +
		 *     +              +                      +  Modulname..From  +
		 *     +              +                      +  IDTargetFrom     +
		 *     +              +                      +  Desc.JumpFrom    +
		 *     +              +                      +                   +
		 *     ++++++++++++++++                      +++++++++++++++++++++
		 *     
		 *     ++++++++++++++++                      +++++++++++++++++++++
		 *     +              +                      +                   +
		 *     + WF-Entry     + -------------------->+  Zielmodul        +
		 *     +              +                      +  Modulname..To    +
		 *     +              +                      +  IDTargetTo       +
   		 *     +              +                      +  Desc.JumpTo      +
		 *     +              +                      +                   +
		 *     ++++++++++++++++                      +++++++++++++++++++++
		 * 
		 * Initialisiert die Parameter, falls nach dem erzeugen des Workflows noch ein Connection-Objekt generiert werden soll.
		 * 
		 * @param ModulnameWhereToJumpTo
		 * @param IDToJumpTo
		 * @param DescriptionToJumpTo
		 * @param ModulnameWhereToJumpFrom
		 * @param IDToJumpFrom
		 * @param DescriptionToJumpFrom
		 */
		public void setModulLinkInformations(	String ModulnameWhereToJumpTo, 
												String IDToJumpTo,
												String DescriptionToJumpTo,
												String ModulnameWhereToJumpFrom, 
												String IDToJumpFrom,
												String DescriptionToJumpFrom){
			
			this.m_ModulNameWhereToJumpTo 	= ModulnameWhereToJumpTo;
			this.m_ID_ConnectorTo  			= IDToJumpTo;
			this.m_ConnectorTextJumpTo 		= DescriptionToJumpTo;
			
			this.m_ModulNameWhereToJumpFrom = ModulnameWhereToJumpFrom;
			this.m_ID_ConnectorFrom 		= IDToJumpFrom;
			this.m_ConnectorTextJumpFrom 	= DescriptionToJumpFrom;
			
			m_bCreateConnector = true;
		}
		
		
		
		/**
		 * Für eine initialisierung der Maskenwerte kann man hier die Component-Map-Elemente vorbesetzen 
		 * @param Key
		 * @param Value
		 */
		public void setParameterForInitialisation (String Key, String Value){
			try {
				m_ParameterForInitialisationOfMask.put(Key, Value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Auslesen der gesetzten Parameter-Werte für die vorbesetzung von Componenten der Component-Map des Workflow-Eintrags
		 * @param Key
		 * @return
		 */
		public String getParameterForInitialisation (String Key){
			String sRet = null;
			if (m_ParameterForInitialisationOfMask.containsKey(Key)){
				sRet = m_ParameterForInitialisationOfMask.get(Key);
			} 
			return sRet;
		}

		
		
		
		
		
		
		/**
		 * Validierer der prüft ob der Neueintrag eines Laufzettel-Eintrags erlaubt ist
		 * Speziell für den Button WF_LIST_BT_NEW_EXTERNAL
		 * @author manfred
		 *
		 */
		private class ValidateLaufzettelEintrag extends XX_ActionValidator{

			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException {

				return null;
			}

			
			
			@Override
			protected MyE2_MessageVector isValid(String cID_LaufzettelEintrag)
					throws myException {
				
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				
				if (WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Eintrag_Parent != null)
				{	
					// prüfen, ob der bestehende Laufzetteleintrag gelöscht ist:
					RECORD_LAUFZETTEL_EINTRAG oEintrag = new RECORD_LAUFZETTEL_EINTRAG(WF_LIST_BT_NEW_EXTERNAL.this.m_ID_Eintrag_Parent);
					boolean bIsDeleted = oEintrag.get_GELOESCHT_cUF_NN("").equalsIgnoreCase("Y");
					if (bIsDeleted)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(	new MyE2_String( "Gelöschte Laufzetteleinträge dürfen nicht mehr bearbeitet werden.")));
					}
					
					boolean bIstAbgeschlossen = !oEintrag.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN("").equals("");
					if (bIstAbgeschlossen)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Abgeschlossene Einträge dürfen nicht mehr erweitert werden.")));
					}
				}
				
				return oMV;

			}
			
		}

	
		
		/**
		 * Overridden
		 * 
		 *  enabled wird nur nach der vailiderung entschieden, normalerweise ist der button immer enabled
		 *  
		 *  TODO: Prüfen, ob das auch stimmt. Einfach übernommen aus ..Button_INROW
		 */
		public void set_bEnabled_For_Edit(boolean _enabled) throws myException
		{
			boolean enabled = this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
			
			if (this.EXT().get_oComponentMAP() != null && this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null)
			{
				String cROWID = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				enabled = enabled && (this.valid_IDValidation(bibALL.get_Vector(cROWID)).size()==0);
			}
			
			this.setEnabled(enabled);
			if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
			{
				if (enabled)
					this.setIcon(this.get_oImgEnabled());
				else
					this.setIcon(this.get_oImgDisabled());
			}
				
		}


}
