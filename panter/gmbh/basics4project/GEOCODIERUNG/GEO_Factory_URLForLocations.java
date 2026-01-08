/**
 * panter.gmbh.basics4project.GEOCODIERUNG
 * @author manfred
 * @date 17.10.2018
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import panter.gmbh.basics4project.ENUM_FIELD_TYP;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 17.10.2018
 * Paramterzeile der Seite ist aktuell: ?p=<lat>,<lon>$<Text>$<color>&p=<lat>,<lon>$<Text>$<color>&......
 * 
 * Bsp: http://0.0.0.0/show_route_p.html?p=49.531049,8.081388$Point 1$red$&p=48.438800,8.530367$Point 2$grey$&p=48.438800,6.530367$Point c$yellow$&p=47.438800,9.530367$Point 3$green&zoom=16
 *  
 */
public class GEO_Factory_URLForLocations {
	
	private Vector<GEO_Coordinate4Map> _vCoord = new Vector<>();
	
	private String sServerUrl = "";
	
	private ENUM_Popup_Settings _ShowPopups = ENUM_Popup_Settings.KEIN_POPUP_BEIM_START;
	
	/**
	 * Settings, wie die Popups angezeigt werden sollen
	 * @author manfred
	 * @date 18.10.2018
	 *
	 */
	public enum ENUM_Popup_Settings {
		KEIN_POPUP_BEIM_START("Kein Popup","0")
		,EIN_POPUP_BEIM_START_CLOS_ON_ANY_CLICK("Ein Popup beim laden zeigen, close on any click","1")
		,ALLE_POPUP_BEIM_START_CLOSE_ON_ANY_CLICK("Alle Popup beim laden zeigen, close on any click","2")
		,EIN_POPUP_BEIM_START_CLOSE_ON_CLICK("Alle Popup beim laden zeigen, close on click","3")
		,ALLE_POPUP_BEIM_START_CLOSE_ON_CLICK("Alle Popup beim laden zeigen, close on click","4")
		;
		
		private String _name;
		private String _value;
		
		private ENUM_Popup_Settings(String name, String value) {
			_name 	= name;
			_value 	= value;
		}
		public String getURLString(){
			return ("&showpopups="+ _value);
		}
	}
	
	
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 17.10.2018
	 *
	 */
	public GEO_Factory_URLForLocations() throws myException {
		sServerUrl = ENUM_MANDANT_CONFIG.URL_GEO_SERVER.getUncheckedValue();

	}
	
	
	
	public ENUM_Popup_Settings get_ShowPopups() {
		return _ShowPopups;
	}

	public GEO_Factory_URLForLocations set_ShowPopups(ENUM_Popup_Settings ShowPopups) {
		this._ShowPopups = ShowPopups;
		return this;
	}
	
	
	
	/**
	 * fügt einen Punkt für die Karte dazu, nur wenn lat und long vorhanden sind
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @param coord
	 */
	public void addGeoCoordiante (GEO_Coordinate4Map coord){
		if (S.isAllFull(coord._lat,coord._lng)){
			_vCoord.add(coord);
		}
	}
	
		
	public String getURL4Points() throws myException{
		return getURL(false);
	}
	
	
	public String getURL4Route() throws myException{
		return getURL(true);
	}
	
	
	/**
	 * gibt es koordinaten?
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @return
	 */
	public boolean bHasPoints(){
		return _vCoord.size() > 0;
	}
	
	
	/**
	 * Liste der Koordinaten
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @return
	 */
	public Vector<GEO_Coordinate4Map> getCoordinates(){
		return _vCoord;
	}
	
	
	/**
	 * String zusammensetzen...
	 * @author manfred
	 * @date 17.10.2018
	 *
	 * @param bShowRoute
	 * @return
	 * @throws myException
	 * @throws UnsupportedEncodingException
	 */
	private String getURL(boolean bShowRoute) throws myException{
		
		String page = bShowRoute ?ENUM_MANDANT_CONFIG.URL_GEO_SHOW_ROUTE.getUncheckedValue(): ENUM_MANDANT_CONFIG.URL_GEO_SHOW_POINTS.getUncheckedValue();
		
		// <key:value> paare aus der Url schmeissen. Die braucht man erst später
		if (page.contains("<")){
			page = page.substring(0, page.indexOf("<"));
		}
		
		
		// page und server-URL normieren 
		if (!page.startsWith("/")) page = "/" + page;
		if (!page.endsWith("?")) page = page + "?";
		
		if (sServerUrl.endsWith("/") ){
			sServerUrl = sServerUrl.substring(0, sServerUrl.length()-1);
		}
		
		
		String sUrl = "";
		
		
		for (GEO_Coordinate4Map c : _vCoord){
			sUrl += "&" + c.getUrlText();
		}
		
		
		// das erste & entfernen und codieren
		sUrl = sUrl.substring(1);
		
		String sURI_ori = sServerUrl + page + sUrl + _ShowPopups.getURLString();
		String sURI_enc = sURI_ori;
		
		try {
            String scheme = null;
            String ssp = sURI_ori;
            int es = sURI_ori.indexOf(':');
            if (es > 0) {
                scheme = sURI_ori.substring(0, es);
                ssp = sURI_ori.substring(es + 1);
            }
            sURI_enc = (new URI(scheme, ssp, null)).toString();
            
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// falls es ein Problem mit dem Encoden gibt, versuchen wir die ORiginale URI durchzuwinken.
			sURI_enc = sURI_ori;
		}
		
		return sURI_enc;
		
		

	}


	
	
	
}
