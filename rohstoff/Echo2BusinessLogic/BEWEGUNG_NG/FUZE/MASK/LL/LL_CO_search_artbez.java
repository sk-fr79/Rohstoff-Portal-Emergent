package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public class LL_CO_search_artbez extends RB_HL_SearchArtbez  {

	
	private ENUM_VEKTORPOS_TYP pos_typ = null;

	
	public LL_CO_search_artbez(ENUM_VEKTORPOS_TYP typ, int width) throws myException {
		super();

		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_ALL_ARTBEZ);
		this.set_show_bt_add_all_found_artikel_bez(false);
		
		this.pos_typ = typ;
		this.get_tf_search_input().setWidth(new Extent(width));

		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		
		this.get_buttonStartSearch().add_GlobalValidator(new own_validator());
		
		
		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_mid();
		this._clear()
			._a(get_tf_search_input(), gl)
			._a(get_buttonErase(), gl)
			._a(get_buttonStartSearch(), gl)
			._a(this.get_gridContainer_to_show_searchResult(), gl._c()._span(3))
			._setSize(120,20,20);

		
	}

	
	
	private class own_validator extends XX_ActionValidator_NG {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			LL_CO_search_artbez oThis = LL_CO_search_artbez.this;
			
			LL_CM__Collector cm_collector = (LL_CM__Collector)oThis._find_componentMapCollector_i_belong_to();
			
			//auf jeden fall muss das datum gefuellt sein
			MyDate date = new MyDate(cm_collector.get_start_atom().getRbComponentSavable(BEWEGUNG_ATOM.leistungsdatum).rb_readValue_4_dataobject()) ;
			
			if (!date.get_bOK()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ladedatum MUSS vor der Sorte gefüllt sein !")));
			}
			
			//startstation muss gefuellt sein
			if (!((LL_CO_search_own_station)cm_collector.get_atom_left__lager_left().getComp(BEWEGUNG_STATION.id_adresse)).is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Startlager MUSS vor der Sorte gefüllt sein !")));
			}

			//zielstation muss gefuellt sein
			if (!((LL_CO_search_own_station)cm_collector.get_atom_right__lager_ziel().getComp(BEWEGUNG_STATION.id_adresse)).is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ziellager MUSS vor der Sorte gefüllt sein !")));
			}
			
			return mv;
		}
		
	}

	
	
	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicContainer();
	}

	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_artBez, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if (this.pos_typ==ENUM_VEKTORPOS_TYP.LL_MAIN) {
			new _LL_Controller_Artikel(this._find_componentMapCollector_i_belong_to()).fill_artikel(id_artBez, mv);
		}
		return mv;
	}


	
	
	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {
		gridcontainer_4_search_results.removeAll();
		MyE2_Label lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new MyE2_Label(" ");
		} else {
			lab_help=new MyE2_Label(
			new RECORD_ARTIKEL_BEZ_ext(c_result_value_4_db).get__complete_anr1_anr2_artbez1(), new E2_FontItalic(-2),true);				
		}
		
		gridcontainer_4_search_results._a(lab_help, new RB_gld());
		gridcontainer_4_search_results.setBorder(null);
	}

	
	
	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ARTIKEL_BEZ)p_result_record).get_ID_ARTIKEL_BEZ_cUF();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}

	

	

	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		_LL_Mask_ControllerMap  mc = new _LL_Mask_ControllerMap(this._find_componentMapCollector_i_belong_to());
		
		MyLong l_id_adresse = mc.get_MyLong_maskVal(mc.get_masterKey().k_atom_left(), BEWEGUNG_STATION.id_adresse);
		
		if (l_id_adresse!=null && l_id_adresse.isOK()) {
			return new RECORD_ADRESSE_extend(l_id_adresse.get_lValue());
		}
		
		return null;
	}

	
	
	
}
