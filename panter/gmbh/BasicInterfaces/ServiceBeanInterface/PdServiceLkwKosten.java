/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface PdServiceLkwKosten {
	/**
	 * 
	 * @param kfzKennzeichenOrId or idMaschinen 
	 * @return s sum of all costsets with type km
	 * @throws myException
	 */
	public BigDecimal getKostenProKm(String kfzKennzeichenOrId) throws myException;
}
