package panter.gmbh.Echo2.TEST1;

import panter.gmbh.indep.S;

public class verlaengere100 implements IF_ownText {
	
	protected IF_ownText text = null;

	/**
	 * @param text
	 */
	public verlaengere100(IF_ownText p_text) {
		super();
		this.text=p_text;
	}

	@Override
	public String s() {
		String c = S.NN(this.text.s());
		if (c.length()<100) {
			c=c+S.n_times(" ", 100).substring(0,100-c.length());
		}
		return c;
	}

	
	
	
	
	
	
}
