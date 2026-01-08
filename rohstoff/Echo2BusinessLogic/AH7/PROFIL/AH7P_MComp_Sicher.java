package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_MComp_Sicher extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7P_MComp_Sicher(boolean start) throws myException {
		super();
		
		String[][] array = new String[3][2];
		
		array[0][0]="-"; 															array[0][1]="";
		array[1][0]=S.ms(start?"Sichere Quelle":"Sicheres Ziel").CTrans(); 			array[1][1]="Y";
		array[2][0]=S.ms(start?"UNSICHERE Quelle":"UNSICHERES Ziel").CTrans(); 		array[2][1]="N";
		this._populate(array);
	}

}
