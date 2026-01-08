package rohstoff.utils;

import java.util.Locale;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.DotFormatter;


public class MyFuhreNG extends MyDataRecordHashMap
{
	public static String    	    HASHKEY_MENGE_POSITIONEN_RECHNUNG = 	"HASHKEY_MENGE_POSITIONEN_RECHNUNG";
	public static String    	    HASHKEY_MENGE_POSITIONEN_GUTSCHRIFT = 	"HASHKEY_MENGE_POSITIONEN_GUTSCHRIFT";
	
	
	private MyDataRecordHashMap		hmVPosTPA = null;
	private MyDataRecordHashMap		hmVKopfTPA = null;
	
	private My_VPos_KON    			hmVPosKonEK = null;
	private My_VPos_KON    			hmVPosKonVK = null;
	
	private boolean 				bVPosTPA_DONE = false;           // werden beim ersten aufbauversuch true, sodass die beim benutzen nur einmal aufgebaut werden
	private boolean					bVKopfTPA_DONE = false;
	

	//werte, die ert nach einem Aufruf der methode get_Actual_StatusBuchung() definiert sind
	private Double  				dSUMME_POSITIONEN_RECHNUNG = null;
	private Double  				dSUMME_POSITIONEN_GUTSCHRIFT = null;
	
	
	public MyFuhreNG(String crecordID) throws myException
	{
		super("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE", crecordID);
	}


	public void Refresh() throws myException
	{
		super.Refresh();
	}
	
	
	
	public MyDataRecordHashMap get_hmVPosTPA() throws myException		
	{
		if (! this.bVPosTPA_DONE)
		{
			this.bVPosTPA_DONE = true;
			if (!bibALL.isEmpty(this.get_UnFormatedValue("ID_VPOS_TPA")))
				this.hmVPosTPA = new MyDataRecordHashMap("JT_VPOS_TPA","ID_VPOS_TPA",this.get_UnFormatedValue("ID_VPOS_TPA"));
		}
		
		return this.hmVPosTPA;	
	}

	public MyDataRecordHashMap get_hmVKopfTPA() throws myException		
	{
		if (! this.bVKopfTPA_DONE)
		{
			this.bVKopfTPA_DONE = true;
			
			if (this.get_hmVPosTPA() != null)
			{
				if (!bibALL.isEmpty(this.get_hmVPosTPA().get_UnFormatedValue("ID_VKOPF_TPA")))
					this.hmVKopfTPA = new MyDataRecordHashMap("JT_VKOPF_TPA","ID_VKOPF_TPA",this.get_hmVPosTPA().get_UnFormatedValue("ID_VKOPF_TPA"));
			}
		}
		
		return this.hmVKopfTPA;	
	}

	
	public boolean get_bTPA_Ist_Gebucht() throws myException
	{
		boolean bRueck = false;
		
		if (this.get_hmVKopfTPA()!=null)
			if (this.get_hmVKopfTPA().get_UnFormatedValue("ABGESCHLOSSEN").equals("Y"))
					bRueck = true;

		return bRueck;
	}


	public boolean get_b_Fuhre_HAT_TPA()  throws myException
	{
		boolean bRueck = false;
		
		if (!bibALL.isEmpty(this.get_UnFormatedValue("ID_VPOS_TPA")))
			bRueck = true;
		
		return bRueck;
	}


	public boolean get_b_Fuhre_IS_DELETED()  throws myException
	{
		boolean bRueck = false;
		
		if (bibALL.null2leer(this.get_UnFormatedValue("DELETED")).equals("Y"))
			bRueck = true;
		
		return bRueck;
	}
	
	
	public boolean get_b_Fuhre_HAS_MENGEN()  throws myException
	{
		boolean bRueck = true;
		
		if (bibALL.null2leer(this.get_UnFormatedValue("MENGE_AUFLADEN_KO")).equals("") &&
			bibALL.null2leer(this.get_UnFormatedValue("MENGE_ABLADEN_KO")).equals(""))
			bRueck = false;
		
		return bRueck;
	}


//	public boolean get_b_Fuhre_HAT_WIEGEKARTE()  throws myException
//	{
//		boolean bRueck = false;
//		
//		if (! bibALL.null2leer(this.get_UnFormatedValue("WIEGEKARTENKENNER")).equals(""))
//			bRueck = true;
//		
//		return bRueck;
//	}

	
	
	public boolean get_bFuhreIstGutschriftFuhre()  throws myException
	{
		return (!this.get_UnFormatedValue("ID_ADRESSE_START").equals(bibALL.get_ID_ADRESS_MANDANT()));
	}
	
	
	public boolean get_bFuhreIstRechnungsFuhre()  throws myException
	{
		return (!this.get_UnFormatedValue("ID_ADRESSE_ZIEL").equals(bibALL.get_ID_ADRESS_MANDANT()));
	}

	
	
	public boolean get_bLadeMengeFuerGutschrift()  throws myException
	{
		return bibALL.null2leer(this.get_UnFormatedValue("LADEMENGE_GUTSCHRIFT")).equals("Y");
	}
	
	public boolean get_bAbladeMengeFuerRechnung()  throws myException
	{
		return bibALL.null2leer(this.get_UnFormatedValue("ABLADEMENGE_RECHNUNG")).equals("Y");
	}
	
	
	
	/**
	 * sucht die abrechnungsmenge je nach gesetzten schaltern raus (liefert bei leerstring 0 oder 0,000 zurueck
	 */
	public String get_AbrechnungsMenge_GUTSCHRIFT(boolean bFormated)  throws myException
	{
		String cRueck = "";
		
		if (this.get_bLadeMengeFuerGutschrift())
		{
			if (bFormated)
				cRueck = this.get_FormatedValue("MENGE_AUFLADEN_KO");
			else
				cRueck = this.get_UnFormatedValue("MENGE_AUFLADEN_KO");
		}
		else
		{
			if (bFormated)
				cRueck = this.get_FormatedValue("MENGE_ABLADEN_KO");
			else
				cRueck = this.get_UnFormatedValue("MENGE_ABLADEN_KO");
			
		}
		
		if (bibALL.isEmpty(cRueck))
		{
			if (bFormated)
				cRueck="0,000";
			else
				cRueck="0";
		}
		
		
		return cRueck;
	}
	
	

	
	/*
	 * sucht die abrechnungsmenge je nach gesetzten schaltern raus
	 */
	public String get_AbrechnungsMenge_RECHNUNG(boolean bFormated)  throws myException
	{
		String cRueck = "";
		
		if (this.get_bAbladeMengeFuerRechnung())
		{
			if (bFormated)
				cRueck = this.get_FormatedValue("MENGE_ABLADEN_KO");
			else
				cRueck = this.get_UnFormatedValue("MENGE_ABLADEN_KO");
		}
		else
		{
			if (bFormated)
				cRueck = this.get_FormatedValue("MENGE_AUFLADEN_KO");
			else
				cRueck = this.get_UnFormatedValue("MENGE_AUFLADEN_KO");
			
		}
		
		if (bibALL.isEmpty(cRueck))
		{
			if (bFormated)
				cRueck="0,000";
			else
				cRueck="0";
		}

		
		return cRueck;
	}
	
	

	
	public Double get_dAbrechungsMenge_GUTSCHRIFT() throws myException
	{
		return get_abrechnungsmenge(this.get_AbrechnungsMenge_GUTSCHRIFT(true));
	}
	
	public Double get_dAbrechungsMenge_RECHNUNG() throws myException
	{
		return get_abrechnungsmenge(this.get_AbrechnungsMenge_RECHNUNG(true));
	}

	private Double get_abrechnungsmenge(String cWert) throws myException
	{
		DotFormatter oDF = new DotFormatter(cWert,3,Locale.GERMAN,true,3);
		if (! oDF.doFormat())
		{
			throw new myExceptionForUser("Falsche Zahleneingabe !!");
		}
		else
			return new Double(oDF.getDoubleValue());
	}
	
	
	
	
	public boolean get_bFuhreIstReineLagerFuhre()  throws myException
	{
		return !get_bFuhreIstGutschriftFuhre() && !get_bFuhreIstRechnungsFuhre();
		
	}
	

	public boolean get_bFuhreIstStreckenFuhre()  throws myException
	{
		return get_bFuhreIstGutschriftFuhre() && get_bFuhreIstRechnungsFuhre();
	}

	
	
	public String get_L_NAME1()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("L_NAME1"));	};
	public String get_L_NAME2()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("L_NAME2"));	};
	public String get_L_ORT()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("L_ORT"));	};
	public String get_A_NAME1()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("A_NAME1"));	};
	public String get_A_NAME2()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("A_NAME2"));	};
	public String get_A_ORT()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("A_ORT"));	};
	public String get_ANR1_EK()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ANR1_EK"));	};
	public String get_ANR2_EK()   				throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ANR2_EK"));	};
	public String get_ARTBEZ1_EK()   			throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ARTBEZ1_EK"));	};
	public String get_MENGE_VORGABE_KO()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("MENGE_VORGABE_KO"));	};
	public String get_MENGE_AUFLADEN_KO() 	  	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("MENGE_AUFLADEN_KO"));	};
	public String get_MENGE_ABLADEN_KO()  	 	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("MENGE_ABLADEN_KO"));	};
	public String get_ID_ADRESSE_START()  	 	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ADRESSE_START"));	};
	public String get_ID_ADRESSE_ZIEL()   		throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ADRESSE_ZIEL"));	};
	public String get_ID_ADRESSE_LAGER_START()  throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ADRESSE_LAGER_START"));	};
	public String get_ID_ADRESSE_LAGER_ZIEL()  	throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ADRESSE_LAGER_ZIEL"));	};
	public String get_ID_VPOS_KON_EK()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_VPOS_KON_EK"));	};
	public String get_ID_VPOS_KON_VK()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_VPOS_KON_VK"));	};
	public String get_ID_VPOS_TPA_FUHRE_SONDER() throws myException {		return bibALL.null2leer(this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_SONDER"));	};
	public String get_ID_VPOS_TPA_FUHRE()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE"));	};
	public String get_DATUM_AUFLADEN()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("DATUM_AUFLADEN"));	};
	public String get_DATUM_ABLADEN()  			throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("DATUM_ABLADEN"));	};

	public String get_F_MENGE_VORGABE_KO()  	throws myException 	{		return bibALL.null2leer(this.get_FormatedValue("MENGE_VORGABE_KO"));	};
	public String get_F_MENGE_AUFLADEN_KO()  	throws myException 	{		return bibALL.null2leer(this.get_FormatedValue("MENGE_AUFLADEN_KO"));	};
	public String get_F_MENGE_ABLADEN_KO()   	throws myException	{		return bibALL.null2leer(this.get_FormatedValue("MENGE_ABLADEN_KO"));	};
	
	public String get_F_DATUM_AUFLADEN()  		throws myException 	{		return bibALL.null2leer(this.get_FormatedValue("DATUM_AUFLADEN"));	};
	public String get_F_DATUM_ABLADEN()   		throws myException	{		return bibALL.null2leer(this.get_FormatedValue("DATUM_ABLADEN"));	};
	
	public boolean get_bLADEMENGE_GUTSCHRIFT()  throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("LADEMENGE_GUTSCHRIFT")).equals("Y");	};
	public boolean get_bABLADEMENGE_RECHNUNG()  throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ABLADEMENGE_RECHNUNG")).equals("Y");	};
	
	public String get_cLADEMENGE_GUTSCHRIFT()  	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("LADEMENGE_GUTSCHRIFT"));	};
	public String get_cABLADEMENGE_RECHNUNG()  	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ABLADEMENGE_RECHNUNG"));	};
	
	public String get_ID_ARTIKEL()   			throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ARTIKEL"));	};
	public String get_ID_ARTIKEL_BEZ_EK()  		throws myException 	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ARTIKEL_BEZ_EK"));	};
	public String get_ID_ARTIKEL_BEZ_VK()   	throws myException	{		return bibALL.null2leer(this.get_UnFormatedValue("ID_ARTIKEL_BEZ_VK"));	};
	
	
	public boolean get_OHNE_MENGENABRECHNUNG() 		throws myException {	return bibALL.null2leer(this.get_UnFormatedValue("OHNE_ABRECHNUNG")).equals("Y");	};
	public boolean get_KEIN_VK_KONTRAKT_NOETIG() 	throws myException {	return bibALL.null2leer(this.get_UnFormatedValue("KEIN_KONTRAKT_NOETIG")).equals("Y");	};
	public boolean get_OHNE_AVV_VERTRAG_CHECK() 	throws myException {	return bibALL.null2leer(this.get_UnFormatedValue("OHNE_AVV_VERTRAG_CHECK")).equals("Y");	};
	
//	/**
//	 * @return s Buchungsstatus einer Fuhre aufgrund des gespeicherten Wertes
//	 * @throws myException
//	 */
//	public boolean get_bSAVED_STATUS_BUCHUNG_LEER()   throws myException
//	{			
//		return bibALL.null2leer(this.get_UnFormatedValue("STATUS_BUCHUNG")).equals(""+myCONST.STATUS_FUHRE_KEINE_RG_POS)
//				|| bibALL.null2leer(this.get_UnFormatedValue("STATUS_BUCHUNG")).equals("");	
//	}
	
//	/**
//	 * @return s Buchungsstatus einer Fuhre aufgrund des gespeicherten Wertes
//	 * @throws myException
//	 */
//	public boolean get_bSAVED_STATUS_BUCHUNG_TEIL()   throws myException
//	{			
//		return bibALL.null2leer(this.get_UnFormatedValue("STATUS_BUCHUNG")).equals(""+myCONST.STATUS_FUHRE_TEIL_RG_POS);	
//	}
	
//	/**
//	 * @return s Buchungsstatus einer Fuhre aufgrund des gespeicherten Wertes
//	 * @throws myException
//	 */
//	public boolean get_bSAVED_STATUS_BUCHUNG_FERTIG()   throws myException
//	{			
//		return bibALL.null2leer(this.get_UnFormatedValue("STATUS_BUCHUNG")).equals(""+myCONST.STATUS_FUHRE_FERTIG_RG_POS);	
//	}

	
//	public boolean get_bSAVED_STATUS_BUCHUNG_FERTIG_AKTIV()   throws myException
//	{			
//		return bibALL.null2leer(this.get_UnFormatedValue("STATUS_BUCHUNG")).equals(""+myCONST.STATUS_FUHRE_FERTIG_RG_POS_AKTIV);	
//	}

//	public boolean get_bFuhreOhneAbrechnung()   throws myException
//	{			
//		return bibALL.null2leer(this.get_UnFormatedValue("OHNE_ABRECHNUNG")).equals("Y");	
//	}



	

	
	
	
	public My_VPos_KON get_VPOS_KON_EK() throws myException
	{
		if (this.hmVPosKonEK==null)
		{
			if (!bibALL.isEmpty(this.get_UnFormatedValue("ID_VPOS_KON_EK")))
			{
				this.hmVPosKonEK = new My_VPos_KON(this.get_UnFormatedValue("ID_VPOS_KON_EK"));
			}
		}
		return this.hmVPosKonEK;
	}
	
	

	
	
	public My_VPos_KON get_VPOS_KON_VK() throws myException
	{
		if (this.hmVPosKonVK==null)
		{
			if (!bibALL.isEmpty(this.get_UnFormatedValue("ID_VPOS_KON_VK")))
			{
				this.hmVPosKonVK = new My_VPos_KON(this.get_UnFormatedValue("ID_VPOS_KON_VK"));
			}
		}
		return this.hmVPosKonVK;
	}
	

	
	public boolean get_b_Fuhre_HAT_RechnungBuchung() throws myException
	{
		boolean bRueck = true;
		
		String cQueryBuchung = "SELECT COUNT(JT_VPOS_RG.ID_VPOS_RG) FROM "+bibE2.cTO()+".JT_VPOS_RG " +
										" WHERE " +
										"   NVL(JT_VPOS_RG.DELETED,'N') = 'N' AND " +
										"   NVL(JT_VPOS_RG.LAGER_VORZEICHEN,0)=-1 AND "+
										" JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD="+this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		
		if (bibDB.EinzelAbfrage(cQueryBuchung).equals("0"))
			bRueck = false;

		
		
		return bRueck;
	}


	public boolean get_b_Fuhre_HAT_GutschriftBuchung() throws myException
	{
		boolean bRueck = true;
		
		String cQueryBuchung = "SELECT COUNT(JT_VPOS_RG.ID_VPOS_RG) FROM "+bibE2.cTO()+".JT_VPOS_RG " +
										" WHERE " +
										"   NVL(JT_VPOS_RG.DELETED,'N') = 'N' AND " +
										"   NVL(JT_VPOS_RG.LAGER_VORZEICHEN,0)=1 AND "+
										" JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD="+this.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		
		if (bibDB.EinzelAbfrage(cQueryBuchung).equals("0"))
			bRueck = false;

		return bRueck;
	}


	public Double get_dSUMME_POSITIONEN_GUTSCHRIFT()
	{
		return dSUMME_POSITIONEN_GUTSCHRIFT;
	}


	public Double get_dSUMME_POSITIONEN_RECHNUNG()
	{
		return dSUMME_POSITIONEN_RECHNUNG;
	}

	

	
	
	
	

	
}
