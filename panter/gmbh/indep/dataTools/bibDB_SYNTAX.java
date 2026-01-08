package panter.gmbh.indep.dataTools;

import panter.gmbh.basics4project.DEBUG;

public class bibDB_SYNTAX
{

	/**
	 * 
	 * @param tablename
	 * @param fieldname
	 * @param vergleichswert
	 * @return string mit einem Inhalt wie z.B. NVL(JT_ADRESSE.AKTIV,'N')='N'
	 */
	public static String GENERATE_YES_NO_WHERE(String tablename, String fieldname, String vergleichswert) {
		String cRueck = "NVL("+tablename+"."+fieldname+",'N')=";
		if (vergleichswert.startsWith("'") && vergleichswert.endsWith("'")) {
			cRueck += vergleichswert;
		} else  {
			cRueck += ("'"+vergleichswert+"'");
		}
		return cRueck;
	}
	
	
	/**
	 * 
	 * @param tablename
	 * @param fieldname
	 * @param vergleichswert
	 * @return string mit einem Inhalt wie z.B. NVL(JT_ADRESSE.AKTIV,'N')='N'
	 */
	public static String GENERATE_YES_NO_WHERE_IN_BRACKETS(String tablename, String fieldname, String vergleichswert) {
		String cRueck = "NVL("+tablename+"."+fieldname+",'N')=";
		if (vergleichswert.startsWith("'") && vergleichswert.endsWith("'")) {
			cRueck += vergleichswert;
		} else  {
			cRueck += ("'"+vergleichswert+"'");
		}
		return "("+cRueck+")";
	}
	
	
	
	/**
	 * 
	 * @param tablename
	 * @param fieldname
	 * @param vergleichswert
	 * @return string mit einem Inhalt wie z.B. JT_ADRESSE.NAME1='MUELLER'
	 */
	public static String GENERATE_SIMPLE_WHERE(String tablename, String fieldname, String vergleichswert, boolean bVergleichsWertIstString) {
		String cRueck = tablename+"."+fieldname+"=";
		if (bVergleichsWertIstString) {
			if (vergleichswert.startsWith("'") && vergleichswert.endsWith("'")) {
				cRueck += vergleichswert;
			} else  {
				cRueck += ("'"+vergleichswert+"'");
			}
		} else {
			cRueck += vergleichswert;
		}
		
		DEBUG.System_print(cRueck, null);
		return cRueck;
	}
	

	
	/**
	 * 
	 * @param cField
	 * @return "NVL("+cField+",'N')='N'";
	 */
	public static String NOT_DEL(String cField) {
		return "NVL("+cField+",'N')='N'";
	}
	
	/**
	 * 
	 * @param cField
	 * @return "NVL("+cField+",'N')='Y'";
	 */
	public static String IS_TRUE(String cField) {
		return "NVL("+cField+",'N')='Y'";
	}

	/**
	 * 
	 * @param cField
	 * @return "NVL("+cField+",'N')='N'";
	 */
	public static String IS_FALSE(String cField) {
		return "NVL("+cField+",'N')='N'";
	}
	
	
	/**
	 * formatierhilfe fuer oracle-querys
	 * @param fieldName
	 * @param formatString ist die formatdefinition in deutscher notation (tausenderpunkt, dezimalkomma)
	 *         beipiele: fm9g990d000 = tausender punkt, 3 nachkommastellen 
	 * @return
	 */
	public static String ORA_FORMATSTRING(String fieldName, String formatString) {
		String c_rueck = "to_char("+fieldName+",'"+formatString+"','NLS_NUMERIC_CHARACTERS = '',.''')";
		
		return c_rueck;
	}
	
	
}
