package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class AR_Grid extends MyE2_Grid {

	private Color   colorBase = new E2_ColorBase();

	public AR_Grid() {
		super();
	}

	public AR_Grid(int iAbstand, Component... components) {
		super(iAbstand, components);
	}

	public AR_Grid(int iNumCols, int iBorderSize) {
		super(iNumCols, iBorderSize);
	}

	public AR_Grid(int iSpalten, MutableStyle oStyle, GridLayoutData gl,Component... components) {
		super(iSpalten, oStyle, gl, components);
	}

	public AR_Grid(int iNumCols, MutableStyle oStyle) {
		super(iNumCols, oStyle);
	}

	public AR_Grid(int iNumCols) {
		super(iNumCols);
	}

	public AR_Grid(int[] iSpalten, int iBorderSize) {
		super(iSpalten, iBorderSize);
	}

	public AR_Grid(int[] iSpalten, MutableStyle oStyle,	boolean bScaleRowsTo100Percent) {
		super(iSpalten, oStyle, bScaleRowsTo100Percent);
	}

	public AR_Grid(int[] iSpalten, MutableStyle oStyle) {
		super(iSpalten, oStyle);
	}

	public AR_Grid(MutableStyle oStyle) {
		super(oStyle);
	}

	public Color getColorBase() {
		return this.colorBase;
	}

	public void setColorBase(Color p_colorBase) {
		this.colorBase = p_colorBase;
	}
	
	
	
	
}
