 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CON_LIST_DATASEARCH extends E2_DataSearch
{
    public CON_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.container.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(CONTAINER.id_container.fn(),"ID Container",true);
        this.addSearchDef(CONTAINER.container_nr.fn(),"Container-Nr",false);
        this.addSearchDef(CONTAINER.bemerkung.fn(),"Bemerkung",false);
        this.addSearchDef(CONTAINER.tara.fn(),"Tara-Gewicht",true);
        this.addSearchDef(CONTAINER.id_containertyp.fn(),"ID Containertyp",true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_container  FROM "+bibE2.cTO()+"."+_TAB.container.n()+" WHERE TO_CHAR("+_TAB.container.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_container  FROM "+bibE2.cTO()+"."+_TAB.container.n()+" WHERE UPPER("+_TAB.container.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
