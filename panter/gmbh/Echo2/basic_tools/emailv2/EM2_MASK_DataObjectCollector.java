 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
  
 
public class EM2_MASK_DataObjectCollector extends RB_DataobjectsCollector_V22 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public EM2_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        this.registerComponent(_TAB.email_send.rb_km(), new EM2_MASK_DataObject(this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public EM2_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_EMAIL_SEND, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
    
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        this.registerComponent(_TAB.email_send.rb_km(), new EM2_MASK_DataObject(new Rec22(_TAB.email_send)._fill_id(id_EMAIL_SEND),status,this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
    }
 
    @Override
	public void database_to_dataobject(Object startPoint) throws myException {
	}
  
	@Override
	public RB_DataobjectsCollector_V22 get_copy() throws myException {
		return null;
	}
  	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V22 do_collector, MyE2_MessageVector mv)	throws myException {
		
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V22 do_collector,	MyE2_MessageVector mv) throws myException {
		
	}
}
 
 
