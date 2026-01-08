package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class MyArtikelNG extends MyDataRecordHashMap
{

	public MyArtikelNG(String cID_ARTIKEL_Unformated) throws myException
	{
		super("SELECT " +
				"ART1.ID_ARTIKEL,	ART1.ARTBEZ1, ART1.ARTBEZ2,ART1.ID_EINHEIT,ART1.ANR1,ART1.AKTIV,ART1.ZOLLTARIFNR, " +
				"ART1.MENGENDIVISOR, ART1.ID_EINHEIT_PREIS, ART1.EUNOTIZ,ART1.EUCODE,ART1.BASEL_NOTIZ,ART1.BASEL_CODE," +
				"EINH1.EINHEITKURZ EINHEIT, NVL(EINH2.EINHEITKURZ,EINH1.EINHEITKURZ) EINHEIT_PREIS " +
				" FROM " +
				""+bibE2.cTO()+".JT_ARTIKEL ART1,"+bibE2.cTO()+".JT_ARTIKEL ART2,"+bibE2.cTO()+".JT_EINHEIT EINH1,"+bibE2.cTO()+".JT_EINHEIT EINH2 " +
				" WHERE" +
				" ART1.ID_EINHEIT = EINH1.ID_EINHEIT AND " +
				" ART2.ID_EINHEIT_PREIS = EINH2.ID_EINHEIT (+) AND " +
				" ART1.ID_ARTIKEL = ART2.ID_ARTIKEL AND " +
				" ART1.ID_ARTIKEL="+cID_ARTIKEL_Unformated);
	
		
	}
	
	
	public String get_ID_ARTIKEL()  		throws myException	{	return this.get_UnFormatedValue("ID_ARTIKEL");	}
	public String get_ARTBEZ1()  			throws myException	{	return this.get_UnFormatedValue("ARTBEZ1");	}
	public String get_ARTBEZ2()  			throws myException	{	return this.get_UnFormatedValue("ARTBEZ2");	}
	public String get_ANR1()  				throws myException	{	return this.get_UnFormatedValue("ANR1");	}
	public String get_AKTIV()  				throws myException	{	return this.get_UnFormatedValue("AKTIV");	}
	public String get_MENGENDIVISOR()  		throws myException	{	return this.get_UnFormatedValue("MENGENDIVISOR");	}
	public String get_ID_EINHEIT_PREIS()  	throws myException	{	return this.get_UnFormatedValue("ID_EINHEIT_PREIS");	}
	public String get_EUNOTIZ()  			throws myException	{	return this.get_UnFormatedValue("EUNOTIZ");	}
	public String get_EUCODE()  			throws myException	{	return this.get_UnFormatedValue("EUCODE");	}
	public String get_BASEL_NOTIZ()  		throws myException	{	return this.get_UnFormatedValue("BASEL_NOTIZ");	}
	public String get_BASEL_CODE()  		throws myException	{	return this.get_UnFormatedValue("BASEL_CODE");	}
	
	public String get_EINHEIT()  			throws myException	{	return this.get_UnFormatedValue("EINHEIT");	}
	public String get_ZOLLTARIFNR()  		throws myException	{	return this.get_UnFormatedValue("ZOLLTARIFNR");	}

	
	public String get_EINHEIT_PREIS()	throws myException		
	{
		String cRueck =  this.get_UnFormatedValue("EINHEIT_PREIS");
		
		if (cRueck.equals(""))
			cRueck = this.get_UnFormatedValue("EINHEIT");
		
		return cRueck;
	}

	

}
