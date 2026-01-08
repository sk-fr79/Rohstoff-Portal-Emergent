package panter.gmbh.Echo2.ActionEventTools.Break4Popup;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MessageAgent_InPOPUP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class E2_BtCancelAndClosePopups extends E2_Button {

	private E2_Break4Popup ownBreak4Popup = null;
	
	public E2_BtCancelAndClosePopups(E2_Break4Popup p_ownBreak4Popup) {
		super();
		
		this.ownBreak4Popup = p_ownBreak4Popup;
		
		//hier auf jeden fall einen dummyActionAgent, damit ein innerActionListener angelegt wird 
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			}
		});

		this.addActionListener(new OwnActionListenerCloseAndDoNothing());
		
	}

	
	/**
	 * bricht den kompletten E2_Break4PopupContainer - stack ab und schliesst alle popup-container
	 * @author martin
	 *
	 */
	private class OwnActionListenerCloseAndDoNothing implements ActionListener {
		
		/**
		 * @param popupContainer
		 */
		public OwnActionListenerCloseAndDoNothing() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				E2_BtCancelAndClosePopups.this.ownBreak4Popup.closeAllPopups();
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
				new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
			}
		}
	}

}
