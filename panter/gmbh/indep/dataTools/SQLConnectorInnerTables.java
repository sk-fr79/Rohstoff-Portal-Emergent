package panter.gmbh.indep.dataTools;


/*
 * klasse, die die abfragedefinition zur verbindung innerer Tabellen regelt
 */
public class SQLConnectorInnerTables
{
	String cConnectorString = null;

	public SQLConnectorInnerTables(String connString)
	{
		super();
		cConnectorString = connString;
	}

	public String get_cConnectorString()
	{
		return cConnectorString;
	}

	public void set_cConnectorString(String connectorString)
	{
		cConnectorString = connectorString;
	}
	
	
}
