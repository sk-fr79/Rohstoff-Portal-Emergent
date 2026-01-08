 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_NewCopy;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_LIST_bt_Copy extends RB_BtV4_NewCopy {
	
    public WK_RB_LIST_bt_Copy(RB_TransportHashMap  p_tpHashMap) {
        super();
        
        this.setText(new MyString("Wiegekarte kopieren").CTrans()) ; 
        this._setShapeStandardTextButton();
        this.add_GlobalValidator(WK_RB_VALIDATORS.NEW.getValidator());
        
        p_tpHashMap._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());
        this._setTransportHashMap(p_tpHashMap);

        this._addObjectAccessor((o)->{
        	o.getBtCancel()._setShapeStandardTextButton().setText(new MyString("Abbrechen").CTrans());
        	o.getBtSave()._setShapeStandardTextButton().setText(new MyString("Speichern & Schlieﬂen").CTrans());
        	o.getBtSaveAndReopen()._setShapeStandardTextButton().setText(new MyString("Speichern").CTrans());
        });
        
    }
 
    
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
		this.get_grid4MaskExternal()._clear();
    	this.getTtransportHashMap()._setToExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL,   this.get_grid4MaskExternal());
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new WK_RB_MASK_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
	}
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
 
		if (idToCopy==null) {
			throw new myException("Cannot copy null-id");
		}
		
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        								new WK_RB_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();

	}
 	
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
 
		MyLong l = new MyLong(idToCopy);
 		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        			new WK_RB_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
 
 	
   @Override
   public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
       return null;
   }
   
   
   @Override
   public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
       return new E2_Break4PopupController()._registerBreak(
    		   new Break4MaskCloseWhenSomethingWasChanged(
    				   WK_RB_LIST_bt_Copy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
   }
 

}
 
 
