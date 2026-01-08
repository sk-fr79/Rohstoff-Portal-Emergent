 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
   
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_LIST_ComponentMap.BtStartReport;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
  
public class RQ_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQ_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    public void fillComponents(E2_ComponentMAP map, SQLResultMAP oUsedResultMAP) throws myException  {
    	
		Rec21 recReport = new Rec21(_TAB.reporting_query)._fill_id(
				oUsedResultMAP.getUfs(REPORTING_QUERY.id_reporting_query));

		BtStartReport rep = (BtStartReport)map.get(RQ_CONST.RQ_NAMES.LISTBUTTON_START_REPORT.db_val());

		rep.add_GlobalAUTHValidator_AUTO("STARTFREIGABE_"+RQ_CONST.getModuKenner(recReport));
		
		if (recReport.is_no_db_val(REPORTING_QUERY.aktiv)) {
			rep._setAktiv(false);
		}
    	
    }
}
 
 
