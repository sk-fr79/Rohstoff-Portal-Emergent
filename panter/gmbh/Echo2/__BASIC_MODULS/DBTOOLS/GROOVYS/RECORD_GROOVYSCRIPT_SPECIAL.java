package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.GROOVYSCRIPT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_GROOVYSCRIPT;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_GROOVYSCRIPT_SPECIAL extends RECORD_GROOVYSCRIPT
{

	public RECORD_GROOVYSCRIPT_SPECIAL(RECORD_GROOVYSCRIPT recordOrig)
	{
		super(recordOrig);
	}
	
	
	public RECORD_GROOVYSCRIPT_SPECIAL() throws myException
	{
		super();
	}


	public RECORD_GROOVYSCRIPT_SPECIAL(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}


	public RECORD_GROOVYSCRIPT_SPECIAL(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}


	public RECORD_GROOVYSCRIPT_SPECIAL(MyConnection Conn) throws myException
	{
		super(Conn);
	}


	public RECORD_GROOVYSCRIPT_SPECIAL(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}


	public RECORD_GROOVYSCRIPT_SPECIAL(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}



//	/**
//	 * 
//	 * @return s  array[0]=Name of return-Value, array[1]=return-Value
//	 * @throws myException
//	 */
//	public String[] _get_KeyValuePair() throws myException
//	{
//		String[] cRueck = new String[2];
//		
//		cRueck[0] = this.get_NAME_RETURN_VAR_cUF();
//		try
//		{
//			cRueck[1] = bibGroovy.interpretGroovySingleStringReturn(this.get_SCRIPT_cUF(), null, this.get_NAME_RETURN_VAR_cUF());
//		}
//		catch (myGroovyException ex)
//		{
//			ex.printStackTrace();
//			throw ex;
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//			throw new myException(ex.getLocalizedMessage());
//		}
//		
//		return cRueck;
//	}

	
//	/**
//	 * 
//	 * @return s  interpreted value
//	 * @throws myException
//	 */
//	public String get_value(HashMap<String, String> add_on_parameters) throws myException
//	{
//		String cRueck = "";
//		
//		try
//		{
//			cRueck = bibGroovy.interpretGroovySingleStringReturn(this.get_SCRIPT_cUF(), add_on_parameters, this.get_NAME_RETURN_VAR_cUF());
//		}
//		catch (myGroovyException ex)
//		{
//			ex.printStackTrace();
//			throw ex;
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//			throw new myException(ex.getLocalizedMessage());
//		}
//		
//		return cRueck;
//	}
//
	
	
	
	public Vector<String>  get_v_parameter_liste_in_groovyscript() throws myException {
		Vector<String>  v_ret = new Vector<>();
		
		String script = this.ufs(GROOVYSCRIPT.script);
		
		int i_max =0;
		for (int i=1;i<20;i++) {
			if (script.contains(("PARAMETER"+i))) {
				i_max = i;
			}
		}

		//jetzt die liste fuellen
		for (int i=1;i<=i_max;i++) {
			v_ret.add("PARAMETER"+i);
		}
		
 		return v_ret;
	}

	
}
