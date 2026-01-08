package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorNN;

public class FZ_abschluss_arbeiten {

	private VectorNN<String>  v_statements_am_abschluss = new VectorNN<>();

	/**
	 * @throws myException 
	 * 
	 */
	public FZ_abschluss_arbeiten(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		super();
		
		//als erstes den gespeicherten vektor suchen

		for (RB_Dataobject d_o: do_collector) {
			RB_Dataobject_V2 do2 = (RB_Dataobject_V2)d_o;
			
			Rec20 r_vektor = null;
			if (do2.get_rec20().get_tab() == _TAB.bewegung_vektor) {
				r_vektor = do2.get_rec20().get_rec_after_save_new();
			} else {
				r_vektor = do2.get_rec20()._rebuild();
			}
			if (r_vektor!=null) {
				break;
			}
		}


		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
