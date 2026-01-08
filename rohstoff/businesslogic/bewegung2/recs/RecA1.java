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
public class RecA1 extends RB_Dataobject_V21 {

	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(_TAB.bg_atom.n())._setHASHKEY("RecA1@09aadd4e-ece4-11e8-8eb2-f2801f1b9fd1");

	public RecA1(MASK_STATUS status) throws myException {
		super(_TAB.bg_atom,status);
	}

	public RecA1(Rec21 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status);
		if (baseRec.get_tab()!=_TAB.bg_atom) {
			throw new myException("RecA1 can only by of TABLE bg_atom");
		}
	}
	
	public RecA1(Long id, MASK_STATUS status) throws myException {
		super(_TAB.bg_atom,id,status);
	}

	@Override
	public RecA1 _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}


	
	
	

} 
