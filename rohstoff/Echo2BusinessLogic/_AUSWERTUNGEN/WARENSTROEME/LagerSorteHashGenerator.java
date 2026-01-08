package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LagerSorteHashGenerator
{
	public String ID_ADRESSE_LAGER = null;
	public String ID_ARTIKEL = null;
	public String ID_EINHEIT  = null;
	public String ID_EINHEIT_PREIS  = null;
	public String MENGEN_DIVISOR  = null;
	
	public LagerSorteHashGenerator(	String iD_ADRESSE_LAGER, 
									String iD_ARTIKEL,
									String iD_EINHEIT, 
									String iD_EINHEIT_PREIS, 
									String mENGEN_DIVISOR) throws myException
	{
		super();
		ID_ADRESSE_LAGER = 	iD_ADRESSE_LAGER;
		ID_ARTIKEL = 		iD_ARTIKEL;
		ID_EINHEIT = 		iD_EINHEIT;
		ID_EINHEIT_PREIS = 	iD_EINHEIT_PREIS;
		MENGEN_DIVISOR =	mENGEN_DIVISOR;
		
		if (ID_ADRESSE_LAGER==null || ID_ARTIKEL==null   || ID_EINHEIT==null    || ID_EINHEIT_PREIS==null   || MENGEN_DIVISOR==null )
		{
			throw new myException(this,"Error building HashKey for Fuhrencollektor !!");
		}
	}

	
	
	public String get_HASHKEY()
	{
		return (ID_ADRESSE_LAGER+"|"+ID_ARTIKEL+"|"+ID_EINHEIT+"|"+ID_EINHEIT_PREIS+"|"+MENGEN_DIVISOR);
	}
	
	
	public String get_DropDownText() throws myException
	{
		String cRueck = "";
		
		RECORD_ARTIKEL  recSorte = new RECORD_ARTIKEL(this.ID_ARTIKEL);
		
		cRueck += recSorte.get___KETTE(bibALL.get_Vector("ANR1", "ARTBEZ1"));
		
		RECORD_EINHEIT recEinheit = new RECORD_EINHEIT(this.ID_EINHEIT);
		
		RECORD_EINHEIT recEinheitPreis = recEinheit;
		if (S.isFull(this.ID_EINHEIT_PREIS))
		{
			recEinheitPreis = new RECORD_EINHEIT(this.ID_EINHEIT_PREIS);
		}
		
		cRueck += "  ["+recEinheit.get_EINHEITKURZ_cUF()+"/"+recEinheitPreis.get_EINHEITKURZ_cUF()+"]  ";
		
		
		if (this.ID_ADRESSE_LAGER.trim().equals("-1"))
		{
			cRueck += " [Lager/Strecke MIXED] ";
		}
		else if (this.ID_ADRESSE_LAGER.trim().equals("0"))
		{
			cRueck += " [STRECKE] ";
		}
		else
		{
			RECORD_ADRESSE recLager =  new RECORD_ADRESSE(this.ID_ADRESSE_LAGER) ;
			cRueck += ("  ["+ (recLager.get___KETTE(bibALL.get_Vector("NAME1", "NAME2","STRASSE", "ORT"))+" ---")+"]");
		}
		
		return cRueck;
	}
	
	
	public BigDecimal get_bdMengenDivisor() throws myException
	{
		MyBigDecimal  oMBD = new MyBigDecimal(this.MENGEN_DIVISOR);
		return oMBD.get_bdWert();
	}
	
}
