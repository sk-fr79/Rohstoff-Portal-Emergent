/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 02.12.2019
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.MyGregorianCalendar;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;

/**
 * @author martin
 * @date 02.12.2019
 *
 */
public class PdServiceRechneZahlungsZiel {

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 */
	public PdServiceRechneZahlungsZiel() {
	}

	
	/**
	 * 
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param leistungsDatum
	 * @param rechnungsDatum
	 * @param zahlungsBedingungen
	 * @return result-zahlungsdatum or null if referenzdate is null
	 * @throws Exception
	 */
	public Date getZahlungsZiel(Date leistungsDatum, Date rechnungsDatum, Rec21 zahlungsBedingungen) throws Exception {
		
		//normalfall ist das leistungsdatum als referenz
		Date referenzDatum = zahlungsBedingungen.is_yes_db_val(ZAHLUNGSBEDINGUNGEN.zahldat_calc_rechdat)?rechnungsDatum:leistungsDatum;
		
		if (referenzDatum==null) {
			return null;
		} else {
			MyGregorianCalendar ziel = new MyGregorianCalendar(referenzDatum);
			
			Long lFixMonat = 	zahlungsBedingungen.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixmonat);
			Long lFixTag =   	zahlungsBedingungen.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixtag);
			Long lZahlTage =   	zahlungsBedingungen.getLongDbValue(ZAHLUNGSBEDINGUNGEN.zahltage);

			
			if (lFixMonat != null && lFixTag != null) {
			
				for (int i=0;i<lFixMonat.intValue();i++) {
					ziel = myDateHelper.Find_First_Day_NextMonth(ziel);
				}
				if (lFixTag.longValue()>=1) {
					ziel = myDateHelper.Add_Day_To_Calendar(lFixTag.intValue()-1, ziel);
				}
			} else if (lZahlTage != null) {
				ziel = myDateHelper.Add_Day_To_Calendar(lZahlTage.intValue(), ziel);
			}
			return ziel.getTime();
		}
		
		
	}
	
	
	
}
