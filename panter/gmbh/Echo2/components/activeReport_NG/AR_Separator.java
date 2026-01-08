package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import echopointng.Separator;

public class AR_Separator extends Separator implements IF_AR_Component{

	private GridLayoutData gl = null;
	
	
	
	public AR_Separator(GridLayoutData p_gl) {
		super();
		this.gl = p_gl;
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
