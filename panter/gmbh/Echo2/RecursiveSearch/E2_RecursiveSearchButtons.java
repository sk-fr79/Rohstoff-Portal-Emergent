package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.exceptions.myException;

public class E2_RecursiveSearchButtons
{
	private Object 						oSearchedContainer = 	null;
	private Vector<MyE2IF__Component> 	vButtons = 				new Vector<MyE2IF__Component>();

	public E2_RecursiveSearchButtons(Object searchedContainer) throws myException
	{
		super();
		oSearchedContainer = searchedContainer;
		
		if (!(oSearchedContainer instanceof Row || oSearchedContainer instanceof Column || oSearchedContainer instanceof Grid))
			throw new myException("Container can only search in Row / Column / Grid");

		if (oSearchedContainer instanceof Row)
			this.StartSearchRow((Row)searchedContainer);
		
		if (oSearchedContainer instanceof Column)
			this.StartSearchColumn((Column)searchedContainer);
		
		if (oSearchedContainer instanceof Grid)
			this.StartSearchGrid((Grid)searchedContainer);
		
		
	}

	
	private void StartSearchGrid(Grid oGrid)
	{
		Component[] arrayComponents = oGrid.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (arrayComponents[i] instanceof MyE2_Button || arrayComponents[i] instanceof MyE2_ButtonWithKey)
			{
				this.vButtons.add((MyE2IF__Component)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Grid)
			{
				this.StartSearchGrid((Grid)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Row)
			{
				this.StartSearchRow((Row)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Column)
			{
				this.StartSearchColumn((Column)arrayComponents[i]);
			}
		}
		
	}

	
	
	
	private void StartSearchRow(Row oRow)
	{
		Component[] arrayComponents = oRow.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (arrayComponents[i] instanceof MyE2_Button || arrayComponents[i] instanceof MyE2_ButtonWithKey)
			{
				this.vButtons.add((MyE2IF__Component)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Grid)
			{
				this.StartSearchGrid((Grid)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Row)
			{
				this.StartSearchRow((Row)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Column)
			{
				this.StartSearchColumn((Column)arrayComponents[i]);
			}
		}
		
	}
	
	private void StartSearchColumn(Column oCol)
	{
		
		Component[] arrayComponents = oCol.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (arrayComponents[i] instanceof MyE2_Button || arrayComponents[i] instanceof MyE2_ButtonWithKey)
			{
				this.vButtons.add((MyE2IF__Component)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Grid)
			{
				this.StartSearchGrid((Grid)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Row)
			{
				this.StartSearchRow((Row)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Column)
			{
				this.StartSearchColumn((Column)arrayComponents[i]);
			}
		}
		
	}


	public Vector get_vButtons()
	{
		return vButtons;
	}
	
	
	
}
