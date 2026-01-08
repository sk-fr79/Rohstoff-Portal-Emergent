package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _ST_CONST {

	public enum _ST_KEYS {
		ANR12(BEWEGUNG_ATOM.id_artikel_bez)
		,
		;

		private IF_Field field = null;

		private _ST_KEYS(IF_Field f) {
			this.field = f;
		}

		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}
	}


}
