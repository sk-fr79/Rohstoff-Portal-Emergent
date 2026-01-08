package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;


public class E2_RecursiveSearch_AB_Basis extends E2_RecursiveSearch_Component
{

	public E2_RecursiveSearch_AB_Basis(String cSearchedClass)
	{
		super(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(), null, null);
		
		this.get_vSearchedTypes().add(cSearchedClass);
	}

	
	public E2_RecursiveSearch_AB_Basis(Vector<String> vSearchedClasses)
	{
		super(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(), vSearchedClasses, null);
	}

	
	
	
}
