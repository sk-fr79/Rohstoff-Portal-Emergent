 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
  
  
import java.util.Vector;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class RQP_LIST_bt_ListToMaskInListRow extends RB_BtV4_List2Mask {
    
    public RQP_LIST_bt_ListToMaskInListRow(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
        super(bEdit);
        this._setTransportHashMap(p_tpHashMap);
   
        this.add_GlobalValidator(bEdit?RQP_VALIDATORS.EDIT.getValidator():RQP_VALIDATORS.EDIT.getValidator());
        
    }
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTransportHashMap()._setRBModulContainerMask(
    			new RQP_MASK_MaskModulContainer(this.getTransportHashMap()));
        return this.getTransportHashMap().getRBModulContainerMask();
    }
  
    
    @Override
    public RB_hm_multi_DataobjectsCollector generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
    	
    	RQP_LIST_bt_ListToMaskInListRow oThis = RQP_LIST_bt_ListToMaskInListRow.this;
    	
    	
		if (this.EXT().get_oComponentMAP()!=null) {
			MyLong  id = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			if (id.isOK()) {
		        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
		        RB__CONST.MASK_STATUS aktuellerStatus = this.isUsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
	            collector.put(id.get_cUF_LongString(), new RQP_MASK_DataObjectCollector(oThis.getTransportHashMap(), id.get_cUF_LongString(),aktuellerStatus));
	            return collector;
			} else {
				throw new myException("Error finding id to Edit");
			}
		} else {
			throw new myException("Error:  no containing E2_ComponentMAP");
		}
    }
    
    
    @Override
    public MyE2_String generateTitelInfo4MaskWindow() throws myException {
        return new MyE2_String(this.isUsedToEdit()?"Bearbeiten eines Datensatzes vom Typ: Listenspalten":"Anzeige eines Datensatzes vom Typ: Listenspalten");
    }
    
    @Override
    public MyE2_String generateMessagetextForSaveRecord() throws myException {
        return new MyE2_String("Datensatzes vom Typ: Listenspalten wurde gespeichert");
    }
    
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generateWindowCloseActions() throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
     	RQP_LIST_bt_ListToMaskInListRow oThis = RQP_LIST_bt_ListToMaskInListRow.this;
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
    
   
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
	
   
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		RQP_LIST_bt_ListToMaskInListRow copy= new RQP_LIST_bt_ListToMaskInListRow(this.isUsedToEdit(), this.getTransportHashMap());
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
   
	/**
	 * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
	 * @param enabled
	 * @throws myException
	 */
	public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}
	
   
    
}
 
 
