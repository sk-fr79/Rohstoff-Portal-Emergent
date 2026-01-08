package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.E2_MessageHandler;
import panter.gmbh.Echo2.Messaging.MessageAgent_InPOPUP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;


public class E2_InnerActionListenerForActionAgents implements ActionListener  {
	
	
	//20170904: erweiterung auf die moeglichkeit, an der actionkomponente angehaengte unterbrecher
	//          fuer eine benutzerinteraktion einzubauen, dazu wird die alte methode 
	//          <public void actionPerformed(ActionEvent eEvent)> in die neue private methode 	<private void actionLocal(ActionEvent eEvent)>
	//          umgewandelt und die methode <public void actionPerformed(ActionEvent eEvent)> wird benutzt, um die break-popups abzuarbeiten 
	private boolean check4Breaks = true;
	
	
	public E2_InnerActionListenerForActionAgents(boolean p_check4Breaks) {
		super();
		this.check4Breaks = p_check4Breaks;
	}

	

	
	
	public E2_InnerActionListenerForActionAgents() 
	{
		super();
	}


	@Override
	public void actionPerformed(ActionEvent eEvent) {
		
		//rufendes element muss 2 interfaces erfuellen
		E2_IF_Handles_ActionAgents 	oComponentWithAction = 	(E2_IF_Handles_ActionAgents)eEvent.getSource();

		//boolean hasBreak4PopupControllers = oComponentWithAction.getBreak4PopupController()!=null && oComponentWithAction.getBreak4PopupController().getvBreaks().size()>0;

		//2018-01-16: aenderung: keine implizite erzeugung eines E2Break4PopupController, somit im standard null
		boolean hasBreak4PopupControllers = oComponentWithAction.getBreak4PopupController()!=null;
		
		if (hasBreak4PopupControllers && this.check4Breaks) {
			try {
				E2_Break4PopupController breakController = oComponentWithAction.getBreak4PopupController();
				breakController.getMv().clear();
				breakController._initAtExecution(eEvent);    //2018-01-16: weitere einflussnahme
				breakController.executeChecks(eEvent);
				//zuerst pruefen, ob bei den checks fehlermeldungen aufgelaufen sind
				bibMSG.add_MESSAGE(breakController.getMv());
				if (bibMSG.get_bHasAlarms()) {
					E2_MessageHandler oMessageHandler = new E2_MessageHandler(new MyActionEvent(eEvent));
					oMessageHandler.cleanMessages();
					oMessageHandler.showMessages();
				} else {
					if (breakController.hasRelevantBreak()) {
						bibE2.set_LAST_ACTIONEVENT(new MyActionEvent(eEvent));
						breakController.popupsIfRelevant();
						//hier stehen die popups bereits auf dem schirm
						//falls beim erstellen der popups alarme aufgetreten sind, diese anzeigen
						bibMSG.add_MESSAGE(breakController.getMv());
						if (bibMSG.get_bHasAlarms()) {
							//20180517: falls hier ein fehler auftritt, alle popups schliessen und damit abbruch
							breakController.closeAllPopups();
							//20180517
							E2_MessageHandler oMessageHandler = new E2_MessageHandler(new MyActionEvent(eEvent));
							oMessageHandler.cleanMessages();
							oMessageHandler.showMessages();
						}
					} else {
						this.actionLocal(eEvent);  //dann  ganz normal weiter
					}
				}
				//ansonsten stehen die popup auf dem schirm
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
				
				new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
			}
			
		} else {
			this.actionLocal(eEvent);
		}
		
		
	}

	
	
	
	
	private void actionLocal(ActionEvent eEvent) {

		// hier wird ein eigene Event untergeschoben, der traeger weiterer infos sein kann
		MyActionEvent oMyActionEvent = new MyActionEvent(eEvent);
		
		//rufendes element muss 2 interfaces erfuellen
		E2_IF_Handles_ActionAgents 	oComponentWithAction = 	(E2_IF_Handles_ActionAgents)oMyActionEvent.getSource();

		// hier wird die ganze message-agent zurodnung automatisiert
		E2_MessageHandler oMessageHandler = new E2_MessageHandler(oMyActionEvent);
		
		try
		{
			V_Single_BasicModuleContainer_PopUp_BeforeExecute v_Single_BasicModuleContainer_PopUp_BeforeExecute=new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
			
			//hier nachsehen, ob der event von einem Object kommt, das das IF__BasicModuleContainer_PopUp_BeforeExecute_Container implementiert
			if (eEvent != null && eEvent.getSource() instanceof IF__BasicModuleContainer_PopUp_BeforeExecute_Container)
			{
				v_Single_BasicModuleContainer_PopUp_BeforeExecute = ((IF__BasicModuleContainer_PopUp_BeforeExecute_Container)eEvent.getSource()).get_vE2_BasicModuleContainer_PopUp_BeforeExecute();
				oMyActionEvent.get_vE2_ContainerBeforeExecute_Dynamic().addAll(v_Single_BasicModuleContainer_PopUp_BeforeExecute);
			}


			
			//hier muss der timestamp gesetzt werden, da hier die klick-events ankommen und der stamp vor einem validierungsvorgang gestartet werden muss
			
			/*
			 *	globales mitschreiben des letzten actionEvents und Behandlung der
			 *	MessageEvents wird nur gemacht, wenn die ausloesende komponente als aktiv gekennzeichnet ist
			 * zeitstempel setzen bei allen actions in den basiscontainern (wirkt nur auf Basiscontainer, in popups ohne wirkung
			 * ABER NICHT bei vom programm ausgeloesten aktionen, d.h. die von einem anderen ActionEvent ausgeloest werden
			 */
			if (!oComponentWithAction.get_bIsPassivAction())
			{
				if (oComponentWithAction.get_bMustSet_MILLISECONDSTAMP_TO_Session())
				{
					//aktueller timestamp setzen (in Session, falls die Actionkomponente aus einer
					//entsprechen markierten E2_BasicModuleContainer-Umgebung gestartet wird)
					Vector<E2_BasicModuleContainer>  vStartContainerHirarchie = new E2_RecursiveSearchParent_BasicModuleContainer(oMyActionEvent).get_vMotherContainers();
					if (vStartContainerHirarchie.size()==0)
					{
						//dann wurde der knopf aus dem E2_Container (z.B. aus dem menue gestartet)
						this.write_Stamp("E2_Container");
					}
					else
					{
						for (int i=0;i<vStartContainerHirarchie.size();i++)
						{
							if (vStartContainerHirarchie.get(i).get_bIsStartContainer_4_DBTimeStamps())
							{
								this.write_Stamp("E2_BasicModuleContainer");
								bibE2.set_StartContainer_4_DBTimeStamps(vStartContainerHirarchie.get(i));
								break;
							}
						}
					}
				}
				// der Actionevent wird bis zum kompletten zyklus der action-performed-schleife gespeichert
				// und kann von allen inneren prozeduren benutzt werden
				//2016-02-01: ausnahme fuer bestimmte buttons, die indirekt ausfuehren
				if (oComponentWithAction instanceof IF_Handles_StealthMode) {
					MyActionEvent stealth_event = ((IF_Handles_StealthMode)oComponentWithAction).get_action_event_of_calling_klick();
					if (stealth_event==null) {
						bibE2.set_LAST_ACTIONEVENT(oMyActionEvent);
					} else {
						bibE2.set_LAST_ACTIONEVENT(stealth_event);   //dann bleibt der actionEvent vom aufrufenden klick erhalten
					}
				} else {
					bibE2.set_LAST_ACTIONEVENT(oMyActionEvent);
				}
			}

			
			
			bibMSG.add_MESSAGE(oComponentWithAction.valid_GlobalValidation());
			if (bibMSG.get_bIsOK())
			{
				Vector<XX_ActionAgent> vAgents = oComponentWithAction.get_vActionAgents();
				for (int i=0;i<vAgents.size();i++)
				{
					XX_ActionAgent oAgent =	vAgents.get(i);
					Vector<XX_ActionAgent> vRestActionAgents = new Vector<XX_ActionAgent>();
					/*
					 *  alle agenten im Vector ab der aktuellen position der ExecINFO mitgeben, falls ein popup-Container diese
					 *  Action durchfuehrt (
					 */
					for (int k=i+1;k<vAgents.size();k++)                
					{
						vRestActionAgents.add(vAgents.get(k));
					}
					oAgent.ExecuteAgentCode(new ExecINFO_doAction(oMyActionEvent,oComponentWithAction.get_bIsPassivAction(),i,vRestActionAgents));
					
					/*
					  * !!!!!! WICHTIG !!! TODO !  wenn popups angezeigt werden und es gibt mehr als einen actionagenten, dann werden die folgenden
					  *                            actionAgents hier ausgefuehrt und auch in dem finalen speicher-button der popups !!!!!
					  * !!!!!! DIESER BUG MUSS NOCH KORRIGIERT WERDEN !!!!                           
					 */
					
					
					//falls ein fehler aufgetreten ist, dann bei diesem actionAgent raus und ende
					if (bibMSG.get_bHasAlarms())
					{
						break;
					}
				}
			}
			else
			{
				//falls die aktion auf eine CheckBox gelaufen ist, dann wurde der haken auf jeden fall umgesetzt, auch wenn die action
				//ueberhaupt nicht gestartet ist.
				//deshalb muss hier wieder der haken auf zurueck-getoggelt werden
				if (oComponentWithAction instanceof CheckBox)
				{
					((CheckBox)oComponentWithAction).setSelected(!((CheckBox)oComponentWithAction).isSelected());
				}
			}

		}
		
		catch (myExceptionForUser ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		
		//debug:
		//System.out.println("meldungen (1): "+bibMSG.MV().size());

		
		/*
		 *	globales mitschreiben des letzten actionEvents und Behandlung der
		 *	MessageEvents wird nur gemacht, wenn die ausloesende komponente als aktiv gekennzeichnet ist
		 */
		if (!oComponentWithAction.get_bIsPassivAction())
		{
			
			//2011-12-22: neues message-handling
			oMessageHandler.cleanMessages();
			oMessageHandler.showMessages();
			
			try
			{
				new E2_RefreshGlobalInfos();
			} 
			catch (myException e)
			{
				e.printStackTrace();
			}
		}
	
		//debug:
		//System.out.println("meldungen (2): "+bibMSG.MV().size());
		
	}
	
	
	
	private void write_Stamp(String cWoher) throws myException
	{
		String cStamp = bibDB.EinzelAbfrage("SELECT "+DB_META.ORA_TIMESTAMP_MILLISECS+" FROM DUAL");
		if (cStamp.startsWith("2"))                 //sicherheitsabfrage, gilt bis ins Jahr 2999, dann beginnt der stamp mit 3
		{
			bibE2.set_LAST_DB_TIMESTAMP(cStamp);
			DEBUG.System_println("DB-Timestamp geschrieben:   ...: "+cStamp+"  <"+cWoher+">", DEBUG.DEBUG_FLAG_SQL_TIMESTAMP);
		}
		else
		{
			throw new myException(this,"Error with Timestamp !!");
		}
	}






}
