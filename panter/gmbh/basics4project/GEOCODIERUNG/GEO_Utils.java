package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class GEO_Utils {

	/**
	 * 
	 * @param point_a
	 * @param point_b
	 * @return number kilometers
	 * @throws myException
	 */
	public static MyBigDecimal getDistanceInMeter(GEO_Point point_a, GEO_Point point_b) throws myException{

		double p1_lat = point_a.get_latitude().get_bdWert().doubleValue();
		double p1_lon = point_a.get_longitude().get_bdWert().doubleValue();
		double p2_lat = point_b.get_latitude().get_bdWert().doubleValue();
		double p2_lon = point_b.get_longitude().get_bdWert().doubleValue();

		double p1_lat_rad = Math.toRadians(p1_lat);
		double p2_lat_rad = Math.toRadians(p2_lat);

		double delta_phi = Math.toRadians(p2_lat-p1_lat);
		double delta_rho = Math.toRadians(p2_lon-p1_lon);

		double R = 6371e3;

		double a =  Math.sin(delta_phi/2) * Math.sin(delta_phi/2) 

				+ Math.cos(p1_lat_rad) * Math.cos(p2_lat_rad)* Math.sin(delta_rho/2)* Math.sin(delta_rho/2);

		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return new MyBigDecimal((R*b)/1000, 8);

	}

	public static MyE2_MessageVector check_coordinate(MyBigDecimal bd_latitude, MyBigDecimal bd_longitude) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();

		int max_east 	= 41;
		int max_west 	= -11;
		int max_north 	= 70;
		int max_south	= 35;

		if(bd_latitude.get_cErrorCODE().equals(MyBigDecimal.ERROR_NULLSTRING)){
			mv._addAlarm("Latitude : ERROR_NULLSTRING");
			return mv;
		}

		if(bd_longitude.get_cErrorCODE().equals(MyBigDecimal.ERROR_NULLSTRING)){
			mv._addAlarm("Longitude : ERROR_NULLSTRING");
			return mv;
		}

		if(!bd_latitude.get_bOK()) {
			mv._addAlarm(bd_latitude.get_FormatedRoundedNumber(8) + " Breite ist kein Zahl");
		}
		if(!bd_longitude.get_bOK()) {
			mv._addAlarm(bd_longitude.get_FormatedRoundedNumber(8) + " Laenge ist kein Zahl");
		}

		if(mv.get_bIsOK()) {
			boolean is_coor_ok = 
					(bd_latitude.get_longValue()>max_west) && (bd_latitude.get_longValue()<max_east) ||
					(bd_longitude.get_longValue()<max_north) && (bd_longitude.get_longValue()>max_south) ;
			if(! is_coor_ok){
				mv._addAlarm("["
						+bd_latitude.get_FormatedRoundedNumber(8)+ ";"
						+bd_longitude.get_FormatedRoundedNumber(8)
						+ "]: befindet sich nicht im Kontinentaleuropa");
			}
		}

		return mv;
	}

	public static MyE2_MessageVector check_latitude(MyBigDecimal bd_latitude) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if(!bd_latitude.get_cErrorCODE().equals(MyBigDecimal.ERROR_NULLSTRING)){
			if(!bd_latitude.get_bOK()) {
				mv._addAlarm(bd_latitude.get_FormatedRoundedNumber(8) + " Laenge ist kein Zahl");
			}else {

				int maxEast = 41;
				int maxWest = -11;
				if((bd_latitude.get_longValue()>maxWest) && (bd_latitude.get_longValue()<maxEast)) {

				}else {
					mv._addAlarm(bd_latitude.get_FormatedRoundedNumber(8) + ": befindet sich nicht im Kontinentaleuropa");
				}
			}
		}else {
			mv._addAlarm("ERROR_NULLSTRING");
		}
		return mv;
	}

	public static MyE2_MessageVector check_longitude(MyBigDecimal bd_longitude) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if(!bd_longitude.get_cErrorCODE().equals(MyBigDecimal.ERROR_NULLSTRING)){
			if(!bd_longitude.get_bOK()) {
				mv._addAlarm(bd_longitude.get_FormatedRoundedNumber(8) + " Laenge ist kein Zahl");
			}else {
				int maxNorth = 70;
				int maxSouth = 35;

				if( !(bd_longitude.get_longValue()<maxNorth) && (bd_longitude.get_longValue()>maxSouth) ){
					mv._addAlarm(bd_longitude.get_FormatedRoundedNumber(8) + ": befindet sich nicht im Kontinentaleuropa");
				}
			}
		}else {
			mv._addAlarm("ERROR_NULLSTRING");
		}
		return mv;
	}
	
	


	
	public static String normalizeOrt(String ort) {
		ort = ort.replace("/", " ");
		ort = ort.replace("-", " ");
		ort = ort.replace(".", " ");
		ort = GEO_Utils.replaceUmlauteToUpper(ort);

		ort = S.clean(ort);
		return ort;
	}


	public static String normalizeStreet(String strasse) {
		strasse = GEO_Utils.replaceUmlauteToUpper(strasse);
		strasse += " ";  // wegen der möglichkeit, dass "Scheffelstr"
		strasse = strasse.replace("STR.", "STRASSE ");
		strasse = strasse.replace("STR ", "STRASSE ");
		strasse = strasse.replace("STRAßE", "STRASSE");
		strasse=S.clean(strasse);
		return strasse;
	}


	public static String replaceUmlauteToUpper(String orig) {
		String ret = orig;
		ret = ret.toUpperCase();
		ret = ret.replace("ä", "AE");
		ret = ret.replace("ö", "OE");
		ret = ret.replace("ü", "UE");
		ret = ret.replace("Ä", "AE");
		ret = ret.replace("Ö", "OE");
		ret = ret.replace("Ü", "UE");
		ret = ret.replace("ß", "SS");
		return ret;
	}

	/**
	 * angaben 10-12 werden zu 10
	 * @param hnr
	 * @return
	 */
	public static String normalizeHausnr(String hnr) {
		if (hnr.contains("-")) {
			hnr = hnr.substring(0,hnr.indexOf("-"));
		}
		hnr=S.clean(hnr);
		hnr = hnr.replace(" ", "");
		
		return hnr;
	}
	
	
	
	
}
