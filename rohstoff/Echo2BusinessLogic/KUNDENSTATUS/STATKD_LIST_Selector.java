package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
 	
	
	// Wareneingang/Ausgangs-Selektion
	private MyE2_SelectField 			   	oSelZeitraum = null;
	private String[][] 		 				arrZeitraum = null;
	private MyE2_SelectFieldWithParameters  oSelDatum = null;
	private MyE2_SelectField 				oSelCustomer = null;
	
	private MyE2_Button						oBtnRefreshDays = null;
	
	
	private STATKD_COL_Status_Kunde        oColStatusKunde = null;
	
	
	public STATKD_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		oSelCustomer = new MyE2_SelectField("" +
				" SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')|| '-'||  NVL(JT_ADRESSE.ORT,'-') " +
				" ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , " +
				" JT_ADRESSE.ID_ADRESSE"  +
				" FROM " + bibE2.cTO() + ".JT_ADRESSE " +
				" WHERE ID_ADRESSE IN (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_STATUS_KUNDE S ) ORDER BY NAMEN " 
				, false, true, false, false);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelCustomer, "JT_STATUS_KUNDE.ID_ADRESSE = #WERT#",null,null ));
		
		oSelCustomer.add_oActionAgent(new localActionAgentCustomer(),true);
		oSelCustomer.setWidth(new Extent(300));
		
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
		oSelVector.add(new E2_ListSelectorStandard(this.oSelZeitraum, "JT_STATUS_KUNDE.BUCHUNGSDATUM >= ADD_MONTHS(SYSDATE,#WERT#)", null, null));	
		
		actionAgentZeitraum oHandlerZeitraum = new actionAgentZeitraum();
		
		oSelZeitraum.add_oActionAgent(oHandlerZeitraum,true);
		//oSelZeitraum.RefreshComboboxFromSQL();
		
		
		oSelDatum = new MyE2_SelectFieldWithParameters("SELECT DISTINCT BUCHUNGSDATUM, to_char(BUCHUNGSDATUM,'yyyy-mm-dd') " +
				" FROM "+ bibE2.cTO()+".JT_STATUS_KUNDE WHERE BUCHUNGSDATUM >= ADD_MONTHS(SYSDATE,#P1#) ORDER BY BUCHUNGSDATUM DESC", 
				false, 
				true, 
				true, 
				false);
		oSelDatum.AddParameter("#P1#");
		
		oSelDatum.SetParameter("#P1#", oSelZeitraum.get_ActualWert());
		oSelDatum.RefreshComboboxFromSQL();
		oSelDatum.setToolTipText(new MyE2_String("Bei Rechnungs/Gutschrifts-Zeilen das Druckdatum auf Beleg, sonst das Datum(Erst.)").CTrans());
		
		// aktuell das 1. Datum selektieren
		if (oSelDatum.get_DataToView().size() > 1){
			oSelDatum.setSelectedIndex(1);
			String s = oSelDatum.get_ActualWert();
			oSelDatum.set_ActiveValue_OR_FirstValue(s);
		}
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);

		
		oSelVector.add(new E2_ListSelectorStandard(oSelDatum, "JT_STATUS_KUNDE.BUCHUNGSDATUM = '#WERT#'", null, null));
		
		
		// den gleichen Eventhandler wie für den Zeitraum-Refresh
		oBtnRefreshDays = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oBtnRefreshDays.add_oActionAgent(oHandlerZeitraum);
		oBtnRefreshDays.setToolTipText(new MyString("Buchungstage aktualisieren.").CTrans());
		
			
		MyE2_Grid oGridInnen = new MyE2_Grid(5,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Kunde:"),1, new Insets(4,2,20,2));
		oGridInnen.add(oSelCustomer,2, new Insets(4,2,15,2));
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100), 2, new Insets(4,2,15,2));
		
		
		oGridInnen.add(new MyE2_Label("Betrachtungszeitraum:"), 1,new Insets(4,2,20,2));
		oGridInnen.add(oSelZeitraum, 1, new Insets(4,2,15,2));

		
		
		
		
		
		MyE2_Row oRowHelper = new MyE2_Row();
		oRowHelper.add(new MyE2_Label("Buchungstag:"), new Insets(4,2,5,2));
		oRowHelper.add(oBtnRefreshDays,new Insets(2,2,2,2));
		oRowHelper.add(oSelDatum, new Insets(4,2,15,2));
		oRowHelper.add(new MyE2_Label(""),  new Insets(4,2,20,2));
		
		oGridInnen.add(oRowHelper,3,E2_INSETS.I_0_0_0_0);
		
		// TEST KUNDENDISPLAY
		oColStatusKunde = new STATKD_COL_Status_Kunde();
		oColStatusKunde.set_Style_Heading(MyE2_Label.STYLE_NORMAL_BOLD());
		oColStatusKunde.set_Style_Text_Positive(MyE2_Label.STYLE_NORMAL_PLAIN());
		oColStatusKunde.set_Style_Text_Negative(MyE2_Label.STYLE_NORMAL_RED());
		
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Aktueller Kundenstatus Forderungen / Verbindlichkeiten:"),MyE2_Label.STYLE_NORMAL_BOLD()),5,E2_INSETS.I_0_0_0_0);
		oGridInnen.add(oColStatusKunde,5,E2_INSETS.I_0_5_0_0);
		

	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	

	
	private class actionAgentZeitraum extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			STATKD_LIST_Selector oThis = STATKD_LIST_Selector.this;
			String sZeitraum = oThis.oSelZeitraum.get_ActualWert();
			String sDatum = oThis.oSelDatum.get_ActualWert();
			
			oThis.oSelDatum.SetParameter("#P1#", sZeitraum);
			oThis.oSelDatum.RefreshComboboxFromSQL();
			oThis.oSelDatum.set_ActiveValue_OR_FirstValue(sDatum);
			

		}
		
	}
	
	
	private class localActionAgentCustomer extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			STATKD_LIST_Selector oThis = STATKD_LIST_Selector.this;
			
			String sDatum = oThis.oSelDatum.get_ActualWert();
			sDatum = (sDatum == null ? "" : sDatum);
			
			String idCustomer = oThis.oSelCustomer.get_ActualWert();
			idCustomer = (idCustomer == null ? "" : idCustomer);
			
			
			// TEST KUNDENINFO
			oThis.oColStatusKunde.setIDAdresse(idCustomer);
			oThis.oColStatusKunde.refresh();
			// TEST KUNDENINFO
			
			// wenn der
			if (idCustomer.equals("") && sDatum.equals("") ){

				// versuchen, den 1.Eintrag zu setzen
				try {
					oThis.oSelDatum.setSelectedIndex(1);
					String s = oThis.oSelDatum.get_ActualWert();
					oThis.oSelDatum.set_ActiveValue_OR_FirstValue(s);
					
				} catch (Exception e) {
					// kein Eintrag da...
				}
			}  else  if (!idCustomer.equals("")){
				oThis.oSelDatum.setSelectedIndex(0);
				String s = oThis.oSelDatum.get_ActualWert();
				oThis.oSelDatum.set_ActiveValue_OR_FirstValue(s);
			}

		}
		
	}
	
}
