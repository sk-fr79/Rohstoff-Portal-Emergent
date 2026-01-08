package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceAddArtikelbezToAdresse;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
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
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSQL;
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
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTIKEL_BEZ_ext;

public abstract class RB_HL_SearchArtbez21 extends RB_SearchFieldSaveable {

	
	public static enum EnArtbezSearchEkOrVk {
		EK, VK
	}

	/**
	 * 
	 * @return RECORD_ADRESSE der aktuellen startadresse (kann eine Lageradresse
	 *         oder eine hauptadresse sein
	 * @throws myException
	 */
	public abstract Rec21_adresse findActualReferencedAdress(MyE2_MessageVector mv) throws myException;

	private EnArtbezSearchEkOrVk artbezSearchEkOrVk = EnArtbezSearchEkOrVk.EK;

	// variablen werden bei jedem suchvorgang gefuellt
	private RecList21 	rlAllArtikelBez = null;
	private RecList21 	rlAdressArtikelBez = null;    //mit unbeschraenkter auswahl
	
	private Rec21_adresse mainAdress = null;

	private MyE2_CheckBox cb_show_all = (MyE2_CheckBox) new MyE2_CheckBox(new MyE2_String("Auch nicht gelistete kundenspezifische Artikelbezeichnungen aus dem Suchergebnis anzeigen"), false, false)
															._aaa(new ownActionClickCheckbox_not_listed_artikel());

	// zusatzgrid fuer die steuerungen oberhalb der liste
	private E2_Grid add_on_grid = new E2_Grid();


	// wenn genau eine erlaubte artikel-bez gefunden wird, dann diese laden
	private own_result_button single_result_button = null;

	
	//duerfen bei vorhandener adresse nicht zugeordnete Artbez ausgewaehlt werden ?
	private boolean   allowNotRelatedArtbezToBeSelected = true;
	private boolean   allowAddNoteRelatedToAdress = true;
	
	public RB_HL_SearchArtbez21() throws myException {
		super();
		this._init();
		this.get_buttonStartSearch().add_oActionAgent(new actionUnsetCheckboxShowAllFoundArtikel(), true);
		this.get_tf_search_input().setWidth(new Extent(100));
	}

	private void _init() throws myException {

		this._setRenderSearchResultVisibleOnMaskInEmptyManualSettings(true);

		// basis-selektion
		this.and_statement_collector_4_basic().and(new vgl_YN(ARTIKEL_BEZ.aktiv, true));

		this.and_statement_collector_4_search()
				.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.anr1), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search()
				.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL_BEZ.anr2), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL_BEZ.artbez1),
				COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL_BEZ.artbez2),
				COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(
				new FieldTerm(ATTRIBUTES.TO_CHAR, ARTIKEL_BEZ.id_artikel_bez), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.artbez1), COMP.LIKE,
				new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER, ARTIKEL.artbez2), COMP.LIKE,
				new TermSimple("UPPER('%#WERT#%')")));

		this.set_iMaxResults(5000); // bei zu wenigen, kann eine leere suchmaske dazu fuehren, dass die gelisteten
									// der firma hinter den angezeigten liegt und damit nicht sichtbar wird

		// speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHARTBEZ);
	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db)  throws myException {
		gridcontainer_4_search_results.removeAll();
		MyE2_Label lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help = new MyE2_Label(" ");
		} else {
			lab_help = new MyE2_Label(new RECORD_ARTIKEL_BEZ_ext(c_result_value_4_db).get__complete_anr1_anr2_artbez1(),
					new E2_FontItalic(-2), true);
		}

		MyE2_Grid g = lab_help.get_InBorderGrid(new Border(1, new E2_ColorDark(), Border.STYLE_SOLID), new Extent(200),
				E2_INSETS.I(0, 0, 0, 0));
		g.setRowHeight(0, new Extent(20));
		gridcontainer_4_search_results.add(g);
	}

	public XX_ActionAgent generate_StartSearchAction() throws myException {
		return new own_searchActionAgent(null);
	}

	public XX_ActionAgent generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(RB_HL_SearchArtbez21.this.rb_set_db_value_manual("", true, true));
			}
		};
	}


	public EnArtbezSearchEkOrVk getArtbezSearchEkOrVk() {
		return artbezSearchEkOrVk;
	}

	public RB_HL_SearchArtbez21 _setArtbezSearchEkOrVk(EnArtbezSearchEkOrVk search_EK_VK) {
		this.artbezSearchEkOrVk = search_EK_VK;
		return this;
	}


	
	/**
	 * 
	 * @author martin
	 * @date 21.01.2019
	 * die moeglichkeite, dem searchangenten einen artikel zu uebergeben kommt aus der sortengruppen-auswahl via popup.
	 * bei der standard-suche ist m_artikel = null
	 *  
	 */
	private class own_searchActionAgent extends XX_ActionAgent {

		private Rec21_artikel m_artikel = null;

		public own_searchActionAgent(Rec21_artikel artikel) {
			super();
			this.m_artikel = artikel;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			RB_HL_SearchArtbez21 oThis = RB_HL_SearchArtbez21.this;

			String c_text_4_search = oThis.get_tf_search_input().getText();

			// jetzt evtl. rahmenbedingungen der suche festlegen
			oThis.and_statement_collector_4_action().clear();
			
			if (bibMSG.MV().isOK()) {
				if (m_artikel != null) {
					oThis.and_statement_collector_4_action()
							.and(new vgl(ARTIKEL.id_artikel, this.m_artikel.getIdLong().toString()));
				}
				/*
				 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit
				 * auch formatierte IDs gefunden werden
				 */
				String cTextTest = bibALL.ReplaceTeilString(c_text_4_search, ".", "");
				if (bibALL.isInteger(cTextTest)) {
					c_text_4_search = cTextTest;
				}

				try {

					oThis.get_rb_ResultButtonArray().clear();
					
					oThis.rlAllArtikelBez = null;
					
					bibMSG.add_MESSAGE(oThis.execute_searchquery_and_fill_resultbutton_array(c_text_4_search));

					if (bibMSG.get_bIsOK()) {
						//wenn eine suche gestartet wurde, dann ist oThis.rl_such_ergebnis != null
						//sonst ist zuerst das zwischenpopup zur sortenauswahl gestartet
						if (oThis.rlAllArtikelBez != null) {
							if (oThis.rlAllArtikelBez.size()>0) {
								// evtl. vorhandene gespeicherte sortierung laden
								if (oThis.get_key_4_save_sorting() != null) {
									new RB_SaveSortOfPopup(oThis).RESTORE();
								}
								
								// vorsortierung falls werte gesetzt sind
								oThis.get_rb_ResultButtonArray().sort();
								
								if (oThis.single_result_button != null) {
									oThis.single_result_button.doActionPassiv();
									bibMSG.add_MESSAGE(new MyE2_Warning_Message("Genau ein Datensatz gefunden und geladen !"),false);
								} else {
									//anzeigen
									oThis.get_grid_container_4_popupWindow().removeAll();
									oThis.fill_grid_4_popup(oThis.get_grid_container_4_popupWindow(), oThis.get_rb_ResultButtonArray(),	oExecInfo);
									oThis.get_container_4_popupWindow().RESET_Content();
									oThis.get_container_4_popupWindow().add(oThis.get_grid_container_4_popupWindow(), E2_INSETS.I(4, 4, 4, 4));
									oThis.get_container_4_popupWindow().CREATE_AND_SHOW_POPUPWINDOW(oThis.get_width_popup_window(), oThis.get_height_popup_window(),	oThis.get_title_of_popup());
									
								}
							} else {
								bibMSG.MV()._addAlarm(S.ms("Keine Sorten gefunden ! Bitte genauere Angaben machen !"));
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					bibMSG.MV()._addAlarm(ex.getLocalizedMessage());
				}
			}
		}

	}


	
	@Override
	public MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {
		
		MyE2_MessageVector mv = bibMSG.getNewMV();

		// basis-query, fuer alle nach den suchkriterien (ohne den einfluss der beschraenkung nach firma)
		SEL sel = new SEL(_TAB.artikel_bez).FROM(_TAB.artikel_bez).INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel,ARTIKEL.id_artikel);

		Triple<String> wheres = this.getWhereStatementParts(search_text);

		String whereFromUserInput = 	wheres.getVal1();
		String whereFromBase = 			wheres.getVal2();
		String whereFromAction = 		wheres.getVal3(); 		// bedingungen je nach momentaner situation (es gibt eine Firma oder
																// eine vorgegebene haupt-sorte)

		this.mainAdress = this.findActualReferencedAdress(bibMSG.MV());   // wenn die main adresse leer ist oder der mandant, dann suche via popup sonst direkt anzeigen
		if (this.mainAdress != null) {
			this.mainAdress = this.mainAdress._getMainAdresse();
			if (this.mainAdress.isAdressOfMandant()) {
				this.mainAdress=null;
			} 
		}

		
		boolean keineSpezifischenSorten = true;
		if (this.mainAdress!=null) {
			RecList21 rlSortenbez = this.mainAdress.getAllowedArtikelBezLief(this.artbezSearchEkOrVk.name());
			if (rlSortenbez.size()>0) {
				keineSpezifischenSorten=false;
			}
		}
		
		if (keineSpezifischenSorten && (S.isAllEmpty(whereFromUserInput, whereFromAction))) {
			
			// wenn es keine einschraenkung gibt, dann die vorselektion starten
			this.rlAdressArtikelBez = null;
			this.rlAllArtikelBez = null;
			new PopupWithPreSelect();
			
		} else {
			
			if (S.isFull(whereFromUserInput)) {
				sel.AND(new TermSimple(whereFromUserInput));
			}
			if (S.isFull(whereFromBase)) {
				sel.AND(new TermSimple(whereFromBase));
			}
			if (S.isFull(whereFromAction)) {
				sel.AND(new TermSimple(whereFromAction));
			}

			
			String sqlAllArtikelBez = sel.s();       //sql-query ohne beruecksichtigung der adress-ID
			
			if (this.mainAdress!=null) {
				sel.AND(new TermSimple(ARTIKEL_BEZ.id_artikel_bez.tnfn()+
						" IN (SELECT "+ARTIKELBEZ_LIEF.id_artikel_bez.tnfn()+" FROM "+_TAB.artikelbez_lief.n()
										+" WHERE "+ARTIKELBEZ_LIEF.id_adresse.tnfn()+"="+this.mainAdress.getIdLong().toString()
										+" AND "+  ARTIKELBEZ_LIEF.artbez_typ.tnfn()+"="+bibSQL.includeInTicks(O.NN(this.artbezSearchEkOrVk,EnArtbezSearchEkOrVk.EK).name(),true)
							+")"));		
			}
			
			
			String sqlAdressArtikelBez = sel.s();    //eingeschraenkt auf die adresse (falls eine angegeben ist)

			//zuerst die (evtl. engere) adresse-auswahl
			this.rlAdressArtikelBez = 	new RecList21(_TAB.artikel_bez)._fill( sqlAdressArtikelBez);
			this.rlAllArtikelBez = 		new RecList21(_TAB.artikel_bez);
			
			if (sqlAllArtikelBez.equals(sqlAdressArtikelBez)) {
				this.rlAllArtikelBez.putAll(this.rlAdressArtikelBez);
			} else {
				this.rlAllArtikelBez = new RecList21(_TAB.artikel_bez)._fill( new SqlStringExtended(sqlAllArtikelBez));
			}
			
			this.buildResultButtonArray();
			
			
		}

		return mv;
	}

	
	
	
	
	private void buildResultButtonArray() throws myException {

		this.single_result_button = null;

		RecList21 resultToUse = this.rlAdressArtikelBez;
		if (this.cb_show_all.isSelected()) {
			resultToUse = this.rlAllArtikelBez;
		}
		
		//falls das resultat leer ist und der schalter "zeige alle" ausgeschaltet, dann den schalter einschalten
		if (resultToUse.size()==0 && !this.cb_show_all.isSelected()) {
			this.cb_show_all.setSelected(true);
			resultToUse = this.rlAllArtikelBez;
		}
		
		
		this.get_rb_ResultButtonArray().clear();

		for (Rec21 r : resultToUse) {

			RB_ResultButton[] res_zeile = new RB_ResultButton[4];

			String sort_id = ("00000000000000000000000000000000").substring(r.getIdLong().toString().length())
					+ r.getIdLong().toString();

			Rec21_artikel_bez rb = new Rec21_artikel_bez(r);
			res_zeile[0] = new own_result_button(this, rb, rb.__get_ANR1(),  rb.__get_ANR1_ANR2());
			res_zeile[1] = new own_result_button(this, rb, rb.getUfs(ARTIKEL_BEZ.anr2),  null);
			res_zeile[2] = new own_result_button(this, rb, rb.getUfs(ARTIKEL_BEZ.artbez1),  rb.getUfs(ARTIKEL_BEZ.artbez1));
			res_zeile[3] = new own_result_button(this, rb, rb.getUfs(ARTIKEL_BEZ.id_artikel_bez), 	 sort_id);

			this.get_rb_ResultButtonArray().add(res_zeile);

		}

		if (this.get_rb_ResultButtonArray().size()==1) {
			this.single_result_button =(own_result_button) this.get_rb_ResultButtonArray().get(0)[0];
		}
		
		this.get_rb_ResultButtonArray().sort();

	}

	
	@Override
	public void fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo)  	throws myException {
		
		//alle ids, die direkt waehlbar sind
		VEK<Long> disabledIds = new VEK<>();
		if (this.rlAdressArtikelBez.size()!=this.rlAllArtikelBez.size()) {
			if (!this.allowNotRelatedArtbezToBeSelected) {
				disabledIds._a(this.rlAllArtikelBez.getLongs(ARTIKEL_BEZ.id_artikel_bez, true).values());
				disabledIds.removeAll(this.rlAdressArtikelBez.getLongs(ARTIKEL_BEZ.id_artikel_bez, true).values());
			}
		}

		//alle ids, die der adresse gehoeren
		VEK<Long> ownIds = new VEK<Long>();
		if (this.mainAdress!=null) {
			RecList21 rlSortenbez = this.mainAdress.getAllowedArtikelBezLief(this.artbezSearchEkOrVk.name());
			if (rlSortenbez.size()>0) {
				ownIds._a(rlSortenbez.getLongs(ARTIKELBEZ_LIEF.id_artikel_bez, true).values());
			}
		}
		
		Vector<Component> v_buttons = new Vector<Component>();
		v_buttons.add(new RB_SearchFieldListSortButton(this, 0, new MyE2_String("ANR1"), null));
		v_buttons.add(new RB_SearchFieldListSortButton(this, 1, new MyE2_String("ANR2"), null));
		v_buttons.add(new RB_SearchFieldListSortButton(this, 2, new MyE2_String("Artbez1"), null));
		v_buttons.add(new RB_SearchFieldListSortButton(this, 3, new MyE2_String("ID"), null));

		Integer i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS status_actual = this.get_rb_ResultButtonArray().get_actual_sort_status();

		// sortierstatus beruecksichtigen
		if (i_actual_sort_col != null && i_actual_sort_col >= 0 && i_actual_sort_col < 4 && status_actual != null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton) comp).set_sortstatus_actual(status_actual);
			}
		}

		//VEK<Long> aktive = new VEK<Long>()._a(this.rlAdressArtikelBez.getLongs(ARTIKEL_BEZ.id_artikel_bez, true).values());
		
		E2_Grid gs = new E2_Grid()._nB()._setSize(80,50,400,50,20);
		gs._a(v_buttons.get(0), new RB_gld()._left_mid())._a(v_buttons.get(1), new RB_gld()._left_mid())._a(v_buttons.get(2), new RB_gld()._left_mid())._a(v_buttons.get(3), new RB_gld()._center_mid())._a();

		for (RB_ResultButton_IF[] arr : this.get_rb_ResultButtonArray()) {
			gs._a((E2_Button)arr[0])._a((E2_Button)arr[1])._a((E2_Button)arr[2])._a((E2_Button)arr[3]);
			Long id = ((Rec21)arr[0].get_result_record()).getIdLong();
			boolean aktiv = !disabledIds.contains(id);
			((E2_Button)arr[0]).set_bEnabled_For_Edit(aktiv);
			((E2_Button)arr[1]).set_bEnabled_For_Edit(aktiv);
			((E2_Button)arr[2]).set_bEnabled_For_Edit(aktiv);
			((E2_Button)arr[3])._al_center().set_bEnabled_For_Edit(aktiv);

			
			String typ = "EK-Sorte";
			if (O.NN(this.artbezSearchEkOrVk,EnArtbezSearchEkOrVk.EK)==EnArtbezSearchEkOrVk.VK) {
				typ = "VK-Sorte";
			}
			
			Component compInSpalte5 = new RB_lab(); 
			if (!aktiv && this.allowAddNoteRelatedToAdress && this.mainAdress!=null) {
				//dann den zufuegebutton hier rein, sonst nur ein label
				compInSpalte5=new ActivateButton(this.mainAdress, new Rec21_artikel_bez((Rec21)arr[0].get_result_record()));
			} else if (ownIds.contains(id) && this.mainAdress!=null) {
				compInSpalte5 = new E2_Button()._image("aktiv.png")._ttt(S.ms("Sortenbezeichnung ist der Firma ").ut(this.mainAdress.__get_name1_ort()).t(" als "+typ+" zugeordnet !"));
			}
			
			gs._a(compInSpalte5, new RB_gld()._center_mid());
			
		}

		grid_4_popup.removeAll();
		
		if (this.rlAdressArtikelBez.size()!=this.rlAllArtikelBez.size()) {
			this.add_on_grid.removeAll();
			this.add_on_grid._a(this.cb_show_all);
			grid_4_popup.add(this.add_on_grid, E2_INSETS.I(0, 0, 0, 4));
		}
		grid_4_popup.add(gs, E2_INSETS.I(0, 0, 0, 0));
	}

	
	/**
	 * eigener resultbutton, der eine sorte, die nicht im erlaubten block ist, dem
	 * lieferanten hinzufuegt
	 * 
	 * @author martin
	 *
	 */
	private class ActivateButton extends E2_Button {

		public ActivateButton(Rec21_adresse adresse, Rec21_artikel_bez p_result_record) throws myException {
			super();
			this.setStyle(E2_Button.StyleImageButton());

			this.setIcon(E2_ResourceIcon.get_RI("multi_select_add_new.png"));
			if (adresse != null) {
				this._ttt(new MyE2_String("Sorte zu den EK-Artikel der Firma ", true,
						adresse.get__FullNameAndAdress_mit_id(), false, " hinzufügen !", true));
				this.add_oActionAgent(new ownActionAddArtikelBez(adresse, p_result_record));
			}
		}
	}

	
	
	


	
	private class ownActionAddArtikelBez extends XX_ActionAgent {
		private Rec21_adresse f_recAdress = null;
		private Rec21_artikel_bez f_recArtBez = null;

		public ownActionAddArtikelBez(Rec21_adresse recAdress, Rec21_artikel_bez recArtBez) {
			super();
			this.f_recAdress = recAdress;
			this.f_recArtBez = recArtBez;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PdServiceAddArtikelbezToAdresse artbezAdd = new PdServiceAddArtikelbezToAdresse(this.f_recAdress,this.f_recArtBez);
			artbezAdd.get_btSave().add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RB_HL_SearchArtbez21.this.execute_searchquery_and_fill_resultbutton_array(RB_HL_SearchArtbez21.this.get_tf_search_input().getText());
					RB_HL_SearchArtbez21.this.fill_grid_4_popup(RB_HL_SearchArtbez21.this.get_grid_container_4_popupWindow(),RB_HL_SearchArtbez21.this.get_rb_ResultButtonArray(), oExecInfo);
					
				}
			});
		}
	}

	private class own_result_button extends RB_ResultButton {
		private String sort_string = null;

		public own_result_button(RB_SearchFieldSaveable calling_searchField, MyRECORD_IF_RECORDS p_result_record,String cText, String sortstring) throws myException {
			super(calling_searchField, p_result_record, cText, E2_Button.baseStyle());
			this.sort_string = sortstring;

			if (S.isEmpty(this.sort_string)) {
				this.sort_string = cText;
			}

			this._style(E2_Button.baseStyle());

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
			RB_HL_SearchArtbez21 oThis = RB_HL_SearchArtbez21.this;
			oThis.buildResultButtonArray();
			oThis.fill_grid_4_popup(oThis.get_grid_container_4_popupWindow(), oThis.get_rb_ResultButtonArray(),	oExecInfo);
		}
	}

	
	

	private class actionUnsetCheckboxShowAllFoundArtikel extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_HL_SearchArtbez21.this.cb_show_all.setSelected(false);
		}
	}

	
	
	
	
	
	
	/**
	 * eigener popup, der bei leeren suchanfragen ueber die artikelgruppe -> artikel -vorselektiert
	 * @author martin
	 * @date 21.01.2019
	 *
	 */
	private class PopupWithPreSelect extends E2_BasicModuleContainer {

		private E2_Grid gridLeftRight = new E2_Grid()._setSize(500);
		private E2_Grid gridANR1_SUB = 	new E2_Grid()._setSize(30,30,30,30,30);
		private E2_Grid gridArtikel = 	new E2_Grid()._setSize(500);

		public PopupWithPreSelect() throws myException {
			super();

			gridLeftRight._clear()._a(gridANR1_SUB, new RB_gld()._left_top()._ins(3));

			this.add(gridLeftRight, E2_INSETS.I(2, 2, 2, 2));

			SEL s_query = new SEL("SUBSTR(" + ARTIKEL.anr1.tnfn() + ",1,2)").ADD_Distinct().FROM(_TAB.artikel)
					.ORDERUP(new TermSimple("1"));

			String[][] s_arr = bibDB.EinzelAbfrageInArray(new SqlStringExtended(s_query.s()));

			this.gridANR1_SUB._w100()._clear()._setSize(60,60,60,60,60);

			this.gridANR1_SUB._a(new RB_lab()._t(S.ms("Vorauswahl Hauptsorten ..."))._fsa(3)._b(), new RB_gld()._span(5)._ins(2,2,10,2));
			
			
			for (int i = 0; i < s_arr.length; i++) {
				this.gridANR1_SUB._a(new OwnButtonAnr1(s_arr[i][0]), new RB_gld()._ins(2));
			}
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(800), S.ms("Auswahl mögliche Sorten ..."));
		}

		// button der z.b. alle sorten sucht, die mit 01 beginnen
		private class OwnButtonAnr1 extends E2_Button {

			private SEL search = null;
			private String anr1_12="";

			public OwnButtonAnr1(String anr1_12) throws myException {
				super();
				this.anr1_12 = anr1_12;
				this._t(anr1_12);

				this.search = new SEL(_TAB.artikel).FROM(_TAB.artikel)
						.WHERE(new TermSimple(
								"SUBSTR(" + ARTIKEL.anr1.tnfn() + ",1,2)=" + bibSQL.includeInTicks(anr1_12, true)))
						.ORDERUP(ARTIKEL.anr1);

				this._aaa(new OwnActionSelectArtikel());
				this._aaa(()->{bibMSG.MV()._addWarnUT("Hello World2");});
				
				this._style(E2_Button.baseStyle());
				this._width(new Extent(95, Extent.PERCENT));
				this._height(new Extent(95, Extent.PERCENT));
			}

			private class OwnActionSelectArtikel extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RecList21 rl = new RecList21(_TAB.artikel)._fill(new SqlStringExtended(search.s()));
					gridLeftRight._clear()._a(gridArtikel,  new RB_gld()._left_top()._ins(3));
					
					gridArtikel._clear();

					//zuerst den zurueck-button
					gridArtikel._a((E2_Button)new E2_Button()._style(E2_Button.baseStyle())._t(S.ms("<--- Zurück zu Sortengruppen"))._b()._fsa(2)._i()._aaa(
										()->{gridLeftRight._clear()._a(gridANR1_SUB, new RB_gld()._left_top()._ins(3));}
									), new RB_gld()._ins(3,10,3,10));
					gridArtikel._a(new RB_lab()._t(S.ms("Sorten, die zur Gruppe ").ut(anr1_12).t(" gehören !"))._fsa(2), 
									new RB_gld()._ins(3,10,3,10));
					
					for (Rec21 r : rl) {
						Rec21_artikel r_art = new Rec21_artikel(r);
						E2_Button btStartSearch = new E2_Button()._t(r_art.__get_anr1_artbez1(true));
						btStartSearch._aaa(new own_searchActionAgent(r_art));
						btStartSearch._aaa(()->{PopupWithPreSelect.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);});
						btStartSearch._style(E2_Button.baseStyle());
						gridArtikel._a(btStartSearch, new RB_gld()._ins(2));
					}
				}
			}
		}

	}



	public boolean isAllowNotRelatedArtbezToBeSelected() {
		return allowNotRelatedArtbezToBeSelected;
	}

	public RB_HL_SearchArtbez21 _setAllowNotRelatedArtbezToBeSelected(boolean allowNotRelatedArtbezToBeSelected) {
		this.allowNotRelatedArtbezToBeSelected = allowNotRelatedArtbezToBeSelected;
		return this;
	}

}
