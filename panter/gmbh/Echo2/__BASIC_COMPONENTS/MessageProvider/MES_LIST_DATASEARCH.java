package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider.MES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class MES_LIST_DATASEARCH extends E2_DataSearch{
    public MES_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.message_provider.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(MESSAGE_PROVIDER.id_message_provider.fn(),"ID",true);
        
		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

    }
    
    
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException   {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_message_provider  FROM "+bibE2.cTO()+"."+_TAB.message_provider.n()+" WHERE TO_CHAR("+_TAB.message_provider.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_message_provider  FROM "+bibE2.cTO()+"."+_TAB.message_provider.n()+" WHERE UPPER("+_TAB.message_provider.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
