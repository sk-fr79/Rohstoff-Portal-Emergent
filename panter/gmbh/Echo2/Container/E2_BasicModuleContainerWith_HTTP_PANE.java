package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import echopointng.HttpPaneEx;

// ein basic-ModuleContainer zum Einblenden von URLs
public class E2_BasicModuleContainerWith_HTTP_PANE extends E2_BasicModuleContainer 
{

	private HttpPaneEx  oWebSitePane = new HttpPaneEx();
	
	public E2_BasicModuleContainerWith_HTTP_PANE(int iWidth, int iHeight) 
	{
		super();
		this.set_bVisible_Row_For_Messages(true);
		this.set_oExtWidth(new Extent(iWidth));
		this.set_oExtHeight(new Extent(iHeight));
	}

	
	public void showWebsite(String cURL)
	{
		this.RESET_Content();
		this.add_In_ContainerEX(this.oWebSitePane, this.get_oExtWidth() , this.get_oExtHeight(), E2_INSETS.I_0_0_0_0);
		this.oWebSitePane.setURI(cURL);
	}
	
	
}
