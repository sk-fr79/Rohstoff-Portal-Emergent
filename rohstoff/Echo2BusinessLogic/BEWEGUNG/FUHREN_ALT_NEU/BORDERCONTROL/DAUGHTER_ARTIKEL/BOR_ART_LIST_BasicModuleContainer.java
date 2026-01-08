 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.TRANSLATOR;
  
  
public class BOR_ART_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
    public static final String NAME_OF_CHECKBOX_IN_LIST =        BOR_ART_CONST.BOR_ART_NAMES.CHECKBOX_LISTE.db_val();
    public static final String NAME_OF_LISTMARKER_IN_LIST =        BOR_ART_CONST.BOR_ART_NAMES.MARKER_LISTE.db_val();

    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    
    
    public BOR_ART_LIST_BasicModuleContainer(PARAMHASH  p_params) throws myException    {
        super(TRANSLATOR.LIST.get_modul().get_callKey());
      
        this.params = p_params;     

        if(p_params == null){
	       this.params = new PARAMHASH();
	    }        
        
        this.set_bVisible_Row_For_Messages(false);
        
        E2_NavigationList oNaviList = new E2_NavigationList();
        
        this.params.put(BOR_ART_PARAMS.BOR_ART_MODULCONTAINER_LIST,this);
        this.params.put(BOR_ART_PARAMS.BOR_ART_NAVILIST,oNaviList);
        
        oNaviList.INIT_WITH_ComponentMAP(new BOR_ART_LIST_ComponentMap(this.params),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
        BOR_ART_LIST_BedienPanel oPanel = new BOR_ART_LIST_BedienPanel(oNaviList, this.params);
        
        // grauer Rahmen 
        this.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
        
        // Panels
        this.add(oPanel, E2_INSETS.I_2_2_2_2);
        this.add(oNaviList, E2_INSETS.I_2_2_2_2);
        
        
        this._setWidth(800);
        
        oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
    }
        
}
 
