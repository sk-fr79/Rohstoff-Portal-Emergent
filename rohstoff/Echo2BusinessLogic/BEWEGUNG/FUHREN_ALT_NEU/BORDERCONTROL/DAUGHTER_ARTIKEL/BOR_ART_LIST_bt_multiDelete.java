 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
  
public class BOR_ART_LIST_bt_multiDelete extends E2_Button {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;

    
    public BOR_ART_LIST_bt_multiDelete(E2_NavigationList onavigationList ,PARAMHASH  p_params)    {
        super();
        this._image(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
        
        this.params= p_params;        
        
        
        this.add_oActionAgent(new ownActionAgent(onavigationList,this));
        this.add_GlobalValidator(ENUM_VALIDATION.BORDERCROSSING_ARTIKEL_DELETE.getValidator());
        
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,BORDERCROSSING_ARTIKEL("BORDERCROSSING_ARTIKEL-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,BORDERCROSSING_ARTIKEL_EDIT( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,BORDERCROSSING_ARTIKEL_VIEW( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,BORDERCROSSING_ARTIKEL_NEW( 		ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.NEW)
			,BORDERCROSSING_ARTIKEL_DELETE( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
        
    }
    
    private class ownActionAgent extends ButtonActionAgentMULTIDELETE
    {
        public ownActionAgent(E2_NavigationList onavigationList,  E2_Button oownButton)
        {
            super(new MyE2_String(onavigationList.get_vSelectedIDs_Unformated().size()>1
            			?
            			"Löschen von "+onavigationList.get_vSelectedIDs_Unformated().size()+" Datensätzen vom Typ: Verwalten der Bordercrossing-Artikel ?"
            			:
            			"Loeschen eines Datensatzes vom Typ: Verwalten der Bordercrossing-Artikel ?"), onavigationList);
        }
        
        /*
         * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
         */
        public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)              {return  new Vector<String>();}
        public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated)             {return  new Vector<String>();}
        public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException     {return  new MyE2_MessageVector();}
        
        public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {
        	this.get_navigationList()._REBUILD_ACTUAL_SITE("");
        	
        }
        
        public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
    }
    
    
    
}
 
