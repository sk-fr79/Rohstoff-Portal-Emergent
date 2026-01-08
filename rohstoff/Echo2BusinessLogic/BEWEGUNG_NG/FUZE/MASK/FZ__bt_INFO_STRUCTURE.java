package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW.BEW_ModelView_bt;


public class FZ__bt_INFO_STRUCTURE extends BEW_ModelView_bt {
	
	private IF_mapCollector_4_FZ_MaskModulContainer own_ll_cm_collector = null;

	public FZ__bt_INFO_STRUCTURE(IF_mapCollector_4_FZ_MaskModulContainer p_own_ll_cm_collector) throws myException {
		super(_TAB.bewegung_vektor);
		this.own_ll_cm_collector = p_own_ll_cm_collector;
	}


	@Override
	public String get_id_table() throws myException {
		String id = null;
		if (this.own_ll_cm_collector.get_map_vektor().getRbDataObjectActual().get_MyRECORD()!=null) {
			id = ((RECORD_BEWEGUNG_VEKTOR)this.own_ll_cm_collector.get_map_vektor().getRbDataObjectActual().get_MyRECORD()).ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor);
		}
		return id;
	}

}
