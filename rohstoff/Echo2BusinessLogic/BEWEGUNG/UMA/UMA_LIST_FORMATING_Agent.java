package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;


import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class UMA_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		boolean bIsLocked = S.NN(oUsedResultMAP.get_UnFormatedValue("ABGESCHLOSSEN")).equals("Y");
		boolean bIsDeleted = S.NN(oUsedResultMAP.get_UnFormatedValue("DELETED")).equals("Y");
		
		((UMA_LIST_BT_LOCK_UMA_KONTRAKT)oMAP.get__Comp(UMA_CONST.NAME_OF_LOCK_UNLOCK_BUTTON)).set_RED_GREEN_LockButton(bIsLocked);
		
		
		if (bIsDeleted)
		{
			oMAP.set_AllComponentsAsDeleted(bibALL.get_Vector("DEL_DATE", "DEL_GRUND","DEL_KUERZEL"));
			((UMA_LIST_BT_LOCK_UMA_KONTRAKT)oMAP.get__Comp(UMA_CONST.NAME_OF_LOCK_UNLOCK_BUTTON)).set_DELETED_LockButton();
		}

		UMA_LIST_GridSortenAnzeige oSortenListLiefer = (UMA_LIST_GridSortenAnzeige)oMAP.get__Comp(UMA_CONST.LIST_COMP_TOCHTER_LIEFERSORTEN);
		UMA_LIST_GridSortenAnzeige oSortenListRueckLiefer = (UMA_LIST_GridSortenAnzeige)oMAP.get__Comp(UMA_CONST.LIST_COMP_TOCHTER_RUECKLIEFERSORTEN);
		
		oSortenListLiefer.populate_Sorten();
		oSortenListRueckLiefer.populate_Sorten();
		
		
	}

}
