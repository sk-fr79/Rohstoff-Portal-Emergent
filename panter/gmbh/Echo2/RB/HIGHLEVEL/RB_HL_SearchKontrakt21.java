package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.ENUM_ListSortStatus;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchField.IF_RB_ResultButton_can_edit_searched_record;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SaveSortOfPopup;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.TRIPLE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;

public abstract class RB_HL_SearchKontrakt21 extends RB_SearchFieldSaveable implements IF_RB_ResultButton_can_edit_searched_record{

	private int 					textFieldInputWidth = 	100;
	private ENUM_VORGANGSART 		vorgangsart = null;

//	public enum SearchWoher {
//		SUCHE_START_VOM_SUCHFELD
//		,SUCHE_START_VOM_VOR_POPUP
//	}

	
	
	public abstract Rec21_adresse   getActualAdressFromMask();
	public abstract Rec21_artikel   getActualArtikelFromMask();
	
	/**
	 * 
	 * @param m_typ
	 * @throws myException
	 */
	public RB_HL_SearchKontrakt21(ENUM_VORGANGSART m_vg_art) throws myException {
		super();
		this.vorgangsart = m_vg_art;
		this._init();
	}

	/**
	 * 
	 * @param m_typ
	 * @param bShowEraser
	 * @throws myException
	 */
	public RB_HL_SearchKontrakt21(ENUM_VORGANGSART m_vg_art, boolean bShowEraser) throws myException {
		super(bShowEraser);
		this.vorgangsart = m_vg_art;
		this._init();
	}


	
	private void _init() throws myException {
		
		this._setRenderSearchResultVisibleOnMaskInEmptyManualSettings(true);

		this.get_tf_search_input().setWidth(new Extent(100));
		this._setFirstColInResultPopupFocusAble(false);

		if (this.vorgangsart==null) {
			throw new myException(this,"kontrakt-component MUST define vorgangsart !");
		}
		
		this.and_statement_collector_4_basic().and(new vgl(VKOPF_KON.vorgang_typ, this.vorgangsart.db_val()));
		
		this.and_statement_collector_4_basic().and(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).and(new vgl(ADRESSE.aktiv, "Y"));
		this.and_statement_collector_4_basic().and(new vgl_YN(VPOS_KON_TRAKT.abgeschlossen, false));
		this.and_statement_collector_4_basic().and(new vgl_YN(VPOS_KON.deleted, false));
		this.and_statement_collector_4_basic().and(new vgl_YN(VKOPF_KON.deleted, false));
		
		
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new TermSimple("TO_CHAR("+VPOS_KON.id_vpos_kon.tnfn()+")"), COMP.EQ, new TermSimple("REPLACE('#WERT#','.','')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VKOPF_KON.buchungsnummer), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		
		
		
		this.set_iMaxResults(500);
		
		//speicherstatus fuer die gewaehlte sortierung hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCH_KONTRAKT);
		
	}
	
	


	

	
	
	
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(RB_HL_SearchKontrakt21.this.rb_set_db_value_manual("", true,true));
			}
		};
	}


	@Override
	public  XX_ActionAgent  	generate_StartSearchAction() throws myException {
		return new ActionStartSearch(null);
	}

	
	private class ActionStartSearch extends XX_ActionAgent {
		
		private PAIR<Rec21_artikel, Rec21_adresse> pairAdressAndSorte = null;
		
		public ActionStartSearch(PAIR<Rec21_artikel, Rec21_adresse> p) {
			super();
			this.pairAdressAndSorte = p;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RB_HL_SearchKontrakt21 oThis = RB_HL_SearchKontrakt21.this;
			
			String c_text_4_search = S.NN(oThis.get_tf_search_input().getText());
			
			/*
			 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
			 */
			String cTextTest = bibALL.ReplaceTeilString(c_text_4_search,".","");
			if (bibALL.isInteger(cTextTest)) {
				c_text_4_search=cTextTest;
			}
					
			Rec21_adresse 	recAdress = oThis.getActualAdressFromMask();
			Rec21_artikel   recArtikel = oThis.getActualArtikelFromMask();
			
			Long    		idAdresse = null;
			Long 			idArtikel = null;
			
			if (recAdress!=null && recAdress.is_ExistingRecord()) {
				recAdress = recAdress._getMainAdresse();
				idAdresse = recAdress.getIdLong();
			}
			
			if (recArtikel!=null && recArtikel.is_ExistingRecord()) {
				idArtikel = recArtikel.getIdLong();
			}
			
			
			oThis.and_statement_collector_4_action().clear();
			if (idAdresse !=null || idArtikel!=null) {
				if (idAdresse!=null) {
					oThis.and_statement_collector_4_action().and(new vgl(ADRESSE.id_adresse,idAdresse.toString()));
				}
				if (idArtikel != null) {
					oThis.and_statement_collector_4_action().and(new vgl(VPOS_KON.id_artikel,idArtikel.toString()));
				}
			} else if (this.pairAdressAndSorte!=null) {
				oThis.and_statement_collector_4_action().and(new vgl(ADRESSE.id_adresse,this.pairAdressAndSorte.getVal2().getIdLong().toString()))
														.and(new vgl(VPOS_KON.id_artikel,this.pairAdressAndSorte.getVal1().getIdLong().toString()))
														;
			}
			
			//wenn beides leer ist, dann ein popup vorschalten, das 
			if (S.isEmpty(c_text_4_search) && oThis.and_statement_collector_4_action().size()==0) {
				//dann hier eine vorabfrage nach firmen und sorten und diese via popup starten
				new PopupWithPreSelect();
				
			} else {
				//sonst geht hier die suche weiter
				
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
						
						if (oThis.get_rb_ResultButtonArray().size()==0) {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
							bibMSG.add_MESSAGE(oThis.do_mask_settings_after_search("",true));
						} else if (oThis.get_rb_ResultButtonArray().size()==1) {
							((E2_IF_Handles_ActionAgents)oThis.get_rb_ResultButtonArray().get(0)[0]).doActionPassiv();				
							bibMSG.add_MESSAGE(new MyE2_Warning_Message("Genau ein Datensatz gefunden und geladen !"),false);
						} else {
							//popup aufbauen	
							E2_BasicModuleContainer  container = oThis.get_container_4_popupWindow();
			
							oThis.get_grid_container_4_popupWindow().removeAll();;
							oThis.fill_grid_4_popup(	oThis.get_grid_container_4_popupWindow(),
																					oThis.get_rb_ResultButtonArray(), 
																					oExecInfo);
							container.RESET_Content();
							container.add(oThis.get_grid_container_4_popupWindow(), E2_INSETS.I(4,4,4,4));
							
							
							container.CREATE_AND_SHOW_POPUPWINDOW(	oThis.get_width_popup_window(), 
																	oThis.get_height_popup_window(),
																	oThis.get_title_of_popup());
			
							container.save_focusable_components_outside(oThis.get_tf_search_input());
							
							if (oThis.isFirstColInResultPopupFocusAble()) {
								oThis.get_rb_ResultButtonArray().set_1st_column_focusable();
							}
							MyE2IF__Component  focusable = oThis.get_rb_ResultButtonArray().get_focus_component();
							bibALL.setFocus((Component) focusable);
							
						}
					}
				} catch (myException ex) {
					throw ex;
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new myException(ex.getLocalizedMessage());
				}
					
			}
			
		}




	}
	
	
	

	@Override
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		MyE2_MessageVector mv = bibMSG.newMV();
				
		Vector<String>      v_wheres = new Vector<String>();
		String 				search_block_with_placeholder = this.and_statement_collector_4_search().s();

		RB_HL_SearchKontrakt21 oThis = RB_HL_SearchKontrakt21.this;
		Long idAdresse = null;
		
		
		
		if (!(oThis.and_statement_collector_4_action().size()>0)) {
			if (S.isEmpty(oThis.get_tf_search_input().getText())) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie Suchbegriffe für eine Firma/Station ein !")));
			}
		}

		if (mv.get_bIsOK()) {

			if (S.isFull(search_text)) {
				StringSeparator 	v_separator = new StringSeparator(search_text," ");
				for (String s: v_separator) {
					v_wheres.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
				}
			}
			
			v_wheres.add("("+this.and_statement_collector_4_basic().s()+")");
			if (this.and_statement_collector_4_action().size()>0) {
				v_wheres.add("("+this.and_statement_collector_4_action().s()+")");
			}
			
			String sql_where = bibALL.Concatenate(v_wheres, " AND ", "1=1");  //suchanteil und zusatzbedingungen
			
			
			Vector<String> v_names = new Vector<String>();
			for (IF_Field f: VPOS_KON.values()) {
				v_names.add(f.tnfn());
			}
			
			SEL  s_query = new SEL(_TAB.vpos_kon).FROM(_TAB.vpos_kon)
							.INNERJOIN(_TAB.vkopf_kon, VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon)
							.INNERJOIN(_TAB.vpos_kon_trakt, VPOS_KON.id_vpos_kon, VPOS_KON_TRAKT.id_vpos_kon)
							.INNERJOIN(_TAB.adresse,   VKOPF_KON.id_adresse,  ADRESSE.id_adresse)
							;
			
			String c_sql_query = s_query.s()+" WHERE "+sql_where+" ORDER BY "+ADRESSE.name1.tnfn();
	
		//	DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
	
			RecList21 rlVposKon = new RecList21(_TAB.vpos_kon)._fill(c_sql_query);
			
			this.fillResultButtonArray(rlVposKon);	
		
		}
		return mv;
	}
	
	
	
	
	private void fillResultButtonArray(RecList21 rlVposKon) throws myException {
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();

		
		for (Rec21 r: rlVposKon) {
			
			Rec21_VPosKon 	recVposKon = 		new Rec21_VPosKon(r);
			Rec21_VKopfKon 	recVkopf = 			new Rec21_VKopfKon(r.get_up_Rec21(VKOPF_KON.id_vkopf_kon));
			Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));
			
			BigDecimal bdMenge = 				O.NN(recVposKon.get_raw_resultValue_bigDecimal(VPOS_KON.anzahl),BigDecimal.ZERO);
			BigDecimal bdGeliefert = 			O.NN(recVposKon.get_gesamt_fuhre_menge().get_bdWert(), BigDecimal.ZERO);
			BigDecimal bdOffen = 				bdMenge.subtract(bdGeliefert);
			BigDecimal bdPreis = 				O.NN(recVposKon.get_raw_resultValue_bigDecimal(VPOS_KON.einzelpreis), BigDecimal.ZERO);
			
			VEK<TRIPLE<String,String,MutableStyle>>  v_selColumns = new VEK<>();
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVkopf.getFs(VKOPF_KON.buchungsnummer, "<"+recVkopf.getFs(VKOPF_KON.id_vkopf_kon)+">")+"-"+recVposKon.getFs(VPOS_KON.positionsnummer,"0"),
																	recVkopf.getFs(VKOPF_KON.buchungsnummer, "<"+recVkopf.getFs(VKOPF_KON.id_vkopf_kon)+">")+"-"+recVposKon.getFs(VPOS_KON.positionsnummer,"0"),
																	RB_HL_SearchKontrakt21.styleListButtonsLeft()));

			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVkopf.getFs(VKOPF_KON.ist_fixierung, "N"),
																	recVkopf.getFs(VKOPF_KON.ist_fixierung, "N"),
																	RB_HL_SearchKontrakt21.styleListButtonsMid()));

			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVkopf.getFs(VKOPF_KON.druckdatum, "-"),
																	recVkopf.getFs(VKOPF_KON.druckdatum, "-"),
																	RB_HL_SearchKontrakt21.styleListButtonsMid()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recAdresse.get__FullNameAndAdress_Typ2(),
																	recAdresse.get__FullNameAndAdress_Typ2().toUpperCase(),
																	RB_HL_SearchKontrakt21.styleListButtonsLeft()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(3),
																	S.getNumberSortable(new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(3)),
																	RB_HL_SearchKontrakt21.styleListButtonsRight()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	new MyBigDecimal(bdOffen).get_FormatedRoundedNumber(3),
																	S.getNumberSortable(new MyBigDecimal(bdOffen).get_FormatedRoundedNumber(3)),
																	RB_HL_SearchKontrakt21.styleListButtonsRight()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVposKon.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2), 
																	recVposKon.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2),
																	RB_HL_SearchKontrakt21.styleListButtonsLeft()));
			 
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVposKon.getUfs(VPOS_KON.artbez1), 
																	recVposKon.getUfs(VPOS_KON.artbez1),
																	RB_HL_SearchKontrakt21.styleListButtonsLeft()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	new MyBigDecimal(bdPreis).get_FormatedRoundedNumber(2),
																	S.getNumberSortable(new MyBigDecimal(bdPreis).get_FormatedRoundedNumber(2)),
																	RB_HL_SearchKontrakt21.styleListButtonsRight()));
			
			v_selColumns._a(new TRIPLE<String,String,MutableStyle>(	recVposKon.getGueltigkeitsZeitraum(),
																	recVposKon.getGueltigkeitsZeitraum(),
																	RB_HL_SearchKontrakt21.styleListButtonsMid()));

			
			RB_ResultButton[] res_zeile = new RB_ResultButton[10];
			
			for (int i=0;i<10; i++) {
				res_zeile[i]= new own_result_button(this,recVposKon, v_selColumns.get(i).getVal1(),v_selColumns.get(i).getVal2(),v_selColumns.get(i).getVal3());
			}
			v_result_arrays.add(res_zeile);
		}

	}
	

	
	
	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {

		E2_Grid grid = new E2_Grid()._w100()._nB();
		Vector<Component>  v_buttons = new Vector<Component>();
		
		v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("Buchung-Nr."),	RB_HL_SearchKontrakt21.styleTitleButtonsLeft()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Fix"),			RB_HL_SearchKontrakt21.styleTitleButtonsMid()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Datum"),		RB_HL_SearchKontrakt21.styleTitleButtonsLeft()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,3,  new MyE2_String("Firma"),		RB_HL_SearchKontrakt21.styleTitleButtonsLeft()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("Menge"),		RB_HL_SearchKontrakt21.styleTitleButtonsRight()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,5,  new MyE2_String("Offen"),		RB_HL_SearchKontrakt21.styleTitleButtonsRight()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,6,  new MyE2_String("Sorte"),		RB_HL_SearchKontrakt21.styleTitleButtonsLeft()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,7,  new MyE2_String("Artikelbez"),	RB_HL_SearchKontrakt21.styleTitleButtonsLeft()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,8,  new MyE2_String("Preis"),		RB_HL_SearchKontrakt21.styleTitleButtonsRight()));
		v_buttons.add(new RB_SearchFieldListSortButton(this,9,  new MyE2_String("Gültig"),		RB_HL_SearchKontrakt21.styleTitleButtonsMid()));

		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<5 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		grid._s(10)	._a(v_buttons.get(0), new RB_gld()._col_back_d()._ins(2)._left_mid())
					._a(v_buttons.get(1), new RB_gld()._col_back_d()._ins(2)._center_mid())
					._a(v_buttons.get(2), new RB_gld()._col_back_d()._ins(2)._center_mid())
					._a(v_buttons.get(3), new RB_gld()._col_back_d()._ins(2)._left_mid())
					._a(v_buttons.get(4), new RB_gld()._col_back_d()._ins(2)._right_mid())
					._a(v_buttons.get(5), new RB_gld()._col_back_d()._ins(2)._right_mid())
					._a(v_buttons.get(6), new RB_gld()._col_back_d()._ins(2)._left_mid())
					._a(v_buttons.get(7), new RB_gld()._col_back_d()._ins(2)._left_mid())
					._a(v_buttons.get(8), new RB_gld()._col_back_d()._ins(2)._right_mid())
					._a(v_buttons.get(9), new RB_gld()._col_back_d()._ins(2)._center_mid())
					;
		
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
									MyRECORD_IF_RECORDS 		p_result_record, 
									String 						cText,		
									String 						sortstring,
									MutableStyle  				style) throws myException {
			super(calling_searchField, p_result_record, cText, style);
			this.sort_string = sortstring;
			
			this.setStyle(E2_Button.baseStyle());
			
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
	public E2_BtEditAdress get_button_to_open_mask_to_referenced_record() {
		return null;
	}

	
	public int getTextFieldInputWidth() {
		return textFieldInputWidth;
	}

	public RB_HL_SearchKontrakt21 _setTextFieldInputWidth(int textFieldInputWidth) {
		this.textFieldInputWidth = textFieldInputWidth;
		this.get_tf_search_input()._w(textFieldInputWidth);
		return this;
	}

	public ENUM_VORGANGSART getVorgangsart() {
		return vorgangsart;
	}
	
	
	
	
	private class PopupWithPreSelect extends E2_BasicModuleContainer {

		private E2_Grid grid = new E2_Grid();

		private VEK<PAIR<Rec21_artikel, Rec21_adresse>> allePaare = new VEK<>();
		private SortButton      						sortArtikel = new SortButton(0);
		private SortButton      						sortFirma = new SortButton(1);
		
		public PopupWithPreSelect() throws myException {
			super();
			
			this.add(grid, E2_INSETS.I(2,2,2,2));
			
			SEL  s_query = new SEL(VPOS_KON.id_artikel, VKOPF_KON.id_adresse).ADD_Distinct().FROM(_TAB.vpos_kon)
						.INNERJOIN(_TAB.vkopf_kon, VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon)
						.INNERJOIN(_TAB.vpos_kon_trakt, VPOS_KON.id_vpos_kon, VPOS_KON_TRAKT.id_vpos_kon)
						.INNERJOIN(_TAB.adresse,   VKOPF_KON.id_adresse,  ADRESSE.id_adresse)
						.WHERE(new vgl_YN(VPOS_KON_TRAKT.abgeschlossen, false))
						.AND(new vgl(VKOPF_KON.vorgang_typ,RB_HL_SearchKontrakt21.this.vorgangsart.db_val()))
						.AND(new vgl_YN(VKOPF_KON.deleted, false))
						.AND(new vgl_YN(VPOS_KON.deleted, false))
						;
			
			String[][] s_arr = bibDB.EinzelAbfrageInArray(new SqlStringExtended(s_query.s()));
			
			//jetzt aufbauen
			allePaare._clear();
			
			for (int i=0; i< s_arr.length; i++) {
				allePaare._a(
						new PAIR<>((Rec21_artikel)new Rec21_artikel()._fill_id(s_arr[i][0]),(Rec21_adresse)new Rec21_adresse()._fill_id(s_arr[i][1])));
			}
			
			//erste sortierung
			allePaare.sort((a,b)->{
				try {
					return a.getVal1().__get_anr1_artbez1(false).toUpperCase().compareTo(b.getVal1().__get_anr1_artbez1(false).toUpperCase());
				} catch (myException e) {
					e.printStackTrace();
					return 0;
				}
			});
			this.sortArtikel.actualStatus=ENUM_ListSortStatus.UP;
			
			
			rebuild();
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800),new Extent(800),S.ms("Auswahl mögliche Kontrakte ..."));
		}
		
		
		private void rebuild() throws myException {
			
			this.grid._w100()._clear()._setSize(400,400);
			
			RB_gld ld = new RB_gld()._ins(5,1,5,1);
			
			
			String typ = RB_HL_SearchKontrakt21.this.vorgangsart==ENUM_VORGANGSART.EK_KONTRAKT?"Lieferanten":"Abnehmer";
			this.grid._a(new RB_lab()._t(S.ms("Kontrakte folgender Kombinationen aus "+typ+" und Sorte sind vorhanden und offen:")), new RB_gld()._ins(5,5,5,5)._span(2));
			this.grid._a(this.sortArtikel, ld._c()._col_back_dd())._a(this.sortFirma, ld._c()._col_back_dd());
			
			for (PAIR<Rec21_artikel, Rec21_adresse> p: allePaare) {
				this.grid	._a(new OwnButton(p, false), ld )
							._a(new OwnButton(p, true), ld)
				;
			}
		}
 		
		
		private class OwnButton extends E2_Button {
			private PAIR<Rec21_artikel, Rec21_adresse> m_p = null;
			
			public OwnButton(PAIR<Rec21_artikel, Rec21_adresse> p, boolean showAdress) throws myException {
				super();
				this.m_p=p;
				if (showAdress) {
					this._t(p.getVal2().get__FullNameAndAdress_mit_id())._standard_text_button();
				} else {
					this._t(p.getVal1().__get_anr1_artbez1(true))._standard_text_button();
				}
				this._aaa(new ActionStartSearch(this.m_p))
					._aaa(()->{PopupWithPreSelect.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);});;
				
				
				this._style(E2_Button.baseStyle());
				this._width(new Extent(100,Extent.PERCENT));
				this._height(new Extent(100,Extent.PERCENT));
				
			}
		}

		
		private class SortButton extends E2_Button {
			private ENUM_ListSortStatus actualStatus = ENUM_ListSortStatus.NEUTRAL;
			private int colNumber = 0;
			
			public SortButton(int colNumber) throws myException {
				super();
				this.colNumber = colNumber;
				
				if (colNumber==0) {
					this._t("Sorte");
				} else if (colNumber==1) {
					this._t("Firma");
				} else {
					throw new myException("Error: 2b7f8998-199d-11e9-ab14-d663bd873d93: only 0 and 1 arre allowed!");
				}
				
				this._aaa(new SortAction());
			}
		
			private class SortAction extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					SortButton 			oThis 	= SortButton.this;
					PopupWithPreSelect	ooThis = PopupWithPreSelect.this;
					
					ENUM_ListSortStatus newStatus = 	oThis.actualStatus.getStatusNext();
					
					if (oThis.colNumber==0) {
						ooThis.sortFirma.actualStatus=ENUM_ListSortStatus.NEUTRAL;  //anderer Button neutral
						ooThis.sortArtikel.actualStatus=newStatus;  //anderer Button neutral

						//sortieren nach sorte
						ooThis.allePaare.sort((a,b)->{
							try {
								if (newStatus==ENUM_ListSortStatus.UP) {
									return a.getVal1().__get_anr1_artbez1(false).toUpperCase().compareTo(b.getVal1().__get_anr1_artbez1(false).toUpperCase());
								} else {
									return b.getVal1().__get_anr1_artbez1(false).toUpperCase().compareTo(a.getVal1().__get_anr1_artbez1(false).toUpperCase());
								}
							} catch (myException e) {
								e.printStackTrace();
								return 0;
							}
							
						});
						
					} else {
						ooThis.sortArtikel.actualStatus=ENUM_ListSortStatus.NEUTRAL;  //anderer Button neutral
						ooThis.sortFirma.actualStatus=newStatus;  //anderer Button neutral

						//sortieren nach Firma
						ooThis.allePaare.sort((a,b)->{
							try {
								if (newStatus==ENUM_ListSortStatus.UP) {
									return a.getVal2().get__FullNameAndAdress_mit_id().toUpperCase().compareTo(b.getVal2().get__FullNameAndAdress_mit_id().toUpperCase());
								} else {
									return b.getVal2().get__FullNameAndAdress_mit_id().toUpperCase().compareTo(a.getVal2().get__FullNameAndAdress_mit_id().toUpperCase());
								}
							} catch (myException e) {
								e.printStackTrace();
								return 0;
							}
							
						});

					}
					ooThis.rebuild();
				}
				
			}
			
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	
}
