package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse korrigiert die fälschlicherweise 0Euro abgespeicherten Umbuchungspreise 
 * 
 * @author manfred
 *
 */
public class LAG_Reorganize_Umbuchungspreise {

	
	public LAG_Reorganize_Umbuchungspreise(){
		
	}
	
	
	public void reorganize() throws myException {
		// alle Warenausgänge der Umbuchungen finden
		RECLIST_LAGER_KONTO listWAs = new RECLIST_LAGER_KONTO(RECORD_LAGER_KONTO.FIELD__BUCHUNG_HAND + " = 'U' " +
										  " AND " + RECORD_LAGER_KONTO.FIELD__PREIS + " = 0 " +
										  " AND " + RECORD_LAGER_KONTO.FIELD__BUCHUNGSTYP + "= 'WA' ", "ERZEUGT_AM");
		
		Vector<String> vSql = new Vector<String>();
		
		int nError 	= 0;
		int nOk		= 0;
		 
		for (RECORD_LAGER_KONTO rec: listWAs.values()){
			
			// SqlStatements löschen
			vSql.clear();
			
			// Preis des Ausgangslager ermitteln
			BigDecimal bdPreis = 
					LAG_LagerPreisHandler.ermittleDurchschnittspreisVonLager_Ext(
					rec.get_ID_ADRESSE_LAGER_cUF(), 
					rec.get_ID_ARTIKEL_SORTE_cUF(), 
					1, 
					10, 
					rec.get_BUCHUNGSDATUM_VALUE_FOR_SQLSTATEMENT().replace("'", "")					
					);
			
			// nur wenn ein Preis gefunden wurde, weitermachen
			if (bdPreis == null){
				nError++;
				continue;
			}
			
			
			
			String sSelectErzeugtAM = " SELECT ERZEUGT_AM FROM JT_LAGER_KONTO WHERE ID_LAGER_KONTO = " + rec.get_ID_LAGER_KONTO_cUF();
			
			// finden der Gegenbuchung (Eingangsbuchungd er anderen Sorte
			RECLIST_LAGER_KONTO listGegen = new RECLIST_LAGER_KONTO( RECORD_LAGER_KONTO.FIELD__BUCHUNG_HAND + " = 'U' " +
													" AND " + RECORD_LAGER_KONTO.FIELD__BUCHUNGSTYP + "= 'WE' " +
													" AND " + RECORD_LAGER_KONTO.FIELD__ERZEUGT_AM + " = (" + sSelectErzeugtAM + ") " +
													" AND " + RECORD_LAGER_KONTO.FIELD__ID_ADRESSE_LAGER + " = " + rec.get_ID_ADRESSE_LAGER_cUF()
													, "");
			
			RECORD_LAGER_KONTO gegenbuchung = null;
			if (listGegen.size() == 1 ){
				gegenbuchung = listGegen.get(0);
			}
		
			
			Vector<String> vDoNotUpdate = DB_STATICS.get_AutomaticWrittenFields_STD();
			vDoNotUpdate.add("ERZEUGT_AM");
			vDoNotUpdate.add("BUCHUNGSDATUM");
			vDoNotUpdate.add("BUCHUNGSZEIT");
			vDoNotUpdate.add("ID_MANDANT");
			
			
			// jetzt die neuen Werte setzen und speichern
			String sPreis = MyNumberFormater.formatDez(bdPreis.toPlainString(), 2, true);
			
			rec.set_NEW_VALUE_PREIS(sPreis);
			rec.set_NEW_VALUE_IST_GEAENDERT("Y");
			vSql.add(rec.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
			
			if (gegenbuchung != null){
				gegenbuchung.set_NEW_VALUE_PREIS(sPreis);
				gegenbuchung.set_NEW_VALUE_IST_GEAENDERT("Y");
				vSql.add(gegenbuchung.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
			}
			
			boolean bRet = true;
			MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vSql, true);
			bRet &= mv.get_bIsOK();
			
			if (bRet){
				bibDB.Commit();
				nOk++;
			} else {
				bibDB.Rollback();
				nError++;
			}
			
		} // end Schleife
		
		bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden " + Integer.toString(nOk) + " Einträge erfolgreich verarbeitet. " + 
																Integer.toString(nError) + " Einträge konnten nicht aktualisiert werden."		)));
		
	}
		
	
}

