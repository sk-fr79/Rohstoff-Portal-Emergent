package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_Lager;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_Standort;

public class WK_RB_LIST_Selector extends E2_ExpandableRow {

	// Selektoren
	private WK_RB_SelField_Lager oSelLager = null;
	private WK_RB_SelField_Standort oSelStandort = null;

	private MyE2_CheckBox oCBNoDate = null;
	private ownTF4Datum oDatumVon = null;

	private MyE2_SelectField oSelWEWA = null;
	private String[][] arrWE = null;

	private MyE2_SelectFieldWithParameters oSelKFZ_Gesamtverwiegung = null;
	private MyE2_CheckBox oCBGesamtUndEinzel = null;

	private E2_SelectionComponentsVector oSelVector = null;

	// zentrale hashmap fuer transport von infos
	private RB_TransportHashMap m_tpHashMap = null;

	
	
	/**
	 * 
	 * @author manfred
	 * @date 24.04.2020
	 *
	 * @param p_tpHashMap
	 * @throws myException
	 */
	public WK_RB_LIST_Selector(RB_TransportHashMap p_tpHashMap) throws myException {
		// E2_NavigationList oNavigationList

		super(new MyE2_String("Selektionsblock geschlossen"), 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID),	
																new Border(0, new E2_ColorDDDark(), Border.STYLE_SOLID));
		this.m_tpHashMap = p_tpHashMap;
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR, this);
		this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR, this.oSelVector);


		//
		// CombobBox der Läger, die dem Benutzer zugeordnet sind
		//
		// der aktuelle Benutzer
		String IdUser = bibALL.get_ID_USER();

		//
		// die dem Benutzer zugeordnete Waagen / und Lager
		//
		String sSqlSelect = "SELECT DISTINCT * from ("
				+ " SELECT NVL(PLZ,'') ||' '|| NVL(ORT,'') ||', ' || NVL(NAME1,'') ||' ' || NVL(NAME2,'') , ID_ADRESSE "
				+ " FROM   " + bibE2.cTO() + ".JT_ADRESSE " + " INNER JOIN  " + bibE2.cTO() + ".JT_WAAGE_SETTINGS   "
				+ " 	ON JT_ADRESSE.ID_ADRESSE = JT_WAAGE_SETTINGS.ID_ADRESSE_LAGER   " + " INNER JOIN  "
				+ bibE2.cTO() + ".JT_WAAGE_USER "
				+ "	ON 	( JT_WAAGE_SETTINGS.ID_MANDANT = JT_WAAGE_USER.ID_MANDANT ) AND "
				+ "  		( JT_WAAGE_SETTINGS.ID_WAAGE_STANDORT = JT_WAAGE_USER.ID_WAAGE_STANDORT )"
				+ " WHERE JT_WAAGE_USER.ID_USER = " + IdUser + " ORDER BY JT_WAAGE_USER.SORTIERUNG )";

		oSelLager = new WK_RB_SelField_Lager(sSqlSelect, false, true, false, false);
		oSelLager.add_oActionAgent(new actionAgentLager());

		oSelVector.add(new E2_ListSelectorStandard(oSelLager, "JT_WIEGEKARTE.ID_ADRESSE_LAGER = #WERT#", null, null));

		// Standort, abhängig vom Lager
		sSqlSelect = " SELECT STANDORT, ID_WAAGE_STANDORT FROM "
				+ " ( SELECT DISTINCT O.STANDORT, O.ID_WAAGE_STANDORT,O.SORTIERUNG " + " FROM  " + bibE2.cTO()
				+ ".JT_WAAGE_STANDORT  O " + " INNER JOIN " + bibE2.cTO()
				+ ".JT_WAAGE_SETTINGS S on S.ID_WAAGE_STANDORT = O.ID_WAAGE_STANDORT " + " INNER JOIN " + bibE2.cTO()
				+ ".JT_WAAGE_USER U on U.ID_WAAGE_STANDORT = S.ID_WAAGE_STANDORT " + " WHERE  U.ID_USER = " + IdUser
				+ " AND S.ID_ADRESSE_LAGER = #ID_ADRESSE_LAGER# " + " ORDER BY O.SORTIERUNG )";
		oSelStandort = new WK_RB_SelField_Standort(sSqlSelect, false, true, false, false);
		oSelStandort.AddParameter("#ID_ADRESSE_LAGER#");
		String idLager = bibALL.isEmpty(oSelLager.get_ActualWert()) ? "-1" : oSelLager.get_ActualWert();
		oSelStandort.SetParameter("#ID_ADRESSE_LAGER#", idLager);
		oSelStandort.RefreshComboboxFromSQL();

		oSelVector.add(
				new E2_ListSelectorStandard(oSelStandort, "JT_WIEGEKARTE.ID_WAAGE_STANDORT =  #WERT# ", null, null));

		MyE2_CheckBox oCBShowPrintedEntries = new MyE2_CheckBox(
				new MyE2_String("Vollständige und gedruckte Wiegekarten ausblenden"));
		oSelVector.add(new E2_ListSelectorStandard(oCBShowPrintedEntries,
				" ( ( NVL(JT_WIEGEKARTE.DRUCKZAEHLER,0) + NVL(JT_WIEGEKARTE.DRUCKZAEHLER_EINGANGSSCHEIN,0) = 0) OR JT_WIEGEKARTE.NETTOGEWICHT IS NULL)",
				""));
		oCBShowPrintedEntries.setSelected(true);

		MyE2_CheckBox oCBShowStornoEntries = new MyE2_CheckBox(new MyE2_String("Stornierte WK anzeigen"));
		oSelVector.add(new E2_ListSelectorStandard(oCBShowStornoEntries, "", " NVL(JT_WIEGEKARTE.STORNO,'N')='N' "));
		oCBShowStornoEntries.setSelected(false);

		oCBGesamtUndEinzel = new MyE2_CheckBox(new MyE2_String("Gesamt- und Einzelverwiegungen"), new MyE2_String(
				"Aktiv: Listet alle WK mit Gesamtverwiegungen auf. Bei Selektion werden alle zur Gesamtverwiegung gehörigen Wiegungen gelistet."));
		oCBGesamtUndEinzel.add_oActionAgent(new agentCBGesamtverwiegung());

		// Wiegedatum
		oCBNoDate = new MyE2_CheckBox(new MyString("Datum:"),
				new MyE2_String("Aktiv: Zeigt nur die Einträge des gewählten Datums an, sonst alle Einträge."));
		oCBNoDate.setSelected(true);

		oCBNoDate.add_oActionAgent(new agentCBNoDate());
		oCBNoDate.get_vActionAgents().add(new agentCBGesamtverwiegung());
		

		Calendar cal = new GregorianCalendar();
		String sDate = myDateHelper.FormatDateNormal(cal.getTime());
		oDatumVon = new ownTF4Datum(sDate, true);
		oDatumVon.get_oButtonCalendar().setToolTipText(new MyE2_String("Wiegedatum").CTrans());
		oDatumVon.get_oTextField().set_bEnabled_For_Edit(false);
		oSelVector.add(new E2_ListSelectorStandard(oDatumVon, "TRUNC(JT_WIEGEKARTE.ERZEUGT_AM,'DD') = '#WERT#'", null));
		oDatumVon.get_vActionAgentsZusatz().add(0, new agentCBGesamtverwiegung());

		//
		// Wareneingang/ -Ausgang
		arrWE = new String[][] { { new MyE2_String("-").CTrans(), "" },
				{ new MyE2_String("Wareneingang").CTrans(), "Y" }, { new MyE2_String("Warenausgang").CTrans(), "N" } };

		this.oSelWEWA = new MyE2_SelectField(arrWE, null, false);
		oSelVector.add(new E2_ListSelectorStandard(this.oSelWEWA, "NVL(JT_WIEGEKARTE.IST_LIEFERANT,'N') = '#WERT#'",
				null, null));

		//
		// Fahrzeugauswahl bei der Gesamtverwiegung
		oSelKFZ_Gesamtverwiegung = new MyE2_SelectFieldWithParameters(
				" SELECT  KENNZEICHEN || ' (' || W.LFD_NR || ')', W.ID_WIEGEKARTE from  " + bibE2.cTO()
						+ ".JT_WIEGEKARTE W" + " where TRUNC(W.ERZEUGT_AM,'dd')  #ERZEUGT_AM# "
						+ " and NVL(W.IST_GESAMTVERWIEGUNG,'N') = 'Y'",
				false, true, false, false);
		
//		oSelKFZ_Gesamtverwiegung = new MyE2_SelectFieldWithParameters(
//				" SELECT  KENNZEICHEN || ' (' || W.LFD_NR || ')', W.ID_WIEGEKARTE from  " + bibE2.cTO()
//						+ ".JT_WIEGEKARTE W" + " where TRUNC(W.ERZEUGT_AM,'dd')  #ERZEUGT_AM# "
//						+ " and NVL(W.IST_GESAMTVERWIEGUNG,'N') = 'Y'"
//						+ " and 0 = ( SELECT count(  W.ID_WIEGEKARTE ) "
//						+ "          FROM JT_WIEGEKARTE W1  WHERE "
//						+ "           W1.ID_WIEGEKARTE_PARENT = W.ID_WIEGEKARTE "
//						+ "          )"
//						+ "",
//				false, true, false, false);
		
		
		
		oSelKFZ_Gesamtverwiegung.AddParameter("#ERZEUGT_AM#");
		oSelKFZ_Gesamtverwiegung.SetParameter("#ERZEUGT_AM#", " = '1900-01-01' ");

		this.refreshSelectionKFZ();
//		oSelVector.add(new E2_ListSelectorStandard(oSelKFZ_Gesamtverwiegung,
//				" NVL(JT_WIEGEKARTE.ID_WIEGEKARTE_PARENT, JT_WIEGEKARTE.ID_WIEGEKARTE) = #WERT#", null, null));

		oSelVector.add(new E2_ListSelectorStandard(oSelKFZ_Gesamtverwiegung,
				" ( JT_WIEGEKARTE.ID_WIEGEKARTE_PARENT = #WERT# OR JT_WIEGEKARTE.ID_WIEGEKARTE = #WERT# )", null, null));

		// 2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined oDB_BasedSelektor = new E2_ListSelector_DB_Defined(
				E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE);
		this.oSelVector.add(oDB_BasedSelektor);

		// GUI
		MyE2_Grid oGrid = new MyE2_Grid(3, 0);
		oGrid.setOrientation(MyE2_Grid.ORIENTATION_VERTICAL);

		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")), 1, E2_INSETS.I_0_0_0_0);
		oGrid.add(new MyE2_Label(new MyE2_String("Standort:")), 1, E2_INSETS.I_0_0_0_0);
		oGrid.add(oCBNoDate, 1, E2_INSETS.I_0_0_0_0);

		oGrid.add(oSelLager, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(oSelStandort, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(oDatumVon, 1, E2_INSETS.I_10_0_0_0);

//		oGrid.add(oCBHideCompleteEntries,1,E2_INSETS.I_10_0_0_0);
		oGrid.add(oCBShowPrintedEntries, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(oCBShowStornoEntries, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(new MyE2_Label(new MyE2_String("")), 1, E2_INSETS.I_10_0_0_0);

		oGrid.add(new MyE2_Label(new MyE2_String("WE/WA:")), 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(oSelWEWA, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(new MyE2_Label(new MyE2_String("")), 1, E2_INSETS.I_10_0_0_0);

		oGrid.add(oCBGesamtUndEinzel, 1, E2_INSETS.I_10_0_0_0);
		oGrid.add(oSelKFZ_Gesamtverwiegung, 1, E2_INSETS.I_10_0_0_0);
		// oGrid.add(new MyE2_Label(new MyE2_String("")),1,E2_INSETS.I_10_0_0_0);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(100), 1, E2_INSETS.I_10_0_0_0);

		this.add(oGrid);

	}

	public E2_SelectionComponentsVector get_oSelVector() {
		return oSelVector;
	}

	/**
	 * Füllt die Combobox für die Auswahl der Fahrzeuge bei Gesamtverwiegungen
	 * 
	 * @throws myException
	 */
	private void refreshSelectionKFZ() throws myException {

		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date dt;

		oSelKFZ_Gesamtverwiegung.setSelectedIndex(0);

		try {
			if (oDatumVon.get_oLastDateKlicked() != null) {
				dt = df.parse(oDatumVon.get_oFormatedDateFromTextField());
				df.applyPattern("yyyy-MM-dd");
				String sDatum = df.format(dt);
				oSelKFZ_Gesamtverwiegung.SetParameter("#ERZEUGT_AM#", " = '" + sDatum + "'");
				oSelKFZ_Gesamtverwiegung.RefreshComboboxFromSQL();
			} else {
				oSelKFZ_Gesamtverwiegung.SetParameter("#ERZEUGT_AM#", " is not null ");
				oSelKFZ_Gesamtverwiegung.RefreshComboboxFromSQL();
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}

		if (oCBGesamtUndEinzel.isSelected()) {
			oSelKFZ_Gesamtverwiegung.set_bEnabled_For_Edit(true);
		} else {
			oSelKFZ_Gesamtverwiegung.set_bEnabled_For_Edit(false);
		}
		oSelVector.doActionPassiv();

	}

	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN {
		public ownTF4Datum(String cStartWert, boolean bEnabled4Edit) throws myException {
			super(cStartWert, 100);
			this.set_bEnabled_For_Edit(bEnabled4Edit);

		}
	}

	private class agentCBGesamtverwiegung extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_RB_LIST_Selector oThis = WK_RB_LIST_Selector.this;
			oThis.refreshSelectionKFZ();

		}

	}

	private class agentCBNoDate extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_RB_LIST_Selector oThis = WK_RB_LIST_Selector.this;

			boolean bNoDate = !oThis.oCBNoDate.isSelected();
			if (bNoDate) {
				oThis.oDatumVon.set_oLastDateKlicked(null);
				oThis.oDatumVon.get_oTextField().setText("");
				oThis.oDatumVon.set_bEnabled_For_Edit(false);
			} else {
				Calendar cal = new GregorianCalendar();
				myDateHelper o = new myDateHelper(cal);
				oThis.oDatumVon.set_oLastDateKlicked(o);
				oThis.oDatumVon.get_oTextField()
						.setText(oThis.oDatumVon.get_oLastDateKlicked().get_cDateFormatForMask());
				oThis.oDatumVon.set_bEnabled_For_Edit(true);
			}

			oThis.oSelVector.doActionPassiv();

		}

	}

	private class actionAgentLager extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_RB_LIST_Selector oThis = WK_RB_LIST_Selector.this;

			String sIDLager = bibALL.isEmpty(oThis.oSelLager.get_ActualWert()) ? "-1"
					: oThis.oSelLager.get_ActualWert();
			String sIDOld = oThis.oSelStandort.get_ActualWert();

			oThis.oSelStandort.SetParameter("#ID_ADRESSE_LAGER#", sIDLager);
			oThis.oSelStandort.RefreshComboboxFromSQL();
			oThis.oSelStandort.set_ActiveValue_OR_FirstValue(sIDOld);

		}

	}

	
	
	
	public WK_RB_SelField_Lager getoSelLager() {
		return oSelLager;
	}

	/**
	 * die Id des aktuell selektierten Lagers
	 * @author manfred
	 * @date 27.04.2020
	 *
	 * @return
	 */
	public String get_IDLager() {
		String id_lager = "";
		try {
			id_lager = getoSelLager().get_ActualWert();
		} catch (myException e) {
			//
		}
		return id_lager;
	}
	

	public WK_RB_SelField_Standort getoSelStandort() {
		return oSelStandort;
	}

	/**
	 * die ID des aktuell selektierten Waagestandortes
	 * @author manfred
	 * @date 27.04.2020
	 *
	 * @return
	 */
	public String get_IDWaageStandort() {
		String id_waage_standort = "";
		try {
			id_waage_standort = getoSelStandort().get_ActualWert();
		} catch (myException e) {
			//
		}
		return id_waage_standort;
	}
	

	public MyE2_CheckBox getoCBNoDate() {
		return oCBNoDate;
	}

	public ownTF4Datum getoDatumVon() {
		return oDatumVon;
	}

	public MyE2_SelectField getoSelWEWA() {
		return oSelWEWA;
	}

	public MyE2_SelectFieldWithParameters getoSelKFZ_Gesamtverwiegung() {
		return oSelKFZ_Gesamtverwiegung;
	}

	public MyE2_CheckBox getoCBOnlyGroups() {
		return oCBGesamtUndEinzel;
	}

	public E2_SelectionComponentsVector getoSelVector() {
		return oSelVector;
	}

}
