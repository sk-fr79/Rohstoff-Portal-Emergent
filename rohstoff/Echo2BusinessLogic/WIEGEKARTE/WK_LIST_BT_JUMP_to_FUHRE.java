package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

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
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;


/**
 * 
 * @author martin
 * 2012-05-07: sprungfunktion aus wiegekarten zur fuhre
 */
public class WK_LIST_BT_JUMP_to_FUHRE extends MyE2_ButtonInLIST
{

	private boolean bSprungIstMoeglich = true;
	
	
	
	public WK_LIST_BT_JUMP_to_FUHRE()
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"),E2_ResourceIcon.get_RI("kompass_fuhre__.png"));
		
		this.setToolTipText(new MyE2_String("Sprung zur zugehörigen Fuhre (wenn vorhanden !!)").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WK_LIST_BT_JUMP_to_FUHRE oWK = WK_LIST_BT_JUMP_to_FUHRE.this;
			
			String cID_WIEGEKARTE = oWK.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (S.isFull(cID_WIEGEKARTE))
			{
				RECORD_WIEGEKARTE  recWK = new RECORD_WIEGEKARTE(cID_WIEGEKARTE);
				
				if (S.isFull(recWK.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
				{
					new BaseJumper(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentale", bibVECTOR.get_Vector(recWK.get_ID_VPOS_TPA_FUHRE_cUF_NN("")), true, null);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Diese Wiegung wurde keiner Fuhre zugeteilt !!")));
				}
			}
			
		}
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		WK_LIST_BT_JUMP_to_FUHRE oButton = new WK_LIST_BT_JUMP_to_FUHRE();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}
	
	
	/*
	 * hier spezieller setter
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		
		boolean enabled = this.bSprungIstMoeglich;
		
		this.setEnabled(enabled);
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (enabled)
				this.setIcon(this.get_oImgEnabled());
			else
				this.setIcon(this.get_oImgDisabled());
		}
			
	}


	public void set_bSprungIstMoeglich(boolean bSprungIstMoeglich)
	{
		this.bSprungIstMoeglich = bSprungIstMoeglich;
	}

	
	
}
