package panter.gmbh.indep;

public class PairS extends Pair<String> {

	public PairS(String p1, String p2) {
		super(p1, p2);
	}

	public String[] array12() {
		String[] s = new String[2];
		s[0] = this.getVal1();
		s[1] = this.getVal2();
		return s;
	}
	
	public String[] array21() {
		String[] s = new String[2];
		s[0] = this.getVal2();
		s[1] = this.getVal1();
		return s;
	}

	
}
