package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;


//2011-01-10: erweitert bei relevanten ComponentMaps die markierung der Checkboxen auf den loeschgrund
public class BS__SubQueryAgentMarkDeletedRows extends XX_ComponentMAP_SubqueryAGENT {

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException 
	{
	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
	}

}
