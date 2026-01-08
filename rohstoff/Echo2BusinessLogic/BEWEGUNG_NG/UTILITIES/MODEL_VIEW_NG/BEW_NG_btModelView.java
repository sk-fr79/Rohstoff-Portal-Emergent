package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW_NG;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class BEW_NG_btModelView extends BEW_NG_ModelView_bt {
	
	private String id_uf = null;
	
	public BEW_NG_btModelView(_TAB table, String p_id_uf) throws myException {
		super(table);
		this.id_uf = p_id_uf;
	}

	@Override
	public String get_id_table() {
		return this.id_uf;
	}

}
