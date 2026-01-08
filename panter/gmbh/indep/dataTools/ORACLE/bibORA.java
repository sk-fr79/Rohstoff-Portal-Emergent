package panter.gmbh.indep.dataTools.ORACLE;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bibORA {

	/**
	 * erzeugt aus einem java.util.Date einen in sql-statements nutzbaren string via function
	 * @param datumUhrzeit
	 * @return
	 */
	public static String insertStringFunction(Date datumUhrzeit) {
		String sqlblock = "";
		
		SimpleDateFormat df = new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss" );
		
		sqlblock = "TO_DATE('"+df.format(datumUhrzeit)+"','YYYY.MM.DD HH24:MI:SS')";
		
		
		return sqlblock;
	}
	
	
	
}
