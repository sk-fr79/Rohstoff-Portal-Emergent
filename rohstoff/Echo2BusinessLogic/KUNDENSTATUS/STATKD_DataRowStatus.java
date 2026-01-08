package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Klasse zur temporären Datenhaltung der Forderungsbeträge
 * @author manfred
 *
 */
public class STATKD_DataRowStatus {

	private String m_AdressID = null;

	private BigDecimal dFIBU_FORDERUNG = null;
	private BigDecimal dRECH_FORDERUNG = null;
	private BigDecimal dFREIEPOS_FORDERUNG = null;
	
	
	// die einzelnen Fuhren-Daten nur als Schreibzugriff realisiert
	private BigDecimal dFUHRE_FORDERUNG = null;						// Fuhren die geliefert sind aber noch keine Abrechnung vorhanden ist
	private BigDecimal dFUHRE_FORDERUNG_GEPLANT = null;
	
	private BigDecimal dFIBU_VERBINDLICHKEIT = null;
	private BigDecimal dRECH_VERBINDLICHKEIT = null;
	private BigDecimal dFREIEPOS_VERBINDLICHKEIT = null;

	// die einzelnen Fuhren-Daten nur als Schreibzugriff realisiert
	private BigDecimal dFUHRE_VERBINDLICHKEIT = null;
	private BigDecimal dFUHRE_VERBINDLICHKEIT_GEPLANT = null;
	
	
	private Vector<STATKD_DataRowFuhreEntry> vFuhreGefahren_Forderung = new Vector<STATKD_DataRowFuhreEntry>();
	private Vector<STATKD_DataRowFuhreEntry> vFuhreGeplant_Forderung = new Vector<STATKD_DataRowFuhreEntry>();
	
	private Vector<STATKD_DataRowFuhreEntry> vFuhreGefahren_Verbindlichkeit = new Vector<STATKD_DataRowFuhreEntry>();
	private Vector<STATKD_DataRowFuhreEntry> vFuhreGeplant_Verbindlichkeit = new Vector<STATKD_DataRowFuhreEntry>();
	
	// eine Hashtable die alle Fibu-Buchungsbloecke eines Kunden verwaltet um den Netto-Betrag der Fibu aus den Rechnungspositionen zu ermitteln
	// innerhalb des Buchungsblockes sind die einzelnen Rechnungspositionen abgelegt 
	private Hashtable<String, STATKD_DataRowFibu_Buchungsblock>  htBuchungsblock_FORDERUNG = new Hashtable<String, STATKD_DataRowFibu_Buchungsblock>();
	private Hashtable<String, STATKD_DataRowFibu_Buchungsblock>  htBuchungsblock_VERBINDLICHKEIT = new Hashtable<String, STATKD_DataRowFibu_Buchungsblock>();
	
	
	
	public STATKD_DataRowStatus(String m_AdressID) {
		super();
		this.m_AdressID = m_AdressID;
	}


	public STATKD_DataRowStatus(String p_AdressID, 
//			BigDecimal pdFIBU_FORD,
			BigDecimal pdRECH_FORD, 
			BigDecimal pdFREIEPOS_FORD,
//			BigDecimal pdFIBU_VERB,
			BigDecimal pdRECH_VERB, 
			BigDecimal pdFREIEPOS_VERB){
		super();
		
		this.m_AdressID = p_AdressID;
//		this.dFIBU_FORDERUNG = pdFIBU_FORD != null ? pdFIBU_FORD : BigDecimal.ZERO ;
		this.dRECH_FORDERUNG = pdRECH_FORD != null ? pdRECH_FORD : BigDecimal.ZERO ;
		this.dFREIEPOS_FORDERUNG = pdFREIEPOS_FORD != null ? pdFREIEPOS_FORD : BigDecimal.ZERO ;
		
//		this.dFIBU_VERBINDLICHKEIT = pdFIBU_VERB != null ? pdFIBU_VERB : BigDecimal.ZERO ;
		this.dRECH_VERBINDLICHKEIT = pdRECH_VERB != null ? pdRECH_VERB : BigDecimal.ZERO ;
		this.dFREIEPOS_VERBINDLICHKEIT = pdFREIEPOS_VERB != null ? pdFREIEPOS_VERB : BigDecimal.ZERO ;
	}

	
	
	
	public String get_AdressID() {
		return m_AdressID;
	}
	public void set_AdressID(String m_AdressID) {
		this.m_AdressID = m_AdressID;
	}


	public BigDecimal get_FIBU_FORDERUNG() {
		return (dFIBU_FORDERUNG !=null ? dFIBU_FORDERUNG : BigDecimal.ZERO);
	}
//	public void set_FIBU_FORDERUNG(BigDecimal dFIBU) {
//		this.dFIBU_FORDERUNG = dFIBU;
//	}


	public BigDecimal get_RECH_FORDERUNG() {
		return (dRECH_FORDERUNG != null ? dRECH_FORDERUNG : BigDecimal.ZERO);
	}
	public void set_RECH_FORDERUNG(BigDecimal dRECH) {
		this.dRECH_FORDERUNG = dRECH;
	}


	public BigDecimal get_FREIEPOS_FORDERUNG() {
		return (dFREIEPOS_FORDERUNG != null ? dFREIEPOS_FORDERUNG : BigDecimal.ZERO);
	}
	public void set_FREIEPOS_FORDERUNG(BigDecimal dFREIEPOS) {
		this.dFREIEPOS_FORDERUNG = dFREIEPOS;
	}

	
	/**
	 * @param dFUHRE  -- WRITE_ONLY to public
	 */
	public BigDecimal get_FUHRE_FORDERUNG(){
		return this.dFUHRE_FORDERUNG != null ? this.dFUHRE_FORDERUNG: BigDecimal.ZERO ;
	}
	
	
	public BigDecimal get_FUHRE_FORDERUNG_GEPLANT(){
		return this.dFUHRE_FORDERUNG_GEPLANT != null ? this.dFUHRE_FORDERUNG_GEPLANT : BigDecimal.ZERO;
	}
	

	/**
	 * Gibt die Summe aller Forderungen zurück
	 * 
	 * @return  FIBU_FORDERUNG + FREIEPOS_FORDERUNG + RECHNUNGS_FORDERUNG + FUHRE_FORDERUNG + FUHRE_FORDERUNG_GEPLANT
	 */
	public BigDecimal get_Gesamt_FORDERUNG() {
		return get_FIBU_FORDERUNG().add(get_FREIEPOS_FORDERUNG()).add(get_RECH_FORDERUNG()).add(get_FUHRE_FORDERUNG().add(get_FUHRE_FORDERUNG_GEPLANT()));
	}


	//
	//  Verbindlichkeiten
	//
	public BigDecimal get_FIBU_VERBINDLICHKEIT() {
		return (dFIBU_VERBINDLICHKEIT !=null ? dFIBU_VERBINDLICHKEIT : BigDecimal.ZERO);
	}
//	public void set_FIBU_VERBINDLICHKEIT(BigDecimal dFIBU) {
//		this.dFIBU_VERBINDLICHKEIT = dFIBU;
//	}


	public BigDecimal get_RECH_VERBINDLICHKEIT() {
		return (dRECH_VERBINDLICHKEIT != null ? dRECH_VERBINDLICHKEIT : BigDecimal.ZERO);
	}
	public void set_RECH_VERBINDLICHKEIT(BigDecimal dRECH) {
		this.dRECH_VERBINDLICHKEIT = dRECH;
	}


	public BigDecimal get_FREIEPOS_VERBINDLICHKEIT() {
		return (dFREIEPOS_VERBINDLICHKEIT != null ? dFREIEPOS_VERBINDLICHKEIT : BigDecimal.ZERO);
	}
	public void set_FREIEPOS_VERBINDLICHKEIT(BigDecimal dFREIEPOS) {
		this.dFREIEPOS_VERBINDLICHKEIT = dFREIEPOS;
	}

	
	
	public BigDecimal get_FUHRE_VERBINDLICHKEIT(){
		return this.dFUHRE_VERBINDLICHKEIT != null ? this.dFUHRE_VERBINDLICHKEIT: BigDecimal.ZERO ;
	}
	
	public BigDecimal get_FUHRE_VERBINDLICHKEIT_GEPLANT(){
		return this.dFUHRE_VERBINDLICHKEIT_GEPLANT != null ? this.dFUHRE_VERBINDLICHKEIT_GEPLANT : BigDecimal.ZERO;
	}

	
	/**
	 * Gibt die Summe aller Forderungen zurück
	 * 
	 * @return  FIBU_VERBINDLICHKEIT + FREIEPOS_VERBINDLICHKEIT + RECHNUNGS_VERBINDLICHKEIT + FUHRE_VERBINDLICHKEIT + FUHRE_VERBINDLICHKEIT_GEPLANT
	 */
	public BigDecimal get_Gesamt_VERBINDLICHKEIT() {
		return get_FIBU_VERBINDLICHKEIT().add(get_FREIEPOS_VERBINDLICHKEIT()).add(get_RECH_VERBINDLICHKEIT()).add(get_FUHRE_VERBINDLICHKEIT().add(get_FUHRE_VERBINDLICHKEIT_GEPLANT()));
	}


	
	/**
	 * Gibt die Summe von Vorderungen und Verbindlichkeiten zurück
	 * 
	 * @return
	 */
	public BigDecimal get_Gesamt(){
		return get_Gesamt_FORDERUNG().add(get_Gesamt_VERBINDLICHKEIT());
	}

	
	
	/**
	 * Fügt ein Fuhreneintrag zum internen Vector der gefahrenen Fuhren dazu
	 * @param oFuhreEntry
	 */
	public void add_FuhreGefahrenForderung(STATKD_DataRowFuhreEntry oFuhreEntry){
		if (oFuhreEntry != null){
			this.vFuhreGefahren_Forderung.add(oFuhreEntry);
		}
	}
	
	
	public void add_FuhreGeplantForderung(STATKD_DataRowFuhreEntry oFuhreEntry){
		if (oFuhreEntry != null){
			this.vFuhreGeplant_Forderung.add(oFuhreEntry);
		}
	}
	
	

	/**
	 * Fügt einen Rechnungspositions-Eintrag eines FORDERUNG-BUCHUNGSBLOCKS ein
	 * @author manfred
	 * @date   10.04.2013
	 * @param sBuchungsblockNr
	 * @param bdSteuersatz
	 * @param bdNettoBetrag
	 * @param bdBruttoBetrag
	 */
	public void add_FibuEntry_FORDERUNG (String sBuchungsblockNr, BigDecimal bdSteuersatz, BigDecimal bdNettoBetrag, BigDecimal bdBruttoBetrag){
		add_FibuEntry(true, sBuchungsblockNr, bdSteuersatz, bdNettoBetrag, bdBruttoBetrag);
	}
	
	/**
	 * Fügt einen Rechnungspositions-Eintrag eines VERBINDLICHKEITS-BUCHUNGSBLOCKS ein
	 * @author manfred
	 * @date   10.04.2013
	 * @param sBuchungsblockNr
	 * @param bdSteuersatz
	 * @param bdNettoBetrag
	 * @param bdBruttoBetrag
	 */
	public void add_FibuEntry_VERBINDLICHKEIT (String sBuchungsblockNr, BigDecimal bdSteuersatz, BigDecimal bdNettoBetrag, BigDecimal bdBruttoBetrag){
		add_FibuEntry(false, sBuchungsblockNr, bdSteuersatz, bdNettoBetrag, bdBruttoBetrag);
	}
	
	
	/**
	 * Fügt einen Rechnungspositions-Eintrag oder eine Zahlungsvorgans-Eintrag in den Buchungsblock ein für die spätere Ermittlung des Buchugsblock-Netto-Betrags
	 * @author manfred
	 * @date   10.04.2013
	 * @param sBuchungsblockNr
	 * @param bdSteuersatz
	 * @param bdNettoBetrag
	 * @param bdBruttoBetrag
	 */
	private void add_FibuEntry(boolean bForderung, String sBuchungsblockNr, BigDecimal bdSteuersatz, BigDecimal bdNettoBetrag, BigDecimal bdBruttoBetrag){
		
		Hashtable<String, STATKD_DataRowFibu_Buchungsblock>  htBuchungsblock = (bForderung ? htBuchungsblock_FORDERUNG : htBuchungsblock_VERBINDLICHKEIT); 
			
		STATKD_DataRowFibu_Buchungsblock oBlock = null;
		
		
		STATKD_DataRowFibu_RechPos pos = new STATKD_DataRowFibu_RechPos(bdSteuersatz, bdNettoBetrag, bdBruttoBetrag);
		
		if (htBuchungsblock.containsKey(sBuchungsblockNr) ){
			oBlock = htBuchungsblock.get(sBuchungsblockNr);
			oBlock.addDataRowFibu_RechPos(pos);
		} else {
			oBlock = new STATKD_DataRowFibu_Buchungsblock(bForderung);
			oBlock.addDataRowFibu_RechPos(pos);
			htBuchungsblock.put(sBuchungsblockNr, oBlock);
		}
	}
	

	
	
	/**
	 * Fügt ein Fuhreneintrag zum internen Vector der gefahrenen Fuhren dazu
	 * @param oFuhreEntry
	 */
	public void add_FuhreGefahrenVerbindlichkeit(STATKD_DataRowFuhreEntry oFuhreEntry){
		if (oFuhreEntry != null){
			this.vFuhreGefahren_Verbindlichkeit.add(oFuhreEntry);
		}
	}
	
	
	public void add_FuhreGeplantVerbindlichkeit(STATKD_DataRowFuhreEntry oFuhreEntry){
		if (oFuhreEntry != null){
			this.vFuhreGeplant_Verbindlichkeit.add(oFuhreEntry);
		}
	}


	/**
	 * Gibt die Liste aller Fuhren-Ids zurück, die bei der Ermittlung der Summe berücksichtigt wurden
	 * @author manfred
	 * @date 05.07.2018
	 *
	 * @return
	 */
	public Vector<String> getIDs_Fuhre_Verbindlichkeiten(){
		Vector<String> vIDs = new Vector<>();
		for (STATKD_DataRowFuhreEntry o: this.vFuhreGefahren_Verbindlichkeit){
			vIDs.add(o.getID_Fuhre());
		}
		return vIDs;
	}
	
	/**
	 * Gibt die Liste aller Fuhren-Ids zurück, die bei der Ermittlung der Summe berücksichtigt wurden
	 * @author manfred
	 * @date 05.07.2018
	 *
	 * @return
	 */
	public Vector<String> getIDs_Fuhre_Verbindlichkeiten_geplant(){
		Vector<String> vIDs = new Vector<>();
		for (STATKD_DataRowFuhreEntry o: this.vFuhreGeplant_Verbindlichkeit){
			vIDs.add(o.getID_Fuhre());
		}
		return vIDs;
	}

	/**
	 * Gibt die Liste aller Fuhren-Ids zurück, die bei der Ermittlung der Summe berücksichtigt wurden
	 * @author manfred
	 * @date 05.07.2018
	 *
	 * @return
	 */
	public Vector<String> getIDs_Fuhre_Forderungen(){
		Vector<String> vIDs = new Vector<>();
		for (STATKD_DataRowFuhreEntry o: this.vFuhreGefahren_Forderung){
			vIDs.add(o.getID_Fuhre());
		}
		return vIDs;
	}

	/**
	 * Gibt die Liste aller Fuhren-Ids zurück, die bei der Ermittlung der Summe berücksichtigt wurden
	 * @author manfred
	 * @date 05.07.2018
	 *
	 * @return
	 */
	public Vector<String> getIDs_Fuhre_Forderungen_geplant(){
		Vector<String> vIDs = new Vector<>();
		for (STATKD_DataRowFuhreEntry o: this.vFuhreGeplant_Forderung){
			vIDs.add(o.getID_Fuhre());
		}
		return vIDs;
	}

	/**
	 * Berechnet die gesamten Fuhren-Daten der Einträge des Kunden
	 */
	public void calculateDaten(){
		
		STATKD_DataRowFuhreEntry entry;
		BigDecimal bdvalue = BigDecimal.ZERO;
		// FORDERUNGEN
		Iterator<STATKD_DataRowFuhreEntry> itr = vFuhreGefahren_Forderung.iterator();
		while (itr.hasNext()){
			entry = itr.next();
			bdvalue = bdvalue.add(entry.getPreisGesamt());
		}
		this.dFUHRE_FORDERUNG = bdvalue;
		
		// FORDERUNGEN_GEPLANT
		bdvalue = BigDecimal.ZERO;
		itr = vFuhreGeplant_Forderung.iterator();
		while (itr.hasNext()){
			entry = itr.next();
			bdvalue = bdvalue.add(entry.getPreisGesamt());
		}
		this.dFUHRE_FORDERUNG_GEPLANT = bdvalue;
		
		// VERBINDLICHKEITEN
		bdvalue = BigDecimal.ZERO;
		itr = vFuhreGefahren_Verbindlichkeit.iterator();
		while (itr.hasNext()){
			entry = itr.next();
			bdvalue = bdvalue.add(entry.getPreisGesamt());
		}
		this.dFUHRE_VERBINDLICHKEIT = bdvalue;

		// VERBINDLICHKEITEN_GEPLANT
		bdvalue = BigDecimal.ZERO;
		itr = vFuhreGeplant_Verbindlichkeit.iterator();
		while (itr.hasNext()){
			entry = itr.next();
			bdvalue = bdvalue.add(entry.getPreisGesamt());
		}
		this.dFUHRE_VERBINDLICHKEIT_GEPLANT = bdvalue;
		
		
		//
		// die Fibu-Werte ermitteln
		//
		bdvalue = BigDecimal.ZERO;
		STATKD_DataRowFibu_Buchungsblock entryBB;
		Iterator<STATKD_DataRowFibu_Buchungsblock>itrBB = htBuchungsblock_FORDERUNG.values().iterator();
		while (itrBB.hasNext() ){
			entryBB = itrBB.next();
			bdvalue = bdvalue.add( entryBB.getBuchungsblockBetrag());
		}
		this.dFIBU_FORDERUNG = bdvalue;
		
		bdvalue = BigDecimal.ZERO;
		itrBB = htBuchungsblock_VERBINDLICHKEIT.values().iterator();
		while (itrBB.hasNext() ){
			entryBB = itrBB.next();
			bdvalue = bdvalue.add( entryBB.getBuchungsblockBetrag());
		}
		this.dFIBU_VERBINDLICHKEIT = bdvalue;
		
	}
	
}
