package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selector_Fragment_User;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD__NaviList;


public class WF_SelectorSwitcher extends MyE2_SelectField {

	public enum SWITCH_TYPE {
		VARIANTE2(new MyE2_String("Selektor 1"))
		,VARIANTE3(new MyE2_String("Selektor 2"))
		;
		
		private MyE2_String c_text = null;
		private SWITCH_TYPE(MyE2_String text) {
			this.c_text = text;
		}
		
		public static LinkedHashMap<String, MyE2_String> get_dd_hm() {
			LinkedHashMap<String, MyE2_String> hm=new LinkedHashMap<>();
			for (SWITCH_TYPE t: SWITCH_TYPE.values()) {
				hm.put(t.name(), t.c_text);
			}
			return hm;
		}
	}
	
	private 	WF_HEAD_LIST__Selectioncontainer  					main_selektor = null;
	private  	WF_HEAD_LIST_SelectionComponentVector_Version2   	sel2 = null;
	private  	WF_HEAD_LIST_SelectionComponentVector_Version3   	sel3 = null;
	
	private     WF_HEAD__NaviList f_navilist = null;
	
	public WF_SelectorSwitcher(WF_HEAD_LIST__Selectioncontainer p_main_selektor, WF_HEAD__NaviList navilist, String modul_kenner) throws myException {
		super(SWITCH_TYPE.get_dd_hm(), false);
		
		this.f_navilist = navilist;
		
		this.setWidth(new Extent(150));
		
		sel2 = 	new WF_HEAD_LIST_SelectionComponentVector_Version2(navilist, this, modul_kenner);
		sel3 = 	new WF_HEAD_LIST_SelectionComponentVector_Version3(navilist, modul_kenner);
		
		this.main_selektor = p_main_selektor;
		
		this.add_oActionAgent(new ownActionAgentSwitch());
		this.add_oActionAgent(new ownActionSaveSetting());
		
		//letzte einstellung laden
		new ownUserSetter().restore();
		
	}

	
	private class ownActionAgentSwitch extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WF_SelectorSwitcher.this.set_active_selektor();
			
		}
	}
	
	
	public void set_active_selektor() throws myException {
		main_selektor.get_grid_4_selektorSwitch().removeAll();
		if 	(this.get_ActualWert().equals(SWITCH_TYPE.VARIANTE2.name())) {
			main_selektor.get_grid_4_selektorSwitch()._a(sel2.get_oSelComponent());
			this.f_navilist.set_sel_hauptuser_from_selektor(this.find_superuser_selektor(sel2));
			sel2.doActionPassiv();
		} else if 	(this.get_ActualWert().equals(SWITCH_TYPE.VARIANTE3.name())) {
			main_selektor.get_grid_4_selektorSwitch()._a(sel3.get_oSelComponent());
			this.f_navilist.set_sel_hauptuser_from_selektor(this.find_superuser_selektor(sel3));
			sel3.doActionPassiv();
		}
	}
	
	
	private MyE2_SelectField  find_superuser_selektor(E2_SelectionComponentsVector sel_v) {
		for (XX_ListSelektor sel: sel_v) {
			if (sel instanceof WF_HEAD_LIST_Selector_Fragment_User) {
				return ((WF_HEAD_LIST_Selector_Fragment_User)sel).get_SelectField();
			} else  if (sel instanceof WF_ListSelektor_3_super_user_selektor) {
				return ((WF_ListSelektor_3_super_user_selektor)sel).get_selField();
			}
		}
		return null;
	}
	
	
	
	private class ownActionSaveSetting extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new ownUserSetter().save();			
		}
	}
	
	
	private class ownUserSetter extends E2_UserSetting_SIMPLE {
		
		String universal_key = ENUM_USER_SAVEKEY.SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN.name();
		
		public ownUserSetter() {
			super(ENUM_USER_SAVEKEY.SESSION_HASH_WF_HEAD_LIST_SELECTOR_MAIN.name());
		}
		
		public void save() throws myException 	{
			String actual_selekt_val = WF_SelectorSwitcher.this.get_ActualWert();
			this.STORE(this.universal_key, actual_selekt_val);
		}
		
		public void restore() throws myException 	{
			String cRueck = (String)this.get_Settings(this.universal_key);
			if (S.isFull(cRueck)){
				WF_SelectorSwitcher.this.set_ActiveValue_OR_FirstValue(cRueck);
			} else {
				WF_SelectorSwitcher.this.set_ActiveValue_OR_FirstValue("");
			}
			
		}

	}
	
	
}
