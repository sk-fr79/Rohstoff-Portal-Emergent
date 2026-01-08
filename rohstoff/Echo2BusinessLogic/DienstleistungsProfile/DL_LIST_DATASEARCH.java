 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_like;
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
  
public class DL_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public DL_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.dlp_profil.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.DIENSTLEISTUNG_PROFIL_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(DLP_PROFIL.id_dlp_profil.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_dlp_profil),								false);
        this.addSearchDef(DLP_PROFIL.anteil_menge.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.anteil_menge),									true);
        this.addSearchDefAdresse(DLP_PROFIL.id_adresse_start.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_start),							false);
        this.addSearchDefAdresse(DLP_PROFIL.id_adresse_ziel.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_ziel),							false);
        this.addSearchDefAdresse(DLP_PROFIL.id_adresse_fremdware.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_fremdware),					false);
        this.addSearchDefAdresse(DLP_PROFIL.id_adresse_buchungslager.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_buchungslager),			false);
        this.addSearchDefAdresse(DLP_PROFIL.id_adresse_rechnung.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_rechnung),					false);
        
        this.addSearchDef(DLP_PROFIL.umrech_mge_quelle_ziel.fn(),DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.umrech_mge_quelle_ziel),	false);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_DIENSTLEISTUNG_PROFIL  FROM "+bibE2.cTO()+"."+_TAB.dlp_profil.n()+" WHERE UPPER(TO_CHAR("+_TAB.dlp_profil.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_DIENSTLEISTUNG_PROFIL  FROM "+bibE2.cTO()+"."+_TAB.dlp_profil.n()+" WHERE UPPER(TO_CHAR("+_TAB.dlp_profil.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
    
    
    private void addSearchDefAdresse(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
       String cSearch = "SELECT ID_DIENSTLEISTUNG_PROFIL  FROM "+bibE2.cTO()+"."+_TAB.dlp_profil.n()+" WHERE "+_TAB.dlp_profil.n()+"."+cFieldName;
       
       SEL s_in = new SEL(ADRESSE.id_adresse).FROM(_TAB.adresse).WHERE(new Or(new vgl_like(ADRESSE.name1, "#WERT#"))
    		   																.OR(new vgl_like(ADRESSE.name2, "#WERT#"))
    		   																.OR(new vgl_like(ADRESSE.strasse, "#WERT#"))
    		   																.OR(new vgl_like(ADRESSE.ort, "#WERT#"))
    		   																.OR(new TermSimple("TO_CHAR(ID_ADRESSE)=#WERT#"))
    		   																.OR(new TermSimple("TO_CHAR(ID_ADRESSE,'99G999', 'NLS_NUMERIC_CHARACTERS = '',.')='#WERT#'"))
    		   																).AND(  new Or(new vgl(ADRESSE.adresstyp, "1")).OR(new vgl(ADRESSE.adresstyp, "5")));

       cSearch = cSearch+ " IN ("+s_in.s()+")";
    	
    }
        
}
 
 
