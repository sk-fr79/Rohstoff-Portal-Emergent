package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class RB_gstyle extends MutableStyle {

	public RB_gstyle() {
		super();
	}
	
	public RB_gstyle _bord(Border  border) {
		this.setProperty(Grid.PROPERTY_BORDER,border);
		return this;
	}

	public RB_gstyle _bord_l_grey() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), Color.LIGHTGRAY, Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_black() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), Color.BLACK, Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_red() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), Color.RED, Border.STYLE_SOLID));
		return this;
	}

	
	public RB_gstyle _bord_d_grey() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), Color.DARKGRAY, Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_light() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorLight(), Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_llight() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorLLight(), Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_dark() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorDark(), Border.STYLE_SOLID));
		return this;
	}

	public RB_gstyle _bord_ddark() {
		this.setProperty(Grid.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorDDark(), Border.STYLE_SOLID));
		return this;
	}

	
	public RB_gstyle _ins(Insets  insets) {
		this.setProperty(Grid.PROPERTY_INSETS,insets);
		return this;
	}

	public RB_gstyle _vertical() {
		this.setProperty(Grid.PROPERTY_ORIENTATION,new Integer(MyE2_Grid.ORIENTATION_VERTICAL));
		return this;
	}
	
	public RB_gstyle _horizontal() {
		this.setProperty(Grid.PROPERTY_ORIENTATION,new Integer(MyE2_Grid.ORIENTATION_HORIZONTAL));
		return this;
	}
	
	public RB_gstyle _wid(int width) {
		this.setProperty(Grid.PROPERTY_WIDTH,new Extent(width));
		return this;
	}
	
	public RB_gstyle _wid(Extent width) {
		this.setProperty(Grid.PROPERTY_WIDTH,width);
		return this;
	}
	
	public RB_gstyle _w100() {
		this.setProperty(Grid.PROPERTY_WIDTH,new Extent(99,Extent.PERCENT));
		return this;
	}

	
	
	/**
	 * 		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyleNOBorder.setProperty( Grid.PROPERTY_ORIENTATION, new Integer(MyE2_Grid.ORIENTATION_VERTICAL));
		oStyleNOBorder.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
	
	 */
	
	
}
