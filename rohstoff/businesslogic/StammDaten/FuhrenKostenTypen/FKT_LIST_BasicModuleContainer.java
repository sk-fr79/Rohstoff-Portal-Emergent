 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.StammDaten.FuhrenKostenTypen.FKT_CONST.TRANSLATOR;
  
public class FKT_LIST_BasicModuleContainer extends Project_BasicModuleContainer {
    public static final String NAME_OF_CHECKBOX_IN_LIST =        FKT_CONST.FKT_NAMES.CHECKBOX_LISTE.dbVal();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        FKT_CONST.FKT_NAMES.MARKER_LISTE.dbVal();
 
    public FKT_LIST_BasicModuleContainer() throws myException   {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        oNaviList.INIT_WITH_ComponentMAP(new FKT_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        FKT_LIST_BedienPanel oPanel = new FKT_LIST_BedienPanel(oNaviList);
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oNaviList._REBUILD_COMPLETE_LIST("");
    }
        
}
 
