 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REPORT_REITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;
public class RR_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {
    public RR_MASK_DataObjectCollector() throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.report_reiter), new RR_MASK_DataObject(new RECORDNEW_REPORT_REITER()));
    }
    
    
    public RR_MASK_DataObjectCollector(String id_report_reiter, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.report_reiter), new RR_MASK_DataObject(new RECORD_REPORT_REITER(id_report_reiter),status));
    }
    @Override
    public void database_to_dataobject(Object id_report_reiter) throws myException {
       //wird bei verbundenen dataObjects benoetigt, wo bei der basis-id gestartet, alle aufgebaut wird
    }
    @Override
    public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)    throws myException {
    }
    


}
 
