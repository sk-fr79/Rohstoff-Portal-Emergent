package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class ES_RECORD_ADRESSE extends RECORD_ADRESSE {

	private MyE2_String beschriftung = null;
	private MyE2_String titel = null;
	
	public ES_RECORD_ADRESSE(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
		this.beschriftung = new MyE2_String(this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2,_DB.ADRESSE$ORT)),false);
	}

	
	
	public ES_RECORD_ADRESSE(MyRECORD recordOrig) throws myException {
		super(recordOrig);
		this.beschriftung = new MyE2_String(this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2,_DB.ADRESSE$ORT)),false);
	}



	public ES_RECORD_ADRESSE(String c_ID_or_WHEREBLOCK_OR_SQL,MyE2_String p_beschriftung) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
		
		this.beschriftung = p_beschriftung;
	}


	public MyE2_String get__beschriftung() {
		return beschriftung;
	}



	public void set__beschriftung(MyE2_String p_beschriftung) {
		this.beschriftung = p_beschriftung;
	}



	public MyE2_String get__titel() {
		return titel;
	}



	public void set__titel(MyE2_String p_titel) {
		this.titel = p_titel;
	}

	
}
