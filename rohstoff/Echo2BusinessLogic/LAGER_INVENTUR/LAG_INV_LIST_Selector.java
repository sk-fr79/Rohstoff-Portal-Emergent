package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
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
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory_Extended;

public class LAG_INV_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	private MyE2_SelectField 	oSelArtikel = null;
	private MyE2_Button      oPBRefreshMaterial = null;
	private MyE2_CheckBox    oCBZeigeAusgeglichene = null;

//	private LAG_SelectFieldOwnLAGER 		oSelLager = null;
	private LAG_LagerSelectField_Factory_Extended oSelLagerFactory = null;
	private MyE2_SelectField				oSelLager = null;
	
	public LAG_INV_LIST_Selector(E2_NavigationList oNavigationList, String cMODUL_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		this.initSelector(oNavigationList, cMODUL_KENNER);
	}

	
	protected void initSelector(E2_NavigationList oNavigationList, String cMODUL_KENNER)
			throws myException {
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		//
		// CombobBox der Läger
		//
//		oSelLager = new LAG_SelectFieldOwnLAGER();
		oSelLagerFactory = new LAG_LagerSelectField_Factory_Extended(); 
		oSelLager =oSelLagerFactory.getSelectField();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,
				"JT_LAGER_INVENTUR.ID_ADRESSE_LAGER = #WERT#", null, null));


		this.oSelArtikel = new MyE2_SelectField(
				"SELECT DISTINCT  jt_artikel.Anr1 || ' - ' ||  jt_artikel.ArtBez1 , jt_artikel.id_artikel  from "
						+ bibE2.cTO()
						+ ".JT_LAGER_KONTO "
						+ " join "
						+ bibE2.cTO()
						+ ".JT_ARTIKEL on JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL "
						+ " WHERE JT_LAGER_KONTO.id_mandant="
						+ bibALL.get_ID_MANDANT() + " ORDER BY 1", false,
				true, false, false);

		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,"JT_LAGER_INVENTUR.ID_ARTIKEL_SORTE = #WERT#", null, null));

		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(
				E2_ResourceIcon.get_RI("reload.png"), true);
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_INV_LIST_Selector othis = LAG_INV_LIST_Selector.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
			}
		});


		

		// Checkbox für die Anzeige der Ausgeglichenen Einträge
		oCBZeigeAusgeglichene = new MyE2_CheckBox(new MyString("Zeige ausgeglichene Einträge"));
		String sColDef = oNavigationList.get_oComponentMAP__REF().get_hmRealDBComponents().get("LAGERBESTAND").EXT_DB().get_oSQLField().get_cFieldAusdruck();
		String s_Ausgeglichen = "(" + sColDef + " != JT_LAGER_INVENTUR.MENGE ) ";

		oSelVector.add(new E2_ListSelectorStandard(oCBZeigeAusgeglichene, "", s_Ausgeglichen));
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		
		
		MyE2_Grid oGrid = new MyE2_Grid(6, 0);

		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")), 1, E2_INSETS.I_0_0_0_0);
		oGrid.add(oSelLagerFactory.render(), 5, new Insets(33,0,0,0));
//		oGrid.add(new MyE2_Label(new MyE2_String("")), 2, E2_INSETS.I_0_0_0_0);
//		oGrid.setColumnWidth(2, new Extent(200));
		
		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Sorte:")), 1, E2_INSETS.I_0_0_0_0);
		MyE2_Row rowArtikel = new MyE2_Row();
		rowArtikel.add(oPBRefreshMaterial, E2_INSETS.I_0_0_0_0);
		rowArtikel.add(oSelArtikel, E2_INSETS.I_0_0_0_0);
		oGrid.add(rowArtikel,3,E2_INSETS.I_10_0_0_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("")), 1, E2_INSETS.I_0_0_0_0);

		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),1,E2_INSETS.I_10_0_0_0);
		oGrid.add(oCBZeigeAusgeglichene,1,E2_INSETS.I_10_0_0_0);
		
//		// Zeile Zeige Lagerbestand
//		//oGrid.add(new MyE2_Label(new MyE2_String("")), 1, E2_INSETS.I_0_0_0_0);
//		MyE2_Row rowAusgeglichen = new MyE2_Row();
//		rowAusgeglichen.add(oCBZeigeAusgeglichene, E2_INSETS.I_0_0_0_0);
//		oGrid.add(rowAusgeglichen,3,E2_INSETS.I_10_0_0_0);
		
		
		this.add(oGrid);
	}
	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
