package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class My_VKopf_RG extends MyDataRecordHashMap 
{

	
	
	public My_VKopf_RG(String cID_VKOPF_RG) throws myException 
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG="+cID_VKOPF_RG);
	}

	
	
	public int get_NumberOfNotDeletedPositions() throws myException
	{
		MyDataRecordHashMap oHMCount = new MyDataRecordHashMap(
				"SELECT COUNT(*) AS ANZAHL FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+this.get_UnFormatedValue("ID_VKOPF_RG")+" AND NVL(JT_VPOS_RG.DELETED,'N')='N'");
		
		int iRueck = -1;
		
		try
		{
			Integer iCount = oHMCount.get_intValue("ANZAHL");
			if (iCount != null)
				iRueck = iCount.intValue();
			
		}
		catch (Exception ex)
		{
			throw new myException(this.getClass().getName()+": Error counting number of positions");
		}
		
		if (iRueck<0)
			throw new myException(this.getClass().getName()+": Error counting number of positions");

		return iRueck;
	}
	
}
