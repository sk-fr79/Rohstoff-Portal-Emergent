package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LAND__MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public LAND__MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAENDER_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LAND__MASK_ComponentMAP oLAND__MASK_ComponentMAP = new LAND__MASK_ComponentMAP();
		
		this.INIT(oLAND__MASK_ComponentMAP, new LAND__MASK_TAB(oLAND__MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
