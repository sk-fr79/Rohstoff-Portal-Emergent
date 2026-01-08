package panter.gmbh.indep;



public class StringConnector 
{
	private StringBuffer oStringBuffer = new StringBuffer();

	public StringConnector() 
	{
		super();
	}
	
	public void add_When_Not_Emtpy(String cAdd, String cSeparator)
	{
		if (cAdd != null && !cAdd.trim().equals(""))
		{
			oStringBuffer.append(cAdd);
			oStringBuffer.append(cSeparator);
		}
	}
	
	
	public String get_CompleteString()
	{
		return oStringBuffer.toString();
	}
	
}
