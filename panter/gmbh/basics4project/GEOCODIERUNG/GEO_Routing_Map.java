package panter.gmbh.basics4project.GEOCODIERUNG;

import java.util.TreeMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class GEO_Routing_Map extends TreeMap<Integer, VEK<MyBigDecimal>>{

	public MyE2_MessageVector mv;

	private String locale = "de";

	private static final String VEHICLE_TYPE 	= "car";
	private static final String OUTPUT_FORMAT 	= "JSON";
	private static final boolean INSTRUCTIONS 	= false;
	private static final boolean DETAILS 		= false;

	public GEO_Routing_Map() {
		super();
		this.mv = new MyE2_MessageVector();
		this.clear();
	}


	/**
	 * Add of a vector with following value (in order): breite, laenge
	 * @param breite: latitude
	 * @param laenge: longitude
	 * @return
	 * @throws myException
	 */
	public GEO_Routing_Map _add_adresse_coordinates(MyBigDecimal breite, MyBigDecimal laenge) throws myException {
		int order_idx = this.keySet().size();
		VEK<MyBigDecimal> value_vektor = new VEK<>();
		value_vektor._a(new VEK<MyBigDecimal>()._a(breite)._a(laenge));
		this.put(order_idx, value_vektor);
		return this;
	}

	
	/**
	 * Add of a vector with following value (in order): breite, laenge, id_adresse
	 * @param breite: latitude
	 * @param laenge: longitude
	 * @param id_adresse	
	 * @return
	 * @throws myException
	 */
	public GEO_Routing_Map _add_adresse_coordinates(String breite, String laenge, String id_adresse) throws myException {
		int order_idx = this.keySet().size();
		VEK<MyBigDecimal> value_vektor = new VEK<>();
		value_vektor._a(new VEK<MyBigDecimal>()._a(new MyBigDecimal(breite))._a(new MyBigDecimal(laenge))._a(new MyBigDecimal(id_adresse)));
		this.put(order_idx, value_vektor);
		return this;
	}

	public String get_abfrage_fuer_graphhoper() {

		if(this.size()<2) {
			mv._addAlarm("");
		}else {
			StringBuffer points_list = new StringBuffer("q=");
			for(Integer key: this.keySet()) {

				points_list.append("&point=");
				points_list.append(this.get(key).get(1));
				points_list.append(this.get(key).get(0));
			}

			StringBuffer abfrage_buffer = new StringBuffer();
			abfrage_buffer.append("route?");
			abfrage_buffer.append(points_list)									.append("&");
			abfrage_buffer.append("vehicle="+VEHICLE_TYPE)						.append("&");
			abfrage_buffer.append("locale="+locale)								.append("&");
			abfrage_buffer.append("detail="+(DETAILS?"true":"false"))			.append("&");
			abfrage_buffer.append("type="+OUTPUT_FORMAT)						.append("&");
			abfrage_buffer.append("instructions="+(INSTRUCTIONS?"true":false))	.append("");

			return abfrage_buffer.toString();
		}

		return "";
	}

	/**
	 * baut eine OSRM Abfrage<br/>
	 * 
	 * <u>Abfrage form</u>: "/{service}/{version}/{profile}/{coordinates}[.{format}]?option=value&option=value"</br>
	 * <br>
	 * <i>service:</i>
	 * <i>version:</i>
	 * <i>profile:</i>
	 * <i>coordinates</i>
	 * @return
	 */
	public String get_abfrage_fuer_osrm() {

		int idx_breite = 0;
		int idx_laenge = 1;
		
		if(this.size()<2) {
			mv._addAlarm("");
		}else {
			StringBuffer abfrage_buffer 	= new StringBuffer("/");
			StringBuffer coordinates_list 	= new StringBuffer();
			
			for(Integer key: this.keySet()) {
				VEK<MyBigDecimal> vCoor = this.get(key);
				
				coordinates_list
				.append(vCoor.get(idx_laenge).get_UnFormatedRoundedNumber(8))
				.append(",")
				.append(vCoor.get(idx_breite).get_UnFormatedRoundedNumber(8))
				;
				if((this.keySet().size()-1) != key) {
					coordinates_list.append(";");
				}
			}
			
			abfrage_buffer
			.append("route").append("/")
			.append("v1").append("/")
			.append("driving").append("/")
			.append(coordinates_list)
			;
			return abfrage_buffer.toString();
		}

		return "";
	}
}