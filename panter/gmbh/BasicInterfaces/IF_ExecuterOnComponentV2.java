/**
 * panter.gmbh.BasicInterfaces
 * @author martin
 * @date 05.11.2019
 * 
 */
package panter.gmbh.BasicInterfaces;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;

/**
 * @author martin
 * @date 05.11.2019
 *
 */
public interface IF_ExecuterOnComponentV2<T extends Component> {
	
	public MyE2_MessageVector execute(T component);
	
}
