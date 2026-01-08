package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF__ChangeEnumArray_to_StringArray;
import panter.gmbh.BasicInterfaces.IF__ChangeEnumArray_to_StringArray_x_2;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class AT_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    public AT_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        ownSelectorTablename sel_geaendert_von = new ownSelectorTablename();
        this.oSelVector.add(sel_geaendert_von);
        MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen.add(sel_geaendert_von.get_oComponentForSelection());
        
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
    private class ownSelectorTablename extends E2_ListSelectorMultiDropDown2 {

    	public ownSelectorTablename() throws myException {
            super();
            
            IF__ChangeEnumArray_to_StringArray_x_2 change = (Enum[] enums)-> { 	String[][] v= new String[enums.length+1][2]; 
            																	v[0][0]="-";v[0][1]=""; 
            																	int k = 1;
            																	for (int i=enums.length-1;i>=0;i--) { 
            																		v[k][0]=v[k++][1]=enums[i].name().toUpperCase();
            																	} 
            																	return v; };
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(change.make_new_array(_TAB.values()),"", false);    
            
            And  bed = new And(TRIGGER_ACTION_DEF.table_basename,"'#WERT#'");
            
            this.INIT(selFieldKenner, bed.s(), null);
            this.set_extOfSelectComponentDropDown(new Extent(200));
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
            gridHelp.add(new MyE2_Label(new MyE2_String("Tabelle:")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
    public static String[] create_names(Enum[] nums) {
    	String[] c_ret =  new String[nums.length];
    	
    	for (int i=0;i<nums.length;i++) {
    		c_ret[i]=nums[i].name();
    	}
    	
    	return c_ret;
    }
    
}
 
