package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _LL_CONST {
	public enum _LL_KEYS {
		ANR12(BEWEGUNG_ATOM.id_artikel_bez)
		,
		;

		private IF_Field field = null;

		private _LL_KEYS(IF_Field f) {
			this.field = f;
		}

		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}
	}
}
