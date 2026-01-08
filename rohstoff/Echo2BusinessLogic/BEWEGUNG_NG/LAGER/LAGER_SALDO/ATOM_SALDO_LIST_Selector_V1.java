package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;


import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
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
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_SelektorEigenwarenFremdwarenStrecken;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;

public class ATOM_SALDO_LIST_Selector_V1 extends E2_ListSelectorContainer
{
	
	/**
	 * 
	 */
	private UTIL_MultiSelectField_Factory_ForLager  		oSelMulti_Lager = null;

	private UTIL_MultiSelectField_Hauptsorte		 		oSelMulti_Hauptsorte = null;
	private UTIL_MultiSelectField_Sorte				 		oSelMulti_Sorte = null;
	private MyE2_Button      								oPBRefreshMaterial = null;

	private ATOM_LAG_SelektorEigenwarenFremdwarenStrecken 	oSelectorLagerTypen = null;
	
	private MyE2_CheckBox 				   					oCBCalculateVertragsDaten = null;
	private MyE2_SelectField 								oSelEinheit = null;
	private E2_SelectionComponentsVector 					oSelVector = null;
	
	
	
	
	public ATOM_SALDO_LIST_Selector_V1(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		initSelector(oNavigationList,cMODULE_KENNER);
		
	}



	protected void initSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER)
			throws myException
	{
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		//
		// selektor mit "Streckenlager"
		//
		oSelMulti_Lager = new UTIL_MultiSelectField_Factory_ForLager(true,"",500, "JT_BEWEGUNG_STATION.ID_ADRESSE =  #WERT# ");
		oSelVector.add(oSelMulti_Lager);
				
		oSelectorLagerTypen = new ATOM_LAG_SelektorEigenwarenFremdwarenStrecken(oNavigationList, "JT_LIEFERADRESSE", "JT_BEWEGUNG_STATION",true,"");
		oSelVector.add (oSelectorLagerTypen);
		

		oSelMulti_Hauptsorte = new UTIL_MultiSelectField_Hauptsorte(null, 50, "SUBSTR(JT_ARTIKEL.ANR1,0,2) = '#WERT#'" /*,new actionAgentHauptartikel()*/);
		oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		oSelVector.add(oSelMulti_Hauptsorte);
		
		
		oSelMulti_Sorte = new UTIL_MultiSelectField_Sorte(null, 250, "JT_BEWEGUNG_ATOM.ID_ARTIKEL = #WERT#");
		oSelVector.add(oSelMulti_Sorte);
		
		// Einheiten
		oSelEinheit = new MyE2_SelectField("SELECT EINHEITLANG, ID_EINHEIT  from "
				+ bibE2.cTO()
				+ ".JT_EINHEIT "
				+ " WHERE ID_MANDANT = "
				+ bibALL.get_ID_MANDANT() + " ORDER BY IST_STANDARD DESC, EINHEITLANG", false, true,
		false, false);

		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_BEWEGUNG_ATOM.ID_ARTIKEL IN (" +
				" SELECT JT_ARTIKEL.ID_ARTIKEL " +
				" FROM  " + bibE2.cTO() + ".JT_ARTIKEL " +
				" LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_EINHEIT " +
				"	ON JT_ARTIKEL.ID_MANDANT = JT_EINHEIT.ID_MANDANT " +
				"	AND JT_ARTIKEL.ID_EINHEIT = JT_EINHEIT.ID_EINHEIT " +
				" WHERE JT_EINHEIT.ID_EINHEIT = #WERT# ) ", null,null));
		
		// Schalter, ob die Vertragsdaten geladen werden sollen
		oCBCalculateVertragsDaten = new MyE2_CheckBox(new MyE2_String("Vertragsdaten anzeigen"));
		oCBCalculateVertragsDaten.setSelected(false);
		
		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		this.oPBRefreshMaterial.setToolTipText(new MyE2_String("Liste der Materialien neu laden.").toString());
		oPBRefreshMaterial.add_oActionAgent(new actionAgentHauptartikel());

		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		//
		// GUI
		//
		MyE2_Grid oGrid = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		// Zeile Lagertypen
		oGrid.add(new MyE2_Label(new MyE2_String("Lager-Art:")),1,E2_INSETS.I_0_0_0_0);
		oGrid.add(oSelectorLagerTypen.get_oComponentForSelection(),2,E2_INSETS.I_0_0_0_5);
		
		
		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Lager:")),1,E2_INSETS.I_0_0_0_0);		
		oGrid.add(oSelMulti_Lager.get_oComponentForSelection(),1, E2_INSETS.I_0_0_0_0);
		
		oGrid.add(new MyE2_Label(new MyE2_String("")),1,E2_INSETS.I_0_0_0_0);
		oGrid.setColumnWidth(2, new Extent(200));

		// Zeile
		oGrid.add(new MyE2_Label(new MyE2_String("Hauptsorte:")),1,E2_INSETS.I_0_0_0_0);
		
		MyE2_Row rowSorte = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowSorte.add(oSelMulti_Hauptsorte.get_oComponentForSelection(),E2_INSETS.I_0_0_0_0);
		
		rowSorte.add(new MyE2_Label(new MyE2_String("Sorte:")),E2_INSETS.I_10_0_0_0);
		rowSorte.add(oPBRefreshMaterial,E2_INSETS.I_0_0_0_0);
		rowSorte.add(oSelMulti_Sorte.get_oComponentForSelection(),E2_INSETS.I_10_0_0_0);

		rowSorte.add(oCBCalculateVertragsDaten,E2_INSETS.I_10_0_0_0);
		rowSorte.add(oSelVector.get_AktivPassivComponent(),E2_INSETS.I_10_0_0_0);
		oGrid.add(rowSorte,2,E2_INSETS.I_0_0_0_0);

		// Zeile 
		oGrid.add(new MyE2_Label(new MyE2_String("Einheit:")),E2_INSETS.I_0_0_0_0);
		MyE2_Row rowAdditional = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		rowAdditional.add(oSelEinheit,E2_INSETS.I_0_0_0_0);
		rowAdditional.add(new MyE2_Label(new MyE2_String(oDB_BasedSelektor.get_bIsFilled()?"Divers:":"")),E2_INSETS.I_20_0_0_0);
		rowAdditional.add(oDB_BasedSelektor.get_oComponentForSelection(100),E2_INSETS.I_5_0_0_0);
		oGrid.add(rowAdditional,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
	
		this.add(oGrid);
	}

	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	
	public boolean get_VertragsdatenAnzeigen(){
		return oCBCalculateVertragsDaten.isSelected();
	}
	
	
	public boolean get_RefreshSaldo(){
//		return oCBRefreshSaldo.isSelected();
		return true;
	}
	

	/**
	 * setzt den Parameter bei der Sorten-Dropdown dass die Sorten den Hauptsorten angepasst werden.
	 * @author manfred
	 *
	 */
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_SALDO_LIST_Selector_V1 oThis = ATOM_SALDO_LIST_Selector_V1.this;
			
			Vector<String> vHauptartikel = oSelMulti_Hauptsorte.get_SelectedValues();
			Vector<String> vWhere = new Vector<String>();
			for (String s: vHauptartikel){
				vWhere.add("substr('00" + s.trim() + "',-2) " );
			}
			
			String sParamValues = "";
			if (vHauptartikel != null && vHauptartikel.size() > 0) {
				sParamValues = " AND SUBSTR(ANR1,0,2) in ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
			}
			oThis.oSelMulti_Sorte.Refresh("#P1#", sParamValues);
		}
	}
	
		
	
}
