package panter.gmbh.BasicInterfaces.ServiceBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONException;

import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoRoutingEntfernungUndKosten;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Road_Model;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Routing_JSON_Parser;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Routing_Map;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class PdServiceGeoRoutingEntfernungUndKostenBean implements PdServiceGeoRoutingEntfernungUndKosten {

	
	@Override
	public HashMap<ENUM_ROUTING_TYP, BigDecimal> getDistanceTimeAmdCosts(	BigDecimal 	startLaditude
																,BigDecimal startLongitude
																,BigDecimal zielLaditude
																,BigDecimal zielLongitude
																,String     kfzKennzeichenLkw
																,String     kfzKennzeichenAnhaenger
																,MyE2_MessageVector mv
																) throws myException {
		
		if (O.isOneNull(startLaditude,startLongitude,zielLaditude,zielLongitude)) {
			mv._addAlarm("Geokodierung ist nicht vollständig!");
			return null;
		}
		
		
		HashMap<ENUM_ROUTING_TYP,BigDecimal> response_map = 
				new HashMap<ENUM_ROUTING_TYP,BigDecimal>(){{
					put(ENUM_ROUTING_TYP.ENTFERNUNG,null);
					put(ENUM_ROUTING_TYP.ZEIT_SEK,null);
					put(ENUM_ROUTING_TYP.ZEIT_MIN,null);
					put(ENUM_ROUTING_TYP.KOSTEN_KM,null);
				}};
		
		GEO_Routing_Map routing_instance = new GEO_Routing_Map();
		
		//start punkt
		routing_instance._add_adresse_coordinates(new MyBigDecimal(startLaditude),new MyBigDecimal(startLongitude));
		
		//ziel punkt
		routing_instance._add_adresse_coordinates(new MyBigDecimal(zielLaditude),new MyBigDecimal(zielLongitude));
		
		if(routing_instance.size()==2) {
			
			String osrm_server_adresse = ENUM_MANDANT_CONFIG.OSRM_LKW_URL.getUncheckedValue();
			
			String abfrage_osrm = routing_instance.get_abfrage_fuer_osrm();
			
			try {
				URL osrm_url = new URL(osrm_server_adresse+abfrage_osrm);
				
				URLConnection osrm_connection = osrm_url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(osrm_connection.getInputStream()));

				String inputLine;

				StringBuffer buff = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					buff.append(inputLine);
				}
				VEK<GEO_Road_Model> parser_response = null;
				
				if(buff !=null && buff.length()>0) {
					parser_response = new GEO_Routing_JSON_Parser().parse_osrm_response(buff);
				}
				
				if(parser_response!= null && parser_response.size()>0) {
					response_map
					.put(ENUM_ROUTING_TYP.ENTFERNUNG, parser_response.get(0).get_bd_entfernung());
					
					response_map
					.put(ENUM_ROUTING_TYP.ZEIT_SEK, new BigDecimal(parser_response.get(0).get_time()));
					
					response_map
					.put(ENUM_ROUTING_TYP.ZEIT_MIN, 
							MyBigDecimal.divide(
							new MyBigDecimal(parser_response.get(0).get_time().replace(".", ",")).get_bdWert(),
							new BigDecimal("60"),
							2));
					
					//jetzt nachsehen, wenn eines der lkwkennzeichen vorhanden ist, dann die kosten rechnen
					if (parser_response.get(0).get_bd_entfernung()!=null ) {
						BigDecimal bdDistance = parser_response.get(0).get_bd_entfernung();
						
						BigDecimal bdKosten = new BigDecimal(0);
						
						PdServiceLkwKostenBean kosten = new PdServiceLkwKostenBean();
						if (S.isFull(kfzKennzeichenLkw)) {
							bdKosten=bdKosten.add(O.NN(kosten.getKostenProKm(kfzKennzeichenLkw), new BigDecimal(0)));
						}
						if (S.isFull(kfzKennzeichenAnhaenger)) {
							bdKosten=bdKosten.add(O.NN(kosten.getKostenProKm(kfzKennzeichenAnhaenger), new BigDecimal(0)));
						}
						
						BigDecimal bdKostenGesamt = bdKosten.multiply(bdDistance, new MathContext(64,RoundingMode.HALF_UP));
						bdKostenGesamt=bdKostenGesamt.setScale(2,RoundingMode.HALF_UP);
						
						response_map.put(ENUM_ROUTING_TYP.KOSTEN_KM, bdKostenGesamt);
					}
					
					
					
				}else {
					mv._addAlarm("Keine Route gefunden !");
				}
				
			} catch (MalformedURLException e) {
				mv._addAlarm(e.getMessage());
			} catch (IOException e) {
				mv._addAlarm(e.getMessage());
			} catch (JSONException e) {
				mv._addAlarm(e.getMessage());
			}
			
		}else {
			mv._addAlarm("Braucht ein start und ziel punkt !");
		}
		return response_map;
		
	}
	
}
