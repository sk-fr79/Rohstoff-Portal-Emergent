/**
 * panter.gmbh.BasicInterfaces.ServiceBeanInterface
 * @author manfred
 * @date 10.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.util.Vector;

/**
 * @author manfred
 * @date 10.12.2018
 *
 */
public interface PdServiceShowRoutingBetweenAddresses {

	
	/**
	 * Zeigt die Route zwischen mindestens 2 Adressen an
	 * @author manfred
	 * @date 10.12.2018
	 *
	 * @param id_adresses
	 */
	public void showRoutingBetweenAddresses(Vector<String> id_adresses);
	
	
}
