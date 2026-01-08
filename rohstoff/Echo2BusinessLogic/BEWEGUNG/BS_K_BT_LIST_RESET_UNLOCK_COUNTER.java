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
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class BS_K_BT_LIST_RESET_UNLOCK_COUNTER extends MyE2_Button
{
	private String        cVKOPF_TABLE = null;
	private String        cVKOPF_ID  = null;

	public BS_K_BT_LIST_RESET_UNLOCK_COUNTER(	E2_NavigationList 	onavigationList,
												BS__SETTING oSETTING) throws myException
	{
		super(new MyE2_String("Zurücksetzen Entsperrzähler"));
		
		this.cVKOPF_TABLE = oSETTING.get_oVorgangTableNames().get_cVKOPF_TAB();
		this.cVKOPF_ID = oSETTING.get_oVorgangTableNames().get_cVKOPF_PK();

		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	this.cVKOPF_TABLE,
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Nur erlaubt bei Belegen, die NICHT abgeschlossen und NICHT geloescht sind !")));

		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oSETTING.get_cMODULCONTAINER_LIST_IDENTIFIER(),"RESET_UNLOCK_COUNTER"));
		this.setToolTipText(new MyE2_String("Zurücksetzen des Entsperrzählers eines Vorgangs").CTrans());

	}
	
	private class ownActionAgent extends ButtonActionAgentMultiSQLStatement
	{
		public ownActionAgent(E2_NavigationList onavigationList) throws myException
		{
			super(new MyE2_String("Entsperrzähler zurücksetzen"),
					"UPDATE "+bibE2.cTO()+"."+cVKOPF_TABLE+" SET ZAEHLER_ENTSPERRUNG=NULL  WHERE "+cVKOPF_ID+"=#ID#",
					onavigationList);
		}
		public MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete) throws myException {return null;}
	}
	
}
