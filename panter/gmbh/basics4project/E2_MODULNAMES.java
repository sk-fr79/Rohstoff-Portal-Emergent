package panter.gmbh.basics4project;


public class E2_MODULNAMES
{
	
	
	
	//2011-12-02: Das globale Programm bekommt auch einen "MODULNAMEN"
	/*
	 * diesen modulkenner bekommen alle buttons, die nicht ueber jedes modul
	 */
	public static String                  MODUL_KENNER_PROGRAMM_WIDE =          "MODUL_KENNER_PROGRAMM_WIDE";

	
	
	
	/*
	 * bekommt die eindeutigen modul-kenner der echo2-module als static,
	 * Wichtig! Module, die aus dem menue gestartet werden, bekommen als
	 * 			Vorsatz IMMER MODUL_ !!!!
	 */
	public static final String NAME_MODUL_TESTAPP	= 					"MODUL_TESTAPP";
	public static final String NAME_MODUL_FUHRENFUELLER	= 				"MODUL_FUHRENFUELLER";
	//2015-04-29: neue kennungen fuer die zugehoerigen upload-buttons 
	public static final String NAME_MODUL_FUO_LISTE_EK	= 				"MODUL_FUO_LISTE_EK";
	public static final String NAME_MODUL_FUO_LISTE_VK	= 				"MODUL_FUO_LISTE_VK";
	
	public static final String NAME_MODUL_FUHRENMASKE	= 				"MODUL_FUHRENMASKE";
	public static final String NAME_MODUL_FUHREN_ORT_MASKE	= 			"NAME_MODUL_FUHREN_ORT_MASKE";
	public static final String NAME_FUHRE_FBAM_MASK = 					"FUHRE_FBAM_MASK";
	public static final String NAME_MODUL_FBAM_LIST = 					"MODUL_FBAM_LIST";
	public static final String NAME_MODUL_BBAM_LIST = 					"MODUL_BBAM_LIST";
	public static final String NAME_MODUL_BBAM_MASK = 					"MODUL_BBAM_MASK";
	public static final String NAME_MODUL_FAHRPLAN = 						"MODUL_FAHRPLAN";
	public static final String NAME_MODUL_FAHRTENPOOL = 					"MODUL_FAHRTENPOOL";
	public static final String NAME_MODUL_FAHRPLANUEBERSICHT=				"MODUL_FAHRPLANUEBERSICHT";
	public static final String NAME_MODUL_FAHRTENVARIANTEN =				"MODUL_FAHRTENVARIANTEN";
	public static final String NAME_MODUL_FAHRTENSCHNELLERFASSUNG =		"NAME_MODUL_FAHRTENSCHNELLERFASSUNG";
	public static final String NAME_MODUL_FIRMENSTAMM_LIST =				"MODUL_FIRMENSTAMM_LISTE";
	public static final String NAME_MODUL_FIRMENSTAMM_MASK =				"MODUL_FIRMENSTAMM_MASKE";
	public static final String NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE =	"MODUL_FIRMENSTAMM_MASK_INFOLISTE";
	
	//2011-12-16: eigener modulkenner fuer die lagerlist in der firmenstmm-maske
	public static final String NAME_MODUL_FIRMENSTAMM_MASK_LAGER_LISTE =	"MODUL_FIRMENSTAMM_MASK_LAGER_LISTE";
	
	//2014-12-04: eigener Modulkenner fuer die maske-lieferadresse
	public static final String NAME_MODUL_FIRMENSTAMM_MASK_LAGER_MASKE =	"MODUL_FIRMENSTAMM_MASK_LAGER_MASKE";
	
	public static final String NAME_MODUL_ARTIKELSTAMM_LISTE =				"MODUL_ARTIKELSTAMM_LISTE";
	public static final String NAME_MODUL_ARTIKELSTAMM_MASKE =				"MODUL_ARTIKELSTAMM_MASKE";
	public static final String NAME_MODUL_LAGERLISTE =						"MODUL_LAGERLISTE";
	public static final String NAME_MODUL_LAGERMASKE =						"MODUL_LAGERMASKE";
	public static final String NAME_MODUL_LAGER_ERFASSUNG =					"MODUL_LAGER_ERFASSUNG";
	
	public static final String NAME_MODUL_ABNAHMEABGEBOT_LISTENEINGABE =	"MODUL_ABNAHMEABGEBOT_LISTENEINGABE";
	
	public static final String NAME_MODUL_ABNAHMEANGEBOT_LIST =				"MODUL_ABNAHMEANGEBOT_LIST";
	public static final String NAME_MODUL_ABNAHMEANGEBOT_MASK =				"MODUL_ABNAHMEANGEBOT_MASK";
	public static final String NAME_MODUL_ABNAHMEANGEBOT_POS_MASK =			"NAME_MODUL_ABNAHMEANGEBOT_POS_MASK";
	
	public static final String NAME_MODUL_VERKAUFSANGEBOT_LIST =			"MODUL_VERKAUFSANGEBOT_LIST";
	public static final String NAME_MODUL_VERKAUFSANGEBOT_MASK =			"MODUL_VERKAUFSANGEBOT_MASK";
	public static final String NAME_MODUL_VERKAUFSANGEBOT_POS_MASK =		"MODUL_VERKAUFSANGEBOT_POS_MASK";

	public static final String NAME_MODUL_EK_KONTRAKT_LIST =				"MODUL_EK_KONTRAKT_LIST";
	public static final String NAME_MODUL_EK_KONTRAKT_MASK =				"MODUL_EK_KONTRAKT_MASK";
	public static final String NAME_MODUL_EK_KONTRAKT_POS_MASK =			"MODUL_EK_KONTRAKT_POS_MASK";

	public static final String NAME_MODUL_VK_KONTRAKT_LIST =				"MODUL_VK_KONTRAKT_LIST";
	public static final String NAME_MODUL_VK_KONTRAKT_MASK =				"MODUL_VK_KONTRAKT_MASK";
	public static final String NAME_MODUL_VK_KONTRAKT_POS_MASK =			"MODUL_VK_KONTRAKT_POS_MASK";

	public static final String NAME_MODUL_EK_KONTRAKT_LIST_NG =				"MODUL_EK_KONTRAKT_LIST_NG";
	public static final String NAME_MODUL_EK_KONTRAKT_MASK_NG =				"MODUL_EK_KONTRAKT_MASK_NG";
	public static final String NAME_MODUL_EK_KONTRAKT_POS_MASK_NG =			"MODUL_EK_KONTRAKT_POS_MASK_NG";
	
	public static final String NAME_MODUL_VK_KONTRAKT_LIST_NG =				"MODUL_VK_KONTRAKT_LIST_NG";
	public static final String NAME_MODUL_VK_KONTRAKT_MASK_NG =				"MODUL_VK_KONTRAKT_MASK_NG";
	public static final String NAME_MODUL_VK_KONTRAKT_POS_MASK_NG =			"MODUL_VK_KONTRAKT_POS_MASK_NG";

	public static final String NAME_MODUL_ADMINTOOLS =						"MODUL_ADMINTOOLS";
	public static final String NAME_MODUL_ORACLETOOLS =						"MODUL_ORACLETOOLS";

	public static final String NAME_MODUL_EAK =								"MODUL_EAK";
	
	public static final String NAME_MODUL_VERTRAGSCLEARING_EK =				"MODUL_VERTRAGSCLEARING_EK";
	public static final String NAME_MODUL_VERTRAGSCLEARING_VK =				"MODUL_VERTRAGSCLEARING_VK";
	
	
	public static final String NAME_MODUL_TPA_LIST =						"NAME_MODUL_TPA_LIST";
	public static final String NAME_MODUL_TPA_MASK =						"NAME_MODUL_TPA_MASK";
	public static final String NAME_MODUL_TPA_POS_MASK =					"NAME_MODUL_TPA_POS_MASK";
	
	public static final String NAME_MODUL_FREIEPOSITIONEN =					"NAME_MODUL_FREIEPOSITIONEN";
	public static final String NAME_MODUL_POSITION_VORLAGEN =				"NAME_MODUL_POSITION_VORLAGEN";
	
	public static final String NAME_MODUL_LISTE_FUHREN_SONDERFAELLE =		"NAME_MODUL_LISTE_FUHREN_SONDERFAELLE";
	public static final String NAME_MODUL_MASKE_FUHREN_SONDERFAELLE =		"NAME_MODUL_MASKE_FUHREN_SONDERFAELLE";
	
	public static final String NAME_MODUL_RECHNUNGEN_LIST =					"NAME_MODUL_RECHNUNGEN_LIST";
	public static final String NAME_MODUL_RECHNUNGEN_MASK =					"NAME_MODUL_RECHNUNGEN_MASK";
	public static final String NAME_MODUL_RECHNUNGEN_POS_MASK =				"NAME_MODUL_RECHNUNGEN_POS_MASK";

	public static final String NAME_MODUL_GUTSCHRIFTEN_LIST =				"NAME_MODUL_GUTSCHRIFTEN_LIST";
	public static final String NAME_MODUL_GUTSCHRIFTEN_MASK =				"NAME_MODUL_GUTSCHRIFTEN_MASK";
	public static final String NAME_MODUL_GUTSCHRIFTEN_POS_MASK =			"NAME_MODUL_GUTSCHRIFTEN_POS_MASK";

	public static final String NAME_MODUL_EK_VK_KONTRAKTZUORDNUNGEN  =		"NAME_MODUL_EK_VK_KONTRAKTZUORDNUNGEN";
	
	public static final String NAME_MODUL_WAEGUNG_ERFASSUNG = 				"NAME_MODUL_WAEGUNG_ERFASSUNG";
	public static final String NAME_MODUL_WIEGEKARTE_LISTE = 				"NAME_MODUL_WIEGEKARTE_LISTE";
	public static final String NAME_MODUL_WIEGEKARTE_MASKE = 				"NAME_MODUL_WIEGEKARTE_MASKE";

	
	public static final String NAME_MODUL_BANKENSTAMM_LISTE =				"NAME_MODUL_BANKENSTAMM_LISTE";
	public static final String NAME_MODUL_BANKENSTAMM_MASKE =				"NAME_MODUL_BANKENSTAMM_MASKE";
	
	public static final String NAME_MODUL_PROJEKT_LISTE =					"NAME_MODUL_PROJEKT_LISTE";
	public static final String NAME_MODUL_PROJEKT_MASKE =					"NAME_MODUL_PROJEKT_MASKE";

	
	
	public static final String NAME_SELECT_KONTRAKTE_IN_FUHRE =				"NAME_SELECT_KONTRAKTE_IN_FUHRE";

	public static final String NAME_WORKFLOW_LAUFZETTEL_LISTE =				"NAME_WORKFLOW_LAUFZETTEL_LISTE";
	public static final String NAME_WORKFLOW_LAUFZETTEL_MASKE =				"NAME_WORKFLOW_LAUFZETTEL_MASKE";

	
	public static final String NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE =		"NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE";
	public static final String NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE =		"NAME_WORKFLOW_LAUFZETTEL_EINTRAG_MASKE";
	
	
	
	public static final String  NAME_MODUL_LAGER_BEWEGUNG_LISTE = 			"MODUL_LAGER_BEWEGUNG_LISTE";
	public static final String  NAME_MODUL_LAGER_BEWEGUNG_MASKE = 			"MODUL_LAGER_BEWEGUNG_MASKE";
	public static final String  NAME_MODUL_LAGERLISTE_KONTO = 				"MODUL_LAGERLISTE_KONTO";

	public static final String  NAME_MODUL_LAGER_INVENTUR_MASKE = 			"MODUL_LAGER_INVENTUR_MASKE";
	public static final String  NAME_MODUL_LAGER_INVENTUR_LISTE = 			"MODUL_LAGER_INVENTUR_LISTE";
	
	
	public static final String  NAME_MODUL_FIBU_LIST =		 				"MODUL_FIBU_LIST";
	public static final String  NAME_MODUL_FIBU_MASK = 						"MODUL_FIBU_MASK";
	
	public static final String  NAME_MODUL_INTRASTAT_MASKE = 				"MODUL_INTRASTAT_MASKE";
	public static final String  NAME_MODUL_INTRASTAT_LISTE = 				"MODUL_INTRASTAT_LISTE";
	
	public static final String  NAME_MODUL_NACHRICHTEN_MASKE = 				"MODUL_NACHRICHTEN_MASKE";
	public static final String  NAME_MODUL_NACHRICHTEN_LISTE = 				"MODUL_NACHRICHTEN_LISTE";
	
	public static final String  NAME_MODUL_TPA_KOSTEN_LISTE = 				"MODUL_TPA_KOSTEN_LISTE";
//	public static final String  NAME_MODUL_TPA_KOSTEN_MASKE = 				"MODUL_TPA_KOSTEN_MASKE";

	//public static final String  NAME_MODUL_ROHSTOFFAUSWERTUNGEN = 			"NAME_MODUL_ROHSTOFFAUSWERTUNGEN";
	
	
	public static final String  NAME_MODUL_ERFASSUNG_FUHRENKOSTEN = 		"MODUL_ERFASSUNG_FUHRENKOSTEN";
	
	public static final String  NAME_MODUL_LOG_TRIGGER_DEF_LISTE = 			"MODUL_LOG_TRIGGER_DEF_LISTE";
	public static final String  NAME_MODUL_LOG_TRIGGER_DEF_MASKE = 			"MODUL_LOG_TRIGGER_DEF_MASKE";
	public static final String  NAME_MODUL_LOG_TRIGGER_ENTRIES_LISTE = 		"MODUL_LOG_TRIGGER_ENTRIES_LISTE";
	
	//2014-01-14: modul zum versenden von Inventur-eMails
	public static final String  NAME_MODUL_VERSENDE_INVENTUR_ANFORDERUNG = 	"MODUL_VERSENDE_INVENTUR_ANFORDERUNG";
	
	
	
//	//2012-12-06: regeln fuer steuer
//	public static final String  MODUL_TAX_LIST = 							"MODUL_TAX_LIST";
//	public static final String  MODUL_TAX_MASK = 							"MODUL_TAX_MASK";
//	
//	public static final String  MODUL_TAXRULES_LIST = 						"MODUL_TAXRULES_LIST";
//	public static final String  MODUL_TAXRULES_MASK = 						"MODUL_TAXRULES_MASK";
	
	
	//2014-02-25: modul, das alle fuhren nach steuervarianten durchsucht und vorschlaege setzt
	public static final String  MODUL_TAXRULES_FINDER = 					"MODUL_TAXRULES_FINDER";
	
	
	public static final String  MODUL_SELEKTORDEF_LIST = 					"MODUL_SELEKTORDEF_LIST";
	public static final String  MODUL_SELEKTORDEF_MASK = 					"MODUL_SELEKTORDEF_MASK";
	
	
	//2013-02-25: liste/maske fuer erfassung von groovy-ausdruecken fuer sql-statements innerhalb von Datenbank-Ausdruecken
	public static final String  MODUL_GROOVYDEF_LIST = 						"MODUL_GROOVYDEF_LIST";
	public static final String  MODUL_GROOVYDEF_MASK = 						"MODUL_GROOVYDEF_MASK";
	
	
	
	// 2013-01-16: neues Modul Bewegungsatom-Saldo
	public static final String NAME_MODUL_ATOM_LAGER_SALDO 	= "MODUL_ATOM_LAGER_SALDO";
	public static final String NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST = "MODUL_ATOM_LAGER_BEWEGUNG_LIST";
	public static final String NAME_MODUL_ATOM_LAGER_BEWEGUNG_MASK = "MODUL_ATOM_LAGER_BEWEGUNG_MASK";
	
	
	
	
	/*
	 * modulnamen von tochtermodulen in masken (die nicht in den hauptmodulen auftauchen)
	 */
	public static final String NAME_MODUL_FIRMENSTAMM_MASK_MATSPEZ =		"MODUL_FIRMENSTAMM_MASKE_MATSPEZ";

	public static final String  NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE = "MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE";
	public static final String  NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_MASKE = "MODUL_KUNDENSTATUS_FORDERUNGEN_MASKE";
	
	public static final String  NAME_MODUL_LAGERSTATUS_LISTE = "MODUL_LAGERSTATUS_LISTE";
	public static final String  NAME_MODUL_LAGERSTATUS_MASKE = "MODUL_LAGERSTATUS_MASKE";
	
	
	public static final String  NAME_MODUL_MASCHINENSTAMM_LISTE = "NAME_MODUL_MASCHINENSTAMM_LISTE";
	public static final String  NAME_MODUL_MASCHINENSTAMM_MASKE = "NAME_MODUL_MASCHINENSTAMM_MASKE";

	public static final String  NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE = "MODUL_MASCHINENSTAMM_AUFGABE_LISTE";
	public static final String  NAME_MODUL_MASCHINENSTAMM_AUFGABE_MASKE = "MODUL_MASCHINENSTAMM_AUFGABE_MASKE";

	
	public static final String  NAME_MODUL_FUHREN_SCHNELLERFASSUNG = 		"MODUL_FUHREN_SCHNELLERFASSUNG";

	public static final String  NAME_MODUL_KREDITVERSICHERUNG_LISTE = "MODUL_KREDITVERSICHERUNG_LISTE";
	public static final String  NAME_MODUL_KREDITVERSICHERUNG_MASKE = "MODUL_KREDITVERSICHERUNG_MASKE";
	public static final String  NAME_MODUL_KREDITVERSICHERUNG_POS_INLAY = "MODUL_KREDITVERSICHERUNG_POS_INLAY";
	
	public static final String  NAME_MODUL_UMA_LIST = 					"MODUL_UMA_LIST";
	public static final String  NAME_MODUL_UMA_MASK = 					"MODUL_UMA_MASK";
	
	
	//2012-11-12: neues modul live-auswertung
	public static final String  NAME_MODUL_ATOM_LIVELISTE = 			"NAME_MODUL_ATOM_LIVELISTE";
	
	
	// 2013-02-06 modul email-Inbox
	public static final String  NAME_MODUL_MAIL_INBOX_LISTE =  "MODUL_MAIL_INBOX_LISTE";
	public static final String  NAME_MODUL_MAIL_INBOX_MASKE =  "MODUL_MAIL_INBOX_MASKE";
	
	
	//2013-07-01: modulcontainer fuer neue warenbewegung
	public static final String  NAME_MODUL_WARENBEWEGUNG_NG_LIST =  "MODUL_WARENBEWEGUNG_NG_LIST";
	
	
	
	//2013-10-10: modulname fuer die reportlisten-angaben in den diversen modulen
	public static final String  NAME_MODUL_GLOBALE_LISTE_ZUM_EINSTELLEN_DER_MODULREPORTS =  "MODUL_GLOBALE_LISTE_ZUM_EINSTELLEN_DER_MODULREPORTS";
	
	
	//2013-11-07: modulname fuer das ausprogrammierte laendermodul
	public static final String  NAME_MODUL_LAENDER_LIST =  "MODUL_LAENDER_LIST";
	public static final String  NAME_MODUL_LAENDER_MASK =  "MODUL_LAENDER_MASK";
	
	
	//2014-02-28: modulname fuer die ausprogrammierten fieldrules
	public static final String  NAME_MODUL_FIELDRULE_LIST =  "MODUL_FIELDRULE_LIST";
	public static final String  NAME_MODUL_FIELDRULE_MASK =  "MODUL_FIELDRULE_MASK";
	
	
	//2014-03-11: kosten-tochter fuer die adressmaske
	public static final String  NAME_MODUL_ADRESSEN_KOSTEN_LIST =  "MODUL_ADRESSEN_KOSTEN_LIST";
	public static final String  NAME_MODUL_ADRESSEN_KOSTEN_MASK =  "MODUL_ADRESSEN_KOSTEN_MASK";
	
	// 2014-04-15 Inventurliste fuer die atomaren LAGER
	public static final String  NAME_MODUL_LAGER_ATOM_INVENTUR_MASKE = 			"MODUL_LAGER_ATOM_INVENTUR_MASKE";
	public static final String  NAME_MODUL_LAGER_ATOM_INVENTUR_LISTE = 			"MODUL_LAGER_ATOM_INVENTUR_LISTE";
	
	// 2014-04-28 Lagerbewertung - Offene WEs
	public static final String  NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE = 			"MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE";
	public static final String  NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_MASKE = 			"MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_MASKE";
	
	public static final String  NAME_MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_LISTE = 			"MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_LISTE";
	public static final String  NAME_MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_MASKE = 			"MODUL_ATOM_LAGER_BEWERTUNG_OHNE_KOSTEN_MASKE";
	
	
	
	//2014-05-06: eMail-Versender aus masken
	public static final String  NAME_MODUL_MAIL_AUS_MASK_DEF__MASK = 		"MODUL_MAIL_AUS_MASK_DEF__MASK";
	public static final String  NAME_MODUL_MAIL_AUS_MASK_DEF__LIST = 		"MODUL_MAIL_AUS_MASK_DEF__LIST";
	
	
	//2014-09-04: Definition der Summationsspalten in den Listenmodulen
	public static final String  NAME_COLS_TO_CALC_DEF_LIST = 				"COLS_TO_CALC_DEF_LIST";
	public static final String  NAME_COLS_TO_CALC_DEF_MASK = 				"COLS_TO_CALC_DEF_MASK";

	//2014-10-14: Erfassung von FIBU-Konten-Regel
	public static final String  NAME_LIST_FIBU_KONTEN_REGELN = 				"NAME_LIST_FIBU_KONTEN_REGELN";
	public static final String  NAME_MASK_FIBU_KONTEN_REGELN = 				"NAME_MASK_FIBU_KONTEN_REGELN";
	
	//2014-10-15: Datev-Export
	public static final String  NAME_FIBU_KONTEN_EXPORT = 					"NAME_FIBU_KONTEN_EXPORT";

	//2014-11-05: Modul zu Fibu-Export-Profilen (für Datev-Export)
	public static final String  NAME_FIBU_EXPORT_PROFILES_LIST = 				"FIBU_EXPORT_PROFILES_LIST";
	public static final String  NAME_FIBU_EXPORT_PROFILES_MASK = 				"FIBU_EXPORT_PROFILES_MASK";

	//2014-12-17: Modulkopie für die neuen Fibu-Konten-Regeln
	public static final String  NAME_LIST_FIBU_KONTEN_REGELN_NEU = 				"NAME_LIST_FIBU_KONTEN_REGELN_NEU";
	public static final String  NAME_MASK_FIBU_KONTEN_REGELN_NEU = 				"NAME_MASK_FIBU_KONTEN_REGELN_NEU";
	
	//2014-12-18: Erfassungsmodul fuer die neuen Fibu-Konten-Regeln-filter-based
	public static final String  NAME_LIST_FIBU_KONTEN_REGELN_FILTERBASED = 		"NAME_LIST_FIBU_KONTEN_REGELN_FILTERBASED";
	public static final String  NAME_MASK_FIBU_KONTEN_REGELN_FILTERBASED = 		"NAME_MASK_FIBU_KONTEN_REGELN_FILTERBASED";
	
	//2015-03-03: Spielwiese 
	public static final String  SPIELWIESE_LIST = 		"SPIELWIESE_LIST";
	public static final String  SPIELWIESE_MASK = 		"SPIELWIESE_MASK";
	
	//2015-03-05: BAM-Importmodul (nilsandre) 
	public static final String  BAM_IMPORT_LIST = 		"BAM_IMPORT_LIST";
	public static final String  BAM_IMPORT_MASK = 		"BAM_IMPORT_MASK";
	
	
	//2015-03-24: EMAIL_SEND_LISTE
	public static final String  EMAIL_SEND_LIST = 		"EMAIL_SEND_LIST";
	public static final String  EMAIL_SEND_MASK = 		"EMAIL_SEND_MASK";
	
	// 2016-07-26 Import Zolltarifnummer
	public static final String NAME_MODUL_IMPORT_ZOLLTARIFNUMMER = "MODUL_IMPORT_ZOLLTARIFNUMMER";



	
	//2015-03-25: modulkenner fuer die upload-popup-maske
	//public static final String  POPUP_UPLOADS = 			"POPUP_UPLOADS";
	
	//2015-04-27: Auswertungen Next Generation (nilsandre)
	//public static final String  NAME_MODUL_ROHSTOFFAUSWERTUNGEN_NG = 			"NAME_MODUL_ROHSTOFFAUSWERTUNGEN_NG";
}

