package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public abstract class RB_HL_SearchMainAdress21 extends RB_SearchFieldSaveable  implements IF_RB_ResultButton_can_edit_searched_record{

	public abstract E2_BasicModuleContainer generate_container_4_popup_window() throws myException;
	public abstract String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException;
	
	private ownButtonEditAdresse  	bt_editAdresse = new ownButtonEditAdresse();

	
	public RB_HL_SearchMainAdress21() throws myException {
		super();
		this._init();
	}

	public RB_HL_SearchMainAdress21(boolean bShowEraser) throws myException {
		super(bShowEraser);
		this._init();
	}


	
	private void _init() throws myException {
		this.and_statement_collector_4_basic().and(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO))
		.and(new vgl(ADRESSE.aktiv, "Y"));
		
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("TO_CHAR("+ADRESSE.id_adresse.tnfn()+")"), COMP.EQ, new TermSimple("REPLACE('#WERT#','.','')")));
		
		this.set_iMaxResults(500);
		
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHADRESS);
		
	}
	
	


	@Override
	public void render_search_result_visible_on_mask(	E2_Grid gridcontainer_4_search_results, 
														String c_result_value_4_db) throws myException {
		
		gridcontainer_4_search_results._clear();
		RB_lab 	lab_help = null;
		
		MyLong m_id = new MyLong(c_result_value_4_db);
		if (m_id.isOK()) {
		
			Rec21_adresse  recAdresse = new Rec21_adresse()._fill_id(m_id.get_lValue())._getMainAdresse();
			String label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("Hauptadresse").CTrans()+")";
			label_txt = recAdresse.get__FullNameAndAdress_Typ2();
			
			lab_help=new RB_lab()._t(label_txt)._fsa(-2);				
			gridcontainer_4_search_results.add(lab_help);
		} else {
			lab_help=new RB_lab(" ");
		}
	}

	
	
	
	
	@Override
	public  XX_ActionAgent  	generate_StartSearchAction() throws myException {
		return new RB_SearchField_actionStartSearch(this);
	}
	
	
	@Override
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_HL_SearchMainAdress21.this.rb_set_db_value_manual("",true,true);
			}
		};
	}


	@Override
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		
		Triple<String> tr = this.getWhereStatementParts(search_text);
		
		String sql_where = bibALL.Concatenate(tr.getVEK(true), " AND ", "1=1");  //suchanteil und zusatzbedingungen
		
		Vector<String> v_names = new Vector<String>();
		for (IF_Field f: ADRESSE.values()) {
			v_names.add(f.tnfn());
		}

		String field_list = bibALL.Concatenate(v_names, ",", "*");
		
		String c_sql_query =	"SELECT "+field_list+" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE "+sql_where
										+ "  ORDER BY NAME1,NAME2,ID_ADRESSE ";

		//DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());

		RecList21 rl = new RecList21(_TAB.adresse)._fill(new SqlStringExtended(c_sql_query));
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();
		
		
		for (Rec21 r : rl) {
			Rec21_adresse  recAdresse = new Rec21_adresse(r);
			RB_ResultButton[] res_zeile = new RB_ResultButton[5];

			String c_firma = "";
			Vector<String> v_lieferant_abnehmer = new Vector<String>();
			

			String c_sortHelper = "";   //sorgt dafuer, dass beim sortieren nach firma die hauptadresse zuerst komme
			
			String sort_id = ("00000000000000000000000000000000").substring(recAdresse.getIdLong().toString().length())+recAdresse.getIdLong().toString();
			Vector<String> sort_texte = new Vector<String>();
			sort_texte.add(recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
			sort_texte.add(recAdresse.get__strasseHausnummer(" ").toUpperCase());
			sort_texte.add(c_firma+c_sortHelper+recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
			sort_texte.add(bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>").toUpperCase());
			sort_texte.add(sort_id);
			
			res_zeile[0]= new own_result_button(this,recAdresse, recAdresse.get__FullNameAndAdress_Typ2(),	E2_Button.baseStyle(),sort_texte.get(0));
			res_zeile[1]= new own_result_button(this,recAdresse, recAdresse.get__strasseHausnummer(" "), 	E2_Button.baseStyle(),sort_texte.get(1));
			res_zeile[2]= new own_result_button(this,recAdresse, c_firma, 									E2_Button.baseStyle(),sort_texte.get(2));
			res_zeile[3]= new own_result_button(this,recAdresse, bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>"), E2_Button.baseStyle(),sort_texte.get(3));
			res_zeile[4]= new own_result_button(this,recAdresse, recAdresse.getIdLong().toString(), E2_Button.baseStyle(),sort_texte.get(4));
			
			v_result_arrays.add(res_zeile);
			
		}
		
		return new MyE2_MessageVector();
	}
	
	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		
		E2_Grid gs = new E2_Grid()._nB()._w100();
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
		
		gs  ._a(v_buttons.get(0))
			._a(v_buttons.get(1))
			._a(v_buttons.get(2))
			._a(v_buttons.get(3))
			._a(v_buttons.get(4))
			;
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs  ._a(arr[0].me())
				._a(arr[1].me())
				._a(arr[2].me())
				._a(arr[3].me())
				._a(arr[4].me())
				;
		}
		gs._setSize(200,100,200,100,100);
	
		grid_4_popup.removeAll();
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

	private class ownButtonEditAdresse extends E2_BtEditAdress {

		public ownButtonEditAdresse() throws myException {
			super();
			this._ttt(S.mt("Adresse bearbeiten"));
		}

		@Override
		public MyLong find_id_adress() throws myException {
			String c_actual_station = null;
			if (RB_HL_SearchMainAdress21.this.is_search_done_correct()) {
				c_actual_station = RB_HL_SearchMainAdress21.this.rb_readValue_4_dataobject();
				return new MyLong(c_actual_station);
			}
			return null;
		}
		
	}



	@Override
	public E2_BtEditAdress get_button_to_open_mask_to_referenced_record() {
		return this.bt_editAdresse;
	}

	
}
