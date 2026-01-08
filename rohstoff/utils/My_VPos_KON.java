package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class My_VPos_KON extends MyDataRecordHashMap 
{

	private My_VKopf_KON  		oVKopfKON = null;
	private My_VPos_KON_TRAKT  	oVPos_KON_TRAKT = null;
	
	public My_VPos_KON(String cID_VPOS_KON) throws myException 
	{
		super("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VPOS_KON="+cID_VPOS_KON);
	}

	public My_VKopf_KON get_oVKopfKON() throws myException 
	{
		if (this.oVKopfKON==null)
			this.oVKopfKON = new My_VKopf_KON(this.get_UnFormatedValue("ID_VKOPF_KON"));
			
		return oVKopfKON;
	}

	public My_VPos_KON_TRAKT get_oVPos_KON_TRAKT() throws myException 
	{
		if (this.oVPos_KON_TRAKT==null)
			this.oVPos_KON_TRAKT = new My_VPos_KON_TRAKT(this.get_UnFormatedValue("ID_VPOS_KON"));
			
		return oVPos_KON_TRAKT;
	}

	
	public boolean get_bPosIstAbgeschlossen() throws myException
	{
		return this.get_oVPos_KON_TRAKT().get_UnFormatedValue_LeerWennNull("ABGESCHLOSSEN").equals("Y");
		
	}
	
	public boolean get_bKopfIstAbgeschlossen() throws myException
	{
		return this.get_oVKopfKON().get_UnFormatedValue_LeerWennNull("ABGESCHLOSSEN").equals("Y");
	}
	
	
	
	
}
