package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;



/**
 * mit einer klasse, die aus dieser klasse abgeleitet wird, koennen Componenten
 * vom TYP MyE2IF__Component nach dem fuellen einer MAP nach 
 * beliebigen kriterien nachgeladen werden. Dies kann z.b. erfolgen,
 * wenn zusatzlabels nicht ueber outer-joins in die hauptabfrage 
 * definiert werden koennen 
 */
public abstract class XX_ComponentMAP_SubqueryAGENT 
{
	public abstract void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException;
	public abstract void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) 	throws myException;
	
}
