package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;



public class HAD_comp_Label extends MyE2_Label implements IF_AR_Component {

	private GridLayoutData  gl = null;
	
	public HAD_comp_Label(GridLayoutData  _gl, String cText, Font oFont) {
		super(cText, oFont);
		this.gl=_gl;
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


