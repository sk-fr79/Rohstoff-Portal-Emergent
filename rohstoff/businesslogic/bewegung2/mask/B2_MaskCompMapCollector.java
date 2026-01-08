 
package rohstoff.businesslogic.bewegung2.mask;
   
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForSettingsBeforeSave;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;
  
public class B2_MaskCompMapCollector extends RB_ComponentMapCollector { 
	
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    
	public B2_MaskCompMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
        
	    this.m_tpHashMap = p_tpHashMap;     
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
	        
	    this.registerComponent(RecV.key, 	new B2_MaskComponentMap_VEKTOR(this.m_tpHashMap,EnTabKeyInMask.V));
	    this.registerComponent(RecS1.key, 	new B2_MaskComponentMap_STATION(this.m_tpHashMap,EnPositionStation.LEFT, EnTabKeyInMask.S1));
	    this.registerComponent(RecA1.key, 	new B2_MaskComponentMap_ATOM(this.m_tpHashMap,EnPositionStation.LEFT, EnTabKeyInMask.A1));
	    this.registerComponent(RecS2.key, 	new B2_MaskComponentMap_STATION(this.m_tpHashMap,EnPositionStation.MID, EnTabKeyInMask.S2));
	    this.registerComponent(RecA2.key, 	new B2_MaskComponentMap_ATOM(this.m_tpHashMap,EnPositionStation.RIGHT, EnTabKeyInMask.A2));
	    this.registerComponent(RecS3.key, 	new B2_MaskComponentMap_STATION(this.m_tpHashMap,EnPositionStation.RIGHT, EnTabKeyInMask.S3));

	    
	    this._addBeforeMaskSaveCycleAndValidationListener((o)-> {
			MyE2_MessageVector mv = bibMSG.newMV();
			new B2_McForSettingsBeforeSave(this.get(RecV.key))._setFieldsBeforeSave(mv);
			return mv;
	    });

    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
	

}
 
 
