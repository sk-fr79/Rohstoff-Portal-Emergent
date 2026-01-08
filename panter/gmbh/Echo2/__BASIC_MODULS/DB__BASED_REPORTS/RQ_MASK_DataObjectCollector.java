 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
 
 
public class RQ_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQ_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this._setUseRec21(true);
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        this.registerComponent(_TAB.reporting_query.rb_km(), new RQ_MASK_DataObject(this.m_tpHashMap));
        
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public RQ_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_REPORTING_QUERY, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
 
        this._setUseRec21(true);
 
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        this.registerComponent(_TAB.reporting_query.rb_km(), new RQ_MASK_DataObject(new Rec21(_TAB.reporting_query)._fill_id(id_REPORTING_QUERY),status,this.m_tpHashMap));
        
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
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)	throws myException {
		
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,	MyE2_MessageVector mv) throws myException {
		
	}
}
 
 
