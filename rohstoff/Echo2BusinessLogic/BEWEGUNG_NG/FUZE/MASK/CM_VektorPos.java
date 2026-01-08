package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class CM_VektorPos extends RB_ComponentMap {

	public CM_VektorPos() throws myException {
		super();

		this.rb_INIT_4_DB(_TAB.bewegung_vektor_pos);
		
		this.registerComponent(BEWEGUNG_VEKTOR_POS.pos_typ, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos,new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));

	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

}
