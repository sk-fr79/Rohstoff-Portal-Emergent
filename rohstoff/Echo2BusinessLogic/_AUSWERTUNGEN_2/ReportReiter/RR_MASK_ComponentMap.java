 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;
public class RR_MASK_ComponentMap extends RB_ComponentMap {
    public RR_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(new RB_KF(REPORT_REITER.id_report_reiter),    new RB_TextField_old(REPORT_REITER.id_report_reiter,80));
        this.registerComponent(new RB_KF(REPORT_REITER.reihenfolge),    new RB_TextField_old(REPORT_REITER.reihenfolge,80));
        this.registerComponent(new RB_KF(REPORT_REITER.reitername),    new RB_TextField_old(REPORT_REITER.reitername,250));
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
    public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer,MASK_STATUS status) throws myException {
    	SurfaceSettingsContainer.rb_get(new RB_KF(REPORT_REITER.reihenfolge)).set_MustField(true);
    	SurfaceSettingsContainer.rb_get(new RB_KF(REPORT_REITER.reitername)).set_MustField(true);
    	return new MyE2_MessageVector();
    }
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
}
 
