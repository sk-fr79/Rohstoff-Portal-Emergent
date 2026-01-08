package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK__ZUORDNUNG_EK_VK;

public class BSKC_BT_ZUORDNUNG extends MyE2_Button
{
	
	private String 												cID_VPOS_KON = 						null;
	private BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt 	oListExpanderAusVertragsclearing = 	null;
	
	public BSKC_BT_ZUORDNUNG()
	{
		super(E2_ResourceIcon.get_RI("connect_small.png") , true);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ZUORDNUNG_EK_VK_KONTRAKTE"));
		//this.add_IDValidator(new ownValidator());
		this.setToolTipText(new MyE2_String("Zuordnung Kontrakte").CTrans());
	}
	

	public BSKC_BT_ZUORDNUNG(String cID_VPOS_KON, BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt 	ListExpanderAusVertragsclearing)
	{
		super(E2_ResourceIcon.get_RI("connect_small.png") , true);
		this.oListExpanderAusVertragsclearing = ListExpanderAusVertragsclearing;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("ZUORDNUNG_EK_VK_KONTRAKTE"));
		//this.add_IDValidator(new ownValidator());
		this.setToolTipText(new MyE2_String("Zuordnung Kontrakte").CTrans());
		this.set_cID_VPOS_KON(cID_VPOS_KON);
	}

	
	//2014-01-31: validierer ausser kraft, da die zuordnungsmaske selbst definiert, ob nur lesend oder schreibbar 
//	private class ownValidator extends XX_ActionValidator
//	{
//		@Override
//		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
//		{
//			return null;
//		}
//
//		@Override
//		protected MyE2_MessageVector isValid(String c_ID_VPOS_KON)		throws myException
//		{
//			MyE2_MessageVector oMV = new MyE2_MessageVector();
//			
//			RECORD_VPOS_KON  recVPOS_KON = new RECORD_VPOS_KON(c_ID_VPOS_KON);
//			
//			if (recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message("Diese Position wurde bereits geschlossen !"));
//			}
//			return oMV;
//		}
//		
//		
//	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BSKC_BT_ZUORDNUNG oButton = new BSKC_BT_ZUORDNUNG();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		
		return oButton;
	}

	public void set_bEnabled_For_Edit(boolean bEnabled)
	{
		// tot
	}
	
	
	public void set_cID_VPOS_KON(String cid_vpos_kon_vk)
	{
		cID_VPOS_KON = cid_vpos_kon_vk;
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		public ownActionAgent()
		{
			super();
		}


		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSKC_BT_ZUORDNUNG oThis = BSKC_BT_ZUORDNUNG.this;
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(bibALL.get_Vector(oThis.cID_VPOS_KON)));

			if (bibMSG.get_bHasAlarms())
			{
				return;
			}
		
			
			if (bibALL.isEmpty(oThis.cID_VPOS_KON) || ! bibALL.isInteger(oThis.cID_VPOS_KON))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BSKC_BT_ZUORDNUNG: ID not initialized !!"));
			}
			else
			{
				new own_ZUORDNUNG_EK_VK(oThis.cID_VPOS_KON);
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
			if (BSKC_BT_ZUORDNUNG.this.oListExpanderAusVertragsclearing != null)
			{
				BSKC_BT_ZUORDNUNG.this.oListExpanderAusVertragsclearing.refreshDaughterComponent();
			}
			else
			{
				if ( S.isEmpty(((BSKC_LIST_ComponentMAP)BSKC_BT_ZUORDNUNG.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_oComponentMAP__REF()).get_cID_VPOS_KON__Gegen()))   //Haupt-Navilist
				{
					BSKC_BT_ZUORDNUNG.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE("");
				}
				else
				{
					BSKC_BT_ZUORDNUNG.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._REBUILD_COMPLETE_LIST("");      // ausklapp-navilist
				}
			}
		}
		
	}

}
