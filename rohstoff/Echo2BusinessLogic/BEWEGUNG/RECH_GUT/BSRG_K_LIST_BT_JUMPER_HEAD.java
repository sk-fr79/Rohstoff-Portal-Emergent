package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Border;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BSRG_K_LIST_BT_JUMPER_HEAD extends MyE2_PopUpMenue
{
	
	private E2_ComponentMAP    oNaviListRef_CompMap = null;

	
	public BSRG_K_LIST_BT_JUMPER_HEAD(E2_ComponentMAP  NaviListRef_CompMap) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_popup.png"), E2_ResourceIcon.get_RI("kompass_popup.png"),false);
		
		this.oNaviListRef_CompMap = NaviListRef_CompMap;

		this.setBackground(new E2_ColorDark());
		this.setToggleRolloverBackground(new E2_ColorDark());
		this.setBorder(new Border(0, new E2_ColorDark(), Border.STYLE_NONE));
		this.setToggleBorder(new Border(0, new E2_ColorDark(), Border.STYLE_NONE));

		
		this.addButton(new ownJumpButtonToFibu(), true);
		this.addButton(new ownJumpButtonToFuhren(), true);
		
		this.setToolTipText(new MyE2_String("Sprungfunktionen in verschiedene Module ...").CTrans());
	}


	
	private class ownJumpButtonToFibu extends MyE2_Button
	{
		public ownJumpButtonToFibu() throws myException
		{
			super(new MyE2_String("Sprungziel: Fibu / Rechnung-/Gutschrift-Ausgänge"));
			this.add_oActionAgent(new ownActionJumpToFibu());
		}
	}

	
	private class ownActionJumpToFibu extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJumpToFibu()	throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FIBU_LIST, "Fibu-Einträge");
			this.set_bRefuseJumpWhenNoIDs(true);
		}

		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST() throws myException
		{
			Vector<String> vIDs_VKOPF_RG = BSRG_K_LIST_BT_JUMPER_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vIDs_VKOPF_RG.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie die gewünschten Belege !!")));
			}
			return oMV;
		}

		
		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs_VKOPF_RG = BSRG_K_LIST_BT_JUMPER_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();

			VectorSingle  vID_FIBU = new VectorSingle();
			for (String cID_VKOPF_RG: vIDs_VKOPF_RG)
			{
				vID_FIBU.addAll(bibVECTOR.get_VectorFromSet(new RECORD_VKOPF_RG(cID_VKOPF_RG).get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get_ID_FIBU_hmString_UnFormated("").keySet()));
			}

			return vID_FIBU;
		}
		
	}
	
	
	

	private class ownJumpButtonToFuhren extends MyE2_Button
	{
		public ownJumpButtonToFuhren() throws myException
		{
			super(new MyE2_String("Sprungziel: Fuhrenzentrale"));
			this.add_oActionAgent(new ownActionJumpToFuhren());
		}
	}

	
	private class ownActionJumpToFuhren extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJumpToFuhren()	throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
			this.set_bRefuseJumpWhenNoIDs(true);
		}

		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST() throws myException
		{
			Vector<String> vIDs_VKOPF_RG = BSRG_K_LIST_BT_JUMPER_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vIDs_VKOPF_RG.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie die gewünschten Belege !!")));
			}
			return oMV;
		}


		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs_VKOPF_RG = BSRG_K_LIST_BT_JUMPER_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();
			VectorSingle vIDFuhren = new VectorSingle();
			
			for (String cID_VKOPF_RG: vIDs_VKOPF_RG)
			{
				RECLIST_VPOS_RG  reclistRGs = new RECORD_VKOPF_RG(cID_VKOPF_RG).get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
				
				for(int i=0;i<reclistRGs.get_vKeyValues().size();i++)
				{
					if (S.isFull(reclistRGs.get(i).get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF()))
					{
						vIDFuhren.add(reclistRGs.get(i).get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN("-1"));
					}
				}
			}
			
			return vIDFuhren;
		}
		
	}
	

	

	
}
