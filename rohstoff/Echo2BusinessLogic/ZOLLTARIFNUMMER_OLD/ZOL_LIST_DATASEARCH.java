 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class ZOL_LIST_DATASEARCH extends E2_DataSearch
{
    public ZOL_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.zolltarifnummer.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(ZOLLTARIFNUMMER.id_zolltarifnummer.fn(),"id_zolltarifnummer",true);
        this.addSearchDef(ZOLLTARIFNUMMER.aktiv.fn(),"aktiv",false);
        this.addSearchDef(ZOLLTARIFNUMMER.nummer.fn(),"nummer",false);
        this.addSearchDef(ZOLLTARIFNUMMER.text1.fn(),"text1",false);
        this.addSearchDef(ZOLLTARIFNUMMER.text2.fn(),"text2",false);
        this.addSearchDef(ZOLLTARIFNUMMER.text3.fn(),"text3",false);
        this.addSearchDef(ZOLLTARIFNUMMER.bm_text.fn(),"bm_text",false);
        this.addSearchDef(ZOLLTARIFNUMMER.bm_nummer.fn(),"bm_nummer",true);
        this.addSearchDef(ZOLLTARIFNUMMER.letzter_import.fn(),"letzter_import",false);
        this.addSearchDef(ZOLLTARIFNUMMER.reverse_charge.fn(),"reverse_charge",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_zolltarifnummer  FROM "+bibE2.cTO()+"."+_TAB.zolltarifnummer.n()+" WHERE TO_CHAR("+_TAB.zolltarifnummer.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_zolltarifnummer  FROM "+bibE2.cTO()+"."+_TAB.zolltarifnummer.n()+" WHERE UPPER("+_TAB.zolltarifnummer.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
