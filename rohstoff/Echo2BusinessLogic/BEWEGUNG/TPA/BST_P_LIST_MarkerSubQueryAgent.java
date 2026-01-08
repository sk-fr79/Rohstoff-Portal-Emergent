package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BST_P_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		RECORD_VPOS_TPA  oRec_VPOS_TPA = new RECORD_VPOS_TPA(oUsedResultMAP.get_cUNFormatedROW_ID());
		
		if (oRec_VPOS_TPA.is_DELETED_YES())
		{
			oMAP.set_AllComponentsAsDeleted();
		}
		
		if (oRec_VPOS_TPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).is_IST_STORNIERT_YES())
		{
			((BST__ButtonStornoInfo)oMAP.get__Comp(BST__CONST.HASH_KEY_POSITION_STORNO_INFO))._INIT(oRec_VPOS_TPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF());
		}
		
		
	}

}
