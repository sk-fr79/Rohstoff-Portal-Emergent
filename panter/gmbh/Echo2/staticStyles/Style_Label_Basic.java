package panter.gmbh.Echo2.staticStyles;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class Style_Label_Basic extends MutableStyle
{

	public Style_Label_Basic()
	{
		super();
		
		// this.setProperty( Label.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		this.setProperty( Label.PROPERTY_FONT, new E2_FontPlain());
	}

	public Style_Label_Basic(Font oFont)
	{
		super();
		
		// this.setProperty( Label.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		this.setProperty( Label.PROPERTY_FONT, oFont);
	}

	
}
