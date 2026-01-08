package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ZZ_AW_WARENSTROEME;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class DatenTypUndMenge extends HashMap<String, BigDecimal>
{
	
	public String     cID_ADRESSE_LAGER =  "";
	public String     cID_ARTIKEL =  "";
	public String     cID_EINHEIT = "";
	public String     cID_EINHEIT_PREIS = "";
	public String     cMENGEN_DIVISOR = "";
	
	private LagerSorteHashGenerator  oLagSortHashGen = null;

	
	private VectorSingle   vIDsFuhren = new VectorSingle();
	


	private String[] cDoubleFelder = {"MENGE_FUHRE_WE",
									"MENGE_FUHRE_WA",
									"MENGE_RG_POS_WE",
									"MENGE_RG_POS_WA",
									"MENGE_RG_POS_ABZUG_WE",
									"MENGE_RG_POS_ABZUG_WA",
									"GESAMTPREIS_WE",
									"GESAMTPREIS_WA",
									"GPREIS_ABZ_MGE_WE",
									"GPREIS_ABZ_MGE_WA",
									"MENGE_RG_POS_ABZUG_LAG_REL_WE",
									"MENGE_RG_POS_ABZUG_LAG_REL_WA",
									"MENGE_LAGERAUSGANG",
									"MENGE_LAGEREINGANG",
									"MENGE_ABZUG_LAGERAUSGANG",
									"MENGE_ABZUG_LAGEREINGANG",
									"MENGE_FUHRE_ABZUG_WE",
									"MENGE_FUHRE_ABZUG_WA",
									"GPREIS_ABZ_AUF_NETTOMGE_WE",
									"GPREIS_ABZ_AUF_NETTOMGE_WA",
									"EPREIS_BRUTTO_WE",
									"EPREIS_BRUTTO_WA",
									"GPREIS_NETTO_WE",
									"GPREIS_NETTO_WA",
									"MENGE_RG_POS_NETTO_WE",
									"MENGE_RG_POS_NETTO_WA",
									"EPREIS_NETTO_WE",
									"EPREIS_NETTO_WA",
									"EPREIS_NETTO_MARGE",
									"GPREIS_NETTO_MARGE"};
									;
	
	
	public DatenTypUndMenge(LagerSorteHashGenerator  LagSortHashGen) throws myException 
	{
		
		this.oLagSortHashGen = LagSortHashGen;
		
		cID_ADRESSE_LAGER = 	oLagSortHashGen.ID_ADRESSE_LAGER;
		cID_ARTIKEL = 			oLagSortHashGen.ID_ARTIKEL;
		cID_EINHEIT = 			oLagSortHashGen.ID_EINHEIT;
		cID_EINHEIT_PREIS = 	oLagSortHashGen.ID_EINHEIT_PREIS;
		cMENGEN_DIVISOR=		oLagSortHashGen.MENGEN_DIVISOR;

		for (int i=0;i<this.cDoubleFelder.length;i++)
		{
			this.put(this.cDoubleFelder[i],new BigDecimal(0));
		}
		
		this.put("MENGENDIVISOR", LagSortHashGen.get_bdMengenDivisor());
		
	}
	
	
	public LagerSorteHashGenerator get_oLagSortHashGen()
	{
		return oLagSortHashGen;
	}


	
	public void add_ID_FUHRE(String cID_FUHRE)
	{
		this.vIDsFuhren.add(cID_FUHRE);
	}
	
//	public void ADD(String cFeldName, BigDecimal bdWert) throws myException
//	{
//		this.vIDsFuhren.add(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
//	}
//	
//	
//	public void ADD(String cFeldName, BigDecimal bdWert, RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt) throws myException
//	{
//		this.vIDsFuhren.add(recFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF());
//		this.ADD(cFeldName, bdWert);
//	}
	
	
	
	public void ADD(String cFeldName, BigDecimal bdWert) throws myException
	{
		if (this.containsKey(cFeldName))
		{
			BigDecimal bdWertBisher = this.get(cFeldName);
			
			this.put(cFeldName, bdWertBisher.add(bdWert));
		}
		else
		{
			throw new myException(this,"Hashkey:"+cFeldName+" is not in Collector-Map");
		}
	}
	
	
	public BigDecimal GET(String cFeldName, BigDecimal bdWertWennNull) throws myException
	{
		if (this.containsKey(cFeldName))
		{
			BigDecimal bdWertBisher = this.get(cFeldName);
			if (bdWertBisher == null)
			{
				return bdWertWennNull;
			}
			else
			{
				return bdWertBisher;
			}
		}
		else
		{
			throw new myException(this,"Hashkey:"+cFeldName+" is not in Collector-Map");
		}
		
	}
	
	
	public void SET(String cFeldName, BigDecimal bdWert) throws myException
	{
		if (this.containsKey(cFeldName))
		{
			this.put(cFeldName, bdWert);
		}
		else
		{
			throw new myException(this,"Hashkey:"+cFeldName+" is not in Collector-Map");
		}
		
	}

	
	
	
	public void BERECHNE_SONDER_FELDER() throws myException
	{
		//feld urspruenglicher einzelpreis (zurueckgerechnet)
		BigDecimal b0 = BigDecimal.ZERO;
		
		MathContext  oMC = new MathContext(16, RoundingMode.HALF_UP);

		
		BigDecimal  bdOriginalEpreisWE = b0;    //EPREIS_BRUTTO_WE
		if (this.GET("MENGE_RG_POS_WE",b0).compareTo(b0)>0)
		{
			bdOriginalEpreisWE = this.GET("GESAMTPREIS_WE",b0);
			bdOriginalEpreisWE = bdOriginalEpreisWE.divide(this.GET("MENGE_RG_POS_WE",b0), oMC);
			bdOriginalEpreisWE = bdOriginalEpreisWE.multiply(this.GET("MENGENDIVISOR",b0), oMC);
		}
		this.SET("EPREIS_BRUTTO_WE",bdOriginalEpreisWE);
		
		
		
		//feld urspruenglicher einzelpreis (zurueckgerechnet)
		BigDecimal  bdOriginalEpreisWA = b0;    //EPREIS_BRUTTO_WA
		if (this.GET("MENGE_RG_POS_WA",b0).compareTo(b0)>0)
		{
			bdOriginalEpreisWA = this.GET("GESAMTPREIS_WA",b0);
			bdOriginalEpreisWA = bdOriginalEpreisWA.divide(this.GET("MENGE_RG_POS_WA",b0), oMC);
			bdOriginalEpreisWA = bdOriginalEpreisWA.multiply(this.GET("MENGENDIVISOR",b0), oMC);
		}
		this.SET("EPREIS_BRUTTO_WA",bdOriginalEpreisWA);


		//felder: Positionsbetrag effektiv     //GPREIS_NETTO_WE  --  GPREIS_NETTO_WA
		BigDecimal  bdGPREIS_NETTO_WE = this.GET("GESAMTPREIS_WE",b0).subtract(this.GET("GPREIS_ABZ_MGE_WE",b0)).subtract(this.GET("GPREIS_ABZ_AUF_NETTOMGE_WE",b0));
		BigDecimal  bdGPREIS_NETTO_WA = this.GET("GESAMTPREIS_WA",b0).subtract(this.GET("GPREIS_ABZ_MGE_WA",b0)).subtract(this.GET("GPREIS_ABZ_AUF_NETTOMGE_WA",b0));
		this.SET("GPREIS_NETTO_WE",bdGPREIS_NETTO_WE);
		this.SET("GPREIS_NETTO_WA",bdGPREIS_NETTO_WA);
		
		
		//felder: nettomengen 				   //MENGE_RG_POS_NETTO_WE   --   MENGE_RG_POS_NETTO_WA
		BigDecimal  bdMENGE_RG_POS_NETTO_WE = this.GET("MENGE_RG_POS_WE",b0).subtract(this.GET("MENGE_RG_POS_ABZUG_LAG_REL_WE",b0));
		BigDecimal  bdMENGE_RG_POS_NETTO_WA = this.GET("MENGE_RG_POS_WA",b0).subtract(this.GET("MENGE_RG_POS_ABZUG_LAG_REL_WA",b0));
		this.SET("MENGE_RG_POS_NETTO_WE",bdMENGE_RG_POS_NETTO_WE);
		this.SET("MENGE_RG_POS_NETTO_WA",bdMENGE_RG_POS_NETTO_WA);
		
		//feld einzelpreis auf nettomenge (zurueckgerechnet)     //EPREIS_NETTO_WE
		BigDecimal  bdEPREIS_NETTO_WE = b0;
		if (bdMENGE_RG_POS_NETTO_WE.compareTo(b0)>0)
		{
			bdEPREIS_NETTO_WE = bdGPREIS_NETTO_WE.divide(bdMENGE_RG_POS_NETTO_WE, oMC);
			bdEPREIS_NETTO_WE = bdEPREIS_NETTO_WE.multiply(this.GET("MENGENDIVISOR",b0), oMC);
		}
		this.SET("EPREIS_NETTO_WE",bdEPREIS_NETTO_WE);

		//feld einzelpreis auf nettomenge (zurueckgerechnet)    //EPREIS_NETTO_WA
		BigDecimal  bdEPREIS_NETTO_WA = b0;
		if (bdMENGE_RG_POS_NETTO_WA.compareTo(b0)>0)
		{
			bdEPREIS_NETTO_WA = bdGPREIS_NETTO_WA.divide(bdMENGE_RG_POS_NETTO_WA, oMC);
			bdEPREIS_NETTO_WA = bdEPREIS_NETTO_WA.multiply(this.GET("MENGENDIVISOR",b0), oMC);
		}
		this.SET("EPREIS_NETTO_WA",bdEPREIS_NETTO_WA);
		
		//System.out.println(MyNumberFormater.formatDez(bdEpreisAufNettoMengeWA, 2, true));

		
		//feld differenz der effektivpreise auf nettomenge      //EPREIS_NETTO_MARGE
		BigDecimal  bdEPREIS_NETTO_MARGE = bdEPREIS_NETTO_WA.subtract(bdEPREIS_NETTO_WE);
		this.SET("EPREIS_NETTO_MARGE",bdEPREIS_NETTO_MARGE);

		//feld differenz effektivpreise                        //GPREIS_NETTO_MARGE
		BigDecimal  bdGPREIS_NETTO_MARGE = 			bdGPREIS_NETTO_WA.subtract(bdGPREIS_NETTO_WE);
		this.SET("GPREIS_NETTO_MARGE",bdGPREIS_NETTO_MARGE);

		
	}
	
	
	public MyE2_MessageVector SpeichereSatz(String cREPORTNUMMER) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		RECORDNEW_ZZ_AW_WARENSTROEME  recNew = new RECORDNEW_ZZ_AW_WARENSTROEME();
		
		recNew.set_NEW_VALUE_ID_ADRESSE_LAGER(this.cID_ADRESSE_LAGER);
		recNew.set_NEW_VALUE_ID_ARTIKEL(this.cID_ARTIKEL);
		recNew.set_NEW_VALUE_ID_EINHEIT(this.cID_EINHEIT);
		recNew.set_NEW_VALUE_ID_EINHEIT_PREIS (this.cID_EINHEIT_PREIS);
		recNew.set_NEW_VALUE_MENGENDIVISOR(this.cMENGEN_DIVISOR);
		recNew.set_NEW_VALUE_BENUTZERKUERZEL(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN(""));
		recNew.set_NEW_VALUE_REPORTNUMMER(cREPORTNUMMER);
		
		for (int i=0;i<this.cDoubleFelder.length;i++)
		{
			recNew.set_NewValueForDatabase(this.cDoubleFelder[i],this.GET(this.cDoubleFelder[i], new BigDecimal(0)));
		}

		recNew.do_WRITE_NEW_ZZ_AW_WARENSTROEME(oMV);
		
		return oMV;
		
	}
	
	public VectorSingle 	get_vIDsFuhren()
	{
		return vIDsFuhren;
	}
	
}