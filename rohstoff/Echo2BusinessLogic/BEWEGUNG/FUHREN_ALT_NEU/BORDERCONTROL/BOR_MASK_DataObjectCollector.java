
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BORDERCROSSING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING;
import panter.gmbh.indep.exceptions.myException;


public class BOR_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {

	public BOR_MASK_DataObjectCollector() throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.bordercrossing), new BOR_MASK_DataObject(new RECORDNEW_BORDERCROSSING()));
	}

	public BOR_MASK_DataObjectCollector(String id_bordercrossing, MASK_STATUS status) throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.bordercrossing), new BOR_MASK_DataObject(new RECORD_BORDERCROSSING(id_bordercrossing), status));
	}

	@Override
	public void database_to_dataobject(Object id_bordercrossing) throws myException {

		// wird bei verbundenen dataObjects benoetigt, wo bei der basis-id
		// gestartet, alle aufgebaut wird
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException {

	}


}
