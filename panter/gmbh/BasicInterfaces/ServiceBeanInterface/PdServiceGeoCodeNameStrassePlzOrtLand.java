/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Locations;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface PdServiceGeoCodeNameStrassePlzOrtLand {

	/**
	 * 
	 * @param idAdress
	 * @param map
	 * @return singular geoLocation
	 * @throws myException
	 */
	public GEO_Location getLocation(String idAdresse, String land, String strasse, String hnr, String plz, String ort, GEO_ErrorMap map) throws myException;
	
	public GEO_Locations getLocations(String idAdresse, String land, String strasse, String hnr, String plz, String ort, GEO_ErrorMap map) throws myException;
	
	
}
