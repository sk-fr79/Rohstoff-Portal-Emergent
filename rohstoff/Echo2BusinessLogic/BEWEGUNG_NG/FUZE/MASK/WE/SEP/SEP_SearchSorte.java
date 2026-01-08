package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;


public class SEP_SearchSorte extends RB_HL_SearchArtbez {

	public SEP_SearchSorte() throws myException {
		super();
		this.get_tf_search_input()._size_and_font(14, 100,  new E2_FontPlain());
		this.mark_Neutral();
		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ALL_ARTBEZ);
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.KEY_SAVE_SORT_WE_SEPARATION_POPUP);
		this.set_show_bt_add_all_found_artikel_bez(false);
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownContainer();
	}

	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv) throws myException {
		return null;
	}


	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ARTIKEL_BEZ)p_result_record).ufs(ARTIKEL_BEZ.id_artikel_bez);
	}
	
	private class ownContainer extends E2_BasicModuleContainer {
	}

	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		return null;
	}
	
	
}
