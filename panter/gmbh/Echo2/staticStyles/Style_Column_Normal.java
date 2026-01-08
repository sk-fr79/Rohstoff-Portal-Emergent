package panter.gmbh.Echo2.staticStyles;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class Style_Column_Normal extends MutableStyle
{

	public Style_Column_Normal(int iBorderSize, Insets oInsets)
	{
		super();
		//this.setProperty( Column.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		if (iBorderSize>0) this.setProperty( Column.PROPERTY_BORDER, new Border(iBorderSize, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		this.setProperty( Column.PROPERTY_INSETS, oInsets); 
		this.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());
	}
	
}
