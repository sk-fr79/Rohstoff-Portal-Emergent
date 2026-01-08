/**
 * panter.gmbh.indep
 * @author martin
 * @date 13.02.2019
 * 
 */
package panter.gmbh.indep;

import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.components.MyE2IF__Component;

/**
 * @author martin
 * @date 13.02.2019
 *
 */
public class bibEcho {

	

	/**
	 * einer Echo-Component-Collection rekursive eine forground-farbe geben 
	 * @author martin
	 * @date 13.02.2019
	 *
	 * @param col
	 * @param c
	 */
	public static void setGenericForeGround(Color col, Collection<MyE2IF__Component> c) {
		for (Object o: c) {
			if (o instanceof Component) {
				bibEcho.setGenericForeGround(col, (Component)o);
			}
		}
	}
	
	
	public static void setGenericForeGround(Color col, Component p_c) {
		if (col==null || p_c == null ) {
			return;
		}
		
		Component c = (Component)p_c;
		
		if (c instanceof Grid) {
			for (Component ci : ((Grid)c).getComponents()) {
				bibEcho.setGenericForeGround(col,ci);
			}
		} else if (c instanceof Column) {
			for (Component ci : ((Column)c).getComponents()) {
				bibEcho.setGenericForeGround(col,ci);
			}

		} else if (c instanceof Row) {
			for (Component ci : ((Row)c).getComponents()) {
				bibEcho.setGenericForeGround(col,ci);
			}
		} else {
			c.setForeground(col);
		}
	}

	
	
}
