package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_ListSelector extends E2_ListSelectorContainer 
{

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 			oSelVector = null;
	
	private SELECTOR_COMPONENT_FirmenAuswahl   		oSelKundenMitPositionen = null;

	private MyE2_CheckBox       					oCB_ShowWareneingang = 	new MyE2_CheckBox(new MyE2_String("NUR Gutschrift"));
	private MyE2_CheckBox       					oCB_ShowWarenausgang = 	new MyE2_CheckBox(new MyE2_String("NUR Rechnung"));
	
	private MyE2_CheckBox  							oCB_ShowDeletedRows = 	new MyE2_CheckBox(new MyE2_String("Zeige Gelöschte"));
	
	
	private ActionAgent_RadioFunction_CheckBoxList oRB_CB = new ActionAgent_RadioFunction_CheckBoxList(true);
	
	public BSFP_ListSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		//super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		super();
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		// verhindern, dass wareneingang und warenausgang gleichzeitig moeglich ist 
		this.oRB_CB.add_CheckBox(this.oCB_ShowWareneingang);
		this.oRB_CB.add_CheckBox(this.oCB_ShowWarenausgang);
		
		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
										"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
										" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
										" (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG IS NULL  AND "+
												"   NVL(DELETED,'N')='N')" +
										" ORDER BY NAMEN";

		
		oSelKundenMitPositionen = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor,40, 150, this.oSelVector, new MyE2_String("Firma"));
		this.oSelKundenMitPositionen.REFRESH_KundenSelektor();
		
		
		//String cSQL_ERZEUGT_VON = "SELECT NVL(VORNAME,'-')||' '||NVL(NAME1,'-') AS A,KUERZEL AS B FROM "+bibE2.cTO()+".JD_USER WHERE NVL(IST_VERWALTUNG,'N')='Y' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1";
		//2012-10-12: neue darstellung, standard-konform
		//String cSQL_ERZEUGT_VON = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')' AS A,KUERZEL AS B FROM "+bibE2.cTO()+".JD_USER WHERE NVL(IST_VERWALTUNG,'N')='Y' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1";
		
		String cSQL_ERZEUGT_VON_WHERE = "JT_VPOS_RG.ERZEUGT_VON='#WERT#'";
		String cSQL_GEAENDERT_VON_WHERE = "JT_VPOS_RG.GEAENDERT_VON='#WERT#'";
		
		//2012-10-12: mehrfach-selektion der mitarbeiter
//		E2_ListSelectorStandard oSelErzeugtVon = 	new E2_ListSelectorStandard(cSQL_ERZEUGT_VON,cSQL_ERZEUGT_VON_WHERE,true, new MyE2_String("Angelegt:"), new Integer(12), new Integer(100));
//		E2_ListSelectorStandard oSelGeaendertVon = 	new E2_ListSelectorStandard(cSQL_ERZEUGT_VON,cSQL_GEAENDERT_VON_WHERE,true, new MyE2_String("letzte Änderung:"), new Integer(12), new Integer(100));
		ownSelectorMultiDropdownMitarbeiter oSelErzeugtVon = new ownSelectorMultiDropdownMitarbeiter(cSQL_ERZEUGT_VON_WHERE,new MyE2_String("Angelegt:"));
		ownSelectorMultiDropdownMitarbeiter oSelGeaendertVon = new ownSelectorMultiDropdownMitarbeiter(cSQL_GEAENDERT_VON_WHERE, new MyE2_String("letzte Änderung:"));
		
		
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,""," NVL(JT_VPOS_RG.DELETED,'N')='N'"));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowWareneingang,  " NVL(JT_VPOS_RG.LAGER_VORZEICHEN,0)=1",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowWarenausgang,  " NVL(JT_VPOS_RG.LAGER_VORZEICHEN,0)=-1",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitPositionen.get_oSelKunden()," NVL(JT_VPOS_RG.ID_ADRESSE,0)=#WERT#", null, null));
		
		oSelVector.add(oSelErzeugtVon);
		oSelVector.add(oSelGeaendertVon);
		
		
		//2016-08-08: selektor leistungdatum von-bis
		ownSelektorLeistungsdatum seldatum = new ownSelektorLeistungsdatum();
		oSelVector.add(seldatum);
		
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		oSelVector.add(oDB_BasedSelektor);
		
		
		
		MyE2_Grid oGrid = new MyE2_Grid(10,0);
		
		Insets oIN2 = new Insets(0,2,20,2);
		
		oGrid.add(this.oCB_ShowWareneingang,1,oIN2);
		oGrid.add(this.oCB_ShowWarenausgang,1,oIN2);
		oGrid.add(this.oCB_ShowDeletedRows,1,oIN2);
		oGrid.add(this.oSelKundenMitPositionen,1,oIN2);
		oGrid.add(oSelErzeugtVon.get_oComponentForSelection(),1,oIN2);
		oGrid.add(oSelGeaendertVon.get_oComponentForSelection(),1,oIN2);

		//2016-08-08: selektor leistungdatum von-bis
		oGrid.add(new RB_lab()._tr("Leistungdatum"),new RB_gld()._ins(0,2,2,2)._left_mid());
		oGrid.add(seldatum.get_oComponentWithoutText(),1,oIN2);
		
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));
		
		oGrid.add(new E2_ComponentGroupHorizontal(0,this.oSelVector.get_AktivPassivComponent(),E2_INSETS.I_0_0_0_0));
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
	
	
	//2012-10-12: multiselektion fuer die Bearbeiter
	/**
	 * test
	 * @author martin
	 *
	 */
	private class ownSelectorMultiDropdownMitarbeiter extends E2_ListSelectorMultiDropDown
	{
		private MyE2_String cBeschriftung = null;
		
		public ownSelectorMultiDropdownMitarbeiter(String WhereString, MyE2_String Beschriftung) throws myException
		{
			super(
					"SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')' AS A,KUERZEL AS B FROM "+
						bibE2.cTO()+".JD_USER WHERE NVL(IST_VERWALTUNG,'N')='Y' AND ID_MANDANT="+
						bibALL.get_ID_MANDANT()+" ORDER BY 1", WhereString);
			
			this.cBeschriftung = Beschriftung;
			this.get_oSelFieldBasis().setWidth(new Extent(100));
			this.fill_Grid4AnzeigeStatusSingle();
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException
		{
			return new ownE2_BasicModuleContainer();
		}
		private class ownE2_BasicModuleContainer extends E2_BasicModuleContainer
		{
		}
		
		@Override
		public void fill_Grid4AnzeigeStatusMulti()
		{
			this.get_grid4Anzeige().removeAll();
			this.get_grid4Anzeige().setSize(3);
			MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
			oLab.setWidth(this.get_oSelFieldBasis().getWidth());
			oLab.get_ownLabel().setFont(new E2_FontBold(-2));
			this.get_grid4Anzeige().add(new MyE2_Label(this.cBeschriftung),E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(oLab,E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
			oLab.get_ownLabel().setToolTipText(this.get_oButtonOpenMultiSelectPopup().getToolTipText());
			
		}

		@Override
		public void fill_Grid4AnzeigeStatusSingle()
		{
			this.get_grid4Anzeige().removeAll();
			this.get_grid4Anzeige().setSize(3);
			this.get_grid4Anzeige().add(new MyE2_Label(this.cBeschriftung),E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oSelFieldBasis(),E2_INSETS.I_0_0_5_0);
			this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);

		}

	}

	

	/**
	 * neuer selektor leistungsdatum von-bis
	 * @author martin
	 *
	 */
	private class ownSelektorLeistungsdatum extends E2_SelektorDateFromTo_NG {

		public ownSelektorLeistungsdatum() throws myException	{
			super();
			this.INIT_Selector(null, VPOS_RG.ausfuehrungsdatum.tnfn(), VPOS_RG.ausfuehrungsdatum.tnfn(), new Extent(80));
		}
	}

	

}
