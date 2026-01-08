package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CAL_SQLFieldMAP_TERMIN_USER extends Project_SQLFieldMAP 
{
	public CAL_SQLFieldMAP_TERMIN_USER(SQLFieldMAP oSQLFieldMap_TERMIN) throws myException 
	{
		super("JT_TERMIN_USER", null, false);
	
		
		// jetzt die connection herstellen
		SQLFieldJoinOutside oJoin = new SQLFieldJoinOutside(	"JT_TERMIN_USER",
																"ID_TERMIN",
																"ID_TERMIN",
																new MyE2_String("ID_TERMIN"),
																false,
																bibE2.get_CurrSession(),
																oSQLFieldMap_TERMIN.get_oSQLFieldPKMainTable());
		
		this.add_SQLField(oJoin,true);
		this.add_SQLField(
				new SQLFieldForRestrictTableRange(	"JT_TERMIN_USER",
													"ID_USER",
													"ID_USER",
													new MyE2_String("ID_USER"),
													bibALL.get_ID_USER(),
													bibE2.get_CurrSession()),true);
		
		this.get_("IS_OWNER").set_cDefaultValueFormated("Y");             // bei direkter neueingabe immer owner = Y
		                                                                  // die verteilten werden per batch vervielfaeltigt
		
		this.initFields();
	}

}
