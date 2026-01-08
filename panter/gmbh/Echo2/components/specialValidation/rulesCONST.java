package panter.gmbh.Echo2.components.specialValidation;

import java.util.HashMap;

public class rulesCONST
{
	
	
	/*
	 * in der regelstruktur wird normalerweise auf Datenbank-Felder mit zugehoeriger Tabelle definiert
	 * Wenn in der Maske Komplexe Felder gesperrt werden sollen, dann muss als Tabellenname
	 *  NO_STANDARD_DB_FIELD  angegeben werden (hier funktioniert nur darf oder darf nicht)
	 */
	public static final String KEY_NO_STANDARD_DB_FIELD = "NO_STANDARD_DB_FIELD"; 

	
	//2012-02-24: edit-regeln: REGEX, ALLOW-EDIT, NOT-EMPTY, die regeln, die in der tabelle JT_FIELD_RULE stehen koennen
	public static enum EDIT_RULES_FROM_TABLE_FIELD_RULE
	{
		REGEX,
		ALLOW_EDIT,
		NOT_EMPTY,
		PRE_DEFINED,                      //2013-04-19: wert fuer feld vordefinieren
	};
	

	
	public static HashMap<String, String>  hmFieldRules4UserDropdowns = new HashMap<String, String>();
	static {
		HashMap<String, String>  hmRules = new HashMap<String, String>();
		hmRules.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.REGEX.toString(),		"Regulärer Ausdruck");
		hmRules.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.ALLOW_EDIT.toString(),	"Erlaube Eingabe");
		hmRules.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.NOT_EMPTY.toString(),	"Nicht leer");
		hmRules.put(rulesCONST.EDIT_RULES_FROM_TABLE_FIELD_RULE.PRE_DEFINED.toString(),	"Vordefiniert");
		hmFieldRules4UserDropdowns.putAll(hmRules);
	}
	

}
