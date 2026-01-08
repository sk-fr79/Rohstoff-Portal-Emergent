package panter.gmbh.indep.dataTools.GENERATE;

public class TERM {
	public String LEFT = null;
	public String OP = null;
	public String RIGHT = null;
	
	boolean ConnecterAsAnd = true;
	
	
	public TERM(String lEFT, String oP, String rIGHT) {
		super();
		LEFT = lEFT;
		OP = oP;
		RIGHT = rIGHT;
	}
	
	public TERM(String lEFT, String oP, String rIGHT, boolean apostroLeft, boolean apostroRight) {
		super();
		LEFT = apostroLeft?"'"+lEFT+"'":lEFT;
		OP = oP;
		RIGHT = apostroRight?"'"+rIGHT+"'":rIGHT;;
	}
	
	public String get_TERM() {
		return this.LEFT+" "+this.OP+" "+this.RIGHT;
	}

}
