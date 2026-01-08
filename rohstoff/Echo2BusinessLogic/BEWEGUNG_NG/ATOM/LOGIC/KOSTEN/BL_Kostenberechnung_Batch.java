package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.bibDB;

public class BL_Kostenberechnung_Batch implements ICallableTask{

	BL_Kostenberechnung m_oKosten = null;
	
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();
	
	/**
	 * 
	 */
	public BL_Kostenberechnung_Batch() {

	}

	
	/**
	 * 
	 */
	@Override
	public boolean runTask() {
		boolean bRet = false;
		
		GregorianCalendar calBegin ;
		calBegin = new GregorianCalendar();
		
		// Berechnungsmodul initialisieren
		m_oKosten = new BL_Kostenberechnung();
		
		// cachen der Transportkostensätze
		m_oKosten.cacheTransportkosten();
		
		
		// KEINE Handbuchungen aktualisieren
//		String sSelectBewegung = "SELECT ID_BEWEGUNG_VEKTOR FROM JT_BEWEGUNG_VEKTOR where ID_BEWEGUNG IS NOT NULL order by ID_BEWEGUNG_VEKTOR  ";
		String sSelectBewegung = "SELECT " + BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn() + " FROM " + BEWEGUNG_VEKTOR.fullTabName() + 
											" WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + 
											" IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )";
		
		
		String [][] asIds = bibDB.EinzelAbfrageInArray(sSelectBewegung);
		String sIDVektor= "?";
		boolean bCommit = true;
		
		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				sIDVektor = asIds[iRun][0];
				
				// ein Commit alle 20 Vorgänge
				bCommit = (iRun % 20 == 0);
				
				m_oKosten.ErzeugeSQL_Kostensaetze_Fuer_Vektor(sIDVektor);
				m_oKosten.executeSqlStatements(bCommit);
				m_oKosten.clearStatements();
				
			} catch (Exception e_default){
				e_default.printStackTrace();
				m_TaskMessages.add("Fehler bei der Kostenfindung des Vektors " + sIDVektor);
			}
		}
		
		// ein letztes Commit
		m_oKosten.clearStatements();
		m_oKosten.executeSqlStatements(true);
		
		
		GregorianCalendar calEnd = new  GregorianCalendar();
		long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		
		m_TaskMessages.add("Es wurden für " + asIds.length + " Bewegungen die Kosten geprüft in" + diff_in_sec + " sec.");
		
		bRet = true;
		return bRet;
	}


	
	
	
	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters  = hmParameters;		
	}




	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}

}
