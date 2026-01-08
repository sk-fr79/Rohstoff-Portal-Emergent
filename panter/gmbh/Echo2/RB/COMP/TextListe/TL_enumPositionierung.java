/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 19.02.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 19.02.2020
 *
 */
public enum TL_enumPositionierung implements IF_enum_4_db_specified<TL_enumPositionierung> {

		KOPF("KOPF","Kopftext")
		,FUSS("FUSS","Fusstext")
		;

		private String m_dbVal = null;
		private String m_userText = null;

		private TL_enumPositionierung(String dbVal,String userText) {
			this.m_dbVal = dbVal;
			this.m_userText = userText;
		}
		
		@Override
		public String db_val() {
			return m_dbVal;
		}

		@Override
		public String user_text() {
			return m_userText;
		}


		@Override
		public TL_enumPositionierung[] get_Values() {
			return TL_enumPositionierung.values();
		}

}
