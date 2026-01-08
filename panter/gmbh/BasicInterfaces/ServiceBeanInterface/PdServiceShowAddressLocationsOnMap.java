/**
 * panter.gmbh.BasicInterfaces.ServiceBeanInterface
 * @author manfred
 * @date 03.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.util.Vector;

/**
 * @author manfred
 * @date 03.12.2018
 *
 */
public interface PdServiceShowAddressLocationsOnMap {
	
	/**
	 * Zeigt die Hauptadresse und die Lager-Adressen in der Map
	 * @author manfred
	 * @date 04.12.2018
	 *
	 * @param id_adresses
	 */
	public void showAddressLocationsOnMap(Vector<String> id_adresses);

	
	/**
	 * Zeigt die angegebene Adress-ID in der Map
	 * @author manfred
	 * @date 04.12.2018
	 *
	 * @param id_adress
	 */
	public void showSingleAddressLocationsOnMap(String id_adress);
	
}
