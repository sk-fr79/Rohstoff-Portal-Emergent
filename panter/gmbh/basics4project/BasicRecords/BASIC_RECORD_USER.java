package panter.gmbh.basics4project.BasicRecords;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DATEV_PROFILE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BASIC_RECORD_USER extends MyRECORD implements MyRECORD_IF_RECORDS {

	public static String TABLENAME = "JD_USER";

	public BASIC_RECORD_USER() throws myException {
		super();
		this.set_TABLE_NAME("JD_USER");
	}

	public BASIC_RECORD_USER(MyConnection Conn) throws myException {
		super(Conn);
		this.set_TABLE_NAME("JD_USER");
	}

	public BASIC_RECORD_USER(RECORD_USER recordOrig) {
		super(recordOrig);
		this.set_TABLE_NAME("JD_USER");
	}

	public BASIC_RECORD_USER(long lID_Unformated) throws myException {
		super("JD_USER", "ID_USER", "" + lID_Unformated);
		this.set_TABLE_NAME("JD_USER");
	}

	public BASIC_RECORD_USER(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super();
		this.set_TABLE_NAME("JD_USER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL)) {
			this.PrepareAndBuild("*", bibE2.cTO() + ".JD_USER", "ID_USER=" + c_ID_or_WHEREBLOCK_OR_SQL);
		} else {
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT ")) {
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			} else {
				this.PrepareAndBuild("*", bibE2.cTO() + ".JD_USER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}
	}

	public BASIC_RECORD_USER(long lID_Unformated, MyConnection Conn) throws myException {
		super("JD_USER", "ID_USER", "" + lID_Unformated, Conn);

		this.set_TABLE_NAME("JD_USER");
	}

	public BASIC_RECORD_USER(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException {
		super(Conn);
		this.set_TABLE_NAME("JD_USER");

		if (bibALL.isLong(c_ID_or_WHEREBLOCK_OR_SQL)) {
			this.PrepareAndBuild("*", bibE2.cTO() + ".JD_USER", "ID_USER=" + c_ID_or_WHEREBLOCK_OR_SQL);
		} else {
			if (c_ID_or_WHEREBLOCK_OR_SQL.toUpperCase().trim().startsWith("SELECT ")) {
				this.BuildRecord(c_ID_or_WHEREBLOCK_OR_SQL);
			} else {
				this.PrepareAndBuild("*", bibE2.cTO() + ".JD_USER", c_ID_or_WHEREBLOCK_OR_SQL);
			}
		}

	}

	private RECORD_DATEV_PROFILE UP_RECORD_DATEV_PROFILE_id_datev_profile = null;

	// public String get_TABLENAME()
	// {
	// return "JD_USER";
	// }

	public String get_PRIMARY_KEY_NAME() {
		return "ID_USER";
	}

	public String get_PRIMARY_KEY_UF() throws myException {
		return this.get_ID_USER_cUF();
	}

	public long get_PRIMARY_KEY_VALUE() throws myException {
		return this.get_ID_USER_lValue(null).longValue();
	}

	public String get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException {
		MySqlStatementBuilder oSQLStatementBuilder = (bOnlyChangedFields ? this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS() : this.get_StatementBuilderFilledWithActualValues());

		if (oSQLStatementBuilder.size() == 0) // sonst keine aenderungen
		{
			throw new myException(this, "Error: No field in RECORD_USER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString("JD_USER", bibE2.cTO(), "ID_USER=" + this.get_ID_USER_cUF(), vFieldsNotInUpdate);
	}

	public MyE2_MessageVector UPDATE(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException {
		return bibDB.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STATEMENT(vFieldsNotInUpdate, bOnlyChangedFields)), true);
	}

	public MyE2_MessageVector DELETE() throws myException {
		return bibDB.ExecMultiSQLVector(bibALL.get_Vector("DELETE FROM " + bibE2.cTO() + ".JD_USER WHERE ID_USER=" + this.get_ID_USER_cUF()), true);
	}

	public String get_DELETE_STATEMENT() throws myException {
		return "DELETE FROM " + bibE2.cTO() + ".JD_USER WHERE ID_USER=" + this.get_ID_USER_cUF();
	}

	/**
	 * REBUILD wird ueberschrieben, falls der record-datensatz von einer
	 * uebergeordnet recordlist rausgezogen wird dort liegen keine sql-querys
	 * vor. deshalb muessen diese neu definiert werden
	 * 
	 */
	public void REBUILD() throws myException {
		if (S.isEmpty(this.get_cSQL_4_Build())) {
			if (S.isFull(this.get_ID_USER_cUF())) {
				this.PrepareAndBuild("*", bibE2.cTO() + ".JD_USER", "ID_USER=" + this.get_ID_USER_cUF());
			}
		} else {
			super.REBUILD();
		}
	}

	/**
	 * References the Field ID_DATEV_PROFILE Falls keine
	 * this.get_ID_DATEV_PROFILE_cUF() leer ist, wird null zurueckgegeben
	 */
	public RECORD_DATEV_PROFILE get_UP_RECORD_DATEV_PROFILE_id_datev_profile() throws myException {
		if (S.isEmpty(this.get_ID_DATEV_PROFILE_cUF()))
			return null;

		if (this.UP_RECORD_DATEV_PROFILE_id_datev_profile == null) {
			this.UP_RECORD_DATEV_PROFILE_id_datev_profile = new RECORD_DATEV_PROFILE(this.get_ID_DATEV_PROFILE_cUF(), this.get_oConn());
		}
		return this.UP_RECORD_DATEV_PROFILE_id_datev_profile;
	}

	public static String FIELD__AKTIV = "AKTIV";
	public static String FIELD__ANREDE = "ANREDE";
	public static String FIELD__AUTCODE = "AUTCODE";
	public static String FIELD__COLOR_MASK_ERROR_BLUE = "COLOR_MASK_ERROR_BLUE";
	public static String FIELD__COLOR_MASK_ERROR_GREEN = "COLOR_MASK_ERROR_GREEN";
	public static String FIELD__COLOR_MASK_ERROR_RED = "COLOR_MASK_ERROR_RED";
	public static String FIELD__COLOR_MASK_OK_BLUE = "COLOR_MASK_OK_BLUE";
	public static String FIELD__COLOR_MASK_OK_GREEN = "COLOR_MASK_OK_GREEN";
	public static String FIELD__COLOR_MASK_OK_RED = "COLOR_MASK_OK_RED";
	public static String FIELD__COLOR_MASK_WARN_BLUE = "COLOR_MASK_WARN_BLUE";
	public static String FIELD__COLOR_MASK_WARN_GREEN = "COLOR_MASK_WARN_GREEN";
	public static String FIELD__COLOR_MASK_WARN_RED = "COLOR_MASK_WARN_RED";
	public static String FIELD__DEVELOPER = "DEVELOPER";
	public static String FIELD__EIGENDEF_BREITEAENDERBAR = "EIGENDEF_BREITEAENDERBAR";
	public static String FIELD__EIGENDEF_MENUEBREITE = "EIGENDEF_MENUEBREITE";
	public static String FIELD__EIGENDEF_SCHRIFTGROESSE = "EIGENDEF_SCHRIFTGROESSE";
	public static String FIELD__EMAIL = "EMAIL";
	public static String FIELD__ERZEUGT_AM = "ERZEUGT_AM";
	public static String FIELD__ERZEUGT_VON = "ERZEUGT_VON";
	public static String FIELD__FENSTER_MIT_SCHATTEN = "FENSTER_MIT_SCHATTEN";
	public static String FIELD__GEAENDERT_VON = "GEAENDERT_VON";
	public static String FIELD__GESCHAEFTSFUEHRER = "GESCHAEFTSFUEHRER";
	public static String FIELD__HAT_FAHRPLAN_BUTTON = "HAT_FAHRPLAN_BUTTON";
	public static String FIELD__ID_DATEV_PROFILE = "ID_DATEV_PROFILE";
	public static String FIELD__ID_MANDANT = "ID_MANDANT";
	public static String FIELD__ID_SPRACHE = "ID_SPRACHE";
	public static String FIELD__ID_USER = "ID_USER";
	public static String FIELD__ID_USERGROUP = "ID_USERGROUP";
	public static String FIELD__IST_FAHRER = "IST_FAHRER";
	public static String FIELD__IST_SUPERVISOR = "IST_SUPERVISOR";
	public static String FIELD__IST_VERWALTUNG = "IST_VERWALTUNG";
	public static String FIELD__KUERZEL = "KUERZEL";
	public static String FIELD__LAUFZEIT_SESSION = "LAUFZEIT_SESSION";
	public static String FIELD__LETZTE_AENDERUNG = "LETZTE_AENDERUNG";
	public static String FIELD__LISTEGESAMTLAENGE = "LISTEGESAMTLAENGE";
	public static String FIELD__LISTESEITELAENGE = "LISTESEITELAENGE";
	public static String FIELD__MAIL_SIGNATUR = "MAIL_SIGNATUR";
	public static String FIELD__MELDUNG_KREDITVERS_ABLAUF = "MELDUNG_KREDITVERS_ABLAUF";
	public static String FIELD__MELDUNG_KREDITVERS_BETRAG = "MELDUNG_KREDITVERS_BETRAG";
	public static String FIELD__MENGENABSCHLUSS_FUHRE_OK = "MENGENABSCHLUSS_FUHRE_OK";
	public static String FIELD__NAME = "NAME";
	public static String FIELD__NAME1 = "NAME1";
	public static String FIELD__NAME2 = "NAME2";
	public static String FIELD__NAME3 = "NAME3";
	public static String FIELD__PASSWORT = "PASSWORT";
	public static String FIELD__PREISABSCHLUSS_FUHRE_OK = "PREISABSCHLUSS_FUHRE_OK";
	public static String FIELD__SONDERRECH_ZEIGE_OPLISTE_SALDO = "SONDERRECH_ZEIGE_OPLISTE_SALDO";
	public static String FIELD__TELEFAX = "TELEFAX";
	public static String FIELD__TELEFON = "TELEFON";
	public static String FIELD__TEXTCOLLECTOR = "TEXTCOLLECTOR";
	public static String FIELD__TODO_SUPERVISOR = "TODO_SUPERVISOR";
	public static String FIELD__VERTICAL_OFFSET_MASKTABS = "VERTICAL_OFFSET_MASKTABS";
	public static String FIELD__VORNAME = "VORNAME";

	public String get_AKTIV_cUF() throws myException {
		return this.get_UnFormatedValue("AKTIV");
	}

	public String get_AKTIV_cF() throws myException {
		return this.get_FormatedValue("AKTIV");
	}

	public String get_AKTIV_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("AKTIV");
	}

	public String get_AKTIV_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("AKTIV", cNotNullValue);
	}

	public String get_AKTIV_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("AKTIV", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_AKTIV(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("AKTIV", cNewValueFormated);
	}

	public boolean is_AKTIV_YES() throws myException {
		boolean bRueck = false;

		if (get_AKTIV_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_AKTIV_NO() throws myException {
		boolean bRueck = false;

		if (get_AKTIV_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_ANREDE_cUF() throws myException {
		return this.get_UnFormatedValue("ANREDE");
	}

	public String get_ANREDE_cF() throws myException {
		return this.get_FormatedValue("ANREDE");
	}

	public String get_ANREDE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ANREDE");
	}

	public String get_ANREDE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ANREDE", cNotNullValue);
	}

	public String get_ANREDE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ANREDE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ANREDE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ANREDE", cNewValueFormated);
	}

	public String get_AUTCODE_cUF() throws myException {
		return this.get_UnFormatedValue("AUTCODE");
	}

	public String get_AUTCODE_cF() throws myException {
		return this.get_FormatedValue("AUTCODE");
	}

	public String get_AUTCODE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("AUTCODE");
	}

	public String get_AUTCODE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("AUTCODE", cNotNullValue);
	}

	public String get_AUTCODE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("AUTCODE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_AUTCODE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("AUTCODE", cNewValueFormated);
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cUF() throws myException {
		return this.get_UnFormatedValue("EIGENDEF_BREITEAENDERBAR");
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cF() throws myException {
		return this.get_FormatedValue("EIGENDEF_BREITEAENDERBAR");
	}

	public String get_EIGENDEF_BREITEAENDERBAR_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_BREITEAENDERBAR");
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("EIGENDEF_BREITEAENDERBAR", cNotNullValue);
	}

	public String get_EIGENDEF_BREITEAENDERBAR_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("EIGENDEF_BREITEAENDERBAR", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_BREITEAENDERBAR(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("EIGENDEF_BREITEAENDERBAR", cNewValueFormated);
	}

	public boolean is_EIGENDEF_BREITEAENDERBAR_YES() throws myException {
		boolean bRueck = false;

		if (get_EIGENDEF_BREITEAENDERBAR_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_EIGENDEF_BREITEAENDERBAR_NO() throws myException {
		boolean bRueck = false;

		if (get_EIGENDEF_BREITEAENDERBAR_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_EIGENDEF_MENUEBREITE_cUF() throws myException {
		return this.get_UnFormatedValue("EIGENDEF_MENUEBREITE");
	}

	public String get_EIGENDEF_MENUEBREITE_cF() throws myException {
		return this.get_FormatedValue("EIGENDEF_MENUEBREITE");
	}

	public String get_EIGENDEF_MENUEBREITE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_MENUEBREITE");
	}

	public String get_EIGENDEF_MENUEBREITE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("EIGENDEF_MENUEBREITE", cNotNullValue);
	}

	public String get_EIGENDEF_MENUEBREITE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("EIGENDEF_MENUEBREITE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_MENUEBREITE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("EIGENDEF_MENUEBREITE", cNewValueFormated);
	}

	public Long get_EIGENDEF_MENUEBREITE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("EIGENDEF_MENUEBREITE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_EIGENDEF_MENUEBREITE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("EIGENDEF_MENUEBREITE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_EIGENDEF_MENUEBREITE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("EIGENDEF_MENUEBREITE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_EIGENDEF_MENUEBREITE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_MENUEBREITE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_EIGENDEF_MENUEBREITE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_MENUEBREITE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_EIGENDEF_MENUEBREITE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("EIGENDEF_MENUEBREITE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_EIGENDEF_MENUEBREITE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("EIGENDEF_MENUEBREITE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cUF() throws myException {
		return this.get_UnFormatedValue("EIGENDEF_SCHRIFTGROESSE");
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cF() throws myException {
		return this.get_FormatedValue("EIGENDEF_SCHRIFTGROESSE");
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("EIGENDEF_SCHRIFTGROESSE");
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("EIGENDEF_SCHRIFTGROESSE", cNotNullValue);
	}

	public String get_EIGENDEF_SCHRIFTGROESSE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("EIGENDEF_SCHRIFTGROESSE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_EIGENDEF_SCHRIFTGROESSE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("EIGENDEF_SCHRIFTGROESSE", cNewValueFormated);
	}

	public Long get_EIGENDEF_SCHRIFTGROESSE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_EIGENDEF_SCHRIFTGROESSE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_EIGENDEF_SCHRIFTGROESSE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_EIGENDEF_SCHRIFTGROESSE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_SCHRIFTGROESSE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_EIGENDEF_SCHRIFTGROESSE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_EIGENDEF_SCHRIFTGROESSE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_EIGENDEF_SCHRIFTGROESSE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_EIGENDEF_SCHRIFTGROESSE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("EIGENDEF_SCHRIFTGROESSE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_EMAIL_cUF() throws myException {
		return this.get_UnFormatedValue("EMAIL");
	}

	public String get_EMAIL_cF() throws myException {
		return this.get_FormatedValue("EMAIL");
	}

	public String get_EMAIL_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("EMAIL");
	}

	public String get_EMAIL_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("EMAIL", cNotNullValue);
	}

	public String get_EMAIL_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("EMAIL", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_EMAIL(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("EMAIL", cNewValueFormated);
	}

	public String get_ERZEUGT_AM_cUF() throws myException {
		return this.get_UnFormatedValue("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cF() throws myException {
		return this.get_FormatedValue("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_AM");
	}

	public String get_ERZEUGT_AM_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ERZEUGT_AM", cNotNullValue);
	}

	public String get_ERZEUGT_AM_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ERZEUGT_AM", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_AM(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ERZEUGT_AM", cNewValueFormated);
	}

	public String get_ERZEUGT_VON_cUF() throws myException {
		return this.get_UnFormatedValue("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cF() throws myException {
		return this.get_FormatedValue("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ERZEUGT_VON");
	}

	public String get_ERZEUGT_VON_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ERZEUGT_VON", cNotNullValue);
	}

	public String get_ERZEUGT_VON_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ERZEUGT_VON", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ERZEUGT_VON(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ERZEUGT_VON", cNewValueFormated);
	}

	public String get_FENSTER_MIT_SCHATTEN_cUF() throws myException {
		return this.get_UnFormatedValue("FENSTER_MIT_SCHATTEN");
	}

	public String get_FENSTER_MIT_SCHATTEN_cF() throws myException {
		return this.get_FormatedValue("FENSTER_MIT_SCHATTEN");
	}

	public String get_FENSTER_MIT_SCHATTEN_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("FENSTER_MIT_SCHATTEN");
	}

	public String get_FENSTER_MIT_SCHATTEN_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("FENSTER_MIT_SCHATTEN", cNotNullValue);
	}

	public String get_FENSTER_MIT_SCHATTEN_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("FENSTER_MIT_SCHATTEN", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_FENSTER_MIT_SCHATTEN(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("FENSTER_MIT_SCHATTEN", cNewValueFormated);
	}

	public boolean is_FENSTER_MIT_SCHATTEN_YES() throws myException {
		boolean bRueck = false;

		if (get_FENSTER_MIT_SCHATTEN_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_FENSTER_MIT_SCHATTEN_NO() throws myException {
		boolean bRueck = false;

		if (get_FENSTER_MIT_SCHATTEN_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_GEAENDERT_VON_cUF() throws myException {
		return this.get_UnFormatedValue("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cF() throws myException {
		return this.get_FormatedValue("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("GEAENDERT_VON");
	}

	public String get_GEAENDERT_VON_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("GEAENDERT_VON", cNotNullValue);
	}

	public String get_GEAENDERT_VON_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("GEAENDERT_VON", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_GEAENDERT_VON(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("GEAENDERT_VON", cNewValueFormated);
	}

	public String get_GESCHAEFTSFUEHRER_cUF() throws myException {
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cF() throws myException {
		return this.get_FormatedValue("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("GESCHAEFTSFUEHRER");
	}

	public String get_GESCHAEFTSFUEHRER_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("GESCHAEFTSFUEHRER", cNotNullValue);
	}

	public String get_GESCHAEFTSFUEHRER_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("GESCHAEFTSFUEHRER", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_GESCHAEFTSFUEHRER(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("GESCHAEFTSFUEHRER", cNewValueFormated);
	}

	public boolean is_GESCHAEFTSFUEHRER_YES() throws myException {
		boolean bRueck = false;

		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_GESCHAEFTSFUEHRER_NO() throws myException {
		boolean bRueck = false;

		if (get_GESCHAEFTSFUEHRER_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_HAT_FAHRPLAN_BUTTON_cUF() throws myException {
		return this.get_UnFormatedValue("HAT_FAHRPLAN_BUTTON");
	}

	public String get_HAT_FAHRPLAN_BUTTON_cF() throws myException {
		return this.get_FormatedValue("HAT_FAHRPLAN_BUTTON");
	}

	public String get_HAT_FAHRPLAN_BUTTON_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("HAT_FAHRPLAN_BUTTON");
	}

	public String get_HAT_FAHRPLAN_BUTTON_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("HAT_FAHRPLAN_BUTTON", cNotNullValue);
	}

	public String get_HAT_FAHRPLAN_BUTTON_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("HAT_FAHRPLAN_BUTTON", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_HAT_FAHRPLAN_BUTTON(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("HAT_FAHRPLAN_BUTTON", cNewValueFormated);
	}

	public boolean is_HAT_FAHRPLAN_BUTTON_YES() throws myException {
		boolean bRueck = false;

		if (get_HAT_FAHRPLAN_BUTTON_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_HAT_FAHRPLAN_BUTTON_NO() throws myException {
		boolean bRueck = false;

		if (get_HAT_FAHRPLAN_BUTTON_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_ID_MANDANT_cUF() throws myException {
		return this.get_UnFormatedValue("ID_MANDANT");
	}

	public String get_ID_MANDANT_cF() throws myException {
		return this.get_FormatedValue("ID_MANDANT");
	}

	public String get_ID_MANDANT_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_MANDANT");
	}

	public String get_ID_MANDANT_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ID_MANDANT", cNotNullValue);
	}

	public String get_ID_MANDANT_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ID_MANDANT", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_MANDANT(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ID_MANDANT", cNewValueFormated);
	}

	public Long get_ID_MANDANT_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("ID_MANDANT").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_ID_MANDANT_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("ID_MANDANT").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_MANDANT_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_MANDANT_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_ID_MANDANT_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("ID_MANDANT").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_ID_MANDANT_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("ID_MANDANT").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_ID_SPRACHE_cUF() throws myException {
		return this.get_UnFormatedValue("ID_SPRACHE");
	}

	public String get_ID_SPRACHE_cF() throws myException {
		return this.get_FormatedValue("ID_SPRACHE");
	}

	public String get_ID_SPRACHE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_SPRACHE");
	}

	public String get_ID_SPRACHE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ID_SPRACHE", cNotNullValue);
	}

	public String get_ID_SPRACHE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ID_SPRACHE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_SPRACHE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ID_SPRACHE", cNewValueFormated);
	}

	public Long get_ID_SPRACHE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("ID_SPRACHE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_ID_SPRACHE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("ID_SPRACHE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_ID_SPRACHE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("ID_SPRACHE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_SPRACHE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SPRACHE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_SPRACHE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_SPRACHE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_ID_SPRACHE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("ID_SPRACHE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_ID_SPRACHE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("ID_SPRACHE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_ID_USER_cUF() throws myException {
		return this.get_UnFormatedValue("ID_USER");
	}

	public String get_ID_USER_cF() throws myException {
		return this.get_FormatedValue("ID_USER");
	}

	public String get_ID_USER_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USER");
	}

	public String get_ID_USER_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ID_USER", cNotNullValue);
	}

	public String get_ID_USER_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ID_USER", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USER(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ID_USER", cNewValueFormated);
	}

	public Long get_ID_USER_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("ID_USER").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_ID_USER_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("ID_USER").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_ID_USER_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("ID_USER").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USER_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USER_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_ID_USER_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("ID_USER").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_ID_USER_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("ID_USER").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_ID_USERGROUP_cUF() throws myException {
		return this.get_UnFormatedValue("ID_USERGROUP");
	}

	public String get_ID_USERGROUP_cF() throws myException {
		return this.get_FormatedValue("ID_USERGROUP");
	}

	public String get_ID_USERGROUP_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_USERGROUP");
	}

	public String get_ID_USERGROUP_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ID_USERGROUP", cNotNullValue);
	}

	public String get_ID_USERGROUP_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ID_USERGROUP", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_USERGROUP(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ID_USERGROUP", cNewValueFormated);
	}

	public Long get_ID_USERGROUP_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("ID_USERGROUP").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_ID_USERGROUP_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("ID_USERGROUP").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_ID_USERGROUP_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("ID_USERGROUP").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USERGROUP_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERGROUP_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_USERGROUP_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_USERGROUP_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_ID_USERGROUP_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("ID_USERGROUP").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_ID_USERGROUP_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("ID_USERGROUP").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_IST_FAHRER_cUF() throws myException {
		return this.get_UnFormatedValue("IST_FAHRER");
	}

	public String get_IST_FAHRER_cF() throws myException {
		return this.get_FormatedValue("IST_FAHRER");
	}

	public String get_IST_FAHRER_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_FAHRER");
	}

	public String get_IST_FAHRER_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("IST_FAHRER", cNotNullValue);
	}

	public String get_IST_FAHRER_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("IST_FAHRER", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_FAHRER(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("IST_FAHRER", cNewValueFormated);
	}

	public boolean is_IST_FAHRER_YES() throws myException {
		boolean bRueck = false;

		if (get_IST_FAHRER_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_IST_FAHRER_NO() throws myException {
		boolean bRueck = false;

		if (get_IST_FAHRER_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_IST_SUPERVISOR_cUF() throws myException {
		return this.get_UnFormatedValue("IST_SUPERVISOR");
	}

	public String get_IST_SUPERVISOR_cF() throws myException {
		return this.get_FormatedValue("IST_SUPERVISOR");
	}

	public String get_IST_SUPERVISOR_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_SUPERVISOR");
	}

	public String get_IST_SUPERVISOR_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("IST_SUPERVISOR", cNotNullValue);
	}

	public String get_IST_SUPERVISOR_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("IST_SUPERVISOR", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_SUPERVISOR(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("IST_SUPERVISOR", cNewValueFormated);
	}

	public boolean is_IST_SUPERVISOR_YES() throws myException {
		boolean bRueck = false;

		if (get_IST_SUPERVISOR_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_IST_SUPERVISOR_NO() throws myException {
		boolean bRueck = false;

		if (get_IST_SUPERVISOR_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_IST_VERWALTUNG_cUF() throws myException {
		return this.get_UnFormatedValue("IST_VERWALTUNG");
	}

	public String get_IST_VERWALTUNG_cF() throws myException {
		return this.get_FormatedValue("IST_VERWALTUNG");
	}

	public String get_IST_VERWALTUNG_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("IST_VERWALTUNG");
	}

	public String get_IST_VERWALTUNG_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("IST_VERWALTUNG", cNotNullValue);
	}

	public String get_IST_VERWALTUNG_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("IST_VERWALTUNG", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_IST_VERWALTUNG(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("IST_VERWALTUNG", cNewValueFormated);
	}

	public boolean is_IST_VERWALTUNG_YES() throws myException {
		boolean bRueck = false;

		if (get_IST_VERWALTUNG_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_IST_VERWALTUNG_NO() throws myException {
		boolean bRueck = false;

		if (get_IST_VERWALTUNG_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_KUERZEL_cUF() throws myException {
		return this.get_UnFormatedValue("KUERZEL");
	}

	public String get_KUERZEL_cF() throws myException {
		return this.get_FormatedValue("KUERZEL");
	}

	public String get_KUERZEL_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("KUERZEL");
	}

	public String get_KUERZEL_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("KUERZEL", cNotNullValue);
	}

	public String get_KUERZEL_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("KUERZEL", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_KUERZEL(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("KUERZEL", cNewValueFormated);
	}

	public String get_LAUFZEIT_SESSION_cUF() throws myException {
		return this.get_UnFormatedValue("LAUFZEIT_SESSION");
	}

	public String get_LAUFZEIT_SESSION_cF() throws myException {
		return this.get_FormatedValue("LAUFZEIT_SESSION");
	}

	public String get_LAUFZEIT_SESSION_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("LAUFZEIT_SESSION");
	}

	public String get_LAUFZEIT_SESSION_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("LAUFZEIT_SESSION", cNotNullValue);
	}

	public String get_LAUFZEIT_SESSION_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("LAUFZEIT_SESSION", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_LAUFZEIT_SESSION(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("LAUFZEIT_SESSION", cNewValueFormated);
	}

	public Long get_LAUFZEIT_SESSION_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("LAUFZEIT_SESSION").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_LAUFZEIT_SESSION_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("LAUFZEIT_SESSION").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_LAUFZEIT_SESSION_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("LAUFZEIT_SESSION").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LAUFZEIT_SESSION_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAUFZEIT_SESSION_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LAUFZEIT_SESSION_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LAUFZEIT_SESSION_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_LAUFZEIT_SESSION_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("LAUFZEIT_SESSION").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_LAUFZEIT_SESSION_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("LAUFZEIT_SESSION").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_LETZTE_AENDERUNG_cUF() throws myException {
		return this.get_UnFormatedValue("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cF() throws myException {
		return this.get_FormatedValue("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("LETZTE_AENDERUNG");
	}

	public String get_LETZTE_AENDERUNG_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("LETZTE_AENDERUNG", cNotNullValue);
	}

	public String get_LETZTE_AENDERUNG_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("LETZTE_AENDERUNG", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_LETZTE_AENDERUNG(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("LETZTE_AENDERUNG", cNewValueFormated);
	}

	public String get_LISTEGESAMTLAENGE_cUF() throws myException {
		return this.get_UnFormatedValue("LISTEGESAMTLAENGE");
	}

	public String get_LISTEGESAMTLAENGE_cF() throws myException {
		return this.get_FormatedValue("LISTEGESAMTLAENGE");
	}

	public String get_LISTEGESAMTLAENGE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("LISTEGESAMTLAENGE");
	}

	public String get_LISTEGESAMTLAENGE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("LISTEGESAMTLAENGE", cNotNullValue);
	}

	public String get_LISTEGESAMTLAENGE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("LISTEGESAMTLAENGE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTEGESAMTLAENGE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("LISTEGESAMTLAENGE", cNewValueFormated);
	}

	public Long get_LISTEGESAMTLAENGE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("LISTEGESAMTLAENGE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_LISTEGESAMTLAENGE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("LISTEGESAMTLAENGE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_LISTEGESAMTLAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("LISTEGESAMTLAENGE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LISTEGESAMTLAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTEGESAMTLAENGE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LISTEGESAMTLAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTEGESAMTLAENGE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_LISTEGESAMTLAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("LISTEGESAMTLAENGE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_LISTEGESAMTLAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("LISTEGESAMTLAENGE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_LISTESEITELAENGE_cUF() throws myException {
		return this.get_UnFormatedValue("LISTESEITELAENGE");
	}

	public String get_LISTESEITELAENGE_cF() throws myException {
		return this.get_FormatedValue("LISTESEITELAENGE");
	}

	public String get_LISTESEITELAENGE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("LISTESEITELAENGE");
	}

	public String get_LISTESEITELAENGE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("LISTESEITELAENGE", cNotNullValue);
	}

	public String get_LISTESEITELAENGE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("LISTESEITELAENGE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_LISTESEITELAENGE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("LISTESEITELAENGE", cNewValueFormated);
	}

	public Long get_LISTESEITELAENGE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("LISTESEITELAENGE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_LISTESEITELAENGE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("LISTESEITELAENGE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_LISTESEITELAENGE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("LISTESEITELAENGE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LISTESEITELAENGE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTESEITELAENGE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_LISTESEITELAENGE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_LISTESEITELAENGE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_LISTESEITELAENGE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("LISTESEITELAENGE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_LISTESEITELAENGE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("LISTESEITELAENGE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_MAIL_SIGNATUR_cUF() throws myException {
		return this.get_UnFormatedValue("MAIL_SIGNATUR");
	}

	public String get_MAIL_SIGNATUR_cF() throws myException {
		return this.get_FormatedValue("MAIL_SIGNATUR");
	}

	public String get_MAIL_SIGNATUR_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("MAIL_SIGNATUR");
	}

	public String get_MAIL_SIGNATUR_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("MAIL_SIGNATUR", cNotNullValue);
	}

	public String get_MAIL_SIGNATUR_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("MAIL_SIGNATUR", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_MAIL_SIGNATUR(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("MAIL_SIGNATUR", cNewValueFormated);
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cUF() throws myException {
		return this.get_UnFormatedValue("MENGENABSCHLUSS_FUHRE_OK");
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cF() throws myException {
		return this.get_FormatedValue("MENGENABSCHLUSS_FUHRE_OK");
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("MENGENABSCHLUSS_FUHRE_OK");
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("MENGENABSCHLUSS_FUHRE_OK", cNotNullValue);
	}

	public String get_MENGENABSCHLUSS_FUHRE_OK_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("MENGENABSCHLUSS_FUHRE_OK", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_MENGENABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("MENGENABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}

	public boolean is_MENGENABSCHLUSS_FUHRE_OK_YES() throws myException {
		boolean bRueck = false;

		if (get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_MENGENABSCHLUSS_FUHRE_OK_NO() throws myException {
		boolean bRueck = false;

		if (get_MENGENABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_NAME_cUF() throws myException {
		return this.get_UnFormatedValue("NAME");
	}

	public String get_NAME_cF() throws myException {
		return this.get_FormatedValue("NAME");
	}

	public String get_NAME_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME");
	}

	public String get_NAME_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("NAME", cNotNullValue);
	}

	public String get_NAME_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("NAME", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("NAME", cNewValueFormated);
	}

	public String get_NAME1_cUF() throws myException {
		return this.get_UnFormatedValue("NAME1");
	}

	public String get_NAME1_cF() throws myException {
		return this.get_FormatedValue("NAME1");
	}

	public String get_NAME1_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME1");
	}

	public String get_NAME1_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("NAME1", cNotNullValue);
	}

	public String get_NAME1_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("NAME1", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME1(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("NAME1", cNewValueFormated);
	}

	public String get_NAME2_cUF() throws myException {
		return this.get_UnFormatedValue("NAME2");
	}

	public String get_NAME2_cF() throws myException {
		return this.get_FormatedValue("NAME2");
	}

	public String get_NAME2_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME2");
	}

	public String get_NAME2_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("NAME2", cNotNullValue);
	}

	public String get_NAME2_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("NAME2", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME2(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("NAME2", cNewValueFormated);
	}

	public String get_NAME3_cUF() throws myException {
		return this.get_UnFormatedValue("NAME3");
	}

	public String get_NAME3_cF() throws myException {
		return this.get_FormatedValue("NAME3");
	}

	public String get_NAME3_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("NAME3");
	}

	public String get_NAME3_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("NAME3", cNotNullValue);
	}

	public String get_NAME3_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("NAME3", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_NAME3(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("NAME3", cNewValueFormated);
	}

	public String get_PASSWORT_cUF() throws myException {
		return this.get_UnFormatedValue("PASSWORT");
	}

	public String get_PASSWORT_cF() throws myException {
		return this.get_FormatedValue("PASSWORT");
	}

	public String get_PASSWORT_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("PASSWORT");
	}

	public String get_PASSWORT_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("PASSWORT", cNotNullValue);
	}

	public String get_PASSWORT_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("PASSWORT", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_PASSWORT(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("PASSWORT", cNewValueFormated);
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cUF() throws myException {
		return this.get_UnFormatedValue("PREISABSCHLUSS_FUHRE_OK");
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cF() throws myException {
		return this.get_FormatedValue("PREISABSCHLUSS_FUHRE_OK");
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("PREISABSCHLUSS_FUHRE_OK");
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("PREISABSCHLUSS_FUHRE_OK", cNotNullValue);
	}

	public String get_PREISABSCHLUSS_FUHRE_OK_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("PREISABSCHLUSS_FUHRE_OK", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_PREISABSCHLUSS_FUHRE_OK(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("PREISABSCHLUSS_FUHRE_OK", cNewValueFormated);
	}

	public boolean is_PREISABSCHLUSS_FUHRE_OK_YES() throws myException {
		boolean bRueck = false;

		if (get_PREISABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_PREISABSCHLUSS_FUHRE_OK_NO() throws myException {
		boolean bRueck = false;

		if (get_PREISABSCHLUSS_FUHRE_OK_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF() throws myException {
		return this.get_UnFormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO");
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cF() throws myException {
		return this.get_FormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO");
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("SONDERRECH_ZEIGE_OPLISTE_SALDO");
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNotNullValue);
	}

	public String get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_SONDERRECH_ZEIGE_OPLISTE_SALDO(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("SONDERRECH_ZEIGE_OPLISTE_SALDO", cNewValueFormated);
	}

	public boolean is_SONDERRECH_ZEIGE_OPLISTE_SALDO_YES() throws myException {
		boolean bRueck = false;

		if (get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_SONDERRECH_ZEIGE_OPLISTE_SALDO_NO() throws myException {
		boolean bRueck = false;

		if (get_SONDERRECH_ZEIGE_OPLISTE_SALDO_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_TELEFAX_cUF() throws myException {
		return this.get_UnFormatedValue("TELEFAX");
	}

	public String get_TELEFAX_cF() throws myException {
		return this.get_FormatedValue("TELEFAX");
	}

	public String get_TELEFAX_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFAX");
	}

	public String get_TELEFAX_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("TELEFAX", cNotNullValue);
	}

	public String get_TELEFAX_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("TELEFAX", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFAX(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("TELEFAX", cNewValueFormated);
	}

	public String get_TELEFON_cUF() throws myException {
		return this.get_UnFormatedValue("TELEFON");
	}

	public String get_TELEFON_cF() throws myException {
		return this.get_FormatedValue("TELEFON");
	}

	public String get_TELEFON_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("TELEFON");
	}

	public String get_TELEFON_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("TELEFON", cNotNullValue);
	}

	public String get_TELEFON_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("TELEFON", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_TELEFON(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("TELEFON", cNewValueFormated);
	}

	public String get_TEXTCOLLECTOR_cUF() throws myException {
		return this.get_UnFormatedValue("TEXTCOLLECTOR");
	}

	public String get_TEXTCOLLECTOR_cF() throws myException {
		return this.get_FormatedValue("TEXTCOLLECTOR");
	}

	public String get_TEXTCOLLECTOR_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("TEXTCOLLECTOR");
	}

	public String get_TEXTCOLLECTOR_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("TEXTCOLLECTOR", cNotNullValue);
	}

	public String get_TEXTCOLLECTOR_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("TEXTCOLLECTOR", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_TEXTCOLLECTOR(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("TEXTCOLLECTOR", cNewValueFormated);
	}

	public boolean is_TEXTCOLLECTOR_YES() throws myException {
		boolean bRueck = false;

		if (get_TEXTCOLLECTOR_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_TEXTCOLLECTOR_NO() throws myException {
		boolean bRueck = false;

		if (get_TEXTCOLLECTOR_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_TODO_SUPERVISOR_cUF() throws myException {
		return this.get_UnFormatedValue("TODO_SUPERVISOR");
	}

	public String get_TODO_SUPERVISOR_cF() throws myException {
		return this.get_FormatedValue("TODO_SUPERVISOR");
	}

	public String get_TODO_SUPERVISOR_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("TODO_SUPERVISOR");
	}

	public String get_TODO_SUPERVISOR_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("TODO_SUPERVISOR", cNotNullValue);
	}

	public String get_TODO_SUPERVISOR_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("TODO_SUPERVISOR", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_TODO_SUPERVISOR(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("TODO_SUPERVISOR", cNewValueFormated);
	}

	public boolean is_TODO_SUPERVISOR_YES() throws myException {
		boolean bRueck = false;

		if (get_TODO_SUPERVISOR_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_TODO_SUPERVISOR_NO() throws myException {
		boolean bRueck = false;

		if (get_TODO_SUPERVISOR_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cUF() throws myException {
		return this.get_UnFormatedValue("VERTICAL_OFFSET_MASKTABS");
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cF() throws myException {
		return this.get_FormatedValue("VERTICAL_OFFSET_MASKTABS");
	}

	public String get_VERTICAL_OFFSET_MASKTABS_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("VERTICAL_OFFSET_MASKTABS");
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("VERTICAL_OFFSET_MASKTABS", cNotNullValue);
	}

	public String get_VERTICAL_OFFSET_MASKTABS_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("VERTICAL_OFFSET_MASKTABS", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_VERTICAL_OFFSET_MASKTABS(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("VERTICAL_OFFSET_MASKTABS", cNewValueFormated);
	}

	public Long get_VERTICAL_OFFSET_MASKTABS_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("VERTICAL_OFFSET_MASKTABS").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_VERTICAL_OFFSET_MASKTABS_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("VERTICAL_OFFSET_MASKTABS").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_VERTICAL_OFFSET_MASKTABS_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("VERTICAL_OFFSET_MASKTABS").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_VERTICAL_OFFSET_MASKTABS_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERTICAL_OFFSET_MASKTABS_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_VERTICAL_OFFSET_MASKTABS_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_VERTICAL_OFFSET_MASKTABS_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_VERTICAL_OFFSET_MASKTABS_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("VERTICAL_OFFSET_MASKTABS").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_VERTICAL_OFFSET_MASKTABS_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("VERTICAL_OFFSET_MASKTABS").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}

	public String get_VORNAME_cUF() throws myException {
		return this.get_UnFormatedValue("VORNAME");
	}

	public String get_VORNAME_cF() throws myException {
		return this.get_FormatedValue("VORNAME");
	}

	public String get_VORNAME_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("VORNAME");
	}

	public String get_VORNAME_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("VORNAME", cNotNullValue);
	}

	public String get_VORNAME_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("VORNAME", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_VORNAME(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("VORNAME", cNewValueFormated);
	}

	public static HashMap<String, MyRECORD.DATATYPES> HM_FIELDS = new HashMap<String, MyRECORD.DATATYPES>() {
		{
			put("AKTIV", MyRECORD.DATATYPES.YESNO);
			put("ANREDE", MyRECORD.DATATYPES.TEXT);
			put("AUTCODE", MyRECORD.DATATYPES.TEXT);
			put("EIGENDEF_BREITEAENDERBAR", MyRECORD.DATATYPES.YESNO);
			put("EIGENDEF_MENUEBREITE", MyRECORD.DATATYPES.NUMBER);
			put("EIGENDEF_SCHRIFTGROESSE", MyRECORD.DATATYPES.NUMBER);
			put("EMAIL", MyRECORD.DATATYPES.TEXT);
			put("ERZEUGT_AM", MyRECORD.DATATYPES.DATE);
			put("ERZEUGT_VON", MyRECORD.DATATYPES.TEXT);
			put("FENSTER_MIT_SCHATTEN", MyRECORD.DATATYPES.YESNO);
			put("GEAENDERT_VON", MyRECORD.DATATYPES.TEXT);
			put("GESCHAEFTSFUEHRER", MyRECORD.DATATYPES.YESNO);
			put("HAT_FAHRPLAN_BUTTON", MyRECORD.DATATYPES.YESNO);
			put("ID_MANDANT", MyRECORD.DATATYPES.NUMBER);
			put("ID_SPRACHE", MyRECORD.DATATYPES.NUMBER);
			put("ID_USER", MyRECORD.DATATYPES.NUMBER);
			put("ID_USERGROUP", MyRECORD.DATATYPES.NUMBER);
			put("IST_FAHRER", MyRECORD.DATATYPES.YESNO);
			put("IST_SUPERVISOR", MyRECORD.DATATYPES.YESNO);
			put("IST_VERWALTUNG", MyRECORD.DATATYPES.YESNO);
			put("KUERZEL", MyRECORD.DATATYPES.TEXT);
			put("LAUFZEIT_SESSION", MyRECORD.DATATYPES.NUMBER);
			put("LETZTE_AENDERUNG", MyRECORD.DATATYPES.DATE);
			put("LISTEGESAMTLAENGE", MyRECORD.DATATYPES.NUMBER);
			put("LISTESEITELAENGE", MyRECORD.DATATYPES.NUMBER);
			put("MAIL_SIGNATUR", MyRECORD.DATATYPES.TEXT);
			put("MENGENABSCHLUSS_FUHRE_OK", MyRECORD.DATATYPES.YESNO);
			put("NAME", MyRECORD.DATATYPES.TEXT);
			put("NAME1", MyRECORD.DATATYPES.TEXT);
			put("NAME2", MyRECORD.DATATYPES.TEXT);
			put("NAME3", MyRECORD.DATATYPES.TEXT);
			put("PASSWORT", MyRECORD.DATATYPES.TEXT);
			put("PREISABSCHLUSS_FUHRE_OK", MyRECORD.DATATYPES.YESNO);
			put("SONDERRECH_ZEIGE_OPLISTE_SALDO", MyRECORD.DATATYPES.YESNO);
			put("TELEFAX", MyRECORD.DATATYPES.TEXT);
			put("TELEFON", MyRECORD.DATATYPES.TEXT);
			put("TEXTCOLLECTOR", MyRECORD.DATATYPES.YESNO);
			put("TODO_SUPERVISOR", MyRECORD.DATATYPES.YESNO);
			put("VERTICAL_OFFSET_MASKTABS", MyRECORD.DATATYPES.NUMBER);
			put("VORNAME", MyRECORD.DATATYPES.TEXT);
			put("DEVELOPER", MyRECORD.DATATYPES.YESNO);
		}
	};

	@Override
	public HashMap<String, DATATYPES> get_HM_FIELDNAMES() {
		return RECORD_USER.HM_FIELDS;
	}

	@Override
	public MyRECORD_NEW get_RECORD_NEW() throws myException {
		return null;
	}

	@Override
	public String get_TABLENAME() {
		return BASIC_RECORD_USER.TABLENAME;
	}

	/*
	 * 2012-09-18: simples update-statement-erstellung, nur geaenderte felder
	 */
	public String get_SQL_UPDATE_STD() throws myException {
		MySqlStatementBuilder oSQLStatementBuilder = this.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();

		if (oSQLStatementBuilder.size() == 0) // sonst keine aenderungen
		{
			throw new myException(this, "Error: No field in RECORD_USER was changed !");
		}
		return oSQLStatementBuilder.get_CompleteUPDATEString(BASIC_RECORD_USER.TABLENAME, bibE2.cTO(), "ID_USER=" + this.get_ID_USER_cUF(), null);
	}

	/*
	 * 2012-09-18: simples update, nur geaenderte felder
	 */
	public MyE2_MessageVector UPDATE(boolean bCommit) throws myException {
		return bibDB.ExecMultiSQLVector(bibALL.get_Vector(this.get_SQL_UPDATE_STD()), bCommit);
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cUF() throws myException {
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_ABLAUF");
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cF() throws myException {
		return this.get_FormatedValue("MELDUNG_KREDITVERS_ABLAUF");
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDUNG_KREDITVERS_ABLAUF");
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_ABLAUF", cNotNullValue);
	}

	public String get_MELDUNG_KREDITVERS_ABLAUF_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("MELDUNG_KREDITVERS_ABLAUF", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}

	// 2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF(String cNewValueFormated) throws myException {
		return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", cNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(long lNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", lNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(double dNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", dNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(BigDecimal bdNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", bdNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_ABLAUF_(GregorianCalendar calNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_ABLAUF", calNewValueFormated);
	}

	public boolean is_MELDUNG_KREDITVERS_ABLAUF_YES() throws myException {
		boolean bRueck = false;

		if (get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_MELDUNG_KREDITVERS_ABLAUF_NO() throws myException {
		boolean bRueck = false;

		if (get_MELDUNG_KREDITVERS_ABLAUF_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cUF() throws myException {
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_BETRAG");
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cF() throws myException {
		return this.get_FormatedValue("MELDUNG_KREDITVERS_BETRAG");
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("MELDUNG_KREDITVERS_BETRAG");
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("MELDUNG_KREDITVERS_BETRAG", cNotNullValue);
	}

	public String get_MELDUNG_KREDITVERS_BETRAG_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("MELDUNG_KREDITVERS_BETRAG", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}

	// 2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG(String cNewValueFormated) throws myException {
		return super.check_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", cNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(long lNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", lNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(double dNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", dNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(BigDecimal bdNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", bdNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_MELDUNG_KREDITVERS_BETRAG_(GregorianCalendar calNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("MELDUNG_KREDITVERS_BETRAG", calNewValueFormated);
	}

	public boolean is_MELDUNG_KREDITVERS_BETRAG_YES() throws myException {
		boolean bRueck = false;

		if (get_MELDUNG_KREDITVERS_BETRAG_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_MELDUNG_KREDITVERS_BETRAG_NO() throws myException {
		boolean bRueck = false;

		if (get_MELDUNG_KREDITVERS_BETRAG_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_DEVELOPER_cUF() throws myException {
		return this.get_UnFormatedValue("DEVELOPER");
	}

	public String get_DEVELOPER_cF() throws myException {
		return this.get_FormatedValue("DEVELOPER");
	}

	public String get_DEVELOPER_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("DEVELOPER");
	}

	public String get_DEVELOPER_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("DEVELOPER", cNotNullValue);
	}

	public String get_DEVELOPER_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("DEVELOPER", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}

	// 2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_DEVELOPER(String cNewValueFormated) throws myException {
		return super.check_NewValueForDatabase("DEVELOPER", cNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(long lNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("DEVELOPER", lNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(double dNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("DEVELOPER", dNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(BigDecimal bdNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("DEVELOPER", bdNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_DEVELOPER_(GregorianCalendar calNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("DEVELOPER", calNewValueFormated);
	}

	public boolean is_DEVELOPER_YES() throws myException {
		boolean bRueck = false;

		if (get_DEVELOPER_cUF_NN("N").equals("Y")) {
			bRueck = true;
		}

		return bRueck;
	}

	public boolean is_DEVELOPER_NO() throws myException {
		boolean bRueck = false;

		if (get_DEVELOPER_cUF_NN("N").equals("N")) {
			bRueck = true;
		}

		return bRueck;
	}

	public String get_ID_DATEV_PROFILE_cUF() throws myException {
		return this.get_UnFormatedValue("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cF() throws myException {
		return this.get_FormatedValue("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_VALUE_FOR_SQLSTATEMENT() throws myException {
		return this.get_cVALUE_FOR_SQLSTATEMENT("ID_DATEV_PROFILE");
	}

	public String get_ID_DATEV_PROFILE_cUF_NN(String cNotNullValue) throws myException {
		return this.get_UnFormatedValue("ID_DATEV_PROFILE", cNotNullValue);
	}

	public String get_ID_DATEV_PROFILE_cF_NN(String cNotNullValue) throws myException {
		return this.get_FormatedValue("ID_DATEV_PROFILE", cNotNullValue);
	}

	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException {
		return this.set_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}

	// 2013-09-18: new check_ Methode, die nichts schreibt
	public MyE2_MessageVector check_NEW_VALUE_ID_DATEV_PROFILE(String cNewValueFormated) throws myException {
		return super.check_NewValueForDatabase("ID_DATEV_PROFILE", cNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(long lNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("ID_DATEV_PROFILE", lNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(double dNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("ID_DATEV_PROFILE", dNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(BigDecimal bdNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("ID_DATEV_PROFILE", bdNewValueFormated);
	}

	// 2013-07-17: new wetting-methods, like in recordnew-object
	public MyE2_MessageVector set_NEW_VALUE_ID_DATEV_PROFILE_(GregorianCalendar calNewValueFormated) throws myException {
		return super.set_NewValueForDatabase("ID_DATEV_PROFILE", calNewValueFormated);
	}

	public Long get_ID_DATEV_PROFILE_lValue(Long lValueWhenNULL) throws myException {
		Long lRueck = this.get("ID_DATEV_PROFILE").getLongValue();
		if (lRueck == null) {
			return lValueWhenNULL;
		} else {
			return lRueck;
		}
	}

	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL) throws myException {
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
		if (dRueck == null) {
			return dValueWhenNULL;
		} else {
			return dRueck;
		}
	}

	public Double get_ID_DATEV_PROFILE_dValue(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		Double dRueck = this.get("ID_DATEV_PROFILE").getDoubleValue();
		if (dRueck == null) {
			dRueck = dValueWhenNULL; // der wert wird auch gerunden (falls nicht
										// null)
		}

		if (dRueck == null) {
			return null;
		} else {
			BigDecimal bdTemp = new BigDecimal(dRueck);
			BigDecimal bdValue = bdTemp.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
			dRueck = bdValue.doubleValue();
			return new Double(dRueck);
		}
	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @return "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_DATEV_PROFILE_cUF_NN(Double dValueWhenNULL, int iNachkommaRunden) throws myException {
		String cFormat = "#0," + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	/**
	 * 
	 * @param dValueWhenNULL
	 * @param iNachkommaRunden
	 * @param bTausender
	 * @return s "" wenn der double-Wert null ist
	 * @throws myException
	 */
	public String get_ID_DATEV_PROFILE_cF_NN(Double dValueWhenNULL, int iNachkommaRunden, boolean bTausender) throws myException {
		String cFormat = "#,###,##0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);
		if (!bTausender)
			cFormat = "#0." + "00000000000000000000000000000000".substring(0, iNachkommaRunden);

		DecimalFormat df = new DecimalFormat(cFormat);

		Double dHelp = this.get_ID_DATEV_PROFILE_dValue(dValueWhenNULL, iNachkommaRunden);
		if (dHelp == null) {
			return "";
		}

		// beim runden auf 0 formatiert er als letztes zeichen . oder ,
		String cRueck = df.format(dHelp.doubleValue());
		if (cRueck.endsWith(",") || cRueck.endsWith("."))
			cRueck = cRueck.substring(0, cRueck.length() - 1);

		return cRueck;

	}

	public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL) throws myException {
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
		if (bdRueck == null) {
			return bdValueWhenNULL;
		} else {
			return bdRueck;
		}
	}

	public BigDecimal get_ID_DATEV_PROFILE_bdValue(BigDecimal bdValueWhenNULL, int iNachkommaRunden) throws myException {
		BigDecimal bdRueck = this.get("ID_DATEV_PROFILE").getBigDecimalValue();
		if (bdRueck == null) {
			bdRueck = bdValueWhenNULL; // der wert wird auch gerunden (falls
										// nicht null)
		}

		if (bdRueck == null) {
			return null;
		} else {
			return bdRueck.setScale(iNachkommaRunden, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	
	
    /** 2015-02-03
     * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
    */
    public MySqlStatementBuilder get_StatementBuilder(boolean bExcludeAutomaticFields) throws myException   {
      return this.get_StatementBuilderFilledWithActualValues(bExcludeAutomaticFields);
    }

    /** 2015-02-17
     * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
    */
	@Override
	public boolean get_bHasSomething_to_save() throws myException {
		return this.get_bAnyFieldIsRealyChanged();
	}

	  /** 2015-02-17
     * hinzugefuegt, um interface MyRECORD_IF_FILLABLE zu erfuellen 
     */
    @Override
    public BASIC_RECORD_USER build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException {
      if (S.isEmpty(this.get_cSQL_4_Build())) {
         throw new myException(this,"build_NEW_INSTANCE_ACTUAL_DATABASEVALUES is not possible, no valid sql-statement is inside !");
      }
      return new BASIC_RECORD_USER(this.get_cSQL_4_Build());
     }

	
	
	

}
