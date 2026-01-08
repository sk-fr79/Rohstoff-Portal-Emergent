/**
 * panter.gmbh.Echo2.RB.COMP.SearchFieldV2
 * @author martin
 * @date 19.09.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.indep.S;

/**
 * @author martin
 * @date 19.09.2019
 *
 */
public enum EnSortStatusButtonGrid {
	 UP("sortup.png")
	,DOWN("sortdown.png")
	,NEUTRAL("empty10.png")
	;
	private String 		name_icon = 	null;
	
	private EnSortStatusButtonGrid(String p_name_icon) {
		this.name_icon = p_name_icon;
	}
	
	public EnSortStatusButtonGrid get_next() {
		EnSortStatusButtonGrid s_next  = null;
		switch(this) {
			case UP:
				s_next=DOWN;
				break;
			case DOWN:
				s_next = UP;
				break;
			case NEUTRAL:
				s_next = UP;
				break;
			default:
				s_next = UP;
		}
		return s_next;
	}

	public E2_ResourceIcon icon() {
		return E2_ResourceIcon.get_RI(this.name_icon);
	}
	
	
	public boolean isUp() {
		boolean b_rueck  = true;
		switch(this) {
			case DOWN:
				b_rueck  = false;
				break;
			default:
				b_rueck  = true;
		}
		return b_rueck;
	}

	public static EnSortStatusButtonGrid getStatus(String name) {
		for (EnSortStatusButtonGrid s: EnSortStatusButtonGrid.values()) {
			if (s.name().equals(S.NN(name))) {
				return s;
			}
		}
		return null;
	}
}


