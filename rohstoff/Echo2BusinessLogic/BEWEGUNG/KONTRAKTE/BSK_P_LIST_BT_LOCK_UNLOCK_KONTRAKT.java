package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT extends MyE2_Button
{

	public BSK_P_LIST_BT_LOCK_UNLOCK_KONTRAKT(	E2_NavigationList 		onavigationList,
												BS__SETTING 			oSETTING) throws myException
	{
		super(E2_ResourceIcon.get_RI("kontrakt_sperren_entsperren_22.png"),true);

		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_ARTIKELPOS_MENGE_PREIS_NOT_LEER);

		this.add_oActionAgent(new ownActionAgent(onavigationList));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oSETTING.get_cMODULCONTAINER_MASK_IDENTIFIER(),"LOCK_UNLOCK_KONTRAKTPOS"));
		this.setToolTipText(new MyE2_String("Öffnen/Schliessen eines (oder mehrerer) Kontraktpositionen").CTrans());
	}
	
	
	
	
	private class ownActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{
		public ownActionAgent(E2_NavigationList onavigationList) throws myException
		{
			super(new MyE2_String("Öffnen/Schliessen Kontrakt-Position"),
					onavigationList, 
					"ABGESCHLOSSEN",
					"JT_VPOS_KON_TRAKT",
					"ID_VPOS_KON_TRAKT");
			
			this.set_oAddonDialogBuilder(new ____ADD_ON_DIALOG_BUILDER_Benachrichtige()
			{
				@Override
				public void fill_v_ID_VPOS_KON_ToToggle() throws myException
				{
					this.get_vIDsToToggle().removeAllElements();
					this.get_vIDsToToggle().addAll(ownActionAgent.this.get_oNavigationList().get_vSelectedIDs_Unformated());
				}
			});
		}
		
		
		/*
		 * macht aus der ID_VPOS_KON eine ID_VPOS_KON_TRAKT
		 */
		public String Create_ID_FOR_UpdateStatement(String cID_AUS_LISTE) throws myException
		{
			String cRueck = null;
			
			cRueck = bibDB.EinzelAbfrage("SELECT ID_VPOS_KON_TRAKT FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE ID_VPOS_KON="+cID_AUS_LISTE);
			
			if (! bibALL.isInteger(cRueck))
				throw new myException("BSK_P_BT_LIST_LOCK_UNLOCK_EK_KONTRAKT:Create_ID_FOR_UpdateStatement:Error creating ID_VPOS_KON_TRAKT");
			
			return cRueck;
		}


		public MyE2_MessageVector CheckIdToToggle(Vector<String> vID_UnformatedToDelete){return null;}
		public void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException {}
		public void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated) throws myException {}
		public Vector<String> get_vSQL_After_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}
		public Vector<String> get_vSQL_Before_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException {return null;}

	}	

	
	
}
