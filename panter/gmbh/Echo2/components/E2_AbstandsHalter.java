package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;

public class E2_AbstandsHalter extends MyE2_Grid
{

	public E2_AbstandsHalter(int iWidth_in_pixel)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setColumnWidth(0, new Extent(iWidth_in_pixel));
		this.add(new MyE2_Label(""));
		
	}

}
