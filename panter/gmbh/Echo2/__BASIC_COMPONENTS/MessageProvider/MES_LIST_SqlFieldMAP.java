package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class MES_LIST_SqlFieldMAP extends Project_SQLFieldMAP {
    public MES_LIST_SqlFieldMAP() throws myException   {
        super(_TAB.message_provider.n(), "", false);
        
        this.initFields();
    }
    
}
 
