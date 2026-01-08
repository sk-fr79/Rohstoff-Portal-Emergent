package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMultiSQLStatement;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class BS_K_BT_LIST_UNLOCK_VORGANG extends MyE2_Button
{
	
	private String        cVKOPF_TABLE = null;
	private String        cVKOPF_ID  = null;

	public BS_K_BT_LIST_UNLOCK_VORGANG(		E2_NavigationList 	onavigationList,
											BS__SETTING 		oSETTING,
											MyString 			cLabelButton,
											MyString      		cToolTipText,
											String     			cIDENTIFIER_BUTTON
											
											) throws myException
	{
		super(cLabelButton);
		this.cVKOPF_TABLE = oSETTING.get_oVorgangTableNames().get_cVKOPF_TAB();
		this.cVKOPF_ID = oSETTING.get_oVorgangTableNames().get_cVKOPF_PK();
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	this.cVKOPF_TABLE ,
															"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
															bibALL.get_Array("Y","N"),
															true, new MyE2_String("Nur erlaubt bei Belegen, die abgeschlossen und noch nicht geloescht sind !")));
		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oSETTING.get_cMODULCONTAINER_LIST_IDENTIFIER(),cIDENTIFIER_BUTTON));
		this.setToolTipText(cToolTipText.CTrans());
	}
	
	private class ownActionAgent extends ButtonActionAgentMultiSQLStatement
	{
		public ownActionAgent(E2_NavigationList onavigationList) throws myException
		{
			super(new MyE2_String("Beleg Entsperren"),
					"UPDATE "+bibE2.cTO()+"."+
					cVKOPF_TABLE+
					" SET ABGESCHLOSSEN='N' WHERE " +cVKOPF_ID+ "=#ID#",
					onavigationList);
			
			// weiteres statement dazu
			this.ADD_Statement("UPDATE "+bibE2.cTO()+"."+cVKOPF_TABLE+" SET ZAEHLER_ENTSPERRUNG=  NVL(ZAEHLER_ENTSPERRUNG,0)+1 WHERE " +
									cVKOPF_ID+"=#ID#");
			
		}
		public MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete) throws myException {return null;}
	}
	
}
