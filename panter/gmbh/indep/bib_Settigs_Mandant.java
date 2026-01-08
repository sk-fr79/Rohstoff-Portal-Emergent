package panter.gmbh.indep;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * verwaltet die zusatzfelder in den mandanten (einstellungsfelder usw)
 * @author martin
 *
 */
public class bib_Settigs_Mandant {
	
	
	public static String PREFIX_SESSION_STORED_SETTINGS = "PREFIX_SESSION_STORED_SETTINGS@@@@";
	
	/*
	 * neue version, die zuerst das vorhandensein des parameters prueft und dann einen wert nachschaut
	 */
	public static boolean  IS__Value(String cFeldName, String cValueWhenNotDefined, String cValueWhenNotSet)
	{
		String cValue = cValueWhenNotDefined;
		
		String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ " +
				" WHERE JD_MANDANT_ZUSATZ.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
		
		if (!cAnzahl.trim().equals("0"))
		{
			try
			{
				RECORD_MANDANT_ZUSATZ  recZusatz = new RECORD_MANDANT_ZUSATZ("ID_MANDANT="+bibALL.get_ID_MANDANT()+
						" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
				
				
				if (recZusatz!=null)
				{
					cValue = recZusatz.get_TEXT_VALUE_cUF_NN("");
					if (S.isEmpty(cValue))
					{
						cValue = cValueWhenNotSet;
					}
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
			}

		}
		
		return cValue.equals("Y");
	}

	

	
	
	
	/**
	 * Liest den Settingswert aus der JD_MANDANT_ZUSATZ.
	 * falls dort der Wert nicht definiert ist, dann wird in JD_MANDANT_ZUSATZ_FELDNAME der Defaultwert gelesen
	 * falls dort keiner gesetzt ist, wird cValueWhenNoDefault zurückgegeben.
	 * 
	 * 
	 * NB: 	Jetzt doch mit Records realisiert, da es Einträge in der Tabelle JD_MANDANT_ZUSATZ_FELDNAMEN gibt, die doppelt (mit Mandanten) sind.
	 * 		Dann ist die zuordnung nicht mehr so einfach. Diese Lösung sollte beides lösen.
	 * 
	 * @author manfred
	 * @date   23.10.2015
	 *
	 * @param cFeldName
	 * @param cValueWhenNoDefault
	 * @return
	 */
	public static String  get__Value(String cFeldName,  String cValueWhenNoDefault)
	{
		String 								cValue 			= cValueWhenNoDefault;
		String 								cValueSet 		= null;
		
		
		if (cFeldName != null){
			SettingValues settings = new SettingValues(cFeldName);
			cValueSet = settings.getTextValueORDefault();
			
			
			if (cValueSet != null) {
				cValue = cValueSet;
			} else {
				cValue = cValueWhenNoDefault;
			}
		}
		
		return cValue;
	}
	
	
	
	/**
	 * Liest den Settingswert aus der JD_MANDANT_ZUSATZ.
	 * falls dort der Wert nicht definiert ist, dann wird in JD_MANDANT_ZUSATZ_FELDNAME der Defaultwert gelesen
	 * falls dort keiner gesetzt ist, wird cValueWhenNoDefault zurückgegeben.
	 * 
	 * 
	 * NB: 	Jetzt doch mit Records realisiert, da es Einträge in der Tabelle JD_MANDANT_ZUSATZ_FELDNAMEN gibt, die doppelt (mit Mandanten) sind.
	 * 		Dann ist die zuordnung nicht mehr so einfach. Diese Lösung sollte beides lösen.
	 * 
	 * @author manfred
	 * @date   23.10.2015
	 *
	 * @param cFeldName
	 * @param ValueWhenNoDefault
	 * @return
	 */
	public static Long  get__Value(String cFeldName,  Long ValueWhenNoDefault)
	{
		Long 								Value 			= ValueWhenNoDefault;
		Long 								ValueSet 		= null;
		
		
		if (cFeldName != null){
			SettingValues settings = new SettingValues(cFeldName);
			ValueSet = settings.getLongValueORDefault();
			
			
			if (ValueSet != null) {
				Value = ValueSet;
			} else {
				Value = ValueWhenNoDefault;
			}
		}
		
		return Value;
	}
	
	
	
	
	
	/**
	 * gibt den TEXT-Wert des Settings zurück oder 
	 *   cValueWhenNotDefined 	der Wert ist zwar in den Settings JD_MANDANT_ZUSATZ_FELDNAMEN definiert, aber für den Mandanten ist kein Spezieller Eintrag eingetragen.
	 *   cValueWhenNotSet     	Es gibt einen Eintrag für den Mandanten in JD_MANDANT_ZUSATZ, aber der Textwert ist leer.
	 * 
	 * @author manfred
	 * @date   23.10.2015
	 *
	 * @param cFeldName
	 * @param cValueWhenNotDefined
	 * @param cValueWhenNotSet
	 * @return
	 */
	public static String  get__Value(String cFeldName,  String cValueWhenNotDefined, String cValueWhenNotSet)
	{
		String 								cValue 			= cValueWhenNotDefined;
		String 								cValueSet 		= null;
		String								cValueDefault   = null;
		
		if (cFeldName != null){
			SettingValues settings = new SettingValues(cFeldName);
			cValueSet = settings.getTextValue();
			
			
			if (cValueSet != null) {
				cValue = cValueSet;
			} else if ( !settings.bZusatzwertIstGesetzt() ){
				cValue = cValueWhenNotDefined;
			} else {
				cValue = cValueWhenNotSet;
			}
		}
		
		return cValue;
	}

	
	

	
	public static boolean get_UNTERDRUECKE_LIEFERBED_ABFRAGE_IN_FUHREN_LISTE() {
		return bib_Settigs_Mandant.get__Value("UNTERDRUECKE_LIEFERBED_ABFRAGE_IN_FUHREN_LISTE", "N").equals("Y");
	}
	

	public static boolean get_WEIGERMELDUNG_IN_KUNDENARCHIV() {
		return bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WEIGERMELDUNG_IN_KUNDENARCHIV.toString(), "N").equals("Y");
	}
	
		
	
	/**
	 * soll die prüfung nach überschrittener Kreditausfallversicherung unterdrueckt werden
	 * @return
	 */
	public static boolean get_SUPPRESS_KREDITSTATE_CHECK_BEFORE_PRINT_PAPERS() {
		String cKEY = bib_Settigs_Mandant.PREFIX_SESSION_STORED_SETTINGS+
					(ENUM_MANDANT_ZUSATZ_FELDNAMEN.SUPPRESS_CHECKING_KREDITSTATUS_BEFORE_PRINT_PAPERS.toString());
		String cWERT_Y_N = (String)bibALL.getSessionValue(cKEY);

		if (S.isEmpty(cWERT_Y_N)) {
			cWERT_Y_N = bib_Settigs_Mandant.get__Value(
					ENUM_MANDANT_ZUSATZ_FELDNAMEN.SUPPRESS_CHECKING_KREDITSTATUS_BEFORE_PRINT_PAPERS.toString(), "N","N");
			bibALL.setSessionValue(cKEY, cWERT_Y_N);
		}
		
		return cWERT_Y_N.equals("Y");
	}
	
	
	/**
	 * allgemeine store-methode mit zwischenspeicherung in der session
	 */
	public static boolean get_StoredValue(ENUM_MANDANT_ZUSATZ_FELDNAMEN key) {
		String cKEY = bib_Settigs_Mandant.PREFIX_SESSION_STORED_SETTINGS+key.toString();
		String cWERT_Y_N = (String)bibALL.getSessionValue(cKEY);

		if (S.isEmpty(cWERT_Y_N)) {
			cWERT_Y_N = bib_Settigs_Mandant.get__Value(key.toString(), "N","N");
			bibALL.setSessionValue(cKEY, cWERT_Y_N);
		}
		return cWERT_Y_N.equals("Y");
	}
	

	/**
	 * loescht alle in der session gespeicherten keys der enum MandantZusatzNamen
	 */
	public static void clear_mandantensettings_in_session() {
		for (ENUM_MANDANT_ZUSATZ_FELDNAMEN key: ENUM_MANDANT_ZUSATZ_FELDNAMEN.values()) {
			bibALL.setSessionValue(bib_Settigs_Mandant.PREFIX_SESSION_STORED_SETTINGS+key.toString(), null);
		}
	}
	
	
	
	/**
	 * true, wenn der Schalter auf 'Y' explizit oder per default-WErt gesetzt ist, false sonst
	 * @return
	 */
	public static boolean get_Waage_IstDruckeWiegekarteWennNurHandwaegung(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WAAGE_DRUCK_WENN_NUR_HANDWAEGUNG.toString(), "N").equals("Y");
		return bRet;
	}
	
	public static boolean get_Waage_IstSPERRUNGBeiMindestlastUnterschreitung(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WAAGE_SPERRE_BEI_MINDESTLAST_UNTERSCHREITUNG.toString(), "Y").equals("Y");
		return bRet;
	}
	
	public static boolean get_Waage_IstWARNUNGBeiMindestlastUnterschreitungWE(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WAAGE_WARNUNG_BEI_MINDESTLAST_WE_UNTERSCHREITUNG.toString(), "Y").equals("Y");
		return bRet;
	}
	
	public static boolean get_Wiegekarte_ArchivAnFuhreAnbinden(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WIEGEKARTE_ARCHIV_TO_FUHRE.toString(), "Y").equals("Y");
		return bRet;
	}

	public static boolean get_Wiegekarte_ArchivAnAdresseAnbinden(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WIEGEKARTE_ARCHIV_TO_ADRESSE.toString(), "Y").equals("Y");
		return bRet;
	}

	public static boolean get_Workflow_Send_Mail_on_Status_Change(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WORKFLOW_SEND_MAIL_ON_STATUS_CHANGE.toString(), "N").equals("Y");
		return bRet;
	}

	public static boolean get_Nachricht_Allow_Send_Additional_Email(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.NACHRICHT_ALLOW_SEND_ADDITIONAL_EMAIL.toString(), "N").equals("Y");
		return bRet;
	}
	
	public static boolean get_Workflow_Nachricht_as_Sofortnachricht() {
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.WORKFLOW_SEND_SOFORTNACHRICHT.toString(), "N").equals("Y");
		return bRet;
	}
	
	public static boolean is_DIRECTACCESS_REPORT_LIST_IS_NEW_MODULE(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.DIRECTACCESS_REPORT_LIST_IS_NEW_MODULE.toString(), "N").equals("Y");
		return bRet;
	}

	public static boolean get_Lager_Allow_Buchung_Buchhalterisch(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.LAGER_ALLOW_BUCHUNG_BUCHHALTERISCH.toString(), "N").equals("Y");
		return bRet;
	}
	
	/**
	 * 2016-02-16: EU-Vertraege pruefen
	 * @return
	 */
	public static boolean get_EU_VERTRAG_PRUEFUNG_AN_JURISTISCHER_ADRESSE(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_JUR_ORT.toString(), "N").equals("Y");
		return bRet;
	}

	public static boolean get_EU_VERTRAG_PRUEFUNG_AN_GEOGRAFISCHER_ADRESSE(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_GEO_ORT.toString(), "N").equals("Y");
		return bRet;
	}
	
	public static boolean get_VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG.toString(), "N").equals("Y");
		return bRet;
	}
	//ende vertraege pruefen
	
	public static boolean get_PRUEFE_EU_VERTRAEGE_BEI_PRIVATADRESSE(){
		boolean bRet = false;
		bRet = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.PRUEFE_EU_VERTRAEGE_BEI_PRIVATADRESSE.toString(), "N").equals("Y");
		return bRet;
	}
	
	/**
	 * Klasse die alle Settings-Werte beinhaltet, die gesetzt sind
	 * @author manfred
	 * @date 23.10.2015
	 *
	 */
	private static class SettingValues{
		String sFieldname 			= null;
		String sMandant   			= null;

		RECORD_MANDANT_ZUSATZ  				recZusatz 		= null;
		RECORD_MANDANT_ZUSATZ_FELDNAMEN 	recFeld 		= null;
		
		public SettingValues(String sFieldname) {
			recZusatz = load_value(sFieldname);
			recFeld   = load_value(sFieldname,recZusatz);
		}
		
		
		/**
		 * lädt den spezifischen Zusatztext des Mandanten und gibt den Record zurück
		 * 
		 * @author manfred
		 * @date   23.10.2015
		 *
		 * @param sFeldname
		 * @return
		 */
		private  RECORD_MANDANT_ZUSATZ load_value( String sFeldname) {
			RECORD_MANDANT_ZUSATZ recEntry 		= null;
			try {

				// schauen, ob es den Eintrag beim Mandanten gibt
				recEntry = new RECORD_MANDANT_ZUSATZ(	" ID_MANDANT= " + bibALL.get_ID_MANDANT()+
						 								" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(sFeldname)+")");
				
			} catch (myException e) {
				recEntry = null;
			}
			return recEntry;
		}

		
		/**
		 * Lädt den Zusatztext-Record unabhängig vom Mandanten. 
		 * Falls kein recZusatz angegeben wird und es mehrere Einträge sind, wird nur der erste zurückgegeben.
		 * 
		 * Falls recZusazt angegeben wird, wird genau der zugehörige Record zurückgegeben
		 * 
		 * @author manfred
		 * @date   23.10.2015
		 *
		 * @param sFeldname
		 * @param recZusatz
		 * @return
		 */
		private  RECORD_MANDANT_ZUSATZ_FELDNAMEN load_value(String sFeldname, RECORD_MANDANT_ZUSATZ recZusatz ){
			RECLIST_MANDANT_ZUSATZ_FELDNAMEN reclistFeld = null;
			RECORD_MANDANT_ZUSATZ_FELDNAMEN  recFeld = null;
			// dann das Feld auch lesen
			try {
				if (recZusatz != null){
					recFeld = recZusatz.get_UP_RECORD_MANDANT_ZUSATZ_FELDNAMEN_id_mandant_zusatz_feldnamen();
				} else {
					reclistFeld = new RECLIST_MANDANT_ZUSATZ_FELDNAMEN(" UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(sFeldname)+")", "ID_MANDANT");
					if (reclistFeld.size() > 0){
						recFeld = reclistFeld.get(0);
					}
				}
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return recFeld;
		}
		

		/**
		 * gibt an, ob der Zusatzwert beim Mandanten gesetzt ist
		 * 
		 * @author manfred
		 * @date   23.10.2015
		 *
		 * @return
		 */
		public boolean bZusatzwertIstGesetzt (){
			return recZusatz != null;
		}
		
		
		
		
		
		
		public String getTextValue(){
			String sRet = null;
			if (recZusatz != null){
				try {
					sRet = recZusatz.get_TEXT_VALUE_cUF();
				} catch (myException e) {
					sRet = null;
				}
			}
			return sRet;
		}
		
		public String getTextDefaultValue(){
			String sRet = null;
			if (recFeld != null){
				try {
					sRet = recFeld.get_DEFAULT_TEXT_VALUE_cUF();
				} catch (myException e) {
					sRet = null;
				}
			}
			return sRet;
		}
		
		
		public String getYNValue(){
			String sRet = null;
			if (recZusatz != null){
				try {
					sRet = recZusatz.get_YES_NO_VALUE_cUF();
				} catch (myException e) {
					sRet = null;
				}
			}
			return sRet;
		}
		
		public String getYNDefaultValue(){
			String sRet = null;
			if (recFeld != null){
				try {
					sRet = recFeld.get_DEFAULT_YES_NO_VALUE_cUF();
				} catch (myException e) {
					sRet = null;
				}
			}
			return sRet;
		}
		

		public Long getLongValue(){
			Long lRet = null;
			if (recZusatz != null){
				try {
					lRet = recZusatz.get_LONG_VALUE_lValue(null);
				} catch (myException e) {
					lRet = null;
				}
			}
			return lRet;
		}
		
		public Long getLongDefaultValue(){
			Long lRet = null;
			if (recFeld != null){
				try {
					lRet = recFeld.get_DEFAULT_LONG_VALUE_lValue(null);
				} catch (myException e) {
					lRet = null;
				}
			}
			return lRet;
		}

		

		public BigDecimal getFloatValue(){
			BigDecimal lRet = null;
			if (recZusatz != null){
				try {
					lRet = recZusatz.get_FLOAT_VALUE_bdValue(null);
				} catch (myException e) {
					lRet = null;
				}
			}
			return lRet;
		}
		
		public BigDecimal getFloatDefaultValue(){
			BigDecimal lRet = null;
			if (recFeld != null){
				try {
					lRet = recFeld.get_DEFAULT_FLOAT_VALUE_bdValue(null);
				} catch (myException e) {
					lRet = null;
				}
			}
			return lRet;
		}		
		
		
		
		
		public String getTextValueORDefault(){
			return ( getTextValue()!= null ? getTextValue(): getTextDefaultValue() );
		}

		
		
		public String getYesNoValueORDefault(){
			return ( getYNValue() != null ? getYNValue(): getYNDefaultValue() );
		}

		
		
		public Long getLongValueORDefault(){
			return ( getLongValue() != null ? getLongValue() : getLongDefaultValue() );
		}
		

		public BigDecimal getFloatValueORDefault(){
			return ( getFloatValue() != null ? getFloatValue() : getFloatDefaultValue() );
		}

		
		
		
	}
	
	
	
	
	
	
	
//	public static String  get__Value(String cFeldName,  String cValueWhenNotDefined, String cValueWhenNotSet)
//	{
//		String cValue 			= cValueWhenNotDefined;
//	
//		String[][] cWerte = bibDB.EinzelAbfrageInArray(
//			" SELECT Z.TEXT_VALUE, ZF.DEFAULT_TEXT_VALUE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN ZF " +
//			" LEFT OUTER JOIN  "+bibE2.cTO()+".JD_MANDANT_ZUSATZ  Z " +
//			"		ON ZF.ID_MANDANT = Z.ID_MANDANT " +
//			"		AND ZF.ID_MANDANT_ZUSATZ_FELDNAMEN = Z.ID_MANDANT_ZUSATZ_FELDNAMEN " +
//			" WHERE ZF.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(ZF.FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")"
//			,(String)null);
//		
//		
//		if(cWerte.length > 0){
//			if (S.isEmpty(cWerte[0][0])) {
//				cValue = cValueWhenNotSet;
//			} else {
//				cValue 		= cWerte[0][0];
//			}
//		}
//		
//		return cValue;
//	}
//
	
	
	
//	/**
//	 * Ermittlung des Long-wertes der Mandanten-Settings
//	 * @param cFeldName
//	 * @param ValueWhenNotDefined
//	 * @param cValueWhenNotSet
//	 * @return
//	 */
//	public static Long get__Value(String FeldName,  Long ValueWhenNotDefined ){
//		Long lValue = ValueWhenNotDefined;
//		
//		String[][] cWerte = bibDB.EinzelAbfrageInArray(
//				
//				" SELECT Z.LONG_VALUE, ZF.DEFAULT_LONG_VALUE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN ZF " +
//				" LEFT OUTER JOIN  "+bibE2.cTO()+".JD_MANDANT_ZUSATZ  Z " +
//				"		ON ZF.ID_MANDANT = Z.ID_MANDANT " +
//				"		AND ZF.ID_MANDANT_ZUSATZ_FELDNAMEN = Z.ID_MANDANT_ZUSATZ_FELDNAMEN " +
//				" WHERE ZF.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(ZF.FIELDNAME)=UPPER("+bibALL.MakeSql(FeldName)+")"
//				,(String)null);
//			
//		if(cWerte.length > 0){
//			try {
//					if (!S.isEmpty(cWerte[0][0])) {
//						lValue = Long.parseLong( cWerte[0][0] );
//					} else if (!S.isEmpty(cWerte[0][1])) {
//						lValue = Long.parseLong( cWerte[0][1] );
//					}
//			} catch (NumberFormatException e) {
//				//
//			}
//		}
//		
//		
//		return lValue;
//	}
	
//	/**
//	 * @deprecated
//	 * @param cFeldName
//	 * @param cValueWhenNotDefined
//	 * @param cValueWhenNotSet
//	 * @return
//	 */
//	public static String  get___Value(String cFeldName, String cValueWhenNotDefined, String cValueWhenNotSet)
//	{
//		String cValue = cValueWhenNotDefined;
//		
//		try
//		{
//			RECORD_MANDANT_ZUSATZ  recZusatz = new RECORD_MANDANT_ZUSATZ("ID_MANDANT="+bibALL.get_ID_MANDANT()+
//					" AND UPPER(FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")");
//			
//			
//			if (recZusatz!=null)
//			{
//				cValue = recZusatz.get_TEXT_VALUE_cUF_NN("");
//				if (S.isEmpty(cValue))
//				{
//					cValue = cValueWhenNotSet;
//				}
//			}
//		}
//		catch (myException e)
//		{
//			e.printStackTrace();
//		}
//
//		return cValue;
//	}
	

	
//	/**
//	 * Liest den Wert aus der DB.
//	 * Falls der Wert nicht explizit beim Mandanten gesetzt ist aber es gibt einen Defaultwert, wird der Default genommen
//	 * sonst der alternative übergebenen Wert cValueWhenNoDefault.
//	 * Dabei ist es egal ob der Wert überhaupt definiert wurde in der JD_MANDANT_ZUSATZ_FELDNAMEN.
//	 * 
//	 * Schneller da ohne RECORDS und mit JOIN 
//	 * 
//	 * @author manfred
//	 * @date   12.04.2013
//	 * @param cFeldName
//	 * @param cValueWhenNoDefault
//	 * @return
//	 */
//	public static String  get___Value(String cFeldName,  String cValueWhenNoDefault)
//	{
//		String cValue 			= cValueWhenNoDefault;
//		String cValueSet 		= null;
//		String cValueDefault 	= null;
//	
//		String[][] cWerte = bibDB.EinzelAbfrageInArray(
//			" SELECT Z.TEXT_VALUE, ZF.DEFAULT_TEXT_VALUE FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN ZF " +
//			" LEFT OUTER JOIN  "+bibE2.cTO()+".JD_MANDANT_ZUSATZ  Z " +
//			"		ON ZF.ID_MANDANT = Z.ID_MANDANT " +
//			"		AND ZF.ID_MANDANT_ZUSATZ_FELDNAMEN = Z.ID_MANDANT_ZUSATZ_FELDNAMEN " +
//			" WHERE ZF.ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  UPPER(ZF.FIELDNAME)=UPPER("+bibALL.MakeSql(cFeldName)+")"
//			,(String)null);
//		
//		
//		if(cWerte.length > 0){
//			cValueSet 		= cWerte[0][0];
//			cValueDefault 	= cWerte[0][1];
//			
//			if (cValueSet != null) {
//				cValue = cValueSet;
//			} else if (cValueDefault != null){
//				cValue = cValueDefault;
//			}
//		}
//		
//		return cValue;
//	}
//	

	
	
	
}
