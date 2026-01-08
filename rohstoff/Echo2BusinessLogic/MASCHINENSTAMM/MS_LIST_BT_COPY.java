package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW_FROM_COPY;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MS_LIST_BT_COPY extends MyE2_Button{
	public MS_LIST_BT_COPY(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("copy.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("COPY_MS"));

	}
	
	private class ownActionAgent extends ButtonActionAgentNEW_FROM_COPY
	{
		
		
		private MyString cMessageStartingNew = 		new MyE2_String("Erfassen eines KOPIERTEN Datensatzes ...");
		
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Kopiere... "), onavigationList, omaskContainer, oownButton, null, null);
		}
		

		public void do_innerAction() throws myException
		{
			/*
			 * Maske einstellen
			 */
			Vector<String> vIDs_Selected = this.get_oNavigationList().get_vSelectedIDs_Unformated();
			if (vIDs_Selected.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bei einer Kopie muss exakt EIN Datensatz ausgewählt werden !"));
				return;
			}
			
			E2_vCombinedComponentMAPs vComponentMAPs = this.get_oMaskContainer().get_vCombinedComponentMAPs();
			vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_COPY,vIDs_Selected.get(0));
			
			// jetzt die Aufgaben aus der Maske entfernen
			MS_MASK_ComponentMAP oMap = (MS_MASK_ComponentMAP)vComponentMAPs.get_oE2_ComponentMAP_MAIN();
			
			MS__MASK_FullDaughterAufgaben oAufgaben = (MS__MASK_FullDaughterAufgaben)oMap.get(MS__CONST.HASHKEY_MASK_INLAY_AUFGABEN);
			oAufgaben.prepare_DaughterContentForNew();
			
			MyE2_DB_Label oLblId = (MyE2_DB_Label)oMap.get__Comp("ID_MASCHINEN");
			oLblId.setText("-");
			
			
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(this.cMessageStartingNew.CTrans()));
		}
		
	}

}
