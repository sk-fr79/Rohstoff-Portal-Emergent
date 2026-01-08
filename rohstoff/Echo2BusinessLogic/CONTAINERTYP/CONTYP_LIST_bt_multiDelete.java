 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import java.util.Vector;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_CONST.CONTYP_BUTTONS;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
public class CONTYP_LIST_bt_multiDelete extends E2_Button {
    public CONTYP_LIST_bt_multiDelete(E2_NavigationList onavigationList)    {
        super();
        this._image(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
        
        this.add_oActionAgent(new ownActionAgent(onavigationList,this));
        this.add_GlobalValidator(ENUM_VALIDATION.CONTAINERTYP_DELETE.getValidator());
        
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,CONTAINERTYP("CONTAINERTYP-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,CONTAINERTYP_EDIT( 	ENUM_VALID_THEME.CONTAINERTYP, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,CONTAINERTYP_VIEW( 	ENUM_VALID_THEME.CONTAINERTYP, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,CONTAINERTYP_NEW( 		ENUM_VALID_THEME.CONTAINERTYP, 	ENUM_VALID_BASICFUNCTION.NEW)
			,CONTAINERTYP_DELETE( 	ENUM_VALID_THEME.CONTAINERTYP, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
        
    }
    
    private class ownActionAgent extends ButtonActionAgentMULTIDELETE
    {
        public ownActionAgent(E2_NavigationList onavigationList,  E2_Button oownButton)
        {
            super(new MyE2_String("Loeschen von Containtertypen"), onavigationList);
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
 
