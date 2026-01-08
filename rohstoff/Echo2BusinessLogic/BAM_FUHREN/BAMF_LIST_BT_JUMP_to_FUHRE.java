package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.BaseJumper;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;


/**
 * 
 * @author martin
 * 2012-05-07: sprungfunktion aus wiegekarten zur fuhre
 */
public class BAMF_LIST_BT_JUMP_to_FUHRE extends MyE2_ButtonInLIST
{

	
	public BAMF_LIST_BT_JUMP_to_FUHRE()
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"),E2_ResourceIcon.get_RI("kompass_fuhre__.png"));
		
		this.setToolTipText(new MyE2_String("Sprung zur zugehörigen Fuhre in der Fuhrenzentrale").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BAMF_LIST_BT_JUMP_to_FUHRE oJump = BAMF_LIST_BT_JUMP_to_FUHRE.this;
			
			String cID_FBAM = oJump.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (S.isFull(cID_FBAM))
			{
				RECORD_FBAM  recFB = new RECORD_FBAM(cID_FBAM);
				
				if (S.isFull(recFB.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
				{
					new BaseJumper(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentale", bibVECTOR.get_Vector(recFB.get_ID_VPOS_TPA_FUHRE_cUF_NN("")), true, null);
				}
				else if (S.isFull(recFB.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("")))
				{
					new BaseJumper(	E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentale", bibVECTOR.get_Vector(
									recFB.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort().get_ID_VPOS_TPA_FUHRE_cUF()), true, null);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Diese Beanstandungsmeldung wurde keiner Fuhre zugeteilt !!")));
				}
			}
			
		}
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BAMF_LIST_BT_JUMP_to_FUHRE oButton = new BAMF_LIST_BT_JUMP_to_FUHRE();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}

	
	
}
