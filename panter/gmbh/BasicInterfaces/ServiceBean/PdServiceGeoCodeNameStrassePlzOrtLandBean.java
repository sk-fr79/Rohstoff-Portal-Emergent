/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoCodeNameStrassePlzOrtLand;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Locations;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_NominatimService;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PdServiceGeoCodeNameStrassePlzOrtLandBean implements PdServiceGeoCodeNameStrassePlzOrtLand {

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.Service.GeoCodeExistingAdress#getLocation(java.lang.Long, panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap)
	 */
	@Override
	public GEO_Location getLocation(String idAdresse, String isoLaenderCode, String strasse, String hnr, String plz, String ort, GEO_ErrorMap map) throws myException {
		String id_adresse = S.NN(idAdresse,"0");
		GEO_Location loc = new GEO_NominatimService()
				._generateNominatimUrls(id_adresse,hnr,strasse,plz,ort,isoLaenderCode, map)
				.findGeoLocation(map);
		
		return loc;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoCodeNameStrassePlzOrtLand#getLocations(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap)
	 */
	@Override
	public GEO_Locations getLocations(String idAdresse, String isoLaenderCode, String strasse, String hnr, String plz, String ort,GEO_ErrorMap map) throws myException {
		String id_adresse = S.NN(idAdresse,"0");
		return new GEO_NominatimService()
				._generateNominatimUrls(id_adresse,hnr,strasse,plz,ort,isoLaenderCode, map)
				.findGeoLocations(map);
	}

	
	
	
}
