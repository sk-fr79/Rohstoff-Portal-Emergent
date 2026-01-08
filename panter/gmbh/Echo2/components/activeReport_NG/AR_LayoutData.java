package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;

public class AR_LayoutData extends GridLayoutData {

	/**
	 * 
	 * @param columnspan
	 */
	public AR_LayoutData(int columnspan) {
		this(columnspan,null,null,null);
	}

	/**
	 * 
	 * @param columnspan
	 * @param colorDiffToBaseColor
	 */
//	public AR_LayoutData(int columnspan, int colorDiffToBaseColor) {
//		this(columnspan,null,null,colorDiffToBaseColor);
//	}

	
	/**
	 * 
	 * @param columnspan
	 * @param insets
	 * @param colorDiffToBaseColor
	 */
	public AR_LayoutData(int columnspan,Insets insets, int colorDiffToBaseColor) {
		this(columnspan,insets,null,colorDiffToBaseColor);
	}
	
	
	/**
	 * 
	 * @param columnspan
	 * @param insets
	 */
	public AR_LayoutData(int columnspan,Insets insets) {
		this(columnspan,insets,null,null);
	}

	
	/**
	 * 
	 * @param columnspan
	 * @param insets
	 * @param align
	 * @param colorBack
	 */
	public AR_LayoutData(int columnspan, Insets insets, Alignment align, Color colorBack) {
		super();
		this.setAlignment(align==null?new Alignment(Alignment.LEFT, Alignment.TOP):align);
		this.setInsets(insets==null?E2_INSETS.I(1,1,10,1):insets);
		this.setColumnSpan(columnspan);
		this.setBackground(colorBack==null?new E2_ColorBase():colorBack);
	}

	/**
	 * 
	 * @param columnspan
	 * @param insets
	 * @param align
	 * @param colorDiffToBaseColor
	 */
	public AR_LayoutData(int columnspan, Insets insets, Alignment align, int colorDiffToBaseColor) {
		super();
		this.setAlignment(align==null?new Alignment(Alignment.LEFT, Alignment.TOP):align);
		this.setInsets(insets==null?E2_INSETS.I(1,1,10,1):insets);
		this.setColumnSpan(columnspan);
		this.setBackground(new E2_ColorBase(colorDiffToBaseColor));
	}

	
	
}
