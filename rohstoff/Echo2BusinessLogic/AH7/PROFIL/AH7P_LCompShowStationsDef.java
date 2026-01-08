package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.components.E2_ListComp_ListVal;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_TEILNEHMER;

public class AH7P_LCompShowStationsDef extends E2_ListComp_ListVal {

	@SuppressWarnings("unchecked")
	public AH7P_LCompShowStationsDef(IF_Field field) throws myException {
		super();
		this._setFieldKeyToShow(field.fn());
		for (AH7__ENUM_TEILNEHMER t: AH7__ENUM_TEILNEHMER.values()) {
			this._add(t.getPairLangtext());
		}
	}

}
