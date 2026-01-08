package rohstoff.utils;


import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.VectorDataBaseQuery;
import panter.gmbh.indep.exceptions.myException;

public class MyArtikel extends VectorDataBaseQuery
{
	
	
	public MyArtikel(String cID_ARTIKEL_Unformated) throws myException
	{
		super(  "ART1.ID_ARTIKEL,	ART1.ARTBEZ1, ART1.ARTBEZ2,	" +
				"ART1.ID_EINHEIT,ART1.ANR1,	ART1.AKTIV,	ART1.ZOLLTARIFNR, ART1.MENGENDIVISOR, ART1.ID_EINHEIT_PREIS, ART1.EUNOTIZ," +
				"ART1.EUCODE,ART1.BASEL_NOTIZ,ART1.BASEL_CODE,EINH1.EINHEITKURZ EINHEIT,	 NVL(EINH2.EINHEITKURZ,EINH1.EINHEITKURZ) EINHEIT_MENGE",
				""+bibE2.cTO()+".JT_ARTIKEL ART1,"+bibE2.cTO()+".JT_ARTIKEL ART2,	"+bibE2.cTO()+".JT_EINHEIT EINH1, "+bibE2.cTO()+".JT_EINHEIT EINH2",
				"ART1.ID_EINHEIT = EINH1.ID_EINHEIT AND ART2.ID_EINHEIT_PREIS = EINH2.ID_EINHEIT (+) AND " +
				"ART1.ID_ARTIKEL = ART2.ID_ARTIKEL ",
				"ART1.ID_ARTIKEL="+cID_ARTIKEL_Unformated,
				null);
		
		if (this.size()!=1)
			throw new myException("MyArtikel:Constructor:Error cannot get MyArtikel from: "+cID_ARTIKEL_Unformated);
		
		if (!bibALL.ReplaceTeilString(this.get_resultValue("ID_ARTIKEL",0),".","").equals(cID_ARTIKEL_Unformated))
			throw new myException("MyArtikel:Constructor:Undefined Error: "+cID_ARTIKEL_Unformated);
		
	}

	
	public String get_ID_ARTIKEL()  		{	return this.get_resultValue("ID_ARTIKEL",0);	}
	public String get_ARTBEZ1()  			{	return this.get_resultValue("ARTBEZ1",0);	}
	public String get_ARTBEZ2()  			{	return this.get_resultValue("ARTBEZ2",0);	}
	public String get_ANR1()  				{	return this.get_resultValue("ANR1",0);	}
	public String get_AKTIV()  				{	return this.get_resultValue("AKTIV",0);	}
	public String get_MENGENDIVISOR()  		{	return this.get_resultValue("MENGENDIVISOR",0);	}
	public String get_ID_EINHEIT_PREIS()  	{	return this.get_resultValue("ID_EINHEIT_PREIS",0);	}
	public String get_EUNOTIZ()  			{	return this.get_resultValue("EUNOTIZ",0);	}
	public String get_EUCODE()  			{	return this.get_resultValue("EUCODE",0);	}

	public String get_BASEL_NOTIZ()  		{	return this.get_resultValue("BASEL_NOTIZ",0);	}
	public String get_BASEL_CODE()  		{	return this.get_resultValue("BASEL_CODE",0);	}

	public String get_EINHEIT()  			{	return this.get_resultValue("EINHEIT",0);	}
	public String get_ZOLLTARIFNR()  		{	return this.get_resultValue("ZOLLTARIFNR",0);	}
	
	public String get_EINHEIT_PREIS()		
	{
		String cRueck =  this.get_resultValue("EINHEIT_MENGE",0);
		
		if (cRueck.equals(""))
			cRueck = this.get_resultValue("EINHEIT",0);
		
		return cRueck;
	}
	
	
	
	
}
