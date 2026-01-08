package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_ENUM_SONDERPROFILE;

public class AH7P_MComp_SonderProfil extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7P_MComp_SonderProfil() throws myException {
		super();
		
		String[][] array = AH7_ENUM_SONDERPROFILE.getDDArray();
		this._populate(array)._setForceEmptyValInFront(false);
	}

}
