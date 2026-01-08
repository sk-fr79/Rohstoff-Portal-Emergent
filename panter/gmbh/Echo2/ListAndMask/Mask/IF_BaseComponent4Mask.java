package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.indep.exceptions.myException;

public interface IF_BaseComponent4Mask
{
	public Vector<IF_ADDING_Allowed>  get_Basic_Mask_Container_Components() throws myException;
}
