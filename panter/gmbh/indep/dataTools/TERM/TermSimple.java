package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.exceptions.myException;

public class TermSimple implements Term {

	private String inhalt = null;
	
	public TermSimple(String p_inhalt) {
		super();
		this.inhalt = p_inhalt;
	}

	@Override
	public String s() throws myException {
		return this.inhalt;
	}

}
