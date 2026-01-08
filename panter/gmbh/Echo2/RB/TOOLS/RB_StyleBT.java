package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

public class RB_StyleBT extends MutableStyle {

	public RB_StyleBT() {
		super();
	}
	
	public RB_StyleBT _defaultText() {
		this.setProperty( Button.PROPERTY_BACKGROUND, new E2_ColorBase(-10)); 
		this.setProperty( Button.PROPERTY_DISABLED_BACKGROUND, new E2_ColorBase(-10)); 
		this.setProperty( Button.PROPERTY_FOREGROUND, Color.BLACK);
		this.setProperty( Button.PROPERTY_DISABLED_FOREGROUND, Color.DARKGRAY);
		this.setProperty( Button.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		this.setProperty( Button.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		this.setProperty( Button.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		this.setProperty( Button.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		this.setProperty( Button.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		this.setProperty( Button.PROPERTY_FONT, new E2_FontPlain());
		this.setProperty( Button.PROPERTY_LINE_WRAP,new Boolean(false));
		return this;
	}
	
	public RB_StyleBT _lw(Boolean b) {
		this.setProperty(Button.PROPERTY_LINE_WRAP,b);
		return this;
	}
	
	
	public RB_StyleBT _bord(Border  border) {
		this.setProperty(Button.PROPERTY_BORDER,border);
		return this;
	}

	public RB_StyleBT _bord_l_grey() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), Color.LIGHTGRAY, Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_black() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), Color.BLACK, Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_red() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), Color.RED, Border.STYLE_SOLID));
		return this;
	}

	
	public RB_StyleBT _bord_d_grey() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), Color.DARKGRAY, Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_light() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorLight(), Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_llight() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorLLight(), Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_dark() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorDark(), Border.STYLE_SOLID));
		return this;
	}

	public RB_StyleBT _bord_ddark() {
		this.setProperty(Button.PROPERTY_BORDER,new Border(new Extent(1), new E2_ColorDDark(), Border.STYLE_SOLID));
		return this;
	}

	
	public RB_StyleBT _ins(Insets  insets) {
		this.setProperty(Button.PROPERTY_INSETS,insets);
		return this;
	}


	public RB_StyleBT _wid(int width) {
		this.setProperty(Button.PROPERTY_WIDTH,new Extent(width));
		return this;
	}
	
	public RB_StyleBT _wid(Extent width) {
		this.setProperty(Button.PROPERTY_WIDTH,width);
		return this;
	}
	
	public RB_StyleBT _w100() {
		this.setProperty(Button.PROPERTY_WIDTH,new Extent(99,Extent.PERCENT));
		return this;
	}

	
	public RB_StyleBT _backCol(Color col) {
		this.setProperty(Button.PROPERTY_BACKGROUND,col);
		return this;
	}

	
	public RB_StyleBT _foreCol(Color col) {
		this.setProperty(Button.PROPERTY_FOREGROUND,col);
		return this;
	}

	
	public RB_StyleBT _foreColDisabled(Color col) {
		this.setProperty(Button.PROPERTY_DISABLED_FOREGROUND,col);
		return this;
	}

	
	public RB_StyleBT _font(Font font) {
		this.setProperty(Button.PROPERTY_FONT,font);
		return this;
	}

	public RB_StyleBT _alCenter() {
		this.setProperty(Button.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		return this;
	}

	public RB_StyleBT _alLeft() {
		this.setProperty(Button.PROPERTY_ALIGNMENT,new Alignment(Alignment.LEFT, Alignment.CENTER));
		return this;
	}
	
	public RB_StyleBT _alRight() {
		this.setProperty(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.RIGHT, Alignment.CENTER));
		return this;
	}
	
	
	
}
