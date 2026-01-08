package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class My_VKopf_KON extends MyDataRecordHashMap 
{

	
	
	public My_VKopf_KON(String cID_VKOPF_KON) throws myException 
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON="+cID_VKOPF_KON);
	}


	public String get_VORGANGSART() throws myException 
	{
		return this.get_UnFormatedValue("VORGANG_TYP");
	}
	
	
	public boolean get_bIstEK() throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP").equals(myCONST.VORGANGSART_EK_KONTRAKT);
		
	}
	
	public boolean get_bIstVK() throws myException
	{
		return this.get_UnFormatedValue("VORGANG_TYP").equals(myCONST.VORGANGSART_VK_KONTRAKT);
		
	}
	
	
}
