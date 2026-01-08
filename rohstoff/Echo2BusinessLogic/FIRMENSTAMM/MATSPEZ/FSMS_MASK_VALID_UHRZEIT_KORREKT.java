package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyTime;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_MASK_VALID_UHRZEIT_KORREKT extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap)	throws myException {
		return make_internal_check(oMAP, ActionType, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap)	throws myException {
		return make_internal_check(oMAP, ActionType, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap)	throws myException {
		return make_internal_check(oMAP, ActionType, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap)	throws myException {
		return new MyE2_MessageVector();
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap)	throws myException {
		return new MyE2_MessageVector();
	}

	
	
	private MyE2_MessageVector make_internal_check(E2_ComponentMAP oMAP, int ActionType, SQLMaskInputMAP oInputMap)	throws myException 	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
		{
			String cUhrZeit = S.NN(oMAP.get_cActualDBValueFormated(_DB.MAT_SPEZ$ZEIT_ERFASSUNG)).trim();
			
			if (S.isFull(cUhrZeit))
			{
				MyTime oTestTime = new MyTime(cUhrZeit);
				
				if (!oTestTime.get_bOK())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Uhrzeit muss im Format <hh:mm> erfasst werden (Bsp: 14:29")));
				}
				else
				{
					oMAP.get__DBComp(_DB.MAT_SPEZ$ZEIT_ERFASSUNG).set_cActualMaskValue(oTestTime.get_cTimeKorrekt());
					if (oInputMap != null)
					{
						oInputMap.put(_DB.MAT_SPEZ$ZEIT_ERFASSUNG, oTestTime.get_cTimeKorrekt());
					}
				}
			}
		}
		return oMV;
	}
	
}
