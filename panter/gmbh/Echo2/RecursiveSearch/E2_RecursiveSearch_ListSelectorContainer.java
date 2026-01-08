package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.indep.bibALL;

public class E2_RecursiveSearch_ListSelectorContainer extends E2_RecursiveSearch_Component
{

	public E2_RecursiveSearch_ListSelectorContainer(Component searchedContainer)
	{
		super(searchedContainer, bibALL.get_Vector(E2_ListSelectorContainer.class.getName()), null);
	}

	public E2_ListSelectorContainer  get_found_E2_ListSelectorContainer()
	{
		E2_ListSelectorContainer  oRueck = null;
		
		Vector<E2_ListSelectorContainer>   vListSelectorContainer = new Vector<E2_ListSelectorContainer>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof E2_ListSelectorContainer)
			{
				vListSelectorContainer.add((E2_ListSelectorContainer)this.get_vAllComponents().get(i));
			}
		}
		
		if (vListSelectorContainer.size()==1)
		{
			oRueck = vListSelectorContainer.get(0);
		}
		
		return oRueck;
	}
}
