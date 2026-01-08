package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.indep.bibALL;

public class E2_RecursiveSearch_E2_DataSearch extends E2_RecursiveSearch_Component
{

	public E2_RecursiveSearch_E2_DataSearch(Component searchedContainer)
	{
		super(searchedContainer, bibALL.get_Vector(E2_DataSearch.class.getName()), null);
	}

	public E2_DataSearch  get_found_E2_DataSearch()
	{
		E2_DataSearch  oRueck = null;
		
		Vector<E2_DataSearch>   vListSelectorContainer = new Vector<E2_DataSearch>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof E2_DataSearch)
			{
				vListSelectorContainer.add((E2_DataSearch)this.get_vAllComponents().get(i));
			}
		}
		
		if (vListSelectorContainer.size()==1)
		{
			oRueck = vListSelectorContainer.get(0);
		}
		
		return oRueck;
	}
}
