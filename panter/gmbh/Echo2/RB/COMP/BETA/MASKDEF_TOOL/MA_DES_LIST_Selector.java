package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_MASKNAME;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    public MA_DES_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        ownSelectorGeaendertVon sel_geaendert_von 	= new ownSelectorGeaendertVon();
        ownSelectorMaskname 	sel_maskname 		= new ownSelectorMaskname();
        ownSelectorTablename 	sel_tablename 		= new ownSelectorTablename();
        
        this.oSelVector.add(sel_geaendert_von);
        this.oSelVector.add(sel_tablename);
        this.oSelVector.add(sel_maskname);
        
        E2_Grid oGridInnen = new E2_Grid()._s(4);
       
        this.add(oGridInnen, E2_INSETS.I(4,4,20,4));
        
        oGridInnen._a(sel_geaendert_von.get_oComponentForSelection(), 	new RB_gld()._left_mid());
        oGridInnen._a(sel_maskname.get_oComponentForSelection(),  		new RB_gld()._left_mid());
        oGridInnen._a(sel_tablename.get_oComponentForSelection(),  		new RB_gld()._left_mid());
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
            
            And  bed = new And(MASK_DEF.geaendert_von,"'#WERT#'");
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
//            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            E2_Grid  gridHelp = new E2_Grid()._setSize(breite);
            
            gridHelp._a(new RB_lab()._t("Geaendert von"), new RB_gld()._ins(0,2,10,2)._left_mid());
            gridHelp._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorTablename extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorTablename() throws myException {
            super();
            
            String abfrage = DB_META.get_TablesQuerySort_A_to_Z(bibE2.cTO(),true, true, true);
            String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(abfrage,false);
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(bibARR.add_emtpy_db_value_inFront(cTabellen),"", false, new Extent(150));    
            
            And  bed = new And(MASK_DEF.tablename,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {100,150};
            
            E2_Grid  gridHelp = new E2_Grid()._setSize(breite); //breite, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
            gridHelp._a(new RB_lab()._t("Tabelle"), 		new RB_gld()._ins(0,2,10,2)._right_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2));
          
            return gridHelp;
        }
        
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorMaskname extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorMaskname() throws myException {
            super();
    		String[][] 	cTabellen = ENUM_MASKNAME.TEST.get_dd_Array(true);
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(cTabellen,"", false, new Extent(150));    
            
            And  bed = new And(MASK_DEF.maskname,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {100,150};
            
            E2_Grid  gridHelp = new E2_Grid()._setSize(breite);
            gridHelp._a(new RB_lab()._t("Maskname"), 		new RB_gld()._ins(0,2,10,2)._right_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2));
            
            return gridHelp;
        }
        
    }
}
 
