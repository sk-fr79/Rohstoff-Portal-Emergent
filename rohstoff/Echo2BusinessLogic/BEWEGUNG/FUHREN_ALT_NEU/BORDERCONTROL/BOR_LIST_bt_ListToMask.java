
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_CONST.BOR_BUTTONS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_LIST_bt_ListToMask;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;


public class BOR_LIST_bt_ListToMask extends RB_bt_List2Mask {

	private E2_NavigationList naviList = null;

	public BOR_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
		super(bEdit,p_naviList);
		this.set_bAutoRefreshList(true);
		this.naviList = p_naviList;
		this.add_GlobalValidator(new ownValidator());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit ? BOR_BUTTONS.EDIT.db_val() : BOR_BUTTONS.VIEW.db_val()));
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {

		return new BOR_MASK_MaskModulContainer();
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated_Select_the_one_and_only());

		RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
		RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit() ? RB__CONST.MASK_STATUS.EDIT : RB__CONST.MASK_STATUS.VIEW;

		for (String id : v_ids) {
			collector.put(id, new BOR_MASK_DataObjectCollector(id, aktuellerStatus));
		}
		return collector;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

		return new MyE2_String(this.is_UsedToEdit() ? "Bearbeiten: Grenzkontroll-Eintrag" : "Anzeige: Grenzkontroll-Eintrag");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {

		return new MyE2_String("Grenzkontroll-Eintrag wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {

     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
    	
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
				// nachdem gelöscht wurde, ein Dirty-Flag setzen, damit die Parent-Parent-Liste refresht werden kann
//				BOR_ART_LIST_bt_ListToMask.this.params.put(BOR_ART_PARAMS.BOR_ART_IS_DIRTY, true);
				
				BOR_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
				
				
			}
		});
    	
        return v_rueck;
		
		
//		return null;
	}
	
	private class ownValidator extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (BOR_LIST_bt_ListToMask.this.naviList.get_vSelectedIDs_Unformated_Select_the_one_and_only().size()==0) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens einen Datensatz aus !")));
			}
			
			return mv;
		}
		
	}
	
}
