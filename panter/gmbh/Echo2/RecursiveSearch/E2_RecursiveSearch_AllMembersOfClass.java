package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.myVectors.bibVECTOR;


public class E2_RecursiveSearch_AllMembersOfClass extends E2_RecursiveSearch_Component
{

	public E2_RecursiveSearch_AllMembersOfClass(String cSearchedClass) 	{
		super(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(), bibVECTOR.get_Vector(cSearchedClass), null);
	}

	
	public E2_RecursiveSearch_AllMembersOfClass(Vector<String> vSearchedClasses) {
		super(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(), vSearchedClasses, null);
	}

	
	
	
}
