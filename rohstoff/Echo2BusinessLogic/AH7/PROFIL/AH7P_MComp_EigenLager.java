package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_MComp_EigenLager extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7P_MComp_EigenLager(boolean start) throws myException {
		super();
		
		String[][] array = new String[3][2];
		
		array[0][0]="-"; 																		array[0][1]="";
		array[1][0]=bibALL.get_RECORD_MANDANT().get___KETTE(MANDANT.name1,MANDANT.name2); 		array[1][1]="Y";
		array[2][0]=S.ms(start?"Lieferant":"Abnehmer").CTrans(); 								array[2][1]="N";
		
		this._populate(array);
	}

}
