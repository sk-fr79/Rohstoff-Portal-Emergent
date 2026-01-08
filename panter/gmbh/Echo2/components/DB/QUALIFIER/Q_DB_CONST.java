package panter.gmbh.Echo2.components.DB.QUALIFIER;

public class Q_DB_CONST
{
	public static String get_TABLE_NAME(String cTableName)
	{
		if 		(cTableName.startsWith("JT_"))
		{
			return cTableName.substring(3);
		}
		else if (cTableName.startsWith("JD_"))
		{
			return cTableName.substring(3);
		}
		else
		{
			return cTableName;
		}
	}
}
