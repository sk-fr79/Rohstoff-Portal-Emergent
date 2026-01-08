 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class AI_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public AI_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super();
        
        AI__TYP typ = (AI__TYP)p_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);

        this._init(_TAB.adresse_info.n(),_TAB.scanner_settings.keyFieldName(), typ==AI__TYP.INFO?MODUL.ADRESSE_INFO_EMBEDDED_LIST.get_callKey():MODUL.ADRESSE_MESSAGE_EMBEDDED_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(ADRESSE_INFO.aktiv.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.aktiv),	true);
        this.addSearchDef(ADRESSE_INFO.datumeintrag.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumeintrag),	true);
        this.addSearchDef(ADRESSE_INFO.datumereignis.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumereignis),	true);
        this.addSearchDef(ADRESSE_INFO.folgedatum.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.folgedatum),	true);
        this.addSearchDef(ADRESSE_INFO.id_adresse.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse),	true);
        this.addSearchDef(ADRESSE_INFO.id_adresse_info.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse_info),	true);
        this.addSearchDef(ADRESSE_INFO.id_aktionsanlass.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_aktionsanlass),	true);
        this.addSearchDef(ADRESSE_INFO.id_besuchsergebnis1.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis1),	true);
        this.addSearchDef(ADRESSE_INFO.id_besuchsergebnis2.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis2),	true);
        this.addSearchDef(ADRESSE_INFO.id_besuchsergebnis3.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis3),	true);
        this.addSearchDef(ADRESSE_INFO.id_user.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user),	true);
        this.addSearchDef(ADRESSE_INFO.id_user_ersatz.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_ersatz),	true);
        this.addSearchDef(ADRESSE_INFO.id_user_sachbearbeiter.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_sachbearbeiter),	true);
        this.addSearchDef(ADRESSE_INFO.ist_message.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.ist_message),	true);
        this.addSearchDef(ADRESSE_INFO.kuerzel.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.kuerzel),	true);
        this.addSearchDef(ADRESSE_INFO.message_sofort.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_sofort),	true);
        this.addSearchDef(ADRESSE_INFO.message_typ.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_typ),	true);
        this.addSearchDef(ADRESSE_INFO.text.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.text),	true);
        this.addSearchDef(ADRESSE_INFO.wiederholungjaehrlich.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungjaehrlich),	true);
        this.addSearchDef(ADRESSE_INFO.wiederholungmonatlich.fn(),AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungmonatlich),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_ADRESSE_INFO  FROM "+bibE2.cTO()+"."+_TAB.adresse_info.n()+" WHERE UPPER(TO_CHAR("+_TAB.adresse_info.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_ADRESSE_INFO  FROM "+bibE2.cTO()+"."+_TAB.adresse_info.n()+" WHERE UPPER(TO_CHAR("+_TAB.adresse_info.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
