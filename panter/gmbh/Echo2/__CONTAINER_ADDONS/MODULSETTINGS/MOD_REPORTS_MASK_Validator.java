package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class MOD_REPORTS_MASK_Validator extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return internalCheck(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return internalCheck(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return internalCheck(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return internalCheck(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return internalCheck(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	
	private MyE2_MessageVector internalCheck(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector  mvRueck = new MyE2_MessageVector();
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			if ( !(	oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_PDF)||
					oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_XLS) ||
					oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_HTML) ||
					oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_TXT) ||
					oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_XML))) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens ein Download-Format angeben !")));
			}
			
			if (oMAP.get_bActualDBValue(_DB.REPORT$PREFER_PDF) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_PDF)) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn der bevorzugte Dateityp PDF ist, dann muss PDF auf in der Liste der erlaubten Dateitypen sein !")));
			}
			if (oMAP.get_bActualDBValue(_DB.REPORT$PREFER_XLS) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_XLS)) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn der bevorzugte Dateityp XLS ist, dann muss XLS auf in der Liste der erlaubten Dateitypen sein !")));
			}
			if (oMAP.get_bActualDBValue(_DB.REPORT$PREFER_HTML) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_HTML)) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn der bevorzugte Dateityp HTML ist, dann muss HTML auf in der Liste der erlaubten Dateitypen sein !")));
			}
			if (oMAP.get_bActualDBValue(_DB.REPORT$PREFER_TXT) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_TXT)) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn der bevorzugte Dateityp TXT ist, dann muss TXT auf in der Liste der erlaubten Dateitypen sein !")));
			}
			if (oMAP.get_bActualDBValue(_DB.REPORT$PREFER_XML) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_XML)) {
				mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn der bevorzugte Dateityp XML ist, dann muss XML auf in der Liste der erlaubten Dateitypen sein !")));
			}
			
			
			if (oMAP.get_bActualDBValue(_DB.REPORT$PASS_MULTI_ID) && !oMAP.get_bActualDBValue(_DB.REPORT$ALLOW_PDF)) {
		//		mvRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Wenn  Mehrfach-ID-Übergabe kann nur PDF als Ausgabeformat angegeben werden !")));
			}
			
		}
		
		return mvRueck;
	}
	
	
}
