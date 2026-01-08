package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;

public class RECORD_BEWEGUNG_STATION_ext extends RECORD_BEWEGUNG_STATION {

	/**
	 * @param recordOrig
	 */
	public RECORD_BEWEGUNG_STATION_ext(RECORD_BEWEGUNG_STATION recordOrig) {
		super(recordOrig);
	}

	
	
	/**
	 * 
	 * @return
	 * @throws myException 
	 */
	public MyE2_String info_string_station() throws myException {
		MyE2_String c  = new MyE2_String("");
		
		RECORD_ADRESSE adress = this.get_UP_RECORD_ADRESSE_id_adresse();
		
		if (adress!=null) {
		
			if (S.isFull(this.get_UP_RECORD_ADRESSE_id_adresse().get_SONDERLAGER_cUF_NN(""))) {
				c=new MyE2_String(ENUM_SONDERLAGER.find_SonderlagerFromDBValue(this.get_UP_RECORD_ADRESSE_id_adresse().get_SONDERLAGER_cUF_NN("")).user_text());
			} else {
				c = new MyE2_String(adress.get___KETTE(ADRESSE.name1,ADRESSE.name2,ADRESSE.ort),false);
			}
		
		} else {
			c=new MyE2_String("<Station nicht definiert>");
		}
		
		return c;
	}
	
	
}
