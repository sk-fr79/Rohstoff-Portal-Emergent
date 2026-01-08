package rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

public class BerechnungPreis {


	
	/**
	 * Ermittelt einen neuen Einzelpreis mit einem neuen Gewichtungsfaktor
	 * (z.B. zur Umwandlung von Euro/t nach Euro/kg)
	 * 
	 * @author manfred
	 * @date   12.07.2012
	 * @param Preis
	 * @param GewichtungNeu
	 * @return 
	 * @throws myException 
	 */
	public GewichteterWert berechnePreisMitGewichtung(GewichteterWert Preis, BigDecimal GewichtungNeu) throws myException{
		
		GewichteterWert pErgebnis = new GewichteterWert();
		
		try {
			pErgebnis.setGewichtung(GewichtungNeu);
			
			BigDecimal bdPreisNeu = (Preis.getWert().divide(Preis.getGewichtung(), 10, BigDecimal.ROUND_HALF_UP)).multiply(GewichtungNeu);
			pErgebnis.setWert( bdPreisNeu );
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der gewichteten Preisberechnung", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der gewichteten Preisberechnung", e_def);
		}
		
		return pErgebnis;
	}
	
	
	
	
	/**
	 * Ermittelt den Einzelpreis der Basiseinheit (immer Gewichtung = 1)
	 * @author manfred
	 * @date   12.07.2012
	 * @param Preis
	 * @return
	 * @throws myException 
	 */
	public GewichteterWert berechnePreisMitBasisgewichtung(GewichteterWert Preis) throws myException{
		
		GewichteterWert pErgebnis = new GewichteterWert();
		
		try {
			pErgebnis.setGewichtung(BigDecimal.ONE);
			
			BigDecimal bdPreisNeu = Preis.getWert().divide(Preis.getGewichtung(), 10, BigDecimal.ROUND_HALF_UP);
			pErgebnis.setWert( bdPreisNeu );
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der Preisberechnung mit der Basiseinheit", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der Preisberechnung mit der Basiseinheit", e_def);
		}
		
		return pErgebnis;
	}
	
	

	/**
	 * Ermittelt den Gesamtpreis anhand der gewichteten Werte
	 * @author manfred
	 * @date   16.07.2012
	 * @param Preis
	 * @param Menge
	 * @return
	 * @throws myException 
	 */
	public BigDecimal berechneGesamtpreis (GewichteterWert Preis, GewichteterWert Menge) throws myException{
		if (Preis == null || Menge == null) return null;

		BigDecimal bdRet = null;
		
		try {
			BigDecimal bdTemp = Menge.getWert().multiply(Preis.getWert());
			BigDecimal bdTempFaktor = Menge.getGewichtung().divide(Preis.getGewichtung(),10,BigDecimal.ROUND_HALF_UP);
			if (bdTemp == null || bdTempFaktor == null) return null;
			
			bdRet = bdTemp.multiply(bdTempFaktor);
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der Gesamtpreisberechnung", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der Gesamtpreisberechnung", e_def);
		}
		
		return bdRet;
	}
	
	
	
	
	
}
