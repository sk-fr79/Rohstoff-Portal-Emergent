package panter.gmbh.indep;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.jaxen.NamedAccessNavigator;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_Color_FromHexString;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.RECLIST_SCANNER_SETTINGS_SPECIAL;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_LAGER;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_SETTINGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_COLUMNS_TO_CALC;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIELD_RULE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MASCHINENTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_COLUMNS_TO_CALC;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINENTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.objectstore.SingularSettings.ENUM_SINGULAR_SETTINGS;
import panter.gmbh.indep.dataTools.HASHMAP_MyMetaFieldDef;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSKLASSE_DEF_EXT;
import rohstoff.utils.bibROHSTOFF;


//klasse mit statische set- und get-methoden fuer die aktuelle session
public class bibSES 
{
//	private static String SES_KEY____ALLOWED_MENUES_4_USER = 					"____ALLOWED_MENUES_4_USER";
//	private static String SES_KEY____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN = 	"____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN";
//	private static String SES_KEY____BORDER_COLOR_4_UMA_BUTTON = 				"____BORDER_COLOR_4_UMA_BUTTON";
//	private static String SES_KEY____PRAEFIX_SONDERLAGER = 						"___SONDERLAGER_";
//	
//	private static String SES_KEY____EIGENE_LKW_RECLIST = 						"____EIGENE_LKW_RECLIST";
//	private static String SES_KEY____SUMMATIONSSPALTEN =						"____SUMMATIONSSPALTEN";
//	
//	public static String SES_KEY____UUID_HASHMAP_4_FINGERPRINTS =				"____UUID_HASHMAP_4_FINGERPRINTS";
//	
////	public static String SES_KEY____SCANNERPROFILE_4_USER = 					"____SCANNERPROFILE_4_USER";
//	
//	public static String SES_KEY_HASHMAPS_METAFIELDDEFS = 						"____KEY_HASHMAPS_METAFIELDDEFS";
//	
//	public static String SES_KEY_FIELD_RULES = 									"____KEY_FIELD_RULES";
//	
//	//  Timestamp der Session, an dem die Sofortnachrichten das nächste mal hochkommen sollen.
//	public static String SES_KEY____MESSAGE_POPUP_TIMESTAMP =					"____MESSAGE_POPUP_TIMESTAMP"; 							
	

	//
	//  timestamp, des nächsten Zeitpunktes ab wann die Sofortnachrichten angezeugt werden sollen.
	//
	public static void set_Messages_Popup_TimeStamp (Calendar timestamp){
		bibALL.setSessionValue(bibSES_keys4save.SES_KEY____MESSAGE_POPUP_TIMESTAMP.s(), timestamp);
	}
	
	public static Calendar get_Messages_Popup_TimeStamp (){
		return (Calendar)bibALL.getSessionValue(bibSES_keys4save.SES_KEY____MESSAGE_POPUP_TIMESTAMP.s()); 
	}
	
	
	
	public static void set_ALLOWED_MENUES_4_USER(HashMap<String, String>  hmAllowedMenues)
	{
		bibALL.setSessionValue(bibSES_keys4save.SES_KEY____ALLOWED_MENUES_4_USER.s(), hmAllowedMenues);
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> get_ALLOWED_MENUES_4_USER()
	{
		if (bibALL.getSessionValue(bibSES_keys4save.SES_KEY____ALLOWED_MENUES_4_USER.s())==null)
		{
			bibSES.set_ALLOWED_MENUES_4_USER(new HashMap<String, String>());
		}
		
		return (HashMap<String, String>) bibALL.getSessionValue(bibSES_keys4save.SES_KEY____ALLOWED_MENUES_4_USER.s());
	}
	

	
	public static void set_FS_ModulContainer_MASK(FS_ModulContainer_MASK  oFirmenMask)
	{
		bibALL.setSessionValue(bibSES_keys4save.SES_KEY____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN.s(), oFirmenMask);
	}

	public static FS_ModulContainer_MASK get_FS_ModulContainer_MASK() throws myException
	{
		if (bibALL.getSessionValue(bibSES_keys4save.SES_KEY____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN.s())==null)
		{
			bibSES.set_FS_ModulContainer_MASK(new FS_ModulContainer_MASK());
		}
		
		return (FS_ModulContainer_MASK) bibALL.getSessionValue(bibSES_keys4save.SES_KEY____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN.s());
	}
	


	
	/**
	 * einmal pro session wird die gewuenschte uma-button-umrandungsfarbe gewaehlt
	 * @return
	 */
	public static Color get_Color4_Border_UMA_Button() {
		Color oColBorder = new Color(0,0,0);  //schwarz ist standard

		if (bibSES.getSessionValue(bibSES_keys4save.SES_KEY____BORDER_COLOR_4_UMA_BUTTON)==null) {
			
			String cFarbeRahmen = bib_Settigs_Mandant.get__Value("FARBE_UMA_BUTTON_IN_LISTE", "","").trim();
			if (S.isFull(cFarbeRahmen)) {
				if (cFarbeRahmen.startsWith("#")) {
					Integer iWert = null;
					try {
						iWert = Integer.parseInt(cFarbeRahmen.substring(1),16);
						if (iWert!=null) {
							oColBorder = new E2_Color_FromHexString(cFarbeRahmen);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY____BORDER_COLOR_4_UMA_BUTTON, oColBorder);
		}
		return (Color) bibSES.getSessionValue(bibSES_keys4save.SES_KEY____BORDER_COLOR_4_UMA_BUTTON);
	}
	
	
	
	
	
	/**
	 * Ermitteln der Lager-Adress-ID der Sonderlaeger
	 * @author manfred
	 * @date   17.05.2013
	 * @param lagerkennung
	 * @return
	 */
	public static String getID_AdresseLAGER( String lagerkennung){
		
		String cRueck = null;
		String sessionvariable = bibSES_keys4save.SES_KEY____PRAEFIX_SONDERLAGER + lagerkennung;
		
		if (bibALL.getSessionValue(sessionvariable) != null)
		{
			cRueck = ((String) bibALL.getSessionValue(sessionvariable)).trim();
		}
		
		if (bibALL.isEmpty(cRueck))
		{
			try {
				RECORD_ADRESSE oRec = new RECORD_ADRESSE(" trim(SONDERLAGER) = trim('" + lagerkennung.toUpperCase().trim() + "') ");
				cRueck = oRec.get_ID_ADRESSE_cUF();
				// die sessionvariable setzen
				bibALL.setSessionValue(sessionvariable, cRueck);
				
			} catch (myException e) {
				// im Fehlerfall wird null zurück gegeben
				e.printStackTrace();
			}
		} 
		
		return cRueck;
	}
	
	
	/**
	 * Ermittelt die LageradressID anhand der ENUM
	 * @author manfred
	 * @date 26.10.2017
	 *
	 * @param enumSonderlager
	 * @return
	 */
	public static String get_ID_AdresseLAGER (ENUM_SONDERLAGER enumSonderlager) {
		return getID_AdresseLAGER(enumSonderlager.db_val());
	}
	
	
	/**
	 * Ermittelt die LageradressID anhand der ENUM
	 * @author manfred
	 * @date 26.10.2017
	 *
	 * @param enumSonderlager
	 * @return
	 */
	public static Long get_ID_AdresseLAGER_longValue(ENUM_SONDERLAGER enumSonderlager){
		return Long.parseLong(get_ID_AdresseLAGER(enumSonderlager));
	}
	
	
	 
	
	public static String get_ID_ADRESSE_LAGER_STRECKE(){
		return  getID_AdresseLAGER(ENUM_SONDERLAGER.STRECKE.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_STRECKE_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.STRECKE.db_val()));
	}
	
	
	public static String get_ID_ADRESSE_LAGER_MENGENKORREKTUR(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.MK.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_MENGENKORREKTUR_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.MK.db_val()) ) ;
	}
	
	
	public static String get_ID_ADRESSE_LAGER_SORTENWECHSEL(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.SW.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_SORTENWECHSEL_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.SW.db_val()));
	}

	
	public static String get_ID_ADRESSE_LAGER_KORREKTUR_HAND(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.KH.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_KORREKTUR_HAND_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.KH.db_val()));
	}
	
	public static String get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.UH.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.UH.db_val()));
	}
	
	public static String get_ID_ADRESSE_LAGER_LAGERLAGER(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.LL.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_LAGERLAGER_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.LL.db_val()));
	}
	
	public static String get_ID_ADRESSE_LAGER_MIXED(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.MI.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_MIXED_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.MI.db_val()));
	}
	
	public static String get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.ZWE.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.ZWE.db_val()));
	}	

	public static String get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA(){
		return getID_AdresseLAGER(ENUM_SONDERLAGER.ZWA.db_val());
	}
	public static Long get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA_longValue(){
		return Long.parseLong(getID_AdresseLAGER(ENUM_SONDERLAGER.ZWA.db_val()));
	}	

	
	
	/**
	 * 
	 * @return ein einmal pro session gebautes RECORD_USER-Object
	 * @throws myException
	 */
	public static RECORD_USER get_RECORD_USER() throws myException {
		
		RECORD_USER recUSER = (RECORD_USER)bibALL.getSessionValue(bibCONST.HASHKEY_SESSION_USER_RECORD);

		if (recUSER==null) {
			recUSER = new RECORD_USER(bibALL.get_RECORD_USER().get_ID_USER_lValue(null));
			bibALL.setSessionValue(bibCONST.HASHKEY_SESSION_USER_RECORD, recUSER);
		}
		
		return recUSER;
	}
	

	
	/**
	 * 
	 * @return ein einmal pro session gebautes RECORD_MANDANT-Object
	 * @throws myException
	 */
	public static RECORD_MANDANT get_RECORD_MANDANT() throws myException {
		
		RECORD_MANDANT recMANDANT = (RECORD_MANDANT)bibALL.getSessionValue(bibCONST.HASHKEY_SESSION_MANDANT_RECORD);

		if (recMANDANT==null) {
			recMANDANT = new RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_lValue(null));
			bibALL.setSessionValue(bibCONST.HASHKEY_SESSION_MANDANT_RECORD, recMANDANT);
		}
		
		return recMANDANT;
	}
	

	
	//speicherpaar um eine RECLIST der eigenen LKWs in der SESSION ablegen zu koennen
	public static void set_EIGENE_LKW(RECLIST_MASCHINEN  rlEIGENE_LKW)
	{
		bibSES.setSessionValue(bibSES_keys4save.SES_KEY____EIGENE_LKW_RECLIST, rlEIGENE_LKW);
	}

	public static RECLIST_MASCHINEN get_EIGENE_LKW(boolean buildNew) throws myException
	{
		RECLIST_MASCHINEN rlRueck = (RECLIST_MASCHINEN) bibSES.getSessionValue(bibSES_keys4save.SES_KEY____EIGENE_LKW_RECLIST);
		
		if (rlRueck==null || buildNew) {
			rlRueck = new RECLIST_MASCHINEN();

			String cQuery = "SELECT * FROM "+bibE2.cTO()+"."+_DB.MASCHINENTYP+" WHERE "+
									bibDB_SYNTAX.GENERATE_YES_NO_WHERE(_DB.MASCHINENTYP, _DB.MASCHINENTYP$IST_LKW, "Y");
	
			RECLIST_MASCHINENTYP  rlTYP = new RECLIST_MASCHINENTYP(cQuery);
			
			if (rlTYP.get_vKeyValues().size()>0) {
				for (RECORD_MASCHINENTYP  mtyp: rlTYP.values()) {
					rlRueck.putAll(mtyp.get_DOWN_RECORD_LIST_MASCHINEN_id_maschinentyp());
				}
			}
			bibSES.set_EIGENE_LKW(rlRueck);
		}
		
		return rlRueck;
	}

	
	
	//2014-08-04: speicherpaar zum hinterlegen des Laenderkuerzels eines mandante
	//private static String SES_KEY____LAENDERCODE_MANDANT = 						"____LAENDERCODE_MANDANT";

	public static void set_LAENDERCODE_MANDANT(String cLAND_MANDANT) {
		bibSES.setSessionValue(bibSES_keys4save.SES_KEY____LAENDERCODE_MANDANT, cLAND_MANDANT);
	}

	public static String get_LAENDERCODE_MANDANT() throws myException {
		String cLAND_MANDANT = (String) bibSES.getSessionValue(bibSES_keys4save.SES_KEY____LAENDERCODE_MANDANT);
		
		if (S.isEmpty(cLAND_MANDANT)) {
			
			cLAND_MANDANT = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0")).get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN("");
			bibSES.set_LAENDERCODE_MANDANT(cLAND_MANDANT);
		}
		return cLAND_MANDANT;
	}

	
	
	
	
	public static void set_EIGENE_WAAGE_LAGER_ADRESSEN(Vector<String>  vEigeneWaageLagerAdressen)
	{
		bibSES.setSessionValue(bibSES_keys4save.SES_KEY_____EIGENE_WAAGE_LAGER_ADRESSEN, vEigeneWaageLagerAdressen);
	}

	/**
	 * Erzeugt einen Vector mit AdressIDs der für die Verwiegung relevanten Lagerorte 
	 * @author manfred
	 * @date 10.10.2017
	 *
	 * @param buildNew
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get__EIGENE_WAAGE_LAGER_ADRESSEN(boolean buildNew) throws myException
	{
		Vector<String> vRueck = (Vector<String>) bibSES.getSessionValue(bibSES_keys4save.SES_KEY_____EIGENE_WAAGE_LAGER_ADRESSEN);
		
		if (vRueck==null || buildNew) {
			vRueck = new Vector<>();

			String cQuery = " SELECT id_adresse_lager FROM "+ WAAGE_SETTINGS.fullTabName() + " UNION " + 
							" SELECT id_adresse_lager FROM " + WAAGE_LAGER.fullTabName();
									
			String [][] arrRes = bibDB.EinzelAbfrageInArray(cQuery);
			for (int i=0; i<arrRes.length; i++){
				vRueck.add(arrRes[i][0]);
			}
			
			bibSES.set_EIGENE_WAAGE_LAGER_ADRESSEN(vRueck);
		}
		
		return vRueck;
	}
	
	
	/**
	 * puffert die eigenen Lieferadressen aus der bibRohstoff in der Session
	 * @author manfred
	 * @date 29.08.2018
	 *
	 * @param buildNew
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get__EigeneLieferadressenOhneSonderlager(boolean buildNew) throws myException 
	{
		Vector<String> vRueck = (Vector<String>) bibSES.getSessionValue(bibSES_keys4save.SES_KEY_____EIGENE_LIEFERADRESSEN_OHNE_SONDERLAGER);
		if (vRueck==null || buildNew) {
			vRueck = new Vector<>();
			vRueck = bibROHSTOFF.get_vEigeneLieferadressen(false);
			
			bibSES.set__EigeneLieferadressenOhneSonderlager(vRueck);
		}
		return vRueck;
	}

	public static void set__EigeneLieferadressenOhneSonderlager(Vector<String>  vEigeneLieferadressen)
	{
		bibSES.setSessionValue(bibSES_keys4save.SES_KEY_____EIGENE_LIEFERADRESSEN_OHNE_SONDERLAGER, vEigeneLieferadressen);
	}

	
	

	


	/**
	 * 2-stufige hashmap (schneller zugriff): 
	 * 		1. Modulname
	 * 		2. Spaltenlabel
	 * 		3. Record	
	 * @return
	 * @throws myException 
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>>> get_set_SUM_COLUMES_IN_LISTMODULES() throws myException
	{

		HashMap<String, HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>>>  hmCalcCols = (HashMap<String, HashMap<String,Vector<RECORD_COLUMNS_TO_CALC>>>)
				bibSES.getSessionValue(bibSES_keys4save.SES_KEY____SUMMATIONSSPALTEN);
		
		if (bibSES.getSessionValue(bibSES_keys4save.SES_KEY____SUMMATIONSSPALTEN)==null ||
			((HashMap<String, HashMap<String, RECORD_COLUMNS_TO_CALC>> )bibSES.getSessionValue(bibSES_keys4save.SES_KEY____SUMMATIONSSPALTEN)).size()==0)
		{
			hmCalcCols = new HashMap<String, HashMap<String,Vector<RECORD_COLUMNS_TO_CALC>>>();
			
			RECLIST_COLUMNS_TO_CALC rlCols2Calc = new RECLIST_COLUMNS_TO_CALC("SELECT * FROM "+bibE2.cTO()
									+"."+_DB.COLUMNS_TO_CALC+" WHERE NVL("
									+_DB.COLUMNS_TO_CALC$ACTIVE+",'N')='Y'");
			
			//zuerst die module sammeln
			VectorSingle vModuls = new VectorSingle();
			for (RECORD_COLUMNS_TO_CALC recCol: rlCols2Calc.values()) {
				vModuls.add(recCol.get_MODULNAME_LISTE_cUF());
			}
			
			//rohdaten anlegen
			for (String cModule: vModuls) {
				hmCalcCols.put(cModule, new HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>>());
			}

			//jetzt fuellen
			for (RECORD_COLUMNS_TO_CALC recCol: rlCols2Calc.values()) {
				String cMOD_LABEL = recCol.get_MODULNAME_LISTE_cUF();
				String cCOL_LABEL = recCol.get_COLUMN_LABEL_cUF();
				
				HashMap<String, Vector<RECORD_COLUMNS_TO_CALC>> hmCalcRecords = hmCalcCols.get(cMOD_LABEL);
				
				Vector<RECORD_COLUMNS_TO_CALC> vColumn = hmCalcRecords.get(cCOL_LABEL);
				if (vColumn==null) {
					vColumn = new Vector<RECORD_COLUMNS_TO_CALC>();
					hmCalcRecords.put(cCOL_LABEL, vColumn);
				}
				vColumn.add(recCol);
			}
			
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY____SUMMATIONSSPALTEN, hmCalcCols);
		}
		
		return hmCalcCols;
	}
	

	
	
	/**
	 * holt und speichert ein oder mehrere Scannerprofile fuer einen benutzer in der session
	 * @param oMV_Rueck
	 * @return
	 * @throws myException
	 */
	public static RECLIST_SCANNER_SETTINGS_SPECIAL  get_RECLIST_SCANNER_4_USER(MyE2_MessageVector oMV_Rueck) throws myException {
		if (bibALL.getSessionValue(bibSES_ENUM.SCANNERLISTE.name())==null) {
			RECLIST_SCANNER_SETTINGS_SPECIAL recSCANNER =  new RECLIST_SCANNER_SETTINGS_SPECIAL(bibALL.get_RECORD_USER().get_ID_USER_cUF(), 
					(oMV_Rueck!=null?oMV_Rueck:new MyE2_MessageVector()));
			bibALL.setSessionValue(bibSES_ENUM.SCANNERLISTE.name(), recSCANNER);
		}
		return (RECLIST_SCANNER_SETTINGS_SPECIAL)bibALL.getSessionValue(bibSES_ENUM.SCANNERLISTE.name());
	}
	
	
	
	
	
	/**
	 * 2014-08-26: session-container mit MyMetaFieldDef-objekten
	 * @param cTable
	 * @param cField
	 * @return
	 * @throws myException
	 */
	public static HASHMAP_MyMetaFieldDef  get_MetaFieldDEF(String cTable) throws myException {

		@SuppressWarnings("unchecked")
		HashMap<String, HASHMAP_MyMetaFieldDef>  hmContainer = (HashMap<String, HASHMAP_MyMetaFieldDef>)bibSES.getSessionValue(bibSES_keys4save.SES_KEY_HASHMAPS_METAFIELDDEFS);
				
		//zuerst schauen, ob der hautpcontainer vorhanden ist 
		if (hmContainer == null) {
			hmContainer = new HashMap<String, HASHMAP_MyMetaFieldDef>();
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY_HASHMAPS_METAFIELDDEFS, hmContainer);
		}

		//dann schauen, ob die tabelle definiert ist
		HASHMAP_MyMetaFieldDef hmDefTable = hmContainer.get(cTable);
		if (hmDefTable == null) {
			hmDefTable = new HASHMAP_MyMetaFieldDef(cTable,true);
			hmContainer.put(cTable, hmDefTable);
		}
		
		return hmDefTable.get_CLONE();
	}
	

	/**
	 * 2014-08-26: serverweiter container mit MyMetaFieldDef-objekten
	 * @param cTable
	 * @param cField
	 * @return
	 * @throws myException
	 */
	public static MyMetaFieldDEF  get_MetaFieldDEF(String cTable, String cField) throws myException {
		
		@SuppressWarnings("unchecked")
		HashMap<String, HASHMAP_MyMetaFieldDef>  hmContainer = (HashMap<String, HASHMAP_MyMetaFieldDef>)bibSES.getSessionValue(bibSES_keys4save.SES_KEY_HASHMAPS_METAFIELDDEFS);
				
		//zuerst schauen, ob der hautpcontainer vorhanden ist 
		if (hmContainer == null) {
			hmContainer = new HashMap<String, HASHMAP_MyMetaFieldDef>();
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY_HASHMAPS_METAFIELDDEFS, hmContainer);
		}

		//dann schauen, ob die tabelle definiert ist
		HASHMAP_MyMetaFieldDef hmDefTable = hmContainer.get(cTable);
		if (hmDefTable == null) {
			hmDefTable = new HASHMAP_MyMetaFieldDef(cTable,true);
			hmContainer.put(cTable, hmDefTable);
		}
		
		return hmDefTable.get(cField).get_Clone();
	}
	

	
	/**
	 * 2015-03-13: verlagert in die bibSES
	 * @return
	 * @throws myException
	 */
	public static RECLIST_FIELD_RULE  get_hmFieldRules() throws myException
	{
		RECLIST_FIELD_RULE fieldRules = (RECLIST_FIELD_RULE)bibSES.getSessionValue(bibSES_keys4save.SES_KEY_FIELD_RULES);
		if (fieldRules == null) {
			fieldRules = new RECLIST_FIELD_RULE("SELECT * FROM " + bibE2.cTO() + ".JT_FIELD_RULE");
			bibSES.setSessionValue(bibSES_keys4save.SES_KEY_FIELD_RULES, fieldRules);
		}
		return fieldRules;
	}

	
	
	
	public static String get_ID_MANDANT_UF() {
		return (String)bibSES.getSessionValue(bibSES_ENUM.ID_MANDANT.toString());
	}
	
	public static String get_USER_KUERZEL() {
		return (String)bibSES.getSessionValue(bibSES_ENUM.USER_KUERZEL.toString());
	}

	
	
	/**
 	 * 2015-05-21: getAllowedButtons umgezogen und auf AdhocEinlesen umgestellt
	 * 
	 * @return ARRAY[n][3] with [RANGE][BUTTON][Y] or [N] for actual user, wenn error, then ["TEST#DUMMY","TEST#DUMMY","N"]
	 */
	public static String[][] get_ALLOWEDBUTTONS() {
		String[][] cRueck = null;
	
		try {
			cRueck = (String[][])bibALL.getSessionValue(bibSES_ENUM.ALLOWEDBUTTONS.toString());
			
			if (cRueck == null) {
				cRueck = new bibSES_readerUserRights(new  RECORD_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF())).get_cARRAY_KENNER();
				bibALL.setSessionValue(bibSES_ENUM.ALLOWEDBUTTONS.toString(), cRueck);
			}
		} catch (myException e) {
			e.printStackTrace();
			cRueck = null;
		}
	
		
		if (cRueck==null) {
			cRueck = new String[1][3];
			cRueck[0][0]="TEST#DUMMYRANGE";
			cRueck[0][1]="TEST#DUMMYBUTTON";
			cRueck[0][1]="N";
		}
		
		return cRueck;
	}

	
	/**
	 * speichert zu jeder id_adressklasse_def die zugehoerige markerfarbe
	 * @return HashMap<String, RECORD_ADRESSKLASSE_DEF_EXT> mit markerfarben zu den Adressklasse-defs
	 * @throws myException 
	 */
	public static LinkedHashMap<String, RECORD_ADRESSKLASSE_DEF_EXT> get_MarkerColorsAdressClass() throws myException {
	
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, RECORD_ADRESSKLASSE_DEF_EXT> hmColors = (LinkedHashMap<String, RECORD_ADRESSKLASSE_DEF_EXT>) bibALL.getSessionValue(bibSES_ENUM.MARK_COLOR_ADRESSCLASS.name());
		
		if (hmColors==null) {
			hmColors = new LinkedHashMap<String, RECORD_ADRESSKLASSE_DEF_EXT>();
			
			RECLIST_ADRESSKLASSE_DEF  rlDef = new RECLIST_ADRESSKLASSE_DEF(new SEL("*").FROM(ADRESSKLASSE_DEF.T()).ORDERUP(ADRESSKLASSE_DEF.colorsort).s());
			
			for (String id: rlDef.get_vKeyValues()) {
				RECORD_ADRESSKLASSE_DEF_EXT recDef = new RECORD_ADRESSKLASSE_DEF_EXT(rlDef.get(id));
				if (recDef.generate_Color()!=null) {
					hmColors.put(recDef.get_ID_ADRESSKLASSE_DEF_cUF(), recDef);
				}
			}
			
			bibALL.setSessionValue(bibSES_ENUM.MARK_COLOR_ADRESSCLASS.name(),hmColors);
		}
		return hmColors;
	}
	
	
	

	/**
	 * 
	 * @param bibSES_keys4save
	 * @return
	 */
	public static Object getSessionValue(bibSES_keys4save key) {
		return bibSES.getSessionValue(key.s());
	}
	
	/**
	 * 
	 * @param PARAMETES_IN_CREATE_SESSION
	 * @return
	 */
	public static Object getSessionValue(bibSES_ENUM key) {
		return bibSES.getSessionValue(key.toString());
	}
	
	/**
	 * 
	 * @param bibSES_keys4save
	 * @param object
	 */
	public static void setSessionValue(bibSES_keys4save key, Object o) {
		bibSES.setSessionValue(key.s(), o);
	}
	
	
	/**
	 * 
	 * @param PARAMETES_IN_CREATE_SESSION
	 * @param o
	 */
	public static void setSessionValue(bibSES_ENUM key, Object o) {
		bibSES.setSessionValue(key.toString(), o);
	}
	

	//2018-05-18:  neue save-funktion fuer singular-storing
	/**
	 * 
	 * @param clas: calling class
	 * @param key: ENUM_SINGULAR_SETTINGS
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static WeakReference<Object> getSessionValue(Class clas, ENUM_SINGULAR_SETTINGS key) {
		return (WeakReference<Object>)bibSES.getSessionValue(clas.getName()+"@@"+bibSES_keys4save.SAVE_ENUM_SINGULAR_SETTING_ADDONCODE.name()+"@@@"+key.name());
	}
	
	/**
	 * 
	 * @param clas: calling class
	 * @param key: ENUM_SINGULAR_SETTINGS
	 * @param o :WeakReference<Object>
	 */
	@SuppressWarnings("rawtypes")
	public static void setSessionValue(Class clas, ENUM_SINGULAR_SETTINGS key, WeakReference<Object> o) {
		DEBUG._print("KEY-for-Save: "+clas.getName()+bibSES_keys4save.SAVE_ENUM_SINGULAR_SETTING_ADDONCODE.name()+"@@@"+key.name());
		bibSES.setSessionValue(clas.getName()+"@@"+bibSES_keys4save.SAVE_ENUM_SINGULAR_SETTING_ADDONCODE.name()+"@@@"+key.name(), o);
	}
	//2018-05-18 -------------------

	
	
	//private kopie der alten bibALL-methoden
    @SuppressWarnings("unchecked")
    public static void setSessionValue(String cName, Object oObject) {
        HashMap<String, Object> __JIL_VALUES = (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	__JIL_VALUES.remove(cName.toUpperCase());
        }
        __JIL_VALUES.put(cName.toUpperCase(), oObject);
    }

    
	//kopie der alten bibALL-methoden
    @SuppressWarnings("unchecked")
    public static Object getSessionValue(String cName)  {
        Object oRueck = null;
        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	oRueck = __JIL_VALUES.get(cName.toUpperCase());
        }
        return oRueck;
    }

	//nachsehen, ob ein key vorhanden ist
    @SuppressWarnings("unchecked")
    public static boolean hasSessionValue(String cName)  {
        boolean bRueck = false;
        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	bRueck = true;
        }
        return bRueck;
    }

    @SuppressWarnings("unchecked")
    public static void removeSessionValue(String cName)  {
        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	__JIL_VALUES.remove(cName.toUpperCase());
        }
    }

    
    
    /**
     * 
     * @return actual record_user stored in session (with all fields, more than old RECORD_USER_BASE from bibALL
     * @throws myException
     */
    public static RECORD_USER RECORD_USER() throws myException {
    	if (bibSES.getSessionValue(bibSES_keys4save.RECORD_USER_REAL)==null) {
    		bibSES.setSessionValue(bibSES_keys4save.RECORD_USER_REAL,new RECORD_USER(bibSES.get_RECORD_USER().get_ID_USER_cUF()));
    	}
    	return (RECORD_USER)bibSES.getSessionValue(bibSES_keys4save.RECORD_USER_REAL);
    }
    
    
    /**
     * 
     * @return actual record_user stored in session (with all fields, more than old RECORD_USER_BASE from bibALL
     * @throws myException
     */
    public static RECORD_MANDANT RECORD_MANDANT() throws myException {
    	if (bibSES.getSessionValue(bibSES_keys4save.RECORD_MANDANT_REAL)==null) {
    		bibSES.setSessionValue(bibSES_keys4save.RECORD_MANDANT_REAL,new RECORD_MANDANT(bibSES.get_RECORD_MANDANT().get_ID_MANDANT_cUF()));
    	}
    	return (RECORD_MANDANT)bibSES.getSessionValue(bibSES_keys4save.RECORD_MANDANT_REAL);
    }

    
	public static ServletContext getServletContext() {
		return bibE2.get_CurrSession().getServletContext();
	}

	
	
    
}
