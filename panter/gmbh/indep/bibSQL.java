package panter.gmbh.indep;

public class bibSQL {

	public static String includeInTicks(String s, boolean forceInclude) {
		String s_r = s;
		
		if (forceInclude) {
			s_r = "'"+s+"'";
		} else {
			if (s.startsWith("'") && s.endsWith("'")) {
				s_r = s;
			} else {
				s_r = "'"+s+"'";
			}
		}
		return s_r;
	}
	
	public static String includeInTicks(String s) {
		return includeInTicks(s,false);
	}
	
}
