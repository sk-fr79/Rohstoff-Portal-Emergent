package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentSingleDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;


public class FS_BT_LIST_DELETE_ADRESS extends MyE2_Button
{

	public FS_BT_LIST_DELETE_ADRESS(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"LOESCHE_ADRESSE"));

	}
	
	private class ownActionAgent extends ButtonActionAgentSingleDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Löschen einer Adresse"), onavigationList);
		}
		
		
		/*
		 * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
		 */
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_BASIS="+cID_toDeleteUnformated);
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+cID_toDeleteUnformated);
			
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] ID_Mitarb = oDB.EinzelAbfrageInArray("SELECT ID_ADRESSE_MITARBEITER FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_BASIS="+cID_toDeleteUnformated);
			String[][] ID_Liefer = oDB.EinzelAbfrageInArray("SELECT ID_ADRESSE_LIEFER FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+cID_toDeleteUnformated);
			for (int i=0;i<ID_Mitarb.length;i++)
			{
				vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP= "+myCONST.ADRESSTYP_MITARBEITER+" AND  ID_ADRESSE="+ID_Mitarb[i][0]);
			}
			for (int i=0;i<ID_Liefer.length;i++)
			{
				vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP= "+myCONST.ADRESSTYP_LIEFERADRESSE+" AND  ID_ADRESSE="+ID_Liefer[i][0]);
			}
			return vRueck;
		}


		public MyE2_MessageVector CheckIdToDelete(String cID_UnformatedToDelete) {	return null;}
		public void Execute_After_DELETE(String cID_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(String cID_toDeleteUnformated) throws myException {}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) {return  new Vector<String>();}

	}
	

	

	
	
}
