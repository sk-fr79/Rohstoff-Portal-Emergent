package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_ARTIKEL_BEZ_ext extends RECORD_ARTIKEL_BEZ {

	public RECORD_ARTIKEL_BEZ_ext() throws myException {
		super();
	}

	public RECORD_ARTIKEL_BEZ_ext(long lID_Unformated, MyConnection Conn) throws myException {
		super(lID_Unformated, Conn);
	}

	public RECORD_ARTIKEL_BEZ_ext(long lID_Unformated) throws myException {
		super(lID_Unformated);
	}

	public RECORD_ARTIKEL_BEZ_ext(MyConnection Conn) throws myException {
		super(Conn);
	}

	public RECORD_ARTIKEL_BEZ_ext(MyRECORD recordOrig) {
		super(recordOrig);
	}

	public RECORD_ARTIKEL_BEZ_ext(RECORD_ARTIKEL_BEZ recordOrig) {
		super(recordOrig);
	}

	public RECORD_ARTIKEL_BEZ_ext(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_ARTIKEL_BEZ_ext(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	
	
	
	public String get__complete_anr1_anr2_artbez1() throws myException {
		RECORD_ARTIKEL ra = this.get_UP_RECORD_ARTIKEL_id_artikel();
		
		Vector<String>  v_s = new Vector<String>();
		v_s.add(ra.get_ANR1_cUF_NN(""));
		v_s.add(this.get_ANR2_cUF_NN(""));
		v_s.add(this.get_ARTBEZ1_cUF_NN(ra.get_ARTBEZ1_cUF_NN("")));
		
		return bibTEXT.concat(v_s);
		
		
	}
	
	
}
