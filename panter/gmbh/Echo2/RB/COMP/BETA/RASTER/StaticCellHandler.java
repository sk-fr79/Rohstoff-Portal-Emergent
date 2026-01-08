package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import nextapp.echo2.app.Component;

public class StaticCellHandler {

	private Component 	f_componentStatic = null;
	private boolean     Needed = true;

	/**
	 * @param componentStatic
	 */
	public StaticCellHandler(Component componentStatic) {
		super();
		this.f_componentStatic = componentStatic;
	}

	public boolean isNeeded() {
		return Needed;
	}

	public void setNeeded(boolean needed) {
		Needed = needed;
	}

	public Component getComponentStatic() {
		return f_componentStatic;
	}
	
	
	
	
}
