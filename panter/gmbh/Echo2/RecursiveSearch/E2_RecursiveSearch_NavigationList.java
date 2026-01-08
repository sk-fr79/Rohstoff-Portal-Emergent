package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.bibALL;

public class E2_RecursiveSearch_NavigationList extends E2_RecursiveSearch_Component
{

	public E2_RecursiveSearch_NavigationList()
	{
		super(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(), bibALL.get_Vector(E2_NavigationList.class.getName()), null);
	}

	
	public E2_RecursiveSearch_NavigationList(Component searchedContainer)
	{
		super(searchedContainer, bibALL.get_Vector(E2_NavigationList.class.getName()), null);
	}

	public Vector<E2_NavigationList>  get_vNavigationLists()
	{
		Vector<E2_NavigationList>   vNaviListRueck = new Vector<E2_NavigationList>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof E2_NavigationList)
			{
				vNaviListRueck.add((E2_NavigationList)this.get_vAllComponents().get(i));
			}
		}
		
		return vNaviListRueck;
	}
}
