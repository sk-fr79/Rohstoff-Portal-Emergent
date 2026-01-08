package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECLIST_USER_FAHRER extends RECLIST_USER
{

	public RECLIST_USER_FAHRER(MyConnection Conn,int MaxNumberOfRecords, String ownKeyField) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y' AND NVL(AKTIV,'N')='Y'", Conn, MaxNumberOfRecords, ownKeyField);
	}

	public RECLIST_USER_FAHRER( MyConnection Conn,int MaxNumberOfRecords) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y' AND NVL(AKTIV,'N')='Y'", Conn, MaxNumberOfRecords);
	}

	public RECLIST_USER_FAHRER( MyConnection Conn) throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y' AND NVL(AKTIV,'N')='Y'", Conn);
	}

	public RECLIST_USER_FAHRER() throws myException
	{
		super("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y' AND NVL(AKTIV,'N')='Y'");
	}


}
