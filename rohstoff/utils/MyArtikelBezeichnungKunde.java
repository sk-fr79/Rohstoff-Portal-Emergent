/*
 * Created on 29.09.2004
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package rohstoff.utils;

import java.util.Locale;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;



/**
 * 
 * klasse sucht mit einem definierten id_adresse und id_artikel
 * die zu dem kunden definierte spezifische artikelbezeichnung heraus
 * 
 * 
 */
public class MyArtikelBezeichnungKunde
{
    
    public static int ERROR_NO_ADRESS_ID = 12345332;
    public static int ERROR_NO_ARTIKEL_ID = 12345333;
    

    private String cID_Adresse = null;
    private String cID_Artikel = null;
    private String[] cAbfrage  = new String[11];

    // vectoren aus String[11] - arrays mit ergebniszeilen der abfragen
    // die spalten sind: ANR1,ANR2,ARTBEZ1,ARTBEZ2, ID_EINHEIT,ID_EINHEIT_PREIS,MENGENDIVISOR,ID_ARTIKEL,ID_ARTIKEL_BEZ, EUCODE, EUNOTIZ 
    private Vector<String[]> 		vResultArraysKundeArtikelbez = new Vector<String[]>();       	// artikelbezeichnungen zu kunde/artikel 
    private Vector<String[]> 		vResultArraysArtikelbez = new Vector<String[]>();				// alle artikelbezeichnungen zu artikel 
    private Vector<String[]> 		vResultArrayArtikel = new Vector<String[]>();					// korrelierendes array zu dem artikel selber (falls keine artikelbezeichungen)
    
  //  private EAK_DataRecordHashMap_CODE  drhmEAKCODE = null;
    
 
    public MyArtikelBezeichnungKunde(String cid_Adresse,String cid_Artikel) throws myException
    {
        this.init( cid_Adresse, cid_Artikel);
    }
    
    public void init(String cid_Adresse,String cid_Artikel) throws myException
    {
        
        this.cID_Adresse 	= bibALL.null2leer(cid_Adresse);
        this.cID_Artikel	= bibALL.null2leer(cid_Artikel);

        
        if (this.cID_Adresse.trim().equals(""))
        {
            throw new myException(MyArtikelBezeichnungKunde.ERROR_NO_ADRESS_ID,"Leere Adress-ID in class:finderArtikelBezeichnung  -> Deshalb keine Kundenspezifische Artikelbezeichnung möglich !!!");
        }

        if (this.cID_Artikel.trim().equals(""))
        {
            throw new myException(MyArtikelBezeichnungKunde.ERROR_NO_ARTIKEL_ID,"Leere Artikel-ID in class:finderArtikelBezeichnung");
        }

        
        this.cAbfrage = new String[14];

        /*
         * zuerst die adress-id identifizieren
         */
		DotFormatter oTestID_Adresse = new DotFormatter(cid_Adresse,0,Locale.GERMAN,false,3);
		DotFormatter oTestID_Artikel = new DotFormatter(cid_Artikel,0,Locale.GERMAN,false,3);
		if (oTestID_Adresse.doFormat())
		{
		    String cID_ADRESSE = oTestID_Adresse.getStringUnFormated();
		    
		    if (oTestID_Artikel.doFormat())
		    {
		        String cID_ARTIKEL = oTestID_Artikel.getStringUnFormated();

		        /*
		         * erste abfrage: falls adresse eine spezifische bezeichnung hat, dann diese laden
		         */
		        String cSQLSorte1 = "SELECT  ANR1," +									// 0
		        							"ANR2," +									// 1
		        							"JT_ARTIKEL_BEZ.ARTBEZ1," +  				// 2
		        							"  NVL(JT_ARTIKELBEZ_LIEF.ARTBEZ2_ALTERNATIV,JT_ARTIKEL_BEZ.ARTBEZ2) AS ARTBEZ2_ALTERNATIV , " + // 3   //NEU_09
		        							"JT_ARTIKEL.ID_EINHEIT," +   				//4
		        							"JT_ARTIKEL.ID_EINHEIT_PREIS," +			//5
		        							"JT_ARTIKEL.MENGENDIVISOR," +				//6
		        							"JT_ARTIKEL.ID_ARTIKEL," +					//7
		        							"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ, " +			//8
		        							"EUCODE, " +								//9
		        							"EUNOTIZ, " +								//10
		        							"  NVL(JT_ARTIKELBEZ_LIEF.ID_EAK_CODE,0)  AS ID_EAK_CODE, " +//11
		        							"JT_ARTIKEL.BASEL_CODE," +					//12
		        							"JT_ARTIKEL.BASEL_NOTIZ, "+					//13
		        							"'Y',"+										// 14  -zeigt, dass der kunde eine eigene artikel-bez hat
		        							"JT_ARTIKEL.ARTBEZ1 AS ARTBEZ1_BASIS, "+     // 15  - zeigt immer die basis-bezeichnung
		        							"JT_ARTIKELBEZ_LIEF.ID_ARTIKELBEZ_LIEF AS ID_ARTIKELBEZ_LIEF"  +     // 16  - ID_ARTIKELBEZ_LIEF
								      	" FROM "+
								      	bibALL.get_TABLEOWNER()+".JT_ARTIKELBEZ_LIEF,"+
								      	bibALL.get_TABLEOWNER()+".JT_ARTIKEL_BEZ,"+
								      	bibALL.get_TABLEOWNER()+".JT_ARTIKEL "+
									   	" WHERE " +
									   	" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
									   	" JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ AND "+
									   	" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE+" AND "+
									   	" JT_ARTIKEL.ID_ARTIKEL="+cID_ARTIKEL +
									   	" ORDER BY ANR2";

                /*
                 * falls nicht, dann die erste verfügbare artikelbez. laden
                 */
                String cSQLSorte2 = "SELECT ANR1," +										//0
                							"ANR2," +										//1
                							"JT_ARTIKEL_BEZ.ARTBEZ1," +						//2
                							"JT_ARTIKEL_BEZ.ARTBEZ2, " +					//3
                							"JT_ARTIKEL.ID_EINHEIT," +						//4
                							"JT_ARTIKEL.ID_EINHEIT_PREIS," +				//5
                							"JT_ARTIKEL.MENGENDIVISOR," +					//6
                							"JT_ARTIKEL.ID_ARTIKEL," +						//7
                							"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ, " +				//8
                							"EUCODE, " +									//9
                							"EUNOTIZ, " +									//10
                							"0 AS ID_EAK_CODE, " +							//11
                							"JT_ARTIKEL.BASEL_CODE," +						//12
                							"JT_ARTIKEL.BASEL_NOTIZ, "+						//13
		        							"'N',"+											// 14  -zeigt, dass der kunde KEINE eigene artikel-bez hat
		        							"JT_ARTIKEL.ARTBEZ1 AS ARTBEZ1_BASIS, "+        // 15  - zeigt immer die basis-bezeichnung
		        							"0 AS ID_ARTIKELBEZ_LIEF"  +   					// 16  - ID_ARTIKELBEZ_LIEF
						          	" FROM "+
						          	bibALL.get_TABLEOWNER()+".JT_ARTIKEL_BEZ,"+
						          	bibALL.get_TABLEOWNER()+".JT_ARTIKEL "+
								   	" WHERE " +
								   	" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
								   	" JT_ARTIKEL.ID_ARTIKEL="+cID_ARTIKEL+
								   	" ORDER BY ANR2";
		        
                /*
                 * falls gar keine artikelbezeichnungen, dann wird die artikelbasis-bezeichnung geladen mit "--" als ANR2
                 */
                String cSQLSorte3 = "SELECT ANR1," +										//0
                							"'--' as ANR2," +								//1
                							"JT_ARTIKEL.ARTBEZ1," +							//2
                							"JT_ARTIKEL.ARTBEZ2, " +						//3
                							"JT_ARTIKEL.ID_EINHEIT," +						//4
                							"JT_ARTIKEL.ID_EINHEIT_PREIS," +				//5
                							"JT_ARTIKEL.MENGENDIVISOR," +					//6
                							"JT_ARTIKEL.ID_ARTIKEL," +						//7
                							"0 as ID_ARTIKEL_BEZ, " +						//8
                							"EUCODE, " +									//9
                							"EUNOTIZ ,  " +									//10
                							"0 AS ID_EAK_CODE, " +							//11
                							"JT_ARTIKEL.BASEL_CODE," +						//12
                							"JT_ARTIKEL.BASEL_NOTIZ, "+						//13
		        							"'N',"+											// 14  -zeigt, dass der kunde KEINE eigene artikel-bez hat
		        							"JT_ARTIKEL.ARTBEZ1 AS ARTBEZ1_BASIS, "+         // 15  - zeigt immer die basis-bezeichnung
		        							"0 AS ID_ARTIKELBEZ_LIEF"  +   					// 16  - ID_ARTIKELBEZ_LIEF
						          	" FROM "+
						          	bibALL.get_TABLEOWNER()+".JT_ARTIKEL "+
								   	" WHERE " +
								   	" JT_ARTIKEL.ID_ARTIKEL="+cID_ARTIKEL;
		        

                
                this.fillQuery(cSQLSorte1,this.vResultArraysKundeArtikelbez);
                this.fillQuery(cSQLSorte2,this.vResultArraysArtikelbez);
                this.fillQuery(cSQLSorte3,this.vResultArrayArtikel);
                
                
                
                boolean bAbfrageIstDurch = false;
                
                
                /*
                 * jetzt die "hauptbezeichnung" rausziehen
                 */
                
                
                /*
                 * kundenspezifisch
                 */
                if (! bAbfrageIstDurch)
                {
                    cAbfrage = this.getSingleArtikelBezeichnung(this.vResultArraysKundeArtikelbez);
                    if (cAbfrage == null)
                    {
                        throw new myException("Fehler bei Abfrage "+cSQLSorte1);
                    }
                    else
                    {
                        if (cAbfrage[0] != null)
                            bAbfrageIstDurch = true; 
                    }
                    
                }
                
                /*
                 * 1. vorhandene bezeichnung
                 */
                if (! bAbfrageIstDurch)
                {
                    cAbfrage = this.getSingleArtikelBezeichnung(this.vResultArraysArtikelbez);
                    if (cAbfrage == null)
                    {
                        throw new myException("Fehler bei Abfrage "+cSQLSorte2);
                    }
                    else
                    {
                        if (cAbfrage[0] != null)
                            bAbfrageIstDurch = true; 
                    }
                    
                }
                
                /*
                 * Artikelbezeichnung selbst
                 */
                if (! bAbfrageIstDurch)
                {
                    cAbfrage = this.getSingleArtikelBezeichnung(this.vResultArrayArtikel);
                    if (cAbfrage == null)
                    {
                        throw new myException("Fehler bei Abfrage "+cSQLSorte3);
                    }
                    else
                    {
                        if (cAbfrage[0] != null)
                            bAbfrageIstDurch = true; 
                    }
                    
                }

                if (!bAbfrageIstDurch)
                {
                    throw new myException("Es konnte keine Artikelbezeichnung geladen werden !");
                }
                  
		    }
		    else
		    {
                throw new myException("Keine korrekte Artikel-ID");

		    }
		}
		else
		{
            throw new myException("Keine korrekte Adress-ID");
		}
    }

    
    /**
     * sucht die abfrageergebnisse durch und liefert nach entscheidungsbaum die richtige artikelbezeichnung
     */
    private String[] getSingleArtikelBezeichnung(Vector<String[]> vErgebnis)
    {
    	
        String[] cRueck = new String[19];

        if (vErgebnis.size()>0)
        {
            
        	String[] QueryArtBez = (String[])vErgebnis.get(0);
            /*
             * schauen, ob der adresse eine artikelbezeichnung zugeordnet ist
             */
            if (QueryArtBez.length >= 1)
            {
            	
                cRueck[0] = QueryArtBez[0];
                cRueck[1] = QueryArtBez[1];
                cRueck[2] = QueryArtBez[2];
                cRueck[3] = QueryArtBez[3];
                cRueck[4] = QueryArtBez[4];
                cRueck[5] = QueryArtBez[5];
                cRueck[6] = QueryArtBez[6];
                cRueck[7] = QueryArtBez[7];
                cRueck[8] = QueryArtBez[8];
                cRueck[9] = QueryArtBez[9];
                cRueck[10] = QueryArtBez[10];
                
                /*
                 * auf 11 und 12 werden die textbezeichner der ID_EINHEIT und ID_EINHEIT_PREIS uebergeben
                 */
                cRueck[11] = bibDB.EinzelAbfrage("SELECT  EINHEITKURZ FROM "+bibALL.get_TABLEOWNER()+".JT_EINHEIT WHERE ID_EINHEIT="+cRueck[4],"","","");
                cRueck[12] = bibDB.EinzelAbfrage("SELECT  EINHEITKURZ FROM "+bibALL.get_TABLEOWNER()+".JT_EINHEIT WHERE ID_EINHEIT="+cRueck[5],"","","");
                
                cRueck[13] = QueryArtBez[11];   // ID_EAK_CODE
                
                cRueck[14] = QueryArtBez[12];   // BASEL_CODE
                cRueck[15] = QueryArtBez[13];   // BASEL_NOTIZ
                cRueck[16] = QueryArtBez[14];   // Hat eigene Artbez (Y/N)
                cRueck[17] = QueryArtBez[15];   // artikelbezeichnung aus dem haupt-stamm
                cRueck[18] = QueryArtBez[16];   // id_artikelbez_lief
            
            }
        }
        
        return cRueck;
        
    }

    
    
    
    /*
     * abfrage liefert null bei fehler, leeres array bei leerem ergebnisarray, volles array, wenn ok
     */
    private void fillQuery(String cSQLQuery, Vector<String[]> vZiel) throws myException
    {
        String[][] QueryArtBez = bibDB.EinzelAbfrageInArray(cSQLQuery,"");
        if (QueryArtBez == null)
        {
        	throw new myException("MyArtikelBezeichnungen:fillQuery:SQL-Error: "+cSQLQuery);
        }
        else
        {
        	for (int i=0;i<QueryArtBez.length;i++)
        	{
        		vZiel.add(QueryArtBez[i]);
        	}
        }
    }
    
    
    
    /*
     * geter fuer die verschiedenen werte
     */
    public String get_ANR1()   				{   return get__(0);  }
    public String get_ANR2()   				{   return get__(1);  }
    public String get_ARTBEZ1()   			{   return get__(2);  }
    
    public String get_ARTBEZ2() throws myException  			
    {
    	String cHelp = this.get__(3);
    	if (!this.get__(18).equals("0"))
    	{
    		//dann muss nochmal nach den zusatzinfos gefandet werden
    		RECORD_ARTBEZ_LIEF_extend  recArtbez_lief_ext = new RECORD_ARTBEZ_LIEF_extend(this.get__(18));
    		cHelp = recArtbez_lief_ext.get_ARTBEZ_2_Incl_Specials();
    	}
    	return cHelp;  
    }
    
    public String get_ID_EINHEIT()   		{   return get__(4);  }
    public String get_ID_EINHEIT_PREIS()   	{   return get__(5);  }
    public String get_MENGENDIVISOR()   	{   return get__(6);  }
    public String get_ID_ARTIKEL()   		{   return get__(7);  }
    public String get_ID_ARTIKEL_BEZ()   	{   return get__(8);  }
    public String get_EUCODE()   			{   return get__(9);  }
    public String get_EUNOTIZ()   			{   return get__(10);  }
    public String get_EINHEIT()   			{   return get__(11);  }
    public String get_EINHEIT_PREIS()   	{   return get__(12);  }
    public String get_ID_EAK_CODE()   		{   return get__(13);  }
    public String get_BASEL_CODE()   		{   return get__(14);  }
    public String get_BASEL_NOTIZ()   		{   return get__(15);  }
    public boolean get_bHasOwnArtikelBez()	{   return get__(16).equals("Y");  }
    public String get_ARTIKELBEZ1_BASIS() 	{   return get__(17);  }

    
    
	/**
	 * @return s array mit String[11] der Angaben zum Artikelstamm selbst
	 * Die spalten sind: ANR1,ANR2,ARTBEZ1,ARTBEZ2, ID_EINHEIT,ID_EINHEIT_PREIS,MENGENDIVISOR,ID_ARTIKEL,ID_ARTIKEL_BEZ, EUCODE, EUNOTIZ
	 * Sollte immer die Länge 1 haben 
	 */
	public Vector<String[]> get_vResultArrayArtikel() 
	{
		return vResultArrayArtikel;
	}

	/**
	 * @return s array mit String[11] aller artikelbezeichnungen des artikels
	 * Die spalten sind: ANR1,ANR2,ARTBEZ1,ARTBEZ2, ID_EINHEIT,ID_EINHEIT_PREIS,MENGENDIVISOR,ID_ARTIKEL,ID_ARTIKEL_BEZ, EUCODE, EUNOTIZ
	 */
	public Vector<String[]> get_vResultArraysArtikelbez() 
	{
		return vResultArraysArtikelbez;
	}

	/**
	 * @return s array mit String[11] aller artikelbezeichnungen des artikels, die diesem kunden zugeordnet sind
	 * Die spalten sind: ANR1,ANR2,ARTBEZ1,ARTBEZ2, ID_EINHEIT,ID_EINHEIT_PREIS,MENGENDIVISOR,ID_ARTIKEL,ID_ARTIKEL_BEZ, EUCODE, EUNOTIZ
	 */
	public Vector<String[]> get_vResultArraysKundeArtikelbez() 
	{
		return vResultArraysKundeArtikelbez;
	}
    
    
    
    
    /**
     * @returns preiseinheit, wenn leer, dann richtige einheit
     */
    public String get_EINHEIT_PREIS_OR_EINHEIT()
    {
        if (get__(12).trim().equals(""))
            return get__(11);
        else
            return get__(12);
        
    }
    
    
    private String get__(int iIndex)
    {
        String cRueck = null;
        if (this.cAbfrage != null)
        {
            cRueck = cAbfrage[iIndex];
        }
        return cRueck;    
    }


    
//    /**
//     * @param ID_ARTIKEL_BEZ
//     * @param oSES
//     * @return s arry with ARTIKEL_BEZ Infos
//     */
//    public static String[][] get_ANR1_ANR2_USW(String ID_ARTIKEL_BEZ, HttpSession oSES)
//    {
//        String cSQLSorte = "SELECT ANR1,ANR2,JT_ARTIKEL_BEZ.ARTBEZ1,JT_ARTIKEL_BEZ.ARTBEZ2, JT_ARTIKEL.ID_EINHEIT,JT_ARTIKEL.ID_EINHEIT_PREIS," +
//										"JT_ARTIKEL.MENGENDIVISOR,JT_ARTIKEL.ID_ARTIKEL,JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ, EUCODE, EUNOTIZ, JT_ARTIKEL.DIENSTLEISTUNG " +
//								  	" FROM "+
//								  	bibALL.get_TABLEOWNER()+".JT_ARTIKEL_BEZ,"+
//								  	bibALL.get_TABLEOWNER()+".JT_ARTIKEL "+
//								   	" WHERE " +
//								   	" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
//								   	" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+ID_ARTIKEL_BEZ;
//
//    	
//        MyDBToolBox oDB = bibALL.get_myDBToolBox();
//        String[][] vRueck = oDB.EinzelAbfrageInArray(cSQLSorte,"");
//        bibALL.destroy_myDBToolBox(oDB);
//        
//        return vRueck;
//    }
//
    
    
//    /**
//     * @param ID_ARTIKEL_BEZ
//     * @param oSES
//     * @return s arry with ARTIKEL_BEZ Infos
//     */
//    public static HashMap<String,String> get_hmArtikelINFO(String ID_ARTIKEL_BEZ, HttpSession oSES)
//    {
//    	Vector<String> vFields = new Vector<String>();
//    	vFields.add("JT_ARTIKEL.ANR1");
//    	vFields.add("JT_ARTIKEL_BEZ.ANR2");
//		vFields.add("JT_ARTIKEL_BEZ.ARTBEZ1");
//		vFields.add("JT_ARTIKEL_BEZ.ARTBEZ2");
//	    vFields.add("JT_ARTIKEL.ID_EINHEIT");
//		vFields.add("JT_ARTIKEL.ID_EINHEIT_PREIS");
//		vFields.add("JT_ARTIKEL.MENGENDIVISOR");
//		vFields.add("JT_ARTIKEL.ID_ARTIKEL");
//		vFields.add("JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ");
//		vFields.add("JT_ARTIKEL.EUCODE");
//		vFields.add("JT_ARTIKEL.EUNOTIZ");
//		vFields.add("JT_ARTIKEL.DIENSTLEISTUNG");
//
//		
//        String cSQLSorte = "SELECT	"+bibALL.ConcatenateWithoutException(vFields,",", null)+
//								  	" FROM "+
//								  	bibALL.get_TABLEOWNER()+".JT_ARTIKEL_BEZ,"+
//								  	bibALL.get_TABLEOWNER()+".JT_ARTIKEL "+
//								   	" WHERE " +
//								   	" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND "+
//								   	" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+ID_ARTIKEL_BEZ;
//
//    	
//        MyDBToolBox oDB = bibALL.get_myDBToolBox();
//        String[][] vRueck = oDB.EinzelAbfrageInArray(cSQLSorte,"");
//        bibALL.destroy_myDBToolBox(oDB);
//        
//        HashMap<String,String> hmRueck = new HashMap<String,String>();
//        
//        for (int i=0;i<vFields.size();i++)
//        {
//        	hmRueck.put(vFields.get(i),vRueck[0][i]);
//        }
//        
//        return hmRueck;
//    }

//	public EAK_DataRecordHashMap_CODE get_drhmEAKCODE() throws myException
//	{
//		// wird beim ersten aufruf intialisiert
//		if (this.drhmEAKCODE == null)
//			if (!this.get_ID_EAK_CODE().equals("0"))
//				this.drhmEAKCODE = new EAK_DataRecordHashMap_CODE(this.get_ID_EAK_CODE());
//		
//		return this.drhmEAKCODE;
//	}

    
    
}
