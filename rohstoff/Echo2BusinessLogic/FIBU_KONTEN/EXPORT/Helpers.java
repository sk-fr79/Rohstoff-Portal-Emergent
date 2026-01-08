package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import panter.gmbh.indep.S;

/**
 * Helper class with some static methods.
 * @author nils
 *
 */
public class Helpers {
	/** 
	 * Returns the difference between two dates as a human-readable string.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String printDifference(Date startDate, Date endDate) {
		// 1 minute = 60 seconds
		// 1 hour = 60 x 60 = 3600
		// 1 day = 3600 x 24 = 86400

		// milliseconds
		long different = endDate.getTime() - startDate.getTime();

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		StringBuilder sb = new StringBuilder(50);

		if (elapsedDays > 0) {
			sb.append(elapsedDays);
			sb.append(" days");
		}
		if (sb.length() > 0) {
			sb.append(",");
		}

		if (elapsedHours > 0) {
			sb.append(elapsedHours);
			sb.append(" hours");
		}
		if (sb.length() > 0) {
			sb.append(",");
		}

		if (elapsedMinutes > 0) {
			sb.append(elapsedMinutes);
			sb.append(" minutes");
		}
		if (sb.length() > 0) {
			sb.append(",");
		}

		sb.append(elapsedSeconds);
		sb.append(" seconds");

		return sb.toString();
	}
	
	/**
	 * Returns an exceptions's stack trace as string
	 * @param e The exception
	 * @return the String representation of the Exception's stack trace
	 */
	public static String getExceptionMessage(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return e.toString();
	}
	
	/**
	 * Prints the given Hashmap nicely, with proper indentation.
	 * @param entry the HashMap to be printed
	 * @return String the nice output
	 */
	public static String printNice(HashMap<String, Object> entry) {
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = entry.keySet().iterator();
		int len = 0;
		while (it.hasNext()) {
			String key = it.next();
			len = Math.max(len, (int) (Math.ceil(key.length() / 5) * 5)+5);
		}

		Iterator<Entry<String, Object>> it2 = entry.entrySet().iterator();
		sb.append("{");
		while (it2.hasNext()) {
			sb.append("\n");
			Entry<String, Object> e = it2.next();
			String key = e.getKey();
			sb.append("   ");
			sb.append(S.padRight(key, len));
			sb.append(" = ");
			Object val = (e == null ? null : e.getValue());
			sb.append(val != null ? val.toString().replace('\n', ' ') : "<null>");
		}
		sb.append("\n");
		sb.append("}");
		return sb.toString();
	}
}
