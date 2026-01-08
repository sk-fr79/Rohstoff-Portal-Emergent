package panter.gmbh.Echo2.AgentsAndValidators;

import java.util.Vector;


/*
 * agent, der bestimmt, wie die Namen von diversen tabellenteilen
 * sind, ausgehend von eine bestimmten tabellennamen 
 */
public abstract class XX_TableNamingAgent
{
	public abstract String 			get_cNameOfPrimaryKey(String cTableName);
	public abstract String 			get_cNameOfSequence(String cTableName);
	public abstract String 			get_SQLSequenceQuery(String cTableName);
	public abstract Vector<String> 	get_vStringOfNotUsedFields();    // getrennt durch :name1:name2:
	
	public String get_StringOfNotUsedFields(String cSeparator)
	{
		String cRueck = cSeparator;
		Vector<String> vFields = this.get_vStringOfNotUsedFields();
		for (int i=0;i<vFields.size();i++)
			cRueck += (((String)vFields.get(i))+cSeparator);
		
		cRueck += cSeparator;
		return cRueck;
	}
}
