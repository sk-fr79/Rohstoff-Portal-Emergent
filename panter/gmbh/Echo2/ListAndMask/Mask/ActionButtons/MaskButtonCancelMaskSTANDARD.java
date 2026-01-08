package panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons;

import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;

public class MaskButtonCancelMaskSTANDARD extends maskButtonCancelMask
{

	public MaskButtonCancelMaskSTANDARD(E2_BasicModuleContainer_MASK maskContainer)
	{
		super(maskContainer);
	}


	
	public boolean doActionAfterCancelMask()
	{
		return true;
	}

}
