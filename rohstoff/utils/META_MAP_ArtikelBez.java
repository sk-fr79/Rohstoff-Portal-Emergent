package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordMETAMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class META_MAP_ArtikelBez extends MyDataRecordMETAMap 
{
	private String cEINHEIT_KURZ = null;
	private String cEINHEIT_PREIS_KURZ = null;
	
	public META_MAP_ArtikelBez(String cID_ARTIKELBEZ) throws myException 
	{
		super("JT_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ", cID_ARTIKELBEZ);
		
		this.add_Map_RelativeToLastTable("JT_ARTIKEL", "ID_ARTIKEL", "ID_ARTIKEL", null);
		
		this.cEINHEIT_KURZ = bibDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+
									this.get_UnFormatedValue("ID_EINHEIT"),"","","");
		
		this.cEINHEIT_PREIS_KURZ = bibDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+
									this.get_UnFormatedValue("ID_EINHEIT_PREIS"), "","","");
		
		
	}

	public String get_cEINHEIT_KURZ() 
	{
		return cEINHEIT_KURZ;
	}

	public String get_cEINHEIT_PREIS_KURZ() 
	{
		return cEINHEIT_PREIS_KURZ;
	}


}
