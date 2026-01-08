 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
public class LH_LIST_Selector extends E2_ListSelectorContainer {

    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public LH_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       

        ownSelectorGeaendertVon 	sel_geaendert_von 	= new ownSelectorGeaendertVon();
        ownSelectorBoxNummer 		sel_boxnr 			= new ownSelectorBoxNummer();
//        ownSelectorDefaultBox		sel_default_box 	= new ownSelectorDefaultBox();
        LH_LIST_Selector_kunde		sel_kunde 			= new LH_LIST_Selector_kunde();
        LH_LIST_Selector_fuhre		sel_fuhre 			= new LH_LIST_Selector_fuhre();
        LH_LIST_Selector_sorte		sel_sorte 			= new LH_LIST_Selector_sorte();
        LH_LIST_Selector_buchungsnr sel_buchungsnr		= new LH_LIST_Selector_buchungsnr();
        
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.oSelVector.add(sel_geaendert_von);
        this.oSelVector.add(sel_boxnr);
        this.oSelVector.add(sel_kunde);
        this.oSelVector.add(sel_fuhre);
        this.oSelVector.add(sel_sorte);
        this.oSelVector.add(sel_buchungsnr);
//        this.oSelVector.add(sel_default_box);
        
        E2_Grid oGridInnen = new E2_Grid()._s(3)._bo_no()._w100();
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen
        ._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._ins(2,1,2,1)._left_mid())
        ._a(sel_kunde.get_oComponentForSelection(), 		new RB_gld()._ins(2,1,2,1)._left_mid())
        ._a(sel_fuhre.get_oComponentForSelection(), 		new RB_gld()._ins(2,1,2,1)._left_mid())
      
        ._a(sel_boxnr.get_oComponentForSelection(), 		new RB_gld()._ins(2,1,2,1)._left_mid())
        ._a(sel_sorte.get_oComponentForSelection(), 		new RB_gld()._ins(2,1,2,1)._left_mid())	
        ._a(sel_buchungsnr.get_oComponentForSelection(),	new RB_gld()._ins(2,1,2,1)._left_mid())	
//        ._a(sel_default_box.get_oComponentForSelection(), 	new RB_gld()._ins(2,1,2,1)._left_mid())
        ;
        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
    }
    
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    private class ownSelectorGeaendertVon extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorGeaendertVon() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT_KUERZEL(ENUM_USER_TYP.BUERO).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    

            And  bed = new And(LAGER_BOX.geaendert_von,"'#WERT#'");
            
            selFieldKenner.setWidth(new Extent(150));
            
            this.INIT(selFieldKenner, bed.s(), null);
            
//            this.get_oSelFieldBasis().setWidth(new Extent(175));
            this.set_extOfSelectComponentDropDown(new Extent(175));
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
        	 E2_Grid  gridHelp = new E2_Grid()._setSize(100,100)._bo_no()
        	            ._a(new RB_lab()._t("Geändert von:"), 	new RB_gld()._ins(0,2,10,2)._left_mid())
        	            ._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            
            return gridHelp;
        }
        
    }
    
    private class ownSelectorBoxNummer extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorBoxNummer() throws myException {
            super();
            
            String lagerBoxAbfrage = new SEL()
            		.ADDFIELD(LAGER_BOX.boxnummer.tnfn() + "|| (case when(NVL(JT_LAGER_BOX.IS_DEFAULT_BOX,'N')='Y')then ' *' end)", "BOXNUMMER")
            		.ADDFIELD(LAGER_BOX.id_lager_box)
            		.FROM(_TAB.lager_box)
            		.ORDERDOWN("NVL("+LAGER_BOX.is_default_box.tnfn()+",'N')")
            		.ORDERUP(LAGER_BOX.id_lager_box.tnfn())
            		.s();
          
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(lagerBoxAbfrage, false, true, false, false);
            
            And  bed = new And(LAGER_BOX.id_lager_box,"'#WERT#'");

            this.INIT(selFieldKenner, bed.s(), null);
            
//            this.get_oSelFieldBasis().setWidth(new Extent(175));
            this.set_extOfSelectComponentDropDown(new Extent(175));
            
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
        	 E2_Grid  gridHelp = new E2_Grid()._setSize(100,100)._bo_no()
        	 ._a(new RB_lab()._t("Boxnummer:"), 		new RB_gld()._ins(0,2,10,2)._left_mid())
        	 ._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
        
    }
}
 
 
