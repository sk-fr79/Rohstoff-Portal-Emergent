package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSA_P_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
	
	
		
		
	}

}
