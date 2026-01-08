/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import panter.gmbh.indep.Pair;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface PdServiceGetKalenderWocheFromDate {
	
	/**
	 * 
	 * @param date  (java.util.Date)  
	 * @param locale /when null, dann automatisch Locale.Germany
	 * @return
	 * @throws myException
	 */
	public int getKalenderWoche(Date date, Locale locale) throws myException;
	
	public default Pair<Date> getStartAndEndFromKW(Date dateInKW, Locale locale) throws myException {
		
		if (locale==null) {
			locale=Locale.GERMANY;
		}
		
		int actualKW = this.getKalenderWoche(dateInKW, locale);
		
		Calendar cal = Calendar.getInstance(Locale.GERMANY);
		cal.setTime(dateInKW);

		Date dateFirst = null;
		Date dateLast = null;
		
		cal.add(Calendar.DATE, -8);
		for (int i=0; i< 20; i++) {
			cal.add(Calendar.DATE, 1);
			if (this.getKalenderWoche(cal.getTime(), locale)==actualKW) {
				if (dateFirst==null) {
					dateFirst = cal.getTime();
				}
				dateLast = cal.getTime();
			}
		}
		
		return new Pair<Date>(dateFirst, dateLast);
		
	}
	
	
	
	
	public default Pair<Date> getStartAndEndFromKW(int calendarWeek, int year, Locale locale) throws myException {
		
		if (locale==null) {
			locale=Locale.GERMANY;
		}

		
		GregorianCalendar cal = new GregorianCalendar(year-1,11,20);

		Date dateFirst = null;
		Date dateLast = null;
		
		for (int i=0; i< 356; i++) {
			cal.add(Calendar.DATE, 1);

			if (this.getKalenderWoche(cal.getTime(), locale)==calendarWeek && cal.get(Calendar.YEAR)==year) {
				if (dateFirst==null) {
					dateFirst = cal.getTime();
				}
				dateLast = cal.getTime();
			}
		}
		
		return new Pair<Date>(dateFirst, dateLast);
		
	}

}
