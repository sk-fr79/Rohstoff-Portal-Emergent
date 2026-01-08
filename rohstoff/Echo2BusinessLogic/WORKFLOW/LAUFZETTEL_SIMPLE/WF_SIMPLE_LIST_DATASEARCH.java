 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE.WF_SIMPLE_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public WF_SIMPLE_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.laufzettel_eintrag.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);

        this.addSearchDef(LAUFZETTEL_EINTRAG.id_laufzettel.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.id_laufzettel_status.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel_status),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.id_user_bearbeiter.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_user_bearbeiter),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.id_user_besitzer.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_user_besitzer),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.aufgabe.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe),	true);
        this.addSearchDef(LAUFZETTEL_EINTRAG.bericht.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.bericht),	true);

        this.addSearchDefParent(LAUFZETTEL.text.fn(),WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL.text), true);
        
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    private void addSearchDefParent(String cFieldName, String cInfoText, boolean searchWithLike)    throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_LAUFZETTEL_EINTRAG  FROM "+bibE2.cTO()+"."+_TAB.laufzettel_eintrag.n()+" INNER JOIN  "+bibE2.cTO()+"."+_TAB.laufzettel.n()
           + " ON " +bibE2.cTO()+"."+LAUFZETTEL_EINTRAG.id_laufzettel.tnfn() + " = " +bibE2.cTO()+"."+LAUFZETTEL.id_laufzettel.tnfn() + 
        " WHERE UPPER(TO_CHAR("+_TAB.laufzettel.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_LAUFZETTEL_EINTRAG  FROM "+bibE2.cTO()+"."+_TAB.laufzettel_eintrag.n()+" INNER JOIN  "+bibE2.cTO()+"."+_TAB.laufzettel.n()
           + " ON " +bibE2.cTO()+"."+LAUFZETTEL_EINTRAG.id_laufzettel.tnfn() + " = " +bibE2.cTO()+"."+LAUFZETTEL.id_laufzettel.tnfn() + 
        " WHERE UPPER(TO_CHAR("+_TAB.laufzettel.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
  
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_LAUFZETTEL_EINTRAG  FROM "+bibE2.cTO()+"."+_TAB.laufzettel_eintrag.n()+" WHERE UPPER(TO_CHAR("+_TAB.laufzettel_eintrag.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_LAUFZETTEL_EINTRAG  FROM "+bibE2.cTO()+"."+_TAB.laufzettel_eintrag.n()+" WHERE UPPER(TO_CHAR("+_TAB.laufzettel_eintrag.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
