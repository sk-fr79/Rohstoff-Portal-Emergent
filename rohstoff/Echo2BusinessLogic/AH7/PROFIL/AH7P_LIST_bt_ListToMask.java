package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask_V2;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_LIST_bt_ListToMask extends RB_bt_List2Mask_V2 {
    
    public AH7P_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
        super(bEdit);
        this._setNaviList(p_naviList);
        this.add_GlobalValidator(bEdit?ENUM_VALIDATION.AH7_PROFIL_EDIT.getValidatorWithoutSupervisorPersilschein():ENUM_VALIDATION.AH7_PROFIL_VIEW.getValidatorWithoutSupervisorPersilschein());
        this._setStandardMessageWhenNothingSelected();
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new AH7P_MASK_MaskModulContainer();
    }
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.get_NaviList().get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new AH7P_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Anhang-7-Profilen":"Anzeige von  Anhang-7-Profilen");
    }
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(" Anhang-7-Profil wurde gespeichert");
    }
    
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
    	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
    	
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				AH7P_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
			}
		});
    	
        return v_rueck;
    }
}
 
