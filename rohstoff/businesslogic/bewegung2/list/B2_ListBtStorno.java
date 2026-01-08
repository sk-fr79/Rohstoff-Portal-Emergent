 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
  
public class B2_ListBtStorno extends B2_ListBtStornoAbstract {
	
    
    public B2_ListBtStorno(RB_TransportHashMap  p_tpHashMap)    {
        super(p_tpHashMap);
        
        
		// storno bei geloeschten nicht erlaubt
		this._addValidator(()->{ 
			MyE2_MessageVector  mv = bibMSG.MV();
			for (String id: getIdVectorsToStorno()) {
				Rec21 r = new Rec21(_TAB.bg_vektor)._fill_id(id);
				if ( S.isFull( r.getUfs(BG_VEKTOR.id_bg_del_info))) {
					mv._addAlarm(S.ms("Mindestens eine der gewählten Warenbewegungen ist gelöscht. Um den Stornozustand zu ändern, bitte das Löschen rückgängig machen !"));
				}
			}
			return mv;
		});
		
		
		// Wenn mengen/preise erfasst sind, nicht erlaubt
		this._addValidator(()->{ 
			
			MyE2_MessageVector  mv = bibMSG.MV();
			for (String id: getIdVectorsToStorno()) {
				Rec21_bgVector r = new Rec21_bgVector(new Rec21(_TAB.bg_vektor)._fill_id(id));
				mv._add(r.checkStornable());
			}
			return mv;

		});

        
    }

	@Override
	public VEK<String> getIdVectorsToStorno() {
		return new VEK<String>()._a(this.getTpHashMap().getNavigationList().get_vSelectedIDs_Unformated());
	}
   
    
    
}
 
 
