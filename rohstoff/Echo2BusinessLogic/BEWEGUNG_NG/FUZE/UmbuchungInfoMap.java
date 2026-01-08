package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT_POS;

public class UmbuchungInfoMap extends LinkedHashMap<ENUM_UMBUCHUNGSDEF, RB_KM> {

	public UmbuchungInfoMap(KEY_VEKT 		p_vector, 
							KEY_VEKT_POS 	p_vectorpos, 
							KEY_ATOM 		p_atomstart, 
							KEY_ATOM 		p_atomziel, 
							KEY_STATION 	p_station_atomstart_links, 
							KEY_STATION 	p_station_atomstart_rechts, 
							KEY_STATION 	p_station_atomziel_links, 
							KEY_STATION 	p_station_atomziel_rechts) {
		super();
		
		this.put(ENUM_UMBUCHUNGSDEF.vector, p_vector);
		this.put(ENUM_UMBUCHUNGSDEF.vectorpos, p_vectorpos);
		this.put(ENUM_UMBUCHUNGSDEF.atomstart, p_atomstart);
		this.put(ENUM_UMBUCHUNGSDEF.atomziel, p_atomziel);
		this.put(ENUM_UMBUCHUNGSDEF.station_atomstart_links, p_station_atomstart_links);
		this.put(ENUM_UMBUCHUNGSDEF.station_atomstart_rechts, p_station_atomstart_rechts);
		this.put(ENUM_UMBUCHUNGSDEF.station_atomziel_links, p_station_atomziel_links);
		this.put(ENUM_UMBUCHUNGSDEF.station_atomziel_rechts, p_station_atomziel_rechts);
		
	}

	
	
	
	
}
