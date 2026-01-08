package panter.gmbh.indep.groovyhandling;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.indep.exceptions.myException;

public class GroovyInterpreter 
{
	HashMap<String, String>  hmInput = null;

	
	public GroovyInterpreter(HashMap<String, String> hmInput) 
	{
		super();
		this.hmInput = hmInput;
	}
	
	
	public GroovyInterpreter(E2_JasperHASH oJasperHash) 
	{
		super();
		this.hmInput = new HashMap<String, String>();
		
		Iterator<String>  oIter = oJasperHash.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cKey = oIter.next();
			
			if (oJasperHash.get(cKey) instanceof String)
			{
				this.hmInput.put(cKey, (String)oJasperHash.get(cKey));
			}
		}
	}
	

	
	public HashMap<String, Object> get_Answer(String cGroovyCode) throws myException
	{
		HashMap<String, Object> mapRueck = new HashMap<String, Object>();
		
		try 
		{
			Binding 			binding = 	new Binding();
			Iterator<String>  	oIter = 	this.hmInput.keySet().iterator();
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				binding.setVariable(cKey,this.hmInput.get(cKey));
			}
			
			GroovyShell 		shell = 	new GroovyShell(binding);

			shell.evaluate(cGroovyCode);
				
			Map<String, Object>  groovyMap = binding.getVariables();
				
			Iterator<String>  oIter2 = groovyMap.keySet().iterator();
			
			while (oIter2.hasNext())
			{
				String cKey = oIter2.next();
				mapRueck.put(cKey,groovyMap.get(cKey));
//				System.out.println("key in groovy-map: "+cKey);
			}
		} 
		catch (CompilationFailedException e) 
		{
			e.printStackTrace();
			throw new myException(this,e.getLocalizedMessage());
		} catch(Exception ex){
			ex.printStackTrace();
			throw new myException(this,ex.getLocalizedMessage());
		}
		
		return mapRueck;
	}
	
	
	
	public String get_cAnswer(String cGroovyCode, String cSpecialAnswerKey) throws myException
	{
		HashMap<String, Object>  hmRueck = this.get_Answer(cGroovyCode);
		
		
		if (hmRueck.containsKey(cSpecialAnswerKey))
		{
			if ((hmRueck.get(cSpecialAnswerKey) instanceof String))
			{
				return (String) hmRueck.get(cSpecialAnswerKey);
			}
			else
			{
				throw new myException(this,"Groovy-Binding: Key "+cSpecialAnswerKey +" is not a String !!");
			}
		}
		else
		{
			throw new myException(this,"Groovy-Binding has no key :"+cSpecialAnswerKey);
		}
		
	}
	
	
	
	
//	/**
//	 * 
//	 * @param cGroovyCode
//	 * @param hmAnswerKeys beinhaltet die hashkeys, die aus dem Groovy-Statement ausgelesen werden sollen
//	 * @return
//	 * @throws myException
//	 */
//	public void fill_KeyMap(String cGroovyCode, HashMap<String, String> hmAnswerKeys) throws myException
//	{
//		HashMap<String, Object>  hmRueck = this.get_Answer(cGroovyCode);
//		
//		for (String cKEY:hmAnswerKeys.keySet()) {
//		
//			if (hmRueck.containsKey(cKEY))
//			{
//				if ((hmRueck.get(cKEY) instanceof String))
//				{
//					hmAnswerKeys.put(cKEY,(String)hmRueck.get(cKEY));
//				}
//			}
//		}
//	}
//	

	
	
	
}
