package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class WK_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public WK_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		WK_MASK_ComponentMAP oWK_MASK_ComponentMAP = new WK_MASK_ComponentMAP();
		
		this.INIT(oWK_MASK_ComponentMAP, new WK_MASK(oWK_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
