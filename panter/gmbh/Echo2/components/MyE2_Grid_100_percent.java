package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;

public class MyE2_Grid_100_percent extends MyE2_Grid 
{
	public MyE2_Grid_100_percent() 
	{
		super();
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
		
	}

	public MyE2_Grid_100_percent(int iNumCols, int iBorderSize) {
		super(iNumCols, iBorderSize);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	public MyE2_Grid_100_percent(int iNumCols, MutableStyle oStyle) {
		super(iNumCols, oStyle);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	public MyE2_Grid_100_percent(int iNumCols) {
		super(iNumCols);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	public MyE2_Grid_100_percent(int[] iSpalten, int iBorderSize) {
		super(iSpalten, iBorderSize);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	public MyE2_Grid_100_percent(int[] iSpalten, MutableStyle oStyle) {
		super(iSpalten, oStyle);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	public MyE2_Grid_100_percent(MutableStyle oStyle) {
		super(oStyle);
		this.setHeight(new Extent(100,Extent.PERCENT));
		this.setWidth(new Extent(100,Extent.PERCENT));
	}

	
	
	
}
