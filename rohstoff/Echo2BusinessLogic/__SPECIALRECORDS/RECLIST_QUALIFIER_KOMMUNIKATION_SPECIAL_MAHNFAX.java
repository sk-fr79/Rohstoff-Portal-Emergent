package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CONST;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS__QUALIFIER_GRID_4_TELEFON;

public class RECLIST_QUALIFIER_KOMMUNIKATION_SPECIAL_MAHNFAX extends RECLIST_QUALIFIER
{
	/**
	 * 
	 * @param cID_KOMMUNIKATION
	 * @throws myException
	 */
	public RECLIST_QUALIFIER_KOMMUNIKATION_SPECIAL_MAHNFAX(String cID_KOMMUNIKATION) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_QUALIFIER WHERE "
				+RECORD_QUALIFIER.FIELD__CLASS_KEY+"="+bibALL.MakeSql(Q_QUALIFIER_KEYS.QUALIFIER_KEY_KOMMUNIKATION_VERWENDUNG)
				+" AND "
				+RECORD_QUALIFIER.FIELD__TABLENAME+"="+bibALL.MakeSql(Q_DB_CONST.get_TABLE_NAME("JT_KOMMUNIKATION"))
				+" AND "
				+RECORD_QUALIFIER.FIELD__DATENBANKTAG+"="+bibALL.MakeSql(FS__QUALIFIER_GRID_4_TELEFON.QU_TELEFON_TYP_4_MAHNUNG_FAX)
				+" AND "
				+RECORD_QUALIFIER.FIELD__ID_TABLE+"="+cID_KOMMUNIKATION);
	}

}
