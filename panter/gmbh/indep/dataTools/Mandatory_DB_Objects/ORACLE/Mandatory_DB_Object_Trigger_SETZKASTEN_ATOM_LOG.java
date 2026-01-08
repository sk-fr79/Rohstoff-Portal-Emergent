package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_SETZKASTEN_ATOM"; 
	
	private static final String SQL_Statement = 		
			"CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG.NAME_TRIGGER+ "  \n" + 
			"BEFORE DELETE OR UPDATE                                                       \n" +
			"ON JT_BEWEGUNG_ATOM                                                           \n" +
			"FOR EACH ROW                                                                  \n" +
			"BEGIN                                                                         \n" +
			"                                                                                                                                                                    \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN      where id_atom_eingang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN      where id_atom_ausgang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +

			"    delete from JT_BEWEGUNG_SETZKASTEN_K    where id_atom_eingang  = :OLD.ID_BEWEGUNG_ATOM;      \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN_K    where id_atom_ausgang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +
			"EXCEPTION                                                                                       \n" +
			"  WHEN NO_DATA_FOUND THEN                                                                       \n" +
			"     return;                                                                                                                                                        \n" +
			"END; \n"   ;

	
	public Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG() {
		super(Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG.SQL_Statement);
	}
}
