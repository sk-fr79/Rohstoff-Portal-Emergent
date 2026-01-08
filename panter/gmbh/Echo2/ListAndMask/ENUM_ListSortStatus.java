/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 06.12.2018
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.Echo2.E2_ResourceIcon;

/**
 * @author martin
 * @date 06.12.2018
 *
 */
public enum ENUM_ListSortStatus {

	UP(E2_ResourceIcon.get_RI("sortup.png"))
	,DOWN(E2_ResourceIcon.get_RI("sortdown.png"))
	,NEUTRAL(E2_ResourceIcon.get_RI("empty10small.png"))
	
	;
	
	private E2_ResourceIcon m_icon = null;
	
	private ENUM_ListSortStatus(E2_ResourceIcon icon) {
		this.m_icon=icon;
	}

	public E2_ResourceIcon getIcon() {
		return m_icon;
	}

	public ENUM_ListSortStatus getStatusNext() {
		
		ENUM_ListSortStatus next = null;
		switch (this) {
		case UP:
			next=ENUM_ListSortStatus.DOWN;
			break;
			
		case DOWN:
			next=ENUM_ListSortStatus.UP;
			break;
			
		case NEUTRAL:
			next=ENUM_ListSortStatus.UP;
			break;
		}
		return next;
	}
	
}
