package rohstoff.utils;

import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class EAK_DataRecordHashMap_BRANCHE extends MyDataRecordHashMap
{

	public EAK_DataRecordHashMap_BRANCHE(String cIDBranche) throws myException
	{
		super("JT_EAK_BRANCHE","ID_EAK_BRANCHE",cIDBranche);
	}

}
