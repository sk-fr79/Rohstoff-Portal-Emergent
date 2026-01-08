package panter.gmbh.indep.Replacer;


import java.util.AbstractMap;
import java.util.LinkedHashMap;

import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS.RECORD_GROOVYSCRIPT_SPECIAL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_GROOVYSCRIPT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_GROOVYSCRIPT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myGroovyException;


/*
 * hier werden platzhalter ersetzungen gemacht, die in SQL-Definitionen
 * wie z.B. in den XML-Defs benutzt werden koennen, um z.B. alle user eines
 * Mandanten nur anzuzeigen oder in DropDowns von Listen fuer die auswahl
 * bestimmten werten, die in selektoren benutzt werden koennen
 */


/*
 * benutzte platzhalter
 * 	SYS_USERNAME,				bibALL.get_USERNAME());
	SYS_KUERZEL,					bibALL.get_KUERZEL());
	SYS_USERID,					bibALL.get_ID_USER());
	SYS_MANDANT_ID,				bibALL.get_ID_MANDANT());
	ID_ADRESSE_MANDANT,		bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
	SYS_REPORTDATE,				bibALL.get_cDateNOW());
 
 */
public class bibReplacer
{

	public static String ReplaceSysvariablesInStrings(String cStringWithVariables) throws myException {
		return bibReplacer.ReplaceSysvariablesInStrings(cStringWithVariables, null);
	}
	

	
	
	/**
	 * liste fuer Bedienerhilfe, alle ersetzbaren strings in den Abfragen
	 * @return
	 * @throws myException 
	 */
	public static LinkedHashMap<String, String[]>  get_ListOfReplaceFields(AbstractMap<String, String> hm_replaceDbValues) throws myException {

		LinkedHashMap<String, String[]> oMap = new LinkedHashMap<String, String[]>();
		 
		oMap.put("#SYS_USERNAME#", 						pair("Benutzername des angemeldeten Benutzers",					bibALL.get_USERNAME()));
		oMap.put("#SYS_KUERZEL#", 						pair("Kürzel des angemeldeten Benutzers",						bibALL.get_KUERZEL()));
		oMap.put("#SYS_USERID#", 						pair("ID_USER des angemeldeten Benutzers",						bibALL.get_ID_USER()));
		oMap.put("#SYS_MANDANT_ID#", 					pair("ID des Mandanten",										bibALL.get_ID_MANDANT()));
		oMap.put("#SYS_ID_ADRESSE_MANDANT#", 			pair("ID_ADRESSE des aktuellen Mandanten",						bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("")));
		oMap.put("#SYS_REPORTDATE#", 					pair("Aktuelles Datum",											bibALL.get_cDateNOW()));
		oMap.put("#SYS_REPORTDATE_FIRST_DAY_OF_MONTH#",	pair("Erster Tag des Monats berechnet auf den aktuellen Tag",	bibALL.get_cDateFirstDateActualMonth()));
		oMap.put("#SYS_REPORTDATE_LAST_DAY_OF_MONTH#", 	pair("Letzter Tag des Monats berechnet auf den aktuellen Tag",	bibALL.get_cDateLastDateActualMonth()));
		 
		
		//jetzt die datenbankwerte
		if (hm_replaceDbValues != null) {
			for (String key: hm_replaceDbValues.keySet()) {
				oMap.put(key, 	pair("Wert aus der Datenbank",hm_replaceDbValues.get(key)));
			}
		}
		
		
		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
		RECLIST_GROOVYSCRIPT rlGroovy = new RECLIST_GROOVYSCRIPT("","");
		for (RECORD_GROOVYSCRIPT recScript: rlGroovy.values())
		{
			
			try
			{
				RECORD_GROOVYSCRIPT_SPECIAL recSpec = new RECORD_GROOVYSCRIPT_SPECIAL(recScript);
				//oMap.put("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+"#",pair(recSpec.get_USER_TEXT_cUF_NN("-"), recSpec._get_KeyValuePair()[1]));
				//simulierter "Platzhalter-Text"
				//nachsehen, ob es parameter gibt
				String c_paramterliste=bibALL.Concatenate(recSpec.get_v_parameter_liste_in_groovyscript(), ":", "");
				if (S.isFull(c_paramterliste)) {
					c_paramterliste = ":"+c_paramterliste;
				}
				
				try {
					oMap.put("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+c_paramterliste+"#",pair(recSpec.get_USER_TEXT_cUF_NN("-"),
																					bibGroovy.replace_groovy_placeholders_with_groovy_results("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+c_paramterliste+"#",recSpec)
																					));
				} catch (myGroovyException e) {
					oMap.put("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+c_paramterliste+"#",pair(recSpec.get_USER_TEXT_cUF_NN("-"),
							"<--Ausführung nicht möglich-->"
							));
				
					e.printStackTrace();
				}
				 
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		 
		 return oMap;
	}

	
	
	
	/**
	 * 2014-05-21
	 * liste fuer Bedienerhilfe, alle ersetzbaren groovy-strings / werte-Paare 
	 * @bWithHashTagStartAndEnd  dann wird am anfang und ende ein Hashtag # eingefügt
	 * @return
	 * @throws myException 
	 */
	public static LinkedHashMap<String, String[]>  get_ListOfReplaceFieldsOnlyGroovy_WITHOUT_PARAMETERS(boolean bWithHashTagStartAndEnd) throws myException
	{

		LinkedHashMap<String, String[]> oMap = new LinkedHashMap<String, String[]>();
		
		String cBegrenzer = bWithHashTagStartAndEnd?"#":"";
		 
		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
		RECLIST_GROOVYSCRIPT rlGroovy = new RECLIST_GROOVYSCRIPT("","");
		for (RECORD_GROOVYSCRIPT recScript: rlGroovy.values())
		{
			
			try
			{

				RECORD_GROOVYSCRIPT_SPECIAL recSpec = new RECORD_GROOVYSCRIPT_SPECIAL(recScript);
				String c_paramterliste=bibALL.Concatenate(recSpec.get_v_parameter_liste_in_groovyscript(), ":", "");
				if (S.isEmpty(c_paramterliste.trim())) {
					oMap.put(cBegrenzer+"GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+cBegrenzer,	pair(recSpec.get_USER_TEXT_cUF_NN("-"), 
																									bibGroovy.replace_groovy_placeholders_with_groovy_results("#GROOVY:"+recScript.get_NAME_RETURN_VAR_cUF()+c_paramterliste+"#",recSpec)));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		 
		 return oMap;
	}

	
	
	
	private static String[] pair(String cBeschreibung, String cWert)
	{
		String[] cRueck = new String[2];
		cRueck[0]=cBeschreibung;
		cRueck[1]=cWert;
		
		return cRueck;
	}
	
	
	/**
	 * 
	 * @param cStringWithVariables
	 * @param hm_zusatzKeyValues  eine hashMap mit zusatzwerten, die ebenfalls ersetzt werden koennen (z.B. werte aus datenbank-abfragen)
	 * @return
	 * @throws myException
	 */
	public static String ReplaceSysvariablesInStrings(String cStringWithVariables, LinkedHashMap<String,String> hm_zusatzKeyValues) throws myException 
	{
		
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_USERNAME#", bibALL.get_USERNAME());
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_KUERZEL#", bibALL.get_KUERZEL());
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_USERID#", bibALL.get_ID_USER());
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_MANDANT_ID#", bibALL.get_ID_MANDANT());
		 //fehler bei der ersten definition, wird fuer vorhandene reports drinngelassen
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#ID_ADRESSE_MANDANT#", bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));  
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_ID_ADRESSE_MANDANT#", bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE#", bibALL.get_cDateNOW());
		
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE_FIRST_DAY_OF_MONTH#", bibALL.get_cDateFirstDateActualMonth());
		cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, "#SYS_REPORTDATE_LAST_DAY_OF_MONTH#", bibALL.get_cDateLastDateActualMonth());
		

		if (hm_zusatzKeyValues!=null) {
			for (String key: hm_zusatzKeyValues.keySet()) {
				cStringWithVariables = bibALL.ReplaceTeilString(cStringWithVariables, key, S.NN(hm_zusatzKeyValues.get(key)));
			}
		}
		
		
		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
		RECLIST_GROOVYSCRIPT rlGroovy = new RECLIST_GROOVYSCRIPT("","");
		for (RECORD_GROOVYSCRIPT recScript: rlGroovy.values()) {
			cStringWithVariables = bibGroovy.replace_groovy_placeholders_with_groovy_results(cStringWithVariables, new RECORD_GROOVYSCRIPT_SPECIAL(recScript));
		}
		
		
		
		
		return cStringWithVariables;
	}


	
	
	
	
}
