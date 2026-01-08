package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public class WE_CO_search_artbez extends RB_HL_SearchArtbez  {

	
	public WE_CO_search_artbez(ENUM_VEKTORPOS_TYP pos_typ, int width) throws myException {
		super();
		this.get_buttonStartSearch().add_GlobalValidator(new own_validator_check_input_order());
		this.set_show_bt_add_all_found_artikel_bez(true);
		
		this.get_tf_search_input().setWidth(new Extent(width));
		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		
		this._allowEmptySearchfield(true);
		this._setArtbezSearchEkOrVk(EnArtbezSearchEkOrVk.EK);
		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_RESTRICTED_AND_ALLOW_ADDING);
		
		this.result_to_bottom();
		
	}

	
	private class own_validator_check_input_order extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			
			__WE_MASTER_KEY key = ((WE_CM__Collector)WE_CO_search_artbez.this.rb_ComponentMap_this_belongsTo().rb_get_belongs_to()).get_master_key();
			
			//komponenten beschaffen
			WE_CO_search_artbez oThis = WE_CO_search_artbez.this;
			RB_SearchFieldSaveable  compID_ADRESSE_START = (RB_SearchFieldSaveable)RB__TOOLS.find_neighbor(oThis, new RB_KF(BEWEGUNG_STATION.id_adresse), 	key.k_atom_left__station_start());
			RB_SearchFieldSaveable  compID_ADRESSE_ZIEL = (RB_SearchFieldSaveable)RB__TOOLS.find_neighbor(oThis, new RB_KF(BEWEGUNG_STATION.id_adresse), 	key.k_atom_right__station_ziel());
			
			if (!compID_ADRESSE_START.is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst den Lieferanten erfassen")));
			}
			if (!compID_ADRESSE_ZIEL.is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst das Lager erfassen")));
			}
			return mv;
		}
		
	}



	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicContainer();
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}


	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_artBez, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		new _WE_Controller_Artikel(this._find_componentMapCollector_i_belong_to()).fill_artikel(id_artBez, mv);
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

	

	@Override
	public String get__actual_maskstring_in_view() throws myException {
		return this.get_tf_search_input().getText();
	}

	
	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		if (this.is_search_done_correct()) {
			return this.get_tf_search_input().getText();
		} else {
			return "";
		}
	}

	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		_WE_MASK_Controller  mc = new _WE_MASK_Controller(this._find_componentMapCollector_i_belong_to());
		
		MyLong l_id_adresse = mc.get_MyLong_maskVal(mc.get_masterKey().k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse);

		RECORD_ADRESSE_extend rec_ad = null;
		
		if (l_id_adresse!=null && l_id_adresse.isOK()) {
			return new RECORD_ADRESSE_extend(l_id_adresse.get_lValue());
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Achtung! Keine Adresse gefunden")));
		}
		return rec_ad;
	}

	
}
