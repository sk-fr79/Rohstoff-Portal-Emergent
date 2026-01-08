package panter.gmbh.Echo2.RB.COMP;

import panter.gmbh.Echo2.MyE2_String;

public class RB {

	public static RB_lab  l(MyE2_String text) {
		return new RB_lab()._t(text);
	}
	
	public static RB_lab  ltr(String text) {
		return new RB_lab()._tr(text);
	}
	
	
	public static RB_lab  lt(String text) {
		return new RB_lab()._t(text);
	}

}
