package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Color;

public class E2_Color_FromHexString extends Color {

	/**
	 * 
	 * @param cHexString (Start mit #)
	 */
	public E2_Color_FromHexString(String cHexString) {
		super(cHexString.startsWith("#")?
			   Integer.parseInt(cHexString.substring(1),16)
			   :
			   Integer.parseInt(cHexString,16)  );
	}

}
