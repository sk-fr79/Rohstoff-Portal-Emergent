package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.indep.exceptions.myException;


public class REP_VER_MASK_ComponentMap extends RB_ComponentMap {

	public REP_VER_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		RB_HighLevel_SelectFieldUser user_comp 	= new RB_HighLevel_SelectFieldUser(REPORT_LOG.report_druck_von, true, true,new Extent(300), ENUM_USER_TYP.AKTIV);
		
		this.registerComponent(REPORT_LOG.id_report_log,   		new RB_lab());
		this.registerComponent(REPORT_LOG.report_datei_name, 	new RB_TextField(300));
		this.registerComponent(REPORT_LOG.report_druck_am,   	new REP_VER_MASK_DruckDatumUndZeit_Anzeige());
//		this.registerComponent(REPORT_LOG.report_titel,    		new RB_TextField(300));

		this.registerComponent(REPORT_LOG.report_druck_von,  	user_comp);
		this.registerComponent(REPORT_LOG.report_jasperfile,    new RB_TextField(300));
		this.registerComponent(REPORT_LOG.report_weg,   		new REP_VER_MASK_DruckTyp_Anzeige());

		this.registerComponent(REP_VER_CONST.REP_VER_MASK_COMPONENT.PARAMETER_DETAIL.fk(), new REP_VER_MASK_DaughterParameter());
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
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		return null;
	}
}

