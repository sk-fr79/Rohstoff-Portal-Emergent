package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.MASK;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_CONST;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_MASK_ComponentMap extends RB_ComponentMap {

	public DRUCK_MASK_ComponentMap() throws myException {
		super();

		this.registerComponent(new RB_KF(DRUCKER.aktiv),    							new RB_CheckBox(DRUCKER.aktiv));
		this.registerComponent(new RB_KF(DRUCKER.beschreibung),    					new RB_TextField(400));
		this.registerComponent(new RB_KF(DRUCKER.id_drucker),    						new RB_TextField(100));
		this.registerComponent(new RB_KF(DRUCKER.name),    							new RB_TextField(400));
		this.registerComponent(new RB_KF(DRUCKER.standort),    						new RB_TextArea(400,5));

		this.registerComponent(new RB_KF(DRUCKER.direct_druck_befehl),    			new RB_TextArea(400,5));
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
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {

	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,	MASK_STATUS status) throws myException {
		if(status == MASK_STATUS.NEW || status == MASK_STATUS.EDIT){
			preSettingsContainer.rb_get(DRUCKER.name).set_MustField(true);
			preSettingsContainer.rb_get(DRUCKER.standort).set_MustField(true);
			preSettingsContainer.rb_get(DRUCKER.direct_druck_befehl).set_MustField(true);
			preSettingsContainer.rb_get(DRUCKER.beschreibung).set_MustField(true);
		}
		
		if(status == MASK_STATUS.NEW){
			preSettingsContainer.rb_get(DRUCKER.direct_druck_befehl).set_rb_Default("lp -d");
		}
		return null;
	}
}

