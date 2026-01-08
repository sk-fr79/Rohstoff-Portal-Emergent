package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _WA_CONST {

	public enum _WA_KEYS {
		EINHEIT_PREIS(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT(BEWEGUNG_ATOM.id_artikel_bez)
		,ANR12(BEWEGUNG_ATOM.id_artikel_bez)

		;

		private IF_Field field = null;

		private _WA_KEYS(IF_Field f) {
			this.field = f;
		}

		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}
	}

}
