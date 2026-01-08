package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
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

public class WE_CO_search_own_station extends RB_HL_Select_Liefer_adressen  {
	
	private ENUM_STATION_TYP  typ_station = null;

	public WE_CO_search_own_station(ENUM_STATION_TYP start_or_ziel, int i_width) throws myException {
		super(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
		this.typ_station = start_or_ziel;
		
		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		this.get_button_to_open_mask_to_referenced_record().focus_off();
		
		this.get_tf_search_input()._w(i_width);
		RB_gld gl = new RB_gld()._ins(0,0,5,0)._left_top();
		RB_gld gl2 = new RB_gld()._ins(0,0,2,0)._left_top();
		this._clear()
		._a(get_tf_search_input(), gl)
		._a(this.get_buttonErase(), gl2)
		._a(this.get_buttonStartSearch(), gl2)
		._a(this.get_button_to_open_mask_to_referenced_record(), gl2)
		._a(this.get_gridContainer_to_show_searchResult(), gl._c()._span(4))
		._setSize(64,20,20, 20);
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicModuleContainer();
	}

	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
	}
	
	
	
	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_adresse, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

//		RB_ComponentMap  map_s = this.rb_ComponentMap_this_belongsTo();
		
//		MyLong l = new MyLong(id_adresse);
		
		new _WE_MASK_Controller(this).fill_ownStation_followers(mv);
		
//		if (l.get_bOK()) {
//		
//			RECORD_ADRESSE_extend  recFound = new RECORD_ADRESSE_extend(l.get_lValue());
//			RECORD_ADRESSE_extend  recMain = recFound.get_main_Adress();
//			RECORD_LIEFERADRESSE  liefer = null;
//			RECORD_FIRMENINFO     rec_fi = null;
//			if (recFound.is_station_adress()) {
//				liefer = recFound.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0);
//			} else {
//				rec_fi = recFound.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
//			}
//			
//			IF_generate_RB_KF K = (field) -> {return new RB_KF(field);};
//				
//			map_s.rb_setValue(BEWEGUNG_STATION.laendercode, 				recFound.get_UP_RECORD_LAND_id_land()!=null?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"");
//			map_s.rb_setValue(BEWEGUNG_STATION.id_adresse_basis, 			recMain.get_ID_ADRESSE_cUF());
//			((RB_SearchField)map_s.rb_Component(BEWEGUNG_STATION.id_adresse_besitzer)).show_from_outside(recMain.ufs(ADRESSE.id_adresse),true);
//			map_s.rb_setValue(BEWEGUNG_STATION.name1, recFound.fs(ADRESSE.name1,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.name2, recFound.fs(ADRESSE.name2,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.name3, recFound.fs(ADRESSE.name3,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.strasse, recFound.fs(ADRESSE.strasse,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.hausnummer, recFound.fs(ADRESSE.hausnummer,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.ort, recFound.fs(ADRESSE.ort,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.ortzusatz, recFound.fs(ADRESSE.ortzusatz,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.oeffnungszeiten, liefer==null?rec_fi.fs(FIRMENINFO.oeffnungszeiten,""):liefer.fs(LIEFERADRESSE.oeffnungszeiten,""));
//			map_s.rb_setValue(BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""));
//			
//			String c_tel_std = recFound.get_StandardTelefonNumber();
//			String c_fax_std = recFound.get_StandardFaxNumber();
//			
//			if (S.isEmpty(c_tel_std)) {
//				c_tel_std = recMain.get_StandardTelefonNumber();
//			}
//			
//			if (S.isEmpty(c_fax_std)) {
//				c_fax_std = recMain.get_StandardFaxNumber();
//			}
//			
//			map_s.rb_setValue(K.K(BEWEGUNG_STATION.telefon), S.NN(c_tel_std));
//			map_s.rb_setValue(K.K(BEWEGUNG_STATION.fax), S.NN(c_fax_std));
//
//		} else {
//			
//			map_s.rb_setValue(BEWEGUNG_STATION.laendercode, 				"");
//			map_s.rb_setValue(BEWEGUNG_STATION.id_adresse_basis, 			"");
//			((RB_SearchField)map_s.rb_Component(BEWEGUNG_STATION.id_adresse_besitzer)).show_from_outside(null,true);
//			map_s.rb_setValue(BEWEGUNG_STATION.name1, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.name2, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.name3, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.strasse, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.hausnummer, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.plz, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.ort, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.ortzusatz, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.oeffnungszeiten, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.plz, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.telefon, "");
//			map_s.rb_setValue(BEWEGUNG_STATION.fax, "");
//		}

		
		return mv;
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
//	}


	
}
