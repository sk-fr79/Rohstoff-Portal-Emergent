package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;

public class FKR_FB_LIST_BT_NEW extends MyE2_Button
{

//	private E2_NavigationList navigationList = null;
	
	public FKR_FB_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		
//		this.navigationList = onavigationList; 
		
//		this.add_oActionAgent(new ownActionAgentCreateRule());
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_FKR_FB"));
	}
	

	
//	private class ownActionAgentCreateRule extends XX_ActionAgent {
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			
//			//hier wird eine neue Regel und ein neuer filter abgefragt, gespeichert und 
//			//im edit-modus geoeffnet
//			
//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
//			
//			String cBeschreibungRegel = "Test";
//			String cBeschreibungFilter = "TestFilter";
//			
//			String cID_FILTER = 				bibDB.get_NextSequenceValueOfTable(_DB.FILTER);
//			String cID_FIBU_KONTENREGEL_NEU = 	bibDB.get_NextSequenceValueOfTable(_DB.FIBU_KONTENREGEL_NEU);
//			
//			
//			RECORDNEW_FILTER  rn_Filter = new RECORDNEW_FILTER();
//			oMV.add_MESSAGE(rn_Filter.set_NEW_VALUE_BESCHREIBUNG(cBeschreibungFilter));
//			oMV.add_MESSAGE(rn_Filter.set_NEW_VALUE_ID_FILTER(cID_FILTER));
//
//			RECORDNEW_FIBU_KONTENREGEL_NEU   rn_FibuKontenRegel = new RECORDNEW_FIBU_KONTENREGEL_NEU();
//			
//			oMV.add_MESSAGE(rn_FibuKontenRegel.set_NEW_VALUE_ID_FIBU_KONTENREGEL_NEU(cID_FIBU_KONTENREGEL_NEU));
//			oMV.add_MESSAGE(rn_FibuKontenRegel.set_NEW_VALUE_ID_FILTER(cID_FILTER));
//			oMV.add_MESSAGE(rn_FibuKontenRegel.set_NEW_VALUE_KOMMENTAR(cBeschreibungRegel));
//			
//			if (oMV.get_bIsOK()) {
//				Vector<String>  vSQL = new Vector<String>();
//				vSQL.add(rn_Filter.get_InsertSQLStatement(true));
//				vSQL.add(rn_FibuKontenRegel.get_InsertSQLStatement(true));
//				
//				oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
//				
//				if (oMV.get_bIsOK()) {
//					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Neue Kontenregel ist geschrieben ...")));
//					FKR_FB_LIST_BT_NEW.this.navigationList.ADD_NEW_ID_TO_ALL_VECTORS(cID_FIBU_KONTENREGEL_NEU);
//					FKR_FB_LIST_BT_NEW.this.navigationList._REBUILD_ACTUAL_SITE(cID_FIBU_KONTENREGEL_NEU);
//				} else {
//					bibMSG.add_MESSAGE(oMV);
//				}
//			} else {
//				bibMSG.add_MESSAGE(oMV);
//			}
//		}
//		
//	}
	
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Neuerfassung einer Kontenregel/Filter"), onavigationList, omaskContainer, oownButton, null, null);
		}
	}
	
}
