package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 * Sucht alle tochterkomponenten vom typ MyE2IF__Component
 * innerhalb des durchsuchten elements
 */
public class E2_RecursiveSearch_IF_Component
{	
	private Object 						oSearchedContainer = 		null;
	private Vector<MyE2IF__Component> 	vIF_Components = 			new Vector<MyE2IF__Component>();
	private Vector<Component> 			vAllComponents = 		   	new Vector<Component>();         // komponenten ausser grid-komponenten,enthaelt auch die vIF_Components
	

	private boolean bIncludeContainers  = true;
	
	public E2_RecursiveSearch_IF_Component(Object searchedContainer, boolean IncludeContainers) throws myException
	{
		super();
		this.oSearchedContainer = searchedContainer;
		this.bIncludeContainers = IncludeContainers;
		
		if (!(	oSearchedContainer instanceof Row || 
				oSearchedContainer instanceof Column || 
				oSearchedContainer instanceof Grid))
			throw new myException("Container can only search in ContentPane / Row / Column / Grid");

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
			if (arrayComponents[i] instanceof MyE2IF__Component)
			{
				if (this.bIncludeContainers)
					this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				else
				{
					if ( !(	arrayComponents[i] instanceof Grid || 
							arrayComponents[i] instanceof Row || 
							arrayComponents[i] instanceof Column))
						this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				}
			}

			
			if (arrayComponents[i] instanceof Grid)
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
			else if (arrayComponents[i] instanceof Component)
			{
				this.vAllComponents.add(arrayComponents[i]);
			}
				

		}
		
	}

	
	
	
	
	
	private void StartSearchRow(Row oRow)
	{
		Component[] arrayComponents = oRow.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{

			
			if (arrayComponents[i] instanceof MyE2IF__Component)
			{
				if (this.bIncludeContainers)
					this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				else
				{
					if ( !(arrayComponents[i] instanceof Grid || arrayComponents[i] instanceof Row || arrayComponents[i] instanceof Column))
						this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				}
			}

			if (arrayComponents[i] instanceof Grid)
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
			else if (arrayComponents[i] instanceof Component)
			{
				this.vAllComponents.add(arrayComponents[i]);
			}


		}
		
	}
	
	private void StartSearchColumn(Column oCol)
	{
		
		Component[] arrayComponents = oCol.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (arrayComponents[i] instanceof MyE2IF__Component)
			{
				if (this.bIncludeContainers)
					this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				else
				{
					if ( !(arrayComponents[i] instanceof Grid || arrayComponents[i] instanceof Row || arrayComponents[i] instanceof Column))
						this.vIF_Components.add((MyE2IF__Component)arrayComponents[i]);
				}
			}
			
			if (arrayComponents[i] instanceof Grid)
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
			else if (arrayComponents[i] instanceof Component)
			{
				this.vAllComponents.add(arrayComponents[i]);
			}
		}
		
	}


	public Vector<MyE2IF__Component> get_vE2IF_Components()
	{
		return vIF_Components;
	}

	public Vector<Component> get_vAllComponentsExtendContainers()
	{
		return this.vAllComponents;
	}

	
	
}
