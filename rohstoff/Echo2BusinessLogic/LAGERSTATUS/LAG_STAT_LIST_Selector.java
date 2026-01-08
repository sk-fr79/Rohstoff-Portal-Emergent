package rohstoff.Echo2BusinessLogic.LAGERSTATUS;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory_Extended;
import rohstoff.utils.bibROHSTOFF;

public class LAG_STAT_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	// Wareneingang/Ausgangs-Selektion
	private MyE2_SelectField 			   	oSelZeitraum = null;
	private String[][] 		 				arrZeitraum = null;
//	private MyE2_SelectFieldWithParameters  oSelDatum = null;
	
	private MyE2_SelectFieldWithParameters oSelArtikel = null;
	private MyE2_SelectFieldWithParameters oSelHauptartikel = null;
//	private LAG_SelectFieldOwnLAGER 		oSelLager = null;
	private LAG_LagerSelectField_Factory_Extended oSelLagerFactory = null;
	private MyE2_SelectField				oSelLager = null;
	private MyE2_Button      				oPBRefreshMaterial = null;
//	private MyE2_Button						oBtnRefreshDays = null;

	private ownTF4Datum 					oDatumBestand = null;
	private MyE2_CheckBox					cbBuchungsTagWaehlen = null;
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private String							m_letzterZeitraum = "-1";
	private String							m_letztesDatum = "";
	
	
	public LAG_STAT_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
//		XX_ActionAgent oAgentDatum = new actionAgentTagesauswahl();
		
		// anzahl der Monate für die die Datumswerte in der Selektion selektiert werden sollen (Wert = Anzahl Monate)
		// Wareneingang/ -Ausgang
		arrZeitraum = new String[][]{
				{new MyE2_String("1 Monat").CTrans(),"-1"},
				{new MyE2_String("2 Monate").CTrans(),"-2"},
				{new MyE2_String("3 Monate").CTrans(),"-3"},
				{new MyE2_String("4 Monate").CTrans(),"-4"},
				{new MyE2_String("5 Monate").CTrans(),"-5"},
				{new MyE2_String("6 Monate").CTrans(),"-6"},
				{new MyE2_String("9 Monate").CTrans(),"-9"},
				{new MyE2_String("1 Jahr").CTrans(),"-12"},
				{new MyE2_String("2 Jahre").CTrans(),"-24"},
				{new MyE2_String("-").CTrans(),"-9999"}
				};
		
		
		
		this.oSelZeitraum = new MyE2_SelectField(arrZeitraum, null, false);
		oSelVector.add(new E2_ListSelectorStandard(this.oSelZeitraum, "JT_STATUS_LAGER.BUCHUNGSDATUM >= ADD_MONTHS(SYSDATE,#WERT#)", null, null));	
		
//		actionAgentZeitraum oHandlerZeitraum = new actionAgentZeitraum();
//		
//		oSelZeitraum.add_oActionAgent(oHandlerZeitraum,true);
		//oSelZeitraum.RefreshComboboxFromSQL();
		
//		
//		oSelDatum = new MyE2_SelectFieldWithParameters("SELECT DISTINCT BUCHUNGSDATUM, to_char(BUCHUNGSDATUM,'yyyy-mm-dd') " +
//				" FROM "+ bibE2.cTO()+".JT_STATUS_LAGER WHERE BUCHUNGSDATUM >= ADD_MONTHS(SYSDATE,#P1#) ORDER BY BUCHUNGSDATUM DESC", 
//				false, 
//				true, 
//				true, 
//				false);
//		oSelDatum.AddParameter("#P1#");
//		
//		oSelDatum.SetParameter("#P1#", oSelZeitraum.get_ActualWert());
//		oSelDatum.RefreshComboboxFromSQL();
//		oSelDatum.setToolTipText(new MyE2_String("Datum der Erstellung").CTrans());
//		
//		// aktuell das 1. Datum selektieren
//		if (oSelDatum.get_DataToView() != null && oSelDatum.get_DataToView().size() > 1){
//			oSelDatum.setSelectedIndex(1);
//			String s = oSelDatum.get_ActualWert();
//			oSelDatum.set_ActiveValue_OR_FirstValue(s);
//		}
//		oSelVector.add(new E2_ListSelectorStandard(oSelDatum, "JT_STATUS_LAGER.BUCHUNGSDATUM = '#WERT#'", null, null));
		
		
		
		// alternative das aktuelle Datum als Kalender
		
		cbBuchungsTagWaehlen = new MyE2_CheckBox(new MyString("Buchungstag wählen"));
		cbBuchungsTagWaehlen.setSelected(false);
		cbBuchungsTagWaehlen.add_oActionAgent(new actionAgentBuchungstagAktiv());
		
		
		Calendar cal = new GregorianCalendar();
//		cal.add(Calendar.MONTH, -1);
//		String sDate =  myDateHelper.FormatDateNormal(cal.getTime());
		oDatumBestand = new ownTF4Datum(null,true);
		oDatumBestand.get_oButtonCalendar().setToolTipText(new MyE2_String("Datum, an dem die historischen Werte angezeigt werden sollen").CTrans());
		oDatumBestand.get_oTextField().set_bEnabled_For_Edit(false);
		oSelVector.add(new E2_ListSelectorStandard(oDatumBestand,"JT_STATUS_LAGER.BUCHUNGSDATUM = '#WERT#'",null));		
		
		// den gleichen Eventhandler wie für den Zeitraum-Refresh
//		oBtnRefreshDays = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
//		oBtnRefreshDays.add_oActionAgent(oHandlerZeitraum);
//		oBtnRefreshDays.setToolTipText(new MyString("Buchungstage aktualisieren.").CTrans());
		
		
		//
		// CombobBox der Läger
		//
		Vector<String> sAdressIds = bibROHSTOFF.get_vEigeneLieferadressen();
		String sWhere = " AND ID_ADRESSE IN ("
				+ bibALL.Concatenate(sAdressIds, ",", "") + ")";

//		oSelLager = (new LAG_LagerSelectField_Factory()).getSelectField();
		oSelLagerFactory = new LAG_LagerSelectField_Factory_Extended();
		oSelLager = oSelLagerFactory.getSelectField();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,
				"JT_STATUS_LAGER.ID_ADRESSE = #WERT#", null, null));
		
		
		this.oSelHauptartikel = new MyE2_SelectFieldWithParameters("SELECT DISTINCT  SUBSTR(ANR1,0,2) , SUBSTR(ANR1,0,2)  from "
				+ bibE2.cTO()
				+ ".JT_STATUS_LAGER "
				+ " join "
				+ bibE2.cTO()
				+ ".JT_ARTIKEL on JT_STATUS_LAGER.ID_SORTE = JT_ARTIKEL.ID_ARTIKEL "
				+ " WHERE JT_STATUS_LAGER.id_mandant= "
				+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false, true,
		false, false);
		oSelHauptartikel.add_oActionAgent(new actionAgentHauptartikel());
		oSelHauptartikel.RefreshComboboxFromSQL();

		
		
		this.oSelArtikel = new MyE2_SelectFieldWithParameters(
				"SELECT DISTINCT  jt_artikel.Anr1 || ' - ' ||  jt_artikel.ArtBez1 , jt_artikel.id_artikel  from "
						+ bibE2.cTO()
						+ ".JT_STATUS_LAGER "
						+ " join "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL on JT_STATUS_LAGER.ID_SORTE = JT_ARTIKEL.ID_ARTIKEL "
						+ " WHERE JT_STATUS_LAGER.id_mandant="
						+ bibALL.get_ID_MANDANT() + " #P1# ORDER BY 1", false, true,
				false, false);

		oSelArtikel.AddParameter("#P1#");
		oSelArtikel.RefreshComboboxFromSQL();
//		oSelArtikel.add_oActionAgent(oAgentDatum);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelHauptartikel, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'",null,null));
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,	"JT_STATUS_LAGER.ID_SORTE = #WERT#", null, null));
		
		
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon
				.get_RI("reload.png"), true);
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_STAT_LIST_Selector othis = LAG_STAT_LIST_Selector.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
			}
		});
		
		

		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		//breitenanpasung
		oSelArtikel.setWidth(new Extent(500));
//		oSelLager.setWidth(new Extent(667));
		

		
		MyE2_Grid oGrid = new MyE2_Grid(6, 0);

		//zeile 1
		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")), 1, E2_INSETS.I_0_0_2_0);
		oGrid.add(oSelLagerFactory.setSelectfieldWidth(719).render(), 5, E2_INSETS.I_4_0_4_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("")),2,E2_INSETS.I_0_0_0_0);
//		oGrid.setColumnWidth(2, new Extent(200));
		
		
		//zeile 2
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_2_0);
		oGrid.add(oSelHauptartikel,1,E2_INSETS.I_4_0_4_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("Sorte:")) ,1,E2_INSETS.I_0_0_0_0);
//		MyE2_Row rowArtikel = new MyE2_Row();
//		rowArtikel.add(oSelHauptartikel,E2_INSETS.I_0_0_0_0);
//		rowArtikel.add(new MyE2_Label(new MyE2_String("Sorte:")) ,E2_INSETS.I_10_0_0_0);
//		rowArtikel.add(oPBRefreshMaterial, E2_INSETS.I_0_0_0_0);
//		rowArtikel.add(oSelArtikel, E2_INSETS.I_0_0_0_0);
		oGrid.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Sorte:")),oPBRefreshMaterial,oSelArtikel, E2_INSETS.I_5_0_5_0),4,E2_INSETS.I_0_0_2_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("")),2,E2_INSETS.I_0_0_0_0);

		//zeile 3
		oGrid.add(new MyE2_Label("Betrachtungszeitraum:"), 1, E2_INSETS.I_0_0_2_0);
		oGrid.add(oSelZeitraum, 1, E2_INSETS.I_4_0_4_0);

//		MyE2_Row oRowHelper = new MyE2_Row();
//		oRowHelper.add(oSelZeitraum, E2_INSETS.I_0_0_0_0);
//		oRowHelper.add(new MyE2_Label("Buchungstag:"), E2_INSETS.I_10_0_0_0);
//		oRowHelper.add(oBtnRefreshDays,E2_INSETS.I_2_0_0_0);
//		oRowHelper.add(oSelDatum, E2_INSETS.I_2_0_0_0);
	
//		oRowHelper.add(cbDatumAktiv, E2_INSETS.I_20_0_0_0);
//		oRowHelper.add(oDatumBestand, E2_INSETS.I_2_0_0_0);
		
		oGrid.add(new E2_ComponentGroupHorizontal(0,cbBuchungsTagWaehlen,oDatumBestand, E2_INSETS.I_0_0_5_0),3,E2_INSETS.I_5_0_5_0);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),210),1,E2_INSETS.I_0_0_0_0);
		//oGrid.add(new MyE2_Label(new MyE2_String("")),2,E2_INSETS.I_0_0_0_0);
		
		this.add(oGrid,E2_INSETS.I_4_4_4_4);
		
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	

	public String getSelectedLager(){
		String sRet = null;
		try {
			return oSelLager.get_ActualWert();
		} catch (myException e) {
			sRet = "";
		}
		return sRet;
	}
	
	
	public String getSelectedSorte(){
		String sRet = null;
		try {
			return oSelArtikel.get_ActualWert();
		} catch (myException e) {
			sRet = "";
		}
		return sRet;
	}
	
	
	
	

	
	private class actionAgentBuchungstagAktiv extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_STAT_LIST_Selector oThis = LAG_STAT_LIST_Selector.this;
			
			boolean bNoDate = ! oThis.cbBuchungsTagWaehlen.isSelected();
			if (bNoDate){
				oThis.m_letztesDatum =  oThis.oDatumBestand.get_oFormatedDateFromTextField();
				oThis.oDatumBestand.set_oLastDateKlicked(null);
				oThis.oDatumBestand.get_oTextField().setText("");
				oThis.oDatumBestand.set_bEnabled_For_Edit(false);
				
				oThis.oSelZeitraum.set_bEnabled_For_Edit(true);
				oThis.oSelZeitraum.set_ActiveValue_OR_FirstValue(oThis.m_letzterZeitraum);
				
			} else {
				myDateHelper o = null;

				if (!bibALL.isEmpty(oThis.m_letztesDatum)) {
					o = new myDateHelper(oThis.m_letztesDatum);
				} else {
					Calendar cal = new GregorianCalendar();
					o = new myDateHelper(cal);
				}
				
				oThis.oDatumBestand.set_oLastDateKlicked(o);
				oThis.oDatumBestand.get_oTextField().setText(oThis.oDatumBestand.get_oLastDateKlicked().get_cDateFormatForMask());
				oThis.oDatumBestand.set_bEnabled_For_Edit(true);
				
				oThis.oSelZeitraum.set_bEnabled_For_Edit(false);
				oThis.m_letzterZeitraum = oThis.oSelZeitraum.get_ActualWert();
				oThis.oSelZeitraum.set_ActiveValue("-9999");
			}
			
			oThis.oSelVector.doActionPassiv();
//			String sZeitraum = oThis.oSelZeitraum.get_ActualWert();
//			String sDatum = oThis.oSelDatum.get_ActualWert();
//			
//			oThis.oSelDatum.SetParameter("#P1#", sZeitraum);
//			oThis.oSelDatum.RefreshComboboxFromSQL();
//			oThis.oSelDatum.set_ActiveValue_OR_FirstValue(sDatum);
		}
		
	}
	
	
//	
//	private class actionAgentZeitraum extends XX_ActionAgent{
//		
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			LAG_STAT_LIST_Selector oThis = LAG_STAT_LIST_Selector.this;
//			String sZeitraum = oThis.oSelZeitraum.get_ActualWert();
//			String sDatum = oThis.oSelDatum.get_ActualWert();
//			
//			oThis.oSelDatum.SetParameter("#P1#", sZeitraum);
//			oThis.oSelDatum.RefreshComboboxFromSQL();
//			oThis.oSelDatum.set_ActiveValue_OR_FirstValue(sDatum);
//			
//
//		}
//		
//	}
//	
//	

	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_STAT_LIST_Selector oThis = LAG_STAT_LIST_Selector.this;
			String sHauptartikel = oThis.oSelHauptartikel.get_ActualWert();
			String sArtikel = oThis.oSelArtikel.get_ActualWert();
			String sParam1 = "";
			if (sHauptartikel != null && !sHauptartikel.isEmpty()){
				sParam1 = " AND SUBSTR(ANR1,0,2) = '" + sHauptartikel + "'";
			} 
		
			oThis.oSelArtikel.SetParameter("#P1#", sParam1);
			oThis.oSelArtikel.RefreshComboboxFromSQL();
			oThis.oSelArtikel.set_ActiveValue_OR_FirstValue(sArtikel);

		}
		
	}
	
	

	/**
	 * Agent, der den Tag setzt, in Abhängikeit von Kunde und Sorte
	 * 
	 * es soll Sinnvoll das Tagesdatum gesetzt werden, wenn mehrere Sorten in der Auswahl sind.
	 * 
	 * @author manfred
	 *
	 */
//	private class actionAgentTagesauswahl extends XX_ActionAgent{
//		
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			LAG_STAT_LIST_Selector oThis = LAG_STAT_LIST_Selector.this;
//			
//			String sDatum = oThis.oSelDatum.get_ActualWert();
//			sDatum = (sDatum == null ? "" : sDatum);
//			
//			String sIDSorte = oThis.oSelArtikel.get_ActualWert();
//			
//			String idLager = oThis.oSelLager.get_ActualWert();
//			idLager = (idLager == null ? "" : idLager);
//			
//			
//			if (sIDSorte.equals("") && idLager.equals("") ){
//				// Datum auf aktuellsten Datumswert setzen , oder auf dem gegebenen Datumswert lassen
//				if (sDatum.equals("") && oThis.oSelDatum.get_DataToView() != null && oThis.oSelDatum.get_DataToView().size() > 1){
//					oThis.oSelDatum.setSelectedIndex(1);
//					String s = oThis.oSelDatum.get_ActualWert();
//					oThis.oSelDatum.set_ActiveValue_OR_FirstValue(s);
//				}
//			} else if (!sIDSorte.equals("") && !idLager.equals("") ){
//				// Datum auf "-" setzen 
//				if (oThis.oSelDatum.get_DataToView() != null && oThis.oSelDatum.get_DataToView().size() > 0){
//					oThis.oSelDatum.setSelectedIndex(0);
//					String s = oThis.oSelDatum.get_ActualWert();
//					oThis.oSelDatum.set_ActiveValue_OR_FirstValue(s);
//				}
//			}
//		}
//		
//	}
//	

	
	// date-time-picker
	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			
		}
	}
	

}
