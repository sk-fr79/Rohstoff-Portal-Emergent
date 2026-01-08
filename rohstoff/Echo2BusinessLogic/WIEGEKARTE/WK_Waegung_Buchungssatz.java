package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEGUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;
import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase.ENUM_WaageResultStatus;

public class WK_Waegung_Buchungssatz {
	private String ID_WAEGUNG = null;
	
	private String ID_WIEGEKARTE = null;
	private String LFDNR = null;
	private String HANDEINGABE = null;
	private BigDecimal GEWICHT = null;
	private String DATUM = null;
	private String ZEIT =null;
	private String WAEGUNG_POS = null;
	private String STORNO = null;
	private String W_FEHLERCODE = null;
	private String W_STATUS = null;
	private String W_DATUM = null;
	private String W_ZEIT = null;
	private String W_IDENT_NR = null;
	private String W_WAAGEN_NR = null;
	private String W_BRUTTO_GEWICHT = null;
	private String W_TARAGEWICHT = null;
	private String W_NETTO_GEWICHT = null;
	private String W_EINHEIT = null;
	private String W_TARACODE = null;
	private String W_WAEGEBEREICH = null;
	private String W_TERMINAL = null;
	private String W_PRUEFZIFFER  = null;
	private String WAAGE_DS_ORI = null;
	private String ID_USER_WAEGUNG = null;
	private String ID_WAAGE_SETTINGS = null;
	private String HANDEINGABE_BEM = null;
	
	// falls der Wert aus der Waage kommt, hier nochmal der WaageSatz 
//	private WaageSatzBase m_WaageSatz = null;
	private String m_WaageFehlerBeschreibung = null;
	private ENUM_WaageResultStatus m_WaageResultStatus = null;
	
	/**
	 * Standard-Konstruktor
	 */
	public WK_Waegung_Buchungssatz(){
	
	}
	
	/** Konstruktor mit einem WAEGUNGs-Record
	 * 
	 * @param oRec
	 * @throws myException 
	 */
	public WK_Waegung_Buchungssatz(RECORD_WAEGUNG oRec) throws myException{
		if (oRec == null){
			return;
		}
		
		ID_WAEGUNG = oRec.get_ID_WAEGUNG_cUF();
		ID_WIEGEKARTE = oRec.get_ID_WIEGEKARTE_cUF();
		HANDEINGABE = oRec.get_HANDEINGABE_cUF();
		GEWICHT = oRec.get_GEWICHT_cUF() != null ? new BigDecimal(oRec.get_GEWICHT_cUF()) : null;
		DATUM = oRec.get_DATUM_cUF();
		ZEIT = oRec.get_ZEIT_cUF();
		WAEGUNG_POS = oRec.get_WAEGUNG_POS_cUF();
		STORNO = oRec.get_STORNO_cUF();
		W_FEHLERCODE = oRec.get_W_FEHLERCODE_cUF();
		W_STATUS = oRec.get_W_STATUS_cUF();
		W_DATUM = oRec.get_W_DATUM_cUF();
		W_ZEIT = oRec.get_W_ZEIT_cUF();
		W_IDENT_NR = oRec.get_W_IDENT_NR_cUF();
		W_WAAGEN_NR = oRec.get_W_WAAGEN_NR_cUF();
		W_BRUTTO_GEWICHT = oRec.get_W_BRUTTO_GEWICHT_cUF();
		W_TARAGEWICHT = oRec.get_W_TARAGEWICHT_cUF();
		W_NETTO_GEWICHT = oRec.get_W_NETTO_GEWICHT_cUF();
		W_EINHEIT = oRec.get_W_EINHEIT_cUF();
		W_TARACODE = oRec.get_W_TARACODE_cUF();
		W_WAEGEBEREICH = oRec.get_W_WAEGEBEREICH_cUF();
		W_TERMINAL = oRec.get_W_TERMINAL_cUF();
		W_PRUEFZIFFER = oRec.get_W_PRUEFZIFFER_cUF();
		WAAGE_DS_ORI = oRec.get_WAAGE_DS_ORI_cUF();
		ID_USER_WAEGUNG = oRec.get_ID_USER_WAEGUNG_cUF();
		HANDEINGABE_BEM = oRec.get_HANDEINGABE_BEM_cUF();
		setID_WAAGE_SETTINGS(oRec.get_ID_WAAGE_SETTINGS_cUF());

	}
	
	
	/**
	 * Konstruktor mit allen Feldern
	 * @param iDWAEGUNG
	 * @param iDWIEGEKARTE
	 * @param lFDNR
	 * @param hANDEINGABE
	 * @param gEWICHT
	 * @param dATUM
	 * @param zEIT
	 * @param wAEGUNGPOS
	 * @param sTORNO
	 * @param wFEHLERCODE
	 * @param wSTATUS
	 * @param wDATUM
	 * @param wZEIT
	 * @param wIDENTNR
	 * @param wWAAGENNR
	 * @param wBRUTTOGEWICHT
	 * @param wTARAGEWICHT
	 * @param wNETTOGEWICHT
	 * @param wEINHEIT
	 * @param wTARACODE
	 * @param wWAEGEBEREICH
	 * @param wTERMINAL
	 * @param wPRUEFZIFFER
	 * @param wAAGEDSORI
	 */
	public WK_Waegung_Buchungssatz(String iDWAEGUNG, String iDWIEGEKARTE,
			String lFDNR, String hANDEINGABE, BigDecimal gEWICHT, String dATUM,
			String zEIT, String wAEGUNGPOS, String sTORNO, String wFEHLERCODE,
			String wSTATUS, String wDATUM, String wZEIT, String wIDENTNR,
			String wWAAGENNR, String wBRUTTOGEWICHT, String wTARAGEWICHT,
			String wNETTOGEWICHT, String wEINHEIT, String wTARACODE,
			String wWAEGEBEREICH, String wTERMINAL, String wPRUEFZIFFER,
			String wAAGEDSORI,
			String iD_USER_WAEGUNG,
			String iD_WAAGE_SETTINGS,
			String hANDEINGABE_BEM) {

		ID_WAEGUNG = iDWAEGUNG;
		ID_WIEGEKARTE = iDWIEGEKARTE;
		LFDNR = lFDNR;
		HANDEINGABE = hANDEINGABE;
		GEWICHT = gEWICHT;
		DATUM = dATUM;
		ZEIT = zEIT;
		WAEGUNG_POS = wAEGUNGPOS;
		STORNO = sTORNO;
		W_FEHLERCODE = wFEHLERCODE;
		W_STATUS = wSTATUS;
		W_DATUM = wDATUM;
		W_ZEIT = wZEIT;
		W_IDENT_NR = wIDENTNR;
		W_WAAGEN_NR = wWAAGENNR;
		W_BRUTTO_GEWICHT = wBRUTTOGEWICHT;
		W_TARAGEWICHT = wTARAGEWICHT;
		W_NETTO_GEWICHT = wNETTOGEWICHT;
		W_EINHEIT = wEINHEIT;
		W_TARACODE = wTARACODE;
		W_WAEGEBEREICH = wWAEGEBEREICH;
		W_TERMINAL = wTERMINAL;
		W_PRUEFZIFFER = wPRUEFZIFFER;
		WAAGE_DS_ORI = wAAGEDSORI;
		ID_USER_WAEGUNG = iD_USER_WAEGUNG;
		HANDEINGABE_BEM = hANDEINGABE_BEM;
		setID_WAAGE_SETTINGS(iD_WAAGE_SETTINGS); 
	}
	
	/** Konstruktur aus einem Systec-WaageSatz
	 * 
	 * @param oWaageSatz
	 */
	public WK_Waegung_Buchungssatz(WaageSatzBase oWaageSatz){
		if (oWaageSatz instanceof WaageSatzBase){
			this.W_BRUTTO_GEWICHT = oWaageSatz.getData(WaageSatzBase.WF_BruttoGewicht);
			this.W_DATUM = oWaageSatz.getData(WaageSatzBase.WF_Datum);
			this.W_EINHEIT = oWaageSatz.getData(WaageSatzBase.WF_Einheit);
			this.W_FEHLERCODE = oWaageSatz.getData(WaageSatzBase.WF_Fehlercode);
			this.W_IDENT_NR = oWaageSatz.getData(WaageSatzBase.WF_IdentNummer);
			this.W_NETTO_GEWICHT = oWaageSatz.getData(WaageSatzBase.WF_NettoGewicht);
			this.W_PRUEFZIFFER = oWaageSatz.getData(WaageSatzBase.WF_Pruefziffer);
			this.W_TARACODE = oWaageSatz.getData(WaageSatzBase.WF_TaraCode);
			this.W_TARAGEWICHT = oWaageSatz.getData(WaageSatzBase.WF_TaraGewicht);
			this.W_TERMINAL = oWaageSatz.getData(WaageSatzBase.WF_TerminalNr);
			this.W_WAAGEN_NR = oWaageSatz.getData(WaageSatzBase.WF_WaagenNummer);
			this.W_WAEGEBEREICH = oWaageSatz.getData(WaageSatzBase.WF_Waegebereich);
			this.W_ZEIT = oWaageSatz.getData(WaageSatzBase.WF_Zeit);
			
			
			try {
				SimpleDateFormat df = new SimpleDateFormat(oWaageSatz.getDatumsFormat()) ;
				Date dt = df.parse(this.W_DATUM);
				
				SimpleDateFormat dfTarget = new SimpleDateFormat("yyyy-MM-dd");
				this.DATUM = "'" +  dfTarget.format(dt) + "'";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				this.DATUM = null;
			} catch (Exception e1){
				// alles andere auch...
				this.DATUM = null;
			}
			
			if(this.W_ZEIT != null){
				this.ZEIT = "'"  + this.W_ZEIT + "'";
			}

			// manche Gewichte sind negativ und manche haben ein Space im Gewichtswert
			
			if (this.W_BRUTTO_GEWICHT != null){
				Double dbl = bibALL.makeDoublefromNumberString(this.W_BRUTTO_GEWICHT.replaceAll(" ", ""),",",".");
				if (dbl != null){
					this.GEWICHT = new BigDecimal(dbl);
				}
			}
			
			// der Status des Waagesatzes
			this.m_WaageFehlerBeschreibung = oWaageSatz.getErrorDescription();
			this.m_WaageResultStatus = oWaageSatz.getStatus();
		}
	}

	
	public String getID_WAEGUNG() {
		return ID_WAEGUNG;
	}
	public void setID_WAEGUNG(String iDWAEGUNG) {
		ID_WAEGUNG = iDWAEGUNG;
	}
	public String getID_WIEGEKARTE() {
		return ID_WIEGEKARTE;
	}
	public void setID_WIEGEKARTE(String iDWIEGEKARTE) {
		ID_WIEGEKARTE = iDWIEGEKARTE;
	}

	public String getHANDEINGABE() {
		return HANDEINGABE;
	}
	public void setHANDEINGABE(String hANDEINGABE) {
		HANDEINGABE = hANDEINGABE;
	}
	public BigDecimal getGEWICHT() {
		return GEWICHT;
	}
	public void setGEWICHT(BigDecimal gEWICHT) {
		GEWICHT = gEWICHT;
	}
	public String getDATUM() {
		return DATUM;
	}
	public void setDATUM(String dATUM) {
		DATUM = dATUM;
	}
	public String getZEIT() {
		return ZEIT;
	}
	public void setZEIT(String zEIT) {
		ZEIT = zEIT;
	}
	public String getWAEGUNG_POS() {
		return WAEGUNG_POS;
	}
	public void setWAEGUNG_POS(String wAEGUNGPOS) {
		WAEGUNG_POS = wAEGUNGPOS;
	}
	public String getSTORNO() {
		return STORNO;
	}
	public void setSTORNO(String sTORNO) {
		STORNO = sTORNO;
	}
	public String getW_FEHLERCODE() {
		return W_FEHLERCODE;
	}
	public void setW_FEHLERCODE(String wFEHLERCODE) {
		W_FEHLERCODE = wFEHLERCODE;
	}
	public String getW_STATUS() {
		return W_STATUS;
	}
	public void setW_STATUS(String wSTATUS) {
		W_STATUS = wSTATUS;
	}
	public String getW_DATUM() {
		return W_DATUM;
	}
	public void setW_DATUM(String wDATUM) {
		W_DATUM = wDATUM;
	}
	public String getW_ZEIT() {
		return W_ZEIT;
	}
	public void setW_ZEIT(String wZEIT) {
		W_ZEIT = wZEIT;
	}
	public String getW_IDENT_NR() {
		return W_IDENT_NR;
	}
	public void setW_IDENT_NR(String wIDENTNR) {
		W_IDENT_NR = wIDENTNR;
	}
	public String getW_WAAGEN_NR() {
		return W_WAAGEN_NR;
	}
	public void setW_WAAGEN_NR(String wWAAGENNR) {
		W_WAAGEN_NR = wWAAGENNR;
	}
	public String getW_BRUTTO_GEWICHT() {
		return W_BRUTTO_GEWICHT;
	}
	public void setW_BRUTTO_GEWICHT(String wBRUTTOGEWICHT) {
		W_BRUTTO_GEWICHT = wBRUTTOGEWICHT;
	}
	public String getW_TARAGEWICHT() {
		return W_TARAGEWICHT;
	}
	public void setW_TARAGEWICHT(String wTARAGEWICHT) {
		W_TARAGEWICHT = wTARAGEWICHT;
	}
	public String getW_NETTO_GEWICHT() {
		return W_NETTO_GEWICHT;
	}
	public void setW_NETTO_GEWICHT(String wNETTOGEWICHT) {
		W_NETTO_GEWICHT = wNETTOGEWICHT;
	}
	public String getW_EINHEIT() {
		return W_EINHEIT;
	}
	public void setW_EINHEIT(String wEINHEIT) {
		W_EINHEIT = wEINHEIT;
	}
	public String getW_TARACODE() {
		return W_TARACODE;
	}
	public void setW_TARACODE(String wTARACODE) {
		W_TARACODE = wTARACODE;
	}
	public String getW_WAEGEBEREICH() {
		return W_WAEGEBEREICH;
	}
	public void setW_WAEGEBEREICH(String wWAEGEBEREICH) {
		W_WAEGEBEREICH = wWAEGEBEREICH;
	}
	public String getW_TERMINAL() {
		return W_TERMINAL;
	}
	public void setW_TERMINAL(String wTERMINAL) {
		W_TERMINAL = wTERMINAL;
	}
	public String getW_PRUEFZIFFER() {
		return W_PRUEFZIFFER;
	}
	public void setW_PRUEFZIFFER(String wPRUEFZIFFER) {
		W_PRUEFZIFFER = wPRUEFZIFFER;
	}
	public String getWAAGE_DS_ORI() {
		return WAAGE_DS_ORI;
	}
	public void setWAAGE_DS_ORI(String wAAGEDSORI) {
		WAAGE_DS_ORI = wAAGEDSORI;
	}

	public String getID_USER_WAEGUNG() {
		return ID_USER_WAEGUNG;
	}

	public void setID_USER_WAEGUNG(String iD_USER_WAEGUNG) {
		ID_USER_WAEGUNG = iD_USER_WAEGUNG;
	}


	public void setID_WAAGE_SETTINGS(String iD_WAAGE_SETTINGS) {
		ID_WAAGE_SETTINGS = iD_WAAGE_SETTINGS;
	}

	public String getID_WAAGE_SETTINGS() {
		return ID_WAAGE_SETTINGS;
	}

	
	public void setHANDEINGABE_BEM(String hANDEINGABE_BEM){
		HANDEINGABE_BEM = hANDEINGABE_BEM;
	}
	
	
	public String getHANDEINGABE_BEM(){
		return HANDEINGABE_BEM;
	}
	
	
	/**
	 * Falls der Buchungssatz aus dem Wiegevorgang kommt, wird der Status der Wägung noch im Satz festgehalten
	 * @return
	 */
	public ENUM_WaageResultStatus get_WaageResultStatus(){
		return m_WaageResultStatus;
	}
	
	/**
	 * Falls der Buchungssatz aus dem Wiegevorgang kommt, wird ein evtl. Fehler hier in Klartext eingetragen
	 * @return
	 */
	public String get_WaageFehlerBeschreibung(){
		return m_WaageFehlerBeschreibung;
	}

	
	/**
	 * Gibt den SQLString für ein Insert aus den gegebenen Daten zurück
	 * @return
	 * @throws myException
	 */
	public String getSQLForInsert() throws myException{
		String sSql = "";
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
		BigDecimal dSaldo = BigDecimal.ZERO;

		oSql.addSQL_Paar("ID_WAEGUNG", "SEQ_WAEGUNG.NEXTVAL", false);
		oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
		oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    oSql.addSQL_Paar("ID_WIEGEKARTE", this.getID_WIEGEKARTE() , false);
	    oSql.addSQL_Paar("HANDEINGABE", this.getHANDEINGABE(), true);
	    oSql.addSQL_Paar("GEWICHT", this.getGEWICHT() != null ? this.getGEWICHT().toPlainString() : null ,false);
	    oSql.addSQL_Paar("DATUM", this.getDATUM() , false);
	    oSql.addSQL_Paar("ZEIT", this.getZEIT() ,false);
	    oSql.addSQL_Paar("WAEGUNG_POS",this.getWAEGUNG_POS() ,false);
	    oSql.addSQL_Paar("STORNO", this.getSTORNO(),true);
	    oSql.addSQL_Paar("W_FEHLERCODE", this.getW_FEHLERCODE(),true);
	    oSql.addSQL_Paar("W_STATUS", this.getW_STATUS() ,true);
	    oSql.addSQL_Paar("W_DATUM", this.getW_DATUM() ,true);
	    oSql.addSQL_Paar("W_ZEIT", this.getW_ZEIT(),true);
	    oSql.addSQL_Paar("W_IDENT_NR", this.getW_IDENT_NR(),true);
	    oSql.addSQL_Paar("W_WAAGEN_NR", this.getW_WAAGEN_NR() ,true);
	    oSql.addSQL_Paar("W_BRUTTO_GEWICHT", this.getW_BRUTTO_GEWICHT(),true);
	    oSql.addSQL_Paar("W_TARAGEWICHT", this.getW_TARAGEWICHT(),true);
	    oSql.addSQL_Paar("W_NETTO_GEWICHT",this.getW_NETTO_GEWICHT() ,true);
	    oSql.addSQL_Paar("W_EINHEIT", this.getW_EINHEIT() ,true);
	    oSql.addSQL_Paar("W_TARACODE", this.getW_TARACODE(),true);
	    oSql.addSQL_Paar("W_WAEGEBEREICH", this.getW_WAEGEBEREICH() ,true);
	    oSql.addSQL_Paar("W_TERMINAL", this.getW_TERMINAL() ,true);
	    oSql.addSQL_Paar("W_PRUEFZIFFER",this.getW_PRUEFZIFFER() ,true);
	    oSql.addSQL_Paar("WAAGE_DS_ORI",this.getWAAGE_DS_ORI() ,true);
	    oSql.addSQL_Paar("ID_USER_WAEGUNG", this.getID_USER_WAEGUNG(),false);
	    oSql.addSQL_Paar("ID_WAAGE_SETTINGS", this.getID_WAAGE_SETTINGS(),false);
	    oSql.addSQL_Paar("HANDEINGABE_BEM", this.getHANDEINGABE_BEM(), true);
	    

   	    sSql = oSql.get_CompleteInsertString("JT_WAEGUNG", bibE2.cTO());

		return sSql;
	}

	
	
}
