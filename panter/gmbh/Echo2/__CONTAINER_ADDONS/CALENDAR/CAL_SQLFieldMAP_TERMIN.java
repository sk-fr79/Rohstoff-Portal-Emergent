package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CAL_SQLFieldMAP_TERMIN extends Project_SQLFieldMAP 
{
	
	
	public CAL_SQLFieldMAP_TERMIN() throws myException 
	{
		super("JT_TERMIN", null, false);
		
		this.get_("ZEIT_VON").set_cDefaultValueFormated("00:00");
		this.get_("ZEIT_BIS").set_cDefaultValueFormated("00:00");
		this.initFields();

	}

}
