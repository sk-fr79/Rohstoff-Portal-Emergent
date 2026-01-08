 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.TRANSLATOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class BOR_ART_LIST_DATASEARCH extends E2_DataSearch {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_LIST_DATASEARCH(E2_NavigationList oNaviList ,PARAMHASH  p_params) throws myException    {
        super(_TAB.bordercrossing_artikel.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.params = p_params;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(BORDERCROSSING_ARTIKEL.id_artikel.fn(),"id_artikel",true);
        this.addSearchDef(BORDERCROSSING_ARTIKEL.id_bordercrossing.fn(),"id_bordercrossing",true);
        this.addSearchDef(BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel.fn(),"id_bordercrossing_artikel",true);
        this.addSearchDef(BORDERCROSSING_ARTIKEL.menge.fn(),"menge",true);
        //20180523: datenbank gestützte suche zufuegen
        this.initAfterConstruction();
        
    }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException   {
    
    
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_bordercrossing_artikel  FROM "+bibE2.cTO()+"."+_TAB.bordercrossing_artikel.n()+" WHERE TO_CHAR("+_TAB.bordercrossing_artikel.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_bordercrossing_artikel  FROM "+bibE2.cTO()+"."+_TAB.bordercrossing_artikel.n()+" WHERE UPPER("+_TAB.bordercrossing_artikel.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
