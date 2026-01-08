package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

public class B2_fieldInfoComp_Ids extends E2_Grid implements IF_FieldInfo_Component {

	public B2_fieldInfoComp_Ids() {
		super();
		this.setOrientation(ORIENTATION_HORIZONTAL);
		
		this._s(6)._bo_dd();
		
	}

	@Override
	public Component get_component(Rec21 r) throws myException {
		HMAP<RB_KM, Rec21> rec_hm = null;
		
		RB_gld gld = new RB_gld()._ins(1)._left_top();
		
		if(r.get_tab() == _TAB.bg_vektor) {
			 rec_hm = new Rec21_bgVector(r).getCompleteStackOfRecords();
		}else if(r.get_tab() == _TAB.bg_atom) {
			rec_hm= new Rec21_bgVector(r.get_up_Rec21(BG_ATOM.id_bg_vektor)).getCompleteStackOfRecords();
		}else if(r.get_tab() == _TAB.bg_station) {
			rec_hm= new Rec21_bgVector(r.get_up_Rec21(BG_VEKTOR.id_bg_vektor)).getCompleteStackOfRecords();
		}else {
			this._a("<-- ERROR ! -->", new RB_gld()._ins(1)._span(5));
			return this.c();
		}
		
		this._clear()
		._a(new RB_lab()._t("Id-Vektor")._fsa(-2)._i()._lwn(), 						gld)
		._a(new RB_lab()._t("Id-Stat. (Z)")._fsa(-2)._i()._lwn(), 					gld)
		._a(new RB_lab()._t("Id-Atom (Q)")._fsa(-2)._i()._lwn(), 					gld)
		._a(new RB_lab()._t("Id-Station (S)")._fsa(-2)._i()._lwn(), 				gld)
		._a(new RB_lab()._t("Id-Atom (S)")._fsa(-2)._i()._lwn(), 					gld)
		._a(new RB_lab()._t("Id-Stat. (Z)")._fsa(-2)._i()._lwn(), 					gld);
		
		this
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecV.key).get_key_value()), 	gld)
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecS1.key).get_key_value()), gld)
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecA1.key).get_key_value()), gld)
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecS2.key).get_key_value()), gld)
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecA2.key).get_key_value()), gld)
		._a(new RB_lab()._fsa(-2)._t(rec_hm.get(RecS3.key).get_key_value()), gld)
		;		
		return this.c();
	}
}
