
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class BOR_LIST_SqlFieldMAP extends Project_SQLFieldMAP {

	public BOR_LIST_SqlFieldMAP() throws myException {
		super(_TAB.bordercrossing.n(), "", false);

		this.initFields();
	}

}
