package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_LIST_SqlFieldMAP extends Project_SQLFieldMAP {
    public AH7P_LIST_SqlFieldMAP() throws myException    {
        super(_TAB.ah7_profil.n(), "", false);
        this.initFields();
    }
    
}
 
