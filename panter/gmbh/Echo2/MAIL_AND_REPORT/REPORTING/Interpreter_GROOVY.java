package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Iterator;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.groovyhandling.GroovyInterpreter;

public class Interpreter_GROOVY 
{
	private String 			cGroovyText = "";
	private E2_JasperHASH  	hmAustausch = null;
	
	private String          cAntwort = null;
	


	/**
	 * 
	 * @param SQLText  MUSS mit SELECT beginnen
	 * @param hm_Austausch
	 */
	public Interpreter_GROOVY(String SQLText, E2_JasperHASH hm_Austausch) throws myException
	{
		super();
		this.cGroovyText = SQLText.trim();
		this.hmAustausch = hm_Austausch;
		

		if (this.cGroovyText.startsWith("<GROOVY>"))
		{
			this.cGroovyText = this.cGroovyText.substring("<GROOVY>".length());
		}
		else
		{
			throw new myException(this,"Error: GROOVY-Evaluation: NO GROOVY-Script !!!");
		}
		
		
		if (S.isEmpty(this.cGroovyText))
		{
			throw new myException(this,"Error: GROOVY-Evaluation: NO Empty-Groovyscript ALLOWED");
		}

		
		
		
		Iterator<String> oIter = this.hmAustausch.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cKEY = oIter.next();
			
			if (this.hmAustausch.get(cKEY) instanceof String)
			{
				this.cGroovyText=bibALL.ReplaceTeilString(this.cGroovyText, "#"+cKEY+"#", (String)this.hmAustausch.get(cKEY));
			}
		}

		
		//nachsehen, ob es noch platzhalter gibt
		int iStart=this.cGroovyText.indexOf("#");
		if (iStart>=0)
		{
			int iEnd = this.cGroovyText.indexOf("#", iStart+1);
			
			String cParameter = "";
			if (iEnd>=-1)
			{
				cParameter = this.cGroovyText.substring(iStart, iEnd);
			}
			
			throw new myException(this,"Error: There is one undefined Parameter: "+cParameter);
		}
		
		
		GroovyInterpreter  groovy = new GroovyInterpreter(this.hmAustausch);
		
		this.cAntwort = groovy.get_cAnswer(this.cGroovyText, "RETURNVALUE");
	}
	
	
	public String get_cAntwort() 
	{
		return cAntwort;
	}

	
	
	
}
