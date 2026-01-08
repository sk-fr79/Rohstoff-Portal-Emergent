/**
 * panter.gmbh.BasicInterfaces
 * @author martin
 * @date 05.11.2019
 * 
 */
package panter.gmbh.BasicInterfaces;

import nextapp.echo2.app.Component;

/**
 * @author martin
 * @date 05.11.2019
 * 
 * 
 * bsp.
 * 	
	private VEK<IF_ExecuterOnComponentV2<T>>    changeListeners = new   VEK<>();       
	
	public T _clearChangeListener() {
		this.changeListeners.clear();
		return this;
	}
	
	public T _addChangeListener(IF_ExecuterOnComponentV2<T> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}
	

 *
 */
public interface IF_HasChangeListeners<T extends Component> {
	public T  _clearChangeListener();
	public T _addChangeListener(IF_ExecuterOnComponentV2<T> changeListener);
}
