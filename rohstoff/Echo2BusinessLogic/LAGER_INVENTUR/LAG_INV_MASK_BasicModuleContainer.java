package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LAG_INV_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public LAG_INV_MASK_BasicModuleContainer(boolean bNurNullbuchungen) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_INVENTUR_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LAG_INV_MASK_ComponentMAP oLAG_INV_MASK_ComponentMAP = new LAG_INV_MASK_ComponentMAP(bNurNullbuchungen);
		
		this.INIT(oLAG_INV_MASK_ComponentMAP, new LAG_INV_MASK(oLAG_INV_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
