/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Rec21_mwstschluessel extends Rec21 {

	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_mwstschluessel() throws myException {
		super(_TAB.mwstschluessel);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_mwstschluessel(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.mwstschluessel) {
			throw new myException("baseRec must be rec of table mwstschluessel !");
		}
	}

	
	
	public RecList21  getAenderungsZeiten() throws myException {
		RecList21 rlTaxes = this.get_down_reclist21(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel,null, MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn());
		return rlTaxes;
	}
	
	
	public BigDecimal getSteuersatz(Date leistungsDatum) throws myException {
		if (leistungsDatum==null) { throw new myException("Error leistungsDatum MUST not be null!! <ddcce58c-2e06-4aeb-bfa3-252f7fad8ed0>");};
		
		try {
			BigDecimal  steuersatz =  (BigDecimal)this.getRawVal(MWSTSCHLUESSEL.steuersatz);
			
			RecList21 rlAenderungsZeiten = this.getAenderungsZeiten();
			
			//datumswerte via strings vergleichen, damit keine minuten o.ä. oder timestamps
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String sLeistungsDatum = sf.format(leistungsDatum);
			
			
			for (Rec21 steuer_aenderungen: rlAenderungsZeiten) {
				String sGueltigVon = sf.format(steuer_aenderungen.getDateDbValue(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von));
				String sGueltigBis = sf.format(steuer_aenderungen.getDateDbValue(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis));
				
				if (sLeistungsDatum.compareTo(sGueltigVon)>=0 && sLeistungsDatum.compareTo(sGueltigBis)<=0) {
					steuersatz = steuer_aenderungen.getBigDecimalDbValue(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz);
				}
			}
			return steuersatz;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	
	
	
	
	
}
