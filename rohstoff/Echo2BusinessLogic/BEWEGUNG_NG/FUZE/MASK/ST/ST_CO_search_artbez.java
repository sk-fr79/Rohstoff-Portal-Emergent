package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

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
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public class ST_CO_search_artbez extends RB_HL_SearchArtbez  {

	private ENUM_VEKTORPOS_TYP pos_typ = null;


	public ST_CO_search_artbez(ENUM_VEKTORPOS_TYP typ, int width) throws myException {
		super();

		this.pos_typ = typ;
		this.get_tf_search_input().setWidth(new Extent(width));

		this._setArtbezSearchTyp(EnArtbezSearchTyp.SHOW_RESTRICTED_AND_ALLOW_ADDING);


		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();

		this.get_buttonStartSearch().add_GlobalValidator(new own_validator());

		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_top();
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

			ST_CO_search_artbez oThis = ST_CO_search_artbez.this;

			ENUM_VEKTORPOS_TYP p_typ = oThis.pos_typ;

			ST___MASK_Controller ct = new ST___MASK_Controller(oThis._find_componentMapCollector_i_belong_to());

			//schluessel besorgen
			__ST_MASTER_KEY 	mKey = (__ST_MASTER_KEY)((KEY_ATOM)oThis.rb_ComponentMap_this_belongsTo().getOwnMaskKey()).get_root_key();
			
			KEY_ATOM 	aKey = null;
			if(p_typ==ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT){
				aKey = mKey.k_vektor_pos_left__atom_left();
			}else{
				aKey = mKey.k_vektor_pos_right__atom_right();
			}

			KEY_STATION sKey = null;
			if(p_typ==ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT){
				sKey = mKey.k_vektor_pos_left__atom_left__station_start();
			}else{
				sKey = mKey.k_vektor_pos_right__atom_right__station_ziel();
			}


			//auf jeden fall muss das datum gefuellt sein
			MyDate date =  ct.get_MyDate_maskVal(aKey,BEWEGUNG_ATOM.leistungsdatum);
			if (date==null || date.isNotOK()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Datum MUSS vor der Sorte gefüllt sein !")));
			}

			//station muss vor dem datum gefuellt sein
			MyLong id_lager = ct.get_MyLong_maskVal(sKey, BEWEGUNG_STATION.id_adresse); 
			if (id_lager==null || id_lager.isNotOK()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Stationsadresse MUSS vor der Sorte gefüllt sein !")));
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
		if(pos_typ==ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT){
			new _ST_Controller_Artikel_Quelle(this._find_componentMapCollector_i_belong_to(), SEARCH_EK_OR_VK.EK).fill_artikel(id_artBez, mv);
		}else{
			new _ST_Controller_Artikel_Ziel(this._find_componentMapCollector_i_belong_to(), SEARCH_EK_OR_VK.VK).fill_artikel(id_artBez, mv);
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
	public String get__actual_maskstring_in_view() throws myException {
		
		return this.get_tf_search_input().getText();
	}


	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		ST___MASK_Controller  mc = new ST___MASK_Controller(this._find_componentMapCollector_i_belong_to());

		MyLong l_id_adresse  = null;

		if (this.pos_typ==ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT) {
			l_id_adresse = mc.get_MyLong_maskVal(mc.get_masterKey().k_vektor_pos_left__atom_left__station_start(), BEWEGUNG_STATION.id_adresse);
		} else {
			l_id_adresse = mc.get_MyLong_maskVal(mc.get_masterKey().k_vektor_pos_right__atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse);
		}


		if (l_id_adresse!=null && l_id_adresse.isOK()) {
			return new RECORD_ADRESSE_extend(l_id_adresse.get_lValue());
		}

		return null;
	}



}
