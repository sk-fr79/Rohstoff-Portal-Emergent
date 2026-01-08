package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WAAGE_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAAGE_LAGER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum Behandeln von Fuhren und Fuhreninformationen
 * @author manfred
 *
 */
public class WK_FuhrenInfo {

	
	private String m_IdMandant = null;
	private String m_VFuhren = null;
	
	/** Datum im ISO-Format yyy-MM-dd
	 */
	private String   m_DatumVon = null;

	/** Datum im ISO-Format yyy-MM-dd
	 */
	private String   m_DatumBis = null;
	
	private myDateHelper        m_DateHelper = null;
	
	private Vector<String> m_ID_Adressen_Lager = new Vector<String>();
	
	/**
	 * Default-Konstruktor
	 * @throws myException 
	 */
	public WK_FuhrenInfo() throws myException {
		m_IdMandant = bibALL.get_ID_MANDANT();
		m_VFuhren =  bibALL.get_TABLEOWNER() + ".V" + m_IdMandant + "_FUHREN";
		
		myDateHelper dh = new myDateHelper(new Date());
		
		m_DatumVon = dh.get_cDateFormat_ISO_FORMAT();
		m_DatumBis = dh.get_cDateFormat_ISO_FORMAT();
	}
	
	
	public String get_DatumVon() {
		return m_DatumVon;
	}

	public void set_DatumVon(String mDatumVon) {
		m_DatumVon = mDatumVon;
	}

	public String get_DatumBis() {
		return m_DatumBis;
	}

	public void set_DatumBis(String mDatumBis) {
		m_DatumBis = mDatumBis;
	}

	
	
	/**
	 * Gibt eine Liste von Fuhren, die für das übergebene Lager vorhanden sind.
	 * Alle anderen parameter müssen vorher als Property übergeben sein.
	 * Standard-Voreinstellungen: 
	 * Zeitraum: Alle Fuhren von Heute
	 * Status:	Alle Fuhren, die nicht Storniert sind 
	 * @param IdLager
	 * @return
	 */
	public ArrayList<WK_RECORD_FuhrenInfo> getFuhrenInfos(String IdLager){
		return getFuhrenInfos(null, null, IdLager);
	}
	
	
	/**
	 * Sucht nach genau der einen Fuhre des Lagers..
	 * @param IdFuhre
	 * @param IdFuhrenOrt
	 * @param IdLager
	 * @return
	 */
	public WK_RECORD_FuhrenInfo getFuhrenInfo( String IdFuhre, String IdFuhrenOrt, String IdLager) {
		
		WK_RECORD_FuhrenInfo oRet = null;
		
		// suche nach der fuhre Ohne Einschränkung auf die Zeit
		ArrayList<WK_RECORD_FuhrenInfo> l = this.getFuhrenInfos(IdFuhre, IdFuhrenOrt, IdLager,null,null);
		
		if(l.size() == 1){
			oRet = l.get(0);
		} else if(l.size() > 1){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist mehr als 1 Eintrag für die Fuhre gefunden worden!").CTrans()));
			oRet = null;
		} else if (l.size() == 0){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es ist keine Fuhre mit der gegebenen ID gefunden worden!").CTrans()));
		}
		return oRet;
	}
	
	
	private ArrayList<WK_RECORD_FuhrenInfo> getFuhrenInfos(String IdFuhre, String IdFuhrenOrt, String IdLager){
		return getFuhrenInfos(IdFuhre, IdFuhrenOrt, IdLager,m_DatumVon,m_DatumBis);
	}
	
	
	
	/**
	 * Ermitteln einer Fuhreninformation aus den FuhrenIDs
	 * @param IdFuhre
	 * @param IdFuhrenOrt
	 * @return
	 */
	private ArrayList<WK_RECORD_FuhrenInfo> getFuhrenInfos_alt(String IdFuhre, String IdFuhrenOrt, String IdLager, String datum_von, String datum_bis){
		
		ArrayList<WK_RECORD_FuhrenInfo> oInfos  = new ArrayList<WK_RECORD_FuhrenInfo>();
		
		String sSqlMain = "";
		String sSqlWhere = "";
		
		if (IdLager == null || IdLager.trim().equals("")){
			// fehler, es gibt keine Einträge ohne Fuhren!!
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager angegeben sein!").CTrans()));
		}
		

		// Einschränkung Fuhre
		if (IdFuhre != null ){
			if(!IdFuhre.trim().equals("")){
				sSqlWhere = " AND F.ID_VPOS_TPA_FUHRE = " + IdFuhre;
			}
		} 
		else {
			// fehler, es gibt keine Einträge ohne Fuhren!!
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der Fuhren-Daten!").CTrans()));
		}
		
		
		// Einschränkung FuhrenORT
		if (IdFuhrenOrt != null ){
			if (!IdFuhrenOrt.trim().equals("")){
				sSqlWhere += " AND F.ID_VPOS_TPA_FUHRE_ORT = " + IdFuhrenOrt;
			}
		}
		else {
			sSqlWhere += " AND F.ID_VPOS_TPA_FUHRE_ORT IS null ";
		}

		
		// Einschränkung Zeitraum
		if (datum_bis != null && !datum_bis.trim().equals("")){
			sSqlWhere += " AND ( F.DATUM_ANLIEFERUNG_ENDE <= '" + datum_bis + "' or  F.DATUM_ABHOLUNG_ENDE <= '" + datum_bis + "') ";
		}
		if (datum_von != null && !datum_von.trim().equals("")){
			sSqlWhere += " AND ( F.DATUM_ANLIEFERUNG <= '" + datum_von + "' or  F.DATUM_ABHOLUNG <= '" + datum_von + "') ";
		}
	
		
		sSqlMain = " SELECT F.ID_VPOS_TPA_FUHRE, " + 
			" F.ID_VPOS_TPA_FUHRE_ORT, " + 
			" F.ID_ADRESSE_LAGER_START, " + 
			" F.ID_ADRESSE_LAGER_ZIEL, " + 
			" case F.ID_ADRESSE_LAGER_ZIEL   when " + IdLager + " then  'Y'  ELSE CASE F.ID_ADRESSE_LAGER_START WHEN " + IdLager + " then 'N' ELSE 'Y' END END AS IST_Lieferung, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_NAME1 else F.L_NAME1 end as name1, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_NAME2 else F.L_NAME2 end as name2, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_NAME3 else F.L_NAME3 end as name3, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_STRASSE else F.L_STRASSE end as strasse, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_HAUSNUMMER else F.L_HAUSNUMMER end as Hausnummer, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_PLZ else F.L_PLZ end as plz, " + 
			" case F.ID_ADRESSE_LAGER_START  when " + IdLager + " then  F.A_ORT else F.L_ORT  end as ort, " + 
			" F.TRANSPORTKENNZEICHEN,  " + 
			" F.ANHAENGERKENNZEICHEN,  " + 
			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  F.ANTEIL_ABLADEMENGE_ABN " +     
			" 	ELSE CASE F.ID_ADRESSE_LAGER_START  WHEN " + IdLager + " THEN F.ANTEIL_ABLADEMENGE_LIEF  ELSE null END " +  
			" END AS LADEMENGE, " + 
			" F.ANTEIL_ABLADEMENGE_ABN, " +  
			" F.ANTEIL_ABLADEMENGE_LIEF,  " + 
			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  b1.ID_ARTIKEL " + 
			" 	ELSE CASE F.ID_ADRESSE_LAGER_START WHEN " + IdLager + " then b2.ID_ARTIKEL ELSE b1.ID_ARTIKEL END " +	 
			" END AS ID_ARTIKEL, " + 
			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  F.ID_ARTIKEL_BEZ_EK " + 
			" 	ELSE CASE F.ID_ADRESSE_LAGER_START WHEN " + IdLager + " then F.ID_ARTIKEL_BEZ_VK  " +
			"   ELSE F.ID_ARTIKEL_BEZ_EK END " +	 
			" END AS ID_ARTIKEL " + 
			" ,F.BUCHUNGSNR_FUHRE " +
			" ,NVL2(k1.ID_ADRESSE, k1.ID_ADRESSE, F.ID_ADRESSE_SPEDITION ) as ID_ADRESSE_SPEDITION, " +
			//case when V.MENGEN_VORZEICHEN > 0 or V.MENGEN_VORZEICHEN = 0 then '1' else '3' end
			// Streckeninfo, d.h. immer den Abnehmer
			" case when F.ID_ADRESSE_ZIEL != " + bibALL.get_ID_ADRESS_MANDANT() + " AND F.ID_ADRESSE_START != " + bibALL.get_ID_ADRESS_MANDANT() + " THEN  'Y'  ELSE  'N' END AS IST_STRECKE , " + 
			" F.A_NAME1  as strecke_name1, " + 
			" F.A_NAME2  as strecke_name2, " + 
			" F.A_NAME3  as strecke_name3, " + 
			" F.A_STRASSE as strecke_strasse, " + 
			" F.A_HAUSNUMMER as strecke_hausnummer, " + 
			" F.A_PLZ as strecke_plz, " + 
			" F.A_ORT as strecke_ort " +
			
			" FROM " + m_VFuhren  + " F " + 
			" LEFT OUTER JOIN " + bibALL.get_TABLEOWNER() + ".JT_ARTIKEL_BEZ b1 on b1.ID_ARTIKEL_BEZ =  F.ID_ARTIKEL_BEZ_EK " + 
			" LEFT OUTER JOIN " + bibALL.get_TABLEOWNER() + ".JT_ARTIKEL_BEZ b2 on b2.ID_ARTIKEL_BEZ =  F.ID_ARTIKEL_BEZ_VK  " + 
			" LEFT OUTER JOIN "+ bibALL.get_TABLEOWNER() + ".JT_VPOS_TPA t1 on t1.ID_VPOS_TPA =  F.ID_VPOS_TPA " +
			" LEFT OUTER JOIN "+ bibALL.get_TABLEOWNER() + ".JT_VKOPF_TPA k1 on k1.ID_VKOPF_TPA =  t1.ID_VKOPF_TPA " +
			" WHERE " + 
			" ( F.ALT_WIRD_NICHT_GEBUCHT IS NULL  OR  F.ALT_WIRD_NICHT_GEBUCHT = 'N') " +  
			" AND (F.IST_STORNIERT is null OR F.IST_STORNIERT = 'N')  " +
			
			// Laut Frau Meier müssen auch Streckenfuhren gefunden werden
			//			" AND  (F.ID_ADRESSE_LAGER_START = " + IdLager + "  OR  F.ID_ADRESSE_LAGER_ZIEL = " + IdLager + ") " +
				sSqlWhere;

		if (!bibMSG.get_bIsOK()){
			return oInfos;
		}

		
		// Liste Aufbauen
		WK_RECORD_FuhrenInfo oFuhrenInfo = null;
		
		String [][] cFuhrenDaten = new String[0][0];
		cFuhrenDaten =  bibDB.EinzelAbfrageInArray(sSqlMain,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cFuhrenDaten != null && cFuhrenDaten.length > 0){
			// sonst den Vektor füllen
			for (int i = 0; i < cFuhrenDaten.length; i++){
					oFuhrenInfo = new WK_RECORD_FuhrenInfo();
					
					oFuhrenInfo.setID_VPOS_TPA_FUHRE(cFuhrenDaten[i][0]);
					oFuhrenInfo.setID_VPOS_TPA_FUHREN_ORT(cFuhrenDaten[i][1]);
					oFuhrenInfo.setID_ADRESSE_START(cFuhrenDaten[i][2]);
					oFuhrenInfo.setID_ADRESSE_ZIEL(cFuhrenDaten[i][3]);
					oFuhrenInfo.setIST_LIEFERANT(cFuhrenDaten[i][4]);
					oFuhrenInfo.setNAME1(cFuhrenDaten[i][5]);
					oFuhrenInfo.setNAME2(cFuhrenDaten[i][6]);
				 	oFuhrenInfo.setNAME3(cFuhrenDaten[i][7]);
				 	oFuhrenInfo.setSTRASSE(cFuhrenDaten[i][8]);
				 	oFuhrenInfo.setHAUSNUMMER(cFuhrenDaten[i][9]);
				 	oFuhrenInfo.setPLZ(cFuhrenDaten[i][10]);
				 	oFuhrenInfo.setORT(cFuhrenDaten[i][11]);
				 	oFuhrenInfo.setTRANSPORTKENNZEICHEN(cFuhrenDaten[i][12]);
				 	oFuhrenInfo.setANHAENGERKENNZEICHEN(cFuhrenDaten[i][13]);
				 	oFuhrenInfo.setLADEMENGE(cFuhrenDaten[i][14]);
				 	oFuhrenInfo.setABLADEMENGE_ABN(cFuhrenDaten[i][15]);
				 	oFuhrenInfo.setABLADEMENGE_LIEF(cFuhrenDaten[i][16]);
				 	oFuhrenInfo.setID_SORTE(cFuhrenDaten[i][17]);
				 	oFuhrenInfo.setID_ARTIKEL_BEZ(cFuhrenDaten[i][18]);
				 	oFuhrenInfo.setBUCHUNGSNUMMER(cFuhrenDaten[i][19]);
				 	oFuhrenInfo.setID_ADRESSE_SPEDITION(cFuhrenDaten[i][20]);
				 	
				 	oFuhrenInfo.setIST_STRECKE(cFuhrenDaten[i][21]);
					oFuhrenInfo.setSTRECKE_NAME1(cFuhrenDaten[i][22]);
					oFuhrenInfo.setSTRECKE_NAME2(cFuhrenDaten[i][23]);
				 	oFuhrenInfo.setSTRECKE_NAME3(cFuhrenDaten[i][24]);
				 	oFuhrenInfo.setSTRECKE_STRASSE(cFuhrenDaten[i][25]);
				 	oFuhrenInfo.setSTRECKE_HAUSNUMMER(cFuhrenDaten[i][26]);
				 	oFuhrenInfo.setSTRECKE_PLZ(cFuhrenDaten[i][27]);
				 	oFuhrenInfo.setSTRECKE_ORT(cFuhrenDaten[i][28]);
				 	
				 	
				 	oInfos.add(oFuhrenInfo);
			}
		}
		
		return oInfos;
	}
	

	
	
	/**
	 * Ermitteln einer Fuhreninformation aus den FuhrenIDs
	 * @param IdFuhre
	 * @param IdFuhrenOrt
	 * @return
	 */
	private ArrayList<WK_RECORD_FuhrenInfo> getFuhrenInfos(String IdFuhre, String IdFuhrenOrt, String IdLager, String datum_von, String datum_bis){
		
		ArrayList<WK_RECORD_FuhrenInfo> oInfos  = new ArrayList<WK_RECORD_FuhrenInfo>();

		MyE2_MessageVector oMSG = new  MyE2_MessageVector();
		
		
		// das "hauptlager" als Lager eintragen
		m_ID_Adressen_Lager.add(IdLager);
		
		// alle eingetragenen Laeger auch noch als moegliche Lager eintragen
		RECLIST_WAAGE_LAGER olist;
		try {
			olist = new RECLIST_WAAGE_LAGER("ID_ADRESSE_BASIS = " + IdLager, "");
			for(RECORD_WAAGE_LAGER rec : olist.values()){
				m_ID_Adressen_Lager.add(rec.get_ID_ADRESSE_LAGER_cUF_NN("*"));
			}
		} catch (myException e) {
			e.printStackTrace();
		}

		
		
		String sSqlMain = "";
		String sSqlWhere = "";
		
		if (IdLager == null || IdLager.trim().equals("")){
			// fehler, es gibt keine Einträge ohne Fuhren!!
			oMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es muss ein Lager angegeben sein!").CTrans()));
		}
		

		// Einschränkung Fuhre
		if (IdFuhre != null ){
			if(!IdFuhre.trim().equals("")){
				sSqlWhere = " AND F.ID_VPOS_TPA_FUHRE = " + IdFuhre;
			}
		} 
		else {
			// fehler, es gibt keine Einträge ohne Fuhren!!
			oMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ermittlung der Fuhren-Daten!").CTrans()));
		}
		
		
		// Einschränkung FuhrenORT
		if (IdFuhrenOrt != null ){
			if (!IdFuhrenOrt.trim().equals("")){
				sSqlWhere += " AND F.ID_VPOS_TPA_FUHRE_ORT = " + IdFuhrenOrt;
			}
		}
		else {
			sSqlWhere += " AND F.ID_VPOS_TPA_FUHRE_ORT IS null ";
		}

		
		// Einschränkung Zeitraum
		if (datum_bis != null && !datum_bis.trim().equals("")){
			sSqlWhere += " AND ( F.DATUM_ANLIEFERUNG_ENDE <= '" + datum_bis + "' or  F.DATUM_ABHOLUNG_ENDE <= '" + datum_bis + "') ";
		}
		if (datum_von != null && !datum_von.trim().equals("")){
			sSqlWhere += " AND ( F.DATUM_ANLIEFERUNG <= '" + datum_von + "' or  F.DATUM_ABHOLUNG <= '" + datum_von + "') ";
		}
	
		
		sSqlMain = " SELECT " +
			" F.ID_VPOS_TPA_FUHRE, " + 
			" F.ID_VPOS_TPA_FUHRE_ORT, " + 
			" F.ID_ADRESSE_LAGER_START, " + 
			" F.ID_ADRESSE_LAGER_ZIEL, " + 
			" F.ID_ADRESSE_START ," +
			" F.ID_ADRESSE_ZIEL, " +
			" F.A_NAME1, " +
			" F.A_NAME2, " +
			" F.A_NAME3, " +
			" F.A_STRASSE, " +
			" F.A_HAUSNUMMER, " +
			" F.A_PLZ," +
			" F.A_ORT, " +
			" F.L_NAME1, " +
			" F.L_NAME2, " +
			" F.L_NAME3, " +
			" F.L_STRASSE, " +
			" F.L_HAUSNUMMER, " +
			" F.L_PLZ," +
			" F.L_ORT, " +
			" F.TRANSPORTKENNZEICHEN,  " + 
			" F.ANHAENGERKENNZEICHEN,  " +
			" F.ANTEIL_ABLADEMENGE_ABN, " +
			" F.ANTEIL_ABLADEMENGE_LIEF, " +
			" b1.ID_ARTIKEL, " +
			" b2.ID_ARTIKEL, " +
			" F.ID_ARTIKEL_BEZ_EK, " +
			" F.ID_ARTIKEL_BEZ_VK, " +
			" F.BUCHUNGSNR_FUHRE, " +
			" K1.ID_ADRESSE, " +
			" F.ID_ADRESSE_SPEDITION " +

//			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  F.ANTEIL_ABLADEMENGE_ABN " +     
//			" 	ELSE CASE F.ID_ADRESSE_LAGER_START  WHEN " + IdLager + " THEN F.ANTEIL_ABLADEMENGE_LIEF  ELSE null END " +  
//			" END AS LADEMENGE, " + 
			
//			" F.ANTEIL_ABLADEMENGE_ABN, " +  
//			" F.ANTEIL_ABLADEMENGE_LIEF,  " + 
//			
//			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  b1.ID_ARTIKEL " + 
//			" 	ELSE CASE F.ID_ADRESSE_LAGER_START WHEN " + IdLager + " then b2.ID_ARTIKEL ELSE b1.ID_ARTIKEL END " +	 
//			" END AS ID_ARTIKEL, " + 
			
//			" CASE F.ID_ADRESSE_LAGER_ZIEL WHEN " + IdLager + " THEN  F.ID_ARTIKEL_BEZ_EK " + 
//			" 	ELSE CASE F.ID_ADRESSE_LAGER_START WHEN " + IdLager + " then F.ID_ARTIKEL_BEZ_VK  " +
//			"   ELSE F.ID_ARTIKEL_BEZ_EK END " +	 
//			" END AS ID_ARTIKEL " + 
//			
//			" ,F.BUCHUNGSNR_FUHRE " +
//			" ,NVL2(k1.ID_ADRESSE, k1.ID_ADRESSE, F.ID_ADRESSE_SPEDITION ) as ID_ADRESSE_SPEDITION, " +
//			//case when V.MENGEN_VORZEICHEN > 0 or V.MENGEN_VORZEICHEN = 0 then '1' else '3' end
//			// Streckeninfo, d.h. immer den Abnehmer
//			" case when F.ID_ADRESSE_ZIEL != " + bibALL.get_ID_ADRESS_MANDANT() + " AND F.ID_ADRESSE_START != " + bibALL.get_ID_ADRESS_MANDANT() + " THEN  'Y'  ELSE  'N' END AS IST_STRECKE , " + 
//			" F.A_NAME1  as strecke_name1, " + 
//			" F.A_NAME2  as strecke_name2, " + 
//			" F.A_NAME3  as strecke_name3, " + 
//			" F.A_STRASSE as strecke_strasse, " + 
//			" F.A_HAUSNUMMER as strecke_hausnummer, " + 
//			" F.A_PLZ as strecke_plz, " + 
//			" F.A_ORT as strecke_ort " +
			
			" FROM " + m_VFuhren  + " F " + 
			" LEFT OUTER JOIN " + bibALL.get_TABLEOWNER() + ".JT_ARTIKEL_BEZ b1 on b1.ID_ARTIKEL_BEZ =  F.ID_ARTIKEL_BEZ_EK " + 
			" LEFT OUTER JOIN " + bibALL.get_TABLEOWNER() + ".JT_ARTIKEL_BEZ b2 on b2.ID_ARTIKEL_BEZ =  F.ID_ARTIKEL_BEZ_VK  " + 
			" LEFT OUTER JOIN "+ bibALL.get_TABLEOWNER() + ".JT_VPOS_TPA t1 on t1.ID_VPOS_TPA =  F.ID_VPOS_TPA " +
			" LEFT OUTER JOIN "+ bibALL.get_TABLEOWNER() + ".JT_VKOPF_TPA k1 on k1.ID_VKOPF_TPA =  t1.ID_VKOPF_TPA " +
			" WHERE " + 
			" ( F.ALT_WIRD_NICHT_GEBUCHT IS NULL  OR  F.ALT_WIRD_NICHT_GEBUCHT = 'N') " +  
			" AND (F.IST_STORNIERT is null OR F.IST_STORNIERT = 'N')  " +
			
			// Laut Frau Meier müssen auch Streckenfuhren gefunden werden
			//			" AND  (F.ID_ADRESSE_LAGER_START = " + IdLager + "  OR  F.ID_ADRESSE_LAGER_ZIEL = " + IdLager + ") " +
				sSqlWhere;

		if (!oMSG.get_bIsOK()){
			bibMSG.add_MESSAGE(oMSG);
			return oInfos;
		}

		
		// Liste Aufbauen
		WK_RECORD_FuhrenInfo oFuhrenInfo = null;
		
		String [][] cFuhrenDaten = new String[0][0];
		cFuhrenDaten =  bibDB.EinzelAbfrageInArray(sSqlMain,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cFuhrenDaten != null && cFuhrenDaten.length > 0){
			// sonst den Vektor füllen
			for (int i = 0; i < cFuhrenDaten.length; i++){
					oFuhrenInfo = new WK_RECORD_FuhrenInfo();
					
					String s_ID_VPOS_TPA_FUHRE = cFuhrenDaten[i][0];
					String s_ID_VPOS_TPA_FUHRE_ORT = cFuhrenDaten[i][1];
					
					String s_ID_ADRESSE_LAGER_START = cFuhrenDaten[i][2];
					String s_ID_ADRESSE_LAGER_ZIEL = cFuhrenDaten[i][3];
					
					String s_ID_ADRESSE_START  = cFuhrenDaten[i][4];
					String s_ID_ADRESSE_ZIEL = cFuhrenDaten[i][5];
					
					String s_A_NAME1 = cFuhrenDaten[i][6];
					String s_A_NAME2 = cFuhrenDaten[i][7];
					String s_A_NAME3 = cFuhrenDaten[i][8];
					String s_A_STRASSE = cFuhrenDaten[i][9];
					String s_A_HAUSNUMMER = cFuhrenDaten[i][10];
					String s_A_PLZ = cFuhrenDaten[i][11];
					String s_A_ORT  = cFuhrenDaten[i][12];
					String s_L_NAME1  = cFuhrenDaten[i][13];
					String s_L_NAME2  = cFuhrenDaten[i][14];
					String s_L_NAME3 = cFuhrenDaten[i][15];
					String s_L_STRASSE = cFuhrenDaten[i][16];
					String s_L_HAUSNUMMER = cFuhrenDaten[i][17];
					String s_L_PLZ = cFuhrenDaten[i][18];
					String s_L_ORT  = cFuhrenDaten[i][19];
					String s_TRANSPORTKENNZEICHEN  = cFuhrenDaten[i][20]; 
					String s_ANHAENGERKENNZEICHEN   = cFuhrenDaten[i][21];
					String s_ANTEIL_ABLADEMENGE_ABN = cFuhrenDaten[i][22];
					String s_ANTEIL_ABLADEMENGE_LIEF = cFuhrenDaten[i][23];
					String s_EK_ID_ARTIKEL = cFuhrenDaten[i][24];
					String s_VK_ID_ARTIKEL = cFuhrenDaten[i][25];
					String s_ID_ARTIKEL_BEZ_EK = cFuhrenDaten[i][26];
					String s_ID_ARTIKEL_BEZ_VK = cFuhrenDaten[i][27];
					String s_BUCHUNGSNR_FUHRE = cFuhrenDaten[i][28];
					String s_K1_ID_ADRESSE = cFuhrenDaten[i][29];
					String s_ID_ADRESSE_SPEDITION  = cFuhrenDaten[i][30];

					
					String sIstStrecke = (	!s_ID_ADRESSE_START.equals(bibALL.get_ID_ADRESS_MANDANT()) 
										&& 	!s_ID_ADRESSE_ZIEL.equals(bibALL.get_ID_ADRESS_MANDANT()) ) ? "Y" : "N";
					
					
					
					String Lademenge = null;
					if (m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_ZIEL)){
//					if (m_ID_Adressen_Lager.contains(s_ID_ADRESSE_ZIEL)){
														Lademenge = s_ANTEIL_ABLADEMENGE_ABN;
					} else if (m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_START)){
//					} else if (m_ID_Adressen_Lager.contains(s_ID_ADRESSE_START)){
						Lademenge = s_ANTEIL_ABLADEMENGE_LIEF;
					}
					
					String sIDArtikel = s_EK_ID_ARTIKEL; 
//					if ( m_ID_Adressen_Lager.contains(s_ID_ADRESSE_START) ) {
					if ( m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_START) ) {
						 sIDArtikel = s_VK_ID_ARTIKEL; 
					}
					
					String sIDArtikelBez = s_ID_ARTIKEL_BEZ_EK; 
					if ( m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_START) ) {
//					if ( m_ID_Adressen_Lager.contains(s_ID_ADRESSE_START) ) {
						 sIDArtikelBez = s_ID_ARTIKEL_BEZ_VK; 
					}
					
					String sIDAdresseSped = s_K1_ID_ADRESSE != null ? s_K1_ID_ADRESSE : s_ID_ADRESSE_SPEDITION; 
					
					oFuhrenInfo.setID_VPOS_TPA_FUHRE(s_ID_VPOS_TPA_FUHRE);
					oFuhrenInfo.setID_VPOS_TPA_FUHREN_ORT(s_ID_VPOS_TPA_FUHRE_ORT);
//					oFuhrenInfo.setID_ADRESSE_START(s_ID_ADRESSE_START);
//					oFuhrenInfo.setID_ADRESSE_ZIEL(s_ID_ADRESSE_ZIEL);
					oFuhrenInfo.setID_ADRESSE_START(s_ID_ADRESSE_LAGER_START);
					oFuhrenInfo.setID_ADRESSE_ZIEL(s_ID_ADRESSE_LAGER_ZIEL);
					
					
//					String IST_Lieferung = m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_START) ? "N" : "Y";
					String IST_Lieferung = m_ID_Adressen_Lager.contains(s_ID_ADRESSE_LAGER_START) ? "N" : "Y";
					oFuhrenInfo.setIST_LIEFERANT(IST_Lieferung);
					oFuhrenInfo.setNAME1( IST_Lieferung.equals("N") ? s_A_NAME1 : s_L_NAME1 );
					oFuhrenInfo.setNAME2( IST_Lieferung.equals("N") ? s_A_NAME2 : s_L_NAME2 );
				 	oFuhrenInfo.setNAME3( IST_Lieferung.equals("N") ? s_A_NAME3 : s_L_NAME3 );
				 	oFuhrenInfo.setSTRASSE( IST_Lieferung.equals("N") ? s_A_STRASSE : s_L_STRASSE );
				 	oFuhrenInfo.setHAUSNUMMER( IST_Lieferung.equals("N") ? s_A_HAUSNUMMER : s_L_HAUSNUMMER );
				 	oFuhrenInfo.setPLZ( IST_Lieferung.equals("N") ? s_A_PLZ : s_L_PLZ );
				 	oFuhrenInfo.setORT( IST_Lieferung.equals("N") ? s_A_ORT : s_L_ORT );
				 	
				 	oFuhrenInfo.setTRANSPORTKENNZEICHEN(s_TRANSPORTKENNZEICHEN);
				 	oFuhrenInfo.setANHAENGERKENNZEICHEN(s_ANHAENGERKENNZEICHEN);
				 	oFuhrenInfo.setLADEMENGE(Lademenge);
				 	oFuhrenInfo.setABLADEMENGE_ABN(s_ANTEIL_ABLADEMENGE_ABN);
				 	oFuhrenInfo.setABLADEMENGE_LIEF(s_ANTEIL_ABLADEMENGE_LIEF);
				 	oFuhrenInfo.setID_SORTE(sIDArtikel);
				 	oFuhrenInfo.setID_ARTIKEL_BEZ(sIDArtikelBez);
				 	oFuhrenInfo.setBUCHUNGSNUMMER(s_BUCHUNGSNR_FUHRE);
				 	oFuhrenInfo.setID_ADRESSE_SPEDITION(sIDAdresseSped);
				 	
				 	oFuhrenInfo.setIST_STRECKE(sIstStrecke);
				 	
					oFuhrenInfo.setSTRECKE_NAME1( s_A_NAME1 );
					oFuhrenInfo.setSTRECKE_NAME2( s_A_NAME2 );
				 	oFuhrenInfo.setSTRECKE_NAME3( s_A_NAME3);
				 	oFuhrenInfo.setSTRECKE_STRASSE(s_A_STRASSE);
				 	oFuhrenInfo.setSTRECKE_HAUSNUMMER(s_A_HAUSNUMMER);
				 	oFuhrenInfo.setSTRECKE_PLZ(s_A_PLZ);
				 	oFuhrenInfo.setSTRECKE_ORT(s_A_ORT);
				 	
				 	// die aktuelle Liste der Läger nochmal ablegen, damit man sie beim Aufrufer verarbeiten kann
				 	oFuhrenInfo.setLagerListe(m_ID_Adressen_Lager);
				 	
				 	oInfos.add(oFuhrenInfo);
			}
		}
		
		return oInfos;
	}
	
	
	
	
	
	
}
