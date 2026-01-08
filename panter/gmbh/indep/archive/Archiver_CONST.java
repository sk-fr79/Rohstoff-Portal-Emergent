package panter.gmbh.indep.archive;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class Archiver_CONST  {

	public static enum MEDIENKENNER {
	    EMAIL(			"EMAIL",		"Archiveinträge für archivierte, verschickte eMails"),
	    PRINT(			"PRINT",		"Archiveinträge für archivierte, gedruckte Belege"),
	    UPLOAD(			"UPLOAD",		"Hochgeladene Dokumente"),
	    ORIGBELEG(		"ORIGBELEG",	"Originalbelege, müssen zusätzlich den Schalter ORIGINALBELEG tragen"),
	    ORIG_KOPIE_EMAIL("ORIG_KOPIE_EMAIL",	"Originalbelege mit Kopiestempel für eMail-Versand"),
	    INBOX_ANHANG(	"INBOX_ANHANG",	"Anhänge von eingehenden eMails"),
	    INBOX_ORIG(		"INBOX_ORIG",	"Original von eingehenden eMails"),
	    CAM_CAPTURE(	"CAM_CAPTURE",	"Kamera-Snapshots"),
	    WEIGERMELDUNG(	"WEIGERMELDUNG","Archivierte Weigermeldungen"),
	    IMPORT_ABZUG(	"IMPORT_ABZUG", "Foto von importierten Abzugsmeldungen"),
	    SCANNER_FILE(	"SCANNER_FILE",	"Scanner-Dateien"),
	    AUTOARCHIV(		"AUTOARCHIV","Archiviertes Dokument eMail oder Print"),
	    WIEGEKARTE(		"WIEGEKARTE","Archivierte Wiegekarte"),
	    WK_EINGANGSSCHEIN ("WK_EINGANGSSCHEIN","Archivierter Wiegekarten-Eingangsschein"),
	    WK_STORNO		 ("WK_STORNO","Stornierte Wiegekarte"),
	    EK_KONTRAKT_DRUCK("EK_KONTRAKT", "Archivierter EK Kontrakt-Druck"),
	    VK_KONTRAKT_DRUCK("VK_KONTRAKT", "Archivierter VK Kontrakt-Druck")
		,ROMAN_ABRECHBLATT("ROMAN_ABRECHBLATT",	"Abrechnungsblatt aus Rohstoffmanager")

	    ;

	    private String 		db_Wert = null;
	    private MyE2_String erKlaerung = null;
	    
	    
	    MEDIENKENNER(String DB_Wert, String Erklaerung) {
	    	this.db_Wert = DB_Wert;
	    	this.erKlaerung = new MyE2_String(Erklaerung);
	    }
	
	    public String get_DB_WERT() {
	    	return this.db_Wert;
	    }
	    public MyE2_String get_Erklaerung() {
	    	return this.erKlaerung;
	    }
	    
	}
	
	
//	public static String[][] MEDIENKENNER_SEL_ARRAY_MIT_LEER = {	{"*",""},
//		{"e-Mail-Anlagen",Archiver_CONST.MEDIENKENNER.EMAIL.get_DB_WERT()},
//		{"Druck-Dateien",Archiver_CONST.MEDIENKENNER.PRINT.get_DB_WERT()},
//		{"Hochgeladene Dateien",Archiver_CONST.MEDIENKENNER.UPLOAD.get_DB_WERT()},
//		{"e-Mail Eingang Anlagen",Archiver_CONST.MEDIENKENNER.INBOX_ANHANG.get_DB_WERT()},
//		{"e-Mail Eingang Originale",Archiver_CONST.MEDIENKENNER.INBOX_ORIG.get_DB_WERT()},
//		{"Kamera-Bilddatei",Archiver_CONST.MEDIENKENNER.CAM_CAPTURE.get_DB_WERT()},
//		{"Scanner-Datei",Archiver_CONST.MEDIENKENNER.SCANNER_FILE.get_DB_WERT()},
//		{"Weigermeldung",Archiver_CONST.MEDIENKENNER.WEIGERMELDUNG.get_DB_WERT()},
//		};
//
	
	/**
	 * fuer den selector in der medien-upload-box 
	 * @return
	 * @throws myException 
	 */
	public static String[][] get_MEDIENKENNER_SEL_ARRAY_MIT_LEER() throws myException {
		String[][] arrayRueck = new String[Archiver_CONST.MEDIENKENNER.values().length+1][2];
		
		arrayRueck[0][0] = "*";
		arrayRueck[0][1] = "";
		
		int i=1;
		for (Archiver_CONST.MEDIENKENNER sel: Archiver_CONST.MEDIENKENNER.values()) {
			arrayRueck[i][0]=sel.get_Erklaerung().CTrans();
			arrayRueck[i++][1]=sel.get_DB_WERT();
		}
		return arrayRueck;
	}
	

	/**
	 * fuer den selector in der medien-upload-box 
	 * @return
	 * @throws myException 
	 */
	public static String[][] get_PROGRAMMKENNER_SEL_ARRAY_MIT_LEER() throws myException {
		String[][] arrayRueck = new String[Archiver_CONST.PROGRAMMKENNER.values().length+1][2];
		
		arrayRueck[0][0] = "*";
		arrayRueck[0][1] = "";
		
		int i=1;
		for (Archiver_CONST.PROGRAMMKENNER sel: Archiver_CONST.PROGRAMMKENNER.values()) {
			arrayRueck[i][0]=sel.get_Erklaerung().CTrans();
			arrayRueck[i++][1]=sel.get_DB_WERT();
		}
		return arrayRueck;
	}
	

	
	

	/**
	 * verwendbare programmkenner fuer die JT_ARCHIVMEDIEN
	 * @author martin
	 *
	 */
	public static enum PROGRAMMKENNER {
		
		BENUTZER_SIGNATUR(      "BENUTZER_SIGNATUR",    "Unterschriftsdatei für Benutzer")
		,RECHNUNG_ANHANG(		"RECHNUNG_ANHANG",		"Rechnungsversand")
		,GUTSCHRIFT_ANHANG(		"GUTSCHRIFT_ANHANG",	"Gutschriftsversand")
		,RECH_GUT_ANHANG(		"RECH_GUT_ANHANG",		"Rech.und Gutsch.-Versand")
		//2015-06-16: neue programmkenner fuer die dateien, die fuer BAMS/Weigermeldungen benutzt werden
		,BAM_DRUCK_ANHANG(				"BAM_DRUCK_ANHANG",					"Anhang für BAM-Belege")
		,WEIGER_DRUCK_ANHANG(			"WEIGER_DRUCK_ANHANG",				"Anhang für WEIGER-Belege")
		,BAM_UND_WEIGER_DRUCK_ANHANG(	"BAM_UND_WEIGER_DRUCK_ANHANG",		"Anhang für BAM-/WEIGER-Belege")
		
		;

	    private String 		db_Wert = null;
	    private MyE2_String erKlaerung = null;
	    
	    
	    PROGRAMMKENNER(String DB_Wert, String Erklaerung) {
	    	this.db_Wert = DB_Wert;
	    	this.erKlaerung = new MyE2_String(Erklaerung);
	    }
	
	    public String get_DB_WERT() {
	    	return this.db_Wert;
	    }
	    public MyE2_String get_Erklaerung() {
	    	return this.erKlaerung;
	    }
	    
	    public static MyE2_String get_Erklaerung(String db_wert) {
	    	for (PROGRAMMKENNER kenn: PROGRAMMKENNER.values()) {
	    		if (kenn.get_DB_WERT().equals(db_wert)) {
	    			return kenn.get_Erklaerung();
	    		}
	    	}
//	    	
//	    	if (db_wert.equals(PROGRAMMKENNER.BENUTZER_SIGNATUR.db_Wert)) {
//	    		return PROGRAMMKENNER.BENUTZER_SIGNATUR.erKlaerung;
//	    	} else if (db_wert.equals(PROGRAMMKENNER.RECH_GUT_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.RECH_GUT_ANHANG.erKlaerung;
//	    	} else if (db_wert.equals(PROGRAMMKENNER.GUTSCHRIFT_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.GUTSCHRIFT_ANHANG.erKlaerung;
//	    	} else if (db_wert.equals(PROGRAMMKENNER.RECHNUNG_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.RECHNUNG_ANHANG.erKlaerung;
//	    		
//	    	} else if (db_wert.equals(PROGRAMMKENNER.RECHNUNG_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.RECHNUNG_ANHANG.erKlaerung;
//	    	}else if (db_wert.equals(PROGRAMMKENNER.RECHNUNG_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.RECHNUNG_ANHANG.erKlaerung;
//	    	} else if (db_wert.equals(PROGRAMMKENNER.RECHNUNG_ANHANG.db_Wert)) {
//	    		return PROGRAMMKENNER.RECHNUNG_ANHANG.erKlaerung;
//	    	}
	    	
	    	
	    	return new MyE2_String("");
	    }
	    
	    
	}
	
	

	public static String[][] DD_VKOPF_RG = 	{	
												{Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_DB_WERT()},
												{Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_DB_WERT()},	
												{Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_DB_WERT()},	
											};
	
	public static String[][] DD_VPOS_TPA_FUHRE = 	{	
												{Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_DB_WERT()},
												{Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_DB_WERT()},	
												{Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_DB_WERT()},	
											};
	
	public static String[][] DD_VPOS_TPA_FUHRE_ORT = 	{	
												{Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_DB_WERT()},
												{Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_DB_WERT()},	
												{Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_DB_WERT()},	
											};

	public static String[][] DD_FBAM = 	{	
												{Archiver_CONST.PROGRAMMKENNER.BAM_DRUCK_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.BAM_DRUCK_ANHANG.get_DB_WERT()},
												{Archiver_CONST.PROGRAMMKENNER.WEIGER_DRUCK_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.WEIGER_DRUCK_ANHANG.get_DB_WERT()},	
												{Archiver_CONST.PROGRAMMKENNER.BAM_UND_WEIGER_DRUCK_ANHANG.get_Erklaerung().CTrans(),Archiver_CONST.PROGRAMMKENNER.BAM_UND_WEIGER_DRUCK_ANHANG.get_DB_WERT()},	
											};
	
	
	
	/**
	 * dropdown-auswahlen fuer die upload-download-popup, die festlegen,
	 * welche auswahlwerte das feld programm_kenner hat.  
	 */
	public static enum PROGRAMMKENNER_SELECTVALUES {
		
		VKOPF_RG(			_DB.VKOPF_RG, Archiver_CONST.DD_VKOPF_RG),
		VPOS_TPA_FUHRE(		_DB.VPOS_TPA_FUHRE,Archiver_CONST.DD_VPOS_TPA_FUHRE),
		VPOS_TPA_FUHRE_ORT(	_DB.VPOS_TPA_FUHRE_ORT,Archiver_CONST.DD_VPOS_TPA_FUHRE_ORT),
		FBAM(				_DB.FBAM,Archiver_CONST.DD_FBAM)
		
		;
		private String     TABLENAME = null;	
		private String[][] Array4DropDown = null;
		
		PROGRAMMKENNER_SELECTVALUES(String tableName, String[][] array4DropDown) {
			this.TABLENAME = tableName;
			if (tableName.startsWith("JT_") || tableName.startsWith("JD_")) {
				this.TABLENAME=tableName.substring(3);
			}
			this.Array4DropDown = array4DropDown;
		}

		public String get_TABLENAME() {
			return TABLENAME;
		}

		public String[][] get_Array4DropDown() {
			return Array4DropDown;
		}
		
		public String[][] get_Array4DropDownWithEmtpyInFront() throws myException {
			return bibARR.add_array_inFront(this.Array4DropDown, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
		}
	}
	
	

	/**
	 * pro tabelle, wo es moeglich sein soll, kann die programmkenner-auswahl rausgesucht werden
	 * @return
	 * @throws myException 
	 */
	public static HashMap<String, String[][]> get_hm_DDVALUES_IN_UPLOAD() throws myException {
		HashMap<String, String[][]> hmRueck = new HashMap<String, String[][]>();
		
		for (Archiver_CONST.PROGRAMMKENNER_SELECTVALUES sel: Archiver_CONST.PROGRAMMKENNER_SELECTVALUES.values()) {
			hmRueck.put(sel.get_TABLENAME(),sel.get_Array4DropDownWithEmtpyInFront());
		}
		return hmRueck;
	}
	
	
	
}
