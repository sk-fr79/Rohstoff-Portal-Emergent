package panter.gmbh.indep.dataTools;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class MyRecordComparer
{
	private Vector<MyRecordCompareRule> vCompareRules = new Vector<MyRecordCompareRule>();
	private Vector<String>			    vFieldsList = new Vector<String>();
	
	public void add_CompareRule(String cFieldName) throws myException
	{
		if (this.vFieldsList.contains(cFieldName.toUpperCase()))
		{
			throw new myException(this,"It is forbidden to put two times the same fieldname to comparelist !!");
		}
		this.vCompareRules.add(new StandardCompareRule(cFieldName));
		this.vFieldsList.add(cFieldName.toUpperCase());
	}
	
	public void add_CompareRule(MyRecordCompareRule ownRule) throws myException
	{
		if (this.vFieldsList.contains(ownRule.get_cFIELDNAME().toUpperCase()))
		{
			throw new myException(this,"It is forbidden to put two times the same fieldname to comparelist !!");
		}
		this.vCompareRules.add(ownRule);
		this.vFieldsList.add(ownRule.get_cFIELDNAME().toUpperCase());
	}
	
	
	public boolean IsEqual(MyRECORD rec1, MyRECORD rec2) throws myException
	{
		boolean bRueck = true;
				
		for (MyRecordCompareRule compare: this.vCompareRules)
		{
			if (!compare.IsEqual(rec1, rec2))
			{
				bRueck = false;
				
				//System.out.println("ungleichheit .... :"+compare.get_cFIELDNAME());
				
				break;
			}
		}
		return bRueck;
	}
	
	
	
	
	
	private class StandardCompareRule extends MyRecordCompareRule
	{
		public StandardCompareRule(String cfieldname)
		{
			super(cfieldname);
		}

		@Override
		public boolean IsEqual(MyRECORD rec1, MyRECORD rec2) throws myException
		{
//			//debug
//			boolean bTest =rec1.get_UnFormatedValue(this.get_cFIELDNAME(), "").equals(rec2.get_UnFormatedValue(this.get_cFIELDNAME(), "")); 
//			System.out.println(bTest?"GLEICH: "+this.get_cFIELDNAME():"UNGLEICH: "+this.get_cFIELDNAME());
//			//debug
			
			return rec1.get_UnFormatedValue(this.get_cFIELDNAME(), "").equals(rec2.get_UnFormatedValue(this.get_cFIELDNAME(), ""));
		}
		
	}
}
