package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LC_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public LC_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_COLS_TO_CALC_DEF_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LC_MASK_ComponentMAP oLC_MASK_ComponentMAP = new LC_MASK_ComponentMAP();
		
		this.INIT(oLC_MASK_ComponentMAP, new LC_MASK(oLC_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
