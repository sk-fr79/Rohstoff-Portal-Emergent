 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.Break4Popup.MyE2_Alarm_Message_Break4Popup;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.SEARCHDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_MASK_ComponentMap extends RB_ComponentMap {
	
	
    private String modulKenner;
    
	public SD_MASK_ComponentMap(String modulKenner) throws myException {
        super();
		this.modulKenner = modulKenner;
        
        this.registerComponent(new RB_KF(SEARCHDEF.id_searchdef),  	new RB_TextField()._width(100));
        this.registerComponent(new RB_KF(SEARCHDEF.modulkenner),  	new RB_TextField()._width(500));
        this.registerComponent(new RB_KF(SEARCHDEF.sqlblock),    	new RB_TextArea()._width(500)._rows(10));
        this.registerComponent(new RB_KF(SEARCHDEF.user_text),   	new RB_TextField()._width(500));
        this.registerComponent(new RB_KF(SEARCHDEF.aktiv),    		new RB_CheckBox(SEARCHDEF.aktiv));
        this.registerComponent(SD_CONST.SD_NAMES.MASK_BT_HELP.getPseudoFieldKey(_TAB.searchdef), new SD_MASK_compInfoSQL());
        
        this.registerSetterValidators(SEARCHDEF.sqlblock, new SetAndValidSqlStatement());
        
        
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
		preSettingsContainer.rb_get(SEARCHDEF.modulkenner).set_rb_Default(modulKenner);
		preSettingsContainer.rb_get(SEARCHDEF.modulkenner).set_Enabled(false);

		return null;
	}
	
	
	private class SetAndValidSqlStatement extends RB_Mask_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				RB_MaskController co = new RB_MaskController(rbMASK);
				
				if (!co.get_liveVal(SEARCHDEF.sqlblock).contains("#WERT#")) {
					mv.add_MESSAGE(new MyE2_Alarm_Message_Break4Popup(S.ms("Der SQL-Block MUSS einen Ausdruck enthalten, der der Term #WERT# enthält! ")));
				}
				if (co.get_liveVal(SEARCHDEF.sqlblock).toUpperCase().contains("INSERT")) {
					mv.add_MESSAGE(new MyE2_Alarm_Message_Break4Popup(S.ms("Der SQL-Block MUSS das Wort INSERT nicht enthalten! ")));
				}
				if (co.get_liveVal(SEARCHDEF.sqlblock).toUpperCase().contains("UPDATE")) {
					mv.add_MESSAGE(new MyE2_Alarm_Message_Break4Popup(S.ms("Der SQL-Block MUSS das Wort UPDATE nicht enthalten! ")));
				}
				if (co.get_liveVal(SEARCHDEF.sqlblock).toUpperCase().contains("DELETE")) {
					mv.add_MESSAGE(new MyE2_Alarm_Message_Break4Popup(S.ms("Der SQL-Block MUSS das Wort DELETE nicht enthalten! ")));
				}
			}
			return mv;
		}
		
	}
	
}
 
