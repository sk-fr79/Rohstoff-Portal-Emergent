/**
 * panter.gmbh.basics4project.GEOCODIERUNG
 * @author manfred
 * @date 17.10.2018
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;

import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;

/**
 * Kapselt die Punkte die im Aufruf einer Webseite benötigt werden.
 * @author manfred
 * @date 17.10.2018
 *
 */

public class GEO_Coordinate4Map {
	String _lat = "";
	String _lng = "";
	String _text = "";
	ENUM_GEO_MARKER_COLOR _color = ENUM_GEO_MARKER_COLOR.BLUE;
	
	
	/**
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @param _lat
	 * @param _lng
	 * @param _text
	 * @param _color
	 */
	public GEO_Coordinate4Map(String _lat, String _lng, String _text, ENUM_GEO_MARKER_COLOR _color) {
		super();
		this._lat = _lat;
		this._lng = _lng;
		this._text = _text;
		this._color = _color;
	}

	
	public GEO_Coordinate4Map(BigDecimal _lat, BigDecimal _lng, String _text, ENUM_GEO_MARKER_COLOR _color) throws myException {
		super();
		if(_lat != null && _lng != null) {
			this._lat = MyNumberFormater.formatDez(_lat, 8, false, '.',null, false);
			this._lng = MyNumberFormater.formatDez(_lng, 8, false, '.',null, false);
			this._text = _text;
			this._color = _color;
		} else {
			throw new myException("Number Format Exception: Lat/Lng is null");
		}
	}


	public String get_lat() {
		return _lat;
	}
	
	public GEO_Coordinate4Map set_lat(String _lat) {
		this._lat = _lat;
		return this;
	}
	
	public GEO_Coordinate4Map set_lat(BigDecimal _lat) throws myException {
		if (_lat != null){
			this._lat =  MyNumberFormater.formatDez(_lat, 8, false, '.',null, false);;
			return this;
		} else {
			throw new myException("Number Format Exception");
		}
	}
	
	public String get_lng() {
		return _lng;
	}
	
	
	public GEO_Coordinate4Map set_lng(String _lng)  {
		this._lng = _lng;
		return this;
	}
	
	public GEO_Coordinate4Map set_lng(BigDecimal _lng) throws myException {
		if (_lng != null){
			this._lng =  MyNumberFormater.formatDez(_lng, 8, false, '.',null, false);;
			return this;
		} else {
			throw new myException("Number Format Exception");
		}
	}	
	
	public String get_text() {
		return _text;
	}
	public GEO_Coordinate4Map set_text(String _text) {
		this._text = _text;
		return this;
	}
	public String get_color() {
		return _color.db_val();
	}
	public GEO_Coordinate4Map set_color(ENUM_GEO_MARKER_COLOR _color) {
		this._color = _color;
		return this;
	}
	
	/**
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @return
	 */
	public String getUrlText() {
		
		String enc_text = "";
		try {
			enc_text = URLEncoder.encode(_text, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			
		}
		
		
		return "p="+_lat+","+_lng+"$"+enc_text+"$"+_color.db_val();

	}
}