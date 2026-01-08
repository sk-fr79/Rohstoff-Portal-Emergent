/**
 * rohstoff.businesslogic.bewegung.mask.recs
 * @author martin
 * @date 20.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.recs;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 20.11.2018
 *
 */
public class RecS1 extends RB_Dataobject_V21 implements IF_Station {
	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(_TAB.bg_station.n())._setHASHKEY("RecS1@71e4ad03-288b-41a4-95b2-7cc520f5d867");

	public RecS1(MASK_STATUS status) throws myException {
		super(_TAB.bg_station,status);
	}

	public RecS1(Rec21 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=_TAB.bg_station) {
			throw new myException("RecS1 can only by of TABLE bg_station");
		}
	}
	
	public RecS1(Long id, MASK_STATUS status) throws myException {
		super(_TAB.bg_station,id,status);
	}

	
	@Override
	public RecS1 _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	
}
