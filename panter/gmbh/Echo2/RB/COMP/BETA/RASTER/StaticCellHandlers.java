package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import java.util.HashMap;
import java.util.Vector;

public class StaticCellHandlers extends HashMap<String, StaticCellHandler> {

	/**
	 * 
	 */
	public StaticCellHandlers() {
		super();
	}

	
	public void setAllNotNeeded() {
		for (StaticCellHandler cellHandler: this.values()) {
			cellHandler.setNeeded(false);
		}
	}
	
	
	public void removeNotNeeded() {
		Vector<String>  vNotNeeded = new Vector<>();
		
		for (String c: this.keySet()) {
			if (!this.get(c).isNeeded()) {
				vNotNeeded.add(c);
			}
		}

		for (String key: vNotNeeded) {
			this.remove(key);
		}
	}
	
	
}
