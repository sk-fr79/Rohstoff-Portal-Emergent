package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.query.DELETE;
import panter.gmbh.indep.dataTools.query.ID;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.dataTools.query.Query;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.U;
import panter.gmbh.indep.dataTools.query.UPDATE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.Filter;
import panter.gmbh.indep.filter.RuleFactory;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.AccountRule;
/**
 * Database logic for the {@see DatevExporter}. Mostly static calls
 * that encapsulate the generation and issueing of SQL calls 
 * and returns HashMaps of that data accordingly.
 * @author nils
 *
 */
@SuppressWarnings("unused")
public class DBUtil {
	private static String driverClass = "oracle.jdbc.driver.OracleDriver";
	public static String DATUMSFELD_ZUR_SUCHE_OFFENER_MONATE = "druckdatum";
	public static String DATUMSFELD2_ZUR_SUCHE_OFFENER_MONATE = "letzte_aenderung";
	
	/** 
	 * Deletes a whole export set 
	 * @param id the export id identifying the set to be deleted
	 * @throws myException
	 */
	public static void deleteExport(int id) throws myException {
		String idMandant = bibALL.get_RECORD_MANDANT()
				.get_ID_MANDANT_cUF_NN("");
		bibDB.ExecSQL(new DELETE().from(_DB.EXPORT_LOG).where("id_export_log", id).and("id_mandant", idMandant).toString(), false);
		bibDB.ExecSQL(new DELETE().from(_DB.VKOPF_EXPORT_RG).where("id_export_log", id).and("id_mandant", idMandant).toString(), false);
		bibDB.ExecSQL(new DELETE().from(_DB.VPOS_EXPORT_RG).where("id_export_log", id).and("id_mandant", idMandant).toString(), true);
	}

	/**
	 * Generates an Export ID for the given date range (and therefore, adds a row to 
	 * _DB.EXPORT_LOG relation) and puts all "jt_vkopf_rg" entries that match the 
	 * date range into the _DB.VKOPF_EXPORT_RG relation (first substep); 
	 * then it pulls all "jt_vpos_rg" for the exported VKopfs into the
	 * _DB.VPOS_EXPORT_RG relation (second substep);  
	 * @param start the Date to start with
	 * @param end the Date to end with (exclusively)
	 * @param comment the comment to be written to jt_export_log, identifying 
	 *        the export for a human
	 * @return the id of the generated export, or 0 in case of failure
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException
	 */
	static int generateExport(final Date start, final Date end,
			final String comment) throws SQLException, 
			myException {

		String idMandant = bibALL.get_RECORD_MANDANT()
				.get_ID_MANDANT_cUF_NN("");
		String idUser = bibALL.get_RECORD_USER().get_ID_USER_cUF_NN("");

		int idExportLog = 0;

		if (Config.instance().flushExportTablesBeforeExporting()) {
//			bibDB.ExecSQL(new DELETE().from(_DB.EXPORT_LOG).toString(), false);
//			bibDB.ExecSQL(new DELETE().from(_DB.VKOPF_EXPORT_RG).toString(), false);
//			bibDB.ExecSQL(new DELETE().from(_DB.VPOS_EXPORT_RG).toString(), true);
		}
		/*
		 * 1. Schritt: Erzeuge Export-ID und fülle die Exportlauf-Metadaten
		 */
		INSERT i0 = new INSERT("id_export_log", "erzeugt_von", "erzeugt_am", "bemerkungen", "von", "bis").into(_DB.EXPORT_LOG).
				values(new U("seq_export_log.NEXTVAL"),idUser, new U("CURRENT_DATE"), comment, start, end);
		
		bibDB.ExecSQL(i0.toString(), true);

		// Obtain the inserted update ID
		String strExportLog = bibDB.EinzelAbfrage(new SELECT("seq_export_log.currval").from("DUAL").toString());
		idExportLog = Integer.parseInt(strExportLog); 
		
		if (idExportLog > 0) {
			System.out.println("Generated update id " + idExportLog);

			/*
			 * 2. Schritt: Alle benötigten Rechnungsköpfe werden
			 * exportiert. Hier muss noch die WHERE-Condition rein, die
			 * checkt, welche Rechnungen tatsächlich zu übernehmen sind;
			 * die Query fügt nur solche Rechnungsköpfe ein, die nicht
			 * bereits früher eingefügt wurden.
			 */

			INSERT i1 = new INSERT("id_vkopf_export_rg", "id_mandant", "id_export_log", "id_adresse")
				.into(_DB.VKOPF_EXPORT_RG)
				.select(
					new SELECT("vkopf.id_vkopf_rg", idMandant, idExportLog, "vkopf.id_adresse")
					.from("jt_vkopf_rg vkopf")
					.where("vkopf.abgeschlossen", "Y")
					.and("vkopf.buchungsnummer", "ISNOT", null)
					.and("vkopf.id_mandant", idMandant)
					.and("vkopf.id_vkopf_rg", "NOTIN", new SELECT("jt.id_vkopf_export_rg").from("jt_vkopf_export_rg jt"))
					
/*					.and("vkopf.druckdatum", ">=", start)
					.and("vkopf.druckdatum", "<=", end) 
					*/
					.and("vkopf.druckdatum", ">=", start).or("vkopf.letzte_aenderung", ">=", start) 
					.and("vkopf.druckdatum", ">=", start).or("vkopf.letzte_aenderung", "<=", end)
					.and("vkopf.druckdatum", "<=", end).or("vkopf.letzte_aenderung", ">=", start)
					.and("vkopf.druckdatum", "<=", end).or("vkopf.letzte_aenderung", "<=", end)

				);
			bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(i1.toString(), true);
			
			/*
			 * 3. Schritt: Anhand der für den Export vorgesehenen Köpfe
			 * werden jetzt die Posten übernommen, und auch wieder nur
			 * solche, die noch nicht in den Export übernommen wurden.
			 */
			INSERT i2 = new INSERT("id_vpos_export_rg", "id_vkopf_rg", "id_mandant", "id_export_log").into(_DB.VPOS_EXPORT_RG).select(
				new SELECT("vpos.id_vpos_rg", "vpos.id_vkopf_rg", idMandant, idExportLog)
				.from("jt_vpos_rg", "vpos")
				.where("vpos.deleted", "!=", "Y")
				.and("id_vkopf_rg", "IN", 
					new SELECT("id_vkopf_export_rg").from(_DB.VKOPF_EXPORT_RG)
					.where("id_export_log", idExportLog).and("id_mandant", idMandant)
					.and("id_vkopf_export_rg",  "NOT IN", 
						new SELECT("id_vkopf_rg").from(_DB.VPOS_EXPORT_RG)
					)
				)
			);

			bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(i2.toString(), true);
		}
		return idExportLog;
	}
	
	/**
	 * Obtains the data that has been written by the {@link generateExport} to the 
	 * export relations and uses this to apply account finding logics. "Raw" in this
	 * terminology means that raw Hashmaps are returned.
	 * @param ep the ExportProperties identifying the entries to be retured
	 * @return the data to apply account finding logics upon
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> returnEntriesRaw(ExportProperties ep, int id_vpos_rg) throws SQLException, myException {
		return returnEntriesRaw(ep, id_vpos_rg, false);
	}

	public static SELECT returnEntriesRawSQL(ExportProperties ep, int id_vpos_rg, boolean noRecords) {
		// "fi.umsatzsteuerid", "fi.umsatzsteuerlkz"
		// Korrigiert: Nimm UST-Id aus dem Kopf 
		SELECT s = new SELECT("kopf.*", "pos.*",  
				new U("pos.anr1 || '-' || pos.anr2 AS anr"), 
				new U("CASE WHEN kopf.umsatzsteuerlkz IS NULL THEN fi.umsatzsteuerlkz ELSE kopf.umsatzsteuerlkz END AS umsatzsteuerlkz"), 
				new U("CASE WHEN kopf.umsatzsteuerid IS NULL THEN fi.umsatzsteuerid ELSE kopf.umsatzsteuerid END AS umsatzsteuerid"), 
				
				// New 23.06.2015 (nilsandre): Alle vier neuen U(...) Konstrukte eingefügt: fibu_preis, fibu_preis_fw, fibu_kundentyp, fibu_leistung
				new U("gesamtpreis - NVL(gpreis_abz_mge, 0) - NVL(gpreis_abz_auf_nettomge, 0) AS fibu_preis"), 
				new U("gesamtpreis_fw - NVL(gpreis_abz_mge_fw, 0) - NVL(gpreis_abz_auf_nettomge_fw, 0) AS fibu_preis_fw"),
				new U("CASE WHEN (fi.privat = 'Y') THEN 'pr' ELSE 'fi' END AS fibu_kundentyp"), 
				new U(
					"CASE WHEN ("
						+ "(gesamtpreis - NVL(gpreis_abz_mge, 0) - NVL(gpreis_abz_auf_nettomge, 0)) < 0 "
						+ "AND pos.lager_vorzeichen = 1 AND NVL(a.end_of_waste, 'N') = 'N' AND NVL(a.dienstleistung, 'N') = 'N' AND NVL(a.ist_produkt, 'N') = 'N') THEN 'DL' "
					+ "ELSE (" 
				    + "CASE " 
				       + " WHEN (NVL(ist_produkt, 'N') = 'Y') THEN 'W' "
				       + " WHEN (NVL(dienstleistung, 'N') = 'Y') THEN 'DL' "
				       + " WHEN (NVL(end_of_waste, 'N') = 'Y') THEN 'W' "
				       + " ELSE 'W' /*Schrott*/"
				    +" END " 
				   +" ) END AS fibu_leistung"),
				// 2015-06-23: Fuhren-Start und Ziel-Land
				"land_ad_fu_start.laendercode AS fuhre_start_land",
				"land_ad_fu_ziel.laendercode AS fuhre_ziel_land",
			   
				"fi.kreditor_nummer",
				"fi.kreditor_nummer",
				"fi.debitor_nummer", "a.dienstleistung", "a.ist_produkt",
				"fi.privat",
				"fi.privat_mit_ustid", "fi.firma", "fi.firma_ohne_ustid", 
				"la.laendercode AS adresse_laendercode",
				"currency.kurzbezeichnung AS waehrung_fremd",
				
				"fk1.konto AS tax_konto_re",
				"fk2.konto AS tax_konto_gs"
				
				
				)
				.from("jt_vkopf_rg", "kopf")
				.join("jt_vpos_rg", "pos")
					.on("pos.id_vkopf_rg", "=", new ID("kopf.id_vkopf_rg"));
		
		if (ep != null && ep.getId() != 0) {
			s.join(_DB.VPOS_EXPORT_RG, "ex_pos")
				.on("pos.id_vpos_rg", "=", new ID("ex_pos.id_vpos_export_rg"))
			.join(_DB.VKOPF_EXPORT_RG, "ex_kopf")
				.on("kopf.id_vkopf_rg", "=", new ID("ex_kopf.id_vkopf_export_rg"));
		}
		
		s.join("jd_waehrung", "currency")
			.on("currency.id_waehrung", "=", new ID("pos.id_waehrung_fremd"))
		.join("jt_artikel", "a")
			.on("pos.id_artikel", "=", new ID("a.id_artikel"))
		.leftJoin("jt_firmeninfo", "fi")
			.on("kopf.id_adresse", "=", new ID("fi.id_adresse"))
		.leftJoin("jt_adresse", "ad")
			.on("kopf.id_adresse", "=", new ID("ad.id_adresse"))
		.leftJoin("jd_land", "la")
			.on("ad.id_land", "=", new ID("la.id_land"))

		.leftJoin("jt_tax", "ta")
			.on("pos.id_tax", "=", new ID("ta.id_tax"))
			.leftJoin("jt_fibu_konto", "fk1")
				.on("ta.id_fibu_konto_re", "=", new ID("fk1.id_fibu_konto"))
			.leftJoin("jt_fibu_konto", "fk2")
				.on("ta.id_fibu_konto_gs", "=", new ID("fk2.id_fibu_konto"))
			
		// Addon 23.06.2015: Fuhrendaten
		.leftJoin("jt_vpos_tpa_fuhre", "fu")
			.on("pos.id_vpos_tpa_fuhre_zugeord", "=", new ID("fu.id_vpos_tpa_fuhre"))
			.leftJoin("jt_adresse", "ad_fu_start")
				.on("fu.id_adresse_start", "=", new ID("ad_fu_start.id_adresse"))
				.leftJoin("jd_land", "land_ad_fu_start")
					.on("land_ad_fu_start.id_land", "=", new ID("ad_fu_start.id_land"))

			.leftJoin("jt_adresse", "ad_fu_ziel")
				.on("fu.id_adresse_ziel", "=", new ID("ad_fu_ziel.id_adresse"))
				.leftJoin("jd_land", "land_ad_fu_ziel")
					.on("land_ad_fu_ziel.id_land", "=", new ID("ad_fu_ziel.id_land"))
	
		.orderBy("kopf.id_vkopf_rg", "pos.positionsnummer").asc();
		
		
		s.where();
		
		if (ep != null) {
			if (ep.getId() != 0) {
				s.where("ex_kopf.id_export_log", ep.getId())
				.and("ex_pos.id_export_log", new ID("ex_kopf.id_export_log"));
			} else {
				if (ep.getStart() != null && ep.getEnd() != null) {
					// My: ((dgs && dke) || (ags && ake))
					// MyCNF: ((dgs || agn) && (dgs || ake) && (dke || ags) && (dke || ake))
					
					// Dies ist geändert, so dass auch das letzte Änderungsdatum in den entsprechenden Selektions-
					// Zeitraum mit einbezogen wird
					s.where("kopf.druckdatum", ">=", ep.getStart()).and("kopf.druckdatum", "<=", ep.getEnd()); 

//					s.where("kopf.druckdatum", ">=", ep.getStart()).or("kopf.letzte_aenderung", ">=", ep.getStart()) 
//						.and("kopf.druckdatum", ">=", ep.getStart()).or("kopf.letzte_aenderung", "<=", ep.getEnd())
//						.and("kopf.druckdatum", "<=", ep.getEnd()).or("kopf.letzte_aenderung", ">=", ep.getStart())
//						.and("kopf.druckdatum", "<=", ep.getEnd()).or("kopf.letzte_aenderung", "<=", ep.getEnd());
				}
				// Diese beiden fälle wurden um das == null in der 2'ten Komponente ergänzt
				if (ep.getStart() != null && ep.getEnd() == null) {
					s.where("kopf.druckdatum", ">=", ep.getStart());
				}
				if (ep.getEnd() != null && ep.getStart() == null) {
					s.where("kopf.druckdatum", "<=", ep.getEnd());
				}
			}
		} else {
			if (id_vpos_rg != 0) {
				s.where("pos.id_vpos_rg", id_vpos_rg);
			}
		}
		if (noRecords) {
			s.and("1", "2");
		}
		s.and("pos.deleted", "!=", "Y");
		
		return s;
	}

	public static ArrayList<HashMap<String, Object>> returnEntriesRaw(ExportProperties ep, int id_vpos_rg, boolean noRecords) throws SQLException, myException {
		SELECT sel = returnEntriesRawSQL(ep, id_vpos_rg, noRecords);
		String sql = sel.toString(); 
		ArrayList<HashMap<String, Object>> result;
		System.out.println(sql);
		return select(sql);
	}
	
	private static ArrayList<ExportBuchung> returnEntries(
			ExportProperties ep, int id_vpos_rg) throws SQLException, myException {
		ArrayList<HashMap<String, Object>> result = returnEntriesRaw(ep, id_vpos_rg);
		ArrayList<ExportBuchung> answer = new ArrayList<ExportBuchung>();
		for (HashMap<String, Object> entry : result) {
			ExportBuchung e = new ExportBuchung().fromDBEntry(entry);
			answer.add(e);
		}
		return answer;
	}
	
	public static ArrayList<ExportBuchung> returnEntries(ExportProperties ep) throws SQLException, myException {
		return returnEntries(ep, 0);
	}
	
	/**
	 * Methods for obtaining a single Entry
	 * @param id_vpos_rg
	 * @return
	 * @throws SQLException
	 * @throws myException
	 */
	public static ExportBuchung returnEntry(int id_vpos_rg) throws SQLException, myException {
		return returnEntries(null, id_vpos_rg).get(0);
	}
	public static ArrayList<HashMap<String, Object>> returnEntry(String... belegnummer) throws SQLException, myException {
		SELECT s = returnEntriesRawSQL(null, 0, false);
		s.where("buchungsnummer", "IN", belegnummer);
		String sql = s.toString();
		System.out.println(sql);
		ArrayList<HashMap<String, Object>> result = bibDB.get_Native_DataObjects(sql, 0, true);
		return result;
	}

	public static HashMap<String, Object> returnEntryRaw(int id_vpos_rg) throws SQLException, myException {
		return returnEntriesRaw(null, id_vpos_rg).get(0);
	}


	/**
	 * Called by {@link DatevExporter} upon materialization of an Export; all logics
	 * already have been applied, so this is just to obtain the data for writing.
	 * @param ep the {@ExportProperties} for the selected Export run
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException
	 */
	public static ArrayList<ExportBuchung> returnDataForWrite(
			ExportProperties ep) throws SQLException, myException {
		SELECT s = new SELECT("e.*", "kopf.*", "pos.summe", "pos.konto", 
				"pos.gegenkonto", "pos.buchungsnummer", "pos.waehrung", "pos.druckdatum", "pos.id_vkopf_rg", "pos.id_vpos_export_rg AS id_vpos_rg")
			.from(_DB.EXPORT_LOG, "e")
			.join("jt_vkopf_export_rg kopf")
				.on("e.id_export_log", "kopf.id_export_log")
			.join(_DB.VPOS_EXPORT_RG, "pos")
				.on("kopf.id_vkopf_export_rg", "pos.id_vkopf_rg")
			.where("e.id_export_log", ep.getId())
			.orderBy("pos.buchungsnummer").asc()
			.orderBy("pos.id_vpos_export_rg").asc()
		;
		
		String sql = s.toString();
		ArrayList<HashMap<String, Object>> result = bibDB.get_Native_DataObjects(sql, 0, true);
		ArrayList<ExportBuchung> answer = new ArrayList<ExportBuchung>();
		for (HashMap<String, Object> entry : result) {
			ExportBuchung e = new ExportBuchung().fromExportedEntry(entry);
			answer.add(e);
		}
		return answer;
	}

	/**
	 * Called by {@link DatevExporter} upon materialization of an Export; all addresses
	 * that are contained in the export Set that have not yet been exported are now
	 * queried
	 * @param ep the {@ExportProperties} for the selected Export run
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException
	 */
	public static ArrayList<ExportAddress> returnAddressDataForWrite(
			ExportProperties ep) throws SQLException, myException {
		SELECT s = new SELECT(
				"a.*", "fi.*", "l.*",
				new SELECT(
					new U("K.WERT_LAENDERVORWAHL || ' ' || K.WERT_VORWAHL || ' ' || K.WERT_RUFNUMMER AS NUMMER"))
					.from("JT_KOMMUNIKATION", "K")
					.join("JT_KOMMUNIKATIONS_TYP", "KT")
					.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
					.where("K.ID_KOMMUNIKATION", 
							new SELECT(new U("MIN(K.ID_KOMMUNIKATION)"))
							.from("JT_KOMMUNIKATION", "K")
							.join("JT_KOMMUNIKATIONS_TYP", "KT")
							.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
							.where(new U("NVL(KT.IST_TEL_NUMMER,'N')"), "Y")
							.and("K.ID_ADRESSE", new ID("A.ID_ADRESSE"))
					)
					.as("TEL"),
				new SELECT(new U("K.WERT_LAENDERVORWAHL || ' ' || K.WERT_VORWAHL || ' ' || K.WERT_RUFNUMMER AS NUMMER"))
					.from("JT_KOMMUNIKATION", "K")
					.join("JT_KOMMUNIKATIONS_TYP", "KT")
					.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
					.where("K.ID_KOMMUNIKATION", 
							new SELECT(new U("MIN(K.ID_KOMMUNIKATION)"))
							.from("JT_KOMMUNIKATION", "K")
							.join("JT_KOMMUNIKATIONS_TYP", "KT")
							.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
							.where(new U("NVL(KT.IST_FAX_NUMMER,'N')"), "Y")
							.and("K.ID_ADRESSE", new ID("A.ID_ADRESSE"))
					)
					.as("FAX"), 
				new SELECT(new U("K.WERT_LAENDERVORWAHL || ' ' || K.WERT_VORWAHL || ' ' || K.WERT_RUFNUMMER AS NUMMER"))
					.from("JT_KOMMUNIKATION", "K")
					.join("JT_KOMMUNIKATIONS_TYP", "KT")
					.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
					.where("K.ID_KOMMUNIKATION", 
							new SELECT(new U("MIN(K.ID_KOMMUNIKATION)"))
							.from("JT_KOMMUNIKATION", "K").join("JT_KOMMUNIKATIONS_TYP", "KT")
							.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
							.where(new U("NVL(KT.IST_TEL_NUMMER,'N')"), "Y")
							.and(new U("NVL(K.IST_STANDARD,'N')"), "Y")
							.and("K.ID_ADRESSE", new ID("A.ID_ADRESSE"))
					)
					.as("STD_TEL"), 
				new SELECT(new U("K.WERT_LAENDERVORWAHL || ' ' || K.WERT_VORWAHL || ' ' || K.WERT_RUFNUMMER AS NUMMER"))
					.from("JT_KOMMUNIKATION", "K")
					.join("JT_KOMMUNIKATIONS_TYP", "KT")
					.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
					.where("K.ID_KOMMUNIKATION", 
							new SELECT(new U("MIN(K.ID_KOMMUNIKATION)"))
							.from("JT_KOMMUNIKATION", "K")
							.join("JT_KOMMUNIKATIONS_TYP", "KT")
							.on("KT.ID_KOMMUNIKATIONS_TYP", "K.ID_KOMMUNIKATIONS_TYP")
							.where(new U("NVL(KT.IST_FAX_NUMMER,'N')"), "Y")
							.and(new U("NVL(K.IST_STANDARD,'N')"), "Y")
							.and("K.ID_ADRESSE", new ID("A.ID_ADRESSE"))
					)
					.as("STD_FAX")
			).from("jt_firmeninfo", "fi")
			.join("jt_adresse", "a")
				.on("a.id_adresse", "fi.id_adresse")
			.leftJoin("jd_land", "l")
				.on("a.id_land", "l.id_land")
			.where("fi.id_adresse", "IN", 
				new SELECT().distinct("id_adresse").from(_DB.VKOPF_EXPORT_RG, "kopf").where("id_export_log", ep.getId())
			);
		
		String sql = s.toString();
		ArrayList<HashMap<String, Object>> result = bibDB.get_Native_DataObjects(sql, 0, true);
		ArrayList<ExportAddress> answer = new ArrayList<ExportAddress>();
		for (HashMap<String, Object> entry : result) {
			ExportAddress a = new ExportAddress().fromDBEntry(entry);
			answer.add(a);
		}
		return answer;
	}
	
	/**
	 * Helper to execute a SELECT statement and return it's results appropriately.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException 
	 */
	public static ArrayList<HashMap<String, Object>> select(String sql)
			throws SQLException, myException {
		return bibDB.get_Native_DataObjects(sql, 0, true);
	}

	public static ArrayList<HashMap<String, Object>> select(SELECT s) 
			throws SQLException, myException {
		return select(s.toString());
	}

	public static HashMap<String, Object> selectOne(String sql)
			throws SQLException, myException {
		return select(sql).get(0);
	}

	public static HashMap<String, Object> selectOne(SELECT s) 
			throws SQLException, myException {
		return select(s).get(0);
	}

	public static boolean query(String q) 
			throws SQLException {
			return bibDB.ExecSQL(q, true);
		
	}
	public static boolean query(Query q) 
			throws SQLException, myException {
			return query(q.toString());
		
	}
	/**
	 * Get the new rules from database
	 * @param test
	 * @return
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> getAccountRules()
			throws SQLException, myException {
		return getAccountRules(true);
	}
	public static ArrayList<HashMap<String, Object>> getAccountRules(boolean activeOnly)
				throws SQLException, myException {
		String idMandant; 
		idMandant = bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF_NN("");

		SELECT s = new SELECT("rule.*").from("jt_fibu_kontenregel_neu rule").where("rule.id_mandant", idMandant);
		if (activeOnly) {
			s.and("rule.aktiv", "Y");
		}
		ArrayList<HashMap<String, Object>> answer = select(s.toString());
		return answer;
	}

	/**
	 * Called by AccountFinder to get all accounts that need to be added to the
	 * VPos entries
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> getAccounts()
			throws SQLException, myException {
		String idMandant; 
		idMandant = bibALL.get_RECORD_MANDANT()
			.get_ID_MANDANT_cUF_NN("");
		return select(new SELECT("*").from("jt_fibu_konto", "account").where("id_mandant", idMandant).toString());
	}

	/** 
	 * This is called upon startup of FIBU_EXPORT module, to find all exports that have
	 * been done so far (they are displayed in the SelectField).
	 * If the {@see Config} file has a first old export id set to be printed, only
	 * IDs larger that the one configured are printed to the list (to prevent overflow).
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> getOldExports()
			throws SQLException, myException {
		String idMandant = bibALL.get_RECORD_MANDANT()
				.get_ID_MANDANT_cUF_NN("");
		
		SELECT s = new SELECT("*").from(_DB.EXPORT_LOG).where("id_mandant", idMandant);
		
		String lastExportIdToPrint = Config.instance().getFirstOldExportId();
		if (lastExportIdToPrint != null && !lastExportIdToPrint.equals("")) {
			s.where("id_export_log", ">=", lastExportIdToPrint);
		}
		s.orderBy("id_export_log").desc();
		return select(s.toString());
	}
	
	/**
	 * This is called upon startup of the FIBU_EXPORT module, it returns all (month/year)
	 * pairs for which VKopf-data is present which has not yet been exported. These 
	 * will be displayed in the SelectField as possible "Neuexporte" (new exports).
	 * See the {@see Config#getFirstRelevantVkopfExportDate()} to limit the old
	 * months in the list to prevent overflow. 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws myException
	 */
	public static ArrayList<HashMap<String, Object>> getAllExportRelevantMonts() 
		throws myException, SQLException {
		String idMandant = bibALL.get_RECORD_MANDANT()
				.get_ID_MANDANT_cUF_NN("");
		
		SELECT sel = new SELECT().distinct(
			new U("TO_CHAR("+DATUMSFELD_ZUR_SUCHE_OFFENER_MONATE+", 'YYYY') AS jahr"),
			new U("TO_CHAR("+DATUMSFELD_ZUR_SUCHE_OFFENER_MONATE+", 'MM') AS monat"),
			new U("TO_CHAR("+DATUMSFELD_ZUR_SUCHE_OFFENER_MONATE+", 'YYYY-MM') AS exportmonat")
			)
			.from("jt_vkopf_rg")
			.where(DATUMSFELD_ZUR_SUCHE_OFFENER_MONATE, ">=", Config.instance().getFirstRelevantVkopfExportDate())
			//.or(DATUMSFELD2_ZUR_SUCHE_OFFENER_MONATE, ">=", Config.instance().getFirstRelevantVkopfExportDate())
			.and("abgeschlossen", "Y")
			.and("id_mandant", idMandant)
			.and("id_vkopf_rg", "NOTIN", new SELECT("id_vkopf_export_rg").from(_DB.VKOPF_EXPORT_RG))
			.orderBy("exportmonat").desc();

		ArrayList<HashMap<String, Object>> result = select(sel.toString());
		return result;
	}

	/**
	 * Marks an Export as already written.
	 * @param idExportLog
	 */
	public static void updateExportAsMaterialized(int idExportLog) {
		bibDB.ExecSQL(new UPDATE(_DB.EXPORT_LOG).set("materialisiert", "Y").where("id_export_log", idExportLog).toString(), true);
	}

	/**
	 * Marks addresses from the vkopf_export_rg table as NULL (if they already have been exported)
	 * @param idExportLog
	 */
	public static void removeAlreadyExportedAdressesFromExportSet(int idExportLog) {
		//TODO: Hier muss noch im zweiten geschachtelten WHERE der Schalter "EXPORTIERT = N" eingebaut werden
		UPDATE u = new UPDATE(_DB.VKOPF_EXPORT_RG)
			.set("id_adresse", null)
			.where("id_export_log", idExportLog)
			.and("id_adresse", "IN", 
					new SELECT("id_adresse").from("jt_firmeninfo").where("1", "3")
			);
		bibDB.ExecSQL(u.toString(), true);
 	}

	/**
	 * This function adds the details the AccountFinder found out on the to-be-materialized 
	 * VPos in the table jt_vpos_export_rg; after every VPos of an export set has been processed,
	 * it is ready to be "looked through" manually and then to be materialized
	 * @param entry
	 * @param matched
	 * @throws SQLException
	 */
	public static void updateVPOS(ExportBuchung entry, AccountRule rule)
			throws SQLException {
		if (entry.getId_vpos_rg().intValue() == 132000) {
			System.out.println(entry);
		}
		
		UPDATE u = new UPDATE(_DB.VPOS_EXPORT_RG)
			.set("id_fibu_kontenregel", (rule != null ? rule.getRuleId() : null))
			.set("konto", entry.getKonto())
			.set("prio", 0) //matched.getPrio()
			.set("gegenkonto", entry.getGegenkonto())
			.set("id_vpos_rg_parent", entry.getId_vpos_parent())
			.set("buchungsnummer", entry.getBuchungsnummer())
			.set("summe", entry.getGesamtpreis())
			.set("waehrung", entry.getWaehrung())
			.set("druckdatum", entry.getDatum())
			.where("id_vpos_export_rg", entry.getId_vpos_rg().longValue());
		bibDB.ExecSQL(u.toString(), true);
	}
	
	/**
	 * Used for virtual taxes; currently not in use
	 * @param idMandant
	 * @param steuersatz
	 * @param land
	 * @param steuervermerk
	 * @return
	 */
	@Deprecated
	public static BigDecimal queryForTaxIdBySteuertext(BigDecimal idMandant, BigDecimal steuersatz, String land, String steuervermerk) {
		try {
			SELECT sel = new SELECT("t.id_tax").from("jt_tax", "t").join("jd_land", "l").on("t.id_land", "l.id_land")
			.where("t.id_tax", ">", "2000")
			.and("t.id_mandant", idMandant.intValue())
			.and("t.steuersatz", steuersatz.intValue())
			.orderBy("id_tax").asc();
			
			if (land == null) {
				sel.and("l.ust_praefix", "IS", null);
			} else {
				sel.and("l.ust_praefix", land);
			}

			if (steuervermerk == null) {
				sel.and("t.steuervermerk", "IS", null);
			} else {
				sel.and("t.steuervermerk", steuervermerk);
			}

			ArrayList<HashMap<String, Object>> result = select(sel.toString());
			
			if (result.size() > 0) {
				// Take the first TaxID we found
				return (BigDecimal)result.get(0).get("ID_TAX");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retrieves a filter (with given ID) from database. The filter in the
	 * Datev logic represents a "rule" that a VPOS is checked against.
	 * @param id
	 * @throws myException 
	 * @throws SQLException 
	 */
	public static ArrayList<HashMap<String, Object>> getFilter (int id) throws SQLException, myException {
		SELECT sel = new SELECT("o.id_filter_and", "condition_left", "condition_op", "condition_right", 
				"condition_left_type", "condition_right_type", "condition_positive")
			.from("jt_filter_or", "o")
			.join("jt_filter_and", "a")
			.on("a.id_filter_and", "o.id_filter_and")
			.join("jt_filter", "f")
			.on("f.id_filter", "a.id_filter");
			
		if (id > 0) {
			sel.where("f.id_filter", id);
		}
		sel.orderBy("o.id_filter_and").asc();
		return select(sel.toString());
	}
	
	
	public static ArrayList<String> getEUTaxIdCodes() throws SQLException, myException {
		SELECT s = new SELECT("ust_praefix").from("jd_land").where("intrastat_jn", "Y");
		ArrayList<HashMap<String, Object>> res = DBUtil.select(s);
		ArrayList<String> answer = new ArrayList<String>(); 

		Iterator<HashMap<String, Object>> ait = res.iterator();
		while (ait.hasNext()) {
			HashMap<String, Object> current = ait.next();
			String tmp = (String)current.get("UST_PRAEFIX");
			if (tmp != null) {
				answer.add(tmp.toUpperCase());
			}
		}
		return answer;
		
	}
}