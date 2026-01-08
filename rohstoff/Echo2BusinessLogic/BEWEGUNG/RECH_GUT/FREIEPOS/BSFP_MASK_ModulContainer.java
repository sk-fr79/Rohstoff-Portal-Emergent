package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_MASK_ModulContainer extends E2_BasicModuleContainer_MASK 
{
	
	private	BSFP_MASK_ComponentMAP 		oComponentMAP_Mask = null;

	
	public BSFP_MASK_ModulContainer()  throws myException
	{
		super();
		
		this.oComponentMAP_Mask = 		new BSFP_MASK_ComponentMAP(this);
		BSFP_MASK  oMask		= 		new BSFP_MASK(oComponentMAP_Mask);
		
		this.INIT(oComponentMAP_Mask,oMask,new Extent(1000),new Extent(650));
		 
	}


	
}
