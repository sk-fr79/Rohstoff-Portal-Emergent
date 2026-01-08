package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Iterator;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class Interpreter_SQL 
{
	private String 			cSQLText = "";
	private E2_JasperHASH  	hmAustausch = null;
	
	private String          cAntwort = null;
	


	/**
	 * 
	 * @param SQLText  MUSS mit SELECT beginnen
	 * @param hm_Austausch
	 */
	public Interpreter_SQL(String SQLText, E2_JasperHASH hm_Austausch) throws myException
	{
		super();
		this.cSQLText = SQLText.trim();
		this.hmAustausch = hm_Austausch;
		

		if (this.cSQLText.startsWith("<SQL>"))
		{
			this.cSQLText = this.cSQLText.substring("<SQL>".length());
		}
		else
		{
			throw new myException(this,"Error: SQL-Evaluation: NO empty SQL-Script allowed!!!");
		}

		
		
		if (S.isEmpty(this.cSQLText))
		{
			throw new myException(this,"Error: SQL-Evaluation: NO QUERY TEXT ALLOWED");
		}

		
		if (!this.cSQLText.toUpperCase().startsWith("SELECT"))
		{
			throw new myException(this,"Error: SQL-Evaluation must start with <SELECT>");
		}
		

		
		Iterator<String> oIter = this.hmAustausch.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cKEY = oIter.next();
			
			
			if (this.hmAustausch.get(cKEY) instanceof String)
			{
				
//				//debug beginn
//				System.out.println(cKEY);
//				if (cKEY.equals("id_vkopf_rg"))
//				{
//					System.out.println("stop");
//				}
//				//debug ende
				

				
				this.cSQLText = bibALL.ReplaceTeilString(this.cSQLText, "#"+cKEY+"#", (String)this.hmAustausch.get(cKEY));
			}
		}

		
		//nachsehen, ob es noch platzhalter gibt
		int iStart=this.cSQLText.indexOf("#");
		if (iStart>=0)
		{
			int iEnd = this.cSQLText.indexOf("#", iStart+1);
			
			String cParameter = "";
			if (iEnd>=-1)
			{
				cParameter = this.cSQLText.substring(iStart, iEnd);
			}
			
			throw new myException(this,"Error: There is one undefined Parameter: "+cParameter);
		}
		
		
		
		//jetzt noch auf gefahren untersuchen
		this.check_gefahr(this.cSQLText, "DELETE");
		this.check_gefahr(this.cSQLText, "UPDATE");
		this.check_gefahr(this.cSQLText, "INSERT");
		
		
		//wenn bis hier keine Exception, dann die Abfrage
		String[][] cAnswer = bibDB.EinzelAbfrageInArray(this.cSQLText);
		
		if (cAnswer.length==1)
		{
			if (cAnswer[0].length==1)
			{
				cAntwort = cAnswer[0][0];
			}
			else
			{
				throw new myException("Error: Resultset MUST have only one Row with on Column !!");
			}
		}
		else
		{
			throw new myException("Error: Resultset MUST have only one Row with on Column !!");
		}
		
	}
	
	
	private void check_gefahr(String cSQL, String cSuchwort) throws myException
	{
		if (cSQL.toUpperCase().indexOf(cSuchwort.toUpperCase())>=0)
		{
			throw new myException(this,"Error: Unallowed Text in Script: "+cSuchwort);
		}
	}
	
	
	public String get_cAntwort() 
	{
		return cAntwort;
	}

	
	
	
}
