package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX__LBL_einheit extends RB_lab {

	public KFIX__LBL_einheit() {
		super();
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject.get_RecORD()!=null){

			String id_vkopf_kon = (dataObject.get_MyRECORD()).get_UnFormatedValue(this.rb_KF().FIELDNAME());

			boolean is_fixKontrakt = new Rec20_VKOPF_KON(new Rec20(_TAB.vkopf_kon)._fill_id(id_vkopf_kon)).is_fixierungsKontrakte();
			
			if(is_fixKontrakt){
				Rec20 record_artikel = new Rec20(_TAB.vkopf_kon)._fill_id(id_vkopf_kon).get_up_Rec20(VKOPF_KON.fix_id_artikel, ARTIKEL.id_artikel, false);
				String einheit = record_artikel.get_fs_dbVal(ARTIKEL.id_einheit_preis);
				this._t(new Rec20(_TAB.einheit)._fill_id(einheit).get_fs_dbVal(EINHEIT.einheitkurz));
			}
			
		}
	}



}
