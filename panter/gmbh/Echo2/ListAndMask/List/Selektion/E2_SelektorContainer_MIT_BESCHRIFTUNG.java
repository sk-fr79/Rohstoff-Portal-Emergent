package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;


/**
 * klasse zum definieren einer kombination aus Beschriftung und einer oder mehreren
 * Selektionselementen, wobei die Breite der Spalten definierbar is
 * @author martin
 *
 */
public class E2_SelektorContainer_MIT_BESCHRIFTUNG extends MyE2_Grid
{

	public E2_SelektorContainer_MIT_BESCHRIFTUNG(int[] iSpalten, Vector<Component> vComponents, Insets  InsetComponents) throws myException
	{
		super(iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		Insets  iComps = (InsetComponents==null ? E2_INSETS.I_0_0_2_0:InsetComponents);
		
		if (iSpalten==null || vComponents==null)
		{
			throw new myException(this,"Null is not allowed !");
		}
		
		if (iSpalten.length!=vComponents.size())
		{
			throw new myException(this,"Width-Array and Component-Vector MUST be same length !!");
		}
		for (int i=0;i<vComponents.size();i++)
		{
			this.add(vComponents.get(i), iComps);
		}
	}
	
	
	
	
	
}
