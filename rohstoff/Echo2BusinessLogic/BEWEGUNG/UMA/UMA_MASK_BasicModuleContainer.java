package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class UMA_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public UMA_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_UMA_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		UMA_MASK_ComponentMAP oUMA_MASK_ComponentMAP = new UMA_MASK_ComponentMAP();
		
		this.INIT(oUMA_MASK_ComponentMAP, new UMA_MASK(oUMA_MASK_ComponentMAP), new Extent(900), new Extent(650),true);
	}
	
	
}
