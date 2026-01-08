package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import java.util.HashMap;

import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.specialValidation.rulesCONST;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RUL_DB_RuleTypes extends MyE2_DB_SelectField
{

	public RUL_DB_RuleTypes(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		HashMap<String, String>  hmRegeln = new HashMap<String, String>();
		hmRegeln.putAll(rulesCONST.hmFieldRules4UserDropdowns);
		
		String[][] cZusatz = {{"-",""}};
		
		String[][] arrTypes = bibARR.get_Array_ValueKey(hmRegeln,cZusatz);
		
		this.set_ListenInhalt(arrTypes, true);
		
	}

}
