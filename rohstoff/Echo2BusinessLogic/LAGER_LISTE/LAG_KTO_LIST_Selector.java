package rohstoff.Echo2BusinessLogic.LAGER_LISTE;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

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
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_von_bis;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory_Extended;
import sun.security.util.Debug;

public class LAG_KTO_LIST_Selector extends E2_ListSelectorContainer
{
	
	/**
	 * test
	 */
	private static final long serialVersionUID = 8835755329351449444L;
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private static String cBasisQueryMultiBereichSelektor = "SELECT DISTINCT  JT_ARTIKEL.ANR1 || ' - ' ||  JT_ARTIKEL.ARTBEZ1 , JT_ARTIKEL.ANR1  from "+ bibE2.cTO()
															+ ".JT_LAGER_KONTO "
															+ " JOIN "
															+ bibE2.cTO()
															+ ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
															" #WHERE# ";

	
	private int[]  iSpalten = {140,80,25,140,70,100,60};              //fuer gleichheit der selektor-unter-grids
	
	private MyE2_SelectFieldWithParameters oSelArtikel = null;
	private MyE2_SelectFieldWithParameters oSelArtikelName = null;
	
	private MyE2_SelectFieldWithParameters oSelHauptartikel = null;
	
	private MyE2_SelectField oSelEinheit = null;

//	private MyE2_SelectField oSelArtikel = null;
	private MyE2_SelectField oSelLager = null;
	private LAG_LagerSelectField_Factory_Extended oSelLagerFactory = null;
	
	private MyE2_Button      oPBRefreshMaterial = null;
	
	private MyE2_CheckBox    oCBStorno = null;
	
	private LAG_KTO_LIST_SelektorWelcheTypenEinblenden oSelectorTypen = null;
	private LAG_KTO_LIST_SelektorStatusFuhre oSelectorStatusFuhren = null;
	
	
	private MyE2_Button		 oPBSetStatusWarenbewegungReal = null;
	

	// Wareneingang/Ausgangs-Selektion
	private MyE2_SelectField 	oSelWEWA = null;
	private String[][] 		 	arrWE = null;
	
	
	// Schnellzugriff auf Jahr/Monat
	private MyE2_SelectField 	oSelMonate = null;
	private String [][]      	arrMonate = null;
	
	private MyE2_SelectField 	oSelJahre = null;
		
	private ownTF4Datum 		oDatumVon = null;
	private ownTF4Datum 		oDatumBis = null;
	
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	//2012-10-15: sortenbereich selektieren
	private SelectorSortenVonBis_Multi      oSortenBlockSelektor = null;
	
	// Fremdwarenläger berücksichtigen
	private MyE2_CheckBox    	oCBShowFremdwarenlager = null;
	private MyE2_CheckBox    	oCBShowEigenwarenlager = null;
	
	
	
	public LAG_KTO_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		initSelector(oNavigationList, cMODULE_KENNER);
		
	}



	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER)  throws myException
	{
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		oSelLagerFactory = new LAG_LagerSelectField_Factory_Extended();
		oSelLager = oSelLagerFactory.getSelectField();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,"JT_LAGER_KONTO.ID_ADRESSE_LAGER = #WERT#",null,null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_ID_ADRESSE_LAGER);

		//2012-10-15: sortenselektor mit mehrfacher bereichsauswahl
		this.oSortenBlockSelektor = new SelectorSortenVonBis_Multi();
		oSortenBlockSelektor.add_ActionAgentToComponent(new actionAgentSortenbreich());
		oSelVector.add(this.oSortenBlockSelektor);
		
		
		
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
		
		
		//2012-10-15: selektionArtikel loescht den mehrfachselektor
		oSelArtikel.add_oActionAgent(new ownActionClearSortenBereichsMultiSelector(), true);
		
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
	
		
		
		oSelVector.add(new E2_ListSelectorStandard(oSelHauptartikel, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'",null,null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_HAUPTSORTE);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,	"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = #WERT#", null, null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_ID_ARTIKEL);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_LAGER_KONTO.ID_ARTIKEL_SORTE IN (" +
				"SELECT JT_ARTIKEL.ID_ARTIKEL " +
				"FROM  " + bibE2.cTO() + ".JT_ARTIKEL " +
				"LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_EINHEIT " +
				"	ON JT_ARTIKEL.ID_MANDANT = JT_EINHEIT.ID_MANDANT " +
				"	AND JT_ARTIKEL.ID_EINHEIT = JT_EINHEIT.ID_EINHEIT " +
				"WHERE JT_EINHEIT.ID_EINHEIT = #WERT# ) ", null,null), ENUM_Selector_Report_Params.LAGERLISTE_ID_ARTIKEL);
		

		
		
		/**
		 * oSelArtikelName ist identisch mit oSelArtikel, nur dass die Anzeige nur den
		 * Namen der Sorte OHNE die ANR1 zeigt
		 */
		this.oSelArtikelName = new MyE2_SelectFieldWithParameters(
				"SELECT DISTINCT  jt_artikel.ArtBez1 , jt_artikel.id_artikel  from "
						+ bibE2.cTO()
						+ ".JT_LAGER_KONTO "
						+ " join "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "
						+ " WHERE JT_LAGER_KONTO.id_mandant="
						+ bibALL.get_ID_MANDANT() + " #P1# ORDER BY 1", false, true,
				false, false);

		oSelArtikelName.AddParameter("#P1#");
		//	Der Parameter kann auch noch explizit gesetzt werden, aber Standardmäßig wird ein Leerstring initialisiert
		
		oSelArtikelName.RefreshComboboxFromSQL();
		
		//2012-10-15: selektionArtikel loescht den mehrfachselektor
		oSelArtikelName.add_oActionAgent(new ownActionClearSortenBereichsMultiSelector(), true);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikelName,	"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = #WERT#", null, null));
		
		// die Synchronisation der Listboxen 
		XX_ActionAgent oAgentSynchronizeListbox = new ownActionSynchronizeSortenList();
		oSelArtikelName.add_oActionAgent( new ownActionSynchronizeSortenList(),true);
		oSelArtikel.add_oActionAgent( new ownActionSynchronizeSortenList(),true);
		
		
		
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.oPBRefreshMaterial.setToolTipText(new MyE2_String("Aktualisieren der Material-Auswahlbox").CTrans());
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent(){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_KTO_LIST_Selector othis = LAG_KTO_LIST_Selector.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
				othis.oSelArtikelName.RefreshComboboxFromSQL();
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
		

		oSelVector.add(new E2_ListSelectorStandard(oDatumVon,"to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'yyyy-MM-dd') >= '#WERT#' ",null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_DATUM_VON);
		oSelVector.add(new E2_ListSelectorStandard(oDatumBis,"to_char(JT_LAGER_KONTO.BUCHUNGSDATUM,'yyyy-MM-dd') <= '#WERT#' ",null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_DATUM_BIS);
		

		
		// Storno-Kennzeichen
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oCBStorno = new MyE2_CheckBox("Stornierte Einträge anzeigen",oStyle);
		oSelVector.add(new E2_ListSelectorStandard(oCBStorno, "", "nvl(JT_LAGER_KONTO.STORNO,'N') = 'N'") ,ENUM_Selector_Report_Params.LAGERLISTE_KONTO_STORNO);
		
		
	
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
		oSelVector.add(new E2_ListSelectorStandard(this.oSelWEWA, "JT_LAGER_KONTO.BUCHUNGSTYP = '#WERT#'", null, null),ENUM_Selector_Report_Params.LAGERLISTE_KONTO_BUCHUNGSTYP);		
		
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
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		
		oCBShowFremdwarenlager = new MyE2_CheckBox("Fremdwarenlager",oStyle);
		oCBShowFremdwarenlager.setSelected(true);
		oSelVector.add(new E2_ListSelectorStandard(oCBShowFremdwarenlager, "","JT_LIEFERADRESSE.ID_ADRESSE_FREMDWARE IS NULL ") ,ENUM_Selector_Report_Params.LAGERLISTE_KONTO_SHOW_FREMDWARENLAGER);
		
		oCBShowEigenwarenlager = new MyE2_CheckBox("Eigenwarenlager",oStyle);
		oCBShowEigenwarenlager.setSelected(true);
		oSelVector.add(new E2_ListSelectorStandard(oCBShowEigenwarenlager, "","JT_LIEFERADRESSE.ID_ADRESSE_FREMDWARE IS NOT NULL"), ENUM_Selector_Report_Params.LAGERLISTE_KONTO_SHOW_EIGENWARENLAGER);
		
		

		// GUI-Aufbau (Spaltenweise)
		MyE2_Grid oGrid = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGrid.setOrientation(MyE2_Grid.ORIENTATION_VERTICAL);
		oGrid.setRowHeight(0,new Extent(23));
		
		// Spalte 1
		oGrid.add_ext(new MyE2_Label(new MyE2_String("Lager:")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);
		
		oGrid.add_ext(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);
		
		oGrid.add_ext(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(new MyE2_Label(new MyE2_String("Sortenbereich:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);		
		oGrid.add_ext(new MyE2_Label(new MyE2_String("Von:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 2
		oGrid.add_ext(oSelLagerFactory.render(),5,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		MyE2_Row oRowLagertypen = new MyE2_Row();
		
		oRowLagertypen.add(new MyE2_Label(new MyE2_String("Einbeziehen:")));
		oRowLagertypen.add(oCBShowEigenwarenlager,E2_INSETS.I_10_0_0_0);
		oRowLagertypen.add(oCBShowFremdwarenlager,E2_INSETS.I_10_0_0_0);
		oGrid.add_ext(oRowLagertypen,5,1,E2_INSETS.I_0_0_0_3,Alignment.ALIGN_TOP);
		
		
		ownSelektorHelpGrid oGridArtikel = new ownSelektorHelpGrid();
		oSelHauptartikel.setWidth(new Extent(50));
		oSelArtikel.setWidth(new Extent(370));
		oSelArtikelName.setWidth(new Extent(370));
		
		oGridArtikel.add(oSelHauptartikel,1,E2_INSETS.I_0_0_5_0);
		oGridArtikel.add(new MyE2_Label(new MyE2_String("Sorte:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridArtikel.add(oPBRefreshMaterial,1, E2_INSETS.I_0_0_5_0);
		oGridArtikel.add(oSelArtikel,4, E2_INSETS.I_0_0_0_0);
		// sortennamen
		oGridArtikel.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_5_0);
		oGridArtikel.add(new MyE2_Label(new MyE2_String("Sortenname:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridArtikel.add(new MyE2_Label(""),1, E2_INSETS.I_0_0_5_0);
		oGridArtikel.add(oSelArtikelName,4, E2_INSETS.I_0_0_0_0);

		
		oGrid.add_ext(oGridArtikel,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		//hier muss der sortenselektor hin (mulitselektor von bis)
		oGrid.add_ext(this.oSortenBlockSelektor.get_oComponentForSelection(),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		ownSelektorHelpGrid oGridDatum = new ownSelektorHelpGrid(); 
		oGridDatum.add(oDatumVon,1,E2_INSETS.I_0_0_5_0);
		oGridDatum.add(new MyE2_Label(new MyE2_String(" bis:")),2,E2_INSETS.I_0_0_5_0);
		oGridDatum.add(oDatumBis,1,E2_INSETS.I_0_0_5_0);
		oGridDatum.add(new MyE2_Label(new MyE2_String("Zeitraum:")),1,E2_INSETS.I_0_0_5_0);
		oSelMonate.setWidth(new Extent(100));
		oSelJahre.setWidth(new Extent(60));
		
		oGridDatum.add(oSelMonate,1,E2_INSETS.I_0_0_0_0);
		oGridDatum.add(oSelJahre,1,E2_INSETS.I_0_0_0_0);
		oGrid.add_ext(oGridDatum,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		oGrid.add_ext(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 3
		oGrid.add_ext(new MyE2_Label(new MyE2_String("Einheit:")),1,1,E2_INSETS.I_5_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(new MyE2_Label(new MyE2_String("WA/WE:")),1,1,E2_INSETS.I_5_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_5_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(new MyE2_Label(new MyE2_String(oDB_BasedSelektor.get_bIsFilled()?"Divers:":"")),1,1,E2_INSETS.I_5_3_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 4
		oGrid.add_ext(oSelEinheit,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(oSelWEWA,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(oCBStorno,1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGrid.add_ext(oDB_BasedSelektor.get_oComponentForSelection(100),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// Spalte 5
		oGrid.add_ext(oSelectorTypen.get_oComponentForSelection(), 1, 3, E2_INSETS.I_10_0_0_0, Alignment.ALIGN_TOP);
		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());
		oGrid.add_ext(oRowPassivschalter,1,1,new Insets(new Extent(50), new Extent(0), new Extent(0), new Extent(0)),Alignment.ALIGN_TOP);

		
		// Spalte 6
		oGrid.add_ext(oSelectorStatusFuhren.get_oComponentForSelection(),1,3,E2_INSETS.I_10_0_0_0, Alignment.ALIGN_TOP);
		MyE2_Row rowBtn = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowBtn.add(oPBSetStatusWarenbewegungReal);
		oGrid.add_ext(rowBtn, 1,1,new Insets(new Extent(90), new Extent(0), new Extent(0), new Extent(0)), Alignment.ALIGN_TOP);
		
		
		this.add(oGrid);
	}

	
	private class ownSelektorHelpGrid extends MyE2_Grid
	{
		public ownSelektorHelpGrid()
		{
			super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			this.set_Spalten(LAG_KTO_LIST_Selector.this.iSpalten);
		}

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
	
//	
//	/**
//	 * gibt die Selectfields der Von-Bis-Komponente zurück
//	 * @author manfred
//	 * @date 10.06.2021
//	 *
//	 * @return
//	 */
//	public MyE2_SelectField[] getSelectFieldsSorteVonBis() {
//		MyE2_SelectField[] fields = new MyE2_SelectField[2];
//		fields[0] = oSortenBlockSelektor.get_oSelFieldBasis_von();
//		fields[1] = oSortenBlockSelektor.get_oSelFieldBasis_bis();
//		return fields;
//	}
//	
//	
//	/**
//	 * gibt die Selektierten Werte der Von-Bis-Komponente zurück
//	 * @author manfred
//	 * @date 10.06.2021
//	 *
//	 * @return
//	 * @throws myException
//	 */
//	public String[] getSelectedSorteVonBis() throws myException {
//		String[] sorten = new String[2];
//		sorten[0] = oSortenBlockSelektor.get_oSelFieldBasis_von().get_ActualWert();
//		sorten[1] = oSortenBlockSelektor.get_oSelFieldBasis_bis().get_ActualWert();
//		return sorten;
//	}
//	
	/**
	 * gibt den Sortenblock-Selektor zurück
	 * @author manfred
	 * @date 11.06.2021
	 *
	 * @return
	 */
	public SelectorSortenVonBis_Multi getSortenblockSelektor() {
		return oSortenBlockSelektor;
	}
	
	/**
	 * gibt die aktuell selektierte Einheit zurück
	 * @return
	 * @throws myException
	 */
	public String getIDSelectedEinheit() throws myException{
		return oSelEinheit.get_ActualWert();
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
			LAG_KTO_LIST_Selector oThis = LAG_KTO_LIST_Selector.this;
			
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
			LAG_KTO_LIST_Selector oThis = LAG_KTO_LIST_Selector.this;
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
			LAG_KTO_LIST_Selector oThis = LAG_KTO_LIST_Selector.this;
			String sHauptartikel = oThis.oSelHauptartikel.get_ActualWert();
			String sArtikel = oThis.oSelArtikel.get_ActualWert();
			String sParam1 = "";
			if (sHauptartikel != null && !sHauptartikel.isEmpty()){
				sParam1 = " AND SUBSTR(ANR1,0,2) = '" + sHauptartikel + "'";
			} 
		
			/* 2. Artikel-Selektor auf Sortennummer/Sortenname */
			oThis.oSelArtikel.SetParameter("#P1#", sParam1);
			oThis.oSelArtikel.RefreshComboboxFromSQL();
			oThis.oSelArtikel.set_ActiveValue_OR_FirstValue(sArtikel);
			
			/* 2. Artikel-Selektor auf Sortenname */
			oThis.oSelArtikelName.SetParameter("#P1#", sParam1);
			oThis.oSelArtikelName.RefreshComboboxFromSQL();
			oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue(sArtikel);
			
			//2012-10-15: multiselect-feld auch mit beruecksichtigen
			oThis.oSortenBlockSelektor.set_SortenGruppe(sHauptartikel);
			
		}
		
	}

	
	/**
	 * wenn der sortenbereich gewählt wird, dann den Einzelartikel löschen!
	 * @author manfred
	 * @date 11.06.2021
	 *
	 */
	private class actionAgentSortenbreich extends XX_ActionAgent {
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_KTO_LIST_Selector oThis = LAG_KTO_LIST_Selector.this;
			if (oThis.oSortenBlockSelektor.get_ArrayOfSelectedValues().size() > 0) {
				
				oThis.oSelArtikel.set_ActiveValue_OR_FirstValue("");
				oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue("");
			}
		}
	}
	

	/**
	 * Synchronisiert die Listboxen die die Sortenbezeichnungen auflisten
	 * @author manfred
	 * @date   01.02.2013
	 */
	private class ownActionSynchronizeSortenList extends XX_ActionAgent	{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_KTO_LIST_Selector oThis = LAG_KTO_LIST_Selector.this;
			
			String sValue = null;
			if (oExecInfo.get_MyActionEvent().getSource() instanceof MyE2_SelectFieldWithParameters ){
				// Aktueller Wert
				sValue = ((MyE2_SelectFieldWithParameters)oExecInfo.get_MyActionEvent().getSource()).get_ActualWert();

				if (oExecInfo.get_MyActionEvent().getSource().equals(oThis.oSelArtikel)){
					// Artikelname nachziehen
					oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue(sValue);
					
				} else if (oExecInfo.get_MyActionEvent().getSource().equals(oThis.oSelArtikelName)){
					// Artikel nachziehen
					oThis.oSelArtikel.set_ActiveValue_OR_FirstValue(sValue);
				}
				
			}
				
			
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

	
	/**
	 * 2012-10-16: beim auswaehlen einer sorten wird der sortenbereichsselektor geloescht
	 * @author martin
	 *
	 */
	private class ownActionClearSortenBereichsMultiSelector extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LAG_KTO_LIST_Selector.this.oSortenBlockSelektor.LEER_MACHEN();
		}
	}
	
	
	
	
	
	//2012-10-15: mehrfachselektion der sorten/sortenbereiche
	protected class SelectorSortenVonBis_Multi extends E2_ListSelectorMultiDropDown_von_bis
	{
		
		
		public SelectorSortenVonBis_Multi() throws myException
		{
			super(bibALL.ReplaceTeilString(LAG_KTO_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#",""),  
					"JT_LAGER_KONTO.ID_ARTIKEL_SORTE IN (SELECT A.ID_ARTIKEL FROM " +bibE2.cTO()+".JT_ARTIKEL A WHERE A.ANR1>='#WERT1#' AND A.ANR1<='#WERT2#')");
			
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
			this.get_grid4Anzeige().set_Spalten(LAG_KTO_LIST_Selector.this.iSpalten);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_von(),1,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(new MyE2_Label(new MyE2_String(" bis ")),2,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_bis(),1,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),1,E2_INSETS.I_0_0_0_0);
			
			this.get_oSelFieldBasis_von().setWidth(new Extent(130));
			this.get_oSelFieldBasis_bis().setWidth(new Extent(130));
			
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
				LAG_KTO_LIST_Selector.this.oSelArtikel.set_ActiveValue_OR_FirstValue(null);
			}
		}
		
		
		/**
		 * 
		 * @param cSortenGruppe sind die 2 ersten stellen der ANR1
		 * @throws myException 
		 */
		public void set_SortenGruppe(String cSortenGruppe) throws myException 
		{
			String cQuery = "";
			
			if (S.isEmpty(cSortenGruppe))
			{
				cQuery = bibALL.ReplaceTeilString(LAG_KTO_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#","");
			}
			else
			{
				cQuery = bibALL.ReplaceTeilString(LAG_KTO_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#","  WHERE JT_ARTIKEL.ANR1 LIKE '"+cSortenGruppe+"%'");
			}
			
			String[][] cValues = E2_ListSelectorMultiDropDown_von_bis.baue_SelectFieldArray(cQuery);
			
			this.LEER_MACHEN();   //alles resetten
			
			this.get_oSelFieldBasis_von().set_ListenInhalt(cValues, false);
			this.get_oSelFieldBasis_bis().set_ListenInhalt(cValues, false);
		}
		
	}
	
	
}
