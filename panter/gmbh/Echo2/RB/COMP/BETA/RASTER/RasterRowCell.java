package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.exceptions.myException;

public class RasterRowCell {
	private int 	   colSpan = 		1;
	private Component  component4Cell = new RB_lab();
	private boolean    isStatic = false;

	private String     ownCellKey = null;
	
	private RasterRow  ownRasterRow = null;
	
	public RasterRowCell(String cellKey, Component comp4Cell, RasterRow rasterRow) {
		super();
		this.component4Cell = comp4Cell;
		this.ownCellKey = cellKey;
		this.ownRasterRow = rasterRow;
	}

	
	public int getColSpan() {
		return colSpan;
	}

	public Component getComponent4Cell() {
		return component4Cell;
	}

	public RasterRowCell _setColSpan(int colSpan) {
		this.colSpan = colSpan;
		return this;
	}

	public RasterRowCell _setComponent4Cell(Component component4Cell) {
		this.component4Cell = component4Cell;
		return this;
	}
	

	public boolean isStatic() {
		return isStatic;
	}

	public RasterRowCell _setStatic(boolean is_Static) {
		this.isStatic = is_Static;
		return this;
	}

	public String getCellKey() {
		return ownCellKey;
	}


	public RasterRow getRasterRow() {
		return ownRasterRow;
	}

	// hier wird im raster nachgesehen, ob es dort einen statischen eintrag unter diesem key gibt, wenn ja wird die komponenten gegen die statische ausgetauscht
	public void prepareRendering() throws myException {
		
		if (this.isStatic) {
			StaticCellHandlers staticCells = this.ownRasterRow.getRaster().getStaticCells();
			
			String keyChain = "CELL:"+this.ownCellKey+"||"+this.ownRasterRow.getKeyChain();
			
			if (staticCells.get(keyChain)!=null) {
				this._setComponent4Cell(staticCells.get(keyChain).getComponentStatic());
				staticCells.get(keyChain).setNeeded(true);
			} else {
				staticCells.put(keyChain,new StaticCellHandler(this.getComponent4Cell()));
				staticCells.get(keyChain).setNeeded(true);

			}
		}
		
	}


}
