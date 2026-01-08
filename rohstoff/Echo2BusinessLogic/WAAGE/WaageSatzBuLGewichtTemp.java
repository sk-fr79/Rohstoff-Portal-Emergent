package rohstoff.Echo2BusinessLogic.WAAGE;


/**
 * Systec-Waage Offenburg / Kehl
 * @author manfred
 *
 */
public class WaageSatzBuLGewichtTemp extends WaageSatzBuLBase {
	
	/**
	 * Der Wagesatz-Aufbau der Systec-Waage
	 * Reihenfolge der Felder entspricht der Reihenfolge im Satzaufbau
	 */
	public WaageSatzBuLGewichtTemp() {
		super();
		this.addNewField(WF_Fehlercode, 1);  // <ERR>
		this.addNewField(WF_BUL_E_CODE, 1);
		this.addNewField(WF_BUL_STAT, 1);
		this.addNewField(WF_TerminalNr,1);
		this.addNewField(WF_BruttoGewicht, 8);
	}
	
	@Override
	public String getDemoData() {

		return "";
	}
	
	
	
}
