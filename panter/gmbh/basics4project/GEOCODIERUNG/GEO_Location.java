package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class GEO_Location {
	private String 			osm_id 		= 		"";
	private String 			place_id 	= 		"";
	private String 			osm_type 	= 		"";
	private MyBigDecimal 	bd_latitude = 		null;
	private MyBigDecimal 	bd_longitude= 		null;

	private String strasse 				= 		"";
	private String ort 					= 		"";
	private String stadt 				=		"";
	private String dorf					=		"";
	private String plz 					= 		"";
	private String hausnummer 			=		"";
	private String land 				= 		"";
	private String land_code 			= 		"";
	private String city	 				=		"";
	
	// Gewichtung, z.B. durch Ermittelung aus der Summe der Übereinstimmungen der Suchanfrage, von extern gesetzt
	private Integer weight				= 		null;

	//SETTER
	public GEO_Location _set_osm_id(String osm_id) throws myException{
		this.osm_id = osm_id;
		return this;
	}

	public GEO_Location _set_place_id(String place_id) throws myException{
		this.place_id = place_id;
		return this;
	}

	public GEO_Location _set_osm_type(String osm_type) throws myException{
		this.osm_type = osm_type;
		return this;
	}

	public GEO_Location _set_latitude(String str_latitude) throws myException{
		this.bd_latitude = new MyBigDecimal(str_latitude.replace(".", ","));
		return this;
	}

	public GEO_Location _set_longitude(String str_longitude) throws myException{
		this.bd_longitude = new MyBigDecimal(str_longitude.replace(".", ","));
		return this;
	}

	public GEO_Location _set_strasse(String strasse) throws myException{
		this.strasse = strasse;
		return this;
	}

	public GEO_Location _set_ort(String ort) throws myException{
		this.ort = ort;
		return this;
	}

	public GEO_Location _set_stadt(String stadt) throws myException{
		this.stadt = stadt;
		return this;
	}

	public GEO_Location _set_dorf(String dorf) throws myException{
		this.dorf = dorf;
		return this;
	}

	public GEO_Location _set_city(String city) {
		this.city = (city);
		return this;
	}
	
	public GEO_Location _set_plz(String plz) throws myException{
		this.plz = plz;
		return this;
	}

	public GEO_Location _set_hausnummer(String hausnummer) throws myException{
		this.hausnummer = hausnummer;
		return this;
	}

	public GEO_Location _set_land(String land) throws myException{
		this.land = land;
		return this;
	}

	public GEO_Location _set_land_code(String land_code) throws myException{
		this.land_code = land_code;
		return this;
	}


	/**
	 * gibt die berechnete Suchgewichtung zurück, default (wenn null) = 0
	 * Muss von aussen gesetzt werden
	 * @author manfred
	 * @date 14.11.2018
	 *
	 * @return
	 */
	public Integer get_weight() {
		return (weight == null? 0 : weight) ;
	}

	public GEO_Location set_weight(Integer rang) {
		this.weight = rang;
		return this;
	}

	
	
	//GETTER

	public String get_osm_id() {
		return osm_id;
	}

	public String get_place_id() {
		return place_id;
	}

	public String get_osm_type() {
		return osm_type;
	}

	public MyBigDecimal get_latitude() {
		return bd_latitude;
	}

	public MyBigDecimal get_longitude() {
		return bd_longitude;
	}

	public String get_strasse() {
		return strasse;
	}
	public String get_ort() {
		if(S.isFull(this.dorf)) {
			return this.dorf;
		}else if (S.isFull(this.stadt)) {
			return this.stadt;
		} else if (S.isFull(this.city)) {
			return this.city;
		}else {
			return ort;
		}
	}

	public String get_city() {
		return this.city;
	}
	
	public String get_plz() {
		return plz;
	}
	public String get_hausnummer() {
		return hausnummer;
	}

	public String get_land() {
		return land;
	}

	public String get_land_code() {
		return land_code;
	}

	public String get_coordinate_4_graphhopper(){
		return bd_latitude+","+bd_longitude;
	}
	
	public GEO_Point get_coordinate(){
		return new GEO_Point(bd_latitude, bd_longitude);
	}
	
	@Override
	public String toString() {
		return "GEO_Result_Model\n{osm_id=" + osm_id + ", place_id=" + place_id + ", osm_type=" + osm_type +"}"
				+ "\n\tlatitude= " + 	bd_latitude.get_FormatedRoundedNumber(8)  
				+ "\n\tlongitude= " + 	bd_longitude.get_FormatedRoundedNumber(8) 
				+ "\n\tstrasse= " + 	strasse
				+ "\n\tDorf= " + 		dorf
				+ "\n\tStadt= " + 		stadt
				+ "\n\tort= " + 		get_ort ()
				+ "\n\tcity= " + 		city
				+ "\n\tplz= " + 		plz 
				+ "\n\thausnummer= " + 	hausnummer 
				+ "\n\tland= " + 		land 										+ "("+ land_code + ")";
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 16.10.2018
	 *
	 * @return text mit strasse, hnr, plz ort
	 */
	public String getShortTextInfo(boolean showCoordinates) {
		String c = "";
		if (S.isFull(this.get_strasse())) {
			c=c+this.get_strasse()+" ";
		}
		
		if (S.isFull(this.get_hausnummer())) {
			c=c+this.get_hausnummer();
		}
		if (S.isFull(this.get_strasse()) || S.isFull(this.get_hausnummer()) ) {
			c=c+", ";
		}
		
		if (S.isFull(this.get_plz())) {
			c=c+this.get_plz()+" ";
		}

		if (S.isFull(this.get_ort())) {
			c=c+this.get_ort()+" ";
		}
		
		if (showCoordinates) {
			c=c+" ... ("+this.get_latitude().get_UnFormatedRoundedNumber(7)+" , "+this.get_longitude().get_UnFormatedRoundedNumber(7)+")";
		}
		
		return c;
	}



}
