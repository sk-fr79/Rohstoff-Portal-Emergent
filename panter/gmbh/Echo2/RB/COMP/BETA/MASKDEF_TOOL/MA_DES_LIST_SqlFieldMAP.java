 
package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class MA_DES_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public MA_DES_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.mask_def.n(), "", false);
        
        this.initFields();
    }
    
}
 
