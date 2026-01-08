package rohstoff.Echo2BusinessLogic.WAAGE;


/**
 * Systec-Waage Offenburg / Kehl
 * @author manfred
 *
 */
public class WaageSatzBuLGewicht extends WaageSatzBuLBase {
	
	/**
	 * Der Wagesatz-Aufbau der Systec-Waage
	 * Reihenfolge der Felder entspricht der Reihenfolge im Satzaufbau
	 */
	public WaageSatzBuLGewicht() {
		super();
		this.addNewField(WF_Fehlercode, 1);  // <ERR> 
		this.addNewField(WF_Datum, 10);
		this.addNewField("leer1", 1);
		this.addNewField(WF_Zeit, 5);
		this.addNewField("leer2", 1);
		this.addNewField(WF_IdentNummer,5 );
		this.addNewField("leer3", 3);
		this.addNewField(WF_BruttoGewicht, 8);
		this.addNewField(WF_Einheit,2 );
		this.addNewField("leer4", 3);
		this.addNewField(WF_TaraGewicht, 8);
		this.addNewField("leer5", 5);
		this.addNewField(WF_NettoGewicht, 8);
		this.addNewField("leer6", 2);
		
	}
	
	@Override
	public String getDemoData() {

		return "";
	}
	
	
}
