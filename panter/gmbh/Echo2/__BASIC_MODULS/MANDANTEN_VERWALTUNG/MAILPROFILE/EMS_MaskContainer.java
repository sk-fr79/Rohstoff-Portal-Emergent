package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class EMS_MaskContainer extends RB_ComponentMapCollector {

	public EMS_MaskContainer() throws myException {
		super();
		
		this.registerComponent(new RB_KM(_TAB.email_send_schablone), new EMS_Mask());
	}

	
	
}
