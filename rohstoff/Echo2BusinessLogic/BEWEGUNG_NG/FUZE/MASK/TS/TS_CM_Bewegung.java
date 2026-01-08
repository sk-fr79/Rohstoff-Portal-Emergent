package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Bewegung;

public class TS_CM_Bewegung extends CM_Bewegung {

	/**
	 * @throws myException
	 */
	public TS_CM_Bewegung() throws myException {
		super();
		
        //hier werden validierer hinterlegt, die die maske pruefen und ergaenzen 
        this.registerSetterValidators(BEWEGUNG.id_bewegung.fk(), new _TS_Mask_Set_and_Valid());
	}

}
