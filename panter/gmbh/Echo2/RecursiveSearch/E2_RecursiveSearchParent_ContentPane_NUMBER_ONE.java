package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 * finds first mother-contentPane in hirachy
 */
public class E2_RecursiveSearchParent_ContentPane_NUMBER_ONE
{
	private Vector<E2_ContentPane> vContentPanes = new Vector<E2_ContentPane>();
	
	public E2_RecursiveSearchParent_ContentPane_NUMBER_ONE(Component oComp)
	{
		super();
		this.find_Parent_E2_ContentPanes(oComp);
		
	}

	
	public E2_RecursiveSearchParent_ContentPane_NUMBER_ONE(MyActionEvent oEvent)
	{
		super();
		if (oEvent == null)
			return;
		
		this.find_Parent_E2_ContentPanes((Component)oEvent.getSource());
		
	}

	public E2_RecursiveSearchParent_ContentPane_NUMBER_ONE()
	{
		super();
		this.find_Parent_E2_ContentPanes((Component)bibE2.get_LAST_ACTIONEVENT().getSource());
		
	}

	private void find_Parent_E2_ContentPanes(Component oComp)
	{
		if (oComp == null)
			return;
		
		Component oCompMother = oComp.getParent();
		if (oCompMother instanceof E2_ContentPane)
			this.vContentPanes.add((E2_ContentPane)oCompMother);
		
		if (oCompMother != null)
			this.find_Parent_E2_ContentPanes(oCompMother);
	}

	
	public E2_ContentPane get_FoundPane()
	{
		E2_ContentPane  oPane = null;
		
		if (this.vContentPanes.size() > 0)
			oPane = (E2_ContentPane)this.vContentPanes.get(this.vContentPanes.size()-1);
		
		return oPane;
	}


	public E2_ContentPane get_FoundPane_ThrowExWhenNotFound() throws myException
	{
		E2_ContentPane  oPane = null;
		
		if (this.vContentPanes.size() > 0)
			oPane = (E2_ContentPane)this.vContentPanes.get(this.vContentPanes.size()-1);
		
		if (oPane==null)
			throw new myException("E2_RecursiveSearchParent_ContentPane_NUMBER_ONE:No Pane Found !");
		
		return oPane;
	}

	
	
}
