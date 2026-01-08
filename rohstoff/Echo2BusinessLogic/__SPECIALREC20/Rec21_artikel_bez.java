/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author sebastien
 * @date 06.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 06.12.2018
 *
 */
public class Rec21_artikel_bez extends Rec21 {

	/**
	 * @author sebastien
	 * @date 06.12.2018
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_artikel_bez() throws myException {
		super(_TAB.artikel_bez);
	}
	
	public Rec21_artikel_bez(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.artikel_bez) {
			throw new myException(this,"Only Record from type ArtieklBez is allowed !");
		}
	}
	
	public Rec21_artikel  __get_rec21_artikel()  throws myException{
		if (this.is_newRecordSet()) {
			
			throw new myException(this,"__get_rec20_artikel is only possible on filled records!");
		}
		
		return new Rec21_artikel(this.get_up_Rec20(ARTIKEL.id_artikel));
	}
	
	
	
	public String __get_ANR1_ANR2_ARTBEZ1() throws myException {
		String c = "";
		Rec21_artikel  ra = this.__get_rec21_artikel();
		c+=ra.get_ufs_dbVal(ARTIKEL.anr1, "<anr1>")+"-"+this.get_ufs_dbVal(ARTIKEL_BEZ.anr2, "<anr2>")+" "+this.get_ufs_dbVal(ARTIKEL_BEZ.artbez1,ra.get_ufs_dbVal(ARTIKEL.artbez1,"<artbez1>" ) );
		return c;
	}

	public String __get_ANR1_ANR2() throws myException {
		String c = "";
		Rec21_artikel  ra = this.__get_rec21_artikel();
		c+=ra.get_ufs_dbVal(ARTIKEL.anr1, "<anr1>")+"-"+this.get_ufs_dbVal(ARTIKEL_BEZ.anr2, "<anr2>");
		return c;
	}	
	public String __get_ANR1() throws myException {
		String c = "";
		Rec21_artikel  ra = this.__get_rec21_artikel();
		c+=ra.get_ufs_dbVal(ARTIKEL.anr1, "<anr1>");
		return c;
	}

	@Override
	public Rec21_artikel_bez _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec21_artikel_bez _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_artikel_bez _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_artikel_bez _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec21_artikel_bez _fill_sql(SqlStringExtended sqlStringExtended) throws myException {
		super._fill_sql(sqlStringExtended);
		return this;
	}

	@Override
	public Rec21 _fill(MyRECORD_IF_RECORDS rec) throws myException {
		return super._fill(rec);
	}	

	
	public Rec21_artikel _getRec21Artikel() throws myException {
		return new Rec21_artikel(this.get_up_Rec21(ARTIKEL.id_artikel));
	}
	
	
	public Rec21 getEinheit() {
		try {
			return this.__get_rec21_artikel().get_up_Rec21(EINHEIT.id_einheit);
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
