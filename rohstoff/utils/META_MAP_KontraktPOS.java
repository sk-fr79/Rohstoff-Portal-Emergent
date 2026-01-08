package rohstoff.utils;

import panter.gmbh.indep.dataTools.MyDataRecordMETAMap;
import panter.gmbh.indep.exceptions.myException;

public class META_MAP_KontraktPOS extends MyDataRecordMETAMap 
{
	

	public META_MAP_KontraktPOS(String cID_VPOS_KON) throws myException 
	{
		super("JT_VPOS_KON", "ID_VPOS_KON", cID_VPOS_KON);
		this.add_Map_RelativeToLastTable("JT_VKOPF_KON", "ID_VKOPF_KON", "ID_VKOPF_KON", null);
		this.add_Map_RelativeToLastTable("JT_ADRESSE", "ID_ADRESSE", "ID_ADRESSE", null);
		this.add_Map_RelativeToTable("JT_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ", null,"JT_VPOS_KON");
		this.add_Map_RelativeToTable("JT_ARTIKEL", "ID_ARTIKEL", "ID_ARTIKEL", null,"JT_ARTIKEL_BEZ");
		
		this.add_Map("JT_VPOS_KON_TRAKT", "ID_VPOS_KON="+this.get_UnFormatedValue("JT_VPOS_KON", "ID_VPOS_KON"));
	}

	
	
}
