 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2_RECLIST_M2N;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR_CONST.RR_BUTTONS;

public class RR_LIST_bt_multiDelete extends MyE2_Button
{
	
	private E2_NavigationList navigationList = null; 

    public RR_LIST_bt_multiDelete(E2_NavigationList p_navigationList)    {
        super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
        this.setToolTipText(new MyE2_String("Löschen einer Reportgruppe").CTrans());
        this.navigationList = p_navigationList;
        
        this.add_oActionAgent(new ownActionAgent(p_navigationList,this));
        this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(RR_BUTTONS.DELETE.db_val()));
        
        this.add_GlobalValidator(new ownValidator_is_some_report_connected());
        this.add_GlobalValidator(new ownValidator_is_something_selected());
    }
    
    private class ownActionAgent extends ButtonActionAgentMULTIDELETE
    {
        public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
        {
            super(new MyE2_String("Löschen einer Reportgruppe"), onavigationList);
        }
        
        /*
         * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
         */
        public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)              {return  new Vector<String>();}
        public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated)             {return  new Vector<String>();}
        public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException     {return  new MyE2_MessageVector();}
        public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
        public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
    }
      
    private class ownValidator_is_something_selected extends XX_ActionValidator {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException {
			Vector<String>  v_selected_ids = RR_LIST_bt_multiDelete.this.navigationList.get_vSelectedIDs_Unformated();
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (v_selected_ids.isEmpty()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst etwas zum Löschen auswählen !")));
			}
			return mv;
		}
		
		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
			return null;
		}
    	
    }
       
    private class ownValidator_is_some_report_connected extends XX_ActionValidator {
 		@Override
 		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) 	throws myException {
 			Vector<String>  v_selected_ids = RR_LIST_bt_multiDelete.this.navigationList.get_vSelectedIDs_Unformated();
 			
 			MyE2_MessageVector mv = new MyE2_MessageVector();
 			
 			for (String id: v_selected_ids) {
 				AW2_RECLIST_M2N testReclist = new AW2_RECLIST_M2N(id);
 				
 				if (testReclist.get_vKeyValues().size()>0) {
 					RECORD_REPORT_REITER  rrr = new RECORD_REPORT_REITER(id);
 					mv.add_MESSAGE(new MyE2_Alarm_Message(
 							new MyE2_String("Achtung! Der Report-Reiter ",true,
 											rrr.get_REITERNAME_cF_NN("<-->"),false,
 											" enthält Reports, bitte zuerst löschen !",true)));
 				}
 				
 			}
 			
 			
 			return mv;
 		}
 		
 		@Override
 		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
 			return null;
 		}
     	
     }

    
    
}
 
