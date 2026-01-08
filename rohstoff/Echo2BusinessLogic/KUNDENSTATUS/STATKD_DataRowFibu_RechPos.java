package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;

/**
 * Klasse bildet Eintrag ab,
 *  der zum Sammeln von Fibu-Daten für die Zeile in DataRowStatus dient.
 *  
 * Jeder eintrag STATKD_DataRowStatus bekommt zwei Vectoren mit STATKD_DataRowFuhreEntry-Einträgen
 * - für die gefahrenen Fuhren
 * - für die geplanten Fuhren
 * 
 * @author manfred
 *
 */
public class STATKD_DataRowFibu_RechPos {
	private BigDecimal dSteuersatz = null;
	private BigDecimal dPosPreisNetto = null;
	private BigDecimal dPosPreisBrutto = null;
	
	
	
	public STATKD_DataRowFibu_RechPos(BigDecimal bdSteuersatz, 
			BigDecimal bdPreisNetto,
			BigDecimal bdPreisBrutto) {
		super();
		set_Steuersatz(bdSteuersatz);
		set_PosPreisBrutto(bdPreisBrutto);
		set_PosPreisNetto(bdPreisNetto);
	}
	
	
	

	/**
	 * @param dSteuersatz the dSteuersatz to set
	 */
	public void set_Steuersatz(BigDecimal dSteuersatz) {
		this.dSteuersatz = dSteuersatz;
	}



	/**
	 * @return the dSteuersatz
	 */
	public BigDecimal get_Steuersatz() {
		return dSteuersatz;
	}



	/**
	 * @param dPosPreisNetto the dPosPreisNetto to set
	 */
	public void set_PosPreisNetto(BigDecimal dPosPreisNetto) {
		this.dPosPreisNetto = dPosPreisNetto;
	}



	/**
	 * @return the dPosPreisNetto
	 */
	public BigDecimal get_PosPreisNetto() {
		return dPosPreisNetto;
	}



	/**
	 * @param dPosPreisBrutto the dPosPreisBrutto to set
	 */
	public void set_PosPreisBrutto(BigDecimal dPosPreisBrutto) {
		this.dPosPreisBrutto = dPosPreisBrutto;
	}



	/**
	 * @return the dPosPreisBrutto
	 */
	public BigDecimal get_PosPreisBrutto() {
		return dPosPreisBrutto;
	}
	
}
