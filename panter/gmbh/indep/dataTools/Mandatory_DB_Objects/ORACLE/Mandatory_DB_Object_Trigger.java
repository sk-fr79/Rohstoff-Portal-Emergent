package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class Mandatory_DB_Object_Trigger extends Mandatory_DB_Object_ORACLE {

	String cStatement4Trigger = null;
	
	public Mandatory_DB_Object_Trigger(String ObjectName, String Statement4Trigger) {
		super(ObjectName);
		this.cStatement4Trigger = Statement4Trigger;
	}

	
	@Override
	public boolean CreateObject() {
		boolean bRet = false;
		
		// WICHTIG!!
		String sSql = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + this.cStatement4Trigger;
		
		// Trigger erzeugen
		bRet = bibDB.ExecSQL(sSql, true);
		
		return bRet;
	}

	
	
	@Override
	public boolean ObjectExists() {
		return super.ObjectExists_Trigger();
	}

	@Override
	public boolean ObjectIsValid() throws myException  {
		return super.ObjectIsValid_Trigger();
	}


}
