 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
   
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelFieldUser;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
public class HELP2_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public HELP2_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
       
        SEL selUser = new SEL("*").FROM(_TAB.user).WHERE(new vgl_YN(USER.aktiv,true)).AND(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP(USER.name1);
        SEL selDev =  new SEL("*").FROM(_TAB.user).WHERE(new vgl_YN(USER.aktiv,true)).AND(new vgl_YN(USER.developer, true)).AND(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP(USER.name1);
       
        this.registerComponent(new RB_KF(HILFETEXT.abschlussdatum),    	new RB_date_selektor(100,true));
        this.registerComponent(new RB_KF(HILFETEXT.hilfetext),    		new RB_TextArea()._width(HELP2_CONST.HELP2_NUM_CONST.WIDTH_LONGTEXT.getValue())._rows(20));
        this.registerComponent(new RB_KF(HILFETEXT.info_developer),    	new RB_TextArea()._width(HELP2_CONST.HELP2_NUM_CONST.WIDTH_LONGTEXT.getValue())._rows(20));
        this.registerComponent(new RB_KF(HILFETEXT.id_hilfetext),    	new RB_lab());
        this.registerComponent(new RB_KF(HILFETEXT.id_user_bearbeiter), new RB_SelFieldUser(new SqlStringExtended(selDev.s()),true)._width(200));
        this.registerComponent(new RB_KF(HILFETEXT.id_user_ursprung),  	new RB_SelFieldUser(new SqlStringExtended(selUser.s()),true)._width(200));
        
        SEL selVersion = new SEL("*").FROM(_TAB.version).ORDERDOWN(VERSION.version_code);
        
//        this.registerComponent(new RB_KF(HILFETEXT.id_version),    		new RB_selField()._width(200)._populate(
//        																	new RecList21(_TAB.version)._fillWithAll(new VEK<IF_Field>()._a(VERSION.version_code))
//        																	,VERSION.version_code,_TAB.version.key(),true));
 
        this.registerComponent(new RB_KF(HILFETEXT.id_version),    		new RB_selField()._width(200)._populate(
        																				new RecList21(_TAB.version)._fill(selVersion.s()),
        																				VERSION.version_code,
        																				_TAB.version.key(),true));

        
        String[][] enumVals = E2_MODULNAME_ENUM.get_dd_moduls(null, false);
        String[][] enumVals2 = new String[enumVals.length+2][2];
        enumVals2[0][0]= "-"; enumVals2[0][1]= "";
        enumVals2[1][0]= S.ms(HELP2_CONST.pairGlobalRange.getVal2()).CTrans(); enumVals2[1][1]= HELP2_CONST.pairGlobalRange.getVal1();
        for (int i=0;i<enumVals.length;i++) {
            enumVals2[i+2][0]=  enumVals[i][0]; enumVals2[i+2][1]=  enumVals[i][1];
        }
        this.registerComponent(new RB_KF(HILFETEXT.modulkenner),    	new RB_selField()._width(200)._populate(enumVals2));
        this.registerComponent(new RB_KF(HILFETEXT.status),    			new RB_selField()._width(200)._populate(HELP2_CONST.TICKET_STATUS.CLOSED.get_dd_Array(true)));
        this.registerComponent(new RB_KF(HILFETEXT.ticketnummer),    	new RB_TextField()._width(200));
        this.registerComponent(new RB_KF(HILFETEXT.titel),    			new RB_TextField()._width(HELP2_CONST.HELP2_NUM_CONST.WIDTH_LONGTEXT.getValue()));
        this.registerComponent(new RB_KF(HILFETEXT.typ),    			new RB_selField()._width(200)._populate(HELP2_CONST.TICKET_TYP.DOKUMENT.get_dd_Array(true)));
//       //beispiel fuer einen setter-validator 

        this.registerSetterValidators(HILFETEXT.status,new SetAndValidClosed());
 
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
    
        //falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
        if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
            preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
        }
        
        //falls ein modulkenner in der transporthashmap ist, diesen voreinstellen
        if (m_tpHashMap.containsKey(RB_TransportHashMap_ENUM.PLACE_FOR_ANYTHING_ELSE)) {
        	@SuppressWarnings("unchecked")
			HashMap<HELP2_CALLSETTINGS, Object> callSettings = (HashMap<HELP2_CALLSETTINGS, Object>) m_tpHashMap.get(RB_TransportHashMap_ENUM.PLACE_FOR_ANYTHING_ELSE);
        	if (callSettings.containsKey(HELP2_CALLSETTINGS.VORGABE_MODUL)) {
        		E2_MODULNAME_ENUM.MODUL m = ((HELP2__TransportHashMap) m_tpHashMap).getModul();
        		if (m!=null) {
        			preSettingsContainer.rb_set_defaultMaskValue(HILFETEXT.modulkenner, m.get_callKey());
        		}
        	}
        }
		preSettingsContainer.rb_set_defaultMaskValue(HILFETEXT.status, HELP2_CONST.TICKET_STATUS.NEW.db_val());

        
        return null;
     }
	
	
	
	private class SetAndValidClosed extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
				
				RB_MaskController con = new RB_MaskController(HELP2_MASK_ComponentMap.this);
				
				if (S.NN(con.get_liveVal(HILFETEXT.status)).equals(HELP2_CONST.TICKET_STATUS.CLOSED.db_val())) {
					con.set_maskVal(HILFETEXT.abschlussdatum, 		new SimpleDateFormat("dd.MM.yyyy").format(new Date()), mv);
				} else {
					con.set_maskVal(HILFETEXT.abschlussdatum, 		"", mv);
				}
			}
			return mv;
		}
		
	}
	
}
 
 
