package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_ADRESSE_UST_ID_ext extends RECORD_ADRESSE_UST_ID {

	public RECORD_ADRESSE_UST_ID_ext() throws myException {
		super();
	}

	public RECORD_ADRESSE_UST_ID_ext(long lID_Unformated, MyConnection Conn) throws myException {
		super(lID_Unformated, Conn);
	}

	public RECORD_ADRESSE_UST_ID_ext(long lID_Unformated) throws myException {
		super(lID_Unformated);
	}

	public RECORD_ADRESSE_UST_ID_ext(MyConnection Conn) throws myException {
		super(Conn);
	}

	public RECORD_ADRESSE_UST_ID_ext(MyRECORD recordOrig) {
		super(recordOrig);
	}

	public RECORD_ADRESSE_UST_ID_ext(RECORD_ADRESSE_UST_ID recordOrig) {
		super(recordOrig);
	}

	public RECORD_ADRESSE_UST_ID_ext(String c_ID_or_WHEREBLOCK_OR_SQL,MyConnection Conn) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_ADRESSE_UST_ID_ext(String c_ID_or_WHEREBLOCK_OR_SQL)	throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	public Vector<MyE2_String> check_formal_korrekt() throws myException {
		Vector<MyE2_String> v_fehler = new Vector<MyE2_String>();
		
		RECORD_LAND  rl = 			this.get_UP_RECORD_LAND_id_land();
//		RECORD_ADRESSE_extend ad = new RECORD_ADRESSE_extend(this.get_UP_RECORD_ADRESSE_id_adresse()); 
		
		if (rl==null) {
			v_fehler.add(new MyE2_String("Ein Zusatz-UST-ID-Eintrag der Adresse besitzt kein Land !!",true,"Code: "+this.get_UMSATZSTEUERLKZ_cF_NN("-"),false ));
		} else {
		
			//wenn die UST-ID den schalter "nur fuer rc-Sorten" traegt, dann kann eine UST-ID aus einem anderen Land gültig sein
			if (this.is_BEGRENZT_AUF_RC_SORTEN_NO()) {
				if (!rl.get_UST_PRAEFIX_cUF_NN("-1").equals(this.get_UMSATZSTEUERLKZ_cF_NN("-2"))) {
					v_fehler.add(new MyE2_String("Ein Zusatz-UST-ID-Eintrag der Adresse (für alle Sorten) hat ein Länderkürzel, das nicht zum Länderstamm-Eintrag passt:",true,this.get_UMSATZSTEUERLKZ_cF_NN("<-->"),false));
				}
			}
		}
		
		return v_fehler;
	}
	
	
	
	
	
}
