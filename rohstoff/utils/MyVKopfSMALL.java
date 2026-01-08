package rohstoff.utils;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myTranslator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.myDataRecord;
import panter.gmbh.indep.exceptions.myException;



/**
 *  kleine hilfsklasse,
 *  um infos zu einem vorgang zu holen  (ohne MyAdress !!) 
 */
public class MyVKopfSMALL
{

    
    private 	String 			cID_VKOPF 	= "";
    
    private 	MyDBToolBox 	oDB 			= null;
    private 	myDataRecord	oVKopfRecord = null;
    
    private 	String 			cTO = "";
    
    private 	String 			cReportBaseName 			= "";    // formularname
    private		String 			cBuchungsNummerSequence 	= "";
    private 	String 			cVORGANG_TYP				= null;
    private 	String 			cVORGANG_TYP_FOR_USER		= null;
     
    /*
     * es gibt mehrere standard-mailadressen fuer geschaeftspapiere
     * hier wird das passende zugeordnet
     */
    private 	String 				cKorrectEmailAdressForBusinessPaper	= null;
    
    private 	VorgangTableNames	oTN = null;
    
	private 	GregorianCalendar	oCalGueltigVon = null;
	private 	GregorianCalendar	oCalGueltigBis = null;

    
    protected void finalize()
    {
        bibALL.destroy_myDBToolBox(this.oDB);;
    }
    
    public MyVKopfSMALL(String cid_vkopf,VorgangTableNames	otn, HttpSession osES) throws myException
    {
        this.cID_VKOPF 		= cid_vkopf;
        this.oTN			= otn;
        this.cTO 			= bibE2.cTO();
        
        if (bibALL.null2leer(cid_vkopf).equals(""))
        {
            throw new myException("myVorgang: Constructor MUST have a ID_VORGANG");
        }
        
        this.oDB = bibALL.get_myDBToolBox();
        
        /*
         * zustands-parameter
         */
        this.oVKopfRecord = new myDataRecord(""+oTN.get_cVKOPF_TAB()+"",cid_vkopf);
        
        
        
        /*
         * reporttyp feststellen
         */
        this.cVORGANG_TYP = bibALL.null2leer(this.oVKopfRecord.get_resultValue("VORGANG_TYP")).trim();
        
        if (this.cVORGANG_TYP.trim().equals(""))
        {
            throw new myException("myVorgang: not defined VORGANG_TYP");
        }
        
        /*
         * jetzt feststellen, welcher typ und wie demnach der report-basisname heist
         */
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			this.cReportBaseName="angebot";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 		this.cReportBaseName="auftragsbestaetigung";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 		this.cReportBaseName="gutschrift";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 		this.cReportBaseName="lieferschein";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 			this.cReportBaseName="rechnung";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 			this.cReportBaseName="transportauftrag";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	this.cReportBaseName="abnahmeangebot";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		this.cReportBaseName="ekkontrakt";
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		this.cReportBaseName="vkkontrakt";
        
        this.cBuchungsNummerSequence = "SEQ_"+bibALL.get_ID_MANDANT()+"_"+cVORGANG_TYP;


        /*
         * jetzt die beschriftung fuer den benutzer raussuchen
         */
        this.cVORGANG_TYP_FOR_USER = this.cVORGANG_TYP;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_ANGEBOT_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 			this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_AUFT_BEST_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 			this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_GUTSCHRIFT_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 		this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_LIEFERSCHEIN_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 			this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_RECHNUNG_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 			this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_TRANSPORT_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_EK_KONTRAKT_FOR_USER;
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		this.cVORGANG_TYP_FOR_USER = myCONST.VORGANGSART_VK_KONTRAKT_FOR_USER;
        
        
        
        /*
         * jetzt die passende mailadresse fuer dieses papier raussuchen
         */
        this.cKorrectEmailAdressForBusinessPaper = MyAdress.get_EMAILForPaper(this.cVORGANG_TYP,this.oDB,bibE2.cTO(),bibALL.null2leer(this.oVKopfRecord.get_resultValue("ID_ADRESSE")).trim());
        
        String cGueltigVon = this.oVKopfRecord.get_resultValue("GUELTIG_VON");
        String cGueltigBis = this.oVKopfRecord.get_resultValue("GUELTIG_BIS");
        
        if (cGueltigVon != null && !bibALL.isEmpty(cGueltigVon))
        {
        	try
        	{
        		Integer day = new Integer(cGueltigVon.substring(8,10));
        		Integer mon = new Integer(cGueltigVon.substring(5,7));
        		Integer year = new Integer(cGueltigVon.substring(0,4));
        		
        		this.oCalGueltigVon=new GregorianCalendar(year.intValue(),mon.intValue()-1,day.intValue());
        	}
        	catch (Exception ex)
        	{
        		
        	}
        }
        

        if (cGueltigBis != null && !bibALL.isEmpty(cGueltigBis))
        {
        	try
        	{
        		Integer day = new Integer(cGueltigBis.substring(8,10));
        		Integer mon = new Integer(cGueltigBis.substring(5,7));
        		Integer year = new Integer(cGueltigBis.substring(0,4));
        		
        		this.oCalGueltigBis=new GregorianCalendar(year.intValue(),mon.intValue()-1,day.intValue());
        	}
        	catch (Exception ex)
        	{
        		
        	}
        }

        
        
    }

    
    
    
    /**
     * neuaufbau des recordsets, fall sich etwas veraendert hat (z.B. belegnummer, datum usw
     */
    public void doRefresh() throws myException
    {
        this.oVKopfRecord = new myDataRecord(""+oTN.get_cVKOPF_TAB()+"",cID_VKOPF);
    }
    

    /*
     * der name, den ein pdf als anlage enthaelt (fuer den Adressaten sichtbar) 
     */
    public String get_cKorrektPDFName_forBusinessPaper	(boolean bWithRefresh) throws myException
    {
        if (bWithRefresh)
            this.doRefresh();
        
        /*
         * jetzt den passenden anlagename fuer dieses papier raussuchen
         */
        myTranslator oTrans = bibALL.get_TRANSLATOR();
        String cKorrektPDFName_forBusinessPaper =oTrans.translate("ELEKTRONISCHERBELEG");
        
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ANGEBOT)) 			cKorrektPDFName_forBusinessPaper=oTrans.translate("ANGEBOT");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_AUFT_BEST)) 			cKorrektPDFName_forBusinessPaper=oTrans.translate("AUFTRAGSBESTAETIGUNG");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT)) 			cKorrektPDFName_forBusinessPaper=oTrans.translate("GUTSCHRIFT");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_LIEFERSCHEIN)) 		cKorrektPDFName_forBusinessPaper=oTrans.translate("LIEFERSCHEIN");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_RECHNUNG)) 			cKorrektPDFName_forBusinessPaper=oTrans.translate("RECHNUNG");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_TRANSPORT)) 			cKorrektPDFName_forBusinessPaper=oTrans.translate("TRANSPORTAUFTRAG");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)) 	cKorrektPDFName_forBusinessPaper=oTrans.translate("ABNAHMEANGEBOT");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)) 		cKorrektPDFName_forBusinessPaper=oTrans.translate("EINKAUFSKONTRAKT");
        if (this.cVORGANG_TYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)) 		cKorrektPDFName_forBusinessPaper=oTrans.translate("VERKAUFSKONTRAKT");
        
        cKorrektPDFName_forBusinessPaper += "_"+this.oVKopfRecord.get_resultValue("BUCHUNGSNUMMER")+"."+"pdf";
        
        return cKorrektPDFName_forBusinessPaper; 
        
        
    }

    
    
    
    
    
    

    /*
     * baut die fuer diesen vorgang noetige hashmap
     * (2 parameters: id_vorgang und report_path)
     */
    public HashMap<String,String> makeHashMapForPrint() throws myException 
    {	    
        
        HashMap<String,String> oHash = new HashMap<String,String>();
        oHash.put("id_vkopf",this.cID_VKOPF);
        
        String cReportPath = bibALL.get_REPORTPATH(this.cReportBaseName);
        
        if (cReportPath == null) 
            throw new myException("myVorgang:makeHashMapForPrint: Error finding report: "+this.cReportBaseName);
        
        oHash.put("report_path",cReportPath);
        return oHash;

    }
    
    
    /*
     * liefert die anzahl der im vorgang (nicht geloeschten) vorhandenen positionen
     */
    public int get_iNumberOfPositions() throws myException
    {
        int i=0;
        
        String cSQL = "SELECT COUNT(*) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+"  AND   NVL(DELETED,'N')='N'";
        
        String cHelp = this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@");
        
        
        try
        {
            Integer iHelp = new Integer(cHelp);
            i = iHelp.intValue();
            
        }
        catch (Exception ex)
        {
            throw new myException("Error counting positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }
    
    
    /*
     * liefert die anzahl der im vorgang vorhandenen positionen
     */
    public int get_iNumberOfPositionsNULL_PRICE() throws myException
    {
        int i=0;
        
        String cSQL = "SELECT COUNT(*) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+" AND EINZELPREIS IS NULL   AND   NVL(DELETED,'N')='N'";
        
        String cHelp = this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@");
        
        
        try
        {
            Integer iHelp = new Integer(cHelp);
            i = iHelp.intValue();
            
        }
        catch (Exception ex)
        {
            throw new myException("Error counting NULL-PRICE - Positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }

   
    
    /*
     * liefert die anzahl der im vorgang vorhandenen positionen
     */
    public int get_iNumberOfPositions(String cPOSITION_TYP) throws myException
    {
        int i=0;
        
        if (!(cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL) || 
       		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ALTERNATIV) ||
       		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT)))
        {
        	throw new myException("MyVKopfSMALL:get_iNumberOfPositions:Position_Typ :"+cPOSITION_TYP+" is not allowed !!");
        }
        
        
        String cSQL = "SELECT COUNT(*) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+" AND POSITION_TYP='"+cPOSITION_TYP+"'   AND   NVL(DELETED,'N')='N'";
        
        String cHelp = this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@");
        
        
        try
        {
            Integer iHelp = new Integer(cHelp);
            i = iHelp.intValue();
            
        }
        catch (Exception ex)
        {
            throw new myException("Error counting positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }
    
    
    /*
     * liefert die anzahl der im vorgang vorhandenen positionen
     */
    public int get_iNumberOfPositionsNULL_PRICE(String cPOSITION_TYP) throws myException
    {
        int i=0;
        
        if (!(cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL) || 
           		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ALTERNATIV) ||
           		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT)))
            {
            	throw new myException("MyVKopfSMALL:get_iNumberOfPositionsNULL_PRICE:Position_Typ :"+cPOSITION_TYP+" is not allowed !!");
            }

        
        String cSQL = "SELECT COUNT(*) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+" AND EINZELPREIS IS NULL AND POSITION_TYP='"+cPOSITION_TYP+"'   AND   NVL(DELETED,'N')='N'";
        
        String cHelp = this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@");
        
        
        try
        {
            Integer iHelp = new Integer(cHelp);
            i = iHelp.intValue();
            
        }
        catch (Exception ex)
        {
            throw new myException("Error counting NULL-PRICE - Positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }


    
    
    /*
     * liefert die anzahl der im vorgang vorhandenen positionen
     */
    public int get_iNumberOfPositionsNULL_MENGE(String cPOSITION_TYP) throws myException
    {
        int i=0;
        
        if (!(cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL) || 
           		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ALTERNATIV) ||
           		cPOSITION_TYP.equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT)))
            {
            	throw new myException("MyVKopfSMALL:get_iNumberOfPositionsNULL_PRICE:Position_Typ :"+cPOSITION_TYP+" is not allowed !!");
            }

        
        String cSQL = "SELECT COUNT(*) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+" AND ANZAHL IS NULL AND POSITION_TYP='"+cPOSITION_TYP+"'  AND   NVL(DELETED,'N')='N'";
        
        String cHelp = this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@");
        
        
        try
        {
            Integer iHelp = new Integer(cHelp);
            i = iHelp.intValue();
            
        }
        catch (Exception ex)
        {
            throw new myException("Error counting NULL-MENGE - Positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }

    
    
    

    
    /**
     * liefert eine entscheidungshilfe, ob gedruckt werden darf (z.B.)
     * oder ob geloescht werden darf, in dem von jeder position die menge und
     * der einzelpreis abgefragt werden
     * Return: {Menge,Epreis}   -> unformatierte zahlen
     */
    public String[][] get_dMenge_Epreis() throws myException
    {
        
        String cSQL = "SELECT "+oTN.get_cVPOS_PK()+",ANZAHL,EINZELPREIS FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+
        					" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF + "  AND   NVL(DELETED,'N')='N'";
        
        String[][] cHelp = this.oDB.EinzelAbfrageInArray(cSQL,"");
        if (cHelp == null || cHelp.length==0)
            throw new myException("VKopf_Infos:get_dMenge_Epreis:Error querying Positions ...");
        
        return cHelp;
    }

    
    
    public boolean get_bABNAHMEANGEBOT_LEER() throws myException
    {
        boolean bRueck = true;
        String[][] cINFOS = this.get_dMenge_Epreis();
        
        for (int i=0;i<cINFOS.length;i++)
        {
            if (!cINFOS[i][2].equals(""))
            {
                bRueck = false;
                break;
            }
        }
        return bRueck;
    }
    
    public boolean get_bABNAHMEANGEBOT_KOMPLETT_GEFUELLT() throws myException
    {
        boolean bVoll = true;
        String[][] cINFOS = this.get_dMenge_Epreis();
        
        for (int i=0;i<cINFOS.length;i++)
        {
            if (cINFOS[i][2].equals(""))
            {
                bVoll = false;
                break;
            }
        }
        return bVoll;
    }
    
    
    
    
    public double get_dSumOfAllPositions() throws myException
    {
        double i=0;
        
        String cSQL = "SELECT SUM(  NVL(ANZAHL,0)*  NVL(EINZELPREIS,0)) FROM "+this.cTO+"."+oTN.get_cVPOS_TAB()+" WHERE "+
        					oTN.get_cVKOPF_PK()+"="+this.cID_VKOPF+ "  AND   NVL(DELETED,'N')='N'";
    
        try
        {
            i = (new Double(this.oDB.EinzelAbfrage(cSQL,"@@@","@@@","@@@"))).doubleValue();
        }
        catch (Exception ex)
        {
            throw new myException("Error calculating positions to VORGANG "+this.cID_VKOPF);
        }
        return i;
    }
    
    
    public String get_cReportBaseName()
    {
        return this.cReportBaseName;
    }
    
    
    
    
    
    /**
     * @baut eine dropdown-liste mit den werten fuer die 
     * @vorgangstypen, wobei in spalte 0 die uebersetzten, in spalte 1 die datenbank-werte liegen
     */
    public static String[][] get_cVorgangsTypen(HttpSession oSES,boolean bMitLeerFeld)
    {
		/*
		 * die möglichen vorgangstypen liegen in myConst (mit vorangestelltem leerwert)
		 */
        int iArrayLen = myCONST.VORGANGSARTEN.length;
        if (bMitLeerFeld) iArrayLen++;
        
        myTranslator oTrans = bibALL.get_TRANSLATOR();
        
		String[][] ddVorgang_Typ = new String[iArrayLen][2];
		
		int iZaehl = 0;
		
		if (bMitLeerFeld) 
		{
		    ddVorgang_Typ[iZaehl][0]="-"; ddVorgang_Typ[iZaehl][1]="";
		    iZaehl++;
	    }
		
		
		for (int i=0;i<myCONST.VORGANGSARTEN.length;i++)
		{
			ddVorgang_Typ[iZaehl][0]=oTrans.translate(myCONST.VORGANGSARTEN_FOR_USER[i]); 
			ddVorgang_Typ[iZaehl][1]=myCONST.VORGANGSARTEN[i];
			iZaehl++;
		}
        
        return ddVorgang_Typ;

    }
    
    
    
    
 
    public String get_cBuchungsNummerSequence()
    {
        return cBuchungsNummerSequence;
    }
    
   
    public String get_cID_VKOPF()
    {
        return this.oVKopfRecord.get_resultValue(""+oTN.get_cVKOPF_PK()+"");
    }
	

    public String get_cID_ADRESSE()
    {
        return this.oVKopfRecord.get_resultValue("ID_ADRESSE");
    }

    
    
	
    public myDataRecord get_oVKopfRecord()
    {
        return oVKopfRecord;
    }
    
    public String get_cVORGANG_TYP()
    {
        return cVORGANG_TYP;
    }
    
    
    public boolean get_bIstAbgeschlossen()
    {
        boolean bRueck = false;
        
        if (this.oVKopfRecord.get_resultValue("ABGESCHLOSSEN").equals("Y"))
            bRueck = true;
        
        return bRueck;
    }
    
    
    public String get_cKorrectEmailAdressForBusinessPaper()
    {
        return cKorrectEmailAdressForBusinessPaper;
    }
    
	public GregorianCalendar get_oCalGueltigVon() 						
	{		
		return oCalGueltigVon;	
	}

	public GregorianCalendar get_oCalGueltigBis() 						
	{		
		return oCalGueltigBis;	
	}
   
    /**
     * @return s Vector with all relevant mail-Adresses for this paper
     * @throws myException
     */
    public Vector<String> get_vAllEMAILsForPaper(boolean bAdd_Email_FOR_ALL_PAPERS) throws myException
    {
    	Vector<String> vRueck = MyAdress.get_vAllEMAILsForPaper(this.cVORGANG_TYP,bibALL.get_TABLEOWNER(),bibALL.null2leer(this.oVKopfRecord.get_resultValue("ID_ADRESSE")).trim());
    	if (bAdd_Email_FOR_ALL_PAPERS)
    	{
    		Vector<String> v4All = MyAdress.get_vEMAILsForAll(bibALL.get_TABLEOWNER(),bibALL.null2leer(this.oVKopfRecord.get_resultValue("ID_ADRESSE")).trim());
    		for (int i=0;i<v4All.size();i++)
    		{
    			String cMail = (String)v4All.get(i);
    			if (!vRueck.contains(cMail))
    				vRueck.add(cMail);
    			
    		}
    	}
    	return vRueck;
    }
    
    public String get_cVORGANG_TYP_FOR_USER()
    {
        return cVORGANG_TYP_FOR_USER;
    }

}