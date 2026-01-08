package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.MA_DES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_LIST_DATASEARCH extends E2_DataSearch
{
    public MA_DES_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.mask_def.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(MASK_DEF.id_mask_def.fn(),"id_mask_def",true);
        this.addSearchDef(MASK_DEF.left_offset.fn(),"left_offset",true);
        this.addSearchDef(MASK_DEF.maskname.fn(),"maskname",true);
        this.addSearchDef(MASK_DEF.maskname_long.fn(),"maskname_long",true);
        this.addSearchDef(MASK_DEF.nb_of_cols.fn(),"nb_of_cols",true);
        this.addSearchDef(MASK_DEF.pixel_width.fn(),"pixel_width",true);
        this.addSearchDef(MASK_DEF.tablename.fn(),"tablename",true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
    }
    
    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_mask_def  FROM "+bibE2.cTO()+"."+_TAB.mask_def.n()+" WHERE TO_CHAR("+_TAB.mask_def.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_mask_def  FROM "+bibE2.cTO()+"."+_TAB.mask_def.n()+" WHERE UPPER("+_TAB.mask_def.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
