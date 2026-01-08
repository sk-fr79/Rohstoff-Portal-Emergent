package rohstoff.utils;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;


public class MyArtikelBezeichnung extends MyDataRecordHashMap
{
	private HttpSession SES = null; 
	
	public MyArtikelBezeichnung(String cID_ARTIKELBEZ, HttpSession oSES) throws myException
	{
		super("SELECT " +
				" JT_ARTIKEL_BEZ.ARTBEZ1 AS BEZ_ARTBEZ1,	" +
				"JT_ARTIKEL_BEZ.ARTBEZ2 AS BEZ_ARTBEZ2,	" +
				"JT_ARTIKEL_BEZ.ANR2 AS ANR2,	" +
				"JT_ARTIKEL_BEZ.ID_ARTIKEL AS  ID_ARTIKEL,	" +
				"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AS ID_ARTIKEL_BEZ,	" +
				"JT_ARTIKEL.ARTBEZ1 AS ARTBEZ1,	" +
				"JT_ARTIKEL.ARTBEZ2 AS ARTBEZ2,	" +
				"JT_ARTIKEL.ID_EINHEIT AS ID_EINHEIT,	" +
				"JT_ARTIKEL.ANR1 AS ANR1,	" +
				"JT_ARTIKEL.AKTIV AS AKTIV,	" +
				"JT_ARTIKEL.ZOLLTARIFNR AS ZOLLTARIFNR,	" +
				"JT_ARTIKEL.MENGENDIVISOR AS MENGENDIVISOR,	" +
				"JT_ARTIKEL.ID_EINHEIT_PREIS AS ID_EINHEIT_PREIS,	" +
				"JT_ARTIKEL.EUNOTIZ AS EUNOTIZ,	" +
				"JT_ARTIKEL.EUCODE AS EUCODE,	" +
				"JT_ARTIKEL.DIENSTLEISTUNG AS DIENSTLEISTUNG,	" +
				"JT_ARTIKEL.BEMERKUNG_INTERN AS BEMERKUNG_INTERN " +
			" FROM " +bibALL.get_TABLEOWNER()+ ".JT_ARTIKEL, " +bibALL.get_TABLEOWNER()+ ".JT_ARTIKEL_BEZ " +
			" WHERE JT_ARTIKEL_BEZ.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL " +
			" AND ID_ARTIKEL_BEZ="+cID_ARTIKELBEZ);
		
		this.SES = oSES;
	}
	
	
	public String get_ANR1() 			throws myException				{		return this.get_UnFormatedValue("ANR1");	}
	public String get_ANR2() 			throws myException				{		return this.get_UnFormatedValue("ANR2");	}
	public String get_ID_ARTIKEL()		throws myException				{		return this.get_UnFormatedValue("ID_ARTIKEL");	}
	public String get_ID_ARTIKEL_BEZ()	throws myException				{		return this.get_UnFormatedValue("ID_ARTIKEL_BEZ");	}
	
	public String get_BEZ_ARTBEZ1()		throws myException				{		return this.get_UnFormatedValue("BEZ_ARTBEZ1");	}
	public String get_BEZ_ARTBEZ2()		throws myException				{		return this.get_UnFormatedValue("BEZ_ARTBEZ2");	}

	public String get_ZOLLTARIFNR()		throws myException				{		return this.get_UnFormatedValue("ZOLLTARIFNR");	}
	public String get_MENGENDIVISOR()	throws myException				{		return this.get_UnFormatedValue("MENGENDIVISOR");	}
	public String get_EUNOTIZ()			throws myException				{		return this.get_UnFormatedValue("EUNOTIZ");	}
	public String get_EUCODE()			throws myException				{		return this.get_UnFormatedValue("EUCODE");	}
	
	
	public String get_EINHEITKURZ()		throws myException			
	{		
		String cRueck = "";
		if (! bibALL.isEmpty(this.get_UnFormatedValue("ID_EINHEIT")))
		{
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			cRueck = oDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibALL.get_TABLEOWNER()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_UnFormatedValue("ID_EINHEIT"));
			bibALL.destroy_myDBToolBox(oDB);
			if (cRueck.startsWith("@"))
				cRueck = "";
		}
		return cRueck;	
	}
	
	public String get_EINHEIT_PREIS_KURZ()		throws myException	
	{		
		String cRueck = "";
		if (! bibALL.isEmpty(this.get_UnFormatedValue("ID_EINHEIT_PREIS")))
		{
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			cRueck = oDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibALL.get_TABLEOWNER()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_UnFormatedValue("ID_EINHEIT_PREIS"));
			bibALL.destroy_myDBToolBox(oDB);
			if (cRueck.startsWith("@"))
				cRueck = "";
		}
		if (bibALL.isEmpty(cRueck))
			return this.get_EINHEITKURZ();
		else		
			return cRueck;	
	}
	

	
	
}
