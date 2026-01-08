package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_BT_FUHRE_popup_export extends EXP_popup_menue_exporter {

	public FU_LIST_BT_FUHRE_popup_export(E2_NavigationList onavigationList)	{
		super(	onavigationList, 
				new MyE2_String("Fuhren exportieren in xls oder csv-Format (aktuelle Selektion)"),
				E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,
				"EXPORT_FUHREN_XLS",
				"EXPORT_FUHREN_CSV");
		
		this.get_v_append_recs_4_csv_export().add(new EXP_addOn_Lieferant());
		this.get_v_append_recs_4_csv_export().add(new EXP_addOn_Abnehmer());
		this.get_v_append_recs_4_csv_export().add(new EXP_addOn_Spedition());
		this.get_v_append_recs_4_csv_export().add(new EXP_addOn_Sorte());
	}
	
	private class EXP_addOn_Lieferant extends EXP_addOnRecords {

		public EXP_addOn_Lieferant() {
			super(_TAB.adresse, new MyE2_String("Startadresse"), "ADRESSE_START.");
		}

		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA_FUHRE)base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_start();
		}
		
	}

	
	private class EXP_addOn_Abnehmer extends EXP_addOnRecords {

		public EXP_addOn_Abnehmer() {
			super(_TAB.adresse, new MyE2_String("Zieladresse"), "ADRESSE_ZIEL.");
		}

		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA_FUHRE)base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_ziel();
		}
		
	}

	
	private class EXP_addOn_Spedition extends EXP_addOnRecords {

		public EXP_addOn_Spedition() {
			super(_TAB.adresse, new MyE2_String("Spedition"), "ADRESSE_SPED.");
		}

		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA_FUHRE)base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_spedition();
		}
		
	}

	
	private class EXP_addOn_Sorte extends EXP_addOnRecords {

		public EXP_addOn_Sorte() {
			super(_TAB.artikel, new MyE2_String("Sorte"), "ARTIKEL.");
		}

		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA_FUHRE)base_record_of_list).get_UP_RECORD_ARTIKEL_id_artikel();
		}
		
	}

	
}
