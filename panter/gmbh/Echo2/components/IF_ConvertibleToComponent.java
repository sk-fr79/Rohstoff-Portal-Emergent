/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 02.01.2020
 * 
 */
package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Component;

/**
 * @author martin
 * @date 02.01.2020
 *
 */
public interface IF_ConvertibleToComponent {
	
	public default Component component() {
		return (Component) this;
	}
	
}
