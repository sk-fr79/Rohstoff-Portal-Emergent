package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class AS_LIST_bt_popup_export extends EXP_popup_menue_exporter {

	public AS_LIST_bt_popup_export(E2_NavigationList p_navigationlist) {
		super(p_navigationlist);
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonEinheit());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonEinheitP());
		
		this.setToolTipText(new MyE2_String("Export Sortenliste").CTrans());
	}

	
	private class EXP_AddonEinheit extends EXP_addOnRecords {

		public EXP_AddonEinheit() {
			super(_TAB.einheit, new MyE2_String("Einheit Menge"),"EINH_MGE.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ARTIKEL) base_record_of_list).get_UP_RECORD_EINHEIT_id_einheit();
		}
	}
	
	private class EXP_AddonEinheitP extends EXP_addOnRecords {

		public EXP_AddonEinheitP() {
			super(_TAB.einheit, new MyE2_String("Einheit Preis"),"EINH_PREIS.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ARTIKEL) base_record_of_list).get_UP_RECORD_EINHEIT_id_einheit();
		}
	}
	
	
}
