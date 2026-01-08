package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class NvlTerm extends FieldTerm {

	private IF_Field  field_ersatz = null;
	private String    value_ersatz = null;
	
	/**
	 * 
	 * @param field1 (das feld, das als standard benutzt wird)
	 * @param ersatz (wird in den nvl-ausdruck eingesetzt)
	 */
	public NvlTerm(IF_Field field1, IF_Field ersatz) {
		super(field1);
		field_ersatz=ersatz;
	}

	
	public NvlTerm(IF_Field field1, String ersatz) {
		super(field1);
		value_ersatz=ersatz;
	}

	
	@Override
	public String s()  throws myException {
		if (field_ersatz != null){
			return "NVL("+this.get_field().tnfn()+","+this.field_ersatz.tnfn()+")";
		} else {
			return "NVL("+this.get_field().tnfn()+","+this.value_ersatz+")";
		}
	}
}
