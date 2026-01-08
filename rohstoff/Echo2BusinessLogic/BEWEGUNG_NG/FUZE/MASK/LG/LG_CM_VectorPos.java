 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_small;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;


public class LG_CM_VectorPos extends RB_ComponentMap {
	
	
	private ENUM_VEKTORPOS_TYP pos_typ = null;
    
	public LG_CM_VectorPos(ENUM_VEKTORPOS_TYP typ) throws myException {
        super();
        
        this.pos_typ = typ;
        this.rb_INIT_4_DB(_TAB.bewegung_vektor_pos);
        
    	this.registerComponent(BEWEGUNG_VEKTOR_POS.pos_typ, 				new RB_TextField_small(100));
		this.registerComponent(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos,new RB_TextField_small(100));
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
    	if (status.isStatusNew()) {
    		SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_VEKTOR_POS.posnr).rb_set_formated_value_forced_at_save(this.pos_typ==ENUM_VEKTORPOS_TYP.LG_MAIN?"1":"4");
    		SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_VEKTOR_POS.pos_typ).rb_set_formated_value_forced_at_save(this.pos_typ.db_val());
    	}
        return new MyE2_MessageVector();
    }
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
}
 
