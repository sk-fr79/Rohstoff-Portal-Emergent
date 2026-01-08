package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;


public abstract class Raster extends E2_Grid implements IF_RB_Component, RasterRow {

	private RB_KF 						Key = null;

	private int 						f_colWidth = 20; // standard-spaltenbreite
	private int 						f_colCount = 50; // standard-spaltenzahl

	private Color 						f_color_title = new E2_ColorDDark();
	private Color 						f_color_content = new E2_ColorDark();
	private Color 						f_color_freespace = new E2_ColorBase();
	

	private RasterRowCells				f_titelRasterRowCells = null;
	private RasterRows 					f_rasterRows =  new RasterRows();
	
	public abstract void 				generateTitleRasterRowCells() throws myException;
	public abstract void 				buildRasterRows(RasterRow callingRasterRow) throws myException;

	private RasterRows					previousRasterRows = new RasterRows();
	
	//eine Hashmap mit allen statischen objecten, wird vor jedem neubau "resetet" und in den cellen gefuellt
	private StaticCellHandlers          staticCells = new StaticCellHandlers();
	
	
	/**
	 * 
	 */
	public Raster() {
		super();
		
		if( this.getRasterColWidth() > 0){
			this.f_colWidth = getRasterColWidth();
		}
		
		if( this.getRasterColCount() > 0){
			this.f_colCount = getRasterColCount();
		}
		
		this._initRaster();
		this.setRowKey("dummy4start");
	}
	
	
	
	public Raster _initRaster() {
		int[] cols = new int[this.f_colCount];
		for (int i = 0; i < this.f_colCount; cols[i++] = this.f_colWidth);

		super._setSize(cols);

		return this;
	}
	
	
	public void render() throws myException {
		this._clear();
		
		if (this.f_titelRasterRowCells != null && this.f_titelRasterRowCells.size()>0) {
			this.f_titelRasterRowCells.render(this, this.getColor_title());
		}
		this.f_rasterRows.render(this);
		
//		this.staticCells.removeNotNeeded();
	}

	
	public void generateCellList(RasterRow callingRasterRow) throws myException {
		this.staticCells.setAllNotNeeded();

		this.buildCellList();
		
		//evtl. statische austauschen (aus den vorigen aufbauscenarien)
		for (RasterRowCell cell: this.getCellList().values()) {
			cell.prepareRendering();
		}
		
		this.staticCells.removeNotNeeded();
	}

	

	
	public RasterRowCells getTitelRasterRowCells() {
		return f_titelRasterRowCells;
	}
	
	public RasterRows getRasterRows() {
		return f_rasterRows;
	}
	
	public void setTitelRasterRowCells(RasterRowCells titelRow) {
		this.f_titelRasterRowCells = titelRow;
	}
	

	public Color getColor_title() {
		return f_color_title;
	}
	public Color getColor_content() {
		return f_color_content;
	}
	public Color getColor_freespace() {
		return f_color_freespace;
	}
	public void setColor_title(Color color_title) {
		this.f_color_title = color_title;
	}
	public void setColor_content(Color color_content) {
		this.f_color_content = color_content;
	}
	public void setColor_freespace(Color color_freespace) {
		this.f_color_freespace = color_freespace;
	}

	
	
	public  int getRasterColWidth() {
		return this.f_colWidth;
	}
	
	public  int getRasterColCount() {
		return this.f_colCount;
	}

	
	public Raster _setRasterColWidth(int f_colWidth) {
		this.f_colWidth = f_colWidth;
		return this._initRaster();
	}

	public Raster _setRasterColCount(int f_colCount) {
		this.f_colCount = f_colCount;
		return this._initRaster();
	}

	
	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return null;
	}

	public RasterRows getPreviousRasterRows() {
		return previousRasterRows;
	}
	public StaticCellHandlers getStaticCells() {
		return staticCells;
	}

	
	public void completeBuildAndRender() throws myException {
		this._clear();
		this.generateTitleRasterRowCells();
		this.generateRasterRows(this);
		this.render();
	}
	
	
	
	//-------- methoden aus RasterRow, die hier eigentlich immer leer sind
	@Override
	public RasterRowCells getCellList() throws myException { 		return null;	}

	@Override
	public int getInsetColCount() throws myException {		return 0;	}

	@Override
	public void buildCellList() throws myException {	}

	@Override
	public String getRowKey() {		return null;	}

	@Override
	public void setRowKey(String key) {	}

	@Override
	public Raster getRaster() {		return null;	}

	@Override
	public RasterRow getMotherRasterRow() {		return null;	}

	
}
