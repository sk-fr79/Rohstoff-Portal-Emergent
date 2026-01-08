package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_Search_Kontrakt_Angebot_in_ONE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RECORD_VPOS_KON_ext;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RECORD_VPOS_STD_ext;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;


public class WE_CO_search_kontrakt_angebot extends FZ_MASK_Search_Kontrakt_Angebot_in_ONE  {

	public WE_CO_search_kontrakt_angebot(SEARCH_EK_OR_VK p_searchTyp, RB_TextField _tf_kontrakt, RB_TextField _tf_angebot,int i_width) throws myException {
		super(p_searchTyp, _tf_kontrakt, _tf_angebot);
		
		this.get_tf_search_input().focus_on();
		this.get_tf_search_input().setWidth(new Extent(i_width));
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		
		this.get_labelAng().focus_off();
		this.get_labelKon().focus_off();
		this.get_labelempty().focus_off();
		
		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_top();
		this._clear()
			._a(get_tf_search_input(), gl)
			._a(get_grid4Label_kontrakt_oder_angebot(), gl)
			._a(this.get_buttonErase(), gl)
			._a(this.get_buttonStartSearch(), gl)
			._a(get_gridContainer_to_show_searchResult(), gl._c()._span(4))
			._setSize(120,20,20,20);
	}
	
	@Override
	public String get__actual_maskstring_in_view() throws myException {
		
		return this.get_tf_search_input().getText();
	}
	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		new _WE_Controller_kontrakt(this._find_componentMapCollector_i_belong_to()).fill_angebot_kontrakt(mv, unformated_MaskValue);
		
		return mv;
	}

	
	


	@Override
	public void render_search_result_visible_on_mask(E2_Grid grid_result_container, String c_result_value_4_db) throws myException {
		
		//jetzt den string auseinandernehmen
		String c_id_angebot = "";
		String c_id_kontrakt = "";
		
		if(S.isFull(c_result_value_4_db)) {
		
			if (c_result_value_4_db.startsWith("KON@")) {
				c_id_kontrakt = c_result_value_4_db.substring(4);
			} else if (c_result_value_4_db.startsWith("ANG@")) {
				c_id_angebot = c_result_value_4_db.substring(4);
			}
		}

		MyLong l_angebot = new MyLong(c_id_angebot);
		MyLong l_kontrakt = new MyLong(c_id_kontrakt);
		
		if (l_angebot.get_bOK()) {
			new RECORD_VPOS_STD_ext(new RECORD_VPOS_STD(l_angebot.get_lValue())).fill_grid_with_infos(grid_result_container);
		} else if (l_kontrakt.get_bOK()) {
			new RECORD_VPOS_KON_ext(new RECORD_VPOS_KON(l_kontrakt.get_lValue())).fill_grid_with_infos(grid_result_container);
		} else {
			grid_result_container.removeAll();
		}
		
	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		//hier nur zum loeschen erlaubt
		if (S.isEmpty(valueFormated)) {
			this.clear_component();
		} else {
			throw new myException("hier nicht verwenden !!");
		}
	}




	
	@Override
	protected MyLong get_id_adresse_station_in_search_moment() throws myException {
		//diese komponente ist in einem RB_ComponentMAP auf basis eines vektors registriert
		
		KEY_ATOM key = (KEY_ATOM)this.rb_ComponentMap_this_belongsTo().getOwnMaskKey();
	
		WE_CO_SearchStation_customer lieferant = (WE_CO_SearchStation_customer)
								this.rb_ComponentMap_this_belongsTo().rb_get_belongs_to().get(key.startstation()).getRbComponent(BEWEGUNG_STATION.id_adresse);
		
		if (lieferant!= null && lieferant.is_search_done_correct()) {
			if (S.isFull(lieferant.rb_readValue_4_dataobject())) {
				MyLong id_adresse = new MyLong(lieferant.rb_readValue_4_dataobject());
				if (id_adresse.get_bOK()) {
					return id_adresse;
				}
			}
		}
		return null;
	}


	@Override
	protected MyLong get_id_artikel_bez_in_searchmoment() throws myException {
		KEY_ATOM key = (KEY_ATOM)this.rb_ComponentMap_this_belongsTo().getOwnMaskKey();

		MyLong l_id_artbez = new _WE_Controller_kontrakt(this._find_componentMapCollector_i_belong_to()).get_MyLong_maskVal(key, BEWEGUNG_ATOM.id_artikel_bez);
		
		if (l_id_artbez != null && l_id_artbez.isOK()) {
			Rec20 rec_art_bez = new Rec20(_TAB.artikel_bez)._fill_id(l_id_artbez.get_lValue());
			return rec_art_bez.get_myLong_dbVal(ARTIKEL_BEZ.id_artikel_bez);
		}
		return null;
	}

}
