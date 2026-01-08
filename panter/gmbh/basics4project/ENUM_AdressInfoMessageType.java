/**
 * panter.gmbh.basics4project
 * @author martin
 * @date 18.12.2018
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 18.12.2018
 * umstellung von konstanten auf enum
 */
public enum ENUM_AdressInfoMessageType  implements IF_enum_4_db_specified<ENUM_AdressInfoMessageType>{
	
	ADRESS_INFO_TYP_ALLGEMEIN( 				"ALLGEMEIN", 		"Allgemeine Info")
	,ADRESS_INFO_TYP_KONTRAKT_INFO( 		"KONTRAKT_INFO",	"Info zu EK- und VK-Kontrakten")
	,ADRESS_INFO_TYP_KONTRAKT_INFO_EK( 		"KONTRAKT_INFO_EK",	"Info zu EK-Kontrakten")
	,ADRESS_INFO_TYP_KONTRAKT_INFO_VK( 		"KONTRAKT_INFO_VK",	"Info zu VK-Kontrakten")
	,ADRESS_INFO_TYP_TPA_INFO( 				"TPA_INFO",			"Info zu Transportaufträgen")
	,ADRESS_INFO_TYP_RECH_GUT_INFO( 		"RECH_GUT_INFO",	"Info zu Rechnungen/Gutschriften")
	,ADRESS_INFO_TYP_RECHNUNG_INFO( 		"RECHNUNG_INFO",	"Info zu Rechnungen")
	,ADRESS_INFO_TYP_GUTSCHRIFT_INFO( 		"GUTSCHRIFT_INFO",	"Info zu Gutschriften")
	,ADRESS_INFO_TYP_ANGEBOT_INFO( 			"ANGEBOT_INFO",		"Info zu Angeboten")
	,ADRESS_INFO_TYP_ABGEBOT_EK_INFO( 		"ANGEBOT_EK_INFO",	"Info zu Abnahme-Angeboten")
	,ADRESS_INFO_TYP_ANGEBOT_VK_INFO( 		"ANGEBOT_VK_INFO",	"Info zu Verkaufs-Angeboten")
	,ADRESS_INFO_TYP_EINKAUF( 				"EINKAUF_INFO",		"Info zu Einkauf (Allgemein/Fuhren)")
	,ADRESS_INFO_TYP_VERKAUF( 				"VERKAUF_INFO",		"Info zu Verkauf (Allgemein/Fuhren)")
	,ADRESS_INFO_TYP_FIBU( 					"FIBU_INFO",		"Info FIBU/Buchhaltung")

	;

	private String m_dbVal=		null;
	private String m_userInfo=	null;

	private ENUM_AdressInfoMessageType(String dbVal, String userInfo) {
		this.m_dbVal = dbVal;
		this.m_userInfo = userInfo;
	}
	
	@Override
	public String db_val() {
		return m_dbVal;
	}

	@Override
	public String user_text() {
		return m_userInfo;
	}

	@Override
	public ENUM_AdressInfoMessageType[] get_Values() {
		return ENUM_AdressInfoMessageType.values();
	}
	
	
	/*
	 * ADRESS-Info-Typ
	 * bitmuster:  Stelle 1: unbenutzt: Stelle 2: Vorgang, Stelle 3: Vorgang Untergruppe
	 */
//	public static String 					ADRESS_INFO_TYP_ALLGEMEIN = 				"ALLGEMEIN";
//	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO = 			"KONTRAKT_INFO";
//	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO_EK = 			"KONTRAKT_INFO_EK";
//	public static String 					ADRESS_INFO_TYP_KONTRAKT_INFO_VK = 			"KONTRAKT_INFO_VK";
//	public static String 					ADRESS_INFO_TYP_TPA_INFO = 					"TPA_INFO";
//	public static String 					ADRESS_INFO_TYP_RECH_GUT_INFO = 			"RECH_GUT_INFO";
//	public static String 					ADRESS_INFO_TYP_RECHNUNG_INFO = 			"RECHNUNG_INFO";
//	public static String 					ADRESS_INFO_TYP_GUTSCHRIFT_INFO = 			"GUTSCHRIFT_INFO";
//	public static String 					ADRESS_INFO_TYP_ANGEBOT_INFO = 				"ANGEBOT_INFO";
//	public static String 					ADRESS_INFO_TYP_ABGEBOT_EK_INFO = 			"ANGEBOT_EK_INFO";
//	public static String 					ADRESS_INFO_TYP_ANGEBOT_VK_INFO = 			"ANGEBOT_VK_INFO";
//
//	//neue info-Typen
//	public static String 					ADRESS_INFO_TYP_EINKAUF = 					"EINKAUF_INFO";
//	public static String 					ADRESS_INFO_TYP_VERKAUF = 					"VERKAUF_INFO";
//	
//	//2011-02-19: infotyp fuer die FIBU
//	public static String 					ADRESS_INFO_TYP_FIBU = 						"FIBU_INFO";
	

}
