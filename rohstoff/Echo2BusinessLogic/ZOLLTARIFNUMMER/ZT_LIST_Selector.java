 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selektor_checkBox;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
public class ZT_LIST_Selector extends E2_ExpandableRow {
    

    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public ZT_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
        this.oSelVector.add(sel_geaendert_von);
        
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(E2_MODULNAME_ENUM.MODUL.ZT_NUMMER_LIST.get_callKey());
		oSelVector.add(oDB_BasedSelektor);

		
		AktivSelector aktiv = new AktivSelector();
		RcSelector    rc = new RcSelector();
		
		oSelVector.add(aktiv);
		oSelVector.add(rc);
		
		E2_Grid grid = new E2_Grid()._setSize(200,150,170,200);
		this.add(grid, E2_INSETS.I_4_4_4_4);
		
		grid	._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._ins(0, 2, 2, 2))
				._a(aktiv.get_oComponentForSelection(), new RB_gld()._ins(0, 2, 2, 2))
				._a(rc.get_oComponentForSelection(), new RB_gld()._ins(0, 2, 2, 2))
				._a(oDB_BasedSelektor.get_oComponentForSelection(), new RB_gld()._ins(0, 2, 2, 2))
				;
        
        
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
            
            And  bed = new And(ZOLLTARIFNUMMER.geaendert_von,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
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
            gridHelp.add(new MyE2_Label(new MyE2_String("Geändert von")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
    
	private class AktivSelector extends E2_ListSelektorMultiselektionStatusFeld_STD{
		public AktivSelector() throws myException {
			super(new int[] {60,60}, true, S.ms("") ,new Extent(5));
			this.ADD_STATUS_TO_Selector(true,	new vgl_YN(ZOLLTARIFNUMMER.aktiv, true).s(), S.ms("Aktiv"),  S.ms("Aktive Zolltarifnummern anzeigen"));		
			this.ADD_STATUS_TO_Selector(false,	new vgl_YN(ZOLLTARIFNUMMER.aktiv, false).s(), S.ms("Inaktiv"),  S.ms("Inaktive Zolltarifnummern anzeigen"));			
		}
	}

	
	private class RcSelector extends E2_ListSelektorMultiselektionStatusFeld_STD{
		public RcSelector() throws myException {
			super(new int[] {80,80}, true, S.ms("") ,new Extent(5));
			this.ADD_STATUS_TO_Selector(true,	new vgl_YN(ZOLLTARIFNUMMER.reverse_charge, true).s(), S.ms("RC: JA"),  S.ms("Zolltarifnummern mit Status RC:Ja anzeigen"));		
			this.ADD_STATUS_TO_Selector(true,	new vgl_YN(ZOLLTARIFNUMMER.reverse_charge, false).s(), S.ms("RC: Nein"),  S.ms("Zolltarifnummern mit Status RC:Nein anzeigen"));			
		}
	}

	
}
 
 
