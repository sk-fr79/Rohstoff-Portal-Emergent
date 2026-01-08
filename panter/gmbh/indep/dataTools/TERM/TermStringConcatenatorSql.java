package panter.gmbh.indep.dataTools.TERM;

import java.util.ArrayList;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class TermStringConcatenatorSql implements Term {

	private ArrayList<Term> alTerm = new ArrayList<Term>();
	private String          c_sep = "";
	
	public TermStringConcatenatorSql() {
	}

	public TermStringConcatenatorSql add_f(IF_Field field) {
		if (field!=null) {
			this.alTerm.add(field.t());
		}
		return this;
	}
	
	public TermStringConcatenatorSql add_s(String field) {
		if (field != null) {
			alTerm.add(new TermSimple(field));
		}
		return this;
	}
	
	/**
	 * 
	 * @param constant wird umgewandelt in 'constant'
	 * @return
	 */
	public TermStringConcatenatorSql add_c(String constant) {
		if (constant != null) {
			alTerm.add(new TermSimple("'"+constant+"'"));
		}
		return this;
	}

	public TermStringConcatenatorSql add_t(Term field) {
		if (field != null) {
			alTerm.add(field);
		}
		return this;
	}
	
	
	
	public TermStringConcatenatorSql(String separator, IF_Field... fields) {
		super();
		this.c_sep = separator;
		
		if (fields != null) {
			int pos = 0;
			for (IF_Field f: fields) {
				alTerm.add(f.t());
				if (pos<(fields.length-1)) {
					alTerm.add(new TermSimple("'"+separator+"'"));
				}
				pos ++;
			}
		}
	}

	public TermStringConcatenatorSql(String separator, Term... fields) {
		super();
		this.c_sep = separator;
		
		if (fields != null) {
			int pos = 0;
			for (Term t: fields) {
				alTerm.add(t);
				if (pos<(fields.length-1)) {
					alTerm.add(new TermSimple("'"+separator+"'"));
				}
				pos ++;
			}
		}
	}

	
	public TermStringConcatenatorSql(String separator, String... fields) {
		super();
		this.c_sep = separator;
		
		if (fields != null) {
			int pos = 0;
			for (String s: fields) {
				alTerm.add(new TermSimple(s));
				if (pos<(fields.length-1)) {
					alTerm.add(new TermSimple("'"+separator+"'"));
				}
				pos ++;
			}
		}
	}


	
	
	
	
	/**
	 * setzt den trenner fuer das erzeugte sql-Term (innerhalb des concatierungs-ausdrucks)
	 * @param separator
	 * @return
	 */
	public TermStringConcatenatorSql set_separator(String separator) {
		this.c_sep = separator;
		return this;
	}
	
	
	@Override
	public String s() throws myException {
		StringBuffer sbRueck = new StringBuffer();
		for (Term t: this.alTerm) {
			sbRueck.append(t.s()+"||");
		}
		String cRueck = sbRueck.toString();
		if (cRueck!=null && cRueck.lastIndexOf("||")>0) {
			cRueck = cRueck.substring(0,cRueck.lastIndexOf("||"));
		}
		
		return cRueck;
	}

}
