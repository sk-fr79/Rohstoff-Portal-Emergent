 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public CONTYP_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.containertyp.n(), "", false);
        
        this.initFields();
    }
    
}
 
