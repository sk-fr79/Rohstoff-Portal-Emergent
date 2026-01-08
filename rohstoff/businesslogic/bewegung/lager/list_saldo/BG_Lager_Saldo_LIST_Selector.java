 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
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
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.TRANSLATOR;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_LIST_SelektorEW_FW_ST.ENUM_SELEKTOR_Lagertyp;
import rohstoff.businesslogic.bewegung.lager.utils.BG_Util_MultiSelectField_Factory_ForLager;
import rohstoff.businesslogic.bewegung.lager.utils.BG_Util_MultiSelectField_Hauptsorte;
import rohstoff.businesslogic.bewegung.lager.utils.BG_Util_MultiSelectField_Sorte;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.basics4project.ENUM_USER_TYP;


@Deprecated
public class BG_Lager_Saldo_LIST_Selector extends E2_ExpandableRow {
    
	/**
	 * 
	 */
	private BG_Util_MultiSelectField_Factory_ForLager  		oSelMulti_Lager = null;

	private BG_Util_MultiSelectField_Hauptsorte		 		oSelMulti_Hauptsorte = null;
	private BG_Util_MultiSelectField_Sorte			 		oSelMulti_Sorte = null;
	private MyE2_Button      								oPBRefreshMaterial = null;

	private BG_Lager_Saldo_LIST_SelektorEW_FW_ST 		oSelectorLagerTypen = null;
	
	private MyE2_CheckBox 				   					oCBCalculateVertragsDaten = null;
	private MyE2_SelectField 								oSelEinheit = null;

	private ownTF4Datum										oDateAdditional1 = null;
	private ownTF4Datum										oDateAdditional2 = null;
	
	private E2_SelectionComponentsVector 					oSelVector = null;

	
	//fuer gleichheit der selektor-unter-grids
	private int[] 											iSpaltenGridMain 	= {100,1000,100,100};
	
    
    public BG_Lager_Saldo_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        
		//
		// selektor mit "Streckenlager"
		//
		oSelMulti_Lager = new BG_Util_MultiSelectField_Factory_ForLager (true,"",800, "S1.ID_ADRESSE =  #WERT# ");
		oSelVector.add(oSelMulti_Lager);
				
		oSelectorLagerTypen = new BG_Lager_Saldo_LIST_SelektorEW_FW_ST( /*oNavigationList, "JT_LIEFERADRESSE", "JT_BEWEGUNG_STATION",*/ true,"");
		oSelectorLagerTypen.set_CheckboxIsExternal(2);
		oSelVector.add (oSelectorLagerTypen);
		
		

		oSelMulti_Hauptsorte = new BG_Util_MultiSelectField_Hauptsorte(null, 50, "SUBSTR(ART.ANR1,0,2) = '#WERT#'" /*,new actionAgentHauptartikel()*/);
		oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		oSelVector.add(oSelMulti_Hauptsorte);
		
		
		oSelMulti_Sorte = new BG_Util_MultiSelectField_Sorte(null, 250, "JT_BG_LADUNG.ID_ARTIKEL = #WERT#");
		oSelVector.add(oSelMulti_Sorte);
		
		// Einheiten
		oSelEinheit = new MyE2_SelectField("SELECT EINHEITLANG, ID_EINHEIT  from "
				+ bibE2.cTO()
				+ ".JT_EINHEIT "
				+ " WHERE ID_MANDANT = "
				+ bibALL.get_ID_MANDANT() + " ORDER BY IST_STANDARD DESC, EINHEITLANG", false, true,
		false, false);

		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, "JT_BG_LADUNG.ID_ARTIKEL IN (" +
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
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(/*cMODULE_KENNER*/TRANSLATOR.LIST.name());
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

        
        
        
//        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
//        this.oSelVector.add(sel_geaendert_von);
//        MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
//        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
//        
//        oGridInnen.add(sel_geaendert_von.get_oComponentForSelection());
        
    }
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorGeaendertVon extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorGeaendertVon() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT_KUERZEL(ENUM_USER_TYP.BUERO).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    
            
//            And  bed = new And(BG_LADUNG.geaendert_von,"'#WERT#'");
//            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {100,100};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            gridHelp.add(new MyE2_Label(new MyE2_String("#-- SELEKTOR --#")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
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
			BG_Lager_Saldo_LIST_Selector oThis = BG_Lager_Saldo_LIST_Selector.this;
			
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
 
