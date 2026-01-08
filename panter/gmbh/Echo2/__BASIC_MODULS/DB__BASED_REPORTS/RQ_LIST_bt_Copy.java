 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_NewCopy;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
  
public class RQ_LIST_bt_Copy extends RB_BtV4_NewCopy {
	
    public RQ_LIST_bt_Copy(RB_TransportHashMap  p_tpHashMap) {
        super();
        this.add_GlobalValidator(RQ_VALIDATORS.NEW.getValidator());
        this._setTransportHashMap(p_tpHashMap);
        
        this._aaa(new OwnActionConnectTextfields());
        this._aaa(new OwnActionAgentOpenCopiedMasks());
    }
 
    
    //kleiner actionagent, der die kopierten Felder QUERY1 bis QUERY4 wieder zusammensetzt
    private class OwnActionConnectTextfields extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RQ_LIST_bt_Copy.this.getTtransportHashMap().getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey());
			
			RB_ComponentMap map = getTtransportHashMap().getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey());
			
			RB_MaskController rbc = new RB_MaskController(map);
			
			String c1 = rbc.get_liveVal(REPORTING_QUERY.query1);
			String c2 = rbc.get_liveVal(REPORTING_QUERY.query2);
			String c3 = rbc.get_liveVal(REPORTING_QUERY.query3);
			String c4 = rbc.get_liveVal(REPORTING_QUERY.query4);
			
			String c = "";
			if (S.isFull(c1)) {c+=c1; c+="\n"; }
			if (S.isFull(c2)) {c+=c2; c+="\n"; }
			if (S.isFull(c3)) {c+=c3; c+="\n"; }
			if (S.isFull(c4)) {c+=c4; c+="\n"; }
			
			map.getIfRbComp(RQ_TextAnzeigeAllSQL.KEY).rb_set_db_value_manual(c);
		}
    }
    
    
    private class OwnActionAgentOpenCopiedMasks extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_ComponentMap map = getTtransportHashMap().getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey());

			RB_MaskController rbc = new RB_MaskController(map);
			
			rbc.set_maskVal(REPORTING_QUERY.aktiv, "N", bibMSG.MV());
			bibMSG.MV().add_MESSAGE(getTtransportHashMap().getMaskComponentMapCollector(). rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_LOAD_ACTION));
		}
    }
    
    
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new RQ_MASK_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
	}
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
 
		if (idToCopy==null) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        		new RQ_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
 	
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
 
		MyLong l = new MyLong(idToCopy);
 		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        			new RQ_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
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
    				   RQ_LIST_bt_Copy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
   }
 
 	
}
 
 
