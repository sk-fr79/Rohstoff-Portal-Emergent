 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class CON_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public CON_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.container.n(), "", false);
        
		
		// ContainerTyp
		this.add_JOIN_Table(_TAB.containertyp.n(), 
				"", 
				SQLFieldMAP.LEFT_OUTER, 
				":KURZBEZEICHNUNG:BESCHREIBUNG:CONTAINERINHALT:", 
				true, 
				CONTAINER.id_containertyp.tnfn() + " = " + CONTAINERTYP.id_containertyp.tnfn(), 
				"CT_", 
				null);

        
		
		
        this.initFields();
    }
    
}
 
