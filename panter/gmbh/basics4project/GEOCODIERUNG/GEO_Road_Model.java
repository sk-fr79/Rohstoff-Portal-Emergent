package panter.gmbh.basics4project.GEOCODIERUNG;

import java.math.BigDecimal;

import panter.gmbh.indep.MyBigDecimal;

public class GEO_Road_Model {
	private String distance = null;
	private String time ="";
	private String descend = "";
	private String ascend="";
	private String bbox="";

	private String weight="";
	private String points="";
	private boolean points_encoded=false;
	private String transfers ="";
	private String legs= "";
	private String details="";
	private String snapped_waypoints="";
	
	private String abfrage ="";
	
	/** weg benutz fur tile server */
	private String geometries = "";
	
	/**
	 * return the distance in kilometers.
	 * @return
	 */
	public String get_entfernung() {
		return MyBigDecimal.divide(new BigDecimal(this.distance),new BigDecimal(1000),0).toString();
	}
	
	/**
	 * return the distance in kilometers.
	 * @return
	 */
	public BigDecimal get_bd_entfernung() {
		return MyBigDecimal.divide(new BigDecimal(this.distance),new BigDecimal(1000),2);
	}
	
	public String get_time() {
		return time;
	}
	
	public String get_descend() {
		return descend;
	}
	
	public String get_ascend() {
		return ascend;
	}
	
	public String get_bounding_box() {
		return bbox;
	}
	
	public String get_weight() {
		return weight;
	}
	
	public String get_points() {
		return points;
	}
	
	public boolean is_points_encoded() {
		return points_encoded;
	}
	
	public String get_transfers() {
		return transfers;
	}
	
	public String get_legs() {
		return legs;
	}
	
	public String get_details() {
		return details;
	}
	
	public String get_snapped_waypoints() {
		return snapped_waypoints;
	}
	public void set_distance(String distance) {
		this.distance = distance;
	}
	
	public void set_time(String time) {
		this.time = time;
	}
	
	public void set_descend(String descend) {
		this.descend = descend;
	}
	
	public void set_ascend(String ascend) {
		this.ascend = ascend;
	}
	
	public void set_bounding_box(String bbox) {
		this.bbox = bbox;
	}
	
	public void set_weight(String weight) {
		this.weight = weight;
	}
	
	public void set_points(String points) {
		this.points = points;
	}
	
	public void set_points_encoded(boolean points_encoded) {
		this.points_encoded = points_encoded;
	}
	
	public void set_transfers(String transfers) {
		this.transfers = transfers;
	}
	
	public void set_legs(String legs) {
		this.legs = legs;
	}
	
	public void set_details(String details) {
		this.details = details;
	}
	
	public void set_snapped_waypoints(String snapped_waypoints) {
		this.snapped_waypoints = snapped_waypoints;
	}
	
	@Override
	public String toString() {
		return "Distance=" + distance + "\nTime=" + time + "\nDescend=" + descend + "\nascend="
				+ ascend;
	}

	public String getAbfrage() {
		return abfrage;
	}

	public void setAbfrage(String abfrage) {
		this.abfrage = abfrage;
	}

	public String get_polyline() {
		return geometries;
	}

	public void set_polyline(String geometries) {
		this.geometries = geometries;
	}

	
	
}
