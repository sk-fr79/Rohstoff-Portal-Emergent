package panter.gmbh.Echo2.TEST1;

import panter.gmbh.indep.S;

public class lockereAuf implements IF_ownText {
	
	protected IF_ownText text = null;

	/**
	 * erzeugt instance
	 * @param t
	 * @return
	 */
	public static lockereAuf i(IF_ownText t) {
		return new lockereAuf(t);
	}
	/**
	 * @param text
	 */
	private lockereAuf(IF_ownText p_text) {
		super();
		this.text=p_text;
	}

	@Override
	public String s() {
		String c = S.NN(this.text.s());
		
		StringBuffer sb = new StringBuffer();
		for (char ch: c.toCharArray()) {
			sb.append(ch).append(' ');
		}
		
		return sb.toString().trim();
	}

}
