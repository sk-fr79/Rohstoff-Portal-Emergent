package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTENREGEL_NEU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_OR;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_LIST_BT_DELETE extends MyE2_Button {
	public FKR_FB_LIST_BT_DELETE(E2_NavigationList onavigationList) {
		super(E2_ResourceIcon.get_RI("delete.png"), E2_ResourceIcon
				.get_RI("leer.png"));

		// this.navigationList = onavigationList;

		this.add_oActionAgent(new DeleteWholeRuleAgent(onavigationList, this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(
				"LOESCHE_FKR_FB"));
	}

	private class DeleteWholeRuleAgent extends ButtonActionAgentMULTIDELETE {
		public DeleteWholeRuleAgent(E2_NavigationList onavigationList,
				MyE2_Button oownButton) {
			super(new MyE2_String("Löschen von Kontenregeln"), onavigationList);
		}

		public Vector<String> get_vSQL_Before_DELETE(
				String cID_toDeleteUnformated) {
			return new Vector<String>();
		}

		public Vector<String> get_vSQL_After_DELETE(
				String cID_toDeleteUnformated) {
			return new Vector<String>();
		}

		public MyE2_MessageVector CheckIdToDelete(
				Vector<String> vID_UnformatedToDelete) throws myException {
			return new MyE2_MessageVector();
		}

		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated)
				throws myException {

		}
		
		/** 
		 * Deletes the "Kontenregel-Filter" by descending into the Filter and it's 
		 * conditions and deleting all "child" touples in JT_FILTER_OR, JT_FILTER_AND 
		 * and JT_FIBU_KONTENREGEL_NEU, before deleting the record in JT_FILTER itself.
		 */
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated)
				throws myException {
			// All "Kontenregel-IDs" to be deleted
			for (String idFilter : vIDs_toDeleteUnformated) {
				//				System.out.println("Attempt to delete Kontenregel:"+idFilter);
				RECORD_FIBU_KONTENREGEL_NEU rfkn = new RECORD_FIBU_KONTENREGEL_NEU(
						"ID_FILTER = "+idFilter);

				RECORD_FILTER rf = new RECORD_FILTER(idFilter);

				RECLIST_FILTER_AND rlfa = new RECLIST_FILTER_AND("ID_FILTER = " + idFilter, "1");
				for (RECORD_FILTER_AND rfa : rlfa.values()) {
					String idfa = rfa.get_ID_FILTER_AND_cUF(); 
					RECLIST_FILTER_OR rlfo = new RECLIST_FILTER_OR("ID_FILTER_AND = " + idfa, "1");
					for (RECORD_FILTER_OR rfo : rlfo.values()) {
						//System.out.print("--> delOR="+rfo.get_ID_FILTER_OR_cUF());
						rfo.DELETE();
					}
					//System.out.print("-> delAnd="+rfa.get_ID_FILTER_AND_cUF());
					rfa.DELETE();
				}
				//System.out.print("> delKR="+rfkn.get_ID_FILTER_cUF());
				rfkn.DELETE();
			}
		}
	}

}
