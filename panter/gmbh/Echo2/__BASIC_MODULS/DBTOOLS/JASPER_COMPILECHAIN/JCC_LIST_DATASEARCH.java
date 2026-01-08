 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN.JCC_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_LIST_DATASEARCH extends E2_DataSearch
{
    public JCC_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.jasper_compile_chain.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(JASPER_COMPILE_CHAIN.compiletarget.fn(),				"Ziel zum kompilieren",false);
        this.addSearchDef(JASPER_COMPILE_CHAIN.reportname.fn(),					"Reportname",false);
        this.addSearchDef(JASPER_COMPILE_CHAIN.id_jasper_compile_chain.fn(),	"id",true);
        
		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

    }
    
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_jasper_compile_chain  FROM "+bibE2.cTO()+"."+_TAB.jasper_compile_chain.n()+" WHERE TO_CHAR("+_TAB.jasper_compile_chain.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_jasper_compile_chain  FROM "+bibE2.cTO()+"."+_TAB.jasper_compile_chain.n()+" WHERE UPPER("+_TAB.jasper_compile_chain.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
