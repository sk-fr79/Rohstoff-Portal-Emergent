 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_PARAMS;
  
public class TAXG_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_LIST_SqlFieldMAP(PARAMHASH  p_params) throws myException   {
        super(_TAB.tax_group.n(), "", false);
        
        this.params = p_params;        
        
        if (this.params != null) {
            this.params.put(TAXG_PARAMS.TAXG_MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        }
        
        
        this.initFields();
    }
    
}
 
