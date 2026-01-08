package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;


/**
 * validierer-objekt, das prueft, ob ein eigenes Lager vorhanden ist, und wenn ja, ob es ein fremdwarenlager ist
 * @author martin
 *
 */
public class FU_Valid_Fremdware_und_Fremdwaren_Lager extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return pruefe_situation(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	private MyE2_MessageVector  pruefe_situation(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			FU_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER oValidator = new FU_VALID_HELPER_FREMDWAREN_FREMDWAREN_LAGER((FU_MASK_ComponentMAP)oMAP);
			oMV_Rueck.add_MESSAGE(oValidator.get_vValidErgebnis());
		
		}		
		return oMV_Rueck;
	
	}
	
	
}
