package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;

public class KFIX_P_M_SetAndValid_ladung_schalter extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {


		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		MASK_STATUS actual_status = rbMASK.getRbDataObjectActual().rb_MASK_STATUS();
		
		KFIX_P_M__ComponentMap compMap = (KFIX_P_M__ComponentMap)rbMASK;
		
		if((ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) && (actual_status==MASK_STATUS.EDIT)){
			Rec20_VPOS_KON record_vpos_on = new Rec20_VPOS_KON(new Rec20(_TAB.vpos_kon)._fill_id(compMap.getRbComponentSavable(VPOS_KON.id_vpos_kon).rb_readValue_4_dataobject()) );

			boolean is_ladung_position = ((RB_CheckBox)rbMASK.get__Comp(VPOS_KON.typ_ladung)).rb_readValue_4_dataobject().equals("Y");

			if(is_ladung_position){

			boolean isEk = compMap.is_einkauf_position();

			String anzahl_wert = "";
			
			if(record_vpos_on.get_how_many_fuhre_are_associated_with_position(isEk) == 0){
				
				anzahl_wert = new MyBigDecimal(0l).get_FormatedRoundedNumber(3);

			}else if (record_vpos_on.get_how_many_fuhre_are_associated_with_position(isEk) == 1){
				/*einkaufskontrakte*/
				if(isEk){
					
					
					RecList20 fuhre_ek_rec_list = record_vpos_on.get_down_reclist20(
							VPOS_TPA_FUHRE.id_vpos_kon_ek,
							"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'", 
							"");

					RecList20 fuhre_ort_ek_rec_list = record_vpos_on.get_down_reclist20(
							VPOS_TPA_FUHRE_ORT.id_vpos_kon
							,"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'EK'", 
							"");
					
					if(fuhre_ek_rec_list.size()==1){
						IF_Field src_field = null;
						if(fuhre_ek_rec_list.get(0).is_yes_db_val(VPOS_TPA_FUHRE.lademenge_gutschrift)){
							src_field = VPOS_TPA_FUHRE.anteil_lademenge_lief;
						}else{
							src_field = VPOS_TPA_FUHRE.anteil_ablademenge_lief;
						}
						anzahl_wert = fuhre_ek_rec_list.get(0).get_myBigDecimal_dbVal(src_field, new MyBigDecimal(0)).get_FormatedRoundedNumber(3);
					}else if(fuhre_ort_ek_rec_list.size()==1){
						anzahl_wert = fuhre_ort_ek_rec_list.get(0).get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_lademenge, new MyBigDecimal(0)).get_FormatedRoundedNumber(3);
					}
					
				/*verkaufskontrakte*/	
				}else{
					
					RecList20 fuhre_vk_rec_list = record_vpos_on.get_down_reclist20(
							VPOS_TPA_FUHRE.id_vpos_kon_vk,
							"NVL(JT_VPOS_TPA_FUHRE.ist_storniert,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE.deleted, 'N')='N'", 
							"");

					RecList20 fuhre_ort_vk_rec_list = record_vpos_on.get_down_reclist20(
							VPOS_TPA_FUHRE_ORT.id_vpos_kon
							,"NVL(JT_VPOS_TPA_FUHRE_ORT.deleted,'N')='N' AND JT_VPOS_TPA_FUHRE_ORT.def_quelle_ziel = 'VK'", 
							"");
					
					if(fuhre_vk_rec_list.size()==1){
						IF_Field src_field = null;
						if(fuhre_vk_rec_list.get(0).is_yes_db_val(VPOS_TPA_FUHRE.ablademenge_rechnung)){
							src_field = VPOS_TPA_FUHRE.anteil_ablademenge_abn;
						}else{
							src_field = VPOS_TPA_FUHRE.anteil_lademenge_abn;
						}
						
						anzahl_wert = fuhre_vk_rec_list.get(0).get_myBigDecimal_dbVal(src_field,new MyBigDecimal(0)).get_FormatedRoundedNumber(3);
					}else if(fuhre_ort_vk_rec_list.size()==1){
						anzahl_wert = fuhre_ort_vk_rec_list.get(0).get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge,new MyBigDecimal(0)).get_FormatedRoundedNumber(3);
					}
				}

//				ScompMap.rb_Component(VPOS_KON.anzahl).rb_set_db_value_manual(anzahl_wert);
			}else if (record_vpos_on.get_how_many_fuhre_are_associated_with_position(isEk) >1 ){
				
				anzahl_wert = record_vpos_on.get_gesamt_fuhre_menge(isEk).get_FormatedRoundedNumber(3);
				
				mv._addAlarm("Es wurden bereits mehr als eine Fuhre auf diese Kontraktposition gebucht, deshalt ist der Status  \"Lademenge\"-Position nicht mehr möglich!");
				
				((MyE2_CheckBox)compMap.get__Comp(VPOS_KON.typ_ladung.name())).setSelected(false);
			}

			
			compMap._setValue(VPOS_KON.anzahl, anzahl_wert);
			compMap.getPreSettingsContainer().rb_get(VPOS_KON.anzahl).set_Enabled(false);
		}
		}
		return mv;
	}

}
