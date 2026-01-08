/**
 * @author sebastien
 * @date 02.04.2019
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Collections;

import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Locations;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class PdServiceFindNearestPtFromGeoMidPt {
	
	/**
	 * http://www.geomidpoint.com/calculation.html</br>
	 * [@ §A. Geographic midpoint]
	 * 
	 * @author sebastien
	 * @date 01.04.2019
	 *
	 * @return Nearest GEO_Location from geographic midpoint 
	 * @throws myException
	 */
	public GEO_Location find_nearest_point_from_geographic_midpoint(GEO_Locations vLocs) throws myException{

		VEK<Double[]> vCartCoor = new VEK<>();

		VEK<Double[]>coorHm 		= new VEK<Double[]>();
		VEK<Double> entfernungHm 	= new VEK<Double>();

		for(GEO_Location loc: vLocs) {

			Double latRad = (loc.get_latitude().get_bdWert().doubleValue())*(Math.PI/180);
			Double longRad = (loc.get_longitude().get_bdWert().doubleValue())*(Math.PI/180);

			vCartCoor._a(new Double[] {
					Math.cos(latRad)*Math.cos(longRad),
					Math.cos(latRad)*Math.sin(longRad),
					Math.sin(latRad)}
			);

			coorHm._a(new Double[] {
					loc.get_latitude().get_bdWert().doubleValue(),
					loc.get_longitude().get_bdWert().doubleValue()}
			);

			entfernungHm._a(0d);

		}

		double x = 0;
		double y = 0;
		double z = 0;
		for(Double[] cartcoor: vCartCoor) {
			x = x + cartcoor[0];
			y = y + cartcoor[1];
			z = z + cartcoor[2];
		}

		x = x /(vLocs.size());
		y = y /(vLocs.size());
		z = z /(vLocs.size());

		double Lon = Math.atan2(y,x);
		double hyp = Math.sqrt(x*x+y*y);
		double Lat = Math.atan2(z,hyp);

		double middle_lat = (Lat)*(180/Math.PI);

		double middle_lon = (Lon)*(180/Math.PI);

		for(Double[] w : coorHm) {
			double entfernung = distance_calculation(middle_lat, middle_lon, w[0], w[1]);
			if(entfernung<0.51) {
				entfernungHm._a(entfernung);
			}
		}

		int min_index = entfernungHm.indexOf(Collections.min(entfernungHm));

		return vLocs.get(min_index);
	}

	private double distance_calculation(double lat1, double long1, double lat2, double long2)
	{
		double d2r = (Math.PI / 180);

		double dlong = (long2 - long1) * d2r;
		double dlat = (lat2 - lat1) * d2r;
		double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(lat1*d2r) * Math.cos(lat2*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = 6367 * c;

		return d;
	}

}
