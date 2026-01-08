/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 16.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 16.03.2020
 *
 */
public class RecDOWaegung2 extends RecDOWaegung {
	private static final _TAB table = _TAB.waegung;

	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(table.n())._setHASHKEY("RecWaegung2@a0f2b7d4-3d69-41ea-848b-5a1a78eceb5e");

	
	public RecDOWaegung2( Long id, MASK_STATUS status) throws myException {
		super(table, id, status,2);
	}

	public RecDOWaegung2( MASK_STATUS status) throws myException {
		super(status,2);
	}

	public RecDOWaegung2(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status,2);
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWaegung2 can only by of TABLE " + table.baseTableName() );
		}
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWaegung#_fill_from_wiegekarte(rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWiegekarte)
	 */
	@Override
	public RecDOWaegung2 _fill_from_wiegekarte(RecDOWiegekarte oWk) throws myException {
		return (RecDOWaegung2) super._fill_from_wiegekarte(oWk);
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWaegung#_fill_from_wiegekarte(long)
	 */
	@Override
	public RecDOWaegung2 _fill_from_wiegekarte(long id) throws myException {
		
		return (RecDOWaegung2) super._fill_from_wiegekarte(id);
	}
	
}
