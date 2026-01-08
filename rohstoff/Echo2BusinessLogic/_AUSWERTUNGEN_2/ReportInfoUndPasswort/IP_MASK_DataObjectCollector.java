package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.exceptions.myException;




public class IP_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {

	public IP_MASK_DataObjectCollector(String id_report, MASK_STATUS status)throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.report), new IP_MASK_DataObject(new RECORD_REPORT(id_report), status));
	}

	@Override
	public void database_to_dataobject(Object id_report) throws myException {
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)	throws myException {
	}
	


}
