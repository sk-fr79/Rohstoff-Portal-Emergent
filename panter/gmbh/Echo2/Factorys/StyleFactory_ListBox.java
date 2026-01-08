package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.ListBox;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class StyleFactory_ListBox extends XXX_StyleFactory
{

	public MutableStyle get_Style(boolean bEnabled, boolean bNullable,	boolean bError)
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty( ListBox.PROPERTY_BACKGROUND, new E2_ColorEditBackground()); 
		oStyle.setProperty( ListBox.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorBase());
		oStyle.setProperty( ListBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( ListBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( ListBox.PROPERTY_FONT, new E2_FontPlain());
		
		if (bEnabled)
		{
			if (bError)
			{
				oStyle.setProperty( ListBox.PROPERTY_BACKGROUND, Color.YELLOW);
			}
			if (!bNullable)
			{
				oStyle.setProperty( ListBox.PROPERTY_BORDER, new Border(1, Color.RED, Border.STYLE_SOLID)); 
			}
		}
		else
		{
			oStyle.setProperty( ListBox.PROPERTY_BACKGROUND, new E2_ColorGray(230)); 
		}
		
		return oStyle;
		
	}

}
