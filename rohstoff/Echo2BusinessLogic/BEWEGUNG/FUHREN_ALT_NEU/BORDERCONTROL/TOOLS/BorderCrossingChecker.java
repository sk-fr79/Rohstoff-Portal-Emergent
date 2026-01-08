package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BORDERCROSSING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BORDERCROSSING_EXT;

public class BorderCrossingChecker {

	private Vector<BorderCrossingInfo>   	v_critical_borderCrossings = new Vector<>();
	
	public BorderCrossingChecker(ArrayList<BorderCrossingInfo> al_borderCrossingInfos2Check) throws myException {
		super();
		
		this.v_critical_borderCrossings.clear();
		
		if (al_borderCrossingInfos2Check==null) {
			throw new myException(this,"Error: parameter: al_borderCrossingInfos2Check MUST NOT BE NULL!");
		}
		
//		RECLIST_BORDERCROSSING  rl_bo = new RECLIST_BORDERCROSSING(new SEL(_TAB.bordercrossing).FROM(_TAB.bordercrossing).s());
		// alle Bordercrossing-Definitionen lesen
		BorderCrossing_RecordList rl_bo = new BorderCrossing_RecordList();
		
		
		if (rl_bo.get_recList().size()>0) {
			
			for (BorderCrossingInfo bdc_info: al_borderCrossingInfos2Check) {
			
				for (BorderCrossing_Record  rb: rl_bo.get_recList()) {
					
					BorderCrossingInfo bordCross = bdc_info.get_Copy();     //kopie erzeugen, falls es mehrere meldungen fuer eine warenbewegung gibt
					
					if (rb.must_be_warned(bordCross)) {
						//jetzt nachsehen, ob es den reminder nicht bereits gibt
						if (!rb.reminder_even_exists(bordCross)) { 
							bordCross.set_bordercrossing_that_causes_warning(rb);
							this.v_critical_borderCrossings.add(bordCross);
						}
					}
				}
			}
		}
		
	}

	
	
	
//	public BorderCrossingChecker(ArrayList<BorderCrossingInfo> al_borderCrossingInfos2Check) throws myException {
//		super();
//		
//		this.v_critical_borderCrossings.clear();
//		
//		if (al_borderCrossingInfos2Check==null) {
//			throw new myException(this,"Error: parameter: al_borderCrossingInfos2Check MUST NOT BE NULL!");
//		}
//		
//		
//		RECLIST_BORDERCROSSING  rl_bo = new RECLIST_BORDERCROSSING(new SEL(_TAB.bordercrossing).FROM(_TAB.bordercrossing).s());
//		
//		if (rl_bo.size()>0) {
//			
//			
//			for (BorderCrossingInfo bdc_info: al_borderCrossingInfos2Check) {
//				for (RECORD_BORDERCROSSING  rb: rl_bo) {
//					RECORD_BORDERCROSSING_EXT  rbe = new  RECORD_BORDERCROSSING_EXT(rb);
//					BorderCrossingInfo bordCross = bdc_info.get_Copy();     //kopie erzeugen, falls es mehrere meldungen fuer eine warenbewegung gibt
//					if (rbe.must_be_warned(bordCross)) {
//						//jetzt nachsehen, ob es den reminder nicht bereits gibt
//						if (!rbe.reminder_even_exists(bordCross)) { 
//							bordCross.set_bordercrossing_that_causes_warning(rbe);
//							this.v_critical_borderCrossings.add(bordCross);
//						}
//					}
//				}
//			}
//		}
//		
//	}
	
	
	
	
	/**
	 * liefert die transaktionen, die gewarnt werden muessen aufgrund des grenzuebertritts
	 * @return
	 */
	public Vector<BorderCrossingInfo> get_v_critical_transactions() {
		return v_critical_borderCrossings;
	}

	
	
	
}
