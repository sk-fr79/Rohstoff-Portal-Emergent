package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_bg_land extends Rec20 {

	public Rec20_bg_land() throws myException {
		super(_TAB.land);

	}
	
	public String get_laendercode(String unformated_land_id) throws myException{
		return this._fill_id(unformated_land_id).get_ufs_dbVal(LAND.laendercode, "");
	}

	public String get_laendername(String unformated_land_id) throws myException{
		return this._fill_id(unformated_land_id).get_ufs_dbVal(LAND.laendername, "");
	}

	
}
