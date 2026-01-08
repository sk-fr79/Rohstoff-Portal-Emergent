package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.exceptions.myException;

public class E2_RefreshGlobalInfos
{

	public E2_RefreshGlobalInfos() throws myException
	{
		super();
		
		Vector<E2_Refreshable>  vRefreshables = bibE2.get_vRefreshables();
		 
		for (E2_Refreshable oRefresh: vRefreshables)
		{
			oRefresh.RefreshInfo();
		}
	}

}
