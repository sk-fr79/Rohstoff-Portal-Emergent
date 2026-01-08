package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.Vector;

public class WK_RECORD_FuhrenInfo {
	
	private String ID_VPOS_TPA_FUHRE = null;
	private String ID_VPOS_TPA_FUHREN_ORT = null;
	private String KENNZEICHEN = null;
	private String ID_ADRESSE_START = null;
	private String ID_ADRESSE_ZIEL = null;
	private String TRANSPORTKENNZEICHEN = null;
	private String ANHAENGERKENNZEICHEN = null;
	private String IST_LIEFERANT = null;
	private String NAME1 = null;
	private String NAME2 = null;
	private String NAME3 = null;
	private String STRASSE = null;
	private String HAUSNUMMER = null;
	private String PLZ = null;
	private String ORT = null;
	private String WIEGEKARTEN_KENNER_ABLADEN = null;
	private String WIEGEKARTEN_KENNER_LADEN = null;
	private String LADEMENGE = null;
	private String ABLADEMENGE_ABN = null;
	private String ABLADEMENGE_LIEF = null;
	private String ID_SORTE = null;
	private String ID_ARTIKEL_BEZ = null;
	private String ID_ADRESSE_SPEDITION = null;
	private String BUCHUNGSNUMMER = null;

	//	Infos für Streckenfuhren
	private String STRECKE_NAME1 = null;
	private String STRECKE_NAME2 = null;
	private String STRECKE_NAME3 = null;
	private String STRECKE_STRASSE = null;
	private String STRECKE_HAUSNUMMER = null;
	private String STRECKE_PLZ = null;
	private String STRECKE_ORT = null;
	
	private Vector<String> vLAGERLISTE = new Vector<String>();
	
	
	/**
	 * Liest die Liste aller Läger der Wiegestelle um bei der Auswertung Vergleiche zu haben
	 * @author manfred
	 * @date   04.10.2013
	 * @return
	 */
	public Vector<String> getLagerListe () {
		return vLAGERLISTE;
	}
	
	
	/**
	 * Setzt die Liste aller Läger der Wiegestelle um bei der Auswertung Vergleiche zu haben
	 * @author manfred
	 * @date   04.10.2013
	 * @param vList
	 */
	public void setLagerListe(Vector<String> vList){
		vLAGERLISTE = vList;
	}
	
	
	public String getORT() {
		return ORT;
	}
	public void setORT(String oRT) {
		ORT = oRT;
	}
	public String getLADEMENGE() {
		return LADEMENGE;
	}
	public void setLADEMENGE(String lADEMENGE) {
		LADEMENGE = lADEMENGE;
	}
	public String getABLADEMENGE_ABN() {
		return ABLADEMENGE_ABN;
	}
	public void setABLADEMENGE_ABN(String aBLADEMENGEABN) {
		ABLADEMENGE_ABN = aBLADEMENGEABN;
	}
	public String getABLADEMENGE_LIEF() {
		return ABLADEMENGE_LIEF;
	}
	public void setABLADEMENGE_LIEF(String aBLADEMENGELIEF) {
		ABLADEMENGE_LIEF = aBLADEMENGELIEF;
	}
	public String getID_SORTE() {
		return ID_SORTE;
	}
	public void setID_SORTE(String iDSORTE) {
		ID_SORTE = iDSORTE;
	}
	public String getID_VPOS_TPA_FUHRE() {
		return ID_VPOS_TPA_FUHRE;
	}
	public void setID_VPOS_TPA_FUHRE(String iDVPOSTPAFUHRE) {
		ID_VPOS_TPA_FUHRE = iDVPOSTPAFUHRE;
	}
	public String getID_VPOS_TPA_FUHREN_ORT() {
		return ID_VPOS_TPA_FUHREN_ORT;
	}
	public void setID_VPOS_TPA_FUHREN_ORT(String iDVPOSTPAFUHRENORT) {
		ID_VPOS_TPA_FUHREN_ORT = iDVPOSTPAFUHRENORT;
	}
	public String getKENNZEICHEN() {
		return KENNZEICHEN;
	}
	public void setKENNZEICHEN(String kENNZEICHEN) {
		KENNZEICHEN = kENNZEICHEN;
	}
	public String getID_ADRESSE_START() {
		return ID_ADRESSE_START;
	}
	public void setID_ADRESSE_START(String iDADRESSELAGERSTART) {
		ID_ADRESSE_START = iDADRESSELAGERSTART;
	}
	public String getID_ADRESSE_ZIEL() {
		return ID_ADRESSE_ZIEL;
	}
	public void setID_ADRESSE_ZIEL(String iDADRESSELAGERZIEL) {
		ID_ADRESSE_ZIEL = iDADRESSELAGERZIEL;
	}
	public String getTRANSPORTKENNZEICHEN() {
		return TRANSPORTKENNZEICHEN;
	}
	public void setTRANSPORTKENNZEICHEN(String tRANSPORTKENNZEICHEN) {
		TRANSPORTKENNZEICHEN = tRANSPORTKENNZEICHEN;
	}
	public String getANHAENGERKENNZEICHEN() {
		return ANHAENGERKENNZEICHEN;
	}
	public void setANHAENGERKENNZEICHEN(String aNHAENGERKENNZEICHEN) {
		ANHAENGERKENNZEICHEN = aNHAENGERKENNZEICHEN;
	}
	public String getIST_LIEFERANT() {
		return IST_LIEFERANT;
	}
	public void setIST_LIEFERANT(String iSTLIEFERANT) {
		IST_LIEFERANT = iSTLIEFERANT;
	}
	public String getNAME1() {
		return NAME1;
	}
	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}
	public String getNAME2() {
		return NAME2;
	}
	public void setNAME2(String nAME2) {
		NAME2 = nAME2;
	}
	public String getNAME3() {
		return NAME3;
	}
	public void setNAME3(String nAME3) {
		NAME3 = nAME3;
	}
	public String getSTRASSE() {
		return STRASSE;
	}
	public void setSTRASSE(String sTRASSE) {
		STRASSE = sTRASSE;
	}
	public String getHAUSNUMMER() {
		return HAUSNUMMER;
	}
	public void setHAUSNUMMER(String hAUSNUMMER) {
		HAUSNUMMER = hAUSNUMMER;
	}
	public String getPLZ() {
		return PLZ;
	}
	public void setPLZ(String pLZ) {
		PLZ = pLZ;
	}
	public String getWIEGEKARTEN_KENNER_ABLADEN() {
		return WIEGEKARTEN_KENNER_ABLADEN;
	}
	public void setWIEGEKARTEN_KENNER_ABLADEN(String wIEGEKARTENKENNERABLADEN) {
		WIEGEKARTEN_KENNER_ABLADEN = wIEGEKARTENKENNERABLADEN;
	}
	public String getWIEGEKARTEN_KENNER_LADEN() {
		return WIEGEKARTEN_KENNER_LADEN;
	}
	public void setWIEGEKARTEN_KENNER_LADEN(String wIEGEKARTENKENNERLADEN) {
		WIEGEKARTEN_KENNER_LADEN = wIEGEKARTENKENNERLADEN;
	}
	public void setID_ARTIKEL_BEZ(String iD_ARTIKEL_BEZ) {
		ID_ARTIKEL_BEZ = iD_ARTIKEL_BEZ;
	}
	public String getID_ARTIKEL_BEZ() {
		return ID_ARTIKEL_BEZ;
	}
	public void setID_ADRESSE_SPEDITION(String iD_ADRESSE_SPEDITION) {
		ID_ADRESSE_SPEDITION = iD_ADRESSE_SPEDITION;
	}
	public String getID_ADRESSE_SPEDITION() {
		return ID_ADRESSE_SPEDITION;
	}
	/**
	 * @param bUCHUNGSNUMMER the bUCHUNGSNUMMER to set
	 */
	public void setBUCHUNGSNUMMER(String bUCHUNGSNUMMER) {
		BUCHUNGSNUMMER = bUCHUNGSNUMMER;
	}
	/**
	 * @return the bUCHUNGSNUMMER
	 */
	public String getBUCHUNGSNUMMER() {
		return BUCHUNGSNUMMER;
	}
	
	private String IST_STRECKE = null;
	public String getIST_STRECKE() {
		return IST_STRECKE;
	}
	public void setIST_STRECKE(String iST_STRECKE) {
		IST_STRECKE = iST_STRECKE;
	}
	public String getSTRECKE_NAME1() {
		return STRECKE_NAME1;
	}
	public void setSTRECKE_NAME1(String sTRECKE_NAME1) {
		STRECKE_NAME1 = sTRECKE_NAME1;
	}
	public String getSTRECKE_NAME2() {
		return STRECKE_NAME2;
	}
	public void setSTRECKE_NAME2(String sTRECKE_NAME2) {
		STRECKE_NAME2 = sTRECKE_NAME2;
	}
	public String getSTRECKE_NAME3() {
		return STRECKE_NAME3;
	}
	public void setSTRECKE_NAME3(String sTRECKE_NAME3) {
		STRECKE_NAME3 = sTRECKE_NAME3;
	}
	public String getSTRECKE_STRASSE() {
		return STRECKE_STRASSE;
	}
	public void setSTRECKE_STRASSE(String sTRECKE_STRASSE) {
		STRECKE_STRASSE = sTRECKE_STRASSE;
	}
	public String getSTRECKE_HAUSNUMMER() {
		return STRECKE_HAUSNUMMER;
	}
	public void setSTRECKE_HAUSNUMMER(String sTRECKE_HAUSNUMMER) {
		STRECKE_HAUSNUMMER = sTRECKE_HAUSNUMMER;
	}
	public String getSTRECKE_PLZ() {
		return STRECKE_PLZ;
	}
	public void setSTRECKE_PLZ(String sTRECKE_PLZ) {
		STRECKE_PLZ = sTRECKE_PLZ;
	}
	public String getSTRECKE_ORT() {
		return STRECKE_ORT;
	}
	public void setSTRECKE_ORT(String sTRECKE_ORT) {
		STRECKE_ORT = sTRECKE_ORT;
	}

	
}
