package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Color;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_ColorWindowPaneTitel extends Color
{

	public E2_ColorWindowPaneTitel() throws myException
	{
		super( 	bibALL.get_RECORD_MANDANT().get_COLOR_POPUP_TITEL_RED_lValue(new Long(100)).intValue(),
				bibALL.get_RECORD_MANDANT().get_COLOR_POPUP_TITEL_GREEN_lValue(new Long(100)).intValue(),
				bibALL.get_RECORD_MANDANT().get_COLOR_POPUP_TITEL_BLUE_lValue(new Long(100)).intValue()
			 );
	}
}
