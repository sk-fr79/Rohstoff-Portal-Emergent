package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_STATUS_KUNDE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_BT_LIST_EXCEL extends EXP_popup_menue_exporter
{

	public STATKD_BT_LIST_EXCEL(E2_NavigationList onavigationList)
	{
		super(onavigationList);
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonAdresse());

		this.setToolTipText( new MyE2_String("Kundenbewertung-Historie-Auszug").CTrans());
		
	}
	
	private class EXP_AddonAdresse extends EXP_addOnRecords {

		public EXP_AddonAdresse() {
			super(_TAB.adresse, new MyE2_String("Firma"),"ADRESSE.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			RECORD_STATUS_KUNDE rec_s = ((RECORD_STATUS_KUNDE) base_record_of_list);
			
			if (S.isFull(rec_s.get_ID_ADRESSE_cUF_NN(""))) {
				return new RECORD_ADRESSE(rec_s.get_ID_ADRESSE_cUF_NN(""));
			}
			return null;
		}
	}

}
