/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class BT_MASK_Unlock_BAMF extends MyE2_Button
{

	private BAMF_MASK_ModulContainer BAMF_ContainerMASK = null;
	
	
	public BT_MASK_Unlock_BAMF(BAMF_MASK_ModulContainer oContainerMASK)
	{
		super(E2_ResourceIcon.get_RI("unlock_BAM.png"));
		this.BAMF_ContainerMASK = oContainerMASK;
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oContainerMASK.get_MODUL_IDENTIFIER(),"UNLOCK_BAM"));
		this.add_oActionAgent(new ownActionAgent(oContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN()));
		
		this.add_GlobalValidator(new ownValidator());
		
		this.setToolTipText(new MyE2_String("Beanstandungsmeldungsrelevanter Maskenbereich entsperren ").CTrans());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			BT_MASK_Unlock_BAMF oThis = BT_MASK_Unlock_BAMF.this;
			BAMF_MASK_ComponentMAP     oMap = (BAMF_MASK_ComponentMAP)oThis.BAMF_ContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			
			if (oMap.get_oInternalSQLResultMAP()==null)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Entsperren bei Neueingabe ist nicht sinnvoll !"));
				return oMV;
			}

			BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW recFuhre = oMap.get_recFuhreAusView();
			
			//RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(""+oMap.get_oInternalSQLResultMAP().get_LActualDBValue("ID_VPOS_TPA_FUHRE", true));
			if (recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add(new MyE2_Alarm_Message("Die zugrundeliegende Fuhre wurde bereits storniert oder gelöscht !"));
				return oMV;
			}
			if (recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT) ||
				recFuhre.get_STATUS_BUCHUNG_cUF_NN("-").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
			{
				oMV.add(new MyE2_Alarm_Message("Die zugrundeliegende Fuhre hat bereits Abrechnungspositionen !"));
				return oMV;
			}

			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String unformated)  	throws myException
		{
			return null;
		}
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP oComponentMAP = null;
		public ownActionAgent(E2_ComponentMAP componentMAP)
		{
			super();
			this.oComponentMAP = componentMAP;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			this.oComponentMAP.set_DisabledFromInteractive(BAMF__CONST.FBAM_LOCKLIST_BAM,":",false);
			try
			{
				this.oComponentMAP.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_UNDEFINED);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Felder sind wieder offen !"));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Entsperren !"));
			}
		}
		
	}
}