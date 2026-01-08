package rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

public class BerechnungGewicht {
	
	
	/**
	 * Addiert Gewicht g1 und g2
	 * und gibt das Gewicht gErgebnis mit der Gewichtung von g1 zurück
	 * 
	 * @author manfred
	 * @date   12.07.2012
	 * @param g1
	 * @param g2
	 * @return gErgebnis (mit den Einheiten von g1) 
	 * @throws myException 
	 */
	public GewichteterWert addGewicht(GewichteterWert g1, GewichteterWert g2) throws myException{
		
		if (g1 == null || g2 == null) return null;
		
		GewichteterWert gErgebnis = new GewichteterWert();
		try {
			gErgebnis.setGewichtung(g1.getGewichtung());
			
			
			// g2 umrechnen auf Einheit von g1
			BigDecimal bdG2neu = g2.getWert().multiply(g2.getGewichtung())
				.multiply( BigDecimal.ONE.divide(g1.getGewichtung(), 10, BigDecimal.ROUND_HALF_UP) );
			
			// gErgebnis ist g1 + G2neu
			gErgebnis.setWert( g1.getWert().add(bdG2neu) );
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der Berechnung der Gewichtsaddition", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der Berechnung der Gewichtsaddition", e_def);
		}
		
		
		
		return gErgebnis;
	}
	

	/**
	 * Subtrahiert das Gewicht von g2 von g1
	 * und gibt das Gewicht gErgebnis mit der Gewichtung von g1 zurück
	 * @author manfred
	 * @date   12.07.2012
	 * @param g1
	 * @param g2
	 * @return
	 * @throws myException 
	 */
	public GewichteterWert subtractGewicht(GewichteterWert g1, GewichteterWert g2) throws myException{
		if (g1 == null || g2 == null) return null;
		
		GewichteterWert gErgebnis = new GewichteterWert();
		try {
			gErgebnis.setGewichtung(g1.getGewichtung());
			
			// g2 umrechnen auf Einheit von g1
			BigDecimal bdG2neu = g2.getWert().multiply(g2.getGewichtung())
				.multiply( BigDecimal.ONE.divide(g1.getGewichtung(), 10, BigDecimal.ROUND_HALF_UP) );
			
			gErgebnis.setWert(g1.getWert().subtract(bdG2neu));
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der Berechnung des Gewichtsabzugs", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der Berechnung des Gewichtsabzugs", e_def);
		}
		
		return gErgebnis;
	}
	
	
	
	/**
	 * konvertiert den Gewichtswert von einer Gewichtung zu einer anderen 
	 * @author manfred
	 * @date   02.08.2012
	 * @param g1
	 * @param GewichtungNeu
	 * @return
	 * @throws myException 
	 */
	public GewichteterWert  convertGewicht( GewichteterWert g1, BigDecimal GewichtungNeu) throws myException{
		if (g1 == null || GewichtungNeu == null) return null;
		
		GewichteterWert gErgebnis = new GewichteterWert();
		
		try {
			gErgebnis.setGewichtung(GewichtungNeu);
			
			BigDecimal bdNeu = g1.getWert().multiply( g1.getGewichtung().divide(GewichtungNeu,10,BigDecimal.ROUND_HALF_UP) );
			gErgebnis.setWert(bdNeu);
		} catch (ArithmeticException e) {
			throw new myException("Arithmetischer Fehler bei der Umrechnung der Gewichtung", e);
		} catch (Exception e_def){
			throw new myException("Allgemeiner Fehler bei der Umrechnung der Gewichtung", e_def);
		}
		
		return gErgebnis;
	}
	
	
}
