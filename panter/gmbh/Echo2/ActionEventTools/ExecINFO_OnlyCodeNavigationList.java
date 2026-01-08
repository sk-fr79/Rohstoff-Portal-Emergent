package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;

public class ExecINFO_OnlyCodeNavigationList extends ExecINFO 
{

	private E2_NavigationList navigationList = null;

	/*
	 * wird benutzt, wenn die actionEvents nicht im zusammenhang mit einem
	 * doAction-aufruf ausgefuehrt werden (z.B. als zusatz-aktionen innerhalb eines
	 * anderen kontexts
	 */
	public ExecINFO_OnlyCodeNavigationList(E2_NavigationList oNavigationList) 
	{
		super(null, true);
		this.navigationList =oNavigationList;
	}
	

//	public void set_NavigationList(E2_NavigationList navigationList) {
//		this.navigationList = navigationList;
//	}

	public E2_NavigationList get_NavigationList() {
		return navigationList;
	}

}
