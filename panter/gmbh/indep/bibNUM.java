package panter.gmbh.indep;

import java.math.BigDecimal;

public class bibNUM {

	public static boolean IS_GREATER_0(BigDecimal bdWert) {
		return bdWert.compareTo(BigDecimal.ZERO)>0;
	}
	
	public static boolean IS_GREATER_EQUAL_0(BigDecimal bdWert) {
		return bdWert.compareTo(BigDecimal.ZERO)>0 || bdWert.compareTo(BigDecimal.ZERO)==0;
	}

	public static boolean IS_LESS_EQUAL_0(BigDecimal bdWert) {
		return bdWert.compareTo(BigDecimal.ZERO)<0 || bdWert.compareTo(BigDecimal.ZERO)==0;
	}

	
	public static boolean IS_EQUAL_0(BigDecimal bdWert) {
		return bdWert.compareTo(BigDecimal.ZERO)==0;
	}
	
	
	public static boolean IS_LESS_0(BigDecimal bdWert) {
		return bdWert.compareTo(BigDecimal.ZERO)<0;
	}
	
	
	/**
	 * checks formated strings, empty strings not allowed
	 * @param cTestWerte
	 * @return
	 */
	public static boolean isAllNumber(String... cTestWerte) 	{
		boolean b_all_number = true;
		
		for (String s: cTestWerte) {
			if (S.isEmpty(s)) {
				b_all_number = false;
			} else {
				MyBigDecimal bd = new MyBigDecimal(s);
				if (bd.isNotOK()) {
					b_all_number = false;
				}
			}
		}
		
		return b_all_number;
	}

	/**
	 * checks formated strings, empty strings not allowed
	 * @param cTestWerte
	 * @return
	 */
	public static boolean isAllLong(String... cTestWerte) 	{
		boolean b_all_number = true;
		
		for (String s: cTestWerte) {
			if (S.isEmpty(s)) {
				b_all_number = false;
			} else {
				MyLong lg = new MyLong(s);
				if (lg.isNotOK()) {
					b_all_number = false;
				}
			}
		}
		
		return b_all_number;
	}

	
	
	
}
