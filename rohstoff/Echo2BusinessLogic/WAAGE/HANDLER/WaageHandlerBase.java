package rohstoff.Echo2BusinessLogic.WAAGE.HANDLER;

import java.util.Hashtable;

import rohstoff.Echo2BusinessLogic.WAAGE.WaageSatzBase;


/**
 * Basis für alle Waage-Verbindungen
 * @author manfred
 *
 */
public abstract class WaageHandlerBase {

	// mögliche Kommandos an die Waagen und deren Parameter
	// müssen für jede Waage an die jeweiligen Kommandos angepasst werden
	public enum ENUM_Commands{
		CMD_INIT,
		CMD_READ,
		CMD_READTEMP,
		CMD_TARA_MANUELL,
		CMD_TARA_LOESCHEN,
		CMD_TARA_AUSGLEICH,
		TARA_MANUELL_WERT
		};

	
	// Hashtable zum Ablegen aller Kommandos und möglicher Parameter zu den Kommandos
	// die in den Child-Klassen gesetzt und abgerufen werden können 
	private Hashtable<String, String> m_Parameter;
	
	// das aktuell ausgeführte Kommando
	protected ENUM_Commands    m_currentCommand;
	// Der Waagesatz der zurückgegeben wird, nachdem das Kommando ausgeführt wurde
	protected WaageSatzBase 	m_WaageSatz;

	
	public WaageHandlerBase() {
		m_Parameter = new Hashtable<String, String>();
		m_WaageSatz = null;
	}

	public void setParameter(String Name, String Wert){
		m_Parameter.put(Name, Wert);
	}
	
	protected String getParameter(String Name){
		String sRet = null;
		if (m_Parameter.containsKey(Name)){
			sRet = m_Parameter.get(Name);
		}
		return sRet;
	}
	
	
	/**
	 * Gibt den Waagesatz zurück, der zum Kommando gehört
	 * @return
	 */
	public abstract WaageSatzBase getWaageSatz();
	
	
	public abstract String leseWaage() throws Exception;
	public abstract void setCommand(WaageHandlerBase.ENUM_Commands Command);
	
	
	/**
	 * parst den Wert, der aus der Waage kommt in die jeweilig gültige Datenstruktur
	 * WaageSatzXXX und legt ihn in m_Waagesatz
	 */
	protected abstract void parseWaageResult(String WaageResultValue);

	
}
