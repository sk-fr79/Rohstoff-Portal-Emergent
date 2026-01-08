package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_BG_ATOM_AUSWERT"; 
	
	private static final String SQL_Statement = 		
			"CREATE OR REPLACE TRIGGER  " + Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT.NAME_TRIGGER+ "  \n" + 
" AFTER DELETE OR INSERT OR UPDATE \n " + 
" ON JT_BG_ATOM \n " + 
" FOR EACH ROW \n " +
" BEGIN \n " +
" \n " + 
" \n " +
" delete from JT_BG_AUSWERT where JT_BG_AUSWERT.id_bg_Atom = :OLD.ID_BG_ATOM; \n " +
" \n " +   
" IF INSERTING OR UPDATING THEN \n " +
" -- linke seite eintragen \n " +
" insert into jt_BG_AUSWERT ( \n " +
"         ID_BG_AUSWERT, \n " + 
"         ID_MANDANT, \n " + 
"         LETZTE_AENDERUNG, \n " + 
"         GEAENDERT_VON, \n " + 
"         ERZEUGT_VON, \n " + 
"         ERZEUGT_AM, \n " + 
"         ID_BG_ATOM, \n " + 
"         ID_BG_STATION, \n " + 
"         ID_ADRESSE, \n " +
"         ID_ADRESSE_BASIS, \n " +
"         ID_ADRESSE_BESITZ_LDG, \n " +
"         ID_ARTIKEL, \n " + 
"         ID_ARTIKEL_BEZ, \n " + 
"         DATUM_AUSFUEHRUNG, \n " + 
"         MENGE, \n " + 
"         MENGE_ABZUG, \n " + 
"         MENGE_VERTEILUNG, \n " + 
"         MENGE_NETTO, \n " + 
"         MENGE_ABRECH, \n " +
"         MENGENVORZEICHEN, \n " +
"         ID_BG_DEL_INFO, \n " +
"         ID_BG_STORNO_INFO, \n " +
"         ID_BG_VEKTOR, \n " +
"         STATION_KZ, \n " + 
"		  ID_ADRESSE_GEGEN \n	" +
"         ) \n " + 
" 	VALUES ( \n " +
" 	SEQ_BG_AUSWERT.NEXTVAL, \n " +
"         :NEW.ID_MANDANT, \n " + 
"         :NEW.LETZTE_AENDERUNG, \n " + 
"         :NEW.GEAENDERT_VON, \n " + 
"         :NEW.ERZEUGT_VON, \n " + 
"         :NEW.ERZEUGT_AM, \n " + 
"         :NEW.ID_BG_ATOM, \n " +
"         :NEW.ID_BG_STATION_QUELLE, \n " +
"         (SELECT ID_ADRESSE FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_QUELLE ), \n " + 
"         (SELECT ID_ADRESSE_BASIS FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_QUELLE ) , \n " + 
"         (SELECT ID_ADRESSE_BESITZ_LDG FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_QUELLE ) , \n " + 
"         :NEW.ID_ARTIKEL, \n " + 
"         :NEW.ID_ARTIKEL_BEZ, \n " + 
"         :NEW.DATUM_AUSFUEHRUNG, \n " + 
"         :NEW.MENGE , \n " + 
"         :NEW.MENGE_ABZUG , \n " + 
"         :NEW.MENGE_VERTEILUNG , \n " + 
"         :NEW.MENGE_NETTO , \n " + 
"         :NEW.MENGE_ABRECH , \n " +
"         -1, \n " +
"         :NEW.ID_BG_DEL_INFO, \n " +
"         :NEW.ID_BG_STORNO_INFO, \n " +
"         :NEW.ID_BG_VEKTOR, \n " +
"         (SELECT case when POS_IN_MASK = 'S1' \n " + 
" 				then -1 when pos_in_mask = 'S2' \n " + 
" 				then 0 when pos_in_mask = 'S3' \n " + 
" 				then 1 end \n " +  
" 			FROM JT_BG_STATION  WHERE ID_BG_STATION = :NEW.ID_BG_STATION_QUELLE ) , \n " + 
"          (SELECT ID_ADRESSE FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_ZIEL ) " + 
"         ); \n " +
" \n " +     
" \n " +     
" -- rechte seite eintragen \n " +
"    insert into jt_BG_AUSWERT ( \n " +
"         ID_BG_AUSWERT, \n " + 
"         ID_MANDANT, \n " + 
"         LETZTE_AENDERUNG, \n " + 
"         GEAENDERT_VON, \n " + 
"         ERZEUGT_VON, \n " + 
"         ERZEUGT_AM, \n " + 
"         ID_BG_ATOM, \n " + 
"         ID_BG_STATION, \n " + 
"         ID_ADRESSE, \n " +
"         ID_ADRESSE_BASIS, \n " +
"         ID_ADRESSE_BESITZ_LDG, \n " +
"         ID_ARTIKEL, \n " + 
"         ID_ARTIKEL_BEZ, \n " + 
"         DATUM_AUSFUEHRUNG, \n " + 
"         MENGE, \n " + 
"         MENGE_ABZUG, \n " + 
"         MENGE_VERTEILUNG, \n " + 
"         MENGE_NETTO, \n " + 
"         MENGE_ABRECH, \n " +
"         MENGENVORZEICHEN, \n " +
"         ID_BG_DEL_INFO, \n " +
"         ID_BG_STORNO_INFO, \n " +
"         ID_BG_VEKTOR, \n " +
"         STATION_KZ, \n " +
"		  ID_ADRESSE_GEGEN \n	" +
"         ) \n " + 
" 		VALUES ( \n " +
" 		SEQ_BG_AUSWERT.NEXTVAL, \n " +
"         :NEW.ID_MANDANT, \n " + 
"         :NEW.LETZTE_AENDERUNG, \n " + 
"         :NEW.GEAENDERT_VON, \n " + 
" :NEW.ERZEUGT_VON, \n " + 
" :NEW.ERZEUGT_AM, \n " + 
" :NEW.ID_BG_ATOM, \n " + 
" :NEW.ID_BG_STATION_ZIEL, \n " +
" 	(SELECT ID_ADRESSE FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_ZIEL ) , \n " + 
" 	(SELECT ID_ADRESSE_BASIS FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_ZIEL ) , \n " + 
" 	(SELECT ID_ADRESSE_BESITZ_LDG FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_ZIEL ) , \n " + 
" 	:NEW.ID_ARTIKEL, \n " + 
" 	:NEW.ID_ARTIKEL_BEZ, \n " + 
" 	:NEW.DATUM_AUSFUEHRUNG, \n " + 
" 	:NEW.MENGE , \n " + 
" 	:NEW.MENGE_ABZUG , \n " + 
" 	:NEW.MENGE_VERTEILUNG , \n " + 
" 	:NEW.MENGE_NETTO , \n " + 
" 	:NEW.MENGE_ABRECH, \n " +
" 	1, \n " +
" 	:NEW.ID_BG_DEL_INFO, \n " +
" 	:NEW.ID_BG_STORNO_INFO, \n " +
" 	:NEW.ID_BG_VEKTOR, \n " +
" 	(SELECT case when POS_IN_MASK = 'S1' \n " + 
" 		then -1 when pos_in_mask = 'S2' \n " + 
" 		then 0 when pos_in_mask = 'S3' \n " + 
" 		then 1 end \n " +  
" 		FROM JT_BG_STATION  WHERE ID_BG_STATION = :NEW.ID_BG_STATION_ZIEL ), \n " +
"   (SELECT ID_ADRESSE FROM JT_BG_STATION WHERE ID_BG_STATION = :NEW.ID_BG_STATION_QUELLE ) " + 
" 		); \n " +
" END IF; \n " +
" END; \n " 
;

	
	public Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT() {
		super(Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT.SQL_Statement);
	}
}
