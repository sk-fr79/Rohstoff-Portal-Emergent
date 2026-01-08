package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_CONTAINER extends MyE2_Grid 
{
	
	/**
	 * 
	 * @param cBeschriftung
	 * @param iTextWidth
	 * @param oComponent
	 * @throws myException
	 */
	public SELECTOR_CONTAINER(	MyString	cBeschriftung,
								int 		iTextWidth,
								Component   oComponent) throws myException	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		this.add(oComponent, E2_INSETS.I_10_0_0_0);
	}
	
	

	/**
	 * 
	 * @param oComponentLeft
	 * @param iWidthLeft
	 * @param oComponent
	 * @throws myException
	 */
	public SELECTOR_CONTAINER(	Component   oComponentLeft,
								int 		iWidthLeft,
								Component   oComponent) throws myException	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid help = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		help.setColumnWidth(1, new Extent(iWidthLeft));
		help.add(oComponentLeft);
		
		this.add(help, E2_INSETS.I_0_0_0_0);
		this.add(oComponent, E2_INSETS.I_10_0_0_0);
	}

}
