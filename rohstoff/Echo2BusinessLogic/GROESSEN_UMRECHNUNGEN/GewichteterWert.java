package rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN;

import java.math.BigDecimal;

/**
 * Basiswert der ein Wert (Gewicht/Preis) und die dazugehörige Gewichtung hat 
 * @author manfred
 * @date   12.07.2012
 */

public class GewichteterWert {
		private BigDecimal bdWert = null;
		private BigDecimal bdGewichtung = null;
		
		
		
		public GewichteterWert(){
			
		}
		
		public GewichteterWert(BigDecimal Wert, BigDecimal Gewichtung){
			bdWert = Wert;
			bdGewichtung = Gewichtung;
		}
		
		
		/**
		 * @param bdWert the bdGewichtswert to set
		 */
		public void setWert(BigDecimal bdWert) {
			this.bdWert = bdWert;
		}
		/**
		 * @return the bdWert
		 */
		public BigDecimal getWert() {
			return bdWert;
		}
		/**
		 * @param bdGewichtung the bdUmrechnungsfaktor to set
		 */
		public void setGewichtung(BigDecimal bdGewichtung) {
			this.bdGewichtung = bdGewichtung;
		}
		/**
		 * @return the bdUmrechnungsfaktor
		 */
		public BigDecimal getGewichtung() {
			return bdGewichtung;
		}
		
		
		
}
