package panter.gmbh.basics4project.SANKTION;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public enum ENUM_SANKTION_ENCODE_MAP implements IF_enum_4_db{
	/**N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, N11, N12*/
	LISTN(
			new VEK<String>()._a("LISTN.NAME")
//			._a("LISTN.N1")
//			._a("LISTN.N2")
//			._a("LISTN.N3")
//			._a("LISTN.N4")
//			._a("LISTN.N5")
//			._a("LISTN.N6")
//			._a("LISTN.N7")
//			._a("LISTN.N8")
//			._a("LISTN.N9")
//			._a("LISTN.N10")
//			._a("LISTN.N11")
//			._a("LISTN.N12")
			),

	/**PASSNO */
	LISTP(
			new VEK<String>()
//			._a("LISTP.ID")
//			._a("LISTP.IDP")
			._a("LISTP.PASSNO")
			),

	/**STREET, CITY, COUNTRY */
	LISTA(
			new VEK<String>()
//			._a("LISTA.ID")
//			._a("LISTA.IDA")
			._a("LISTA.STREET")
			._a("LISTA.CITY")
			._a("LISTA.COUNTRY")
			),

	/**ID, NAME1, NAME2, NAME3, VORNAME, STRASSE, HAUSNUMMER, ORT, AUSWEIS_NUMMER, ISO_COUNTRY_CODE*/
	ADRESS(
			new VEK<String>()._a(ADRESSE.name1.fn())._a(ADRESSE.name2.fn())._a(ADRESSE.name3.fn())._a(ADRESSE.vorname.fn())
			._a(ADRESSE.strasse.fn())._a(ADRESSE.hausnummer.fn())._a(ADRESSE.ort.fn())._a(ADRESSE.ausweis_nummer.fn())
			._a(LAND.iso_country_code.fn())
			);
//;
	
	private VEK<String> vFields;
	
	private ENUM_SANKTION_ENCODE_MAP(VEK<String> map_value) {
		this.vFields = map_value;
	}

	public String get_tablename() {
		return name();
	}

	public String[] get_fieldList() {
		String[] rueck = new String[vFields.size()];
		for(int i=0; i<vFields.size();i++) {
			rueck[i] = vFields.get(i);
		}
		return rueck;
	}

	@Override
	public String db_val() {
		return name();
	}

	@Override
	public String user_text() {
		String rueck ="";
		for(String f : vFields) {
			rueck = rueck + ","+f;
		}
		rueck = rueck.replaceFirst(",","");
		return rueck;
	}
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_SANKTION_ENCODE_MAP.values(), emptyPairInFront);
	}
}
