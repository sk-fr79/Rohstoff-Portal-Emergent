package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Arrays;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.BasicInterfaces.IF_Executer3Parts;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.SortButtonForResults;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.is_not_null;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class RB_HL_Search_V2_Adresse extends RB_SearchFieldV2 {

	
	public enum ADRESS_SEARCH_TYPE {
		 FIND_ALL_ADRESSES_MAIN_AND_LAGER_INCLUDE_MANDANT
		,FIND_ALL_ADRESSES_MAIN_AND_LAGER_EXCLUDE_MANDANT
		,FIND_ALL_ADRESSES_ONLY_MAIN_INCLUDE_MANDANT
		,FIND_ALL_ADRESSES_ONLY_MAIN_EXCLUDE_MANDANT
		,FIND_ONLY_MANDANT_ADRESS_INCLUDE_MAIN
		,FIND_ONLY_MANDANT_ADRESS_EXCLUDE_MAIN
		,FIND_ONLY_MANDANTEN_SONDERLAGER
		,UNDEFINED
	}

//	private ownButtonEditAdresse  	bt_editAdresse = new ownButtonEditAdresse();
	private String 					lieferantAbnehmer = "";
	private OwnButtonEditAdresse 	editAdresse = 		new OwnButtonEditAdresse();
	

	private ADRESS_SEARCH_TYPE      actualSearchType = ADRESS_SEARCH_TYPE.UNDEFINED;
	
	
	//erzeugt eine popup-zusatzkomponente mit der infozeile
	private String infoAufSuchherkunft = null;

	
	/**
	 * 
	 * @param exclude_own_adress
	 * @throws myException
	 */
	public RB_HL_Search_V2_Adresse() throws myException {
		super();
		this._setSaveSortSettingsKey(this.getClass().getCanonicalName());
		this._setOwnShape();
		this._setTable(_TAB.adresse);
		
		this.getSortButtonTextsHeaders()._a("Name")._a("Gehört zu HauptAdresse")._a("Land")._a("Ort")._a("Lief./Abn.")._a("Id");
		
		this._init();
	}


	public RB_SearchFieldV2 _setOwnShape() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		//this.getAddOnComponents()._put("EDIT_ADRESSE", editAdresse);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(editAdresse, gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(100,20,20,20,400);

		return this;
	}
	
	private void _init() throws myException {

		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));
		
		
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.getAndStatementCollectorForSearchInputs().or(new vgl(new TermSimple("TO_CHAR(JT_ADRESSE.ID_ADRESSE)"), COMP.EQ, new TermSimple("'#WERT#'")));
		
		//speziller term, der alle lager findet, deren hauptadresse mit dem suchterm uebereinstimmt
		SEL subSearch = new SEL(LIEFERADRESSE.id_adresse_liefer)
									.FROM(_TAB.lieferadresse)
									.INNERJOIN(new TableTerm(_TAB.adresse.fullTableName(),"LA"), new FieldTerm("LA", ADRESSE.id_adresse), LIEFERADRESSE.id_adresse_liefer)
									.INNERJOIN(new TableTerm(_TAB.adresse.fullTableName(),"BA"), new FieldTerm("BA", ADRESSE.id_adresse), LIEFERADRESSE.id_adresse_basis)
									.WHERE(new And()
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"BA",ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")))
											)
								;
		this.getAndStatementCollectorForSearchInputs().or(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+subSearch.s()+")"));
	
		
		
		this.set_iMaxResults(500);
		
	}
	
	
	/**
	 * bedingung zufuegen, die eine automatische suche erlaubt
	 * @author martin
	 * @date 26.09.2019
	 *
	 * @return
	 * @throws myException
	 */
	public RB_SearchFieldV2 _setFindOnlyMandantenAdresses() throws myException {

		this.getAndStatementCollectorForBasic().clear();
		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));

		
		SEL sel = new SEL(LIEFERADRESSE.id_adresse_liefer)
				.FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis,bibALL.get_ID_ADRESS_MANDANT()));

		this.getAndStatementCollectorForBasic().add(new Or(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+sel.s()+")")).OR(new vgl(ADRESSE.id_adresse,bibALL.get_ID_ADRESS_MANDANT())));
		
		this._setSearchIconAutomatikSearch();
		this._setAllowEmptySearchfield(true);
		
		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Es werden ausschliesslich die eigenen Adressen gefunden (auch bei leerem Suchfeld) !"));

		actualSearchType = ADRESS_SEARCH_TYPE.FIND_ONLY_MANDANT_ADRESS_INCLUDE_MAIN;
		
		this._setInfoAufSuchherkunft(null);
		
		return this;
	}
	
	
	
	/**
	 * bedingung zufuegen, die eine automatische suche erlaubt
	 * @author martin
	 * @date 26.09.2019
	 *
	 * @return
	 * @throws myException
	 */
	public RB_SearchFieldV2 _setFindOnlyMandantenSonderlager() throws myException {

		this.getAndStatementCollectorForBasic().clear();
		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new is_not_null(ADRESSE.sonderlager));

		
		SEL sel = new SEL(LIEFERADRESSE.id_adresse_liefer)
				.FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis,bibALL.get_ID_ADRESS_MANDANT()));

		this.getAndStatementCollectorForBasic().add(new Or(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+sel.s()+")")).OR(new vgl(ADRESSE.id_adresse,bibALL.get_ID_ADRESS_MANDANT())));
		
		this._setSearchIconAutomatikSearch();
		this._setAllowEmptySearchfield(true);
		
		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Es werden ausschliesslich Sonderlager gefunden !"));

		actualSearchType = ADRESS_SEARCH_TYPE.FIND_ONLY_MANDANTEN_SONDERLAGER;
		
		this._setInfoAufSuchherkunft(null);
		
		return this;
	}
	
	
	
	
	
	/**
	 * einstellung: suche nach allen adressen
	 * @author martin
	 * @date 07.01.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RB_SearchFieldV2 _setFindAllAdresses() throws myException {
		
		this.getAndStatementCollectorForBasic().clear();
		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));
		
		this._setSearchIconAutomatikSearch();
		this._setAllowEmptySearchfield(false);
		
		this.getButtonStartSearch()._ttt(S.ms("Suche nach Firmenhaupt-Adressen oder Lageradressen!"));

		actualSearchType = ADRESS_SEARCH_TYPE.FIND_ALL_ADRESSES_MAIN_AND_LAGER_INCLUDE_MANDANT;

		this._setInfoAufSuchherkunft(null);

		
		return this;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RB_SearchFieldV2 _setFindOnlyMainAdresses(boolean excludeMandant) throws myException {
		
		this.getAndStatementCollectorForBasic().clear();
		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));
		
		actualSearchType = ADRESS_SEARCH_TYPE.FIND_ALL_ADRESSES_ONLY_MAIN_INCLUDE_MANDANT;

		
		if (excludeMandant) {
			this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.id_adresse,COMP.NOT_EQ,bibALL.get_ID_ADRESS_MANDANT()));
			actualSearchType = ADRESS_SEARCH_TYPE.FIND_ALL_ADRESSES_ONLY_MAIN_EXCLUDE_MANDANT;
		}
		
		
		this._setSearchIconAutomatikSearch();
		this._setAllowEmptySearchfield(false);
		
		this.getButtonStartSearch()._ttt(S.ms("Suche nach Firmenhaupt-Adressen!"));
		
		this._setInfoAufSuchherkunft(null);

		return this;
	}
	

	
	
	

	/**
	 * 	 
	 *  einstellung, dass keine eignen adressen gefunden werden
	 * @author martin
	 * @date 07.01.2020
	 *
	 * @return
	 * @throws myException
	 */
	public RB_SearchFieldV2 _setFindAllOtherButMandantenAdresses() throws myException {

		this.getAndStatementCollectorForBasic().clear();
		//gesucht werden: haupt- oder lieferadressen
		this.getAndStatementCollectorForBasic().and(new Or(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO)).OR(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_LIEFERADRESSE)));
		//nur aktive
		this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));

		
		SEL sel = new SEL(LIEFERADRESSE.id_adresse_liefer)
				.FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis,bibALL.get_ID_ADRESS_MANDANT()));

		this.getAndStatementCollectorForBasic().and(new TermSimple(ADRESSE.id_adresse.tnfn()+" NOT IN ("+sel.s()+")"));
		this.getAndStatementCollectorForBasic().and(new TermSimple(ADRESSE.id_adresse.tnfn()+"<>"+bibALL.get_ID_ADRESS_MANDANT()));
		
		
		this._setSearchIconAutomatikSearch();
		this._setAllowEmptySearchfield(false);
		
		this.getButtonStartSearch()._ttt(S.ms("Suche nach Firmenhaupt-Adressen oder Lageradressen (nur Fremdadressen)"));

		actualSearchType = ADRESS_SEARCH_TYPE.FIND_ALL_ADRESSES_MAIN_AND_LAGER_EXCLUDE_MANDANT;

		
		return this;
	}
	
	
	
	/**
	 * sorgt dafuer, dass bei leerem suchfeld die eigenen adressen angezeigt werden 
	 * @author martin
	 * @date 26.09.2019
	 *
	 *
	 * @return
	 */
	public RB_HL_Search_V2_Adresse _setFindOwnAdresseWhenEmpytSearchField() {

		this._setAllowEmptySearchfield(true);
		this._setSearchIconAutomatikSearch();
		this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Suchbutton mit leerer Eingabe gedückt, dann werden die eigenen Adressen zu Auswahl gestellt !"));
		
		this._registerBeforeStartSearchEvent(()-> {
			try {
				if (S.isEmpty(getTextFieldSearchInput().getText())) {
					SEL sel = new SEL(LIEFERADRESSE.id_adresse_liefer)
							.FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis,bibALL.get_ID_ADRESS_MANDANT()));
	
					this.getAndStatementCollectorOneTimeCondition().add(new Or(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+sel.s()+")")).OR(new vgl(ADRESSE.id_adresse,bibALL.get_ID_ADRESS_MANDANT())));
					_setInfoAufSuchherkunft("Suchergebniss zeigt ausschliesslich die Adressen des Mandanten");
				} else {
					_setInfoAufSuchherkunft("");
				}
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
			}

		});
		
		
		return this;
	}
	
	
	
	@Override
	public E2_BasicModuleContainer generatePopupContainer() throws myException {
		return new OwnPopupContainer();
	}

	private class OwnPopupContainer extends E2_BasicModuleContainer {

		public OwnPopupContainer() {
			super();
			this.set_oExtWidth(new Extent(950));
			this.set_oExtHeight(new Extent(700));
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#createResultButtons(panter.gmbh.indep.dataTools.RECORD2.RecList21, panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2)
	 */
	@Override
	public ResultButtons createResultButtons(RecList21 rlSearchResult, RB_SearchFieldV2 searchField) {
		ResultButtons results = new ResultButtons(this.getSaveSortSettingsKey());
		
		try {
			for (Rec21 r: rlSearchResult) {
				ResultButton[] b = new   ResultButton[6];
				String sort_id = ("00000000000000000000000000000000").substring(r.getIdLong().toString().length())
						+ r.getIdLong().toString();
				
				Rec21_adresse ra = new Rec21_adresse(r);
				Rec21 land = ra.getRecLand();
				String s_land = "?";
				if (land!=null) {
					s_land = land.getFs(LAND.laendercode);
				}
				
				//spalte "Gehört zur hauptadresse" so sortieren, dass alle lieferadressen unterhalb der hauptadresse sortiert werden
				String sortSpalteMainAdresse = "";
				if (ra.__is_main_adresse()) {
					sortSpalteMainAdresse=ra._getMainAdresse().get__Name_flexible(" ")+"0"+ra.get__Name_flexible(" ");
				} else {
					sortSpalteMainAdresse=ra._getMainAdresse().get__Name_flexible(" ")+"1"+ra.get__Name_flexible(" ");
				}
						
				
				VEK<String> vLA = new VEK<>();
				if (ra.is_lieferant() ) {
					vLA._a("Lief.");
				}
				if (ra.is_abnehmer() ) {
					vLA._a("Abnehm.");
				}
				lieferantAbnehmer = new String();
				vLA.stream().forEach((s)-> {lieferantAbnehmer+=("/"+s);});
				if (lieferantAbnehmer.startsWith("/")) {
					lieferantAbnehmer = lieferantAbnehmer.substring(1);
				}
				
				b[0]= (ResultButton)new ResultButton(ra.get__Name_flexible(" "), 		ra, this)._t(ra.get__Name_flexible(" "))._style(E2_Button.baseStyle());
				b[1]= (ResultButton)new ResultButton(sortSpalteMainAdresse, 			ra, this)._t(ra._getMainAdresse().get__Name_flexible(" "))._style(E2_Button.baseStyle());
				b[2]= (ResultButton)new ResultButton(s_land, 							ra, this)._t(s_land)._style(E2_Button.baseStyle());
				b[3]= (ResultButton)new ResultButton(ra.getUfs(ADRESSE.ort,"-"), 		ra, this)._t(ra.get__PLZ_Anschrift(" "))._style(E2_Button.baseStyle());
				b[4]= (ResultButton)new ResultButton(S.NN(lieferantAbnehmer,"-)"), 				ra, this)._t(S.NN(lieferantAbnehmer,"-"))._style(E2_Button.baseStyle());
				b[5]= (ResultButton)new ResultButton(sort_id, 							ra, this)._t(ra.getFs(ADRESSE.id_adresse))
																					._style(E2_Button.baseStyle()._setProp(AbstractButton.PROPERTY_ALIGNMENT, Alignment.ALIGN_RIGHT, Alignment.ALIGN_RIGHT));
				results._a(b);
				
				//jetzt inaktiv schalten wenn die Adresse oder hauptAdresse inaktiv ist
				if (ra.is_no_db_val(ADRESSE.aktiv) || ra._getMainAdresse().is_no_db_val(ADRESSE.aktiv)) {
					Arrays.stream(b).forEach((rb)-> {
						try {
							rb.set_bEnabled_For_Edit(false);
						} catch (myException e) {
							rb.setEnabled(false);
							e.printStackTrace();
						}
					});
				}
				
				if (ra.__is_main_adresse()) {
					Arrays.stream(b).forEach((rb)-> { rb._b();	});
				}
			}
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._add(e.get_ErrorMessage());
			results.clear();
		}
		return results;
	}




	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#renderResultOnMask(panter.gmbh.Echo2.components.E2_Grid, java.lang.Long)
	 */
	@Override
	public void renderResultOnMask(E2_Grid gridForResults, Long id) throws myException {
		gridForResults._clear()._setSize(400)._bo_dd();
		if (id!=null) {
			Rec21_adresse  recAd = new Rec21_adresse()._fill_id(id);
			PAIR<String,String>  result = recAd.getVornameName1Name2OrtInfoZuFirma();
			gridForResults	._a(new RB_lab(result.getVal1())._i()._fsa(-1), new RB_gld()._left_top()._ins(2,0,2,0))
							._a(new RB_lab(result.getVal2())._i()._fsa(-1), new RB_gld()._left_top()._ins(2,0,2,0))
			;
		}
	}


	@Override
	public SEL generateSELWithoutWhere() throws myException {
		return new SEL(_TAB.adresse).FROM(_TAB.adresse);
	}

	
	
	public class OwnButtonEditAdresse extends E2_BtEditAdress {

		public OwnButtonEditAdresse() throws myException {
			super(true);
			this._ttt(S.mt("Adresse bearbeiten"));
		}

		@Override
		public MyLong find_id_adress() throws myException {
			String c_actual_station = null;
			if (is_search_done_correct()) {
				c_actual_station = rb_readValue_4_dataobject();
				return new MyLong(c_actual_station);
			}
			return null;
		}
		
	}

	public OwnButtonEditAdresse getEditAdresse() {
		return editAdresse;
	}


	public ADRESS_SEARCH_TYPE getActualSearchType() {
		return actualSearchType;
	}


	public RB_HL_Search_V2_Adresse _setActualSearchType(ADRESS_SEARCH_TYPE actualSearchType) {
		this.actualSearchType = actualSearchType;
		return this;
	}


	public String getInfoAufSuchherkunft() {
		return infoAufSuchherkunft;
	}


	public RB_HL_Search_V2_Adresse _setInfoAufSuchherkunft(String infoAufSuchherkunft) {
		this.infoAufSuchherkunft = infoAufSuchherkunft;
		if (S.isFull(this.infoAufSuchherkunft)) {
			boolean bereitsVorhanden = false;
			for (IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> executer: getListenersbeforeSearchResultGridRenderer()) {
				if (executer instanceof OwnRenderer) {
					bereitsVorhanden = true;
				}
			}
			if (! bereitsVorhanden) {
				this._addListenerbeforeSearchResultGridRenderer(new OwnRenderer());
			}
		} else {
			IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> executerToRemove = null;
			for (IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> executer: getListenersbeforeSearchResultGridRenderer()) {
				if (executer instanceof OwnRenderer) {
					executerToRemove = executer;
					break;
				}
			}
			if (executerToRemove!=null) {
				getListenersbeforeSearchResultGridRenderer().remove(executerToRemove);
			}
		}
		return this;
	}

	private class OwnRenderer implements IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> {
		@Override
		public void execute(E2_Grid o1, ResultButtons o2, VEK<SortButtonForResults> o3) {
			if (S.isFull(infoAufSuchherkunft)) {
				o1._a(new RB_lab()._tr(infoAufSuchherkunft), new RB_gld()._span(o1.getSize())._ins(10, 5,5,10)._col_back(new E2_ColorHelpBackground())._center_mid());
			}
		}
	}

	
	public RB_SearchFieldV2 _setAllowEmptySearchfield(boolean allowEmptySearchfield) {
		super._setAllowEmptySearchfield(allowEmptySearchfield);
		if (allowEmptySearchfield) {
			_setSearchIconAutomatikSearch();
		} else {
			_setSearchIconNormalSearch();
		}
		return this;
	}

	
	
}
