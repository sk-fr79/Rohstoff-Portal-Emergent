package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MessageAgent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.components.MyE2_WindowPane;




public class MessageAgent_InPOPUP extends XX_MessageAgent
{
	private E2_ContentPane oBasePane = null;
	
	private int iWidth = 600;
	private int iHeight = 600;

	public MessageAgent_InPOPUP(E2_ContentPane basePane)
	{
		super();
		oBasePane = basePane;
	}

	
	public void show_Messages()
	{
		MyE2_MessageVector oMessages = bibMSG.MV();
		
		if (oMessages.size()>0)
		{
			
			MyE2_WindowPane oPopUpPane = new MyE2_WindowPane(new MyE2_String("Fehler"), new Extent(this.iWidth),new Extent(this.iHeight),true);
			
			E2_ContentPane  oPane = new E2_ContentPane(true);
			oPopUpPane.add(oPane);
			oPane.setInsets(new Insets(10,10,10,10));
			MyE2_MessageGrid_NG  msgGrid =   new MyE2_MessageGrid_NG(bibMSG.MV(), false,null);
			msgGrid.setWidth(new Extent(this.iWidth-40));
			oPane.add( msgGrid);
			this.oBasePane.add(oPopUpPane);
		}
		
	}

}
