package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;


import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;

public class ATOM_LAG_BEW_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private MyE2_Button      								m_oPBRefreshMaterial = null;
	private MyE2_CheckBox 	 								m_oCB_ZeigeKompletteEintraege = null; 
	private ATOM_LAG_Mengenermittlung_ext 					m_oMengenErmittlung = null;;
		
	private E2_SelectionComponentsVector 					m_oSelVector = null;

	private ATOM_LAG_BEW_COL_StatusLager  					m_oColStatusanzeige = null;

	private boolean 										m_bKostenberuecksichtigung = false;
	
	private UTIL_MultiSelectField_Factory_ForLager  		m_oSelMulti_Lager = null;
	private UTIL_MultiSelectField_Hauptsorte		 		m_oSelMulti_Hauptsorte = null;
	private UTIL_MultiSelectField_Sorte				 		m_oSelMulti_Sorte = null;
	
	
	public ATOM_LAG_BEW_LIST_Selector(E2_NavigationList oNavigationList, ATOM_LAG_Mengenermittlung_ext oMengenErmittlung, String cMODULE_KENNER, boolean bPreiseMitKosten ) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		m_bKostenberuecksichtigung = bPreiseMitKosten;
		this.m_oMengenErmittlung = oMengenErmittlung;
		this.initSelector(oNavigationList, cMODULE_KENNER, bPreiseMitKosten);
	}

	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return m_oSelVector;
	}
	
	
	
	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER, boolean bPreiseMitKosten)
			throws myException {
		this.m_oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		XX_ActionAgent oAgentStatusRefresh = new actionAgentStatusRefresh();
		
		//
		// Lager
		//
		m_oSelMulti_Lager = new UTIL_MultiSelectField_Factory_ForLager(true,"",500, "S_LAG.ID_ADRESSE = #WERT# ");
		m_oSelMulti_Lager.get_vAgents4ActiveComponentsBeforeSelection().add(new actionClearHaufen());
		m_oSelMulti_Lager.get_vAgents4ActiveComponentsBeforeSelection().add(oAgentStatusRefresh);
		m_oSelVector.add(m_oSelMulti_Lager);

		
		//
		// Hauptartikel
		//
		m_oSelMulti_Hauptsorte = new UTIL_MultiSelectField_Hauptsorte(null, 50, "SUBSTR(ART.ANR1,0,2) = '#WERT#'");
		m_oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		m_oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionClearHaufen());
		m_oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(oAgentStatusRefresh);
		m_oSelVector.add(m_oSelMulti_Hauptsorte);
		
		
		//
		// Artikel
		//
		m_oSelMulti_Sorte = new UTIL_MultiSelectField_Sorte(null, 250, "JT_BEWEGUNG_ATOM.ID_ARTIKEL = #WERT#");
		m_oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionClearHaufen());
		m_oSelMulti_Sorte.get_vAgents4ActiveComponentsBeforeSelection().add(oAgentStatusRefresh);
		m_oSelVector.add(m_oSelMulti_Sorte);
		
		
		this.m_oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.m_oPBRefreshMaterial.setToolTipText(new MyE2_String("Liste der Materialien neu laden.").toString());
		m_oPBRefreshMaterial.add_oActionAgent(new actionAgentHauptartikel());
		m_oPBRefreshMaterial.add_oActionAgent(oAgentStatusRefresh);
		
		
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		m_oCB_ZeigeKompletteEintraege = new MyE2_CheckBox(new MyE2_String("Komplett verbuchte WE zeigen").CTrans(),oStyle) ;
		m_oCB_ZeigeKompletteEintraege.setSelected(false);
		m_oSelVector.add(new E2_ListSelectorStandard(m_oCB_ZeigeKompletteEintraege,"", "AV.ID_BEWEGUNG_ATOM IS NULL "));
		

		m_oColStatusanzeige = new ATOM_LAG_BEW_COL_StatusLager(bPreiseMitKosten);
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.m_oSelVector.add(oDB_BasedSelektor);
		
		/*
		 * einfuegen (nach Einfuegen in Selectionsvector) des zusatz-Action-Agents zum loeschen des Haufens
		 */
		MyE2_Grid oGrid = new MyE2_Grid(6, 0);

		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")), 1, E2_INSETS.I_0_0_0_0);
		oGrid.add(m_oSelMulti_Lager.get_oComponentForSelection(), 5, E2_INSETS.I_0_0_0_0);
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_0_0);
		oGrid.add(m_oSelMulti_Hauptsorte.get_oComponentForSelection(),1,E2_INSETS.I_0_0_10_0);
		
		MyE2_Row rowArtikel = new MyE2_Row();
		rowArtikel.add(new MyE2_Label(new MyE2_String("Sorte:")) ,E2_INSETS.I_0_0_5_0);
		rowArtikel.add(m_oPBRefreshMaterial, E2_INSETS.I_0_0_0_0);
		rowArtikel.add(m_oSelMulti_Sorte.get_oComponentForSelection(), E2_INSETS.I_0_0_0_0);
		oGrid.add(rowArtikel,2,E2_INSETS.I_0_0_0_0);
		
		oGrid.add(m_oCB_ZeigeKompletteEintraege,1,E2_INSETS.I_10_0_0_0);
		oGrid.add(m_oSelVector.get_AktivPassivComponent(),1,E2_INSETS.I_10_0_0_0);
		
		oGrid.add(new MyE2_Label(""),1,E2_INSETS.I_10_0_0_0);
		oGrid.add(new MyE2_Label(""),3,E2_INSETS.I_10_0_0_0);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100),2,E2_INSETS.I_10_0_0_0);
		
		oGrid.add(m_oColStatusanzeige,6,E2_INSETS.I_0_0_0_0);
		
		this.add(oGrid);
		
		
		
	}

	
	
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_BEW_LIST_Selector oThis = ATOM_LAG_BEW_LIST_Selector.this;
			
			Vector<String> vHauptartikel = oThis.m_oSelMulti_Hauptsorte.get_SelectedValues();
			Vector<String> vWhere = new Vector<String>();
			for (String s: vHauptartikel){
				vWhere.add("substr('00" + s.trim() + "',-2) " );
			}
			
			String sParamValues = "";
			if (vHauptartikel != null && vHauptartikel.size() > 0) {
				sParamValues = " AND SUBSTR(ANR1,0,2) in ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
			}
			oThis.m_oSelMulti_Sorte.Refresh("#P1#", sParamValues);
			
			

		}
	}

	
	private class actionAgentStatusRefresh extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_LAG_BEW_LIST_Selector oThis = ATOM_LAG_BEW_LIST_Selector.this;
			oThis.m_oColStatusanzeige.clear();
			
			oThis.m_oColStatusanzeige.set_vIDAdressen(oThis.m_oSelMulti_Lager.get_SelectedValues());
			oThis.m_oColStatusanzeige.set_vIDSorten(oThis.m_oSelMulti_Sorte.get_SelectedValues());
			oThis.m_oColStatusanzeige.set_vIDHauptSorten(oThis.m_oSelMulti_Hauptsorte.get_SelectedValues());
			
			oThis.m_oColStatusanzeige.refresh();
		}
		
	}


	/**
	 * @return the oSelArtikel
	 */
	public E2_ListSelectorMultiDropDown get_oSelSorte()
	{
		return m_oSelMulti_Sorte;
	}

	public E2_ListSelectorMultiDropDown get_oSelHauptSorte()
	{
		return m_oSelMulti_Hauptsorte;
	} 
	
	/**
	 * @return the oSelLager
	 */
	public E2_ListSelectorMultiDropDown get_oSelLager()
	{
		return m_oSelMulti_Lager;
	}

		
	

	/**
	 * @return the oCB_ZeigeKompletteEintraege
	 */
	public MyE2_CheckBox get_oCB_ZeigeKompletteEintraege()
	{
		return m_oCB_ZeigeKompletteEintraege;
	}


	private class actionClearHaufen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			ATOM_LAG_BEW_LIST_Selector.this.m_oMengenErmittlung.clearAuswahlListe();
		}
		
	}
	
	
}
