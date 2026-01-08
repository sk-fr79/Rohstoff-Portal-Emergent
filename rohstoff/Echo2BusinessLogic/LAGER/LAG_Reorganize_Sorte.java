package rohstoff.Echo2BusinessLogic.LAGER;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG.LAG_BEW_StatusErmittlung;

/**
 * Klasse, die in 
 * einem Lager 
 * eine bestimmte Sorte 
 * ab einem bestimmten Zeitpunkt
 * reorganisiert und dafür auch die Lagerbestands-Historie schreibt.
 * 
 * Das ist z.B. nötig, wenn ein Nulldurchgang nachträglich definiert wurde.
 * Dann müssen alle Lagerbewertungen auch nachträglich gepflegt werden.
 * 
 * @author manfred
 *
 */
public class LAG_Reorganize_Sorte {

	private String m_id_lager = null;
	private String m_id_sorte = null;
	private String m_datum_ab = null;
	private boolean m_bReorganizeAll = false;
	
	Vector<String> m_vDaysForReorg = new Vector<String>();
	String[][] aIDs = null;
	
	LAG_BEW_StatusErmittlung oLagerStatusHandler = null;
	
	
	/**
	 * Reorganisation des Setzkastens aller Läger aller Sorten ab dem gegebenen Datum.
	 * Ist das Datum null, dann werden alle Einträge reorganisiert.
	 * @param datum_ab
	 */
	public LAG_Reorganize_Sorte(String datum_ab){
		m_datum_ab = datum_ab;
		if (bibALL.isEmpty(m_datum_ab )){
			m_datum_ab = "2009-01-01";
		}
		
		m_bReorganizeAll = true;
		
	}
	
	
	/**
	 * Reorganisation einer Sorte eines Lagers ab dem gegebenen Datum.
	 * Ist das Datum null, werden alle Einträge reorganisiert.
	 * 
	 * @param id_lager
	 * @param id_sorte
	 * @param datum_ab
	 */
	public LAG_Reorganize_Sorte(String id_lager,String id_sorte, String datum_ab) {
		m_id_lager = id_lager;
		m_id_sorte = id_sorte;
		
		if (bibALL.isEmpty(datum_ab )){
			datum_ab = "2000-01-01";
		}
		
		m_datum_ab = datum_ab;
		m_bReorganizeAll = false;

	}
	

	
	@SuppressWarnings("serial")
	public void Reorganize_Lager_WithProcessBar(){
		

		
		// der Lagerstatus-Handler
		oLagerStatusHandler = new LAG_BEW_StatusErmittlung();

		
		// Tage ermitteln, die neu aufgebaut werden müssen
		m_vDaysForReorg.clear();
		m_vDaysForReorg = oLagerStatusHandler.getTageFuerReorg(m_datum_ab);
		
		int nCountDays = m_vDaysForReorg.size();
		
		// alle Daten aus der Lagerbewegung löschen
		if ( !oLagerStatusHandler.cleanLagerKontoUndBewegungFuerReorg(m_datum_ab, m_id_lager, m_id_sorte)) {
			return;
		}
		
		
		Object o = new E2_ServerPushMessageContainer_STD (new Extent(500),
				new Extent(150),
				new MyE2_String("Reorganisation der Lagereintragungen für die gewählte Sorte und Lager") ,
				true,
				false,
				5000,
				nCountDays,
				20,
				null)
				{

					/**
					 * Hauptroutine der Berechnung
					 */
					public void Run_Loop() throws myException{
						
						Long start;
						Long end;
						int zaehlerSchleife = 0;
						
						
						// neu aufbauen des Lagers Tag für Tag...
						for (String sDate : m_vDaysForReorg){
							
							zaehlerSchleife++;

							if (this.get_oBalken()!=null){
								this.get_oBalken().set_Wert(zaehlerSchleife);
							}

							
							
							// Debug
							DEBUG.System_println("Verbuche Lagereinträge vom: " + sDate, DEBUG.DEBUG_FLAG_DIVERS1);
							start = System.currentTimeMillis();
							
							// am Ende des Tages die Verbuchung im Setzkasten (JT_LAGER_BEWEGUNG) und im Lagerstatus
							if (m_bReorganizeAll) {
								oLagerStatusHandler.verbucheLagerKontoUndErstelleStatus(  sDate );
							} else {
								oLagerStatusHandler.verbucheLagerKontoUndErstelleStatus( m_id_lager, m_id_sorte, sDate );
							}
							
							end = System.currentTimeMillis();
							String duration = Long.toString(((end-start) / 1000)); 
							DEBUG.System_println("Lager Verbucht in " + duration + " sec", DEBUG.DEBUG_FLAG_DIVERS1);

						}

					}
		
					@Override
					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
					{
					}

				};
	}

	
	
}

