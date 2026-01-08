 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class REM_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT {
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException 
    {
    }
    
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
    {
    	
    	MyE2_DB_Label_INGRID lblModul = (MyE2_DB_Label_INGRID)oMAP.get__Comp(REMINDER_DEF.modul_connect_ziel);
    	
    	String sModulname = oUsedResultMAP.get_UnFormatedValue(REMINDER_DEF.modul_connect_ziel.fieldName());
    	if (!bibALL.isEmpty(sModulname)){
    		MODUL  oModul = E2_MODULNAME_ENUM.find_Corresponding_Enum(sModulname);
    		if (oModul != null){
    			lblModul.set_Text(oModul.get_userInfo());
    		}
    		
    	}
    	
    	
    	MyE2_Row_EveryTimeEnabled rowJumper = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(REM_CONST.LIST_BUTTON_MODUL_CONNECT);
		rowJumper.removeAll();

		String id = oUsedResultMAP.get_UnFormatedValue(REMINDER_DEF.id_table.fn());
		String table = oUsedResultMAP.get_UnFormatedValue(REMINDER_DEF.table_name.fn());
		String modul = oUsedResultMAP.get_UnFormatedValue(REMINDER_DEF.modul_connect_ziel.fn());
		
		if (!bibALL.isEmpty(modul)){
			MODUL oModul = E2_MODULNAME_ENUM.find_Corresponding_Enum(modul);
			if (oModul != null && !bibALL.isEmpty(id)){
				REM_LIST_bt_jump btn = new REM_LIST_bt_jump(oModul, id);
				rowJumper.add(btn);
			}
		}
		
		
    	
    	
    	
    }
}
 
