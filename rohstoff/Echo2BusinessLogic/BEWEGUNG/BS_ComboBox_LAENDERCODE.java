package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BS_ComboBox_LAENDERCODE extends 		MyE2_DB_ComboBoxErsatz
{
	public BS_ComboBox_LAENDERCODE(SQLFieldMAP osqlFieldMap) throws myException
	{
		super(osqlFieldMap.get_("LAENDERCODE"), "SELECT LAENDERCODE AS A,LAENDERCODE AS B FROM "+bibE2.cTO()+".JD_LAND ORDER BY LAENDERCODE", false);
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(100),null, new Integer(40));
		
	}


	public BS_ComboBox_LAENDERCODE(SQLField oField) throws myException
	{
		super(oField, "SELECT LAENDERCODE AS A,LAENDERCODE AS B FROM "+bibE2.cTO()+".JD_LAND ORDER BY LAENDERCODE", false);
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(100),null, new Integer(40));
	}


	
}
