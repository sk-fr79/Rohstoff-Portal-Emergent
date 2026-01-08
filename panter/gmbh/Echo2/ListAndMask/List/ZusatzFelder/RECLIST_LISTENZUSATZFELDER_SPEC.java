package panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LISTEN_ZUSATZFELDER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



public class RECLIST_LISTENZUSATZFELDER_SPEC extends RECLIST_LISTEN_ZUSATZFELDER
{
	private String cMODULKENNER_LISTE = null;

	public RECLIST_LISTENZUSATZFELDER_SPEC(String MODULKENNER_LISTE, boolean bNurAktive) throws myException
	{
		super("KENNER_LISTENMODUL="+bibALL.MakeSql(MODULKENNER_LISTE)+(bNurAktive?" AND NVL(AKTIV,'N')='Y'":""), "BESCHRIFTUNG_LISTE");
		this.cMODULKENNER_LISTE = MODULKENNER_LISTE;
	}

	public String get_cMODULKENNER_LISTE()
	{
		return cMODULKENNER_LISTE;
	}
	
	
}
