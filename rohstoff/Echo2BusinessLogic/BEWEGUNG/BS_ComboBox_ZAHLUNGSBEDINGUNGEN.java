package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BS_ComboBox_ZAHLUNGSBEDINGUNGEN extends 		MyE2_DB_ComboBoxErsatz
{
	public BS_ComboBox_ZAHLUNGSBEDINGUNGEN(SQLFieldMAP osqlFieldMap, boolean bBreit) throws myException
	{
		super(osqlFieldMap.get_("ZAHLUNGSBEDINGUNGEN"), "SELECT KURZBEZEICHNUNG AS A,KURZBEZEICHNUNG AS B FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN ORDER BY KURZBEZEICHNUNG", false);
		if (bBreit)
		{
			this.set_WidthAndHeightOfDropDown(new Extent(400),new Extent(100),null, new Integer(400));
			this.get_oTextField().setFont(new E2_FontPlain());
		}
		else
		{
			this.set_WidthAndHeightOfDropDown(new Extent(200),new Extent(100),null, new Integer(100));
			this.get_oTextField().setFont(new E2_FontPlain(-2));
		}
		
		
	}
	
}
