package rohstoff.Echo2BusinessLogic.ABZUEGE;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;


import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ABZUGSSCHABLONEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ABZUGSSCHABLONEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class BL_CONST_ABZUG
{
	
	//private static int     ANZAHL_ABZUGS_TYPEN = 17; 
    //2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
	private static int     ANZAHL_ABZUGS_TYPEN = 18;    
	
	
	/*
	 * klassifizierung der Abzuege
	 */
	
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE 	= 					"MENGE_PROZENT_AUF_AUSGANGSMENGE";
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE 	= 					"MENGE_PROZENT_AUF_AKTUELLEMENGE";
    public static String	ABZUGTYP_MENGE_ABSOLUT 				   		= 					"MENGE_ABSOLUT";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS 	= 					"EPREIS_PROZENT_AUF_AUSGANGSPREIS";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS 	= 					"EPREIS_PROZENT_AUF_AKTUELLPREIS";
    public static String	ABZUGTYP_EPREIS_ABSOLUT 					= 					"EPREIS_ABSOLUT";
    public static String	ABZUGTYP_BETRAG_ABSOLUT 					= 					"BETRAG_ABSOLUT";
    //2011-08-03: neue abzuegstypen auf basis-waehrung
    public static String	ABZUGTYP_EPREIS_BW_ABSOLUT 					= 					"EPREIS_BW_ABSOLUT";
    public static String	ABZUGTYP_BETRAG_BW_ABSOLUT 					= 					"BETRAG_BW_ABSOLUT";
    
    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE 	= 					"INHALT_PROZENT_AUF_AUSGANGSMENGE";
    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE 	= 					"INHALT_PROZENT_AUF_AKTUELLEMENGE";
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE 	= 	"INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE";
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE 	= 	"INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE";
    //abzug fuer teilzahlung
    public static String	ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG 	= 						"EPREIS_PROZENT_VORAUSZAHLUNG";

    //neuer teilzahlungsabzug (rechnet auf die nettosumme)
    public static String	ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG 	= 					"EPREIS_PROZENT_VORAUSZAHLUNG_NG";
    

    //2011-06-09: neuer prozentabzug, der wie ein inhaltsabzug wirkt, nur als Abzug berechnet
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE 	= 		"MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE";
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE 	= 		"MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE";

    
    //2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
    public static String	ABZUGTYP_MENGE_ANHAFTEND 	= 									"ABZUGTYP_MENGE_ANHAFTEND";
    
    //dropdown-texte
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE_ddtext = 					"% der Basis-Menge";
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE_ddtext 	= 				"% der Vorgänger-Menge";
    public static String	ABZUGTYP_MENGE_ABSOLUT_ddtext 				   	= 					"Menge absolut";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS_ddtext	= 				"% Basis-Einzel-Preis";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS_ddtext 	= 				"% Vorgänger-Einzel-Preis";
    public static String	ABZUGTYP_EPREIS_ABSOLUT_ddtext 					= 					"Preis (Kundenwährung) pro Abrechnungseinheit";
    public static String	ABZUGTYP_BETRAG_ABSOLUT_ddtext					= 					"Betrag (Kundenwährung) absolut";	
    //2011-08-03: neue abzuegstypen auf basis-waehrung
    public static String	ABZUGTYP_EPREIS_BW_ABSOLUT_ddtext 					= 				"Preis (Basiswährung) pro Abrechnungseinheit";
    public static String	ABZUGTYP_BETRAG_BW_ABSOLUT_ddtext					= 				"Betrag (Basiswährung) absolut";	

    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE_ddtext	= 				"% Inhalt der Basismenge";	
    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE_ddtext	= 				"% Inhalt der Vorgänger-Menge";	
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE_ddtext= 	"% Inhalt der Basismenge ./.  % Einheitenabzug";	
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE_ddtext= 	"% Inhalt der Vorgänger-Menge ./. % Einheitenabzug";	
    //abzug fuer teilzahlung
    public static String	ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_ddtext= 				"% Prozent VORAUSZAHLUNG (ALT - NICHT MEHR VERWENDEN)";
    
    //abzug fuer teilzahlung
    public static String	ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_NG_ddtext= 		    "% Prozent VORAUSZAHLUNG";

    //2011-06-09: neuer prozentabzug, der wie ein inhaltsabzug wirkt, nur als Abzug berechnet
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE_ddtext 	= 		"% der Basis-Menge / KEINE Reduzierung der Lagermenge";
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE_ddtext 	= 		"% der Vorgänger-Menge / KEINE Reduzierung der Lagermenge";

    
    //2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
    public static String	ABZUGTYP_MENGE_ANHAFTEND_ddtext 	= 								"Menge (anhaftend) / KEINE Reduzierung der Lagermenge";

 
    /*
    platzhalter:  #EH# = Mengeneinheit   
    	#EHP# = preiseinheit    
    	#BW# = Basiswährung    
    	#FW# = Fremdwährung
    	#PM# = Positionsmenge
        #LM# = Laufende Menge
        #PP" = Positionspreis
        "LP" = Laufender Preis  
    	#EINGABE#=Mengenfeld 1    
    	#EINGABE2#=Mengenfeld 2
    */
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE_text = 					"#EINGABE# % der Basis-Menge";
    public static String	ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE_text 	= 					"#EINGABE# % der Vorgänger-Menge";
    public static String	ABZUGTYP_MENGE_ABSOLUT_text 				   	= 					"#EINGABE# #EH# absolut";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS_text	= 					"#EINGABE# % Basis-EPreis #FW# ";
    public static String	ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS_text 	= 					"#EINGABE# % Vorgänger-EPreis #FW# ";
    public static String	ABZUGTYP_EPREIS_ABSOLUT_text 					= 					"#EINGABE# #FW# pro #EHP# (Einzelpreis)";
    public static String	ABZUGTYP_BETRAG_ABSOLUT_text					= 					"#EINGABE# #FW# (Absolut-Betrag)"; 	
    //2011-08-03: neue abzuegstypen auf basis-waehrung
    public static String	ABZUGTYP_EPREIS_BW_ABSOLUT_text 					= 				"#EINGABE# #BW# pro #EHP# (Einzelpreis)";
    public static String	ABZUGTYP_BETRAG_BW_ABSOLUT_text					= 					"#EINGABE# #BW# (Absolut-Betrag)"; 	

    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE_text	= 					"#EINGABE# % Inhalt der Basismenge";	
    public static String	ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE_text	= 					"#EINGABE# % Inhalt der Vorgänger-Menge";	
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE_text	= 	"#EINGABE# % Inhalt der Basismenge ./. #EINGABE2# % Einheitenabzug";	
    public static String	ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE_text	= 	"#EINGABE# % Inhalt der Vorgänger-Menge ./. #EINGABE2# % Einheitenabzug";	
    
    //abzug fuer teilzahlung
    public static String	ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_text= 					"#EINGABE# % VORAUSZAHLUNG";

    //abzug fuer teilzahlung
    public static String	ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_NG_text= 				"#EINGABE# % VORAUSZAHLUNG";

    //2011-06-09: neuer prozentabzug, der wie ein inhaltsabzug wirkt, nur als Abzug berechnet
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE_text 	= 		"#EINGABE# % der Basis-Menge (anhaftend)";
    public static String	ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE_text 	= 		"#EINGABE# % der Vorgänger-Menge (anhaftend)";

    //2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
    public static String	ABZUGTYP_MENGE_ANHAFTEND_text 	= 									"#EINGABE# #EH# anhaftend (bleibt im Lager)";

    

    public static HashMap<String, String> HM_ABZUG_PLATZHALTER = null;
    static
    {
    	HashMap<String,String> hmTemp = new HashMap<String, String>();
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE,					BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE,					BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE_text);
    	//2011-06-09
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE,		BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE,		BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE_text);
    	
    	//2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND,									BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND_text);
    	
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT,									BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS,				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS,					BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT,									BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT,									BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT_text);
        //2011-08-03: neue abzuegstypen auf basis-waehrung
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT_text);

    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE,				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE,				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE,BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE,BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE_text);
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG,					BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_text);
    	
    	hmTemp.put(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG,					BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_NG_text);
    	
    	HM_ABZUG_PLATZHALTER = hmTemp;
    };

    
    private static HashMap<String,BL_CONST_ABZUG.AbzugsDef> HM_ABZUG_DEFS = null;
    static
    {
    	HashMap<String,BL_CONST_ABZUG.AbzugsDef> hmStrings = new HashMap<String,BL_CONST_ABZUG.AbzugsDef>();
    	
    	hmStrings.put(	BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE,
    				 	new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE,				BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE_ddtext,						BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE_text, "100","Y",""));
    	
    	hmStrings.put(  BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE,				BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE_ddtext,						BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE_text, "110","Y",""));
    	
    	//2011-06-09: neue abzuege
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE,	BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE_ddtext,			BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE_text, "120","Y",""));

    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE,	BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE_ddtext,			BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE_text, "130","Y",""));
    	
    	//2015-12-07: neuer abzugstyp: absolute menge lageranhaftend
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND,
						new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND,								BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND_ddtext,										BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND_text, "135","Y",""));
   	

    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT_ddtext,										BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT_text, "140","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS,				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS_ddtext,					BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS_text, "150","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS,				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS_ddtext,						BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS_text, "160","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT_ddtext,										BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT_text, "170","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT_ddtext,										BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT_text, "180","Y",""));

        //2011-08-03: neue abzuegstypen auf basis-waehrung
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT_ddtext,										BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT_text, "170","Y",""));
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT,								BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT_ddtext,										BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT_text, "180","Y",""));

    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE,				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE_ddtext,					BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE_text, "190","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE,				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE_ddtext,					BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE_text, "200","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE,BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE_ddtext,	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE_text, "210","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE,BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE_ddtext,	BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE_text, "220","Y",""));
    	
    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG,					BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_ddtext,					BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_text, "230","Y",""));

    	hmStrings.put( 	BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG,
    					new BL_CONST_ABZUG.AbzugsDef(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG,    			BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_NG_ddtext,				BL_CONST_ABZUG.ABZUGTYP_INHALT_EPREIS_PROZENT_VORAUSZAHLUNG_NG_text, "240","Y",""));
    	
    	HM_ABZUG_DEFS = hmStrings;
    }
    
    
    
    
    public static HashMap<String,BL_CONST_ABZUG.AbzugsDef> get_HM_ABZUGS_DEFS() throws myException
    {
    	RECLIST_ABZUGSSCHABLONEN recListSchablonen = new RECLIST_ABZUGSSCHABLONEN("SELECT * FROM "+bibE2.cTO()+".JT_ABZUGSSCHABLONEN WHERE ID_ABZUGSSCHABLONEN>0 ORDER BY SORTIERUNG");
    	HashMap<String,BL_CONST_ABZUG.AbzugsDef> hmRueck = new HashMap<String,BL_CONST_ABZUG.AbzugsDef>();

    	hmRueck.putAll(BL_CONST_ABZUG.HM_ABZUG_DEFS);

    	for (int i=0;i<recListSchablonen.get_vKeyValues().size();i++)
    	{
    		RECORD_ABZUGSSCHABLONEN recAZ = recListSchablonen.get(i);
    		
    		//nur solche, die in den BL_CONST_ABZUG.HM_ABZUG_DEFS enthalten sind, durch den datenbank-wert ersetzen
    		
    		if (BL_CONST_ABZUG.HM_ABZUG_DEFS.containsKey(recAZ.get_ABZUG_TYP_cUF()))
    		{
	    		hmRueck.put(recAZ.get_ABZUG_TYP_cUF(),
	    								new BL_CONST_ABZUG.AbzugsDef(	recAZ.get_ABZUG_TYP_cUF(),
	    								recAZ.get_ABZUG_DROP_DOWN_TEXT_cUF(), 
	    								recAZ.get_ABZUG_BELEGTEXT_SCHABLONE_cUF(),
	    								recAZ.get_SORTIERUNG_cUF(), 
	    								recAZ.get_SICHTBAR_cUF_NN("Y"),
	    								recAZ.get_HILFETEXT_cUF()));
    		}
    	}
    	
    	return hmRueck;
    }
    
    
    
	public static class AbzugsDef
	{
		public String ABZUG_TYP = null;
		public String ABZUG_DROP_DOWN_TEXT = null;
		public String ABZUG_BELEGTEXT_SCHABLONE = null;
		public String SORTIERUNG = null;
		public String  SICHTBAR = null;
		public String HILFETEXT = null;
		
		/**
		 * 
		 * @param aBZUG_TYP
		 * @param aBZUG_DROP_DOWN_TEXT
		 * @param aBZUG_BELEGTEXT_SCHABLONE
		 * @param sORTIERUNG
		 * @param sICHTBAR
		 * @param hILFETEXT
		 */
		public AbzugsDef(String aBZUG_TYP, String aBZUG_DROP_DOWN_TEXT, String aBZUG_BELEGTEXT_SCHABLONE, String sORTIERUNG,String sICHTBAR, String hILFETEXT)
		{
			super();
			ABZUG_TYP = aBZUG_TYP;
			ABZUG_DROP_DOWN_TEXT = aBZUG_DROP_DOWN_TEXT;
			ABZUG_BELEGTEXT_SCHABLONE = aBZUG_BELEGTEXT_SCHABLONE;
			HILFETEXT = hILFETEXT;
			SICHTBAR = sICHTBAR;
			SORTIERUNG = sORTIERUNG;
		}
		

		public boolean get_bIST_SICHTBAR()
		{
			return (S.NN(SICHTBAR).equals("Y"));
		}
		
	}

    
    
    
    
    
    

    
 
    

	   public static HashMap<String,String> get_HM__ABZUG_PLATZHALTER() throws myException
	    {
	       	// RECLIST_ABZUGSSCHABLONEN recListSchablonen = new RECLIST_ABZUGSSCHABLONEN("SELECT * FROM "+bibE2.cTO()+".JT_ABZUGSSCHABLONEN WHERE ID_ABZUGSSCHABLONEN>0 ORDER BY SORTIERUNG");

		    HashMap<String,BL_CONST_ABZUG.AbzugsDef>  hmAbzugsDefs = BL_CONST_ABZUG.get_HM_ABZUGS_DEFS();
	       	
    		HashMap<String,String> hmRueck = new HashMap<String,String>();
		    	
    		Iterator<BL_CONST_ABZUG.AbzugsDef> oIter = hmAbzugsDefs.values().iterator();
    		
	    	while (oIter.hasNext())
	    	{
	    		BL_CONST_ABZUG.AbzugsDef oDef = oIter.next();
	    		hmRueck.put(oDef.ABZUG_TYP, oDef.ABZUG_BELEGTEXT_SCHABLONE);
	    	}
	    	return hmRueck;
	    	
	    }
	    
	    
	    
	    
	    public static String[][] get_ABZUG_DD_ARRAY(boolean bLeerInFront) throws myException
	    {
	    	int iNum = bLeerInFront?BL_CONST_ABZUG.ANZAHL_ABZUGS_TYPEN+1:BL_CONST_ABZUG.ANZAHL_ABZUGS_TYPEN;

	    	
		    HashMap<String,BL_CONST_ABZUG.AbzugsDef>  hmAbzugsDefs = BL_CONST_ABZUG.get_HM_ABZUGS_DEFS();


			Vector<BL_CONST_ABZUG.AbzugsDef> vHelp = new Vector<BL_CONST_ABZUG.AbzugsDef>();
			
			Iterator<BL_CONST_ABZUG.AbzugsDef> oIter = hmAbzugsDefs.values().iterator();
			while (oIter.hasNext())
			{
				vHelp.add(oIter.next());
			}
			
			//jetzt nach der sort-spalte sortieren ([3])
			Collections.sort(vHelp, new Comparator<BL_CONST_ABZUG.AbzugsDef>()
			{
				@Override
				public int compare(BL_CONST_ABZUG.AbzugsDef o1, BL_CONST_ABZUG.AbzugsDef o2)
				{
					return S.NN(o1.SORTIERUNG).compareTo(S.NN(o2.SORTIERUNG));
				}
			});
		    
		    
	    	String[][] cRueck  = new String[iNum][2];
		    	
	    	int iCount = 0;
	    	if (bLeerInFront)
	    	{
	    		cRueck[iCount][0]="*";
	    		cRueck[iCount][1]="";
	    		iCount++;
	    	}

  
	    	for (int i=0;i<vHelp.size();i++)
	    	{
	    		BL_CONST_ABZUG.AbzugsDef oDef = vHelp.get(i);

	    		cRueck[iCount][0]=oDef.ABZUG_DROP_DOWN_TEXT;
		    	cRueck[iCount][1]=oDef.ABZUG_TYP;
	    		iCount++;
	    	}
	    	return cRueck;
	    	
	    }
	    

	    
	    //2011-08-04: in der fuhre werden geldabzuege nur noch auf basis-waehrung erlaubt
	    public static String[][] get_ABZUG_DD_ARRAY(boolean bLeerInFront, boolean bHoleSichtbare, boolean bSperreFremdWaehrung) throws myException
	    {

	    	
		    HashMap<String,BL_CONST_ABZUG.AbzugsDef>  hmAbzugsDefs = BL_CONST_ABZUG.get_HM_ABZUGS_DEFS();


			Vector<BL_CONST_ABZUG.AbzugsDef> vHelp = new Vector<BL_CONST_ABZUG.AbzugsDef>();
			
			Iterator<BL_CONST_ABZUG.AbzugsDef> oIter = hmAbzugsDefs.values().iterator();
			while (oIter.hasNext())
			{
				vHelp.add(oIter.next());
			}
			
			//jetzt nach der sort-spalte sortieren ([3])
			Collections.sort(vHelp, new Comparator<BL_CONST_ABZUG.AbzugsDef>()
			{
				@Override
				public int compare(BL_CONST_ABZUG.AbzugsDef o1, BL_CONST_ABZUG.AbzugsDef o2)
				{
					return S.NN(o1.SORTIERUNG).compareTo(S.NN(o2.SORTIERUNG));
				}
			});
			
			//jetzt die gewuenschte untermenge (angezeigt oder nicht) zaehlen
			Vector<BL_CONST_ABZUG.AbzugsDef> vSichtbar = new Vector<BL_CONST_ABZUG.AbzugsDef>();
			Vector<BL_CONST_ABZUG.AbzugsDef> vUnSichtbar = new Vector<BL_CONST_ABZUG.AbzugsDef>();
	    	
			for (BL_CONST_ABZUG.AbzugsDef oDef: vHelp)
			{
				//in fuhren darf nur mit basis-waehrung gerechnet werden
				if (bSperreFremdWaehrung && 
						( oDef.ABZUG_TYP.equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT) ||
						  oDef.ABZUG_TYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT) )
					)
				{
					vUnSichtbar.add(oDef);
				}
				else
				{
					if (oDef.get_bIST_SICHTBAR())
					{
						vSichtbar.add(oDef);
					}
					else
					{
						vUnSichtbar.add(oDef);
					}
				}
			}

			
			
			int iNum = 0;
			
			if (bHoleSichtbare)
			{
				iNum = bLeerInFront?vSichtbar.size()+1:vSichtbar.size();
		    	String[][] cRueck  = new String[iNum][2];
		    	
		    	int iCount = 0;
		    	if (bLeerInFront)
		    	{
		    		cRueck[iCount][0]="*";
		    		cRueck[iCount][1]="";
		    		iCount++;
		    	}

	  
		    	for (int i=0;i<vSichtbar.size();i++)
		    	{
		    		BL_CONST_ABZUG.AbzugsDef oDef = vSichtbar.get(i);

		    		cRueck[iCount][0]=oDef.ABZUG_DROP_DOWN_TEXT;
			    	cRueck[iCount][1]=oDef.ABZUG_TYP;
		    		iCount++;
		    	}
		    	return cRueck;

			}
			else
			{
				iNum = bLeerInFront?vUnSichtbar.size()+1:vUnSichtbar.size();
				
		    	String[][] cRueck  = new String[iNum][2];
		    	
		    	int iCount = 0;
		    	if (bLeerInFront)
		    	{
		    		cRueck[iCount][0]="*";
		    		cRueck[iCount][1]="";
		    		iCount++;
		    	}

	  
		    	for (int i=0;i<vUnSichtbar.size();i++)
		    	{
		    		BL_CONST_ABZUG.AbzugsDef oDef = vUnSichtbar.get(i);

		    		cRueck[iCount][0]=oDef.ABZUG_DROP_DOWN_TEXT;
			    	cRueck[iCount][1]=oDef.ABZUG_TYP;
		    		iCount++;
		    	}
		    	return cRueck;

			}
			
	    	
	    	
	    }

	
	
    
}
