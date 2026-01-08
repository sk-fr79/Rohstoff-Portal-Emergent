package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class LAG_BEW_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4084195772656610436L;

	public LAG_BEW_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_LAGER_BEWEGUNG_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		LAG_BEW_MASK_ComponentMAP oLAG_BEW_MASK_ComponentMAP = new LAG_BEW_MASK_ComponentMAP();
		
		this.INIT(oLAG_BEW_MASK_ComponentMAP, new LAG_BEW_MASK(oLAG_BEW_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
