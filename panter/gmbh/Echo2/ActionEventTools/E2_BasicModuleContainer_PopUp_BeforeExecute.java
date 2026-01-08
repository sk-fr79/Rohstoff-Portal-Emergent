package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


/*
 * basic-moduleContainer zum einblenden eines (nicht immer erscheinden) popup-fensters
 * fuer weitere abfragen vor einem speichervorgang aus einer Maske
 */
public abstract class E2_BasicModuleContainer_PopUp_BeforeExecute extends   E2_BasicModuleContainer 
{
	/*
	 * der die komponente, die dieses popup aufgerufen hat
	 */
	private XX_ActionAgent  				oActionAgent_Calling = null;
	private ExecINFO  						oExecInfoCalling = null;

	private MyE2_Button 					oButtonForOK = 			new MyE2_Button("OK");
	private MyE2_Button 					oButtonForAbbruch = 	new MyE2_Button("Abbruch");
	
	private boolean 					   	bShowWindowAsSplitpane = false;
	
	/*
	 * soll die urspruengliche Action mit dem OK-Button automatisch ausgefuehrt werden ?
	 */
	private boolean 						bExecuteAgent_Calling_after_OK = true;
	
	/*
	 * soll das Fenster auch geschlossen werden nach OK, in dem Fehler auftauchen ?
	 */
	private boolean  						bCloseAfterOK_With_Errors = false;

	public E2_BasicModuleContainer_PopUp_BeforeExecute()
	{
		super();
		this.oButtonForOK.add_oActionAgent(new actionOK());
		this.oButtonForAbbruch.add_oActionAgent(new actionCancel());
		
	
		/*
		 * window-close-action, damit der schliessknopf wirkt wie DER ABBRUCH-Button 
		 */
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(E2_BasicModuleContainer_PopUp_BeforeExecute.this)
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				if (E2_BasicModuleContainer_PopUp_BeforeExecute.this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL))
				{
					E2_BasicModuleContainer_PopUp_BeforeExecute.this.oActionAgent_Calling.ResetLoop();
				}
			}
		});
		
	}
		
	
	private class actionOK extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainer_PopUp_BeforeExecute oThis = E2_BasicModuleContainer_PopUp_BeforeExecute.this;
			
			try
			{
				oThis.doOwnCode_in_ok_button();
				if (bibMSG.get_bIsOK() && oThis.bExecuteAgent_Calling_after_OK)
				{
					oThis.oActionAgent_Calling.ExecuteAgentCode(oThis.oExecInfoCalling);
					
					/* 
					 * jetzt evtl. folgende actionagenten nochmal laufen lassen (aber nur der innere code),
					 * hier werden dann keine popups mehr ausgefuehrt
					 */
					for (int i=0;i<oThis.oExecInfoCalling.get_vActionAgentsREST().size();i++)
					{
						oThis.oExecInfoCalling.get_vActionAgentsREST().get(i).executeAgentCode(oThis.oExecInfoCalling);
						//falls ein fehler aufgetreten ist, dann bei diesem actionAgent raus und ende
						if (bibMSG.get_bHasAlarms())
						{
							break;
						}
					}
					
				}
				if (bibMSG.get_bIsOK() || oThis.bCloseAfterOK_With_Errors)
				{
					oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
				
			}
			catch (myException ex)
			{
				throw ex;
			}
		}
	}
	

	private class actionCancel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainer_PopUp_BeforeExecute oThis = E2_BasicModuleContainer_PopUp_BeforeExecute.this;
			try
			{
				oThis.doOwnCode_in_cancel_button();
				E2_BasicModuleContainer_PopUp_BeforeExecute.this.oActionAgent_Calling.ResetLoop();
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
			catch (myException ex)
			{
				throw ex;
			}
		}
	}
	
	
	
	// hier landet der code, der nach dem klick auf das ok und vor dem ausfuehren des rufenden actionAgents ausgefuehrt wird
	protected abstract void doOwnCode_in_ok_button() throws myException;

	protected abstract void doOwnCode_in_cancel_button() throws myException;

	
	// wird bei jedem durchlauf durch den XX_ActionAgent aufgerufen
	public abstract void doBuildContent(ExecINFO oExecInfo) throws myException;
	
	// wird aufgerufen, ob es angezeigt werden muss oder nicht
	public abstract boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException;
	
	


	public MyE2_Button get_oButtonForAbbruch() 
	{
		return oButtonForAbbruch;
	}



	public MyE2_Button get_oButtonForOK() 
	{
		return oButtonForOK;
	}


	public void set_oActionAgent_Calling(XX_ActionAgent actionAgent_Calling) 
	{
		oActionAgent_Calling = actionAgent_Calling;
	}



	public void set_oExecInfoCalling(ExecINFO execInfoCalling) 
	{
		oExecInfoCalling = execInfoCalling;
	}

	public boolean is_bShowWindowAsSplitpane()
	{
		return bShowWindowAsSplitpane;
	}

	public void set_bShowWindowAsSplitpane(boolean showWindowAsSplitpane)
	{
		bShowWindowAsSplitpane = showWindowAsSplitpane;
	}

	
	public void POPUP(XX_ActionAgent oAgentCalling, ExecINFO oExecInfo) throws myException
	{
		this.set_oActionAgent_Calling(oAgentCalling);
		this.set_oExecInfoCalling(oExecInfo);
		this.doBuildContent(oExecInfo);
		if (this.is_bShowWindowAsSplitpane())
		{
			this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(null, null, null);
		}
		else
		{
			this.CREATE_AND_SHOW_POPUPWINDOW(null, null, null);
		}

	}

	public boolean get_bExecuteAgent_Calling_after_OK()
	{
		return bExecuteAgent_Calling_after_OK;
	}

	public void setBExecuteAgent_Calling_after_OK(boolean executeAgent_Calling_after_OK)
	{
		bExecuteAgent_Calling_after_OK = executeAgent_Calling_after_OK;
	}

	public boolean get_bCloseAfterOK_With_Errors()
	{
		return bCloseAfterOK_With_Errors;
	}

	public void set_bCloseAfterOK_With_Errors(boolean closeAfterOK_With_Errors)
	{
		bCloseAfterOK_With_Errors = closeAfterOK_With_Errors;
	}
	
}
