package panter.gmbh.Echo2.MAIL_AND_REPORT;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_DRUCKER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.FIBU.FIBU_LIST_BT_PrintMail_Saldenliste;

/*
 * sammlung statischer methoden im bereich reporting
 */
public class bibREP
{
	
	public static RECLIST_DRUCKER get_DruckerFuerModulUndBenutzer(String cMODULE_IDENTIFIER, String cID_USER_UF, boolean bMustHaveDirectDruck) throws myException
	{
		RECLIST_DRUCKER  recRueck = new RECLIST_DRUCKER();
		
		String ID_USER = cID_USER_UF;
		if (S.isEmpty(ID_USER))
		{
			ID_USER = bibALL.get_ID_USER();
		}
		
		//drucker suchen und zur auswahl anbieten
		String cSQL_Drucker = "SELECT JT_DRUCKER_ZUORDNUNG.ID_DRUCKER FROM "+bibE2.cTO()+".JT_DRUCKER_ZUORDNUNG " +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_DRUCKER ON (JT_DRUCKER_ZUORDNUNG.ID_DRUCKER=JT_DRUCKER.ID_DRUCKER) " +
						" WHERE JT_DRUCKER_ZUORDNUNG.ID_USER="+ID_USER+
						(bMustHaveDirectDruck?" AND DIRECT_DRUCK_BEFEHL IS NOT NULL ":"")+" AND JT_DRUCKER_ZUORDNUNG.MODUL_KENNER="+bibALL.MakeSql(cMODULE_IDENTIFIER);
		
		String[][] arr_ID_Drucker = bibDB.EinzelAbfrageInArray(cSQL_Drucker);
		
		if (arr_ID_Drucker != null)
		{
			for (int i=0;i<arr_ID_Drucker.length;i++)
			{
				recRueck.ADD(new RECORD_DRUCKER(arr_ID_Drucker[i][0]),false);
			}
		}

		return recRueck;
	}

	
	
	public static String[][] get_ArrayDruckerNamePlusDruckBefehl(RECLIST_DRUCKER recListDrucker, boolean bAddEmpty) throws myException
	{
		int iCount = recListDrucker.get_vKeyValues().size();
		
		String[][] cRueck = new String[bAddEmpty?iCount+1:iCount][2];
		
		int iIndex=0;
		if (bAddEmpty)
		{
			cRueck[0][0] = "--";
			cRueck[0][1] = "";
			iIndex=1;
		}
		
		for (int i=0;i<recListDrucker.get_vKeyValues().size();i++)
		{
			cRueck[iIndex][0]=recListDrucker.get(i).get_NAME_cUF_NN("??");
			cRueck[iIndex][1]=recListDrucker.get(i).get_DIRECT_DRUCK_BEFEHL_cUF_NN("??");
			iIndex++;
		}
		return cRueck;
	}
	
	
	
	/*
	 * 2012-07-25: methode, um eine bestimmte zahl tabellenids in die reporttabelle zu schreiben
	 */
	public static String  WriteReportBlock(Vector<String> vID_DATA_TABLE) throws myException
	{
		// dann werden die zugehoerigen IDs in die Tabelle JD_REPORTAKTION geschrieben
		String cREPORTBLOCK  = null;
		cREPORTBLOCK = bibDB.EinzelAbfrage("SELECT "+bibE2.cTO()+".SEQ_REPORTNUMMER.NEXTVAL FROM DUAL");
		
		if (!bibALL.isLong(cREPORTBLOCK))
		{
			throw new myExceptionForUser("Ich konnte keine freie Report-Nummer ermitteln (SEQ_REPORTNUMMER)!");
		}
		else
		{
			String cSQL_Base = "INSERT INTO "+bibE2.cTO()+".JD_REPORTAKTION (REPORTNUMMER,ID_REPORTAKTION,ID_TABLE) VALUES (" +
								cREPORTBLOCK+",SEQ_REPORTAKTION.NEXTVAL,";
			
			for (int i=0,k=vID_DATA_TABLE.size();i<k;i++)
			{
				String cSQL = cSQL_Base+vID_DATA_TABLE.get(i)+")";
				if (!bibDB.ExecSQL(cSQL,false))
				{
					bibDB.Rollback();
					throw new myExceptionForUser("Fehler beim Schreiben der Reportliste");
				}
			}
			bibDB.Commit();
		}
		
		return cREPORTBLOCK;
	}

	
	/*
	 * 2012-07-25: methode, um einen bestehende reportblock zu loeschen
	 */
	public static void DeleteReportBlock(String cReportBlockID) throws myException
	{
		if (S.isFull(cReportBlockID) && bibALL.isLong(cReportBlockID))
		{
			bibDB.ExecSQL("DELETE FROM "+bibE2.cTO()+".JD_REPORTAKTION WHERE REPORTNUMMER="+cReportBlockID,true);
		}
	}
	
	

}
