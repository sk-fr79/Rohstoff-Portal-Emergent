package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 * finds first mother-BasicModuleContainer
 */
public class E2_RecursiveSearchParent_Grid
{
	private Vector<Grid> vMotherGrids = new Vector<Grid>();
	
	
	public E2_RecursiveSearchParent_Grid(Component oComp)
	{
		super();
		this.find_Parent_Grid(oComp);
	}

	
	public E2_RecursiveSearchParent_Grid(ActionEvent oEvent)
	{
		super();
		
		if (oEvent == null || !(oEvent.getSource() instanceof Component))
			return;
		
		Component oComp = (Component)oEvent.getSource();

		this.find_Parent_Grid(oComp);
		
	}


	
	public E2_RecursiveSearchParent_Grid()
	{
		super();
		MyActionEvent oEvent = bibE2.get_LAST_ACTIONEVENT();
		
		if (!(oEvent.getSource() instanceof Component))
			return;
		
		Component oComp = (Component)oEvent.getSource();

		this.find_Parent_Grid(oComp);
		
	}

	

	// sammelt alle E2_BasicModuleContainer-objekte in der hierarchie
	private void find_Parent_Grid(Component oComp)
	{
		
		Component oCompMother = oComp.getParent();
		
		if (oCompMother != null)
		{
			if (oCompMother instanceof Grid)
			{
				 this.vMotherGrids.add((Grid)oCompMother);   				// gefunden !!
				 
				 //und weitersuchen
				 this.find_Parent_Grid(oCompMother);
			}
			this.find_Parent_Grid(oCompMother); 	// weitersuchen
		}
		
	}

	
	public Grid get_First_Grid()
	{
		Grid oContainer = null;
		if (this.vMotherGrids.size()>0)
			oContainer = (Grid)this.vMotherGrids.get(0);
		
		return oContainer;
	}


	
	public Grid get_First_FoundGrid_ThrowExWhenNotFound() throws myException
	{
		Grid oContainer = null;
		if (this.vMotherGrids.size()>0)
			oContainer = (Grid)this.vMotherGrids.get(0);

		if (oContainer == null)
			throw new myException("E2_RecursiveSearchParent_Grid: No Grid Found !");

		return oContainer;
	}


	


	
	
	
	public Vector<Grid> get_vMotherGrids() 
	{
		return vMotherGrids;
	}

	public Vector<Grid> get_vMotherGrids_ThrowExWhenNotFound()  throws myException
	{
		if (this.vMotherGrids.isEmpty())
			throw new myException("E2_RecursiveSearchParent_Grid: No Grid Found !");
			
		return vMotherGrids;
	}

	
}
