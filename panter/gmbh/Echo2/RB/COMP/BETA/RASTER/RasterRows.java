package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import java.util.LinkedHashMap;

import panter.gmbh.indep.exceptions.myException;

public class RasterRows  extends  LinkedHashMap<String,RasterRow> {

	public RasterRows() {
		super();
	}
	
	public void	render(Raster raster) throws myException {
		for (RasterRow row: this.values()) {
			row.render(raster);
		}
	}
	

	@Deprecated
	public RasterRow put(String key, RasterRow row) {
		super.put(key, row);
		return row;
	}

	
	public RasterRow put(RasterRow row) throws myException {
		super.put(row.getRowKey(), row);
		return row;
	}

	
	
}