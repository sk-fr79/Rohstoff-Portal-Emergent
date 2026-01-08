package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.HashMap;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;

public class HELP2__TransportHashMap extends RB_TransportHashMap {
	
	HashMap<HELP2_CALLSETTINGS, Object> callSettings = null;

	public HELP2__TransportHashMap() {
		super();
	}

	
	@SuppressWarnings("unchecked")
	public void setModul(E2_MODULNAME_ENUM.MODUL modul) {
		this.callSettings = (HashMap<HELP2_CALLSETTINGS, Object>)this.getPlace4Everything();
		
		if (this.getPlace4Everything()==null) {
			this.callSettings = new HashMap<>();
			this._setPlace4Everything(this.callSettings);
		}
		
		this.callSettings.put(HELP2_CALLSETTINGS.VORGABE_MODUL, modul);
	}
	
	
	@SuppressWarnings("unchecked")
	public E2_MODULNAME_ENUM.MODUL getModul() {
		this.callSettings = (HashMap<HELP2_CALLSETTINGS, Object>)this.getPlace4Everything();
		if (this.callSettings !=null) {
			return (E2_MODULNAME_ENUM.MODUL)this.callSettings.get(HELP2_CALLSETTINGS.VORGABE_MODUL);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public void setCheckBoxShowImages(RB_cb modul) {
		this.callSettings = (HashMap<HELP2_CALLSETTINGS, Object>)this.getPlace4Everything();
		
		if (this.getPlace4Everything()==null) {
			this.callSettings = new HashMap<>();
			this._setPlace4Everything(this.callSettings);
		}
		
		this.callSettings.put(HELP2_CALLSETTINGS.CHECKBOX_ZEIGE_BILDER, modul);
	}
	
	
	@SuppressWarnings("unchecked")
	public RB_cb getCheckBoxShowImages() {
		this.callSettings = (HashMap<HELP2_CALLSETTINGS, Object>)this.getPlace4Everything();
		if (this.callSettings !=null) {
			return (RB_cb)this.callSettings.get(HELP2_CALLSETTINGS.CHECKBOX_ZEIGE_BILDER);
		}
		return null;
	}

	
}
