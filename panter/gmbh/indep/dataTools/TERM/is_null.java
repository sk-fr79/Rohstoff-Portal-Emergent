package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;

public class is_null extends vgl {

	public is_null(IF_Field field) throws myException {
		super(null,field,COMP.EQ, null);
	}

}
