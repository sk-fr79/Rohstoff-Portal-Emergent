package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.SelectField;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class StyleFactory_SelectField extends XXX_StyleFactory
{

	public MutableStyle get_Style(boolean bEnabled, boolean bNullable,	boolean bError)
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, new E2_ColorBase(0)); 
		oStyle.setProperty( SelectField.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( SelectField.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( SelectField.PROPERTY_FONT, new E2_FontPlain());
		oStyle.setProperty( SelectField.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		
		if (bEnabled)
		{
			if (bError)
			{
				oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, Color.YELLOW);
			}
			else
			{
				oStyle.setProperty(SelectField.PROPERTY_BACKGROUND, new E2_ColorEditBackground());
			}
			if (!bNullable)
			{
				oStyle.setProperty( SelectField.PROPERTY_BORDER, new Border(1, Color.RED, Border.STYLE_SOLID)); 
			}
		}
		else
		{
			oStyle.setProperty( SelectField.PROPERTY_BACKGROUND, new E2_ColorGray(230)); 
		}
		
		return oStyle;
	}

}
