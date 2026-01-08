 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_NewCopy;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.B2_DefaultFields;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskDataObjectCollector;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskModulContainer;
  
public class B2_ListBtCopy extends RB_BtV4_NewCopy {
	
    public B2_ListBtCopy(RB_TransportHashMap  p_tpHashMap) {
        super();
        this.add_GlobalValidator(ENUM_VALIDATION.BG_TRANSPORT_NEW.getValidator());
        this._setTransportHashMap(p_tpHashMap);
    }
 
    
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new B2_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
	}
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
 
		if (idToCopy==null) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new B2_MaskDataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
 	
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
 
		MyLong l = new MyLong(idToCopy);
 		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        			new B2_MaskDataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
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
    				   B2_ListBtCopy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), new B2_DefaultFields()));
   }
 
 	
}
 
 
