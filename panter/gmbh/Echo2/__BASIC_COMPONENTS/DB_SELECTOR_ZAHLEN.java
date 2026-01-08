package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_SELECTOR_ZAHLEN extends MyE2_DB_SelectField
{


	/**
	 * 
	 * @param osqlField
	 * @param aDefArray z.b. {{"-",""},{"1","1"},{"2","2"},{"3","3"}}
	 * @throws myException
	 */
	public DB_SELECTOR_ZAHLEN(SQLField osqlField, String[][] aDefArray, int iWidth) throws myException
	{
		super(osqlField, aDefArray,false);
		this.setWidth(new Extent(iWidth));
	}

}
