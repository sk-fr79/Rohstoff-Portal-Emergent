package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_LAG_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public ATOM_LAG_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWEGUNG_MASK);
		
		this.set_bVisible_Row_For_Messages(true);
		
		ATOM_LAG_MASK_ComponentMAP oATOM_LAG_MASK_ComponentMAP = new ATOM_LAG_MASK_ComponentMAP();
		
		this.INIT(oATOM_LAG_MASK_ComponentMAP, new ATOM_LAG_MASK(oATOM_LAG_MASK_ComponentMAP), new Extent(900), new Extent(650));
	}
	
	
}
