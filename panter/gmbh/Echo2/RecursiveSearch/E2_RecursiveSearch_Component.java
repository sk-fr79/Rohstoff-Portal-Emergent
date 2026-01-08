package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import nextapp.echo2.app.Component;



/**
 * @author martin
 * Sucht alle tochterkomponenten vom typ Component
 * innerhalb des durchsuchten elements
 */
public class E2_RecursiveSearch_Component
{	
	private Vector<Component> 	vAllComponents = 		   	new Vector<Component>();         


	private Vector<String> 		vSearchedTypes = 			new Vector<String>();
	private Vector<String> 		vNotSearchedClasses = 		new Vector<String>();
	
	/**
	 * Suche nach allen Components in searchedContainer
	 * @param searchedContainer
	 * @param vSearchedClasses enthaelt die namenstrings der gesuchten klassen (wenn null oder leerer Vector, dann werden alle components gefunden)
	 * @param vNegativeList enthaelt die namenstrings von klassen die nicht gefunden werden sollen  (hat prioritaet gegen die positivliste)
	 */
	public E2_RecursiveSearch_Component(Component searchedContainer, Vector<String> vSearchedClasses, Vector<String> vNegativeList)
	{
		super();
		if (searchedContainer == null)
			return;

		
		if (vSearchedClasses != null)
			this.vSearchedTypes.addAll(vSearchedClasses);
		
		if (vNegativeList != null)
			this.vNotSearchedClasses.addAll(vNegativeList);

		
		this.StartSearch(searchedContainer);
	}

	
	/**
	 * Suche nach allen Components in searchedContainer
	 * @param vSearchedContainer = Vector mit durchsuchten containern
	 * @param vSearchedClasses enthaelt die namenstrings der gesuchten klassen (wenn null oder leerer Vector, dann werden alle components gefunden)
	 * @param vNegativeList enthaelt die namenstrings von klassen die nicht gefunden werden sollen  (hat prioritaet gegen die positivliste)
	 */
	public E2_RecursiveSearch_Component(Vector<Component> vSearchedContainer, Vector<String> vSearchedClasses, Vector<String> vNegativeList)
	{
		super();
		if (vSearchedContainer == null || vSearchedContainer.size()==0)
			return;
		
		if (vSearchedClasses != null)
			this.vSearchedTypes.addAll(vSearchedClasses);
		
		if (vNegativeList != null)
			this.vNotSearchedClasses.addAll(vNegativeList);

		for (int i=0;i<vSearchedContainer.size();i++)
		{
			this.StartSearch((Component)vSearchedContainer.get(i));
		}
	}

	
	private void StartSearch(Component oComponent)
	{
		Component[] arrayComponents = oComponent.getComponents();
		
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (this.vSearchedTypes.size() != 0)
			{
				SearchMemberNameInInheritanceLine oSearch = 
					new SearchMemberNameInInheritanceLine(arrayComponents[i].getClass(),this.vSearchedTypes);
				
				SearchMemberNameInInheritanceLine oSearch2 = 
					new SearchMemberNameInInheritanceLine(arrayComponents[i].getClass(),this.vNotSearchedClasses);
				
				if (oSearch.get_bFound() && !oSearch2.get_bFound())
				{
					this.vAllComponents.add(arrayComponents[i]);
				}
			}
			else
			{
				SearchMemberNameInInheritanceLine oSearch2 = 
					new SearchMemberNameInInheritanceLine(arrayComponents[i].getClass(),this.vNotSearchedClasses);
				
				if (!oSearch2.get_bFound())
				{
					this.vAllComponents.add(arrayComponents[i]);
				}
			}
			this.StartSearch(arrayComponents[i]);
		}
	}

	
	
	public Vector<Component> get_vAllComponents()
	{
		return this.vAllComponents;
	}
	
	
	/**
	 * Suchliste:
	 * hier werden strings angehaengt wie z.B. E2_BasicModuleContainer.class.getName();
	 * @return
	 */
	public Vector<String> get_vSearchedTypes()
	{
		return vSearchedTypes;
	}


	/**
	 * Anti-Suchliste:
	 * hier werden strings angehaengt wie z.B. E2_BasicModuleContainer.class.getName();
	 * @return
	 */
	public Vector<String> get_vNotSearchedClasses()
	{
		return vNotSearchedClasses;
	}


	
}
