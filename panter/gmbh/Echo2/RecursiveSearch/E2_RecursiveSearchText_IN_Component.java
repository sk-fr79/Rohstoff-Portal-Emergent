package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;

/*
 * sucht einen TextField oder TextArea innerhalb einer componente
 */
public class E2_RecursiveSearchText_IN_Component
{
	private Vector<MyE2IF__Component> vTextObjects  = 	   new Vector<MyE2IF__Component>();     // vector enthaelt textFields oder TextAreas

	public E2_RecursiveSearchText_IN_Component(Component searchedContainer)
	{
		super();
		this.StartSearch(searchedContainer);
	}

	
	private void StartSearch(Component oComponent)
	{
		Component[] arrayComponents = oComponent.getComponents();
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (arrayComponents[i] instanceof MyE2_TextField || arrayComponents[i] instanceof MyE2_TextArea)
			{
				this.vTextObjects.add((MyE2IF__Component)arrayComponents[i]);
			}
			else if (arrayComponents[i] instanceof Component)
			{
				this.StartSearch((Component)arrayComponents[i]);
			}
		}
		
	}
	
	
	public Vector<MyE2IF__Component> get_vTextObjects()
	{
		return vTextObjects;
	}
	
}
