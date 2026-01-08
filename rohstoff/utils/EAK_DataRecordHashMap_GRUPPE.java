package rohstoff.utils;

import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class EAK_DataRecordHashMap_GRUPPE extends MyDataRecordHashMap
{

	private EAK_DataRecordHashMap_BRANCHE oBranche = null;
	
	
	public EAK_DataRecordHashMap_GRUPPE(String cIDGruppe) throws myException
	{
		super("JT_EAK_GRUPPE","ID_EAK_GRUPPE",cIDGruppe);
		this.oBranche = new EAK_DataRecordHashMap_BRANCHE(this.get_UnFormatedValue("ID_EAK_BRANCHE"));
	}


	public EAK_DataRecordHashMap_BRANCHE get_hmBranche()
	{
		return oBranche;
	}

}
