/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 15.02.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_Button_DbSimple;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

/**
 * @author martin
 * @date 15.02.2019
 *
 */
public class B2_ListLabelFuhrenTyp extends E2_Button_DbSimple{

	public B2_ListLabelFuhrenTyp() {
		super();
		this._s_BorderText()._fsa(2)._b()._aaa(()->{
			B2_ListLabelFuhrenTyp.this.EXT().get_oComponentMAP()._setLineSelected();
		});;
		
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		EnTransportTyp typ = new Rec21_bgVector(((E2_ComponentMAP_IF_Rec21)this.EXT().get_oComponentMAP()).getRec21()).getTransportTyp();
		
		switch (typ) {
		case LAGER_LAGER:
			this._t("LL");
			this._ttt(S.ms("Lager-Lager-Fuhre"));
			break;
		case STRECKE:
			this._t("S");
			this._ttt(S.ms("Strecken-Fuhre"));
			break;
		case WA:
			this._t("WA");
			this._ttt(S.ms("Warenausgang"));
			break;
		case WE:
			this._t("WE");
			this._ttt(S.ms("Wareneingang"));
			break;
		case AUSBUCHUNG:
			this._t("AB");
			this._ttt(S.ms("Ausbuchung"));
			break;
		case AUSBUCHUNG_F:
			this._t("ABF");
			this._ttt(S.ms("Ausbuchung (Fremdware)"));
			break;
		case EINBUCHUNG:
			this._t("EB");
			this._ttt(S.ms("Einbuchung"));
			break;
			
		case EINBUCHUNG_F:
			this._t("EBF");
			this._ttt(S.ms("Einbuchung (Fremdware)"));
			break;

		case FREMDWARENTRANSPORT:
			this._t("FW");
			this._ttt(S.ms("Fremdwarentransport"));
			break;

		case LEERGUTRANSPORT:
			this._t("LG");
			this._ttt(S.ms("Leergut-Transport"));
			break;
		case TESTSTELLUNG:
			this._t("TS");
			this._ttt(S.ms("Teststellung"));
			break;
		case UMBUCHUNG:
			this._t("UB");
			this._ttt(S.ms("Umbuchung"));
			break;
		case WA_L:
			this._t("WAL");
			this._ttt(S.ms("Verkauf Fremdware (bleibt im Lager)"));
			break;

		case WE_L:
			this._t("WEL");
			this._ttt(S.ms("Einkauf Fremdware (bereits im Lager)"));
			
			break;
		default:
			this._t("-");
			break;
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._t("-");
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_ListLabelFuhrenTyp bnew = new B2_ListLabelFuhrenTyp();
		bnew.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(bnew));
		return bnew;
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	

	
	
}
