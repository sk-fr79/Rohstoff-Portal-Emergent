package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class RB_KM extends RB_K {

	private _TAB db_table = null; 
	
	//2018-11-20: leerer konstuctor
	public RB_KM() {
		super();
	}
	
	
	public RB_KM(String tableName) throws myException {
		super(tableName);
	}

	
	public RB_KM(_TAB table) throws myException {
		super(table.n());
		this.db_table = table;
	}

	
	public RB_KM(String tableName, String hashkeyName) throws myException {
		super(tableName, hashkeyName);
	}

	public RB_KM(_TAB table, String hashkeyName) throws myException {
		super(table.n(), hashkeyName);
		this.db_table = table;
	}

	
	public String TABLENAME() {
		return this.get_REALNAME();
	}
	public String HASHKEY() {
		return this.get_HASHKEY();
	}


	/**
	 * 
	 * @return falls ein _TAB im konstruktor genutzt wurde, dann wird dieser hier zurueckgegeben
	 */
	public _TAB get_db_table() {
		return db_table;
	}

	
	/**
	 * weiter table evaluierung, falls nur der tablename vorhanden ist
	 */
	public _TAB getTable() {
		try {
			if (db_table!=null) {
				return db_table;
			} else {
				return _TAB.find_Table(this.REALNAME);
			}
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * den hash-wert um einen string erweitern
	 * @param extender
	 * @return
	 */
	public RB_KM _extendKey(String extender) {
		this.set_HASHKEY(this.get_HASHKEY()+":"+extender);
		
		return this;
	}
	
	
}
