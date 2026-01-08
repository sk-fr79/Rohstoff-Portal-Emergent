package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;

public abstract class FZ_SaV_Manuell_EPreis extends RB_Mask_Set_And_Valid {

	private RB_ComponentMapCollector component_map_collector;

	public abstract KEY_ATOM get_atom(RB_ComponentMap rbMASK)throws myException;

	public abstract MASK_STATUS get_status(RB_ComponentMap rbMASK) throws myException;

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION){
			valid_action_before_field_validation(rbMASK);
		}

		if(ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION){
			klick_action(rbMASK);
		}

		if(ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
			load_action(rbMASK);

		}

		return mv;
	}

	private void load_action(RB_ComponentMap rbMASK) throws myException {
		if(get_status(rbMASK)==MASK_STATUS.EDIT || get_status(rbMASK)==MASK_STATUS.NEW){

			String vpos_kon_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.id_vpos_kon.fk()).get__actual_maskstring_in_view();//rb_readValue_4_dataobject();
			String vpos_std_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.id_vpos_std.fk()).get__actual_maskstring_in_view();

			boolean is_manuell_preis 	= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.manuell_preis.fk()).get__actual_maskstring_in_view().equals("Y")?true:false;

			if(is_manuell_preis && (S.isFull(vpos_std_id)||S.isFull(vpos_kon_id)) ){

				rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(true);

			}else if(!is_manuell_preis && (S.isFull(vpos_std_id)||S.isFull(vpos_kon_id))){

				rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.manuell_preis.fk()).set_bEnabled_For_Edit(true);

				rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(false);

//				DEBUG.System_println("°°CASE1"+is_manuell_preis+" ; "+ vpos_std_id + " ; "+ vpos_kon_id);

			}else if( S.isEmpty(vpos_std_id) && S.isEmpty(vpos_kon_id) ){

				rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.manuell_preis.fk()).set_bEnabled_For_Edit(false);

//				DEBUG.System_println("--CASE2"+is_manuell_preis+" ; "+ vpos_std_id + " ; "+ vpos_kon_id);
			}
		}
	}


	private void klick_action(RB_ComponentMap rbMASK) throws myException {
		boolean is_manuell = rbMASK.getRbComponent(BEWEGUNG_ATOM.manuell_preis).get__actual_maskstring_in_view().equals("Y")?true:false;

		String vpos_kon_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.id_vpos_kon.fk()).get__actual_maskstring_in_view();
		String vpos_std_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.id_vpos_std.fk()).get__actual_maskstring_in_view();

		if(is_manuell && (S.isFull(vpos_kon_id)|| S.isFull(vpos_std_id))){
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(is_manuell);

		}else if(!is_manuell && (S.isFull(vpos_kon_id)|| S.isFull(vpos_std_id))){
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),BEWEGUNG_ATOM.e_preis.fk()).rb_set_db_value_manual(searchPreis(vpos_kon_id, vpos_std_id));
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(false);
		}else if(S.isFull(vpos_kon_id)|| S.isFull(vpos_std_id) ){
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(is_manuell);
		}
	}


	private String searchPreis(String vpos_kon_id, String vpos_std_id) throws myException {
		String ePreis = "";

		
		if(S.isFull(vpos_kon_id)){
			ePreis = new Rec20(_TAB.vpos_kon)._fill_id(bibALL.convertID2UnformattedID(vpos_kon_id)).get_ufs_dbVal(VPOS_KON.einzelpreis);	
		}else if(S.isFull(vpos_std_id)){
			ePreis = new Rec20(_TAB.vpos_std)._fill_id(bibALL.convertID2UnformattedID(vpos_std_id)).get_ufs_dbVal(VPOS_STD.einzelpreis);	
		}
		
		return ePreis;
		
	}

	private void valid_action_before_field_validation(RB_ComponentMap rbMASK) throws myException {
		String vpos_kon_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.id_vpos_kon.fk()).get__actual_maskstring_in_view();
		String vpos_std_id 			= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.id_vpos_std.fk()).get__actual_maskstring_in_view();
		boolean is_manuell_preis 	= rbMASK._find_component_in_neighborhood(get_atom(rbMASK), BEWEGUNG_ATOM.manuell_preis.fk()).get__actual_maskstring_in_view().equals("Y")?true:false;

		if((S.isFull(vpos_kon_id) || S.isFull(vpos_std_id)) && ! is_manuell_preis){
			Rec20 record_vpos;

			String epreis = "";

			if(S.isFull(vpos_kon_id)){
				record_vpos = new Rec20(_TAB.vpos_kon)._fill_id(bibALL.convertID2UnformattedID(vpos_kon_id));
				epreis = record_vpos.get_fs_dbVal(VPOS_KON.einzelpreis,"");
			}else if(S.isFull(vpos_std_id)){
				record_vpos = new Rec20(_TAB.vpos_std)._fill_id(bibALL.convertID2UnformattedID(vpos_std_id));
				epreis = record_vpos.get_fs_dbVal(VPOS_STD.einzelpreis,"");
			}

			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),BEWEGUNG_ATOM.e_preis.fk()).rb_set_db_value_manual(epreis);
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),BEWEGUNG_ATOM.e_preis.fk()).set_bEnabled_For_Edit(false);

		}else if(S.isEmpty(vpos_std_id) && S.isEmpty(vpos_kon_id)){
			rbMASK._find_component_in_neighborhood(get_atom(rbMASK),	BEWEGUNG_ATOM.manuell_preis.fk()).rb_set_db_value_manual("N");

		}
	}

	public RB_ComponentMapCollector getComponentMapCollector(){
		return component_map_collector;
	}

}
