package panter.gmbh.Echo2.ListAndMask.List.Search;

import panter.gmbh.indep.exceptions.myException;



/**
 * search-agent, der die Suche an eine andere einheit weitergibt
 */
public abstract class E2_DataSearchAgentAbstract
{

	public abstract void StartSearch(E2_DataSearch oDataSearch, E2_SearchDefinition oSpecialDef) throws myException;
	public abstract void ResetSearch(E2_DataSearch oDataSearch, boolean bForceRebuild) throws myException;
	
}
