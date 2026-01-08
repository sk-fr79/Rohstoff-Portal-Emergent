package panter.gmbh.Echo2.staticStyles;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Table;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class Style_Table_Normal extends MutableStyle
{

	public Style_Table_Normal(Border oBorder)
	{
		super();

		// this.setProperty( Table.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_DOTTED));
		
		if (oBorder != null ) 
			this.setProperty( Table.PROPERTY_BORDER, oBorder);
		
		this.setProperty( Table.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorBase(10));
		this.setProperty( Table.PROPERTY_INSETS, new Insets(0,2,2,2)); 
		this.setProperty( Table.PROPERTY_FONT, new E2_FontPlain());
				
	}

	
	public Style_Table_Normal(Border oBorder, Insets oInsets)
	{
		super();

		// this.setProperty( Table.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_DOTTED));
		
		if (oBorder != null ) 
			this.setProperty( Table.PROPERTY_BORDER, oBorder);
		
		this.setProperty( Table.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorBase(10));
		if (oInsets != null)
			this.setProperty( Table.PROPERTY_INSETS, oInsets); 
		
		this.setProperty( Table.PROPERTY_FONT, new E2_FontPlain());
				
	}


	public Style_Table_Normal(Border oBorder, Insets oInsets, Color colBackground)
	{
		super();

		// this.setProperty( Table.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_DOTTED));
		
		if (oBorder != null ) 
			this.setProperty( Table.PROPERTY_BORDER, oBorder);
		
		this.setProperty( Table.PROPERTY_ROLLOVER_BACKGROUND, colBackground);
		if (oInsets != null)
			this.setProperty( Table.PROPERTY_INSETS, oInsets); 
		
		this.setProperty( Table.PROPERTY_FONT, new E2_FontPlain());
				
	}

	
}
