package panter.gmbh.basics4project;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/*
 * hier static-bereich der die feldnamen deklariert (ab 2014-12-01) 
 */
public enum ENUM_MANDANT_ZUSATZ_FELDNAMEN { 
	WEIGERMELDUNG_IN_KUNDENARCHIV(null, null, null),
	SUPPRESS_CHECKING_KREDITSTATUS_BEFORE_PRINT_PAPERS(null, null, null),
	EXPORT_PATH_FOR_ABZUGSGRUND_XML(null, null, null),
	ZWINGE_SUCHE_NACH_LIEFERADRESSE_IN_FUHRE(null, null, null),
	//2015-08-23: in der alten Fuhren-UST-Struktur wurden fehlende/falsch UST-ID nur gewarnt, diese koennen hier auf status fehler geschaltet werden
	//2015-10-27: wirkt jetzt auch umgekehrt, es werden beim ausgeschaltetem Feld die fehler im der Handeldefinition nur noch als warnung angezeigt
	ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID(null, null, null),             
	
	//historische testkonstantenliste ebenfalls ueberfuehrt in die enums (2015-09-21: martin)
	ANHANG7_VARIANTE_FUER_KONTROLLE_DRUCKEN(null, null, null),
	DRUCKE_ZWEITES_EUBLATT(null, null, null),
	FARBE_UMA_BUTTON_IN_LISTE(null, null, null),
	LIEFERSCHEIN_BESITZTEXT(null, null, null),
	LIEFERSCHEIN_FUSSTEXT(null, null, null),
	MAHNUNG_1_UEBERSCHRIFT_EN(null, null, null),
	MAHNUNG_2_UEBERSCHRIFT_EN(null, null, null),
	MAHNUNG_3_UEBERSCHRIFT_DE(null, null, null),
	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE3(null, null, null),
	MAHNUNG_SEITENFUSS_BLOCK_RECHTS(null, null, null),
	WAAGE_HOFSCHEIN_DRUCKEN_WA(null, null, null),
	BELEGTEXT_DUPLIKAT(null, null, null),
	BEWEGUNGSSATZ_KEINE_SONDERLAGER_ERZEUGEN(null, null, null),
	ERLAUBE_FEHLENDE_AUSWEISNUMMER_IN_GUTSCHRIFTEN_BARKUNDEN(null, null, null),
	FORMULARFUSSBILDUNTERSCHRIFT(null, null, null),
	FORMULARFUSSTEXT_RECHTS(null, null, null),
	FREMDWARE_ZU_FREMDWARENLAGER_INAKTIV(null, null, null),
	LIEFERSCHEIN_GELANGENTEXT(null, null, null),
	MAHNUNG_2_UEBERSCHRIFT_DE(null, null, null),
	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE1(null, null, null),
	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE4(null, null, null),
	ANHANG7_VARIANTE_FUER_ABHNEHMER_DRUCKEN(null, null, null),
	FORMULARFUSSTEXT_LINKS(null, null, null),
	JT_VPOS_TPA_FUHRE_KENNZEICHEN_IST_MUSSFELD(null, null, null),
	JT_VPOS_TPA_FUHRE_VORGABE_TRANSPORTMITTEL(null, null, null),
	MAHNUNG_3_UEBERSCHRIFT_EN(null, null, null),
	ORDNUNGSNUMMER_CMR(null, null, null),
	RECHNUNG_PER_FAX_EINDRUCK(null, null, null),
	FORMULARFUSSTEXT_MITTE(null, null, null),
	WAAGE_HOFSCHEIN_DRUCKEN_ANZEIGE_BUTTON(null, null, null),
	ADRESS_EK_SORTE_AVV_AUTOMATISCH(null, null, null),
	HINWEIS_INTERNER_BELEG(null, null, null),
	MAHNUNG_1_UEBERSCHRIFT_DE(null, null, null),
	MAHNUNG_1_UEBERSCHRIFT_FR(null, null, null),
	MAHNUNG_SEITENFUSS_BLOCK_MITTE(null, null, null),
	WAAGE_HOFSCHEIN_DRUCKEN_WE(null, null, null),
	ABHOLNACHRICHTEIGENTUMSHINWEIS(null, null, null),
	BROWSER_OPEN_WINDOW_COMMAND(null, null, null),
	HANDELSDEF_ERLAUBE_VARIANTE_EGAL_IN_DROPDOWN(null, null, null),
	LAGER_WA_GEWICHT_NETTO(null, null, null),
	LIEFERAVIS_EIGENTUMSVORBEHALT(null, null, null),
	MAHNUNG_2_UEBERSCHRIFT_FR(null, null, null),
	MAHNUNG_3_UEBERSCHRIFT_FR(null, null, null),
	RECHNUNGSPOSITION_MANUELL_SCHROTTSORTE_VERBIETEN(null, null, null),
	STEUERERMITTLUNG_MIT_HANDELSDEF(null, null, null),
	VK_KONTRAKT_ENDTEXT_STD(null, null, null),
	WIEGEGEWICHTE_BEIDSEITIG_EINTRAGEN(null, null, null),
	ANHANG7_FELDCHECK_NUR_WARN(null, null, null),
	EK_KONTRAKT_ENDTEXT_STD(null, null, null),
	ERLAUBE_FEHLENDE_UST_KENNZEICHEN_IN_EU_RECHNUNG(null, null, null),
	FELDLISTE_KONTRAKT_LIEFERORT(null, null, null),
	FORMULARFUSSTEXT(null, null, null),
	LAGER_WA_PREIS_KORREKTUR(null, null, null),
	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE2(null, null, null),
	FREMDWARE_ZU_FREMDWARENLAGER_WENN_AKTIV_NUR_WARNUNG(null, null, null),
	LAGER_UMBUCHUNGEN_ERLAUBEN(null, null, null),
	MAHNUNG_SEITENFUSS_BLOCK_LINKS(null, null, null),
	VORGABEWERT_MITARBEITERGESCHENK(null, null, null),

	VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG(null, null, null),
	HANDELSVERTRAG_PRUEFE_AN_JUR_ORT(null, null, null),
	HANDELSVERTRAG_PRUEFE_AN_GEO_ORT(null, null, null),
	
	KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF(null, null, null),
	KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF_KADENZZEIT(null, null, null),
	
	WAAGE_WARNUNG_BEI_MINDESTLAST_WE_UNTERSCHREITUNG(null, null, null),
	WAAGE_DRUCK_WENN_NUR_HANDWAEGUNG(null, null, null),
	WAAGE_SPERRE_BEI_MINDESTLAST_UNTERSCHREITUNG(null, null, null),
	WIEGEKARTE_ARCHIV_TO_FUHRE(null, null, null),
	WIEGEKARTE_ARCHIV_TO_ADRESSE(null, null, null),
	WORKFLOW_SEND_MAIL_ON_STATUS_CHANGE(null, null, null),
	WORKFLOW_SEND_SOFORTNACHRICHT(null,null,null),
	DIRECTACCESS_REPORT_LIST_IS_NEW_MODULE(null, null, null),
	NACHRICHT_ALLOW_SEND_ADDITIONAL_EMAIL(null, null, null),
	LAGER_ALLOW_BUCHUNG_BUCHHALTERISCH(null, null, null)
	,PRUEFE_EU_VERTRAEGE_BEI_PRIVATADRESSE(null, null, null)
	,TEST("info fuer test", "maskText", "defaulttext")
	,TEST1("info fuer test", "maskText", "defaulttext")
	,TEST2("info fuer test", "maskText", "defaulttext")
	,TEST3("info fuer test", "maskText", "defaulttext")
	,WEBSEITE_WAEHRUNGSKURSE("Webseite fur Waehrungskurse", "WEBSEITE_WAEHRUNGSKURSE", "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html")
	
	;
	
	private String f_infoText = 		null;
	private String f_maskText = 		null;
	private String f_defaultValue =		null;
	
	
	private ENUM_MANDANT_ZUSATZ_FELDNAMEN(String infoText, String maskText, String defaultValue) {
		this.f_infoText = infoText;
		this.f_maskText = maskText;
		this.f_defaultValue = defaultValue;
	}


	public String get_infoText() {
		if (S.isEmpty(f_infoText)) {
			return this.toString();
		} 
		return f_infoText;
	}


	public String get_maskText() {
		if (S.isEmpty(f_maskText)) {
			return this.toString();
		} 
		return f_maskText;
	}


	public String get_defaultValue() {
		return f_defaultValue;
	}

	
	public String getTextVal(String id_mandant) throws myException {
		
		String c_ret  = null;
		
		MyE2_MessageVector mv =  new MyE2_MessageVector();
				
		
		SEL selTextVal =  new SEL(_TAB.mandant_zusatz).FROM(_TAB.mandant_zusatz).WHERE(MANDANT_ZUSATZ.id_mandant, id_mandant).AND(
																					new vgl(MANDANT_ZUSATZ.fieldname,this.toString()));
		SEL selFeldNamen = new SEL(_TAB.mandant_zusatz_feldnamen).FROM(_TAB.mandant_zusatz_feldnamen).WHERE(new vgl(MANDANT_ZUSATZ_FELDNAMEN.fieldname,this.toString()));
		
		//zuerst nach dem text fragen, wenn nicht vorhanden, dann nach dem Feldnamen fragen, wenn gefunden, dann feldnamen neu schreiben und default zurueck;
		Rec20 recText = new Rec20(_TAB.mandant_zusatz)._fill_sql(selTextVal.s());
		
		MyDBToolBox oDB = MyDBToolBox_FAB.generate_STANDARD_DBToolBox();
		oDB.set_bErsetzungTableView(false);
		
		try {
		if (recText.is_newRecordSet()) {   
			//wenn kein ergebnis, dann nach dem feldnamen fragen
			RecList20 rlFeld = new RecList20(_TAB.mandant_zusatz_feldnamen)._set_dbtoolbox(oDB)._fill(selFeldNamen.s());
			
			if (rlFeld.size()==0) {
				Rec20 rFeld = new Rec20(_TAB.mandant_zusatz_feldnamen);
				
				c_ret = this.get_defaultValue();

				DEBUG.System_println("NEUES ZUSATZFELD zu schreiben: "+this.toString());
//				DEBUG.System_println("sql: "+rlFeld.get_sql_4_save(false));
				
				//und den mandant_zusatz_feldnamen rauschreiben
				rFeld	._nv(MANDANT_ZUSATZ_FELDNAMEN.fieldname, 			this.toString(), mv)
						._nv(MANDANT_ZUSATZ_FELDNAMEN.relation_info, 		this.get_infoText(), mv)
						._nv(MANDANT_ZUSATZ_FELDNAMEN.default_text_value, 	this.get_defaultValue(), mv)
						._nv(MANDANT_ZUSATZ_FELDNAMEN.mask_text, 			this.get_maskText(), mv)
						._nv(MANDANT_ZUSATZ_FELDNAMEN.field_type, 			"UNDEF", mv)._SAVE(true, mv);
				
				
				if (mv.get_bHasAlarms()) {
					throw new myException(this,"Error adding ENUM-Member to mandant_zusatz_feldnamen");
				} else {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new  MyE2_String("Zusatzfeld in die Datenbank geschrieben:").ut(this.toString())));
				}
			} else {
				c_ret = rlFeld.get(0).get_fs_dbVal(MANDANT_ZUSATZ_FELDNAMEN.default_text_value);
				
				// falls der textschluessel mehrmal in mehreren mandanten angelegt wurde, muss eine
				// fehlermeldung angezeigt werden
				if (rlFeld.size()>1) {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(S.ms("Achtung! Der Feldschlüssel ").ut(this.name()).t(" wurde in mehreren Mandanten angelegt !")));
				}
			}
			
			
		} else {
			c_ret = recText.get_ufs_dbVal(MANDANT_ZUSATZ.text_value);
		}
			
		} catch (Exception e) {
			bibALL.destroy_myDBToolBox(oDB);
			if (e instanceof myException) {
				throw (myException)e;
			}
		}
		
		bibALL.destroy_myDBToolBox(oDB);
		if (mv.get_bHasAlarms()) {
			throw new myException(this,"Error reading zusatzvalue !");
		}
					
		return c_ret;
	}

	
	
	/**
	 * alle fehlende datensaetze schreiben
	 * @throws myException 
	 */
	public static void write_all_new() throws myException {
		
		for (ENUM_MANDANT_ZUSATZ_FELDNAMEN dec: ENUM_MANDANT_ZUSATZ_FELDNAMEN.values()) {
			dec.getTextVal(bibALL.get_ID_MANDANT());
		}
		
	}

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///alter code vor entfernen der (ueberall gleichen) keys
	
//	WEIGERMELDUNG_IN_KUNDENARCHIV(),
//	SUPPRESS_CHECKING_KREDITSTATUS_BEFORE_PRINT_PAPERS(),
//	EXPORT_PATH_FOR_ABZUGSGRUND_XML(),
//	ZWINGE_SUCHE_NACH_LIEFERADRESSE_IN_FUHRE(),
//	//2015-08-23: in der alten Fuhren-UST-Struktur wurden fehlende/falsch UST-ID nur gewarnt, diese koennen hier auf status fehler geschaltet werden
//	//2015-10-27: wirkt jetzt auch umgekehrt, es werden beim ausgeschaltetem Feld die fehler im der Handeldefinition nur noch als warnung angezeigt
//	ERZWINGE_FEHLER_WARENBEWEGUNG_BELEGDRUCK_BEI_FALSCHER_USTID(),             
//	
//	//historische testkonstantenliste ebenfalls ueberfuehrt in die enums (2015-09-21: martin)
//	ANHANG7_VARIANTE_FUER_KONTROLLE_DRUCKEN("ANHANG7_VARIANTE_FUER_KONTROLLE_DRUCKEN"),
//	DRUCKE_ZWEITES_EUBLATT("DRUCKE_ZWEITES_EUBLATT"),
//	FARBE_UMA_BUTTON_IN_LISTE("FARBE_UMA_BUTTON_IN_LISTE"),
//	LIEFERSCHEIN_BESITZTEXT("LIEFERSCHEIN_BESITZTEXT"),
//	LIEFERSCHEIN_FUSSTEXT("LIEFERSCHEIN_FUSSTEXT"),
//	MAHNUNG_1_UEBERSCHRIFT_EN("MAHNUNG_1_UEBERSCHRIFT_EN"),
//	MAHNUNG_2_UEBERSCHRIFT_EN("MAHNUNG_2_UEBERSCHRIFT_EN"),
//	MAHNUNG_3_UEBERSCHRIFT_DE("MAHNUNG_3_UEBERSCHRIFT_DE"),
//	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE3("MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE3"),
//	MAHNUNG_SEITENFUSS_BLOCK_RECHTS("MAHNUNG_SEITENFUSS_BLOCK_RECHTS"),
//	WAAGE_HOFSCHEIN_DRUCKEN_WA("WAAGE_HOFSCHEIN_DRUCKEN_WA"),
//	BELEGTEXT_DUPLIKAT("BELEGTEXT_DUPLIKAT"),
//	BEWEGUNGSSATZ_KEINE_SONDERLAGER_ERZEUGEN("BEWEGUNGSSATZ_KEINE_SONDERLAGER_ERZEUGEN"),
//	ERLAUBE_FEHLENDE_AUSWEISNUMMER_IN_GUTSCHRIFTEN_BARKUNDEN("ERLAUBE_FEHLENDE_AUSWEISNUMMER_IN_GUTSCHRIFTEN_BARKUNDEN"),
//	FORMULARFUSSBILDUNTERSCHRIFT("FORMULARFUSSBILDUNTERSCHRIFT"),
//	FORMULARFUSSTEXT_RECHTS("FORMULARFUSSTEXT_RECHTS"),
//	FREMDWARE_ZU_FREMDWARENLAGER_INAKTIV("FREMDWARE_ZU_FREMDWARENLAGER_INAKTIV"),
//	LIEFERSCHEIN_GELANGENTEXT("LIEFERSCHEIN_GELANGENTEXT"),
//	MAHNUNG_2_UEBERSCHRIFT_DE("MAHNUNG_2_UEBERSCHRIFT_DE"),
//	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE1("MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE1"),
//	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE4("MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE4"),
//	ANHANG7_VARIANTE_FUER_ABHNEHMER_DRUCKEN("ANHANG7_VARIANTE_FUER_ABHNEHMER_DRUCKEN"),
//	FORMULARFUSSTEXT_LINKS("FORMULARFUSSTEXT_LINKS"),
//	JT_VPOS_TPA_FUHRE_KENNZEICHEN_IST_MUSSFELD("JT_VPOS_TPA_FUHRE_KENNZEICHEN_IST_MUSSFELD"),
//	JT_VPOS_TPA_FUHRE_VORGABE_TRANSPORTMITTEL("JT_VPOS_TPA_FUHRE_VORGABE_TRANSPORTMITTEL"),
//	MAHNUNG_3_UEBERSCHRIFT_EN("MAHNUNG_3_UEBERSCHRIFT_EN"),
//	ORDNUNGSNUMMER_CMR("ORDNUNGSNUMMER_CMR"),
//	RECHNUNG_PER_FAX_EINDRUCK("RECHNUNG_PER_FAX_EINDRUCK"),
//	FORMULARFUSSTEXT_MITTE("FORMULARFUSSTEXT_MITTE"),
//	WAAGE_HOFSCHEIN_DRUCKEN_ANZEIGE_BUTTON("WAAGE_HOFSCHEIN_DRUCKEN_ANZEIGE_BUTTON"),
//	ADRESS_EK_SORTE_AVV_AUTOMATISCH("ADRESS_EK_SORTE_AVV_AUTOMATISCH"),
//	HINWEIS_INTERNER_BELEG("HINWEIS_INTERNER_BELEG"),
//	MAHNUNG_1_UEBERSCHRIFT_DE("MAHNUNG_1_UEBERSCHRIFT_DE"),
//	MAHNUNG_1_UEBERSCHRIFT_FR("MAHNUNG_1_UEBERSCHRIFT_FR"),
//	MAHNUNG_SEITENFUSS_BLOCK_MITTE("MAHNUNG_SEITENFUSS_BLOCK_MITTE"),
//	WAAGE_HOFSCHEIN_DRUCKEN_WE("WAAGE_HOFSCHEIN_DRUCKEN_WE"),
//	ABHOLNACHRICHTEIGENTUMSHINWEIS("ABHOLNACHRICHTEIGENTUMSHINWEIS"),
//	BROWSER_OPEN_WINDOW_COMMAND("BROWSER_OPEN_WINDOW_COMMAND"),
//	HANDELSDEF_ERLAUBE_VARIANTE_EGAL_IN_DROPDOWN("HANDELSDEF_ERLAUBE_VARIANTE_EGAL_IN_DROPDOWN"),
//	LAGER_WA_GEWICHT_NETTO("LAGER_WA_GEWICHT_NETTO"),
//	LIEFERAVIS_EIGENTUMSVORBEHALT("LIEFERAVIS_EIGENTUMSVORBEHALT"),
//	MAHNUNG_2_UEBERSCHRIFT_FR("MAHNUNG_2_UEBERSCHRIFT_FR"),
//	MAHNUNG_3_UEBERSCHRIFT_FR("MAHNUNG_3_UEBERSCHRIFT_FR"),
//	RECHNUNGSPOSITION_MANUELL_SCHROTTSORTE_VERBIETEN("RECHNUNGSPOSITION_MANUELL_SCHROTTSORTE_VERBIETEN"),
//	STEUERERMITTLUNG_MIT_HANDELSDEF("STEUERERMITTLUNG_MIT_HANDELSDEF"),
//	VK_KONTRAKT_ENDTEXT_STD("VK_KONTRAKT_ENDTEXT_STD"),
//	WIEGEGEWICHTE_BEIDSEITIG_EINTRAGEN("WIEGEGEWICHTE_BEIDSEITIG_EINTRAGEN"),
//	ANHANG7_FELDCHECK_NUR_WARN("ANHANG7_FELDCHECK_NUR_WARN"),
//	EK_KONTRAKT_ENDTEXT_STD("EK_KONTRAKT_ENDTEXT_STD"),
//	ERLAUBE_FEHLENDE_UST_KENNZEICHEN_IN_EU_RECHNUNG("ERLAUBE_FEHLENDE_UST_KENNZEICHEN_IN_EU_RECHNUNG"),
//	FELDLISTE_KONTRAKT_LIEFERORT("FELDLISTE_KONTRAKT_LIEFERORT"),
//	FORMULARFUSSTEXT("FORMULARFUSSTEXT"),
//	LAGER_WA_PREIS_KORREKTUR("LAGER_WA_PREIS_KORREKTUR"),
//	MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE2("MAHNUNG_MANDANTEN_ANSCHRIFTBLOCK_ZEILE2"),
//	FREMDWARE_ZU_FREMDWARENLAGER_WENN_AKTIV_NUR_WARNUNG("FREMDWARE_ZU_FREMDWARENLAGER_WENN_AKTIV_NUR_WARNUNG"),
//	LAGER_UMBUCHUNGEN_ERLAUBEN("LAGER_UMBUCHUNGEN_ERLAUBEN"),
//	MAHNUNG_SEITENFUSS_BLOCK_LINKS("MAHNUNG_SEITENFUSS_BLOCK_LINKS"),
//	VORGABEWERT_MITARBEITERGESCHENK("VORGABEWERT_MITARBEITERGESCHENK"),
//
//	VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG("VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG"),
//	HANDELSVERTRAG_PRUEFE_AN_JUR_ORT("HANDELSVERTRAG_PRUEFE_AN_JUR_ORT"),
//	HANDELSVERTRAG_PRUEFE_AN_GEO_ORT("HANDELSVERTRAG_PRUEFE_AN_GEO_ORT"),
//	
//	KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF("KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF"),
//	KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF_KADENZZEIT("KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF_KADENZZEIT"),
//	
//	WAAGE_WARNUNG_BEI_MINDESTLAST_WE_UNTERSCHREITUNG("WAAGE_WARNUNG_BEI_MINDESTLAST_WE_UNTERSCHREITUNG"),
//	WAAGE_DRUCK_WENN_NUR_HANDWAEGUNG("WAAGE_DRUCK_WENN_NUR_HANDWAEGUNG"),
//	WAAGE_SPERRE_BEI_MINDESTLAST_UNTERSCHREITUNG("WAAGE_SPERRE_BEI_MINDESTLAST_UNTERSCHREITUNG"),
//	WIEGEKARTE_ARCHIV_TO_FUHRE("WIEGEKARTE_ARCHIV_TO_FUHRE"),
//	WIEGEKARTE_ARCHIV_TO_ADRESSE("WIEGEKARTE_ARCHIV_TO_ADRESSE"),
//	WORKFLOW_SEND_MAIL_ON_STATUS_CHANGE("WORKFLOW_SEND_MAIL_ON_STATUS_CHANGE"),
//	DIRECTACCESS_REPORT_LIST_IS_NEW_MODULE(),
//	NACHRICHT_ALLOW_SEND_ADDITIONAL_EMAIL("NACHRICHT_ALLOW_SEND_ADDITIONAL_EMAIL"),
//	LAGER_ALLOW_BUCHUNG_BUCHHALTERISCH("LAGER_ALLOW_BUCHUNG_BUCHHALTERISCH"),
//	PRUEFE_EU_VERTRAEGE_BEI_PRIVATADRESSE();

