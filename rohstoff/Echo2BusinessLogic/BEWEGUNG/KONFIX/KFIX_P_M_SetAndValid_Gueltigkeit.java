package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M_SetAndValid_Gueltigkeit extends RB_Mask_Set_And_Valid{

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();
		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){
			Rec20_VKOPF_KON reckopf = null;

			KFIX_P_M__ComponentMapAddon compMap_kon_trakt = (KFIX_P_M__ComponentMapAddon)rbMASK;

			KFIX_P_M__ComponentMap compMap_Kon = (KFIX_P_M__ComponentMap) rbMASK.rb_get_belongs_to().get(_TAB.vpos_kon.rb_km());

			reckopf = (Rec20_VKOPF_KON) compMap_kon_trakt.getRec_vkopf();

			String Dv="";
			String Db="";
			
			if(reckopf.is_fixierungsKontrakte()){
				Dv = ((E2_TF_4_Date)compMap_Kon.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_VON.name())).get_oFormatedDateFromTextField();
				Db = ((E2_TF_4_Date)compMap_Kon.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_BIS.name())).get_oFormatedDateFromTextField();
			}
			
			String Gv = compMap_kon_trakt.getRbComponentSavable(VPOS_KON_TRAKT.gueltig_von).rb_readValue_4_dataobject();
			String Gb = compMap_kon_trakt.getRbComponentSavable(VPOS_KON_TRAKT.gueltig_bis).rb_readValue_4_dataobject();

			if(S.isEmpty(Gv) ){
				mv._addAlarm("Keine Start datum");		
			}else
				if(S.isEmpty(Gb)){
					mv._addAlarm("Keine Ende datum");
				}else
					if( myDateHelper.get_Date1_Greater_Date2(Gv, Gb)){
						mv._addAlarm("Gueltig_von groesser als Gueltig_bis");				
					}else{
						if(reckopf.is_fixierungsKontrakte() && S.isFull(Gv) && S.isFull(Gb)){
							if(myDateHelper.get_Date1_Less_Date2(Gv, Dv)){
								mv._addAlarm("Die Gültigkeit der Position ist zeitlich vor dem Fixierungsbegin !");
							}
							if(myDateHelper.get_Date1_Greater_Date2(Gb, Db)){
								mv._addAlarm("Die Gültigkeit der Position endet nach dem Fixierungsende !");
							}
						}
					}	
		}
		return mv;
	}

}
