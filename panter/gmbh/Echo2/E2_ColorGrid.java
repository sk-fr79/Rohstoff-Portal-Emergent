package panter.gmbh.Echo2;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;

public class E2_ColorGrid extends MyE2_Grid {
	

	public E2_ColorGrid(int iPixWidth, int iPixHeight, Color col) {
		this(iPixWidth, iPixHeight, col, null);
	}
	
	public E2_ColorGrid(int iPixWidth, int iPixHeight, Color col, MyE2_String toolTips) {
		super(1, MyE2_Grid.STYLE_GRID_BORDER(Color.BLACK));
		MyE2_Label labHelp = new MyE2_Label("__");
		if (toolTips!=null) {
			labHelp.setToolTipText(toolTips.CTrans());
		}
		labHelp.setForeground(col);
		this.add(labHelp);
		this.setRowHeight(0, new Extent(iPixHeight));
		this.setColumnWidth(0, new Extent(iPixWidth));
		this.setBackground(col);
	}

}
