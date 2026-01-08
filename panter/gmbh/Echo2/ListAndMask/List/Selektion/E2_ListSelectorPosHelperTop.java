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
public class E2_ListSelectorPosHelperTop extends MyE2_Grid
{

	public E2_ListSelectorPosHelperTop(String cText, Component oComp4Selection, int iBreiteText, int iBreiteComp)
	{
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setColumnWidth(0, new Extent(iBreiteText));
		this.setColumnWidth(1, new Extent(iBreiteComp));
		
		this.add(new MyE2_Label(new MyE2_String(cText)), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,1,5,0)));
		this.add(oComp4Selection, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,1,0,0)));
	}
	
}
