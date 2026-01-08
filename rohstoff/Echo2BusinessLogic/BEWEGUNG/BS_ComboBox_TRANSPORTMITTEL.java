package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BS_ComboBox_TRANSPORTMITTEL extends 		MyE2_DB_ComboBoxErsatz
{
	public BS_ComboBox_TRANSPORTMITTEL(SQLFieldMAP osqlFieldMap, boolean bBreit) throws myException
	{
		super(osqlFieldMap.get_("TRANSPORTMITTEL"), "SELECT BESCHREIBUNG AS B1,BESCHREIBUNG AS B2 FROM "+bibE2.cTO()+".JT_TRANSPORTMITTEL ORDER BY BESCHREIBUNG", false);
		if (bBreit)
		{
			this.set_WidthAndHeightOfDropDown(new Extent(400),new Extent(200),null, new Integer(400));
			this.get_oTextField().setFont(new E2_FontPlain());
		}
		else
		{
			this.set_WidthAndHeightOfDropDown(new Extent(200),new Extent(100),null, new Integer(200));
			this.get_oTextField().setFont(new E2_FontPlain(-2));
		}
		
		
	}
	
}
