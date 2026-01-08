package panter.gmbh.indep.dataTools;

public class MyDBToolBox_LOG_INFO 
{
	private String cTABLENAME = null;
	private String ID_TABLE = null;
	private String AKTION = null;
	private String DB_SESSION = null;
	
	public MyDBToolBox_LOG_INFO(String ctablename, String id_table, String aktion, String db_session) 
	{
		super();
		this.cTABLENAME = ctablename;
		this.ID_TABLE = id_table;
		this.AKTION = aktion;
		this.DB_SESSION = db_session;
	}

	public boolean get_IS_INSERT()
	{
		return this.AKTION.equals("INSERT");
	}

	public boolean get_IS_UPDATE()
	{
		return this.AKTION.equals("UPDATE");
	}

	public boolean get_IS_DELETE()
	{
		return this.AKTION.equals("DELETE");
	}

	
	public String get_cAKTION() 
	{
		return AKTION;
	}

	public String get_cTABLENAME() 
	{
		return cTABLENAME;
	}

	public String get_cID_TABLE() 
	{
		return ID_TABLE;
	}
	
	public String get_cDB_SESSION() 
	{
		return DB_SESSION;
	}

	
}
