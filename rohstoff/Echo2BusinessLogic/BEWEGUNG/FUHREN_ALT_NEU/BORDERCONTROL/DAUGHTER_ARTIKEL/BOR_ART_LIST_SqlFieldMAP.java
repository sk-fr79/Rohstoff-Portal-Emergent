 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
  
public class BOR_ART_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
   
	//zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public BOR_ART_LIST_SqlFieldMAP(PARAMHASH  p_params) throws myException   {
        super(_TAB.bordercrossing_artikel.n(), BORDERCROSSING_ARTIKEL.id_bordercrossing.fn(), false);
        
        this.params = p_params;        
        
        if (this.params != null) {
            this.params.put(BOR_ART_PARAMS.BOR_ART_MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        }
    

		// ContainerTyp
		this.add_JOIN_Table(_TAB.artikel.n(), 
				"", 
				SQLFieldMAP.INNER, 
				":ANR1:ARTBEZ1:ID_ARTIKEL:", 
				true, 
				BORDERCROSSING_ARTIKEL.id_artikel.tnfn() + " = " + ARTIKEL.id_artikel.tnfn(), 
				"ART_", 
				null);

		
		this.add_SQLField(new SQLFieldForRestrictTableRange(_TAB.bordercrossing_artikel.n(),BORDERCROSSING_ARTIKEL.id_bordercrossing.fn(),BORDERCROSSING_ARTIKEL.id_bordercrossing.fn(),new MyE2_String("Referenzfeld"),"NULL",bibE2.get_CurrSession()), false);
		
        this.initFields();
    }
    
}
 
