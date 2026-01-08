 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_CONST;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
public class CON_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        CON_CONST.CON_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        CON_CONST.CON_NAMES.MARKER_LISTE.db_val();
    public CON_LIST_BasicModuleContainer() throws myException
    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        CON_LIST_BedienPanel oPanel = new CON_LIST_BedienPanel(oNaviList);
        
        // Selektor an die Component-Map hängen
        oNaviList.INIT_WITH_ComponentMAP(new CON_LIST_ComponentMap(oPanel.get_list_Selector()),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
