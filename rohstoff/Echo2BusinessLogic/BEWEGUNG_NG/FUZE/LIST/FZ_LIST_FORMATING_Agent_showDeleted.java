package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;


import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_FORMATING_Agent_showDeleted extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 	{
		RECORD_BEWEGUNG_VEKTOR_SPEC recVect = (RECORD_BEWEGUNG_VEKTOR_SPEC)oMAP.get_Record4MainTable();
		
		if (recVect.is_DELETED_YES() /*|| recVect.get_STATUS_cUF_NN("").equals(ATOM_Const.VEKTOR_STATUS.STORNIERT.db_val())*/) {
			oMAP.set_AllComponentsAsDeleted();
		}
	}

}
