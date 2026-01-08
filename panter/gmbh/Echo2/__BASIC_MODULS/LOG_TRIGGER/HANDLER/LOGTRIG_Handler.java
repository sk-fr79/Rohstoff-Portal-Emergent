package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TRIGGER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum verwalten von Triggern die für das Logging genutzt werden
 * Es werden die Trigger des aktuellen DB-Benutzers über die Tabellen
 * 
 * 
 * @author manfred
 *
 */
public class LOGTRIG_Handler {

	Vector<String> m_vMessages = null;
	
	public LOGTRIG_Handler(){
		m_vMessages = new Vector<String>();
	}
	
	/**
	 * hinzufügen einer Meldung
	 * @param Message
	 */
	private void AddMessage(String Message){
		m_vMessages.add(Message);
	}
	
	/**
	 * löscht alle Meldungen
	 */
    private void ClearMessages(){
    	m_vMessages.clear();
    }
    
	
	/**
	 * gibt alle beim letzten Vorgang generierten Meldungen zuürck
	 * @return
	 */
	public Vector<String> GetMessages(){
		return m_vMessages;
	}
	
	/**
	 * Erzeugt alle Trigger, die in der Tabelle JD_TRIGGER_DEF definiert sind
	 * @return
	 * @throws myException 
	 */
	public boolean CreateTriggers() throws myException{
		
		boolean bRet = true;
		boolean bOk = true;
		
		ClearMessages();
		
		DeleteTriggers();

		String sTypeSQL = "";
		String sType = "";
		
		RECLIST_TRIGGER_DEF list = new RECLIST_TRIGGER_DEF("", "");
		
		for (RECORD_TRIGGER_DEF o: list.values()){
			
			sTypeSQL = DB_META.get_FieldType(o.get_TABELLE_cUF(), o.get_SPALTE_cUF());
			sType  = bibDB.EinzelAbfrage( sTypeSQL );
			
			//im trigger_def steht nur der BASISNAME einer Tabelle
			String cTableNameCompete = this.find_out_CompleteTableName(o.get_TABELLE_cUF());
			if (S.isEmpty(cTableNameCompete))
			{
				AddMessage("Tabelle  " + o.get_TABELLE_cUF() + 
						   " Spalte " + o.get_SPALTE_cUF() + 
						   "(" + o.get_TRIGG_NAME_cUF() + ") konnte nicht gefunden werden!" );
				bRet &= false;
			}
			
			
			bOk = CreateTrigger(cTableNameCompete, o.get_SPALTE_cUF(), o.get_TRIGG_NAME_cUF(), o.get_BESCHREIBUNG_cUF(),sType);
			if (!bOk)
			{
				AddMessage("Trigger für Tabelle " + o.get_TABELLE_cUF() + 
						   " Spalte " + o.get_SPALTE_cUF() + 
						   "(" + o.get_TRIGG_NAME_cUF() + ") konnte nicht generiert werden!" );
				bRet &= false;
			}
		}
		
		return bRet;
	}
	
	
	/*
	 * in den systemtabelle nachschauen, ob die tabelle mit jt_ oder jd_ beginnt
	 */
	private String find_out_CompleteTableName(String cTableNameBase)
	{
		if (DB_META.get_vTableNamesOfSchema(" TABLE_NAME='JT_"+cTableNameBase+"'", true).size()==1)
		{
			return "JT_"+cTableNameBase;
		}
		else
		{
			if (DB_META.get_vTableNamesOfSchema(" TABLE_NAME='JD_"+cTableNameBase+"'", true).size()==1)
			{
				return "JD_"+cTableNameBase;
			}
			else
			{
				return null;
			}
		}
	}
	
	
	/**
	 * Generiert einen Überwachungstrigger für die Tabelle Table und die Spalte Column.
	 * 
	 * @param Table
	 * @param Column
	 * @param Triggername
	 * @param Description
	 * @param DataType - Der Datentyp des DB-Catalogs. Bei NUMBER, DATE und TIMESTAMPx werden besondere Statements definiert um 
	 * eine leserliche Information zu erreichen. 
	 * @return
	 */
	private boolean CreateTrigger(String Table, String Column, String Triggername, String Description, String DataType){
		boolean bRet = false;

		String sNameOfID = "ID" + Table.substring(2);

		String sValueOLD = ":OLD.#column#";
		String sValueNEW = ":NEW.#column#";
		
		if (DataType.toLowerCase().contains("date")){
			sValueOLD = "to_char(" + sValueOLD + ",'dd.MM.yyyy hh24:mi:ss')";
			sValueNEW = "to_char(" + sValueNEW + ",'dd.MM.yyyy hh24:mi:ss')";
		}
		if (DataType.toLowerCase().contains("timestamp")){
			sValueOLD = "to_char(" + sValueOLD + ",'dd.MM.yyyy hh24:mi:ss')";
			sValueNEW = "to_char(" + sValueNEW + ",'dd.MM.yyyy hh24:mi:ss')";
		}
		// Nummernkonvertierung: Momentan nicht machen, da es zu viele Verschiedene Nummernformate (nachkommastellen) gibt,
		// und hier evtl ein Fehler rein kommt. Um genau zu sein müsste man die Daten aus dem Katalog lesen.
		
		// Die Mandanten dürfen nur in den JT_-Tabellen sein, in den JD_-Tabellen nicht.
		String sMandantCol = "ID_MANDANT, ";
		String sMandantVal = ":OLD.ID_MANDANT, ";
		
		if (Table.toLowerCase().startsWith("jd")){
			sMandantCol = "";
			sMandantVal = "";
		}
		
		
		
//		String sSql = 	" CREATE OR REPLACE TRIGGER #triggername# " +
//						" AFTER UPDATE OF #column# ON #table# " +
//						" FOR EACH ROW "+
//						" WHEN ((OLD.#column# != NEW.#column#) or (OLD.#column# is NULL and NEW.#column# is not null) OR (OLD.#column# is not NULL and NEW.#column# is null) ) "+ 
//						" BEGIN "+
//						" INSERT INTO JT_CHANGELOG (ID_CHANGELOG, #mandant_col#  ERZEUGT_AM,ERZEUGT_VON,LETZTE_AENDERUNG,GEAENDERT_VON,TABLENAME,COLNAME,ID_TABLE, OLD_VALUE,NEW_VALUE,BESCHREIBUNG) "+
//						" VALUES (SEQ_CHANGELOG.NEXTVAL, #mandant_val#  SYSDATE,:NEW.GEAENDERT_VON, SYSDATE, :NEW.GEAENDERT_VON, '#table#','#column#',:NEW.#id_table#, #value_old#, #value_new#, '#description#' ); "+
//						" END; ";
		
		//2013-01-08: erweiterung des triggers: die id_user wird aus der umgebungsvariable  sys_context('USERENV','CLIENT_IDENTIFIErR') gelesen
		
		String sSql = 	" CREATE OR REPLACE TRIGGER #triggername# " +
							" AFTER UPDATE OF #column# ON #table# " +
							" FOR EACH ROW "+
							" WHEN ((OLD.#column# != NEW.#column#) or (OLD.#column# is NULL and NEW.#column# is not null) OR (OLD.#column# is not NULL and NEW.#column# is null) ) "+ 
							" BEGIN "+
							" INSERT INTO JT_CHANGELOG (ID_CHANGELOG, #mandant_col#  ERZEUGT_AM,ERZEUGT_VON,LETZTE_AENDERUNG,GEAENDERT_VON,TABLENAME,COLNAME,ID_TABLE, OLD_VALUE,NEW_VALUE,BESCHREIBUNG,ID_USER) "+
							" VALUES (SEQ_CHANGELOG.NEXTVAL, #mandant_val#  SYSDATE,:NEW.GEAENDERT_VON, SYSDATE, :NEW.GEAENDERT_VON, '#tablebase#','#column#',:NEW.#id_table#, #value_old#, #value_new#, '#description#', sys_context('USERENV','CLIENT_IDENTIFIER') ); "+
							" END; ";
		
		
		
		
		// zuerst die "META"-Spalten ersetzen
		sSql = sSql.replaceAll("#value_old#", sValueOLD);
		sSql = sSql.replaceAll("#value_new#", sValueNEW);
		
		sSql = sSql.replaceAll("#mandant_col#", sMandantCol);
		sSql = sSql.replaceAll("#mandant_val#", sMandantVal);
		
		
		
		
		// dann die eigentlichen Werte
		sSql = sSql.replaceAll("#column#", Column);
		
		//!!!! Wichtig hier wird nur der Rumpfname der tabelle in die Logtable geschrieben, da sonst beim Abfragen der LOGTABELLE
		//     diese namen mit derm V?_TABELLENAME ersetzt werden
		sSql = sSql.replaceAll("#table#", Table);
		sSql = sSql.replaceAll("#tablebase#", Table.substring(3));
		sSql = sSql.replaceAll("#triggername#", Triggername);
		sSql = sSql.replaceAll("#description#", Description);
		sSql = sSql.replaceAll("#id_table#", sNameOfID);
		
		
		// trigger generieren
		bRet = bibDB.ExecSQL(sSql, true);
		
		return bRet;
	}
	
	
	
	/**
	 * Löscht alle Trigger des Users, die mit den Zeichen TRG_LG_ beginnen
	 * (Alle Trigger für das Logging werden mit TRG_LG_ angelegt
	 * @return
	 */
	public boolean DeleteTriggers(){
		boolean bRet = true;
		boolean bOk = true;
		
		ClearMessages();
		
		// finden aller Trigger 
		String sSql = "SELECT TRIGGER_NAME FROM SYS.USER_TRIGGERS WHERE TRIGGER_NAME LIKE 'TRG_LG_%' ";  
		String [][] sTriggers = bibDB.EinzelAbfrageInArray(sSql);
		
		int rows = sTriggers.length;
		for (int i = 0; i < rows; i++){
			bOk = DeleteTrigger(sTriggers[i][0]);
			if (!bOk){
				AddMessage(sTriggers[i][0] + " konnte nicht gelöscht werden");
				bRet &= false;
			}
		}		
		return bRet;
	}
	
	

	/**
	 * Löscht einen bestimmten Trigger mit dem angegebenen Namen
	 * @param TriggerName
	 * @return
	 */
	public boolean DeleteTrigger(String TriggerName){
		boolean bRet = false;

		String sSql =   " DROP TRIGGER " + TriggerName ;
		
		bRet = bibDB.ExecSQL(sSql, true);
		return bRet;
	}
	
	
	/**
	 * Löscht alle Log-Trigger und generiert die neuen nach den Vorgaben in der Tabelle JD_TRIGGER_DEF
	 * @return
	 */
	public boolean RecreateTriggers(){
		boolean bRet = false;
		return bRet;
		
	}
	
	
	
	
}
