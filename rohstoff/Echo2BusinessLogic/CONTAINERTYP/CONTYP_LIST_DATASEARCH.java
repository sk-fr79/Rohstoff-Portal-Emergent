 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_LIST_DATASEARCH extends E2_DataSearch
{
    public CONTYP_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.containertyp.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(CONTAINERTYP.id_containertyp.fn(),"ID Containertyp",true);
        this.addSearchDef(CONTAINERTYP.kurzbezeichnung.fn(),"Kurzbezeichnung",false);
        this.addSearchDef(CONTAINERTYP.kuerzel.fn(),"Kürzel",false);
        this.addSearchDef(CONTAINERTYP.beschreibung.fn(),"Beschreibung",false);
        this.addSearchDef(CONTAINERTYP.containerinhalt.fn(),"Containerinhalt",true);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

    }

    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_containertyp  FROM "+bibE2.cTO()+"."+_TAB.containertyp.n()+" WHERE TO_CHAR("+_TAB.containertyp.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_containertyp  FROM "+bibE2.cTO()+"."+_TAB.containertyp.n()+" WHERE UPPER("+_TAB.containertyp.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
