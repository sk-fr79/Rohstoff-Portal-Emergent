package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class AS_BasicModuleContainerMASK extends Project_BasicModuleContainer_MASK
{
	
	
	public AS_BasicModuleContainerMASK() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ARTIKELSTAMM_MASKE);
		
		AS_MASK_ComponentMAP		 oComponentMAP = new AS_MASK_ComponentMAP(this);
		this.INIT(oComponentMAP,new AS_MASK(oComponentMAP),new Extent(1100),new Extent(800));
		
		this.set_iVerticalOffsetForTabbedPane(200);
		
	}

}
