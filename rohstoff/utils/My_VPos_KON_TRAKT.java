package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class My_VPos_KON_TRAKT extends MyDataRecordHashMap 
{
	
	public My_VPos_KON_TRAKT(String cID_VPOS_KON) throws myException 
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+cID_VPOS_KON);
	}

	
}
