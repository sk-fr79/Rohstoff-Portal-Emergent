package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class FS_BT_LIST_Export extends EXP_popup_menue_exporter
{

	public FS_BT_LIST_Export(E2_NavigationList onavigationList)
	{
		super(onavigationList);
		
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonFirmenInfo());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonLand());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonSprache());
		this.get_v_append_recs_4_csv_export().add(new EXP_AddonHaendler());
		
		this.setToolTipText(new MyE2_String("Export Adressenliste").CTrans());

		
	}
	
	private class EXP_AddonFirmenInfo extends EXP_addOnRecords {

		public EXP_AddonFirmenInfo() {
			super(_TAB.firmeninfo, new MyE2_String("Firmeninfo"),"FIRMENINFO.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ADRESSE) base_record_of_list).get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
		}
	}


	private class EXP_AddonLand extends EXP_addOnRecords {

		public EXP_AddonLand() {
			super(_TAB.land, new MyE2_String("Land"),"LAND.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ADRESSE) base_record_of_list).get_UP_RECORD_LAND_id_land();
		}
	}


	private class EXP_AddonSprache extends EXP_addOnRecords {

		public EXP_AddonSprache() {
			super(_TAB.sprache, new MyE2_String("Sprache"),"SPRACHE.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ADRESSE) base_record_of_list).get_UP_RECORD_SPRACHE_id_sprache();
		}
	}

	private class EXP_AddonHaendler extends EXP_addOnRecords {

		public EXP_AddonHaendler() {
			super(_TAB.user, new MyE2_String("Händler"),"HAENDLER.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_ADRESSE) base_record_of_list).get_UP_RECORD_USER_id_user();
		}
	}

	
}
