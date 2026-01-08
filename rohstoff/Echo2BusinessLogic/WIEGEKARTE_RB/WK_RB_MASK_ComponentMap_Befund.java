/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB
 * @author manfred
 * @date 02.07.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_BefundungUser;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_RB_MC_PrintHandling;

/**
 * @author manfred
 * @date 02.07.2020
 *
 */
public class WK_RB_MASK_ComponentMap_Befund extends RB_ComponentMap {

	private RB_TransportHashMap   m_tpHashMap = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public WK_RB_MASK_ComponentMap_Befund(RB_TransportHashMap m_tpHashMap) throws myException {
		super();
		this.m_tpHashMap = m_tpHashMap;
		
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.analyse), new RB_cb(new MyE2_String("Analyse tätigen")));
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.id_wiegekarte), new RB_TextAnzeige());
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.id_wiegekarte_befund), new RB_TextAnzeige());
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.id_wiegekarte_user_befund), new WK_RB_SelField_BefundungUser(m_tpHashMap)._width(400));
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.naesseprobe), new RB_cb(new MyE2_String("Nässeprobe")));
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.sortierung), new RB_cb(new MyE2_String("Sortierung durchführen")));
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.bemerkung), new RB_TextArea(400,5));
		this.registerComponent(new RB_KF(WIEGEKARTE_BEFUND.gedruckt_am), new RB_lab());
		
		this.registerComponent(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BEFUNDUNG.key()		
				, new E2_Button()._setShapeStandardTextButton()._image("printer.png", true)._txt_trans(new MyE2_String("Befundungsauftrag").CTrans())
				._aaa((o)->{
					MyE2_MessageVector mv = bibMSG.getNewMV();
					// Wiegekarte speichern..
					
					
					// dann drucken
					WK_RB_MC_PrintHandling mcPrint = new WK_RB_MC_PrintHandling(WK_RB_MASK_ComponentMap_Befund.this, mv);
					mcPrint.printBefundung() ;
					bibMSG.add_MESSAGE(mv);
				}));
	}
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#setUserdefinedMaskSettingBeforeLoad()
	 */
	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#maskSettings_do_After_Load()
	 */
	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#maskSettings_define_own_pre_settings(panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer, panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS)
	 */
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
			if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        } 
        return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#user_setting_change_EXCLUDE_HASHKEYS(java.util.Vector)
	 */
	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {


	}

}
