 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public JCC_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.jasper_compile_chain.n(), "", false);
        
        this.initFields();
    }
    
}
 
