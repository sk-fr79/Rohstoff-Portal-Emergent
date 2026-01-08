package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;

public class Rec20_station extends Rec20 {

	
	private Rec20 rec_adresse = null;
	
	public Rec20_station(Rec20 rec_station) throws myException {
		super(rec_station);
		this.rec_adresse = this.get_up_rec20(ADRESSE.id_adresse);
	}

	public Rec20 __rec_adresse() {
		return rec_adresse;
	}

	
	/**
	 * 
	 * @return
	 * @throws myException 
	 */
	public MyE2_String info_string_station() throws myException {
		MyE2_String c  = new MyE2_String("");
		
		Rec20 adress = this.get_up_rec20(ADRESSE.id_adresse);
		
		if (adress!=null && adress.size()>0) {
		
			if (S.isFull(adress.get_ufs_dbVal(ADRESSE.sonderlager,""))) {
				c=new MyE2_String(ENUM_SONDERLAGER.find_SonderlagerFromDBValue(adress.get_ufs_dbVal(ADRESSE.sonderlager,"")).user_text());
			} else {
				c = new MyE2_String(adress.get_ufs_kette(" ",ADRESSE.name1,ADRESSE.name2,ADRESSE.ort),false);
			}
		
		} else {
			c=new MyE2_String("<Station nicht definiert>");
		}
		
		return c;
	}

	
	/**
	 * 
	 * @return
	 * @throws myException 
	 */
	public boolean is_reale_adresse() throws myException {
		boolean b_rueck = true;
		
		Rec20 adress = this.get_up_rec20(ADRESSE.id_adresse);
		
		if (adress!=null && adress.size()>0) {
		
			if (S.isFull(adress.get_ufs_dbVal(ADRESSE.sonderlager,""))) {
				b_rueck = false;
			}
		} else {
			b_rueck = false;
		}
		
		return b_rueck;
	}

	
	
}
