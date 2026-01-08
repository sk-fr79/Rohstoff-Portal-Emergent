package panter.gmbh.indep;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.codehaus.groovy.control.CompilationFailedException;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS.RECORD_GROOVYSCRIPT_SPECIAL;
import panter.gmbh.basics4project.DB_ENUMS.GROOVYSCRIPT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myGroovyException;
import panter.gmbh.indep.exceptions.myGroovyException.ENUM_GROOVY_ERRORS;

public class bibGroovy   {
	
	public static String GROOVY_SETTING_DBCONNECTION = 			"GROOVY_SETTING_DBCONNECTION";
	public static String GROOVY_SETTING_ID_USER = 				"GROOVY_SETTING_ID_USER";
	public static String GROOVY_SETTING_ID_MANDANT = 			"GROOVY_SETTING_ID_MANDANT";
	public static String GROOVY_SETTING_ID_ADRESSE_MANDANT = 	"GROOVY_SETTING_ID_ADRESSE_MANDANT";
	
	
	
	
	
	public static String interpretGroovySingleStringReturn(String cGroovyScript, HashMap<String, String> hm_add_on_paramaters, String cNameReturnVariable) throws myException
	{
		String cRueck = null;
		
		try
		{
			Binding binding = new Binding();

			
			//hier die variable, die dem script uebergeben werden...
			binding.setVariable(bibGroovy.GROOVY_SETTING_DBCONNECTION, 			bibALL.get_oConnectionNormal().get_oConnection());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_USER, 				bibALL.get_RECORD_USER().get_ID_USER_cUF());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_MANDANT, 			bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_ADRESSE_MANDANT, 	bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0"));
			
			if (hm_add_on_paramaters!=null) {
				for (String var: hm_add_on_paramaters.keySet()) {
					binding.setVariable(var, S.NN(hm_add_on_paramaters.get(var)));
				}
			}
			
			//hier die verbotenen worte im script suchen:
			String cTestScriptUPPER = cGroovyScript.toUpperCase();
			String cFehlerWort = bibALL.Check_forbidden_words(cTestScriptUPPER);
			if (S.isFull(cFehlerWort))
			{
				throw new myGroovyException(myGroovyException.ENUM_GROOVY_ERRORS.ERROR_OWN_FORBIDDEN_WORD,"You are using a Selektor with a Groovy-Script! This MUST NOT contain one of the word: "+cFehlerWort);
			}
			
			
			GroovyShell shell = new GroovyShell(binding);
			
			shell.evaluate(cGroovyScript);
			
			@SuppressWarnings("unchecked")
			Map<String, Object>  groovyMap = binding.getVariables();
				
			if (groovyMap.containsKey(cNameReturnVariable))
			{
				cRueck = (String)groovyMap.get(cNameReturnVariable);
//				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Das aktuelle Jahr ist: "+cRueck));
			}
			else
			{
				throw new myGroovyException(myGroovyException.ENUM_GROOVY_ERRORS.ERROR_OWN_NO_RETURN_VARIABLE_IN_RETURN_BINDING,"\n\nError no return-Value .. "+cGroovyScript);
			}
		}
		catch (CompilationFailedException e)
		{
			e.printStackTrace();
			throw new myGroovyException(ENUM_GROOVY_ERRORS.ERROR_INTERPRETER,e.getClass().getName()+"\n\nError interpreting script .."+cGroovyScript+"\n\n"+e.getLocalizedMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new myGroovyException(ENUM_GROOVY_ERRORS.ERROR_BASIC,e.getClass().getName()+"\n\nError basic .."+cGroovyScript+"\n\n"+e.getLocalizedMessage());
		}

		
		return cRueck;
	}
	
	
	
	/**
	 * 2014-05-15
	 * @param cGroovyScript
	 * @param hmAnswerKeyAndValueMAP (antwortmap mit gefuellten hashes und leere werten, diese werden aus dem groovy-binding gefuellt
	 * @return
	 * @throws myException
	 */
	public static void interpretGroovySingleStringReturn(String cGroovyScript, HashMap<String, String> hmAnswerKeyAndValueMAP) throws myException
	{
		
		try
		{
			Binding binding = new Binding();

			
			//hier die variable, die dem script uebergeben werden...
			binding.setVariable(bibGroovy.GROOVY_SETTING_DBCONNECTION, 			bibALL.get_oConnectionNormal().get_oConnection());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_USER, 				bibALL.get_RECORD_USER().get_ID_USER_cUF());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_MANDANT, 			bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
			binding.setVariable(bibGroovy.GROOVY_SETTING_ID_ADRESSE_MANDANT, 	bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0"));
			
			
			
			//hier die verbotenen worte im script suchen:
			String cTestScriptUPPER = cGroovyScript.toUpperCase();
			String cFehlerWort = bibALL.Check_forbidden_words(cTestScriptUPPER);
			if (S.isFull(cFehlerWort))
			{
				throw new myGroovyException(myGroovyException.ENUM_GROOVY_ERRORS.ERROR_OWN_FORBIDDEN_WORD,"You are using a Groovy-Script! This MUST NOT contain one of the word: "+cFehlerWort);
			}
			
			
			GroovyShell shell = new GroovyShell(binding);
			
			shell.evaluate(cGroovyScript);
			
			@SuppressWarnings("unchecked")
			Map<String, Object>  groovyMap = binding.getVariables();
				
			for (String cKEY:hmAnswerKeyAndValueMAP.keySet()) {
				
				if (groovyMap.containsKey(cKEY))
				{
					if ((groovyMap.get(cKEY) instanceof String))
					{
						hmAnswerKeyAndValueMAP.put(cKEY,(String)groovyMap.get(cKEY));
					}
				}
			}

		}
		catch (CompilationFailedException e)
		{
			e.printStackTrace();
			throw new myGroovyException(ENUM_GROOVY_ERRORS.ERROR_INTERPRETER,"Error interpreting script .."+cGroovyScript+"/n/n"+e.getLocalizedMessage());
		}

	}
	

	
	
	
	
	
	/**
	 * 
	 * @return auflistung der dem Groovy-interpreter uebergebenen Strings
	 * @throws myException
	 */
	public static HashMap<String, MyE2_String> get_HashMap_ParameterUebergaben() throws myException
	{
		HashMap<String, MyE2_String> hmRueck = new HashMap<String, MyE2_String>();
		
		hmRueck.put(bibGroovy.GROOVY_SETTING_DBCONNECTION, 			new MyE2_String("Datenbank-Connection die im System vom Benutzer verwendet wird ..."));
		hmRueck.put(bibGroovy.GROOVY_SETTING_ID_USER, 				new MyE2_String("Benutzer-ID  ... ",true,bibALL.get_RECORD_USER().get_ID_USER_cUF(),false));
		hmRueck.put(bibGroovy.GROOVY_SETTING_ID_MANDANT, 			new MyE2_String("ID_Mandant ...",true,bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF(),false));
		hmRueck.put(bibGroovy.GROOVY_SETTING_ID_ADRESSE_MANDANT, 	new MyE2_String("ID der Stammadresse des Mandanten ...",true,bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0"),false));

		
		return hmRueck;
	}

	/**
	 * Sucht bei einem Groovy-Tag wie z.B. #GROOVY:TAG1IMJAHR# zuerst nach #GROOVY:TAG1IMJAHR  und sucht holt dann den Textblock bis zum folgenden # separat heraus
	 * Der gefundene Teilstring wird danach mittels Trenner : sepa
	 * @param cStringWithVariables (string, der den parameter enthaelt)
	 * @param groovyTag
	 * @return string mit ersetztem parameter
	 * @throws myException 
	 */
	public static String replace_groovy_placeholders_with_groovy_results(String cStringWithVariables, RECORD_GROOVYSCRIPT_SPECIAL rec_groovy) throws myException {
		return bibGroovy.replace_groovy_placeholders_with_groovy_results(cStringWithVariables, rec_groovy.ufs(GROOVYSCRIPT.name_return_var), rec_groovy.ufs(GROOVYSCRIPT.script));
	}
	
	
	public static String replace_groovy_placeholders_with_groovy_results(String cStringWithVariables, String ret_variable, String groovyProg) throws myException {
		String string2test = cStringWithVariables;
		
		do  {
			
			int startPos = string2test.indexOf("#GROOVY:"+ret_variable);
			if (startPos==-1) {
				break;
			} else {
				String vorne = string2test.substring(0,startPos);
				String rest = string2test.substring(startPos+("#GROOVY:"+ret_variable).length());
//				String ergebnis="";
				
				//rest wird jetzt durchsucht, bis ein # kommt
				String ausdruck = "";
				String hinten = "";
				for (int i=0;i<rest.length();i++) {
					if (rest.substring(i,i+1).equals("#")) {
						hinten = rest.substring(i+1);
						break;
					} else {
						ausdruck = ausdruck+rest.substring(i,i+1);
					}
				}
				
				HashMap<String, String> hm_paramaters4groovy = new HashMap<>();
				int zaehler = 1;
				if (ausdruck.length()>0) {
					//jetzt wird der ausdruck teilstrings separiert (trenner ist ":")
					Vector<String> v_param = bibALL.TrenneZeile(ausdruck, ":");
					
					for (String c_param: v_param) {
						hm_paramaters4groovy.put("PARAMETER"+zaehler, c_param);
						zaehler++;
					}
				}
				String ergebnis = S.NN(bibGroovy.interpretGroovySingleStringReturn(groovyProg, hm_paramaters4groovy, ret_variable));

				//jetzt interpretieren
				string2test = vorne+ergebnis+hinten;
			}
			
		} while (true);
		
		return string2test;
	}

	
}
