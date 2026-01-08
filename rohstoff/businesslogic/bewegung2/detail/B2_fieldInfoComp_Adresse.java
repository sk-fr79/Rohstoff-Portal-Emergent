package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class B2_fieldInfoComp_Adresse extends E2_Grid implements IF_FieldInfo_Component {

	private IF_Field field = null;
	
	
	public B2_fieldInfoComp_Adresse(IF_Field p_field) {
		super();
		this._s(1);
		
		this.field = p_field;
		
	}
	
	public B2_fieldInfoComp_Adresse(IF_Field p_field, boolean isHorizontal) {
		super();
		this._s(1);
		if(isHorizontal) {
			this.setOrientation(ORIENTATION_VERTICAL);
		}
		this.field = p_field;
		
	}

	@Override
	public Component get_component(Rec21 r) throws myException {
		long id_adresse = 0;
		Rec21_adresse recAddr = null;
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		this._clear();
		if(r != null) {
			id_adresse = r.get_myLong_dbVal(this.field, new MyLong(0)).get_lValue();
		}
		
		if(id_adresse >0) {
			
			recAddr = new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(id_adresse));
			
//			this._a(new RB_lab()._t("ID: " + id_adresse), 	gld);
			this._a(new RB_lab()._t(recAddr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2, ADRESSE.name3)), 	gld);
			this._a(new RB_lab()._t(" ("+ recAddr.get_ufs_dbVal(ADRESSE.ort, " - ") + ")"), 						gld);
//			this._a(new RB_lab()._t(recAddr.get_StandardTelefonNumber()), 										gld);
			
//			RB_lab hauptadresse_lbl = new RB_lab()._i()._fsa(-2);
//			if(! recAddr.__is_main_adresse()) {
//				this._a(hauptadresse_lbl._t("Lager von: " + recAddr._getMainAdresse().__get_name1_ort()), 		gld);
//			}else {
//				this._a(hauptadresse_lbl._t("(Hauptadresse)") , 												gld);
//			}
			
		}
		
		return this.c();
	}

}
