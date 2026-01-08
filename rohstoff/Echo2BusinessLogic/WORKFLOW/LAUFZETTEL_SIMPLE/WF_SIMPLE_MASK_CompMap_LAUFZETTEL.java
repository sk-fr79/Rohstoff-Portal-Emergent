/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 22.03.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BETA_MASK_DEF.EDITOR.RB_Mask_Grid_Editor;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeigeMultiLine;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_User;

/**
 * @author manfred
 * @date 22.03.2019
 *
 */
public class WF_SIMPLE_MASK_CompMap_LAUFZETTEL extends RB_ComponentMap {

	// zentrale hashmap fuer transport von infos

	private RB_TransportHashMap m_tpHashMap = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

	/**
	 * @author manfred
	 * @date 22.03.2019
	 *
	 */
	public WF_SIMPLE_MASK_CompMap_LAUFZETTEL(RB_TransportHashMap  p_tpHashMap) throws myException {
	       super();
	       
	       this.m_tpHashMap = p_tpHashMap;        
	       
	       this.registerComponent(LAUFZETTEL.abgeschlossen_am, new RB_date_selektor(80, true));
	       this.registerComponent(LAUFZETTEL.angelegt_am, new RB_date_selektor(80, true));
	       this.registerComponent(LAUFZETTEL.erzeugt_am, new RB_date_selektor(80, true));
	       this.registerComponent(LAUFZETTEL.erzeugt_von, new RB_TextAnzeige());
	       this.registerComponent(LAUFZETTEL.geaendert_von, new RB_TextAnzeige());
	       this.registerComponent(LAUFZETTEL.geloescht, new RB_cb());
	       this.registerComponent(LAUFZETTEL.id_laufzettel, new  RB_TextField()._w(100));
	       this.registerComponent(LAUFZETTEL.id_laufzettel_status, new RB_TextField(100));
	       this.registerComponent(LAUFZETTEL.id_mandant, new RB_TextAnzeige());
	       this.registerComponent(LAUFZETTEL.id_user_besitzer, new RB_TextField(100));
	       this.registerComponent(LAUFZETTEL.id_user_supervisor, new RB_TextField(100));
	       this.registerComponent(LAUFZETTEL.letzte_aenderung, new RB_date_selektor(80, true));
	       this.registerComponent(LAUFZETTEL.privat, new RB_cb());
	       this.registerComponent(LAUFZETTEL.text, new RB_TextArea(800,4));
	       this.registerComponent(LAUFZETTEL.close_all, new RB_cb(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.CLOSE_ALL.user_text()));
	       this.registerComponent(new RB_KF(LAUFZETTEL.id_user_abgeschlossen_von),    new RB_selField()._populate(new RecList21(_TAB.user)._fill(RecList21_User.getSqlExt_Default(true, true)),USER.name,_TAB.user.key(),true)._width(120)._fo_s_plus(0));
	       
	       // Anzeige auf dem Tab des Laufzettel-Eintrags beim bearbeiten (ReadOnly)
	       this.registerComponent (WF_SIMPLE_CONST.MASK_KEYS.LAUFZETTEL_TEXT_RO.key(), new RB_TextAnzeigeMultiLine(800)._rows(4)._setDisableEverytime());
	       this.registerComponent( WF_SIMPLE_CONST.MASK_KEYS_String.LAUFZETTEL_GRID_TASKS.key(), new WF_SIMPLE_MASK_SubgridEntries(this.m_tpHashMap)); 
	       
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#
	 * setUserdefinedMaskSettingBeforeLoad()
	 */
	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#maskSettings_do_After_Load()
	 */
	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#
	 * maskSettings_define_own_pre_settings(panter.gmbh.Echo2.RB.BASICS.
	 * RB_PreSettingsContainer,
	 * panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS)
	 */
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,
			MASK_STATUS status) throws myException {
		
//		preSettingsContainer.rb_set_enabled(LAUFZETTEL.text, false);
		
		if (status.equals(MASK_STATUS.NEW) || status.equals(MASK_STATUS.NEW_COPY)){
			
//			preSettingsContainer.rb_set_enabled(LAUFZETTEL.text, true);
			preSettingsContainer.rb_set_mandatoryField(LAUFZETTEL.text, true);
			    
			// Status ist wie im Laufzettel-Eintrag NEU
			String idStatus= (String)m_tpHashMap.getFromExtender(WF_SIMPLE_CONST.WF_SIMPLE_TransportExtender.DEFAULT_STATUS);
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL.id_laufzettel_status, bibALL.convertID2FormattedID(idStatus));
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL.id_user_besitzer, bibALL.get_ID_USER_FORMATTED());
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL.angelegt_am, bibALL.get_cDateNOW());
			preSettingsContainer.rb_set_defaultMaskValue(LAUFZETTEL.close_all, "Y");
		}
		
		
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#
	 * user_setting_change_EXCLUDE_HASHKEYS(java.util.Vector)
	 */
	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
		// TODO Auto-generated method stub

	}

}
