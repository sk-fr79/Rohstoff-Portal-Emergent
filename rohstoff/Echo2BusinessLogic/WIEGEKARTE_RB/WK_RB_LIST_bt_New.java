 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_New;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_LIST_bt_New extends RB_BtV4_New {
  
    public WK_RB_LIST_bt_New(RB_TransportHashMap  p_tpHashMap) {
        super();
        this.setText(new MyString("Neue Wiegekarte").CTrans());
        this._setShapeStandardTextButton();
        
//        p_tpHashMap._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());
        
        this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(WK_RB_VALIDATORS.NEW.getValidator());
        
        this._addObjectAccessor((o)->{
        	o.getBtCancel()._setShapeStandardTextButton().setText(new MyString("Abbrechen").CTrans());
        	o.getBtSave()._setShapeStandardTextButton().setText(new MyString("Speichern & Schließen").CTrans());
        	o.getBtSaveAndReopen()._setShapeStandardTextButton().setText(new MyString("Speichern").CTrans());
        });
     
        
    }
  
    
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {

    	this.get_grid4MaskExternal()._clear();
    	this.getTtransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());
    	
    	WK_RB_LIST_Selector selector = (WK_RB_LIST_Selector) this.getTtransportHashMap().get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR);
    	
		String _idStandort = selector.getoSelStandort().get_ActualWert();
		String _idAdresseLager = selector.getoSelLager().get_ActualWert();

    	if (S.isEmpty(_idAdresseLager)|| S.isEmpty(_idStandort) ) {
    		bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Für die Erfassung einer Wiegekarte muss ein Lager und ein Standort ausgewählt sein!")));
    	} else {
    		// das Lager und den Standort in die Transporthashmap übertragen
    		this.getTtransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_LAGER,_idAdresseLager );
    		this.getTtransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.ID_STANDORT,_idStandort );
    	}

    	this.getTtransportHashMap()._setRBModulContainerMask(new WK_RB_MASK_MaskModulContainer(this.getTtransportHashMap()));
//    	this.getTtransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());
    	
    	return this.getTtransportHashMap().getRBModulContainerMask();
    }
    
    
	@Override
	public RB_DataobjectsCollector generateDataObjects4New() throws myException {
  
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new WK_RB_MASK_DataObjectCollector(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
	
	
	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
  
		MyLong l = new MyLong(id);
		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new WK_RB_MASK_DataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
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
 
 
