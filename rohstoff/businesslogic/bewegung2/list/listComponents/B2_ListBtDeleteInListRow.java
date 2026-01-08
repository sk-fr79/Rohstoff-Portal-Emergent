 
package rohstoff.businesslogic.bewegung2.list.listComponents;
  
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.list.B2_ListBtDeleteAbstract;
  
public class B2_ListBtDeleteInListRow extends B2_ListBtDeleteAbstract  {
    
/**
	 * @author martin
	 * @date 11.02.2019
	 *
	 * @param p_tpHashMap
	 */
	public B2_ListBtDeleteInListRow(RB_TransportHashMap p_tpHashMap) {
		super(p_tpHashMap);
	}



	public VEK<String> getIdVectorsToDel() {
		return new VEK<String>()._a(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
	}
	
	
  
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_ListBtDeleteInListRow copy= new B2_ListBtDeleteInListRow(this.getTpHashMap());
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
  

  
}
 
 
