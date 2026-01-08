package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;


import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.ToggleButton;
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
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_TextField_With_DatePOPUP;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_SelektorEigenwarenFremdwarenStrecken;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_SelektorEigenwarenFremdwarenStrecken.ENUM_SELEKTOR_Lagertyp;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;

public class ATOM_SALDO_LIST_Selector extends E2_ListSelectorContainer
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
	
	private ownTF4Datum										oDateAdditional1 = null;
	private ownTF4Datum										oDateAdditional2 = null;
	
	
	//fuer gleichheit der selektor-unter-grids
	private int[] 											iSpaltenGridMain 	= {100,1000,100,100};
	
	
	public ATOM_SALDO_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
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
		oSelMulti_Lager = new UTIL_MultiSelectField_Factory_ForLager(true,"",800, "JT_BEWEGUNG_STATION.ID_ADRESSE =  #WERT# ");
		oSelVector.add(oSelMulti_Lager);
				
		oSelectorLagerTypen = new ATOM_LAG_SelektorEigenwarenFremdwarenStrecken(oNavigationList, "JT_LIEFERADRESSE", "JT_BEWEGUNG_STATION",true,"");
		oSelectorLagerTypen.set_CheckboxIsExternal(2);
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
		
		// Datumsfelder, das zusätzlich als Spalte angezeigt werden muss.
		oDateAdditional1 = new ownTF4Datum(null, false);
		oDateAdditional2 = new ownTF4Datum(null, false);
		
		//
		// GUI
		//
		
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
		

		MyE2_Grid oGridLagerauswahl = new MyE2_Grid(3,styleGridBorderInner);
		oGridLagerauswahl.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		oGridAussen.add(oGridLagerauswahl,4,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		oGridLagerauswahl.add(new MyE2_Label(new MyE2_String("Lager:")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		oGridLagerauswahl.add(oSelMulti_Lager.get_oComponentForSelection(),2,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridLagerauswahl.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		oGridLagerauswahl.add(oSelectorLagerTypen.getCheckboxOf(ENUM_SELEKTOR_Lagertyp.STRECKE),2,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		
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

		
		
		MyE2_Grid oGridDivers1 = new MyE2_Grid(styleGridBorderInner);
		oGridAussen.add(oGridDivers1,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		oGridDivers1.setSize(2);
		
		oGridDivers1.add(new MyE2_Label(new MyE2_String("Einheit:")),1,1,E2_INSETS.I_0_0_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oSelEinheit,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		oGridDivers1.add(new MyE2_Label(new MyE2_String(oDB_BasedSelektor.get_bIsFilled()?"Divers:":"")),1,1,E2_INSETS.I_0_5_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oDB_BasedSelektor.get_oComponentForSelection(100),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		oGridDivers1.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_0_5_0,Alignment.ALIGN_TOP);
		oGridDivers1.add(oCBCalculateVertragsDaten,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);

		oGridDivers1.add(new MyE2_Label(new MyE2_String("")),1,1,E2_INSETS.I_0_5_5_0,Alignment.ALIGN_TOP);
		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());
		oGridDivers1.add(oRowPassivschalter,1,1,E2_INSETS.I_0_5_0_0,Alignment.ALIGN_TOP);

		
		E2_ExpandableRow oRowDivers2 = 	new E2_ExpandableRow(	new MyE2_String("Zusätzliche Auswahlkriterien: Lagerorte"), 
				new Border(1,new E2_ColorDDDark(),Border.STYLE_NONE),
			 	new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		// Standardmäßig verstecken
		oRowDivers2.get_oButtonClose().doActionPassiv();
		oGridAussen.add(oRowDivers2,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		MyE2_Grid oGridDivers2 = new MyE2_Grid(styleGridBorderInner);
		oRowDivers2.add(oGridDivers2,E2_INSETS.I_0_0_0_0);
		oGridDivers2.setSize(3);
		
		oGridDivers2.add(new MyE2_Label(new MyE2_String("Lagerorte:")),1,1,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP);	
		oGridDivers2.add(oSelectorLagerTypen.get_oComponentForSelection(),2,1,E2_INSETS.I_10_10_0_0,Alignment.ALIGN_TOP);
		
		oGridDivers2.add(new MyE2_Label(new MyE2_String("Zusätzliche Lagerbestände : ")),1,2,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP);
		oGridDivers2.add(oDateAdditional1,2,1,E2_INSETS.I_10_10_0_0,Alignment.ALIGN_TOP);
		
		oGridDivers2.add(new MyE2_Label(new MyE2_String("")),1,2,E2_INSETS.I_0_5_0_0,Alignment.ALIGN_TOP);
		oGridDivers2.add(oDateAdditional2,2,1,E2_INSETS.I_10_5_0_0,Alignment.ALIGN_TOP);
		
		get_oSelVector().add_oActionAgent(null);
		
		
		
		this.add(oGridAussen);

	}

	
	/**
	 * Gibt den Selektionsvektor zurück
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	
	/**
	 * true, wenn Vertragsdaten angezeigt werden sollen
	 * @author manfred
	 * @date 
	 *
	 * @return
	 */
	public boolean get_VertragsdatenAnzeigen(){
		return oCBCalculateVertragsDaten.isSelected();
	}
	

	/**
	 * gibt das gewählte Zusatzdatum in Iso-Format (yyyy-mm-dd) oder deutschen Format(dd.MM.yyyy) zurück.
	 * @author manfred
	 * @date 19.10.2016
	 *
	 * @param bISOFormat
	 * @return
	 * @throws myException
	 */
	public String get_AdditionalDateForSaldo1(boolean bISOFormat) throws myException{
		return get_AdditionalDateForSaldo(oDateAdditional1,bISOFormat);
	}
	
	
	/**
	 * gibt das gewählte Zusatzdatum in Iso-Format (yyyy-mm-dd) oder deutschen Format(dd.MM.yyyy) zurück.	 
	 * @author manfred
	 * @date 20.10.2016
	 *
	 * @param bISOFormat
	 * @return
	 * @throws myException
	 */
	public String get_AdditionalDateForSaldo2(boolean bISOFormat) throws myException{
		return get_AdditionalDateForSaldo(oDateAdditional2,bISOFormat);
	}
	
	
	/**
	 * Gibt das Datum des gewählten Feldes zurück
	 * @author manfred
	 * @date 20.10.2016
	 *
	 * @param oTF
	 * @param bISOFormat
	 * @return
	 * @throws myException
	 */
	private String get_AdditionalDateForSaldo(ownTF4Datum oTF, boolean bISOFormat) throws myException{
		String sDatum = bISOFormat ? oTF.get_oDBFormatedDateFromTextField() : oTF.get_oFormatedDateFromTextField();
		return sDatum;
	}

	
	
	/**
	 * setzt den Parameter bei der Sorten-Dropdown dass die Sorten den Hauptsorten angepasst werden.
	 * @author manfred
	 *
	 */
	private class actionAgentHauptartikel extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ATOM_SALDO_LIST_Selector oThis = ATOM_SALDO_LIST_Selector.this;
			
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
	
	
	
	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100,true,true);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			this.get_oButtonCalendar().set_bEnabled_For_Edit(true); 
			
			this.get_oButtonEraser().add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					oSelVector.makeSelektion();
				}
			});
			
			
			this.get_vActionAgentsZusatz().add(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					oSelVector.makeSelektion();
				}
			});
		}
	}
	
}
