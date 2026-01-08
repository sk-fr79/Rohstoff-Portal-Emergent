package panter.gmbh.Echo2.RB.IF;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public interface IF_generate_RB_KF {
	public RB_KF K(IF_Field field) throws myException;
}
