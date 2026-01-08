package panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER;

import panter.gmbh.indep.exceptions.myException;

public interface IF_DBC_DaughterTable_do_something_before_filling {
	
	public void prepare4Filling(MyE2_DBC_DaughterTable daughter) throws myException;
	public void prepare4NewMask(MyE2_DBC_DaughterTable daughter) throws myException;
	
}
