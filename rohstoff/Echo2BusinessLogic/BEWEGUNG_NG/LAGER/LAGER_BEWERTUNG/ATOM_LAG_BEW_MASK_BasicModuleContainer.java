package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_LAG_BEW_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/**
	 * 
	 */
	

	public ATOM_LAG_BEW_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		ATOM_LAG_BEW_MASK_ComponentMAP oLAG_BEW_MASK_ComponentMAP = new ATOM_LAG_BEW_MASK_ComponentMAP();
		
		this.INIT(oLAG_BEW_MASK_ComponentMAP, new ATOM_LAG_BEW_MASK(oLAG_BEW_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
