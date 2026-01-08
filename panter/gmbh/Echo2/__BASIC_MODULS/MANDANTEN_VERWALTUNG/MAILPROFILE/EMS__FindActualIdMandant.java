package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.indep.exceptions.myException;

public class EMS__FindActualIdMandant {
	
	private String cID_Mandant_uf = null;

	public EMS__FindActualIdMandant() throws myException{
		super();
		Object foundTS = TS_Treasure_CONST.find_TreasureChest(TS_DEFINITION.MAIL_PROFILE_INLAY_IN_MANDANTENMASK);
		if (foundTS!=null) {
			this.cID_Mandant_uf = (String)((TS_Treasure_Chest) foundTS).get_TREASURE_CHEST();
		} else {
			throw new myException(this,"Error finding actual ID_MANDANT !!!");
		}
	}

	public String get_ID_MANDANT_UF() {
		return cID_Mandant_uf;
	}
	
	
	
	
}
