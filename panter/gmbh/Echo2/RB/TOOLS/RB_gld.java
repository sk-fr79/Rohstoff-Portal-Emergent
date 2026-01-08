package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.BasicInterfaces.IF_Color;

public class RB_gld extends GridLayoutData implements IF_Color<RB_gld> {
	public RB_gld() {
		super();
		this.setInsets(E2_INSETS.I(0,0,0,0));
		this.setColumnSpan(1);
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
	}

	public RB_gld _span(int ispan) {
		this.setColumnSpan(ispan);
		return this;
	}
	
	/**
	 * set rowspan
	 * @param i_rowspan
	 * @return
	 */
	public RB_gld _span_r(int i_rowspan) {
		this.setRowSpan(i_rowspan);
		return this;
	}
	

	/**
	 * werte von anderem GridLayoutdata-objekt holen
	 */
	public RB_gld _copyLayoutData(GridLayoutData ld) {
		this.setAlignment(ld.getAlignment());
		this.setBackground(ld.getBackground());
		this.setBackgroundImage(ld.getBackgroundImage());
		this.setColumnSpan(ld.getColumnSpan());
		this.setInsets(ld.getInsets());
		this.setRowSpan(ld.getRowSpan());
		return this;
	}
	
	/**
	 * werte von anderem Layoutdata-objekt holen
	 */
	public RB_gld _copyLayoutDataSettings(LayoutData ld) {
		if (ld != null) {
			if (ld instanceof GridLayoutData) {
				return this._copyLayoutData((GridLayoutData)ld);
			} else if (ld instanceof ColumnLayoutData) {
				ColumnLayoutData cld = (ColumnLayoutData)ld;
				this.setAlignment(cld.getAlignment());
				this.setBackground(cld.getBackground());
				this.setBackgroundImage(cld.getBackgroundImage());
				this.setInsets(cld.getInsets());
			} else if (ld instanceof RowLayoutData) {
				RowLayoutData rld = (RowLayoutData)ld;
				this.setAlignment(rld.getAlignment());
				this.setBackground(rld.getBackground());
				this.setBackgroundImage(rld.getBackgroundImage());
				this.setInsets(rld.getInsets());
			}
		}
		return this;
	}

	
	/**
	 * 
	 * @param col  = Background-Color
	 * @return
	 */
	public RB_gld _col(Color col) {
		this.setBackground(col);
		return this;
	}

	public RB_gld _ins(Insets in) {
		this.setInsets(in);
		return this;
	}
	
	
	/**
	 * INSETS
	 * @param i_left
	 * @param i_top
	 * @param i_right
	 * @param i_bottom
	 * @return
	 */
	public RB_gld _ins(int i_left, int i_top, int i_right, int i_bottom) {
		this.setInsets(E2_INSETS.I(i_left,i_top, i_right,i_bottom));
		return this;
	}
	
	
	/**
	 * Generates Insets(i_round,i_round,i_round,i_round)
	 * @param i_round
	 * @return
	 */
	public RB_gld _ins(int i_round) {
		this.setInsets(E2_INSETS.I(i_round,i_round, i_round,i_round));
		return this;
	}
	
	public RB_gld _al(Alignment al) {
		this.setAlignment(al);
		return this;
	}
	
	public RB_gld _left_top() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		return this;
	}
	public RB_gld _center_top() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.TOP));
		return this;
	}
	public RB_gld _right_top() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.TOP));
		return this;
	}
	
	public RB_gld _left_mid() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
		return this;
	}
	public RB_gld _center_mid() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		return this;
	}
	public RB_gld _right_mid() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		return this;
	}
	
	public RB_gld _left_bottom() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.BOTTOM));
		return this;
	}
	public RB_gld _center_bottom() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.BOTTOM));
		return this;
	}
	public RB_gld _right_bottom() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.BOTTOM));
		return this;
	}

	/**
	 * 
	 * @return copy of this
	 */
	public RB_gld _c() {
		RB_gld rueck = new RB_gld();
		rueck.setAlignment(this.getAlignment());
		rueck.setBackground(this.getBackground());
		rueck.setBackgroundImage(this.getBackgroundImage());
		rueck.setColumnSpan(this.getColumnSpan());
		rueck.setInsets(this.getInsets());
		rueck.setRowSpan(this.getRowSpan());
		return rueck;
	}

	
	
	
}
