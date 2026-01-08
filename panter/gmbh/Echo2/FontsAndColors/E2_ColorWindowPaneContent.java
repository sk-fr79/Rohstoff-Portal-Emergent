package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Color;
import panter.gmbh.indep.bibALL;

public class E2_ColorWindowPaneContent extends Color
{
	private static int iDiff = 00;
	public E2_ColorWindowPaneContent()
	{
		super( (bibALL.get_iBaseColorRED()+iDiff)>254 ? 254 : bibALL.get_iBaseColorRED()+iDiff, 
				(bibALL.get_iBaseColorGREEN()+iDiff)>254 ? 254 : bibALL.get_iBaseColorGREEN()+iDiff, 
				(bibALL.get_iBaseColorBLUE()+iDiff)>254 ? 254 : bibALL.get_iBaseColorBLUE()+iDiff);
		
	}

}
