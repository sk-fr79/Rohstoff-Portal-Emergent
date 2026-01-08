package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

public enum ENUM_UMBUCHUNGSDEF {
	
	vector(false)
	,vectorpos(true)
	,atomstart(false)
	,atomziel(false)
	,station_atomstart_links(false)
	,station_atomstart_rechts(false)
	,station_atomziel_links(false)
	,station_atomziel_rechts(false)
	
	,atom_start_zu_umbuchung(true)
	,station_start_zu_umbuchung_links(true)
	,station_start_zu_umbuchung_rechts(true)
	,atom_umbuchung_zu_ziel(true)
	,station_umbuchung_zu_ziel_links(true)
	,station_umbuchung_zu_ziel_rechts(true)
	;
	
	private boolean toSave = false;
	private ENUM_UMBUCHUNGSDEF(boolean save) {
		this.toSave=save;
	}
	public boolean isToSave() {
		return toSave;
	}

}
