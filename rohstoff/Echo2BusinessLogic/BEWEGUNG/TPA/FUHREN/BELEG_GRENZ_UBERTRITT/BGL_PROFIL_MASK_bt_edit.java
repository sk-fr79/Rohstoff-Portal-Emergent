 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;
  
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class BGL_PROFIL_MASK_bt_edit extends RB_bt_List2Mask {
	
	private BGL_LIST_popup_BelegGrenzUbertritt popUp;
	
    public BGL_PROFIL_MASK_bt_edit(BGL_LIST_popup_BelegGrenzUbertritt p_parent) {
        super(true);
        this.popUp = p_parent;
        this.setToolTipText(new MyE2_String("Gewähltes Profil bearbeiten").CTrans());
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BGL_PROFIL_MASK_MaskModulContainer();
    }
    
	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler)
			throws myException {
		
		RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
	    RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
	    
	    String id = popUp.get_selected_profile();
	    
	    if(S.isFull(id)) {
	    	collector.put(id, new BGL_PROFIL_MASK_DataObjectCollector(id,aktuellerStatus));
	    }
		return collector;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return S.ms("Profil bearbeiten");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return S.ms("Profil wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(
			RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> closeActionVector = new Vector<>();
		closeActionVector.add(new XX_ActionAgentWhenCloseWindow(popUp) {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String id = popUp.get_selected_profile();
				popUp.reload_profile_list();
				popUp.fill_with_profile(id);
			}
		});
		
		return closeActionVector;
	}
    
}
 
