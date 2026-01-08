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
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 20.11.2018
 *
 */
public class RecV extends RB_Dataobject_V21 {

	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(_TAB.bg_vektor.n())._setHASHKEY("RecV@398a6b4e-3063-4541-8c24-7aa2789a2b58");
	public static _TAB ownTAB = _TAB.bg_vektor;

	
	public RecV(MASK_STATUS status) throws myException {
		super(_TAB.bg_vektor,status);
	}

	public RecV(Rec21 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=_TAB.bg_vektor) {
			throw new myException("RecV can only by of TABLE bg_atom");
		}
	}
	
	public RecV(Long id, MASK_STATUS status) throws myException {
		super(_TAB.bg_vektor,id,status);
	}

	
	
	@Override
	public RecV _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


}
