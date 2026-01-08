 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class RR_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public RR_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.report_reiter.n(), "", false);
        
        this.initFields();
    }
    
}
 
