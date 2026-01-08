package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;


public class Rec_container extends Rec22 {

	
	public Rec_container() throws myException {
		super(_TAB.container);
	}
	
	public Rec_container(Rec22 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.container) {
			throw new myException(this,"Only Record from type Container is allowed !");
		}
	}
	
	
	

	@Override
	public Rec_container _fill(Rec22 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec_container _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_container _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec_container _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec_container _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}

	@Override
	public Rec22 _fill(MyRECORD_IF_RECORDS rec) throws myException {
		return super._fill(rec);
	}

	
	
}
