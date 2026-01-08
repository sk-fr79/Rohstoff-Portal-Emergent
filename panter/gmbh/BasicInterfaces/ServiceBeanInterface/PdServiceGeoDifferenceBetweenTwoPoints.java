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
public interface PdServiceGeoDifferenceBetweenTwoPoints {
	
	/**
	 * 
	 * @param point1Latitude
	 * @param point1Longitude
	 * @param point2Latitude
	 * @param point2Longitude
	 * @return
	 * @throws myException
	 */
	public BigDecimal differenceInMeters(BigDecimal point1Latitude, BigDecimal point1Longitude, BigDecimal point2Latitude, BigDecimal point2Longitude) throws myException;
	
}
