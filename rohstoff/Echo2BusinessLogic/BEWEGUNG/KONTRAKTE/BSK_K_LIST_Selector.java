package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorDatePopup;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSK_K_LIST_Selector extends E2_ListSelectorContainer 
{

	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterADR = new Component_USER_DROPDOWN_NEW(false,120);
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterANG = new Component_USER_DROPDOWN_NEW(false,120);
	private Component_USER_DROPDOWN_NEW		oSelectHaendler = 		new Component_USER_DROPDOWN_NEW(false,120);
	
	private MyE2_SelectField				oSelectLand = null;
	private MyE2_SelectField				oSelectAdressKlasse = null;

	
	private MyE2_CheckBox					oCheckBoxAbgeschlossen = new MyE2_CheckBox(new MyE2_String("Gedruckte"), new MyE2_String("Zeigt nur Kontrakte, die bereits eine Buchungsnummer haben"));
	private MyE2_CheckBox					oCheckBoxOffen = new MyE2_CheckBox(new MyE2_String("Ungedruckte"), new MyE2_String("Zeigt nur Kontrakte, die noch KEINE eine Buchungsnummer haben"));
	private MyE2_CheckBox					oCheckBoxHatOffenePositionen = new MyE2_CheckBox(new MyE2_String("Hat offene Pos."), new MyE2_String("Zeigt nur Kontrakte, die noch mindestens EINE offene Position haben"));
	private MyE2_CheckBox  					oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Zeige gelöschte"), new MyE2_String("Zeigt auch gelöschte Kontrakte und Positionen") );
	
	private BSK_K_LIST_Selektor_MonatJahr   		oSelektor_monat_jahr = new BSK_K_LIST_Selektor_MonatJahr(40,50);
	private BSK_K_LIST_Selektor_SorteKurzSorteLang  oSelSorten = new BSK_K_LIST_Selektor_SorteKurzSorteLang(60, 100, 2);
	
	
	//2015-07-13: neuer Selektor Druckdatum von-bis
	private BSK_K_LIST_Selector_datum_vom_bis       selDruckDatumZeitraum = new BSK_K_LIST_Selector_datum_vom_bis();


	private SELECTOR_COMPONENT_FirmenAuswahl   oSelKundenMitKontrakten = null;

	
	public BSK_K_LIST_Selector(E2_NavigationList oNavigationList, String cBelegtyp, String cMODUL_KENNER) throws myException
	{
		//super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		super();
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		this.oSelectLand = new MyE2_SelectField(
				"select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false,new Extent(120));
		
		
		//die checkboxen Gedruckt/ungedruckt mit radio-function verbinden
		ActionAgent_RadioFunction_CheckBoxList  oRadioButton = new ActionAgent_RadioFunction_CheckBoxList(true);
		oRadioButton.add_CheckBox(this.oCheckBoxOffen);
		oRadioButton.add_CheckBox(this.oCheckBoxAbgeschlossen);
		
		
		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
										"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
										" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
										" (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE NVL(DELETED,'N')='N' AND VORGANG_TYP="
										+ bibALL.MakeSql(cBelegtyp)+" ) "+
										" ORDER BY NAMEN";
		
		oSelKundenMitKontrakten = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor,50, 160, this.oSelVector, new MyE2_String("Firma:"));
		this.oSelKundenMitKontrakten.REFRESH_KundenSelektor();

		
		this.oSelectAdressKlasse = new MyE2_SelectField(
				"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
				false,true,false,false);

		this.oSelectAdressKlasse.setWidth(new Extent(100));

		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR," (JT_ADRESSE.ID_USER = #WERT# OR JT_ADRESSE.ID_USER_ERSATZ = #WERT#) ", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterANG,"JT_VKOPF_KON.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectHaendler,"JT_VKOPF_KON.ID_USER_ANSPRECH_INTERN = #WERT#", null, null));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_VKOPF_KON.LAENDERCODE = '#WERT#'", null, null));

		oSelVector.add(oSelektor_monat_jahr);
		oSelVector.add(oSelSorten);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_VKOPF_KON.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from "+bibE2.cTO()+".JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxAbgeschlossen,"NVL(JT_VKOPF_KON.ABGESCHLOSSEN,'N')='Y'",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxOffen,"NVL(JT_VKOPF_KON.ABGESCHLOSSEN,'N')='N'",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxHatOffenePositionen,
				"JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
						" WHERE JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON AND   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'   AND  NVL(JT_VPOS_KON.DELETED,'N')='N' " +
						"    AND JT_VPOS_KON.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"' )",""));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,"","  NVL(JT_VKOPF_KON.DELETED,'N')='N'"));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitKontrakten.get_oSelKunden()," NVL(JT_VKOPF_KON.ID_ADRESSE,0)=#WERT#", null, null));

		//2012-04-16: neuer selektor auf das feld "DATUM_ERSTELLT"
		ownSelektorGeaendertAm  oSelDatumGeaendert = new ownSelektorGeaendertAm();
		oSelVector.add(oSelDatumGeaendert);
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		oSelVector.add(oDB_BasedSelektor);
		

		//2015-07-13: neuer Selektor Druckdatum von-bis
		oSelVector.add(this.selDruckDatumZeitraum);

		MyE2_Grid oGrid = new MyE2_Grid(2, 0);
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		
		oGrid.add(new ownHelpGrid(120, 100, new MyE2_Label(new MyE2_String("Betreuer(1/2) Firma:")), oSelectMitarbeiterADR), E2_INSETS.I_0_0_10_0);
		oGrid.add(new ownHelpGrid(120, 100, new MyE2_Label(new MyE2_String("Händler:")), 			oSelectHaendler), E2_INSETS.I_0_0_10_0);
		
		oGrid.add(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Land:")), 				this.oSelectLand), E2_INSETS.I_0_0_10_0);
		oGrid.add(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Erfasser:")), 			oSelectMitarbeiterANG), E2_INSETS.I_0_0_10_0);

		oGrid.add(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Aktiv in:")), 			oSelektor_monat_jahr.get_oComponentForSelection()), E2_INSETS.I_0_0_10_0);
		oGrid.add(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Adr.Klas:")), 			this.oSelectAdressKlasse), E2_INSETS.I_0_0_10_0);

		oGrid.add(new ownHelpGrid(110, 110, this.oCheckBoxAbgeschlossen, this.oCheckBoxOffen), 			E2_INSETS.I_0_0_10_0);
		oGrid.add(this.oSelSorten.get_oComponentForSelection(), 										E2_INSETS.I_0_0_10_0);
		
		oGrid.add(new ownHelpGrid(110, 110, this.oCheckBoxHatOffenePositionen, this.oCB_ShowDeletedRows), 	E2_INSETS.I_0_0_10_0);
		oGrid.add(this.oSelKundenMitKontrakten, 															E2_INSETS.I_0_0_10_0);
		
		oGrid.add(oSelDatumGeaendert.get_oComponentForSelection(),										 E2_INSETS.I_0_0_10_0);
		//2015-07-13: neuer Selektor Druckdatum von-bis
		oGrid.add(this.selDruckDatumZeitraum.get_oComponentForSelection(),		   						 E2_INSETS.I_0_0_10_0);

		
		
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), 		E2_INSETS.I_0_0_10_0);
		
		//vorselektion
		this.oCheckBoxHatOffenePositionen.setSelected(true);
		
		this.add(oGrid);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}



	public MyE2_CheckBox get_oCB_ShowDeletedRows()
	{
		return oCB_ShowDeletedRows;
	}

	
	public BSK_K_LIST_Selektor_SorteKurzSorteLang get_oSelSorten() 
	{
		return oSelSorten;
	}

	
	
	//neue selektor-klasse zu definieren alle kontrakte von einem tag
	private class ownSelektorGeaendertAm extends E2_ListSelectorDatePopup
	{

		public ownSelektorGeaendertAm() throws myException
		{
			super(true, true, new Extent(80), new MyE2_String("Erstellt: "), new Extent(50), new Extent(130));
			this.get_vWeitereAktionenInFront().add(new ownActionAgentSchalteNurOffeneEin());
			this.get_oDatumsFeld().get_oTextField().setToolTipText(new MyE2_String("Zeigt alle Kontrakte, die mindestens eine Position enthalten, deren Änderungsstempel das angegebene Datum ist").CTrans());
			this.get_oDatumsFeld().get_oButtonCalendar().setToolTipText(new MyE2_String("Zeigt alle Kontrakte, die mindestens eine Position enthalten, deren Änderungsstempel das angegebene Datum ist").CTrans());
			this.get_oDatumsFeld().get_oButtonEraser().setToolTipText(new MyE2_String("Zeigt alle Kontrakte, die mindestens eine Position enthalten, deren Änderungsstempel das angegebene Datum ist").CTrans());
		}

		
		@Override
		public String get_WhereBlock() throws myException
		{
			String cWhereString = "";
			
			String cDatumsText = this.get_oDatumsFeld().get_oDBFormatedDateFromTextField();
			
		    if (S.isFull(cDatumsText))
		    {
		    	cWhereString = "JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT P.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON P WHERE TO_CHAR(P.LETZTE_AENDERUNG,'YYYY-MM-DD')='"+cDatumsText+"')";
		    }
			return cWhereString;
		}
		
	}

	private class ownActionAgentSchalteNurOffeneEin extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSK_K_LIST_Selector.this.oCheckBoxHatOffenePositionen.setSelected(true);
		}
	}
	
	
	
	private class ownHelpGrid extends MyE2_Grid
	{

		public ownHelpGrid(int iSpalte1, int iSpalte2, Component  oComp1, Component oComp2)
		{
			super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			this.add(oComp1,E2_INSETS.I_0_0_0_0);
			this.add(oComp2,E2_INSETS.I_0_0_0_0);
			this.setColumnWidth(0,new Extent(iSpalte1));
			this.setColumnWidth(1,new Extent(iSpalte2));
		}
		
	}
}
