package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;

import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.NvlTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class IMPORT_Zolltarifnummer_Handler {

	private GregorianCalendar			_calCurrent = new GregorianCalendar(); 
	
	
	/**
	 * gibt zurück, ob die Import-Tabelle Einträge hat
	 * @return
	 */
	public boolean	hasEntries(){
		boolean bRet = false;
		RECLIST_ZOLLTARIFNUMMER_IMPORT _rlEntries;

		try {
			_rlEntries = new RECLIST_ZOLLTARIFNUMMER_IMPORT("", "");
			bRet = _rlEntries.size() > 0;
			
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim ermitteln der Import-Daten"));
		}
		return ( bRet);
	}
	
	
	
	
	
	/**
	 * gibt die Anzahl der neuen Einträge der Import-Tabelle an 
	 * d.h. ZT-Nummern, die nicht in der Stammdatentabelle vorhanden sind
	 * @return
	 * @throws myException 
	 */
	public int getCountNewEntries() {
		int iRet = 0;
		RECLIST_ZOLLTARIFNUMMER_IMPORT rl = getNewEntries();
		if (rl != null){
			iRet = rl.size();
		}
		return iRet;
	}
	
	

	/**
	 * Liste der Einträge die in der neuen Liste vorhanden sind, und in der bestehenden nicht -> Neueinträge
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @return
	 */
	public RECLIST_ZOLLTARIFNUMMER_IMPORT getNewEntries(){
		RECLIST_ZOLLTARIFNUMMER_IMPORT rl = null;
		
		SEL selAktive;
		SEL selNewEntries;
		try {
			selAktive = new SEL()
					.ADDFIELD(ZOLLTARIFNUMMER.nummer)
					.FROM(ZOLLTARIFNUMMER.T())
					.WHERE(new TermSimple("1=1")
//							new NvlTerm(ZOLLTARIFNUMMER.aktiv, "'Y'"),new TermSimple("'Y'") 
							);
		
				
			selNewEntries = new SEL()
				.FROM(ZOLLTARIFNUMMER_IMPORT.T())
				.WHERE(ZOLLTARIFNUMMER_IMPORT.nummer," not in ", "(" + selAktive.s() + ")");
		
			rl = new RECLIST_ZOLLTARIFNUMMER_IMPORT(selNewEntries.s());
		} catch (myException e1) {
			e1.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim ermitteln der neuen Einträge"));
		}
		
		return rl;
	}
	
	
	

	
	
	
	
	
	
	/**
	 * gibt die Anzahl der Einträge an, die in der neuen Liste (import-Liste) nicht gelistet sind, 
	 * aber noch in der alten Liste stehen 
	 * NB: nur gültig, wenn _bCompleteImport == true
	 * @return
	 */
	public int getCountEntriesToDelete(){
		int iRet = 0;
		RECLIST_ZOLLTARIFNUMMER rl = getEntriesToDelete();
		if (rl != null){
			iRet = rl.size();
		}
		return iRet;
	}
	

	
	/**
	 * Liste der Einträge die aktiv in der bestehenden Liste vorhanden sind, aber nicht in der neuen Importliste.
	 * Das sind die Einträge, die evtl. gelöscht (inaktiviert) werden müssen.
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @return
	 */
	public RECLIST_ZOLLTARIFNUMMER getEntriesToDelete(){
		
		RECLIST_ZOLLTARIFNUMMER rl = null;
		SEL selToDelete;
		SEL selImportEntries;
		try {
		
			selImportEntries = new SEL()
					.ADDFIELD(ZOLLTARIFNUMMER_IMPORT.nummer)
					.FROM(ZOLLTARIFNUMMER_IMPORT.T());

			
			selToDelete = new SEL()
					.FROM(ZOLLTARIFNUMMER.T())
					.WHERE(ZOLLTARIFNUMMER.nummer , " not in ", "(" + selImportEntries.s() + ")")
					.AND(new NvlTerm(ZOLLTARIFNUMMER.aktiv, "'Y'"),new TermSimple("'Y'") );

			rl = new RECLIST_ZOLLTARIFNUMMER(selToDelete.s());
		
		} catch (myException e) {
			e.printStackTrace();
		}
		return rl;

	}
	
	
	
	/**
	 * gibt die Anzahl der geänderten Einträge zurück die in der Importliste vorhanden sind
	 * @return
	 */
	public int getCountChangedEntries(){
		int iRet = 0;
		RECLIST_ZOLLTARIFNUMMER rl = getChangedEntries();
		if (rl != null){
			iRet = rl.size();
		}
		return iRet;
	}
	
	
	
	/**
	 * gibt die Liste der bestehenden Einträge zurück, die einen neuen Text in der Import-Tabelle haben.
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @return
	 */
	public RECLIST_ZOLLTARIFNUMMER getChangedEntries(){
		RECLIST_ZOLLTARIFNUMMER rl = null;
		SEL selChanged;
		try {
			selChanged = new SEL()
					.ADDFIELD(ZOLLTARIFNUMMER.fullTabName()+ ".*")
					.FROM(ZOLLTARIFNUMMER.T())
					.INNERJOIN(ZOLLTARIFNUMMER_IMPORT.T(), ZOLLTARIFNUMMER.nummer, ZOLLTARIFNUMMER_IMPORT.nummer)
					.WHERE ( new vgl(ZOLLTARIFNUMMER.id_mandant,ZOLLTARIFNUMMER_IMPORT.id_mandant))  
//					.AND 
//					      (new NvlTerm(ZOLLTARIFNUMMER.aktiv, "'Y'"), new TermSimple("'Y'"))
					.AND ( new NvlTerm(ZOLLTARIFNUMMER.text1,"'*'"), "!=", new NvlTerm(ZOLLTARIFNUMMER_IMPORT.text1,"'*'"))
					.OR  ( new NvlTerm(ZOLLTARIFNUMMER.text2,"'*'"), "!=", new NvlTerm(ZOLLTARIFNUMMER_IMPORT.text2,"'*'"))
					.OR  ( new NvlTerm(ZOLLTARIFNUMMER.text3,"'*'"), "!=", new NvlTerm(ZOLLTARIFNUMMER_IMPORT.text3,"'*'"))
					.OR  ( new NvlTerm(ZOLLTARIFNUMMER.aktiv,"'Y'"), "=", new TermSimple("'N'"));
					

			rl = new RECLIST_ZOLLTARIFNUMMER(selChanged.s());
		
		} catch (myException e) {
			
			e.printStackTrace();
		}
		return rl;
	}
	

	
	/**
	 * korrigiert alle Einträge der Liste
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @return
	 */
	public boolean updateChangedEntries(){
		Vector<String> vSql = new Vector<>();
		
		RECLIST_ZOLLTARIFNUMMER rl = getChangedEntries();
		for (RECORD_ZOLLTARIFNUMMER rec : rl){
			vSql.add( getSQLForUpdate(rec) );
		}
		
		return saveEntries(vSql);
	}
	
	
	
	/**
	 * korrigiert den einen Datensatz
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param recZoll
	 * @return
	 */
	public boolean updateEntry(RECORD_ZOLLTARIFNUMMER recZoll){
		Vector<String> vSql = new Vector<>();
		vSql.add( getSQLForUpdate(recZoll));
		return saveEntries(vSql);
	}
		
	
	/**
	 * gibt das Sql-Statement für die Korrektur zurück
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param recZoll
	 * @return
	 */
	private String  getSQLForUpdate(RECORD_ZOLLTARIFNUMMER recZoll){
		String  sRet = null;
		String  sZTNummer = "";
		
		// lesen des Import-Eintrags
		RECORD_ZOLLTARIFNUMMER_IMPORT recImp = null;
		try {
			sZTNummer = recZoll.get_NUMMER_cUF();
			recImp = new RECORD_ZOLLTARIFNUMMER_IMPORT(ZOLLTARIFNUMMER_IMPORT.nummer.tnfn() + " = '" + sZTNummer + "'");
		} catch (myException e1) {
			recImp = null;
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte kein Importsatz für die ZT-Nummer:" + sZTNummer + " gefunden werden."));
		} 
				
		
		if (recImp != null){
			try {
				recZoll.set_NEW_VALUE_BM_NUMMER(recImp.get_BM_NUMMER_cUF());
				recZoll.set_NEW_VALUE_TEXT1(recImp.get_TEXT1_cUF());
				recZoll.set_NEW_VALUE_TEXT2(recImp.get_TEXT2_cUF());
				recZoll.set_NEW_VALUE_TEXT3(recImp.get_TEXT3_cUF());
				recZoll.set_NEW_VALUE_BM_TEXT(recImp.get_BM_TEXT_cUF());
				recZoll.set_NEW_VALUE_LETZTER_IMPORT_(_calCurrent);
				recZoll.set_NEW_VALUE_AKTIV("Y");
				
				sRet = recZoll.get_SQL_UPDATE_STATEMENT(null, true);
			} catch (myException e) {
				e.printStackTrace();
			}
		}

		return sRet;
	}
	
	
	
	
	
	
	/**
	 * fügt alle neuen Einträge in die Zolltarifliste ein
	 * @author manfred
	 * @date 13.09.2016
	 *
	 * @return
	 */
	public boolean insertNewEntries(){
		Vector<String> vSql = new Vector<>();
		
		RECLIST_ZOLLTARIFNUMMER_IMPORT rl = getNewEntries();
		for (RECORD_ZOLLTARIFNUMMER_IMPORT rec : rl){
			vSql.add( getSQLForInsert(rec) );
		}
		
		return saveEntries(vSql);
	}
	
	
	/**
	 * gibt das SQL-Statement für einen neuen DS zurück
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param recImp
	 * @return
	 */
	private String  getSQLForInsert(RECORD_ZOLLTARIFNUMMER_IMPORT recImp){
		String  sRet = null;
		
		try {
			RECORDNEW_ZOLLTARIFNUMMER rec = new RECORDNEW_ZOLLTARIFNUMMER();
			rec.set_NEW_VALUE_NUMMER( recImp.get_NUMMER_cUF());
			rec.set_NEW_VALUE_TEXT1(recImp.get_TEXT1_cUF());
			rec.set_NEW_VALUE_TEXT2(recImp.get_TEXT2_cUF());
			rec.set_NEW_VALUE_TEXT3(recImp.get_TEXT3_cUF());
			rec.set_NEW_VALUE_BM_NUMMER(recImp.get_BM_NUMMER_cUF());
			rec.set_NEW_VALUE_BM_TEXT(recImp.get_BM_TEXT_cUF());
			rec.set_NEW_VALUE_AKTIV("Y");
			rec.set_NEW_VALUE_LETZTER_IMPORT(_calCurrent);
			sRet = rec.get_InsertSQLStatementWith_Id_Field(true, true);
		} catch (myException e) {
			e.printStackTrace();
		}

		return sRet;
	}
	
	
	
	
	
	
	
	/**
	 * setzt alle zu löschenden Einträge auf INAKTIV
	 * @return
	 */
	public boolean setEntriesInactive(){
		Vector<String> vSql = new Vector<>();
		
		RECLIST_ZOLLTARIFNUMMER rl = getEntriesToDelete();
		for (RECORD_ZOLLTARIFNUMMER r : rl){
			vSql.add(getSQLforEntryInactive(r));
		}
		
		return saveEntries(vSql);
	}
	
	
	/**
	 * setzt den Eintrag auf Inaktiv
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param recZT
	 * @return
	 */
	public boolean setEntryInactive(RECORD_ZOLLTARIFNUMMER recZT){
		Vector<String> vSql = new Vector<>();
		vSql.add(getSQLforEntryInactive(recZT));
		
		return saveEntries(vSql);	
	}

	/**
	 * erzeugt die Query um den Eintrag auf Inaktiv zu setzen	
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param recZT
	 * @return
	 */
	private String getSQLforEntryInactive(RECORD_ZOLLTARIFNUMMER recZT){
		String sRet = null;
		try {
			recZT.set_NEW_VALUE_AKTIV("N");
			recZT.set_NEW_VALUE_LETZTER_IMPORT_(_calCurrent);
			sRet = recZT.get_SQL_UPDATE_STATEMENT(null, true);
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;	
	}
	
	
	
	
	
	/**
	 * Führt die übergebenen SQLStatements durch
	 * @author manfred
	 * @date 15.09.2016
	 *
	 * @param vSql
	 * @return
	 */
	private boolean saveEntries(Vector<String> vSql){
		
		if (bibALL.get_bDebugMode()){
			DEBUG.System_println("SQL-Statements");
			DEBUG.System_print(vSql);
			bibALL.get_bDebugMode();
		}

	
		boolean bOK = bibDB.ExecSQL_in_Batch(vSql, true);
		if (!bOK){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim Schreiben der Daten")));
		} else {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Daten erfolgreich geschrieben")));
		}
		
		return bOK;
		
	}
	
	
	

	
	
	/**
	 * löscht alle Einträge in der Import-Tabelle
	 * @author manfred
	 * @date 14.09.2016
	 *
	 */
	public void cleanImportEntries(){
		String sSql = "DELETE FROM " + ZOLLTARIFNUMMER_IMPORT.fullTabName() + " WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT();
		bibDB.ExecSQL(sSql, true);
	}
	
	
}
