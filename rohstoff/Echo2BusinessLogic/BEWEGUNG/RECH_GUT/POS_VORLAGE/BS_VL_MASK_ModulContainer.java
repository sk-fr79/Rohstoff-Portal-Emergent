package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class BS_VL_MASK_ModulContainer extends E2_BasicModuleContainer_MASK 
{
	
	private	BS_VL_MASK__ComponentMAP 		oComponentMAP_Mask = null;

	
	public BS_VL_MASK_ModulContainer()  throws myException
	{
		super();
		
		this.oComponentMAP_Mask = 		new BS_VL_MASK__ComponentMAP(this);
		
		this.INIT(oComponentMAP_Mask,new BS_VL_MASK(oComponentMAP_Mask),new Extent(1000),new Extent(650));
		 
	}


	
}
