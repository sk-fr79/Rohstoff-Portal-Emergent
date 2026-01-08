package panter.gmbh.basics4project;

import java.util.HashMap;




/*
 * konstanten-sammlung
 * Created on 27. Juni 2002, 18:14
 */
public class myCONST
{

	
	
    // werte, die programmintern auf die Adressen definiert werden, damit klar ist, welcher
    // Art die eingegebene adresse ist
    public static int ADRESSTYP_FIRMENINFO = 1;
    public static int ADRESSTYP_BANK = 2;
    public static int ADRESSTYP_MITARBEITER = 3;
    public static int ADRESSTYP_BENUTZER = 4;
    public static int ADRESSTYP_LIEFERADRESSE = 5;
    // public static int ADRESSTYP_LAGERADRESSE = 6;
    
     
    /*
     * definition der möglichen vorgangstypen für kundenvorgänge
     */ 
    public static String   VORGANGSART_ANGEBOT 			= "ANGEBOT";
    public static String   VORGANGSART_RECHNUNG 		= "RECHNUNG";
    public static String   VORGANGSART_GUTSCHRIFT 		= "GUTSCHRIFT";
    public static String   VORGANGSART_AUFT_BEST 		= "AUFT_BEST";
    public static String   VORGANGSART_LIEFERSCHEIN 	= "LIEFERSCHEIN";
    public static String   VORGANGSART_TRANSPORT 		= "TRANSPORT";
    public static String   VORGANGSART_EK_KONTRAKT 		= "EK_KONTRAKT";
    public static String   VORGANGSART_VK_KONTRAKT 		= "VK_KONTRAKT";
    public static String   VORGANGSART_ABNAHMEANGEBOT 	= "ABNAHMEANGEBOT";
    
    public static String   VORGANGSART_TRANSPORT_ZUSATZ_LIEFERSCHEIN = 		"LIEFERSCHEIN_FUHRE";
    public static String   VORGANGSART_TRANSPORT_ZUSATZ_LIEFERAVIS = 		"LIEFERAVIS_FUHRE";
    public static String   VORGANGSART_TRANSPORT_ZUSATZ_ABHOLSCHEIN = 		"ABHOLAVIS_FUHRE";
    public static String   VORGANGSART_TRANSPORT_ZUSATZ_LADESCHEIN = 		"LADESCHEIN_FUHRE";
    
    public static String   VORGANGSART_TRANSPORT_ZUSATZ_FUHRE = 			"FUHRE";
    
    public static String[] VORGANGSARTEN = {	VORGANGSART_ANGEBOT,
                                            	VORGANGSART_RECHNUNG,
                                            	VORGANGSART_GUTSCHRIFT,
            									VORGANGSART_AUFT_BEST,
            									VORGANGSART_LIEFERSCHEIN,
            									VORGANGSART_TRANSPORT,
            									VORGANGSART_EK_KONTRAKT,
            									VORGANGSART_VK_KONTRAKT,
            									VORGANGSART_ABNAHMEANGEBOT};

    /*
     * und die zugehoerige beschriftung, die uebersetzt werden kann
     */
    public static String   VORGANGSART_ANGEBOT_FOR_USER 			= "Angebot";
    public static String   VORGANGSART_RECHNUNG_FOR_USER 			= "Rechnung";
    public static String   VORGANGSART_GUTSCHRIFT_FOR_USER 			= "Gutschrift";
    public static String   VORGANGSART_AUFT_BEST_FOR_USER 			= "Auftragsbestätigug";
    public static String   VORGANGSART_LIEFERSCHEIN_FOR_USER 		= "Lieferschein";
    public static String   VORGANGSART_TRANSPORT_FOR_USER 			= "Transportauftrag";
    public static String   VORGANGSART_EK_KONTRAKT_FOR_USER 		= "Einkaufskontrakt";
    public static String   VORGANGSART_VK_KONTRAKT_FOR_USER 		= "Verkaufskontrakt";
    public static String   VORGANGSART_ABNAHMEANGEBOT_FOR_USER 		= "Abnahmeangebot";

    public static String[] VORGANGSARTEN_FOR_USER = {	VORGANGSART_ANGEBOT_FOR_USER,
                                                     	VORGANGSART_RECHNUNG_FOR_USER,
                                                     	VORGANGSART_GUTSCHRIFT_FOR_USER,
                                                     	VORGANGSART_AUFT_BEST_FOR_USER,
                                                     	VORGANGSART_LIEFERSCHEIN_FOR_USER,
                                                     	VORGANGSART_TRANSPORT_FOR_USER,
                                                     	VORGANGSART_EK_KONTRAKT_FOR_USER,
                                                     	VORGANGSART_VK_KONTRAKT_FOR_USER,
                                                     	VORGANGSART_ABNAHMEANGEBOT_FOR_USER};

    


    public static String   VG_POSITION_TYP_ARTIKEL 	= 		"ARTIKEL";
    public static String   VG_POSITION_TYP_ZUSATZTEXT 	= 	"ZUSATZTEXT";
    public static String   VG_POSITION_TYP_ALTERNATIV 	= 	"ALTERNATIV";
    
    public static String[] VG_POSITIONSARTEN = {VG_POSITION_TYP_ARTIKEL,VG_POSITION_TYP_ZUSATZTEXT,VG_POSITION_TYP_ALTERNATIV};

    
    //2013-06-04: nummernkreise fuer die Proforma-Rechnungen
    public static String   SONDERVORGANG_PROFORMA_RECHNUNG = "PROFORMA_RECHNUNG";
    
    //201706
    
    /*
     * moegliche nummernkreise, die fuer jeden mandanten separat verwaltet werden
     * fuer jeden vorgangstyp gibt es einen SEQ_<ID_MANDANT>_NAME
     */
    public static String[] NUMMERNKREISE_MANDANT = {	VORGANGSART_ANGEBOT,		VORGANGSART_RECHNUNG,		VORGANGSART_GUTSCHRIFT,
		            									VORGANGSART_AUFT_BEST,		VORGANGSART_LIEFERSCHEIN,	VORGANGSART_TRANSPORT,
		            									VORGANGSART_EK_KONTRAKT,	VORGANGSART_VK_KONTRAKT,	VORGANGSART_ABNAHMEANGEBOT,
		            									VORGANGSART_TRANSPORT_ZUSATZ_FUHRE ,
		            									VORGANGSART_TRANSPORT_ZUSATZ_LIEFERSCHEIN,
		            									VORGANGSART_TRANSPORT_ZUSATZ_ABHOLSCHEIN,
		            									VORGANGSART_TRANSPORT_ZUSATZ_LADESCHEIN,
		            									VORGANGSART_TRANSPORT_ZUSATZ_LIEFERAVIS,
		            									"WIEGEKARTENNR",
		            									"FUHREN_BAM_NUMMER",
		            									"FAHRPLANGRUPPE",
		            									"TEMPREPORTTABLE",
		            									"LAGERUMBUCHUNG_ZAEHLER", 
// 2015-02-13: auf einen nummernkreis					"BUCHUNGSBLOCK_FIBU",
		            									"SCHECKNUMMER_FIBU",
		            									"WK_EINGANGSSCHEIN",
		            									"WK_LIEFERSCHEIN",
		            									"ATOM_VEKTOR_GRUPPE",
		            									SONDERVORGANG_PROFORMA_RECHNUNG
		            									};  // zaehler fuer die insert into .. select ...- querys fuer die temp-table-id
    
//###programm-marker: 	KORREKTUR-FIBU-BUG
    
    /*
     * nummernkreise, die global (ohne mandanten-ID-Vorsatz) gebildet werden
     */
//    public static String[] NUMMERNKREISE_GLOBAL = {"REPORTNUMMER","REPORTARCHIV"};
    //2015-02-13: auf einen nummernkreis legen 
    public static String[] NUMMERNKREISE_GLOBAL = {"REPORTNUMMER"
    												,"REPORTARCHIV"
    												,"BUCHUNGSBLOCK_FIBU"
    												/*2018-07-20:globale-temp-tables (nummerierung der tabellen)*/,"TR_TEMPTABLES"
    												/*2018-07-20:globale-temp-tables (sequenzer fuer die eintraege)*/,"TR_TEMPIDS"};
    
    
	/*
	 * varianten für dei varianten im basisfeld der kommunikationstypen 
	 */
	public static String   KOMM_BASISTYP_TELEFON 	= "TELEFON";
	public static String   KOMM_BASISTYP_TELEFAX 	= "TELEFAX";
	public static String   KOMM_BASISTYP_TELEX 		= "TELEX";
	public static String   KOMM_BASISTYP_SONSTIGE 	= "SONSTIGE";
	public static String[] KOMM_BASISTYPEN 	= {KOMM_BASISTYP_TELEFON,  KOMM_BASISTYP_TELEFAX,  KOMM_BASISTYP_TELEX,KOMM_BASISTYP_SONSTIGE};

	
	
	/*
	 *  varianten für die typen eines kontrakt-status (jeweils ein zeichen fuer den datenbank-wert und ein wort fuer die maske) 
	 */
	public static String   KONTRAKT_STATUS_W_AUSSENDIENST 	= "0";
	public static String   KONTRAKT_STATUS_W_VORBEREITET	= "1";
	public static String   KONTRAKT_STATUS_W_AKTIV			= "2";
	public static String   KONTRAKT_STATUS_W_ABGESCHLOSSEN  = "3";
	
	public static String   KONTRAKT_STATUS_T_AUSSENDIENST 	= "Aussendienst";
	public static String   KONTRAKT_STATUS_T_VORBEREITET	= "Vorbereitet";
	public static String   KONTRAKT_STATUS_T_AKTIV			= "Aktiv";
	public static String   KONTRAKT_STATUS_T_ABGESCHLOSSEN  = "Geschlossen";
	
	
	/*
	 * fuer email-adressen gibt es folgenden klassifizierungen:
	 * 1. die Klassen fuer jeden belegtyp
	 * 2. 	zusaetzlich email fuer alle belege
	 * 		email fuer keine belege
	 */
	
	public static String	EMAIL_TYPE_VALUE_ALLTYPES 			= 	"ALLEBELEGE";
	public static String	EMAIL_TYPE_VALUE_NOTYPES 			= 	"KEINEBELEGE";
	public static String	EMAIL_TYPE_VALUE_ANGEBOT			= 	myCONST.VORGANGSART_ANGEBOT;
	public static String	EMAIL_TYPE_VALUE_RECHNUNG 			= 	myCONST.VORGANGSART_RECHNUNG;
	public static String	EMAIL_TYPE_VALUE_GUTSCHRIFT			= 	myCONST.VORGANGSART_GUTSCHRIFT;
	public static String	EMAIL_TYPE_VALUE_AUFT_BEST 			= 	myCONST.VORGANGSART_AUFT_BEST;
	public static String	EMAIL_TYPE_VALUE_LIEFERSCHEIN 		= 	myCONST.VORGANGSART_LIEFERSCHEIN;
	public static String	EMAIL_TYPE_VALUE_TRANSPORT 			= 	myCONST.VORGANGSART_TRANSPORT;
	public static String	EMAIL_TYPE_VALUE_EK_KONTRAKT 		= 	myCONST.VORGANGSART_EK_KONTRAKT;
	public static String	EMAIL_TYPE_VALUE_VK_KONTRAKT 		= 	myCONST.VORGANGSART_VK_KONTRAKT;
	public static String	EMAIL_TYPE_VALUE_ABNAHMEANGEBOT		= 	myCONST.VORGANGSART_ABNAHMEANGEBOT;
	public static String	EMAIL_TYPE_VALUE_FIBU				= 	"FIBU";
	public static String	EMAIL_TYPE_VALUE_MAHNUNG			= 	"MAHNUNG";
	public static String	EMAIL_TYPE_VALUE_LAGER				= 	"LAGER";
	public static String	EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT	= 	"BUCHHALTUNG_RE_GUT";
	
	
	public static String	EMAIL_TYPE_TEXT_ALLTYPES 			= 	"FÜR ALLE BELEGE";
	public static String	EMAIL_TYPE_TEXT_NOTYPES 			= 	"NICHT für Belege";
	public static String	EMAIL_TYPE_TEXT_ANGEBOT				= 	myCONST.VORGANGSART_ANGEBOT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_RECHNUNG 			= 	myCONST.VORGANGSART_RECHNUNG_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_GUTSCHRIFT 			= 	myCONST.VORGANGSART_GUTSCHRIFT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_AUFT_BEST 			= 	myCONST.VORGANGSART_AUFT_BEST_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_LIEFERSCHEIN 		= 	myCONST.VORGANGSART_LIEFERSCHEIN_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_TRANSPORT 			= 	myCONST.VORGANGSART_TRANSPORT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_EK_KONTRAKT 		= 	myCONST.VORGANGSART_EK_KONTRAKT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_VK_KONTRAKT 		= 	myCONST.VORGANGSART_VK_KONTRAKT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_ABNAHMEANGEBOT		= 	myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER;
	public static String	EMAIL_TYPE_TEXT_FIBU				= 	"Finanzbuchhaltung";
	public static String	EMAIL_TYPE_TEXT_MAHNUNG				= 	"Mahnung";
	public static String	EMAIL_TYPE_TEXT_LAGER				= 	"Lager";
	public static String	EMAIL_TYPE_TEXT_BUCHHALTUNG_RE_GUT	= 	"Versand von Rechnungen/Gutschriften";
	
	public static String[][] EMAIL_TYPE_SELECTOR_ARRAY = {
								{"-",""},
								{myCONST.EMAIL_TYPE_TEXT_ALLTYPES,				myCONST.EMAIL_TYPE_VALUE_ALLTYPES},
								{myCONST.EMAIL_TYPE_TEXT_ANGEBOT,				myCONST.EMAIL_TYPE_VALUE_ANGEBOT},
								{myCONST.EMAIL_TYPE_TEXT_RECHNUNG,				myCONST.EMAIL_TYPE_VALUE_RECHNUNG},
								{myCONST.EMAIL_TYPE_TEXT_GUTSCHRIFT,			myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT},
								{myCONST.EMAIL_TYPE_TEXT_AUFT_BEST,				myCONST.EMAIL_TYPE_VALUE_AUFT_BEST},
								{myCONST.EMAIL_TYPE_TEXT_LIEFERSCHEIN,			myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN},
								{myCONST.EMAIL_TYPE_TEXT_TRANSPORT,				myCONST.EMAIL_TYPE_VALUE_TRANSPORT},
								{myCONST.EMAIL_TYPE_TEXT_EK_KONTRAKT,			myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT},
								{myCONST.EMAIL_TYPE_TEXT_VK_KONTRAKT,			myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT},
								{myCONST.EMAIL_TYPE_TEXT_ABNAHMEANGEBOT,		myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT},
								{myCONST.EMAIL_TYPE_TEXT_NOTYPES,				myCONST.EMAIL_TYPE_VALUE_NOTYPES},
								{myCONST.EMAIL_TYPE_TEXT_FIBU,					myCONST.EMAIL_TYPE_VALUE_FIBU},
								{myCONST.EMAIL_TYPE_TEXT_MAHNUNG,				myCONST.EMAIL_TYPE_VALUE_MAHNUNG},
								{myCONST.EMAIL_TYPE_TEXT_LAGER,					myCONST.EMAIL_TYPE_VALUE_LAGER},
								{myCONST.EMAIL_TYPE_TEXT_BUCHHALTUNG_RE_GUT,	myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT},
	};
	
	
	public static HashMap<String, String> hmTypeAndUserTextEmailTypenReal = new HashMap<String, String>();
	static {
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_ANGEBOT, 					myCONST.EMAIL_TYPE_TEXT_ANGEBOT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_RECHNUNG, 					myCONST.EMAIL_TYPE_TEXT_RECHNUNG);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_GUTSCHRIFT, 				myCONST.EMAIL_TYPE_TEXT_GUTSCHRIFT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_AUFT_BEST, 					myCONST.EMAIL_TYPE_TEXT_AUFT_BEST);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_LIEFERSCHEIN, 				myCONST.EMAIL_TYPE_TEXT_LIEFERSCHEIN);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_TRANSPORT, 					myCONST.EMAIL_TYPE_TEXT_TRANSPORT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_EK_KONTRAKT, 				myCONST.EMAIL_TYPE_TEXT_EK_KONTRAKT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_VK_KONTRAKT, 				myCONST.EMAIL_TYPE_TEXT_VK_KONTRAKT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.VORGANGSART_ABNAHMEANGEBOT, 			myCONST.EMAIL_TYPE_TEXT_ABNAHMEANGEBOT);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.EMAIL_TYPE_VALUE_FIBU, 					myCONST.EMAIL_TYPE_TEXT_FIBU);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.EMAIL_TYPE_VALUE_MAHNUNG, 				myCONST.EMAIL_TYPE_TEXT_MAHNUNG);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.EMAIL_TYPE_VALUE_LAGER, 				myCONST.EMAIL_TYPE_TEXT_LAGER);
		myCONST.hmTypeAndUserTextEmailTypenReal.put(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT, 	myCONST.EMAIL_TYPE_TEXT_BUCHHALTUNG_RE_GUT);
	}
	
	
//	public static HashMap<String, String> hmEM_TYP_SELECTOR = bibALL.get_hmFromArray(myCONST.EMAIL_TYPE_SELECTOR_ARRAY, 1, 12, 1, 0);
	
	
	/*
	 * hier die jeweilige Programmversion eintragen
	 */
	public static String   VERSION = "0.9";
	
	
	
	
	
	
	/*
	 * textkategorieren fuer das laden von textbloecken
	 */
	public static String 	TEXT_KATEGORIE_ABNAHME_ANGEBOT_BEGIN = 		"BEGIN_PRICE_INFORMATION";
	public static String 	TEXT_KATEGORIE_ABNAHME_ANGEBOT_END = 		"END_PRICE_INFORMATION";
	public static String 	TEXT_KATEGORIE_MAILTEXT_VORGANGSMAIL = 		"MAIL_BLOCK_BUSINESSPAPER";
	
	
	
	
    
    
  
    
    
    
    /*
     * definition von moegliche report-parametern
     */
    
	public static String	REPORT_TYPE_VALUE_TEXT 	= 			"PARAM_TEXT";
	public static String	REPORT_TYPE_VALUE_INTEGER = 		"PARAM_INTEGER";
	public static String	REPORT_TYPE_VALUE_DATE 	= 			"PARAM_DATUM";
	
	public static String	REPORT_TYPE_TEXT_TEXT 	= 			"Text";
	public static String	REPORT_TYPE_TEXT_INTEGER = 			"Ganzzahl";
	public static String	REPORT_TYPE_TEXT_DATE 	= 			"Datum";

    
	public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
	public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
	public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
	public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
	public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;
	public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
	public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;

	public static String[][] STATUS_FUHRE_Liste_fuer_Dropdown = {
		{"-",""},
//		{"Storniert",								"-2"},
//		{"Alt",										"-1"},
		{"Eingabe unvollständig",					"1"},
		{"Keine Buchungspositionen vorhanden",		"2"},
		{"Ungebucht",								"3"},
		{"Teilgebucht",								"4"},
		{"Gebucht",									"5"}};

    
	public static String    KERN_NAME_OF_REPORT_TEMP_TABLES = "AAA_TEMPTABLE";
	
	
	
	public static String    FUHRE_BELEG_LIEFERSCHEIN = 			"LIEFERSCHEIN";
	public static String    FUHRE_BELEG_LIEFERSCHEIN_MULTI = 	"LIEFERSCHEIN_MULTI";
	public static String    FUHRE_BELEG_LADESCHEIN = 			"LADESCHEIN";
	public static String    FUHRE_BELEG_LIEFERAVIS = 			"LIEFERAVIS";
	//aenderung: 2010-11-17: fehlende belegtypen
	public static String    FUHRE_BELEG_ABHOLNACHRICHT = 		"ABHOLNACHRICHT";
	public static String    FUHRE_BELEG_EU_BLAETTER = 			"EU_BLAETTER";
	
	public static String    FUHRE_BELEG_ABHOLAVIS = 			"ABHOLAVIS";
	public static String    FUHRE_BELEG_CMR = 					"CMR-FRACHTBRIEF";
	
	public static String    FUHRE_BELEG_LIEFERSCHEIN_USER = 		"LIEFERSCHEIN";
	public static String    FUHRE_BELEG_LIEFERSCHEIN_MULTI_USER = 	"SAMMEL-LIEFERSCHEIN";
	public static String    FUHRE_BELEG_LADESCHEIN_USER = 			"LADESCHEIN";
	public static String    FUHRE_BELEG_LIEFERAVIS_USER = 			"LIEFERAVIS";
	public static String    FUHRE_BELEG_ABHOLAVIS_USER = 			"ABHOLAVIS";
	public static String    FUHRE_BELEG_CMR_USER = 					"CMR-FRACHTBRIEF";
	
	
	
	
	
	//parameter fuer die fortschrittsanzeige-tabelle, fuer solche anzeigen, die nie doppelt vorkommen duerfen
	public static String    FORTSCHRITT_ANZEIGE_ERZEUGE_ANGEBOTE = "FORTSCHRITT_ANZEIGE_ERZEUGE_ANGEBOTE";
	public static String    FORTSCHRITT_ANZEIGE_VORBEREITUNG_SENDELISTE = "FORTSCHRITT_ANZEIGE_VORBEREITUNG_SENDELISTE";
	public static String    FORTSCHRITT_ANZEIGE_BAUE_ANGEBOTS_PDFS   = "FORTSCHRITT_ANZEIGE_BAUE_ANGEBOTS_PDFS";
	public static String    FORTSCHRITT_ANZEIGE_VERSENDE_MAILS   = "FORTSCHRITT_ANZEIGE_VERSENDE_MAILS";
    
	
	
	public static String[][] VERLAENGERTER_EIGENTUMSVORBEHALT = {
		{"-",""},
		{"Zustimmung",		"ZUSTIMMUNG"},
		{"Ablehnung",		"ABLEHNUNG"},
		{"Keine Antwort",	"KEINE_ANTWORT"}};

	
	public static String     FORDERUNGSVERRECHNUNG_ZUSTIMMUNG=							"ZUSTIMMUNG";
	public static String     FORDERUNGSVERRECHNUNG_ZUSTIMMUNG_KONTOKORRENT_ABREDE=		"ZUSTIMMUNG_KKORRENT";
	public static String     FORDERUNGSVERRECHNUNG_ABLEHNUNG=							"ABLEHNUNG";
	public static String     FORDERUNGSVERRECHNUNG_KEINE_ANTWORT=						"KEINE_ANTWORT";
	
	public static String[][] FORDERUNGSVERRECHNUNG = {
		{"-",""},
		{"Zustimmung",				myCONST.FORDERUNGSVERRECHNUNG_ZUSTIMMUNG},
		{"Kontokorrent-Abrede",		myCONST.FORDERUNGSVERRECHNUNG_ZUSTIMMUNG_KONTOKORRENT_ABREDE},                  //2012-02-10: neuer zustand: kontokorrent-abrede
		{"Ablehnung",				myCONST.FORDERUNGSVERRECHNUNG_ABLEHNUNG},
		{"Keine Antwort",			myCONST.FORDERUNGSVERRECHNUNG_KEINE_ANTWORT}};

	
	
	
	public static String[][] 				BUCHUNGSTYPEN = 			new String[5][2];
	public static String[][] 				BUCHUNGSTYPEN_AKTIV = 		new String[2][2];
	public static String[][] 				BUCHUNGSTYPEN_SCHECK = 		new String[1][2];
	
	public static HashMap<String,String>	HM_BUCHUNGSTYPEN = 			new HashMap<String, String>();
	public static HashMap<String,String>	HM_BUCHUNGSTYP_KUERZEL = 	new HashMap<String, String>();
	public static HashMap<String,String>	HM_BUCHUNGSTYP_TOOLTIPS = 	new HashMap<String, String>();
	
	public static String                    BT_DRUCK_RECHNUNG = 	"DRUCK_RECHNUNG";
	public static String                    BT_DRUCK_GUTSCHRIFT = 	"DRUCK_GUTSCHRIFT";
	public static String                    BT_ZAHLUNGSEINGANG = 	"ZAHLUNGSEINGANG";
	public static String                    BT_ZAHLUNGSAUSGANG = 	"ZAHLUNGSAUSGANG";
	public static String                    BT_SCHECKDRUCK = 		"SCHECKDRUCK";
	
	static
	{
		BUCHUNGSTYPEN[0][0] = "Rechnung"; 			BUCHUNGSTYPEN[0][1]= myCONST.BT_DRUCK_RECHNUNG;
		BUCHUNGSTYPEN[1][0] = "Gutschrift"; 		BUCHUNGSTYPEN[1][1]= myCONST.BT_DRUCK_GUTSCHRIFT;
		BUCHUNGSTYPEN[2][0] = "Zahlungseingang"; 	BUCHUNGSTYPEN[2][1]= myCONST.BT_ZAHLUNGSEINGANG;
		BUCHUNGSTYPEN[3][0] = "Zahlungsausgang"; 	BUCHUNGSTYPEN[3][1]= myCONST.BT_ZAHLUNGSAUSGANG;
		BUCHUNGSTYPEN[4][0] = "Scheckdruck"; 		BUCHUNGSTYPEN[4][1]= myCONST.BT_SCHECKDRUCK;
		
		BUCHUNGSTYPEN_AKTIV[0][0] = "Zahlungseingang"; 	BUCHUNGSTYPEN_AKTIV[0][1]= "ZAHLUNGSEINGANG";
		BUCHUNGSTYPEN_AKTIV[1][0] = "Zahlungsausgang"; 	BUCHUNGSTYPEN_AKTIV[1][1]= "ZAHLUNGSAUSGANG";

		BUCHUNGSTYPEN_SCHECK[0][0] = "Scheckdruck"; 	BUCHUNGSTYPEN_SCHECK[0][1]= "SCHECKDRUCK";

		
		HM_BUCHUNGSTYPEN.put(BT_DRUCK_RECHNUNG,	 	"Druck Rechnung");
		HM_BUCHUNGSTYPEN.put(BT_DRUCK_GUTSCHRIFT, 	"Druck Gutschrift");
		HM_BUCHUNGSTYPEN.put(BT_ZAHLUNGSEINGANG, 	"Zahlungseingang");
		HM_BUCHUNGSTYPEN.put(BT_ZAHLUNGSAUSGANG, 	"Zahlungsausgang");
		HM_BUCHUNGSTYPEN.put(BT_SCHECKDRUCK, 		"Scheckdruck");
		
		HM_BUCHUNGSTYP_KUERZEL.put(BT_DRUCK_RECHNUNG,	 	"Rech");
		HM_BUCHUNGSTYP_KUERZEL.put(BT_DRUCK_GUTSCHRIFT, 	"Gut");
		HM_BUCHUNGSTYP_KUERZEL.put(BT_ZAHLUNGSEINGANG, 		"ZE");
		HM_BUCHUNGSTYP_KUERZEL.put(BT_ZAHLUNGSAUSGANG, 		"ZA");
		HM_BUCHUNGSTYP_KUERZEL.put(BT_SCHECKDRUCK, 			"SD");

		HM_BUCHUNGSTYP_TOOLTIPS.put(BT_DRUCK_RECHNUNG,	 	"Beleg wurde durch Rechnungsdruck erzeugt");
		HM_BUCHUNGSTYP_TOOLTIPS.put(BT_DRUCK_GUTSCHRIFT, 	"Beleg wurde durch Gutschriftdruck erzeugt");
		HM_BUCHUNGSTYP_TOOLTIPS.put(BT_ZAHLUNGSEINGANG, 	"Zahlungseingang");
		HM_BUCHUNGSTYP_TOOLTIPS.put(BT_ZAHLUNGSAUSGANG, 	"Zahlungsausgang");
		HM_BUCHUNGSTYP_TOOLTIPS.put(BT_SCHECKDRUCK, 		"Zahlungsausgang durch Scheckdruck");

		
	};
	
	
	/*
	 * ADRESS-Info-Typ
	 * bitmuster:  Stelle 1: unbenutzt: Stelle 2: Vorgang, Stelle 3: Vorgang Untergruppe
	 */
	public static String 					ADRESS_INFO_TYP_ALLGEMEIN = 				"ALLGEMEIN";
	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO = 			"KONTRAKT_INFO";
	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO_EK = 			"KONTRAKT_INFO_EK";
	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO_VK = 			"KONTRAKT_INFO_VK";
	public static String 					ADRESS_INFO_TYP_TPA_INFO = 					"TPA_INFO";
	public static String 					ADRESS_INFO_TYP_RECH_GUT_INFO = 			"RECH_GUT_INFO";
	public static String 					ADRESS_INFO_TYP_RECHNUNG_INFO = 			"RECHNUNG_INFO";
	public static String 					ADRESS_INFO_TYP_GUTSCHRIFT_INFO = 			"GUTSCHRIFT_INFO";
	public static String 					ADRESS_INFO_TYP_ANGEBOT_INFO = 				"ANGEBOT_INFO";
	public static String 					ADRESS_INFO_TYP_ABGEBOT_EK_INFO = 			"ANGEBOT_EK_INFO";
	public static String 					ADRESS_INFO_TYP_ANGEBOT_VK_INFO = 			"ANGEBOT_VK_INFO";

	//neue info-Typen
	public static String 					ADRESS_INFO_TYP_EINKAUF = 					"EINKAUF_INFO";
	public static String 					ADRESS_INFO_TYP_VERKAUF = 					"VERKAUF_INFO";
	
	//2011-02-19: infotyp fuer die FIBU
	public static String 					ADRESS_INFO_TYP_FIBU = 						"FIBU_INFO";
	
	
	
	
	public static HashMap<String,String>	HM_ADRESS_INFO_TYP = 				new HashMap<String, String>();
	static
	{
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_ALLGEMEIN, 			"Allgemeine Info");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO, 		"Info zu EK- und VK-Kontrakten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_EK, 	"Info zu EK-Kontrakten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_VK, 	"Info zu VK-Kontrakten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_TPA_INFO, 			"Info zu Transportaufträgen");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_RECH_GUT_INFO, 		"Info zu Rechnungen/Gutschriften");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_RECHNUNG_INFO, 		"Info zu Rechnungen");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_GUTSCHRIFT_INFO, 	"Info zu Gutschriften");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_ANGEBOT_INFO, 		"Info zu Angeboten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_ABGEBOT_EK_INFO, 	"Info zu Abnahme-Angeboten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_ANGEBOT_VK_INFO, 	"Info zu Verkaufs-Angeboten");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_EINKAUF,			 	"Info zu Einkauf (Allgemein/Fuhren)");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_VERKAUF, 			"Info zu Verkauf (Allgemein/Fuhren)");
		HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_FIBU, 				"Info FIBU/Buchhaltung");
	}
	
	
	

	
	
	
//	//fuhren-kosten werden qualifiziert
//	public static String 					FUKO_SPEDITIONSRECHNUNG =    		"SPEDITIONSRECHNUNG";
//	public static String 					FUKO_EINFUHRUMSATZSTEUER =    		"EINFUHRUMSATZSTEUER";
//	public static String 					FUKO_GEBUEHR_ZOLLAGENTUR =    		"GEBUEHR_ZOLLAGENTUR";
//	
//	public static HashMap<String,String>	HM_FUKO_TYPEN = 					new HashMap<String, String>();
//	static
//	{
//		HM_FUKO_TYPEN.put(myCONST.FUKO_SPEDITIONSRECHNUNG, 			"Speditionsrechnung");
//		HM_FUKO_TYPEN.put(myCONST.FUKO_EINFUHRUMSATZSTEUER, 		"Einfuhrumsatzsteuer");
//		HM_FUKO_TYPEN.put(myCONST.FUKO_GEBUEHR_ZOLLAGENTUR, 		"Gebühr Zollagentur");
//	}
//	
//	public static HashMap<String,String>	HM_FUKO_TYPEN_CODE = 					new HashMap<String, String>();
//	static
//	{
//		HM_FUKO_TYPEN.put(myCONST.FUKO_SPEDITIONSRECHNUNG, 			"Speditionsrechnung (SPED)");
//		HM_FUKO_TYPEN.put(myCONST.FUKO_EINFUHRUMSATZSTEUER, 		"Einfuhrumsatzsteuer (E-UST)");
//		HM_FUKO_TYPEN.put(myCONST.FUKO_GEBUEHR_ZOLLAGENTUR, 		"Gebühr Zollagentur (ZA)");
//	}
	
	
	//2011-08-17: fuhrenklassifizierung
	public static HashMap<String, String>  HM_FUHREN_TYP_STRECKE_LAGER_MIXED = new HashMap<String, String>();
	static
	{
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("0", "Strecke");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("1", "IN-Lager");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("2", "EX-Lager");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("3", "MIXED");
		HM_FUHREN_TYP_STRECKE_LAGER_MIXED.put("4", "Lager-zu-Lager");
	}
	
	public static String[][] ARRAY_FUHREN_TYP_DROPDOWN = {{"-", ""},{"Strecke", "0"},{"IN-Lager","1"},{"EX-Lager","2"},{"MIXED","3"},{"Lager-zu-Lager","4"}};
	
	
	
	//kennstring, mit dem Benutzer-tips aus der datenbank an tooltips in der maske uebergeben werden
	public static final String KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION = "@USER:"; 
	
	
}
