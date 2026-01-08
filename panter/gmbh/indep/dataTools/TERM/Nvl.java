package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.exceptions.myException;

public class Nvl implements Term {

	private Term  	field = null;
	private Term    ersatz = null;
	
	/**
	 * 
	 * @param field1 (das feld, das als standard benutzt wird)
	 * @param ersatz (wird in den nvl-ausdruck eingesetzt)
	 */
	public Nvl(Term field1, Term ersatz) {
		this.field = field1;
		this.ersatz=ersatz;
	}


	/**
	 * 
	 * @param field1 (das feld, das als standard benutzt wird)
	 * @param ersatz (String wird in TermSimple umdefiniert)
	 */
	public Nvl(Term field1, String s_ersatz) {
		this.field = field1;
		this.ersatz=new TermSimple(s_ersatz);
	}

	
	
	@Override
	public String s()  throws myException {
		if (this.field==null || this.ersatz==null){
			return null;
		} else {
			return "NVL("+this.field.s()+","+this.ersatz.s()+")";
		}
	}
}
