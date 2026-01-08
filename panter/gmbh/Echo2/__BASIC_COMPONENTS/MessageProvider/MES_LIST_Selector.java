package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;


public class MES_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    public MES_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        
        
        ownSelectorMailTarget sel_mailTarget = new ownSelectorMailTarget();
        ownSelectorMessageKey sel_messageKey = new ownSelectorMessageKey();
        
        
        this.oSelVector.add(sel_mailTarget);
        this.oSelVector.add(sel_messageKey);
        
        E2_Grid oGridInnen = new E2_Grid()._setSize(300,300);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen	._a(sel_mailTarget.get_oComponentForSelection())
        			._a(sel_messageKey.get_oComponentForSelection())
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
    private class ownSelectorMailTarget extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorMailTarget() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT(false, ENUM_USER_TYP.BUERO,ENUM_USER_TYP.AKTIV).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    
            
            And  bed = new And(MESSAGE_PROVIDER.id_user,"#WERT#");
            
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
            gridHelp.add(new MyE2_Label(new MyE2_String("eMail-Empfänger")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorMessageKey extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorMessageKey() throws myException {
            super();
            
            String[][] typeList = ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.get_dd_Array(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(typeList,"", false);    
            
            And  bed = new And(MESSAGE_PROVIDER.messagekey,"'#WERT#'");
            
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
            gridHelp.add(new MyE2_Label(new MyE2_String("Programmschlüssel")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
}
 
