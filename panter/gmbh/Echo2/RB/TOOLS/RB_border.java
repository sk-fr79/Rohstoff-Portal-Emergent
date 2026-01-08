package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;


public class RB_border extends Border {

	private int   size = 1;
	private Color col = 		Color.BLACK;
	private int   borderstyle = Border.STYLE_SOLID;
	
	public RB_border() {
		this(1, Color.BLACK, Border.STYLE_SOLID);
	}
	
	
	public RB_border(int sizePx, Color color, int style) {
		super(sizePx,color, style);
		this.size = sizePx;
		this.col = color;
		this.borderstyle = style;
	}

	public RB_border _size(int p_size) {
		this.size=p_size;
		return new RB_border(this.size, this.col, this.borderstyle);
	}
	
	public RB_border _col(Color p_col) {
		this.col=p_col;
		return new RB_border(this.size, this.col, this.borderstyle);
	}


}
