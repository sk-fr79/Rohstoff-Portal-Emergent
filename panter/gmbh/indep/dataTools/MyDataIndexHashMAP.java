package panter.gmbh.indep.dataTools;

import java.util.HashMap;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MyDataIndexHashMAP extends HashMap<String,String[]> 
{
	private String cQueryString = null;

	/**
	 * @param SQLQuery (Spalte 1: Index-Wert)
	 * @throws myException
	 * Speichert ueber den wert der ersten ergebnisspalte die komplette ergebnisspalte in einer HashMap
	 */
	public MyDataIndexHashMAP(String SQLQuery, boolean bFormated)  throws myException
	{
		super();
		this.cQueryString = SQLQuery;
		
		String[][] cWerte = null;
		if (bFormated)
			cWerte = bibDB.EinzelAbfrageInArrayFormatiert(this.cQueryString,"");
		else
			cWerte = bibDB.EinzelAbfrageInArray(this.cQueryString,"");
		
		if (cWerte == null)
			throw new myException(this.getClass().getName()+": Error in SQL Query: "+cQueryString);
		
		for (int i=0;i<cWerte.length ;i++)
		{
			if (bibALL.isEmpty(cWerte[i][0]))
				throw new myException(this.getClass().getName()+": HashMap has empty Key-Value: "+cQueryString);
			
			if (this.containsKey(cWerte[i][0]))
				throw new myException(this.getClass().getName()+": HashMap has duplicate Key-Value: "+cQueryString);
			
			this.put(cWerte[i][0], cWerte[i]);
		}
	}
	
	
	public String get_Result(String cHashValue, int iNumberInRow) throws myException
	{
		
		Object oRueck = this.get(cHashValue);
		if (oRueck == null)
			throw new myException(this.getClass().getName()+":get_Result: Key is not there !! ");
		
		String[] cHelp = (String[]) oRueck;
		
		if (iNumberInRow>=cHelp.length)
			throw new myException(this.getClass().getName()+":get_Result: Not enought fields !! ");		
		
		return cHelp[iNumberInRow];
	}
	
	  
	public String get_Result_without_Exception(String cHashValue, int iNumberInRow)
	{
		String cRueck = "";
		
		Object oRueck = this.get(cHashValue);
		if (oRueck == null)
			return cRueck;
		
		String[] cHelp = (String[]) oRueck;
		
		if (iNumberInRow>=cHelp.length)
			return cRueck;
		
		return cHelp[iNumberInRow];
	}
	
	  
	  
	  
	
}
