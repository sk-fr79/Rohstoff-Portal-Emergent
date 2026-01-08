package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAND__MAP_SET_AND_Valid_EU_RC_Sorten extends XX_MAP_Set_And_Valid {

	
	
	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.makeSettings(oMAP, ActionType, oExecInfo, oInputMap,true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.makeSettings(oMAP, ActionType, oExecInfo, oInputMap,true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.makeSettings(oMAP, ActionType, oExecInfo, oInputMap,true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.makeSettings(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	private MyE2_MessageVector makeSettings(		E2_ComponentMAP 	oMAP, 
													int 				ActionType, 
													ExecINFO 			oExecInfo,
													SQLMaskInputMAP 	oInputMap,
													boolean  			bEditable) throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		
		//basis-einstellung
		if (bEditable) {
			((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).set_AktivPassiv(true);
		}
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			if (bEditable && oMAP.get_bIs_Edit() && oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(_DB.LAND$INTRASTAT_JN).equals("N")) {
				((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).set_AktivPassiv(false);
			}
		}
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			if (!oMAP.get_bActualDBValue(_DB.LAND$INTRASTAT_JN)) {
				if (((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).get_iAnzahlAktiveRcSorten()>0) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei einem NICHT-EU-Land können keine RC-Sorten erfasst werden !")));
				}
			}
		}
		
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
			if (!oMAP.get_bActualDBValue(_DB.LAND$INTRASTAT_JN)) {
				if (((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).get_iAnzahlAktiveRcSorten()>0) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei einem NICHT-EU-Land können keine RC-Sorten erfasst werden !")));
				} else {
					//dann das RC-Element inaktiv setzen
					((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).set_AktivPassiv(false);
				}
			} else {
				((LAND__MASK_RC_Sorten) oMAP.get__Comp(LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX)).set_AktivPassiv(true);
			}
		}
		
		
		
		
		
		return oMV;
	}
	
}
