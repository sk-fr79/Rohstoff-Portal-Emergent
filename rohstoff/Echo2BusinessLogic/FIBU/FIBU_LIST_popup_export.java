package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_popup_export extends EXP_popup_menue_exporter
{

	public FIBU_LIST_popup_export(E2_NavigationList onavigationList)
	{
		super(onavigationList);
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonVKOPF());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonADRESSE());
		
	}
	
	private class EXP_AddonVKOPF extends EXP_addOnRecords {

		public EXP_AddonVKOPF() {
			super(_TAB.vkopf_rg, new MyE2_String("Rechnung/Gutschrift-Kopf"),"RG.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_FIBU) base_record_of_list).get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
		}
	}
	
	private class EXP_AddonADRESSE extends EXP_addOnRecords {

		public EXP_AddonADRESSE() {
			super(_TAB.adresse, new MyE2_String("Adresse"),"ADRESSE.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_FIBU) base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse();
		}
	}

}
