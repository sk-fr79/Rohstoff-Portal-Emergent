package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_Button_DbSimple;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class LH_LIST_COMP_LabelTransportTyp extends E2_Button_DbSimple{

	public LH_LIST_COMP_LabelTransportTyp() {
		super();
		this._s_BorderText()._fsa(2)._b()._aaa(()->{
			LH_LIST_COMP_LabelTransportTyp.this.EXT().get_oComponentMAP()._setLineSelected();
		});
		
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		String oText = oResultMAP.get_FormatedValue(LAGER_BOX.boxnummer.fn(),"-");
		if(oResultMAP.get_booleanActualValue(LAGER_BOX.is_default_box.fn())) {
			this._ttt(S.ms("Default Box"));
			oText += " *";
		}
		this._t(oText);
		
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._t("-");
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		LH_LIST_COMP_LabelTransportTyp bnew = new LH_LIST_COMP_LabelTransportTyp();
		bnew.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(bnew));
		return bnew;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	

	
	
}
