package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_FieldDefs_4_SQLFieldMAP;

public class FS_CONST
{
	
	public enum SQL_FIELDS implements IF_FieldDefs_4_SQLFieldMAP {
		
		ID2(ADRESSE.id_adresse.tnfn(), "Adressklasse")
		,ID3(ADRESSE.id_adresse.tnfn(), "Websites")
		;

		private String def = 		null;
		private String userText = 	null;
		
		
		
		private SQL_FIELDS(String p_def, String p_usertext) {
			this.def = 		p_def;
			this.userText = p_usertext;
		}

		@Override
		public String fieldDef() {
			return this.def;
		}

		@Override
		public String fieldAlias() {
			return this.name();
		}

		
		@Override
		public MyE2_String userText() {
			if (S.isEmpty(this.userText)) {
				return new MyE2_String(this.name(),false);
			} else {
				return new MyE2_String(this.userText);
			}
		}
	}
	
	
	
	/*
	 * hash-keys der info-spalten in der liste
	 */
	public static String LIST_COL_INFO_INFOS = 								"LIST_COL_INFO_INFOS";
	public static String LIST_COL_INFO_FILES = 								"LIST_COL_INFO_FILES";
	public static String LIST_COL_INFO_LIEFERADRESSEN = 					"LIST_COL_INFO_LIEFERADRESSEN";
	public static String LIST_COL_INFO_MITARBEITER = 						"LIST_COL_INFO_MITARBEITER";
	
	public static String LIST_COL_IST_LIEFERANT = 							"LIST_COL_IST_LIEFERANT";
	public static String LIST_COL_IST_ABNEHMER = 							"LIST_COL_IST_ABNEHMER";
	
	public static String LIST_COL_USTID_LIST_ZUSATZ = 						"LIST_COL_USTID_LIST_ZUSATZ";
	
	//2017-02-10: neue spalte mit anzeige der "lieblings-seiten eines users"
	public static String LIST_COL_SHOW_WEBSITES = 							"LIST_COL_SHOW_WEBSITES";

	//2018-02-05: neue spalte mit anzeige der buttons fuer geocodierung
	public static String LIST_GEOCODE = 									"LIST_GEOCODE";  
	
	public static String LIST_OSM_SHOW_SINGLE = 							"LIST_OSM_SHOW_SINGLE"; // alternativer Button für die Darstellung einer einzelnen Adresse via OSM-Karte
	public static String LIST_OSM_SHOW_ADR_LAGER = 							"LIST_OSM_ADR_LAGER";  // button für die Anzeige der Adresse und Lager 
	public static String LIST_OSM_SHOW_ADR_IN_HEADING =						"LIST_OSM_SHOW_ADR_IN_HEADING"; // button für die Anzeige der gewählten Adressen im Heading der Liste
	public static String LIST_OSM_SHOW_ADR_LAGER_IN_HEADING =				"LIST_OSM_SHOW_ADR_LAGER_IN_HEADING"; // button für die Anzeige der gewählten Adressen im Heading der Lager-Liste
	
	

	//public static String LIST_COL_DAUGHTER_MITARBEITER_QUALIFIER=			"LIST_COL_DAUGHTER_MITARBEITER_QUALIFIER";   

	//2012-05-15: sprungbutton zur verschiedenen modulen
	public static String LIST_BUTTON_MULTI_JUMP = 							"LIST_BUTTON_MULTI_JUMP";

	//2016-04-06: spalte komponen für anlagen und emails
	public static String LIST_ANLAGEN_EMAILS 	=							"LIST_ANLAGEN_EMAILS";
	
	public static String MASK_FIELD_DAUGHTER_TELEFON=						"MASK_FIELD_DAUGHTER_TELEFON";
	public static String MASK_FIELD_DAUGHTER_EMAIL=							"MASK_FIELD_DAUGHTER_EMAIL";
	public static String MASK_FIELD_DAUGHTER_INTERNET=						"MASK_FIELD_DAUGHTER_INTERNET";
	public static String MASK_FIELD_DAUGHTER_BEZIEHUNG=						"MASK_FIELD_DAUGHTER_BEZIEHUNG";
	public static String MASK_FIELD_DAUGHTER_ARTIKELBEZ=					"MASK_FIELD_DAUGHTER_ARTIKELBEZ";
	public static String MASK_FIELD_DAUGHTER_ARTIKELBEZ_VK=					"MASK_FIELD_DAUGHTER_ARTIKELBEZ_VK";
	public static String MASK_FIELD_DAUGHTER_ARTIKEL_ERLAUBT=				"MASK_FIELD_DAUGHTER_ARTIKEL_ERLAUBT";
	public static String MASK_FIELD_CROSS_CONNECT_TYP=						"MASK_FIELD_CROSS_CONNECT_TYP";
	public static String MASK_FIELD_CROSS_CONNECT_AUSTATTUNG=				"MASK_FIELD_CROSS_CONNECT_AUSTATTUNG";
	public static String MASK_FIELD_CROSS_CONNECT_MWST=						"MASK_FIELD_CROSS_CONNECT_MWST";
	
	//2015-08-31: zusatzbranchen-crosstable
	public static String MASK_FIELD_CROSS_CONNECT_ZUSATZBRANCHE=			"MASK_FIELD_CROSS_CONNECT_ZUSATZBRANCHE";
	
	public static String MASK_FIELD_DAUGHTER_CONTAINERTYPEN=				"MASK_FIELD_DAUGHTER_CONTAINERTYPEN";
	public static String MASK_FIELD_DAUGHTER_UST_IDS=						"MASK_FIELD_DAUGHTER_UST_IDS";
	public static String MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT=		"MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT";
	
	public static String MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER =			"MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS =			"MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES =			"MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN =		"MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_KONTEN =				"MASK_FIELD_COMPLETE_DAUGHTER_KONTEN";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ =				"MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ";
	
	//NEU_09
	public static String MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK =			"MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK";
	public static String MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK =			"MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK";
	
	//NEU_09
	public static String MASK_FIELD_DAUGHTER_TELEFON_LIEF=					"MASK_FIELD_DAUGHTER_TELEFON_LIEF";
	public static String MASK_FIELD_DAUGHTER_EMAIL_LIEF=					"MASK_FIELD_DAUGHTER_EMAIL_LIEF";

	// Manfred 2013-04-15 Anzeige Kundenstatus
	public static String MASK_FIELD_INFO_KUNDENSTATUS = 					"MASK_FIELD_INFO_KUNDENSTATUS";
	
	public static String MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT=		"MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT";
	public static String MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT=		"MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT";

	public static String MASK_FIELD_VALID_KEY_IMPORT_AVV_CODES=					"IMPORT_AVV_CODES_ZU_ADRESSE";
	
	/*
	 * elemente in der mitarbeiter-maske 
	 */
	public static String MASK_FIELD_DAUGHTER_MITARBEITER_INFOS=				"MASK_FIELD_DAUGHTER_MITARBEITER_INFOS";
	public static String MASK_FIELD_DAUGHTER_MITARBEITER_GESCHENKE=			"MASK_FIELD_DAUGHTER_MITARBEITER_GESCHENKE";   //NEU_09
	
	//public static String MASK_FIELD_DAUGHTER_MITARBEITER_QUALIFIER=			"MASK_FIELD_DAUGHTER_MITARBEITER_QUALIFIER";   
	
	
	public static String MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB=				"MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB";    
	
	
	//2014-01-17: neue daughtertable RG-Artikel
	public static String MASK_FIELD_DAUGHTER_RECH_GUT_POS=					"MASK_FIELD_DAUGHTER_RECH_GUT_POS";
	
	
	/*
	 * hash-key fuer eine adress-info, die ueber dem tabbed-pane angezeigt wird
	 */
//	public static String MASK_FIELD_ADRESS_INFO=							"MASK_FIELD_ADRESS_INFO";
	
	
	public static int MASK_WIDTH = 1000;
	public static int MASK_HEIGHT = 800;

	
	//2011-09-20: Kreditversicherungs-inlay
	public static String MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG=				"MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG";
	
	
	//2013-11-15: MASK_FIELD_VALIDIERER
	public static String MASK_FIELD_VALIDATOR_PRIVATADRESSE =					"EINSTUFUNG_ADRESSE_IST_PRIVAT";
	public static String MASK_FIELD_VALIDATOR_FIRMA =							"EINSTUFUNG_ADRESSE_IST_FIRMA";
	public static String MASK_FIELD_VALIDATOR_FIRMA_OHNE_USTID =				"EINSTUFUNG_ADRESSE_SONDERFALL_FIRMA_OHNE_USTID";
	public static String MASK_FIELD_VALIDATOR_PRIVAT_MIT_USTID =				"EINSTUFUNG_ADRESSE_SONDERFALL_PRIVAT_MIT_USTID";
	
	//2013-11-19
	public static String MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE = 			"FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE";	
	public static String MASK_BUTTON_SUCHE_ALLE_KOSTEN_KOMBIS =		 			"BUTTON_SUCHE_ALLE_KOSTEN_KOMBIS";
	public static String MASK_BUTTON_SUCHE_PREISE_FUER_KOSTEN_KOMBIS =		 	"BUTTON_SUCHE_PREISE_FUER_KOSTEN_KOMBIS";
	
	
	//2013-11-19: komponente fuer die anzeige des status /steuer
	public static String MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS= 				"MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS";
	
	public static String LIST_VALID_ERMITTLE_KOSTENRELATIONEN = 	 			"LIST_VALID_ERMITTLE_KOSTENRELATIONEN";
	public static String MASK_VALID_ERMITTLE_KOSTENRELATIONEN =  				"MASK_VALID_ERMITTLE_KOSTENRELATIONEN";
	public static String LIST_VALID_ERMITTLE_KOSTEN =  							"LIST_VALID_ERMITTLE_KOSTEN";
	public static String MASK_VALID_ERMITTLE_KOSTEN =  							"MASK_VALID_ERMITTLE_KOSTEN";
	
	
	public static String TABTEXT_INFOS_IN_ADRESSMASK 						= "Infos";
	public static String TABTEXT_FINANZEN_IN_ADRESSMASK 					= "Finanz/ Handel";
	

	
	public static String MASK_BT_CHECK_USTID 					= "MASK_BT_CHECK_USTID";
	
	
}
