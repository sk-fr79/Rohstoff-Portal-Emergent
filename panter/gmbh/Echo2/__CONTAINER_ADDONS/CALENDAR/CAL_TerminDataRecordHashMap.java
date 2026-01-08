package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.MyQueryBUILDER;
import panter.gmbh.indep.exceptions.myException;

public class CAL_TerminDataRecordHashMap extends MyDataRecordHashMap 
{
	private String cID_TERMIN_USER = null;
	
	public CAL_TerminDataRecordHashMap(String cID_TERMIN_USER_unformated)  throws myException
	{
		super();
		this.cID_TERMIN_USER = cID_TERMIN_USER_unformated;
		
		MyQueryBUILDER  oQuerys = new MyQueryBUILDER("JT_TERMIN","ID_TERMIN",null,bibE2.get_FIELDVECTOR_EXCLUDE(),bibE2.get_CurrSession());
		oQuerys.add_Table("JT_TERMIN_USER","ID_TERMIN_USER",null,"B_",bibE2.get_FIELDVECTOR_EXCLUDE(),"JT_TERMIN.ID_TERMIN=JT_TERMIN_USER.ID_TERMIN");

		this.INIT_Hash(oQuerys.get_SelectBlock(),oQuerys.get_FromBlock(),oQuerys.get_WhereBlock("JT_TERMIN_USER.ID_TERMIN_USER="+cID_TERMIN_USER_unformated));
		
	}

	public String get_cID_TERMIN_USER() 
	{
		return cID_TERMIN_USER;
	}

	public String get_cID_TERMIN() throws myException
	{
		return this.get_UnFormatedValue("ID_TERMIN");
	}

}
