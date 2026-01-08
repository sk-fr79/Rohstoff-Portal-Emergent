package panter.gmbh.basics4project;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_TableNamingAgent;


public class Project_TableNamingAgent extends XX_TableNamingAgent
{
	


	public String get_cNameOfPrimaryKey(String cTableName)
	{
		return "ID_"+cTableName.substring(3);
	}

	public String get_cNameOfSequence(String cTableName)
	{
		return "SEQ_"+cTableName.substring(3);
	}

	public String get_SQLSequenceQuery(String cTableName)
	{
		String cSQL = "SELECT "+bibE2.cTO()+"."+this.get_cNameOfSequence(cTableName)+".NEXTVAL FROM DUAL";
		return cSQL;
	}

	public Vector<String> get_vStringOfNotUsedFields()
	{
		Vector<String>  vRueck = new Vector<String> ();
		vRueck.add("ID_MANDANT");
		vRueck.add("LETZTE_AENDERUNG");
		vRueck.add("GEAENDERT_VON");
		return vRueck ;
	}


}
