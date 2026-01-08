package panter.gmbh.Echo2.RecursiveSearch;

import java.util.Vector;

import panter.gmbh.indep.bibALL;



/**
 * @author martin
 * sucht einen class-name in der vererbungslinie einer klasse (oder mehrerer klassen)
 */
public class SearchMemberNameInInheritanceLine 
{
	private	Vector<String>  	vSearchedNames = null; 
	private boolean 			bFound = false;

	/**
	 * @param checkClass
	 * @param SearchedNames  (String-Vector mit klassennamen, die gefunden werden sollen)
	 */
	public SearchMemberNameInInheritanceLine(Class checkClass, Vector<String> SearchedNames) 
	{
		super();
		this.vSearchedNames = 	SearchedNames;
		
		if (this.vSearchedNames==null)
			this.vSearchedNames = new Vector<String>();
		
		// nur wenn eine Vergleichsliste uebergeben wird, kann auch etwas gefunden werden
		if (this.vSearchedNames.size()>0)
			this.bFound = bCheck(checkClass);
		
	}
	
	
	private boolean bCheck(Class oCheckedClass)
	{
		
		// falls er ganz oben angekommen ist, dann nix gefunden
		if (oCheckedClass.getName().equals(Object.class.getName()))
			return false;
		
		// vMembernames enthaelt die klasse selbst und ihre interfaces
		Vector<String> vMembernames = new Vector<String>();
		
		vMembernames.add(oCheckedClass.getName());
		for (int i=0;i<oCheckedClass.getInterfaces().length;i++)
		{
			vMembernames.add(oCheckedClass.getInterfaces()[i].getName());
		}

		if (bibALL.get_bVectorsHaveOneSameString(this.vSearchedNames, vMembernames))
		{
			return true;
		}
		else
		{
			return bCheck(oCheckedClass.getSuperclass());
		}
	}


	public boolean get_bFound() 
	{
		return bFound;
	}
	
	
	
}
