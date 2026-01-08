/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 05.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_MASK_DaughterListForMotherMask;

/**
 * @author sebastien
 * @date 05.12.2018
 *
 */
public class LH_MASK_ComponentMap extends RB_ComponentMap {
	//zentrale hashmap fuer transport von infos

	private RB_TransportHashMap   m_tpHashMap = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public LH_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		this.m_tpHashMap = p_tpHashMap;        

		this.registerComponent(LAGER_BOX.id_lager_box,  	new RB_TextField(400));
		this.registerComponent(LAGER_BOX.beschreibung,  	new RB_TextArea(400,3));
		this.registerComponent(LAGER_BOX.boxnummer,    		new RB_TextField(400));
		this.registerComponent(LAGER_BOX.is_default_box,    new RB_CheckBox(LAGER_BOX.is_default_box));
		
		this.registerComponent(new LH_KEY_ContainerInlay(), new LH_P_MASK_DaughterListForMotherMask(m_tpHashMap,true));

		this.registerSetterValidators(LAGER_BOX.id_lager_box, new LH_MASK_Box_Set_And_Valid(m_tpHashMap));
	}

	
	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	
	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,
			MASK_STATUS status) throws myException {
//		preSettingsContainer.rb_get(LAGER_BOX.id_lager_box).set_Enabled(false);
//		preSettingsContainer.rb_get(LAGER_BOX.beschreibung).set_Enabled(false);
//		preSettingsContainer.rb_get(LAGER_BOX.boxnummer).set_Enabled(false);
		return null;
	}


	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}



}
