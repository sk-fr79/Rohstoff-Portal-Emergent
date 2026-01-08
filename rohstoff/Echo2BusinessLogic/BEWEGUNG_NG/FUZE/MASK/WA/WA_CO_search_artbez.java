package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchArtbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public class WA_CO_search_artbez extends RB_HL_SearchArtbez  {
	
	private E2_Grid4MaskSimple  add_on_grid = new E2_Grid4MaskSimple();
	private ENUM_VEKTORPOS_TYP pos_typ = null;
	
	public WA_CO_search_artbez(ENUM_VEKTORPOS_TYP typ, int width) throws myException {
		super();

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
			
			WA_CO_search_artbez oThis = WA_CO_search_artbez.this;
			
			WA_CM__Collector cm_collector = (WA_CM__Collector)oThis._find_componentMapCollector_i_belong_to();
			
			//auf jeden fall muss das datum gefuellt sein
			MyDate date = new MyDate(cm_collector.get_atom_left().getRbComponentSavable(BEWEGUNG_ATOM.leistungsdatum).rb_readValue_4_dataobject()) ;
			
			if (!date.get_bOK()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ladedatum MUSS vor der Sorte gefüllt sein !")));
			}
			
			return mv;
		}
		
	}

	
	
	@Override
	public String get__actual_maskstring_in_view() throws myException {	
		return this.get_tf_search_input().getText();
	}
	
	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicContainer();
	}

	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_artBez, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		new _WA_Controller_Artikel(this._find_componentMapCollector_i_belong_to()).fill_artikel(id_artBez, mv);
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
			new RECORD_ARTIKEL_BEZ_ext(c_result_value_4_db).get_ARTBEZ1_cF_NN(""), new E2_FontItalic(-2),true);				
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
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		StringSeparator 	v_separator = new StringSeparator(search_text," ");
		
		//suchbedingungen fuer die suche in der artikelbez-tabelle
		Vector<String>      v_wheres_artbez = new Vector<String>();
		String 				search_block_with_placeholder_artbez = this.and_statement_collector_4_search().s();
		for (String s: v_separator) {
			v_wheres_artbez.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder_artbez, "#WERT#", s.trim())+") ");
		}
		String sql_where_only_search_artikelbez = bibALL.Concatenate(v_wheres_artbez, " AND ", "1=1");   //nur der suchanteil
		String where_statement_only_activ_artbez = "("+this.and_statement_collector_4_basic().s()+")";
		
		
		SEL  sel_artbez1 = new SEL(_TAB.artikel_bez)
							.FROM(_TAB.artikel_bez).INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel)
							.WHERE(new TermSimple(where_statement_only_activ_artbez))
							.AND(new TermSimple(sql_where_only_search_artikelbez));
		
		String c_sql_query = sel_artbez1.s()+" ORDER BY "+ARTIKEL_BEZ.artbez1.fn();
		
		DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		
		RECLIST_ARTIKEL_BEZ	rl = this.get_iMaxResults()>0?new RECLIST_ARTIKEL_BEZ(c_sql_query,this.get_iMaxResults(),true):new RECLIST_ARTIKEL_BEZ(c_sql_query);
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();
		
		E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch_black_and_grey();
		
		for (int i=0;i<rl.get_vKeyValues().size();i++) {
			RECORD_ARTIKEL_BEZ  rec_art_bez = rl.get(i);
			RB_ResultButton[] res_zeile = new RB_ResultButton[4];         //anr1, anr2, artbez1, id_artbez

			E2_MutableStyle style_active = 			style;
			
			String sort_id = ("00000000000000000000000000000000").substring(rec_art_bez.get_ID_ARTIKEL_BEZ_cF().length())+rec_art_bez.get_ID_ARTIKEL_BEZ_cF();
			
			res_zeile[0]= new own_result_button(this,rec_art_bez, rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN(""),style_active,
																								rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("")+rec_art_bez.get_ANR2_cUF_NN(""));
			res_zeile[1]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ANR2_cUF_NN(""), style_active,null);
			res_zeile[2]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ARTBEZ1_cUF_NN(""), style,null);
			res_zeile[3]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ID_ARTIKEL_BEZ_cF(), style,sort_id);
			
			v_result_arrays.add(res_zeile);
			
		}
		return mv;
	}
	
	
	
	
	
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons) throws myException {
		
		Vector<Component>  v_buttons = new Vector<Component>();
		v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("ANR1"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("ANR2"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Artbez1"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,3,  new MyE2_String("ID"),null));
		
		
		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<4 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		E2_Grid4MaskSimple gs = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		gs  .def_(new E2_ColorDark()).def_(new Alignment(Alignment.LEFT,Alignment.TOP))
			.def_(50).add_(v_buttons.get(0))
			.def_(50).add_(v_buttons.get(1))
			.def_(300).add_(v_buttons.get(2)).right_top_()
			.def_(50).add_(v_buttons.get(3))
			.def_(new E2_ColorBase());
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs	.def_(new Alignment(Alignment.LEFT,Alignment.TOP))
				.add_(arr[0].me()).add_(arr[1].me()).add_(arr[2].me())
				.def_(new Alignment(Alignment.RIGHT,Alignment.TOP)).add_(arr[3].me());
		}
		gs.setSize_(4);
	
		grid_4_popup.removeAll();
		grid_4_popup.add(this.add_on_grid,E2_INSETS.I(0,0,0,4));
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}

	
	private class own_result_button extends RB_ResultButton {
		private String sort_string = null;

		public own_result_button(	RB_SearchFieldSaveable 		calling_searchField, 
									MyRECORD_IF_RECORDS p_result_record, 
									String 				cText,		
									E2_MutableStyle 	oStyle,
									String 				sortstring) throws myException {
			super(calling_searchField, p_result_record, cText, oStyle);
			this.sort_string = sortstring;
			
			if (S.isEmpty(this.sort_string)) {
				this.sort_string = cText;
			}
			
			this.add_oActionAgent(new RB_ResultButton_action(p_result_record, calling_searchField));
		}
		
		@Override
		public String get_sort_string() throws myException {
			return this.sort_string;
		}

		@Override
		public Component me() throws myException {
			return this;
		}
	}




	@Override
	public RECORD_ADRESSE_extend findActualReferencedAdress(MyE2_MessageVector mv) throws myException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

//	@Override
//	public void fill_component_4_mask(	RB_TextField 	tf_search_input,	
//										MyE2_Grid 		gridcontainer_4_search_results, 
//										MyE2_Button 	buttonStartSearch, 
//										MyE2_Button 	buttonErase, 
//										Vector<Component> add_on_components) throws myException {
//		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_mid();
//		this._clear()
//			._a(tf_search_input, gl)
//			._a(buttonStartSearch, gl)
//			._a(buttonErase, gl)
//			._a(gridcontainer_4_search_results, gl._c()._span(3))
//			._setSize(120,20,20);
//		
//	}
//	
	
	
	
}
