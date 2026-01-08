package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public enum LAUFZETTEL_USER_ROLE {
	
	OWNER_LAUFZETTEL(LAUFZETTEL.id_user_besitzer,				LAUFZETTEL.id_laufzettel,			new MyE2_String("Laufzettel ist im  Besitz von"))
	,SUPERVISOR_LAUFZETTEL(LAUFZETTEL.id_user_supervisor,		LAUFZETTEL.id_laufzettel,			new MyE2_String("Der Supervisor des Laufzettels ist"))
	,CLOSER_LAUFZETTEL(LAUFZETTEL.id_user_abgeschlossen_von,	LAUFZETTEL.id_laufzettel,			new MyE2_String("Der Laufzettel wurde geschlossen von"))
	,OWNER_TASK(LAUFZETTEL_EINTRAG.id_user_besitzer,			LAUFZETTEL_EINTRAG.id_laufzettel,	new MyE2_String("Laufzettel enthält Aufgaben von Besitzer"))
	,REFEREE_TASK(LAUFZETTEL_EINTRAG.id_user_bearbeiter,		LAUFZETTEL_EINTRAG.id_laufzettel,	new MyE2_String("Laufzettel enthält Aufgaben zu erledigen von"))
	,CLOSER_TASK(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,	LAUFZETTEL_EINTRAG.id_laufzettel,	new MyE2_String("Laufzettel enthält Aufgaben abgeschlossen von"))
	;
	
	private IF_Field  			field_id_user = null;
	private IF_Field  			field_id_laufzettel = null;
	
	private MyE2_String     	beschriftung = null;  
	
	private LAUFZETTEL_USER_ROLE(IF_Field  p_field_id_user, IF_Field p_field_id_laufzettel,  MyE2_String p_beschriftung) {
		this.field_id_user=			p_field_id_user;
		this.field_id_laufzettel = 	p_field_id_laufzettel;
		this.beschriftung = 		p_beschriftung;
	}
	
	/**
	 * 
	 * @return i.e.  "SELECT JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER FROM JT_LAUFZETTEL_EINTRAG
	 */
	public SEL  get_query_select_users_in_field() {
		return new SEL(this.field_id_user).ADD_Distinct().FROM(this.field_id_user._t());
	}
	
	
	 
	/**
	 * 
	 * @return i.e.  "SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL FROM JT_LAUFZETTEL_EINTRAG WHERE JT_LAUFZETTEL_EINTRAG=1000"
	 * @throws myException 
	 */
	public SEL  get_query_id_laufzettel_with_user(String id_user_uf) throws myException {
		return new SEL(this.field_id_laufzettel).ADD_Distinct().FROM(this.field_id_laufzettel._t()).WHERE(this.field_id_user, id_user_uf);
	}
	
	
	
	public MyE2_String get_beschriftung() {
		return this.beschriftung;
	}
	
	public IF_Field field_id_user() {
		return this.field_id_user;
	}

	
	public IF_Field get_id_laufzettel() {
		return field_id_laufzettel;
	}

}
