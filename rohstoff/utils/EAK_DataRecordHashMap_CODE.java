package rohstoff.utils;

import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class EAK_DataRecordHashMap_CODE extends MyDataRecordHashMap
{
	private EAK_DataRecordHashMap_BRANCHE 	oBranche = null;
	private EAK_DataRecordHashMap_GRUPPE 	oGruppe = null;

	public EAK_DataRecordHashMap_CODE(String cIDCode) throws myException
	{
		super("JT_EAK_CODE","ID_EAK_CODE",cIDCode);
		this.oGruppe = new EAK_DataRecordHashMap_GRUPPE(this.get_UnFormatedValue("ID_EAK_GRUPPE"));
		this.oBranche = new EAK_DataRecordHashMap_BRANCHE(this.oGruppe.get_UnFormatedValue("ID_EAK_BRANCHE"));

		
	}

	public EAK_DataRecordHashMap_BRANCHE get_hmBranche()
	{
		return oBranche;
	}

	public EAK_DataRecordHashMap_GRUPPE get_hmGruppe()
	{
		return oGruppe;
	}

}
