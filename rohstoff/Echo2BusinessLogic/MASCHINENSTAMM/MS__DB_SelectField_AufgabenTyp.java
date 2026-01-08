package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class MS__DB_SelectField_AufgabenTyp extends MyE2_DB_SelectField 
{

	public MS__DB_SelectField_AufgabenTyp(SQLField osqlField, int iWidth) throws myException 
	{
		super(osqlField);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JT_MASCHINEN_AUFGABE_TYP", "AUFGABE_TYP", "ID_MASCHINEN_AUFGABE_TYP","", null, true,"AUFGABE_TYP");
		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		
	}

}
