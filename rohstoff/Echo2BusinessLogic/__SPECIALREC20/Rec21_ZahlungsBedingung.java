/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 27.11.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.11.2019
 *
 */
public class Rec21_ZahlungsBedingung extends Rec21 {

	/**
	 * @author martin
	 * @date 27.11.2019
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_ZahlungsBedingung() throws myException {
		super(_TAB.zahlungsbedingungen);
	}

	/**
	 * @author martin
	 * @date 27.11.2019
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_ZahlungsBedingung(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.zahlungsbedingungen) {
			throw new myException("Error! only Rec21 based on table zahlungsbedingungen can be used !");
		}
	}

	@Override
	public Rec21_ZahlungsBedingung _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec21_ZahlungsBedingung _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_ZahlungsBedingung _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_ZahlungsBedingung _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec21_ZahlungsBedingung _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}

	@Override
	public Rec21_ZahlungsBedingung _SAVE(boolean b_commit, MyE2_MessageVector mv_from_call) throws myException {
		super._SAVE(b_commit, mv_from_call);
		return this;
	}

	
	
	
	
}
