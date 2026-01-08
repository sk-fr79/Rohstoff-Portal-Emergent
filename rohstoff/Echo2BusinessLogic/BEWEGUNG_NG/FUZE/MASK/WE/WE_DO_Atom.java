 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;
 
public class WE_DO_Atom extends RB_Dataobject_V2 {
 
    public WE_DO_Atom(Rec20 recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public WE_DO_Atom() throws myException {
        super(_TAB.bewegung_atom);
    }
 
 
    /**
     * sicht die lagerrelevante menge aus (falls eine nachgeschaltete interne umbuchung stattfindet 
     * @return
     * @throws myException
     */
    public MyBigDecimal get_relevant_lager_menge() throws myException {
    	
    	//zuerst den dataobject-collector in den korrekten typ casten
		Rec20 			r_vektor_pos = this.get_rec20().get_up_rec20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos);
		Rec20_vektor 	r_vektor = new Rec20_vektor(r_vektor_pos.get_up_rec20(BEWEGUNG_VEKTOR.id_bewegung_vektor));
	
		MyBigDecimal bd = r_vektor._get_menge_WE_MENGENKORREKTUR();
		return bd;
    }
    
    
    
}
 
