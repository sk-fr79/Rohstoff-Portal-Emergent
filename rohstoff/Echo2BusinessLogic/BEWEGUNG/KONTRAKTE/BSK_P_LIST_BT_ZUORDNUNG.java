package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


public class BSK_P_LIST_BT_ZUORDNUNG extends MyE2_Button
{
	
	private E2_NavigationList  oNaviList = null;
	
	public BSK_P_LIST_BT_ZUORDNUNG(	E2_NavigationList 				onavigationList,   
									BSK_K_MASK__ModulContainer 		oKopfMaskContainer)
	{
		super(E2_ResourceIcon.get_RI("connect.png") , true);
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oKopfMaskContainer.get_MODUL_IDENTIFIER(),"ZUORDNUNG_EK_VK_KONTRAKTE"));
		//this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_POSITION_OFFEN);
		this.setToolTipText(new MyE2_String("Zuordnung Kontrakte").CTrans());
		this.oNaviList = onavigationList;
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		private E2_NavigationList 			o_NaviList = null;
		
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super();
			this.o_NaviList = onavigationList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			
			Vector<String> vIDs_Selected = this.o_NaviList.get_vSelectedIDs_Unformated();
			if (vIDs_Selected.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau eine Kontrakt-Position auswählen !"));
			}
			else
			{
				try
				{
					bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vIDs_Selected);
					if (bibMSG.get_bIsOK())
					{
						String cID_VPOS_SELECTED = (String)vIDs_Selected.get(0);
						new own_ZUORDNUNG_EK_VK(cID_VPOS_SELECTED);
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("BSK_P_BT_LIST_ZUORDNUNG:doAction:",false));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
					
			}
			
		}
		
		
		private class own_ZUORDNUNG_EK_VK extends BSK__ZUORDNUNG_EK_VK
		{

			public own_ZUORDNUNG_EK_VK(String ID_VPOS_KON) throws myException
			{
				super(ID_VPOS_KON);
			}

			@Override
			public void doAfterSave() throws myException
			{
				BSK_P_LIST_BT_ZUORDNUNG.this.oNaviList._REBUILD_ACTUAL_SITE("");
			}
			
		}
		

		
		
	}
}
