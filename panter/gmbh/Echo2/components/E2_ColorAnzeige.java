package panter.gmbh.Echo2.components;

import panter.gmbh.indep.MyInteger;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;

public class E2_ColorAnzeige extends MyE2_Grid {

	private Color color = null;
	
	public E2_ColorAnzeige(String c_red, String c_green, String c_blue, int iWidth, int iHeight, Color BorderColor) {
		super();
		this.setSize(1);
		if (BorderColor!=null) {
			this.setStyle(MyE2_Grid.STYLE_GRID_BORDER(BorderColor));
		} else {
			this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		}
		
		Integer i_r = this.checkColor(c_red);
		Integer i_g = this.checkColor(c_green);
		Integer i_b = this.checkColor(c_blue);
		
		this.removeAll();
		this.add(new MyE2_Label("   "));
		this.setRowHeight(0, new Extent(iHeight));
		this.setColumnWidth(0, new Extent(iWidth));
		this.setBackground(null);
		this.color=null;
		if (i_r!=null && i_g!=null && i_b!=null) {
			this.color=new Color(i_r, i_g, i_b);
		}
		this.setBackground(this.color);
	}

	
	
	private Integer checkColor(String wert) {
		Integer i_rueck = null;
		MyInteger i_test = new MyInteger(wert);
		if (i_test.get_bOK()) {
			i_rueck = i_test.get_oInteger();
			
			if (i_rueck.intValue()<0 || i_rueck.intValue()>255) {
				i_rueck = null;
			}
		}
		
		return i_rueck;
	}



	public Color get_Color() {
		return this.color;
	}

}
