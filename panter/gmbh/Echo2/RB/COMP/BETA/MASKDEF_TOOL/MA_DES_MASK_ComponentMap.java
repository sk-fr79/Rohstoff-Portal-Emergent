package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_MASK_ComponentMap extends RB_ComponentMap {
    public MA_DES_MASK_ComponentMap() throws myException {
        super();
        
        this.registerComponent(MASK_DEF.id_mask_def,   	new RB_TextField()._width(200));

        this.registerComponent(MASK_DEF.tablename,    	new MA_DES_COMP_Selectfield_Tablename(MASK_DEF.tablename));
        this.registerComponent(MASK_DEF.maskname,    		new MA_DES_COMP_Selectfield_Maskname(MASK_DEF.maskname));
        
        this.registerComponent(MASK_DEF.maskname_long,  	new RB_TextArea()._width(400)._rows(5));
        
        this.registerComponent(MASK_DEF.nb_of_cols,    	new RB_TextField()._width(200));
        this.registerComponent(MASK_DEF.pixel_width,    	new RB_TextField()._width(200));
        
        this.registerComponent(MASK_DEF.left_offset,    	new RB_TextField()._width(200));
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
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		preSettingsContainer.rb_get(MASK_DEF.tablename).set_MustField(true);
		preSettingsContainer.rb_get(MASK_DEF.maskname).set_MustField(true);
		preSettingsContainer.rb_get(MASK_DEF.nb_of_cols).set_MustField(true);
		preSettingsContainer.rb_get(MASK_DEF.pixel_width).set_MustField(true);
		
		if(status == MASK_STATUS.NEW) {
			preSettingsContainer.rb_get(MASK_DEF.nb_of_cols).set_rb_Default("1");
			preSettingsContainer.rb_get(MASK_DEF.left_offset).set_rb_Default("0");
			preSettingsContainer.rb_get(MASK_DEF.pixel_width).set_rb_Default("30");
		}
		
    	return mv;
	}
}
 
