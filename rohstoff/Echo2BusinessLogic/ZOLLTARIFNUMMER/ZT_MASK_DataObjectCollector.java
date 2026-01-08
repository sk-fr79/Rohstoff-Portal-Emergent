 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22LandRcSorte;
 
  
 
public class ZT_MASK_DataObjectCollector extends RB_DataobjectsCollector_V22 {
 	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public ZT_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        this.m_tpHashMap = p_tpHashMap;     
        
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);
        
        this.registerComponent(_TAB.zolltarifnummer.rb_km(), new ZT_MASK_DataObject(this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public ZT_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_ZOLLTARIFNUMMER, MASK_STATUS status) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
    
        this.m_tpHashMap._setMaskDataObjectsCollector(this);    
        this.m_tpHashMap._setMaskStatusOnLoad(status);
        
        this.registerComponent(_TAB.zolltarifnummer.rb_km(), new ZT_MASK_DataObject(new Rec22(_TAB.zolltarifnummer)._fill_id(id_ZOLLTARIFNUMMER),status,this.m_tpHashMap));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
        
        this._registerExecuterBeforeCommit((o)-> {
        	MyE2_MessageVector mv = new MyE2_MessageVector();
        	MapForChangeRcTables mapForChangeRcTables = (MapForChangeRcTables)this.m_tpHashMap.getSB("ZT_KEY_AUTOCHANGE_RC_TABLE");
        	
        	for (Rec22LandRcSorte lrs : mapForChangeRcTables.getLandAndSortenToAdd()) {
        		lrs._SAVE(false, mv);
        	}
        	for (Rec22LandRcSorte lrs : mapForChangeRcTables.getLandRcSortenToDelete()) {
        		mv._add(lrs.DELETE(false));
        	}
        	
        	if (mv.isOK()) {
        		if (mapForChangeRcTables.getLandAndSortenToAdd().size()>0) {
        			mv._addInfo(S.ms("RC-Ländereinträge hinzugefügt:").ut(""+mapForChangeRcTables.getLandAndSortenToAdd().size()));
        		}
        		if (mapForChangeRcTables.getLandRcSortenToDelete().size()>0) {
        			mv._addInfo(S.ms("RC-Ländereinträge entfernt:").ut(""+mapForChangeRcTables.getLandRcSortenToDelete().size()));
        		}
        	}
        	
        	
        	return mv;
        });
        
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
 
 
