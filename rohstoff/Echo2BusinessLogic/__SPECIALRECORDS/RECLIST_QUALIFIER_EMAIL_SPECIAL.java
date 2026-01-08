package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CONST;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class RECLIST_QUALIFIER_EMAIL_SPECIAL extends RECLIST_QUALIFIER
{
	/**
	 * 
	 * @param cID_EMAIL
	 * @throws myException
	 */
	public RECLIST_QUALIFIER_EMAIL_SPECIAL(String cID_EMAIL) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_QUALIFIER WHERE "
				+RECORD_QUALIFIER.FIELD__CLASS_KEY+"="+bibALL.MakeSql(Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP)
				+" AND "
				+RECORD_QUALIFIER.FIELD__TABLENAME+"="+bibALL.MakeSql(Q_DB_CONST.get_TABLE_NAME("JT_EMAIL"))
				+" AND "
				+RECORD_QUALIFIER.FIELD__ID_TABLE+"="+cID_EMAIL);
	}

	
	
	public RECLIST_QUALIFIER_EMAIL_SPECIAL(String cID_EMAIL,String cMAIL_TYP) throws myException {
		super("SELECT * FROM "+bibE2.cTO()+"."+_DB.QUALIFIER+" WHERE "
				+_DB.QUALIFIER$CLASS_KEY+"="+bibALL.MakeSql(Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP)
				+" AND "
				+_DB.QUALIFIER$TABLENAME+"="+bibALL.MakeSql(Q_DB_CONST.get_TABLE_NAME(_DB.EMAIL))
				+" AND "
				+_DB.QUALIFIER$DATENBANKTAG+"="+bibALL.MakeSql(cMAIL_TYP)
				+" AND "
				+_DB.QUALIFIER$ID_TABLE+"="+cID_EMAIL
				);
	
	}
	
	
}
