package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
public class REP_VER_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public REP_VER_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
		ownreportselektor sel_report = new ownreportselektor();
		ownreportdruckzeitraum sel_zeitraum = new ownreportdruckzeitraum();
		ownWegSelector weg_selektor = new ownWegSelector();

		this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
		this.oSelVector.add(sel_geaendert_von);
		this.oSelVector.add(sel_report);
		this.oSelVector.add(sel_zeitraum);
		this.oSelVector.add(weg_selektor);

		E2_Grid oGridInnen = new E2_Grid()._s(4)
				._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._left_top()._ins(0,6,20,6))
				._a(sel_report.get_oComponentForSelection(), new RB_gld()._right_top()._ins(20, 6, 20, 2))
				._a(sel_zeitraum.get_oComponentForSelection(100), new RB_gld()._right_top()._ins(20, 6, 20, 2))
				._a(weg_selektor.get_oComponentForSelection(), new RB_gld()._right_top()._ins(20, 6, 20, 2));

		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
	}

	public E2_SelectionComponentsVector get_oSelVector() {
		return oSelVector;
	}

	private class ownreportdruckzeitraum extends E2_SelektorDateFromTo_NG {

		public ownreportdruckzeitraum() throws myException {
			super();
			this.INIT_Selector(S.ms("Report gedruckt:"), REPORT_LOG.report_druck_am.tnfn(), REPORT_LOG.report_druck_am.tnfn(), new Extent(80));
		}

	/*	@Override
		public void Ordne_Komponenten_An_in_DateVonbisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup,
				MyE2_TextField oTextFieldVon, MyE2_TextField oTextFieldBis, MyE2_Button oButtonCalendar,
				MyE2_Button oButtonEraserVon, MyE2_Button oButtonEraserBis) throws myException {
			
			ownSelectVonBisPopup.setSize(6);
			ownSelectVonBisPopup.setColumnWidth(0, new Extent(110));
			ownSelectVonBisPopup.add(new RB_lab(new MyE2_String("Report gedruckt:")),E2_INSETS.I(0, 0, 2, 0));
			ownSelectVonBisPopup.add(oTextFieldVon,E2_INSETS.I(0, 0, 1, 0));
			ownSelectVonBisPopup.add(oButtonEraserVon,E2_INSETS.I(0, 0, 5, 0));
			ownSelectVonBisPopup.add(oTextFieldBis,E2_INSETS.I(0, 0, 1, 0));
			ownSelectVonBisPopup.add(oButtonEraserBis,E2_INSETS.I(0,0,5,0));
			ownSelectVonBisPopup.add(oButtonCalendar,E2_INSETS.I(0,0,1,0));
			
		}

		@Override
		public void HaengeMeineElementeAn_DateVonBisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup,
				MyE2_Button oButtonLos, MyE2_Button oButtonHelp) throws myException {
			
			ownSelectVonBisPopup.setSize(7);
			ownSelectVonBisPopup.add(oButtonHelp,E2_INSETS.I_0_0_5_0);
			
		}*/
	}

	private class ownreportselektor extends E2_ListSelectorMultiDropDown2 {

		public ownreportselektor() throws myException {
			super();

			String report_abfrage = new SEL(REPORT_LOG.report_jasperfile).ADD_Distinct()
					.ADDFIELD(REPORT_LOG.report_jasperfile).FROM(_TAB.report_log)
					.WHERE(new vgl(REPORT_LOG.id_mandant, bibALL.get_ID_MANDANT()))
					.ORDERUP(REPORT_LOG.report_jasperfile).s();

			MyE2_SelectField selFieldKenner = new MyE2_SelectField(report_abfrage, false, true, false, false);
			
			
			And bed = new And(REPORT_LOG.report_jasperfile, "'#WERT#'");

			this.INIT(selFieldKenner, bed.s(), null);
			this.get_oSelFieldBasis().setWidth(new Extent(200));
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {
		}

		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid gridHelp = new E2_Grid()._bo_no()._setSize(50, 100)
					._a(new RB_lab()._t("Report:"), new RB_gld()._ins(2, 0, 10, 2)._right_mid())
					._a(this.get_oComponentWithoutText(), new RB_gld()._ins(2, 0, 2, 0)._left_mid());
			return gridHelp;
		}
	}

	/**
	 * selektor fuer die auswahl von modulen ....
	 * 
	 * @author martin
	 *
	 */
	private class ownSelectorGeaendertVon extends E2_ListSelectorMultiDropDown2 {
		public ownSelectorGeaendertVon() throws myException {
			super();

			String[][] userList = new USER_SELECTOR_GENERATOR_NT(false, ENUM_USER_TYP.BUERO).get_selUsers(true);
			MyE2_SelectField selFieldKenner = new MyE2_SelectField(userList, "", false, new Extent(120));

			And bed = new And(REPORT_LOG.report_druck_von, "'#WERT#'");
			this.INIT(selFieldKenner, bed.s(), null);
			
			this.get_oSelFieldBasis().setWidth(new Extent(200));
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {
		}

		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid gridHelp = new E2_Grid()._setSize(140, 100)._bo_no()
					._a(new RB_lab()._t("Report gedruckt von:"), new RB_gld()._ins(2, 0, 10, 0)._left_mid())
					._a(this.get_oComponentWithoutText(), new RB_gld()._ins(2, 0, 10, 0)._left_mid());
			return gridHelp;
		}

	}

	private class ownWegSelector extends E2_ListSelektorMultiselektionStatusFeld_STD {

		public ownWegSelector() {
			super(new int[] { 60, 60 }, true, S.ms("Typ:"), new Extent(40));

			this.ADD_STATUS_TO_Selector(true,
					"(NVL(" + REPORT_LOG.report_weg.tnfn() + ",'" + ENUM_REPORT_WEG.UND.name() + "')='"
							+ ENUM_REPORT_WEG.PRINT.name() + "')",
					S.ms(ENUM_REPORT_WEG.PRINT.user_text()), S.ms(ENUM_REPORT_WEG.PRINT.user_text()));
			this.ADD_STATUS_TO_Selector(true,
					"(NVL(" + REPORT_LOG.report_weg.tnfn() + ",'" + ENUM_REPORT_WEG.UND.name() + "')='"
							+ ENUM_REPORT_WEG.MAIL.name() + "')",
					S.ms(ENUM_REPORT_WEG.MAIL.user_text()), S.ms(ENUM_REPORT_WEG.MAIL.user_text()));
			this.ADD_STATUS_TO_Selector(true,
					"(NVL(" + REPORT_LOG.report_weg.tnfn() + ",'" + ENUM_REPORT_WEG.UND.name() + "')='"
							+ ENUM_REPORT_WEG.PREVIEW.name() + "')",
					S.ms(ENUM_REPORT_WEG.PREVIEW.user_text()), S.ms(ENUM_REPORT_WEG.PREVIEW.user_text()));
		}
	}
    
}
 
 
