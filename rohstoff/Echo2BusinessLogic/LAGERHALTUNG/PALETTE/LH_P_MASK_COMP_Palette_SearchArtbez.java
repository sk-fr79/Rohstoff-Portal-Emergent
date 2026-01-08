/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 07.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * @author sebastien
 * @date 07.12.2018
 *
 */
public class LH_P_MASK_COMP_Palette_SearchArtbez extends RB_HL_SearchArtbez{

	/**
	 * @author sebastien
	 * @date 07.12.2018
	 *
	 * @throws myException
	 */
	public LH_P_MASK_COMP_Palette_SearchArtbez() throws myException {
		super(true);
				
		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_mid();

		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ALL_ARTBEZ);
		this.get_tf_search_input()._w(100);
		this._clear()
		._a(this.get_tf_search_input(), gl._c())
		._a(this.get_buttonErase(), gl._c())
		._a(this.get_buttonStartSearch(), gl._c())
		._a(this.get_gridContainer_to_show_searchResult(), gl._c()._span(3))
		._setSize(100,20,150);

	}
	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		return null;
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicModuleContainer();
	}

	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv)
			throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		return mv;
	}

	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		RECORD_ARTIKEL_BEZ rab = (RECORD_ARTIKEL_BEZ)p_result_record;
		return rab.get_ID_ARTIKEL_BEZ_cUF();
	}

	private class ownBasicModuleContainer extends E2_BasicModuleContainer{
	}


	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db)
			throws myException {
		gridcontainer_4_search_results._s(1)._clear()._w100();
		String anr12_artbez_txt = "";
		if(S.isFull(c_result_value_4_db)) {
			Rec21_artikel_bez recArtBez = new Rec21_artikel_bez()._fill_id(c_result_value_4_db);

			anr12_artbez_txt	= recArtBez.__get_ANR1_ANR2_ARTBEZ1();
		}
		gridcontainer_4_search_results._a(new RB_lab()._t(anr12_artbez_txt)._i()._lwn(), new RB_gld()._left_mid());
	}
}
