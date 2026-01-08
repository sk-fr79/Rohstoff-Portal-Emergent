package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;




public abstract class MySQL_Statement extends HashMap<String,String> 
{
	private String TABLENAME = 			null;
	private String KEYNAME = 			null;
	private String cIDForStatement = 	null;
	
	

	/**
	 * @return s Vector of Strings to exceute
	 * @throws myException
	 */
	public abstract Vector<String> get_vSQL_StatementStrings() throws myException;


	public String get_TABLENAME() 
	{
		return TABLENAME;
	}


	public String get_cIDsForStatement() 
	{
		return this.cIDForStatement;
	}


	public String get_KEYNAME() {
		return KEYNAME;
	}

	public void set_KEYNAME(String keyname) 
	{
		KEYNAME = keyname;
	}

	public void set_TABLENAME(String tablename) 
	{
		TABLENAME = tablename;
	}

	public void set_cIDForStatement(String idForStatement) 
	{
		this.cIDForStatement = idForStatement;
	}
	
}
