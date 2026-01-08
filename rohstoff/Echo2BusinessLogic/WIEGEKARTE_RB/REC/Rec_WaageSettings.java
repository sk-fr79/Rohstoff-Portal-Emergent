/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 12.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 12.03.2020
 *
 */
public class Rec_WaageSettings extends Rec22 {

	/**
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec_WaageSettings() throws myException {
		super(_TAB.waage_settings);
	}

	
	/**
	 * Copy Konstruktor
	 * 
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec_WaageSettings(Rec22 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.waage_settings) {
			throw new myException(this,"Only Record from type waage_settings is allowed !");
		}
	}

	public Rec_WaageSettings(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.waage_settings) {
			throw new myException(this,"Only Record from type waage_settings is allowed !");
		}
	}

	
	@Override
	public Rec_WaageSettings _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}


	@Override
	public Rec_WaageSettings _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_WaageSettings _fill_id(String id) throws myException {
		// TODO Auto-generated method stub
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_WaageSettings _fill(Rec22 baseRec) throws myException {
		// TODO Auto-generated method stub
		if (baseRec.get_tab() != _TAB.waage_settings) {
			throw new myException(this,"Only Record from type Waegung is allowed !");
		} else {
			super._fill(baseRec);
		}
		return this;
	}


	@Override
	public Rec_WaageSettings _fill(MyRECORD_IF_RECORDS rec) throws myException {
		if (rec.get_TABLENAME() != _TAB.waage_settings.fullTableName() ) {
			throw new myException(this,"Only Record from type waage_settings is allowed !");
		} else {
			super._fill(rec);
			return this;
		}
	}
	
	

	
	
	
	
	
	
	
}
