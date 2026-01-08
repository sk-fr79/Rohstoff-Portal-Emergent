package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_WAEHRUNG_DROPDOWN extends MyE2_DB_SelectField 
{

	public DB_Component_WAEHRUNG_DROPDOWN(SQLField osqlField) throws myException 
	{
		super(osqlField);
		E2_DropDownSettings ddWaehrung = new E2_DropDownSettings( 	"JD_WAEHRUNG", 
																"  NVL(KURZBEZEICHNUNG,'-')||' ('|| NVL(WAEHRUNGSSYMBOL,'-')||')'", 
																"ID_WAEHRUNG",
																null, null, true, 
																"NVL(KURZBEZEICHNUNG,'-')");
		this.set_ListenInhalt(ddWaehrung.getDD(), false);
	}

}
