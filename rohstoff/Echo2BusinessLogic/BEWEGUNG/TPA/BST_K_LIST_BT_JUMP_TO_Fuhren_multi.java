package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BST_K_LIST_BT_JUMP_TO_Fuhren_multi extends MyE2_ButtonInLIST  {

	private E2_NavigationList  f_naviList = null;
	
	public BST_K_LIST_BT_JUMP_TO_Fuhren_multi(E2_NavigationList  naviList) throws myException	{
		super(	E2_ResourceIcon.get_RI("kompass_fuhre.png"), 
				new MyE2_String("Springe zu den Fuhren, die zu den selektierten Transportaufträgen gehören"));
		
		this.f_naviList = naviList;
		this.add_GlobalValidator(new ownValidator());
		this.add_oActionAgent(new ownActionJump());
	}


	private class ownValidator extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			BST_K_LIST_BT_JUMP_TO_Fuhren_multi oThis = BST_K_LIST_BT_JUMP_TO_Fuhren_multi.this;
			if (oThis.f_naviList.get_vSelectedIDs_Unformated_Select_the_one_and_only().size()==0) {
				return new MyE2_MessageVector()._add(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens einen Transportauftrag auswählen !")));
			}
			return new MyE2_MessageVector();
		}
		
	}
	
	
	private class ownActionJump extends XX_ActionAgentJumpToTargetList  {

		public ownActionJump() throws myException 	{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"Fuhre");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException {
			
			BST_K_LIST_BT_JUMP_TO_Fuhren_multi oThis = BST_K_LIST_BT_JUMP_TO_Fuhren_multi.this;
			VectorSingle  vID_VPOS_TPA_FUHRE = new VectorSingle();

			Vector<String> v_id_kopf = oThis.f_naviList.get_vSelectedIDs_Unformated_Select_the_one_and_only();
			
			for (String cID_kopf: v_id_kopf) {
				String sql = "SELECT DISTINCT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU " +
							" WHERE "
							+new vgl_YN("FU", VPOS_TPA_FUHRE.deleted, false).s()+" AND "
							+new vgl_YN("FU", VPOS_TPA_FUHRE.ist_storniert, false).s()+" AND "
							+" FU.ID_VPOS_TPA IN" +
							" (SELECT TPA.ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA TPA WHERE TPA.ID_VKOPF_TPA="+cID_kopf+" )";
				vID_VPOS_TPA_FUHRE.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(sql)));
				
				DEBUG.System_println(sql);
				
			}
			return vID_VPOS_TPA_FUHRE;
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		throw new myExceptionCopy("not copy implemented");
	}
	
}
