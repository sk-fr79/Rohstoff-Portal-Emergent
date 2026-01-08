package panter.gmbh.Echo2.ServerPush;




import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;




/**
 * 
 * @author martin
 *
 */
public abstract class E2_ServerPushMessageContainer extends E2_BasicModuleContainer 
{

	private ownThread  										oThreat = null;
	private boolean    										bIsInterupted = false;
	private E2_ApplicationInstanceWithServerPushUpdateTask 	oPushUpdateTask = null;

	private MyE2_Grid   									oGridBaseForMessages = new MyE2_Grid(1,0);
	
	private boolean   										bAutoCloseAfterLoop = true;
	
	private E2_ComponentGroupHorizontal   					oComponentGroupHorizontal = new E2_ComponentGroupHorizontal(0,E2_INSETS.I_0_0_10_0);
	private MyE2_Button                                     oButtonCloseWindow = new MyE2_Button(new MyE2_String("Schliessen"));
	private MyE2_Button                                     oButtonCancelLoop = new MyE2_Button(new MyE2_String("Abbruch"));
	
	
	


	/**
	 * 
	 * @param oWidth
	 * @param oHeight
	 * @param oTitle
	 * @param bShowInternalGrid
	 * @param AutoCloseAfterLoop
	 * @param ShowCancelButton
	 * @param iMilliSecondsIntervall
	 */
	public E2_ServerPushMessageContainer(	Extent 			oWidth, 
											Extent 			oHeight, 
											MyE2_String 	oTitle, 
											boolean 		bShowInternalGrid,
											boolean 		AutoCloseAfterLoop,
											boolean 		ShowCancelButton,
											int 			iMilliSecondsIntervall) 
	{
		super(); 
		
		this.bAutoCloseAfterLoop = AutoCloseAfterLoop;
		
		
		int iiMili = iMilliSecondsIntervall;
		if (iiMili<=1000)
			iiMili=1000;

		//trocken-schleife, sorgt fuer die screen-updates 
		this.oPushUpdateTask = new E2_ApplicationInstanceWithServerPushUpdateTask(iMilliSecondsIntervall);
		this.oPushUpdateTask.addListener(new ActionListener()
	    {
	       private static final long serialVersionUID = 1L;

	       public void actionPerformed(ActionEvent e)
	       {
	    	   try
	    	   {
	    		   E2_ServerPushMessageContainer.this.oButtonCloseWindow.set_bEnabled_For_Edit(true);    //schaltet den schliess-knopf auf enabled (meist sinnlos, ausser es wurde vorher der unterbrechen - button gedrueckt)
	    	   }
	    	   catch (myException ex){}
	       }
	    });

		
		this.oButtonCloseWindow.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				E2_ServerPushMessageContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}

		});
		
		// der cancel-knopf setzt nur den interrupted-schalter --> wichtig: muss in der schleife selbst ausgewertet werden !!!, hat keine auswirkungen auf die serverpush-komponenten
		this.oButtonCancelLoop.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO execInfo)
			{
				E2_ServerPushMessageContainer.this.bIsInterupted=true;
				try
				{
					E2_ServerPushMessageContainer.this.oButtonCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer.this.oButtonCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
					E2_ServerPushMessageContainer.set_LoopStopped();
				}
				catch (myException ex){}
				
				E2_ServerPushMessageContainer.this.oComponentGroupHorizontal.add(E2_ServerPushMessageContainer.this.oButtonCloseWindow);
			}
		});

		
		if (bShowInternalGrid)
		{
			this.add(this.oGridBaseForMessages, E2_INSETS.I_10_10_10_10);
			this.add(this.oComponentGroupHorizontal, E2_INSETS.I_10_10_10_10);
		}
		
		if (ShowCancelButton)
			this.oComponentGroupHorizontal.add(this.oButtonCancelLoop);
		
			
		try
		{
			
			this.CREATE_AND_SHOW_POPUPWINDOW(oWidth, oHeight,oTitle);
			this.get_oWindowPane().setClosable(false);
			this.get_oWindowPane().setTitleHeight(new Extent(20));
			this.get_oWindowPane().setTitleFont(new E2_FontItalic(-2));
			
			//2012-05-24: Fortschritts-Fenster veraenderbar
			this.setWindowPaneLookAndFeel(this.get_oWindowPane());
			
			this.oPushUpdateTask.start();
			oThreat = new ownThread(bibE2.get_ActiveInstance());
			oThreat.start();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			this.oPushUpdateTask.stop();
		}
		
	}

	/*
	 * kann  ueberschrieben werden, wenn das fenster geschlossen wird
	 */
	public void OVERRIDE_ACTION_WHENCLOSING_WINDOW()
	{
		if (this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL))
			this.oPushUpdateTask.stop();
	}
	


	public abstract void Run_Loop() throws myException;
	
	//2012-05-24: bessere definierbare fortschrittspopup-menues
	public abstract void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException;  

	
	private class ownThread extends Thread 
	{
		ApplicationInstance oUsedAppliInstance = null;
		
		public ownThread(ApplicationInstance activeInstance)
		{
			super();
			ApplicationInstance.setActive(activeInstance);
			this.oUsedAppliInstance = activeInstance;
		}
		
		public ApplicationInstance get_activeInstance()
		{
			return oUsedAppliInstance;
		}
		
		
		public void run()
		{
		  	ApplicationInstance oTest = this.get_activeInstance();
			ApplicationInstance.setActive(oTest);
			
			E2_ServerPushMessageContainer.set_LoopStartet();
			
			try
			{
				
				E2_ServerPushMessageContainer.this.Run_Loop();
				
				E2_ServerPushMessageContainer.set_LoopStopped();
				
				//automatisches schliessen nur moeglich, wenn keine messages aufgelaufen sind
				boolean bHasMessagesToShow = (bibMSG.MV().size()>0);
				
				if (E2_ServerPushMessageContainer.this.bAutoCloseAfterLoop && !bHasMessagesToShow)
				{
					E2_ServerPushMessageContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					E2_ServerPushMessageContainer.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
				}
				else
				{
					// bei close-button werden vorher noch die messages angezeigt, da diese sonst untergehen
					E2_ServerPushMessageContainer.this.showActualMessages(); 
					E2_ServerPushMessageContainer.this.oComponentGroupHorizontal.add(E2_ServerPushMessageContainer.this.oButtonCloseWindow);
					E2_ServerPushMessageContainer.this.oButtonCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer.this.oButtonCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
					E2_ServerPushMessageContainer.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error: "+ex.getLocalizedMessage()));
				// falls irgendwelche messages aufgelaufen sind, dann den messages anzeigen update-prozess beenden und immer den close-button einblenden
				E2_ServerPushMessageContainer.this.showActualMessages(); 
				E2_ServerPushMessageContainer.this.oComponentGroupHorizontal.add(E2_ServerPushMessageContainer.this.oButtonCloseWindow);
				try
				{
					E2_ServerPushMessageContainer.this.oButtonCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer.this.oButtonCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
				} catch (myException exx){}
				E2_ServerPushMessageContainer.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
			}
		}

	}



	public E2_ComponentGroupHorizontal get_oComponentGroupHorizontal() {
		return oComponentGroupHorizontal;
	}

	public boolean get_bIsInterupted() 
	{
		return bIsInterupted;
	}

	public void set_bIsInterupted(boolean isInterupted) 
	{
		bIsInterupted = isInterupted;
	}

	public E2_ApplicationInstanceWithServerPushUpdateTask get_oPushUpdateTask() 
	{
		return oPushUpdateTask;
	}

	public MyE2_Grid get_oGridBaseForMessages() 
	{
		return oGridBaseForMessages;
	}

	
	//die abbruchinfos werden in der SESSION gehalten
	public static void set_LoopStartet()
	{
		bibALL.setSessionValue("__@@@LOOP_IS_ACTIVE", "Y");
	}
	public static void set_LoopStopped()
	{
		bibALL.setSessionValue("__@@@LOOP_IS_ACTIVE", "N");
	}
	public static boolean get_LoopStopped()
	{
		return ((String)bibALL.getSessionValue("__@@@LOOP_IS_ACTIVE")).equals("N");
	}
	
	

	//2011-01-09: buttons veroeffentlichen
	public MyE2_Button get_oButtonCloseWindow()
	{
		return oButtonCloseWindow;
	}
	
	public MyE2_Button get_oButtonCancelLoop()
	{
		return oButtonCancelLoop;
	}

	
	public void showSimpleInfo(MyE2_String info) {
		this.get_oGridBaseForMessages().removeAll();
		this.get_oGridBaseForMessages().add(new RB_lab()._t(info));
	}

	
}
