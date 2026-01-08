package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.indep.exceptions.myException;

public class HADM_DataObjectCollector extends RB_DataobjectsCollector_V1 {


	public HADM_DataObjectCollector() throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.hilfetext), new HADM_DataObject(new RECORDNEW_HILFETEXT()));
	}
	
	
	public HADM_DataObjectCollector(String id_hilfetext) throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.hilfetext), new HADM_DataObject(new RECORD_HILFETEXT(id_hilfetext),MASK_STATUS.EDIT));
	}

	@Override
	public void database_to_dataobject(Object id_hilfetext) throws myException {
//		HADM_DataObject dataObj = (HADM_DataObject)this.get(new RB_KM(_TAB.hilfetext));
//		
//		dataObj.set_RecORD(new RECORD_HILFETEXT((String)id_hilfetext));
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)	throws myException {

	}



}
