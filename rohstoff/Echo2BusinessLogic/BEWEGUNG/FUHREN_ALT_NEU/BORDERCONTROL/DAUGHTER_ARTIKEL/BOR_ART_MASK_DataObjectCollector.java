 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
 
 
public class BOR_ART_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_MASK_DataObjectCollector(PARAMHASH  p_params) throws myException {
        super();
        this.params = p_params;     
        
        if (this.params != null) {
	        this.params.put(BOR_ART_PARAMS.BOR_ART_MASK_DATAOBJECTS_COLLECTOR,this);    
        }
        
        this.registerComponent(_TAB.bordercrossing_artikel.rb_km(), new BOR_ART_MASK_DataObject(this.params));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public BOR_ART_MASK_DataObjectCollector(String id_bordercrossing_artikel, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(_TAB.bordercrossing_artikel.rb_km(), new BOR_ART_MASK_DataObject(new Rec20(_TAB.bordercrossing_artikel)._fill_id(id_bordercrossing_artikel),status,this.params));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
    }
  	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
		// TODO Auto-generated method stub
		
	}
  
	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)
			throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,
			MyE2_MessageVector mv) throws myException {
		// TODO Auto-generated method stub
		
	}
}
 
