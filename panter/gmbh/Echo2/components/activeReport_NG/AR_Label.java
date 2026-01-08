package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;


public class AR_Label extends MyE2_Label implements IF_AR_Component{

	private GridLayoutData gl = null;
	
	/**
	 * 
	 * @param layoutData
	 * @param cText
	 * @param style
	 */
	public AR_Label(GridLayoutData layoutData, Object cText, MutableStyle style) throws myException {
		super(cText);
		
		this.gl = layoutData;
		if (this.gl==null) {
			throw new myException(this,"AR_Label MUST have an GridLayoutData!");
		}

		
		if (style!=null) {
			this.setStyle(style);
		}

		this.setLineWrap(true);
		
	}

	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}
	
	
	
	
}
