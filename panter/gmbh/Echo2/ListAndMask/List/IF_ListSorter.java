/**
 * panter.gmbh.Echo2.ListAndMask.List
 * @author martin
 * @date 06.12.2018
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_Label;

/**
 * @author martin
 * @date 06.12.2018
 *
 */
public interface IF_ListSorter {
	public void Reset();
	public String get_cSORT_STATEMENT_UP();
	public String get_cSORT_STATEMENT_DOWN();
	public MyE2_Label getLabelSortStatus();


	public default void set_Unsorted() {
		this.getLabelSortStatus().setIcon(E2_ResourceIcon.get_RI("empty10small.png"));
	}
	
	public default void set_SortedUP() {
		this.getLabelSortStatus().setIcon(E2_ResourceIcon.get_RI("sortup.png"));
	}
	
	public default void set_SortedDown() {
		this.getLabelSortStatus().setIcon(E2_ResourceIcon.get_RI("sortdown.png"));
	}
}
