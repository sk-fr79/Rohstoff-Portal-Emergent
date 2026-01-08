package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2 extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_BG_ATOM_AUSWERT_2"; 
	
	private static final String SQL_Statement = 		
			"CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2.NAME_TRIGGER+ "  \n" + 
" AFTER  UPDATE \n " + 
" ON JT_BG_STATION \n " + 
" FOR EACH ROW \n " +
" BEGIN \n " +
"     UPDATE JT_BG_AUSWERT  \n " +
"       SET ID_ADRESSE = :NEW.ID_ADRESSE, \n " +
"           ID_ADRESSE_BASIS = :NEW.ID_ADRESSE_BASIS, \n " +
"           ID_ADRESSE_BESITZ_LDG = :NEW.ID_ADRESSE_BESITZ_LDG \n " + 
"         where ID_BG_STATION = :OLD.ID_BG_STATION; \n " + 
" END; \n " 
;

	
	public Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2() {
		super(Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2.SQL_Statement);
	}
}
