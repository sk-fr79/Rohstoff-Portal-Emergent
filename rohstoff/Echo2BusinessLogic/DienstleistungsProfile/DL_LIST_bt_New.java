 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_New;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
public class DL_LIST_bt_New extends RB_BtV4_New {
  
    public DL_LIST_bt_New(RB_TransportHashMap  p_tpHashMap) {
        super();
        
        this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(DL_VALIDATORS.NEW.getValidator());
    }
  
    
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTtransportHashMap()._setRBModulContainerMask(new DL_MASK_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
    }
    
    
	@Override
	public RB_DataobjectsCollector generateDataObjects4New() throws myException {
  
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new DL_MASK_DataObjectCollector(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
	
	
	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Edit(String id) throws myException {
  
		MyLong l = new MyLong(id);
		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new DL_MASK_DataObjectCollector(this.getTtransportHashMap(), l.get_cUF_LongString(), RB__CONST.MASK_STATUS.EDIT));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
    
   
    
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
	
	
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return new E2_Break4PopupController()._registerBreak(
        				new Break4MaskCloseWhenSomethingWasChanged(this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), 
        				new VEK<Pair<RB_K>>()
        				._a(new Pair<RB_K>(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.aktiv.fk()))
        				._a(new Pair<RB_K>(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.umrech_mge_quelle_ziel.fk()))
        				._a(new Pair<RB_K>(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.typ_mengen_calc.fk()))
        				._a(new Pair<RB_K>(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.anteil_menge.fk()))
        				._a(new Pair<RB_K>(DL_CONST.getLeadingMaskKey(),DLP_PROFIL.gilt_ab_datum.fk()))
        				));
    }
    
}
 
 
