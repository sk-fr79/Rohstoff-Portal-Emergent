package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_SelectField_DPI;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_SelectField_FileType;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_SelectField_ModulKenner;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_SelectField_ProgrammKenner;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_ComponentMap extends RB_ComponentMap {

	public SCAN_MASK_ComponentMap() throws myException {
		super();

		this.registerComponent(new RB_KF(SCANNER_SETTINGS.id_scanner_settings),	new RB_TextField(100));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.dpi),					new SCAN_SelectField_DPI(new RB_SQLField(SCANNER_SETTINGS.dpi), 200));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.filetype),				new SCAN_SelectField_FileType(new RB_SQLField(SCANNER_SETTINGS.filetype), 200));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.loop_scan),				new RB_CheckBox(SCANNER_SETTINGS.loop_scan));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.loop_timeout_seconds),	new RB_TextField(200));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.standort),				new RB_TextField(200));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.scanner_name),			new RB_TextField(200));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.module_kenner),			new SCAN_SelectField_ModulKenner(new RB_SQLField(SCANNER_SETTINGS.module_kenner)));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.beschreibung),			new RB_TextArea(400,4));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.programm_kenner),		new SCAN_SelectField_ProgrammKenner(new RB_SQLField(SCANNER_SETTINGS.programm_kenner)));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.scanner_aufruf1),		new RB_TextArea(400,4));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.scanner_aufruf2),		new RB_TextArea(400,4));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.scanner_aufruf3),		new RB_TextArea(400,4));
		this.registerComponent(new RB_KF(SCANNER_SETTINGS.scanner_aufruf4),		new RB_TextArea(400,4));
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		MyE2_MessageVector v = new MyE2_MessageVector();
		return v;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer,MASK_STATUS status) throws myException {
		
		
		return new MyE2_MessageVector();
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

}
