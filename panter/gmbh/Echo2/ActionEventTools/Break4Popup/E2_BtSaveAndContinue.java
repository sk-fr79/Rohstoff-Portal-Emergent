package panter.gmbh.Echo2.ActionEventTools.Break4Popup;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.BasicInterfaces.IF_ExecuterReturnsMV;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MessageAgent_InPOPUP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class E2_BtSaveAndContinue extends E2_Button {
	private E2_Break4Popup ownBreak4Popup = null;
	
	public E2_BtSaveAndContinue(E2_Break4Popup p_ownBreak4Popup) {
		super();
		
		this.ownBreak4Popup = p_ownBreak4Popup;
		
		//hier auf jeden fall einen dummyActionAgent, damit ein innerActionListener angelegt wird 
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			}
		});

		this.addActionListener(new OwnActionListenerSaveAndContinue());
		
	}

	
	/**
	 * bricht den kompletten E2_Break4PopupContainer - stack ab und schliesst alle popup-container
	 * @author martin
	 *
	 */
	private class OwnActionListenerSaveAndContinue implements ActionListener {
		
		/**
		 * @param popupContainer
		 */
		public OwnActionListenerSaveAndContinue() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			E2_Break4Popup ownBreaker = E2_BtSaveAndContinue.this.ownBreak4Popup;
			E2_Break4PopupController breakController = ownBreaker.getBreak4PopupController();
			try {

				MyE2_MessageVector mvAddonExecuters = bibMSG.getNewMV();
				for (IF_ExecuterReturnsMV<E2_Break4Popup> executer: ownBreaker.getExecutersBeforeSave()) {
					mvAddonExecuters._add(executer.execute(ownBreaker));
				}
				if (mvAddonExecuters.hasAlarms()) {
					//dannn wird hier unterbrochen
					bibMSG.add_MESSAGE(mvAddonExecuters);
					new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
				} else {
					
					//hier den bestaetigungszaehler des eigenen popups hochsetzen
					//hier den popup-counter der hashmap hochzaehlen, wenn der popup nochmals gecheckt wird (z.B. im Bestätigungsbutton), ist der zaehler >0 
					breakController.getHmCounter().put(ownBreaker,breakController.getHmCounter().get(ownBreaker).intValue()+1);
	
					//zuerst alle schliessen
					ownBreaker.closeAllPopups();
					
					
					//jetzt alle wieder durchgehen und auf relevanz pruefen (evtl. den rest wieder aufpoppen lassen)
					breakController.getMv().clear();
					breakController.executeChecks(breakController.getEventWhichWasBroken());
					
					bibMSG.add_MESSAGE(breakController.getMv());
	
					if (breakController.getMv().get_bHasAlarms()) {
						// die fehler anzeigen und event abbrechen
						new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
					} else {
						if (breakController.hasRelevantBreak()) {
							bibE2.set_LAST_ACTIONEVENT(new MyActionEvent(breakController.getEventWhichWasBroken()));
							breakController.popupsIfRelevant();
							
							//falls hier ein fehler aufgetreten ist, notfallclose
							if (breakController.getMv().get_bHasAlarms()) {
								breakController.closeAllPopups();
								bibMSG.add_MESSAGE(breakController.getMv());
								//new E2_MessageHandler(new MyActionEvent(breakController.getEventWhichWasBroken()))._showMessages()._cleanMessages();
								new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
							}
							
						} else {
							//wenn keine relevanz gefunden wird, dann den abschluss-execute 
							new E2_InnerActionListenerForActionAgents(false).actionPerformed(breakController.getEventWhichWasBroken());
						}
					}
				}
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
				new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
			}
		}
	}


}
