package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SaveSortOfPopup;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class RB_HL_Select_Liefer_adressen extends RB_SearchFieldSaveable  implements IF_RB_ResultButton_can_edit_searched_record{

	
	private String 			c_id_adresse_basis_unformated = null;
	
	//hier eigene statement-collectoren
	private And     		and_statement_collector_4_basic1 = new And();         //fuer die suche nach hauptadressen
	private And     		and_statement_collector_4_basic2 = new And();         //fuer die suche nach lieferadressen

//	private boolean  		add_edit_button = true;
	
	
	private ownButtonEditAdresse  	bt_editAdresse = new ownButtonEditAdresse();

	
	public RB_HL_Select_Liefer_adressen(String p_id_adresse_basis_unformated ) throws myException {
		super();
		this.c_id_adresse_basis_unformated = p_id_adresse_basis_unformated;
		this._init();
	}

	public RB_HL_Select_Liefer_adressen(boolean bShowEraser, String p_id_adresse_basis_unformated) throws myException {
		super(bShowEraser);
		this.c_id_adresse_basis_unformated = p_id_adresse_basis_unformated;
		this._init();
	}


	
	private void _init() throws myException {
		
		//2016-11-17: im fall des leeren aufrufs, den aufruf in der session cachen
		this.set_key_to_store_resultbutton_array_in_session(bibSES_ENUM.SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD);
		
		this.and_statement_collector_4_basic1.and(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).and(new vgl(ADRESSE.aktiv, "Y"));
		this.and_statement_collector_4_basic2.and(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)).and(new vgl(ADRESSE.aktiv, "Y"));
		
		
		//force own adress
		this.and_statement_collector_4_basic1.and(new vgl(ADRESSE.id_adresse,COMP.EQ,this.c_id_adresse_basis_unformated));
			
		RECORD_ADRESSE  rec_eigene = new RECORD_ADRESSE(this.c_id_adresse_basis_unformated);
		RECLIST_LIEFERADRESSE rl = rec_eigene.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
		Vector<String> eigene_lieferadressen = bibVECTOR.get_Vector(rl.get_ID_ADRESSE_LIEFER_hmString_UnFormated("-1").values());

		//die basisadresse ist auch eine lieferadresse
		//eigene_lieferadressen.add(this.c_id_adresse_basis_unformated);
		
		//jetzt die eigenen lieferadressen als bedingung einschliessen
		this.and_statement_collector_4_basic2.and(
				new vgl(	ADRESSE.id_adresse,
							COMP.IN,
							new TermSimple("("+bibALL.Concatenate(eigene_lieferadressen, ",", "-1")+")")));
		
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("TO_CHAR(JT_ADRESSE.ID_ADRESSE)"), COMP.EQ, new TermSimple("'#WERT#'")));
		
		this.set_iMaxResults(500);
		
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHADRESS);

		
		
		
		//this.get_tf_search_input().set_bEnabled_For_Edit(false);
		this.get_buttonStartSearch().setIcon(E2_ResourceIcon.get_RI("firma.png"));
		
		RECORD_ADRESSE_extend  ra = new RECORD_ADRESSE_extend(this.c_id_adresse_basis_unformated);
		
		this.get_buttonStartSearch().setToolTipText(new MyE2_String("Lieferadressen zu: ",true, ra.get__FullNameAndAdress_Typ2(),false).CTrans());
		
		this.get_buttonStartSearch().__setImages(E2_ResourceIcon.get_RI("firma.png"),true);
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SELECT_LIEFER_ADRESSEN);

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
	
	
	
	public  XX_ActionAgent  	generate_StartSearchAction() throws myException {
		return new ownActionStartSearch();
	}
	
	
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_HL_Select_Liefer_adressen.this.rb_set_db_value_manual("",true,true);
			}
		};
	}


	
	@Override
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String p_searchText) throws myException {

		
		String search_text = p_searchText;
		
		//hier unterscheiden, ob ein suchstring vorhanden oder nicht
		
		//pruefen, ob es ein number-wert ist, wenn ja, dezimalen weg
		MyLong l = new MyLong(search_text);
		if (l.get_bOK()) {
			search_text = l.get_cUF_LongString();
		}

		
		if (S.isFull(search_text.trim())) {
			//dann suche wie sonst
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			Vector<String>      v_wheres1 = new Vector<String>();
			Vector<String>      v_wheres2 = new Vector<String>();
			String 				search_block_with_placeholder = this.and_statement_collector_4_search().s();
			
			for (String s: v_separator) {
				v_wheres1.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
				v_wheres2.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
			}
			
			v_wheres1.add("("+this.and_statement_collector_4_basic1.s()+")");
			v_wheres2.add("("+this.and_statement_collector_4_basic2.s()+")");
			
			String sql_where1 = bibALL.Concatenate(v_wheres1, " AND ", "1=1");  //suchanteil und zusatzbedingungen
			String sql_where2 = bibALL.Concatenate(v_wheres2, " AND ", "1=1");  //suchanteil und zusatzbedingungen
			
			RECLIST_ADRESSE rl = null;
			
			Vector<String> v_names = new Vector<String>();
			for (IF_Field f: ADRESSE.values()) {
				v_names.add(f.tnfn());
			}

			String field_list = bibALL.Concatenate(v_names, ",", "*");
			
			String c_sql_query =	"SELECT "+field_list+" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE "+sql_where1+
										" UNION "+
									"SELECT "+field_list+" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE NVL(AKTIV,'N')='Y' AND ID_ADRESSE IN"
											+ " (SELECT LA.ID_ADRESSE_LIEFER  FROM  "+bibE2.cTO()+".JT_LIEFERADRESSE LA "
												+ " WHERE LA.ID_ADRESSE_LIEFER "
													+ " IN "
													+ " (SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE "+sql_where2+") "+
												" ) "
												+ "  ORDER BY NAME1,NAME2,ID_ADRESSE ";

			DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());

			if (this.get_iMaxResults()>0) {
				rl = new RECLIST_ADRESSE(c_sql_query,this.get_iMaxResults(),true);
			} else {
				rl = new RECLIST_ADRESSE(c_sql_query);
			}
			
			RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
			v_result_arrays.clear();
			
			E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();

			E2_MutableStyle style_kursive = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontItalic());
			E2_MutableStyle style_bold_kursive = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBoldItalic());
			
			
			for (int i=0;i<rl.get_vKeyValues().size();i++) {
				RECORD_ADRESSE_extend  recAdresse = new RECORD_ADRESSE_extend(rl.get(i));
				RB_ResultButton[] res_zeile = new RB_ResultButton[5];
				

				E2_MutableStyle style_active = 			style;
				E2_MutableStyle style_active_firma = 	style_kursive;   //firmenspalte immer kursiv
				
				String c_firma = "";
				Vector<String> v_lieferant_abnehmer = new Vector<String>();
				

				String c_sortHelper = "";   //sorgt dafuer, dass beim sortieren nach firma die hauptadresse zuerst komme
				
				if (recAdresse.get_ADRESSTYP_lValue(-1l).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
					c_firma = recAdresse.get__FullNameAndAdress_Typ2();	
					style_active_firma = style_bold_kursive;
					if (recAdresse.is_lieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (recAdresse.is_abnehmer()) {v_lieferant_abnehmer.add("<ABN>");}
					c_sortHelper = "A";
				} else {
					RECORD_ADRESSE_extend  rec_hpt = recAdresse.get_main_Adress();
					c_firma = rec_hpt.get__FullNameAndAdress_Typ2();	
					if (rec_hpt.is_lieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (rec_hpt.is_abnehmer()) {v_lieferant_abnehmer.add("<ABN>");}
					c_sortHelper = "B";
				}
				
				String sort_id = ("00000000000000000000000000000000").substring(recAdresse.get_ID_ADRESSE_cF().length())+recAdresse.get_ID_ADRESSE_cF();
				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
				sort_texte.add(recAdresse.get__strasse_hausnummer().toUpperCase());
				sort_texte.add(c_firma+c_sortHelper+recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
				sort_texte.add(bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>").toUpperCase());
				sort_texte.add(sort_id);
				
				res_zeile[0]= new own_result_button(this,recAdresse, recAdresse.get__FullNameAndAdress_Typ2(),style_active,sort_texte.get(0));
				res_zeile[1]= new own_result_button(this,recAdresse, recAdresse.get__strasse_hausnummer(), style_active,sort_texte.get(1));
				res_zeile[2]= new own_result_button(this,recAdresse, c_firma, style_active_firma,sort_texte.get(2));
				res_zeile[3]= new own_result_button(this,recAdresse, bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>"), style,sort_texte.get(3));
				res_zeile[4]= new own_result_button(this,recAdresse, recAdresse.get_ID_ADRESSE_cF(), style_active,sort_texte.get(4));
				
				v_result_arrays.add(res_zeile);
				
			}

			
			
			
		} else {
			//dann alle eigenen adressen anzeigen
		
		
			//einfache alle adressen einsammeln und buttons draus machen
			Vector<RECORD_ADRESSE_extend>  v_alle_adresse = new Vector<RECORD_ADRESSE_extend>();

			//an dieser stelle den adressvektor aus dem cache laden (wenn die c_id_adresse_basis_unformated die mandantenadresse ist
			if (   this.c_id_adresse_basis_unformated.equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1")) 
					&& bibSES.getSessionValue(bibSES_ENUM.SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD)!=null
					&& bibSES.getSessionValue(bibSES_ENUM.SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD) instanceof Vector) {
				v_alle_adresse.addAll((Vector)bibSES.getSessionValue(bibSES_ENUM.SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD));
			} else {

				RECORD_ADRESSE_extend rae = new RECORD_ADRESSE_extend(this.c_id_adresse_basis_unformated);
				
				v_alle_adresse.add(rae);
				
				Vector<String>  v_ids_lieferadresse= new Vector<String>(rae.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis().get_ID_ADRESSE_LIEFER_hmString_UnFormated("").values());
				
				if (v_ids_lieferadresse.size()>0) {
					RECLIST_ADRESSE  rla = new RECLIST_ADRESSE(
													ADRESSE.id_adresse.fn()+" IN ("+bibALL.Concatenate(v_ids_lieferadresse, ",", "-1")+") AND "+
													bibDB_SYNTAX.GENERATE_YES_NO_WHERE(ADRESSE.fullTabName(),ADRESSE.aktiv.fn(),"Y"),
													"NAME1,NAME2,ID_ADRESSE");
								
					for (int i=0;i<rla.get_vKeyValues().size();i++) {
						v_alle_adresse.add(new RECORD_ADRESSE_extend(rla.get(i)));
					}
				}
				
				bibSES.setSessionValue(bibSES_ENUM.SAVE_LIST_EIGENE_LAGER_4_SELECT_IN_SEARCHFIELD, v_alle_adresse);
			}
	
			RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
			v_result_arrays.clear();
			
			E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
	
			E2_MutableStyle style_kursive = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontItalic());
			E2_MutableStyle style_bold_kursive = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBoldItalic());
			
			
			for (int i=0;i<v_alle_adresse.size();i++) {
				RECORD_ADRESSE_extend  recAdresse = v_alle_adresse.get(i);
				RB_ResultButton[] res_zeile = new RB_ResultButton[5];
				
	
				E2_MutableStyle style_active = 			style;
				E2_MutableStyle style_active_firma = 	style_kursive;   //firmenspalte immer kursiv
				
				String c_firma = "";
				Vector<String> v_lieferant_abnehmer = new Vector<String>();
				
	
				String c_sortHelper = "";   //sorgt dafuer, dass beim sortieren nach firma die hauptadresse zuerst komme
				
				if (recAdresse.get_ADRESSTYP_lValue(-1l).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
					c_firma = recAdresse.get__FullNameAndAdress_Typ2();	
					style_active_firma = style_bold_kursive;
					if (recAdresse.is_lieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (recAdresse.is_abnehmer()) {v_lieferant_abnehmer.add("<ABN>");}
					c_sortHelper = "A";
				} else {
					RECORD_ADRESSE_extend  rec_hpt = recAdresse.get_main_Adress();
					c_firma = rec_hpt.get__FullNameAndAdress_Typ2();	
					if (rec_hpt.is_lieferant()) {v_lieferant_abnehmer.add("<LIEF>");}
					if (rec_hpt.is_abnehmer()) {v_lieferant_abnehmer.add("<ABN>");}
					c_sortHelper = "B";
				}
				
				
				String sort_id = ("00000000000000000000000000000000").substring(recAdresse.get_ID_ADRESSE_cF().length())+recAdresse.get_ID_ADRESSE_cF();
				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
				sort_texte.add(recAdresse.get__strasse_hausnummer().toUpperCase());
				sort_texte.add(c_firma+c_sortHelper+recAdresse.get__FullNameAndAdress_Typ2().toUpperCase());
				sort_texte.add(bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>").toUpperCase());
				sort_texte.add(sort_id);
				
				
				res_zeile[0]= new own_result_button(this,recAdresse, recAdresse.get__FullNameAndAdress_Typ2(),style_active,sort_texte.get(0));
				res_zeile[1]= new own_result_button(this,recAdresse, recAdresse.get__strasse_hausnummer(), style_active,sort_texte.get(1));
				res_zeile[2]= new own_result_button(this,recAdresse, c_firma, style_active_firma,sort_texte.get(2));
				res_zeile[3]= new own_result_button(this,recAdresse, bibALL.Concatenate(v_lieferant_abnehmer, "/", "<undefiniert>"), style,sort_texte.get(3));
				res_zeile[4]= new own_result_button(this,recAdresse, recAdresse.get_ID_ADRESSE_cF(), style_active,sort_texte.get(4));
				
				v_result_arrays.add(res_zeile);
			}			
		}
		return new MyE2_MessageVector();
	}

	
	
	
	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		
		E2_Grid4MaskSimple gs = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
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
		
		gs  .def_(new E2_ColorDark()).def_(new Alignment(Alignment.LEFT,Alignment.TOP))
			.def_(200).add_(v_buttons.get(0))
			.def_(100).add_(v_buttons.get(1))
			.def_(200).add_(v_buttons.get(2))
			.def_(50).add_(v_buttons.get(3)).def_(new Alignment(Alignment.RIGHT,Alignment.TOP))
			.def_(50).add_(v_buttons.get(4))
			.def_(new E2_ColorBase());
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs		.def_(new Alignment(Alignment.LEFT,Alignment.TOP))
					.add_(arr[0].me()).add_(arr[1].me()).add_(arr[2].me()).add_(arr[3].me())
					.def_(new Alignment(Alignment.RIGHT,Alignment.TOP)).add_(arr[4].me());
		}
		gs.setSize_(5);
	
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

	
	
	private class ownActionStartSearch extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_HL_Select_Liefer_adressen oThis = RB_HL_Select_Liefer_adressen.this;
			
			try	{
				
				//evtl. vorhandene gespeicherte sortierung laden
				if (oThis.get_key_4_save_sorting()!=null) {
					new RB_SaveSortOfPopup(oThis).RESTORE();
				}

				
				oThis.get_rb_ResultButtonArray().clear();
				oThis.execute_searchquery_and_fill_resultbutton_array(S.NN(oThis.get_tf_search_input().getText()));
				
				if (oThis.get_rb_ResultButtonArray().size()==0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
				} else if (oThis.get_rb_ResultButtonArray().size()==1) {
					((E2_Button)oThis.get_rb_ResultButtonArray().get(0)[0]).doActionPassiv();				
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Genau ein Datensatz gefunden und geladen !"),false);
				} else {
					//popup aufbauen	
					E2_BasicModuleContainer  container = oThis.get_container_4_popupWindow();

					container.RESET_Content();
					container.add(oThis.get_grid_container_4_popupWindow(), E2_INSETS.I(4,4,4,4));
					

					oThis.get_grid_container_4_popupWindow().removeAll();;
					oThis.fill_grid_4_popup(	oThis.get_grid_container_4_popupWindow(),
												oThis.get_rb_ResultButtonArray(),
												oExecInfo);
					

					
					

					container.CREATE_AND_SHOW_POPUPWINDOW(	oThis.get_width_popup_window(), 
															oThis.get_height_popup_window(),
															oThis.get_title_of_popup());
					
					container.save_focusable_components_outside(oThis.get_tf_search_input());
					
					oThis.get_rb_ResultButtonArray().set_1st_column_focusable();
					MyE2IF__Component  focusable = oThis.get_rb_ResultButtonArray().get_focus_component();
					bibALL.setFocus((Component) focusable);
					

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException {
		super.set_bEnabled_For_Edit(Enabled);
	}

	
//	@Override
//	public  MyRECORD_IF_RECORDS  get_result_record_from_string(String unformated_MaskValue) throws myException {
//		return new RECORD_ADRESSE(unformated_MaskValue);
//	}

	
//	private class ownButtonEditAdresse extends RB_bt_open_adresse_fromID {
//
//		public ownButtonEditAdresse() throws myException {
//			super(new MyE2_String("Adresse bearbeiten "));
//		}
//
//		@Override
//		public String find_id_adresse_uf() throws myException {
//			return RB_HL_Select_Liefer_adressen.this.c_id_adresse_basis_unformated;
//		}
//
//		@Override
//		public void add_something_to_maskbutton_row(MyE2_Row maskButtonRow, boolean statusEdit) throws myException {
//		}
//		
//	}
//
	
	private class ownButtonEditAdresse extends E2_BtEditAdress {

		public ownButtonEditAdresse() throws myException {
			super();
			this._ttt(S.mt("Adresse bearbeiten"));
		}

		@Override
		public MyLong find_id_adress() throws myException {
			String c_actual_station = null;
			if (RB_HL_Select_Liefer_adressen.this.is_search_done_correct()) {
				c_actual_station = RB_HL_Select_Liefer_adressen.this.rb_readValue_4_dataobject();
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
