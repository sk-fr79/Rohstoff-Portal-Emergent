package panter.gmbh.Echo2.Container;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

public interface IF_MessagePanel
{
	public void showActualMessages();
	
	public void showMessages(MyE2_MessageVector oMV);
}
