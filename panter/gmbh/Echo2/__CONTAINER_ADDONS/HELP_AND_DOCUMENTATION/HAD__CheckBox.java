package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.activeReport_NG.IF_AR_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;

public class HAD__CheckBox extends MyE2_CheckBox  implements IF_AR_Component  {
	
	private RECORD_HILFETEXT  recHilfeText = null;
	private GridLayoutData  gl = null;

	public HAD__CheckBox(GridLayoutData  p_gl, RECORD_HILFETEXT p_recHilfeText, Font font) throws myException {
		super(p_recHilfeText.get_ID_HILFETEXT_cF()+" / "+p_recHilfeText.get_TICKETNUMMER_cF_NN("<Ticket>"));
		this.setFont(font);
		this.gl = p_gl;
		this.recHilfeText = p_recHilfeText;
		this.setLineWrap(true);
	}

	public RECORD_HILFETEXT get_RecHilfeText() {
		return recHilfeText;
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
