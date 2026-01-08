package panter.gmbh.indep.dataTools.TERM.SELECT;

import java.util.ArrayList;

import panter.gmbh.indep.dataTools.TERM.TermList;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.exceptions.myException;


public class FieldTermList extends TermList {
	
	public FieldTermList() {
		super();
	}

	
	public FieldTermList(ArrayList<String> al) {
		for (String s: al) {
			this.add(new TermSimple(s));
		}
	}
	
	public FieldTermList(String... sl) {
		for (String s: sl) {
			this.add(new TermSimple(s));
		}
	}
	
	
	public void add(FieldTerm... sl) {
		for (FieldTerm s: sl) {
			this.add(s);
		}
	}
	
	
	
	@Override
	public String get_separator() {
		return ",";
	}
	
	@Override
	public String s() throws myException {
		if (this.size()==0) {
			return "*";
		} else {
			return super.s();
		}
	}

}
