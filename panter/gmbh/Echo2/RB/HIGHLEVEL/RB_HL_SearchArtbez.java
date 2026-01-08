package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SaveSortOfPopup;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_add_artikelBez_to_Company;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public abstract class RB_HL_SearchArtbez extends RB_SearchFieldSaveable {


	public static enum  EnArtbezSearchTyp {
		SHOW_ALL_ARTBEZ
		,SHOW_ONLY_RESTRICTED_ARTBEZ
		,SHOW_RESTRICTED_AND_ALLOW_ADDING
	}
	
	
	public static enum EnArtbezSearchEkOrVk {
		EK
		,VK
	}
	
	
	/**
	 * 
	 * @return  RECORD_ADRESSE der aktuellen startadresse (kann eine Lageradresse oder eine hauptadresse sein
	 * @throws myException
	 */
	public abstract RECORD_ADRESSE_extend     	findActualReferencedAdress(MyE2_MessageVector mv) throws myException;
   
	private EnArtbezSearchTyp          			artbezSearchTyp = EnArtbezSearchTyp.SHOW_RESTRICTED_AND_ALLOW_ADDING;
	private EnArtbezSearchEkOrVk 				artbezSearchEkOrVk = EnArtbezSearchEkOrVk.EK;		
	
	//variablen werden bei jedem suchvorgang gefuellt
	private RECLIST_ARTIKEL_BEZ	  				rl_such_ergebnis = null;
	private Vector<RECORD_ARTIKEL_BEZ>  		v_artikel_records_found_from_search =    	new Vector<>();
	private Vector<RECORD_ARTIKEL_BEZ>  		v_artikel_records_mark_active =    		new Vector<>();
	private Vector<String> 						v_id_artikel_in_adress_range = 	new Vector<>();
	private RECORD_ADRESSE_extend       		record_haupt_adresse = null;

	private MyE2_CheckBox       				cb_show_all = (MyE2_CheckBox)new MyE2_CheckBox(new MyE2_String("Auch nicht gelistete Suchergebnisse anzeigen"), false, false)._aaa(new ownActionClickCheckbox_not_listed_artikel());

	//zusatzgrid fuer die steuerungen oberhalb der liste
	private E2_Grid    							add_on_grid = new E2_Grid();
	
	private boolean   							show_bt_add_all_found_artikel_bez = true;
	
	
	//wenn genau eine erlaubte artikel-bez gefunden wird, dann diese laden
	private String   							single_found_artbez = null;    		
	private own_result_button       			single_result_button = null;
	
	
	private String     			    			last_sql_search_query = null;
	
	public RB_HL_SearchArtbez() throws myException {
		super();
		this._init();
		this.get_buttonStartSearch().add_oActionAgent(new actionUnsetCheckboxShowAllFoundArtikel(),true);
	}

	public RB_HL_SearchArtbez(boolean bShowEraser) throws myException {
		super(bShowEraser);
		this._init();
		this.get_buttonStartSearch().add_oActionAgent(new actionUnsetCheckboxShowAllFoundArtikel(),true);
	}

	
	private void _init() throws myException {
		//basis-selektion
		this.and_statement_collector_4_basic().and(new vgl_YN(ARTIKEL_BEZ.aktiv, true));
		
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL.anr1), 		COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL_BEZ.anr2), 	COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL_BEZ.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL_BEZ.artbez2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.TO_CHAR,ARTIKEL_BEZ.id_artikel_bez), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL.artbez1), 		COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ARTIKEL.artbez2), 		COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		
		this.set_iMaxResults(5000);    //bei zu wenigen, kann eine leere suchmaske dazu fuehren, dass die gelisteten der firma hinter den angezeigten liegt und damit nicht sichtbar wird
		
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHARTBEZ);
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
		
		MyE2_Grid  g =lab_help.get_InBorderGrid(new Border(1, new E2_ColorDark(), Border.STYLE_SOLID), new Extent(200), E2_INSETS.I(0,0,0,0));
		g.setRowHeight(0, new Extent(20));
		gridcontainer_4_search_results.add(g);
	}

	
	public  XX_ActionAgent  	generate_StartSearchAction() throws myException {
		return new own_searchActionAgent();
	}
	
	
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(RB_HL_SearchArtbez.this.rb_set_db_value_manual("",true,true));
			}
		};
	}



	

//	@Override
//	public  MyRECORD_IF_RECORDS  get_result_record_from_string(String unformated_MaskValue) throws myException {
//		return new RECORD_ARTIKEL_BEZ(unformated_MaskValue);
//	}
//
//	


	

	
	
	
	public EnArtbezSearchTyp getArtbezSearchTyp() {
		return this.artbezSearchTyp;
	}

	public RB_HL_SearchArtbez _setArtbezSearchTyp(EnArtbezSearchTyp p_artbez_Search_typ) {
		this.artbezSearchTyp = p_artbez_Search_typ;
		return this;
	}

	public EnArtbezSearchEkOrVk getArtbezSearchEkOrVk() {
		return artbezSearchEkOrVk;
	}

	public RB_HL_SearchArtbez _setArtbezSearchEkOrVk(EnArtbezSearchEkOrVk search_EK_VK) {
		this.artbezSearchEkOrVk = search_EK_VK;
		return this;
	}

	public Vector<RECORD_ARTIKEL_BEZ> getV_used_artikel_bez() {
		return v_artikel_records_found_from_search;
	}

	public Vector<String> getV_allowed_id_artikelbez() {
		return v_id_artikel_in_adress_range;
	}

	public RECORD_ADRESSE_extend getRa_relevante_haupt_adresse() {
		return record_haupt_adresse;
	}

	public void setV_used_artikel_bez(Vector<RECORD_ARTIKEL_BEZ> v_used_artikel_bez) {
		this.v_artikel_records_found_from_search = v_used_artikel_bez;
	}

	public void setV_allowed_id_artikelbez(Vector<String> v_allowed_id_artikelbez) {
		this.v_id_artikel_in_adress_range = v_allowed_id_artikelbez;
	}


	
	@Override
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		
		//suchbedingungen fuer die suche in der artikelbez-tabelle
		Vector<String>      v_wheres_artbez = new Vector<String>();
		
		if (S.isFull(search_text.trim())) {
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			String 				search_block_with_placeholder_artbez = this.and_statement_collector_4_search().s();
			for (String s: v_separator) {
				v_wheres_artbez.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder_artbez, "#WERT#", s.trim())+") ");
			}
		}
		String sql_where_only_search_artikelbez = bibALL.Concatenate(v_wheres_artbez, " AND ", "1=1");   //nur der suchanteil
		String where_statement_only_activ_artbez = "("+this.and_statement_collector_4_basic().s()+")";
		
		
		SEL  sel_artbez1 = new SEL(_TAB.artikel_bez)
							.FROM(_TAB.artikel_bez).INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel)
							.WHERE(new TermSimple(where_statement_only_activ_artbez))
							.AND(new TermSimple(sql_where_only_search_artikelbez));
		
		this.last_sql_search_query = sel_artbez1.s()+" ORDER BY "+ARTIKEL_BEZ.artbez1.fn();
		
//		DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		
		
		mv.add_MESSAGE(this.refill_result_record_vectors());
		this.refresh_resultbutton_array();
		return mv;
	}

	
	
	
	private MyE2_MessageVector refill_result_record_vectors() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		this.record_haupt_adresse = null;
		this.single_found_artbez = null;
		
		this.v_artikel_records_found_from_search.clear();
		this.v_id_artikel_in_adress_range.clear();
		this.v_artikel_records_mark_active.clear();
		
		
		
		if (this.getArtbezSearchTyp()==EnArtbezSearchTyp.SHOW_ALL_ARTBEZ) {
			this.rl_such_ergebnis = this.get_iMaxResults()>0?new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query,this.get_iMaxResults(),true):new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query);

			v_artikel_records_found_from_search.addAll(	rl_such_ergebnis.get_v_records());
			v_artikel_records_mark_active.addAll(		rl_such_ergebnis.get_v_records());

			v_id_artikel_in_adress_range.addAll(		rl_such_ergebnis.get_ID_ARTIKEL_BEZ_hmString_UnFormated("").values());
			//alle gefundenen sind aktiv

			if (v_artikel_records_mark_active.size()==1) {this.single_found_artbez=v_artikel_records_mark_active.get(0).ufs(ARTIKEL_BEZ.id_artikel_bez); }

		} else if (this.getArtbezSearchTyp()==EnArtbezSearchTyp.SHOW_RESTRICTED_AND_ALLOW_ADDING) {
			this.rl_such_ergebnis = this.get_iMaxResults()>0?new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query,this.get_iMaxResults(),true):new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query);
			
			record_haupt_adresse = this.findActualReferencedAdress(mv);
			if (mv.get_bIsOK()) {

				if (record_haupt_adresse==null) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Adresseingabe gefunden !")));
				} else {
					record_haupt_adresse = record_haupt_adresse.get_main_Adress();
					
					v_artikel_records_found_from_search.addAll(	rl_such_ergebnis.get_v_records());
					
//					DEBUG.System_println("Anzahl gefundene: "+v_artikel_records_found_from_search.size());
					
					v_id_artikel_in_adress_range.addAll(		this.get_arrayListAllowedArtBez_ID_ARTIKEL_BEZ_UF(record_haupt_adresse));
					
//					DEBUG.System_println("Anzahl in adresse: "+v_id_artikel_in_adress_range.size());
					
					v_artikel_records_mark_active.addAll(		rl_such_ergebnis.get_v_records(rec_artbez->{return v_id_artikel_in_adress_range.contains(rec_artbez.ufs(ARTIKEL_BEZ.id_artikel_bez));}));
	
					if (v_artikel_records_mark_active.size()==1) {this.single_found_artbez=v_artikel_records_mark_active.get(0).ufs(ARTIKEL_BEZ.id_artikel_bez); }
				}
			}

		} else if (this.getArtbezSearchTyp()==EnArtbezSearchTyp.SHOW_ONLY_RESTRICTED_ARTBEZ){
			//nur die erlaubten anzeigen
			record_haupt_adresse = this.findActualReferencedAdress(mv);
			this.rl_such_ergebnis = this.get_iMaxResults()>0?new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query,this.get_iMaxResults(),true):new RECLIST_ARTIKEL_BEZ(this.last_sql_search_query);

			if (mv.get_bIsOK()) {
				if (record_haupt_adresse==null) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Adresseingabe gefunden !")));
				} else {
					record_haupt_adresse = record_haupt_adresse.get_main_Adress();

					v_id_artikel_in_adress_range.addAll(		this.get_arrayListAllowedArtBez_ID_ARTIKEL_BEZ_UF(record_haupt_adresse));
					v_artikel_records_found_from_search.addAll(	rl_such_ergebnis.get_v_records(rec_artbez->{return v_id_artikel_in_adress_range.contains(rec_artbez.ufs(ARTIKEL_BEZ.id_artikel_bez));}));
					v_artikel_records_mark_active.addAll(		rl_such_ergebnis.get_v_records(rec_artbez->{return v_id_artikel_in_adress_range.contains(rec_artbez.ufs(ARTIKEL_BEZ.id_artikel_bez));}));
					
					if (v_artikel_records_mark_active.size()==1) {this.single_found_artbez=v_artikel_records_mark_active.get(0).ufs(ARTIKEL_BEZ.id_artikel_bez); }
				}
			}
		}
		
		return mv;
	}
	

	public void refresh_resultbutton_array() throws myException {
		
		this.single_result_button = null;
		
		E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch_black_and_grey();
		
		Vector<RECORD_ARTIKEL_BEZ>  v_angezeigte_artikel_bez = new Vector<>();
		
		if (this.cb_show_all.isSelected()) {
			v_angezeigte_artikel_bez.addAll(v_artikel_records_found_from_search);
		} else {
			v_angezeigte_artikel_bez.addAll(v_artikel_records_mark_active);
		}

		this.get_rb_ResultButtonArray().clear();
		
		for (RECORD_ARTIKEL_BEZ  rec_art_bez: v_angezeigte_artikel_bez) {
			
			RB_ResultButton[] res_zeile = new RB_ResultButton[5];
			E2_MutableStyle style_active = style;
			
			String sort_id = ("00000000000000000000000000000000").substring(rec_art_bez.get_ID_ARTIKEL_BEZ_cF().length())+rec_art_bez.get_ID_ARTIKEL_BEZ_cF();
			
			if (this.v_id_artikel_in_adress_range.contains(rec_art_bez.ufs(ARTIKEL_BEZ.id_artikel_bez))) {
				res_zeile[0]= new own_result_button(this,rec_art_bez, rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN(""),style_active,rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("")+rec_art_bez.get_ANR2_cUF_NN(""));
				res_zeile[1]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ANR2_cUF_NN(""), style_active,null);
				res_zeile[2]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ARTBEZ1_cUF_NN(""), style,null);
				res_zeile[3]= new own_result_button(this,rec_art_bez, rec_art_bez.get_ID_ARTIKEL_BEZ_cF(), style,sort_id);
				res_zeile[4]= (own_result_button)new own_result_button(this,rec_art_bez, "ok", style,sort_id)._width(17);
			} else {
				res_zeile[0]= new own_result_button(this,rec_art_bez, 	rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN(""),style_active,rec_art_bez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("")+rec_art_bez.get_ANR2_cUF_NN(""));
				res_zeile[1]= new own_result_button(this,rec_art_bez, 	rec_art_bez.get_ANR2_cUF_NN(""), style_active,null);
				res_zeile[2]= new own_result_button(this,rec_art_bez, 	rec_art_bez.get_ARTBEZ1_cUF_NN(""), style,null);
				res_zeile[3]= new own_result_button(this,rec_art_bez, 	rec_art_bez.get_ID_ARTIKEL_BEZ_cF(), style,sort_id);
				res_zeile[0].set_bEnabled_For_Edit(false);
				res_zeile[1].set_bEnabled_For_Edit(false);
				res_zeile[2].set_bEnabled_For_Edit(false);
				res_zeile[3].set_bEnabled_For_Edit(false);
				res_zeile[4]= new own_result_activate(record_haupt_adresse,this, rec_art_bez);
			}
			
			if (this.single_found_artbez!=null && this.single_found_artbez.equals(rec_art_bez.ufs(ARTIKEL_BEZ.id_artikel_bez))) {
				this.single_result_button=(own_result_button)res_zeile[0];
			}
			
			this.get_rb_ResultButtonArray().add(res_zeile);
			
		}
		
		this.get_rb_ResultButtonArray().sort();
		
	}
	
	
	
	/**
	 * bei der suche nach sorten gibt es eine positivliste der erlaubten artikel,
	 * ist der Rueckgabewert=null  ist jede gefundene artikelbez erlaubt, sonst nur die in der arraylist 
	 * @return
	 * @throws myException
	 */
	private ArrayList<String>  get_arrayListAllowedArtBez_ID_ARTIKEL_BEZ_UF(RECORD_ADRESSE_extend ra_main) throws myException {
		ArrayList<String> arr_rueck = new ArrayList<>();
		
		if (ra_main==null) {
			throw new myException(this,"Cannot find Adress to qualify artikel_bez-list!!");
		}
		
		RECLIST_ARTIKELBEZ_LIEF abl = ra_main.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
		
		String typ = this.artbezSearchEkOrVk==EnArtbezSearchEkOrVk.EK?"EK":"VK";
		
		Vector<RECORD_ARTIKELBEZ_LIEF>  v_abl = abl.get_v_records(rec_artbez_lief -> {return rec_artbez_lief.ufs(ARTIKELBEZ_LIEF.artbez_typ,"").equals(typ);});

		for (RECORD_ARTIKELBEZ_LIEF r : v_abl){
			arr_rueck.add(r.ufs(ARTIKELBEZ_LIEF.id_artikel_bez));
		}
		
		return arr_rueck;
	}

	
	
	
	/**
	 * eigener resultbutton, der eine sorte, die nicht im erlaubten block ist, dem lieferanten hinzufuegt
	 * @author martin
	 *
	 */
	private class own_result_activate extends RB_ResultButton {
		private String sort_string = null;

		public own_result_activate(	RECORD_ADRESSE_extend  	adresse,
									RB_SearchFieldSaveable 			calling_searchField, 
									MyRECORD_IF_RECORDS 	p_result_record ) throws myException {
			super(calling_searchField, p_result_record, "", MyE2_Button.StyleImageButton());
			this.sort_string = ((RECORD_ARTIKEL_BEZ)p_result_record).ufs(ARTIKEL_BEZ.id_artikel);   //proforma sortierung
			this.setIcon(E2_ResourceIcon.get_RI("multi_select_add_new.png"));
			this._ttt(new MyE2_String("Sorte zu den EK-Artikel der Firma ",true, adresse.get__FullNameAndAdress_Typ1(),false," hinzufügen !",true));
			this.add_oActionAgent(new ownActionAddArtikelBez(adresse, (RECORD_ARTIKEL_BEZ)p_result_record));
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
	

	
	private class ownActionAddArtikelBez extends XX_ActionAgent {
		private RECORD_ADRESSE 		f_recAdress =	null;
		private RECORD_ARTIKEL_BEZ 	f_recArtBez = null;
		
		public ownActionAddArtikelBez(RECORD_ADRESSE recAdress, RECORD_ARTIKEL_BEZ recArtBez) {
			super();
			this.f_recAdress = recAdress;
			this.f_recArtBez = recArtBez;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			own_add_artikelbez_to_company artbezAdd = new own_add_artikelbez_to_company(this.f_recAdress, this.f_recArtBez);
			artbezAdd.get_btSave().add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RB_HL_SearchArtbez.this.refill_result_record_vectors();
					RB_HL_SearchArtbez.this.refresh_resultbutton_array();
					RB_HL_SearchArtbez.this.fill_grid_4_popup(	RB_HL_SearchArtbez.this.get_grid_container_4_popupWindow(),
																RB_HL_SearchArtbez.this.get_rb_ResultButtonArray(), oExecInfo);
				}
			});
		}
	}

	private class own_add_artikelbez_to_company extends FZ_add_artikelBez_to_Company {
		public own_add_artikelbez_to_company(RECORD_ADRESSE recAdress, RECORD_ARTIKEL_BEZ recArtBez) throws myException {
			super(recAdress, recArtBez);
		}
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


	private class ownActionClickCheckbox_not_listed_artikel extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_HL_SearchArtbez oThis = RB_HL_SearchArtbez.this;
			oThis.refresh_resultbutton_array();
			oThis.fill_grid_4_popup(oThis.get_grid_container_4_popupWindow(),oThis.get_rb_ResultButtonArray(), oExecInfo);
		}
	}

	
	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		
		Vector<Component>  v_buttons = new Vector<Component>();
		v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("ANR1"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("ANR2"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Artbez1"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,3,  new MyE2_String("ID"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("+"),null));
		
		
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
			.def_(50).add_(v_buttons.get(4))
			.def_(new E2_ColorBase());
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			own_result_button bt1 = (own_result_button)arr[0];
			if (this.cb_show_all.isSelected() || this.v_id_artikel_in_adress_range.contains(((MyRECORD)bt1.get_result_record()).ufs(ARTIKEL_BEZ.id_artikel_bez))) {
				gs	.def_(new Alignment(Alignment.LEFT,Alignment.TOP))
					.add_(arr[0].me()).add_(arr[1].me()).add_(arr[2].me())
					.def_(new Alignment(Alignment.RIGHT,Alignment.TOP)).add_(arr[3].me()).add_(arr[4].me());
			}
		}
		gs.setSize_(5);
	
		grid_4_popup.removeAll();
		if (this.show_bt_add_all_found_artikel_bez) {
			this.add_on_grid.removeAll();
			this.add_on_grid._a(this.cb_show_all);
			grid_4_popup.add(this.add_on_grid,E2_INSETS.I(0,0,0,4));
		}
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}

	public boolean is_show_bt_add_all_found_artikel_bez() {
		return show_bt_add_all_found_artikel_bez;
	}

	public void set_show_bt_add_all_found_artikel_bez(boolean b_show_bt_add_all_found_artikel_bez) {
		this.show_bt_add_all_found_artikel_bez = b_show_bt_add_all_found_artikel_bez;
	}

	
	private class actionUnsetCheckboxShowAllFoundArtikel extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_HL_SearchArtbez.this.cb_show_all.setSelected(false);
		}
	}
	
	
	
	
	
	
	private class own_searchActionAgent extends XX_ActionAgent {

		public own_searchActionAgent() {
			super();
		}


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
	
			RB_HL_SearchArtbez oThis = RB_HL_SearchArtbez.this;
			
			if (!oThis.is_allow_empty_searchfield()) {
				if (S.isEmpty(oThis.get_tf_search_input().getText())) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie Suchbegriffe ein !")));
					return;
				}
			}

			String c_text_4_search = oThis.get_tf_search_input().getText();
			
			/*
			 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
			 */
			String cTextTest = bibALL.ReplaceTeilString(c_text_4_search,".","");
			if (bibALL.isInteger(cTextTest)) {
				c_text_4_search=cTextTest;
			}
					
			try	{
				
				oThis.get_rb_ResultButtonArray().clear();
				bibMSG.add_MESSAGE(oThis.execute_searchquery_and_fill_resultbutton_array(c_text_4_search));

				if (bibMSG.get_bIsOK()) {
				
					//evtl. vorhandene gespeicherte sortierung laden
					if (oThis.get_key_4_save_sorting()!=null) {
						new RB_SaveSortOfPopup(oThis).RESTORE();
					}
					
					
					//vorsortierung falls werte gesetzt sind
					oThis.get_rb_ResultButtonArray().sort();
	
					if (oThis.single_result_button!=null) {
						oThis.single_result_button.doActionPassiv();				
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Genau ein Datensatz gefunden und geladen !"),false);
					} else if (oThis.get_rb_ResultButtonArray().size()==0) {
						if ( (oThis.artbezSearchTyp == EnArtbezSearchTyp.SHOW_RESTRICTED_AND_ALLOW_ADDING)  &&
							 (oThis.v_artikel_records_found_from_search.size()>0) ) {
							this.baue_auf(oExecInfo);	
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
							bibMSG.add_MESSAGE(oThis.do_mask_settings_after_search("",true));
						}
					} else {
						this.baue_auf(oExecInfo);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
				

			
		}
		
		
		private void baue_auf(ExecINFO oExecInfo) throws myException {
			RB_HL_SearchArtbez oThis = RB_HL_SearchArtbez.this;

			
			//popup aufbauen	
			E2_BasicModuleContainer  container = oThis.get_container_4_popupWindow();

			oThis.get_grid_container_4_popupWindow().removeAll();;
			oThis.fill_grid_4_popup(oThis.get_grid_container_4_popupWindow(), oThis.get_rb_ResultButtonArray(), oExecInfo);
			container.RESET_Content();
			container.add(oThis.get_grid_container_4_popupWindow(), E2_INSETS.I(4,4,4,4));
			
			
			container.CREATE_AND_SHOW_POPUPWINDOW(	oThis.get_width_popup_window(),	oThis.get_height_popup_window(),oThis.get_title_of_popup());

			container.save_focusable_components_outside(oThis.get_tf_search_input());
			
			oThis.get_rb_ResultButtonArray().set_1st_column_focusable();
			MyE2IF__Component  focusable = oThis.get_rb_ResultButtonArray().get_focus_component();
			bibALL.setFocus((Component) focusable);

		}

	}

	
	
	
}
