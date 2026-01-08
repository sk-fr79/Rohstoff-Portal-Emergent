package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SETZKASTEN;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;

/**
 * Klasse dient zum batch-aufruf der ATOM-Setzkasten-Handler
 * @author manfred
 * @date   30.01.2014
 * 
 * curl "http://localhost:8080/rohstoff_app/batch?user=batchuser&pw=xxxx&task=setzkasten"
 * 
 */
public class ATOM_SetzkastenHandler_Batch implements ICallableTask {

	// batch-Parameter
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();
	
	
	@Override
	public boolean runTask() {
		boolean bRet = false;
		
		// prüfen, ob alle Parameter vorhanden sind:
		// Bei dieser Methode gibt es gar keine Parameter, die gesetzt sein müssen, nur welche die gesetzt sein können.
		String idMandant = bibALL.get_ID_MANDANT();
		
		GregorianCalendar cal = new  GregorianCalendar();
		m_TaskMessages.add(new MyString("Start Setzkastenverarbeitung:").CTrans() + cal.getTime().toString() );
		
		ATOM_SetzkastenHandler oSetzkastenHandler = new ATOM_SetzkastenHandler(null);
		oSetzkastenHandler.ReorganiseLagerEntries(false);
		
		cal = new GregorianCalendar();
		m_TaskMessages.add(new MyString("Ende Setzkastenverarbeitung:").CTrans() + cal.getTime().toString() );
		
		
		
		cal = new  GregorianCalendar();
		m_TaskMessages.add(new MyString("Start Setzkastenverarbeitung/Kostenbehaftet:").CTrans() + cal.getTime().toString() );
		
		ATOM_SetzkastenHandler_KALKULATORISCH oSetzkastenHandlerK = new ATOM_SetzkastenHandler_KALKULATORISCH(null);
		oSetzkastenHandlerK.ReorganiseLagerEntries(false);
		
		cal = new GregorianCalendar();
		m_TaskMessages.add(new MyString("Ende Setzkastenverarbeitung/Kostenbehaftet:").CTrans() + cal.getTime().toString() );
		
		
		return true;
	}

	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
			}

	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}

}
