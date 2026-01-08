package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;

//NEU_09                komplette klasse neu
public class MyArtikelbezeichung_NG extends MyDataRecordHashMap 
{
	private String 		cID_ARTIKEL_BEZ = null;
	private String 		cID_ADRESSE = null;
	private boolean 	bMustBeListed = false;
	private String 		cEK_OR_VK = null;
	
	private boolean   	bArtBezWasListed = false;
	

	public MyArtikelbezeichung_NG(String cid_artikel_bez) throws myException 
	{
		super();
		this.init(cid_artikel_bez,null,null,false);
	}	
	
	
	/**
	 * @param cid_artikel_bez
	 * @param cid_adresse
	 * @param EK_OR_VK (EK oder VK)
	 * @param mustBeListed (muss bei der firma als zugelassene sorte hinterlegt sein)
	 * @throws myException
	 */
	public MyArtikelbezeichung_NG(String cid_artikel_bez, String cid_adresse,String EK_OR_VK, boolean mustBeListed) throws myException 
	{
		super();
		this.init(cid_artikel_bez, cid_adresse, EK_OR_VK, mustBeListed);
	}
	
	
	
	private void init(String cid_artikel_bez, String cid_adresse,String EK_OR_VK, boolean mustBeListed) throws myException 
	{
		
		this.cID_ARTIKEL_BEZ = cid_artikel_bez;
		this.cID_ADRESSE = cid_adresse;
		this.bMustBeListed = mustBeListed;
		this.cEK_OR_VK = EK_OR_VK;
		
		if (this.bMustBeListed && bibALL.isEmpty(this.cID_ADRESSE))
			throw new myException("If mustBeListed=true  then ID_ADRESSE MUST be NOT empty !");
		
		
		if (bibALL.isEmpty(this.cID_ADRESSE))
			this.cID_ADRESSE="-1";                    // damit dann die erste abfrage automatisch schief geht
		
//CODE:AVWRTZ		
		// abfrage, falls es eine artikebez-lief-zuordnung gibt oder falls es zwingend eine geben muss (this.bMustBeListed = true) 
		String cQuery1 = "SELECT  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ," +			
							"JT_ARTIKEL.ID_ARTIKEL, " +						
							"JT_ARTIKEL_BEZ.ARTBEZ1," +						
							"JT_ARTIKELBEZ_LIEF.ARTBEZ2_ALTERNATIV AS ARTBEZ2, " +				
							"JT_ARTIKEL.ANR1, " +							
							"JT_ARTIKEL_BEZ.ANR2, " +						
							"JT_ARTIKEL.EUCODE, " +							
							"JT_ARTIKEL.EUNOTIZ, " +						
							"JT_ARTIKEL.ZOLLTARIFNR, " +					
							"JT_ARTIKELBEZ_LIEF.ID_EAK_CODE, " +			
							"JT_ARTIKEL.BASEL_CODE, " +						
							"JT_ARTIKEL.BASEL_NOTIZ, " +					
							"JT_ARTIKELBEZ_LIEF.ZAHLUNGSBEDINGUNGEN, " +	
							"JT_ARTIKELBEZ_LIEF.LIEFERBEDINGUNGEN, " +		
							"JT_ARTIKEL.ID_EINHEIT, " +					
							"  NVL(JT_ARTIKEL.ID_EINHEIT_PREIS,JT_ARTIKEL.ID_EINHEIT) AS ID_EINHEIT_PREIS, " +
							"  NVL(JT_ARTIKEL.DIENSTLEISTUNG,'N') AS DIENSTLEISTUNG, "+
							"  NVL(JT_ARTIKEL.IST_PRODUKT,'N') AS IST_PRODUKT, "+
							"  NVL(JT_ARTIKEL.END_OF_WASTE,'N') AS END_OF_WASTE "+
							"  FROM " +
							bibE2.cTO()+".JT_ARTIKELBEZ_LIEF, "+
							bibE2.cTO()+".JT_ARTIKEL_BEZ, "+
							bibE2.cTO()+".JT_ARTIKEL " +
							"  WHERE " +
							" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
							" JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ  AND " +
							"   NVL(JT_ARTIKEL.AKTIV,'N')='Y'  AND "+
							"   NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' AND "+
							" JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='"+this.cEK_OR_VK+"' AND " +
							" JT_ARTIKELBEZ_LIEF.ID_ADRESSE=" +cid_adresse+ " AND "+
							" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cid_artikel_bez;

		

		 String	cQuery2 = "SELECT  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ," +			
								"JT_ARTIKEL.ID_ARTIKEL, " +						
								"JT_ARTIKEL_BEZ.ARTBEZ1," +						
								"JT_ARTIKEL_BEZ.ARTBEZ2, " +					
								"JT_ARTIKEL.ANR1, " +							
								"JT_ARTIKEL_BEZ.ANR2, " +						
								"JT_ARTIKEL.EUCODE, " +							
								"JT_ARTIKEL.EUNOTIZ, " +						
								"JT_ARTIKEL.ZOLLTARIFNR, " +					
								"-1 AS ID_EAK_CODE, " +							// dummy um gleichstand herzustellen
								"JT_ARTIKEL.BASEL_CODE, " +						
								"JT_ARTIKEL.BASEL_NOTIZ, " +					
								"null AS ZAHLUNGSBEDINGUNGEN, " +				// dummy um gleichstand herzustellen
								"null AS LIEFERBEDINGUNGEN, " +					// dummy um gleichstand herzustellen
								"JT_ARTIKEL.ID_EINHEIT, " +					
								"  NVL(JT_ARTIKEL.ID_EINHEIT_PREIS,JT_ARTIKEL.ID_EINHEIT) AS ID_EINHEIT_PREIS, " +					
								"  NVL(JT_ARTIKEL.DIENSTLEISTUNG,'N') AS DIENSTLEISTUNG, "+
								"  NVL(JT_ARTIKEL.IST_PRODUKT,'N') AS IST_PRODUKT, "+
								"  NVL(JT_ARTIKEL.END_OF_WASTE,'N') AS END_OF_WASTE "+
								"  FROM " +
								bibE2.cTO()+".JT_ARTIKEL_BEZ, "+
								bibE2.cTO()+".JT_ARTIKEL " +
								"  WHERE " +
								" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
								"   NVL(JT_ARTIKEL.AKTIV,'N')='Y'  AND "+
								"   NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' AND "+
								" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cid_artikel_bez;
			

		try
		{
			if (this.bMustBeListed)
			{
				this.INIT_Hash(cQuery1);
				this.bArtBezWasListed = true;
			}
			else
			{
				// wenn es nicht zwingend ist, dass eine listung vorliegt, dann zuerst mit listung testen,
				// dann ohne listung laden
				try
				{
					if (!bibALL.isEmpty(this.cEK_OR_VK))
					{
						this.INIT_Hash(cQuery1);
						this.bArtBezWasListed = true;
					}
					else
					{
						this.INIT_Hash(cQuery2);
						this.bArtBezWasListed = false;
					}
				}
				catch (myException exx)
				{
					this.INIT_Hash(cQuery2);
					this.bArtBezWasListed = false;
				}
			}
		}
		catch (myException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new myException("MyArtikelbezeichung_NG:constructor:Error building query !");
		}
	}
	
	


	public boolean get_bArtBezWasListed() 
	{
		return this.bArtBezWasListed;
	}


	public boolean get_bMustBeListed() 
	{
		return bMustBeListed;
	}


	public String get_cEK_OR_VK() 
	{
		return cEK_OR_VK;
	}


	public String get_cID_ADRESSE() 
	{
		return cID_ADRESSE;
	}


	public String get_cID_ARTIKEL_BEZ() 
	{
		return cID_ARTIKEL_BEZ;
	}

	
	public String get_ID_ARTIKEL_BEZ() 		throws myException {return this.get_UnFormatedValue("ID_ARTIKEL_BEZ"); 			}
	public String get_ID_ARTIKEL()		 	throws myException {return this.get_UnFormatedValue("ID_ARTIKEL"); 				}
	public String get_ARTBEZ1() 			throws myException {return this.get_UnFormatedValue("ARTBEZ1"); 				}
	
	
	
	public String get_ARTBEZ2() 			throws myException 
	{
		String cARTBEZ2 = this.get_UnFormatedValue("ARTBEZ2");
		
		//nachsehen, ob es zusatzinfos gibt
		if (S.isFull(this.cID_ADRESSE) && S.isFull(this.cEK_OR_VK))
		{
			
			//jetzt nachsehen, ob es eine ARTIKEL_BEZ_LIEF - eintragung dazu gibt
			RECLIST_ARTIKELBEZ_LIEF  rlArtbezLief = new RECLIST_ARTIKELBEZ_LIEF(
					"SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+this.cID_ARTIKEL_BEZ+
																	" AND ID_ADRESSE="+this.cID_ADRESSE+
																	" AND ARTBEZ_TYP="+bibALL.MakeSql(this.cEK_OR_VK));
			
			//falls einer oder mehrere gefunden, dann den ersten
			if (rlArtbezLief.get_vKeyValues().size()>0) 
			{
				cARTBEZ2 = new RECORD_ARTBEZ_LIEF_extend(rlArtbezLief.get(0)).get_ARTBEZ_2_Incl_Specials();
			}
		}
		
		
		return cARTBEZ2; 				
	}
	
	
	
	
	
	public String get_ANR1() 				throws myException {return this.get_UnFormatedValue("ANR1"); 					}
	public String get_ANR2() 				throws myException {return this.get_UnFormatedValue("ANR2"); 					}
	public String get_EUCODE() 				throws myException {return this.get_UnFormatedValue("EUCODE"); 					}
	public String get_EUNOTIZ() 			throws myException {return this.get_UnFormatedValue("EUNOTIZ"); 				}
	public String get_ZOLLTARIFNR() 		throws myException {return this.get_UnFormatedValue("ZOLLTARIFNR"); 			}
	public String get_ID_EAK_CODE() 		throws myException {return this.get_UnFormatedValue("ID_EAK_CODE"); 			}
	public String get_BASEL_CODE() 			throws myException {return this.get_UnFormatedValue("BASEL_CODE"); 				}
	public String get_BASEL_NOTIZ() 		throws myException {return this.get_UnFormatedValue("BASEL_NOTIZ"); 			}
	public String get_ZAHLUNGSBEDINGUNGEN() throws myException {return this.get_UnFormatedValue("ZAHLUNGSBEDINGUNGEN"); 	}
	public String get_LIEFERBEDINGUNGEN() 	throws myException {return this.get_UnFormatedValue("LIEFERBEDINGUNGEN"); 		}
	public boolean get_bIstDienstleistung() throws myException {return bibALL.null2leer(this.get_UnFormatedValue("DIENSTLEISTUNG")).equals("Y"); 		}
	public boolean get_bIstProdukt() 		throws myException {return bibALL.null2leer(this.get_UnFormatedValue("IST_PRODUKT")).equals("Y"); 		}
	public boolean get_bIstEndOfWaste() 	throws myException {return bibALL.null2leer(this.get_UnFormatedValue(_DB.ARTIKEL$END_OF_WASTE)).equals("Y"); 		}

	
	public String get_cEINHEIT()throws myException 
	{
		return bibDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_UnFormatedValue("ID_EINHEIT"));
	}
	
	public String get_cEINHEIT_PREIS()throws myException 
	{
		return bibDB.EinzelAbfrage("SELECT EINHEITKURZ FROM "+bibE2.cTO()+".JT_EINHEIT WHERE ID_EINHEIT="+this.get_UnFormatedValue("ID_EINHEIT_PREIS"));
	}

	
}
