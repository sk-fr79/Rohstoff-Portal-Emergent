package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class AT_LIST_DATASEARCH extends E2_DataSearch
{
    public AT_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
    {
        super(_TAB.trigger_action_def.n(),_TAB.trigger_action_def.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(TRIGGER_ACTION_DEF.id_trigger_action_def.fn(),"ID-Trigger",true);
        this.addSearchDef(TRIGGER_ACTION_DEF.trigger_name.fn(),			"Name des Triggers",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.table_basename.fn(),		"Tabellen-Basisname",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.field_name.fn(),			"Feldname",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.execution_class.fn(),		"Ausführungsklasse",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.execution_code.fn(),		"Ausführungscode",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.execution_valid.fn(),		"Ausführungsvalidierung",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.validation_class.fn(),		"Validierungsklasse",false);
        this.addSearchDef(TRIGGER_ACTION_DEF.table_id.fn(),				"Tabellen-ID",true);
        
		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

    }

    private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
    {
        String cSearch = "";
        if (bNumber) {
            cSearch = "SELECT id_trigger_action_def  FROM "+bibE2.cTO()+"."+_TAB.trigger_action_def.n()+" WHERE TO_CHAR("+_TAB.trigger_action_def.n()+"."+cFieldName+")='#WERT#'";
        } else {
            cSearch = "SELECT id_trigger_action_def  FROM "+bibE2.cTO()+"."+_TAB.trigger_action_def.n()+" WHERE UPPER("+_TAB.trigger_action_def.n()+"."+cFieldName+") like upper('%#WERT#%')";
        }
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
