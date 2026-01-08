package panter.gmbh.Echo2.staticStyles;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;

public class Style_Grid_Normal extends MutableStyle
{

	public Style_Grid_Normal(int iBorderSize, Insets oInsets)
	{
		super();
		// this.setProperty( Grid.PROPERTY_BACKGROUND, new E2_ColorBase()); 
		if (iBorderSize>0) this.setProperty( Grid.PROPERTY_BORDER, new Border(iBorderSize, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		this.setProperty( Grid.PROPERTY_INSETS, oInsets); 

	}
	
}
