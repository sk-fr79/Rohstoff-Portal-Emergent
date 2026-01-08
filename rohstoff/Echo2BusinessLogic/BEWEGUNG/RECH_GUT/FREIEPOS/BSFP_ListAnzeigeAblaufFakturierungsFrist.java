/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 17.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposRg;

/**
 * @author martin
 * @date 17.06.2019
 *
 */
public class BSFP_ListAnzeigeAblaufFakturierungsFrist extends E2_UniversalListComponent {

	public BSFP_ListAnzeigeAblaufFakturierungsFrist() {
		super();
		this._setSize(100);
		this.EXT().set_bLineWrapListHeader(true);
		this.EXT().setLongString4ColumnSelection(S.ms("Anzeige Ablauf Fakturierungsfrist"));
		this.EXT().set_oColExtent(new Extent(60));
		this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col_back_d());

	}

	@Override
	public String key() throws myException {
		return "BSFP_ListAnzeigeAblaufFakturierungsFrist<72c43bac-9119-11e9-bc42-526af7764f64>";
	}

	@Override
	public String userText() throws myException {
		return "Fakt. Frist";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent#
	 * prepare_ContentForNew(boolean)
	 */
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		_clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * panter.gmbh.Echo2.components.DB.E2_UniversalListComponent#populate(panter.
	 * gmbh.indep.dataTools.SQLResultMAP)
	 */
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();

		try {
			E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2) this.EXT().get_oComponentMAP();
			Rec21 recPos = new Rec21(map.getRec21()); // rec21-vpos_rg

			this._a(new Rec21_VposRg(recPos).getStatusFakturierungsFrist(), new RB_gld()._ins(1, 2, 1, 1)._center_top());
		} catch (Exception e) {
			this._a(new RB_lab()._t("Error")._ttt("Error find FaktStatus: Code <80fcefd8-9430-11e9-bc42-526af7764f64>"));
			e.printStackTrace();
		}
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new BSFP_ListAnzeigeAblaufFakturierungsFrist();
	}

}
