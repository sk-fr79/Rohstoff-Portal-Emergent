package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE;

import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ABFRAGE_FELD implements IF_enum_4_db {
	DEF_QUELLE_ZIEL("DEF_QUELLE_ZIEL"),
	ID_VPOS_TPA_FUHRE("ID_VPOS_TPA_FUHRE"),
	ID_VPOS_TPA_FUHRE_ORT("ID_VPOS_TPA_FUHRE_ORT"),
	ID_ADRESSE_STATION("ID_ADRESSE_STATION"),
	ID_ARTIKEL("ID_ARTIKEL"),
	PLAN("PLAN"),
	MENGE_BRUTTO("MENGE_BRUTTO"),
	ABZUG_MENGE("ABZUG_MENGE"),
	EPREIS("EPREIS"),
	EPREIS_RESULT("EPREIS_RESULT"),
	ANR1_2("ANR1_2"),
	ART_BEZ1("ART_BEZ1"),
	BUCHUNGSNUMMER("BUCHUNGSNUMMER"),
	DATUM("DATUM"),
	BUCHUNG_STATUS("BUCHUNG_STATUS"),
	A_ORT("A_ORT"),
	A_FIRMA("A_FIRMA"),
	ID_VKOPF_RG("ID_VKOPF_RG")
	;
	
	private String dbFeld="";
	
	private ABFRAGE_FELD(String oDbFeld) {		
		this.dbFeld = oDbFeld;
	}
	
	@Override
	public String db_val() {
		// TODO Auto-generated method stub
		return dbFeld;
	}

	@Override
	public String user_text() {
		return null;
	}

	@Override
	public String user_text_lang() {
		return null;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ABFRAGE_FELD.values(), emptyPairInFront);
	}
	
	public static String getAllFeldIn_1_String() throws myException{
		Vector<String> feldvector = new Vector<String>();

		for(ABFRAGE_FELD f: ABFRAGE_FELD.values()){
			feldvector.add(f.db_val());
		}
		return bibALL.Concatenate(feldvector, ",", "");
	}

}
