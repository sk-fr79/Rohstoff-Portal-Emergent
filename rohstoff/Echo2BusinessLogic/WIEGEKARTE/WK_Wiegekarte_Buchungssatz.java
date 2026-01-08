package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * Klasse bildet einen Datensatz der Wiegekarte ab
 * @author manfred
 *
 */
public class WK_Wiegekarte_Buchungssatz {
	private String ID_WIEGEKARTE = null;
	private String ERZEUGT_VON = null;
	private String ERZEUGT_AM = null;
	private String LFD_NR = null;
	private String ID_ADRESSE_LAGER = null;
	private String ID_ADRESSE_LIEFERANT = null;
	private String KENNZEICHEN = null;
	private String ID_ARTIKEL_SORTE = null;
	private String BEFUND = null;
	private BigDecimal NETTOGEWICHT = null;
	private String IST_LIEFERANT = null;
	private String STORNO = null;
	private String ADRESSE_LIEFERANT = null;
	
	private String ID_ARTIKEL_BEZ = null;
	private String SORTE_HAND = null;

	private String TYP_WIEGEKARTE = null;
	private String ID_ADRESSE_SPEDITION = null;
	private String BEMERKUNG1 = null;
	private String BEMERKUNG2 = null;
	private String BEMERKUNG_INTERN = null;
	private String IST_GESAMTVERWIEGUNG = null;
	private String IST_RADIOAKTIVITAETGEPRUEFT = null;
	
	private BigDecimal GEWICHT_ABZUG = null;
	private String GRUND_ABZUG = null;
	private BigDecimal GEWICHT_NACH_ABZUG = null;
	
	private BigDecimal GEWICHT_ABZUG_FUHRE = null;
	private BigDecimal GEWICHT_NACH_ABZUG_FUHRE = null;
	
	
	private String ID_ADRESSE_ABN_STRECKE = null;
	
	private String ID_WIEGEKARTE_PARENT = null;
	private String EINGANGSSCHEIN_NR = null;
	
	private String ADRESSE_SPEDITION = null;
	
	private String ID_VPOS_TPA_FUHRE = null;
	private String ID_VPOS_TPA_FUHRE_ORT = null;

	private String ID_VPOS_TPA_FUHRE_AUS_FUHRE = null;
	private String ID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE = null;
	
	private BigDecimal DRUCKZAEHLER = null;
	private BigDecimal DRUCKZAEHLER_ES = null;
	private String ID_WAAGE_STANDORT = null;
	
	// 2018-05-15
	private String CONTAINER_NR = null;
	private String SIEGEL_NR = null;
	


	/**
	 * Konstruktor aus Wiegekarten-Record-Objekt
	 * @param oRecord
	 * @throws myException 
	 */
	public WK_Wiegekarte_Buchungssatz(RECORD_WIEGEKARTE oRecord) throws myException{
		if (oRecord == null){ 
			return;
		}
		
		ID_WIEGEKARTE = oRecord.get_ID_WIEGEKARTE_cUF();
		ERZEUGT_VON = oRecord.get_ERZEUGT_VON_cUF();
		ERZEUGT_AM = oRecord.get_ERZEUGT_AM_cUF();
		LFD_NR = oRecord.get_LFD_NR_cUF();
		ID_ADRESSE_LAGER = oRecord.get_ID_ADRESSE_LAGER_cUF();
		ID_ADRESSE_LIEFERANT = oRecord.get_ID_ADRESSE_LIEFERANT_cUF();
		KENNZEICHEN = oRecord.get_KENNZEICHEN_cUF();
		ID_ARTIKEL_SORTE = oRecord.get_ID_ARTIKEL_SORTE_cUF();
		BEFUND = oRecord.get_BEFUND_cUF();
		NETTOGEWICHT = oRecord.get_NETTOGEWICHT_cUF() != null ? new BigDecimal(oRecord.get_NETTOGEWICHT_cUF()) : null ;
		IST_LIEFERANT = oRecord.get_IST_LIEFERANT_cUF();
		STORNO = oRecord.get_STORNO_cUF();
		ADRESSE_LIEFERANT = oRecord.get_ADRESSE_LIEFERANT_cUF();
		ID_ARTIKEL_BEZ = oRecord.get_ID_ARTIKEL_BEZ_cUF();
		
		TYP_WIEGEKARTE = oRecord.get_TYP_WIEGEKARTE_cUF();
		ID_ADRESSE_SPEDITION = oRecord.get_ID_ADRESSE_SPEDITION_cUF();
		BEMERKUNG1 = oRecord.get_BEMERKUNG1_cUF();
		BEMERKUNG2 = oRecord.get_BEMERKUNG2_cUF();
		BEMERKUNG_INTERN = oRecord.get_BEMERKUNG_INTERN_cUF();
		IST_GESAMTVERWIEGUNG = oRecord.get_IST_GESAMTVERWIEGUNG_cUF();
		IST_RADIOAKTIVITAETGEPRUEFT = oRecord.get_STRAHLUNG_GEPRUEFT_cUF();
		

		GEWICHT_ABZUG = oRecord.get_GEWICHT_ABZUG_cUF() != null ? oRecord.get_GEWICHT_ABZUG_bdValue(BigDecimal.ZERO,3) : null ;
		GRUND_ABZUG = oRecord.get_GRUND_ABZUG_cUF();
		GEWICHT_NACH_ABZUG = oRecord.get_GEWICHT_NACH_ABZUG_cUF() != null ? oRecord.get_GEWICHT_NACH_ABZUG_bdValue(BigDecimal.ZERO,3) : null ;
		
		ID_ADRESSE_ABN_STRECKE = oRecord.get_ID_ADRESSE_ABN_STRECKE_cUF();
		
		ID_WIEGEKARTE_PARENT = oRecord.get_ID_WIEGEKARTE_PARENT_cUF();
		EINGANGSSCHEIN_NR = oRecord.get_ES_NR_cUF();
		
		ADRESSE_SPEDITION = oRecord.get_ADRESSE_SPEDITION_cUF();
		SORTE_HAND = oRecord.get_SORTE_HAND_cUF();
		
		GEWICHT_ABZUG_FUHRE = oRecord.get_GEWICHT_ABZUG_FUHRE_cUF() != null ? oRecord.get_GEWICHT_ABZUG_FUHRE_bdValue(BigDecimal.ZERO,3) : null ;
		GEWICHT_NACH_ABZUG_FUHRE = oRecord.get_GEWICHT_NACH_ABZUG_FUHRE_cUF() != null ? oRecord.get_GEWICHT_NACH_ABZUG_FUHRE_bdValue(BigDecimal.ZERO,3) : null ;

		
		// 2011-04-12: Falsch: sonst kann man keine Verbindung zwischen Fuhre und Wiegekarte herstellen,
		// falls die Wiegekarte nicht vollständig gewogen wurde...
		ID_VPOS_TPA_FUHRE = oRecord.get_ID_VPOS_TPA_FUHRE_cUF();
		ID_VPOS_TPA_FUHRE_ORT = oRecord.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
		
		DRUCKZAEHLER = oRecord.get_DRUCKZAEHLER_bdValue(null);
		DRUCKZAEHLER_ES = oRecord.get_DRUCKZAEHLER_EINGANGSSCHEIN_bdValue(null);
		
		
		CONTAINER_NR 	= oRecord.get_CONTAINER_NR_cUF();
		SIEGEL_NR 		= oRecord.get_SIEGEL_NR_cUF();
		
		setID_WAAGE_STANDORT(oRecord.get_ID_WAAGE_STANDORT_cUF());

		// Die WK-Fuhre-Relation wird nun ausschliesslich in der Fuhre bzw. des Fuhrenortes gehalten
		getIDFuhre_ZugeordnetInFuhre();
		
	}
	
	/**
	 * liest die Fuhren und Fuhrenort-ID die Fuhrenseitig zu einer Wiegekarte zugeordnet sind:
	 * 1. In der Wiegekarte ist die Fuhre vermerkt, da sonst keine Unvollständigen Wiegekarten gemerkt werden. (diese werden immer erst nach der Gesamtwägung 
	 *   in die Fuhre übertragen.
	 * 2. In der Fuhre wird wechselseitig die Wiegekarte vermerkt.
	 * Problem: beide Referenzen müssen stimmen, sind aber abhängig.
	 * Falls die Übereinstimmung nicht gegeben ist, wird eine Warnung ausgegeben
	 */
	private void getIDFuhre_ZugeordnetInFuhre(){
		String sSql = "SELECT  W.id_wiegekarte, F.ID_VPOS_TPA_FUHRE, null as ID_FUHREN_ORT" +
				" FROM JT_WIEGEKARTE W" +
				" JOIN JT_VPOS_TPA_FUHRE F on W.ID_WIEGEKARTE =F.ID_WIEGEKARTE_LIEF OR W.ID_WIEGEKARTE =F.ID_WIEGEKARTE_ABN" +
				" WHERE W.ID_WIEGEKARTE = " + ID_WIEGEKARTE.toString() + 
				" UNION " +
				" SELECT W1.id_wiegekarte, F1.ID_VPOS_TPA_FUHRE, F1.ID_VPOS_TPA_FUHRE_ORT" +
				" FROM JT_WIEGEKARTE W1" +
				" JOIN JT_VPOS_TPA_FUHRE_ORT F1 on W1.ID_WIEGEKARTE = F1.ID_WIEGEKARTE" +
				" WHERE W1.ID_WIEGEKARTE = " + ID_WIEGEKARTE.toString();

		String[][] sFuhrenInfo = bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		if (sFuhrenInfo == null) return;
		
		if (sFuhrenInfo.length > 1){
					 
			StringBuilder sb = new StringBuilder();
			for (int i=0;i<sFuhrenInfo.length;i++)
			{
				sb.append( sFuhrenInfo[i][1]);
				sb.append( "-");
				sb.append( sFuhrenInfo[i][2]);
				sb.append(" - " );
			}

			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt eine doppelte Zuordnung einer Wiegekarte zu einer Fuhre: WiegekarteID: " 
					+ ID_WIEGEKARTE
					+ sb.toString()));
			
			return;
		} else if (sFuhrenInfo.length == 1){
			ID_VPOS_TPA_FUHRE_AUS_FUHRE= sFuhrenInfo[0][1];   
			ID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE = sFuhrenInfo[0][2];
			
			if ( !(bibALL.null2leer(ID_VPOS_TPA_FUHRE) + bibALL.null2leer(ID_VPOS_TPA_FUHRE_ORT)).equals(
					(bibALL.null2leer(ID_VPOS_TPA_FUHRE_AUS_FUHRE) + bibALL.null2leer(ID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE)))
				){
				
			}
			
		}
	}
	
	
	
	
	
	/** KONSTRUKTOR aus Einzelwerten
	 * 
	 * @param iDWIEGEKARTE
	 * @param eRZEUGTVON
	 * @param eRZEUGTAM
	 * @param lFDNR
	 * @param iDADRESSELAGER
	 * @param iDADRESSELIEFERANT
	 * @param kENNZEICHEN
	 * @param iDARTIKELSORTE
	 * @param bEFUND
	 * @param iDVPOSTPAFUHRE
	 * @param iDVPOSTPAFUHREORT
	 * @param nETTOGEWICHT
	 * @param iSTLIEFERANT
	 * @param STORNO
	 */
	public WK_Wiegekarte_Buchungssatz(
			String iDWIEGEKARTE, 
			String eRZEUGTVON,
			String eRZEUGTAM, 
			String lFDNR, 
			String iDADRESSELAGER,
			String iDADRESSELIEFERANT, 
			String kENNZEICHEN,
			String iDARTIKELSORTE, 
			String bEFUND, 
			String iDVPOSTPAFUHRE,
			String iDVPOSTPAFUHREORT, 
			BigDecimal nETTOGEWICHT,
			String iSTLIEFERANT,
			String sTORNO,
			String sADRESSE_LIEFERANT,
			String sIDArtikelBez,

			String sTYP_WIEGEKARTE,
			String sID_ADRESSE_SPEDITION,
			String sBEMERKUNG1 ,
			String sBEMERKUNG2 ,
			String sBEMERKUNG_INTERN ,
			String sIST_GESAMTVERWIEGUNG ,
			BigDecimal nGEWICHT_ABZUG ,
			String sGRUND_ABZUG ,
			BigDecimal nGEWICHT_NACH_ABZUG ,
			String sID_ADRESSE_ABN_STRECKE,
			String sID_WIEGEKARTE_PARENT,
			String sEINGANGSSCHEIN_NR,
			String sAdresseSpedition,
			String sSorteHand, 
			BigDecimal nDRUCKZAEHLER,
			BigDecimal nDRUCKZAEHLER_ES,
			BigDecimal nGEWICHT_ABZUG_FUHRE,
			BigDecimal nGEWICHT_NACH_ABZUG_FUHRE,
			String sIstRadioaktivitaetGeprueft,
			String sID_Waage_Standort,
			String sContainerNR,
			String sSiegelNR
		) {
		
		super();
		ID_WIEGEKARTE = iDWIEGEKARTE;
		ERZEUGT_VON = eRZEUGTVON;
		ERZEUGT_AM = eRZEUGTAM;
		LFD_NR = lFDNR;
		ID_ADRESSE_LAGER = iDADRESSELAGER;
		ID_ADRESSE_LIEFERANT = iDADRESSELIEFERANT;
		KENNZEICHEN = kENNZEICHEN;
		ID_ARTIKEL_SORTE = iDARTIKELSORTE;
		BEFUND = bEFUND;
		ID_VPOS_TPA_FUHRE = iDVPOSTPAFUHRE;
		ID_VPOS_TPA_FUHRE_ORT = iDVPOSTPAFUHREORT;
		NETTOGEWICHT = nETTOGEWICHT;
		IST_LIEFERANT = iSTLIEFERANT;
		STORNO = sTORNO;
		ADRESSE_LIEFERANT = sADRESSE_LIEFERANT;
		ID_ARTIKEL_BEZ = sIDArtikelBez;
		
		TYP_WIEGEKARTE = sTYP_WIEGEKARTE;
		ID_ADRESSE_SPEDITION = sID_ADRESSE_SPEDITION;
		BEMERKUNG1 = sBEMERKUNG1 ;
		BEMERKUNG2 = sBEMERKUNG2 ;
		BEMERKUNG_INTERN = sBEMERKUNG_INTERN ;
		IST_GESAMTVERWIEGUNG = sIST_GESAMTVERWIEGUNG ;
		GEWICHT_ABZUG = nGEWICHT_ABZUG ;
		GRUND_ABZUG = sGRUND_ABZUG ;
		GEWICHT_NACH_ABZUG = nGEWICHT_NACH_ABZUG; 
		ID_ADRESSE_ABN_STRECKE = sID_ADRESSE_ABN_STRECKE;
		ID_WIEGEKARTE_PARENT = sID_WIEGEKARTE_PARENT;
		EINGANGSSCHEIN_NR = sEINGANGSSCHEIN_NR;
		ADRESSE_SPEDITION = sAdresseSpedition;
		SORTE_HAND = sSorteHand;
		DRUCKZAEHLER = nDRUCKZAEHLER;
		DRUCKZAEHLER_ES =nDRUCKZAEHLER_ES;
		GEWICHT_ABZUG_FUHRE = nGEWICHT_ABZUG_FUHRE;
		GEWICHT_NACH_ABZUG_FUHRE = nGEWICHT_NACH_ABZUG_FUHRE;
		IST_RADIOAKTIVITAETGEPRUEFT = sIstRadioaktivitaetGeprueft;
		
		CONTAINER_NR = sContainerNR;
		SIEGEL_NR = sSiegelNR;
		
		setID_WAAGE_STANDORT(sID_Waage_Standort);
		
	}
	
	



	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new WK_Wiegekarte_Buchungssatz(ID_WIEGEKARTE, ERZEUGT_VON, ERZEUGT_AM, LFD_NR, ID_ADRESSE_LAGER,
				ID_ADRESSE_LIEFERANT, KENNZEICHEN, ID_ARTIKEL_SORTE,
				BEFUND, ID_VPOS_TPA_FUHRE, ID_VPOS_TPA_FUHRE_ORT, NETTOGEWICHT, IST_LIEFERANT,STORNO, ADRESSE_LIEFERANT,ID_ARTIKEL_BEZ,
				TYP_WIEGEKARTE, ID_ADRESSE_SPEDITION, BEMERKUNG1,	BEMERKUNG2, BEMERKUNG_INTERN, IST_GESAMTVERWIEGUNG ,
				GEWICHT_ABZUG, GRUND_ABZUG, GEWICHT_NACH_ABZUG, ID_ADRESSE_ABN_STRECKE,
				ID_WIEGEKARTE_PARENT,EINGANGSSCHEIN_NR, ADRESSE_SPEDITION, SORTE_HAND, DRUCKZAEHLER, DRUCKZAEHLER_ES, GEWICHT_ABZUG_FUHRE, GEWICHT_NACH_ABZUG_FUHRE,
				IST_RADIOAKTIVITAETGEPRUEFT,getID_WAAGE_STANDORT(),CONTAINER_NR,SIEGEL_NR);
	}
	
	
	public String getID_WIEGEKARTE() {
		return ID_WIEGEKARTE;
	}
	public void setID_WIEGEKARTE(String iDWIEGEKARTE) {
		ID_WIEGEKARTE = iDWIEGEKARTE;
	}
	public String getERZEUGT_VON() {
		return ERZEUGT_VON;
	}
	public void setERZEUGT_VON(String eRZEUGTVON) {
		ERZEUGT_VON = eRZEUGTVON;
	}
	public String getERZEUGT_AM() {
		return ERZEUGT_AM;
	}
	public void setERZEUGT_AM(String eRZEUGTAM) {
		ERZEUGT_AM = eRZEUGTAM;
	}
	public String getLFD_NR() {
		return LFD_NR;
	}
	public void setLFD_NR(String lFDNR) {
		LFD_NR = lFDNR;
	}
	public String getID_ADRESSE_LAGER() {
		return ID_ADRESSE_LAGER;
	}
	public void setID_ADRESSE_LAGER(String iDADRESSELAGER) {
		ID_ADRESSE_LAGER = iDADRESSELAGER;
	}
	public String getID_ADRESSE_LIEFERANT() {
		return ID_ADRESSE_LIEFERANT;
	}
	public void setID_ADRESSE_LIEFERANT(String iDADRESSELIEFERANT) {
		ID_ADRESSE_LIEFERANT = iDADRESSELIEFERANT;
	}
	public String getID_ADRESSE_ABN_STRECKE() {
		return ID_ADRESSE_ABN_STRECKE;
	}

	public void setID_ADRESSE_ABN_STRECKE(String iD_ADRESSE_ABN_STRECKE) {
		ID_ADRESSE_ABN_STRECKE = iD_ADRESSE_ABN_STRECKE;
	}

	public String getKENNZEICHEN() {
		return KENNZEICHEN;
	}
	public void setKENNZEICHEN(String kENNZEICHEN) {
		KENNZEICHEN = kENNZEICHEN;
	}
	public String getID_ARTIKEL_SORTE() {
		return ID_ARTIKEL_SORTE;
	}
	public void setID_ARTIKEL_SORTE(String iDARTIKELSORTE) {
		ID_ARTIKEL_SORTE = iDARTIKELSORTE;
	}
	public String getBEFUND() {
		return BEFUND;
	}
	public void setBEFUND(String bEFUND) {
		BEFUND = bEFUND;
	}
	public String getID_VPOS_TPA_FUHRE() {
		return ID_VPOS_TPA_FUHRE;
	}
	public void setID_VPOS_TPA_FUHRE(String iDVPOSTPAFUHRE) {
		ID_VPOS_TPA_FUHRE = iDVPOSTPAFUHRE;
	}
	public String getID_VPOS_TPA_FUHRE_ORT() {
		return ID_VPOS_TPA_FUHRE_ORT;
	}
	public void setID_VPOS_TPA_FUHRE_ORT(String iDVPOSTPAFUHREORT) {
		ID_VPOS_TPA_FUHRE_ORT = iDVPOSTPAFUHREORT;
	}
	public BigDecimal getNETTOGEWICHT() {
		return NETTOGEWICHT;
	}
	public void setNETTOGEWICHT(BigDecimal nETTOGEWICHT) {
		NETTOGEWICHT = nETTOGEWICHT;
	}
	public String getIST_LIEFERANT() {
		return IST_LIEFERANT;
	}
	public void setIST_LIEFERANT(String iSTLIEFERANT) {
		IST_LIEFERANT = iSTLIEFERANT;
	}
	public String getSTORNO() {
		return STORNO;
	}
	public void setSTORNO(String sTORNO) {
		STORNO = sTORNO;
	}

	public String getADRESSE_LIEFERANT(){
		return ADRESSE_LIEFERANT;
	}
	
	public void setADRESSE_LIEFERANT(String sADRESSE_LIEFERANT){
		ADRESSE_LIEFERANT = sADRESSE_LIEFERANT;
	}

	public String getID_ARTIKEL_BEZ() {
		return ID_ARTIKEL_BEZ;
	}

	public void setID_ARTIKEL_BEZ(String iD_ARTIKEL_BEZ) {
		ID_ARTIKEL_BEZ = iD_ARTIKEL_BEZ;
	}

	
	public String getTYP_WIEGEKARTE() {
		return TYP_WIEGEKARTE;
	}

	public void setTYP_WIEGEKARTE(String tYP_WIEGEKARTE) {
		TYP_WIEGEKARTE = tYP_WIEGEKARTE;
	}

	public String getID_ADRESSE_SPEDITION() {
		return ID_ADRESSE_SPEDITION;
	}

	public void setID_ADRESSE_SPEDITION(String iD_ADRESSE_SPEDITION) {
		ID_ADRESSE_SPEDITION = iD_ADRESSE_SPEDITION;
	}

	public String getBEMERKUNG1() {
		return BEMERKUNG1;
	}

	public void setBEMERKUNG1(String bEMERKUNG1) {
		BEMERKUNG1 = bEMERKUNG1;
	}

	public String getBEMERKUNG2() {
		return BEMERKUNG2;
	}

	public void setBEMERKUNG2(String bEMERKUNG2) {
		BEMERKUNG2 = bEMERKUNG2;
	}

	public String getBEMERKUNG_INTERN() {
		return BEMERKUNG_INTERN;
	}

	public void setBEMERKUNG_INTERN(String bEMERKUNG_INTERN) {
		BEMERKUNG_INTERN = bEMERKUNG_INTERN;
	}

	public String getIST_GESAMTVERWIEGUNG() {
		return IST_GESAMTVERWIEGUNG;
	}

	public void setIST_GESAMTVERWIEGUNG(String iST_GESAMTVERWIEGUNG) {
		IST_GESAMTVERWIEGUNG = iST_GESAMTVERWIEGUNG;
	}


	public BigDecimal getGEWICHT_ABZUG() {
		return GEWICHT_ABZUG;
	}

	public void setGEWICHT_ABZUG(BigDecimal gEWICHT_ABZUG) {
		GEWICHT_ABZUG = gEWICHT_ABZUG;
	}

	public String getGRUND_ABZUG() {
		return GRUND_ABZUG;
	}

	public void setGRUND_ABZUG(String gRUND_ABZUG) {
		GRUND_ABZUG = gRUND_ABZUG;
	}

	public BigDecimal getGEWICHT_NACH_ABZUG() {
		return GEWICHT_NACH_ABZUG;
	}

	public void setGEWICHT_NACH_ABZUG(BigDecimal gEWICHT_NACH_ABZUG) {
		GEWICHT_NACH_ABZUG = gEWICHT_NACH_ABZUG;
	}
	
	

	/**
	 * @param iD_WIEGEKARTE_PARENT the iD_WIEGEKARTE_PARENT to set
	 */
	public void setiD_WIEGEKARTE_PARENT(String iD_WIEGEKARTE_PARENT) {
		this.ID_WIEGEKARTE_PARENT = iD_WIEGEKARTE_PARENT;
	}

	/**
	 * @return the iD_WIEGEKARTE_PARENT
	 */
	public String getiD_WIEGEKARTE_PARENT() {
		return ID_WIEGEKARTE_PARENT;
	}

	public String getEINGANGSSCHEIN_NR() {
		return EINGANGSSCHEIN_NR;
	}

	public void setEINGANGSSCHEIN_NR(String eINGANGSSCHEIN_NR) {
		EINGANGSSCHEIN_NR = eINGANGSSCHEIN_NR;
	}
	
	
	/**
	 * @param ADRESSE_SPEDITION 
	 */
	public void setADRESSE_SPEDITION(String aDRESSE_SPEDITION) {
		ADRESSE_SPEDITION = aDRESSE_SPEDITION;
	}
	public String getADRESSE_SPEDITION() {
		return ADRESSE_SPEDITION;
	}

	
	public String getSORTE_HAND() {
		return SORTE_HAND;
	}

	public void setSORTE_HAND(String sORTE_HAND) {
		SORTE_HAND = sORTE_HAND;
	}

	/**
	 * @param iD_VPOS_TPA_FUHRE_AUS_FUHRE the iD_VPOS_TPA_FUHRE_AUS_FUHRE to set
	 */
	public void setID_VPOS_TPA_FUHRE_AUS_FUHRE(
			String iD_VPOS_TPA_FUHRE_AUS_FUHRE) {
		ID_VPOS_TPA_FUHRE_AUS_FUHRE = iD_VPOS_TPA_FUHRE_AUS_FUHRE;
	}

	/**
	 * @return the iD_VPOS_TPA_FUHRE_AUS_FUHRE
	 */
	public String getID_VPOS_TPA_FUHRE_AUS_FUHRE() {
		return ID_VPOS_TPA_FUHRE_AUS_FUHRE;
	}

	/**
	 * @param iD_VPOS_TPA_FUHRE_ORT_AUS_FUHRE the iD_VPOS_TPA_FUHRE_ORT_AUS_FUHRE to set
	 */
	public void setID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE(
			String iD_VPOS_TPA_FUHRE_ORT_AUS_FUHRE) {
		ID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE = iD_VPOS_TPA_FUHRE_ORT_AUS_FUHRE;
	}

	/**
	 * @return the iD_VPOS_TPA_FUHRE_ORT_AUS_FUHRE
	 */
	public String getID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE() {
		return ID_VPOS_TPA_FUHRE_ORT_AUS_FUHRE;
	}

	public BigDecimal getDRUCKZAEHLER() {
		return DRUCKZAEHLER;
	}

	public void setDRUCKZAEHLER(BigDecimal dRUCKZAEHLER) {
		DRUCKZAEHLER = dRUCKZAEHLER;
	}

	public String getIST_RADIOAKTIVITAETGEPRUEFT() {
		return IST_RADIOAKTIVITAETGEPRUEFT;
	}

	public void setIST_RADIOAKTIVITAETGEPRUEFT(String iST_RADIOAKTIVITAETGEPRUEFT) {
		IST_RADIOAKTIVITAETGEPRUEFT = iST_RADIOAKTIVITAETGEPRUEFT;
	}

	public BigDecimal getGEWICHT_ABZUG_FUHRE() {
		return GEWICHT_ABZUG_FUHRE;
	}

	public void setGEWICHT_ABZUG_FUHRE(BigDecimal gEWICHT_ABZUG_FUHRE) {
		GEWICHT_ABZUG_FUHRE = gEWICHT_ABZUG_FUHRE;
	}

	public BigDecimal getGEWICHT_NACH_ABZUG_FUHRE() {
		return GEWICHT_NACH_ABZUG_FUHRE;
	}

	public void setGEWICHT_NACH_ABZUG_FUHRE(BigDecimal gEWICHT_NACH_ABZUG_FUHRE) {
		GEWICHT_NACH_ABZUG_FUHRE = gEWICHT_NACH_ABZUG_FUHRE;
	}

	public String getID_WIEGEKARTE_PARENT() {
		return ID_WIEGEKARTE_PARENT;
	}

	public void setID_WIEGEKARTE_PARENT(String iD_WIEGEKARTE_PARENT) {
		ID_WIEGEKARTE_PARENT = iD_WIEGEKARTE_PARENT;
	}

	public BigDecimal getDRUCKZAEHLER_ES() {
		return DRUCKZAEHLER_ES;
	}

	public void setDRUCKZAEHLER_ES(BigDecimal dRUCKZAEHLER_ES) {
		DRUCKZAEHLER_ES = dRUCKZAEHLER_ES;
	}
	
	/**
	 * @param iD_WAAGE_STANDORT the iD_WAAGE_STANDORT to set
	 */
	public void setID_WAAGE_STANDORT(String iD_WAAGE_STANDORT) {
		ID_WAAGE_STANDORT = iD_WAAGE_STANDORT;
	}

	/**
	 * @return the iD_WAAGE_STANDORT
	 */
	public String getID_WAAGE_STANDORT() {
		return ID_WAAGE_STANDORT;
	}


	public String getCONTAINER_NR() {
		return CONTAINER_NR;
	}

	public void setCONTAINER_NR(String cONTAINER_NR) {
		CONTAINER_NR = cONTAINER_NR;
	}

	public String getSIEGEL_NR() {
		return SIEGEL_NR;
	}

	public void setSIEGEL_NR(String sIEGEL_NR) {
		SIEGEL_NR = sIEGEL_NR;
	}

	

	/**
	 * Gibt den SQLString für ein Insert aus den gegebenen Daten zurück
	 * @return
	 * @throws myException
	 */
	public String getSQLForInsert() throws myException{
		String sSql = "";
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		oSql.addSQL_Paar("ID_WIEGEKARTE", this.ID_WIEGEKARTE == null ? "SEQ_WIEGEKARTE.NEXTVAL": this.ID_WIEGEKARTE, false);
		oSql.addSQL_Paar("ERZEUGT_VON", this.ERZEUGT_VON == null ?  bibALL.get_KUERZEL(): this.ERZEUGT_VON, true);
		oSql.addSQL_Paar("ERZEUGT_AM", this.ERZEUGT_AM == null ? "SYSDATE" : this.ERZEUGT_AM , false);
	    oSql.addSQL_Paar("LFD_NR", this.getLFD_NR() == null ? "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL" : this.getLFD_NR() , false);
	    oSql.addSQL_Paar("ID_ADRESSE_LAGER", this.getID_ADRESSE_LAGER() , false);
	    oSql.addSQL_Paar("ID_ADRESSE_LIEFERANT", this.getID_ADRESSE_LIEFERANT() , false);
	    oSql.addSQL_Paar("KENNZEICHEN", this.getKENNZEICHEN() ,true);
	    oSql.addSQL_Paar("ID_ARTIKEL_SORTE",this.getID_ARTIKEL_SORTE() , false);
	    oSql.addSQL_Paar("BEFUND", this.getBEFUND() ,true);
	    oSql.addSQL_Paar("NETTOGEWICHT", this.getNETTOGEWICHT() != null ? this.getNETTOGEWICHT().toPlainString() : null , false);
	    oSql.addSQL_Paar("IST_LIEFERANT", this.getIST_LIEFERANT() ,true);
	    oSql.addSQL_Paar("STORNO", this.getSTORNO(),true);
	    oSql.addSQL_Paar("ADRESSE_LIEFERANT", this.getADRESSE_LIEFERANT(),true);
	    oSql.addSQL_Paar("ID_ARTIKEL_BEZ", this.getID_ARTIKEL_BEZ(), false);
	   
	    oSql.addSQL_Paar("TYP_WIEGEKARTE", this.getTYP_WIEGEKARTE(), true);
	    oSql.addSQL_Paar("ID_ADRESSE_SPEDITION", this.getID_ADRESSE_SPEDITION(), false);
	    oSql.addSQL_Paar("ADRESSE_SPEDITION", this.getADRESSE_SPEDITION(),true);
	    
	    oSql.addSQL_Paar("BEMERKUNG1", this.getBEMERKUNG1(), true);
	    oSql.addSQL_Paar("BEMERKUNG2", this.getBEMERKUNG2(), true);
	    oSql.addSQL_Paar("BEMERKUNG_INTERN", this.getBEMERKUNG_INTERN(), true);
	    
	    oSql.addSQL_Paar("IST_GESAMTVERWIEGUNG", this.getIST_GESAMTVERWIEGUNG(), true);
	    oSql.addSQL_Paar("STRAHLUNG_GEPRUEFT",this.getIST_RADIOAKTIVITAETGEPRUEFT(),true);

	    // Gewichte werden gesondert gerechnet
//	    oSql.addSQL_Paar("GEWICHT_ABZUG", this.getGEWICHT_ABZUG() != null ? this.getGEWICHT_ABZUG().toPlainString() : null , false);
//	    oSql.addSQL_Paar("GRUND_ABZUG", this.getGRUND_ABZUG(), true);
//	    oSql.addSQL_Paar("GEWICHT_NACH_ABZUG", this.getGEWICHT_NACH_ABZUG() != null ? this.getGEWICHT_NACH_ABZUG().toPlainString() : null , false);
//	    
//	    oSql.addSQL_Paar("GEWICHT_ABZUG_FUHRE", this.getGEWICHT_ABZUG_FUHRE() != null ? this.getGEWICHT_ABZUG_FUHRE().toPlainString() : null , false);
//	    oSql.addSQL_Paar("GEWICHT_NACH_ABZUG_FUHRE", this.getGEWICHT_NACH_ABZUG_FUHRE() != null ? this.getGEWICHT_NACH_ABZUG_FUHRE().toPlainString() : null , false);

	    
	    oSql.addSQL_Paar("ID_ADRESSE_ABN_STRECKE", this.getID_ADRESSE_ABN_STRECKE(), false);
	    oSql.addSQL_Paar("ID_WIEGEKARTE_PARENT", this.getiD_WIEGEKARTE_PARENT(), false);
	    oSql.addSQL_Paar("ES_NR", this.getEINGANGSSCHEIN_NR(), true);
	    oSql.addSQL_Paar("SORTE_HAND", this.getSORTE_HAND(), true);
	    
	    // Fuhre in der Wiegekarte merken..
	    oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE", this.getID_VPOS_TPA_FUHRE(), false);
	    oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT", this.getID_VPOS_TPA_FUHRE_ORT(), false);
	    oSql.addSQL_Paar("ID_WAAGE_STANDORT", this.getID_WAAGE_STANDORT(), false);
	    
	    
	    // 2018-05-15
	    oSql.addSQL_Paar(WIEGEKARTE.container_nr.fieldName(), this.getCONTAINER_NR(), true);
	    oSql.addSQL_Paar(WIEGEKARTE.siegel_nr.fieldName(), this.getSIEGEL_NR(), true);
	    
	    
	    
	    sSql = oSql.get_CompleteInsertString("JT_WIEGEKARTE", bibE2.cTO());

		return sSql;
	}
	
	
	
	/**
	 * übernimmt die Werte des Buchungssatzes in den übergebenen Wiegekarten-Record
	 * @param recWiegekarte
	 * @throws myException 
	 */
	public void setUpdateValues (RECORD_WIEGEKARTE recWiegekarte) throws myException{
		
		recWiegekarte.set_NEW_VALUE_BEFUND(this.getBEFUND());
		recWiegekarte.set_NEW_VALUE_GEAENDERT_VON(bibALL.get_ID_USER());
		recWiegekarte.set_NEW_VALUE_ID_ADRESSE_LIEFERANT(this.getID_ADRESSE_LIEFERANT());
		recWiegekarte.set_NEW_VALUE_ID_ARTIKEL_SORTE(this.getID_ARTIKEL_SORTE());
		recWiegekarte.set_NEW_VALUE_IST_LIEFERANT(this.getIST_LIEFERANT());
		recWiegekarte.set_NEW_VALUE_KENNZEICHEN(this.getKENNZEICHEN());
		recWiegekarte.set_NEW_VALUE_STORNO(this.getSTORNO());
		recWiegekarte.set_NEW_VALUE_ADRESSE_LIEFERANT(this.getADRESSE_LIEFERANT());
		recWiegekarte.set_NEW_VALUE_ID_ARTIKEL_BEZ(this.getID_ARTIKEL_BEZ());

		
		recWiegekarte.set_NEW_VALUE_ID_ADRESSE_SPEDITION(this.getID_ADRESSE_SPEDITION());
		recWiegekarte.set_NEW_VALUE_ADRESSE_SPEDITION(this.getADRESSE_SPEDITION());
		
		recWiegekarte.set_NEW_VALUE_ID_ADRESSE_ABN_STRECKE(this.getID_ADRESSE_ABN_STRECKE());
		recWiegekarte.set_NEW_VALUE_TYP_WIEGEKARTE(this.getTYP_WIEGEKARTE());
		recWiegekarte.set_NEW_VALUE_IST_GESAMTVERWIEGUNG(this.getIST_GESAMTVERWIEGUNG());
		
		recWiegekarte.set_NEW_VALUE_STRAHLUNG_GEPRUEFT(this.getIST_RADIOAKTIVITAETGEPRUEFT());
		
		recWiegekarte.set_NEW_VALUE_BEMERKUNG_INTERN(this.getBEMERKUNG_INTERN());
		recWiegekarte.set_NEW_VALUE_BEMERKUNG1(this.getBEMERKUNG1());
		recWiegekarte.set_NEW_VALUE_BEMERKUNG2(this.getBEMERKUNG2());
		recWiegekarte.set_NEW_VALUE_BEMERKUNG_INTERN(this.getBEMERKUNG_INTERN());

		
		recWiegekarte.set_NEW_VALUE_ID_WIEGEKARTE_PARENT(this.getiD_WIEGEKARTE_PARENT());
		recWiegekarte.set_NEW_VALUE_ES_NR(this.getEINGANGSSCHEIN_NR());
		recWiegekarte.set_NEW_VALUE_SORTE_HAND(this.getSORTE_HAND());
		
		recWiegekarte.set_NEW_VALUE_ID_VPOS_TPA_FUHRE(this.getID_VPOS_TPA_FUHRE());
		recWiegekarte.set_NEW_VALUE_ID_VPOS_TPA_FUHRE_ORT(this.getID_VPOS_TPA_FUHRE_ORT());
		recWiegekarte.set_NEW_VALUE_ID_WAAGE_STANDORT(this.getID_WAAGE_STANDORT());
		
		// 2018-05-15
		recWiegekarte.set_NEW_VALUE_CONTAINER_NR(this.getCONTAINER_NR());
		recWiegekarte.set_NEW_VALUE_SIEGEL_NR(this.getSIEGEL_NR());

	}


	
	

}
