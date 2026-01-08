/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX_AENDERUNGEN;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * abgeleitete klasse, die die feldwerte der leistungsdaten prueft und evtl. wechseldaten zu neuen steuersaetzen beruecksichtigt
 */
public class RECORD_TAX_EXT extends RECORD_TAX {

	/**
	 * @param recordOrig
	 */
	public RECORD_TAX_EXT(MyRECORD recordOrig) {
		super(recordOrig);
	}
	
	
	
	public String getSteuerSatzKorrigiert(MyDate leistungsdatum, String cWhenEmpty) throws myException {
		return S.NN(this.getSteuerSatzKorrigiert(leistungsdatum),cWhenEmpty);
	}
	
	
	public String getSteuerSatzKorrigiert(MyDate leistungsdatum) throws myException {
		String cSteuerFormated = this.get_STEUERSATZ_cF_NN("");
		
		String cSteuerFormatedNachWechsel = this.get_STEUERSATZ_NEU_cF_NN("");
		
		
		if (leistungsdatum.isOK()) {
		
			if (S.isFull(cSteuerFormatedNachWechsel) && this.getSteuerWechselTermin().isOK()) {
				if (leistungsdatum.isGreaterEqualThan(this.getSteuerWechselTermin())) {
					cSteuerFormated = cSteuerFormatedNachWechsel;
				}
			}
			
			//jetzt nachsehen, ob es eine tax-aenderung gibt, die im leistungsdatum liegt
			RECLIST_TAX_AENDERUNGEN aenderungen = this.get_DOWN_RECORD_LIST_TAX_AENDERUNGEN_id_tax();
			if (aenderungen.size()>0) {
				for (RECORD_TAX_AENDERUNGEN aend: aenderungen) {
					MyDate von = new MyDate(aend.get_DateValue(TAX_AENDERUNGEN.gueltig_von.fn(), null));
					MyDate bis = new MyDate(aend.get_DateValue(TAX_AENDERUNGEN.gueltig_bis.fn(), null));
					
					if (leistungsdatum.isGreaterEqualThan(von) && bis.isGreaterEqualThan(leistungsdatum) ) {
						cSteuerFormated = aend.get_STEUERSATZ_cF_NN("");
						break;
					}
				}
			}
		}
		
		
		return cSteuerFormated;
	}

	
	public BigDecimal getBDSteuerSatzKorrigiert(MyDate leistungsdatum) throws myException {
		BigDecimal  bdSteuer = this.get_STEUERSATZ_bdValue(BigDecimal.ZERO);
		
		String cSteuerFormatedNachWechsel = this.get_STEUERSATZ_NEU_cF_NN("");
		
		if (leistungsdatum.isOK()) {
		
			if (S.isFull(cSteuerFormatedNachWechsel) && this.getSteuerWechselTermin().isOK()) {
				if (leistungsdatum.isGreaterEqualThan(this.getSteuerWechselTermin())) {
					bdSteuer = this.get_STEUERSATZ_NEU_bdValue(BigDecimal.ZERO);
				}
			}
			
			//jetzt nachsehen, ob es eine tax-aenderung gibt, die im leistungsdatum liegt
			RECLIST_TAX_AENDERUNGEN aenderungen = this.get_DOWN_RECORD_LIST_TAX_AENDERUNGEN_id_tax();
			if (aenderungen.size()>0) {
				for (RECORD_TAX_AENDERUNGEN aend: aenderungen) {
					MyDate von = new MyDate(aend.get_DateValue(TAX_AENDERUNGEN.gueltig_von.fn(), null));
					MyDate bis = new MyDate(aend.get_DateValue(TAX_AENDERUNGEN.gueltig_bis.fn(), null));
					
					if (leistungsdatum.isGreaterEqualThan(von) && bis.isGreaterEqualThan(leistungsdatum) ) {
						bdSteuer = aend.get_STEUERSATZ_bdValue(BigDecimal.ZERO);
						break;
					}
				}
			}
		}

		return bdSteuer;
	}
	
	
	
	private MyDate getSteuerWechselTermin() throws myException {
		return new MyDate(this.get_WECHSELDATUM_cF_NN(""));
	}
	
}
