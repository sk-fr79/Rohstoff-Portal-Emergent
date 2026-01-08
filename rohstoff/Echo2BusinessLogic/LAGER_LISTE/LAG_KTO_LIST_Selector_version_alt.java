package rohstoff.Echo2BusinessLogic.LAGER_LISTE;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.utils.bibROHSTOFF;

public class LAG_KTO_LIST_Selector_version_alt extends E2_ListSelectorContainer
{
	
	/**
	 * test
	 */
	private static final long serialVersionUID = 8835755329351449444L;
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private MyE2_SelectFieldWithParameters oSelArtikel = null;
	private MyE2_SelectFieldWithParameters oSelHauptartikel = null;
	
	private MyE2_SelectField oSelEinheit = null;

//	private MyE2_SelectField oSelArtikel = null;
	private MyE2_SelectField oSelLager = null;
	private MyE2_Button      oPBRefreshMaterial = null;
	
	private MyE2_CheckBox    oCBStorno = null;
	
	private LAG_KTO_LIST_SelektorWelcheTypenEinblenden oSelectorTypen = null;
	private LAG_KTO_LIST_SelektorStatusFuhre oSelectorStatusFuhren = null;
	
	
	private MyE2_Button		 oPBSetStatusWarenbewegungReal = null;
	

	// Wareneingang/Ausgangs-Selektion
	private MyE2_SelectField 			   oSelWEWA = null;
	private String[][] 		 arrWE = null;
	
	
	// Schnellzugriff auf Jahr/Monat
	private MyE2_SelectField oSelMonate = null;
	private String [][]      arrMonate = null;
	
	private MyE2_SelectField oSelJahre = null;
		
	private ownTF4Datum oDatumVon = null;
	private ownTF4Datum oDatumBis = null;
	
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public LAG_KTO_LIST_Selector_version_alt(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		initSelector(oNavigationList);
		
	}



	protected void initSelector(E2_NavigationList oNavigationList)
			throws myException
	{
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		//
		// CombobBox der Läger
		//
		Vector<String> sAdressIds = bibROHSTOFF.get_vEigeneLieferadressen();
		String sWhere = " AND ID_ADRESSE IN ("  + bibALL.Concatenate(sAdressIds, ",", "") + ")";
		
//		oSelLager = new LAG_SelectFieldOwnLAGER();
		oSelLager = (new LAG_LagerSelectField_Factory()).getSelectField();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,"JT_LAGER_KONTO.ID_ADRESSE_LAGER = #WERT#",null,null));

		//
		// ComboBox der Materialien
		//
		this.oSelHauptartikel = new MyE2_SelectFieldWithParameters("SELECT DISTINCT  SUBSTR(ANR1,0,2) , SUBSTR(ANR1,0,2)  from "
				+ bibE2.cTO()
				+ ".JT_LAGER_KONTO "
				+ " join "
				+ bibE2.cTO()
				+ ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "
				+ " WHERE JT_LAGER_KONTO.id_mandant= "
				+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false, true,
		false, false);
		oSelHauptartikel.add_oActionAgent(new actionAgentHauptartikel());
		oSelHauptartikel.RefreshComboboxFromSQL();
		
		
		this.oSelArtikel = new MyE2_SelectFieldWithParameters(
				"SELECT DISTINCT  jt_artikel.Anr1 || ' - ' ||  jt_artikel.ArtBez1 , jt_artikel.id_artikel  from "
						+ bibE2.cTO()
						+ ".JT_LAGER_KONTO "
						+ " join "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "
						+ " WHERE JT_LAGER_KONTO.id_mandant="
						+ bibALL.get_ID_MANDANT() + " #P1# ORDER BY 1", false, true,
				false, false);

		oSelArtikel.AddParameter("#P1#");
		//	Der Parameter kann auch noch explizit gesetzt werden, aber Standardmäßig wird ein Leerstring initialisiert
		//oSelArtikel.SetParameter("#P1#", "");
		
		
		//
		// ComboBox der Materialien
		//
		this.oSelEinheit = new MyE2_SelectField("SELECT EINHEITLANG, ID_EINHEIT  from "
				+ bibE2.cTO()
				+ ".JT_EINHEIT "
				+ " WHERE ID_MANDANT = "
				+ bibALL.get_ID_MANDANT() + " ORDER BY IST_STANDARD DESC, EINHEITLANG", false, true,
		false, false);
		oSelHauptartikel.RefreshComboboxFromSQL();
		
		
		oSelArtikel.RefreshComboboxFromSQL();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelHauptartikel, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'",null,null));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,	"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = #WERT#", null, null));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_LAGER_KONTO.ID_ARTIKEL_SORTE IN (" +
				"SELECT JT_ARTIKEL.ID_ARTIKEL " +
				"FROM  " + bibE2.cTO() + ".JT_ARTIKEL " +
				"LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_EINHEIT " +
				"	ON JT_ARTIKEL.ID_MANDANT = JT_EINHEIT.ID_MANDANT " +
				"	AND JT_ARTIKEL.ID_EINHEIT = JT_EINHEIT.ID_EINHEIT " +
				"WHERE JT_EINHEIT.ID_EINHEIT = #WERT# ) ", null,null));
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.oPBRefreshMaterial.setToolTipText(new MyE2_String("Aktualisieren der Material-Auswahlbox").CTrans());
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent(){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_KTO_LIST_Selector_version_alt othis = LAG_KTO_LIST_Selector_version_alt.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
			}
		});

		
		// VON-Zeitraum ab wann die Liste gelesen werden soll.
				// Standardmäßig 1 Monat 
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MONTH, -1);
		String sDate =  myDateHelper.FormatDateNormal(cal.getTime());
		oDatumVon = new ownTF4Datum( sDate, true);
		oDatumVon.get_oButtonCalendar().setToolTipText(new MyE2_String("Datum, ab wann die Lagerbuchungen ermittelt werden sollen.").CTrans());
		oDatumVon.get_oTextField().set_bEnabled_For_Edit(false);
		
		
		// BIS
		cal = new GregorianCalendar();
		sDate =  myDateHelper.FormatDateNormal(cal.getTime());
		oDatumBis = new ownTF4Datum( sDate, true);
		oDatumBis.get_oButtonCalendar().setToolTipText(new MyE2_String("Datum, bis wann die Lagerbuchungen ermittelt.").CTrans());
		oDatumBis.get_oTextField().set_bEnabled_For_Edit(false);
		
	
//		oSelVector.add(new E2_ListSelectorStandard(oDatumVon,"TRUNC(JT_LAGER_KONTO.BUCHUNGSDATUM,'DD') >= to_date('#WERT#','yyyy-mm-dd')",null));
//		oSelVector.add(new E2_ListSelectorStandard(oDatumBis,"TRUNC(JT_LAGER_KONTO.BUCHUNGSDATUM,'DD') <= to_date('#WERT#','yyyy-mm-dd')",null));
		oSelVector.add(new E2_ListSelectorStandard(oDatumVon,"to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'yyyy-MM-dd') >= '#WERT#' ",null));
		oSelVector.add(new E2_ListSelectorStandard(oDatumBis,"to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'yyyy-MM-dd') <= '#WERT#' ",null));
		

		
		// Storno-Kennzeichen
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oCBStorno = new MyE2_CheckBox("Stornierte Einträge anzeigen",oStyle);
		oSelVector.add(new E2_ListSelectorStandard(oCBStorno, "", "nvl(JT_LAGER_KONTO.STORNO,'N') = 'N'") );
		
		
	
		// neuer selektor mit OR-Verknüpfung
		oSelectorTypen = new LAG_KTO_LIST_SelektorWelcheTypenEinblenden(oNavigationList);
		oSelVector.add (oSelectorTypen);
		
		oSelectorStatusFuhren = new LAG_KTO_LIST_SelektorStatusFuhre(oNavigationList);
		oSelVector.add(oSelectorStatusFuhren);
		

		
		
		// Wareneingang/ -Ausgang
		arrWE = new String[][]{
				{new MyE2_String("-").CTrans(),""},
				{new MyE2_String("Wareneingang").CTrans(),"WE"},
				{new MyE2_String("Warenausgang").CTrans(),"WA"}};
		
		this.oSelWEWA = new MyE2_SelectField(arrWE, null, false);
		oSelVector.add(new E2_ListSelectorStandard(this.oSelWEWA, "JT_LAGER_KONTO.BUCHUNGSTYP = '#WERT#'", null, null));		
		
		// Jahr/Monatsauswahl
		
		//
		// ComboBox der Materialien
		//
		XX_ActionAgent oAgentMonate = new actionAgentMonate();
		
		oSelJahre = new MyE2_SelectFieldWithParameters("SELECT DISTINCT to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'YYYY') , to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'YYYY') from "
				+ bibE2.cTO() + ".JT_LAGER_KONTO " 
				+ " WHERE id_mandant= "
				+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false, true,false, false);
		oSelJahre.RefreshComboboxFromSQL();
		oSelJahre.add_oActionAgent(oAgentMonate);
		
		arrMonate = new String[][]{
				{new MyE2_String("-").CTrans(),""},
				{new MyE2_String("Januar").CTrans(),"01"},
				{new MyE2_String("Februar").CTrans(),"02"},
				{new MyE2_String("März").CTrans(),"03"},
				{new MyE2_String("April").CTrans(),"04"},
				{new MyE2_String("Mai").CTrans(),"05"},
				{new MyE2_String("Juni").CTrans(),"06"},
				{new MyE2_String("Juli").CTrans(),"07"},
				{new MyE2_String("August").CTrans(),"08"},
				{new MyE2_String("September").CTrans(),"09"},
				{new MyE2_String("Oktober").CTrans(),"10"},
				{new MyE2_String("November").CTrans(),"11"},
				{new MyE2_String("Dezember").CTrans(),"12"}};
		oSelMonate = new MyE2_SelectField(arrMonate, null, false);
		oSelMonate.add_oActionAgent(oAgentMonate);
		
		
		// Schnellauswahl der An-/Verkaufsfuhren, die gebucht sind
		oPBSetStatusWarenbewegungReal = new MyE2_Button(
				new MyE2_String("Auswahl Gebuchte An-/Verkäufe"), 
				E2_ResourceIcon.get_RI("wizard_mini.png"), 
				E2_ResourceIcon.get_RI("wizard_mini.png")
				);
		
		oPBSetStatusWarenbewegungReal.add_oActionAgent(new actionAgentSelektionGebuchte());
		oPBSetStatusWarenbewegungReal.setToolTipText(new MyString("Setzt die Auswahl um gebuchte Fuhren, die keine Lager/Lager-Fuhren und keine Korrekturbuchungen sind, zu finden.").CTrans());
		

		// GUI-Aufbau (Spaltenweise)
		MyE2_Grid oGrid = new MyE2_Grid(5,0);
		oGrid.setOrientation(MyE2_Grid.ORIENTATION_VERTICAL);
		
		// Spalte 1
		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);		
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("Von:")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);		
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 2
		oGrid.add(oSelLager,5,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		MyE2_Row rowArtikel = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowArtikel.add(oSelHauptartikel,E2_INSETS.I_0_0_0_0);
		rowArtikel.add(new MyE2_Label(new MyE2_String("Sorte:")) ,E2_INSETS.I_10_0_0_0);
		rowArtikel.add(oPBRefreshMaterial, E2_INSETS.I_0_0_0_0);
		rowArtikel.add(oSelArtikel, E2_INSETS.I_0_0_0_0);
		oGrid.add(rowArtikel,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		MyE2_Row rowDatum = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowDatum.add(oDatumVon,E2_INSETS.I_0_0_0_0);
		rowDatum.add(new MyE2_Label(new MyE2_String(" bis:")),E2_INSETS.I_10_0_0_0);
		rowDatum.add(oDatumBis,E2_INSETS.I_0_0_0_0);
		rowDatum.add(new MyE2_Label(new MyE2_String("Zeitraum:")),E2_INSETS.I_10_0_0_0);
		rowDatum.add(oSelMonate,E2_INSETS.I_0_0_0_0);
		rowDatum.add(oSelJahre,E2_INSETS.I_0_0_0_0);
		oGrid.add(rowDatum,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 3
		oGrid.add(new MyE2_Label(new MyE2_String("Einheit:")),1,1,E2_INSETS.I_5_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("WA/WE:")),1,1,E2_INSETS.I_5_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_5_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_5_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 4
		oGrid.add(oSelEinheit,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(oSelWEWA,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(oCBStorno,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 5
		oGrid.add(oSelectorTypen.get_oComponentForSelection(), 1, 4, E2_INSETS.I_10_0_0_0, Alignment.ALIGN_TOP);
//		oGrid.add(oPBSetStatusWarenbewegungReal, 1,1,E2_INSETS.I_5_0_0_0, Alignment.ALIGN_TOP);

		// Spalte 6
		oGrid.add(oSelectorStatusFuhren.get_oComponentForSelection(),1,3,E2_INSETS.I_10_0_0_0, Alignment.ALIGN_TOP);
		MyE2_Row rowBtn = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowBtn.add(oPBSetStatusWarenbewegungReal);
		oGrid.add(rowBtn, 1,1,new Insets(new Extent(90), new Extent(0), new Extent(0), new Extent(0)), Alignment.ALIGN_TOP);
		
		
		
		
		this.add(oGrid);
	}

	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}


	public MyE2_SelectField getSelectFieldLager(){
		return oSelLager;
	}
	
	public MyE2_SelectField getSelectFieldSorte(){
		return oSelArtikel;
	}
	
	public MyE2_TextField_DatePOPUP_OWN getDateBegin(){
		return oDatumVon;
	}
	
	public MyE2_TextField_DatePOPUP_OWN getDateEnd(){
		return oDatumBis;
	}
	
	/**
	 * gibt das aktuell selektierte Lager zurück
	 * @return
	 * @throws myException 
	 */
	public String getIDSelectedLager() throws myException{
		return oSelLager.get_ActualWert();
	}

	/**
	 * gibt die aktuell selektierte Sortenid zurück
	 * @return
	 * @throws myException 
	 */
	public String getIDSelectedSorte() throws myException{
		return oSelArtikel.get_ActualWert();
	}
	
	/**
	 * gibt die aktuell selektierte Sortenbezeichner zurück
	 * @return
	 * @throws myException 
	 */
	public String getSelectedSorteDesc() throws myException{
		return oSelArtikel.get_ActualView();
	}
	
	
	/**
	 * Gibt die aktuell selektierte Hauptsorte zurück
	 * @return
	 * @throws myException 
	 */
	public String getSelectedHauptsorte() throws myException {
		return oSelHauptartikel.get_ActualWert();
	}
	
	/**
	 * Gibt das von-Datum im ISO-Format zurück
	 * @return
	 * @throws myException
	 */
	public String getDatumVon() throws myException{
		return oDatumVon.get_oLastDateKlicked().get_cDateFormat_ISO_FORMAT();
	}
	
	/**
	 * Gibt das bis-Datum im ISO-Format zurück
	 * @return
	 * @throws myException
	 */
	public String getDatumBis() throws myException{
		return oDatumBis.get_oLastDateKlicked().get_cDateFormat_ISO_FORMAT();
	}
	
	

	/**
	 * @return the oSelectorTypen
	 */
	public LAG_KTO_LIST_SelektorWelcheTypenEinblenden get_SelectorTypen() {
		return oSelectorTypen;
	}


	/**
	 * @return the oSelectorStatusFuhren
	 */
	public LAG_KTO_LIST_SelektorStatusFuhre get_SelectorStatusFuhren() {
		return oSelectorStatusFuhren;
	}



	private class actionAgentSelektionGebuchte extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// TODO Auto-generated method stub
			LAG_KTO_LIST_Selector_version_alt oThis = LAG_KTO_LIST_Selector_version_alt.this;
			
			oThis.get_SelectorStatusFuhren().selectCheckbox(0, false);
			oThis.get_SelectorStatusFuhren().selectCheckbox(1, false);
			oThis.get_SelectorStatusFuhren().selectCheckbox(2, false);
			oThis.get_SelectorStatusFuhren().selectCheckbox(3, false);
			oThis.get_SelectorStatusFuhren().selectCheckbox(4, true);
			
			
			oThis.get_SelectorTypen().selectCheckbox(0, true);
			oThis.get_SelectorTypen().selectCheckbox(1, false);
			oThis.get_SelectorTypen().selectCheckbox(2, false);
			oThis.get_SelectorTypen().selectCheckbox(3, false);
			
			oThis.oCBStorno.setSelected(false);
			oThis.oSelVector.doActionPassiv();
		}

	}
	

	
	
	private class actionAgentMonate extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_KTO_LIST_Selector_version_alt oThis = LAG_KTO_LIST_Selector_version_alt.this;
			String sJahr  = oThis.oSelJahre.get_ActualWert();
			String sMonat = oThis.oSelMonate.get_ActualWert();
			
			if (sMonat != "" && sJahr != ""){

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

			}
		}
		
	}
	
	
	
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_KTO_LIST_Selector_version_alt oThis = LAG_KTO_LIST_Selector_version_alt.this;
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

	private class actionAgentCalendarSelected extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_KTO_LIST_Selector_version_alt oThis = LAG_KTO_LIST_Selector_version_alt.this;
			oThis.oSelJahre.setSelectedIndex(0);
			oThis.oSelMonate.setSelectedIndex(0);
		}
		
	}
	
	
	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			
		}
	}

	
	
}
