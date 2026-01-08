package panter.gmbh.Echo2.ListAndMask.List.Export;

import java.util.Vector;

import panter.gmbh.Echo2.UserSettings.E2_Usersetting_Vector_of_Strings;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.LeftRightSelect.LR_CB;
import panter.gmbh.indep.exceptions.myException;

public class EXP_UserSettingFieldList4Export extends E2_Usersetting_Vector_of_Strings {

	public EXP_UserSettingFieldList4Export(EXP_bt_export_CSV_pre_selections_popupWindow export_popup) {
		super(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SETTIN_EXPORT_NAVILIST_TO_CSV.name(),"@",export_popup.generate_module_key_4_save_settings());
	}

	
	public void save_vektor_4_export(Vector<LR_CB<EXP_Field>> v_CB_selected) throws myException {
		Vector<String> v_save = new Vector<>();
		
		for (LR_CB<EXP_Field> cb: v_CB_selected) {
			v_save.add(cb.get_save_key());
		}
		this.STORE_Vector(v_save);
	}


	
	/**
	 * aus der speicherung den rechten (export-vektor) restaurieren
	 * @param v_CB_alle_fields
	 * @return
	 * @throws myException
	 */
	public Vector<LR_CB<EXP_Field>> restore_vektor_4_export(Vector<LR_CB<EXP_Field>> v_CB_alle_fields) throws myException {
		
		Vector<String> v_rest_button_keys = this.READ_Vector();
		Vector<LR_CB<EXP_Field>> v_found_cbs = new Vector<>();
		for (LR_CB<EXP_Field> cb :v_CB_alle_fields) {
			cb.setSelected(false);
		}
		
		for (String key: v_rest_button_keys) {
			for (LR_CB<EXP_Field> cb: v_CB_alle_fields) {
				if (cb.get_save_key().equals(key)) {
					LR_CB<EXP_Field> copy = cb.get_copy(true);
					copy.setSelected(true);
					v_found_cbs.add(cb.get_copy(true));
					cb.setSelected(true);
				}
			}
		}
		
		return v_found_cbs;
	}
	
	
}
