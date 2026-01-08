package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;


/**
 * jump-button fuer die einzelne kontraktposition
 * @author martin
 *
 */
public class KFIX_P_L_JUMPER_TO_Fuhre extends MyE2_ButtonInLIST 
{
	
	private Rec20   recVposKon = null;
	
	public KFIX_P_L_JUMPER_TO_Fuhre(Rec20   RecVposKon) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass.png"));
		
		this.recVposKon=RecVposKon;

		this.setToolTipText(new MyE2_String("Zeigt alle Fuhren an, die zu dieser Kontrakt-Position gehören.").CTrans());

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
			KFIX_P_L_JUMPER_TO_Fuhre oThis = KFIX_P_L_JUMPER_TO_Fuhre.this;
			
			VectorSingle  vID_Fuhren = new VectorSingle();
			
			Vector<String> vMinusEins = new Vector<String>();
			vMinusEins.add("-1");
			
//			RECLIST_VPOS_TPA_FUHRE  	recListFuhreLADE = 		oThis.recVposKon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N'",null,true);
//			RECLIST_VPOS_TPA_FUHRE  	recListFuhreABLADE = 	oThis.recVposKon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N'",null,true);
//			RECLIST_VPOS_TPA_FUHRE_ORT  recListFuhreORT = 		oThis.recVposKon.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N'",null,true);
			
			RecList20 recListFuhreLADE = oThis.recVposKon.get_down_reclist20(VPOS_TPA_FUHRE.id_vpos_kon_ek, "NVL(DELETED,'N')='N'", null);
			RecList20 recListFuhreABLADE = oThis.recVposKon.get_down_reclist20(VPOS_TPA_FUHRE.id_vpos_kon_vk, "NVL(DELETED,'N')='N'", null);
			RecList20 recListFuhreORT = oThis.recVposKon.get_down_reclist20(VPOS_TPA_FUHRE_ORT.id_vpos_kon,"NVL(DELETED,'N')='N'", null);
			
			vID_Fuhren.addAll(recListFuhreLADE.keySet());
			vID_Fuhren.addAll(recListFuhreABLADE.keySet());
			vID_Fuhren.addAll(recListFuhreORT.keySet());
			vID_Fuhren.removeAll(vMinusEins);

			return vID_Fuhren;
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
