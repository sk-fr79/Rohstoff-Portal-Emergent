package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;


public interface RasterRow {


	public RasterRowCells  					getCellList() throws myException;
	public int 	     	   					getInsetColCount() throws myException;
	public RasterRows      					getRasterRows() throws myException;
	public void 							buildRasterRows(RasterRow callingRasterRow) throws myException;  

	//!!!! WICHTIG: in jeder buildRasterRows MUSS generateCellList ausgefuehrt werden
	public void            					buildCellList() throws myException;
	
	
	//!!! WICHTIG: im Konstruktor muss der RowKey gesetzt werden
	public String   						getRowKey() throws myException;
	public void   							setRowKey(String key);
	
	//!!! WICHTIG: das raster muss jeder rasterrow uebereben werden
	public Raster 							getRaster();
	
	public RasterRow  						getMotherRasterRow();
	
	public Color  							getBackgroundColor();	
	
	public default void generateRasterRows(RasterRow callingRasterRow) throws myException {
		this.getRasterRows().clear();
		this.buildRasterRows(callingRasterRow);
	}

	

	
	public default void generateCellList(RasterRow callingRasterRow) throws myException {
		
		//statische zellen uebernehmen
		this.buildCellList();
		
		//evtl. statische austauschen (aus den vorigen aufbauscenarien)
		for (RasterRowCell cell: this.getCellList().values()) {
			cell.prepareRendering();
		}
	}

	
	
	public default RasterRow render(Raster raster) throws myException {
		
		//jetzt nachsehen, ob die zeile passt
		int iCountCols = this.getInsetColCount();
		for (RasterRowCell cell:this.getCellList().values()) {
			iCountCols += cell.getColSpan();
		}
		
		if (iCountCols>raster.getRasterColCount()) {
			throw new myException(this,"Column-Count of row is to big for rasterGrid !");
		}
		
		if (this.getInsetColCount()>0) {
			RB_lab labDummy = new RB_lab()._t("")._set_ld(new RB_gld()._left_top()._col(raster.getColor_freespace())._span(this.getInsetColCount()));
			raster._add_raw(labDummy);
		}
		
		Color background = (this.getBackgroundColor()!=null? this.getBackgroundColor():raster.getColor_content());
		
		this.getCellList().render(raster, background);
		
		//jetzt nachsehen, ob die rasterRow eine tochterliste hat, wenn ja rendern
		RasterRows daughterList =  this.getRasterRows();
		if (daughterList!=null) {
			daughterList.render(raster);
		}
		
		return this;
	}

	
	
	//recursive suche zur ersten RasterRow
	public default RasterRow getFirstRasterRow() {
		
		RasterRow topRasterRow = this;
		RasterRow previcousTopRow = this.getMotherRasterRow();
		
		while (previcousTopRow!=null) {
			previcousTopRow = previcousTopRow.getMotherRasterRow();
			if (previcousTopRow!=null) {
				topRasterRow = previcousTopRow;
			}
		}
		return topRasterRow;
	}


	//alle keyStrings der RasterRows zusammen zu einem keyString addieren
	public default String getKeyChain() throws myException {
		String keyChain = this.getRowKey()+"|";
		
		RasterRow nextTopRow = this.getMotherRasterRow();
		
		while (nextTopRow!=null) {
			nextTopRow = nextTopRow.getMotherRasterRow();
			if (nextTopRow!=null) {
				keyChain+= nextTopRow.getRowKey()+"|";
			}
		}
		return keyChain;
	}

	
	
	
	
	
	
}
