package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.exceptions.myException;

public abstract class MyRecordCompareRule
{
	private String cFIELDNAME = null;
	
	
	
	public MyRecordCompareRule(String cfieldname)
	{
		super();
		this.cFIELDNAME = cfieldname;
	}



	public abstract boolean IsEqual(MyRECORD rec1, MyRECORD rec2) throws myException;



	public String get_cFIELDNAME()
	{
		return this.cFIELDNAME;
	}
	
	
}
