package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_LAB_einheit extends RB_lab {
	
	public KFIX_K_M_LAB_einheit() {
		super();
		this._fsa(-2)._i();
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this._t("");
		if (dataObject.get_RecORD()!=null) {

			String id_artikel = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
			if(S.isFull(id_artikel)){
				String id_einheit = new Rec20(_TAB.artikel)._fill_id(id_artikel).get_ufs_dbVal(ARTIKEL.id_einheit);

				Rec20 einheit_record = new Rec20(_TAB.einheit)._fill_id(id_einheit);

				this._t("")._t("Einheit: "+ einheit_record.get_ufs_dbVal(EINHEIT.einheitlang));
			}
		}
	}


	@Override
	public void rb_set_db_value_manual(String id_artikel) throws myException {
		if (S.isFull(id_artikel)) {

			String id_einheit = new Rec20(_TAB.artikel)._fill_id(id_artikel).get_ufs_dbVal(ARTIKEL.id_einheit);

			Rec20 einheit_record = new Rec20(_TAB.einheit)._fill_id(id_einheit);

			this._t("")._t("Einheit: "+ einheit_record.get_ufs_dbVal(EINHEIT.einheitlang));
		}
	}
}
