 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_REITER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR_CONST.TRANSLATOR;
public class RR_LIST_DATASEARCH extends E2_DataSearch
{
    public RR_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.report_reiter.n(),_TAB.report_reiter.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REPORT_REITER.id_report_reiter.fn(),"Id",true);
        this.addSearchDef(REPORT_REITER.reihenfolge.fn(),"Reihenfolge",true);
        this.addSearchDef(REPORT_REITER.reitername.fn(),"Reitername",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT "+REPORT_REITER.id_report_reiter.tnfn()+ " FROM "+bibE2.cTO()+"."+_TAB.report_reiter.n()+" WHERE TO_CHAR("+_TAB.report_reiter.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT "+REPORT_REITER.id_report_reiter.tnfn()+ " FROM "+bibE2.cTO()+"."+_TAB.report_reiter.n()+" WHERE UPPER("+_TAB.report_reiter.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
