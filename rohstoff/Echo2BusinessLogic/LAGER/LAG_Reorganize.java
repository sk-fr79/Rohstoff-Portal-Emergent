package rohstoff.Echo2BusinessLogic.LAGER;

import java.text.SimpleDateFormat;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.PluginCol_Sequence_NumTab_Builder;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_BEW_StatusErmittlung;

/**
 * Klasse zum durchführen eines Reorg-Laufes des Lagers 
 * mit Aufbau der Lager-Bewertungen und der Lager-Stati
 * ACHTUNG: Klasse nur mit Bedacht und bewusst VON HAND benutzen.
 * 
 * Es gehen unter Umständen viele Informationen verloren!!!
 * U.A. müssen die Lager-Nulldurchgänge berücksichtigt werden
 * 
 * @author manfred
 *
 */
public class LAG_Reorganize {

	Vector<String> m_vSqlPrepareTables = new Vector<String>();
	Vector<String> m_vDaysForReorg = new Vector<String>();
	String[][] aIDs = null;
	
	LAG_LagerHandler oLagerHandler = new LAG_LagerHandler();
	LAG_BEW_StatusErmittlung oLagerStatusHandler = new LAG_BEW_StatusErmittlung();
	
	
	@SuppressWarnings("serial")
	public void Reorganize_GesamtesLager_AufbauAusFuhren_WithProcessBar(){

		

		CleanLagerTablesForReorganization_ForCompleteReorg();
		PrepareDays_ForCompleteReorg();
		
		int nCountDays = m_vDaysForReorg.size();
		
		
		Object o = new E2_ServerPushMessageContainer_STD (new Extent(500),
				new Extent(150),
				new MyE2_String("Reorganisation der Lagereintragungen") ,
				true,
				false,
				5000,
				nCountDays,
				50,
				null)
				{
				

					/**
					 * Hauptroutine der Berechnung
					 */
					public void Run_Loop() throws myException{
						
						Long start;
						Long end;
						int zaehlerSchleife = 0;
						
						
						// lesen der Fuhren /-orte für den Tag
						// und ausführen aller Buchungen für den Tag
//						Vector<String> vSqlBatch = new Vector<>();
						
						
						// neu aufbauen des Lagers Tag für Tag...
						for (String sDate : m_vDaysForReorg){
							
							zaehlerSchleife++;

							Vector<String> vSqlBatch = new Vector<>();
							
							if (this.get_oBalken()!=null){
								this.get_oBalken().set_Wert(zaehlerSchleife);
							}

							// Debug
//							DEBUG.System_println("Verbuche Fuhren vom: " + sDate, DEBUG.DEBUG_FLAG_DIVERS1);
							start = System.currentTimeMillis();
							
							// lesen der Fuhren /-orte für den Tag
							// und ausführen aller Buchungen für den Tag
							aIDs = GetIDsForCompleteReorg(sDate);
							for (int i = 0; i <aIDs.length; i++){
								String idFuhre = aIDs[i][0];
								String idFuhrenOrt = aIDs[i][1];
								
								
								// Statements löschen
								oLagerHandler.m_vSQLStatements.clear();
								
								if (idFuhrenOrt != null){
									// es ist ein Ort
									oLagerHandler.LagerbuchungOrt(idFuhrenOrt);
								} else {
									// es ist eine Fuhre
									oLagerHandler.LagerBuchung(idFuhre);
								}
								
								Vector<String> vSql = oLagerHandler.getSqlStatements();
								
								vSqlBatch.addAll(vSql);
								
								// verbuchen der Daten
//								bibDB.ExecSQL(vSql, true);
								
							}
							
							//buchungen eines Tages in der Batch aufrufen
							bibDB.ExecSQL_in_Batch(vSqlBatch, true);
							
							
							// nur wenn auch an dem Tag neue oder geänderte Eintragungen gemacht wurden, auch eine Verbuchung durchführen
							if (aIDs.length > 0){
								// am Ende des Tages die Verbuchung im Setzkasten (JT_LAGER_BEWEGUNG) und im Lagerstatus
								oLagerStatusHandler.verbucheLagerKontoUndErstelleStatus( sDate );
							}			
							
							end = System.currentTimeMillis();
							String duration = Long.toString(((end-start) / 1000)); 
							DEBUG.System_println(String.format("Tag %s :Lager Verbucht in %s",sDate, duration) , DEBUG.DEBUG_FLAG_DIVERS3);

						}

					}
		
					@Override
					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
					{
					}

				};

		
	}
	
	/**
	 * Reorganisiert das Lager des Mandanten:
	 * 1. Neu beschreiben der Tabellen
	 * 2. Neu bewerten des Lagers
	 * für jeden einzelnen Tag der im internen Vector abgelegt ist
	 * 
	 * prerequisites: 1. CleanLagerTablesForReorganisation
	 *                2. PrepareDays
	 * 
	 * @return
	 */
	public boolean Reorganize_GesamtesLager_AufbauAusFuhren(){
		boolean bRet = true;
		
		LAG_LagerHandler oLagerHandler = new LAG_LagerHandler();
		LAG_BEW_StatusErmittlung oLagerStatusHandler = new LAG_BEW_StatusErmittlung();
		
		// preparation (kopieren und löschen)
		CleanLagerTablesForReorganization_ForCompleteReorg();
		
		// Tage finden
		PrepareDays_ForCompleteReorg();
		
		String[][] aIDs = null;
		
		Long start;
		Long end;
		
		
		
		// neu aufbauen des Lagers Tag für Tag...
		for (String sDate : m_vDaysForReorg){
			
			// Debug
			DEBUG.System_println("Verbuche Fuhren vom: " + sDate, DEBUG.DEBUG_FLAG_DIVERS1);
			start = System.currentTimeMillis();
			
			// lesen der Fuhren /-orte für den Tag
			// und ausführen aller Buchungen für den Tag
			Vector<String> vSqlBatch = new Vector<>();
			
			aIDs = GetIDsForCompleteReorg(sDate);
			for (int i = 0; i <aIDs.length; i++){
				String idFuhre = aIDs[i][0];
				String idFuhrenOrt = aIDs[i][1];
				
				
				// Statements löschen
				oLagerHandler.m_vSQLStatements.clear();
				
				if (idFuhrenOrt != null){
					// es ist ein Ort
					oLagerHandler.LagerbuchungOrt(idFuhrenOrt);
				} else {
					// es ist eine Fuhre
					oLagerHandler.LagerBuchung(idFuhre);
				}
				
				Vector<String> vSql = oLagerHandler.getSqlStatements();
				vSqlBatch.addAll(oLagerHandler.getSqlStatements());
				
				// verbuchen der Daten
//				bibDB.ExecSQL(vSql, true);
			}
			// alle Buchungen eines Tages
			bibDB.ExecSQL_in_Batch(vSqlBatch, true);

			
			// am Ende des Tages die Verbuchung im Setzkasten (JT_LAGER_BEWEGUNG) und im Lagerstatus
//			oLagerStatusHandler.verbucheLagerKontoUndErstelleStatus( sDate );
			end = System.currentTimeMillis();
			String duration = Long.toString(((end-start) / 1000)); 
			
			DEBUG.System_println(String.format("Tag %s :Lager Verbucht in %s",sDate, duration) , DEBUG.DEBUG_FLAG_DIVERS3);
			
//			DEBUG.System_println("Lager Verbucht in " + duration + " sec", DEBUG.DEBUG_FLAG_DIVERS1);

		}
		
		return bRet;
	}
	
	

	/**
	 * Ermittelt die relevanten Fuhren/-Ort IDs zum gewählten Datum
	 * @param Datum
	 * @return
	 */
	private String[][]  GetIDsForCompleteReorg(String Datum){
		
		String sSql = " SELECT v.ID_VPOS_TPA_FUHRE, v.ID_VPOS_TPA_FUHRE_ORT FROM V" + bibALL.get_ID_MANDANT() + "_REALGEWICHTE  v  " +
				" WHERE  v.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
				" AND NVL(v.IST_STORNIERT,'N') = 'N' " +
				" AND NVL(v.DELETED,'N') = 'N' " +
				" AND NVL(v.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " +
//				" AND trunc(NVL(v.DATUM_AUFLADEN, v.DATUM_ABLADEN),'DD') = to_date('" + Datum + "','yyyy-mm-dd') " +
				" AND trunc(NVL(v.DATUM_AUFLADEN, v.DATUM_ABLADEN),'DD') = to_date( ? ,'yyyy-mm-dd') " +
				" AND (v.ID_ADRESSE_START = " + bibALL.get_ID_ADRESS_MANDANT() + " OR v.ID_ADRESSE_ZIEL =  " + bibALL.get_ID_ADRESS_MANDANT() + " ) " +
				" ORDER BY  NVL(v.DATUM_AUFLADEN, v.DATUM_ABLADEN) " + 
				" ";
		
		SqlStringExtended sql = new SqlStringExtended(sSql);
		sql.getValuesList().add(new Param_String("",Datum)) ;

		String[][] IDs = bibDB.EinzelAbfrageInArray(sql,(String)null);
		
		return IDs;
	}
	
	
	/**
	 * Ermittelt die einzelnen Tage, für die die 
	 * Reorganisation durchgeführt werden musste 
	 */
	public void PrepareDays_ForCompleteReorg(){
		/**
		 */
		m_vDaysForReorg.clear();
		
		String sSql = " SELECT DISTINCT  to_char(nvl(DATUM_AUFLADEN, DATUM_ABLADEN),'yyyy-mm-dd')  FROM " +
				" (" +
				" 	SELECT * from V" + bibALL.get_ID_MANDANT() + "_REALGEWICHTE  v " +
				" 	WHERE v.ID_MANDANT = " + bibALL.get_ID_MANDANT() +
				" 	AND NVL(v.IST_STORNIERT,'N') = 'N'" +
				" 	AND NVL(v.DELETED,'N') = 'N'" +
				" 	AND NVL(v.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N'" +
				" 	AND NVL(v.DATUM_AUFLADEN, v.DATUM_ABLADEN) is not null" +
				" ) " +
				" ORDER BY  to_char(nvl(DATUM_AUFLADEN, DATUM_ABLADEN),'yyyy-mm-dd')  " +
				" " ;
		
		String[][] datum = bibDB.EinzelAbfrageInArray(sSql);
		
		for (int i = 0; i< datum.length; i++){
			m_vDaysForReorg.add(datum[i][0]);
		}
		
	}
	
	

	
	
	
	
	
	/**
	 * Generieren der Statements die die betroffenen Tabellen kopiert 
	 * und anschliessend löscht
	 */
	public boolean CleanLagerTablesForReorganization_ForCompleteReorg(){
		boolean bRet = false;
		m_vSqlPrepareTables.clear();
		
		// Tabellen kopieren
		addStatementForCopyTable("JT_LAGER_KONTO");
		addStatementForCopyTable("JT_LAGER_BEWEGUNG");
		addStatementForCopyTable("JT_STATUS_LAGER");
		
		// Original-Tabellen leeren 
		addStatementForEmptyTable("JT_STATUS_LAGER", "");
		addStatementForEmptyTable("JT_LAGER_BEWEGUNG", "");
		addStatementForEmptyTable("JT_LAGER_KONTO", " AND ID_VPOS_TPA_FUHRE IS NOT NULL ");
		
		// lagerkonto-Einträge ids korrigieren
		addStatementsForConsolidateLagerKontoIDs();
		
		
		// debug - löschen aller sql-befehle
//		m_vSqlPrepareTables.clear();
		
		// sichern und löschen der Lagertabellen 
		bRet = bibDB.ExecSQL(m_vSqlPrepareTables, true);
		
		
		
		// sequencer der Tabellen neu setzen:
		Project_TableSequenceBuilder 
		oSeq = new Project_TableSequenceBuilder(   _TAB.lager_konto.fullTableName(),	"REORG");
		bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
		oSeq = new Project_TableSequenceBuilder(   _TAB.lager_bewegung.fullTableName(),	"REORG");
		bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
		oSeq = new Project_TableSequenceBuilder(   _TAB.status_lager.fullTableName(),	"REORG");
		bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
		
		
		return bRet;
		
	}
	
	
	
	
	/**
	 * generiert ein Statement zum kopieren einer Tabelle und fügt es in den Vector vSqlCopyTables ein.
	 * z.B. CREATE TABLE LEBER.Z1_LAGER_KONTO AS SELECT * FROM LEBER.JT_LAGER_KONTO WHERE ID_MANDANT = 1;
	 * @param sTablename
	 */
	private void addStatementForCopyTable(String sTablename){
		
		String sTablePrefix = "Z" + bibALL.get_ID_MANDANT() ;
		SimpleDateFormat df = new SimpleDateFormat("_yyMMddHHmm");
		String sTableExtension = df.format(new java.util.Date());
		
		String sTableNew 	= 	sTablePrefix + sTablename.substring(2) + sTableExtension;
		
		m_vSqlPrepareTables.add("CREATE TABLE " + bibE2.cTO() + "." + sTableNew + " AS SELECT * FROM " + bibE2.cTO() + "." + sTablename + 
																			 " WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT());
	}
	
	/**
	 * generiert ein Statement zum kopieren einer Tabelle und fügt es in den Vector vSqlCopyTables ein.
	 * z.B. create table copy_jt_lager_konto as select * from LEBER.JT_LAGER_KONTO;
	 * @param sTablename
	 */
	private void addStatementForEmptyTable(String sTablename, String sAdditionalWhere ){
		
		m_vSqlPrepareTables.add("DELETE FROM " + bibE2.cTO() + "." + sTablename + " WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + sAdditionalWhere );
		
	}

	
	/**
	 * ändert die IDs der Lagerkonto-Einträge, nachdem alle Einträge ausser der Handbuchungen gelöscht sind.
	 * @author manfred
	 * @date 28.08.2018
	 *
	 */
	private void addStatementsForConsolidateLagerKontoIDs(){
		//m_vSqlPrepareTables.add(e)
		
		
		String sSql = " SELECT id_lager_konto FROM JT_LAGER_KONTO WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + " AND id_vpos_tpa_fuhre is null ORDER BY 1 "				 ;
		
		String[][] ids = bibDB.EinzelAbfrageInArray(sSql);
		Integer newid = 1000;
		
		for (int i = 0; i< ids.length; i++){
			m_vSqlPrepareTables.addElement(String.format("UPDATE JT_LAGER_KONTO set ID_LAGER_KONTO = %s where id_lager_konto = %s ", newid++,ids[i][0] ) );
		}
		
		
		
	}
	
	
	
	
	
}
