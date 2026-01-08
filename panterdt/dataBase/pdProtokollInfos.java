package panterdt.dataBase;

import java.util.Vector;

public class pdProtokollInfos
{
	private String 	cNumberOfTables = "";
	private String 	cNumberOfPrimaryKeys = "";
	private String 	cNumberOfIndex= "";
	private String  cNumberOfViews = "";
	private String  cNumberOfForeignKeys = "";
	private String  cNumberOfSequences = "";

	
	private Vector<String[]>  vExportInfos = new Vector<String[]>();


	public void add_ExportInfo(String cTableName,String CountToExport, String CountExported)
	{
		String[] cHelp = new String[3];
		cHelp[0]=cTableName;
		cHelp[1]=CountToExport;
		cHelp[2]=CountExported;
		this.vExportInfos.add(cHelp);
	}

	
	public int get_NumberOfExportInfos()
	{
		return this.vExportInfos.size();
	}
	
	
	public String get_Tablename(int iNumber)
	{
		return ((String[])this.vExportInfos.get(iNumber))[0];
	}
	public String get_CountToExport(int iNumber)
	{
		return ((String[])this.vExportInfos.get(iNumber))[1];
	}
	public String get_CountExported(int iNumber)
	{
		return ((String[])this.vExportInfos.get(iNumber))[2];
	}
	
	public String getNumberOfForeignKeys()
	{
		return cNumberOfForeignKeys;
	}


	public void setNumberOfForeignKeys(String numberOfForeignKeys)
	{
		cNumberOfForeignKeys = numberOfForeignKeys;
	}


	public String getNumberOfIndex()
	{
		return cNumberOfIndex;
	}


	public void setNumberOfIndex(String numberOfIndex)
	{
		cNumberOfIndex = numberOfIndex;
	}


	public String getNumberOfPrimaryKeys()
	{
		return cNumberOfPrimaryKeys;
	}


	public void setNumberOfPrimaryKeys(String numberOfPrimaryKeys)
	{
		cNumberOfPrimaryKeys = numberOfPrimaryKeys;
	}


	public String getNumberOfSequences()
	{
		return cNumberOfSequences;
	}


	public void setNumberOfSequences(String numberOfSequences)
	{
		cNumberOfSequences = numberOfSequences;
	}


	public String getNumberOfTables()
	{
		return cNumberOfTables;
	}


	public void setNumberOfTables(String numberOfTables)
	{
		cNumberOfTables = numberOfTables;
	}


	public String getNumberOfViews()
	{
		return cNumberOfViews;
	}


	public void setNumberOfViews(String numberOfViews)
	{
		cNumberOfViews = numberOfViews;
	}
	
	
	
	
	
	
}
