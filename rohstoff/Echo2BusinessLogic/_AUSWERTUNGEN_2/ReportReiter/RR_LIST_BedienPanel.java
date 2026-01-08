 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class RR_LIST_BedienPanel extends MyE2_Column {
    
    
    public RR_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
    {
        super(MyE2_Column.STYLE_NO_BORDER());
        
        MyE2_Grid oGridForComponents = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
        
        Insets oInsets = new Insets(0,0,0,5);
        
//        this.add(report_reiter_LIST_Selector, oInsets);
        this.add(oGridForComponents, oInsets);
       
        oGridForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  E2_INSETS.I(2,2,2,2));
        oGridForComponents.add(new RR_LIST_bt_ListToMask(true,oNaviList), E2_INSETS.I(20,2,2,2));
        oGridForComponents.add(new RR_LIST_bt_ListToMask(false,oNaviList),E2_INSETS.I(2,2,2,2));
        oGridForComponents.add(new RR_LIST_bt_New(oNaviList), E2_INSETS.I(2,2,2,2));
        oGridForComponents.add(new RR_LIST_bt_multiDelete(oNaviList), E2_INSETS.I(2,2,2,2));
        
        oGridForComponents.add(new RR_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
        
        
    }
    
}
 



/**
public RR_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
{
    super(MyE2_Column.STYLE_NO_BORDER());
    
//    MyE2_Grid oGridForComponents = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
    MyE2_Grid oGridForComponents = new MyE2_Grid(MyE2_Grid.STYLE_GRID_BORDER(Color.RED));
    
    Insets oInsets = new Insets(0,0,0,5);
    
//    this.add(report_reiter_LIST_Selector, oInsets);
    this.add(oGridForComponents, oInsets);
    
    GridLayoutData  layoutTest = new GridLayoutData();
    layoutTest.setAlignment(new Alignment(Alignment.LEFT, Alignment.BOTTOM));
    layoutTest.setBackground(new Color(200,20, 30));
    layoutTest.setInsets(E2_INSETS.I(2,2,2,2));
    
    
    oGridForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  layoutTest);
    oGridForComponents.add(new RR_LIST_bt_ListToMask(true,oNaviList), layoutTest);
    oGridForComponents.add(new RR_LIST_bt_ListToMask(false,oNaviList),  layoutTest);
    oGridForComponents.add(new RR_LIST_bt_New(oNaviList), E2_INSETS.I_2_2_2_2);
    oGridForComponents.add(new RR_LIST_bt_multiDelete(oNaviList), E2_INSETS.I_2_2_2_2);
    
    oGridForComponents.add(new RR_LIST_DATASEARCH(oNaviList), new Insets(20,2,2,2));
}
*/