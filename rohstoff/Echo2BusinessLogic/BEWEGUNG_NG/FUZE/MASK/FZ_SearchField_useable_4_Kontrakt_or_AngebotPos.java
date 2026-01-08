package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_LIST;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_ANGEBET_OR_KONTRAKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;


/**
 * suchfeld fuer kontrakt und angebote
 * @author martin
 *
 */
public abstract class FZ_SearchField_useable_4_Kontrakt_or_AngebotPos extends RB_SearchFieldSaveable {

	

	// ... dann kontrakte
	private And    and_statement_collector_4_basic = new And();
	
	private String select_base =  "SELECT NVL(A.NAME1,'')||' '||NVL(A.NAME2,'') AS NAME"
										+ ", A.ORT AS ORT"
										+ ", NVL(P.ANR1,'')||'-'||NVL(P.ANR2,'') AS ANR1_2"
										+ ", P.ARTBEZ1"
										+ ", P.ANZAHL"
										+ ", P.EINZELPREIS"
										+ ", TO_CHAR(PZ.GUELTIG_VON,'DD.MM.YYYY') AS GUELTIG_VON"
										+ ", TO_CHAR(PZ.GUELTIG_BIS,'DD.MM.YYYY') AS GUELTIG_BIS";
	
	
	private String  from_kontrakt = " FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT PZ"+
										" INNER JOIN "+bibE2.cTO()+".JT_VPOS_KON P ON (P.ID_VPOS_KON=PZ.ID_VPOS_KON) "+
										" INNER JOIN "+bibE2.cTO()+".JT_VKOPF_KON K ON (K.ID_VKOPF_KON=P.ID_VKOPF_KON) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE  A ON (A.ID_ADRESSE=K.ID_ADRESSE) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=P.ID_ARTIKEL_BEZ) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ART ON (AB.ID_ARTIKEL=ART.ID_ARTIKEL) "+
										"";
	
	private String  from_angebot = " FROM "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT PZ"+
										" INNER JOIN "+bibE2.cTO()+".JT_VPOS_STD P ON (P.ID_VPOS_STD=PZ.ID_VPOS_STD) "+
										" INNER JOIN "+bibE2.cTO()+".JT_VKOPF_STD K ON (K.ID_VKOPF_STD=P.ID_VKOPF_STD) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE  A ON (A.ID_ADRESSE=K.ID_ADRESSE) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=P.ID_ARTIKEL_BEZ) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ART ON (AB.ID_ARTIKEL=ART.ID_ARTIKEL) "+
										"";
	
	
	private String sql = null;


	private SEARCH_ANGEBET_OR_KONTRAKT  	searchBase = null;
	private SEARCH_EK_OR_VK  					searchTyp = null;
	
	
	public FZ_SearchField_useable_4_Kontrakt_or_AngebotPos(SEARCH_ANGEBET_OR_KONTRAKT p_searchBase,  SEARCH_EK_OR_VK  p_searchTyp) throws myException {
		super();
		this.searchBase = p_searchBase;
		this.searchTyp = p_searchTyp;
		
		if (this.searchBase==SEARCH_ANGEBET_OR_KONTRAKT.KONTRAKT) {
			this.sql = select_base+", P.ID_VPOS_KON "+from_kontrakt;

			this.and_statement_collector_4_basic.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_KON_TRAKT.gueltig_von).s(),COMP.LE.s(),"#DATUM#")
														.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_KON_TRAKT.gueltig_bis).s(),COMP.GE.s(),"#DATUM#")
														.and(new vgl_YN("PZ", VPOS_KON_TRAKT.abgeschlossen, false))
														.and(this.searchTyp==SEARCH_EK_OR_VK.EK
															?
															new vgl("K", VKOPF_KON.vorgang_typ, myCONST.VORGANGSART_EK_KONTRAKT)
															:
															new vgl("K", VKOPF_KON.vorgang_typ, myCONST.VORGANGSART_VK_KONTRAKT)	
															)
														.and(new FieldTerm("A",ADRESSE.id_adresse).s(),COMP.EQ.s(),"#ID_ADRESSE#")
														;
			
		} else {
			this.sql = select_base+", P.ID_VPOS_STD "+from_angebot;

			this.and_statement_collector_4_basic.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_STD_ANGEBOT.gueltig_von).s(),COMP.LE.s(),"#DATUM#")
														.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_STD_ANGEBOT.gueltig_bis).s(),COMP.GE.s(),"#DATUM#")
														.and(this.searchTyp==SEARCH_EK_OR_VK.EK
															?
															new vgl("K", VKOPF_STD.vorgang_typ, myCONST.VORGANGSART_ABNAHMEANGEBOT)
															:
															new vgl("K", VKOPF_STD.vorgang_typ, myCONST.VORGANGSART_ANGEBOT)	
															)
													.and(new FieldTerm("A",ADRESSE.id_adresse).s(),COMP.EQ.s(),"#ID_ADRESSE#")
													;

		}

		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("TO_CHAR(A.ID_ADRESSE)"), COMP.EQ, new TermSimple("'#WERT#'")));
		
		this.set_iMaxResults(500);
		
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_SEARCH_KONTRAKT_ODER_ANGEBOT);

		//sicherstellen, dass immer zuerst das datumsfeld gefuellt ist !
		XX_ActionValidator_NG validStartSearch = this.generate_if_needed_validor_4_startSearchButton();
		if (validStartSearch!=null) {
			this.get_buttonStartSearch().add_GlobalValidator(validStartSearch);
		}
		
		this._allowEmptySearchfield(true);
		
	}


	@Override
	public void render_search_result_visible_on_mask(E2_Grid grid, String c_result_value_4_db) throws myException {

		MyLong l = new MyLong(c_result_value_4_db);

		grid.removeAll();
		grid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		grid._setSize(50,120,80,60,60);
		
		if (S.isFull(c_result_value_4_db) && l.get_bOK()) {
			if (this.searchBase==SEARCH_ANGEBET_OR_KONTRAKT.KONTRAKT) {
				new RECORD_VPOS_KON_ext(new RECORD_VPOS_KON(l.get_lValue())).fill_grid_with_infos(grid);
			} else {
				new RECORD_VPOS_STD_ext(new RECORD_VPOS_STD(l.get_lValue())).fill_grid_with_infos(grid);
			}
		}

	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicModuleContainer();
	}


	@Override
	public XX_ActionAgent generate_StartSearchAction() throws myException {
		return new RB_SearchField_actionStartSearch(this);	
	}

	@Override
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FZ_SearchField_useable_4_Kontrakt_or_AngebotPos.this.rb_set_db_value_manual("", true,true);
			}
		};
	}

	
//	@Override
//	public MyRECORD_IF_RECORDS get_result_record_from_string(String unformated_MaskValue) throws myException {
//		if (this.searchBase==SEARCH_ANGEBET_OR_KONTRAKT.ANGEBOT) {
//			return new RECORD_VPOS_STD(unformated_MaskValue);
//		} else {
//			return new RECORD_VPOS_KON(unformated_MaskValue);
//		}
//	}


	
	@Override
	public MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		Vector<String>      v_wheres = new Vector<String>();
		
		if (S.isFull(search_text)) {
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			for (String s: v_separator) {
				v_wheres.add(" ("+bibALL.ReplaceTeilString(this.and_statement_collector_4_search().s(), "#WERT#", s.trim())+") ");
			}
		}
		
		v_wheres.add("("+this.and_statement_collector_4_basic.s()+")");

		
		String sql_where_kontrakt = bibALL.Concatenate(v_wheres, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		

		MyDate date_fuhre = this.extract_relevantDate_from_mask();
		MyLong id_adresse = this.extract_id_adress();
		
		if (date_fuhre==null || id_adresse==null) {
			throw new myException("Date and start-adresse MUST be filled !!");    //darf nicht passieren, da der validierer beim start das verhindert
		}

		RECORD_ADRESSE_extend ra = new RECORD_ADRESSE_extend(id_adresse.get_lValue());
		String id_main_adress = ra.get_main_Adress().get_ID_ADRESSE_cUF();
		
		
		sql_where_kontrakt = bibALL.ReplaceTeilString(sql_where_kontrakt, "#DATUM#", date_fuhre.get_cDBFormatErgebnis_4_SQLString());
		sql_where_kontrakt = bibALL.ReplaceTeilString(sql_where_kontrakt, "#ID_ADRESSE#", id_main_adress);
		
		MyRECORD_LIST rl = null;
		
		if (this.get_iMaxResults()>0) {
			rl = new MyRECORD_LIST(this.sql+" WHERE "+sql_where_kontrakt, "ID_VPOS_KON", this.get_iMaxResults());
		} else {
			rl = new MyRECORD_LIST(this.sql+" WHERE "+sql_where_kontrakt, "ID_VPOS_KON");
		}
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();
		
		E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
		E2_MutableStyle styleRight = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
		styleRight.setProperty(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.RIGHT,Alignment.TOP));
		

		if (rl!=null) {
			for (int i=0;i<rl.get_vKeyValues().size();i++) {
				MyRECORD rec = rl.get_(rl.get_vKeyValues().get(i));
				RB_ResultButton[] res_zeile = new RB_ResultButton[9];
				
			
				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(rec.fs("TYP"));
				sort_texte.add(rec.fs("TYP")+rec.fs("NAME","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ORT","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ANR1_2","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ARTBEZ1","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ANZAHL","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("EINZELPREIS","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("GUELTIG_VON","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("GUELTIG_BIS","-"));

				MyRECORD_IF_RECORDS rec_vp = null;
				if (this.searchBase==SEARCH_ANGEBET_OR_KONTRAKT.KONTRAKT) {
					rec_vp = new RECORD_VPOS_KON(rec.l(VPOS_KON.id_vpos_kon));
				} else {
					rec_vp = new RECORD_VPOS_STD(rec.l(VPOS_STD.id_vpos_std));
				}
				res_zeile[0]= new own_result_button(this,rec_vp, rec.fs("TYP","-"),			style,		sort_texte.get(0));
				res_zeile[1]= new own_result_button(this,rec_vp, rec.fs("NAME","-"),		style,		sort_texte.get(1));
				res_zeile[2]= new own_result_button(this,rec_vp, rec.fs("ORT","-"),			style,		sort_texte.get(2));
				res_zeile[3]= new own_result_button(this,rec_vp, rec.fs("ANR1_2","-"),		style,		sort_texte.get(3));
				res_zeile[4]= new own_result_button(this,rec_vp, rec.fs("ARTBEZ1","-"),		style,		sort_texte.get(4));
				res_zeile[5]= new own_result_button(this,rec_vp, rec.fs("ANZAHL","-"),		styleRight,	sort_texte.get(5));
				res_zeile[6]= new own_result_button(this,rec_vp, rec.fs("EINZELPREIS","-"),	styleRight,	sort_texte.get(6));
				res_zeile[7]= new own_result_button(this,rec_vp, rec.fs("GUELTIG_VON","-"),	styleRight,	sort_texte.get(7));
				res_zeile[8]= new own_result_button(this,rec_vp, rec.fs("GUELTIG_BIS","-"),	styleRight,	sort_texte.get(8));
				
				v_result_arrays.add(res_zeile);
				
			}
		}

		return new MyE2_MessageVector();

	}

	@Override
	public void fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		E2_Grid4MaskSimple gs = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		Vector<Component>  v_buttons = new Vector<Component>();
		v_buttons.add(new MyE2_Label(new MyE2_String("Typ")));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Lieferant"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Ort"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,3,  new MyE2_String("Sorte"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("Sortenbezeichnung"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,5,  new MyE2_String("Menge"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,6,  new MyE2_String("Preis"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,7,  new MyE2_String("Gültig ab"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,8,  new MyE2_String("Gültig bis"),null));
		
		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<9 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		gs  .def_(new E2_ColorDark()).def_(new Alignment(Alignment.LEFT,Alignment.TOP))
			.add_(v_buttons.get(0))
			.add_(v_buttons.get(1))
			.add_(v_buttons.get(2))
			.add_(v_buttons.get(3))
			.add_(v_buttons.get(4)).def_(new Alignment(Alignment.RIGHT,Alignment.TOP))
			.add_(v_buttons.get(5))
			.add_(v_buttons.get(6))
			.add_(v_buttons.get(7))
			.add_(v_buttons.get(8))
			.def_(new E2_ColorBase());
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs		.def_(new Alignment(Alignment.LEFT,Alignment.TOP))
					.add_(arr[0].me())
					.add_(arr[1].me())
					.add_(arr[2].me())
					.add_(arr[3].me())
					.add_(arr[4].me()).def_(new Alignment(Alignment.RIGHT,Alignment.TOP))
					.add_(arr[5].me())
					.add_(arr[6].me())
					.add_(arr[7].me())
					.add_(arr[8].me());
		}
		gs._setSize(30,150,120,80,180,80,80,80,80);
	
		grid_4_popup.removeAll();
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));

	}

	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		if (this.searchBase==SEARCH_ANGEBET_OR_KONTRAKT.KONTRAKT) {
			return ((RECORD_VPOS_KON)p_result_record).ufs(VPOS_KON.id_vpos_kon);
		} else {
			return ((RECORD_VPOS_STD)p_result_record).ufs(VPOS_STD.id_vpos_std);
		}
	}

	
	public abstract MyDate 						extract_relevantDate_from_mask() throws myException;
	public abstract MyLong 						extract_id_adress() throws myException;
	public abstract XX_ActionValidator_NG		generate_if_needed_validor_4_startSearchButton() throws myException; 		
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
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
