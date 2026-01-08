package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;


public class KFIX_K_M_Artikel_SearchField extends RB_HL_SearchArtbez {

	private String idArtikelBez = "";
	private RB_ComponentMap map = null;

	public KFIX_K_M_Artikel_SearchField(RB_ComponentMap oComponentMap, boolean isEk) throws myException {
		super(true);
		this.map = oComponentMap;
		if(isEk){
			this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ONLY_RESTRICTED_ARTBEZ);
			
		}else{
			this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ALL_ARTBEZ);
		}

		this.set_show_bt_add_all_found_artikel_bez(false);
		
		int[] iBreite ={120,25,25,400};

		this.get_buttonStartSearch().setDisabledIcon(E2_ResourceIcon.get_RI("suche__.png"));
		this.get_buttonErase().setDisabledIcon(E2_ResourceIcon.get_RI("eraser__.png"));

		this._clear()._setSize(iBreite)
					._a(this.get_tf_search_input()._w(120), 			new RB_gld()._ins(1)._center_mid())
					._a(this.get_buttonStartSearch(), 					new RB_gld()._ins(1)._center_mid())
					._a(this.get_buttonErase(),							new RB_gld()._ins(1)._center_mid())
					._a(this.get_gridContainer_to_show_searchResult(), 	new RB_gld()._ins(5,1,1,1)._left_mid());

	}

	public String getIdArtikelBez() {
		return idArtikelBez;
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.get_tf_search_input().setText(valueFormated);
		this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), valueFormated);
		this.set_c_vergleichswert_dbfeld(valueFormated);
	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {
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
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ARTIKEL_BEZ)p_result_record).get_ID_ARTIKEL_BEZ_cUF();
	}


	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicContainer();
	}



	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv)
			throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(this._find_componentMapCollector_i_belong_to() == null){
			idArtikelBez = unformated_MaskValue;
		}else if(S.isEmpty(unformated_MaskValue)){
			KFIX_K_M_Controller oControllerMap = new KFIX_K_M_Controller(this._find_componentMapCollector_i_belong_to());
			oControllerMap.do_mask_settings(this, "", true);
		}
		else{
			Rec20 recArtikelBez = new Rec20(_TAB.artikel_bez)._fill_id(unformated_MaskValue);
			Rec20 recArtikel = new Rec20(_TAB.artikel)._fill_id(recArtikelBez.get_myLong_dbVal(ARTIKEL_BEZ.id_artikel).get_lValue());
			
			if(map instanceof KFIX_P_M__ComponentMap){

				String einheit_preis = new Rec20(_TAB.einheit)._fill_id(recArtikel.get_ufs_dbVal(ARTIKEL.id_einheit_preis)).get_fs_dbVal(EINHEIT.einheitkurz);
				String einheit = new Rec20(_TAB.einheit)._fill_id(recArtikel.get_ufs_dbVal(ARTIKEL.id_einheit)).get_fs_dbVal(EINHEIT.einheitkurz);
				
				String text_4_info = recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.artbez1)
						+ " (" + recArtikel.get_fs_dbVal(ARTIKEL.anr1)
						+"/"+ recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.anr2) + ")";
				
				KFIX_P_M_Controller oControllerMap = new KFIX_P_M_Controller(this._find_componentMapCollector_i_belong_to());
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.anr1, recArtikel.get_fs_dbVal(ARTIKEL.anr1), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.anr2, recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.anr2), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.id_artikel, recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.id_artikel), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.mengendivisor, recArtikel.get_fs_dbVal(ARTIKEL.mengendivisor), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.einheit_preis_kurz, einheit_preis, mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.artbez1, recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.artbez1,  "-"), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.artbez2, recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.artbez2,  "-"), mv);
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), VPOS_KON.einheitkurz, einheit, mv);
			
				oControllerMap._refresh().set_maskVal(new RB_KM(_TAB.vpos_kon), KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_SORTE.key(), text_4_info, mv);
				
			}else{
				KFIX_K_M_Controller oControllerMap = new KFIX_K_M_Controller(this._find_componentMapCollector_i_belong_to());
				oControllerMap.do_mask_settings(this, unformated_MaskValue, true);
				
				mv._add(new KFIX_K_M_SetAndValid_einheit().make_Interactive_Set_and_Valid(map, VALID_TYPE.USE_IN_MASK_KLICK_ACTION, null));
			}
		}		

		return mv;
	}



	private class ownBasicContainer extends E2_BasicModuleContainer {
	}

	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		String adresseId = "";

		if(map instanceof KFIX_P_M__ComponentMap){
			adresseId = ((KFIX_P_M__ComponentMap)map).getRec_vkopf().get_id_adresse();
		}else{
			adresseId = ((KFIX_K_M_Adresse_SearchField)map.get__Comp(VKOPF_KON.id_adresse).c()).getIdAdresse();
		}

		if(S.isFull(adresseId)){
			return new RECORD_ADRESSE_extend(adresseId);
		}
		return null;
	}
}
