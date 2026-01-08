 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public FKT_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.fuhren_kosten_typ.n(), "", false);
        
        this.initFields();
    }
    
}
 
