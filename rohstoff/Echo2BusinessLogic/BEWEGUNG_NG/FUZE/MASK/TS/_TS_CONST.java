package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class _TS_CONST {


	public enum _TS_KEYS {
		EINHEIT_PREIS(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT(BEWEGUNG_ATOM.id_artikel_bez)
		,ANR12(BEWEGUNG_ATOM.id_artikel_bez)
		,KOMBI_ANG_KON(BEWEGUNG_ATOM.id_vpos_kon)

		;

		private IF_Field field = null;
		
		private _TS_KEYS(IF_Field f) {
			this.field = f;
		}
		
		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}

	}


}
