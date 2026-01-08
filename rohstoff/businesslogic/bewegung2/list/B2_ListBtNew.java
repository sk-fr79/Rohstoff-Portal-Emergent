 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_New;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.B2_DefaultFields;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskDataObjectCollector;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskModulContainer;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
  
public class B2_ListBtNew extends RB_BtV4_New {
  
    public B2_ListBtNew(RB_TransportHashMap  p_tpHashMap) {
        super();
        
        this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(ENUM_VALIDATION.BG_TRANSPORT_NEW.getValidator());
    }
  
    
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTtransportHashMap()._setRBModulContainerMask(new B2_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
    }
    
    
	@Override
	public RB_DataobjectsCollector generateDataObjects4New() throws myException {
  
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new B2_MaskDataObjectCollector(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
	
	
	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
  
		MyLong l = new MyLong(id);
		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new B2_MaskDataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
    
   
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
	
	
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(
        		new Break4MaskCloseWhenSomethingWasChanged(this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(),new B2_DefaultFields()));
    }
    
}
 
 
