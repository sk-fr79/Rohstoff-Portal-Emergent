 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class TX_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public TX_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.tax.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.TAX_RATE_V2_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(TAX.aktiv.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.aktiv),	true);
        this.addSearchDef(TAX.dropdown_text.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.dropdown_text),	true);
        this.addSearchDef(TAX.historisch.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.historisch),	true);
//        this.addSearchDef(TAX.id_fibu_konto_gs.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.id_fibu_konto_gs),	true);
//        this.addSearchDef(TAX.id_fibu_konto_re.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.id_fibu_konto_re),	true);
        this.addSearchDef(TAX.id_land.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.id_land),	true);
        this.addSearchDef(TAX.id_tax.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.id_tax),	true);
        this.addSearchDef(TAX.id_tax_group.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.id_tax_group),	true);
        this.addSearchDef(TAX.info_text_user.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.info_text_user),	true);
        this.addSearchDef(TAX.leervermerk.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.leervermerk),	true);
        this.addSearchDef(TAX.sort.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.sort),	true);
        this.addSearchDef(TAX.steuersatz.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.steuersatz),	true);
        this.addSearchDef(TAX.steuersatz_neu.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.steuersatz_neu),	true);
        this.addSearchDef(TAX.steuervermerk.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.steuervermerk),	true);
        this.addSearchDef(TAX.taxtyp.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.taxtyp),	true);
        this.addSearchDef(TAX.wechseldatum.fn(),TX_READABLE_FIELD_NAME.getReadableForMask(TAX.wechseldatum),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_TAX  FROM "+bibE2.cTO()+"."+_TAB.tax.n()+" WHERE UPPER(TO_CHAR("+_TAB.tax.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_TAX  FROM "+bibE2.cTO()+"."+_TAB.tax.n()+" WHERE UPPER(TO_CHAR("+_TAB.tax.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
