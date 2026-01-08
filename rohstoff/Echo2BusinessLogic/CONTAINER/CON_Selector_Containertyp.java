/**
 * rohstoff.Echo2BusinessLogic.CONTAINER
 * @author manfred
 * @date 05.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINER;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * selektor fuer die auswahl von modulen ....
 * @author martin
 *
 */
class CON_Selector_Containertyp extends E2_ListSelectorMultiDropDown2 {
    public CON_Selector_Containertyp() throws myException {
        super();
        
        String sSql = new SEL()
				.ADDFIELD(CONTAINERTYP.kurzbezeichnung.fieldName())
				.ADDFIELD(CONTAINERTYP.id_containertyp.fieldName())
				.FROM(_TAB.containertyp)
				.ORDERUP(CONTAINERTYP.kurzbezeichnung).s();
        
        MyE2_SelectField selFieldContainertyp = new MyE2_SelectField(sSql, false, true, false, false);
        And  bed = new And(CONTAINERTYP.id_containertyp,"'#WERT#'");
        
        this.INIT(selFieldContainertyp, bed.s(), null);
        
        // muss nach INIT aufgerufen werden...
        this.set_extOfSelectComponentDropDown(new Extent(300));
    }
    
    @Override
    public E2_BasicModuleContainer get_PopupContainer() throws myException {
        return new ownBasicContainer();
    }
    
    private class ownBasicContainer extends E2_BasicModuleContainer {}
    
    @Override
    public Component get_oComponentForSelection() throws myException {
        int[] breite = {100,300};
        MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
        
        gridHelp.add(new MyE2_Label(new MyE2_String("Containertyp")), E2_INSETS.I(0,2,10,2));
        gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
        
        return gridHelp;
    }
    
}