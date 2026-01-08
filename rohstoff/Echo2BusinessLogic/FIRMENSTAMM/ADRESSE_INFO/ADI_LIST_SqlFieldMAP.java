 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class ADI_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public ADI_LIST_SqlFieldMAP(RECORD_ADRESSE rec_adresse) throws myException {
        super(_TAB.adresse_info.n(), ADRESSE_INFO.id_adresse.fn(), false);
        
        this.add_SQLField(	new SQLFieldForRestrictTableRange(	_TAB.adresse_info.n(), 
        														ADRESSE_INFO.id_adresse.fn(), 
        														ADRESSE_INFO.id_adresse.fn(), 
        														new MyE2_String(ADRESSE_INFO.id_adresse.fn(),false), 
        														rec_adresse.ufs(ADRESSE.id_adresse), 
        														bibE2.get_CurrSession()),true);
        
        this.add_BEDINGUNG_STATIC("NVL("+ADRESSE_INFO.message_typ.tnfn()+",'ALLGEMEIN')='ALLGEMEIN'");
        
        
        this.initFields();
    }
    
}
 
