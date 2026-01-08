 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
public class CON_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
	
	String _id_ContainerTyp = null;
	public CON_MASK_DataObjectCollector() throws myException {
		this(null);
	}
		
	
    public CON_MASK_DataObjectCollector(String id) throws myException {
        super();
        _id_ContainerTyp = id;
        
        this.registerComponent(new CON_KEY(), new CON_MASK_DataObject());
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
   
    public CON_MASK_DataObjectCollector(String id_container, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new  CON_KEY(), new CON_MASK_DataObject(new Rec20(_TAB.container)._fill_id(id_container),status));
        
        this._addMessageTranslator(new RB_MessageTranslator(
                        new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
        
    }
    
    
    public String get_idContainterTyp(){
    	return _id_ContainerTyp;
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
 
