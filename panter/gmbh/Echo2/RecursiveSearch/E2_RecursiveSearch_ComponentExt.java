package panter.gmbh.Echo2.RecursiveSearch;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/**
 * Rekursive Suche nach Komponenten mit generischem Typ
 * Es kann nur noch genau ein Typ gesucht werden.
 * 
 * @author manfred
 * @date   21.09.2011
 * @param <T>
 */


public class E2_RecursiveSearch_ComponentExt<T extends Component>
{	
	private Vector<T> 	m_vAllComponents = 		   	new Vector<T>();  
//	private Class<T> 	m_val;
	
	private String 		m_SearchedType  = null;
	private Vector<String> 		vSearchedTypes = 			new Vector<String>();
	

	
	/**
	 * Suche nach allen Components in searchedContainer
	 * ausgehende vom der ersten Content-Pane in der Session (Root)
	 * @param T val enthält eine Komponenten-Typ
	 */
	
	public E2_RecursiveSearch_ComponentExt( Class<T> val )
	{
		super();
		
		
		// den Klassennamen ermitteln
		m_SearchedType = val.getName();
		
		// wegen einer benutzten Klasse muss der Klassenname in ein Vector verpackt werden
		vSearchedTypes.add(m_SearchedType);
		
		this.StartSearch(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION());
		
	}
	
	
	/**
	 * Suche nach allen Components in searchedContainer
	 * @param searchedContainer
	 * @param vSearchedClasses enthaelt die namenstrings der gesuchten klassen (wenn null oder leerer Vector, dann werden alle components gefunden)
	 * @param vNegativeList enthaelt die namenstrings von klassen die nicht gefunden werden sollen  (hat prioritaet gegen die positivliste)
	 */
	public E2_RecursiveSearch_ComponentExt(Component searchedContainer, Class<T> val )
	{
		super();
		if (searchedContainer == null)
			return;

		// den Klassennamen ermitteln
		m_SearchedType = val.getName();
		
		// wegen einer benutzten Klasse muss der Klassenname in ein Vector verpackt werden
		vSearchedTypes.add(m_SearchedType);
		
		this.StartSearch(searchedContainer);
	}

	
	/**
	 * Suche nach allen Components in searchedContainer
	 * @param vSearchedContainer = Vector mit durchsuchten containern
	 * @param vSearchedClasses enthaelt die namenstrings der gesuchten klassen (wenn null oder leerer Vector, dann werden alle components gefunden)
	 * @param vNegativeList enthaelt die namenstrings von klassen die nicht gefunden werden sollen  (hat prioritaet gegen die positivliste)
	 */
	public E2_RecursiveSearch_ComponentExt(Vector<Component> vSearchedContainer, Class<T> val  )
	{
		super();
		
		// den Klassennamen ermitteln
		m_SearchedType = val.getName();
		
		// wegen einer benutzten Klasse muss der Klassenname in ein Vector verpackt werden
		vSearchedTypes.add(m_SearchedType);
		
		if (vSearchedContainer == null || vSearchedContainer.size()==0){
			return;
		}
		
		
		for (int i=0;i<vSearchedContainer.size();i++)
		{
			this.StartSearch((Component)vSearchedContainer.get(i));
		}
	}

	
	
	/**
	 * Sucht in dem gegebenen Container nach den Komponenten der Klasse
	 * @author manfred
	 * @date   21.09.2011
	 * @param oComponent
	 */
	private void StartSearch(Component oComponent)
	{
		Component[] arrayComponents = oComponent.getComponents();
		
		for (int i=0;i<arrayComponents.length;i++)
		{
			if (m_SearchedType != null)
			{
				SearchMemberNameInInheritanceLine oSearch = 
					new SearchMemberNameInInheritanceLine(arrayComponents[i].getClass(),this.vSearchedTypes);
								
				if (oSearch.get_bFound() )
				{
					this.m_vAllComponents.add((T)arrayComponents[i]);
				}
			}
			
			this.StartSearch(arrayComponents[i]);
		}
	}

	
	/**
	 * Gibt alle gefundenen Objekte zurück
	 * @author manfred
	 * @date   21.09.2011
	 * @return
	 */
	public Vector<T> get_vAllComponents()
	{
		return this.m_vAllComponents;
	}
	
	/**
	 * gibt das als erstes gefundene Objekt zurück
	 * @author manfred
	 * @date   21.09.2011
	 * @return
	 */
	public T getFirstComponent(){
		if (this.m_vAllComponents.size() > 0){
			return m_vAllComponents.elementAt(0);
		} else{
			return null;
		}
	}
	
	
	/**
	 * Wenn mehr als ein Element gefunden wurde, wird eine Exception geworfen,
	 * sonst das Element oder null.
	 * 
	 * @author manfred
	 * @date   21.09.2011
	 * @return
	 * @throws myException
	 */
	public T getSingleComponent() throws myException{
		if (this.m_vAllComponents.size() > 1){
			throw new myException(new MyString("Es wurde mehr als eine Komponente gefunden. Typ: ").CTrans() + m_SearchedType );
		}
		return getFirstComponent();
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





	
}
