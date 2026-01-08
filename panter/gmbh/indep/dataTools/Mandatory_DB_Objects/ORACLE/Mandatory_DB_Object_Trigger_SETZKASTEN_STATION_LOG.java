package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_SETZKASTEN_STATION"; 
	
	private static final String SQL_Statement = 		
			"CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG.NAME_TRIGGER+ "  \n" + 
			"BEFORE DELETE OR UPDATE                                                       \n" +
			"ON JT_BEWEGUNG_STATION                                                           \n" +
			"FOR EACH ROW                                                                  \n" +
			"DECLARE																	   \n" +
			"   LAGERADRESSE NUMBER(10,0);												   \n" +
			"BEGIN                                                                         \n" +
			"                                                                              \n" +
			"    select ( case when :OLD.ID_ADRESSE IN (                                   \n" +
			"                SELECT EIGENE_ADRESS_ID                                       \n" +
			"                  FROM JD_MANDANT                                             \n" +
			"                  WHERE ID_MANDANT = :OLD.ID_MANDANT                          \n" +
			"                UNION                                                         \n" +
			"                SELECT ID_ADRESSE_LIEFER                                      \n" +
			"                  FROM JT_LIEFERADRESSE                                       \n" +
			"                  WHERE JT_LIEFERADRESSE.ID_ADRESSE_BASIS =                                                                                                         \n" + 
			"                        (                                                                                                                                           \n" +
			"                            SELECT EIGENE_ADRESS_ID FROM JD_MANDANT WHERE ID_MANDANT = :OLD.ID_MANDANT AND JT_LIEFERADRESSE.ID_ADRESSE_LIEFER > 900                 \n" +
			"                        )                                                                                                                                           \n" +
			"            ) then :OLD.ID_ADRESSE ELSE null END) into LAGERADRESSE                                                                                                                                                       \n" +
			"  	  FROM dual ;                                                                                                                                                                 \n" +
			"                                                                                                                                                                    \n" +
			"  IF LAGERADRESSE is not null THEN                                                                                                                                  \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN      where id_atom_ausgang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN_K    where id_atom_ausgang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN      where id_atom_eingang  = :OLD.ID_BEWEGUNG_ATOM;     \n" +
			"    delete from JT_BEWEGUNG_SETZKASTEN_K    where id_atom_eingang  = :OLD.ID_BEWEGUNG_ATOM;   \n" +
			"  END IF;                                                                                                                                                           \n" +
			"                                                                                                                                                                    \n" +
			"EXCEPTION                                                                                                                                                           \n" +
			"  WHEN NO_DATA_FOUND THEN                                                                                                                                           \n" +
			"     return;                                                                                                                                                        \n" +
			"END; \n"   ;

	
	public Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG() {
		super(Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG.SQL_Statement);
	}
}
