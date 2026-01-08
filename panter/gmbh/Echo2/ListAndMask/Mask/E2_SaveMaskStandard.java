package panter.gmbh.Echo2.ListAndMask.Mask;

import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;

public class E2_SaveMaskStandard extends E2_SaveMASK
{

	public E2_SaveMaskStandard(E2_BasicModuleContainer_MASK maskContainer, E2_NavigationList oNavigationList)
	{
		super(maskContainer, oNavigationList);
	}

	public boolean checkMaskBeforeSave(
			E2_BasicModuleContainer_MASK oMaskContainer,
			E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps,
			String cActualMaskStatus)
	{
		return true;
	}

	public void actionAfterSaveMask() throws myException
	{
	}

}
