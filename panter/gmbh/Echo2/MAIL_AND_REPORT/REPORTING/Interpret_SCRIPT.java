package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class Interpret_SCRIPT 
{
	private Vector<String>  vAllowdStarters = new Vector<String>();
	private String     		cScript = null;	
	private String     		cRueckgabe = "";
	private E2_JasperHASH   oJasperHash = null;
	

	public static Vector<String> vSTARTER_VALIDATION = bibALL.get_Vector("<SQL>", "<GROOVY>", "<JASPERHASH>");
	public static Vector<String> vSTARTER_ARCHNAME = bibALL.get_Vector("<SQL>", "<GROOVY>", "<JASPERHASH>","<ARCHIVNAME>");
	public static Vector<String> vSTARTER_ARCHID = bibALL.get_Vector("<SQL>", "<GROOVY>", "<JASPERHASH>");
	

	public Interpret_SCRIPT(	Vector<String> AllowdStarters, String Script, E2_JasperHASH  JasperHash)  throws myException
	{
		super();
		this.vAllowdStarters = 	AllowdStarters;
		this.cScript = 			Script;
		this.oJasperHash = JasperHash;
		
		String   cStarterFound = "";
		
		for (String cStarter: vAllowdStarters)
		{
			if (this.cScript.startsWith(cStarter))
			{
				cStarterFound = cStarter;
				break;
			}
		}
		
		
		if (S.isEmpty(cStarterFound))
		{
			throw new myException("Script does not start with korrekt Starterlabel !");
		}
		
		if (cStarterFound.startsWith("<SQL>"))
		{
			cRueckgabe = new Interpreter_SQL(cScript,oJasperHash).get_cAntwort();
		}
		else if (cStarterFound.startsWith("<GROOVY>"))
		{
			cRueckgabe = new Interpreter_GROOVY(cScript, oJasperHash).get_cAntwort();
		}
		else if (cStarterFound.startsWith("<JASPERHASH>"))
		{
			
			cRueckgabe = new Interpreter_JASPERHASH(cScript, oJasperHash).get_cAntwort();
			
		}
		else if (cStarterFound.startsWith("<ARCHIVNAME>"))
		{
			if (S.isFull(cScript.substring(12)))
			{
				cRueckgabe = cScript.substring(12);
			}
		}
	}
	
	public E2_JasperHASH get_oJasperHash() 
	{
		return oJasperHash;
	}

	
	
	public String get_cRueckgabe() 
	{
		return cRueckgabe;
	}
	
	
	
	
	
}
