package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;

public class RB_cld extends ColumnLayoutData {
	public RB_cld() {
		super();
		this.setInsets(E2_INSETS.I(0,0,0,0));
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
	}

	

	/**
	 * werte von anderem GridLayoutdata-objekt holen
	 */
	public RB_cld _copyLayoutData(GridLayoutData ld) {
		this.setAlignment(ld.getAlignment());
		this.setBackground(ld.getBackground());
		this.setBackgroundImage(ld.getBackgroundImage());
		this.setInsets(ld.getInsets());
		return this;
	}
	
	
	
	/**
	 * 
	 * @param col  = Background-Color
	 * @return
	 */
	public RB_cld _col(Color col) {
		this.setBackground(col);
		return this;
	}

	public RB_cld _ins(Insets in) {
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
	public RB_cld _ins(int i_left, int i_top, int i_right, int i_bottom) {
		this.setInsets(E2_INSETS.I(i_left,i_top, i_right,i_bottom));
		return this;
	}
	
	
	/**
	 * Generates Insets(i_round,i_round,i_round,i_round)
	 * @param i_round
	 * @return
	 */
	public RB_cld _ins(int i_round) {
		this.setInsets(E2_INSETS.I(i_round,i_round, i_round,i_round));
		return this;
	}
	
	public RB_cld _al(Alignment al) {
		this.setAlignment(al);
		return this;
	}
	
	public RB_cld _left_top() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		return this;
	}
	public RB_cld _center_top() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.TOP));
		return this;
	}
	public RB_cld _right_top() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.TOP));
		return this;
	}
	
	public RB_cld _left_mid() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
		return this;
	}
	public RB_cld _center_mid() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		return this;
	}
	public RB_cld _right_mid() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		return this;
	}
	
	public RB_cld _left_bottom() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.BOTTOM));
		return this;
	}
	public RB_cld _center_bottom() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.BOTTOM));
		return this;
	}
	public RB_cld _right_bottom() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.BOTTOM));
		return this;
	}

	/**
	 * 
	 * @return copy of this
	 */
	public RB_cld _c() {
		RB_cld rueck = new RB_cld();
		rueck.setAlignment(this.getAlignment());
		rueck.setBackground(this.getBackground());
		rueck.setBackgroundImage(this.getBackgroundImage());
		rueck.setInsets(this.getInsets());
		return rueck;
	}

}
