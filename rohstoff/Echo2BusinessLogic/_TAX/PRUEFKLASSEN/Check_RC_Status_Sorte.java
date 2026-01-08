package rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * klasse sucht in einem land nach der zuordnung dieser sorte zur RC-tabelle
 * @author martin
 *
 */
public class Check_RC_Status_Sorte {
	
	private String id_LAND_UF = "";
	private String id_ARTIKEL_UF = "";
	
	private boolean is_RC = false;

	public boolean is_RC() {
		return this.is_RC;
	}


	public Check_RC_Status_Sorte(String idLAND_UF, String idARTIKEL_UF) throws myException {
		super();
		this.id_LAND_UF = idLAND_UF;
		this.id_ARTIKEL_UF = idARTIKEL_UF;
		
		String cSQL = "SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.LAND_RC_SORTEN+" WHERE "+_DB.Z_LAND_RC_SORTEN$ID_LAND+"="+this.id_LAND_UF+
						" AND "+_DB.Z_LAND_RC_SORTEN$ID_ARTIKEL+"="+this.id_ARTIKEL_UF;
		
		MyLong oLong = new MyLong(bibDB.EinzelAbfrage(cSQL));
		
		if (!oLong.get_bOK()) {
			throw new myException(this,"Error check of combination id_land/id_artikel in "+_DB.LAND_RC_SORTEN);
		}
		
		if (oLong.get_lValue()<0 || oLong.get_lValue()>1) {
			throw new myException(this,"result-error on check of combination id_land/id_artikel in "+_DB.LAND_RC_SORTEN);
		}
		
		if (oLong.get_lValue()==0) {
			this.is_RC=false;
		} else {
			this.is_RC=true;
		}
	}
	
	
}
