 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_TextFieldReadOnly;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class CON_MASK_ComponentMap extends RB_ComponentMap {
	
	public CON_MASK_ComponentMap() throws myException {
		this(null);
	}
	    
	
    public CON_MASK_ComponentMap(String idContainterTyp) throws myException {
        super();
        
        this.registerComponent(new RB_KF(CONTAINER.id_container),    	new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(CONTAINER.container_nr),    	new RB_TextField()._width(200));
        this.registerComponent(new RB_KF(CONTAINER.tara),    			new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(CONTAINER.id_containertyp), 	new CON_SelField_ContainterTyp(CONTAINER.id_container,400) );
        this.registerComponent(new RB_KF(CONTAINER.bemerkung),    	new RB_TextArea()._width(400)._rows(2));
        this.registerComponent(new RB_KF(CONTAINER.uvv_datum), 		new RB_date_selektor2(100, true));
        this.registerComponent(new RB_KF(CONTAINER.aktiv),			new RB_cb(S.ms("Aktiv"))._setWidth(100));
        this.registerComponent(new RB_KF(CONTAINER.tara_korrektur),    	new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(CONTAINER.datum_korrektur),	new RB_TextFieldReadOnly(100,20) );
       
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
		String _idContainerTyp =  ((CON_MASK_DataObjectCollector)(this.rb_get_belongs_to().rb_Actual_DataobjectCollector())).get_idContainterTyp();
		preSettingsContainer.rb_get(CONTAINER.id_containertyp).set_rb_Default(_idContainerTyp);
		
		preSettingsContainer.rb_get(CONTAINER.tara_korrektur).set_Enabled(false);
		
		return null;
	}
	
}
 
