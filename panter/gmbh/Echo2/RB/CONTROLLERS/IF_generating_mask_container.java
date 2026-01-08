package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;

public interface IF_generating_mask_container {
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException;
	public RB_ModuleContainerMASK get_MaskContainer() throws myException;
}
