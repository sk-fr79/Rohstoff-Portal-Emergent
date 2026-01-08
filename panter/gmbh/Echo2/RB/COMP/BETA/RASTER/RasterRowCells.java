package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;

public class RasterRowCells  extends LinkedHashMap<String,RasterRowCell> {
	public RasterRowCells() {
		super();
	}
	
	public void render(Raster raster, Color color4cells) throws myException {
		for (RasterRowCell cell:this.values()) {
			LayoutData     gld = cell.getComponent4Cell().getLayoutData();
			RB_gld ggld = null;
			if (gld==null || !(gld instanceof GridLayoutData)) {
				//dann eines bauen
				ggld = new RB_gld()._left_top()._col(color4cells)._ins(0)._span(cell.getColSpan());
			} else {
				//sonst kopieren
				ggld = new RB_gld()._copyLayoutData((GridLayoutData)gld)._span(cell.getColSpan());
				if (ggld.getBackground()==null) {
					ggld.setBackground(color4cells);
				}
			}
			cell.getComponent4Cell().setLayoutData(ggld);
			raster._add_raw(cell.getComponent4Cell());
		}
		raster._endLine(new RB_gld()._col(color4cells));
	}

	
	@Deprecated
	public RasterRowCell put(String key, RasterRowCell cell) {
		return super.put(key, cell);		
	}
	
	public RasterRowCell put(RasterRowCell cell) {
		super.put(cell.getCellKey(), cell);
		return cell;
	}
	
}
