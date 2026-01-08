package panter.gmbh.indep.dataTools.query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.TestSession;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.SetString;

public class NiceSQLTest extends TestCase {

	@Test
	public void testSelect01() throws SQLException, myException {
		System.out
				.println("TEST 01 ================================================================");
		Query s = new SELECT(new U("*")).from("jt_adresse")
				.where("id_adresse", "=", "10").or("nils", "1")
				.or("martin", "2").and("huh", "nih").or("a", "1").or("b", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect02() throws SQLException, myException {
		System.out
				.println("TEST 02 ================================================================");
		Query s = new SELECT("*")
				.from("jt_adresse")
				.where("id_adresse", "=", "10")
				.and("ex_pos.id_export_log", "=",
						new ID("ex_kopf", "id_export_log")).or("nils", "1")
				.or("martin", "2").and("huh", "nih").or("a", "1").or("b", "2");

		System.out.println(s.toString());

	}

	@Test
	public void testSelect020() throws SQLException, myException {
		System.out
				.println("TEST 020 ===============================================================");
		String p = "1000";
		Query s = new SELECT(new U("COUNT(*)"))
				.from(_DB.ADRESSE)
				.join(_DB.EMAIL)
				.on(_DB.Z_ADRESSE$ID_ADRESSE, "=", _DB.Z_EMAIL$ID_ADRESSE)
				.join(_DB.QUALIFIER)
				.on(_DB.Z_EMAIL$ID_EMAIL, "=", _DB.Z_QUALIFIER$ID_TABLE)
				.where(_DB.Z_ADRESSE$ID_ADRESSE, "=", p)
				.and(_DB.Z_QUALIFIER$TABLENAME, "=", _DB.EMAIL.substring(3))
				.and(_DB.Z_QUALIFIER$CLASS_KEY, "=",
						Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP)
				.and(_DB.Z_QUALIFIER$DATENBANKTAG, "=",
						myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT);
		System.out.println(s.toString());

	}

	@Test
	public void testSelect03() throws SQLException, myException {
		System.out
				.println("TEST 03 ================================================================");
		Query s = new SELECT("kopf.*", "pos.*", "fi.kreditor_nummer",
				"fi.debitor_nummer", "a.dienstleistung", "a.ist_produkt",
				"fi.umsatzsteuerid", "fi.umsatzsteuerlkz", "fi.privat",
				"fi.privat_mit_ustid", "fi.firma", "fi.firma_ohne_ustid",
				"currency.kurzbezeichnung AS waehrung_fremd")
				.from("jt_vkopf_rg", "kopf")
				.join("jt_vpos_rg", "pos")
				.on("pos.id_vkopf_rg", "=", new ID("kopf.id_vkopf_rg"))
				.join("jt_vpos_export_rg", "ex_pos")
				.on("pos.id_vpos_rg", "=", new ID("ex_pos.id_vpos_export_rg"))
				.join("jt_vkopf_export_rg", "ex_kopf")
				.on("kopf.id_vkopf_rg", "=",
						new ID("ex_kopf.id_vkopf_export_rg"))
				.join("jd_waehrung", "currency")
				.on("currency.id_waehrung", "=",
						new ID("pos.id_waehrung_fremd"))
				.join("jt_artikel", "a")
				.on("pos.id_artikel", "=", new ID("a.id_artikel"))
				.leftJoin("jt_firmeninfo", "fi")
				.on("kopf.id_adresse", "=", new ID("fi.id_adresse"))
				.where("ex_kopf.id_export_log", "=", 900)
				.and("ex_pos.id_export_log", "=",
						new ID("ex_kopf.id_export_log"))
				.and("kopf.druckdatum", ">",
						new U("TO_DATE('2014-10-01','YYYY-MM-DD')"))
				.orderBy("kopf.id_vkopf_rg", "pos.positionsnummer").asc();
		System.out.println(s.toString());
	}

	@Test
	public void testSelect04() throws SQLException, myException {
		System.out
				.println("TEST 04 ================================================================");
		Query s = new SELECT("a.*", new SELECT("*").from("world")).as(
				"sonstwas").from("adresse a");
		System.out.println(s.toString());
	}

	@Test
	public void testSelect05() throws SQLException, myException {
		System.out
				.println("TEST 05 ================================================================");
		SELECT s = new SELECT("*").from(
				new SELECT("*").from("country").where("id", 1),
				"aliased_subselect");
		System.out.println(s.toString());
	}

	@Test
	public void testSelect06() throws SQLException, myException {
		System.out
				.println("TEST 06 ================================================================");
		SELECT s = new SELECT("*").from("world").union(
				new SELECT("*").from("mars"));
		System.out.println(s.toString());
	}

	@Test
	public void testSelect07() throws SQLException, myException {
		System.out
				.println("TEST 07 ================================================================");
		SELECT s = new SELECT(new U("*")).from("jt_adresse")
				.where("id_adresse", "=", "10").or("nils", "1")
				.or("martin", "2").and("huh", "nih").or("a", "1").or("b", "2")
				.groupBy("id_adresse").having("id_adresse", ">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect08() throws SQLException, myException {
		System.out
				.println("TEST 08 ================================================================");
		SELECT s = new SELECT("*")
				.from("jt_adresse a")
				.where()
				.not()
				.exists(new SELECT("id_adresse").from("jt_firmeninfo", "fi")
						.where("fi.id_adresse", "=", new ID("a.id_adresse")));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect09() throws SQLException, myException {
		System.out
				.println("TEST 09 ================================================================");
		SELECT s = new SELECT(new U("*")).from("jt_adresse a").where(
				"id",
				"IN",
				new SELECT("id").from("jt_firmeninfo", "fi").where("fi.id",
						"=", new ID("a.id")));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect090() throws SQLException, myException {
		System.out
				.println("TEST 090 ===============================================================");
		SELECT s = new SELECT(new U("*")).from("jt_adresse a").where("id",
				"IN", new SetString("1, 2, 3, 4"));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect10() throws SQLException, myException {
		System.out
				.println("TEST 10 ================================================================");
		Query i = new INSERT().into("jt_adresse a").select(
				new SELECT("id").from("jt_firmeninfo", "fi").where("fi.id",
						"=", new ID("a.id")));
		System.out.println(i.toString());
	}

	@Test
	public void testSelect11() throws SQLException, myException {
		System.out
				.println("TEST 11 ================================================================");
		Query s = new INSERT("id_adresse").into("jt_adresse a").values(1, 2, 3);

		System.out.println(s.toString());
	}

	@Test
	public void testSelect12() throws SQLException, myException {
		System.out
				.println("TEST 12 ================================================================");
		Query s = new INSERT().into("jt_adresse a").select(
				new SELECT("id").from("jt_firmeninfo", "fi").where("fi.id",
						"=", new ID("a.id")));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect13() throws SQLException, myException {
		System.out
				.println("TEST 13 ================================================================");
		Query s = new UPDATE("jt_adresse a").set("id_adresse", "1")
				.set("id_firmeninfo", "21").where("id_adresse", ">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect14() throws SQLException, myException {
		System.out
				.println("TEST 14 ================================================================");
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id_mandant", "1");
		fields.put("id_adresse", "21");
		fields.put("kommentar", "Testkommentar");
		Query s = new UPDATE("jt_adresse a").set(fields).where("id_adresse",
				">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect15() throws SQLException, myException {
		System.out
				.println("TEST 15 ================================================================");
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("id_mandant", 1001);
		fields.put("id_adresse",
				new SELECT("id").from("jt_adressinfo").where("id_adresse", "1"));
		fields.put("kommentar", "Testkommentar");
		Query s = new UPDATE("jt_adresse a").set(fields).where("id_adresse",
				">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect18() throws SQLException, myException {
		System.out
				.println("TEST 18 ================================================================");
		Query s = new DELETE("jt_adresse a").where("id_adresse", ">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect19() throws SQLException, myException {
		System.out
				.println("TEST 19 ================================================================");
		Query s = new DELETE("jt_adresse a").where().exists(
				new SELECT("id_adresse").from("jt_adresse").where("id_adresse",
						"1"));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect20() throws SQLException, myException {
		System.out
				.println("TEST 20 ================================================================");
		Query s = new SELECT("a.*").from("jt_adresse a").where("erzeugt_am",
				"=", new java.sql.Date(14, 11, 31));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect21() throws SQLException, myException {
		System.out
				.println("TEST 21 ================================================================");
		Query s = new SELECT("a.*").from("jt_adresse a").where("erzeugt_am",
				"=", new java.util.Date(14, 11, 31));

		System.out.println(s.toString());
	}

	@Test
	public void testSelect22() throws SQLException, myException {
		System.out
				.println("TEST 22 ================================================================");
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("id_mandant", 1001);
		fields.put("erzeugt_am", java.sql.Date.valueOf("2014-06-30"));
		fields.put("id_adresse",
				new SELECT("id").from("jt_adressinfo").where("id_adresse", "1"));
		fields.put("kommentar", "Testkommentar");
		Query s = new UPDATE("jt_adresse a").set(fields).where("id_adresse",
				">", "2");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect23() throws SQLException, myException {
		System.out
				.println("TEST 23 ================================================================");
		SELECT s = new SELECT("*").from("jt_adresse a").where("id", "IS",
				"NOT_NULL");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect24() throws SQLException, myException {
		System.out
				.println("TEST 24 ================================================================");
		SELECT s = new SELECT("*").from("jt_adresse a").where()
				.not("id", "IS", "NULL").and("a", 121);

		System.out.println(s.toString());
	}

	@Test
	public void testSelect25() throws SQLException, myException {
		System.out
				.println("TEST 25 ================================================================");
		SELECT s = new SELECT("*").from("jt_adresse a").where("strasse",
				"not LIKE", "Hauptstr%");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect26() throws SQLException, myException {
		System.out
				.println("TEST 26 ================================================================");
		Query s = new INSERT("strasse", "plz", "ort").into("jt_adresse a")
				.values("badstr 2", 79098, "FR").values("auweg 7", 71000, "S")
				.values("heupfad 1", 35039, "MR");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect260() throws SQLException, myException {
		System.out
				.println("TEST 260 ===============================================================");
		Query s = new INSERT("id_adresse", "id_mandant").into("jt_adresse")
				.values(999997, 777).values(999998, 777);

		System.out.println(s.toString());
	}

	@Test
	public void testSelect27() throws SQLException, myException {
		System.out
				.println("TEST 27 ================================================================");
		Query s = new INSERT("strasse", "plz", "ort").into("jt_adresse a")
				.values("badstr 2", 79098, "FR");

		System.out.println(s.toString());
	}

	@Test
	public void testSelect28() throws SQLException, myException {
		System.out
				.println("TEST 28 ================================================================");
		SELECT s = new SELECT("*").from("jt_adresse a").where("strasse", "IS",
				null);

		System.out.println(s.toString());
	}

	@Test
	public void testSelect29() throws SQLException, myException {
		System.out
				.println("TEST 29 ================================================================");

		SELECT s = new SELECT("*").from("jt_adresse a").where("strasse",
				"IS NOT", null);
		System.out.println(s.toString());
	}

	@Test
	public void testSelect30() throws SQLException, myException {
		System.out
				.println("TEST 30 ================================================================");

		UPDATE u = new UPDATE("jt_adresse a").set("id_adresse", null).where(
				"strasse", "IS NOT", null);
		System.out.println(u.toString());
	}

	@Test
	public void testSelect31() throws SQLException, myException {
		System.out
				.println("TEST 31 ================================================================");

		UPDATE u = new UPDATE("jt_adresse a").set("id_adresse", 1.21f);
		System.out.println(u.toString());
	}

	@Test
	public void testSelect32() throws SQLException, myException {
		System.out
				.println("TEST 32 ================================================================");

		DELETE u = new DELETE().from("jt_adresse").where("id_mandant",
				new Integer(777));
		System.out.println(u.toString());
	}

	@Test
	public void testSelect33() throws SQLException, myException {
		System.out
				.println("TEST 33 ================================================================");
		String BELEGDATUMSFELD = "druckdatum";
		String idMandant = "1";
		int idExportLog = 291;
		Date start = new Date();
		Date end = new Date();

		Query s = new INSERT("id_vkopf_export_rg", "id_mandant",
				"id_export_log", "id_adresse").into("jt_vkopf_export_rg")
				.select(new SELECT("vkopf.id_vkopf_rg", idMandant, idExportLog,
						"vkopf.id_adresse")
						.from("jt_vkopf_rg vkopf")
						.where("vkopf.druckdatum", ">=", start)
						.and("vkopf.druckdatum", "<=", end)
						.and("vkopf.abgeschlossen", "Y")
						.and("vkopf.buchungsnummer", "ISNOT", null)
						.and("vkopf.id_mandant", idMandant)
						.and("vkopf.id_vkopf_rg",
								"NOTIN",
								new SELECT("jt.id_vkopf_export_rg")
										.from("jt_vkopf_export_rg jt")));

		;

		System.out.println(s.toString());
		String tmpSql = "INSERT INTO jt_vkopf_export_rg (id_vkopf_export_rg, id_mandant, id_export_log, id_adresse) "
				+ "SELECT vkopf.id_vkopf_rg, "
				+ idMandant
				+ ", "
				+ idExportLog
				+ ", vkopf.id_adresse "
				+ "FROM jt_vkopf_rg vkopf WHERE "
				+ (start != null ? "vkopf." + BELEGDATUMSFELD + " >= TO_DATE('"
						+ new java.sql.Date(start.getTime())
						+ "', 'YYYY-MM-DD') AND " : "")
				+ (end != null ? "vkopf." + BELEGDATUMSFELD + " < TO_DATE('"
						+ end.getTime() + "', 'YYYY-MM-DD HH24:MI:SS') AND "
						: "")
				+ "(vkopf.abgeschlossen = 'Y') AND vkopf.buchungsnummer IS NOT NULL AND vkopf.id_mandant = "
				+ idMandant
				+ " "
				+ "AND vkopf.id_vkopf_rg NOT IN ("
				+ "  SELECT jt.id_vkopf_export_rg FROM jt_vkopf_export_rg jt"
				+ ")\n";
		System.out.println("Vergleich: " + tmpSql);

	}

	@Test
	public void testSelect34() throws SQLException, myException {
		System.out
				.println("TEST 34 ================================================================");
		SELECT s = new SELECT(new U("*")).from("jt_adresse a").where("id",
				"IN", new String[] { "1", "2", "3" });

		System.out.println(s.toString());
	}

	@Test
	public void testSelect35() throws SQLException, myException {
		System.out
				.println("TEST 35 ================================================================");
		List<Integer> al = new ArrayList<Integer>();
		al.add(182);
		al.add(178);
		SELECT s = new SELECT(new U("*")).from("jt_adresse a").where("id",
				"IN", al);

		System.out.println(s.toString());
	}

	@Test
	public void testSelect36() throws SQLException, myException {
		System.out
				.println("TEST 36 ================================================================");
		DELETE d = new DELETE("jt_vkopf_export_rg");
		System.out.println(d.toString());
	}

	// Missing SET clause makes us expect the RuntimeException
	@Test(expected = RuntimeException.class)
	public void testSelect37() throws SQLException, myException {
		System.out
				.println("TEST 37 ================================================================");
		UPDATE u = new UPDATE("jt_vkopf_export_rg");
		System.out.println(u.toString());

	}

	@Test(expected = RuntimeException.class)
	public void testSelect38() throws SQLException, myException {
		System.out
				.println("TEST 38 ================================================================");
		UPDATE u = new UPDATE("jt_vkopf_export_rg");
		System.out.println(u.toString());
	}

	@Test
	public void testSelect39() throws SQLException, myException {
		System.out
				.println("TEST 39 ================================================================");
		SELECT u = new SELECT(1, new V("Nils"),
				new V("Test").as("constant_alias")).from("DUAL");
		System.out.println(u.toString());
	}

	@Test
	public void testSelect40() throws SQLException, myException {
		System.out
				.println("TEST 40 ================================================================");
		SELECT u = new SELECT(new ID("v.version").as("renamed_column")).from(
				"version", "v");
		System.out.println(u.toString());
	}

	@Test
	public void testSelect41() throws SQLException, myException {
		System.out
				.println("TEST 41 ================================================================");
		SELECT u01 = new SELECT("*").from("T1").where("id", 1000);
		System.out.println(u01.toString());
		SELECT u02 = new SELECT("*").from("T1").where("id", 100.1210F);
		System.out.println(u02.toString());
		SELECT u03 = new SELECT("*").from("T1").where("id", 1000L);
		System.out.println(u03.toString());
		SELECT u04 = new SELECT("*").from("T1").where("id", 10.00D);
		System.out.println(u04.toString());
	}

	@Test
	public void testSelect42() throws Exception {
		TestSession ses = new TestSession("nils", "nilsandre");
		System.out
				.println("TEST 042 ===============================================================");
		String p = "1000";
		Query s = new SELECT(new U("COUNT(*)"))
				.from(_DB.ADRESSE)
				.join(_DB.EMAIL)
				.on(_DB.Z_ADRESSE$ID_ADRESSE, "=", _DB.Z_EMAIL$ID_ADRESSE)
				.join(_DB.QUALIFIER)
				.on(_DB.Z_EMAIL$ID_EMAIL, "=", _DB.Z_QUALIFIER$ID_TABLE)
				.where(_DB.Z_ADRESSE$ID_ADRESSE, "=", p)
				.and(_DB.Z_QUALIFIER$TABLENAME, "=", _DB.EMAIL.substring(3))
				.and(_DB.Z_QUALIFIER$CLASS_KEY, "=",
						Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP)
				.and(_DB.Z_QUALIFIER$DATENBANKTAG, "=",
						myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT);
		System.out.println(s.toStringSubstituted());
		// Added convenience Methods to extract String parts
		System.out.println("SELECT  = [" + s.getSelect() + "]");
		System.out.println("FROM    = [" + s.getFrom() + "]");
		System.out.println("WHERE   = [" + s.getWhere() + "]");
		System.out.println("ORDERBY = [" + s.getOrderBy() + "]");

	}

	@Test
	public void testSelect43() throws Exception {
		TestSession ses = new TestSession("nils", "nilsandre");
		System.out
				.println("TEST 043 ===============================================================");
		String p = "1000";

		INSERT i0 = new INSERT("id_export_log", "erzeugt_von", "erzeugt_am",
				"bemerkungen").into("jt_export_log").values(
				new U("seq_export_log.NEXTVAL"), 1, new U("CURRENT_DATE"),
				"");

		int idMandant = 1;
		int idExportLog = 9999;
		Date start = new Date();
		Date end = new Date();
		
		INSERT i1 = new INSERT("id_vkopf_export_rg", "id_mandant", "id_export_log", "id_adresse")
		.into("jt_vkopf_export_rg")
		.select(
			new SELECT("vkopf.id_vkopf_rg", idMandant, idExportLog, "vkopf.id_adresse")
				.from("jt_vkopf_rg vkopf")
				.where("vkopf.abgeschlossen", "Y")
				.and("vkopf.buchungsnummer", "ISNOT", null)
				.and("vkopf.id_mandant", idMandant)
				.and("vkopf.id_vkopf_rg", "NOTIN", new SELECT("jt.id_vkopf_export_rg").from("jt_vkopf_export_rg jt"))
				
				.and("vkopf.druckdatum", ">=", start)
				.and("vkopf.druckdatum", "<=", end)
		);
		
		System.out.println(i0.toString());
		System.out.println(i1.toString());

	}
	
	
	@Test
	public void testSelect44() throws Exception {
		TestSession ses = new TestSession("nils", "nilsandre");
		System.out
				.println("TEST 044 ===============================================================");
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
				new SELECT().distinct("id_adresse").from("jt_vkopf_export_rg", "kopf").where("id_export_log", 1111)
		);

	System.out.println(s.toString());

	}
	
	@Test
	public void testIO() {
		Object bi = BigInteger.valueOf(1000);
		System.out.println(bi instanceof BigInteger);
//		System.out.println(bi instanceof BigDecimal);
		System.out.println(bi instanceof Number);
	}
	
	
	@Test
	public void testSelect45() throws Exception {
		TestSession ses = new TestSession("nils", "nilsandre");
		System.out
				.println("TEST 045 ===============================================================");
		UPDATE u = new UPDATE("jt_vpos_export_rg")
		.set("id_fibu_kontenregel", BigDecimal.valueOf(100.10));

		
		System.out.println(u.toString());
	}


	@Test
	public void testTermFactory() throws Exception {
		TestSession ses = new TestSession("nils", "nilsandre");
		System.out
				.println("TEST 046 ===============================================================");

		SELECT sel = new SELECT(Term.field("JTRR", "ID_REPORT_REITER").as("ID"))
				.from(Term.table("JT_REPORT_REITER", "JTRR"))
				.where(Term.field("JTRR","ID_REPORT_REITER"), "=", "1");
		
		System.out.println(sel.toString());
	}
	
	@Test
	public void testSelectWindowingClause01() throws SQLException, myException {
		System.out
				.println("TEST WINDOWING CLAUSE 01 ===============================================");
		
		SELECT s = new SELECT("id").from("jt_adressinfo").where("id_adresse", "1");
		// Include own select as subselect

		
		SELECT s2 = new SELECT("*").from(
				s.select(new U("ROWNUM rn")).where("ROWNUM", "<", 100)
		).where("rn", ">=", 20);

		
		System.out.println(s2.toString());
	}
	
	
	// Possible error with double join (text sheet from Martin Panter, 01.06.2015)
	@Test
	public void testSelectJoinError01() throws SQLException, myException {

		SELECT s1 = new SELECT(); 
		s1.select("DISTINCT "+_DB.Z_ARTIKEL$ARTBEZ1+","+_DB.Z_ARTIKEL$ID_ARTIKEL)
			.from(_DB.VPOS_TPA).join(_DB.ARTIKEL).on(_DB.Z_VPOS_TPA$ID_ARTIKEL, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.where("1", new U("1"))
			.orderBy(_DB.Z_ARTIKEL$ARTBEZ1);

		System.out.println(s1.toString());


		SELECT s2 = new SELECT();
		s2.select("DISTINCT "+_DB.Z_ARTIKEL$ARTBEZ1+","+_DB.Z_ARTIKEL$ID_ARTIKEL)
			.from(_DB.VPOS_TPA).join(_DB.ARTIKEL).on(_DB.Z_VPOS_TPA$ID_ARTIKEL, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.orderBy(_DB.Z_ARTIKEL$ARTBEZ1);
		
		System.out.println(s2.toString());

		
		SELECT s3 = new SELECT("DISTINCT "+_DB.Z_ARTIKEL$ARTBEZ1+","+_DB.Z_ARTIKEL$ID_ARTIKEL)
			.from(_DB.VPOS_TPA).join(_DB.ARTIKEL).on(_DB.Z_VPOS_TPA$ID_ARTIKEL, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.where("1", new U("1"))
			.orderBy(_DB.Z_ARTIKEL$ARTBEZ1);
		
		System.out.println(s3.toString());

		
		SELECT s4 = new SELECT("DISTINCT "+_DB.Z_ARTIKEL$ARTBEZ1+","+_DB.Z_ARTIKEL$ID_ARTIKEL)
			.from(_DB.VPOS_TPA).join(_DB.ARTIKEL).on(_DB.Z_VPOS_TPA$ID_ARTIKEL, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.orderBy(_DB.Z_ARTIKEL$ARTBEZ1);
		
		System.out.println(s4.toString());
		
		assertEquals(s1.toString(), s3.toString());
		assertEquals(s2.toString(), s4.toString());

		
		SELECT s5 = new SELECT(); 
		s5.select().distinct(_DB.Z_ARTIKEL$ARTBEZ1, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.from(_DB.VPOS_TPA).join(_DB.ARTIKEL).on(_DB.Z_VPOS_TPA$ID_ARTIKEL, _DB.Z_ARTIKEL$ID_ARTIKEL)
			.where("1", new U("1"))
			.orderBy(_DB.Z_ARTIKEL$ARTBEZ1);

		System.out.println(s5.toString());
		assertEquals(s1.toString().toLowerCase(), s5.toString().toLowerCase());
		
		
	}

	
	interface E {
		Date getStart();
		Date getEnd();
	}
	
	// Test KNF in where 
	@Test
	public void testDNF2KNF() throws SQLException, myException {
		E ep = new E() {
			public Date getStart() {
				return new Date();
			}
			public Date getEnd() {
				return new Date();
			}
		};
		
		
	
		SELECT s = new SELECT("*").from("test"); 
		s.where("kopf.druckdatum", ">=", ep.getStart()).or("kopf.letzte_aenderung", ">=", ep.getStart()) 
		.and("kopf.druckdatum", ">=", ep.getStart()).or("kopf.letzte_aenderung", "<=", ep.getEnd())
		.and("kopf.druckdatum", "<=", ep.getEnd()).or("kopf.letzte_aenderung", ">=", ep.getStart())
		.and("kopf.druckdatum", "<=", ep.getEnd()).or("kopf.letzte_aenderung", "<=", ep.getEnd());

		System.out.println(s.toString());
	}
}


