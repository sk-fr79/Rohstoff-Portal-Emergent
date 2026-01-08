 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.myVectors.VEK;
  
public class B2_ListBtDelete extends B2_ListBtDeleteAbstract {
	
    
    public B2_ListBtDelete(RB_TransportHashMap  p_tpHashMap)    {
        super(p_tpHashMap);
        this._image(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
        this.add_GlobalValidator(ENUM_VALIDATION.BG_TRANSPORT_DELETE.getValidator());
    }

	@Override
	public VEK<String> getIdVectorsToDel() {
		return new VEK<String>()._a(this.getTpHashMap().getNavigationList().get_vSelectedIDs_Unformated());
	}
   
    
    
}
 
 
