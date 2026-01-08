 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
   
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.indep.exceptions.myException;
  
public class ZT_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public ZT_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.aktiv),    				new RB_cb());
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.bm_nummer),    			new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.bm_text),    				new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.id_zolltarifnummer),    	new RB_lab());
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.letzter_import),    		new RB_date_selektor(100,true));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.nummer),    				new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.reverse_charge),    		new RB_cb());
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text1),   					new RB_TextArea()._setRows(4)._width(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text2),    				new RB_TextArea()._setRows(4)._width(400));
        this.registerComponent(new RB_KF(ZOLLTARIFNUMMER.text3),    				new RB_TextArea()._setRows(4)._width(400));
        

 
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
 
 
