package panter.gmbh.indep.dataTools;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class SQLFieldJoinOutside extends SQLField
{

	
	/*
	 * sqlfeld as verbinder zu haupttabelle ist 
	 */
	private SQLField	oFieldFromOtherTable = null; 
	
	/**
	 * @param ctablename
	 * @param cfieldname
	 * @param cfieldalias
	 * @param cfieldlabelforUser
	 * @param bisNullable
	 * @param oses
	 * @param oFieldFromMotherTable
	 * @throws myException
	 */
	public SQLFieldJoinOutside(	String ctablename, 
								String cfieldname, 
								String cfieldalias, 
								MyString cfieldlabelforUser, 
								boolean bisNullable, 
								HttpSession oses,
								SQLField oFieldFromMotherTable) throws myException
	{
		super(ctablename, cfieldname, cfieldalias, cfieldlabelforUser,bisNullable, oses);

		this.oFieldFromOtherTable = oFieldFromMotherTable;
		
		if (bibALL.isEmpty(cfieldname) || bibALL.isEmpty(oFieldFromMotherTable.get_cFieldName()))
			throw new myException("SQLFieldJoinOutside:Constuctor:Both fields must have a fieldname !!");
		
		this.set_bFieldCanBeWrittenInMask(false);
		
	}
	

	public SQLField get_oFieldFromConnectedTable()
	{
		return oFieldFromOtherTable;
	}



	
}
