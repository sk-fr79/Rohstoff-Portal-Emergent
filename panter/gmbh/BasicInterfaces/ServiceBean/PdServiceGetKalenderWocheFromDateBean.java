/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGetKalenderWocheFromDate;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PdServiceGetKalenderWocheFromDateBean implements PdServiceGetKalenderWocheFromDate {

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGetKalenderWocheFromDate#getKalenderWoche(java.util.Date)
	 */
	@Override
	public int getKalenderWoche(Date date, Locale locale) throws myException {

		if (locale==null) {
			locale=Locale.GERMANY;
		}

		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		int year = 			gc.get(Calendar.YEAR);
		int month = 		gc.get(Calendar.MONTH)+1;
		int dayOfMonth = 	gc.get(Calendar.DATE);
		
		TemporalField woy = WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear();
		
		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
		int weekNumber = localDate.get(woy);
		
		return weekNumber;
	}

}
