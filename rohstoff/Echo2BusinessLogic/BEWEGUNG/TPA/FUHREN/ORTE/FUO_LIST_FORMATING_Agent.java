package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FUO_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{

	
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String cID_VPOS_TPA_FUHRE_ORT = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		if (new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT).is_DELETED_YES())
		{
			oMAP.set_AllComponentsAsDeleted();
		}
	}

}
