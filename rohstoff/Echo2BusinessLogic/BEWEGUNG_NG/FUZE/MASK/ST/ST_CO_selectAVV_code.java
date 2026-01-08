package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SEARCH_AVV_CODES;


public class ST_CO_selectAVV_code extends FZ_SEARCH_AVV_CODES {

	public ST_CO_selectAVV_code() throws myException {
		super();
		this._setSize(40, 250, 20, 20);
		this.get_tf_4_anzeige()._w(250);
	}

	@Override
	public RECORD_ARTIKEL get_actual_artikel() throws myException {
		RB_SearchFieldSaveable sf = (RB_SearchFieldSaveable)this._find_component_in_neighborhood(BEWEGUNG_ATOM.id_artikel_bez);
		
		if (sf != null) {
			if (sf.is_search_done_correct()) {
				MyLong l = new MyLong(sf.get_tf_search_input().getText());
				if (l.get_bOK()) {
					RECORD_ARTIKEL_BEZ  r = new RECORD_ARTIKEL_BEZ(l.get_lValue());
					return r.get_UP_RECORD_ARTIKEL_id_artikel();
				}
			}
		}
		return null;
	}

}
