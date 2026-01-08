
package rohstoff.businesslogic.bewegung2.lager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_von_bis;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;
import rohstoff.businesslogic.bewegung2.global.ENUM_SELEKTOR_Lagertyp;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.Table_alias;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_EW_FW_Abrechenbar;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_EW_FW_ST;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_FirmaUndLager;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_Lager_NG;
import rohstoff.businesslogic.bewegung2.lager.selector.B2_LIST_Selector_TypenEinblenden;

public class B2_LAG_LIST_Selector extends E2_ExpandableRow {

	private E2_SelectionComponentsVector     oSelVector = null;

	private RB_TransportHashMap   m_tpHashMap = null;

	private B2_LIST_Selector_Lager_NG  			oSelMulti_Lager				= null;

	private UTIL_MultiSelectField_Hauptsorte	oSelMulti_Hauptsorte 		= null;
	private UTIL_MultiSelectField_Sorte			oSelMulti_Sorte 			= null;

	private B2_LIST_Selector_TypenEinblenden 	oSelBewegungtypen 			= null;
	private ownTF4Datum 						oDatumVon 					= null;
	private ownTF4Datum 						oDatumBis 					= null;

	private MyE2_SelectField 					oSelMonate 					= null;
	private MyE2_SelectField 					oSelJahre 					= null;

	private MyE2_SelectFieldWithParameters 		oSelArtikelName				= null;

	private ownSelectorEinheit 					oSel_einheit				= null;

	private B2_LIST_Selector_EW_FW_Abrechenbar oSelEwFw_abrechnenbar;

	private SelectorSortenVonBis_Multi oSelSortenBereich;

	private B2_LIST_Selector_FirmaUndLager oSelFirmaLagerVon;

	private B2_LIST_Selector_FirmaUndLager oSelFirmaLagerBis;

	private B2_LIST_Selector_FirmaUndLager oSelFirmaLager;

	private B2_LIST_Selector_EW_FW_ST oSelectorLagerTypen;

	private static String cBasisQueryMultiBereichSelektor = "SELECT DISTINCT  JT_ARTIKEL.ANR1 || ' - ' ||  JT_ARTIKEL.ARTBEZ1 , JT_ARTIKEL.ANR1  from "+ bibE2.cTO()
	+ ".JT_LAGER_KONTO "
	+ " JOIN " + bibE2.cTO() + ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
	" #WHERE# ";

	public B2_LAG_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
	{
		super(S.ms("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));


		this.m_tpHashMap = p_tpHashMap;       
		this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());

		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );

		String jahre_abfrage = new SEL().ADD_Distinct().ADDFIELD("to_char(JT_BG_ATOM.datum_ausfuehrung,'YYYY')").ADDFIELD("to_char(JT_BG_ATOM.datum_ausfuehrung,'YYYY')").FROM(_TAB.bg_atom)
				.WHERE(new vgl(BG_ATOM.id_mandant, bibALL.get_ID_MANDANT())).ORDER("1").s();

		String artikelname_abfrage = new SEL().ADD_Distinct().ADDFIELD(ARTIKEL.artbez1).ADDFIELD(ARTIKEL.id_artikel).FROM(_TAB.artikel)
				.WHERE(new vgl(ARTIKEL.id_mandant, bibALL.get_ID_MANDANT())).s() + "#P1# ORDER BY 1";	

		ownSelectorBewegungTyp sel_bew_typ	= new ownSelectorBewegungTyp();

		oSelectorLagerTypen = new B2_LIST_Selector_EW_FW_ST(this.m_tpHashMap.getNavigationList(),true,"");
		oSelectorLagerTypen.set_CheckboxIsExternal(2);
		oSelVector.add (oSelectorLagerTypen);
		
		this.oSelFirmaLager = new B2_LIST_Selector_FirmaUndLager( 
				"jt_bg_atom.id_bg_vektor in (select id_bg_vektor from jt_bg_station where jt_bg_station.id_adresse_basis= #WERT# )",
				"jt_bg_atom.id_bg_vektor in (select id_bg_vektor from jt_bg_station where jt_bg_station.id_adresse = #WERT# )"
				);
		this.oSelFirmaLager.setDefaultValue(bibALL.get_ID_ADRESS_MANDANT());
		
		String vonAbfrage = "JT_BG_ATOM.id_bg_vektor in (select uatom.id_bg_vektor from jt_bg_atom uatom"
				+ " inner join jt_bg_station ustation on uatom.id_bg_station_quelle = ustation.id_bg_station"
				+ " where uatom.pos_in_mask = 'A1' AND ";
		this.oSelFirmaLagerVon = new B2_LIST_Selector_FirmaUndLager( 
				 vonAbfrage +"ustation.id_adresse_basis = #WERT#)",
				vonAbfrage +"ustation.id_adresse = #WERT# )");
		this.oSelFirmaLagerVon._setLagerLabelText("Startlager:")._setFirmaLabelText("Start Firma:");

		String bisAbfrage = "JT_BG_ATOM.id_bg_vektor in (select uatom.id_bg_vektor from jt_bg_atom uatom"
				+ " inner join jt_bg_station ustation on uatom.id_bg_station_ziel = ustation.id_bg_station"
				+ " where uatom.pos_in_mask = 'A2' AND ";
		this.oSelFirmaLagerBis = new B2_LIST_Selector_FirmaUndLager( 
				bisAbfrage +"ustation.id_adresse_basis = #WERT#)",
				bisAbfrage +"ustation.id_adresse = #WERT#)");
		this.oSelFirmaLagerBis._setLagerLabelText("Ziellager:")._setFirmaLabelText("Ziel Firma:");


		this.oSel_einheit 					= new ownSelectorEinheit();

		this.oSelMulti_Hauptsorte 			= new UTIL_MultiSelectField_Hauptsorte("", 	470, "SUBSTR(ART.ANR1,0,2) = '#WERT#'");
		this.oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		this.oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new ownActionClearSortenBereichsMultiSelector());

		this.oSelMulti_Sorte 				= new UTIL_MultiSelectField_Sorte("", 		470, "ART.id_artikel = #WERT#");
		this.oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionSynchronizeSortenName());
		this.oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(new ownActionClearSortenBereichsMultiSelector());

		this.oSelArtikelName = new MyE2_SelectFieldWithParameters(artikelname_abfrage, false, true, false, false);
		this.oSelArtikelName.add_oActionAgent(new ownActionClearSortenBereichsMultiSelector(), true);
		this.oSelArtikelName.add_oActionAgent(new actionSynchronizeSorten(),true);
		this.oSelArtikelName.AddParameter("#P1#");
		this.oSelArtikelName.setWidth(new Extent(470));
		this.oSelArtikelName.RefreshComboboxFromSQL();

		this.oSelBewegungtypen				= new B2_LIST_Selector_TypenEinblenden(this.m_tpHashMap.getNavigationList());
		this.oSelEwFw_abrechnenbar			= new B2_LIST_Selector_EW_FW_Abrechenbar(m_tpHashMap.getNavigationList());
		this.oSelSortenBereich 				= new SelectorSortenVonBis_Multi();

		String von_sql_stmt = "NVL(to_char("+BG_VEKTOR.datum_planung_von.fn(Table_alias.VEKTOR.getAlias()) +",'yyyy-MM-dd' ) "
				+ ",NVL(to_char("+BG_ATOM.datum_ausfuehrung.tnfn()+",'yyyy-MM-dd'),"
				+ "''))"
				+ ">= '#WERT#' ";	

		String bis_sql_stmt = "NVL(to_char("+BG_VEKTOR.datum_planung_bis.fn(Table_alias.VEKTOR.getAlias()) +",'yyyy-MM-dd' ) "
				+ ",NVL(to_char("+BG_ATOM.datum_ausfuehrung.tnfn()+",'yyyy-MM-dd' )"
				+ ",''))"
				+ "<= '#WERT#' ";

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MONTH, -12);
		this.oDatumVon = new ownTF4Datum(myDateHelper.FormatDateNormal(cal.getTime()), true);
		cal = new GregorianCalendar();
		this.oDatumBis = new ownTF4Datum(myDateHelper.FormatDateNormal(cal.getTime()), true);

		this.oSelMonate = new MyE2_SelectField(B2_LAG_CONST.monatliste,"",false);
		this.oSelMonate.add_oActionAgent(new actionAgentJahre(false));

		this.oSelJahre = new MyE2_SelectFieldWithParameters(jahre_abfrage, false, true,false, false);
		this.oSelJahre.RefreshComboboxFromSQL();
		this.oSelJahre.add_oActionAgent(new actionAgentJahre(true));
		
		this.oSelVector.add(oSelFirmaLager);
		this.oSelVector.add(oSelFirmaLagerBis);
		this.oSelVector.add(oSelFirmaLagerVon);

		this.oSelVector.add(oSelSortenBereich);
		this.oSelVector.add(oSelMulti_Hauptsorte);
		this.oSelVector.add(oSelMulti_Sorte);
		this.oSelVector.add(new E2_ListSelectorStandard(oSelArtikelName, "ART.ID_ARTIKEL = #WERT#", null, null));
		this.oSelVector.add(oSel_einheit);
		this.oSelVector.add(new E2_ListSelectorStandard(oDatumVon, von_sql_stmt,null));
		this.oSelVector.add(new E2_ListSelectorStandard(oDatumBis, bis_sql_stmt,null));
		this.oSelVector.add(oSelEwFw_abrechnenbar);
		this.oSelVector.add(sel_bew_typ);

		this.oSelFirmaLager.addExecuterAfterFirmaSearch(()->clearLagerSelector(enumSourceLager.VONBIS));
		this.oSelFirmaLager.addExecuterAfterHomeJump(()->clearLagerSelector(enumSourceLager.VONBIS));
		
		this.oSelFirmaLagerVon.addExecuterAfterFirmaSearch(()->clearLagerSelector(enumSourceLager.VON));
		this.oSelFirmaLagerVon.addExecuterAfterHomeJump(()->clearLagerSelector(enumSourceLager.VON));
		
		this.oSelFirmaLagerBis.addExecuterAfterFirmaSearch(()->clearLagerSelector(enumSourceLager.BIS));
		this.oSelFirmaLagerBis.addExecuterAfterHomeJump(()->clearLagerSelector(enumSourceLager.BIS));
		
		E2_Grid lager_grid 				= new E2_Grid()._bo_no()._setSize(800)
				._a(oSelFirmaLager.get_oComponentForSelection(), new RB_gld()._ins(4,1,4,4)._left_top())
				;

		E2_Grid lager_grid_startziel 	= new E2_Grid()._bo_no()._setSize(650,800)
				._a(oSelFirmaLagerVon.get_oComponentForSelection()	, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(oSelFirmaLagerBis.get_oComponentForSelection()	, new RB_gld()._ins(4,1,0,1)._left_top())
				;

		E2_Grid  sorte_grid = new E2_Grid()._bo_no()._setSize(100,550)
				._a(new RB_lab()._t("Hauptsorte:")						, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(oSelMulti_Hauptsorte.get_oComponentForSelection() 	, new RB_gld()._ins(4,1,1,1)._left_top())
				._a(new RB_lab()._t("Sorte:")							, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(oSelMulti_Sorte.get_oComponentForSelection() 		, new RB_gld()._ins(4,1,1,1)._left_top())
				._a(new RB_lab()._t("Sortenname:")						, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(oSelArtikelName										, new RB_gld()._ins(4,1,1,1)._left_top())			
				._a(new RB_lab()._t("Sortenbereich:")					, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(oSelSortenBereich.get_oComponentForSelection()		, new RB_gld()._ins(4,1,1,1)._left_top())
				;		

		E2_Grid zeitraum_grid  = new E2_Grid()._bo_no()._setSize(100,150,30,150)
				._a(new RB_lab()._t("Von:")								, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(this.oDatumVon										, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(new RB_lab()._t("bis:")								, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(this.oDatumBis										, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(new RB_lab()._t("Zeitraum:")						, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(oSelMonate											, new RB_gld()._ins(4,1,0,1)._left_top())
				._a(new RB_lab()._t("")									, new RB_gld()._ins(4,1,0,1)._left_mid())
				._a(oSelJahre											, new RB_gld()._ins(4,1,0,1)._left_top())
				;			

		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());

		E2_Grid einheit_grid  = new E2_Grid()._bo_no()._s(1)
				._a(oSel_einheit.get_oComponentForSelection() 			, new RB_gld()._ins(4,1,4,1)._left_top())
				._a(sel_bew_typ.get_oComponentForSelection() 			, new RB_gld()._ins(4,1,4,1)._left_top())
				._a(oRowPassivschalter									, new RB_gld()._ins(4,20,10,1)._right_bottom());
		;

		E2_ExpandableRow oRowDivers2 = 	new E2_ExpandableRow(
				S.ms("Zus‰tzliche Auswahlkriterien: Warenbewegungsart,  Eigen-/Fremdwaren"), 
				new Border(1,new E2_ColorDDDark(),Border.STYLE_NONE),
				new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		// Standardm‰ﬂig verstecken
		oRowDivers2.get_oButtonClose().doActionPassiv();

		E2_Grid oGridDivers2 = new E2_Grid()._s(4);
		oRowDivers2.add(oGridDivers2,E2_INSETS.I_0_0_0_0);
		oGridDivers2.setSize(4);

//		oGridDivers2._a(new RB_lab()._t("Zeige:")										,new RB_gld()._ins(0,2,0,0)._left_top());	
//		oGridDivers2._a(oSelBewegungtypen.get_oComponentForSelection()					,new RB_gld()._ins(10,2,0,0)._left_top());

		oGridDivers2._a(this.oSelEwFw_abrechnenbar.get_oComponentForSelection()			,new RB_gld()._ins(5,2,0,0)._left_top());	
		oGridDivers2._a(new RB_lab()													,new RB_gld()._ins(0,2,0,0)._left_top());	

//		oGridDivers2._a(new RB_lab(S.ms("Lagerorte:"))									,new RB_gld()._ins(0,10,0,0)._left_top());	

		E2_Grid oGridInnen = new E2_Grid()._s(4)._bo_dd()
				._a(lager_grid , 			new RB_gld()._left_top()._span(2))
				._a(oRowDivers2, 			new RB_gld()._left_top()._span(2)._ins(10,2,10,2))
				._a(lager_grid_startziel, 	new RB_gld()._left_top()._span(4))
				._a(sorte_grid ,			new RB_gld()._left_top())
				._a(zeitraum_grid , 		new RB_gld()._left_top())
				._a(einheit_grid , 			new RB_gld()._ins(5,0,0,0)._left_top())
				;

		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
	}

	public E2_SelectionComponentsVector get_oSelVector(){
		return oSelVector;
	}

	public MyE2_TextField_DatePOPUP_OWN getDateBegin(){
		return oDatumVon;
	}

	public MyE2_TextField_DatePOPUP_OWN getDateEnd(){
		return oDatumBis;
	}

	public Vector<String> getSelectedHauptsorte() throws myException{
		return this.oSelMulti_Hauptsorte.get_SelectedValues();
	}

	public Vector<String> getIDSelectedSorte() throws myException{
		return this.oSelMulti_Sorte.get_SelectedValues();
	}

	public String getIDSelectedEinheit() throws myException{
		return oSel_einheit.get_oSelFieldBasis().get_ActualWert();
	}

	public String getDatumVon() throws myException{
		return oDatumVon.get_oDBFormatedDateFromTextField();
	}

	public String getDatumBis() throws myException{
		return oDatumBis.get_oDBFormatedDateFromTextField();
	}

	public String getSortenbereich_where_stmt() throws myException {
		return oSelSortenBereich.get_WhereBlock();
	}

	public ArrayList<String[]> getIdsArray_Sortenbereich() throws myException {
		return oSelSortenBereich.get_ArrayOfSelectedValues();
	}

	public boolean getShowEigenwarenlager() {
		return oSelectorLagerTypen.getStatusOf(ENUM_SELEKTOR_Lagertyp.EIGEN);
	}


	public boolean getShowFremdwarenlager() {
		return oSelectorLagerTypen.getStatusOf(ENUM_SELEKTOR_Lagertyp.FREMD);
	}
	
	public boolean getIncludeStrecke() {
		return oSelectorLagerTypen.getStatusOf(ENUM_SELEKTOR_Lagertyp.STRECKE);
	}
	
	public String getSelectedSorteDesc() throws myException{
		Vector<String> v = oSelMulti_Sorte.get_SelectedText();
		String sRet = "?";
		if (v.size() >1){
			sRet = "Mehrfachselektion Sorte";
		} else {
			if (v.size() == 1){
				sRet = v.get(0);
			}
		}
		return sRet;
	}

	public String getSelectedLagerDesc() throws myException {
		Vector<String> v = oSelMulti_Lager.get_SelectedValues();
		String sRet = "";
		if (v.size() >1){
			sRet = "Mehrfachselektion Lager";
		} else {
			if (v.size() == 1){
				sRet = v.get(0);
			} 
		}
		return sRet;
	}

	private class ownSelectorBewegungTyp extends E2_ListSelectorMultiDropDown2 {
		public ownSelectorBewegungTyp() throws myException {
			super();

			String[][] userList = new String[][] {
				{"IN Lager",	EnTabKeyInMask.A1.dbVal4SqlTerm()}
				,{"Ex Lager", 	EnTabKeyInMask.A2.dbVal4SqlTerm()}
			};

			MyE2_SelectField  selFieldKenner = new MyE2_SelectField(bibARR.add_emtpy_db_value_inFront(userList)	,"", false);    

			And  bed = new And(BG_ATOM.pos_in_mask.tnfn(),"#WERT#");

			this.INIT(selFieldKenner, bed.s(), null);

			selFieldKenner.setWidth(new Extent(190));
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {}
		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid  gridHelp = new E2_Grid()._setSize(90,190)._bo_no();
			gridHelp
			._a(new RB_lab("Lager IN/EX:"), 		new RB_gld()._ins(0,0,10,0)._left_mid())
			._a(this.get_oComponentWithoutText(),	new RB_gld()._ins(0,0,10,0)._left_mid());
			return gridHelp;
		}
	}


	private class ownSelectorEinheit extends E2_ListSelectorMultiDropDown2 {
		public ownSelectorEinheit() throws myException {
			super();

			SEL einheit_abfrage =  new SEL().ADDFIELD(EINHEIT.einheitlang).ADDFIELD(EINHEIT.id_einheit).FROM(_TAB.einheit)
					.WHERE(new vgl(EINHEIT.id_mandant, bibALL.get_ID_MANDANT())).ORDER("IST_STANDARD DESC, EINHEITLANG");

			MyE2_SelectField  selFieldKenner = new MyE2_SelectField(einheit_abfrage.s(),false, true , false, false);    

			And  bed = new And(EINHEIT.id_einheit.fn(Table_alias.EINHEIT.getAlias()),"#WERT#");

			this.INIT(selFieldKenner, bed.s(), null);

			selFieldKenner.setWidth(new Extent(190));
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {}
		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid  gridHelp = new E2_Grid()._setSize(90,190)._bo_no();
			gridHelp
			._a(new RB_lab("Einheit:"), 			new RB_gld()._ins(0,0,10,0)._left_mid())
			._a(this.get_oComponentWithoutText(),	new RB_gld()._ins(0,0,10,0)._left_mid());
			return gridHelp;
		}
	}

	private class actionAgentHauptartikel extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LAG_LIST_Selector oThis = B2_LAG_LIST_Selector.this;

			Vector<String> vHauptartikel = oThis.oSelMulti_Hauptsorte.get_SelectedValues();
			Vector<String> vWhere = new Vector<String>();
			for (String s: vHauptartikel){
				vWhere.add("SUBSTR('00" + s.trim() + "',-2) " );
			}

			String sParamValues = "";
			if (vHauptartikel != null && vHauptartikel.size() > 0) {
				sParamValues = " AND SUBSTR(ANR1,0,2) in ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
			}
			oThis.oSelMulti_Sorte.Refresh("#P1#", sParamValues);



		}
	}


	private class actionSynchronizeSortenName extends XX_ActionAgent	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LAG_LIST_Selector oThis = B2_LAG_LIST_Selector.this;

			Vector<String> vSorten = oThis.oSelMulti_Sorte.get_SelectedValues();

			if (vSorten != null && vSorten.size() == 1){
				oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue(vSorten.firstElement());
			} else {
				oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue("");
			}
		}
	}

	private class ownActionClearSortenBereichsMultiSelector extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			B2_LAG_LIST_Selector.this.oSelSortenBereich.LEER_MACHEN();
		}
	}

	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 182, true, true);
			this.get_oTextField().set_bEnabled_For_Edit(false);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
		}
	}

	private class actionAgentJahre extends XX_ActionAgent {

		private boolean m_fieldIsJahre = false;

		public actionAgentJahre(boolean bFieldIsJahre) {
			m_fieldIsJahre = bFieldIsJahre;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LAG_LIST_Selector oThis = B2_LAG_LIST_Selector.this;
			String sJahr  = oThis.oSelJahre.get_ActualWert();
			String sMonat = oThis.oSelMonate.get_ActualWert();
			if (sMonat == "" && sJahr != ""){

				// setzen von 1.1.Jahr bis 31.12.Jahr
				int nJahr = Integer.parseInt(sJahr);
				GregorianCalendar calVon = new GregorianCalendar(nJahr,0,1);
				GregorianCalendar calBis = new GregorianCalendar(nJahr,11,31); 

				String sDateVon =  myDateHelper.FormatDateNormal(calVon.getTime());
				String sDateBis = myDateHelper.FormatDateNormal(calBis.getTime());

				oThis.oDatumVon.get_oTextField().setText(sDateVon);
				oThis.oDatumBis.get_oTextField().setText(sDateBis);
				oThis.oDatumVon.set_oLastDateKlicked(new myDateHelper(calVon));
				oThis.oDatumBis.set_oLastDateKlicked(new myDateHelper(calBis));

				oThis.oSelVector.doActionPassiv();

			}
			else if (sMonat != "" && sJahr != ""){

				int nMonat = Integer.parseInt(sMonat);
				int nJahr = Integer.parseInt(sJahr);

				GregorianCalendar calVon = new GregorianCalendar(nJahr,nMonat-1,1);
				GregorianCalendar calBis = new GregorianCalendar(nJahr,nMonat-1,1); 
				calBis.add(Calendar.MONTH,1);
				calBis.add(Calendar.DATE, -1);


				String sDateVon =  myDateHelper.FormatDateNormal(calVon.getTime());
				String sDateBis = myDateHelper.FormatDateNormal(calBis.getTime());

				oThis.oDatumVon.get_oTextField().setText(sDateVon);
				oThis.oDatumBis.get_oTextField().setText(sDateBis);
				oThis.oDatumVon.set_oLastDateKlicked(new myDateHelper(calVon));
				oThis.oDatumBis.set_oLastDateKlicked(new myDateHelper(calBis));

				oThis.oSelVector.doActionPassiv();

			} else if (sMonat != "" && sJahr == "" && m_fieldIsJahre){
				oThis.oSelMonate.set_ActiveInhalt_or_FirstInhalt("-");
			}
		}

	}

	@SuppressWarnings("unused")
	private void activationFirmaLagerHandler() {
//		try {
//			boolean isFirmaSelected 	=  S.isFull(oSelFirmaLager.getSelectedFirmaId());
//			boolean isFirmaVonSelected 	=  S.isFull(oSelFirmaLagerVon.getSelectedFirmaId());
//			boolean isFirmaBisSelected 	=  S.isFull(oSelFirmaLagerBis.getSelectedFirmaId());			
//			
//			oSelFirmaLager.setEnabled(false);
//			oSelFirmaLagerVon.setEnabled(false);
//			oSelFirmaLagerBis.setEnabled(false);
//
//			if(isFirmaSelected) {
//				oSelFirmaLager.setEnabled(isFirmaSelected);
//			}else if(isFirmaVonSelected || isFirmaBisSelected) {
//				oSelFirmaLager.setEnabled(false);
//				oSelFirmaLagerVon.setEnabled(true);
//				oSelFirmaLagerBis.setEnabled(true);
//			}else {
//				oSelFirmaLager.setEnabled(true);
//				oSelFirmaLagerVon.setEnabled(true);
//				oSelFirmaLagerBis.setEnabled(true);
//			}
//		} catch (myException e) {
//			e.printStackTrace();
//		}
	}

	private void clearLagerSelector(enumSourceLager source) {
		try {
			switch (source) {
			case BIS:
				this.oSelFirmaLager.setDefaultValue("");
				break;
			case VON:
				this.oSelFirmaLager.setDefaultValue("");
				break;
			case VONBIS:
				this.oSelFirmaLagerVon.setDefaultValue("");
				this.oSelFirmaLagerBis.setDefaultValue("");
				break;
			default:
				break;
			}
			this.oSelVector.doActionPassiv();
		} catch (myException e) {
			e.printStackTrace();
		}
	}
	
	private class actionSynchronizeSorten extends XX_ActionAgent	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LAG_LIST_Selector oThis = B2_LAG_LIST_Selector.this;

			String sValue = null;
			if (oExecInfo.get_MyActionEvent().getSource() instanceof MyE2_SelectFieldWithParameters ){
				// Aktueller Wert
				sValue = ((MyE2_SelectFieldWithParameters)oExecInfo.get_MyActionEvent().getSource()).get_ActualWert();

				// den Wert in der Sorten-Combobox setzen
				oThis.oSelMulti_Sorte.set_MemStringStatusSelektor(sValue);
			}
		}
	}

	private class SelectorSortenVonBis_Multi extends E2_ListSelectorMultiDropDown_von_bis
	{


		public SelectorSortenVonBis_Multi() throws myException
		{
			super(bibALL.ReplaceTeilString(
					B2_LAG_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#",""),  

					"ART.ID_ARTIKEL IN (SELECT A.ID_ARTIKEL FROM JT_ARTIKEL A WHERE A.ANR1>='#WERT1#' AND A.ANR1<='#WERT2#')");

			//wird hier ein selektion aktiv, dann muss die singulaere sortenselektion wegfallen
			this.get_btSpeichern().add_oActionAgent(new ownActionAgentDeleteSortenSelektion(), true);
			this.get_btClear().add_oActionAgent(new ownActionAgentDeleteSortenSelektion(), true);

		}


		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException
		{
			return new ownContainer();
		}

		@Override
		public void fill_Grid4AnzeigeStatusMulti()
		{

			this.get_grid4Anzeige().removeAll();
			this.get_grid4Anzeige().setSize(5);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_von(),1,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(new MyE2_Label(new MyE2_String(" bis ")),2,E2_INSETS.I(1,0,5,0));
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_bis(),1,E2_INSETS.I_0_0_0_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),1,E2_INSETS.I_2_0_0_0);

			this.get_oSelFieldBasis_von().setWidth(new Extent(221));
			this.get_oSelFieldBasis_bis().setWidth(new Extent(221));

		}

		@Override
		public void fill_Grid4AnzeigeStatusSingle()
		{
			this.fill_Grid4AnzeigeStatusMulti();
		}

		private class ownContainer extends E2_BasicModuleContainer
		{
			public ownContainer()
			{
				super();
			}
		}


		private class ownActionAgentDeleteSortenSelektion extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				B2_LAG_LIST_Selector.this.oSelMulti_Sorte.set_MemStringStatusSelektor("");
			}
		}

	}

	private enum enumSourceLager{
		VON,
		BIS,
		VONBIS;
	}


	
}


