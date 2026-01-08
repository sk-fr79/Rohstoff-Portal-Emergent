package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BST_K_LIST_BT_JUMP_TO_Fuhren extends MyE2_ButtonInLIST
{

	public BST_K_LIST_BT_JUMP_TO_Fuhren() throws myException
	{
		super(	E2_ResourceIcon.get_RI("kompass_fuhre.png"), 
				new MyE2_String("Springe zu den Fuhren, die diesem Transportauftrag zugehören"));
		
		this.add_oActionAgent(new ownActionJump());
	}

	
	private class ownActionJump extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJump() throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"Fuhre");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			BST_K_LIST_BT_JUMP_TO_Fuhren oThis = BST_K_LIST_BT_JUMP_TO_Fuhren.this;
			String cID_VPOS_TPA_FUHRE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			VectorSingle  vID_VPOS_TPA_FUHRE = new VectorSingle();

			vID_VPOS_TPA_FUHRE.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray("SELECT DISTINCT FU.ID_VPOS_TPA_FUHRE FROM "+
											bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU " +
											" WHERE FU.ID_VPOS_TPA IN" +
											" (SELECT TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA TPA WHERE TPA.ID_VKOPF_TPA="+cID_VPOS_TPA_FUHRE+" )")));
			
			return vID_VPOS_TPA_FUHRE;
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BST_K_LIST_BT_JUMP_TO_Fuhren oButton = new BST_K_LIST_BT_JUMP_TO_Fuhren();
			return oButton;
		}
		catch (myException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
		
	}
	
}
