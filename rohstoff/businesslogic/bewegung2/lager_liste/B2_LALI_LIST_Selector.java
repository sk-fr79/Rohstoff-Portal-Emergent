package rohstoff.businesslogic.bewegung2.lager_liste;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_von_bis;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_ExpandableRow_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;
import rohstoff.businesslogic.bewegung2.lager_liste.B2_LALI_LIST_SelektorWelcheTypenEinblenden.ENUM_SELEKTOR_BEWEGUNGSTYPEN;
import rohstoff.businesslogic.bewegung2.lager_liste.B2_LALI_LIST_Selektor_EW_Abrechenbar.ENUM_SELEKTOR_EWFW;
import rohstoff.businesslogic.bewegung2.lager_liste.B2_LALI_SelektorEigenwarenFremdwaren.ENUM_SELEKTOR_Lagertyp;



public class B2_LALI_LIST_Selector extends E2_ExpandableRow
{
	
	
	private static String cBasisQueryMultiBereichSelektor = "SELECT DISTINCT  JT_ARTIKEL.ANR1 || ' - ' ||  JT_ARTIKEL.ARTBEZ1 , JT_ARTIKEL.ANR1  from "+ bibE2.cTO()
															+ ".JT_LAGER_KONTO "
															+ " JOIN " + bibE2.cTO() + ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "+
															" #WHERE# ";

	//fuer gleichheit der selektor-unter-grids
	private int[] 										iSpaltenGridMain 	= {100,1000,100,100};

	
	

	private UTIL_MultiSelectField_Factory_ForLager  		oSelMulti_Lager = null;
	private B2_LALI_SelektorEigenwarenFremdwaren 			oSelectorLagerTypen = null;

	private UTIL_MultiSelectField_Hauptsorte		 		oSelMulti_Hauptsorte = null;
	private UTIL_MultiSelectField_Sorte				 		oSelMulti_Sorte = null;
	private MyE2_Button      								oPBRefreshMaterial = null;

	private MyE2_SelectFieldWithParameters 					oSelArtikelName = null;
	private MyE2_SelectField 								oSelEinheit = null;
	
	
	private MyE2_CheckBox    								oCBStorno = null;
	private B2_LALI_LIST_SelektorWelcheTypenEinblenden 	oSelectorTypen = null;
	private B2_LALI_LIST_Selektor_EW_Abrechenbar			oSelectorEW = null;
	private MyE2_CheckBox									oCBInclusiveStrecke = null;


	// Wareneingang/Ausgangs-Selektion
	private MyE2_SelectField 	oSelWEWA = null;
	private String[][] 		 	arrWE = null;
	
	// Schnellzugriff auf Jahr/Monat
	private MyE2_SelectField 	oSelMonate = null;
	private String [][]      	arrMonate = null;
	
	private MyE2_SelectField 	oSelJahre = null;
		
	private ownTF4Datum 		oDatumVon = null;
	private ownTF4Datum 		oDatumBis = null;
	
	
	//2012-10-15: sortenbereich selektieren
	private SelectorSortenVonBis_Multi      oSortenBlockSelektor = null;
	
	
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;

	private E2_MutableStyle oStyleCheckboxBorderless = new E2_MutableStyle();



	public B2_LALI_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		// Checkbox Borderless definieren
		oStyleCheckboxBorderless.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		
		
		initSelector(oNavigationList, cMODULE_KENNER);
		
	}

	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	public UTIL_MultiSelectField_Factory_ForLager get_oSelMulti_Lager() {
		return oSelMulti_Lager;
	}

	
	
	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER)  throws myException
	{
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

//		// neuer Selektor für die Auswahl der Lagertypen
		oSelectorLagerTypen = new B2_LALI_SelektorEigenwarenFremdwaren(oNavigationList, "LA", "JT_BG_AUSWERT",true,"");
		oSelectorLagerTypen.set_CheckboxIsExternal(2);
		oSelVector.add (oSelectorLagerTypen);
		
		
		// Lagerauswahl
		oSelMulti_Lager = new UTIL_MultiSelectField_Factory_ForLager(true,"",800,"JT_BG_AUSWERT.ID_ADRESSE =  #WERT# ");
		oSelMulti_Lager.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentLagerSelektion());
		oSelVector.add(oSelMulti_Lager);

		// Sortenselektor mit mehrfacher Bereichsauswahl
		this.oSortenBlockSelektor = new SelectorSortenVonBis_Multi();
		oSelVector.add(this.oSortenBlockSelektor);
		
		//
		// ComboBox der Materialien
		//
		oSelMulti_Hauptsorte = new UTIL_MultiSelectField_Hauptsorte(null, 50, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'"/*,new actionAgentHauptartikel()*/);
		oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new ownActionClearSortenBereichsMultiSelector());
		oSelVector.add(oSelMulti_Hauptsorte);
		
		
		oSelMulti_Sorte = new UTIL_MultiSelectField_Sorte(null, 300, "JT_BG_AUSWERT.ID_ARTIKEL = #WERT#");
		oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionSynchronizeSortenName());
		oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(new ownActionClearSortenBereichsMultiSelector());
		oSelVector.add(oSelMulti_Sorte);
		
		
		//
		// SORTENNAME		
		// oSelArtikelName ist identisch mit oSelArtikel, nur dass die Anzeige nur den
		// Namen der Sorte OHNE die ANR1 zeigt
		this.oSelArtikelName = new MyE2_SelectFieldWithParameters(
				"SELECT DISTINCT  jt_artikel.ArtBez1 , jt_artikel.id_artikel  from "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL "
						+ " WHERE JT_ARTIKEL.id_mandant="
						+ bibALL.get_ID_MANDANT() + " #P1# ORDER BY 1", false, true,
				false, false);

		oSelArtikelName.AddParameter("#P1#");
		oSelArtikelName.setWidth(new Extent(300));
		oSelArtikelName.RefreshComboboxFromSQL();
		oSelArtikelName.add_oActionAgent(new ownActionClearSortenBereichsMultiSelector(), true);
		oSelArtikelName.add_oActionAgent( new actionSynchronizeSorten(),true);
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikelName,	"JT_BG_AUSWERT.ID_ARTIKEL = #WERT#", null, null));

		
		
		//
		// ComboBox der Einheiten
		//
		this.oSelEinheit = new MyE2_SelectField("SELECT EINHEITLANG, ID_EINHEIT  from "
				+ bibE2.cTO()
				+ ".JT_EINHEIT "
				+ " WHERE ID_MANDANT = "
				+ bibALL.get_ID_MANDANT() + " ORDER BY IST_STANDARD DESC, EINHEITLANG", false, true,
		false, false);

		// Einheitselektion auf Standard-Gewichtsfeld
		RECLIST_EINHEIT rlEinheit = new RECLIST_EINHEIT(RECORD_EINHEIT.FIELD__IST_STANDARD + "='Y'" , "");
		if (rlEinheit.size() >0 ){
			RECORD_EINHEIT rec = rlEinheit.get(0);
			oSelEinheit.set_ActiveValue_OR_FirstValue(rec.get_ID_EINHEIT_cUF());
		}
		
//		
//		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_BEWEGUNG_ATOM.ID_ARTIKEL IN (" +
//				" SELECT JT_ARTIKEL.ID_ARTIKEL " +
//				" FROM  " + bibE2.cTO() + ".JT_ARTIKEL " +
//				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_EINHEIT " +
//				"	ON JT_ARTIKEL.ID_MANDANT = JT_EINHEIT.ID_MANDANT " +
//				"	AND JT_ARTIKEL.ID_EINHEIT = JT_EINHEIT.ID_EINHEIT " +
//				" WHERE JT_EINHEIT.ID_EINHEIT = #WERT# ) ", null,null));
//			
		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_ARTIKEL.ID_EINHEIT = #WERT#  ", null,null));
		
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.oPBRefreshMaterial.setToolTipText(new MyE2_String("Aktualisieren der Sorten-Auswahlbox").CTrans());
		this.oPBRefreshMaterial.add_oActionAgent(new actionAgentHauptartikel());

		
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
		

		oSelVector.add(new E2_ListSelectorStandard(oDatumVon,"JT_BG_AUSWERT.DATUM_AUSFUEHRUNG >= to_date('#WERT#','yyyy-mm-dd')  ",null));
		oSelVector.add(new E2_ListSelectorStandard(oDatumBis,"JT_BG_AUSWERT.DATUM_AUSFUEHRUNG <= to_date('#WERT#','yyyy-mm-dd') ",null));
		

		
		// Storno-Kennzeichen
		oCBStorno = new MyE2_CheckBox("Stornierte Einträge anzeigen",oStyleCheckboxBorderless);
		oSelVector.add(new E2_ListSelectorStandard(oCBStorno, " ", "JT_BG_AUSWERT.ID_BG_STORNO_INFO IS NULL ") );
		
		// Streckenlager einblenden-Kennzeichen
		oCBInclusiveStrecke = new MyE2_CheckBox("inclusive Streckenlager", oStyleCheckboxBorderless);
		oSelVector.add(new E2_ListSelectorStandard(oCBInclusiveStrecke, "", "JT_BG_AUSWERT.ID_ADRESSE != " + bibSES.get_ID_ADRESSE_LAGER_STRECKE() ) );
		
		
		
	
		// neuer selektor mit OR-Verknüpfung
		oSelectorTypen = new B2_LALI_LIST_SelektorWelcheTypenEinblenden(oNavigationList,false);
		oSelectorTypen.selectAllCheckboxes(false);
		oSelectorTypen.selectCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KUNDEN, true);
		oSelVector.add (oSelectorTypen);
		

		
		// Selektor auf Eigenwaren
		oSelectorEW = new B2_LALI_LIST_Selektor_EW_Abrechenbar(oNavigationList);
		oSelectorEW.selectAllCheckboxes(false);
		oSelectorEW.selectCheckbox(ENUM_SELEKTOR_EWFW.EIGENWAREN, true);
		oSelVector.add(oSelectorEW);
		
		
		// Wareneingang/ -Ausgang
		arrWE = new String[][]{
				{new MyE2_String("-").CTrans(),""},
				{new MyE2_String("Wareneingang").CTrans(),"+1"},
				{new MyE2_String("Warenausgang").CTrans(),"-1"}};
		
		this.oSelWEWA = new MyE2_SelectField(arrWE, null, false);
		oSelVector.add(new E2_ListSelectorStandard(this.oSelWEWA, "JT_BG_AUSWERT.MENGENVORZEICHEN = #WERT# ", null, null));		
		
		// Jahr/Monatsauswahl
		
		//
		// ComboBox der Materialien
		//
		
		oSelJahre = new MyE2_SelectFieldWithParameters("SELECT DISTINCT to_char(JT_BG_AUSWERT.DATUM_AUSFUEHRUNG,'YYYY') , to_char(JT_BG_AUSWERT.DATUM_AUSFUEHRUNG,'YYYY') from "
				+ bibE2.cTO() + ".JT_BG_AUSWERT " 
				+ " WHERE id_mandant= "
				+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false, true,false, false);
		oSelJahre.RefreshComboboxFromSQL();
		oSelJahre.add_oActionAgent(new actionAgentJahre(true));
		
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
		oSelMonate.add_oActionAgent(new actionAgentJahre(false));
		

		//
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);

		
		
		Vector<MyString> vHelpLagerorte = new Vector<MyString>();
		vHelpLagerorte.add(new MyE2_String("Lagerorte: "));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("Die Lagerorte wählen aus, welche tatsächlichen, physikalischen Lagerorte für die Ermittlung der Bestände "));
		vHelpLagerorte.add(new MyE2_String("und der einzelnen Warenbewegungen zu Grunde liegen sollen."));
		vHelpLagerorte.add(new MyE2_String("."));
		vHelpLagerorte.add(new MyE2_String("Eigenwarenlager"));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("Eigenwarenlager sind die Lagerorte, denen in den Stammdaten kein fremder Besitzer zugeordnet wurde."));
		vHelpLagerorte.add(new MyE2_String("."));
		vHelpLagerorte.add(new MyE2_String("Fremdwarenlager"));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("Fremdwarenlager sind Lagerorte denen ein Fremd-Besitzer zugeordnet ist."));
		vHelpLagerorte.add(new MyE2_String("Auf diese Lager können nur Fremdwaren des Kunden, d.h. Waren die dem zugeordneten Besitzer gehören, gebucht werden."));
		vHelpLagerorte.add(new MyE2_String(""));
		vHelpLagerorte.add(new MyE2_String("."));
		vHelpLagerorte.add(new MyE2_String("inclusive Streckenmengen"));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("Auf der Strecke können Waren im eigenen Besitz oder im Fremdbesitz gefahren werden."));
		vHelpLagerorte.add(new MyE2_String("Falls man Mengen, die auf der Strecke anfallen, angezeigt und ausgewerten möchte, muss man diese "));
		vHelpLagerorte.add(new MyE2_String("Option auswählen."));
		vHelpLagerorte.add(new MyE2_String("."));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("Will man *NUR* die Bestände in den physikalischen Lagerorten ermitteln, muss man"));
		vHelpLagerorte.add(new MyE2_String("die Option 'Streckenmengen einbeziehen' löschen."));
		vHelpLagerorte.add(new MyE2_String("------------------------------"));
		vHelpLagerorte.add(new MyE2_String("."));
		vHelpLagerorte.add(new MyE2_String("Die Selektion der Lagerorte ist Grundlage für die Ermittlung der Bestandsdaten im Summenblock."));
		vHelpLagerorte.add(new MyE2_String("Die Beständen werden immer ermittelt auf der Selektion von Lagerorten und (Haupt-)Sorten"));
		
		
		
		
		ownHelpButton oHelp = new ownHelpButton(vHelpLagerorte,new Extent(700), new Extent(400),new E2_FontPlain(-2)); 
		
		
		//
		//
		//  GUI Definition
		//
		//

		// Innerhalb der einklappbaren Row eine Spalte definieren 
		MyE2_Column oColMain = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		this.add(oColMain);
		
		//
		// Gridstyle für innere Grids
		MutableStyle styleGridBorderInner = MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS();

		MutableStyle styleGridBorderOuter = new MutableStyle();
		styleGridBorderOuter.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		styleGridBorderOuter.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_10_0_10_0);
		styleGridBorderOuter.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		
		
		MyE2_Grid_100_percent  oGridAussen = new MyE2_Grid_100_percent(styleGridBorderOuter);
		oGridAussen.setSize(4);
		oGridAussen.setOrientation(MyE2_Grid.ORIENTATION_HORIZONTAL);
		oGridAussen.setRowHeight(0,new Extent(23));

		// das oGridMain an die Spalte hängen
		oColMain.add(oGridAussen);
		
		
		//
		// 1. Zeile Lagerauswahl
		//
		MyE2_Grid oGridLagerauswahl = new MyE2_Grid(3,styleGridBorderInner);
		oGridLagerauswahl.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		oGridAussen.add(oGridLagerauswahl,4,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		oGridLagerauswahl.add(new MyE2_Label(new MyE2_String("Lager:")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		oGridLagerauswahl.add(oSelMulti_Lager.get_oComponentForSelection(),2,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridLagerauswahl.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
//		oGridLagerauswahl.add(oSelectorLagerTypen.getCheckboxOf(ENUM_SELEKTOR_Lagertyp.STRECKE),2,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridLagerauswahl.add(oCBInclusiveStrecke,2,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		
		// 1. Spalte jetzt ein Grid für die Sorten
		MyE2_Grid oGridSorten = new MyE2_Grid(styleGridBorderInner);
		oGridAussen.add(oGridSorten,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);

		oGridSorten.setSize(4);
		oGridSorten.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		//Z1
		oGridSorten.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGridSorten.add(oSelMulti_Hauptsorte.get_oComponentForSelection(),3,E2_INSETS.I_0_0_5_0);
		// Z2
		oGridSorten.add(new MyE2_Label(new MyE2_String("Sorte:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridSorten.add(oSelMulti_Sorte.get_oComponentForSelection(),3, E2_INSETS.I_0_0_0_0);
		// Z3
		oGridSorten.add(new MyE2_Label(new MyE2_String("Sortenname:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridSorten.add(oSelArtikelName,3, E2_INSETS.I_0_0_0_0);
		// Z4
		oGridSorten.add(new MyE2_Label(new MyE2_String("Sortenbereich:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);	
		oGridSorten.add(this.oSortenBlockSelektor.get_oComponentForSelection(),3,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		
		
		
		MyE2_Grid oGridDatum = new MyE2_Grid(styleGridBorderInner);
		oGridAussen.add(oGridDatum,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		oGridDatum.setSize(4);
		
		oGridDatum.add(new MyE2_Label(new MyE2_String("Von:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGridDatum.add(oDatumVon,1,E2_INSETS.I_0_0_5_0);
		oGridDatum.add(new MyE2_Label(new MyE2_String(" bis:")),1,E2_INSETS.I_0_0_5_0);
		oGridDatum.add(oDatumBis,1,E2_INSETS.I_0_0_5_0);
		
		oGridDatum.add(new MyE2_Label(new MyE2_String("Zeitraum:")),1,E2_INSETS.I_0_0_5_0);
		oSelMonate.setWidth(new Extent(103));
		oSelJahre.setWidth(new Extent(103));
		oGridDatum.add(oSelMonate,1,E2_INSETS.I_0_0_0_0);
		oGridDatum.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGridDatum.add(oSelJahre,1,E2_INSETS.I_0_0_0_0);
		

		
		
		MyE2_Grid oGridDivers1 = new MyE2_Grid(styleGridBorderInner);
		oGridAussen.add(oGridDivers1,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		oGridDivers1.setSize(2);
		
		oGridDivers1.add(new MyE2_Label(new MyE2_String("Einheit:")),1,1,E2_INSETS.I_0_0_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oSelEinheit,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(new MyE2_Label(new MyE2_String("WA/WE:")),1,1,E2_INSETS.I_0_0_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oSelWEWA,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		oGridDivers1.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oCBStorno,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		oGridDivers1.add(new MyE2_Label(new MyE2_String(oDB_BasedSelektor.get_bIsFilled()?"Divers:":"")),1,1,E2_INSETS.I_0_5_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oDB_BasedSelektor.get_oComponentForSelection(100),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		oGridDivers1.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_5_5_0,Alignment.ALIGN_TOP);
		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());
		oGridDivers1.add(oRowPassivschalter,1,1,E2_INSETS.I_0_5_0_0,Alignment.ALIGN_TOP);
		
		
		
		
		E2_ExpandableRow oRowDivers2 = 	new E2_ExpandableRow(	new MyE2_String("Zusätzliche Auswahlkriterien: Warenbewegungsart,  Eigen-/Fremdwaren"), 
																new Border(1,new E2_ColorDDDark(),Border.STYLE_NONE),
															 	new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		// Standardmäßig verstecken
		oRowDivers2.get_oButtonClose().doActionPassiv();
		oGridAussen.add(oRowDivers2,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		MyE2_Grid oGridDivers2 = new MyE2_Grid(styleGridBorderInner);
//		MyE2_Grid oGridDivers2 = new MyE2_Grid(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oRowDivers2.add(oGridDivers2,E2_INSETS.I_0_0_0_0);
		oGridDivers2.setSize(4);
		
		oGridDivers2.add(new MyE2_Label(new MyE2_String("Zeige:")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		oGridDivers2.add(oSelectorTypen.get_oComponentForSelection(),1,1,E2_INSETS.I(10,2,0,0),Alignment.ALIGN_TOP);
		
		oGridDivers2.add(oSelectorEW.get_oComponentForSelection(),1,1,E2_INSETS.I_5_2_0_0,Alignment.ALIGN_TOP);	
		oGridDivers2.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		
		oGridDivers2.add(new MyE2_Label(new MyE2_String("Lagerorte:")),1,1,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP);	
		oGridDivers2.add(oSelectorLagerTypen.get_oComponentForSelection(),2,1,E2_INSETS.I_10_10_0_0,Alignment.ALIGN_TOP);
		oGridDivers2.add(oHelp,1,1,E2_INSETS.I_10_10_0_0,Alignment.ALIGN_TOP);
		
		
	
	}

	
	
	
	private class ownSelektorHelpGrid extends MyE2_Grid
	{
		public ownSelektorHelpGrid(int[] iSpalten)
		{
			super(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
			this.set_Spalten(iSpalten);
		}

	}
	
	
	


//	public MyE2_SelectField getSelectFieldLager(){
//		return oSelLager;
//	}
	
//	public MyE2_SelectField getSelectFieldSorte(){
//		return oSelArtikel;
//	}
//	
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
//	public String getIDSelectedLager() throws myException{
////		return oSelLager.get_ActualWert();
//		String sIDs = oSelMulti_Lager.get_WhereBlock();
//		return "-1";
//	}

	
	public String getWHERESortenbereich() throws myException {
		return oSortenBlockSelektor.get_WhereBlock();
		
	}
	
	public ArrayList<String[]> getArrayOfIds_Sortenbereich() throws myException {
		return oSortenBlockSelektor.get_ArrayOfSelectedValues();
	}
	

	/**
	 * gibt die aktuell selektierte Sortenid zurück
	 * @return
	 * @throws myException 
	 */
	public Vector<String> getIDSelectedSorte() throws myException{
		return oSelMulti_Sorte.get_SelectedValues();
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
	 * gibt die aktuell selektierte Sortenbezeichner zurück
	 * @return
	 * @throws myException 
	 */
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
		//oSelArtikel.get_ActualView();
	}
	
	
	/**
	 * Gibt die aktuell selektierte Hauptsorte zurück
	 * @return
	 * @throws myException 
	 */
	public Vector<String> getSelectedHauptsorte() throws myException {
		return oSelMulti_Hauptsorte.get_SelectedValues();
	}
	
	
	
	/**
	 * gibt die aktuell selektierten Lager zurück
	 * @return
	 * @throws myException
	 */
	public Vector<String> getSelectedLager() throws myException {
		return oSelMulti_Lager.get_SelectedValues();
	}
	
	
	
	/**
	 * gibt den aktuell selektierte Lagerbezeichner zurück wenn genau einer selektiert ist,
	 * sonst "Mehrfachselektion Lager";
	 * sonst Leerstring.
	 * 
	 * @return
	 * @throws myException 
	 */
	public String getSelectedLagerDesc() throws myException {
		Vector<String> v = oSelMulti_Lager.get_SelectedText();
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
	 * Gibt zurück ob der Selektor Einbeziehen von Fremdwarenlager gesetzt ist
	 * @author manfred
	 * @date   17.10.2013
	 * @return
	 */
	public boolean getShowFremdwarenlager(){
		return oSelectorLagerTypen.getStatusOf(ENUM_SELEKTOR_Lagertyp.FREMD);
	}
	
	/**
	 * Gibt zurück ob der Selektor Einbeziehen von Eigenwarenlager gesetzt ist 
	 * @author manfred
	 * @date   17.10.2013
	 * @return
	 */
	public boolean getShowEigenwarenlager(){
		return oSelectorLagerTypen.getStatusOf(ENUM_SELEKTOR_Lagertyp.EIGEN);
	}
	
	/**
	 * Gibt zurück ob der Selektor Einbeziehen von Strecken gesetzt ist
	 * @author manfred
	 * @date   17.10.2013
	 * @return
	 */
	public boolean getIncludeStrecke(){
		return oCBInclusiveStrecke.isSelected();
	}
	
	

	/**
	 * @return the oSelectorTypen
	 */
	public B2_LALI_LIST_SelektorWelcheTypenEinblenden get_SelectorTypen() {
		return oSelectorTypen;
	}




	private class actionAgentSelektionGebuchte extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// TODO Auto-generated method stub
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
			
			oThis.get_SelectorTypen().selectCheckbox(0, true);
			oThis.get_SelectorTypen().selectCheckbox(1, false);
			oThis.get_SelectorTypen().selectCheckbox(2, false);
			oThis.get_SelectorTypen().selectCheckbox(3, false);
			
			oThis.oCBStorno.setSelected(false);
			oThis.oSelVector.doActionPassiv();
		}

	}
	

	
	private class actionAgentJahre extends XX_ActionAgent {

		private boolean m_fieldIsJahre = false;
		
		public actionAgentJahre(boolean bFieldIsJahre) {
			m_fieldIsJahre = bFieldIsJahre;
		}
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
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
	

	
	
	
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
			
			Vector<String> vHauptartikel = oSelMulti_Hauptsorte.get_SelectedValues();
			Vector<String> vWhere = new Vector<String>();
			for (String s: vHauptartikel){
				vWhere.add("substr('00" + s.trim() + "',-2) " );
			}
			
			String sParamValues = "";
			String sParamValuesNamen = "";
			if (vHauptartikel != null && vHauptartikel.size() == 1) {
				sParamValues = " AND SUBSTR(ANR1,0,2) = ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
				
				// dropdown mit Sortennamen aktualisieren
				sParamValuesNamen = sParamValues;
				
			}else if (vHauptartikel != null && vHauptartikel.size() > 1) {
				sParamValues = " AND SUBSTR(ANR1,0,2) in ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
				
			}
			
			oThis.oSelMulti_Sorte.Refresh("#P1#", sParamValues);
			
			oThis.oSelArtikelName.SetParameter("#P1#", sParamValues);
			oThis.oSelArtikelName.set_ActiveInhalt_or_FirstInhalt("");
			oThis.oSelArtikelName.RefreshComboboxFromSQL();
			

			
		}
		
	}


	/**
	 * ActionAgent der die Dropdownliste der Sorte Sychron hält, 
	 * hängt am Dropdown der Sortennamen
	 * @author manfred
	 * @date   01.02.2013
	 */
	private class actionSynchronizeSorten extends XX_ActionAgent	{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
			
			String sValue = null;
			if (oExecInfo.get_MyActionEvent().getSource() instanceof MyE2_SelectFieldWithParameters ){
				// Aktueller Wert
				sValue = ((MyE2_SelectFieldWithParameters)oExecInfo.get_MyActionEvent().getSource()).get_ActualWert();

				// den Wert in der Sorten-Combobox setzen
				oThis.oSelMulti_Sorte.set_MemStringStatusSelektor(sValue);
			}
		}
	}

	
	/**
	 * ActionAgent der die Dropdown Liste mit den Sortennamen synchron hält.
	 * Hängt am Multiselect der Sorte
	 * @author manfred
	 *
	 */
	private class actionSynchronizeSortenName extends XX_ActionAgent	{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
			
			String sValue = null;
			Vector<String> vSorten = oThis.oSelMulti_Sorte.get_SelectedValues();
			
			if (vSorten != null && vSorten.size() == 1){
				oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue(vSorten.firstElement());
			} else {
				oThis.oSelArtikelName.set_ActiveValue_OR_FirstValue("");
			}
		}
	}

	
	
	private class actionAgentLagerSelektion extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
	
			// Wenn ein Eintrag gewählt ist, dann müssen alle Einträge aus den Lager-Orten gesetzt sein.
			if (oThis.oSelMulti_Lager.get_SelectedValues().size() > 0){
				oThis.oSelectorLagerTypen.selectAllCheckboxes(true);
				oThis.oSelectorLagerTypen.set_bEnabled(false);
				
				
				// wenn ein Lager gewählt ist, dann schauen, ob es das streckenlager ist. Wenn ja, dann die Checkbox aktivieren, ansonsten ausschalten und 
				// auf false stellen
				oThis.oCBInclusiveStrecke.setSelected(false);

				for (String lagerid : oThis.oSelMulti_Lager.get_SelectedValues()){
					if (lagerid.equals(bibSES.get_ID_ADRESSE_LAGER_STRECKE())){
						// disable
						oThis.oCBInclusiveStrecke.setSelected(true);
					}
				}
				
			} else {
				oThis.oSelectorLagerTypen.set_bEnabled(true);
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
			B2_LALI_LIST_Selector.this.oSortenBlockSelektor.LEER_MACHEN();
		}
	}
	
	
	
	private class actionClearSortenSelektoren extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LALI_LIST_Selector oThis = B2_LALI_LIST_Selector.this;
			oThis.oSelMulti_Hauptsorte.set_MemStringStatusSelektor("");
			oThis.oSelMulti_Sorte.set_MemStringStatusSelektor("");
			oThis.oSelArtikelName.set_ActiveInhalt_or_FirstInhalt("");
		}
		
	}
	
	
	
	//2012-10-15: mehrfachselektion der sorten/sortenbereiche
	private class SelectorSortenVonBis_Multi extends E2_ListSelectorMultiDropDown_von_bis
	{
		
		
		public SelectorSortenVonBis_Multi() throws myException
		{
			super(bibALL.ReplaceTeilString(B2_LALI_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#",""),  
					"JT_BG_AUSWERT.ID_ARTIKEL IN (SELECT A.ID_ARTIKEL FROM " +bibE2.cTO()+".JT_ARTIKEL A WHERE A.ANR1>='#WERT1#' AND A.ANR1<='#WERT2#')");
			
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
//			this.get_grid4Anzeige().set_Spalten(ATOM_LAG_LIST_Selector.this.iSpaltenGridMain);
			this.get_grid4Anzeige().setSize(5);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_von(),1,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(new MyE2_Label(new MyE2_String(" bis ")),2,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis_bis(),1,E2_INSETS.I_0_0_0_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),1,E2_INSETS.I_2_0_0_0);
			
			this.get_oSelFieldBasis_von().setWidth(new Extent(136));
			this.get_oSelFieldBasis_bis().setWidth(new Extent(136));
			
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
				B2_LALI_LIST_Selector.this.oSelMulti_Sorte.set_MemStringStatusSelektor("");
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
				cQuery = bibALL.ReplaceTeilString(B2_LALI_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#","");
			}
			else
			{
				cQuery = bibALL.ReplaceTeilString(B2_LALI_LIST_Selector.cBasisQueryMultiBereichSelektor+" ORDER BY 1","#WHERE#","  WHERE JT_ARTIKEL.ANR1 LIKE '"+cSortenGruppe+"%'");
			}
			
			String[][] cValues = E2_ListSelectorMultiDropDown_von_bis.baue_SelectFieldArray(cQuery);
			
			this.LEER_MACHEN();   //alles resetten
			
			this.get_oSelFieldBasis_von().set_ListenInhalt(cValues, false);
			this.get_oSelFieldBasis_bis().set_ListenInhalt(cValues, false);
		}
		
	}

	
	
	
	private class ownHelpButton extends MyE2_PopUpButtonWithHelpWindow
	{
		public ownHelpButton(Vector<MyString> vHelpStrings, Extent ExtWidth, Extent ExtHeight, Font oFontForHelpText)
		{
			super(vHelpStrings, ExtWidth, ExtHeight, oFontForHelpText);
		}

		@Override
		public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException
		{
			return new ownBasicContainer();
		}
		private class ownBasicContainer extends E2_BasicModuleContainer
		{
		}
	}
	
	

}
