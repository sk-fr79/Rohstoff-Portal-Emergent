package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;

/**
 * Klasse bildet Eintrag ab,
 *  der zum Sammeln von Fuhren-Daten für die Zeile in DataRowStatus dient.
 * Jeder eintrag STATKD_DataRowStatus bekommt zwei Vectoren mit STATKD_DataRowFuhreEntry-Einträgen
 * - für die gefahrenen Fuhren
 * - für die geplanten Fuhren
 * 
 * @author manfred
 *
 */
public class STATKD_DataRowFuhreEntry {
	private String     sID_ARTIKEL = null;
	private BigDecimal dMenge = null;
	private BigDecimal dPreisEinzel = null;
	private String	   sID_Fuhre = null;
	
	
	public STATKD_DataRowFuhreEntry(
			String sID_ARTIKEL, 
			BigDecimal dMenge,
			BigDecimal dPreisEinzel,
			String sID_FUHRE) {
		super();
		this.sID_ARTIKEL = sID_ARTIKEL;
		this.dMenge = dMenge;
		this.dPreisEinzel = dPreisEinzel;
		this.sID_Fuhre = sID_FUHRE;
	}
	
	
	public String getID_ARTIKEL() {
		return sID_ARTIKEL;
	}
	public void setID_ARTIKEL(String sID_ARTIKEL) {
		this.sID_ARTIKEL = sID_ARTIKEL;
	}
	public BigDecimal getMenge() {
		return dMenge;
	}
	public void setMenge(BigDecimal dMenge) {
		this.dMenge = dMenge;
	}
	public BigDecimal getPreisEinzel() {
		return dPreisEinzel;
	}
	
	public void setPreisEinzel(BigDecimal dPreisEinzel) {
		this.dPreisEinzel = dPreisEinzel;
	}

	public void setID_Fuhre(String idFuhre){
		this.sID_Fuhre = idFuhre;
	}
	
	public String getID_Fuhre(){
		return this.sID_Fuhre;
	}
	
	
	
	/**
	 * gibt den Gesamtpreis des Eintrags zurück, wenn Menge und Preis gesetzt sind.
	 * 
	 * null sonst.
	 * @return
	 */
	public BigDecimal getPreisGesamt(){
		BigDecimal bRet = BigDecimal.ZERO;
		if (dMenge != null && dPreisEinzel != null){
			bRet = dMenge.multiply(dPreisEinzel);
		}
		return bRet;
	}
	
}
