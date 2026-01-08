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
public class RecDOWaegung1 extends RecDOWaegung {
	
	private static final _TAB table = _TAB.waegung;
	
	public static RB_KM key = (RB_KM)new RB_KM()._setREALNAME(table.n())._setHASHKEY("RecWaegung1@20f21205-38f0-46d7-a952-cfa2542cf9f4");

	
	public RecDOWaegung1(_TAB p_tab, Long id, MASK_STATUS status) throws myException {
		super(table, id, status,1);
	}

	public RecDOWaegung1( MASK_STATUS status) throws myException {
		super( status,1);
	}

	public RecDOWaegung1(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec, status,1);
		if (baseRec.get_tab()!=table) {
			throw new myException("RecWaegung1 can only by of TABLE " + table.baseTableName() );
		}
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWaegung#_fill_from_wiegekarte(long)
	 */
	@Override
	public RecDOWaegung1 _fill_from_wiegekarte(long id) throws myException {
		// TODO Auto-generated method stub
		return (RecDOWaegung1) super._fill_from_wiegekarte(id);
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWaegung#_fill_from_wiegekarte(rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecWiegekarte)
	 */
	@Override
	public RecDOWaegung1 _fill_from_wiegekarte(RecDOWiegekarte oWk) throws myException {
		
		return (RecDOWaegung1) super._fill_from_wiegekarte(oWk);
	}
	
}
