package rohstoff.Echo2BusinessLogic.LAGER;


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
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.bibROHSTOFF;

public class LAG_LIST_Selector extends E2_ListSelectorContainer
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8835755329351449444L;

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private MyE2_SelectFieldWithParameters oSelArtikel = null;
	private MyE2_SelectFieldWithParameters oSelHauptartikel = null;
	
	private MyE2_CheckBox 				   oCBCalculateVertragsDaten = null;
	
	
	
	private LAG_LagerSelectField_Factory_Extended oSelLagerFactory = null;
	private MyE2_SelectField oSelLager = null;
//	private LAG_SelectFieldOwnLAGER oSelLager = null;
	
	private MyE2_Button      oPBRefreshMaterial = null;
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public LAG_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		initSelector(oNavigationList, cMODULE_KENNER);
		
	}



	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER)
			throws myException
	{
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		//
		// CombobBox der Läger
		//
		Vector<String> sAdressIds = bibROHSTOFF.get_vEigeneLieferadressen();
		String sWhere = " AND ID_ADRESSE IN ("  + bibALL.Concatenate(sAdressIds, ",", "") + ")";
		
//		oSelLager = new LAG_SelectFieldOwnLAGER();
		// selektor mit "Streckenlager"
		oSelLagerFactory = new LAG_LagerSelectField_Factory_Extended(); 
		oSelLager = oSelLagerFactory.getSelectField();
		oSelLager.setWidth(new Extent(840));
		
		
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,"JT_LAGER_KONTO.ID_ADRESSE_LAGER = #WERT#",null,null),ENUM_Selector_Report_Params.LAGERLISTE_ID_ADRESSE_LAGER);
		
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
		oSelArtikel.RefreshComboboxFromSQL();
		
		oSelVector.add(new E2_ListSelectorStandard(oSelHauptartikel, "SUBSTR(" + bibE2.cTO()+ ".JT_ARTIKEL.ANR1,0,2) = '#WERT#'",null,null),ENUM_Selector_Report_Params.LAGERLISTE_HAUPTSORTE);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,	bibE2.cTO()+ ".JT_LAGER_KONTO.ID_ARTIKEL_SORTE = #WERT#", null, null),ENUM_Selector_Report_Params.LAGERLISTE_ID_ARTIKEL);
		

		
		// Schalter, ob die Vertragsdaten geladen werden sollen
		oCBCalculateVertragsDaten = new MyE2_CheckBox(new MyE2_String("Vertragsdaten anzeigen"));
		oCBCalculateVertragsDaten.setSelected(false);
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.oPBRefreshMaterial.setToolTipText(new MyE2_String("Liste der Materialien neu laden.").toString());
		
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent(){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_LIST_Selector othis = LAG_LIST_Selector.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
			}
		});
	
		
		// Vollständig verbuchte Einträge ausblenden...
		
		
		
		
		MyE2_Grid oGrid = new MyE2_Grid(3,0);
		
		
		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")),1,E2_INSETS.I_0_0_0_0);		
		oGrid.add(oSelLagerFactory.render(),1,E2_INSETS.I_0_0_0_0);
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,E2_INSETS.I_0_0_0_0);
		oGrid.setColumnWidth(2, new Extent(200));

		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_0_0);
		
		MyE2_Row rowSorte = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowSorte.add(oSelHauptartikel,E2_INSETS.I_0_0_0_0);
		rowSorte.add(new MyE2_Label(new MyE2_String("Sorte:")),E2_INSETS.I_10_0_0_0);
		rowSorte.add(oPBRefreshMaterial,E2_INSETS.I_0_0_0_0);
		rowSorte.add(oSelArtikel,E2_INSETS.I_10_0_0_0);
		rowSorte.add(oCBCalculateVertragsDaten,E2_INSETS.I_10_0_0_0);
		rowSorte.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),E2_INSETS.I_10_0_0_0);
		rowSorte.add(oSelVector.get_AktivPassivComponent(),E2_INSETS.I_10_0_0_0);
		oGrid.add(rowSorte,2,E2_INSETS.I_0_0_0_0);
		
		this.add(oGrid);
	}

	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	public MyE2_SelectFieldWithParameters get_oSelArtikel() {
		return oSelArtikel;
	}



	public MyE2_SelectFieldWithParameters get_oSelHauptartikel() {
		return oSelHauptartikel;
	}



	public MyE2_SelectField get_oSelLager() {
		return oSelLager;
	}

	
	public boolean get_VertragsdatenAnzeigen(){
		return oCBCalculateVertragsDaten.isSelected();
	}
	
	

	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_LIST_Selector oThis = LAG_LIST_Selector.this;
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
	
	
	

	
	
	
}
