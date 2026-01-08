package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSKLASSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ANREDE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringConnector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class RECORD_ADRESSE_extend extends RECORD_ADRESSE
{

	private RECORD_ADRESSE_extend  record_main_adress = null;
	
	
	public RECORD_ADRESSE_extend() throws myException
	{
		super();
	}

	public RECORD_ADRESSE_extend(long unformated, MyConnection Conn)  throws myException
	{
		super(unformated, Conn);
	}

	public RECORD_ADRESSE_extend(long unformated) throws myException 
	{
		super(unformated);
	}

	public RECORD_ADRESSE_extend(RECORD_ADRESSE recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_ADRESSE_extend(String c_id_or_whereblock, MyConnection Conn)	throws myException
	{
		super(c_id_or_whereblock, Conn);
	}

	public RECORD_ADRESSE_extend(String c_id_or_whereblock) throws myException
	{
		super(c_id_or_whereblock);
	}

	
	
    /*
     * methode, um zu jeder adresse eine vorhanden standard-telefonnummer zu finden 
     * falls ein eintrag in jt_kommunikation vorhanden ist, der das standard-flag trägt, wieder der genommen
     * sonst der erste gefundene
     */
    public String get_StandardTelefonNumber() throws myException
    {

    	String cRueck = "";
		
		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
						bibE2.cTO()+".jt_kommunikation,"+bibE2.cTO()+".jt_kommunikations_typ "+
						" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
						"jt_kommunikations_typ.basistyp='TELEFON' and jt_kommunikation.id_adresse="+this.get_ID_ADRESSE_cUF();
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
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
	

    public String get_StandardFaxNumber()  throws myException
    {

    	String cRueck = "";
		
		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
							bibE2.cTO()+".jt_kommunikation,"+bibE2.cTO()+".jt_kommunikations_typ "+
						" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
						"jt_kommunikations_typ.basistyp='TELEFAX' and jt_kommunikation.id_adresse="+this.get_ID_ADRESSE_cUF();
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
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
    
    
    
    
    
    
    
    
    
    
    
    public String get_First_eMailAdress4Paper(String cVORGANGTYP, boolean bAddMitarbeiter, boolean bAddLieferadressen) throws myException
    {
    	String cRueck = null;
    	
    	VectorSingle vTest = this.get_V_eMailAdresses4Paper(cVORGANGTYP, bAddMitarbeiter, bAddLieferadressen);
    	
    	if (vTest.size()>0)
    	{
    		cRueck = vTest.get(0);
    	}
    	return cRueck;
    }
    
    

	
    public VectorSingle get_V_eMailAdresses4Paper(String cVORGANGTYP, boolean bAddMitarbeiter, boolean bAddLieferadressen) throws myException
    {

    	Vector<String> vAllowed = bibALL.get_Vector(myCONST.EMAIL_TYPE_VALUE_ANGEBOT, 
    												myCONST.EMAIL_TYPE_VALUE_RECHNUNG,
    												myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT, 
    												myCONST.EMAIL_TYPE_VALUE_AUFT_BEST, 
    												myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN, 
    												myCONST.EMAIL_TYPE_VALUE_TRANSPORT, 
    												myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT, 
    												myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT,
    												myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT);
    	
    	
    	VectorSingle vRueck = new VectorSingle();
    	
    	if (!vAllowed.contains(cVORGANGTYP))
    	{
    		throw new myException(this,cVORGANGTYP+" is not a correct type !");
    	}
    			
		//zuerst die direkten mail-adressen
		for (int k=0;k<this.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
		{
			RECORD_EMAIL oMail = this.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
			if (S.isFull(oMail.get_EMAIL_cUF_NN("")))
			{
				if (this.bCheckMail2(oMail,cVORGANGTYP))
				{
					vRueck.add(oMail.get_EMAIL_cUF_NN(""));
				}
			}
		}
		
		
		if (bAddMitarbeiter)
		{
			//dann die aktiven mitarbeiter-adresse einsammeln
			for (int i=0; i<this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().size();i++)
			{
				RECORD_ADRESSE oAdresseMitarbeiter = this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
				if (oAdresseMitarbeiter.is_AKTIV_YES())
				{
					//dann die Mitarbeiter mail-adressen
					for (int k=0;k<oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
					{
						RECORD_EMAIL oMailMitarbeiter = oAdresseMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
						if (S.isFull(oMailMitarbeiter.get_EMAIL_cUF_NN("")))
						{
							if (this.bCheckMail2(oMailMitarbeiter,cVORGANGTYP))
							{
								vRueck.add(oMailMitarbeiter.get_EMAIL_cUF_NN(""));
							}
						}
					}
				}
			}
		}

		if (bAddLieferadressen)
		{
			//dann die aktiven liefer-adresse einsammeln
			for (int i=0; i<this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().size();i++)
			{
				RECORD_ADRESSE oAdresseLiefer = this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer();
				if (oAdresseLiefer.is_AKTIV_YES())
				{
					//dann die Lieferadressen - mail-adressen
					for (int k=0;k<oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();k++)
					{
						RECORD_EMAIL oMailLieferadresse = oAdresseLiefer.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(k);
						if (S.isFull(oMailLieferadresse.get_EMAIL_cUF_NN("")))
						{
							if (this.bCheckMail2(oMailLieferadresse,cVORGANGTYP))
							{
								vRueck.add(oMailLieferadresse.get_EMAIL_cUF_NN(""));
							}
						}
					}
				}
			}
		}

    	return vRueck;
    }
    
//  alt, a, 2014-09-17 inaktiv  
//	private boolean bCheckMail(RECORD_EMAIL oMail, String eMailBelegtyp) throws myException
//	{
//		boolean bRueck = true;
//		
//		if (S.isFull(eMailBelegtyp))
//		{
//			bRueck = false;
//			
//			if (oMail.get_TYPE_cUF_NN("").equals(myCONST.EMAIL_TYPE_VALUE_ALLTYPES) || 
//				oMail.get_TYPE_cUF_NN("").equals(eMailBelegtyp))
//			{
//				bRueck = true;
//			}
//		}
//		return bRueck;
//	}
//

	
	
	//2014-09-17: neue findung mit matrixdefinition statt dropdown-def
	private boolean bCheckMail2(RECORD_EMAIL oMail, String eMailBelegtyp) throws myException
	{
		boolean bRueck = true;
		
		if (S.isFull(eMailBelegtyp))
		{
			bRueck = false;
			
			Vector<String> vQualis = new RECORD_EMAIL_SPECIAL(oMail).get_vSelectedKeys();
			
			if (vQualis.contains(eMailBelegtyp))
			{
				bRueck = true;
			}
		}

		return bRueck;
	}

	
	
	
    /*
     * findet den ansprechpartner der einer adresse, der das IST_ANPRECHPARTNER - flag hat, oder 
     * den ersten
     */
    public String get_StandardAnsprechpartner(boolean bWennKeinerDannErster,boolean bAnrede,boolean bVorname, boolean bName1, boolean bName2, boolean bName3) throws myException
    {

    	String cRueck = "";
		
		String cQuery = "select AN.ANREDE, A.VORNAME,A.NAME1,A.NAME2,A.NAME3,M.IST_ANSPRECHPARTNER FROM " +
							bibE2.cTO()+".JT_ADRESSE A," +
							bibE2.cTO()+".JT_MITARBEITER M," +
							bibE2.cTO()+".JT_ANREDE AN " +
						  " WHERE 	A.ID_ADRESSE=M.ID_ADRESSE_MITARBEITER " +
						  " AND  	A.ID_ANREDE=AN.ID_ANREDE (+)" +
						  " AND     NVL(A.AKTIV,'N')='Y' "+
						  " AND  	M.ID_ADRESSE_BASIS="+this.get_ID_ADRESSE_cUF();
		
		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");
		
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

	
    
    
    public RECLIST_ADRESSE get_RECLIST_ADRESSEN_MITARBEITER(boolean bOnlyActiv) throws myException
    {
    	RECLIST_ADRESSE reclistRueck = new RECLIST_ADRESSE(this.get_oConn());
    	
    	for (int i=0;i<this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get_vKeyValues().size();i++)
    	{
    		if (bOnlyActiv && this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter().is_AKTIV_NO())
    		{
    			continue;
    		}
    		
    		reclistRueck.ADD(this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter(), false);
    	}
    	return reclistRueck;
    }
    
//    /**
//     * 
//     * @return null falls kein Mitarbeiter vorhanden ist, sonst den Ansprechpartner oder den ersten aktiven den er findet
//     * @throws myException
//     */
//    public RECORD_ADRESSE get_Anpsrechpartner_or_First_RECORD_MITARBEITER() throws myException
//    {
//    	RECLIST_ADRESSE reclistTest = this.get_RECLIST_ADRESSEN_MITARBEITER(true);
//    	
//    	RECORD_ADRESSE recRueck = null;
//    	if (reclistTest.get_vKeyValues().size()>0)
//    	{
//    		recRueck = reclistTest.get(0);              //der erste in der liste wird erstmal festgehalte
//    	}
//    	
//    	
//    	for (int i=0;i<reclistTest.get_vKeyValues().size();i++)
//    	{
//    		if (reclistTest.get(i).get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter().get(0).is_IST_ANSPRECHPARTNER_YES())
//    		{
//    			recRueck = reclistTest.get(i);
//    		}
//    	}
//    	return recRueck;
//    }

    
    
    /**
     * 
     * @param cVORGANGSART
     * Sucht die Firmenspezifischen Werte Werte fuer: Ansprechpartner, Telefonnummer (Kunde), Faxnummer(Kunde), eMail-Kunde fuer eine 
     * Firma heraus.
     * Suchweg: Zuerst werden die Standard-Werte der Firma gezogen, dann wird nachgeschaut, ob es einen fuer den Beleg
     * qualifizierten aktiven Mitarbeiter gibt, wenn ja, dann dessen name, dessen standart-Tel/fax/ und Mail fuer diesen Beleg
     * Hashkeys sind:  ANSPRECHPARTNER,TELEFON,TELEFAX,EMAIL
     * @return
     * @throws myException 
     */
    public HashMap<String, String> get_hmFormularFelderAnsprechpartner(String cVORGANGSART) throws myException
    {
    	HashMap<String, String>  hmRueck = new HashMap<String, String>();
    	
    	String cFieldHash = "ASP_"+cVORGANGSART;
    	
    	//step 1
    	String cAnsprech = 	this.get_StandardAnsprechpartner(true,true,true,true,false,false);
    	String cTelefon = 	this.get_StandardTelefonNumber();
    	String cTelefax = 	this.get_StandardFaxNumber();
    	String cEmail = 	this.get_First_eMailAdress4Paper(cVORGANGSART,true,true);
    	
    	//jetzt nachseheh, ob es einen Mitarbeiter fuer diesen Vorgang gibt
    	RECLIST_MITARBEITER  rlMitarbeiter = this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis();
    	
    	for (int i=0;i<rlMitarbeiter.get_vKeyValues().size();i++)
    	{
    		RECORD_MITARBEITER  recMA = rlMitarbeiter.get(i);
    		
    		if (recMA != null && recMA.containsKey(cFieldHash) && recMA.get_UnFormatedValue(cFieldHash,"N").equals("Y"))
    		{
    			RECORD_ADRESSE recAdrMA = recMA.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
    			if (recAdrMA != null && recAdrMA.is_AKTIV_YES())
    			{
    				RECORD_ADRESSE_extend recAdrMAExt = new RECORD_ADRESSE_extend(recAdrMA);
    				
    				String cName = "";
    				if (recAdrMAExt.get_UP_RECORD_ANREDE_id_anrede()!=null)
    				{
    					cName = cName + recAdrMAExt.get_UP_RECORD_ANREDE_id_anrede().get_ANREDE_cF_NN("")+" ";
    				}
    				cName = cName + recAdrMAExt.get_VORNAME_cUF_NN("")+" ";
    				cName = cName + recAdrMAExt.get_NAME1_cUF_NN("")+" ";
    				
    				cName = cName.trim();
    				cName = bibALL.ReplaceTeilString(cName, "  ", " ");

    				cAnsprech = cName;
    				cEmail = recAdrMAExt.get_First_eMailAdress4Paper(cVORGANGSART,true,true);

    				cTelefon = "";
    				cTelefax = "";
    				
    				RECLIST_KOMMUNIKATION recListKom = recAdrMAExt.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse();
    				for (int k=0;k<recListKom.get_vKeyValues().size();k++)
    				{
    					RECORD_KOMMUNIKATION  recKom = recListKom.get(k);
    					
    					if (recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ()!=null && 
    						recKom.is_IST_STANDARD_YES() &&	
    						recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ().get_BASISTYP_cUF_NN("").equals("TELEFON"))
    					{
    						cTelefon = recKom.get_WERT_LAENDERVORWAHL_cUF_NN("")+" "+
    									recKom.get_WERT_VORWAHL_cUF_NN("")+" "+
    									recKom.get_WERT_RUFNUMMER_cUF_NN("");
    						
    					}

    					if (recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ()!=null && 
        					recKom.is_IST_STANDARD_YES() &&	
        					recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ().get_BASISTYP_cUF_NN("").equals("TELEFAX"))
        				{
    						cTelefax = recKom.get_WERT_LAENDERVORWAHL_cUF_NN("")+" "+
        								recKom.get_WERT_VORWAHL_cUF_NN("")+" "+
        								recKom.get_WERT_RUFNUMMER_cUF_NN("");
        						
        				}
    				}
    				
    				
    				//wurde einer gefunden, dann raus
    				break;
    			}
    		}
    	}
    	
    	
    	hmRueck.put("ANSPRECHPARTNER", cAnsprech);
    	hmRueck.put("TELEFON", cTelefon);
    	hmRueck.put("TELEFAX", cTelefax);
    	hmRueck.put("EMAIL", cEmail);
    	
    	return hmRueck;
    }
    
    
    
    /**
     * 
     * @param cVORGANGSART
     * Sucht die Firmenspezifischen Werte Werte fuer: Ansprechpartner, Telefonnummer (Kunde), Faxnummer(Kunde), eMail-Kunde fuer eine 
     * Firma heraus.
     * Suchweg: Zuerst werden die Standard-Werte der Firma gezogen, dann wird nachgeschaut, ob es einen fuer den Beleg
     * qualifizierten aktiven Mitarbeiter gibt, wenn ja, dann dessen name, dessen standart-Tel/fax/ und Mail fuer diesen Beleg
     * Hashkeys sind:  ANSPRECHPARTNER,TELEFON,TELEFAX
     * @return
     * @throws myException 
     */
    public HashMap<String, String> get_hmFormularFelderAnsprechpartner_ohneEmail(String cVORGANGSART) throws myException
    {
    	HashMap<String, String>  hmRueck = new HashMap<String, String>();
    	
    	String cFieldHash = "ASP_"+cVORGANGSART;
    	
    	//step 1
    	String cAnsprech = 	this.get_StandardAnsprechpartner(true,true,true,true,false,false);
    	String cTelefon = 	this.get_StandardTelefonNumber();
    	String cTelefax = 	this.get_StandardFaxNumber();
    	
    	//jetzt nachseheh, ob es einen Mitarbeiter fuer diesen Vorgang gibt
    	RECLIST_MITARBEITER  rlMitarbeiter = this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis();
    	
    	for (int i=0;i<rlMitarbeiter.get_vKeyValues().size();i++)
    	{
    		RECORD_MITARBEITER  recMA = rlMitarbeiter.get(i);
    		
    		if (recMA != null && recMA.containsKey(cFieldHash) && recMA.get_UnFormatedValue(cFieldHash,"N").equals("Y"))
    		{
    			RECORD_ADRESSE recAdrMA = recMA.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
    			if (recAdrMA != null && recAdrMA.is_AKTIV_YES())
    			{
    				RECORD_ADRESSE_extend recAdrMAExt = new RECORD_ADRESSE_extend(recAdrMA);
    				
    				String cName = "";
    				if (recAdrMAExt.get_UP_RECORD_ANREDE_id_anrede()!=null)
    				{
    					cName = cName + recAdrMAExt.get_UP_RECORD_ANREDE_id_anrede().get_ANREDE_cF_NN("")+" ";
    				}
    				cName = cName + recAdrMAExt.get_VORNAME_cUF_NN("")+" ";
    				cName = cName + recAdrMAExt.get_NAME1_cUF_NN("")+" ";
    				
    				cName = cName.trim();
    				cName = bibALL.ReplaceTeilString(cName, "  ", " ");

    				cAnsprech = cName;

    				cTelefon = "";
    				cTelefax = "";
    				
    				RECLIST_KOMMUNIKATION recListKom = recAdrMAExt.get_DOWN_RECORD_LIST_KOMMUNIKATION_id_adresse();
    				for (int k=0;k<recListKom.get_vKeyValues().size();k++)
    				{
    					RECORD_KOMMUNIKATION  recKom = recListKom.get(k);
    					
    					if (recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ()!=null && 
    						recKom.is_IST_STANDARD_YES() &&	
    						recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ().get_BASISTYP_cUF_NN("").equals("TELEFON"))
    					{
    						cTelefon = recKom.get_WERT_LAENDERVORWAHL_cUF_NN("")+" "+
    									recKom.get_WERT_VORWAHL_cUF_NN("")+" "+
    									recKom.get_WERT_RUFNUMMER_cUF_NN("");
    						
    					}

    					if (recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ()!=null && 
        					recKom.is_IST_STANDARD_YES() &&	
        					recKom.get_UP_RECORD_KOMMUNIKATIONS_TYP_id_kommunikations_typ().get_BASISTYP_cUF_NN("").equals("TELEFAX"))
        				{
    						cTelefax = recKom.get_WERT_LAENDERVORWAHL_cUF_NN("")+" "+
        								recKom.get_WERT_VORWAHL_cUF_NN("")+" "+
        								recKom.get_WERT_RUFNUMMER_cUF_NN("");
        						
        				}
    				}
    				
    				
    				//wurde einer gefunden, dann raus
    				break;
    			}
    		}
    	}
    	
    	
    	hmRueck.put("ANSPRECHPARTNER", cAnsprech);
    	hmRueck.put("TELEFON", cTelefon);
    	hmRueck.put("TELEFAX", cTelefax);
    	
    	return hmRueck;
    }
    

 
    /**
     * 2013-05-16
     * @param cID_ADRESSE
     * @return
     * @throws myException 
     */
    public boolean get_bIstMandantenLager() throws myException{
    	boolean bRueck = false;
    	
    	if (this.get_ID_ADRESSE_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))) {
    		bRueck = true;
    	} else {
    		if (this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer("", "", true).
    				get(0).get_UP_RECORD_ADRESSE_id_adresse_basis().get_ID_ADRESSE_cUF_NN("-").
    				equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))) {
    			bRueck = true;
    		} 
    	}
    	return bRueck;
    }
    
    
    
    
    

    /**
     * 
     * @return name1 name2 ort
     * @throws myException
     */
    public String get__FullNameAndAdress_Typ1() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2,_DB.ADRESSE$ORT));
    	return cRueck;
    }
  
    /**
     * 
     * @return name1 name2 ort
     * @throws myException
     */
    public String get__Name1_and_Name2() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2));
    	return cRueck;
    }

    
    /**
     * 
     * @return vorname name1 name2 ort
     * @throws myException
     */
    public String get__FullNameAndAdress_Typ2() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$VORNAME, _DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2,_DB.ADRESSE$ORT));
    	return cRueck;
    }

    /**
     * 
     * @return vorname name1 name2 ort und (id)
     * @throws myException
     */
    public String get__FullNameAndAdress_mit_id() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$VORNAME, _DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2,_DB.ADRESSE$ORT));
    	cRueck = cRueck+" ("+this.fs(ADRESSE.id_adresse)+")";
    	return cRueck;
    }
   
    /**
     * 
     * @return name1 name2 ort
     * @throws myException
     */
    public String get__strasse_hausnummer() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(ADRESSE.strasse.fn(),ADRESSE.hausnummer.fn()));
    	return cRueck;
    }
    
 
    
    
    /**
     * 
     * @return s recEMAIL with singular eMailAdress 4 sending original RE/GS - Papers or null, when not eMail4Sending 
     * @throws myException 
     */
    public RECORD_EMAIL  get_eMAIL_4_RECH_GUT_MAIL() throws myException {
    	RECLIST_EMAIL  rlEmail = new RECLIST_EMAIL("SELECT "+_DB.EMAIL+".*"+new ownTerm().get_FROM_STATEMENT());
    	if (rlEmail.get_vKeyValues().size()==1) {
    		return rlEmail.get(0);
    	}
    	return null;
    }

    
    
    /**
     * 
     * returns true when adress contains a single email for sending rg-original-files
     * @throws myException 
     */
    public boolean get_bAdresse_is_4_MailingOriginal_RG() throws myException {
    	String cSQL = "SELECT COUNT(*) "+new ownTerm().get_FROM_STATEMENT();
    	String cCOUNT = bibDB.EinzelAbfrage(cSQL,"","","");
    	return cCOUNT.equals("1");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private class ownTerm extends GenTERM {
    	private GenTERM gtJoin1 = new GenTERM();
    	private GenTERM gtJoin2 = new GenTERM();

		public ownTerm() throws myException {
			super();
			this.
			AppendTerm(_DB.Z_ADRESSE$ID_ADRESSE, 		"=", RECORD_ADRESSE_extend.this.get_ID_ADRESSE_cUF(),false,false).
			AppendTerm(_DB.Z_QUALIFIER$TABLENAME, 		"=", _DB.EMAIL.substring(3),false,true).
			AppendTerm(_DB.Z_QUALIFIER$CLASS_KEY, 		"=", Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP,false,true).
			AppendTerm(_DB.Z_QUALIFIER$DATENBANKTAG, 	"=", myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT, false,true);
			
			gtJoin1.AppendTerm(_DB.Z_ADRESSE$ID_ADRESSE, "=", _DB.Z_EMAIL$ID_ADRESSE);
			gtJoin2.AppendTerm(_DB.Z_EMAIL$ID_EMAIL, "=", _DB.Z_QUALIFIER$ID_TABLE);

		}
		
		public String get_FROM_STATEMENT() throws myException {
			return " FROM "+bibE2.cTO()+"."+_DB.ADRESSE+
			" INNER JOIN "+bibE2.cTO()+"."+_DB.EMAIL+" ON ("+this.gtJoin1.get_TERMS_WITH("AND")+")"+
			" INNER JOIN "+bibE2.cTO()+"."+_DB.QUALIFIER+" ON ("+this.gtJoin2.get_TERMS_WITH("AND")+")"+
			" WHERE "+this.get_TERMS_WITH("AND");
		}
    }
    
    
    
    
	public String get_Signatur_name_ort() throws myException {
		Vector<String> vFelder = bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$ORT);
		return this.get___KETTE(vFelder);
	}
  
    
    
    
	/**
	 * 
	 * @return name1, name2, ort
	 * @throws myException
	 */
	public String get_Signatur_String_kurz() throws myException {
		Vector<String> vFelder = bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT);
		return this.get___KETTE(vFelder);
	}
	
//	public MyE2_String get_Signatur_MyE2_String_kurz() throws myException {
//		return new MyE2_String(this.get_Signatur_String_kurz());
//	}
//
    
    
    
	//2015-07-07: sucht die Hauptadresse raus, falls es eine Lieferadresse oder mitarbeiteradresse ist
	
	
	/**
	 * 
	 * @return sucht die Hauptadresse raus, falls es eine Hauptadresse, Lieferadresse oder mitarbeiteradresse ist, sonst null
	 * @throws myException
	 */
	public RECORD_ADRESSE_extend  get_main_Adress()  throws myException{
		
		if (this.record_main_adress==null) {
			if (this.get_ADRESSTYP_lValue(-1l).longValue()==myCONST.ADRESSTYP_FIRMENINFO) {
				this.record_main_adress= new RECORD_ADRESSE_extend(this);
			} else if (this.get_ADRESSTYP_lValue(-1l).longValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
				this.record_main_adress= new RECORD_ADRESSE_extend(this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis());
			} else if (this.get_ADRESSTYP_lValue(-1l).longValue()==myCONST.ADRESSTYP_MITARBEITER) {
				this.record_main_adress= new RECORD_ADRESSE_extend(this.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis());
			} else {
				throw new myException(this, "Querying MainAdress on Adress-ID :"+this.get_ID_ADRESSE_cUF()+" was not sucessfull!");
			}
		}
		return this.record_main_adress;
	}
	
	
	
	
	public RECORD_ADRESSE_extend  base_Adress()  throws myException{
		return this.get_main_Adress();
	}	
    
    
	/**
	 * 
	 * @return name1 name2 name3 , strasse hausnummer , plz ort
	 * @throws myException
	 */
	public String get_block_String_ganze_Adresse() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$VORNAME, _DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$NAME3);
		Vector<String> vFelder2 = bibVECTOR.get_Vector(_DB.ADRESSE$STRASSE, _DB.ADRESSE$HAUSNUMMER);
		Vector<String> vFelder3 = bibVECTOR.get_Vector(_DB.ADRESSE$PLZ, _DB.ADRESSE$ORT);
		
		return this.get___KETTE(vFelder1)+", "+this.get___KETTE(vFelder2)+", "+this.get___KETTE(vFelder3);
	}

	/**
	 * 
	 * @return vorname, name1 name2 name3
	 * @throws myException
	 */
	public String get_block_alle_namen() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$VORNAME, _DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$NAME3);
		return this.get___KETTE(vFelder1);
	}

	
	/**
	 * 
	 * @return vorname, name1 name2 name3 incl. anrede in klammern (wenn vorhanden)
	 * @throws myException
	 */
	public String get_block_alle_namen_incl_anrede() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$VORNAME, _DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$NAME3);
		String s = this.get___KETTE(vFelder1);
		
		RECORD_ANREDE anrede = this.get_UP_RECORD_ANREDE_id_anrede();
		if (anrede != null && S.isFull(anrede.get_ANREDE_cF_NN(""))) {
			s=s+" ("+anrede.get_ANREDE_cF_NN("")+")";
		}
		return s;
	}

	
	
	/**
	 * 
	 * @return name1 name2 name3
	 * @throws myException
	 */
	public String get_block_haupt_namen() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$NAME3);
		return this.get___KETTE(vFelder1);
	}

	
	
	/**
	 * 
	 * @return strasse hausnummer
	 * @throws myException
	 */
	public String get_block_strasse_hr() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$STRASSE, _DB.ADRESSE$HAUSNUMMER);
		return this.get___KETTE(vFelder1);
	}

	/**
	 * 
	 * @return strasse hausnummer
	 * @throws myException
	 */
	public String get_block_plz_ort() throws myException {
		Vector<String> vFelder1 = bibVECTOR.get_Vector(_DB.ADRESSE$PLZ, _DB.ADRESSE$ORT);
		return this.get___KETTE(vFelder1);
	}

 
    /**
     * 
     * @param bOnlyActiv
     * @return alle lieferadressen einer hauptadresse (wahlweise nur die aktiven)
     * @throws myException
     */
    public RECLIST_ADRESSE get_RECLIST_ADRESSEN_LAGER(boolean bOnlyActiv) throws myException    {
    	RECLIST_ADRESSE reclistRueck = new RECLIST_ADRESSE(this.get_oConn());
    	
    	if (this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis()!=null && this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().size()>0) {
	    	for (int i=0;i<this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get_vKeyValues().size();i++) {
	    		if (bOnlyActiv && this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer().is_AKTIV_NO()) {
	    			continue;
	    		}
	    		reclistRueck.ADD(this.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer(), false);
	    	}
    	}
    	return reclistRueck;
    }

    
    
    
    public boolean is_lieferant() throws myException {
    	RECLIST_ADRESSKLASSE_DEF  rld = new RECLIST_ADRESSKLASSE_DEF("SELECT * FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF");
    	
    	for (RECORD_ADRESSKLASSE_DEF def: rld) {
    		if (def.is_IST_LIEFERANT_YES()) {
    			return this.belongs_to_(def);
    		}
    	}
    	return false;
    }
    
    public boolean is_abnehmer() throws myException {
    	RECLIST_ADRESSKLASSE_DEF  rld = new RECLIST_ADRESSKLASSE_DEF("SELECT * FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF");
    	
    	for (RECORD_ADRESSKLASSE_DEF def: rld) {
    		if (def.is_IST_KUNDE_YES()) {
    			return this.belongs_to_(def);
    		}
    	}
    	return false;
    }

    
    public boolean belongs_to_(RECORD_ADRESSKLASSE_DEF rec_ad) throws myException {
    	RECLIST_ADRESSKLASSE  rla = this.get_DOWN_RECORD_LIST_ADRESSKLASSE_id_adresse(
    			new vgl(ADRESSKLASSE.id_adressklasse_def, rec_ad.get_ID_ADRESSKLASSE_DEF_cUF_NN("")).s(),"",true);
    	
    	return (rla.size()>0);
    }
    
    
    public boolean is_main_adress() throws myException {
    	return this.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_FIRMENINFO;
    }
    
   
    public boolean is_station_adress() throws myException {
    	return this.get_ADRESSTYP_lValue(-1L).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE;
    }

    
    
    public boolean is_privat_adresse() throws myException {
    	RECORD_ADRESSE  adr_test = this.get_main_Adress();
    	
    	boolean b_rueck = 		
    	(adr_test.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()==1)
    	?
    	adr_test.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_PRIVAT_YES()
    	:
    	false;
    	
    	return b_rueck;
    }
    

    
    public boolean is_firmen_adresse() throws myException {
    	RECORD_ADRESSE  adr_test = this.get_main_Adress();
    	
    	boolean b_rueck = 		
    	(adr_test.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()==1)
    	?
    	adr_test.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_FIRMA_YES()
    	:
    	false;
    	
    	return b_rueck;
    }
    

    
    /**
     * 
     * @return arraylist mit allen unformated id_artikel_bez
     * @throws myException
     */
    public ArrayList<String>  get_v_uf_id_artikelbez_ek() throws myException {
    	ArrayList<String> rueck = new ArrayList<>();
		RECLIST_ARTIKELBEZ_LIEF  rl = this.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(new And(new vgl(ARTIKELBEZ_LIEF.artbez_typ, "EK")).s(),null,true);
		rueck.addAll(rl.get_ID_ARTIKEL_BEZ_hmString_UnFormated("").values());
		return rueck;
    }
    
    /**
     * 
     * @return arraylist mit allen unformated id_artikel_bez
     * @throws myException
     */
    public ArrayList<String>  get_v_uf_id_artikelbez_vk() throws myException {
    	ArrayList<String> rueck = new ArrayList<>();
		RECLIST_ARTIKELBEZ_LIEF  rl = this.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(new And(new vgl(ARTIKELBEZ_LIEF.artbez_typ, "VK")).s(),null,true);
		rueck.addAll(rl.get_ID_ARTIKEL_BEZ_hmString_UnFormated("").values());
		return rueck;
    }
    
    
    

    /**
     * sucht den EK-AVV-code fuer eine beliebige Artikelbezeichnung raus
     * @param recAB
     * @return
     * @throws myException
     */
    public RECORD_EAK_CODE get_AVV_Code(RECORD_ARTIKEL_BEZ  recAB) throws myException {
    	RECORD_EAK_CODE recCode = null;
    	
    	RECLIST_ARTIKELBEZ_LIEF  rl_lief = this.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse
    					(new And(new vgl(ARTIKELBEZ_LIEF.artbez_typ,"EK")).s(), null, true);
    	
    	for (RECORD_ARTIKELBEZ_LIEF ral: rl_lief) {
    		if (ral.ufs(ARTIKELBEZ_LIEF.id_artikel_bez).equals(recAB.ufs(ARTIKEL_BEZ.id_artikel_bez))) {
    			recCode = ral.get_UP_RECORD_EAK_CODE_id_eak_code();
    		}
    	}
    	
    	return recCode;
    }
 
    

    /**
     * 
     * @return true, when adresse belongs to own company
     * @throws myException
     */
    public boolean is_adress_of_mandant() throws myException {
    	return (this.get_main_Adress().ufs(ADRESSE.id_adresse).equals(bibALL.get_RECORD_MANDANT().ufs(MANDANT.eigene_adress_id)));
    }
    
    
}
