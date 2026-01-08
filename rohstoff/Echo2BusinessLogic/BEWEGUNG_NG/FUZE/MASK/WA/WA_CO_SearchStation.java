package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchAdressStation;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class WA_CO_SearchStation extends RB_HL_SearchAdressStation  {

	private ENUM_STATION_TYP  typ_station = null;


	public WA_CO_SearchStation(ENUM_STATION_TYP start_or_ziel, int i_width) throws myException {
		super(false);
		RB_gld gl = new RB_gld()._ins(0,0,5,0)._left_top();
		RB_gld gl2 = new RB_gld()._ins(0,0,0,2)._left_top();
		
		this._clear()._setSize(95,20,20,20)
		._a(this.get_tf_search_input(), gl)
		._a(this.get_buttonErase(), gl2)
		._a(this.get_buttonStartSearch(), gl2)
		._a(this.get_button_to_open_mask_to_referenced_record(), gl2)
		._a(this.get_gridContainer_to_show_searchResult(), gl2._c()._span(4));

		this.typ_station = start_or_ziel;

		this.get_tf_search_input()._w(i_width);

		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		this.get_button_to_open_mask_to_referenced_record().focus_off();


	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownModulContainer();
	}



	private class ownModulContainer extends E2_BasicModuleContainer {
	}




	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_adresse, boolean aktiv) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();

		new _WA_Mask_ControllerMap(this)._fill_adresse_feldern(mv);
		
		return mv;
	}




	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ADRESSE)p_result_record).get_ID_ADRESSE_cUF();
	}

	public ENUM_STATION_TYP is_start_or_ziel() {
		return typ_station;
	}



	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {

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

//	@Override
//	public MyRECORD_IF_RECORDS get_result_record_from_string(String unformated_MaskValue) throws myException {
//		return new RECORD_ADRESSE(unformated_MaskValue);
//	}
}
