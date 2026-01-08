package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.AgentsAndValidators.XX_TableNamingAgent;

public abstract class XX_DatabaseCompatibilityLayer
{
	private String cShemeName = null;
	private XX_TableNamingAgent oTableNamer = null;
	
	public XX_DatabaseCompatibilityLayer(String cshemaName,XX_TableNamingAgent otableNamer)
	{
		this.cShemeName=	cshemaName;
		this.oTableNamer=	otableNamer;
	}	
	
	
	public abstract String get_QueryForTablesBelongToSheme();
	
	/**
	 * gibt eine sql-query zurueck mit den spalten
	 * [seq_name][min_value][max_value][last_number]
	 */
	public abstract String get_QueryForSequenceStructur();
	public abstract String get_StatementToCreateSequence(String cSequenceName, String cStartValue, String MaxValue);
	
	/**
	 * baut die sequence, die zum primary key einer tabelle gehoeren.
	 * Dazu wird die alte sequence geloescht, der neue startwert (mit evtl. offset)
	 * versehen wieder aufgebaut
	 * Rueckgabe = -1, wenn fehler, sonst der neue startwert
	 */
	public abstract long do_CreateTableSequenceWithLastValue(String cTableName, long iOffset, long iMinimumValue); 
	
	
	

	public String get_cShemeName()
	{
		return cShemeName;
	}
	
	public XX_TableNamingAgent get_oTableNamer()
	{
		return oTableNamer;
	}


	public void set_cShemeName(String shemeName)
	{
		cShemeName = shemeName;
	}


	public void set_oTableNamer(XX_TableNamingAgent tableNamer)
	{
		oTableNamer = tableNamer;
	}
}