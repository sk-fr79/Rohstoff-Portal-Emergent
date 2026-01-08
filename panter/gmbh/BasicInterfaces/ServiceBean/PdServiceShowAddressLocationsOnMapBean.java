/**
 * panter.gmbh.BasicInterfaces.ServiceBean
 * @author manfred
 * @date 03.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceShowAddressLocationsOnMap;
import panter.gmbh.BasicTools.StringParser.PdParser;
import panter.gmbh.BasicTools.StringParser.PdParserResult;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_MARKER_COLOR;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Coordinate4Map;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Factory_URLForLocations;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author manfred
 * @date 03.12.2018
 *
 */
public class PdServiceShowAddressLocationsOnMapBean implements PdServiceShowAddressLocationsOnMap {

	Vector<String> _vids = new Vector<>();
	
	
	@Override
	public void showAddressLocationsOnMap(Vector<String> id_adresses) {
		_vids = id_adresses;
		
		try {
			this.showBrowserWindow();
		} catch (myException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showSingleAddressLocationsOnMap( String id_adress) {
		_vids.add(id_adress);
		
		try {
			this.showBrowserWindow();
		} catch (myException e) {
			e.printStackTrace();
		}
	}
	
	
	private PdServiceShowAddressLocationsOnMap showBrowserWindow( ) throws myException {
		
		// binden der Factory
		GEO_Factory_URLForLocations urlFactory = getURLFactory();
		
		
		if (urlFactory == null || !urlFactory.bHasPoints()){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Es wurden keine Koordinaten angegeben.")));
			return this;
		}
		
		
		// lesen der URL
		String sURL = urlFactory.getURL4Points();
		
		////
		String sPageUrl =  ENUM_MANDANT_CONFIG.URL_GEO_SHOW_POINTS.getUncheckedValue();
		
		if (S.isEmpty(sPageUrl)){
			throw new myException("Fehler bei der Konfiguration der URL in den Mandanten-Daten.");
		}
		
		int _width = 1000;
		int _height= 800;
		int _showpopups = 0;
		
		// width-Block rausschneinden...
		PdParserResult resWidth =  new PdParser().parse(sPageUrl, "width");
		if (resWidth != null && S.isFull(resWidth.value)) {
			_width = new MyLong(resWidth.value).get_iValue();
		}
		// height-Block rausschneiden...
		PdParserResult resHeight= new PdParser().parse(sPageUrl, "height");
		if (resHeight != null && S.isFull(resHeight.value)) {
			_height = new MyLong(resHeight.value).get_iValue();
		}
		
		// height-Block rausschneiden...
		PdParserResult showpopup= new PdParser().parse(sPageUrl, "showpopups");
		if (showpopup != null && S.isFull(showpopup.value)) {
			_showpopups = new MyLong(showpopup.value).get_iValue();
		}

		if (_showpopups > 0){
			sURL += String.format("&showpopups=%d",_showpopups);
		}
		

		ApplicationInstance.getActive().enqueueCommand(
				new BrowserOpenWindowCommand(sURL,"Kartendarstellung",String.format("width=%d,height=%d",_width,_height)));
		
		
		return this;
		
	}

	


	
	
	private GEO_Factory_URLForLocations getURLFactory() throws myException {

		GEO_Factory_URLForLocations factory = null;
		if (_vids != null && _vids.size() > 0){
			
			factory = new GEO_Factory_URLForLocations();
			Rec21_adresse r;
			try {
				
				for (String id:_vids){
					r = new Rec21_adresse()._fill_id( id );
					if (r != null) {
						BigDecimal lat = r.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal lng = r.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						String text = r.get_ufs_kette(",", true,  ADRESSE.name1, ADRESSE.name2 ) + " (" + r.get_ufs_dbVal(ADRESSE.id_adresse) + ")";
						if (lat == null || lng == null)
							continue;
						
							try {
								GEO_Coordinate4Map c = new GEO_Coordinate4Map(lat, lng, text, ENUM_GEO_MARKER_COLOR.GREEN);
								factory.addGeoCoordiante(c);
							} catch (myException e) {
								// fehler beim der Koordinatenumwandlung
							}
						}
					}
					
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return factory;
	}
	

	
	

}
