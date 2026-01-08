 
package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public ADR_FREIGABE_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.sanktion_pruefung.n(), "", false);
       
        this.initFields();
    }
    
}
 
