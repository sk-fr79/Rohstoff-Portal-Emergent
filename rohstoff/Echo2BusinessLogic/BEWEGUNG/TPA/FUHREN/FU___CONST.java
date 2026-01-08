package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;

public class FU___CONST
{
	
	public static String HASH_KEY_GEFAHRGUT_ANZEIGE  = 								"HASH_KEY_GEFAHRGUT_ANZEIGE";
	public static String HASH_KEY_ARTBEZ_ANZEIGE_EK =								"HASH_KEY_ARTBEZ_ANZEIGE_EK";
	public static String HASH_KEY_ARTBEZ_ANZEIGE_VK =								"HASH_KEY_ARTBEZ_ANZEIGE_VK";

	//2018-03-05: neuer button fuer den aufruf zum routen
	public static String HASH_KEY_BT_ROUTING =										"HASH_KEY_BT_ROUTING";
	
	//2018-03-08: neuer button fuer den routen zu show
	public static String HASH_KEY_BT_ROUTING_SHOW =									"HASH_KEY_BT_ROUTING_SHOW";
	
	public static String HASH_KEY_STORNO_ANZEIGE  = 								"HASH_KEY_STORNO_ANZEIGE";
	
	public static String HASH_KEY_MASKE_TOCHTER_KOSTEN  = 							"HASH_KEY_MASKE_TOCHTER_KOSTEN";
	
	public static String HASH_KEY_MASKE_TOCHTER_QUELLORTE  = 						"HASH_KEY_MASKE_TOCHTER_QUELLORTE";
	public static String HASH_KEY_MASKE_TOCHTER_ZIELORTE  = 						"HASH_KEY_MASKE_TOCHTER_ZIELORTE";
	
	public static String HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK  = 						"HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK";
	public static String HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK  = 						"HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK";
	
	public static String HASH_KEY_LISTE_BAM_AN_FUHRE = 	                            "HASH_KEY_LISTE_BAM_AN_FUHRE";
	
	public static String HASH_KEY_LISTE_DIREKT_EDIT = 	                            "HASH_KEY_LISTE_DIREKT_EDIT";
	
	public static String HASH_KEY_MASKE_BUTTON_NEUE_ADRESSE  = 						"HASH_KEY_MASKE_BUTTON_NEUE_ADRESSE";
	
	public static final String HASH_KEY_BUTTON_MASK_OPEN_TPA =						"HASH_KEY_BUTTON_MASK_OPEN_TPA";
	
	public static final String HASH_KEY_BUTTON_MASK_VERTEILE_DATUM =				"HASH_KEY_BUTTON_MASK_VERTEILE_DATUM";
	
	public static final String HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER =			"HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER";
	
	public static final String HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_PLANMENGE_LIEF =	"HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_PLANMENGE_LIEF";
	public static final String HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_LADEMENGE_LIEF =	"HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_LADEMENGE_LIEF";
	public static final String HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_ABLADEMENGE_ABN =	"HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_ABLADEMENGE_ABN";
	
	public static final String HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI =	"HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI";
	public static final String HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI_UND_SAVE =	"HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI_UND_SAVE";
	
	public static final String HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK =		"HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK";
	public static final String HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK =		"HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK";
	
	public static final String HASH_KEY_PRUEFBLOCK_MENGE_EK =						"HASH_KEY_PRUEFBLOCK_MENGE_EK";
	public static final String HASH_KEY_PRUEFBLOCK_MENGE_VK =						"HASH_KEY_PRUEFBLOCK_MENGE_VK";

	public static final String HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK =			"HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK";
	public static final String HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK =			"HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK";
	
	
	public static final String FIELDNAME_ID_VPOS_TPA_FUHRE_3  = 					"ID_VPOS_TPA_FUHRE_3";
	
	//2018-06-07: waehrungsinfo
	public static final String FIELDNAME_INFOBOCK_WAEHRUNGEN =						"FIELDNAME_INFOBOCK_WAEHRUNGEN"; 
	
	
	public static String 		RG_VL_QUELLE_VARIANTE_SONDER = "SONDER";
	public static String 		RG_VL_QUELLE_VARIANTE_FREI = "FREI";
	public static String 		RG_VL_QUELLE_VARIANTE_SORTE = "SORTE";
	public static String[][]  	RG_VL_QUELLE_VARIANTEN = {{"-",""},
														{"Sonderposition",RG_VL_QUELLE_VARIANTE_SONDER},
														{"Frei eingegeben",RG_VL_QUELLE_VARIANTE_FREI},
														{"Sorte",RG_VL_QUELLE_VARIANTE_SORTE}};
	

	public static String HASH_KEY_DAUGHTER_POS_VORLAGEN  = "HASH_KEY_DAUGHTER_POS_VORLAGEN";
	
	
	public static String SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG = 					"SCHALTER_WARNING_NO_AVV_VERTRAG";
	

	/*
	 * in der fuhrenzentrale wird kuenftig folgendes abgewickelt:
	 * Fuhren / Fahrplan-Pool / Fahrplan-Tagespläne.
	 * Unterschieden sind diese varianten durch:
	 * 1. Masken-Tabreihenfolge
	 * 2. unterschiedliche Bedienpanels
	 * 3. unterschiedliche einblendungen der listenspalten
	 * 4. unterschiedliche basis-selektionen
	 * 
	 */
	public static int   TYP_FUHRENLISTE_FUHREN   		= 1;
	public static int   TYP_FUHRENLISTE_FP_TAGESPLAN   	= 2;
	public static int   TYP_FUHRENLISTE_FP_POOL   		= 3;
	

	//falls kein steuervermerk verwendet werden soll, dann werden die felder damit gefuellt
	public static final String  EU_STEUERVERMERK_LEER = "--- LEER ---";
	public static final String  EU_STEUERVERMERK_LAGER = "--- LAGER ---";
	
	
	
	//aenderung 2010-11-17: downloadbutton in archivlisten
	public static final String HASH_BUTTON_DOWNLOAD_ARCHIV  = 						"HASH_BUTTON_DOWNLOAD_ARCHIV";

	
	//aenderung 2010-12-16: downloadbutton in archivlisten
	public static final String HASH_GRID_LIST_GUTSCHRIFTEN  = 						"HASH_GRID_LIST_GUTSCHRIFTEN";
	public static final String HASH_GRID_LIST_RECHNUNGEN  = 						"HASH_GRID_LIST_RECHNUNGEN";
	
	
	public static final String HASH_4_SAVE_KOPIE_SETTINGS_FUHRE = 					"HASH_4_SAVE_KOPIE_SETTINGS_FUHRE";
	
	
	
	public static final String HASH_LIST_BT_KOSTEN_ERFASSUNG = 						"HASH_LIST_BT_KOSTEN_ERFASSUNG";
	
	
	//2018-03-05: neuer button fuer den aufruf zum routen
	public static final String HASH_LIST_BT_ROUTING = 								"HASH_LIST_BT_ROUTING";
	public static final String HASH_LIST_BT_SHOW_ROUTING = 							"HASH_LIST_BT_SHOW_ROUTING";
	
	public static final String HASH_UEBERSTIMMEN_FEHLENDEN_KONTRAKT= 				"UEBERSTIMMEN_FEHLENDEN_KONTRAKT";

	//2011-12-30: neue komponenten fuer die uma-kontrakte
	public static final String HASH_MASK_BUTTON_UMA_KONTRAKT= 						"MASK_BUTTON_UMA_KONTRAKT";

	
	public static final String HASH_LIST_BUTTON_UMA_KONTRAKT= 						"LIST_BUTTON_UMA_KONTRAKT";
	
	public static final String HASH_MASK_COMP_UMA_MASKEN_ANZEIGE= 					"MASK_COMP_UMA_MASKEN_ANZEIGE";
	
	
	//2013-05-27: fremdwaren-lageranzeige in maske
	public static final String HASH_MASK_COMP_FREMDWARENLAGER_LADESEITE= 				"MASK_COMP_FREMDWARENLAGER_LADESEITE";
	public static final String HASH_MASK_COMP_FREMDWARENLAGER_ABLADESEITE= 			"MASK_COMP_FREMDWARENLAGER_ABLADESEITE";
	
	
	// 2013-09-06: summenfeld in der liste fuer die anzeige der proformarechnungen
	public static final String HASH_LIST_FIELD_ANZAHL_PROFROMARECHNUNG = 				"FIELD_ZAHL_PROFORMA";
	
//	// 2013-09-16: feld fuer anzeige der wiegekarten-kennungen im fuhrenort
//	public static final String HASH_LIST_FIELD_WIEGEKARTE_FUO = 						"FIELD_WIEGEKARTE_FUO";
	 
	//2013-09-18: hashkeys fuer die buttons um die fuhrenwiegekarten aendern zu koennen
	public static final String HASH_LIST_FIELD_WIEGEKARTE_LINKS = 					"HASH_LIST_FIELD_WIEGEKARTE_LINKS";
	public static final String HASH_LIST_FIELD_WIEGEKARTE_RECHTS = 					"HASH_LIST_FIELD_WIEGEKARTE_RECHTS";
	

	
	//2013-09-27: hashkey fuer die fuhren-maske: loeschbutton fuer die erfasste ID_HANDELSDEF
	public static final String HASH_MASK_ERASE_ID_HANDELSDEF = 						"HASH_MASK_ERASE_ID_HANDELSDEF";
	//2018-11-27: hashkey fuer die fuhren-maske: editbutton fuer ID_HANDELSDEF
	public static final String HASH_MASK_EDIT_ID_HANDELSDEF = 						"HASH_MASK_EDIT_ID_HANDELSDEF";
	
	
	//2014-02-18: listenschalter fuer das anhaken des feldes  "GELANGENSBESTAETIGUNG_ERHALTEN"
	public static final String VALIDKEY_GELANGENSBESTAETIGUNG_ERHALTEN = 			"GELANGENSBESTAETIGUNG_ERHALTEN";
	
	
	//2014-06-02: feldnamen fuer subquery-felder in fuhrenzentale-liste
	public static final String FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_EK = 			"FU_LIEFERBEDINGUNGEN_EK";
	public static final String FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_VK = 			"FU_LIEFERBEDINGUNGEN_VK";
	//2014-07-04: als weitere info zwei felder mit den Lieferanten/Abnehmer-Passnummern
	public static final String FU_LIST_FIELDNAME_FU_PASSNUMMER_EK = 				"FU_PASSNUMMER_EK";
	public static final String FU_LIST_FIELDNAME_FU_PASSNUMMER_VK = 				"FU_PASSNUMMER_VK";
	
	
	
	public static final String FU_AUTHCODE_EDIT_ALTERNATIVE_LIEFERBEDINGUNG =        " LIEFERBED_ALTERNATIV_EDIT";
	
	//2015-12-09: mehrfachauswahl datumsfelder
	public static final String HASH_KEY_MASK_BUTTON_MULTI_POPUP_DATE = 				"HASH_KEY_MASK_BUTTON_MULTI_POPUP_DATE";
	
	
	//2017-01-16: neue aktive beschriftung
	public static final String ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE =   				"ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE";
	public static final String ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE =   			"ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE";
	
	
	
	
    // 20170901: weitere felder
	public static enum addonFields implements IF_enum_4_fielddefinition {
		
		AA_ZAHL_LIEFERSCHEIN_DRUCKE("(SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_DRUCK FPD WHERE FPD.BELEG='LIEFERSCHEIN' AND FPD.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)", "Zahl gedruckter Lieferscheine")
		;
		
		private String queryDef = null;
		private String userText = null;
		
		
		/**
		 * @param alias
		 * @param queryDef
		 * @param userText
		 */
		private addonFields(String queryDef, String userText) {
			this.queryDef = queryDef;
			this.userText = userText;
		}

		@Override
		public String alias() {
			return this.name();
		}

		@Override
		public String querydef() {
			return this.queryDef;
		}

		@Override
		public String user_text_lang() {
			return this.userText;
		}

		@Override
		public IF_enum_4_fielddefinition[] get_all_defs() {
			return addonFields.values();
		}
		
	}
	
}
