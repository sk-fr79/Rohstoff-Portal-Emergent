package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import java.util.Vector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.indep.exceptions.myException;


public class ZOL_MASK_ComponentMap extends RB_ComponentMap {
    public ZOL_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.aktiv),    			new RB_CheckBox(ZOLLTARIFNUMMER.aktiv));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.bm_nummer),    		new RB_TextField(100));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.bm_text),    		new RB_TextField(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.id_zolltarifnummer), new RB_TextField(100));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.letzter_import),    	new RB_TextField(100));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.nummer),    			new RB_TextField(100));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.reverse_charge),    	new RB_CheckBox(ZOLLTARIFNUMMER.reverse_charge));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text1),    			new RB_TextField(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text2),    			new RB_TextField(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text3),    			new RB_TextField(400));
    }
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
        return null;
    }
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
        return null;
    }
    
    
    /* (non-Javadoc)
     * @see panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap#maskSettings_define_own_pre_settings(panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer, panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS)
     */
    @Override
    public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
    	// TODO Auto-generated method stub
    	// 	return null;
    	return new MyE2_MessageVector();
    }

    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
}
 
