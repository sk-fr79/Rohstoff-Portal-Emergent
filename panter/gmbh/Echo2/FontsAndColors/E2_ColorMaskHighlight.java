package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Color;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_ColorMaskHighlight extends Color
{

	public E2_ColorMaskHighlight() throws myException
	{
		super( 	bibALL.get_RECORD_MANDANT().get_COLOR_MASK_HIGHLIGHT_RED_lValue(new Long(100)).intValue(),
				bibALL.get_RECORD_MANDANT().get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(new Long(100)).intValue(),
				bibALL.get_RECORD_MANDANT().get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(new Long(100)).intValue()
			 );

	}
}
