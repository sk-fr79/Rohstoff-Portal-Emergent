package panter.gmbh.Echo2.ServerPush;


import java.util.Date;
import java.util.Iterator;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
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
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;




/**
 * 
 * @author martin
 *
 */
public abstract class E2_ServerPushMessageContainer_V2 extends E2_BasicModuleContainer {

	private ownThread  										oThreat = null;
	private boolean    										bIsInterupted = false;
	private OwnApplicationInstanceWithServerPushUpdateTask 	oPushUpdateTask = null;

	private E2_Grid	   										gridForMessagesComponent = new E2_Grid()._s(1)._w100();
	private Component  										componentToShowUpdates = null;
	
	private boolean   										autoCloseAfterLoop = true;
	
	private E2_Button                                     	btCloseWindow = new E2_Button()._t(S.ms("Schliessen"))._setShapeStandardTextButton();
	private E2_Button                                     	btCancelLoop =  new E2_Button()._t(S.ms("Abbruch"))._setShapeStandardTextButton();
	private E2_Grid 			   							grid4components =    new E2_Grid()._setSize(80,80);
	
	private boolean 										enableCancelButton ;

	public abstract void Run_Loop() throws myException;
	
	//2012-05-24: bessere definierbare fortschrittspopup-menues
	public abstract void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException;  

	
	//kontrollvariable, die dafuer sorgt, dass nicht innerhalb eines refresh-events zweimal der popup-inhalt veraendert wird
	private boolean 										allowedToUpdatePopupInfos = false; 
	

	/**
	 * 
	 * @param oWidth
	 * @param oHeight
	 * @param p_titel
	 * @param bShowInternalGrid
	 * @param p_autoCloseAfterLoop
	 * @param p_enableCancelButton
	 * @param p_milliSecondsIntervall
	 */
	public E2_ServerPushMessageContainer_V2(int 			width, 
											int 			height, 
											MyE2_String 	p_titel, 
											boolean 		p_autoCloseAfterLoop,
											boolean 		p_enableCancelButton,
											int 			p_milliSecondsIntervall,
											Component       p_componentToShowUpdates 
											) {
		super(); 
		
		this.autoCloseAfterLoop = p_autoCloseAfterLoop;
		this.enableCancelButton = p_enableCancelButton;
		
		//trocken-schleife, sorgt fuer die screen-updates 
		this.oPushUpdateTask = new OwnApplicationInstanceWithServerPushUpdateTask(p_milliSecondsIntervall);
		this.oPushUpdateTask.addListener(new ActionListener() {
	       private static final long serialVersionUID = 1L;
	       public void actionPerformed(ActionEvent e)    {
	    	   try  {
	    		   E2_ServerPushMessageContainer_V2.this.btCloseWindow.set_bEnabled_For_Edit(true);    //schaltet den schliess-knopf auf enabled (meist sinnlos, ausser es wurde vorher der unterbrechen - button gedrueckt)
	    	   }
	    	   catch (myException ex){}
	       }
	    });

		
		this.btCloseWindow.setVisible(false);          //am anfang immer verdeckt
		this.btCloseWindow._aaa(()->{
			CLOSE_AND_DESTROY_POPUPWINDOW(false);
		});
		
		
		// der cancel-knopf setzt nur den interrupted-schalter --> wichtig: muss in der schleife selbst ausgewertet werden !!!, hat keine auswirkungen auf die serverpush-komponenten
		this.btCancelLoop.add_oActionAgent(new XX_ActionAgent() {
			public void executeAgentCode(ExecINFO execInfo) {
				E2_ServerPushMessageContainer_V2.this.bIsInterupted=true;
				try	{
					E2_ServerPushMessageContainer_V2.this.btCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer_V2.this.btCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
					E2_ServerPushMessageContainer_V2.set_LoopStopped();
				} catch (myException ex){
					ex.printStackTrace();
				}
				btCloseWindow.setVisible(true);;
			}
		});

		
		if (p_componentToShowUpdates!=null) {
			this.componentToShowUpdates = p_componentToShowUpdates;
		} else {
			this.componentToShowUpdates = new RB_lab(".. ich arbeite ..");
		}
		
		this.fillPopupContainer();
		
		try	{
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(width), new Extent(height),p_titel);
			this.get_oWindowPane().setClosable(false);
			this.get_oWindowPane().setTitleHeight(new Extent(20));
			this.get_oWindowPane().setTitleFont(new E2_FontItalic(-2));
			
			//2012-05-24: Fortschritts-Fenster veraenderbar
			this.setWindowPaneLookAndFeel(this.get_oWindowPane());
			
			this.oPushUpdateTask.start();
			oThreat = new ownThread(bibE2.get_ActiveInstance());
			oThreat.start();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			this.oPushUpdateTask.stop();
		}
		
	}

	/**
	 * definiert den aufbau des popup-containers
	 * @author martin
	 * @date 09.07.2019
	 * can be overwritten
	 *
	 */
	public void fillPopupContainer() {
		this.add(this.gridForMessagesComponent, E2_INSETS.I(2));
		this.gridForMessagesComponent._a(componentToShowUpdates, new RB_gld()._ins(5, 5, 5, 5));
		this.grid4components._setSize(90,90)._a(btCancelLoop, new RB_gld()._ins(2, 2, 20, 2))._a(btCloseWindow, new RB_gld()._ins(2, 2, 2, 2));
		this.gridForMessagesComponent._a(grid4components, new RB_gld()._ins(5, 10, 5, 5));

		if (!enableCancelButton) {
			try {
				this.btCancelLoop.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				this.btCancelLoop.setEnabled(false);
				e.printStackTrace();
			}
		}

	}
	
	
	/*
	 * kann  ueberschrieben werden, wenn das fenster geschlossen wird
	 */
	public void OVERRIDE_ACTION_WHENCLOSING_WINDOW() {
		if (this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL)) {
			this.oPushUpdateTask.stop();
		}
	}
	



	private class ownThread extends Thread 	{
		ApplicationInstance oUsedAppliInstance = null;
		
		public ownThread(ApplicationInstance activeInstance) {
			super();
			ApplicationInstance.setActive(activeInstance);
			this.oUsedAppliInstance = activeInstance;
		}
		
		public ApplicationInstance get_activeInstance() {
			return oUsedAppliInstance;
		}
		
		
		public void run() {
		  	ApplicationInstance oTest = this.get_activeInstance();
			ApplicationInstance.setActive(oTest);
			
			E2_ServerPushMessageContainer_V2.set_LoopStartet();
			
			try	{
				
				E2_ServerPushMessageContainer_V2.this.Run_Loop();
				
				E2_ServerPushMessageContainer_V2.set_LoopStopped();
				
				//automatisches schliessen nur moeglich, wenn keine messages aufgelaufen sind
				boolean bHasMessagesToShow = (bibMSG.MV().size()>0);
				
				if (E2_ServerPushMessageContainer_V2.this.autoCloseAfterLoop && !bHasMessagesToShow) {
					E2_ServerPushMessageContainer_V2.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					E2_ServerPushMessageContainer_V2.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
				} else {
					// bei close-button werden vorher noch die messages angezeigt, da diese sonst untergehen
					E2_ServerPushMessageContainer_V2.this.showActualMessages(); 
					E2_ServerPushMessageContainer_V2.this.btCloseWindow.setVisible(true);
					E2_ServerPushMessageContainer_V2.this.btCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer_V2.this.btCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
					E2_ServerPushMessageContainer_V2.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error: "+ex.getLocalizedMessage()));
				// falls irgendwelche messages aufgelaufen sind, dann den messages anzeigen update-prozess beenden und immer den close-button einblenden
				E2_ServerPushMessageContainer_V2.this.showActualMessages(); 
				E2_ServerPushMessageContainer_V2.this.btCloseWindow.setVisible(true);
				try
				{
					E2_ServerPushMessageContainer_V2.this.btCancelLoop.set_bEnabled_For_Edit(false);
					E2_ServerPushMessageContainer_V2.this.btCloseWindow.set_bEnabled_For_Edit(false);   // dann muss das system auf den naechsten zyklus warten (falls noch eine message angezeigt wird ...
				} catch (myException exx){}
				E2_ServerPushMessageContainer_V2.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
			} finally {
				E2_ServerPushMessageContainer_V2.this.oPushUpdateTask.set_bTaskIsDead(true);          // ein letztes update laeuft noch und dann schluss
			}
		}

	}



	public boolean isInterupted() {
		return bIsInterupted;
	}

	public void setInterupted(boolean isInterupted) {
		bIsInterupted = isInterupted;
	}

	public OwnApplicationInstanceWithServerPushUpdateTask getPushUpdateTask() {
		return oPushUpdateTask;
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
	public E2_Button getButtonCloseWindow() {
		return btCloseWindow;
	}
	
	public E2_Button getButtonCancelLoop() {
		return btCancelLoop;
	}

	
	
	/**
	 * implizite implementierung, um den alten code nicht zu stoeren
	 * @author martin
	 * @date 09.07.2019
	 *
	 */
	public class OwnApplicationInstanceWithServerPushUpdateTask extends E2_ApplicationInstanceWithServerPushUpdateTask	{

	    private long nextUpdate;
	    private long interval;
		
		public OwnApplicationInstanceWithServerPushUpdateTask(long p_interval) {
			super(p_interval);
			this.interval = p_interval;
		}
		
	    public void update()   {
	        long currentTime = new Date().getTime();
	        for ( Iterator<ActionListener> iter = getEventListeners().iterator(); iter.hasNext(); ){
	            ((ActionListener)iter.next()).actionPerformed(new ActionEvent(this, ""));
	        }
	        
	        nextUpdate = currentTime + interval;
	        
	        allowedToUpdatePopupInfos=true;
	        
	        if (this.isTaskIsDead()) {
	        	this.stop();
	        }
	    }
	    
	    public boolean isReady() {
	        return new Date().getTime() >= nextUpdate;
	    }

	}




	public OwnApplicationInstanceWithServerPushUpdateTask getoPushUpdateTask() {
		return oPushUpdateTask;
	}


	public E2_Grid getGridForMessagesComponent() {
		return gridForMessagesComponent;
	}

	public Component getComponentToShowUpdates() {
		return componentToShowUpdates;
	}


	public boolean isbAutoCloseAfterLoop() {
		return autoCloseAfterLoop;
	}


	public E2_Button getBtCloseWindow() {
		return btCloseWindow;
	}


	public E2_Button getBtCancelLoop() {
		return btCancelLoop;
	}


	public E2_Grid getGrid4components() {
		return grid4components;
	}


	public boolean isEnableCancelButton() {
		return enableCancelButton;
	}

	public boolean isAllowedToUpdatePopupInfos() {
		return allowedToUpdatePopupInfos;
	}

	public void setAllowedToUpdatePopupInfos(boolean allowed) {
		this.allowedToUpdatePopupInfos=false;
	}
	
}
