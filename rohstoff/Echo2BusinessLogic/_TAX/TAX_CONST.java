package rohstoff.Echo2BusinessLogic._TAX;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.myVectors.bibVECTOR;


public class TAX_CONST
{
	public static String TAXTYP_UNDEFINIERT = "UNDEFINIERT";
	public static String TAXTYP_RC_DEUTSCH = "RC_DEUTSCH";
	public static String TAXTYP_NULL_LAGERSEITE = "0_LAGER";
	
	public static String[][] TAXTYP_DD_ARRAY = 
	{
		{"-",							""},
		{"<undefiniert>",				TAX_CONST.TAXTYP_UNDEFINIERT},
		{"Reverse Charge Deutschland",	TAX_CONST.TAXTYP_RC_DEUTSCH},
		{"Lagerseite/Proformaeintrag",	TAX_CONST.TAXTYP_NULL_LAGERSEITE},
		
	};
	
	
	
	
	public static String TP_VERANTWORTUNG_EGAL = 			"EGAL";
	public static String TP_VERANTWORTUNG_QUELLE = 			"QUELLE";
	public static String TP_VERANTWORTUNG_ZIEL = 			"ZIEL";
	public static String TP_VERANTWORTUNG_MANDANT = 		"MANDANT";
	
	public static Vector<String>  V_TP_VERANTWORTUNG = bibVECTOR.get_Vector(
			TAX_CONST.TP_VERANTWORTUNG_EGAL, TAX_CONST.TP_VERANTWORTUNG_MANDANT, TAX_CONST.TP_VERANTWORTUNG_QUELLE, TAX_CONST.TP_VERANTWORTUNG_ZIEL);
	
	
	
	public static String MELDUNG_KEINE = 		"KEINE";
	public static String MELDUNG_INFO = 		"INFO";
	public static String MELDUNG_WARNUNG = 	"WARNUNG";
	public static String MELDUNG_ERROR = 		"ERROR";
	
	public static String[][] MELDUNG_DD_ARRAY = 
	{
		{"",			""},
		{"keine Meldung",	TAX_CONST.MELDUNG_KEINE},
		{"Information",		TAX_CONST.MELDUNG_INFO},
		{"Warnung",			TAX_CONST.MELDUNG_WARNUNG},
		{"Fehler",			TAX_CONST.MELDUNG_ERROR},
	};
	
	
	public static HashMap<String, String> hmMELDUNGEN = new HashMap<String, String>();
	static 
	{
		hmMELDUNGEN.put(TAX_CONST.MELDUNG_KEINE, 	"keine Meldung");
		hmMELDUNGEN.put(TAX_CONST.MELDUNG_INFO, 	"Information");
		hmMELDUNGEN.put(TAX_CONST.MELDUNG_WARNUNG, 	"Warnung");
		hmMELDUNGEN.put(TAX_CONST.MELDUNG_ERROR, 	"Fehler");
	}
	
	

	
	

	/*
	 * 2013-01-07: intrastatmeldungen varianten
	 * Alte Fuhren werden mit Undefklassifiziert, damit das system entscheiden kann, ob nach dem automatischen
	 * alten verfahren oder nach dem neuen manuellen Verfahren gemeldet wird
	 */
	public static String     INTRASTAT_MELDEN_YES =  "Y";
	public static String     INTRASTAT_MELDEN_NO =   "N";
//	public static String     INTRASTAT_MELDEN_UDEF = "UNDEF";
	public static String[][] INTRASTAT_MELDEN_DD_ARRAY = 
	{
		{"",				""},
		{"[X]    - Melden",		TAX_CONST.INTRASTAT_MELDEN_YES},
		{"[ ]    - Nicht melden",	TAX_CONST.INTRASTAT_MELDEN_NO},
//		{"UNDEFINIERT",			TAX_CONST.INTRASTAT_MELDEN_UDEF},
	};

	
	
	public static int        AUSWAHL_Y_N_EGAL__Y = 		1;
	public static int        AUSWAHL_Y_N_EGAL__N = 		-1;
	public static int        AUSWAHL_Y_N_EGAL__EGAL = 	0;
	
	
	
//	//2018-07-12: validierer durch exacte-globale keys ersetzen
//	//ein paar valdierer-Keya
//	public static final  String  VALID_KEY_ERFASSE_FEHLENDE_HANDELSDEF = 	"ERFASSE_FEHLENDE_HANDELSDEF";
//	public static final  String  VALID_KEY_OEFFNE_HANDELSDEF_MODUL = 		"OEFFNE_HANDELSDEF_MODUL";
//	public static final  String  VALID_KEY_BEARBEITE_HANDELSDEF = 			"BEARBEITE_HANDELSDEF ";
//	public static final  String  VALID_KEY_ERZEUGE_NEUE_HANDELSDEF = 		"ERZEUGE_NEUE_HANDELSDEF";
//	
//	

	
	//	//validierer-key fuer die validierung des Steuersatz-Aktiv/Passiv-Wertes
	public static final  String  VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_LISTE = 	"STEUER_AKTIV_PASSIV_IN_LISTE";
	public static final  String  VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_MASKE = 	"STEUER_AKTIV_PASSIV_IN_MASKE";
	
}
