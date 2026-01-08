package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

public enum ENUM_PREISZUORDNUNG {
	ATOM_LEFT_2_RIGHT("LEFT")
	,ATOM_RIGHT_2_LEFT("RIGHT")
	,NOTHING("NOTHING")
	;
	
	private String start = "";
	
	private ENUM_PREISZUORDNUNG(String p_richtung){
		this.start = p_richtung;	
	}
	
	public String get_start_side(){
		return this.start;
	}
}
