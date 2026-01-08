 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;


public class CON_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    CON_Selector_Containertyp 				_sel_containertyp = null;
    
    public CON_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        
        _sel_containertyp = new CON_Selector_Containertyp();
        
        this.oSelVector.add(_sel_containertyp);
        MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen.add(_sel_containertyp.get_oComponentForSelection());
        
    }
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    /**
     * Die SelectionList für den Containtertyp
     * @author manfred
     * @date 06.12.2017
     *
     * @return
     */
    public CON_Selector_Containertyp getSelectorContainerTyp(){
    	return _sel_containertyp;
    }
    
}
 
