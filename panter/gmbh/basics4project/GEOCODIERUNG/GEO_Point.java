package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.indep.MyBigDecimal;

public class GEO_Point {
	private MyBigDecimal bd_latitude;
	private MyBigDecimal bd_longitude;
	
	public GEO_Point(MyBigDecimal p_bd_latitude, MyBigDecimal p_bd_longitude) {
		this.bd_latitude 	= p_bd_latitude;
		this.bd_longitude 	= p_bd_longitude;
	}

	public MyBigDecimal get_latitude() {
		return bd_latitude;
	}

	public MyBigDecimal get_longitude() {
		return bd_longitude;
	}
	
	
}
