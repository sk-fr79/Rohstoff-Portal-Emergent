package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
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
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class RB_HL_SearchAdressStation21 extends RB_SearchFieldSaveable implements IF_RB_ResultButton_can_edit_searched_record{
	
	private ownButtonEditAdresse  	bt_editAdresse = 		new ownButtonEditAdresse();
	private int 					textFieldInputWidth = 	100;

	
	private boolean   				m_excludeOwnAdresses = 	false;
	private boolean   				m_onlyOwnAdresses = 	false;
	private boolean   				m_showEraser = 			false;
	
	private boolean   				m_onlyLagerAdressen = false;
	private boolean   				m_onlyHauptAdressen = false;
	
	//falls nur eine auswahl einer hauptadresse gesucht werden soll 
	private Long 					idAdresseMain  = null;
	
	
	/**
	 * 
	 * @param exclude_own_adress
	 * @throws myException
	 */
	public RB_HL_SearchAdressStation21() throws myException {
		super();
		this.get_tf_search_input().setWidth(new Extent(100));
		this._setFirstColInResultPopupFocusAble(false);
		this._setRenderSearchResultVisibleOnMaskInEmptyManualSettings(true);
		this.set_iMaxResults(1000);
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHADRESS);
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
		
		MyE2_Grid  g =lab_help.get_InBorderGrid(new Border(1, new E2_ColorDark(), Border.STYLE_SOLID), new Extent(200), E2_INSETS.I(0,0,0,0));
		g.setRowHeight(0, new Extent(20));
		gridcontainer_4_search_results.add(g);
	}

	
	
	
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(RB_HL_SearchAdressStation21.this.rb_set_db_value_manual("", true,true));
			}
		};
	}


	@Override
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {

		this.and_statement_collector_4_basic().clear();
		this.and_statement_collector_4_search().clear();
		
		Or typen = new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE));
		this.and_statement_collector_4_basic()	.and(typen);

		
		String idOwnId = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
		SEL subMandantenAdresses = new SEL(LIEFERADRESSE.id_adresse_liefer).FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, idOwnId));
		if (this.m_excludeOwnAdresses) {
			this.and_statement_collector_4_basic().and(new vgl(ADRESSE.id_adresse,COMP.NOT_EQ,idOwnId));
			this.and_statement_collector_4_basic().and(new TermSimple(ADRESSE.id_adresse.tnfn()+" NOT IN ("+subMandantenAdresses.s()+")"));
		} 
		if (this.m_onlyOwnAdresses) {
			this.and_statement_collector_4_basic().and(new Or(new vgl(ADRESSE.id_adresse,COMP.EQ,idOwnId)).OR(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+subMandantenAdresses.s()+")")));
		} 
		
		if (this.m_onlyHauptAdressen) {
			this.and_statement_collector_4_basic().and(new vgl(ADRESSE.adresstyp,COMP.EQ,""+myCONST.ADRESSTYP_FIRMENINFO));
		} 
		if (this.m_onlyLagerAdressen) {
			this.and_statement_collector_4_basic().and(new vgl(ADRESSE.adresstyp,COMP.EQ,""+myCONST.ADRESSTYP_LIEFERADRESSE));
		} 
		
		
		
		//suche ueber die innere-join lager-zu-hauptadresse-query, ALB heisst Adresse lager oder basis, AB ist die zughoerige Basis-Adresse
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.NAME1)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.NAME2)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.NAME3)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.STRASSE)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.PLZ)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(AB.ORT)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.NAME1)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.NAME2)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.NAME3)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.STRASSE)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.PLZ)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("UPPER(ALB.ORT)"), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("TO_CHAR(ALB.ID_ADRESSE)"), COMP.EQ, new TermSimple("REPLACE('#WERT#','.','')")));
		
		
		
		Vector<String>      v_wheres = new Vector<String>();
		String 				search_block_with_placeholder = this.and_statement_collector_4_search().s();
		
		if (S.isFull(search_text)) {
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			for (String s: v_separator) {
				v_wheres.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
			}
		}
		
		String inner = "SELECT ALB.ID_ADRESSE FROM JT_ADRESSE ALB " 
						+" LEFT OUTER JOIN JT_LIEFERADRESSE LIEF  ON ( LIEF.ID_ADRESSE_LIEFER=ALB.ID_ADRESSE)"
						+" LEFT OUTER JOIN JT_ADRESSE       AB    ON ( "
						+ "								(NVL(LIEF.ID_ADRESSE_BASIS,0)=AB.ID_ADRESSE AND ALB.ADRESSTYP=5)"
													+ "  OR"
													+ " (AB.ID_ADRESSE=ALB.ID_ADRESSE AND ALB.ADRESSTYP=1 )  )"
					   +" WHERE "+bibALL.Concatenate(v_wheres, " AND ", "1=1")
					   +" AND NVL(ALB.AKTIV,'N')='Y' "
					   +" AND NVL(AB.AKTIV,'N')='Y' "
					;
		if (this.idAdresseMain!=null) {
			inner = inner + "AND  AB.ID_ADRESSE="+this.idAdresseMain.toString();
		}

					
		//jetzt die aeussere suche mit den begrenzungen
		String sqlKomplett = "SELECT * FROM "+ADRESSE.fullTabName()	+" WHERE "+ADRESSE.id_adresse.tnfn()+" IN ("+inner+" )";
		if (this.and_statement_collector_4_basic().size()>0) {
			sqlKomplett=sqlKomplett+" AND "+"("+this.and_statement_collector_4_basic().s()+")";
		}
		if (this.and_statement_collector_4_action().size()>0) {
			sqlKomplett=sqlKomplett+" AND "+"("+this.and_statement_collector_4_action().s()+")";
		}
					


		DEBUG._print("Adress-Suchquery: "+sqlKomplett);

		RecList21 rl = new RecList21(_TAB.adresse)._fill(sqlKomplett);
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();
		

		for (Rec21 r: rl) {
			
			Rec21_adresse recAdresse = new Rec21_adresse(r);
			RB_ResultButton[] res_zeile = new RB_ResultButton[5];
			
			String c_firma = "";
			Vector<String> v_lieferant_abnehmer = new Vector<String>();
			

			String c_sortHelper = "";   //sorgt dafuer, dass beim sortieren nach firma die hauptadresse zuerst komme
			
			boolean isMain = false;
			
			if ( ((BigDecimal)recAdresse.getRawVal(ADRESSE.adresstyp)).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
				isMain =true;
				c_firma = recAdresse.get__FullNameAndAdress_Typ2();	

				if (recAdresse.getIdLong().toString().equals(bibALL.get_ID_ADRESS_MANDANT())) {
					v_lieferant_abnehmer.add("<Zentrale>");
				} else {
					if (recAdresse.isLieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (recAdresse.isKunde()) {v_lieferant_abnehmer.add("<ABN>");}
				}
				c_sortHelper = "A";
			} else {
				Rec21_adresse  rec_hpt = recAdresse._getMainAdresse();
				c_firma = rec_hpt.get__FullNameAndAdress_Typ2();	
				
				if (rec_hpt.getIdLong().toString().equals(bibALL.get_ID_ADRESS_MANDANT())) {
					v_lieferant_abnehmer.add("<Eigenes Lager>");
				} else {
					if (rec_hpt.isLieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (rec_hpt.isKunde()) {v_lieferant_abnehmer.add("<ABN>");}
				}
				c_sortHelper = "B";
			}
			
			String sort_id = ("00000000000000000000000000000000").substring(recAdresse.getUfs(ADRESSE.id_adresse).length())+recAdresse.getUfs(ADRESSE.id_adresse);
			Vector<String> sort_texte = new Vector<String>();
			sort_texte.add(recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
			sort_texte.add(recAdresse.get__strasseHausnummer(" ").toUpperCase());
			sort_texte.add(c_firma+c_sortHelper+recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
			sort_texte.add(bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>").toUpperCase());
			sort_texte.add(sort_id);
			
			res_zeile[0]= new own_result_button(this,recAdresse, recAdresse.get__FullNameAndAdress_Typ2(),sort_texte.get(0));
			res_zeile[1]= new own_result_button(this,recAdresse, recAdresse.get__strasseHausnummer(" "), sort_texte.get(1));
			res_zeile[2]= new own_result_button(this,recAdresse, c_firma, sort_texte.get(2));
			res_zeile[3]= new own_result_button(this,recAdresse, bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>"), sort_texte.get(3));
			res_zeile[4]= new own_result_button(this,recAdresse, recAdresse.getUfs(ADRESSE.id_adresse), sort_texte.get(4));
			
			//wenn es eine firma ist, dann bold
			for (E2_Button b: res_zeile) {
				if (c_sortHelper.equals("A")) {
					b._style(E2_Button.baseStyleBold());
					b._b();
				} else {
					b._style(E2_Button.baseStyle());
				}
			}
			
			v_result_arrays.add(res_zeile);
			
			if (this.get_iMaxResults()>0 && v_result_arrays.size()>this.get_iMaxResults()) {
				break;
			}
		}
		
		return new MyE2_MessageVector();
	}
	
	

	
	
	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		
		E2_Grid grid = new E2_Grid()._w100()._nB();
		Vector<Component>  v_buttons = new Vector<Component>();
		v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("Adresse"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Strasse"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Firma"),null));
		v_buttons.add(new MyE2_Label(new MyE2_String("Lief/Abn")));
		v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("ID"),null));
		
		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<5 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		grid._s(5)	._a(v_buttons.get(0), new RB_gld()._col_back_d()._ins(2))
					._a(v_buttons.get(1), new RB_gld()._col_back_d()._ins(2))
					._a(v_buttons.get(2), new RB_gld()._col_back_d()._ins(2))
					._a(v_buttons.get(3), new RB_gld()._col_back_d()._ins(2))
					._a(v_buttons.get(4), new RB_gld()._col_back_d()._ins(2));
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			for (RB_ResultButton_IF r: arr) {
				grid._a((Component)r, new RB_gld()._ins(2));
			}	
		}
	
		grid_4_popup.removeAll();
		grid_4_popup.add(grid,E2_INSETS.I(0,0,0,0));
	}

	
	
	private class own_result_button extends RB_ResultButton {
		private String sort_string = null;

		public own_result_button(	RB_SearchFieldSaveable 		calling_searchField, 
									MyRECORD_IF_RECORDS p_result_record, 
									String 				cText,		
									String 				sortstring) throws myException {
			super(calling_searchField, p_result_record, cText, null);
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

		@Override
		public own_result_button _b() {
			super._b();
			return this;
		}
		
		
	}
	

	
	private class ownButtonEditAdresse extends E2_BtEditAdress {

		public ownButtonEditAdresse() throws myException {
			super();
			this._ttt(S.mt("Adresse bearbeiten"));
		}

		@Override
		public MyLong find_id_adress() throws myException {
			String c_actual_station = null;
			if (RB_HL_SearchAdressStation21.this.is_search_done_correct()) {
				c_actual_station = RB_HL_SearchAdressStation21.this.rb_readValue_4_dataobject();
				return new MyLong(c_actual_station);
			}
			return null;
		}
		
	}

	@Override
	public E2_BtEditAdress get_button_to_open_mask_to_referenced_record() {
		return this.bt_editAdresse;
	}

	
	public int getTextFieldInputWidth() {
		return textFieldInputWidth;
	}

	public RB_HL_SearchAdressStation21 _setTextFieldInputWidth(int textFieldInputWidth) {
		this.textFieldInputWidth = textFieldInputWidth;
		this.get_tf_search_input()._w(textFieldInputWidth);
		return this;
	}


	public boolean isExcludeOwnAdress() {
		return m_excludeOwnAdresses;
	}

	public RB_HL_SearchAdressStation21 _setExcludeOwnAdresses(boolean excludeOwnAdress) {
		this.m_excludeOwnAdresses = excludeOwnAdress;
		return this;
	}

	public boolean isShowEraser() {
		return m_showEraser;
	}

	public RB_HL_SearchAdressStation21 _setShowEraser(boolean showEraser) {
		this.m_showEraser = showEraser;
		return this;
	}

	public boolean isOnlyOwnAdresses() {
		return m_onlyOwnAdresses;
	}

	public RB_HL_SearchAdressStation21 _setOnlyOwnAdresses(boolean onlyOwnAdresses) {
		this.m_onlyOwnAdresses = onlyOwnAdresses;
		return this;
	}



	

	public boolean isOnlyLagerAdressen() {
		return m_onlyLagerAdressen;
	}




	public RB_HL_SearchAdressStation21 _setOnlyLagerAdressen(boolean onlyLagerAdressen) {
		this.m_onlyLagerAdressen = onlyLagerAdressen;
		return this;
	}




	public boolean isOnlyHauptAdressen() {
		return m_onlyHauptAdressen;
	}




	public RB_HL_SearchAdressStation21 _setOnlyHauptAdressen(boolean onlyHauptAdressen) {
		this.m_onlyHauptAdressen = onlyHauptAdressen;
		return this;
	}




	public Long getIdAdresseMainRestrict() {
		return idAdresseMain;
	}




	public RB_HL_SearchAdressStation21 _setIdAdresseMainRestrict(Long idAdresseMain) {
		this.idAdresseMain = idAdresseMain;
		return this;
	}
	
	

//	
	
}
