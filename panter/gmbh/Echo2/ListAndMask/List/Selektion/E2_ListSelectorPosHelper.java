package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;

/**
 * komponente, um eine saubere anordnung hinzubekommen,
 * Grid aus 2 zellen mit definierter breite
 * @author martin
 *
 */
public class E2_ListSelectorPosHelper extends MyE2_Grid
{

	public E2_ListSelectorPosHelper(String cText, Component oComp4Selection, int iBreiteText, int iBreiteComp)
	{
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setColumnWidth(0, new Extent(iBreiteText));
		this.setColumnWidth(1, new Extent(iBreiteComp));
		
		this.add(new MyE2_Label(new MyE2_String(cText)), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0));
		this.add(oComp4Selection, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
	}
	
}
