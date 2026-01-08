package panter.gmbh.basics4project.validation;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * enum definiert einzelne Bereiche, z.B. ADRESSE, die dann in verschiedenen Modulen zur Validierung herangezogen werden (z.B. Adressbearbeitung aus dem Modul Fuhrenzentrale)
 * @author martin
 *
 */
public enum ENUM_VALID_THEME   implements IF_enum_4_db{

	MENUE_CALL("Menü-Aufruf")	    //sondertheme, kann bei Menue-Aufrufen gesetzt werden (ist limitiert auf E2_MODULNAME_ENUM - eintraege vom type  MODUL_TYP.LIST  
	
	,KONTRAKT_EINKAUF("Einkaufskontrakte")
	,KONTRAKT_VERKAUF("Verkaufskontrakte")
	,AH7_STEUERDATEI("Anhang 7-Steuertabelle")
	,AH7_PROFIL("Anhang 7-Profile")
	
	//2017-11-13: @sebastien maskdefinition und maskdefinitioncell werkzeug
	,MASK_DEFINITION("Mask Definition")
	,MASK_DEFINITION_CELL("Mask Zelle Definition")

	,MESSAGE_PROVIDER("System-Meldung: Verteilungen")
	
	//20171201 Containerverwaltung
	,CONTAINER("Container-Verwaltung")
	// 20171206 Containtertyp-Verwaltung
	,CONTAINERTYP ("Containtertyp-Verwaltung")
	//2018-03-06 Lagerbewegungs-Liste der BG-Sätze
	,BG_LAGER_BEWEGUNG ("Lagerbewegung der BG-Sätze")
	,BG_LAGER_SALDO ("Lagerbestand der BG-Sätze")
	
	,FUHREN_KOSTEN_TYP("Fuhrenkosten bearbeiten")
	
	,SANKTION_TABELLE("Sanktionstabelle")
	
	//2018-06-12 Manfred - für die Grenzübertritts-Artikel
	,BORDERCROSSING_ARTIKEL("Grenzübertritt-Artikel")
	
	
	//2018-06-13: taxgroup
    ,TAX_GROUP("Steuersatz-Gruppe")

    //2018-06-26: report verlauf
    ,REPORT_LOG("REPORT_LOG-Beschreibung")
    
    
    //2018-07-12: thema Handelsdefinitionen
    ,HANDELSDEFINITIONEN("Handelsdefinitionen/Steuerregeln")
    
    
    //2018-07-22: SQL-basierte Reports
    ,REPORTING_QUERY("REPORTING_QUERY (SQL-basierte Reports) -Beschreibung")

    //2018-08-27: Bankenstamm global validieren
    ,BANKEN_STAMM("Bankenstamm verwalten")
    
    //2018-08-28: neues Hilfemodul
    ,HILFETEXT("Hilfetexte")
    
    //2018-11-09: neues Lagerhaltungsmodul
    ,LAGER_PALETTE("Lagereinträge")
    ,LAGER_PALETTE_BOX("Palette-Box verknüpfung")
    
    ,BG_TRANSPORT("Warenbewegung")

    //20181212: adressen-info bearbeiten
    ,ADRESSE_INFO("Adress-Zusatzinfo")
    ,ADRESSE_MELDUNG("Adress-Programm-Meldung")

    ,ADRESSE("Adresse")
    ,ARTIKEL("Artikel")
    ,LAUFZETTE_SIMPLE("Laufzetteleintrag")

	,ABNAHMEANGEBOT("Abnahmeangebot")
	,ANGEBOT("Verkaufsangebot")
	 
	,BG_ATOM("BG_ATOM-Beschreibung")
	
	,RECHUNGSDRUCK_DATUMSWERTE("Rechnungsdatum/Zahlungsziel beim Rechnungs-Druck")
	
	,WARENBEWEGUNGEN("Bereich Fuhren/Bewegungen")
	
    ,DIENSTLEISTUNG_PROFIL("Dienstleistungsprofile bearbeiten")

    //2019.08.30
    ,LAGER_BOX("Boxeinträge")
    
    //2020-01-27: report-varianten
    ,REP_VARIANTEN("Report-Varianten")
    
    // 2020-03-11 neue Wiegemaske
    ,WIEGEKARTE_RB("Wiegekarte (RB)")
    ,WIEGEKARTE_RB_CHILD_ABZUG("Abzug Gebinde")
    ,WIEGEKARTE_RB_MGE_ABZ("Abzug Menge")
    
    
    //2020-03-25: Textliste-Vorlagen-Verwaltung
    ,TEXT_LISTE_VORLAGE("Textlisten-Vorlagen")

    //2020-06-25: erweiterung steuerschluessel
    ,MWSTSCHLUESSEL_OLD("MWST-Steuerschlüssel (ALT)")

    ,ZOLLTARIFNUMMER("Bereich Zolltarifnummern")
    
    //2020-11-05 neues Tax-Modul
    ,TAX("Steuersätze bearbeiten")
    
    
    //2021-02-09: email-versand
    ,EMAIL_SEND("Email-Archiv")
 	
    ;
	
	private String beschreibung = null;

	/**
	 * @param p_beschreibung
	 */
	private ENUM_VALID_THEME(String p_beschreibung) {
		this.beschreibung = p_beschreibung;
	}
	

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return beschreibung;
	}

	@Override
	public String user_text_lang() {
		return beschreibung;
	}
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VALID_THEME.values(), emptyPairInFront);
	}

	public static String[][] getDDArraySorted(boolean emptyPairInFront) throws myException {
		VEK<ENUM_VALID_THEME> v = new VEK<>();
		for (ENUM_VALID_THEME e: ENUM_VALID_THEME.values()) {
			v._a(e);
		}
		v.sort((o1,o2)-> {return o1.user_text().compareTo(o2.user_text());});
		return bibENUM.dd_array(v.toArray(ENUM_VALID_THEME.class), emptyPairInFront);
	}
	
}
