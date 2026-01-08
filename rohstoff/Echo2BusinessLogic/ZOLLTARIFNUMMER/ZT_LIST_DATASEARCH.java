 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class ZT_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public ZT_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.zolltarifnummer.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.ZT_NUMMER_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(ZOLLTARIFNUMMER.aktiv.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.aktiv),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.bm_nummer.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.bm_nummer),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.bm_text.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.bm_text),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.id_zolltarifnummer.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.id_zolltarifnummer),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.letzter_import.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.letzter_import),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.nummer.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.nummer),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.reverse_charge.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.reverse_charge),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.text1.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.text1),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.text2.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.text2),	true);
        this.addSearchDef(ZOLLTARIFNUMMER.text3.fn(),ZT_READABLE_FIELD_NAME.getReadable(ZOLLTARIFNUMMER.text3),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_ZOLLTARIFNUMMER  FROM "+bibE2.cTO()+"."+_TAB.zolltarifnummer.n()+" WHERE UPPER(TO_CHAR("+_TAB.zolltarifnummer.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_ZOLLTARIFNUMMER  FROM "+bibE2.cTO()+"."+_TAB.zolltarifnummer.n()+" WHERE UPPER(TO_CHAR("+_TAB.zolltarifnummer.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
