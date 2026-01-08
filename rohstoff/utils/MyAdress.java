/*
 * hilfsklasse fuer die behandlung von adressen
 */

package rohstoff.utils;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myTranslator;
import panter.gmbh.indep.StringConnector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.VectorDataBaseQuery;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;


public class MyAdress extends MyDataRecordHashMap 
{
	
	private String 			cID_ADRESSE = "";
	private MyDBToolBox 	oDB = null;
	private String 		 	cTO = ""; 
	   	
    private String cLAND_KLARTEXT = "";
	private String cSPRACHE_KLARTEXT = "";
//	private String cWAEHRUNG_KLARTEXT = "";
	private String cZAHLUNGSBEDINGUNGEN_KLARTEXT = "";
	private String cLIEFERBEDINGUNGEN_KLARTEXT = "";
	
	private myTranslator oTranslator = null;
	
	/*
	 * variablensatz fuer die standard-lieferadressen
	 */
	private String 		cL_ID_ADRESSE = null;
	private String 		cL_VORNAME = null;
	private String 		cL_NAME1 = null;
	private String 		cL_NAME2 = null;
	private String 		cL_NAME3 = null;
	private String 		cL_STRASSE = null;
	private String 		cL_HAUSNUMMER = null;
	private String 		cL_PLZ = null;
	private String 		cL_ORT = null;
	private String 		cL_ORTZUSATZ = null;
	private String 		cL_LAENDERCODE = null;

	// lieferinfo, die im TPA-Positionssatz in die Artikelbeschreibung eingeblendet wird
	private String      cL_LIEFERINFO_TPA = null;
	
	
	/*
	 * die firmeninfo wird ebenfalls zur verfuegung gestellt
	 */
	private VectorDataBaseQuery vDBQueryFirmenInfoFormated = null;
	
	
	
	
	/**
	 * @param cid_ADRESSE
	 * @param osES
	 * @param bStandard_LieferAdresse_Is_BaseAdress (true: lieferadresse ist immer die hauptadresse, false: lieferadresse ist die erste oder std. lieferadresse)
	 * @throws myException
	 */
	public MyAdress(String cid_ADRESSE, boolean bStandard_LieferAdresse_Is_BaseAdress) throws myException
	{
		super("JT_ADRESSE",cid_ADRESSE);
	    
	    this.cTO = bibE2.cTO();
	       	 
		this.cID_ADRESSE = cid_ADRESSE;

		
		this.oDB = bibALL.get_myDBToolBox();
	
		cLAND_KLARTEXT	=				oDB.EinzelAbfrage("SELECT LAENDERCODE FROM "+this.cTO+".JD_LAND WHERE ID_LAND="+this.get_UnFormatedValue("ID_LAND"),"","","");
		cSPRACHE_KLARTEXT=				oDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+this.cTO+".JD_SPRACHE WHERE ID_SPRACHE="+this.get_UnFormatedValue("ID_SPRACHE"),"","","");
		//cWAEHRUNG_KLARTEXT=				oDB.EinzelAbfrage("SELECT KURZBEZEICHNUNG FROM "+this.cTO+".JD_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_UnFormatedValue("ID_WAEHRUNG"),"","","");
		cZAHLUNGSBEDINGUNGEN_KLARTEXT=	oDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+this.cTO+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN"),"","","");
		cLIEFERBEDINGUNGEN_KLARTEXT=	oDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+this.cTO+".JT_LIEFERBEDINGUNGEN WHERE ID_LIEFERBEDINGUNGEN="+this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN"),"","","");
		
		try
		{
		    this.vDBQueryFirmenInfoFormated = new   VectorDataBaseQuery(" * ",this.cTO+".JT_FIRMENINFO","","ID_ADRESSE="+cid_ADRESSE,"");
		}
		catch (Exception ex)
		{}
		
		
		/*
		 * liefert entweder die hauptadresse oder wenn eine vorhanden die ERSTE lieferadresse, wenn mehrere vorhanden die standard-adresse
		 */
		VectorDataBaseQuery vLieferAdresse = MyAdress.find_Lieferadressen(cid_ADRESSE);
		int iPos = 0;				// hautpadresse
		
		if (vLieferAdresse.size()>1)
		{
			iPos=1;					// 1. lieferadresse
			for(int k=0;k<vLieferAdresse.size();k++)
			{
				if (vLieferAdresse.get_resultValue("IST_STANDARD",k).equals("Y"))
				{
					iPos=k;			// standard-lieferadresse
				}
			}
		}

		if (bStandard_LieferAdresse_Is_BaseAdress) 
			iPos=0;
		
		this.cL_ID_ADRESSE 		= vLieferAdresse.get_resultValue("ID_ADRESSE",iPos);
		this.cL_VORNAME 		= vLieferAdresse.get_resultValue("VORNAME",iPos);
		this.cL_NAME1 			= vLieferAdresse.get_resultValue("NAME1",iPos);
		this.cL_NAME2 			= vLieferAdresse.get_resultValue("NAME2",iPos);
		this.cL_NAME3 			= vLieferAdresse.get_resultValue("NAME3",iPos);
		this.cL_STRASSE 		= vLieferAdresse.get_resultValue("STRASSE",iPos);
		this.cL_HAUSNUMMER 		= vLieferAdresse.get_resultValue("HAUSNUMMER",iPos);
		this.cL_PLZ 			= vLieferAdresse.get_resultValue("PLZ",iPos);
		this.cL_ORT 			= vLieferAdresse.get_resultValue("ORT",iPos);
		this.cL_ORTZUSATZ 		= vLieferAdresse.get_resultValue("ORTZUSATZ",iPos);
		this.cL_LAENDERCODE		= vLieferAdresse.get_resultValue("LAENDERCODE",iPos);
		this.cL_LIEFERINFO_TPA  = vLieferAdresse.get_resultValue("LIEFERINFO_TPA",iPos);
		
		
		vLieferAdresse = null;
		
	}
	
	
	
    
    /*
     * methode, um zu jeder adresse eine vorhanden standard-telefonnummer zu finden 
     * falls ein eintrag in jt_kommunikation vorhanden ist, der das standard-flag trägt, wieder der genommen
     * sonst der erste gefundene
     */
    public String get_StandardTelefonNumber()
    {

    	String cRueck = "";
		
		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
						this.cTO+".jt_kommunikation,"+this.cTO+".jt_kommunikations_typ "+
						" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
						"jt_kommunikations_typ.basistyp='TELEFON' and jt_kommunikation.id_adresse="+this.cID_ADRESSE;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2];
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][3].equals("Y"))
				{
					/*
					 * standard gefunden
					 */
					cRueck = cErgebnis[i][0]+" "+cErgebnis[i][1]+" "+cErgebnis[i][2];
				}
			}
			
		}
		return cRueck.trim();
    }
	

    public String get_StandardFaxNumber()
    {

    	String cRueck = "";
		
		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
						this.cTO+".jt_kommunikation,"+this.cTO+".jt_kommunikations_typ "+
						" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
						"jt_kommunikations_typ.basistyp='TELEFAX' and jt_kommunikation.id_adresse="+this.cID_ADRESSE;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2];
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][3].equals("Y"))
				{
					/*
					 * standard gefunden
					 */
					cRueck = cErgebnis[i][0]+" "+cErgebnis[i][1]+" "+cErgebnis[i][2];
				}
			}
			
		}
		return cRueck.trim();
    }
    

    
    
    
    /**
     * @param cTYPE
     * @param bALLTypesWhenNone
     * @return mailadresse fuer einen bestimmten belegtyp (oder leer-string)
     * @throws myException
     */
    public String get_EmailForPaper(String cTYPE) throws myException
    {
    	return MyAdress.get_EMAILForPaper(cTYPE,this.oDB,this.cTO,this.cID_ADRESSE);
    }
    

    /**
     * @param cTYPE
     * @param bALLTypesWhenNone
     * @param oDB
     * @param cTableOwner
     * @param cID_ADRESS
     * @return
     * @throws myException
     */
    public static String get_EMAILForPaper(String cTYPE, MyDBToolBox oDB, String cTableOwner, String cID_ADRESS) throws myException
    {

    	if (!(cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_ANGEBOT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_AUFT_BEST) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_RECHNUNG) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_TRANSPORT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT)
    		))
    		throw new myException("MyAdress:get_EMAILForPaper: "+cTYPE+" is no allowed type !!");
    	
    	if (bibALL.isEmpty(cID_ADRESS))
       		throw new myException("MyAdress:get_EMAILForPaper: ID_ADRESS is empty !!");

    	

    	String cRueck = "";
		
		String cQuery = "select EMAIL,TYPE  from "+
							cTableOwner+".jt_email "+
						" where type='"+cTYPE+"' "+
						" AND id_adresse="+cID_ADRESS;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0];
		}
		
		if (bibALL.isEmpty(cRueck))
			cRueck=MyAdress.get_EMAILForAll(cTableOwner,cID_ADRESS);
		
		return cRueck.trim();
    }


    
    /**
     * @param cTYPE
     * @param cTableOwner
     * @param cID_ADRESS
     * @param bALLTypesWhenNone
     * @return s Vector with all eMailAdresses this Adress has for this paper
     * @throws myException
     */
    public static Vector<String> get_vAllEMAILsForPaper(String cTYPE, String cTableOwner, String cID_ADRESS) throws myException
    {

    	Vector<String> vRueck = new Vector<String>();
    	
    	if (!(cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_ANGEBOT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_AUFT_BEST) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_RECHNUNG) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_TRANSPORT) ||
    		cTYPE.equals(myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT)
    		))
    		throw new myException("MyAdress:get_EMAILForPaper: "+cTYPE+" is no allowed type !!");
    	
    	if (bibALL.isEmpty(cID_ADRESS))
       		throw new myException("MyAdress:get_EMAILForPaper: ID_ADRESS is empty !!");

		
		String cQuery = "select EMAIL,TYPE  from "+
							cTableOwner+".jt_email "+
						" where type='"+cTYPE+"' "+
						" AND id_adresse="+cID_ADRESS;
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			for (int i=0;i<cErgebnis.length;i++)
			{
				vRueck.add(cErgebnis[i][0]);
			}
		}

		// falls keine korrekte adresse gefunden, dann wenigstens eine aus dem pool "fuer alle belege"
		if (vRueck.size()==0)
		{
			String cHelp=MyAdress.get_EMAILForAll(cTableOwner,cID_ADRESS);
			if (!bibALL.isEmpty(cHelp))
				vRueck.add(cHelp);
		}
		
		return vRueck;
    }

    
    
    
    

    /**
     * @param cTableOwner
     * @param cID_ADRESS
     * @return
     */
    public static String get_EMAILForAll(String cTableOwner, String cID_ADRESS) throws myException
    {

    	if (bibALL.isEmpty(cID_ADRESS))
       		throw new myException("MyAdress:get_EMAILForAll: ID_ADRESS is empty !!");


    	
      	String cRueck = "";
		String cQuery = "select EMAIL,TYPE  from "+
							cTableOwner+".jt_email "+
						" where type='"+myCONST.EMAIL_TYPE_VALUE_ALLTYPES+"' "+
						" AND id_adresse="+cID_ADRESS;
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0];
		}
		return cRueck.trim();
    }
    

    
    
    
    
    /**
     * @param cTableOwner
     * @param cID_ADRESS
     * @returns all emails with kenner EMAIL_TYPE_VALUE_ALLTYPES
     */
    public static Vector<String> get_vEMAILsForAll(String cTableOwner, String cID_ADRESS) throws myException
    {

    	if (bibALL.isEmpty(cID_ADRESS))
       		throw new myException("MyAdress:get_EMAILForAll: ID_ADRESS is empty !!");


    	
      	Vector<String> vRueck = new Vector<String>();
		String cQuery = "select EMAIL,TYPE  from "+
							cTableOwner+".jt_email "+
						" where type='"+myCONST.EMAIL_TYPE_VALUE_ALLTYPES+"' "+
						" AND id_adresse="+cID_ADRESS;
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			for (int i=0;i<cErgebnis.length;i++)
			{
				vRueck.add(cErgebnis[i][0]);
			}
		}
		
		return	vRueck;
    }
    


    
    
    
    
    /**
     * @return s Mailadresse fuer ALLE belegtypen (oder leer-string)
     */
    public String get_EmailForAll() throws myException
    {
    	return MyAdress.get_EMAILForAll(this.cTO,this.cID_ADRESSE);
    }
    

    
    
    /**
     * @return s Mailadresse die nicht fuer Belege erlaubt ist (oder leer-string)
     */
    public String get_EmailForNone()
    {

      	String cRueck = "";
		String cQuery = "select EMAIL,TYPE  from "+
						this.cTO+".jt_email "+
						" where type='"+myCONST.EMAIL_TYPE_VALUE_NOTYPES+"' "+
						" AND id_adresse="+this.cID_ADRESSE;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0];
		}
		return cRueck.trim();
    }

    
    
    

    
    /**
     * @return s Mailadresse einer bestimmten sorte oder die erste, die er findet
     */
    public String get_EmailForALL_Or_FirstEmail(String cEMAIL_TYPE)
    {

      	String cRueck = "";
		String cQuery = "select EMAIL,TYPE  from "+
							bibE2.cTO()+".jt_email "+
						" where id_adresse="+this.cID_ADRESSE;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");

		// standard-Mailadresse zuerst
		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0];
			
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][1].equals(cEMAIL_TYPE))
				{
					cRueck = cErgebnis[i][0];
					break;
				}
			}
		}
		return cRueck.trim();
    }

 

    
    
     /*
     * findet den ansprechpartner der einer adresse, der das IST_ANPRECHPARTNER - flag hat, oder 
     * den ersten
     */
    public String get_StandardAnsprechpartner(boolean bWennKeinerDannErster,boolean bAnrede,boolean bVorname, boolean bName1, boolean bName2, boolean bName3)
    {

    	String cRueck = "";
		
		String cQuery = "select AN.ANREDE, A.VORNAME,A.NAME1,A.NAME2,A.NAME3,M.IST_ANSPRECHPARTNER FROM " +
							this.cTO+".JT_ADRESSE A," +
							this.cTO+".JT_MITARBEITER M," +
							this.cTO+".JT_ANREDE AN " +
						  " WHERE 	A.ID_ADRESSE=M.ID_ADRESSE_MITARBEITER " +
						  " AND  	A.ID_ANREDE=AN.ID_ANREDE (+)" +
						  " AND  	M.ID_ADRESSE_BASIS="+this.cID_ADRESSE;
		
		String[][] cErgebnis= oDB.EinzelAbfrageInArray(cQuery,"");
		
		StringConnector oStrCon = new StringConnector();
		int iFound = -1;   
		
		if (cErgebnis != null && cErgebnis.length>0)
		{
			if (bWennKeinerDannErster)
			{
				iFound = 0;
			}
			
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][5].equals("Y"))
				{
					iFound = i;
					break;
				}
			}

			if (iFound>-1)
			{
				/*
				 * standard gefunden
				 */
				if (bAnrede) 	oStrCon.add_When_Not_Emtpy(cErgebnis[iFound][0]," ");
				if (bVorname) 	oStrCon.add_When_Not_Emtpy(cErgebnis[iFound][1]," ");
				if (bName1) 	oStrCon.add_When_Not_Emtpy(cErgebnis[iFound][2]," ");
				if (bName2) 	oStrCon.add_When_Not_Emtpy(cErgebnis[iFound][3]," ");
				if (bName3) 	oStrCon.add_When_Not_Emtpy(cErgebnis[iFound][4]," ");
				cRueck = oStrCon.get_CompleteString().trim();
			}
		}
		return cRueck.trim();
    }
    
    
    
    
    
    
    public String get_cLAENDERCODE_KLARTEXT()
    {
        return cLAND_KLARTEXT;
    }
    public String get_cLIEFERBEDINGUNGEN_KLARTEXT()
    {
        return cLIEFERBEDINGUNGEN_KLARTEXT;
    }
    public String get_cSPRACHE_KLARTEXT()
    {
        return cSPRACHE_KLARTEXT;
    }
    public String get_cID_WAEHRUNG_UNFORMATED() throws myException
    {
        return this.get_UnFormatedValue("ID_WAEHRUNG");
    }
    public String get_cID_WAEHRUNG_FORMATED() throws myException
    {
        return this.get_FormatedValue("ID_WAEHRUNG");
    }

    public String get_cZAHLUNGSBEDINGUNGEN_KLARTEXT()
    {
        return cZAHLUNGSBEDINGUNGEN_KLARTEXT;
    }
    
    

     

     

    
    
    /*
     * fuer das laden einer adresse in die maske eines vorgangs
     * der unterschied zwischen formated = true/false ist, dass das feld umrechnungskurs aus dem unformatierten 1.043 ein formatiertes 1,043 macht,
     * da dieses feld das einzige mit nachkomma ist
     */
    public HashMap<String,String> get_hmAdressContent() throws myException
    {
        HashMap<String,String> hmRueck = new HashMap<String,String>();

        String[] cHelpArrayBase 	= {"ID_ADRESSE","VORNAME","NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","ORTZUSATZ","PLZ_POB","POB"};
 
        try
        {
    		for (int i=0;i<cHelpArrayBase.length;i++)
    		{
    		    hmRueck.put(cHelpArrayBase[i],this.get_UnFormatedValue_LeerWennNull(cHelpArrayBase[i]));    
    		}
    		hmRueck.put("LAENDERCODE",this.get_cLAENDERCODE_KLARTEXT());
    		hmRueck.put("SPRACHE",this.get_cSPRACHE_KLARTEXT());
    		hmRueck.put("ID_WAEHRUNG_FREMD",this.get_cID_WAEHRUNG_UNFORMATED());
    		hmRueck.put("ZAHLUNGSBEDINGUNGEN",this.get_cZAHLUNGSBEDINGUNGEN_KLARTEXT());
    		hmRueck.put("LIEFERBEDINGUNGEN",this.get_cLIEFERBEDINGUNGEN_KLARTEXT());

    		hmRueck.put("NAME_ANSPRECHPARTNER",this.get_StandardAnsprechpartner(false,true,true,true,false,false));
    		
    		/*
    		 * lieferadressen werden ab position 1 gefunden, 0 ist immer die hauptadresse
    		 */
    		VectorDataBaseQuery vLieferAdresse = MyAdress.find_Lieferadressen(this.cID_ADRESSE);
    		int iPos = 0;
    		if (vLieferAdresse.size()>1)
    		{
    			iPos=1;
    			for (int k=0;k<vLieferAdresse.size();k++)
    			{
					if (vLieferAdresse.get_resultValue("IST_STANDARD",k).equals("Y"))
					{
						iPos=k;			// standard-lieferadresse
					}
    			}
    		}
    		
    		
   	     	String[] cHelpArrayLiefer 	= {"VORNAME","NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","ORTZUSATZ","LAENDERCODE"};
    		/*
    		 * jetzt die vorige lieferadresse löschen
    		 */
   	     	for (int i=0;i<cHelpArrayLiefer.length;i++)
   	     	{
   	     	    hmRueck.put("L_"+cHelpArrayLiefer[i],vLieferAdresse.get_resultValue(cHelpArrayLiefer[i],iPos));
   	     	}
        }
        catch(Exception ex)
        {
            throw new myException(this.oTranslator.translate("Die Masken-Felder konnten nicht geladen werden ! myAdress:FuelleMaskeMitAdressValues"));
        }
        
        
        return hmRueck;
    }

    
    
    
    
    
    /*
     * info-string einer adresse
     */
    public String get_cAdressInfo() throws myException
    {
        String cRueck = "";
		/*
		 * jetzt das info-feld füllen (das dem benutzer in der messagezeile zeigt, wen er gefunden hat
		 */
		if (! this.get_UnFormatedValue_LeerWennNull("ID_ADRESSE").equals("")) 	cRueck += this.get_UnFormatedValue_LeerWennNull("ID_ADRESSE")+"  /  ";
		if (! this.get_UnFormatedValue_LeerWennNull("NAME1").equals("")) 		cRueck += this.get_UnFormatedValue_LeerWennNull("NAME1")+"  /  ";
		if (! this.get_UnFormatedValue_LeerWennNull("NAME2").equals("")) 		cRueck += this.get_UnFormatedValue_LeerWennNull("NAME2")+"  /  ";
		if (! this.get_UnFormatedValue_LeerWennNull("STRASSE").equals("")) 		cRueck += this.get_UnFormatedValue_LeerWennNull("STRASSE")+"  /  ";
		if (! this.get_UnFormatedValue_LeerWennNull("ORT").equals("")) 			cRueck += this.get_UnFormatedValue_LeerWennNull("ORT")+"  /  ";
		
		return cRueck;
        
    }
    
 
    

    /**
     * @param bID_ADRESSE
     * @param bVORNAME
     * @param bNAME1
     * @param bNAME2
     * @param bNAME3
     * @param bSTRASSE
     * @param bLAND
     * @param bPLZ
     * @param bORT
     * @param cTrenner
     * @return
     */
    public String get_cAdressInfo(boolean bID_ADRESSE,boolean bVORNAME,boolean bNAME1,boolean bNAME2, boolean bNAME3,boolean bSTRASSE, boolean bLAND,boolean bPLZ, boolean bORT, String cTrenner) throws myException
    {
        String cRueck = "";
		/*
		 * jetzt das info-feld füllen (das dem benutzer in der messagezeile zeigt, wen er gefunden hat
		 */
		if (! this.get_UnFormatedValue_LeerWennNull("ID_ADRESSE").equals("") 	&& bID_ADRESSE) cRueck += this.get_UnFormatedValue_LeerWennNull("ID_ADRESSE")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("VORNAME").equals("") 		&& bVORNAME) cRueck += this.get_UnFormatedValue_LeerWennNull("VORNAME")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("NAME1").equals("") 		&& bNAME1) cRueck += this.get_UnFormatedValue_LeerWennNull("NAME1")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("NAME2").equals("") 		&& bNAME2) cRueck += this.get_UnFormatedValue_LeerWennNull("NAME2")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("NAME3").equals("") 		&& bNAME3) cRueck += this.get_UnFormatedValue_LeerWennNull("NAME3")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("STRASSE").equals("") 		&& bSTRASSE) cRueck += this.get_UnFormatedValue_LeerWennNull("STRASSE")+cTrenner;
		if (! this.get_cLAENDERCODE_KLARTEXT().equals("") 								&& bLAND) cRueck += this.get_cLAENDERCODE_KLARTEXT()+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("PLZ").equals("") 			&& bPLZ) cRueck += this.get_UnFormatedValue_LeerWennNull("PLZ")+cTrenner;
		if (! this.get_UnFormatedValue_LeerWennNull("ORT").equals("") 			&& bORT) cRueck += this.get_UnFormatedValue_LeerWennNull("ORT")+cTrenner;
		
		return cRueck;
        
    }
 
    
    
    public String get_cID_ADRESSE() throws myException
    {
        String cRueck=this.get_UnFormatedValue("ID_ADRESSE");
        
        if (cRueck == null || cRueck.equals(""))
            throw new myException("myAdress:get_cID_ADRESSE: Adress-ID is empty !!");
        
        return cRueck;
        
    }
    
	
    public String get_cID_ADRESSE_orig()
    {
        return this.cID_ADRESSE;
    }
    
    
    
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);;
		
	}
 
	
	
	
	public boolean get_bHas_Lieferadresse_WithID(String cID_ADRESSE_LIEFER) throws myException
	{
	    boolean bRueck = false;
	    DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(cID_ADRESSE_LIEFER);
	    
	    if (!oDF.doFormat() || cID_ADRESSE_LIEFER==null || cID_ADRESSE_LIEFER.equals(""))
	        throw new myException("myAdress:get_bHasLagerWithID: ID_ADRESSE_LAGE is not correct !");
	    

	    String cID = oDF.getStringUnFormated();
	    
	    if (this.get_cID_ADRESSE_orig().equals(cID))           // die eigene adresse ist immer lageradresse
	        return true;
	    
	    String cQuery = "SELECT count(*) FROM " +this.cTO+ ".JT_LIEFERADRESSE WHERE JT_LIEFERADRESSE.ID_ADRESSE_LIEFER = "+cID_ADRESSE_LIEFER+ " AND JT_LIEFERADRESSE.ID_ADRESSE_BASIS = "+this.get_cID_ADRESSE_orig();
	    
	    if (this.oDB.EinzelAbfrage(cQuery).equals("1"))
	    {
	        bRueck = true;
	    }
	    return bRueck;
	    
	}
	
	

	   /*
     * methode, um zu jeder adresse eine lieferadresse zu finden:
     * Der Vector enthält die eigene Adresse zuerst, dann
     * alle zugeordneten lieferadressen
     * 
     * Die elemente des Vectors sind arrays[n]
     */
    public static VectorDataBaseQuery find_Lieferadressen(String cID_ADRESSE) throws myException
    {
    	String cTO = bibE2.cTO();
    	VectorDataBaseQuery vRueck = null;    
    	VectorDataBaseQuery vZusatz = null;

		vRueck = new VectorDataBaseQuery("JT_ADRESSE.ID_ADRESSE,VORNAME,NAME1,NAME2,NAME3,STRASSE,HAUSNUMMER,JT_ADRESSE.ID_LAND,LAENDERCODE,PLZ,ORT,ORTZUSATZ,'N' as IST_STANDARD,OEFFNUNGSZEITEN,LIEFERINFO_TPA ",
		                                 cTO+".JT_ADRESSE,"+cTO+".JD_LAND, "+cTO+".JT_FIRMENINFO ",
		                                 " JT_ADRESSE.ID_LAND=JD_LAND.ID_LAND (+) AND JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE ",
		                                 " JT_ADRESSE.ID_ADRESSE="+cID_ADRESSE,
		                                 " ID_ADRESSE"
		                                 );
		
		//NEU_09  -- aktiv-pruefung der lieferadressen
		// bei lieferadressen wird gleich auf aktiv='Y' mitgeprueft
		vZusatz = new VectorDataBaseQuery("JT_ADRESSE.ID_ADRESSE,VORNAME,NAME1,NAME2,NAME3,STRASSE,HAUSNUMMER,JT_ADRESSE.ID_LAND,LAENDERCODE,PLZ,ORT,ORTZUSATZ,IST_STANDARD,OEFFNUNGSZEITEN, LIEFERINFO_TPA ",
		                                  cTO+".JT_ADRESSE,"+cTO+".JT_LIEFERADRESSE,"+cTO+".JD_LAND ",
		                                  "   NVL(JT_ADRESSE.AKTIV,'N')='Y' AND JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER AND  JT_ADRESSE.ID_LAND=JD_LAND.ID_LAND (+)",
		                                  " JT_LIEFERADRESSE.ID_ADRESSE_BASIS="+cID_ADRESSE,
		                                  " ID_ADRESSE");

//		bibALL.System_println("vRueck :"+vRueck.size());
//		bibALL.System_println("vZusatz :"+vZusatz.size());
		
		vRueck.add_Another_Vector(vZusatz);
		
		return vRueck;
    	
    }

    public String get_c_STD_L_HAUSNUMMER()   	{     	return this.cL_HAUSNUMMER;  }
    public String get_c_STD_L_ID_ADRESSE()   	{     	return this.cL_ID_ADRESSE;  }
    public String get_c_STD_L_NAME1()  			{      	return this.cL_NAME1;      }
    public String get_c_STD_L_NAME2()    		{      	return this.cL_NAME2;   }
    public String get_c_STD_L_NAME3()   		{      	return this.cL_NAME3;   }
    public String get_c_STD_L_ORT()	    		{      	return this.cL_ORT;    }
    public String get_c_STD_L_ORTZUSATZ()    	{      	return this.cL_ORTZUSATZ;   }
    public String get_c_STD_L_PLZ()	   			{      	return this.cL_PLZ;    }
    public String get_c_STD_L_STRASSE()    		{      	return this.cL_STRASSE;    }
    public String get_c_STD_L_VORNAME()			{      	return this.cL_VORNAME;    }
    public String get_c_STD_L_LAENDERCODE()		{      	return this.cL_LAENDERCODE;    }
    public String get_c_STD_L_LIEFERINFO_TPA()  {		return this.cL_LIEFERINFO_TPA;  }
    

    /*
     * 
       
       
  ID_ADRESSE     
LETZTE_AENDERUNG  
     */
    
    public String get_cFI_BESCHREIBUNG()  		{    return this._get_FI_Field("BESCHREIBUNG");   }
    public String get_cFI_EINSTUFUNG()  		{    return this._get_FI_Field("EINSTUFUNG");   }
    public String get_cFI_ZAHL_ANGESTELLTE()  		{    return this._get_FI_Field("ZAHL_ANGESTELLTE");   }
    public String get_cFI_ZAHLANGESTELLTE_AM()  		{    return this._get_FI_Field("ZAHLANGESTELLTE_AM");   }
    public String get_cFI_AQUISITIONSKOSTEN()  		{    return this._get_FI_Field("AQUISITIONSKOSTEN");   }
    public String get_cFI_UMSATZPOTENTIAL()  		{    return this._get_FI_Field("UMSATZPOTENTIAL");   }
    public String get_cFI_KREDITLIMIT()  		{    return this._get_FI_Field("KREDITLIMIT");   }
    public String get_cFI_KREDITSTAND()  		{    return this._get_FI_Field("KREDITSTAND");   }
    public String get_cFI_DOKUMENTKOPIEN()  		{    return this._get_FI_Field("DOKUMENTKOPIEN");   }
    public String get_cFI_GRUENDUNGSDATUM()  		{    return this._get_FI_Field("GRUENDUNGSDATUM");   }
    public String get_cFI_STAMMKAPITAL()  		{    return this._get_FI_Field("STAMMKAPITAL");   }
    public String get_cFI_HANDELSREGISTER()  		{    return this._get_FI_Field("HANDELSREGISTER");   }
    public String get_cFI_UMSATZSTEUERLKZ()  		{    return this._get_FI_Field("UMSATZSTEUERLKZ");   }
    public String get_cFI_UMSATZSTEUERID()  		{    return this._get_FI_Field("UMSATZSTEUERID");   }
    public String get_cFI_STEUERNUMMER()  		{    return this._get_FI_Field("STEUERNUMMER");   }
    public String get_cFI_KREDITOR_NUMMER()  		{    return this._get_FI_Field("KREDITOR_NUMMER");   }
    public String get_cFI_DEBITOR_NUMMER()  		{    return this._get_FI_Field("DEBITOR_NUMMER");   }
    public String get_cFI_BESUCHSRYTHMUS()  		{    return this._get_FI_Field("BESUCHSRYTHMUS");   }
    public String get_cFI_ID_BRANCHE()  		{    return this._get_FI_Field("ID_BRANCHE");   }
    public String get_cFI_ID_RECHTSFORM()  		{    return this._get_FI_Field("ID_RECHTSFORM");   }
    public String get_cFI_ID_ZAHLUNGSMEDIUM()  		{    return this._get_FI_Field("ID_ZAHLUNGSMEDIUM");   }
    public String get_cFI_LIEFERMENGE_SCHNITT()  		{    return this._get_FI_Field("LIEFERMENGE_SCHNITT");   }
    public String get_cFI_LIEFERMENGE_MAX()  		{    return this._get_FI_Field("LIEFERMENGE_MAX");   }
    public String get_cFI_KREDITVERS_NR()  		{    return this._get_FI_Field("KREDITVERS_NR");   }
    public String get_cFI_SCHECKDRUCK_JN()  		{    return this._get_FI_Field("SCHECKDRUCK_JN");   }
    public String get_cFI_OEFFNUNGSZEITEN()  		{    return this._get_FI_Field("OEFFNUNGSZEITEN");   }
 
    
  
    public boolean get_bIS_FirmenAdresse() throws myException
    {
    	boolean bRueck = false;
    	if (bibALL.null2leer(this.get_UnFormatedValue("ADRESSTYP")).trim().equals(""+myCONST.ADRESSTYP_FIRMENINFO))
    		bRueck = true;
    	return bRueck;
    }
    public boolean get_bIS_LieferAdresse() throws myException
    {
    	boolean bRueck = false;
    	if (bibALL.null2leer(this.get_UnFormatedValue("ADRESSTYP")).trim().equals(""+myCONST.ADRESSTYP_LIEFERADRESSE))
    		bRueck = true;
    	return bRueck;
    }
    public boolean get_bIS_MitarbeiterAdresse() throws myException
    {
    	boolean bRueck = false;
    	if (bibALL.null2leer(this.get_UnFormatedValue("ADRESSTYP")).trim().equals(""+myCONST.ADRESSTYP_MITARBEITER))
    		bRueck = true;
    	return bRueck;
    }
    public boolean get_bIS_BankAdresse() throws myException
    {
    	boolean bRueck = false;
    	if (bibALL.null2leer(this.get_UnFormatedValue("ADRESSTYP")).trim().equals(""+myCONST.ADRESSTYP_BANK))
    		bRueck = true;
    	return bRueck;
    }

    
    
    
    private  String _get_FI_Field(String cFieldName)
    {
        if (this.vDBQueryFirmenInfoFormated == null)
            return null;
        
        String cRueck = this.vDBQueryFirmenInfoFormated.get_resultValueFormated(cFieldName,0);
        return cRueck;
        
    }
    
  

   public boolean get_bABLADEMENGE_FUER_GUTSCHRIFT() throws myException
   {
    	
    	if (this.vDBQueryFirmenInfoFormated == null)
    		throw new myException("MyAdress:get_bABLADEMENGE_FUER_GUTSCHRIFT:Error false adress-type !!");
	    	
    	return this.vDBQueryFirmenInfoFormated.get_resultValueFormated("ABLADEMENGE_FUER_GUTSCHRIFT",0).equals("Y");
    }

   public boolean get_bLADEMENGE_FUER_RECHNUNG() throws myException
   {
    	
    	if (this.vDBQueryFirmenInfoFormated == null)
    		throw new myException("MyAdress:get_bLADEMENGE_FUER_RECHNUNG:Error false adress-type !!");
	    	
    	return this.vDBQueryFirmenInfoFormated.get_resultValueFormated("LADEMENGE_FUER_RECHNUNG",0).equals("Y");
    }
    

	/**
	 * @return s MyAdress-object mit der BaseAdress einer Lieferadresse. 
	 * Falls es bereits eine basis-Adresse ist, dann wird null zurueckgegeben
	 */
	public MyAdress get_oBaseAdressFromLieferadress() throws myException
	{
		MyAdress oAdressRueck = null;
		
		String cID_Adresse_Start_Basis = this.get_cID_ADRESSE_orig();
		if (this.get_UnFormatedValue("ADRESSTYP").equals(""+myCONST.ADRESSTYP_LIEFERADRESSE))
		{
			// dann ist es eine Lieferadresse
			String cSQLQuery = "SELECT ID_ADRESSE_BASIS FROM "+this.cTO+".JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER="+cID_Adresse_Start_Basis;
			cID_Adresse_Start_Basis = oDB.EinzelAbfrage(cSQLQuery,"@@@@","@@@@","@@@@");
			if (cID_Adresse_Start_Basis.startsWith("@@@"))
				throw new myException("Error Querying <LIEFERADRESSE> start !");
			oAdressRueck = new MyAdress(cID_Adresse_Start_Basis, false);
		}
		
		return oAdressRueck;
	}
    
    
	    

    
}
