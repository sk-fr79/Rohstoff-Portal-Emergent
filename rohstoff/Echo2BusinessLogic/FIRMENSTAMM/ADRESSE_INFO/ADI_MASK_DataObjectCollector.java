package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.exceptions.myException;


public class ADI_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {
	


    public ADI_MASK_DataObjectCollector(RECORD_ADRESSE  p_rec_adresse) throws myException {
        super();
		//this.rec_adresse = p_rec_adresse;
        this.registerComponent(new RB_KM(_TAB.adresse_info), new ADI_MASK_DataObject(new RECORDNEW_ADRESSE_INFO()));
    }
    
    
    public ADI_MASK_DataObjectCollector(String id_adresse_info, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.adresse_info), new ADI_MASK_DataObject(new RECORD_ADRESSE_INFO(id_adresse_info),status));
    }
    
    @Override
    public void database_to_dataobject(Object id_adresse_info) throws myException {
    }
    
    @Override
    public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)    throws myException {
    }
}
 
