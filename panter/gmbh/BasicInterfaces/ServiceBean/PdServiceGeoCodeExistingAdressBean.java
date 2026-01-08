/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoCodeExistingAdress;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_Error;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_NominatimService;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PdServiceGeoCodeExistingAdressBean implements PdServiceGeoCodeExistingAdress {

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.Service.GeoCodeExistingAdress#getLocation(java.lang.Long, panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap)
	 */
	@Override
	public GEO_Location getLocation(Long idAdress, GEO_ErrorMap map) throws myException {
		
		Rec20 rc = new Rec20(_TAB.adresse)._fill_id(idAdress);
		GEO_Location loc = new GEO_NominatimService()._generateNominatimUrls(rc, map).findGeoLocation(map);
		
		if (loc != null) {
			//dann erfolgreich
			if (    loc.get_latitude().getUfRounded(8).equals(rc.getMyBdOld(ADRESSE.latitude, new MyBigDecimal(0)).getUfRounded(8)) 
					&&  loc.get_longitude().getUfRounded(8).equals(rc.getMyBdOld(ADRESSE.longitude, new MyBigDecimal(0)).getUfRounded(8))){	
				    map._add(ENUM_GEO_Error.OK_ALREADY_EXISTING, idAdress.toString());
			} else {
				map._add(ENUM_GEO_Error.OK, idAdress.toString());
			}
		}
		
		return loc;
	}

	
	
}
