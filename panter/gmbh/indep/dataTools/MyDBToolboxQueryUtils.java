package panter.gmbh.indep.dataTools;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.query.AID;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.dataTools.query.Query;
import panter.gmbh.indep.dataTools.query.SQLPart;
import panter.gmbh.indep.dataTools.query.U;
import panter.gmbh.indep.dataTools.query.UPDATE;

/**
 * Toolbox for handling {@link Query} classes with the current DBToolbox.
 * @author nils
 *
 */
public class MyDBToolboxQueryUtils {
	static String[][] zusatzfelder = null;
	static Hashtable<String, Pattern> ersetzungsmuster = null;
	static {
		//2015-05-07: geaendert martin
		//zusatzfelder = (String[][]) bibALL.getSessionValue("ZUSATZFELDER");
		zusatzfelder = DB_STATICS.get_DB_ZusatzFelder_STD();
//		ersetzungsmuster = (Hashtable<String, Pattern>) bibALL.getSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN");
//    	if (ersetzungsmuster == null){
    		ersetzungsmuster = new Hashtable<String, Pattern>();
    		String[][] ersetzungstabelle = (String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT");
    		// Pattern aufbauen
            for (int i = 0; i < ersetzungstabelle.length; i++) {
            	Pattern pattern = Pattern.compile(ersetzungstabelle[i][0],Pattern.CASE_INSENSITIVE | Pattern.LITERAL );
            	ersetzungsmuster.put(ersetzungstabelle[i][1], pattern);
            }
//            bibALL.setSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN", ersetzungsmuster);
//    	}
	}
	
	/**
	 * Adds the "Zusatzfelder" to the Query for INSERT and UPDATE types.
	 * Note that these are added as unquoted values, to support SYSDATE or
	 * the like.
	 * @param q the Query
	 */
	public static void addZusatzfelder(Query q) {
		if (q instanceof INSERT) {
			INSERT qi = (INSERT)q;
    		for (int i = 0; i < zusatzfelder.length; i++) {
    			qi.set(zusatzfelder[i][0], new U(zusatzfelder[i][1]));
    		}
    		q = qi;
        }
		
        if (q instanceof UPDATE) {
        	UPDATE qu = (UPDATE)q;
    		for (int i = 0; i < zusatzfelder.length; i++) {
    			qu.set(zusatzfelder[i][0], new U(zusatzfelder[i][1]));
    		}
    		q = qu;
        }
	}
	
	
	public static void ersetzeTabellen(Query q) {
/*		replaceSQLPartList(q.getTables());
		replaceSQLPartList(q.getFields());
		replaceSQLPartList(q.getOrderBy());
//		replaceSQLPartList(q.getWhereClause());
		replaceSQLPartList(q.getGroupBy());
		replaceSQLPartList(q.getJoins());
		
		*/
		System.out.println(q.toString());
	}
	
	private static void replaceSQLPartList(List<? extends SQLPart> e) {
		if (e == null) return;
		for (SQLPart p: e) {
		}
	}


	public static String replaceTable(String tableName) {
        Iterator<Entry<String, Pattern>> it = ersetzungsmuster.entrySet().iterator();
        while (it.hasNext()) {
        	Entry<String, Pattern> e = it.next();
    		Pattern pattern = e.getValue();
    		Matcher matcher = pattern.matcher(tableName);
    		if (matcher.find()){
    			tableName = matcher.replaceAll(Matcher.quoteReplacement(e.getKey()));
    		}
        }
        return tableName;
	}


	public static Query createCopy(Query q) {
		//TODO: Hier korrekte Copy
		return q;
		// TODO Auto-generated method stub
		//return null;
	}
	

}
