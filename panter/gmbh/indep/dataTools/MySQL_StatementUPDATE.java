package panter.gmbh.indep.dataTools;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;


public class MySQL_StatementUPDATE extends MySQL_Statement 
{

	
	/**
	 * @param tablename
	 * @param keyname
	 * @param idsForStatement
	 */
	public MySQL_StatementUPDATE(String tablename, String keyname, String cIDForStatement) 
	{
		super();
		this.set_TABLENAME(tablename);
		this.set_KEYNAME(keyname);
		this.set_cIDForStatement(cIDForStatement);
	}

	

	/**
	 * @param tablename
	 * @param keyname
	 * @param idsForStatement
	 * @param cUpdates
	 */
	public MySQL_StatementUPDATE(String tablename, String keyname, String cIDForStatement, String[][] cUpdates) 
	{
		super();
		this.set_TABLENAME(tablename);
		this.set_KEYNAME(keyname);
		this.set_cIDForStatement(cIDForStatement);
		
		for (int i=0;i<cUpdates.length;i++)
		{
			this.put(cUpdates[i][0],cUpdates[i][1]);
		}
	}


	

	public Vector<String> get_vSQL_StatementStrings() 
	{
		Vector<String> vSQL = new Vector<String>();
		
		String cHelp = "UPDATE "+bibE2.cTO()+"."+this.get_TABLENAME()+" SET ";
		
		String cUpdateBlock = "";
		
		Iterator<Map.Entry<String, String>> it = this.entrySet().iterator(); 

		while (it.hasNext()) 
		{
		    Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
		    
		    if (entry.getKey() != null)
		    {
		    	cUpdateBlock += ","+(String)entry.getKey()+"=";
		    	
		    	if (entry.getValue() == null)
		    		cUpdateBlock += "NULL";
		    	else
		    		cUpdateBlock += (String)entry.getValue();
		    }
		} 			

		
		if (! bibALL.isEmpty(cUpdateBlock))
		{
			cUpdateBlock = " "+cUpdateBlock.substring(1)+ " ";
			vSQL.add(cHelp+" "+cUpdateBlock+" WHERE "+this.get_KEYNAME()+"="+(String)this.get_cIDsForStatement());
		}
		
		return vSQL;
	}

	
	
	
	public String toString()
	{
		String cRueck = "";
		Vector<String> vHelp = this.get_vSQL_StatementStrings();
		
		
		for (int i=0;i<vHelp.size();i++)
		{
			cRueck += (String)vHelp.get(i)+"\n";
		}
		
		cRueck += " /// \n TRIGGER !!!! TRIGGER !!!";
		
		return cRueck;
		
	}
	
	
	
}


