/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Rec21_waehrung extends Rec21 {

	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_waehrung() throws myException {
		super(_TAB.waehrung);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_waehrung(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.waehrung) {
			throw new myException("baseRec is false type !");
		}
	}

	
	/**
	 * 
	 * @return s true, wenn record represents the system-currency of mandant 
	 * @throws myException
	 */
	public boolean isBaseCurrency() throws myException {
		return this.getId()==bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_lValue(-1l).longValue();
	}
	
	/**
	 * 
	 * @return s true, wenn record represents the system-currency of mandant 
	 * @throws myException
	 */
	public boolean isForeignCurrency() throws myException {
		return !this.isBaseCurrency();
	}
	

	/**
	 * 
	 * @return s currency symbol or the notnull shortname
	 * @throws myException
	 */
	public String getSymbol() throws myException {
		return this.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung);
	}

	/**
	 * 
	 * @return s currency symbol or the notnull shortname
	 * @throws myException
	 */
	public String getSymbol(String onErrorOrNull) {
		String s = onErrorOrNull;
		try {
			s = S.NN(this.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung),onErrorOrNull);
		} catch (myException e) {
			e.printStackTrace();
		}
		return s;
	}

	
	@Override
	public Rec21_waehrung _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_waehrung _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
}
