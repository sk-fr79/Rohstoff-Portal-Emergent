package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class Mandatory_DB_Object_Function_by_file extends Mandatory_DB_Object_ORACLE {

	String cStatement4Function = null;
	
	public Mandatory_DB_Object_Function_by_file(String FunctionName, String fileNameWithDefinition) throws myException {
		super(FunctionName);
		this.cStatement4Function = new TextFileLoader(getClass(), fileNameWithDefinition).get_loadedText();
	}

	
	@Override
	public boolean CreateObject() {
		boolean bRet = false;
		
		// WICHTIG!!
		String sSql = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS + this.cStatement4Function;
		
		// Trigger erzeugen
		bRet = bibDB.ExecSQL(sSql, true);
		
		return bRet;
	}

	
	
	@Override
	public boolean ObjectExists() {
		return super.Object_FunctionExists();
	}

	@Override
	public boolean ObjectIsValid() throws myException  {
		return super.Object_FunctionValid();
	}


}
