/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.REC
 * @author manfred
 * @date 12.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 12.03.2020
 *
 */
public class Rec_WaageUser extends Rec22 {

	/**
	 * @author manfred
	 * @date 12.03.2020
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec_WaageUser() throws myException {
		super(_TAB.waage_user);
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
	public Rec_WaageUser(Rec22 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() !=  this.get_tab() ) {
			throw new myException(this,"Only Record from type " + this.get_TABLENAME() +" is allowed !");
		}
	}

	 
	
	@Override
	public Rec_WaageUser _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}


	@Override
	public Rec_WaageUser _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_WaageUser _fill_id(String id) throws myException {
		// TODO Auto-generated method stub
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_WaageUser _fill(Rec22 baseRec) throws myException {
		// TODO Auto-generated method stub
		if (baseRec.get_tab() != this.get_tab()) {
			throw new myException(this,"Only Record from type " + this.get_TABLENAME() + " is allowed !");
		} else {
			super._fill(baseRec);
		}
		return this;
	}


	@Override
	public Rec_WaageUser _fill(MyRECORD_IF_RECORDS rec) throws myException {
		if (rec.get_TABLENAME() != this.get_tab().fullTableName() ) {
			throw new myException(this,"Only Record from type " + this.get_TABLENAME() + " is allowed !");
		} else {
			super._fill(rec);
			return this;
		}
	}
	
	

	
	
	
	
	
	
	
}
