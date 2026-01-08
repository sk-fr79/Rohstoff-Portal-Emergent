package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.tempTables.myCONST_TEMP_TABLES;
import panter.gmbh.indep.exceptions.myException;

public abstract class XXX_ViewBuilder
{
	
	public abstract boolean build_View_forAll_Mandants() throws myException;
	
	
	/**
	 * 2013-10-08: Methode, die bei den view-build-klassen aufgerufen werden koennen, um die Views in Temporaere tabellen ueberfuehren zu koennen
	 * @param cNameView muss sowas sein wie: 	S1_EINKAUF_ATOM
	 * @param cViewName muss dann sein z.B.:	TT_EINKAUF_ATOM  und muss in der enum: myCONST_TEMP_TABLE.TEMP_TABLES vorhanden sein
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector create_TempTable(String cNameView, String cNameTempTable) throws myException {
		
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();

		boolean bErlaubtTabelleZuBauen = false;
		
		
		DEBUG.System_println(cNameView+" ----> "+cNameTempTable,"");
		
		if (!this.bTableIsAllowd(cNameTempTable)) {
			oMV_Rueck.add_MESSAGE(
					new MyE2_Alarm_Message(
							new MyE2_String("Die temporäre Tabelle: ",true,cNameTempTable,false," ist nicht in der Liste der erlaubten Temp-Tables !",true)));
			return oMV_Rueck;
		}
		
		//zuerst checken, ob der tabellen-name bereits vorhanden ist
		boolean bTableIstVorhanden = DB_META.Check_Table_exists(cNameTempTable);
		if (!bTableIstVorhanden) {
			bErlaubtTabelleZuBauen = true;
		} else {
			if (DB_META.Check_Table_is_TEMP_TABLE(cNameTempTable)) {
				bErlaubtTabelleZuBauen = true;
			} else {
				oMV_Rueck.add_MESSAGE(
						new MyE2_Alarm_Message(
								new MyE2_String("Die temporäre Tabelle: ",true,cNameTempTable,false," kann nicht als temporäre Tabelle identifiziert werden (DURATION=SYS$SESSION) !",true)));
				return oMV_Rueck;
			}
		}
		
		
		if (bErlaubtTabelleZuBauen) {
			if (bTableIstVorhanden) {
				if (! (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS("DELETE FROM "+cNameTempTable, true) && 
					   bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS("DROP TABLE "+cNameTempTable, true))) 	{
					oMV_Rueck.add_MESSAGE(
							new MyE2_Alarm_Message(
									new MyE2_String("Die temporäre Tabelle: ",true,cNameTempTable,false," kann nicht gelöscht werden (vermutlich offene Sessions) !",true)));
					
					
//					String cDeleteStatements4DeleteSessions = "SELECT 'alter system kill session '''|| s.sid||','||s.serial#||''';'   FROM   v$session s   WHERE  UPPER(s.username) = '"+bibE2.cTO().toUpperCase()+"'";
//					String[][] vInfos = bibDB.EinzelAbfrageInArray(cDeleteStatements4DeleteSessions, false);
//					for (int i=0;i<vInfos.length;i++) {
//						oMV_Rueck.add_MESSAGE(new MyE2_Info_Message(vInfos[i][0]));
//					}
					return oMV_Rueck;
									
				}
			}
			String cSQL_Create = "CREATE GLOBAL TEMPORARY TABLE "+cNameTempTable+" ON COMMIT PRESERVE ROWS AS (SELECT * FROM "+cNameView+")";
			
			if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL_Create, true)) {
				oMV_Rueck.add_MESSAGE(new MyE2_Info_Message(
						new MyE2_String("Die temporäre Tabelle: ",true,cNameTempTable,false," wurde auf der Basis des Views ",true,cNameView,false," erzeugt!",true)));
			} else {
				oMV_Rueck.add_MESSAGE(
						new MyE2_Alarm_Message(
								new MyE2_String("Die temporäre Tabelle: ",true,cNameTempTable,false," kann nicht erzeugt werden !",true,cSQL_Create,false)));
			}
		}
	
		return oMV_Rueck;
		
	}
	
	private boolean bTableIsAllowd(String cNameTempTable) {
		boolean bRueck = false;
		
		for (myCONST_TEMP_TABLES.TEMP_TABLES tab: myCONST_TEMP_TABLES.TEMP_TABLES.values()) {
			if (tab.name().equals(cNameTempTable.toUpperCase())) {
				bRueck = true;
			}
		}
		
		return bRueck;
	}
	
}
