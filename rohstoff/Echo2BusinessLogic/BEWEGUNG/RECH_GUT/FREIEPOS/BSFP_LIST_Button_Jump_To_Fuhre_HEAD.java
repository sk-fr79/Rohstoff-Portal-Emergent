package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;


/*
 * sprung-button fuer listenheader, springt alle markierten an
 */
public class BSFP_LIST_Button_Jump_To_Fuhre_HEAD extends MyE2_Button
{
 
	private E2_ComponentMAP    oNaviListRef_CompMap = null;
	
	
	public BSFP_LIST_Button_Jump_To_Fuhre_HEAD(E2_ComponentMAP  NaviListRef_CompMap) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));
		
		this.oNaviListRef_CompMap = NaviListRef_CompMap;
		
		this.add_oActionAgent(new ownActionJump());
		
		this.setToolTipText(new MyE2_String("Zeigt die Fuhren der in der Liste gewählten freien Positionen an ...").CTrans());
	}

	
	
	
	private class ownActionJump extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJump() 	throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
			this.set_bRefuseJumpWhenNoIDs(true);
		}

		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST() throws myException
		{
			Vector<String> vIDs_VPOS_RG = BSFP_LIST_Button_Jump_To_Fuhre_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vIDs_VPOS_RG.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie die gewünschten RG/Positionen !!")));
			}
			return oMV;
		}


		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs_VPOS_RG = BSFP_LIST_Button_Jump_To_Fuhre_HEAD.this.oNaviListRef_CompMap.get_oNavigationList_This_Belongs_to().get_vSelectedIDs_Unformated();
			
			VectorSingle vIDs_Fuhren = new VectorSingle();
			
			for (String cID_RG_POS: vIDs_VPOS_RG)
			{
				RECORD_VPOS_RG  recRG = new RECORD_VPOS_RG(cID_RG_POS);
				if (recRG.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord()!=null)
				{
					vIDs_Fuhren.add(recRG.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord().get_ID_VPOS_TPA_FUHRE_cUF());
				}
			}
			
			return vIDs_Fuhren;
		}
		
	}
	
	
	
}
