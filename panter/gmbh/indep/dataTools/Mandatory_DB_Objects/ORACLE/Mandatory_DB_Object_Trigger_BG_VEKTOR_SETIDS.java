package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 
/**
 * 
 * @author martin
 * @date 30.07.2020
 * trigger, der bei jedem speichern eines vektors die eigene id in die (formal nach oben angeordeten) JT_BG_ATOM und JT_BG_STATION verteilt
 */

public class Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_BG_VEKTOR_SETIDS"; 
	
	private static final String SQL_Statement = 		
	      "CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS.NAME_TRIGGER+ "  \n" + 
			" AFTER DELETE OR INSERT OR UPDATE \n " + 
			" ON JT_BG_VEKTOR \n " + 
			" FOR EACH ROW \n " +
			" BEGIN \n " +
				" \n " + 
				" \n " +
				" IF INSERTING OR UPDATING THEN \n " +
				" \n " +
					" UPDATE JT_BG_ATOM    A SET A.ID_BG_VEKTOR = :NEW.ID_BG_VEKTOR WHERE (A.ID_BG_ATOM =: NEW.ID_BG_ATOM_QUELLE OR A.ID_BG_ATOM =: NEW.ID_BG_ATOM_ZIEL); \n "+
					" UPDATE JT_BG_STATION S SET S.ID_BG_VEKTOR = :NEW.ID_BG_VEKTOR WHERE S.ID_BG_STATION IN (SELECT A.ID_BG_STATION_QUELLE FROM JT_BG_ATOM A WHERE A.ID_BG_ATOM =: NEW.ID_BG_ATOM_QUELLE OR A.ID_BG_ATOM =: NEW.ID_BG_ATOM_ZIEL); \n "+
					" UPDATE JT_BG_STATION S SET S.ID_BG_VEKTOR = :NEW.ID_BG_VEKTOR WHERE S.ID_BG_STATION IN (SELECT A.ID_BG_STATION_ZIEL   FROM JT_BG_ATOM A WHERE A.ID_BG_ATOM =: NEW.ID_BG_ATOM_QUELLE OR A.ID_BG_ATOM =: NEW.ID_BG_ATOM_ZIEL); \n "+
				" END IF; \n " +
			" END; \n " 
		;

	public Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS() {
		super(Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS.SQL_Statement);
	}
}
