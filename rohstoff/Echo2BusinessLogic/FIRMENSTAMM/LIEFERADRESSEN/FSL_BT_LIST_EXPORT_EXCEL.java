package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class FSL_BT_LIST_EXPORT_EXCEL extends EXP_popup_menue_exporter {

	public FSL_BT_LIST_EXPORT_EXCEL(E2_NavigationList onavigationList) 	{
		super(onavigationList);
		
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonFirmenAdresse());

		
		this.setToolTipText(new MyE2_String("Export der Lageradresse").CTrans());
	}
	
	private class EXP_AddonFirmenAdresse extends EXP_addOnRecords {

		public EXP_AddonFirmenAdresse() {
			super(_TAB.adresse, new MyE2_String("Firma zum Lager"),"FIRMA.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			RECORD_ADRESSE adr_lager = ((RECORD_ADRESSE) base_record_of_list);
			return adr_lager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}
	}


	
}
