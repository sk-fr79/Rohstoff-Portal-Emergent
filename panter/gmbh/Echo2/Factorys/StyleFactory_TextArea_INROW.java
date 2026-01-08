package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class StyleFactory_TextArea_INROW extends XXX_StyleFactory
{

	
	public MutableStyle get_Style(boolean bEnabled, boolean bNullable,	boolean bError)
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorEditBackground()); 
		oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( TextComponent.PROPERTY_INSETS, new Insets(1,1,1,1)); 
		oStyle.setProperty( TextComponent.PROPERTY_FONT, new E2_FontPlain());
		
		if (bEnabled)
		{
			if (bError)
			{
				oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, Color.YELLOW);
			}
			if (!bNullable)
			{
				oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(1, Color.RED, Border.STYLE_SOLID)); 
			}
		}
		else
		{
			oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		}
		
		return oStyle;
	}

}
