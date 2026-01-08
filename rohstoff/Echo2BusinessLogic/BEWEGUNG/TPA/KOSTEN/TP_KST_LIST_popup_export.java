package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_popup_export extends EXP_popup_menue_exporter {
	
	public TP_KST_LIST_popup_export(E2_NavigationList p_navigationlist) {
		super(p_navigationlist);
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonTPA());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonSped());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonFuhre());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonLadeStation());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonAbladeStation());
		this.setToolTipText(new MyE2_String("Export Transportposition").CTrans());
	}

	
	private class EXP_AddonTPA extends EXP_addOnRecords {

		public EXP_AddonTPA() {
			super(_TAB.vkopf_tpa, new MyE2_String("Transportauftrag"),"TPA.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA) base_record_of_list).get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa();
		}
	}
	
	
	
	private class EXP_AddonSped extends EXP_addOnRecords {

		public EXP_AddonSped() {
			super(_TAB.adresse, new MyE2_String("Speditions-Adresse"),"ADR_SPED.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_VPOS_TPA) base_record_of_list).get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_UP_RECORD_ADRESSE_id_adresse();
		}
	}
	
	
	private class EXP_AddonFuhre extends EXP_addOnRecords {

		public EXP_AddonFuhre() {
			super(_TAB.vpos_tpa_fuhre, new MyE2_String("Fuhre"),"FUHRE.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			RECORD_VPOS_TPA 		vpos_tpa= 	(RECORD_VPOS_TPA) base_record_of_list;
			RECORD_VPOS_TPA_FUHRE 	fu 		= 	vpos_tpa.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
			
			return fu;
		}
	}
	
	private class EXP_AddonLadeStation extends EXP_addOnRecords {

		public EXP_AddonLadeStation() {
			super(_TAB.adresse, new MyE2_String("Startstation"),"LADESTAT.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			RECORD_VPOS_TPA 		vpos_tpa= 	(RECORD_VPOS_TPA) base_record_of_list;
			RECORD_VPOS_TPA_FUHRE 	fu 		= 	vpos_tpa.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
			return fu.get_UP_RECORD_ADRESSE_id_adresse_lager_start();
		}
	}

	private class EXP_AddonAbladeStation extends EXP_addOnRecords {

		public EXP_AddonAbladeStation() {
			super(_TAB.adresse, new MyE2_String("Zielstation"),"ABLADESTAT.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			RECORD_VPOS_TPA 		vpos_tpa= 	(RECORD_VPOS_TPA) base_record_of_list;
			RECORD_VPOS_TPA_FUHRE 	fu 		= 	vpos_tpa.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
			return fu.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel();
		}
	}

	
	
}
