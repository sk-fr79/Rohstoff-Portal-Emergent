package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_SetAndValid_einheit extends RB_Mask_Set_And_Valid {

	public KFIX_K_M_SetAndValid_einheit() {
	}

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
			String id = bibALL.convertID2UnformattedID(rbMASK.getRbComponentSavable(VKOPF_KON.fix_id_artikel).rb_readValue_4_dataobject());
			if(S.isFull(id)){
				String id_artikel = bibALL.convertID2UnformattedID(id);

				String id_einheit = new Rec20(_TAB.artikel)._fill_id(id_artikel).get_ufs_dbVal(ARTIKEL.id_einheit);

				Rec20 einheit_record = new Rec20(_TAB.einheit)._fill_id(id_einheit);

				if(einheit_record.is_yes_db_val(EINHEIT.ist_standard)){
					rbMASK.getComp(VPOS_KON.typ_25_to).setEnabled(true);
				}else{
					rbMASK.getRbComponent(VPOS_KON.typ_25_to).rb_set_db_value_manual("N");
					rbMASK.getComp(VPOS_KON.typ_25_to).setEnabled(false);
				}
			}
		}

		return mv;
	}

}
