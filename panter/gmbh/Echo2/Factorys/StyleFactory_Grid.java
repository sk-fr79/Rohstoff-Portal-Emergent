package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class StyleFactory_Grid
{

	// weitere implementiertung der grid-styles, die auch im myE2_Grid zu finden sind. 
	// hier sind die 100% nicht 99%
	 
	public static MutableStyle 	STYLE_GRID_DDARK_BORDER()
	{
		MutableStyle oStyleDDARKBorder = new MutableStyle();
		oStyleDDARKBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		return oStyleDDARKBorder;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		return oStyleNOBorder;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyleNOBorder;
	}

	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyleNOBorder.setProperty( Grid.PROPERTY_ORIENTATION, new Integer(MyE2_Grid.ORIENTATION_VERTICAL));
		return oStyleNOBorder;
	}


	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}


	
	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	
	public static MutableStyle 	STYLE_GRID_DD_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	
	public static MutableStyle 	STYLE_GRID_BLACK_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_BLACK_BORDER_NO_INSETS()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyle;
	}


	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_NO_INSETS()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyle;
	}


	
	//neue styles mit schwarzem rahmen
	public static MutableStyle 	STYLE_GRID_BORDER(Color oCol)
	{
		MutableStyle oStyleDDARKBorder = new MutableStyle();
		oStyleDDARKBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		
		return oStyleDDARKBorder;
	}
	
	//neue styles mit schwarzem rahmen
	public static MutableStyle 	STYLE_GRID_BORDER(Border oBorder)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,oBorder);
		
		return oStyle;
	}
	

	
	
	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11(Color oCol)
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}


	
	public static MutableStyle 	STYLE_GRID_BORDER_W100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}
	


	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11_W100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_BORDER_W100_H100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}
	
	
	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11_W100_H100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		return oStyle;
	}

	//ende styles mit schwarz
	
	
	
	
}
