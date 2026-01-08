package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import panter.gmbh.Echo2.E2_ResourceIcon;

public class FSI_Const_and_help {
	
	public enum SORT_BY{
		ASC("ASC", E2_ResourceIcon.get_RI("sortup.png")),
		DESC("DESC", E2_ResourceIcon.get_RI("sortdown.png")),
		DEFAULT("", E2_ResourceIcon.get_RI("sortupdown_mini.png"));

		private E2_ResourceIcon ikon;
		private String sqlValue;

		private SORT_BY(String sqlValue, E2_ResourceIcon ikon) {
			this.sqlValue= sqlValue;
			this.ikon = ikon;
		}

		public E2_ResourceIcon getIkon() {
			return ikon;
		}

		public String getSortingWay() {
			return sqlValue;
		}


	}

	public enum QUERY_KEY{
		FUHRE_LIEFERANT,
		FUHRE_ABNEHMER,
		ORTE_EK,
		ORTE_VK;
	}
}
