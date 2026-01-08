package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX__LBL_waehrung extends RB_lab {

	public KFIX__LBL_waehrung() {
		super();
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject.get_RecORD()!=null){
			String id_waehrungfremd_vpos = (dataObject.get_MyRECORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
			
			this._t(new Rec20(_TAB.waehrung)._fill_id(id_waehrungfremd_vpos).get_fs_dbVal(WAEHRUNG.kurzbezeichnung));
		}
	}



}
