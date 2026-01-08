package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import echopointng.ComboBox;

public class StyleFactory_ComboBox extends XXX_StyleFactory
{

	public MutableStyle get_Style(boolean bEnabled, boolean bNullable,	boolean bError)
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty( ComboBox.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		oStyle.setProperty( ComboBox.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorBase());
		oStyle.setProperty( ComboBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( ComboBox.PROPERTY_ROLLOVER_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
//		oStyle.setProperty( ComboBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE);     // fuehrt zu crash wenn die select-box "runtergeklickt" wird
		oStyle.setProperty( ComboBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( ComboBox.PROPERTY_FONT, new E2_FontPlain());
		oStyle.setProperty( ComboBox.PROPERTY_POPUP_BACKGROUND, new E2_ColorBase(20));
		oStyle.setProperty( ComboBox.PROPERTY_POPUP_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		
		if (bEnabled)
		{

			if (!bNullable)
			{
				oStyle.setProperty( ComboBox.PROPERTY_BORDER, new Border(1, Color.RED, Border.STYLE_SOLID)); 
			}
		}
		else
		{
			oStyle.setProperty( ComboBox.PROPERTY_BACKGROUND, new E2_ColorGray(230)); 
		}
		
		return oStyle;
	}

}
