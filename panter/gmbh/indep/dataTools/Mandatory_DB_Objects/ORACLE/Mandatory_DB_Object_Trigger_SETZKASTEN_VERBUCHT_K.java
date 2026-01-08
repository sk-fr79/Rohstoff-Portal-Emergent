package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_SETZKASTEN_VERBUCHT_K"; 
	
	private static final String SQL_Statement = 		
			"CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K.NAME_TRIGGER+ "  \n" + 
			"BEFORE DELETE OR UPDATE                                                       \n" +
			"ON JT_BEWEGUNG_SETZKASTEN_K                                                     \n" +
			"FOR EACH ROW                                                                  \n" +
			"BEGIN                                                                         \n" +
			"                                                                                                                                                                    \n" +
			"    delete from JT_BEWEGUNG_ATOM_VERBUCHT_K   where id_bewegung_atom = :OLD.ID_ATOM_EINGANG;     \n" +
			"    delete from JT_BEWEGUNG_ATOM_VERBUCHT_K   where id_bewegung_atom = :OLD.ID_ATOM_AUSGANG;     \n" +
			"EXCEPTION                                                                                       \n" +
			"  WHEN NO_DATA_FOUND THEN                                                                       \n" +
			"     return;                                                                                                                                                        \n" +
			"END; \n"   ;

	
	public Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K() {
		super(Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K.SQL_Statement);
	}
}
