/**
 * panter.gmbh.indep
 * @author manfred
 * @date 27.04.2016
 * 
 */
package panter.gmbh.indep;

import java.util.Comparator;


/**
 * Comparator für 2-dim-Arrays
 * Sortierung nach 1. Wert
 * @author manfred
 * @date 25.04.2016
 *
 */
public class Comparator_For_2_dimensional_Arrays implements Comparator <Comparable[]> {
	/**
	 * @author manfred
	 * @date 25.04.2016
	 *
	 */
	private int 		_col_to_sort = 1;
	private boolean 	_ascending = true;

	public Comparator_For_2_dimensional_Arrays() {
		this(0,true);
	}

	public Comparator_For_2_dimensional_Arrays(int col,boolean ascending) {
		_col_to_sort = col;
		_ascending = ascending;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Comparable[] entry1, Comparable[] entry2) {
		int cmp = entry1[_col_to_sort].compareTo(entry2[_col_to_sort]);
		return _ascending ? cmp : -cmp ;
	}

}


