 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
 
 
public class TAXG_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_MASK_ComponentMap(PARAMHASH  p_params) throws myException {
       super();
       this.params = p_params;        
       
         
        this.registerComponent(new RB_KF(TAX_GROUP.aktiv),    new RB_cb());
        this.registerComponent(new RB_KF(TAX_GROUP.id_tax_group),    new RB_lab());
        this.registerComponent(new RB_KF(TAX_GROUP.kurztext),    new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(TAX_GROUP.langtext),    new RB_TextArea()._width(400)._rows(5));
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
 
