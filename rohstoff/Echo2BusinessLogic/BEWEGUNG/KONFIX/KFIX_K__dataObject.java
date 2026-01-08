package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

/**
 * hilfsdataset, wird allen unter der vkopf_kon - struktur liegenden dataset untergeschoben, um locking-pruefungen zu ermoeglichen
 * @author martin
 *
 */
public class KFIX_K__dataObject extends RB_Dataobject_V2 {

	public KFIX_K__dataObject(String id_vkopf_kon) throws myException {
		
		super(_TAB.vkopf_kon);
		this.set_actualMASK_STATUS(MASK_STATUS.EDIT);
		Rec20 rec_kopf = new Rec20(_TAB.vkopf_kon)._fill_id(id_vkopf_kon);
		String name3 = rec_kopf.get_value4sql_statements(VKOPF_KON.name3);
		rec_kopf._add_field_val_pair(VKOPF_KON.name3, name3);
		this.set_rec20(rec_kopf);
	}
	

}
