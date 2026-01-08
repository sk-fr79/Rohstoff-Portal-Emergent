package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_LIST_DATASEARCH extends E2_DataSearch
{
    public DRUCK_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.drucker.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(DRUCKER.aktiv.fn(),"Aktiv",true);
        this.addSearchDef(DRUCKER.beschreibung.fn(),"Beschreibung",true);
        this.addSearchDef(DRUCKER.direct_druck_befehl.fn(),"Druckbefehl",true);
        this.addSearchDef(DRUCKER.id_drucker.fn(),"ID",true);
        this.addSearchDef(DRUCKER.name.fn(),"Name",true);
        this.addSearchDef(DRUCKER.standort.fn(),"Standort",true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_drucker  FROM "+bibE2.cTO()+"."+_TAB.drucker.n()+" WHERE TO_CHAR("+_TAB.drucker.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_drucker  FROM "+bibE2.cTO()+"."+_TAB.drucker.n()+" WHERE UPPER("+_TAB.drucker.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
