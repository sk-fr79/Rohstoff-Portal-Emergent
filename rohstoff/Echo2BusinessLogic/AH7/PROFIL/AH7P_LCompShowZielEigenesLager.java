package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.components.E2_ListComp_ListVal;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_LCompShowZielEigenesLager extends E2_ListComp_ListVal {

	public AH7P_LCompShowZielEigenesLager() throws myException {
		super();
		this._setFieldKeyToShow(AH7_PROFIL.ziel_eigenes_lager.fn());
		this._add(Pair.P("Y", bibALL.get_RECORD_MANDANT().get___KETTE(MANDANT.name1,MANDANT.name2)),Pair.P("N", "Abnehmer"));
	}

}
