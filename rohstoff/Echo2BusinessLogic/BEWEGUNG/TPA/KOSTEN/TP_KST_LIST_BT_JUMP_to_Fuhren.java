package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class TP_KST_LIST_BT_JUMP_to_Fuhren extends MyE2_ButtonInLIST
{

	public TP_KST_LIST_BT_JUMP_to_Fuhren() throws myException
	{
		super(	E2_ResourceIcon.get_RI("kompass_fuhre.png"), 
				new MyE2_String("Springe zu der Fuhre, die zu dieser TPA-Position gehört"));
		
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
			TP_KST_LIST_BT_JUMP_to_Fuhren oThis = TP_KST_LIST_BT_JUMP_to_Fuhren.this;
			String id_VPOS_TPA_FUHRE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(TP_KST__CONST.SQLFIELDMAP_FIELDS.ID_FUHRE.name());
			return bibVECTOR.get_Vector(id_VPOS_TPA_FUHRE);
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			TP_KST_LIST_BT_JUMP_to_Fuhren oButton = new TP_KST_LIST_BT_JUMP_to_Fuhren();
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
