package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

public class AH7P_MComp_StatusRelation extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7P_MComp_StatusRelation() throws myException {
		super();
		
		String[][] array = new String[4][2];
		
		array[0][0]="-"; 																		array[0][1]="";
		array[1][0]=AH7__ENUM_STATUSRELATION.UNDEF.user_text(); 								array[1][1]=AH7__ENUM_STATUSRELATION.UNDEF.db_val();
		array[2][0]=AH7__ENUM_STATUSRELATION.INACTIVE.user_text(); 								array[2][1]=AH7__ENUM_STATUSRELATION.INACTIVE.db_val();
		array[3][0]=AH7__ENUM_STATUSRELATION.ACTIVE.user_text(); 								array[3][1]=AH7__ENUM_STATUSRELATION.ACTIVE.db_val();
		
		this._populate(array);
	}

}
