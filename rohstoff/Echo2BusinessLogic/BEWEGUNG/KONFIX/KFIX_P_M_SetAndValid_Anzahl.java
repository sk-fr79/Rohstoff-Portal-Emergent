package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.math.BigDecimal;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M_SetAndValid_Anzahl extends RB_Mask_Set_And_Valid {

	private static MyBigDecimal BD0 = new MyBigDecimal("0");

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){

			Rec20_VKOPF_KON reckopf = null;

			KFIX_P_M__ComponentMap compMap = (KFIX_P_M__ComponentMap)rbMASK;

			reckopf = compMap.getRec_vkopf();
			if(reckopf.is_fixierungsKontrakte()){
				String id_to_update = bibALL.convertID2UnformattedID(compMap.getRbComponentSavable(VPOS_KON.id_vpos_kon).rb_readValue_4_dataobject());

				MyBigDecimal akt_menge = null;

				if(S.isFull(id_to_update)){
					akt_menge = new Rec20(_TAB.vpos_kon)._fill_id(id_to_update).get_myBigDecimal_dbVal(VPOS_KON.anzahl, BD0);
				}else{
					akt_menge = BD0;
				}
				if(akt_menge.get_longValue()>0){
					MyBigDecimal position_menge = new MyBigDecimal(compMap.getRbComponentSavable(VPOS_KON.anzahl).rb_readValue_4_dataobject(), BD0.get_bdWert(),BD0.get_bdWert());
					MyBigDecimal fixiert_menge = reckopf.get_aktuelle_fixiert_menge().subtract_from_me(akt_menge);
					MyBigDecimal fix_gesamt_menge = reckopf.get_fixierung_gesamt_menge();

					BigDecimal neue_fixiert_menge = position_menge.add_to_me(fixiert_menge);

					if(neue_fixiert_menge.compareTo(fix_gesamt_menge.get_bdWert())==1){
						mv._addAlarm("Die Menge in den Positionen ist größer als die Gesamtmenge des Fixierungsvertrags!");
						compMap.getRbComponentSavable(VPOS_KON.anzahl).mark_FalseInput();
					}else if(akt_menge.get_longValue()==0){
						mv._addWarn("Keine Gesamte Fixierungsmenge im Kopf");
					}
				}

			}

		}
		return mv;
	}
}
