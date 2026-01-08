 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
  
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMapCollector;
 
 
 
public class WK_RB_CHILD_MGE_ABZ_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_CHILD_MGE_ABZ_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this._setUseRec21(true);
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        this.registerComponent(_TAB.wiegekarte_mge_abz.rb_km(), new WK_RB_CHILD_MGE_ABZ_MASK_DataObject(this.m_tpHashMap));
        
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public WK_RB_CHILD_MGE_ABZ_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_WIEGEKARTE_MGE_ABZ, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
 
        this._setUseRec21(true);
 
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        this.registerComponent(_TAB.wiegekarte_mge_abz.rb_km(), new WK_RB_CHILD_MGE_ABZ_MASK_DataObject(new Rec22(_TAB.wiegekarte_mge_abz)._fill_id(id_WIEGEKARTE_MGE_ABZ),status,this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
    }
 
    @Override
	public void database_to_dataobject(Object startPoint) throws myException {
	}
  
	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}
  	


	@Override
	public MyE2_MessageVector rb_Dataobjects_to_Database(boolean forceSave) throws myException {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		mv._add( super.rb_Dataobjects_to_Database(forceSave) );
		
		
		//TODO: Abzüge neu rechnen
    	bibMSG.add_MESSAGE(new MyE2_Info_Message("Abzug gespeichert!"));
    	WK_RB_MASK_ComponentMapCollector compMapMother =  (WK_RB_MASK_ComponentMapCollector) m_tpHashMap.getMotherTransportHashMap().getMaskComponentMapCollector();
    	compMapMother._DoCompleteSaveCycle();
    	
    	
		return mv;
	}
	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)	throws myException {
		
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,	MyE2_MessageVector mv) throws myException {
		
	}
}
 
 
