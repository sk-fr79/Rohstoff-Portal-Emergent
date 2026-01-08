package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.components.E2_ListComp_ListVal;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.indep.Pair;

public class AH7P_LCompShowZielInland extends E2_ListComp_ListVal {

	public AH7P_LCompShowZielInland() {
		super();
		this._setFieldKeyToShow(AH7_PROFIL.ziel_inland.fn());
		this._add(Pair.P("Y", "Inland"),Pair.P("N", "Ausland"));
	}

}
