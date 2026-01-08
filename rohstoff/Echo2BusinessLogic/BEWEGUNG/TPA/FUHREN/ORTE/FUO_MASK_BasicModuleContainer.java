package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class FUO_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{	
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;
	
	private String    						EK_VK = null;

	public FUO_MASK_BasicModuleContainer(String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FUHREN_ORT_MASKE);

		this.EK_VK = cEK_VK; 
		
		this.oFUO_DaughterComponent = FUO_DaugherComponent;
		this.set_bVisible_Row_For_Messages(true);

		FUO_MASK_ComponentMAP oFUO_MASK_ComponentMAP = new FUO_MASK_ComponentMAP(cEK_VK, oFUO_DaughterComponent);
		
		this.INIT(oFUO_MASK_ComponentMAP, new FUO_MASK(oFUO_MASK_ComponentMAP,cEK_VK, oFUO_DaughterComponent), new Extent(900), new Extent(650));
		
	}
	
	/*
	 * der fuhrenort kann aus der Fuhrenmaske oder aus der tpa-pos-maske gestartet werden
	 */
	public E2_BasicModuleContainer_MASK get_Mother_MASK_ModuleContainer_This_Belongs_To()
	{
		return this.oFUO_DaughterComponent.EXT().get_ModuleContainer_MASK_this_BelongsTo();
	}


	public String get_EK_VK()
	{
		return EK_VK;
	}

	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}
	
	
	
}
