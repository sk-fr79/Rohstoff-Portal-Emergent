package panter.gmbh.Echo2.RecursiveSearch;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_ContentPane;



/**
 * @author martin
 * finds first mother-contentPane
 */
public class E2_RecursiveSearchParent_ContentPane
{
	private E2_ContentPane oContentPane = null;
	
	public E2_RecursiveSearchParent_ContentPane(Component oComp)
	{
		super();
		this.oContentPane = this.get_Parent_E2_ContentPane(oComp);
		
	}
	
	
	private E2_ContentPane get_Parent_E2_ContentPane(Component oComp)
	{
		E2_ContentPane o_ContentPane = null;

		Component oCompMother = oComp.getParent();
		if (oCompMother instanceof E2_ContentPane)
			o_ContentPane = (E2_ContentPane)oCompMother;
		else if (oCompMother != null)
			o_ContentPane = this.get_Parent_E2_ContentPane(oCompMother);
		
		return o_ContentPane;
	}

	
	public E2_ContentPane get_FoundPane()
	{
		return this.oContentPane;
	}

}
