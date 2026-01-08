package panter.gmbh.Echo2.Container;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;


public interface IF_BasicModuleContaier_Refreshable
{
	public void Prepare_for_Refresh(E2_BasicModuleContainer oCalingContainer) throws myException;
	public void Refresh(Vector<String> vZusatzVector) throws myException;
}
