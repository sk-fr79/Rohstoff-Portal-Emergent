package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_bewegung extends Rec20 {

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_bewegung(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	/**
	 * @throws myException
	 */
	public Rec20_bewegung() throws myException {
		super(_TAB.bewegung);
	}

	
	public Rec20_bewegung _fill_id(String id) throws myException {
		super._fill_id(id);
//		MyLong lid = new MyLong(id);
//		if (lid.get_bOK()) {
//			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+lid.get_cUF_LongString());
//		} else {
//			throw new myException(this,"Error ID "+id+" is no number !");
//		}
		return this;
	}



	
	
	
}
