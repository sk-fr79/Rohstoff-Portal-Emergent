/**
 * rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER
 * @author martin
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;

import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22LandRcSorte;

/**
 * @author martin
 * @date 19.10.2020
 *
 * hilfsobject, das bei einer masken-speicher-aktion 
 */
public class MapForChangeRcTables {

	private VEK<Rec22LandRcSorte> 				landRcSortenToDelete = new VEK<Rec22LandRcSorte>();
	private VEK<Rec22LandRcSorte> 				landAndSortenToAdd = new VEK<Rec22LandRcSorte>();
	/**
	 * @author martin
	 * @date 19.10.2020
	 *
	 */
	public MapForChangeRcTables() {
		super();
	}
	
	public MapForChangeRcTables _clear() {
		this.landAndSortenToAdd._clear();
		this.landRcSortenToDelete._clear();
		
		return this;
	}
	
	public void addToDelete(Rec22LandRcSorte landAndSorte) {
		this.landRcSortenToDelete._a(landAndSorte);
	}
	
	public void addToAdd(Rec22LandRcSorte landAndSorte) {
		this.landAndSortenToAdd._a(landAndSorte);
	}

	/**
	 * @return the landRcSortenToDelete
	 */
	public VEK<Rec22LandRcSorte> getLandRcSortenToDelete() {
		return landRcSortenToDelete;
	}

	/**
	 * @return the landAndSortenToAdd
	 */
	public VEK<Rec22LandRcSorte> getLandAndSortenToAdd() {
		return landAndSortenToAdd;
	}
	
}
