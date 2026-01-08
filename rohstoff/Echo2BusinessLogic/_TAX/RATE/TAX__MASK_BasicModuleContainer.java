package rohstoff.Echo2BusinessLogic._TAX.RATE;


import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class TAX__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public TAX__MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.MODUL_TAX_MASK.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		TAX__MASK_ComponentMAP oTAX__MASK_ComponentMAP = new TAX__MASK_ComponentMAP();
		
		this.INIT(oTAX__MASK_ComponentMAP, new TAX__MASK(oTAX__MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
