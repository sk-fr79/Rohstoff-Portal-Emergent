 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.SEARCHDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public SD_LIST_SqlFieldMAP(String modulKenner) throws myException 
    {
        super(_TAB.searchdef.n(), SEARCHDEF.modulkenner.fn(), false);
        
        this.add_SQLField(new SQLFieldForRestrictTableRange(		_TAB.searchdef.fullTableName(), 
        															SEARCHDEF.modulkenner.fn(), 
        															SEARCHDEF.modulkenner.fn(), 
        															S.ms(SEARCHDEF.modulkenner.fn()), 
        															"'"+modulKenner+"'", 
        															bibE2.get_CurrSession()), false);
        
        
        this.initFields();
    }
    
}
 
