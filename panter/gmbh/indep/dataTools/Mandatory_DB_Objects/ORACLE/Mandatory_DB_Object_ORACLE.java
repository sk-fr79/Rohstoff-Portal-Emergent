package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.Mandatory_DB_Object_Base;
import panter.gmbh.indep.exceptions.myException;

public abstract class Mandatory_DB_Object_ORACLE extends Mandatory_DB_Object_Base {


	public Mandatory_DB_Object_ORACLE(String ObjectName) {
		super(ObjectName);
	}


	/**
	 * Methode prüft ob ein Trigger in der DB vorhanden ist.
	 * @author manfred
	 * @date   21.01.2013
	 * @param TriggerName
	 * @return
	 */
	protected boolean ObjectExists_Trigger(){
		boolean bRet = false;
		String sSql = " SELECT trigger_name from  sys.user_triggers " +
				      " WHERE UPPER(trigger_name) = '" + getObjectName().toUpperCase() + "' " +
				      " AND UPPER(TABLE_OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}
	
		return bRet;
	}

	
	/**
	 * zuerst prüfen, ob der Trigger valide ist, wenn nicht versuchen, zu kompilieren und dann nochmal prüfen
	 * @author manfred
	 * @date   18.09.2013
	 * @return
	 * @throws myException
	 */
	protected boolean ObjectIsValid_Trigger() throws myException {
		
		String cSQLCheckTrigger = "SELECT COUNT(*) " +
				" FROM SYS.DBA_OBJECTS " +
				" WHERE STATUS = 'VALID'" +
				" AND OBJECT_TYPE='TRIGGER'" +
				" AND UPPER(OWNER)="+bibALL.MakeSql(bibE2.cTO().toUpperCase()) +
				" AND UPPER(OBJECT_NAME)="+bibALL.MakeSql(this.getObjectName().toUpperCase());
		
		String cSqlCompileTrigger = "ALTER TRIGGER " + bibE2.cTO().toUpperCase() + "." +this.getObjectName().toUpperCase() + " COMPILE";
		
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQLCheckTrigger, false);

		if (cErgebnis !=null && cErgebnis.length==1 && cErgebnis[0][0].trim().equals("1")) {
			return true;
		} else {
			
			// trigger kompilieren und nochmal schauen
			bibDB.ExecSQL(cSqlCompileTrigger, true); 

			cErgebnis = bibDB.EinzelAbfrageInArray(cSQLCheckTrigger, false);
			if (cErgebnis !=null && cErgebnis.length==1 && cErgebnis[0][0].trim().equals("1")) {
				return true;
			} else {
				throw new myException(this,"Error Trigger not valid / can not be compiled: "+this.getObjectName(),this.getObjectName());
			}
		}
	}
	
	
	
	
	/**
	 * Methode prüft ob eine View in der DB vorhanden ist.
	 * @author manfred
	 * @date   21.01.2013
	 * @param TriggerName
	 * @return
	 */
	protected boolean ObjectExists_View(){
		boolean bRet = false;
		String sSql = " SELECT view_name from  SYS.ALL_VIEWS " +
				      " WHERE UPPER(view_name) = '" + getObjectName().toUpperCase() + "' " +
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
		
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}
	
		return bRet;
	}

	
	/**
	 * Methode prüft, ob die vorgegebenen Daten vorhanden sind
	 * @author manfred
	 * @date   15.05.2013
	 * @return
	 */
	protected boolean ObjectExists_Data(){
		return false;
	}
	
	
	
	
	
	/**
	 * Methode prueft, ob ein package in der datenbank vorhanden ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_PackageExists() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Package_name().toUpperCase() + "' " +
				      " AND OBJECT_TYPE = 'PACKAGE' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}
	
	
	
	/**
	 * Methode prueft, ob ein package in der datenbank valid ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_PackageValid() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Package_name().toUpperCase() + "' " +
				      " AND STATUS = 'VALID' " +
				      " AND OBJECT_TYPE = 'PACKAGE' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}

	
	/**
	 * Methode prueft, ob ein package in der datenbank vorhanden ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_PackageBodyExists() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Package_name().toUpperCase() + "' " +
				      " AND OBJECT_TYPE = 'PACKAGE BODY' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}
	
	
	
	/**
	 * Methode prueft, ob ein package in der datenbank valid ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_PackageBodyValid() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Package_name().toUpperCase() + "' " +
				      " AND STATUS = 'VALID' " +
				      " AND OBJECT_TYPE = 'PACKAGE BODY' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}

	
	
	/**
	 * Methode prueft, ob ein context in der datenbank vorhanden ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_ContextExists() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Context_name().toUpperCase() + "' " +
				      " AND OBJECT_TYPE = 'CONTEXT' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}
	
	
	
	/**
	 * Methode prueft, ob ein context in der datenbank valid ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_ContextValid() {
		boolean bRet = false;
		
		Mandatory_DB_Object_OracleSysContextHandlerPackage oThis = (Mandatory_DB_Object_OracleSysContextHandlerPackage)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.get_Context_name().toUpperCase() + "' " +
				      " AND STATUS = 'VALID' " +
				      " AND OBJECT_TYPE = 'CONTEXT' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}

	
	
	
	/**
	 * Methode prueft, ob eine function in der datenbank vorhanden ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_FunctionExists() {
		boolean bRet = false;
		
		Mandatory_DB_Object_ORACLE oThis = (Mandatory_DB_Object_ORACLE)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.getObjectName().toUpperCase() + "' " +
				      " AND OBJECT_TYPE = 'FUNCTION' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}
	
	
	
	/**
	 * Methode prueft, ob eine function in der datenbank valid ist
	 * @author martin
	 * @date 20161219 
	 * @return
	 */
	protected boolean Object_FunctionValid() {
		boolean bRet = false;
		
		Mandatory_DB_Object_ORACLE oThis = (Mandatory_DB_Object_ORACLE)this;
		
		String sSql = " SELECT OBJECT_NAME from  SYS.DBA_OBJECTS " +
				      " WHERE UPPER(OBJECT_NAME) = '" + oThis.getObjectName().toUpperCase() + "' " +
				      " AND STATUS = 'VALID' " +
				      " AND OBJECT_TYPE = 'FUNCTION' "+
				      " AND UPPER(OWNER) = '"  + bibE2.cTO().toUpperCase() +  "'" ; 
	
		String[][] sValues = bibDB.EinzelAbfrageInArray(sSql);
	
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (sValues.length == 1 ){
			bRet = true ;
		}

		return bRet;
	}


	
	

}
