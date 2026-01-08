package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	public PROJEKT_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_PROJEKT_MASKE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		PROJEKT_MASK_ComponentMAP oPROJEKT_MASK_ComponentMAP = new PROJEKT_MASK_ComponentMAP();
		
		this.INIT(oPROJEKT_MASK_ComponentMAP, new PROJEKT_MASK(oPROJEKT_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
	}
	
	
	
	
}
