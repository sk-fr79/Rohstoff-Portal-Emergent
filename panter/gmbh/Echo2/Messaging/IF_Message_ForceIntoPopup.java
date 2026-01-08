package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.event.WindowPaneListener;


public interface IF_Message_ForceIntoPopup extends WindowPaneListener {
	
	public void     							create_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg);
	public E2_WindowPane_4_PopupMessages        get_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg);
	
}
