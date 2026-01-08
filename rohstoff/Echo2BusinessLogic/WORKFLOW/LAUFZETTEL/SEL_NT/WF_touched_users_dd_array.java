package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class WF_touched_users_dd_array {

	
	private String[][] users_that_are_touching = null;
	private String[][] arrayPrefix =  {{"-",""}};

	public WF_touched_users_dd_array(boolean nurAktive) throws myException {
		super();
		
		String sqlNurAktive = nurAktive ? " AND NVL(JD_USER.AKTIV,'N') = 'Y' " : "";
		
		
		//query ueber alle benutzer, die in den Laufzetteln vorkommen
		String c_sql = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')',ID_USER FROM "+bibE2.cTO()+".JD_USER "	+ 
					" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT() +
					sqlNurAktive ;
		
		c_sql = c_sql+" AND (";
		for (LAUFZETTEL_USER_ROLE role: LAUFZETTEL_USER_ROLE.values()) {
			c_sql = c_sql+" JD_USER.ID_USER IN ("+role.get_query_select_users_in_field().s()+")"+" OR ";
		}
		c_sql = c_sql.substring(0,c_sql.length()-4)+")";       //das letzte or weg 
		c_sql += " ORDER BY 1";
		// ---------------------------------------------------------
		
		
		String[][] array_users = bibDB.EinzelAbfrageInArray(c_sql) ;
		
		if (array_users == null || array_users.length==0) {
			this.users_that_are_touching = this.arrayPrefix;
		} else {
			this.users_that_are_touching=bibARR.add_array_inFront(array_users, this.arrayPrefix);
		}
	}

	
	public String[][] get_users_that_are_touching() {
	
		return users_that_are_touching;
	}

	
	
	
	
}
