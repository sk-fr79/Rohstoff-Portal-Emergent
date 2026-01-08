package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_SetAndValid_fixierungsmenge extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){
			MyLong fix_gesamt_menge = new MyLong(0l);
			
			if(rbMASK instanceof KFIX_K_M_ComponentMap){
				fix_gesamt_menge = new MyLong(rbMASK.getRbComponent(VKOPF_KON.fix_menge_gesamt).get__actual_maskstring_in_view());
				
			}else if(rbMASK instanceof KFIX_P_M__ComponentMap){
				fix_gesamt_menge = new MyLong( ((RB_TextAnzeige)rbMASK.get__Comp(VKOPF_KON.fix_menge_gesamt).c()).getText() );
			
			}
			if(fix_gesamt_menge.get_bOK() && fix_gesamt_menge.get_lValue()==0 ){
				mv._addAlarm("Die Fixierungsmenge darf nicht null sein!");
			}
		}

		return mv;
	}

}
