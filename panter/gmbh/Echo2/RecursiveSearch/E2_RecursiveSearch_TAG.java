package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import org.apache.commons.collections.ListUtils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

import nextapp.echo2.app.Component;



/**
 * @author martin
 * Sucht alle tochterkomponenten vom typ Component
 * innerhalb des durchsuchten elements
 */
public class E2_RecursiveSearch_TAG
{	
	private Vector<MyE2IF__Component > 	vAllComponents = 		   	new Vector<MyE2IF__Component>();         
	private Vector<E2_SEARCH_TAGS.SEARCH_TAGS> 				vSearchedTags = 			new Vector<E2_SEARCH_TAGS.SEARCH_TAGS>();
	

	/**
	 * Suche in alle komponenten nach getaggeten elementen 
	 * @param searchedContainer
	 * @param vSearched_Tags
	 */
	public E2_RecursiveSearch_TAG(Component searchedContainer, Vector<E2_SEARCH_TAGS.SEARCH_TAGS> vSearched_Tags)
	{
		super();
		if (searchedContainer == null)
		{
			searchedContainer = bibE2.GET_FIRST_CONTENTPANE_IN_SESSION();
		}
			
		
		if (vSearched_Tags != null)
			this.vSearchedTags.addAll(vSearched_Tags);
		
		this.StartSearch(searchedContainer);
	}

	
	/**
	 * Suche in alle komponenten nach getaggeten elementen 
	 * @param searchedContainer
	 * @param vSearched_Tags
	 */
	public E2_RecursiveSearch_TAG(Vector<E2_SEARCH_TAGS.SEARCH_TAGS> vSearched_Tags)
	{
		super();
		
		if (vSearched_Tags != null)
			this.vSearchedTags.addAll(vSearched_Tags);
		
		this.StartSearch(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION());
	}

	
	/**
	 * Suche in alle komponenten nach getaggeten elementen 
	 * @param searchedContainer
	 * @param vSearched_Tags
	 */
	public E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS cSearched_Tag)
	{
		super();
		
		if (cSearched_Tag != null)
			this.vSearchedTags.add(cSearched_Tag);
		
		this.StartSearch(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION());
	}



	
	private void StartSearch(Component oComponent)
	{
		Component[] arrayComponents = oComponent.getComponents();
		
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (this.vSearchedTags.size() != 0)
			{
				if (arrayComponents[i] instanceof MyE2IF__Component)
				{
					Vector<E2_SEARCH_TAGS.SEARCH_TAGS> vComponentTags = ((MyE2IF__Component)arrayComponents[i]).EXT().get_vSEARCH_TAGS();
					
					if (ListUtils.retainAll(this.vSearchedTags, vComponentTags).size()>0)
					{
						this.vAllComponents.add((MyE2IF__Component)arrayComponents[i]);
					}
				}
			}
			this.StartSearch(arrayComponents[i]);
		}
	}

	
	
	public Vector<MyE2IF__Component> get_vAllComponents()
	{
		return this.vAllComponents;
	}

	public MyE2IF__Component get_SingleFoundComponentThrowExWhenNotFound() throws myException
	{
		if (this.vAllComponents.size()==1)
		{
			return this.vAllComponents.get(0);
		}
		
		throw new myException(this,"No single component with tags found ....");
	}

	public MyE2IF__Component get_SingleFoundComponent() throws myException
	{
		if (this.vAllComponents.size()==1)
		{
			return this.vAllComponents.get(0);
		}
		
		return null;
	}

	
	
	/**
	 * Suchliste:
	 * hier werden strings angehaengt wie z.B. E2_BasicModuleContainer.class.getName();
	 * @return
	 */
	public Vector<E2_SEARCH_TAGS.SEARCH_TAGS> get_vSearchedTags()
	{
		return vSearchedTags;
	}


	
}
