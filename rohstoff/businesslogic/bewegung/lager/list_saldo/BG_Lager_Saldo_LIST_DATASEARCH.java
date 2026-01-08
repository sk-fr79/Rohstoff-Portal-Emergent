 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public class BG_Lager_Saldo_LIST_DATASEARCH extends E2_DataSearch
{
	
	E2_NavigationList _naviList = null;
	
    public BG_Lager_Saldo_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(""/*_TAB.bg_ladung.n()*/,_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        _naviList= oNaviList;
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
//        this.addSearchDef(BG_LADUNG.anr1.tnfn(),"Anr 1",false);
//        this.addSearchDef(BG_LADUNG.artbez1.tnfn(),"Artbez 1",false);
//        this.addSearchDef(BG_LADUNG.id_artikel.tnfn(),"ID Sorte",true);
        this.addSearchDef("S1.ID_ADRESSE", "ID Adresse", true);
        
        // Suchfeld-String aus der Sqlfield holen
        String sOrt = _naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_(BG_Lager_Saldo_NAMES.ADRESS_INFO.name()).get_cFieldAusdruck();
        this.addSearchDef(sOrt, "Lager", false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    
    
    
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {

    	// select für die Suche aus der Sqlfieldmap holen
    	String sqlSelect = _naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR(null,false);
    	sqlSelect = sqlSelect.substring(0, sqlSelect.toUpperCase().lastIndexOf("ORDER BY"));
    	
        String cSearch = "";
        if (bNumber) {
        	cSearch = sqlSelect  +" WHERE TO_CHAR("+cFieldName+")='#WERT#'";
        } else {
        	cSearch = sqlSelect + " WHERE UPPER("+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
