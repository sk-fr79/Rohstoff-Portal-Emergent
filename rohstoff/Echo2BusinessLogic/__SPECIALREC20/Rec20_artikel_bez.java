package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_artikel_bez extends Rec20 {

	public Rec20_artikel_bez() throws myException {
		super(_TAB.artikel_bez);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_artikel_bez(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	@Override
	public Rec20_artikel_bez _fill_id(String id) throws myException {
//		MyLong lid = new MyLong(id);
//		if (lid.get_bOK()) {
//			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+lid.get_cUF_LongString());
//		} else {
//			throw new myException(this,"Error ID "+id+" is no number !");
//		}
		super._fill_id(id);
		return this;
	}
	
	
	@Override
	public Rec20_artikel_bez _fill_id(long id) throws myException {
		super._fill_id(id);
		//this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+id);
		return this;
	}
	

	
	public Rec20_artikel  __get_rec20_artikel()  throws myException{
		if (this.is_newRecordSet()) {
			
			throw new myException(this,"__get_rec20_artikel is only possible on filled records!");
		}
		
		return new Rec20_artikel(this.get_up_Rec20(ARTIKEL.id_artikel));
	}
	
	
	
	public String __get_ANR1_ANR2_ARTBEZ1() throws myException {
		String c = "";
		Rec20_artikel  ra = this.__get_rec20_artikel();
		c+=ra.get_ufs_dbVal(ARTIKEL.anr1, "<anr1>")+"-"+this.get_ufs_dbVal(ARTIKEL_BEZ.anr2, "<anr2>")+" "+this.get_ufs_dbVal(ARTIKEL_BEZ.artbez1,ra.get_ufs_dbVal(ARTIKEL.artbez1,"<artbez1>" ) );
		return c;
	}

	public String __get_ANR1_ANR2() throws myException {
		String c = "";
		Rec20_artikel  ra = this.__get_rec20_artikel();
		c+=ra.get_ufs_dbVal(ARTIKEL.anr1, "<anr1>")+"-"+this.get_ufs_dbVal(ARTIKEL_BEZ.anr2, "<anr2>");
		return c;
	}	
}
