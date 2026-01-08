/**
 * panter.gmbh.indep
 * @author martin
 * @date 02.12.2019
 * 
 */
package panter.gmbh.indep;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author martin
 * @date 02.12.2019
 *
 */
public class MyGregorianCalendar extends GregorianCalendar {

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 */
	public MyGregorianCalendar() {
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param zone
	 */
	public MyGregorianCalendar(TimeZone zone) {
		super(zone);
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param aLocale
	 */
	public MyGregorianCalendar(Locale aLocale) {
		super(aLocale);
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param zone
	 * @param aLocale
	 */
	public MyGregorianCalendar(TimeZone zone, Locale aLocale) {
		super(zone, aLocale);
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public MyGregorianCalendar(int year, int month, int dayOfMonth) {
		super(year, month, dayOfMonth);
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minute
	 */
	public MyGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
		super(year, month, dayOfMonth, hourOfDay, minute);
	}

	/**
	 * @author martin
	 * @date 02.12.2019
	 *
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 */
	public MyGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
		super(year, month, dayOfMonth, hourOfDay, minute, second);
	}

	
	
	public MyGregorianCalendar(Date date) {
		this();
		this.setTime(date);
	}
	
	
	
	public MyGregorianCalendar(GregorianCalendar cal) {
		this.setTime(cal.getTime());
	}
	
}
