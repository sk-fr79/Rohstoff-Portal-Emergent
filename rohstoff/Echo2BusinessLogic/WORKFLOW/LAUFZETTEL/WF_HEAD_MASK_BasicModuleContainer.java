package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class WF_HEAD_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -328189365878583954L;

	public WF_HEAD_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		WF_HEAD_MASK_ComponentMAP oWF_HEAD_MASK_ComponentMAP = new WF_HEAD_MASK_ComponentMAP();
		
		this.INIT(oWF_HEAD_MASK_ComponentMAP, new WF_HEAD_MASK(oWF_HEAD_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
	}
	
	
	
	
}
