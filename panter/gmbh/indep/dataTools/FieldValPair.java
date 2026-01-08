package panter.gmbh.indep.dataTools;


/**
 * hilfsklasse zum befuellen von RECORDNEW-objekten
 * @author martin
 *
 */
public class FieldValPair {
	private IF_Field  f = null;
	private String    s_f = null;
	
	/**
	 * 
	 * @param p_f field
	 * @param p_s_f formated string
	 */
	public FieldValPair(IF_Field p_f, String   p_s_f) {
		super();
		this.f = p_f;
		this.s_f = p_s_f;
	}

	
	public IF_Field getF() {
		return f;
	}

	
	public String getS_f() {
		return s_f;
	}

}
