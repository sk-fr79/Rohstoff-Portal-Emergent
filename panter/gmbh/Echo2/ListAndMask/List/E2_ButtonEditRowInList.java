package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_ButtonEditRowInList extends MyE2_Row   implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2_Button							oButtonEdit = new MyE2_Button(E2_ResourceIcon.get_RI("edit.png"),true);
	private MyE2_Button							oButtonSave = new MyE2_Button(E2_ResourceIcon.get_RI("save.png"),true);
	private MyE2_Button							oButtonCancel = new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"),true);
	private MyE2_Label							oLabelSpace	= new MyE2_Label(E2_ResourceIcon.get_RI("empty.png"));
	
	private boolean								bStatusEdit = false;
	
	/*
	 * ein schalter, der verhindern kann, dass bei gesteuerter edit-funktion
	 * von aussen mehrfach die gleiche meldung kommt
	 */
	private boolean 							bShowOwnMessages = true;
	
	/*
	 * fuer jeden der 3 buttons werden je ein actionagent definiert
	 */
	private ActionAgent_Edit_InListButton  		oAgentEdit = null;
	private ActionAgent_Save_InListButton  		oAgentSave = null;
	private ActionAgent_Cancel_InListButton  	oAgentCancel = null;

	
	private XX_ActionAgent    					oAgentSwitchToEditmode = null;
	private XX_ActionAgent    					oAgentEndEditmode = null;
	
	
	
	
	public E2_ButtonEditRowInList(boolean bshowOwnMessages)
	{
		super();
		
		this.bShowOwnMessages = bshowOwnMessages;

		this.setStyle(new Style_Row_Normal(0,new Insets(0)));
		
		this.oAgentEdit = new ActionAgent_Edit_InListButton();
		this.oAgentSave = new ActionAgent_Save_InListButton();
		
		this.oAgentCancel = new ActionAgent_Cancel_InListButton();
		
		this.oButtonEdit.add_oActionAgent(this.oAgentEdit);
		this.oButtonSave.add_oActionAgent(this.oAgentSave);
		this.oButtonCancel.add_oActionAgent(this.oAgentCancel);
		
		this.oButtonSave.set_bMustSet_MILLISECONDSTAMP_TO_Session(false);
		
		this.oButtonEdit.setToolTipText((new MyE2_String("Editieren dieser Zeile ...")).CTrans());
		this.oButtonSave.setToolTipText((new MyE2_String("Speichern der Eingaben dieser Zeile ...")).CTrans());
		this.oButtonCancel.setToolTipText((new MyE2_String("Abbrechen des Editiervorgangs ...")).CTrans());
		
		// referenz in den buttons auf das enhaltende objekt
		this.oButtonEdit.EXT().set_O_PLACE_FOR_EVERYTHING(this);
		this.oButtonSave.EXT().set_O_PLACE_FOR_EVERYTHING(this);
		this.oButtonCancel.EXT().set_O_PLACE_FOR_EVERYTHING(this);
		
		this.oButtonSave.set_bMustSet_MILLISECONDSTAMP_TO_Session(false);
		
		this._actualise();
	}

	
	public E2_ButtonEditRowInList(	boolean bshowOwnMessages,
									XX_ActionAgent AgentSwitchToEditmode,
									XX_ActionAgent AgentEndEditmode)
	{
		super();
		
		this.bShowOwnMessages = bshowOwnMessages;
		this.setStyle(new Style_Row_Normal(0,new Insets(0)));
		
		this.oAgentEdit = new ActionAgent_Edit_InListButton();
		this.oAgentSave = new ActionAgent_Save_InListButton();
		this.oAgentCancel = new ActionAgent_Cancel_InListButton();
		
		this.oButtonEdit.add_oActionAgent(this.oAgentEdit);
		this.oButtonSave.add_oActionAgent(this.oAgentSave);
		this.oButtonCancel.add_oActionAgent(this.oAgentCancel);
		
		this.oButtonEdit.setToolTipText((new MyE2_String("Editieren dieser Zeile ...")).CTrans());
		this.oButtonSave.setToolTipText((new MyE2_String("Speichern der Eingaben dieser Zeile ...")).CTrans());
		this.oButtonCancel.setToolTipText((new MyE2_String("Abbrechen des Editiervorgangs ...")).CTrans());
		
		this.oAgentSwitchToEditmode = 	AgentSwitchToEditmode;
		this.oAgentEndEditmode = 		AgentEndEditmode;

		// referenz in den buttons auf das enhaltende objekt
		this.oButtonEdit.EXT().set_O_PLACE_FOR_EVERYTHING(this);
		this.oButtonSave.EXT().set_O_PLACE_FOR_EVERYTHING(this);
		this.oButtonCancel.EXT().set_O_PLACE_FOR_EVERYTHING(this);

		this.oButtonSave.set_bMustSet_MILLISECONDSTAMP_TO_Session(false);

		this._actualise();
	}

	
	
	public void _actualise()
	{
		this.removeAll();
		if (this.bStatusEdit)
		{
			this.add(this.oButtonSave);
			this.add(this.oButtonCancel);
		}
		else
		{
			this.add(this.oButtonEdit);
			this.add(this.oLabelSpace);
		}
	}
	
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		/*
		 * bei neueingabe deaktiviert
		 */
		boolean bInnerEnabled = enabled;

		/*
		 * standard
		 */
		this.oButtonEdit.set_bEnabled_For_Edit(true);
		
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
			{
				bInnerEnabled=false;
				this.oButtonEdit.set_bEnabled_For_Edit(false);
			}
		
		this.bStatusEdit = bInnerEnabled;
		this._actualise();
	}

	public boolean get_bShowOwnMessages()						{		return bShowOwnMessages;	}
	
	
	
	
	public boolean get_bStatusEdit()						{		return bStatusEdit;	}
	public void set_bStatusEdit(boolean statusEdit)			{		bStatusEdit = statusEdit;	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		E2_ButtonEditRowInList oRueck = new E2_ButtonEditRowInList(this.bShowOwnMessages,this.oAgentSwitchToEditmode,this.oAgentEndEditmode);
		
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		
		/*
		 * jetzt evtl. vorhandene validatoren des edit-buttons mitziehen
		 */
		for (int i=0;i<this.oButtonEdit.get_vGlobalValidators().size();i++)
			oRueck.get_oButtonEdit().add_GlobalValidator((XX_ActionValidator)this.oButtonEdit.get_vGlobalValidators().get(i));
				
		for (int i=0;i<this.oButtonEdit.get_vIDValidators().size();i++)
			oRueck.get_oButtonEdit().add_IDValidator((XX_ActionValidator)this.oButtonEdit.get_vIDValidators().get(i));
		
		
		return oRueck;
	}

	
	public MyE2_Button get_oButtonCancel()				{		return oButtonCancel;	}
	public MyE2_Button get_oButtonEdit()				{		return oButtonEdit;	}
	public MyE2_Button get_oButtonSave()				{		return oButtonSave;	}

	
	
	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Edit_InListButton extends XX_ActionAgent
	{
		public ActionAgent_Edit_InListButton()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * die komponenten-map der row
			 */
			try
			{
				
				E2_ComponentMAP oMap = E2_ButtonEditRowInList.this.EXT().get_oComponentMAP();
				
				// rowid nachschauen
				String cID_Unformated = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

				bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(cID_Unformated));
				
				if (bibMSG.get_bIsOK())
				{
				
					/*
					 * zuerst alle anderen, evtl. offenen komponenten schliessen und neu einlesen
					 */
					oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_EDIT,true,new Boolean(true));
//					oMap.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_EDIT);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Bearbeiten Listen-ID: "+oMap.get_oInternalSQLResultMAP().get_cFormatedROW_ID()),true);
					
					// jetzt feststellen, ob es die erste editierzeile war
					Vector<E2_ComponentMAP> vMAP = oMap.get_VectorComponentMAP_thisBelongsTo();
					if (vMAP != null && vMAP.size()>0)
					{
						int iCountEditMode = 0;
						for (int i=0;i<vMAP.size();i++)
						{
							Vector<MyE2IF__Component> vRealComponents = ((E2_ComponentMAP)vMAP.get(i)).get_REAL_ComponentVector();
							for (int k=0;k<vRealComponents.size();k++)
							{
								if (vRealComponents.get(k) instanceof E2_ButtonEditRowInList)
								{
									if (((E2_ButtonEditRowInList)vRealComponents.get(k)).get_bStatusEdit())
									{
										iCountEditMode++;
										break;                   // naechste MAP
									}
								}
							}
						}
						
						if (iCountEditMode == 1)
						{
							if (E2_ButtonEditRowInList.this.oAgentSwitchToEditmode != null)
								E2_ButtonEditRowInList.this.oAgentSwitchToEditmode.ExecuteAgentCode(new ExecINFO_OnlyCode());
						}
						
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(),false);
			}
		}
	}
	
	

	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Save_InListButton extends XX_ActionAgent
	{
		private MyDBToolBox 				oDB = bibALL.get_myDBToolBox();

		protected void finalize()
		{
			bibALL.destroy_myDBToolBox(this.oDB);
		}
		
		public ActionAgent_Save_InListButton()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ComponentMAP oMap = E2_ButtonEditRowInList.this.EXT().get_oComponentMAP();

			
			try
			{
				
				SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();			// vorige resultmap
				
				Vector<String> vUpdates = null;

				/*
				 * zuerst pruefen, ob der satz im netz geaendert wurde,
				 * wenn der rueckgabevector leer ist, dann ist alles gut 
				 */
				Vector<String> vChanges = oMap.get_ChangedFieldResults();
				if (vChanges.size()==0)
				{
					/*
					 * zuerst die eingabewerte pruefen, alles gut heist: vector ist leer
					 */
					SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(oResult,bibMSG.MV(),E2_ComponentMAP.STATUS_EDIT);
					
					if (bibMSG.get_bIsOK())
					{
						vUpdates = oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResult,oInputMap);
						
						bibMSG.add_MESSAGE(this.oDB.ExecMultiSQLVector(vUpdates,true));
						
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Listeneintrag gespeichert: "+oMap.get_oInternalSQLResultMAP().get_cFormatedROW_ID()),true);
						}
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Datensatz wurde von einem anderen Teilnehmer veraendert (2)!"));
					bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vChanges);
				}
				
				if (bibMSG.get_bIsOK())
				{
					oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,new Boolean(false));
//					oMap.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
					
					
					// jetzt feststellen, ob es die letzte editierzeile war
					Vector<E2_ComponentMAP> vMAP = oMap.get_VectorComponentMAP_thisBelongsTo();
					if (vMAP != null && vMAP.size()>0)
					{
						int iCountEditMode = 0;
						for (int i=0;i<vMAP.size();i++)
						{
							Vector<MyE2IF__Component> vRealComponents = ((E2_ComponentMAP)vMAP.get(i)).get_REAL_ComponentVector();
							for (int k=0;k<vRealComponents.size();k++)
							{
								if (vRealComponents.get(k) instanceof E2_ButtonEditRowInList)
								{
									if (((E2_ButtonEditRowInList)vRealComponents.get(k)).get_bStatusEdit())
									{
										iCountEditMode++;
										break;                   // naechste MAP
									}
								}
							}
							
						}
						
						if (iCountEditMode == 0)
						{
							if (E2_ButtonEditRowInList.this.oAgentEndEditmode != null)
								E2_ButtonEditRowInList.this.oAgentEndEditmode.ExecuteAgentCode(new ExecINFO_OnlyCode());
						}
					}
				}
				//////////////
				
				
				

			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error building update-stack "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
	
	}
	

	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Cancel_InListButton extends XX_ActionAgent
	{
		public ActionAgent_Cancel_InListButton()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ComponentMAP oMap = E2_ButtonEditRowInList.this.EXT().get_oComponentMAP();
			try
			{
				oMap.fill_ComponentMapFromDB(null,oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),E2_ComponentMAP.STATUS_VIEW,false,new Boolean(false));
//				oMap.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Listenbearbeitung abgebrochen: "+oMap.get_oInternalSQLResultMAP().get_cFormatedROW_ID()));
				
				
				
				// jetzt feststellen, ob es die letzte editierzeile war
				Vector<E2_ComponentMAP> vMAP = oMap.get_VectorComponentMAP_thisBelongsTo();
				if (vMAP != null && vMAP.size()>0)
				{
					int iCountEditMode = 0;
					for (int i=0;i<vMAP.size();i++)
					{
						Vector<MyE2IF__Component> vRealComponents = ((E2_ComponentMAP)vMAP.get(i)).get_REAL_ComponentVector();
						for (int k=0;k<vRealComponents.size();k++)
						{
							if (vRealComponents.get(k) instanceof E2_ButtonEditRowInList)
							{
								if (((E2_ButtonEditRowInList)vRealComponents.get(k)).get_bStatusEdit())
								{
									iCountEditMode++;
									break;                   // naechste MAP
								}
							}
						}
						
					}
					
					if (iCountEditMode == 0)
					{
						if (E2_ButtonEditRowInList.this.oAgentEndEditmode != null)
							E2_ButtonEditRowInList.this.oAgentEndEditmode.ExecuteAgentCode(new ExecINFO_OnlyCode());
					}
				}
				//////////////

				
				//2012-12-13: settings-collection anwenden
				oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMap);
				
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error restoring Row: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
		
	}






	
}
