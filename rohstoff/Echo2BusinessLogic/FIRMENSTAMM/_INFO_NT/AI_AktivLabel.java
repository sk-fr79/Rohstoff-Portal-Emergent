package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;

public class AI_AktivLabel extends MyE2_Label implements IF_AR_Component{

	private GridLayoutData gl = null;
	
	public AI_AktivLabel(boolean bAktiv, GridLayoutData p_gl) {
		super(E2_ResourceIcon.get_RI(bAktiv?"aktiv.png":"inaktiv.png"));
		this.gl = p_gl;
		this.setToolTipText(bAktiv?"Aktiv":"Inaktiv");
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
