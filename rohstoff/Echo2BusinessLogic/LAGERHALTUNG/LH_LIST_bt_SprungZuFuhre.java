/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 25.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 25.01.2019
 *
 */
public class LH_LIST_bt_SprungZuFuhre extends MyE2_ButtonInLIST {
	

	private String 				id_fuhre;
	
	public LH_LIST_bt_SprungZuFuhre(String p_id_fuhre) throws myException {

		super(E2_ResourceIcon.get_RI("kompass_mini.png"));

		this.id_fuhre = p_id_fuhre;
		this.setToolTipText(new MyE2_String("Sprung zu Fuhre.").CTrans());

		this.add_oActionAgent(new ownJumpAction());
	}

	private class ownJumpAction extends XX_ActionAgentJumpToTargetList
	{

		public ownJumpAction() throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			LH_LIST_bt_SprungZuFuhre othis = LH_LIST_bt_SprungZuFuhre.this;
			return new VEK<String>()._a(othis.id_fuhre);
		}

		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine passende Fuhre für den Sprung gefunden ...")));
			}
			return oMV;
		}
	}
}