 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_LIST_bt_ListToMask extends RB_BtV4_List2Mask  {
	
    public WF_SIMPLE_LIST_bt_ListToMask(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
        super(bEdit);
        this._setTransportHashMap(p_tpHashMap);
         
        this.add_GlobalValidator(bEdit?WF_SIMPLE_VALIDATORS.EDIT.getValidator():WF_SIMPLE_VALIDATORS.EDIT.getValidator());
        
        this.add_GlobalValidator(new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				WF_SIMPLE_LIST_bt_ListToMask oThis = WF_SIMPLE_LIST_bt_ListToMask.this;
				
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (oThis.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated().size()==0) {
					mv._addAlarm(new MyE2_String("Sie muessen mindestens eine Datenzeile auswaehlen !"));
				}
				return mv;
			}
		});
    }
     
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTransportHashMap()._setMaskStatusOnLoad(this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW);
        return new WF_SIMPLE_MASK_MaskModulContainer(this.getTransportHashMap());
    }
    
    
    @Override
    public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new WF_SIMPLE_MASK_DataObjectCollector(this.getTransportHashMap(),id,aktuellerStatus));
        }
        return collector;
    }
    
    
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
        return new MyE2_String(this.isUsedToEdit()?"Bearbeiten eines Laufzettel-Eintrags":"Anzeige eines Laufzettel-Eintrags");
    }
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return new MyE2_String("Laufzettel-Eintrag wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	WF_SIMPLE_LIST_bt_ListToMask oThis = WF_SIMPLE_LIST_bt_ListToMask.this;
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(oThis.getTransportHashMap().getRBModulContainerMask()) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oThis.getTransportHashMap().getNavigationList()._RebuildSiteAndKeepMarkers("");
			}
		});
    	
        return v_rueck;
    }
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(
        		this.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
    }
   
     
}
 
 
