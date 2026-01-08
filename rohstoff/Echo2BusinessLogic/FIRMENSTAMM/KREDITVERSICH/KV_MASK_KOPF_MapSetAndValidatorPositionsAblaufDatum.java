/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH
 * @author martin
 * @date 27.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.06.2019
 *
 */
public class KV_MASK_KOPF_MapSetAndValidatorPositionsAblaufDatum extends XX_MAP_Set_And_Valid {

	
	
	private KV_HEAD_LIST_BasicModule_Inlay    kv_head_list_BasicModule_Inlay = null;
	
	public KV_MASK_KOPF_MapSetAndValidatorPositionsAblaufDatum(KV_HEAD_LIST_BasicModule_Inlay    p_kv_head_list_BasicModule_Inlay ) {
		super();
		this.kv_head_list_BasicModule_Inlay = p_kv_head_list_BasicModule_Inlay;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
	
		MyE2_MessageVector mv = bibMSG.getNewMV();

		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			
			try {
				Long idAdresse =  Long.parseLong(this.kv_head_list_BasicModule_Inlay.getIdAdresse());
				Long idKVKopf =   Long.parseLong(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				Long faktFrist = oInputMap.get_LActualValue(KREDITVERS_KOPF.fakturierungsfrist.fn(), true, true, 0l);

				mv._add(KV_Lib.checkCorrectEndDates(idAdresse, new PAIR<Long,Long>()._setVal1(idKVKopf)._setVal2(faktFrist), null));
				
			} catch (NumberFormatException e) {
				mv._addAlarm("Error Check Positions: Cannot find Head-ID: Code <4163be1c-9c03-11e9-a2a3-2a2ae2dbcce4>");
				e.printStackTrace();
			} catch (myException e) {
				mv._addAlarm("Error Check Positions: Cannot find Head-ID: Code <4163be1c-9c03-11e9-a2a3-2a2ae2dbcce4>");
				e.printStackTrace();
			} catch (Exception e) {
				mv._addAlarm("Unknown Error: Validation: Code <4163be1c-9c03-11e9-a2a3-2a2ae2dbcce4>"+"//"+e.getLocalizedMessage());
				
			}
		}
	
		return mv;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return null;
	}

}
