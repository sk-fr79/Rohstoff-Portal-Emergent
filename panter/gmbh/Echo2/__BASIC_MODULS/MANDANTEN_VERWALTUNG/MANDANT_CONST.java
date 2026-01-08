package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;


public class MANDANT_CONST 
{
	public static String HASHKEY_FULL_DAUGHTER_ZUSATZFELDER = "FULL_DAUGHTER_ZUSATZFELDER";
	public static String HASHKEY_FULL_DAUGHTER_STEUERVERMERKE = "FULL_DAUGHTER_STEUERVERMERKE";
	
	
	public static String HASHKEY_MASK_POPUP_ABZUEGE = "HASHKEY_MASK_POPUP_ABZUEGE";
	
	public static String HASHKEY_MASK_ZUSATZFELDER_YESNO = "HASHKEY_MASK_ZUSATZFELDER_YESNO";
	
	public static String HASHKEY_MASK_HILFSBUTTON_RECHNUNGSDATUMSKALKULATION = "HASHKEY_MASK_HILFSBUTTON_RECHNUNGSDATUMSKALKULATION";

	public static String HASHKEY_FULL_DAUGHTER_EMAIL_SCHABLONE= "FULL_DAUGHTER_EMAIL_SCHABLONE";
	
	public static String HASHKEY_MASK_MANDANT_DECISIONS= "HASHKEY_MASK_MANDANT_DECISIONS";

	public static String HASHKEY_MASK_MANDANT_CONFIGURATION= "HASHKEY_MASK_MANDANT_CONFIGURATION";
	/*
	 * erlaubte textwerte bei der definition eines mandanten-zusatzfeldes MANDANT_ZUSATZ_FELDNAMEN$FIELD_TYPE
	 */
	public static enum ALLOWED_VALUES_ZUSATZFELD_TYPEN {
		YES_NO,
		TEXT,
		NUMBER,
		DATE,
		UNDEF
	}
	

	/**
	 * definiert die "mailprofile", d.h. die kombination aus absender, email-betreff und email-Text fuer automatisch generierte emails
	 * @author martin
	 *
	 */
	public enum MAILPROFILE {
		
		 ALLGEMEIN("ALLGEMEIN","<Rechnungs/Gutschriftversand>")
		,RECHNUNG("RECHNUNG","Rechnungsversand")
		,GUTSCHRIFT("GUTSCHRIFT","Gutschriftsversand")
		,SYSTEMNACHRICHT("SYSTEMNACHRICHT","Systemnachrichten/Meldungsversand")
		,FIBUDOKUMENTE("FIBUDOKUMENTE","Versand von Finanzbuchhaltungs-Dokumenten")
		,FIBUMAHNUNGEN("FIBUMAHNUNGEN","Versand von Mahnungen")
		,FIBUVERVER("FIBUVERVER","Versand von Verrechnungsvereinbarungen")
		,FIBUKONTOKORRENT("FIBUKONTOKORRENT","Versand von Kontokorrentabrede")
		,FIBUZAHLUNGSAVIS("FIBUZAHLUNGSAVIS","Versand von Zahlungsavisen")
		,EK_VK_KONTRAKT("EK_VK_KONTRAKT", "EK und VK Kontrakten")
		,BELEG_GRENZUBERTRITT("BELEG_GRENZUBERTRITT", "Beleg inner-europäische Grenzübertritt")
		;
		
		private String DB_Value = null;
		private String DB_MaskText = null;
		
		MAILPROFILE(String db_Value, String db_MaskText) {
			this.DB_Value = db_Value;
			this.DB_MaskText = db_MaskText;
		}

		public String get_DB_Value() {
			return DB_Value;
		}

		public String get_DB_MaskText() {
			return DB_MaskText;
		}
		
		public static MAILPROFILE find_MailProfile(String value) {
			MAILPROFILE rueck = null;
			
			for (MAILPROFILE p: MAILPROFILE.values()) {
				if (p.DB_Value.equals(value)) {
					rueck = p;
					break;
				}
			}
			
			
			return rueck;
		}
		
	}
	
	public static String[][] get_MailProfileDropDown(boolean bAddEmptyInFront) {
		String[][] cRueck = new String[(MAILPROFILE.values().length+(bAddEmptyInFront?1:0))][2];
		
		int i=0;
		if (bAddEmptyInFront) {
			cRueck[i][0]="-";cRueck[i][1]="";
			i++;
		}
		for (MAILPROFILE mp: MAILPROFILE.values()) {
			cRueck[i][0]=new MyE2_String(mp.get_DB_MaskText()).CTrans(); 
			cRueck[i][1]=mp.get_DB_Value();
			i++;
		}
		return cRueck;
	}
	
	
	
}
