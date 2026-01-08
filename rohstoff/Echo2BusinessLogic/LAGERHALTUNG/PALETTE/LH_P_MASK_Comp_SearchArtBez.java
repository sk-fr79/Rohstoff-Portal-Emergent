/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 07.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * @author sebastien
 * @date 07.12.2018
 *
 */
public class LH_P_MASK_Comp_SearchArtBez extends RB_HL_SearchArtbez {

	public LH_P_MASK_Comp_SearchArtBez() throws myException {
		super(true);

		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ALL_ARTBEZ);


		this.set_show_bt_add_all_found_artikel_bez(false);

		int[] iBreite ={200,25,25,400};

		this.get_buttonStartSearch().setDisabledIcon(E2_ResourceIcon.get_RI("suche__.png"));
		this.get_buttonErase().setDisabledIcon(E2_ResourceIcon.get_RI("eraser__.png"));

		this._clear()._setSize(iBreite)
		._a(this.get_tf_search_input()._w(200), 			new RB_gld()._ins(1)._center_mid())
		._a(this.get_buttonStartSearch(), 					new RB_gld()._ins(1)._center_mid())
		._a(this.get_buttonErase(),							new RB_gld()._ins(1)._center_mid())
		._a(this.get_gridContainer_to_show_searchResult(), 	new RB_gld()._ins(5,1,1,1)._left_mid());
	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db)
			throws myException {
		gridcontainer_4_search_results.removeAll();
		RB_lab lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new RB_lab();
		} else {
			Rec20 recArtBezExt = new Rec20(_TAB.artikel_bez)._fill_id(c_result_value_4_db);

			String anr1 = recArtBezExt.get_up_Rec20( ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel,false).get_fs_dbVal(ARTIKEL.anr1);
			String anr2 = recArtBezExt.get_fs_dbVal(ARTIKEL_BEZ.anr2, "");
			String artBez = recArtBezExt.get_fs_dbVal(ARTIKEL_BEZ.artbez1, "");
			lab_help=
					new RB_lab()
					._t(artBez+" ("+anr1+"/"+anr2+",  ID Artikel: " + recArtBezExt.get_fs_dbVal(ARTIKEL_BEZ.id_artikel)+ ")")._fsa(-2)._i();			
		}

		gridcontainer_4_search_results._a_lm(lab_help);
		gridcontainer_4_search_results.setBorder(null);
	}



	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		return null;
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv)
			throws myException {
		return null;
	}

	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ARTIKEL_BEZ)p_result_record).get_ID_ARTIKEL_BEZ_cUF();
	}

	private class ownBasicContainer extends E2_BasicModuleContainer {
	}

}
