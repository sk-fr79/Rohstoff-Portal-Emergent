 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TRANSLATOR;
  
  
public class TAXG_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        TAXG_CONST.TAXG_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        TAXG_CONST.TAXG_NAMES.MARKER_LISTE.db_val();
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    public TAXG_LIST_BasicModuleContainer(PARAMHASH  p_params) throws myException    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
        this.params = p_params;     
		if (p_params == null) {
	       this.params = new PARAMHASH();
	    }        
        
        this.set_bVisible_Row_For_Messages(true);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        this.params.put(TAXG_PARAMS.TAXG_MODULCONTAINER_LIST,this);
        this.params.put(TAXG_PARAMS.TAXG_NAVILIST,oNaviList);
        
        oNaviList.INIT_WITH_ComponentMAP(new TAXG_LIST_ComponentMap(this.params),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
        TAXG_LIST_BedienPanel oPanel = new TAXG_LIST_BedienPanel(oNaviList, this.params);
        
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        oNaviList._REBUILD_COMPLETE_LIST("");
    }
        
}
 
