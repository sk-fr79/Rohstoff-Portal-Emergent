 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TRANSLATOR;
  
public class TAXG_LIST_DATASEARCH extends E2_DataSearch {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_LIST_DATASEARCH(E2_NavigationList oNaviList ,PARAMHASH  p_params) throws myException    {
        super(_TAB.tax_group.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.params = p_params;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(TAX_GROUP.aktiv.fn(),				TAXG_CONST.TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.aktiv),true);
        this.addSearchDef(TAX_GROUP.id_tax_group.fn(),		TAXG_CONST.TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.id_tax_group),true);
        this.addSearchDef(TAX_GROUP.kurztext.fn(),			TAXG_CONST.TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.kurztext),true);
        this.addSearchDef(TAX_GROUP.langtext.fn(),			TAXG_CONST.TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.langtext),true);
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
        
    }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException   {
    
    
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_tax_group  FROM "+bibE2.cTO()+"."+_TAB.tax_group.n()+" WHERE TO_CHAR("+_TAB.tax_group.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_tax_group  FROM "+bibE2.cTO()+"."+_TAB.tax_group.n()+" WHERE UPPER("+_TAB.tax_group.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
