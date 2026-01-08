package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

public class Rec21_user extends Rec21 {


	public Rec21_user() throws myException {
		super(_TAB.user);
	}


	public Rec21_user(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.user) {
			throw new myException(this,"Only Record from type USER are allowed !");
		}
	}


	//fill-methodes
	@Override
	public Rec21_user _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec21_user _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_user _fill_sql(SqlStringExtended sqlExt) throws myException {
		super._fill_sql(sqlExt);
		return this;
	}


	@Override
	public Rec21_user _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_user _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec21_user _fill(MyRECORD_IF_RECORDS rec) throws myException {
		super._fill(rec);
		return this;
	}


	public String getVal4DropDown() throws myException {
		String c = "";
		c= this.get_ufs_kette(", ", USER.name1,USER.vorname);
		
		c+= "("+this.getUfs(USER.kuerzel, this.getFs(USER.id_user))+")"; 
		return c;
	}

}
