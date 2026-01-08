package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _LG_CONST {


	public enum _LG_KEYS {
		EINHEIT_PREIS(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT(BEWEGUNG_ATOM.id_artikel_bez)
		,ANR12(BEWEGUNG_ATOM.id_artikel_bez)
		;

		private IF_Field field = null;
		
		private _LG_KEYS(IF_Field f) {
			this.field = f;
		}
		
		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}

	}


}
