/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoDifferenceBetweenTwoPoints;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PdServiceGeoDifferenceBetweenTwoPointsBean implements PdServiceGeoDifferenceBetweenTwoPoints {

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.ServiceBeanInterface.GeoDifferenceBetweenTwoPoints#differenceInMeters(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal differenceInMeters(BigDecimal point1Latitude, BigDecimal point1Longitude,	BigDecimal point2Latitude, BigDecimal point2Longitude) throws myException {
		
		if (point1Latitude==null || point1Longitude==null || point2Latitude==null || point2Longitude==null) {
			throw new myException(this, "null is not allowed !");
		}
		
		double p1_lat = point1Latitude.doubleValue();
		double p1_lon = point1Longitude.doubleValue();
		double p2_lat = point2Latitude.doubleValue();
		double p2_lon = point2Longitude.doubleValue();

		double p1_lat_rad = Math.toRadians(p1_lat);
		double p2_lat_rad = Math.toRadians(p2_lat);

		double delta_phi = Math.toRadians(p2_lat-p1_lat);
		double delta_rho = Math.toRadians(p2_lon-p1_lon);

		double R = 6371;

		double a =  Math.sin(delta_phi/2) * Math.sin(delta_phi/2) 

				+ Math.cos(p1_lat_rad) * Math.cos(p2_lat_rad)* Math.sin(delta_rho/2)* Math.sin(delta_rho/2);

		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return new MyBigDecimal((R*b*1000), 8).get_bdWert();
	}

}
