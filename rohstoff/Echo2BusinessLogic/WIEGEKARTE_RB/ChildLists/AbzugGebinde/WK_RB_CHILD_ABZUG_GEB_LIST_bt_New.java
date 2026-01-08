 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_New;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_ABZUG_GEB_LIST_bt_New extends RB_BtV4_New {
  
    public WK_RB_CHILD_ABZUG_GEB_LIST_bt_New(RB_TransportHashMap  p_tpHashMap) {
        super();
        
        this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(WK_RB_CHILD_ABZUG_GEB_VALIDATORS.NEW.getValidator());
        this._setShowSaveAndReOpenButton(false);
    }
  
    
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTtransportHashMap()._setRBModulContainerMask(new WK_RB_CHILD_ABZUG_GEB_MASK_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
    }
    
    
	@Override
	public RB_DataobjectsCollector generateDataObjects4New() throws myException {
  
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new WK_RB_CHILD_ABZUG_GEB_MASK_DataObjectCollector(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
	
	
	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
  
		MyLong l = new MyLong(id);
		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new WK_RB_CHILD_ABZUG_GEB_MASK_DataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
    
   
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
	
	
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(new Break4MaskCloseWhenSomethingWasChanged(this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
    }
    
}
 
 
