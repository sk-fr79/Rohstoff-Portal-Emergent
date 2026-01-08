package panter.gmbh.indep.dataTools;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;


public class SQLFieldForRestrictTableRange_FIELD_MUST_BE_NULL extends  SQLFieldForRestrictTableRange {

	public SQLFieldForRestrictTableRange_FIELD_MUST_BE_NULL(String ctablename,	String cfieldname, String cfieldlabel, MyString cfieldLabelForUser)
			throws myException {
		super(ctablename, cfieldname, cfieldlabel, cfieldLabelForUser,
				"NULL", bibE2.get_CurrSession());
	}

	//2013-06-24: eigene methode zur erzeugung des where-Blocks
	public String get_WhereBlockForSQL_Bedingung() throws myException {
		return (this.get_cTableName()+"."+this.get_cFieldName()+" IS NULL");
	}

	
	
}
