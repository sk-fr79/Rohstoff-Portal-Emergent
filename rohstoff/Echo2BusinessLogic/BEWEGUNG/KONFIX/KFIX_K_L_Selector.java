package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorDatePopup;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Selector_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_L_Selector extends E2_ListSelectorContainer 
{

	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterADR = 	new Component_USER_DROPDOWN_NEW(false,120);
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterANG = 	new Component_USER_DROPDOWN_NEW(false,120);
	private Component_USER_DROPDOWN_NEW		oSelectHaendler = 			new Component_USER_DROPDOWN_NEW(false,120);
	
	private MyE2_SelectField				oSelectLand = 				null;
	private MyE2_SelectField				oSelectAdressKlasse = 		null;

	
	private RB_cb							cbBoxOffen = 				new RB_cb()._txt_trans("Offene Kon.")._fo_s_plus(-2)._tooltxt_tr("Kontrakte, die noch nicht abgeschlossen sind, einblenden");
	private RB_cb							cbAbgeschlossen = 			new RB_cb()._txt_trans("Abgeschl.")._fo_s_plus(-2)._tooltxt_tr("Kontrakte, die bereits abgeschlossen sind, einblenden");

	private RB_cb							cbHatOffenePositionen = 	new RB_cb()._txt_trans("Offene Positionen")._fo_s_plus(-2)._tooltxt_tr("Zeigt nur Kontrakte, die noch mindestens EINE offene Position haben");
	private RB_cb							cbKeinOffenePositionen = 	new RB_cb()._txt_trans("Keine offenen Pos.")._fo_s_plus(-2)._tooltxt_tr("Zeigt nur Kontrakte, keine offene Position haben");

	private RB_cb  							cbShowActive = 				new RB_cb()._txt_trans("Zeige aktive")._fo_s_plus(-2)._tooltxt_tr("Zeigt aktive Kontrakte");
	private RB_cb  							cbShowDeleted = 			new RB_cb()._txt_trans("Zeige gelöschte")._fo_s_plus(-2)._tooltxt_tr("Zeigt gelöschte Kontrakte und Positionen");

	private RB_cb 							cbNormalKontrakte = 		new RB_cb()._txt_trans("Std.-Kontrakte")._fo_s_plus(-2)._tooltxt_tr("Blendet Standard-Kontrakte ein"); 
	private RB_cb							cbFixiKontrakte =			new RB_cb()._txt_trans("Fix.-Kontrakte")._fo_s_plus(-2)._tooltxt_tr("Blendet Fixierungs-Kontrakte ein");
	
	
	private KFIX_K_L_Selektor_MonatJahr  	oSelektor_monat_jahr = new KFIX_K_L_Selektor_MonatJahr(40,50);
	private ownSelectSorten  				oSelSorten = new ownSelectSorten(60, 100, 2);
	
	
	//2015-07-13: neuer Selektor Druckdatum von-bis
	private KFIX_K_L_Selector_datum_vom_bis       selDruckDatumZeitraum = new KFIX_K_L_Selector_datum_vom_bis();


	private ownSelectFirmenAuswahl					oSelKundenMitKontrakten = null;

	
	public KFIX_K_L_Selector(E2_NavigationList oNavigationList, VORGANGSART belegtyp, String cMODUL_KENNER) throws myException
	{
		//super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		super();
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		this.oSelectLand = new MyE2_SelectField(
				"select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false,new Extent(120));
		
		
//		//die checkboxen Gedruckt/ungedruckt mit radio-function verbinden
//		ActionAgent_RadioFunction_CheckBoxList  oRadioButton = new ActionAgent_RadioFunction_CheckBoxList(true);
//		oRadioButton.add_CheckBox(this.oCheckBoxOffen);
//		oRadioButton.add_CheckBox(this.oCheckBoxAbgeschlossen);
		
		
		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
										"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
										" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
										" (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE NVL(DELETED,'N')='N' AND VORGANG_TYP="
										+ bibALL.MakeSql(belegtyp.get_DBValue())+" ) "+
										" ORDER BY NAMEN";
		
		oSelKundenMitKontrakten = new ownSelectFirmenAuswahl(cQueryKundenSelektor,this.oSelVector);
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
		
		//ausschliessende paare
		oSelVector.add(new E2_ListSelectorStandard(this.cbAbgeschlossen,		"",	new vgl_YN(VKOPF_KON.abgeschlossen, false).s()));
		oSelVector.add(new E2_ListSelectorStandard(this.cbBoxOffen,				"",	new vgl_YN(VKOPF_KON.abgeschlossen, true).s()));

		String csqlRaw = "((SELECT COUNT(*)  FROM  JT_VPOS_KON VP "
										+ " INNER JOIN JT_VPOS_KON_TRAKT VPK  ON (VPK.ID_VPOS_KON=VP.ID_VPOS_KON)"
										+ " WHERE VP.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON  AND NVL(VP.DELETED,'N')='N' AND NVL(VPK.ABGESCHLOSSEN,'N')='##')>0)";

		String cSql1 = bibALL.ReplaceTeilString(csqlRaw, "##", "Y");
		String cSql2 = bibALL.ReplaceTeilString(csqlRaw, "##", "N");

		oSelVector.add(new E2_ListSelectorStandard(this.cbHatOffenePositionen,	"",	cSql2));
		oSelVector.add(new E2_ListSelectorStandard(this.cbKeinOffenePositionen,	"",	cSql1));
		
		//S.I.!
//		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxHatOffenePositionen,
//				"(JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
//						" WHERE JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON AND   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'   AND  NVL(JT_VPOS_KON.DELETED,'N')='N' " +
//						"    AND JT_VPOS_KON.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"' ) OR JT_VKOPF_KON.ID_VKOPF_KON NOT in (SELECT DISTINCT id_vkopf_kon from JT_VPOS_KON))",""));
		 
//		oSelVector.add(new E2_ListSelectorStandard(this.cbHatOffenePositionen,
//				"(JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
//						" WHERE JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON AND   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'   AND  NVL(JT_VPOS_KON.DELETED,'N')='N' " +
//						"    AND JT_VPOS_KON.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"' ) )",""));
		 
		
		oSelVector.add(new E2_ListSelectorStandard(this.cbNormalKontrakte, "" ,	new vgl_YN(VKOPF_KON.ist_fixierung, true).s()));
		oSelVector.add(new E2_ListSelectorStandard(this.cbFixiKontrakte, "", 	new vgl_YN(VKOPF_KON.ist_fixierung, false).s()));
		
		oSelVector.add(new E2_ListSelectorStandard(this.cbShowActive,	"",	"NVL(JT_VKOPF_KON.DELETED,'N')='Y'"));
		oSelVector.add(new E2_ListSelectorStandard(this.cbShowDeleted,"",	"NVL(JT_VKOPF_KON.DELETED,'N')='N'"));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitKontrakten.get_selectKunden()," NVL(JT_VKOPF_KON.ID_ADRESSE,0)=#WERT#", null, null));

		//2012-04-16: neuer selektor auf das feld "DATUM_ERSTELLT"
		ownSelektorGeaendertAm  oSelDatumGeaendert = new ownSelektorGeaendertAm();
		oSelVector.add(oSelDatumGeaendert);
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		oSelVector.add(oDB_BasedSelektor);
		

		//2015-07-13: neuer Selektor Druckdatum von-bis
		oSelVector.add(this.selDruckDatumZeitraum);

		RB_gld   lo = 	new RB_gld()._left_top()._ins(0, 0, 10, 0);
		RB_gld   lo2 = 	new RB_gld()._left_top()._ins(0, 3, 10, 0);
		RB_gld   lo3 = 	new RB_gld()._left_top()._ins(0, 2, 10, 2);
		
		RB_gld   ld2l = new RB_gld()._ins(0, 3, 3, 3);
		RB_gld   ld2lext = new RB_gld()._span_r(2)._ins(0, 2, 10, 2);
		
		
		E2_Grid oGrid = new E2_Grid()._s(2);
		oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
		oGrid	._a(new ownHelpGrid(90, 100, new MyE2_Label(new MyE2_String("Betreuer(1/2):")), 	oSelectMitarbeiterADR), lo)
				._a(new ownHelpGrid(90, 100, new MyE2_Label(new MyE2_String("Händler:")), 			oSelectHaendler), lo)
		
				._a(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Land:")), 				this.oSelectLand), lo)
				._a(new ownHelpGrid(70, 100, new MyE2_Label(new MyE2_String("Erfasser:")), 			oSelectMitarbeiterANG), lo)

				._a(new ownHelpGrid(65, 80, new MyE2_Label(new MyE2_String("Aktiv in:")), 			oSelektor_monat_jahr.get_oComponentForSelection()), lo)
				._a(new ownHelpGrid(65, 80, new MyE2_Label(new MyE2_String("Adr.Klas:")), 			this.oSelectAdressKlasse), lo)

				._a(new E2_Grid()._s(1)._bo_dd()._a(this.cbBoxOffen,			ld2l)._a(this.cbAbgeschlossen, 		ld2l), ld2lext)
				._a(new E2_Grid()._s(1)._bo_dd()._a(this.cbShowActive,			ld2l)._a(this.cbShowDeleted, 		ld2l), ld2lext)
				._a(new E2_Grid()._s(1)._bo_dd()._a(this.cbNormalKontrakte,		ld2l)._a(this.cbFixiKontrakte, 		ld2l), ld2lext)
				._a(new E2_Grid()._s(1)._bo_dd()._a(this.cbHatOffenePositionen,	ld2l)._a(this.cbKeinOffenePositionen,ld2l), ld2lext)
						
//		
//				._a(this.oCheckBoxHatOffenePositionen, 	lo2)
//				._a(this.oCB_ShowDeletedRows, 			lo2)
				
		
				._a(this.oSelSorten.get_oComponentForSelection(), lo)
				._a(this.oSelKundenMitKontrakten, lo)
		
				._a(oSelDatumGeaendert.get_oComponentForSelection(), lo3)
				._a(this.selDruckDatumZeitraum.get_oComponentForSelection(), lo)
	
				._a(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), lo3);
		
		//vorselektion
		this.cbBoxOffen.setSelected(true);
		this.cbAbgeschlossen.setSelected(true);
		this.cbHatOffenePositionen.setSelected(true);
		this.cbFixiKontrakte.setSelected(true);
		this.cbNormalKontrakte.setSelected(true);
		this.cbShowActive.setSelected(true);
		
		
		this.add(oGrid);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}



	public CheckBox get_oCB_ShowDeletedRows() {
		return cbShowDeleted;
	}

	
	public KFIX_K_L_Selektor_SorteKurzSorteLang get_oSelSorten() 
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
			KFIX_K_L_Selector.this.cbHatOffenePositionen.setSelected(true);
		}
	}
	

	private class ownHelpGrid extends E2_Grid
	{

		public ownHelpGrid(int iSpalte1, int iSpalte2, Component  oComp1, Component oComp2)
		{
			super();
			
			int[] ibreite = {iSpalte1, iSpalte2};
			this._setSize(ibreite)._gld(new RB_gld()._ins(2, 2, 2, 2));
			
			this._a_lm(oComp1)._a_lm(oComp2);
		}
	}
	
	
	private class ownSelectFirmenAuswahl extends Selector_FirmenAuswahl {
		/**
		 * @param Query4AdressListe
		 * @param SelVector
		 */
		public ownSelectFirmenAuswahl(String Query4AdressListe, E2_SelectionComponentsVector SelVector) {
			super(Query4AdressListe, SelVector);
			this.get_selectKunden().setWidth(new Extent(170));
			this._clear()
				._setSize(60, 20, 170)._a(new RB_lab()._tr("Kunden: "), new RB_gld()._ins(0, 2, 0, 2)._left_top())
				._a(this.get_button_refresh(), new RB_gld()._ins(0, 2, 0, 2)._left_top())
				._a(this.get_selectKunden(), new RB_gld()._ins(0, 2, 0, 2)._left_top())
				;
		}
	}
	
	
	private class ownSelectSorten extends KFIX_K_L_Selektor_SorteKurzSorteLang {
		public ownSelectSorten(int iWidthSorteKurz, int iWidthSorteLang, int iLenSorteKurzZeichen) throws myException {
			super(iWidthSorteKurz, iWidthSorteLang, iLenSorteKurzZeichen);
			
			this.get_gridSelComponent()
				._clear()
				._setSize(80, 40, 130)._a(new RB_lab()._tr("Sorten: "), new RB_gld()._ins(0, 2, 0, 2)._left_top())
				._a(this.get_selectSorteKurz(), new RB_gld()._ins(0, 2, 3, 2)._left_top())
				._a(this.get_selectSorteExact(), new RB_gld()._ins(0, 2, 0, 2)._left_top())
			;
		}
	}
	
}
