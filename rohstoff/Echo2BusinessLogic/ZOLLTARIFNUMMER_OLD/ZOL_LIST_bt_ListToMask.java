package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import java.util.Vector;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_CONST.ZOL_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;


public class ZOL_LIST_bt_ListToMask extends RB_bt_List2Mask {
    private E2_NavigationList  naviList = null;
    
    public ZOL_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
        super(bEdit);
        this.naviList = p_naviList;
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?ZOL_BUTTONS.EDIT.db_val():ZOL_BUTTONS.VIEW.db_val()));
        
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new ZOL_MASK_MaskModulContainer();
    }
    
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new ZOL_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Zolltarifnummer":"Anzeige von Zolltarifnummer");
    }
    
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String("Zolltarifnummer wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
        return null;
    }
}
 
