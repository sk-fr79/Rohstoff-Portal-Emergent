/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 10.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

/**
 * @author sebastien
 * @date 10.12.2018
 *
 */
public class LH_P_MASK_Comp_fuhreSearch extends RB_SearchFieldSaveable {

	private SEL select_clause = null;

	private RB_lab 			labelname_4_result 		=  new RB_lab()._i()._lwn()._t(" ");
//	private RB_lab 			labelartikel_4_result 	=  new RB_lab()._i()._lwn();
	private boolean 		bEingangsFuhre 			= true;
	/**
	 * @author sebastien
	 * @date 10.12.2018
	 *
	 * @throws myException
	 */
	public LH_P_MASK_Comp_fuhreSearch(int i_sch_field_lght, boolean isEingangsFuhre) throws myException {
		super();

		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_mid();

		this.bEingangsFuhre = isEingangsFuhre;
		this.get_tf_search_input()._w(i_sch_field_lght);
		this._clear()._a(get_tf_search_input(), gl);
		if(bEingangsFuhre) {
			this._a(get_buttonErase(), gl);
		}else {
			this._a();
		}
		this
		._a(get_buttonStartSearch(), gl)
		._a(new E2_Grid()._s(1)._w100()._setRowHight(20,20)._bo_col(new E2_ColorDark())
				._a(labelname_4_result, gl._c()._left_top())
				//				._a(labelartikel_4_result, gl._c()._left_top())
				,gl._c()._left_top()._span(3)
				)
		._setSize(i_sch_field_lght,20,20);

		this.select_clause = 
				new SEL()
				.FROM(_TAB.vpos_tpa_fuhre);

		/*	this.select_clause.INNERJOIN(_TAB.artikel_bez, 
					(bEingangsFuhre)?VPOS_TPA_FUHRE.id_artikel_bez_ek:VPOS_TPA_FUHRE.id_artikel_bez_vk, 
					ARTIKEL_BEZ.id_artikel_bez)
				;
		this.select_clause.INNERJOIN(_TAB.artikel, 
				ARTIKEL_BEZ.id_artikel, 
				ARTIKEL.id_artikel)
			;*/

		this.and_statement_collector_4_search().set_CoverInBrackets(false);
		this.and_statement_collector_4_search().and(new vgl_YN(VPOS_TPA_FUHRE.ist_storniert, false)).set_CoverInBrackets(false);
		this.and_statement_collector_4_search().and(new vgl_YN(VPOS_TPA_FUHRE.abgeschlossen, false)).set_CoverInBrackets(false);
		this.and_statement_collector_4_search().and(new vgl(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, 	COMP.LIKE,	new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(VPOS_TPA_FUHRE.buchungsnr_fuhre), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ARTIKEL_BEZ.anr2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ARTIKEL_BEZ.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ARTIKEL.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);


		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);
		//		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')"))).set_CoverInBrackets(false);

	}


	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownContainer();
	}


	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ""+p_result_record.get_PRIMARY_KEY_VALUE();
	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid grid, String c_result_value_4_db)
			throws myException {
		if(S.isFull(c_result_value_4_db)) {
			Rec21 recVposTpaFuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(c_result_value_4_db);

			String name1ort 		= "";
//			String artikel_detail	= "";
			if(bEingangsFuhre) {
				name1ort = new Rec21_adresse(recVposTpaFuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_start, ADRESSE.id_adresse, false))
						.__get_name1_ort();
			}
			else {
				name1ort = new Rec21_adresse(recVposTpaFuhre.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_ziel, ADRESSE.id_adresse, false))
						.__get_name1_ort();
//				artikel_detail = new Rec21_artikel_bez()._fill_id(recVposTpaFuhre.getUfs(VPOS_TPA_FUHRE.id_artikel_bez_vk)).__get_ANR1_ANR2_ARTBEZ1();
			}
			this.labelname_4_result._t(name1ort);
//			this.labelartikel_4_result._t(artikel_detail);
			if(this.rb_ComponentMap_this_belongsTo() != null) {
				this.rb_ComponentMap_this_belongsTo()._find_component_in_neighborhood(LAGER_PALETTE.buchungsnr_hand).rb_set_db_value_manual(recVposTpaFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre,""));
			}
		}
	}

	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv)
			throws myException {

		MyLong l = new MyLong(unformated_MaskValue);

		if (S.isFull(unformated_MaskValue) && l.get_bOK() && bEingangsFuhre) {
			Rec21 oFuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(unformated_MaskValue);

			String id_artbez_ek = oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek);
			String buchungs_nr = oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre);
			this.rb_ComponentMap_this_belongsTo()._find_component_in_neighborhood(ARTIKEL_BEZ.id_artikel_bez).rb_set_db_value_manual(id_artbez_ek);
			this.rb_ComponentMap_this_belongsTo()._find_component_in_neighborhood(LAGER_PALETTE.buchungsnr_hand).rb_set_db_value_manual(buchungs_nr);
		}
		return new MyE2_MessageVector();
	}

	@Override
	public XX_ActionAgent generate_StartSearchAction() throws myException {
		return new RB_SearchField_actionStartSearch(this);
	}

	@Override
	public XX_ActionAgent generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				clear_content();
			}
		};
	}

	public void clear_content() throws myException{
		this.labelname_4_result._t("");
		this.rb_set_db_value_manual("", true,true);
	}

	@Override
	protected MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		VEK<String>      v_wheres = new VEK<String>();

		if (S.isFull(search_text)) {
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			for (String s: v_separator) {
				v_wheres.add(" ("+bibALL.ReplaceTeilString(this.and_statement_collector_4_search().s(), "#WERT#", s.trim())+") ");
			}
		}

		RecList21 ergebnisse_record = new RecList21(_TAB.vpos_tpa_fuhre)._fill(this.select_clause.s() + " WHERE " + bibALL.Concatenate(v_wheres, " AND ", "1=1"));

		if(ergebnisse_record.size()>0) {
			RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
			v_result_arrays.clear();

			E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
			E2_MutableStyle styleRight = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
			styleRight.setProperty(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.RIGHT,Alignment.TOP));

			for(Rec21 fuhre_record : ergebnisse_record) {

				RB_ResultButton[] res_zeile = new RB_ResultButton[5];

				String id_adresse = "";
				String id_artikel_bez = "";
				String zielAdresse = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_ziel,"");
				String startAdresse = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_start,"");

				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre));
				sort_texte.add(fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre,"<-->"));

				if(bEingangsFuhre) {
					id_adresse = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_start,fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_start,""));
					id_artikel_bez = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_artikel_bez_ek);
					sort_texte.add(fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_start));
				}else {
					id_adresse = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_ziel,	fuhre_record.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_ziel,""));
					id_artikel_bez = fuhre_record.getUfs(VPOS_TPA_FUHRE.id_artikel_bez_vk);
					sort_texte.add(fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_ziel));
				}

				sort_texte.add(fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel,""));

				MyRECORD_IF_RECORDS rec_vp = fuhre_record;

				Rec21 rec_adresse = new Rec21(_TAB.adresse)._fill_id(id_adresse);
				String kunde = rec_adresse.get_ufs_kette(" " , ADRESSE.name1, ADRESSE.name2) + " - " + rec_adresse.get_ufs_dbVal(ADRESSE.ort);

				String sorte ="<-->";

				if(S.isFull(id_artikel_bez)) {
					Rec21 rec_artikel = new Rec21(_TAB.artikel_bez)._fill_id(id_artikel_bez);
					sorte = "("+ 
							rec_artikel.get_up_Rec21(ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel, false).getUfs(ARTIKEL.anr1,"")+"-"+
							rec_artikel.get_ufs_dbVal(ARTIKEL_BEZ.anr2,"") + ") " + rec_artikel.getUfs(ARTIKEL_BEZ.artbez1,"");
				}

				String waren_dir = "";
				String waren_ttt = "";
				if(zielAdresse.equals(bibALL.get_ID_ADRESS_MANDANT())) {
					waren_dir="WE";
					waren_ttt="Wareneingang";
				}else if(startAdresse.equals(bibALL.get_ID_ADRESS_MANDANT())) {
					waren_dir="WA";
					waren_ttt="Warenausgang";
				}else {
					waren_dir="ST";
					waren_ttt="Streckenfuhre";
				}

				res_zeile[0]= new own_result_button(this,rec_vp, fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre,""), 	style, sort_texte.get(0));
				res_zeile[1]= new own_result_button(this,rec_vp, waren_dir , 														style, null, waren_ttt);
				res_zeile[2]= new own_result_button(this,rec_vp, fuhre_record.get_ufs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre,"<-->"), 	style, sort_texte.get(1));
				res_zeile[3]= new own_result_button(this,rec_vp, kunde, 															style, sort_texte.get(2));
				res_zeile[4]= new own_result_button(this,rec_vp, sorte, 															style, sort_texte.get(3));

				v_result_arrays.add(res_zeile);
			}
		}

		return mv;
	}

	@Override
	public void fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo)
			throws myException {

		E2_Grid gs = new E2_Grid()._clear()._bo_no()._setSize(50,100,100,450,450);

		RB_gld gl_title_lt = new RB_gld()._left_top();

		VEK<Component>  v_buttons = new VEK<Component>();
		v_buttons
		._a(new RB_SearchFieldListSortButton(this,0,  S.ms("Fuhre ID"),null))
		._a(new RB_lab()._t("Transport typ"))
		._a(new RB_SearchFieldListSortButton(this,1,  S.ms("Buchungsnr."),null))
		._a(new RB_SearchFieldListSortButton(this,2,  S.ms("Kunde"),null))
		._a(new RB_SearchFieldListSortButton(this,3,  S.ms("Sorte"),null))
		;

		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();




		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<9 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}

		gs  
		._a(v_buttons.get(0), gl_title_lt)
		._a(v_buttons.get(1), gl_title_lt)
		._a(v_buttons.get(2), gl_title_lt)
		._a(v_buttons.get(3), gl_title_lt)
		._a(v_buttons.get(4), gl_title_lt)

		;		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs 	
			._a(arr[0].me(), gl_title_lt._col(new E2_ColorBase()))
			._a(arr[1].me(), gl_title_lt._col(new E2_ColorBase()))
			._a(arr[2].me(), gl_title_lt._col(new E2_ColorBase()))
			._a(arr[3].me(), gl_title_lt._col(new E2_ColorBase()))
			._a(arr[4].me(), gl_title_lt._col(new E2_ColorBase()))
			;
		}


		grid_4_popup.removeAll();
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}

	private class ownContainer extends E2_BasicModuleContainer{
	}

	private class own_result_button extends RB_ResultButton {
		private String sort_string = null;

		public own_result_button(	RB_SearchField		calling_searchField, 
				MyRECORD_IF_RECORDS p_result_record, 
				String 				cText,		
				E2_MutableStyle 	oStyle,
				String 				sortstring) throws myException {
			super(calling_searchField, p_result_record, cText, oStyle);
			this.sort_string = sortstring;

			this.add_oActionAgent(new RB_ResultButton_action(p_result_record, calling_searchField));

		}

		public own_result_button(	RB_SearchField		calling_searchField, 
				MyRECORD_IF_RECORDS p_result_record, 
				String 				cText,		
				E2_MutableStyle 	oStyle,
				String 				sortstring,
				String 				tooltipText) throws myException {
			super(calling_searchField, p_result_record, cText, oStyle);
			this.sort_string = sortstring;
			this._ttt(tooltipText);
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
}
