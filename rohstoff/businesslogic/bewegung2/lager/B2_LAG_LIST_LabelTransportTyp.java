package rohstoff.businesslogic.bewegung2.lager;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_Button_DbSimple;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_LAG_LIST_LabelTransportTyp extends E2_Button_DbSimple{

	public B2_LAG_LIST_LabelTransportTyp() {
		super();
		this._s_BorderText()._fsa(2)._b()._aaa(()->{
			B2_LAG_LIST_LabelTransportTyp.this.EXT().get_oComponentMAP()._setLineSelected();
		});
		
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		String atom_pos = new Rec21(_TAB.bg_atom)._fill_id(oResultMAP.get_UnFormatedValue(BG_ATOM.id_bg_atom.fn())).getUfs(BG_ATOM.pos_in_mask,"");
		
		if(S.isFull(atom_pos)) {
			if(atom_pos.equals(EnTabKeyInMask.A1.dbVal())){
				this._t("L");
				this._ttt("Atom links");
			}else if(atom_pos.equals(EnTabKeyInMask.A2.dbVal())){
				this._t("R");
				this._ttt("Atom rechts");
			}else {
				this._t("-");
			}
		}else {
			this._t("-");
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._t("-");
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_LAG_LIST_LabelTransportTyp bnew = new B2_LAG_LIST_LabelTransportTyp();
		bnew.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(bnew));
		return bnew;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	

	
	
}
