package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public DRUCK_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.drucker.n(), "", false);
        
        this.initFields();
    }
    
}
 
