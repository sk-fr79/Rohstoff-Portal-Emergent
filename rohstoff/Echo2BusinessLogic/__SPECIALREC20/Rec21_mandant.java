/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 27.05.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.05.2019
 *
 */
public class Rec21_mandant extends Rec21 {

	private Rec21_adresse  recAdresseMandant = null;
	
	
	/**
	 * @author martin
	 * @date 27.05.2019
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_mandant() throws myException {
		super(_TAB.mandant);
	}

	public Rec21_mandant(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	
	/**
	 * returns Rec21_adresse from mandant
	 * @author martin
	 * @date 27.05.2019
	 *
	 * @return
	 */
	public Rec21_adresse getRecAdresseMandant() {
		try {
			if (this.getLongDbValue(MANDANT.eigene_adress_id)!=null && this.recAdresseMandant==null) {
				this.recAdresseMandant = new Rec21_adresse()._fill_id(this.getLongDbValue(MANDANT.eigene_adress_id));
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return this.recAdresseMandant;
		
	}

	@Override
	public Rec21_mandant _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec21_mandant _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_mandant _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_mandant _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec21_mandant _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}

	@Override
	public Rec21_mandant _fill(MyRECORD_IF_RECORDS rec) throws myException {
		super._fill(rec);
		return this;
	}

	@Override
	public Rec21_mandant _rebuildRecord() throws myException {
		super._rebuildRecord();
		return this;
	}

	@Override
	public Rec21_mandant _nv(IF_Field f, String formated_value, MyE2_MessageVector mv) throws myException {
		super._nv(f, formated_value, mv);
		return this;
	}
	
	
}
