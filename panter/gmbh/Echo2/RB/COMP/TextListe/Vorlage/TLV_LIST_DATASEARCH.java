 
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class TLV_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public TLV_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.text_liste_vorlage.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.TEXT_LISTE_VORLAGE_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(TEXT_LISTE_VORLAGE.beschreibung_lang.fn(),TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.beschreibung_lang),	true);
        this.addSearchDef(TEXT_LISTE_VORLAGE.bezeichnung.fn(),TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.bezeichnung),	true);
        this.addSearchDef(TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn(),TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.id_text_liste_vorlage),	true);
        this.addSearchDef(TEXT_LISTE_VORLAGE.tablereference.fn(),TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.tablereference),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_TEXT_LISTE_VORLAGE  FROM "+bibE2.cTO()+"."+_TAB.text_liste_vorlage.n()+" WHERE UPPER(TO_CHAR("+_TAB.text_liste_vorlage.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_TEXT_LISTE_VORLAGE  FROM "+bibE2.cTO()+"."+_TAB.text_liste_vorlage.n()+" WHERE UPPER(TO_CHAR("+_TAB.text_liste_vorlage.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
