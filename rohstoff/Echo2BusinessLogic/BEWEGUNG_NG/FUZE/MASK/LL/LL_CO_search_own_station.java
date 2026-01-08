package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_arrangeable;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Select_Liefer_adressen;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class LL_CO_search_own_station extends RB_HL_Select_Liefer_adressen {
	
	private ENUM_STATION_TYP  typ_station = null;

	public LL_CO_search_own_station(ENUM_STATION_TYP start_or_ziel,int i_width) throws myException {
		super(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
		this.typ_station = start_or_ziel;
		this.get_tf_search_input()._w(i_width);
		this.ArrangeAdressSearchField.arange(this);
		
		this.get_buttonErase().add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (LL_CO_search_own_station.this.typ_station==ENUM_STATION_TYP.START_STATION) {
					new _LL_Mask_ControllerMap(LL_CO_search_own_station.this).fill_adresse_start_followers(bibMSG.MV());
				} else {
					new _LL_Mask_ControllerMap(LL_CO_search_own_station.this).fill_adresse_ziel_followers(bibMSG.MV());
				}
			}
		});
		
	}

	
	/**
	 * formatiert ein adress-suchfeld immer gleiche, gesamtbreite <200 pixel, suchfeld, search, erase, edit, darunter info ueber gefundenen satz 
	 */
	private IF_arrangeable<RB_SearchField> ArrangeAdressSearchField = (sf) -> {
				
		//auf eine linie ausrichten
		RB_gld gl = new RB_gld()._ins(0,0,5,0)._left_top();
		RB_gld gl2 = new RB_gld()._ins(0,0,2,0)._left_top();
		sf._clear()
			._a(sf.get_tf_search_input(), gl)
			._a(sf.get_buttonErase(), gl2)
			._a(sf.get_buttonStartSearch(), gl2)
			._a(((IF_RB_ResultButton_can_edit_searched_record)sf).get_button_to_open_mask_to_referenced_record(), gl2)
			._a(sf.get_gridContainer_to_show_searchResult(), gl._c()._span(4))
			._setSize(95,20,20,20);

		return sf;};


	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicModuleContainer();
	}

	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
	}
	
	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_adresse, boolean aktiv) throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		new _LL_Mask_ControllerMap(this._find_componentMapCollector_i_belong_to()).fill_adresse_feldern(this.typ_station, mv);

		return mv;
	}

	
	
	@Override
	public void render_search_result_visible_on_mask(	E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {
		
		gridcontainer_4_search_results.removeAll();
		MyE2_Label lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new MyE2_Label(" ");
		} else {
			RECORD_ADRESSE_extend  recAdresse = new RECORD_ADRESSE_extend(c_result_value_4_db);
			String label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("Hauptadresse").CTrans()+")";
			if (!recAdresse.is_main_adress()) {
				RECORD_ADRESSE_extend rh = recAdresse.get_main_Adress();
				label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("bei ").CTrans()+rh.get__FullNameAndAdress_Typ2()+")";
			}
			lab_help=new MyE2_Label(label_txt, new E2_FontItalic(-2),true);	
		}
		
		gridcontainer_4_search_results._a(lab_help, new RB_gld());
		gridcontainer_4_search_results.setBorder(null);
	}



	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ADRESSE)p_result_record).get_ID_ADRESSE_cUF();
	}
	
	public ENUM_STATION_TYP is_start_or_ziel() {
		return typ_station;
	}

	

	

//	@Override
//	public void rb_set_db_value_manual(String valueFormated) throws myException {
//		this.get_tf_search_input().setText(valueFormated);
//		this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), valueFormated);
//		this.set_c_vergleichswert_dbfeld(valueFormated);
//		
//
//	}





	

	
}
