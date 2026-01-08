/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH
 * @author martin
 * @date 27.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import java.util.Date;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.O;
import panter.gmbh.indep.TRIPLE;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 27.06.2019
 *
 */
public class KV_MASK_POS_MapSetAndValidatorPositionsAblaufDatum extends XX_MAP_Set_And_Valid {

	
	private KV_POS_LIST_BasicModule_Inlay 		kv_pos_list_basicmodule_inlay = null;
	private KV_HEAD_LIST_BasicModule_Inlay    	kv_head_list_BasicModule_Inlay = null;
	
	
	public KV_MASK_POS_MapSetAndValidatorPositionsAblaufDatum(KV_POS_LIST_BasicModule_Inlay posInlay, KV_HEAD_LIST_BasicModule_Inlay    p_kv_head_list_BasicModule_Inlay ) {
		super();
		this.kv_pos_list_basicmodule_inlay = posInlay;
		this.kv_head_list_BasicModule_Inlay = p_kv_head_list_BasicModule_Inlay;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return check( oMAP,  ActionType, oExecInfo,  oInputMap, null);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return check( oMAP,  ActionType, oExecInfo,  oInputMap, null);
	}
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return check( oMAP,  ActionType, oExecInfo,  oInputMap, oMAP.get_oInternalSQLResultMAP().getLongId());
	}

	private MyE2_MessageVector check(E2_ComponentMAP oMAP, int ActionType,ExecINFO oExecInfo, SQLMaskInputMAP oInputMap, Long idPosAktuelleMaske) {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
	
			try {
				Long 		idAdresse = 				Long.parseLong(this.kv_head_list_BasicModule_Inlay.getIdAdresse());
				
				
				TRIPLE<Long, Date, Date>  position = null;
				
				//jetzt noch die aktuelle maske checken (falls neu wird diese mit der pseudo-id 0 hinterlegt)
				Rec21  recKVKopf = new Rec21(_TAB.kreditvers_kopf)._fill_id(kv_pos_list_basicmodule_inlay.getIdKopfSatz());
				if (recKVKopf.getLongDbValue(KREDITVERS_KOPF.fakturierungsfrist)!=null) {
					Long id_aktuellePos = O.NN(idPosAktuelleMaske, 0l);
					Date dateAb = MyDate.parse(oInputMap.get_InputString(KREDITVERS_POS.gueltig_ab.fn()));
					Date dateBis = MyDate.parse(oInputMap.get_InputString(KREDITVERS_POS.gueltig_bis.fn()));
					position = new TRIPLE<Long, Date, Date>()._setVal1(id_aktuellePos)._setVal2(dateAb)._setVal3(dateBis);
				}

				mv._add(KV_Lib.checkCorrectEndDates(idAdresse,null, position));
				
				
			} catch (NumberFormatException e) {
				mv._addAlarm("Error Check Positions: Cannot find Head-ID: Code <a436292e-98d6-11e9-a2a3-2a2ae2dbcce4>");
				e.printStackTrace();
			} catch (myException e) {
				mv._addAlarm("Error Check Positions: Cannot find Head-ID: Code <ca65eaf8-98d6-11e9-a2a3-2a2ae2dbcce4>");
				e.printStackTrace();
			} catch (Exception e) {
				mv._addAlarm("Unknown Error: Validation: Code <ca65eaf8-98d6-11e9-a2a3-2a2ae2dbcce4>"+"//"+e.getLocalizedMessage());
				
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
