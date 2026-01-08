 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class IP_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public IP_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.report.n(), "", false);
        
        this.initFields();
    }
    
}
 
