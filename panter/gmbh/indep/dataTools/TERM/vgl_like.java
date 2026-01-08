package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class vgl_like implements Term {

	private IF_Field 	field = null;
	private String 		ausdruck = null;
	


	public vgl_like(IF_Field _field, String _ausdruck) {
		super();
		this.field = _field;
		this.ausdruck = _ausdruck;
	}



	@Override
	public String s() throws myException {
		String s = "UPPER("+this.field.tnfn()+") LIKE UPPER('%"+this.ausdruck+"%')";
		
		return s;
	}

}
