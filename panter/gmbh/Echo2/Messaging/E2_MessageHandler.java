package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public class E2_MessageHandler
{

	private XX_MessageManipulator  oManipulator = null;

	
	
	public E2_MessageHandler(ActionEvent oEvent)
	{
		super();
		
		if (oEvent != null && oEvent.getSource() != null && oEvent.getSource() instanceof E2_IF_Handles_ActionAgents) {
			this.oManipulator = ((E2_IF_Handles_ActionAgents)oEvent.getSource()).get_MessageManipulator();
		}
		
	}
	
	// sieht selbst nach, wo eine message angezeigt wird
	public void showMessages()
	{
		
		boolean bMessageWasShown = false;
		
		
		if (this.oManipulator != null) {
			MyE2_MessageVector  oMV_Zwischen = new MyE2_MessageVector();
			MyE2_MessageVector  oMV_new = null;
			
			oMV_Zwischen.add_MESSAGE(bibMSG.MV());
			
			try {
				oMV_new = this.oManipulator.get_Changed_MessageVector(oMV_Zwischen);
				
				if (oMV_new != null) {
					//dann den Vector in bibMSG ersetzen
					bibMSG.MV().removeAllElements();
					bibMSG.MV().add_MESSAGE(oMV_new);
				}
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		//hier unterscheiden, ob eine message vorhanden ist, die in ein eigenes popup-fenster soll
		//z.B. solche mit decisions und bestaetigungsbuttons
		IF_Message_ForceIntoPopup oForcePopup = this.getFirst_if_popup(bibMSG.MV());
		if (oForcePopup!=null) {
			oForcePopup.create_WindowPane4ShowingMessages(oForcePopup);
			E2_WindowPane_4_PopupMessages oPopUp = oForcePopup.get_WindowPane4ShowingMessages(oForcePopup);
			
			oPopUp.showActualMessages();
			oPopUp.showPopup(oForcePopup);
			
			bMessageWasShown = true;
			
		} else {
		
			E2_BasicModuleContainer  oContainer4Messages = bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().get_NewestActivMessageBasicModuleContainer();
	
			if (oContainer4Messages!=null)
			{
				oContainer4Messages.showActualMessages();
				bMessageWasShown = true;
			}
				
		}
		
		// notnagel
		if (!bMessageWasShown) {
			new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
		}
				
	}
	
	public void cleanMessages()
	{
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().clean_oldMessages();
	}
	
	/**
	 * 2018-05-18: neue methoden mit selbstrueckgabe
	 * @return
	 */
	public E2_MessageHandler _cleanMessages() 	{
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().clean_oldMessages();
		return this;
	}
	
	public E2_MessageHandler _showMessages() 	{
		this.showMessages();
		return this;
	}

	
	
	//2014-11-20: neue methode um Messages zu behandeln, die in eigenen popups angezeigt werden sollen
	private IF_Message_ForceIntoPopup getFirst_if_popup(MyE2_MessageVector oMV) {
		IF_Message_ForceIntoPopup msgRueck = null;
		for (MyE2_Message  msg: oMV) {
			if (msg instanceof IF_Message_ForceIntoPopup) {
				return ((IF_Message_ForceIntoPopup)msg);
			}
		}
		return msgRueck;
	}
	
}
