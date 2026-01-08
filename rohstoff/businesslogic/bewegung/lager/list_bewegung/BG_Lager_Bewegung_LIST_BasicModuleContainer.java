 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_LIST_Summay;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST;
import rohstoff.businesslogic.bewegung.lager.list_bewegung.BG_Lager_Bewegung_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
  
@Deprecated
public class BG_Lager_Bewegung_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        BG_Lager_Bewegung_CONST.BG_Lager_Bewegung_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        BG_Lager_Bewegung_CONST.BG_Lager_Bewegung_NAMES.MARKER_LISTE.db_val();
    public BG_Lager_Bewegung_LIST_BasicModuleContainer() throws myException
    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        oNaviList.INIT_WITH_ComponentMAP(new BG_Lager_Bewegung_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        BG_Lager_Bewegung_LIST_BedienPanel oPanel = new BG_Lager_Bewegung_LIST_BedienPanel(oNaviList);
        
        BG_Lager_Bewegung_LIST_Summay oPanelSummary = new BG_Lager_Bewegung_LIST_Summay(oNaviList, oPanel.get_list_Selector());
        
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oPanelSummary,E2_INSETS.I_2_0_2_0);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
