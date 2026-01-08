package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.indep.exceptions.myException;


public class bibDate {

	static SimpleDateFormat dfDateISO = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat dfDateTimeISO = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Wandelt einen ISO-Date-String in ein Date-Objekt um
	 * @author manfred
	 * @date   27.07.2012
	 * @param sDatumISO
	 * @return
	 * @throws myException 
	 */
	public static Date String2Date (String sDatumISO) throws myException{
		if (sDatumISO == null || sDatumISO.trim().isEmpty()) return null;
		
		Date dt = null;
		try {
			dt = dfDateISO.parse(sDatumISO);
		} catch (ParseException e) {
			throw new myException("ParseExcemption: " + sDatumISO + " konnte nicht mit dem Format " + dfDateISO.toLocalizedPattern() + " geparst werden.");
		}
		
		return dt;
	}
	
	/**
	 * Wandelt ein Datum java.util.Date in ein String im ISO-Format yyyy-MM-dd um
	 * 
	 * @author manfred
	 * @date   07.10.2015
	 *
	 * @param date2Convert
	 * @return
	 */
	public static String Date2StringISO (java.util.Date date2Convert){
		if (date2Convert == null) return null;
		String sDate = null;
		sDate = dfDateISO.format(date2Convert); 
		return sDate;
	}
	
	
	
	/**
	 * Wandelt einen ISO-DateTime-String in ein Date-Objekt um
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @author manfred
	 * @date   27.07.2012
	 * @param sDatumISO
	 * @return
	 * @throws myException 
	 */
	public static Date String2DateTime (String sDatumISO) throws myException{
		if (sDatumISO == null || sDatumISO.trim().isEmpty()) return null;
		Date dt = null;
		try {
			dt = dfDateTimeISO.parse(sDatumISO);
		} catch (ParseException e) {
			throw new myException("ParseExcemption: " + sDatumISO + " konnte nicht mit dem Format " + dfDateISO.toLocalizedPattern() + " geparst werden.");
		}
		return dt;
	}
	
	
	
	
}
