package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.bibDB;

public class BG_Kostenberechnung_Batch implements ICallableTask{

	BG_Kostenberechnung m_oKosten = null;
	
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();
	
	/**
	 * 
	 */
	public BG_Kostenberechnung_Batch() {

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
		m_oKosten = new BG_Kostenberechnung();
		
		// cachen der Transportkostensätze
		m_oKosten.cacheTransportkosten();
		
		
		// KEINE Handbuchungen aktualisieren
		String sSelectBewegung = "SELECT distinct " + BG_VEKTOR.id_bg_vektor.tnfn() + " FROM " + BG_VEKTOR.fullTabName() ;
		String [][] asIds = bibDB.EinzelAbfrageInArray(sSelectBewegung);
		String sIDVektor= "?";

		// Statistiken ...
		m_oKosten.initCounters();
		
		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				sIDVektor = asIds[iRun][0];
				
				
				m_oKosten.ErzeugeSQL_Kostensaetze_Fuer_BG_Vektor(sIDVektor);
				
			} catch (Exception e_default){
				e_default.printStackTrace();
				m_TaskMessages.add("Fehler bei der Kostenfindung des Vektors " + sIDVektor);
			}
		}

		GregorianCalendar calEnd = new  GregorianCalendar();
		long diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;

		m_TaskMessages.add(String.format( "Es wurden für %d Vektoren die Kosten geprüft in %d sec  --> %d DS/s" , asIds.length, diff_in_sec,  asIds.length / diff_in_sec  ) );
		
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
