package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;


import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
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
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerSelectField_Factory_Extended;
import rohstoff.utils.bibROHSTOFF;

public class LAG_BEW_LIST_Selector extends E2_ListSelectorContainer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151155024324529183L;

	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private MyE2_SelectFieldWithParameters oSelArtikel = null;
	private MyE2_SelectFieldWithParameters oSelHauptartikel = null;
	
//	private LAG_SelectFieldOwnLAGER oSelLager = null;
	private LAG_LagerSelectField_Factory_Extended oSelLagerFactory = null;
	private MyE2_SelectField		oSelLager = null;
	private MyE2_Button      oPBRefreshMaterial = null;
	private MyE2_CheckBox 	 oCB_ZeigeKompletteEintraege = null; 
	private LAG_Mengenermittlung m_oMengenErmittlung = null;;
		
	private E2_SelectionComponentsVector 	oSelVector = null;

	private LAG_BEW_COL_StatusLager  oColStatusanzeige = null;
	
	
	public LAG_BEW_LIST_Selector(E2_NavigationList oNavigationList, LAG_Mengenermittlung oMengenErmittlung, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		this.m_oMengenErmittlung = oMengenErmittlung;
		this.initSelector(oNavigationList, cMODULE_KENNER);
	}

	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	
	
	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER)
			throws myException {
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		XX_ActionAgent oAgentStatusRefresh = new actionAgentStatusRefresh();
		
		//
		// CombobBox der Läger
		//
		Vector<String> sAdressIds = bibROHSTOFF.get_vEigeneLieferadressen();
		String sWhere = " AND ID_ADRESSE IN ("
				+ bibALL.Concatenate(sAdressIds, ",", "") + ")";

//		oSelLager = new LAG_SelectFieldOwnLAGER();
		oSelLagerFactory = new LAG_LagerSelectField_Factory_Extended();
		oSelLager = oSelLagerFactory.getSelectField();
		
		oSelLager.add_oActionAgent(oAgentStatusRefresh);
		oSelVector.add(new E2_ListSelectorStandard(oSelLager,
				"JT_LAGER_KONTO.ID_ADRESSE_LAGER = #WERT#", null, null));

		
		
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
		oSelHauptartikel.add_oActionAgent(oAgentStatusRefresh);
		
		
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
		
		oSelArtikel.add_oActionAgent(oAgentStatusRefresh);
		
		
		oSelVector.add(new E2_ListSelectorStandard(oSelHauptartikel, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'",null,null));
		oSelVector.add(new E2_ListSelectorStandard(oSelArtikel,	"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = #WERT#", null, null));
		
		
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon
				.get_RI("reload.png"), true);
		this.oPBRefreshMaterial.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException {
				LAG_BEW_LIST_Selector othis = LAG_BEW_LIST_Selector.this;
				othis.oSelArtikel.RefreshComboboxFromSQL();
			}
		});
		
		
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oCB_ZeigeKompletteEintraege = new MyE2_CheckBox(new MyE2_String("Komplett verbuchte WE zeigen").CTrans(),oStyle) ;
		oCB_ZeigeKompletteEintraege.setSelected(false);
		
		
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_ZeigeKompletteEintraege,"","  NVL(JT_LAGER_KONTO.IST_KOMPLETT,'N')='N'"));
		
		oColStatusanzeige = new LAG_BEW_COL_StatusLager();
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		/*
		 * einfuegen (nach Einfuegen in Selectionsvector) des zusatz-Action-Agents zum loeschen des Haufens
		 */
		this.oSelLager.add_oActionAgent(new actionClearHaufen(),true);
		this.oSelArtikel.add_oActionAgent(new actionClearHaufen(),true);
		//this.oCB_ZeigeKompletteEintraege.add_oActionAgent(new actionClearHaufen(),true);
		
		MyE2_Grid oGrid = new MyE2_Grid(6, 0);

		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")), 1, E2_INSETS.I_0_0_0_0);
		oGrid.add(oSelLagerFactory.render(), 5, E2_INSETS.I_0_0_0_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("")),2,E2_INSETS.I_0_0_0_0);
//		oGrid.setColumnWidth(2, new Extent(200));
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_0_0);
		oGrid.add(oSelHauptartikel,1,E2_INSETS.I_0_0_10_0);
		
		MyE2_Row rowArtikel = new MyE2_Row();
		rowArtikel.add(new MyE2_Label(new MyE2_String("Sorte:")) ,E2_INSETS.I_0_0_5_0);
		rowArtikel.add(oPBRefreshMaterial, E2_INSETS.I_0_0_0_0);
		rowArtikel.add(oSelArtikel, E2_INSETS.I_0_0_0_0);
		oGrid.add(rowArtikel,2,E2_INSETS.I_0_0_0_0);
		
		oGrid.add(oCB_ZeigeKompletteEintraege,1,E2_INSETS.I_10_0_0_0);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),1,E2_INSETS.I_10_0_0_0);
		

		
//		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_0_0);
//		oGrid.add(oSelHauptartikel,1,E2_INSETS.I_0_0_0_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("Sorte:")), 1, E2_INSETS.I_0_0_0_0);
//		oGrid.add(oPBRefreshMaterial, 1, E2_INSETS.I_0_0_0_0);
//		oGrid.add(oSelArtikel, 1, E2_INSETS.I_0_0_0_0);
//		oGrid.add(oCB_ZeigeKompletteEintraege,2,E2_INSETS.I_0_0_0_0);
//		oGrid.add(new MyE2_Label(new MyE2_String("")), 5, E2_INSETS.I_0_0_0_0);
		
		oGrid.add(oColStatusanzeige,6,E2_INSETS.I_0_0_0_0);
		
		this.add(oGrid);
		
		
		
	}

	
	
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_BEW_LIST_Selector oThis = LAG_BEW_LIST_Selector.this;
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
	
	private class actionAgentStatusRefresh extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LAG_BEW_LIST_Selector oThis = LAG_BEW_LIST_Selector.this;
			oThis.oColStatusanzeige.set_IDSorte(oThis.oSelArtikel.get_ActualWert());
			oThis.oColStatusanzeige.set_Hauptsorte(oThis.oSelHauptartikel.get_ActualWert());
			oThis.oColStatusanzeige.set_IDAdresse(oThis.oSelLager.get_ActualWert());
			
			oThis.oColStatusanzeige.refresh();
		}
		
	}

	/**
	 * @return the oSelArtikel
	 */
	public MyE2_SelectField get_oSelArtikel()
	{
		return oSelArtikel;
	}

	public MyE2_SelectField get_oSelHauptartikel()
	{
		return oSelHauptartikel;
	} 
	
	/**
	 * @return the oSelLager
	 */
	public MyE2_SelectField get_oSelLager()
	{
		return oSelLager;
	}


	/**
	 * @return the oCB_ZeigeKompletteEintraege
	 */
	public MyE2_CheckBox get_oCB_ZeigeKompletteEintraege()
	{
		return oCB_ZeigeKompletteEintraege;
	}


	private class actionClearHaufen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			LAG_BEW_LIST_Selector.this.m_oMengenErmittlung.clearAuswahlListe();
		}
		
	}
	
	
}
